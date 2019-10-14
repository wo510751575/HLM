package com.lj.business.member.aync;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.CommonConstant;
import com.lj.business.cp.dto.coupon.AddCoupon;
import com.lj.business.cp.dto.couponMemberRelation.AddCouponMemberRelation;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.emus.RealName;
import com.lj.business.cp.emus.RuleStatus;
import com.lj.business.cp.emus.ToCoupon;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.dto.AddPersonMemberByWx;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPage;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPageReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.emus.PushConfigType;
import com.lj.business.member.emus.PushTime;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.service.IWxPushConfigService;
import com.lj.business.member.utils.WeiChatUtils;
//import com.lj.business.recruit.dto.SendRecruitUrl;
//import com.lj.business.recruit.service.IExecutePushService;
//import com.lj.business.recruit.service.IStudentService;
import com.lj.business.weixin.dto.UpdateFriendsMemberInfo;
import com.lj.business.weixin.dto.imchat.SendChatByNewPerson;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxSmallProgramService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.IDistributeCache;
import com.lj.messagecenter.msg.service.INotifyService;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * 
 * 
 * 类说明：新增微信客户时,异步发送优惠券、个人名片、个人问候
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年12月6日
 */
@Service
public class AyncSendMessageByNewMemberService {

	private final static Logger logger = LoggerFactory.getLogger(AyncSendMessageByNewMemberService.class);
	// 商户service
	@Resource
	private IMerchantService merchantService;
	// 优惠券规则service
	@Resource
	private ICouponRuleService couponRuleService;
	// 优惠券service
	@Resource
	private ICouponService couponService;
	// 优惠券用户关联service
	@Resource
	private ICouponMemberRelationService couponMemberRelationService;
	// 消息通知service
	@Resource
	private INotifyService notifyService;
	// 配置消息
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Resource
	private ThreadPoolTaskExecutor taskExecutor;

	@Resource
	private IImChatInfoService imChatInfoService;

	@Autowired
	private IMerchantParamsService merchantParamsService;
	@Resource
	private IDistributeCache redis;
	@Resource
	private IShopTerminalService shopTerminalService;
	@Resource
	private IImFriendsInfoService imFriendsInfoService;

	@Resource
	private IWxPushConfigService wxPushConfigService;

	@Resource
	private IWxPublicAccountService wxPublicAccountService;

	@Resource
	private IWxSmallProgramService wxSmallProgramService;
	@Resource
	private IManagerMemberService managerMemberService;
	@Resource
	private IGuidMemberService guidMemberService;

	/**
	 * 
	 *
	 * 方法说明：向新增客户发送优惠券、个人名片、个人问候
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @param addPersonMemberByWx
	 * @param firstAdd            是否首次添加
	 * @author 曾垂瑜 CreateDate: 2017年11月24日
	 *
	 */
	public void sendMessage(final FindGmAssistantShopReturn guidMember,
			final FindShopTerminalReturn findShopTerminalReturn, final PersonMemberBase personMemberBase,
			final AddPersonMemberByWx addPersonMemberByWx, final boolean firstAdd) {
		final Integer addType = addPersonMemberByWx.getAddType();
		final Integer gmAddFlag = addPersonMemberByWx.getGmAddFlag();

		// 控制2秒内部只能发送一次
		if (redis.get("REDIS-KEY-PUSH-" + personMemberBase.getNoWx()) != null) {
			return;
		}
		redis.set("REDIS-KEY-PUSH-" + personMemberBase.getNoWx(), personMemberBase.getNoWx(), 2);

		taskExecutor.execute(new Runnable() { // 通过线程池发送
			@Override
			public void run() {
				logger.info("向新增的客户[{}]发送个人问题、个人名片和优惠券等", personMemberBase.getMemberNo());
				/**
				 * 线程沉睡2秒，保证创建客户主流程提交事务，避免提交事务发生查询不到客户信息的异常出现
				 * 发送优惠券、名片等业务逻辑不是创建客户的主流程，应使用异步消息进行广播再处理 XXX
				 */
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					logger.error("沉睡当前线程失败", e);
				}

				// 首次添加才推送
				if (firstAdd) {
					// 1、自动推送消息
					FindWxPushConfigPage findWxPushConfigPage = new FindWxPushConfigPage();
					findWxPushConfigPage.setMerchantNo(findShopTerminalReturn.getMerchantNo());
					findWxPushConfigPage.setPushTime(PushTime.CLAIMED.name());
					findWxPushConfigPage.setNoWx(findShopTerminalReturn.getNoWx());
					List<FindWxPushConfigPageReturn> configList = wxPushConfigService
							.findWxPushConfigToPush(findWxPushConfigPage);
					logger.info("终端推送列表：{}", configList);
					if (null != configList && !configList.isEmpty()) { // 优先推送终端微信配置的记录
						pushByPushConfig(findShopTerminalReturn, personMemberBase, configList); // 按终端推送
					} else { // 从商户参数配置表中获取需推送的配置消息
						sendMessageByMerchantParam(findShopTerminalReturn, personMemberBase, addType, gmAddFlag,
								firstAdd);
					}
				}
				// 2、处理认领前客户上传的数据：聊天记录、朋友圈等
				processByNotClaimed(guidMember, personMemberBase, addType, addPersonMemberByWx);

			}
		});
	}

	/**
	 * 
	 *
	 * 方法说明：根据终端微信推送自定义消息
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @param configList
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void pushByPushConfig(FindShopTerminalReturn findShopTerminalReturn, PersonMemberBase personMemberBase,
			List<FindWxPushConfigPageReturn> configList) {
		String orgType = configList.get(0).getOrgType();
		for (FindWxPushConfigPageReturn config : configList) {
			// 如果按终端配置了推送消息，那按商户统一配置的推送消息就不发送
			if (orgType.equals(config.getOrgType())) {
				switch (PushConfigType.valueOf(config.getType())) {
				case GREET: // 推送话术、问候语
					sendGreetingsByPushConfig(findShopTerminalReturn, personMemberBase, config.getContent());
					break;
				case IMAGE: // 推送图片
					sendImgByPushConfig(findShopTerminalReturn, personMemberBase, config.getShareIcon());
					break;
//				case MGR_CARD:	// 推送店长名片
//					sendManagerCardByPushConfig(findShopTerminalReturn, personMemberBase);
//					break;
				case SHARE: // 推送分享
					sendShareByPushConfig(findShopTerminalReturn, personMemberBase, config);
					break;
				case PA: // 推送公众号
					sendPublicAccountByPushConfig(findShopTerminalReturn, personMemberBase, config);
					break;
				case SP: // 推送小程序
					sendSmallProgramByPushConfig(findShopTerminalReturn, personMemberBase, config);
					break;
				case GM_CARD:
					sendGmCardByPushConfig(findShopTerminalReturn, personMemberBase);
					break;
				default:
					logger.error("不支持的推送数据： {}", config);
					break;
				}
			}
		}
	}

	/**
	 * 
	 *
	 * 方法说明：从商户参数配置表中获取需推送的配置消息
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @param addType
	 * @param gmAddFlag
	 * @param firstAdd
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void sendMessageByMerchantParam(FindShopTerminalReturn findShopTerminalReturn,
			PersonMemberBase personMemberBase, Integer addType, Integer gmAddFlag, boolean firstAdd) {
		FindMerchantParams findMerchantParams = new FindMerchantParams();
		String groupName = "push_switch";// 组名
		findMerchantParams.setGroupName(groupName);
		findMerchantParams.setMerchantNo(findShopTerminalReturn.getMerchantNo());
		Map<String, String> paramsMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);

		if (MapUtils.isNotEmpty(paramsMap)) {
			// 客户认领后发送个人问候(默认为认领后发送)
			if (paramsMap.get("greetings") != null && "ON".equals(paramsMap.get("greetings"))
					&& !"beforeClaim".equals(paramsMap.get("greetingsTime"))) {
				sendGreetingsByMerchantParam(findShopTerminalReturn, personMemberBase, paramsMap.get("greetingsInfo"));
			}

			if (paramsMap.get("card") != null && "ON".equals(paramsMap.get("card"))) {
				// 发送个人名片
				sendCard(findShopTerminalReturn, personMemberBase);
			}
			if (paramsMap.get("coupon") != null && "ON".equals(paramsMap.get("coupon"))) {
				// 发送优惠券
				if (firstAdd) {
					if (gmAddFlag != null && gmAddFlag == 1) {// 导购主动添加
						sendCoupon(findShopTerminalReturn, personMemberBase);
					}
				}
			}

			// 发送H5链接，带用户信息，场景：用来补充会员信息
			if (paramsMap.get("H5") != null && "ON".equals(paramsMap.get("H5")) && paramsMap.get("2.H5URL") != null) {
				// 1.发送欢迎词
				// 2.发送H5链接 ，带用户信息
				// 3.回复感谢词

				String welcome = paramsMap.get("1.welcome") == null ? "" : paramsMap.get("1.welcome");
				if (StringUtils.isNotEmpty(welcome)) {
					String content = String.format(paramsMap.get("1.welcome"),
							findShopTerminalReturn.getMerchantName());
//					String content = "感谢您加入"+guidMember.getMerchantName()+"，请点击链接补充个人信息";
					sendText(findShopTerminalReturn, personMemberBase.getMemberNo(), content);
				}

				String url = String.format(paramsMap.get("2.H5URL"), personMemberBase.getMemberNo(),
						findShopTerminalReturn.getNoWx());
				sendText(findShopTerminalReturn, personMemberBase.getMemberNo(), url);
			}

		}
	}

	/**
	 * 
	 *
	 * 方法说明：处理未认领前客户上传的数据
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @param addType
	 * @param addPersonMemberByWx
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void processByNotClaimed(FindGmAssistantShopReturn guidMember, PersonMemberBase personMemberBase,
			Integer addType, AddPersonMemberByWx addPersonMemberByWx) {
		// 将未认领前，客户发给导购的聊天记录发给导购
		sendChatByNewPerson(guidMember, personMemberBase, addType);

		// 客户未认领前发的朋友圈、评论和点赞没有客户信息，认领后需更新其客户信息
		updateMemberInfoByFriends(guidMember, personMemberBase, addPersonMemberByWx);
	}

	/**
	 * 
	 *
	 * 方法说明：处理与招聘有关的业务
	 *
	 * @param guidMember
	 * @param personMemberBase
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	@Deprecated
	private void processRecruit(GuidMember guidMember, PersonMemberBase personMemberBase) {
		Map<String, String> map = localCacheSystemParams.getSystemParamGroup("recruit", "recruit");
//		logger.debug("配置参数"+map);
		String merchantNo = map.get("merchantNo");
		// 如果是招聘商户则执行发送链接方法
		if (merchantNo.equals(guidMember.getMerchantNo())) {
			FindShopTerminalReturn shopTerminalReturn = shopTerminalService
					.findShopTerminalNormal(guidMember.getNoWx());

			logger.error("终端机类型为空无法匹配类型,发送链接失败！：" + shopTerminalReturn.getNoWx());
		}
	}

	/**
	 * 
	 * 方法说明：发送个人问候
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @param greetingsInfo    商户自定义问候话术，目前只支持配置一条
	 * @author 彭俊霖 CreateDate: 2017年12月13日
	 *
	 */
	public void sendGreetingsByMerchantParam(final FindShopTerminalReturn findShopTerminalReturn,
			final PersonMemberBase personMemberBase, String greetingsInfo) {
		try {
			// 如果商户没有自定义问候话术，则从基础配置信息里随机获取一条
			if (StringUtils.isEmpty(greetingsInfo)) {
				Map<String, String> personalGreetingsMap = localCacheSystemParams.getSystemParamGroup("ms",
						"personalGreetings");
				String greetingKey = "greeting" + ((int) (personalGreetingsMap.size() * Math.random()) + 1);
				greetingsInfo = personalGreetingsMap.get(greetingKey);
			}
			/*
			 * Map<String, String> personalGreetingsMap =
			 * localCacheSystemParams.getSystemParamGroup("ms", "personalGreetings"); String
			 * greetingKey="greeting"+((int)(personalGreetingsMap.size()*Math.random())+1);
			 * greetingsInfo = personalGreetingsMap.get(greetingKey);
			 */
			// 获取随机数量和内容的问候表情
			Object[] arr = new Object[WeiChatUtils.getOccur(greetingsInfo, "{")];
			arr[0] = StringUtils.toString(findShopTerminalReturn.getAlias());// 微信号
			Map<String, String> greetingEmojiMap = localCacheSystemParams.getSystemParamGroup("ms", "greetingEmoji");
			int emojiSize = arr.length - 1;// 表情坑位数
			for (int i = 1; i <= emojiSize; i++) {
				arr[i] = "";
				for (int j = 0; j < (int) (3 * Math.random()) + 1; j++) {// 暂定一个坑随机填1~3个表情
					String emojiKey = "emoji" + ((int) (greetingEmojiMap.size() * Math.random()) + 1);
					arr[i] += greetingEmojiMap.get(emojiKey);
				}
			}
			// 整合话术及表情,生成问候语
			greetingsInfo = MessageFormat.format(greetingsInfo, arr);

			// 发送消息
			SendImChatInfo sendImChatInfo = new SendImChatInfo();
			sendImChatInfo.setSenderFlag(1);
			sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
			sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
			sendImChatInfo.setType(ChatInfoType.TEXT.getCode());
			sendImChatInfo.setContent(greetingsInfo);// 个人问候
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("发送导购个人问候：{}", sendImChatInfo);
			imChatInfoService.sendChat(sendImChatInfo);

		} catch (Exception e) {
			logger.error("发送导购个人问候失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：发送个人名片
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @author 曾垂瑜 CreateDate: 2017年12月6日
	 *
	 */
	public void sendCard(final FindShopTerminalReturn findShopTerminalReturn, final PersonMemberBase personMemberBase) {
		try {
			SendImChatInfo sendImChatInfo = new SendImChatInfo();
			sendImChatInfo.setSenderFlag(1);
			sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
			sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
			sendImChatInfo.setType(ChatInfoType.CARTE_SHARE.getCode()); // 导购个人名片
			Map<String, String> groupMap = localCacheSystemParams.getSystemParamGroup("api", "common");
			if (StringUtils.isNotEmpty(findShopTerminalReturn.getHeadurl())) {
				String commonUrl = groupMap.get("commonUrl");
				commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() - 1) : commonUrl;
				sendImChatInfo.setResources(commonUrl + findShopTerminalReturn.getHeadurl());
			} else { // 没有设置头像，取默认头像
				sendImChatInfo.setResources(groupMap.get("guidDHUrl"));
			}
			sendImChatInfo.setShareTitle(findShopTerminalReturn.getAlias());
			sendImChatInfo
					.setShareDes("微信：" + findShopTerminalReturn.getAlias() + " \n" + findShopTerminalReturn.getNoWx());
			sendImChatInfo.setShareUrl(groupMap.get("gmCardUrl") + findShopTerminalReturn.getNoWx());
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("发送导购个人名片：{}", sendImChatInfo);
			imChatInfoService.sendChat(sendImChatInfo);
		} catch (Exception e) {
			logger.error("发送导购个人名片失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：发送优惠券
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @author 曾垂瑜 CreateDate: 2017年12月6日
	 *
	 */
	public void sendCoupon(final FindShopTerminalReturn findShopTerminalReturn,
			final PersonMemberBase personMemberBase) {
		try {
			// LOGGER.info(">>>>>>>>>>>>>>>>>>>>开始执行自动发送优惠券");
			FindCouponRule findCouponRule = new FindCouponRule();
			findCouponRule.setMerchantNo(findShopTerminalReturn.getMerchantNo());
//			findCouponRule.setShopNo(guidMember.getShopNo());
			findCouponRule.setRuleStatus(RuleStatus.YES.toString());
			findCouponRule.setToCoupon(ToCoupon.NONE.toString());
			findCouponRule.setRealName(RealName.NO.toString());
			findCouponRule.setProduce("NO");
			// LOGGER.info(">>>>>>>>>>>>>>>>>>>>查询条件：{}",JsonUtils.jsonFromObject(findCouponRule));
			List<FindCouponRuleReturn> findCouponRuleReturnList = couponRuleService.findCouponRuleList(findCouponRule);
			if (CollectionUtils.isEmpty(findCouponRuleReturnList)) {
				// LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>当前商户没有配置优惠券规则<<<<<<<<<<<<<<<<<<<<<<");
				return;
			}

			Collections.sort(findCouponRuleReturnList, new Comparator<FindCouponRuleReturn>() {
				@Override
				public int compare(FindCouponRuleReturn o1, FindCouponRuleReturn o2) { // 数组倒序
					return (int) (o2.getCouponNotes() - o1.getCouponNotes());
				}

			});

			// 选择折扣力度最大的优惠券
			FindCouponRuleReturn findCouponRuleReturn = findCouponRuleReturnList.get(0);

			// LOGGER.info("优惠券规则json为：{}",JsonUtils.jsonFromObject(findCouponRuleReturn));

			AddCoupon addCoupon = new AddCoupon();
			addCoupon.setMerchantNo(findShopTerminalReturn.getMerchantNo());
			addCoupon.setMerchantName(findShopTerminalReturn.getMerchantName());
//			addCoupon.setShopNo(guidMember.getShopNo());
//			addCoupon.setShopName(guidMember.getShopName());
			addCoupon.setRuleNo(findCouponRuleReturn.getCode());
			addCoupon.setCouponNo(GUID.generateCode());
			addCoupon.setCouponStatus(CouponStatus.USED.toString());
			addCoupon.setUseDate(new Date());
			addCoupon.setCreateDate(new Date());
			couponService.addCoupon(addCoupon); // 新增优惠券

			AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
//			addCouponMemberRelation.setMemberNoGm(guidMember.getMemberNo());
//			addCouponMemberRelation.setMemberNameGm(guidMember.getMemberName());
			addCouponMemberRelation.setMemberNo(personMemberBase.getMemberNo());
			addCouponMemberRelation.setMemberName(personMemberBase.getMemberName());
			addCouponMemberRelation.setCouponNo(addCoupon.getCouponNo());
			addCouponMemberRelation.setCouponStatus(addCoupon.getCouponStatus());
			addCouponMemberRelation.setUseDate(new Date());
			addCouponMemberRelation.setCreateDate(new Date());
			couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation); // 新增优惠券用户绑定关系

//			Map<String, String> paramMap = new HashMap<String, String>();
			String cp_result_url = localCacheSystemParams.getSystemParam("cp", "result_url", "cp_result_url");
			// 优惠券领取url
			String resultUrl = String.format(cp_result_url, findShopTerminalReturn.getNoWx(),
					personMemberBase.getMemberNo(), addCoupon.getCouponNo());
//			paramMap.put("resultUrl", resultUrl);
//			paramMap.put("noWx", personMemberBase.getNoWx()); // 微信号
//			paramMap.put("remark", findCouponRuleReturn.getCouponRemark()); // 优惠券备注
//			paramMap.put("title", findCouponRuleReturn.getCouponName()); // 推送标题

			FindMerchantDto findMerchantDto = new FindMerchantDto();
			findMerchantDto.setMerchantNo(findCouponRuleReturn.getMerchantNo());
			FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);

			// 通过中控版本扫码（1导购扫码、2客户扫码）进来的客户，则通知IM功能推送优惠券
			SendImChatInfo sendImChatInfo = new SendImChatInfo();
			sendImChatInfo.setSenderFlag(1);
			sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
			sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
			sendImChatInfo.setType(ChatInfoType.COUPON_SHARE.getCode()); // 分享优惠券
			if (findMerchantReturnDto != null && StringUtils.isNotEmpty(findMerchantReturnDto.getLogoAddr())) {
				String uploadUrl = localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl"); // 商户图片静态地址
				sendImChatInfo.setResources(uploadUrl + findMerchantReturnDto.getLogoAddr()); // 商户图标
			} else {
				String couponUrl = localCacheSystemParams.getSystemParam("api", "logo", "couponLogoUrl");
				sendImChatInfo.setResources(couponUrl);
			}
			sendImChatInfo.setShareTitle(findCouponRuleReturn.getCouponName());
			sendImChatInfo.setShareDes("说明:" + findCouponRuleReturn.getCouponRemark());
			sendImChatInfo.setShareUrl(resultUrl);
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("发送优惠券：{}", sendImChatInfo);
			imChatInfoService.sendChat(sendImChatInfo);

		} catch (Exception e) {
			logger.error("发送优惠券失败", e);
		}
	}

	/**
	 * 2.发送H5链接 ，带用户信息
	 * 
	 * @param guidMember
	 * @param personMemberBase
	 * @param greetingsInfo
	 */
	private void sendText(FindShopTerminalReturn findShopTerminalReturn, final String memberNo, String content) {
		try {

			// 发送消息
			SendImChatInfo sendImChatInfo = new SendImChatInfo();
			sendImChatInfo.setSenderFlag(1);
			sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
			sendImChatInfo.setMemberNo(memberNo);
			sendImChatInfo.setType(ChatInfoType.TEXT.getCode());
			sendImChatInfo.setContent(content);
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("发送H5链接：{}", sendImChatInfo);
			imChatInfoService.sendChat(sendImChatInfo);

		} catch (Exception e) {
			logger.error("发送H5链接失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：导购认领后，将客户发给导购的聊天记录发给导购
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @param addType
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月22日
	 *
	 */
	private void sendChatByNewPerson(FindGmAssistantShopReturn guidMember, PersonMemberBase personMemberBase,
			Integer addType) {
		/*
		 * if(addType != 2 || addType != 4) { return; }
		 */

		SendChatByNewPerson sendChatByNewPerson = new SendChatByNewPerson();
		sendChatByNewPerson.setMemberNoGm(guidMember.getAssistantNo());
		sendChatByNewPerson.setMemberNameGm(guidMember.getAssistantName());
		sendChatByNewPerson.setNoWxGm(guidMember.getNoWx());
		sendChatByNewPerson.setMemberNo(personMemberBase.getMemberNo());
		sendChatByNewPerson.setMemberName(personMemberBase.getMemberName());
		sendChatByNewPerson.setNoWx(personMemberBase.getNoWx());
		sendChatByNewPerson.setAlias(personMemberBase.getNoWxAlias());
//		sendChatByNewPerson.setShopNo(guidMember.getShopNo());
		try {
			imChatInfoService.sendChatByNewPerson(sendChatByNewPerson);
		} catch (Exception e) {
			logger.error("向导购" + guidMember.getAssistantNo() + "发送客户" + personMemberBase.getMemberNo() + "聊天记录失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：客户未认领前发的朋友圈、评论和点赞没有客户信息，认领后需更新其客户信息
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @param addPersonMemberByWx
	 *
	 * @author 曾垂瑜 CreateDate: 2018年5月4日
	 *
	 */
	private void updateMemberInfoByFriends(FindGmAssistantShopReturn guidMember, PersonMemberBase personMemberBase,
			AddPersonMemberByWx addPersonMemberByWx) {
		UpdateFriendsMemberInfo updateFriendsMemberInfo = new UpdateFriendsMemberInfo();
//		updateFriendsMemberInfo.setShopNo(guidMember.getShopNo());
		updateFriendsMemberInfo.setNoWxShop(guidMember.getNoWx());
		updateFriendsMemberInfo.setNoWx(personMemberBase.getNoWx());
//		updateFriendsMemberInfo.setBeginTime(addPersonMemberByWx.getAcceptTime());
		updateFriendsMemberInfo.setMemberNo(personMemberBase.getMemberNo());
		updateFriendsMemberInfo.setMemberName(personMemberBase.getMemberName());
		updateFriendsMemberInfo.setMemberNoGm(guidMember.getAssistantNo());
		updateFriendsMemberInfo.setMemberNameGm(guidMember.getAssistantName());
		try {
			imFriendsInfoService.updateFriendsMemberInfo(updateFriendsMemberInfo);
			logger.debug("已更新客户未认领前发的朋友圈、评论和点赞的客户信息：{}", updateFriendsMemberInfo);
		} catch (Exception e) {
			logger.error("更新客户未认领前发的朋友圈、评论和点赞的客户信息失败：{}", updateFriendsMemberInfo, e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：发送图片
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @param imgUrl
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void sendImgByPushConfig(FindShopTerminalReturn findShopTerminalReturn, PersonMemberBase personMemberBase,
			String imgUrl) {
		try {
			if (StringUtils.isEmpty(imgUrl)) {
				return;
			}

			if (!StringUtils.isHttp(imgUrl)) {
				Map<String, String> groupMap = localCacheSystemParams.getSystemParamGroup("api", "common");
				String commonUrl = groupMap.get("commonUrl");
				commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() - 1) : commonUrl;
				imgUrl = commonUrl + imgUrl;
			}

			SendImChatInfo sendImChatInfo = new SendImChatInfo();
			sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
			sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
			sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
			sendImChatInfo.setType(ChatInfoType.IMG.getCode());
			sendImChatInfo.setResources(imgUrl);
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("客户认领后，自定义推送图片消息：{}", sendImChatInfo);
			imChatInfoService.sendChat(sendImChatInfo);
		} catch (Exception e) {
			logger.error("客户认领后，自定义推送图片消息失败", e);
		}
	}

	private int appearNumber(String srcText, String findText) {
		int count = 0;
		Pattern p = Pattern.compile(findText);
		Matcher m = p.matcher(srcText);
		while (m.find()) {
			count++;
		}
		return count;
	}

	/**
	 * 
	 *
	 * 方法说明：发送话术、问候语
	 *
	 * @param guidMember
	 * @param personMemberBase
	 * @param content
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	public void sendGreetingsByPushConfig(FindShopTerminalReturn findShopTerminalReturn,
			PersonMemberBase personMemberBase, String content) {
		try {
			if (StringUtils.isEmpty(content)) {
				return;
			}

			// 替换表情
			Map<String, String> greetingEmojiMap = localCacheSystemParams.getSystemParamGroup("ms", "greetingEmoji");
			String findText = "\\{emoji\\}";
			int num = appearNumber(content, findText);
			for (int i = 0; i < num; i++) {
				String emojiKey = "emoji" + ((int) (greetingEmojiMap.size() * Math.random()) + 1);
				content = content.replaceFirst(findText, greetingEmojiMap.get(emojiKey));
			}
//			logger.info("sendGreetings content:[{}]", content);
			// 替换商城地址
//			String mecUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.name(), "qrcode", "url_pre");
//			content = content.replaceAll("\\{shopUrl\\}", mecUrl + "?shopNo=" + guidMember.getShopNo());
			// 替换终端编号
//			content = content.replaceAll("\\{shopNo\\}", guidMember.getShopNo());
			// 替换终端名称
//			content = content.replaceAll("\\{shopName\\}", guidMember.getShopName());
			// 替换店长姓名
//			List<ManagerMemberReturnDto> managerMemberByShop = managerMemberService.findManagerMemberByShop(guidMember.getShopNo());
//			if (CollectionUtils.isNotEmpty(managerMemberByShop)) {
//			    logger.info("sendGreetings终端编号[{}]，店长手机号[{}]", addFriends.getShopNo(), managerMemberByShop.get(0).getMobile());
//			    content = content.replaceAll("\\{mgrName\\}", managerMemberByShop.get(0).getMemberName());
//            }
			// 替换终端昵称
			content = content.replaceAll("\\{stName\\}", findShopTerminalReturn.getNickname());
			// 替换终端微信
			content = content.replaceAll("\\{noWxGm\\}", findShopTerminalReturn.getNoWx());
			// 替换客户微信
			content = content.replaceAll("\\{noWx\\}", personMemberBase.getNoWx());

			// 发送消息
			SendImChatInfo sendImChatInfo = new SendImChatInfo();
			sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
			sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
			sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
			sendImChatInfo.setType(ChatInfoType.TEXT.getCode());
			sendImChatInfo.setContent(content);
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("客户认领后，自定义推送话术、问候语：{}", sendImChatInfo);
			imChatInfoService.sendChat(sendImChatInfo);
		} catch (Exception e) {
			logger.error("客户认领后，自定义推送话术、问候语失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：发送店长名片
	 *
	 * @param addFriends
	 *
	 * @author 许新龙 CreateDate: 2018年7月25日
	 *
	 */
	@Deprecated
	private void sendManagerCardByPushConfig(FindShopTerminalReturn findShopTerminalReturn,
			PersonMemberBase personMemberBase) {
		/*
		 * try { List<ManagerMemberReturnDto> managerMemberByShop =
		 * managerMemberService.findManagerMemberByShop(guidMember.getShopNo()); if
		 * (CollectionUtils.isNotEmpty(managerMemberByShop)) { ManagerMemberReturnDto
		 * managerMember = managerMemberByShop.get(0); FindGuidMemberPage
		 * findGuidMemberPage = new FindGuidMemberPage();
		 * findGuidMemberPage.setMobile(managerMember.getMobile()); //
		 * findGuidMemberPage.setShopNo(guidMember.getShopNo());
		 * List<FindGuidMemberPageReturn> guidMembers =
		 * guidMemberService.findGuidMembers(findGuidMemberPage); if
		 * (CollectionUtils.isNotEmpty(guidMembers)) { FindGuidMemberPageReturn
		 * guidMemberShop = guidMembers.get(0);
		 * 
		 * //发送消息 SendImChatInfo sendImChatInfo = new SendImChatInfo();
		 * sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
		 * sendImChatInfo.setMemberNoGm(guidMember.getMemberNo());
		 * sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
		 * sendImChatInfo.setType(ChatInfoType.CARTE_SHARE.getCode());
		 * 
		 * Map<String, String> groupMap =
		 * localCacheSystemParams.getSystemParamGroup("api", "common");
		 * if(StringUtils.isNotEmpty(guidMemberShop.getHeadAddress())) { String
		 * commonUrl = groupMap.get("commonUrl"); commonUrl = commonUrl.endsWith("/") ?
		 * commonUrl.substring(0, commonUrl.length() -1) : commonUrl;
		 * sendImChatInfo.setResources(commonUrl + guidMemberShop.getHeadAddress()); }
		 * else { // 没有设置头像，取默认头像
		 * sendImChatInfo.setResources(groupMap.get("guidDHUrl")); }
		 * 
		 * sendImChatInfo.setShareTitle(guidMemberShop.getMemberName());
		 * sendImChatInfo.setShareDes("电话：" + guidMemberShop.getMobile() + " \n" +
		 * guidMemberShop.getShopName());
		 * sendImChatInfo.setShareUrl(groupMap.get("gmCardUrl") +
		 * guidMemberShop.getMemberNo());
		 * 
		 * sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
		 * sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
		 * logger.info("客户认领后，自定义推送店长名片：{}", sendImChatInfo);
		 * imChatInfoService.sendChat(sendImChatInfo); } } } catch(Exception e) {
		 * logger.error("客户认领后，自定义推送店长名片失败", e); }
		 */
	}

	/**
	 * 
	 *
	 * 方法说明：发送导购名片
	 *
	 * @param addFriends
	 *
	 * @author 许新龙 CreateDate: 2018年7月25日
	 *
	 */
	public void sendGmCardByPushConfig(FindShopTerminalReturn findShopTerminalReturn,
			PersonMemberBase personMemberBase) {
		try {
			// 发送消息
			SendImChatInfo sendImChatInfo = new SendImChatInfo();
			sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
			sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
			sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
			sendImChatInfo.setType(ChatInfoType.CARTE_SHARE.getCode());

			Map<String, String> groupMap = localCacheSystemParams.getSystemParamGroup("api", "common");
			if (StringUtils.isNotEmpty(findShopTerminalReturn.getHeadurl())) {
				String commonUrl = groupMap.get("commonUrl");
				commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() - 1) : commonUrl;
				sendImChatInfo.setResources(commonUrl + findShopTerminalReturn.getHeadurl());
			} else { // 没有设置头像，取默认头像
				sendImChatInfo.setResources(groupMap.get("guidDHUrl"));
			}

			sendImChatInfo.setShareTitle(findShopTerminalReturn.getAlias());
			sendImChatInfo.setShareDes("微信：" + findShopTerminalReturn.getAlias());
			sendImChatInfo.setShareUrl(groupMap.get("gmCardUrl") + findShopTerminalReturn.getNoWx());

			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("客户认领后，自定义推送导购名片：{}", sendImChatInfo);
			imChatInfoService.sendChat(sendImChatInfo);
		} catch (Exception e) {
			logger.error("客户认领后，自定义推送导购名片失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：发送分享类消息
	 *
	 * @param addFriends
	 * @param config
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void sendShareByPushConfig(FindShopTerminalReturn findShopTerminalReturn, PersonMemberBase personMemberBase,
			FindWxPushConfigPageReturn config) {
		try {

			String imgUrl = config.getShareIcon();
			if (!StringUtils.isHttp(imgUrl)) {
				Map<String, String> groupMap = localCacheSystemParams.getSystemParamGroup("api", "common");
				String commonUrl = groupMap.get("commonUrl");
				commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() - 1) : commonUrl;
				imgUrl = commonUrl + imgUrl;
			}
			SendImChatInfo sendImChatInfo = new SendImChatInfo();
			sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
			sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
			sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
			sendImChatInfo.setType(ChatInfoType.SHARE.getCode());
			sendImChatInfo.setResources(imgUrl);
			sendImChatInfo.setShareTitle(config.getShareTitle());
			sendImChatInfo.setShareDes(config.getShareDes());
			sendImChatInfo.setShareUrl(config.getShareUrl());
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
			logger.info("客户认领后，自定义推送分享消息：{}", sendImChatInfo);
			imChatInfoService.sendChat(sendImChatInfo);
		} catch (Exception e) {
			logger.error("客户认领后，自定义推送分享消息失败：", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：发送公众号消息
	 *
	 * @param addFriends
	 * @param config
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void sendPublicAccountByPushConfig(FindShopTerminalReturn findShopTerminalReturn,
			PersonMemberBase personMemberBase, FindWxPushConfigPageReturn config) {
		try {
			FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
			findWxPublicAccount.setCode(config.getShareCode());
			FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService
					.findWxPublicAccount(findWxPublicAccount);
			if (findWxPublicAccountReturn != null && CommonConstant.I_YES == findWxPublicAccountReturn.getStatus()) {
				SendImChatInfo sendImChatInfo = new SendImChatInfo();
				sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
				sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
				sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
				sendImChatInfo.setType(ChatInfoType.CARD.getCode());
				sendImChatInfo.setResources(findWxPublicAccountReturn.getPaLogo());
				sendImChatInfo.setShareTitle(findWxPublicAccountReturn.getPaName());
				sendImChatInfo.setShareDes(findWxPublicAccountReturn.getPaDesc());
				Map<String, String> contentMap = new HashMap<>();
				contentMap.put("certflag", findWxPublicAccountReturn.getPaCertflag());
				contentMap.put("username", findWxPublicAccountReturn.getPaUsername());
				contentMap.put("alias", findWxPublicAccountReturn.getPaAlias());
				contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY,
						findWxPublicAccountReturn.getWxParam());
				sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
				sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
				sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
				logger.info("客户认领后，自定义推送公众号名片：{}", sendImChatInfo);
				imChatInfoService.sendChat(sendImChatInfo);
			}
		} catch (Exception e) {
			logger.error("客户认领后，自定义推送公众号名片失败：", e);
		}

	}

	/**
	 * 
	 *
	 * 方法说明：发送小程序消息
	 *
	 * @param addFriends
	 * @param config
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void sendSmallProgramByPushConfig(FindShopTerminalReturn findShopTerminalReturn,
			PersonMemberBase personMemberBase, FindWxPushConfigPageReturn config) {
		try {
			FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
			findWxSmallProgram.setCode(config.getShareCode());
			FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService
					.findWxSmallProgram(findWxSmallProgram);
			if (findWxSmallProgramReturn != null && CommonConstant.I_YES == findWxSmallProgramReturn.getStatus()) {
				SendImChatInfo sendImChatInfo = new SendImChatInfo();
				sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
				sendImChatInfo.setNoWxGm(findShopTerminalReturn.getNoWx());
				sendImChatInfo.setMemberNo(personMemberBase.getMemberNo());
				sendImChatInfo.setType(ChatInfoType.SMALL_PROGRAM.getCode());
				sendImChatInfo.setResources(findWxSmallProgramReturn.getSpLogo());
				sendImChatInfo.setShareTitle(findWxSmallProgramReturn.getSpName());
				sendImChatInfo.setShareDes(findWxSmallProgramReturn.getSpDesc());
				sendImChatInfo.setShareUrl(findWxSmallProgramReturn.getSpUrl());
				Map<String, String> contentMap = new HashMap<>();
				contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY,
						findWxSmallProgramReturn.getWxParam());
				sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
				sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
				sendImChatInfo.setAllowZkOffline(Boolean.TRUE); // 允许离线发送到中控客户端
				logger.info("客户认领后，自定义推送小程序：{}", sendImChatInfo);
				imChatInfoService.sendChat(sendImChatInfo);
			}
		} catch (Exception e) {
			logger.error("客户认领后，自定义推送小程序失败：", e);
		}
	}
}
