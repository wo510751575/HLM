package com.lj.business.member.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.core.util.WxOpenIdUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.member.aync.AyncSendMessageByNewMemberService;
import com.lj.business.member.aync.AyncSendMessageByNonMemberService;
import com.lj.business.member.common.MemberUtils;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IAddFriendsDao;
import com.lj.business.member.dao.IGuidMemberDao;
import com.lj.business.member.dao.IPersonMemberBaseDao;
import com.lj.business.member.dao.IPersonMemberDao;
import com.lj.business.member.dao.IPersonMemberImDao;
import com.lj.business.member.dao.IPmLabelPmDao;
import com.lj.business.member.domain.AddFriends;
import com.lj.business.member.domain.GuidMember;
import com.lj.business.member.domain.PersonMember;
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.domain.PmLabelPm;
import com.lj.business.member.dto.AddPersonMember;
import com.lj.business.member.dto.AddPersonMemberAll;
import com.lj.business.member.dto.AddPersonMemberBase;
import com.lj.business.member.dto.AddPersonMemberByWx;
import com.lj.business.member.dto.AddPersonMemberByWxReturn;
import com.lj.business.member.dto.AddPersonMemberReturn;
import com.lj.business.member.dto.ChangePmLabel;
import com.lj.business.member.dto.CountPersonMemberReturn;
import com.lj.business.member.dto.DelPersonMember;
import com.lj.business.member.dto.DelPersonMemberReturn;
import com.lj.business.member.dto.DoRepeatMemberDto;
import com.lj.business.member.dto.EditPersonMember;
import com.lj.business.member.dto.FindCountPersonMember;
import com.lj.business.member.dto.FindGmDistantPageReturn;
import com.lj.business.member.dto.FindGroupedUnContactMemberHeader;
import com.lj.business.member.dto.FindGroupedUnContactMemberPmType;
import com.lj.business.member.dto.FindGroupedUnContactMemberReturn;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindImIndexPage;
import com.lj.business.member.dto.FindImIndexPageReturn;
import com.lj.business.member.dto.FindMemberInfoReturn;
import com.lj.business.member.dto.FindMemberRecord;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.FindNewPmCountDto;
import com.lj.business.member.dto.FindNewPmPage;
import com.lj.business.member.dto.FindNewPmPageReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberAgeStatisticsReturn;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseList;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberBaseReturnList;
import com.lj.business.member.dto.FindPersonMemberInterestStatisticsReturn;
import com.lj.business.member.dto.FindPersonMemberPage;
import com.lj.business.member.dto.FindPersonMemberPageReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.FindPersonMemberReturnList;
import com.lj.business.member.dto.FindPersonMemberSexStatisticsReturn;
import com.lj.business.member.dto.FindPmInfoAll;
import com.lj.business.member.dto.FindPmInfoAllReturn;
import com.lj.business.member.dto.FindPmLabel;
import com.lj.business.member.dto.FindPmSeachPage;
import com.lj.business.member.dto.FindPmSeachPageReturn;
import com.lj.business.member.dto.FindPmType;
import com.lj.business.member.dto.FindPmTypeIndexPage;
import com.lj.business.member.dto.FindPmTypeIndexPageReturn;
import com.lj.business.member.dto.FindPmTypeIndexReturn;
import com.lj.business.member.dto.FindPmTypeReturn;
import com.lj.business.member.dto.FindPmWxBpInfo;
import com.lj.business.member.dto.FindPmWxBpInfoReturn;
import com.lj.business.member.dto.FindPmWxInfo;
import com.lj.business.member.dto.FindPmWxInfoReturn;
import com.lj.business.member.dto.FindTodayManageShop;
import com.lj.business.member.dto.FindTodayManageShopReturn;
import com.lj.business.member.dto.FindUnContactMember;
import com.lj.business.member.dto.FindUnContactMemberReturn;
import com.lj.business.member.dto.FindUnContactPmType;
import com.lj.business.member.dto.FindUnchatMemberPage;
import com.lj.business.member.dto.FindUnchatMemberPageReturn;
import com.lj.business.member.dto.FindUrgentMbrPage;
import com.lj.business.member.dto.FindUrgentMbrPageReturn;
import com.lj.business.member.dto.GmLabelDto;
import com.lj.business.member.dto.MecMemberDto;
import com.lj.business.member.dto.MecMemberNoReturn;
import com.lj.business.member.dto.MemLineDto;
import com.lj.business.member.dto.MemberNoAndShopNoPageDto;
import com.lj.business.member.dto.PersonMemberDto;
import com.lj.business.member.dto.PersonMemberRateDto;
import com.lj.business.member.dto.PersonMemberStsGroupByShop;
import com.lj.business.member.dto.PmLabelDto;
import com.lj.business.member.dto.UpdatePersonMember;
import com.lj.business.member.dto.UpdatePersonMemberBase;
import com.lj.business.member.dto.UpdatePersonMemberBaseRatioClientInfoDto;
import com.lj.business.member.dto.UpdatePersonMemberReturn;
import com.lj.business.member.dto.behaviorPm.AddBehaviorPm;
import com.lj.business.member.dto.behaviorPm.FindBehaviorPm;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMember;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.guidMemberIntegral.GuidMemberIntegralDto;
import com.lj.business.member.dto.guidmemberActionInfo.AddGuidmemberActionInfo;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.wxPmFollow.FindWxPmByGm;
import com.lj.business.member.emus.FirstIntroduce;
import com.lj.business.member.emus.GuidmemberActionType;
import com.lj.business.member.emus.IntegralType;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.emus.MemerSourceType;
import com.lj.business.member.emus.NameAuthFlag;
import com.lj.business.member.emus.SpruceUpType;
import com.lj.business.member.emus.UnContactCodeEnum;
import com.lj.business.member.emus.UrgentFlagType;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IBehaviorPmService;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGmLabelService;
import com.lj.business.member.service.IGuidMemberIntegralService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IGuidmemberActionInfoService;
import com.lj.business.member.service.IMemLineService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberExtService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.service.IChatFriendsFacade;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.dto.imchat.FindMemberChatCount;
import com.lj.business.weixin.dto.imchat.FindMemberChatCountReturn;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.messagecenter.msg.service.INotifyService;
import com.lj.oms.service.AreaHessianService;

/**
 * 类说明：实现类
 * <p>
 * <p>
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 *         <p>
 *         <p>
 *         CreateDate: 2017-06-14
 */
@Service
public class PersonMemberServiceImpl implements IPersonMemberService {

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private IMerchantParamsService merchantParamsService;

	@Resource
	IWxRedpackDetailInfoService wxRedpackDetailInfoService;

	@Autowired
	private ICommonService commonService;

	@Resource
	private AyncSendMessageByNonMemberService ayncSendMessageByNonMemberService;
	/**
	 * Logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(PersonMemberServiceImpl.class);

	/**
	 * The person member dao.
	 */
	@Resource
	private IPersonMemberDao personMemberDao;

	/**
	 * The pm type dao.
	 */
	@Autowired
	private IPmTypeService pmTypeService;

	/**
	 * The iguidMember dao.
	 */
	@Resource
	private IGuidMemberDao iguidMemberDao;

	/**
	 * The iperson member base dao.
	 */
	@Resource
	private IPersonMemberBaseDao personMemberBaseDao;

	/**
	 * The behavior pm service.
	 */
	@Resource
	private IBehaviorPmService behaviorPmService;

	@Resource
	private IAddFriendsService addFriendsService;
	/**
	 * The person member base service.
	 */
	@Resource
	private IPersonMemberBaseService personMemberBaseService;

	/**
	 * The guid member service.
	 */
	@Resource
	private IGuidMemberService guidMemberService;

	/**
	 * The merchant service.
	 */
	@Resource
	private IMerchantService merchantService;

	/**
	 * The mem line service.
	 */
	@Resource
	private IMemLineService memLineService;

	@Resource
	private IGuidMemberIntegralService guidMemberIntegralService;

	@Resource
	private AreaHessianService areaHessianService;

	@Resource
	private IGuidmemberActionInfoService guidmemberActionInfoService;

	@Resource
	private IPersonMemberService personMemberService;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	// upadate by 杨杰 begin
	@Resource
	private ICouponRuleService couponRuleService;
	@Resource
	private ICouponService couponService;
	@Resource
	private ICouponMemberRelationService couponMemberRelationService;
	// 消息通知service
	@Resource
	private INotifyService notifyService;
	@Resource
	private PersonMemberServiceImpl personMemberServiceImpl;
	@Resource
	private AyncSendMessageByNewMemberService ayncSendMessageByNewMemberService;

	@Autowired
	private IPmLabelPmDao pmLabelPmDao;
	@Autowired
	private IPmLabelService pmLabelService;
	@Resource
	private IPersonMemberExtService personMemberExtService;
	@Resource
	public IPersonMemberImDao personMemberImDao;
	@Resource
	public IPersonMemberImService personMemberImService;
	@Resource
	public IImChatInfoService imChatInfoService;
	@Resource
	private IAddFriendsDao addFriendsDao;
	@Autowired
	private IShopTerminalService shopTerminalService;
	@Autowired
	private IGmAssistantShopService gmAssistantShopService;
	@Autowired
	private IChatRoomMemberService chatRoomMemberService;
	@Autowired
	private IGmLabelService gmLabelService;

	/**
	 * 取消客户绑定，取消客户认领
	 */
	public void updateCanclePersonMember(String gmNo, String wxNo, String noWxGm) throws TsfaServiceException {
		logger.debug("updateCanclePersonMember (String gmNo={}, String wxNo={},String noWxGm={}) - start", gmNo, wxNo,
				noWxGm);
		AssertUtils.notAllNullAndEmpty(gmNo, "导购编号不能为空");
		AssertUtils.notAllNullAndEmpty(wxNo, "客户微信不能为空");
		AssertUtils.notAllNullAndEmpty(noWxGm, "终端微信不能为空");
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("gmNo", gmNo);
			map.put("wxNo", wxNo);
			map.put("noWxGm", noWxGm);
			personMemberDao.updateCanclePersonMember(map);

			// 更新版本号
			personMemberDao.updateVersionAllMemberByGm(gmNo);

		} catch (Exception e) {
			logger.error("客户取消认领错误", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_CANCLE_ERROR, "客户取消认领错误.！", e);
		}
		logger.debug("updateCanclePersonMember () - end");
	}

	/**
	 * 转移客户
	 * 
	 * @throws TsfaServiceException
	 */
	public void updateFriendsWithTransfer(String sourceGmNo, String newGmName, String rediectGmNo, String memberNo,
			String noWxGm) throws TsfaServiceException {
		logger.debug("transferFriends () - start -  value={}", sourceGmNo + "|" + rediectGmNo + "|" + memberNo);

		AssertUtils.notNull(sourceGmNo);
		AssertUtils.notNull(rediectGmNo);
		AssertUtils.notNull(memberNo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sourceGmNo", sourceGmNo);
		map.put("newGmName", newGmName);
		map.put("rediectGmNo", rediectGmNo);
		map.put("memberNo", memberNo);
		map.put("noWxGm", noWxGm);
		map.put("version", String.valueOf(System.currentTimeMillis()));
		try {

			personMemberDao.updateFriendsWithTransfer(map);
			addFriendsDao.updateFriendsWithTransfer(map);

			// 更新版本号
			personMemberDao.updateVersionAllMemberByGm(rediectGmNo);

			personMemberDao.updateVersionAllMemberByGm(sourceGmNo);

			// **************成功转移后更新缓存数据 **********************
			// 获取导购微信号、客户微信号、客户微信别名
			/*
			 * GuidMember gm = new GuidMember(); gm.setMemberNo(rediectGmNo); GuidMember
			 * guidMember = iguidMemberDao.selectByParams(gm);
			 * 
			 * Map<String, Object> memberInfoMap =
			 * personMemberImDao.getGmMemberWxInfo(rediectGmNo, memberNo);
			 * 
			 * GmMemberRelateInfoDto gmMemberInfo = new GmMemberRelateInfoDto();
			 * 
			 * gmMemberInfo.setMemberNoGm(rediectGmNo);
			 * gmMemberInfo.setMemberNameGm(newGmName); gmMemberInfo.setMemberNo(memberNo);
			 * gmMemberInfo.setMemberName((String) memberInfoMap.get("memberName"));
			 * gmMemberInfo.setGmWx((String) memberInfoMap.get("gmWx"));
			 * gmMemberInfo.setNoWx((String) memberInfoMap.get("noWx"));
			 * gmMemberInfo.setNoWxAlias((String) memberInfoMap.get("noWxAlias"));
			 * 
			 * // gmMemberInfo.setShopNo(guidMember.getShopNo()); //
			 * gmMemberInfo.setShopName(guidMember.getShopName());
			 * gmMemberInfo.setMerchantNo(guidMember.getMerchantNo());
			 * gmMemberInfo.setMerchantName(guidMember.getMerchantName());
			 * 
			 * try { personMemberImService.cacheGmMemberRelateInfo(gmMemberInfo); } catch
			 * (Exception e) { logger.error("缓存用户关联信息失败！", e); }
			 */
		} catch (Exception e) {
			logger.error("转移失败", e);
		}

		logger.debug("transferFriends () - end");
	}

	/**
	 * 通知App已成功转移
	 * 
	 * @param memberNoGm
	 * @param memberNo
	 * @param state      1新增 2 移除
	 */
	public void sendTransSuccess(String memberNoGm, String memberNo, String state, String noWxGm, String noWx) {
		///////////////////////////////////////////////////////////////////////
		// 缓存用户关联信息失败不能影响导购添加好友流程，因为程序不能从缓存中获取数据还能从MySQL中获取数据
		// 如果如果导购跟客户添加微信好友成功，则更新Redis客户导购关联信息缓存
		try {
			IChatFriendsFacade basic = commonService.getHessianIChatFriendsFacade(noWxGm);
			basic.sendFriendsInfoForGm(memberNoGm, memberNo, state, noWx);
		} catch (Exception e) {
			logger.error("客户认领转移错误", e);
			throw new TsfaServiceException(ErrorCode.ADD_FRIENDS_TRANSFER_ERROR, "客户认领转移错误.！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：新增好友发送相关消息，如问候、红包等
	 *
	 * @param addFriends
	 *
	 * @author 曾垂瑜 CreateDate: 2018年3月20日
	 *
	 */
	private void sendMessageByNewFriends(AddFriends addFriends) {
		FindMerchantParams findMerchantParams = new FindMerchantParams();
		String groupName = "push_switch";// 组名
		findMerchantParams.setGroupName(groupName);
		findMerchantParams.setMerchantNo(addFriends.getMerchantNo());
		Map<String, String> paramsMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);

		if (MapUtils.isNotEmpty(paramsMap)) {
			// 客户认领前发送个人问候(默认为认领后发送)
			if (paramsMap.get("greetings") != null && "ON".equals(paramsMap.get("greetings"))
					&& "beforeClaim".equals(paramsMap.get("greetingsTime"))) {
				addFriendsService.sendGreetings(addFriends, paramsMap.get("greetingsInfo"));
			}
		}

		// 异步推送终端配置信息
//			ayncSendMessageByNonMemberService.sendMessage(addFriends);
//		}
	}

	// upadate by 杨杰 end

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#findPmInfoAll(com
	 * .lj.business.member.dto.FindPmInfoAll)
	 */
	@Override
	public FindPmInfoAllReturn findPmInfoAll(FindPmInfoAll findPmInfoAll) throws TsfaServiceException {
		logger.debug("findPmInfoAll(FindPmInfoAll findPmInfoAll={}) - start", findPmInfoAll);

		AssertUtils.notNull(findPmInfoAll);
		AssertUtils.notAllNull(findPmInfoAll.getMemberNoGm(), "导购编号不能为空");
		try {

			PersonMemberBase personMemberBase = null;

			if (StringUtils.isNotEmpty(findPmInfoAll.getMemberNo())) {
				FindPersonMemberBase findPersonMemberBaseQuery = new FindPersonMemberBase();
				findPersonMemberBaseQuery.setMemberNo(findPmInfoAll.getMemberNo());
				personMemberBase = personMemberBaseDao.selectByParams(findPersonMemberBaseQuery);
			}

			// 如果不传客户号就要穿微信号
			if (StringUtils.isEmpty(findPmInfoAll.getMemberNo())) {
				if (StringUtils.isNotEmpty(findPmInfoAll.getNoWx())) {
					FindPersonMemberBase findPersonMemberBaseQuery = new FindPersonMemberBase();
					findPersonMemberBaseQuery.setNoWx(findPmInfoAll.getNoWx());
					personMemberBase = personMemberBaseDao.selectByParams(findPersonMemberBaseQuery);
					if (personMemberBase == null) {
						throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "该群成员不是您的好友！");
					}

					findPmInfoAll.setMemberNo(personMemberBase.getMemberNo());
				}
				if (findPmInfoAll.getNoWx() == null) {
					AssertUtils.notAllNullAndEmpty(findPmInfoAll.getMemberNo(), findPmInfoAll.getNoWx(), "导购编号或微信不能为空");
				}
			}

			PersonMember personMemberQuery = new PersonMember();
			personMemberQuery.setMemberNo(findPmInfoAll.getMemberNo());
			personMemberQuery.setShopWx(findPmInfoAll.getNoWxGm());
			personMemberQuery.setMemberNoGm(findPmInfoAll.getMemberNoGm());
			PersonMember personMember = personMemberDao.selectByParamKey(personMemberQuery);
			if (personMember == null) {
				throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "该客户您未认领！");
			}
			FindPmInfoAllReturn findPmInfoAllReturn = new FindPmInfoAllReturn();
			findPmInfoAllReturn.setCodePm(personMember.getCode());
			findPmInfoAllReturn.setMemberNo(personMemberBase.getMemberNo());
			findPmInfoAllReturn.setMemberName(personMember.getMemberName());
			findPmInfoAllReturn.setMemberNoGm(personMember.getMemberNoGm());
			findPmInfoAllReturn.setMemberNameGm(personMember.getMemberNameGm());
			findPmInfoAllReturn.setMerchantNo(personMember.getMerchantNo());
			findPmInfoAllReturn.setMerchantName(personMember.getMerchantName());
			findPmInfoAllReturn.setSpruceUp(personMember.getSpruceUp());
			findPmInfoAllReturn.setWxFriends(Integer.valueOf(1).equals(personMember.getWxFriends()));// 是否微信好友
			if (!StringUtils.isEmpty(personMember.getSpruceUp()))
				findPmInfoAllReturn.setSpruceUpName(SpruceUpType.valueOf(personMember.getSpruceUp()).getName());
			findPmInfoAllReturn.setHouses(personMember.getHouses());
			findPmInfoAllReturn.setUrgencyPm(personMember.getUrgencyPm());
			findPmInfoAllReturn.setBomCode(personMember.getBomCode());
			findPmInfoAllReturn.setBomName(personMember.getBomName());
			if (personMember.getExpectPurchaseTime() != null) {
				findPmInfoAllReturn.setExpectPurchaseTime(DateUtils.formatDate(personMember.getExpectPurchaseTime(),
						DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
			}
			if (personMember.getExpTime() != null) {
				findPmInfoAllReturn.setExpTime(
						DateUtils.formatDate(personMember.getExpTime(), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
			}
			findPmInfoAllReturn.setRemark(personMember.getRemark());
			findPmInfoAllReturn.setNickNameRemarkWx(personMember.getNickNameRemarkWx());
			findPmInfoAllReturn.setNickNameRemarkLocal(personMember.getNickNameRemarkLocal());
			findPmInfoAllReturn.setBrandCompare(personMember.getBrandCompare());
			findPmInfoAllReturn.setClientSpecial(personMember.getClientSpecial());
			findPmInfoAllReturn.setConsumeFrequency(personMember.getConsumeFrequency());
			findPmInfoAllReturn.setRemark(personMember.getRemark());

			findPmInfoAllReturn.setCodePmBase(personMemberBase.getCode());
			findPmInfoAllReturn.setMobile(personMemberBase.getMobile());
			findPmInfoAllReturn.setJob(personMemberBase.getJob());
			if (!StringUtils.isEmpty(personMemberBase.getJob())) {
				MemLineDto memLine = new MemLineDto();
				memLine.setCode(personMemberBase.getJob());
				MemLineDto memLineDto = memLineService.findMemLine(memLine);
				if (memLineDto != null) {
					findPmInfoAllReturn.setJobName(memLineDto.getName());
				}
			}
			findPmInfoAllReturn.setAge(personMemberBase.getAge());
			findPmInfoAllReturn.setSex(personMemberBase.getSex());
			findPmInfoAllReturn.setMemberSrc(personMemberBase.getMemberSrc());
			if (!StringUtils.isEmpty(personMemberBase.getMemberSrc()))
				findPmInfoAllReturn
						.setMemberSrcName(MemerSourceType.valueOf(personMemberBase.getMemberSrc()).getName());

			if (personMemberBase.getNoWxAlias() == null || personMemberBase.getNoWxAlias().equals("")) {
				findPmInfoAllReturn.setNoWx(personMemberBase.getNoWx());
			}
			if (personMemberBase.getNoWxAlias() != null && !personMemberBase.getNoWxAlias().equals("")) {
				findPmInfoAllReturn.setNoWx(personMemberBase.getNoWxAlias());
			}
			findPmInfoAllReturn.setNickNameWx(personMemberBase.getNickNameWx());
			findPmInfoAllReturn.setHeadAddress(personMemberBase.getHeadAddress());
			findPmInfoAllReturn.setInterest(personMemberBase.getInterest());
			findPmInfoAllReturn.setAreaCode(personMemberBase.getAreaCode());
			findPmInfoAllReturn.setProvinceCode(personMemberBase.getProvinceCode());
			findPmInfoAllReturn.setCityCode(personMemberBase.getCityCode());
			findPmInfoAllReturn.setCityAreaCode(personMemberBase.getCityAreaCode());
			findPmInfoAllReturn.setBirthday(personMemberBase.getBirthday());
			findPmInfoAllReturn.setConstellation(personMemberBase.getConstellation());
			FindBehaviorPm findBehaviorPm = new FindBehaviorPm();
			findBehaviorPm.setMemberNo(findPmInfoAll.getMemberNo());

//            FindBehaviorPmReturn findBehaviorPmReturn = behaviorPmService.findBehaviorPm(findBehaviorPm);
//            findPmInfoAllReturn.setBehaviorDate(findBehaviorPmReturn.getBehaviorDate());
//            if (!"暂无动态".equals(findBehaviorPmReturn.getBehaviorDesc()) || DateUtils.differentDays(new Date(),
//                    findBehaviorPmReturn.getBehaviorDate()) < 60) {
//                findPmInfoAllReturn.setBehaviorDesc(findBehaviorPmReturn.getBehaviorDesc());
//            } else {    // 超过60则显示为无动态
//                findPmInfoAllReturn.setBehaviorDesc("无动态");
//            }
			findPmInfoAllReturn.setBehaviorDesc("无动态");

			// 查找客户原来所属分类关联CODE
			Map<String, String> map = new HashMap<String, String>();
			map.put("merchantNo", personMember.getMerchantNo());
			map.put("codePm", personMember.getCode());
			findPmInfoAllReturn.setPmTypeCode(personMember.getPmTypeCode());
			findPmInfoAllReturn.setTypeName(personMember.getPmTypeName());
			// 查询客户标签列表-乐莎莎
			map.put("memberNo", personMemberBase.getMemberNo());
			map.put("shopWx", personMember.getShopWx());
			List<PmLabelDto> labels = pmLabelPmDao.findPmLabelByCond(map);
			findPmInfoAllReturn.setLabels(labels);

			logger.debug("findPmInfoAll(FindPmInfoAll) - end - return value={}", findPmInfoAllReturn);
			return findPmInfoAllReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户会员信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查找客户会员信息信息错误！", e);
		}

	}

	// /LEOPENG 待完善
	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#addPersonMemberAll
	 * (com.lj.business.member.dto .AddPersonMemberAll)
	 */
	@Override
	public void addPersonMemberAll(AddPersonMemberAll addPersonMemberAll) throws TsfaServiceException {

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#addPersonMember(com
	 * .lj.business.member.dto .AddPersonMember)
	 */
	@Override
	public AddPersonMemberReturn addPersonMember(AddPersonMember addPersonMember) throws TsfaServiceException {
		logger.debug("addPersonMember(AddPersonMember addPersonMember={}) - start", addPersonMember);

		AssertUtils.notNull(addPersonMember);
		try {
			PersonMember personMember = new PersonMember();
			// add数据录入
			personMember.setCode(GUID.getPreUUID());
			if (addPersonMember.getMemberNo() != null && !"".equals(addPersonMember.getMemberNo())) {
				personMember.setMemberNo(addPersonMember.getMemberNo());
			} else {
				personMember.setMemberNo(GUID.generateByUUID());
			}
			personMember.setMemberName(addPersonMember.getMemberName());
			personMember.setMemberNoGm(addPersonMember.getMemberNoGm());
			personMember.setMemberNameGm(addPersonMember.getMemberNameGm());
			personMember.setMerchantNo(addPersonMember.getMerchantNo());
			personMember.setMerchantName(addPersonMember.getMerchantName());
			personMember.setSpruceUp(addPersonMember.getSpruceUp());
			personMember.setHouses(addPersonMember.getHouses());
			personMember.setKeepEye(addPersonMember.getKeepEye());
			personMember.setUrgencyPm(addPersonMember.getUrgencyPm());
			personMember.setBomCode(addPersonMember.getBomCode());
			personMember.setBomName(addPersonMember.getBomName());
			personMember.setCreateId(addPersonMember.getCreateId());
			personMember.setRemark4(addPersonMember.getRemark4());
			personMember.setRemark3(addPersonMember.getRemark3());
			personMember.setRemark2(addPersonMember.getRemark2());
			personMember.setRemark(addPersonMember.getRemark());
			/* 扫码相关 */
			personMember.setLatitude(addPersonMember.getLatitude());
			personMember.setLongitude(addPersonMember.getLongitude());
			personMember.setScanAddress(addPersonMember.getScanAddress());

			personMember.setWxFriends(addPersonMember.getWxFriends());
			personMember.setAddType(addPersonMember.getAddType()); // 客户新增方式
			personMember.setVersion(System.currentTimeMillis()); // 版本号
			personMemberDao.insertSelective(personMember);
			// AddPersonMemberReturn addPersonMemberReturn = new AddPersonMemberReturn();

			// logger.debug("addPersonMember(AddPersonMember) - end - return value={}",
			// addPersonMemberReturn);
			// return addPersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增客户会员信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_ADD_ERROR, "新增客户会员信息错误！", e);
		}

		///////////////////////////////////////////////////////////////////////
		// 缓存用户关联信息失败不能影响导购添加好友流程，因为程序不能从缓存中获取数据还能从MySQL中获取数据
		// 如果如果导购跟客户添加微信好友成功，则更新Redis客户导购关联信息缓存
		/*
		 * if (null != addPersonMember.getWxFriends() && addPersonMember.getWxFriends()
		 * == 1) { // 获取导购微信号、客户微信号、客户微信别名 Map<String, Object> memberInfoMap =
		 * personMemberImDao.getGmMemberWxInfo(addPersonMember.getMemberNoGm(),
		 * addPersonMember.getMemberNo());
		 * 
		 * GmMemberRelateInfoDto gmMemberInfo = new GmMemberRelateInfoDto();
		 * 
		 * gmMemberInfo.setMemberNoGm(addPersonMember.getMemberNoGm());
		 * gmMemberInfo.setMemberNameGm(addPersonMember.getMemberNameGm());
		 * gmMemberInfo.setMemberNo(addPersonMember.getMemberNo());
		 * gmMemberInfo.setMemberName(addPersonMember.getMemberName());
		 * gmMemberInfo.setGmWx((String) memberInfoMap.get("gmWx"));
		 * gmMemberInfo.setNoWx((String) memberInfoMap.get("noWx"));
		 * gmMemberInfo.setNoWxAlias((String) memberInfoMap.get("noWxAlias"));
		 * 
		 * gmMemberInfo.setMerchantNo(addPersonMember.getMerchantNo());
		 * gmMemberInfo.setMerchantName(addPersonMember.getMerchantName());
		 * 
		 * try { personMemberImService.cacheGmMemberRelateInfo(gmMemberInfo); } catch
		 * (Exception e) { logger.error("缓存用户关联信息失败！", e); } }
		 */

		AddPersonMemberReturn addPersonMemberReturn = new AddPersonMemberReturn();
		return addPersonMemberReturn;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#delPersonMember(com
	 * .lj.business.member.dto .DelPersonMember)
	 */
	@Override
	public DelPersonMemberReturn delPersonMember(DelPersonMember delPersonMember) throws TsfaServiceException {
		logger.debug("delPersonMember(DelPersonMember delPersonMember={}) - start", delPersonMember);

		AssertUtils.notNull(delPersonMember);
		AssertUtils.notNull(delPersonMember.getCode(), "ID不能为空！");
		try {
			personMemberDao.deleteByPrimaryKey(delPersonMember.getCode());
			DelPersonMemberReturn delPersonMemberReturn = new DelPersonMemberReturn();

			logger.debug("delPersonMember(DelPersonMember) - end - return value={}", delPersonMemberReturn);

			return delPersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除客户会员信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_DEL_ERROR, "删除客户会员信息错误！", e);

		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#updatePersonMember
	 * (com.lj.business.member.dto .UpdatePersonMember)
	 */
	@Override
	public UpdatePersonMemberReturn updatePersonMember(UpdatePersonMember updatePersonMember)
			throws TsfaServiceException {
		logger.debug("updatePersonMember(UpdatePersonMember updatePersonMember={}) - start", updatePersonMember);

		AssertUtils.notNull(updatePersonMember);
		AssertUtils.notNullAndEmpty(updatePersonMember.getCode(), "CODE不能为空");
		try {
			PersonMember personMember = new PersonMember();
			// update数据录入
			personMember.setCode(updatePersonMember.getCode());
			personMember.setMemberNo(updatePersonMember.getMemberNo());
			personMember.setMemberName(updatePersonMember.getMemberName());
			personMember.setMemberNoGm(updatePersonMember.getMemberNoGm());
			personMember.setMemberNameGm(updatePersonMember.getMemberNameGm());
			personMember.setMerchantNo(updatePersonMember.getMerchantNo());
			personMember.setMerchantName(updatePersonMember.getMerchantName());
			personMember.setSpruceUp(updatePersonMember.getSpruceUp());
			personMember.setHouses(updatePersonMember.getHouses());
			personMember.setKeepEye(updatePersonMember.getKeepEye());
			personMember.setUrgencyPm(updatePersonMember.getUrgencyPm());
			personMember.setBomCode(updatePersonMember.getBomCode());
			personMember.setBomName(updatePersonMember.getBomName());
			personMember.setBrandCompare(updatePersonMember.getBrandCompare());
			personMember.setClientSpecial(updatePersonMember.getClientSpecial());
			personMember.setUpdateId(updatePersonMember.getUpdateId());
			personMember.setUpdateDate(updatePersonMember.getUpdateDate());
			personMember.setRemark4(updatePersonMember.getRemark4());
			personMember.setRemark3(updatePersonMember.getRemark3());
			personMember.setRemark2(updatePersonMember.getRemark2());
			personMember.setRemark(updatePersonMember.getRemark());
			personMember.setNickNameRemarkWx(updatePersonMember.getNickNameRemarkWx());
			/* 扫码相关 */
			personMember.setLatitude(updatePersonMember.getLatitude());
			personMember.setLongitude(updatePersonMember.getLongitude());
			personMember.setScanAddress(updatePersonMember.getScanAddress());
			// update by 杨杰 2017-09-05
			personMember.setNickNameRemarkLocal(updatePersonMember.getNickNameRemarkLocal());

			personMember.setWxFriends(updatePersonMember.getWxFriends()); // 是否微信好友
			personMember.setAddType(updatePersonMember.getAddType()); // 客户新增方式
			personMember.setVersion(System.currentTimeMillis()); // 客户版本号

			// 客户分类
			personMember.setPmTypeCode(updatePersonMember.getPmTypeCode());
			personMember.setPmTypeName(updatePersonMember.getPmTypeName());
			AssertUtils.notUpdateMoreThanOne(personMemberDao.updateByPrimaryKeySelective(personMember));

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "客户会员信息更新信息错误！", e);
		}

		///////////////////////////////////////////////////////////////////////
		// 缓存用户关联信息失败不能影响导购添加好友流程，因为程序不能从缓存中获取数据还能从MySQL中获取数据
		// 如果如果导购跟客户添加微信好友成功，则更新Redis客户导购关联信息缓存
		/*
		 * if (updatePersonMember.getWxFriends() != null && 1 ==
		 * updatePersonMember.getWxFriends() &&
		 * StringUtils.isNotEmpty(updatePersonMember.getMemberNo()) &&
		 * StringUtils.isNotEmpty(updatePersonMember.getMemberNo())) { //
		 * 获取导购微信号、客户微信号、客户微信别名 Map<String, Object> memberInfoMap =
		 * personMemberImDao.getGmMemberWxInfo(updatePersonMember.getMemberNoGm(),
		 * updatePersonMember.getMemberNo());
		 * 
		 * GmMemberRelateInfoDto gmMemberInfo = new GmMemberRelateInfoDto();
		 * 
		 * gmMemberInfo.setMemberNoGm(updatePersonMember.getMemberNoGm());
		 * gmMemberInfo.setMemberNameGm(updatePersonMember.getMemberNameGm());
		 * gmMemberInfo.setMemberNo(updatePersonMember.getMemberNo());
		 * gmMemberInfo.setMemberName(updatePersonMember.getMemberName());
		 * gmMemberInfo.setGmWx((String) memberInfoMap.get("gmWx"));
		 * gmMemberInfo.setNoWx((String) memberInfoMap.get("noWx"));
		 * gmMemberInfo.setNoWxAlias((String) memberInfoMap.get("noWxAlias"));
		 * 
		 * gmMemberInfo.setMerchantNo(updatePersonMember.getMerchantNo());
		 * gmMemberInfo.setMerchantName(updatePersonMember.getMerchantName());
		 * 
		 * try { personMemberImService.cacheGmMemberRelateInfo(gmMemberInfo); } catch
		 * (Exception e) { logger.error("缓存用户关联信息失败！", e); } }
		 */

		UpdatePersonMemberReturn updatePersonMemberReturn = new UpdatePersonMemberReturn();

		logger.debug("updatePersonMember(UpdatePersonMember) - end - return value={}", updatePersonMemberReturn);

		return updatePersonMemberReturn;
	}

	/**
	 * 修改用户名称 update by 杨杰(不破坏公共方法，改为重写方法)
	 *
	 * @param updatePersonMember
	 * @param updatePersonMemberBase
	 * @return
	 * @throws TsfaServiceException
	 */

	public UpdatePersonMemberReturn updatePersonMember(UpdatePersonMember updatePersonMember,
			UpdatePersonMemberBase updatePersonMemberBase) throws TsfaServiceException {
		logger.debug("updatePersonMember(UpdatePersonMember updatePersonMember={}) - start", updatePersonMember);

		AssertUtils.notNull(updatePersonMember);
		AssertUtils.notNullAndEmpty(updatePersonMember.getCode(), "CODE不能为空");
		try {
			PersonMember personMember = new PersonMember();
			// update数据录入
			personMember.setCode(updatePersonMember.getCode());
			personMember.setMemberNo(updatePersonMember.getMemberNo());
			personMember.setMemberName(updatePersonMember.getMemberName());
			personMember.setMemberNoGm(updatePersonMember.getMemberNoGm());
			personMember.setMemberNameGm(updatePersonMember.getMemberNameGm());
			personMember.setMerchantNo(updatePersonMember.getMerchantNo());
			personMember.setMerchantName(updatePersonMember.getMerchantName());
			personMember.setSpruceUp(updatePersonMember.getSpruceUp());
			personMember.setHouses(updatePersonMember.getHouses());
			personMember.setKeepEye(updatePersonMember.getKeepEye());
			personMember.setUrgencyPm(updatePersonMember.getUrgencyPm());
			personMember.setBomCode(updatePersonMember.getBomCode());
			personMember.setBomName(updatePersonMember.getBomName());
			personMember.setBrandCompare(updatePersonMember.getBrandCompare());
			personMember.setClientSpecial(updatePersonMember.getClientSpecial());
			personMember.setConsumeFrequency(updatePersonMember.getConsumeFrequency());
			personMember.setUpdateId(updatePersonMember.getUpdateId());
			personMember.setUpdateDate(new Date());
			personMember.setExpectPurchaseTime(StringUtils.isEmpty(updatePersonMember.getExpectPurchaseTime()) ? null
					: DateUtils.parseDate(updatePersonMember.getExpectPurchaseTime(),
							DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
			personMember.setExpTime(StringUtils.isEmpty(updatePersonMember.getExpTime()) ? null
					: DateUtils.parseDate(updatePersonMember.getExpTime(), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
			personMember.setRemark4(updatePersonMember.getRemark4());
			personMember.setRemark3(updatePersonMember.getRemark3());
			personMember.setRemark2(updatePersonMember.getRemark2());
			personMember.setRemark(updatePersonMember.getRemark());
			personMember.setNickNameRemarkWx(updatePersonMember.getNickNameRemarkWx());
			/* 扫码相关 */
			personMember.setLatitude(updatePersonMember.getLatitude());
			personMember.setLongitude(updatePersonMember.getLongitude());
			personMember.setScanAddress(updatePersonMember.getScanAddress());
			personMember.setWxFriends(updatePersonMember.getWxFriends()); // 是否微信好友
			personMember.setAddType(updatePersonMember.getAddType()); // 客户新增方式
			personMember.setVersion(System.currentTimeMillis()); // 客户版本号

			// 客户分类
			if (StringUtils.isNotEmpty(updatePersonMember.getPmTypeCode())) {
				personMember.setPmTypeCode(updatePersonMember.getPmTypeCode());
				if (StringUtils.isEmpty(updatePersonMember.getPmTypeName())) {
					FindPmType findPmType = new FindPmType();
					findPmType.setCode(updatePersonMember.getPmTypeCode());
					FindPmTypeReturn findPmTypeReturn = pmTypeService.findPmType(findPmType);
					if (null == findPmTypeReturn) {
						logger.error("客户分类不存在！");
						throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_ERROR, "客户分类不存在！");
					}
					personMember.setPmTypeName(findPmTypeReturn.getTypeName());
				} else {
					personMember.setPmTypeName(updatePersonMember.getPmTypeName());
				}
			}
			personMember.setShopWx(updatePersonMember.getShopWx());
			AssertUtils.notUpdateMoreThanOne(personMemberDao.updateByPrimaryKeySelective(personMember));
			UpdatePersonMemberReturn updatePersonMemberReturn = new UpdatePersonMemberReturn();

			logger.debug("updatePersonMember(UpdatePersonMember) - end - return value={}", updatePersonMemberReturn);

			return updatePersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "客户会员信息更新信息错误！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.lj.business.member.service.IPersonMemberService#updatePersonMemberByMGM
	 * (com.lj.business.member.dto .UpdatePersonMember)
	 */
	@Override
	public UpdatePersonMemberReturn editPersonMember(EditPersonMember editPersonMember) throws TsfaServiceException {
		AssertUtils.notNull(editPersonMember);
		AssertUtils.notNullAndEmpty(editPersonMember.getCode(), "客户基础code不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getCodePm(), "客户扩展code不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getMerchantNo(), "商户号不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getMemberNo(), "客户号不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getNoWxGm(), "终端微信不能为空");
		try {

// #YE007需求：取消备注手机号码唯一性，多商户多门店可对客户添加同一手机号码备注，可搜索同一手机号好友（同一个终端下同手机号只有一个PM）
//			if (StringUtils.isNotEmpty(editPersonMember.getMobile())) {
//				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
//				findPersonMemberBase.setMobile(editPersonMember.getMobile());
//				FindPersonMemberBaseReturn pmb = personMemberBaseService.findByMobile(findPersonMemberBase);

			/*
			 * FindPersonMember findPersonMemberBase = new FindPersonMember();
			 * findPersonMemberBase.setMobile(editPersonMember.getMobile());
			 * findPersonMemberBase.setShopWx(editPersonMember.getNoWxGm());
			 * findPersonMemberBase.setMerchantNo(editPersonMember.getMerchantNo());
			 * FindPersonMemberReturn pmb =
			 * findPersonMemberByMoblieAndShopWx(findPersonMemberBase);
			 */
//				if (pmb != null && pmb.getMemberNo() != editPersonMember.getMemberNo()) {
//					logger.error("该手机号已存在");
//					throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_MOBILE_EXIST, "该手机号已存在！");
//				}
//			}

			FindPersonMember findPersonMember = new FindPersonMember();
			findPersonMember.setCode(editPersonMember.getCodePm());
			FindPersonMemberReturn findPersonMemberReturn = findPersonMember(findPersonMember);
			if (findPersonMemberReturn != null) {

				// 更新客户标签列表
				if (null != editPersonMember.getLabels() && editPersonMember.getLabels().length > 0) {
					PmLabelDto[] labels = editPersonMember.getLabels();
					if (labels != null) {
						ChangePmLabel changePmLabel = new ChangePmLabel();
						changePmLabel.setMemberNo(editPersonMember.getMemberNo());
						changePmLabel.setMerchantNo(editPersonMember.getMerchantNo());
						changePmLabel.setLabels(Arrays.asList(labels));
						pmLabelService.changePmLabel(changePmLabel);
					}
				}

				UpdatePersonMemberBase updatePersonMemberBase = new UpdatePersonMemberBase();
				updatePersonMemberBase.setCode(editPersonMember.getCode());
//				FixBUG 同一客户添加多个终端的情况下，不能变更base名称 if(StringUtils.isNotEmpty(editPersonMember.getNickNameRemarkWx())) {
//					updatePersonMemberBase.setMemberName(editPersonMember.getNickNameRemarkWx());
//				}else {
//					updatePersonMemberBase.setMemberName(editPersonMember.getMemberName());
//				}
				updatePersonMemberBase.setMobile(editPersonMember.getMobile());
				updatePersonMemberBase.setAddMobileDate(editPersonMember.getAddMobileDate());
				updatePersonMemberBase.setJob(editPersonMember.getJob());
				updatePersonMemberBase.setAge(editPersonMember.getAge());
				updatePersonMemberBase.setSex(editPersonMember.getSex());
				updatePersonMemberBase.setHeadAddress(editPersonMember.getHeadAddress());
				updatePersonMemberBase.setUpdateId(findPersonMemberReturn.getMemberNameGm());
				updatePersonMemberBase.setUpdateDate(new Date());
				updatePersonMemberBase.setAddress(editPersonMember.getAddress());
				personMemberBaseService.updatePersonMemberBase(updatePersonMemberBase);

				// 修改扩展表
				UpdatePersonMember updatePersonMember = new UpdatePersonMember();
				updatePersonMember.setCode(editPersonMember.getCodePm());
				updatePersonMember.setRemark(editPersonMember.getRemark());
				updatePersonMember.setUpdateId(editPersonMember.getOperater());
				updatePersonMember.setNickNameRemarkWx(editPersonMember.getNickNameRemarkWx());
				// 客户分组change
				updatePersonMember.setPmTypeCode(editPersonMember.getPmTypeCode());
				updatePersonMember.setShopWx(editPersonMember.getNoWxGm());
				updatePersonMember(updatePersonMember, updatePersonMemberBase);

				// 同步群成员备注名
				if (StringUtils.isNotEmpty(editPersonMember.getNickNameRemarkWx())) {
					final String noWxZk = editPersonMember.getNoWxGm();
					final String memberNo = editPersonMember.getMemberNo();
					final String memberName = editPersonMember.getNickNameRemarkWx();
					taskExecutor.execute(new Runnable() {

						@Override
						public void run() {
							UpdateChatRoomMember updateChatRoomMember = new UpdateChatRoomMember();
							updateChatRoomMember.setNoWxZk(noWxZk);
							updateChatRoomMember.setMemberNo(memberNo);
							updateChatRoomMember.setMemberName(memberName);
							chatRoomMemberService.synChatRoomMember(updateChatRoomMember);

						}
					});
				}
			}
			UpdatePersonMemberReturn updatePersonMemberReturn = new UpdatePersonMemberReturn();
			return updatePersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "客户会员信息更新信息错误！", e);
		}

	}

	@Override
	public UpdatePersonMemberReturn editPersonMemberGMA(EditPersonMember editPersonMember) throws TsfaServiceException {
		AssertUtils.notNull(editPersonMember);
		AssertUtils.notNullAndEmpty(editPersonMember.getCode(), "客户基础code不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getCodePm(), "客户扩展code不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getMerchantNo(), "商户号不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getMemberNo(), "客户号不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getNoWxGm(), "终端微信号不能为空");
		try {

			if (StringUtils.isNotEmpty(editPersonMember.getMobile())) {
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setCode(editPersonMember.getCode());
				findPersonMemberBase.setMobile(editPersonMember.getMobile());
				PersonMemberBase pmb = personMemberBaseService.findMobileAndCode(findPersonMemberBase);

				if (pmb != null && pmb.getMemberNo() != editPersonMember.getMemberNo()) {
					logger.error("该手机号已存在");
					throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_MOBILE_EXIST, "该手机号已存在！");
				}
			}

			FindPersonMember findPersonMember = new FindPersonMember();
			findPersonMember.setCode(editPersonMember.getCodePm());
			FindPersonMemberReturn findPersonMemberReturn = findPersonMember(findPersonMember);
			if (findPersonMemberReturn != null) {
				UpdatePersonMemberBase updatePersonMemberBase = new UpdatePersonMemberBase();
				updatePersonMemberBase.setCode(editPersonMember.getCode());
				updatePersonMemberBase.setMemberNo(editPersonMember.getMemberNo());
				updatePersonMemberBase.setMobile(editPersonMember.getMobile());
				updatePersonMemberBase.setJob(editPersonMember.getJob());
				updatePersonMemberBase.setAge(editPersonMember.getAge());
				updatePersonMemberBase.setSex(editPersonMember.getSex());
				updatePersonMemberBase.setHeadAddress(editPersonMember.getHeadAddress());
				updatePersonMemberBase.setProvinceCode(editPersonMember.getProvinceCode());
				updatePersonMemberBase.setCityCode(editPersonMember.getCityCode());
				updatePersonMemberBase.setCityAreaCode(editPersonMember.getCityAreaCode());
				updatePersonMemberBase.setAreaCode(editPersonMember.getAreaCode());

				if (editPersonMember.getAreaName() != null && !"".equals(editPersonMember.getAreaName())) {
					String areaName = areaHessianService.getAreaNameByCode(editPersonMember.getAreaCode());
					updatePersonMemberBase.setAreaName(areaName);
				}
				updatePersonMemberBase.setUpdateId(editPersonMember.getOperater());
				updatePersonMemberBase.setBirthday(editPersonMember.getBirthday());
				updatePersonMemberBase.setMemberSrc(editPersonMember.getMemberSrc());
				updatePersonMemberBase.setConstellation(editPersonMember.getConstellation());
				personMemberBaseService.updatePersonMemberBase(updatePersonMemberBase);

				// 修改扩展表
				UpdatePersonMember updatePersonMember = new UpdatePersonMember();
				updatePersonMember.setCode(editPersonMember.getCodePm());
				updatePersonMember.setMemberNo(editPersonMember.getMemberNo());
				updatePersonMember.setMemberName(editPersonMember.getMemberName());
				updatePersonMember.setRemark(editPersonMember.getRemark());
				updatePersonMember.setUpdateId(editPersonMember.getOperater());
				updatePersonMember.setVersion(System.currentTimeMillis()); // 客户版本号
				updatePersonMember(updatePersonMember, updatePersonMemberBase);

				/*
				 * taskExecutor.execute(new Runnable() { // 通过线程池通知,应改为异步消息
				 * 
				 * @Override public void run() { try { // 修改资料完成度 FindPersonMemberBase
				 * findPersonMemberBase2 = new FindPersonMemberBase();
				 * findPersonMemberBase2.setCode(editPersonMember.getCode());
				 * FindPersonMemberBaseReturn findPersonMemberBaseReturn2 =
				 * personMemberBaseService .findPersonMemberBase(findPersonMemberBase2);
				 * 
				 * computeRate(findPersonMemberBaseReturn2); } catch (Exception e) {
				 * logger.error("插入各种任务分组与统计失败", e); } } });
				 */

			}
			UpdatePersonMemberReturn updatePersonMemberReturn = new UpdatePersonMemberReturn();
			return updatePersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "客户会员信息更新信息错误！", e);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.lj.business.member.service.IPersonMemberService#updatePersonMemberByMGM
	 * (com.lj.business.member.dto .UpdatePersonMember)
	 */
	public UpdatePersonMemberReturn updatePersonMemberByMGM(UpdatePersonMember updatePersonMember)
			throws TsfaServiceException {
		logger.debug("updatePersonMemberByMGM(UpdatePersonMember updatePersonMember={}) - start", updatePersonMember);

		AssertUtils.notNull(updatePersonMember);
		AssertUtils.notNullAndEmpty(updatePersonMember.getMemberNo(), "客户号不能为空");
		AssertUtils.notNullAndEmpty(updatePersonMember.getShopWx(), "终端微信不能为空");
		try {
			PersonMember personMember = new PersonMember();
			// update数据录入
			personMember.setMemberNo(updatePersonMember.getMemberNo());
			personMember.setShopWx(updatePersonMember.getShopWx());
			personMember.setMemberName(updatePersonMember.getMemberName());
			personMember.setMemberNoGm(updatePersonMember.getMemberNoGm());
			personMember.setMemberNameGm(updatePersonMember.getMemberNameGm());
			personMember.setMerchantNo(updatePersonMember.getMerchantNo());
			personMember.setMerchantName(updatePersonMember.getMerchantName());
			personMember.setSpruceUp(updatePersonMember.getSpruceUp());
			personMember.setHouses(updatePersonMember.getHouses());
			personMember.setKeepEye(updatePersonMember.getKeepEye());
			personMember.setUrgencyPm(updatePersonMember.getUrgencyPm());
			personMember.setBomCode(updatePersonMember.getBomCode());
			personMember.setBomName(updatePersonMember.getBomName());
			personMember.setFirstIntroduce(updatePersonMember.getFirstIntroduce());
			personMember.setUpdateId(updatePersonMember.getUpdateId());
			personMember.setUpdateDate(updatePersonMember.getUpdateDate());
			if (StringUtils.isNotEmpty(updatePersonMember.getNickNameRemarkLocal())) {
				if (updatePersonMember.getNickNameRemarkLocal().length() <= 190) {
					personMember.setNickNameRemarkLocal(updatePersonMember.getNickNameRemarkLocal());
				} else {
					personMember.setNickNameRemarkLocal(updatePersonMember.getNickNameRemarkLocal().substring(0, 190));
				}
			}
			personMember.setRemark4(updatePersonMember.getRemark4());
			personMember.setRemark3(updatePersonMember.getRemark3());
			personMember.setRemark2(updatePersonMember.getRemark2());
			personMember.setRemark(updatePersonMember.getRemark());
			personMember.setContactDateLast(updatePersonMember.getContactDateLast());

			personMember.setWxFriends(updatePersonMember.getWxFriends()); // 是否微信好友
			personMember.setVersion(System.currentTimeMillis()); // 客户版本号

			AssertUtils.notUpdateMoreThanOne(personMemberDao.updateByMGM(personMember));
			UpdatePersonMemberReturn updatePersonMemberReturn = new UpdatePersonMemberReturn();

			logger.debug("updatePersonMemberByMGM(UpdatePersonMember) - end - return value={}",
					updatePersonMemberReturn);
			return updatePersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "客户会员信息更新信息错误！", e);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#findPersonMember(
	 * com.lj.business.member.dto .FindPersonMember)
	 */
	@Override
	public FindPersonMemberReturn findPersonMember(FindPersonMember findPersonMember) throws TsfaServiceException {
		logger.debug("findPersonMember(FindPersonMember findPersonMember={}) - start", findPersonMember);

		AssertUtils.notNull(findPersonMember);
		AssertUtils.notAllNull(findPersonMember.getCode(), "Code不能为空");
		try {
			PersonMember personMemberQuery = new PersonMember();
			personMemberQuery.setCode(findPersonMember.getCode());
			personMemberQuery.setMemberNo(findPersonMember.getMemberNo());
			personMemberQuery.setShopWx(findPersonMember.getShopWx());
			personMemberQuery.setMerchantNo(findPersonMember.getMerchantNo());
			PersonMember personMember = personMemberDao.selectByParamKey(personMemberQuery);
			if (personMember == null) {
				return null;
			}
			FindPersonMemberReturn findPersonMemberReturn = new FindPersonMemberReturn();
			// find数据录入
			findPersonMemberReturn.setCode(personMember.getCode());
			findPersonMemberReturn.setMemberNo(personMember.getMemberNo());
			findPersonMemberReturn.setMemberName(personMember.getMemberName());
			findPersonMemberReturn.setMemberNoGm(personMember.getMemberNoGm());
			findPersonMemberReturn.setMemberNameGm(personMember.getMemberNameGm());
			findPersonMemberReturn.setMerchantNo(personMember.getMerchantNo());
			findPersonMemberReturn.setMerchantName(personMember.getMerchantName());
			findPersonMemberReturn.setSpruceUp(personMember.getSpruceUp());
			findPersonMemberReturn.setHouses(personMember.getHouses());
			findPersonMemberReturn.setKeepEye(personMember.getKeepEye());
			findPersonMemberReturn.setUrgencyPm(personMember.getUrgencyPm());
			findPersonMemberReturn.setBomCode(personMember.getBomCode());
			findPersonMemberReturn.setBomName(personMember.getBomName());
			findPersonMemberReturn.setCreateId(personMember.getCreateId());
			findPersonMemberReturn.setCreateDate(personMember.getCreateDate());
			findPersonMemberReturn.setUpdateId(personMember.getUpdateId());
			findPersonMemberReturn.setUpdateDate(personMember.getUpdateDate());
			findPersonMemberReturn.setRemark4(personMember.getRemark4());
			findPersonMemberReturn.setRemark3(personMember.getRemark3());
			findPersonMemberReturn.setRemark2(personMember.getRemark2());
			findPersonMemberReturn.setRemark(personMember.getRemark());
			/* 扫码相关 */
			findPersonMemberReturn.setLatitude(personMember.getLatitude());
			findPersonMemberReturn.setLongitude(personMember.getLongitude());
			findPersonMemberReturn.setScanAddress(personMember.getScanAddress());
			logger.debug("findPersonMember(FindPersonMember) - end - return value={}", findPersonMemberReturn);

			return findPersonMemberReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户会员信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查找客户会员信息信息错误！", e);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.lj.business.member.service.IPersonMemberService#findPersonMemberByMGM
	 * (com.lj.business.member.dto .FindPersonMember)
	 */
	@Override
	public FindPersonMemberReturn findPersonMemberByMGM(FindPersonMember findPersonMember) throws TsfaServiceException {
		logger.debug("findPersonMemberByMGM(FindPersonMember findPersonMember={}) - start", findPersonMember);

		AssertUtils.notNull(findPersonMember);
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNo(), "客户号不能为空");
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNoGm(), "导购号不能为空");
		try {
			PersonMember personMember = personMemberDao.selectByMGM(findPersonMember);
			if (personMember == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCode.PERSON_MEMBER_NOT_EXIST_ERROR,"客户会员信息不存在");
			}
			FindPersonMemberReturn findPersonMemberReturn = new FindPersonMemberReturn();
			// find数据录入
			findPersonMemberReturn.setCode(personMember.getCode());
			findPersonMemberReturn.setMemberNo(personMember.getMemberNo());
			findPersonMemberReturn.setMemberName(personMember.getMemberName());
			findPersonMemberReturn.setMemberNoGm(personMember.getMemberNoGm());
			findPersonMemberReturn.setMemberNameGm(personMember.getMemberNameGm());
//            findPersonMemberReturn.setShopNo(personMember.getShopNo());
//            findPersonMemberReturn.setShopName(personMember.getShopName());
			findPersonMemberReturn.setMerchantNo(personMember.getMerchantNo());
			findPersonMemberReturn.setMerchantName(personMember.getMerchantName());
			findPersonMemberReturn.setSpruceUp(personMember.getSpruceUp());
			findPersonMemberReturn.setHouses(personMember.getHouses());
			findPersonMemberReturn.setKeepEye(personMember.getKeepEye());
			findPersonMemberReturn.setUrgencyPm(personMember.getUrgencyPm());
			findPersonMemberReturn.setBomCode(personMember.getBomCode());
			findPersonMemberReturn.setBomName(personMember.getBomName());
			findPersonMemberReturn.setCreateId(personMember.getCreateId());
			findPersonMemberReturn.setCreateDate(personMember.getCreateDate());
			findPersonMemberReturn.setUpdateId(personMember.getUpdateId());
			findPersonMemberReturn.setUpdateDate(personMember.getUpdateDate());
			findPersonMemberReturn.setRemark4(personMember.getRemark4());
			findPersonMemberReturn.setRemark3(personMember.getRemark3());
			findPersonMemberReturn.setRemark2(personMember.getRemark2());
			findPersonMemberReturn.setRemark(personMember.getRemark());
			/* 扫码相关 */
			findPersonMemberReturn.setLatitude(personMember.getLatitude());
			findPersonMemberReturn.setLongitude(personMember.getLongitude());
			findPersonMemberReturn.setScanAddress(personMember.getScanAddress());
			findPersonMemberReturn.setBrandCompare(personMember.getBrandCompare());
			findPersonMemberReturn.setClientSpecial(personMember.getClientSpecial());
			findPersonMemberReturn.setConsumeFrequency(personMember.getConsumeFrequency());
			findPersonMemberReturn.setPmTypeCode(personMember.getPmTypeCode());
			findPersonMemberReturn.setPmTypeName(personMember.getPmTypeName());
			logger.debug("findPersonMemberByMGM(FindPersonMember) - end - return value={}", findPersonMemberReturn);

			return findPersonMemberReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户会员信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查找客户会员信息信息错误！", e);
		}

	}

	@Override
	public Page<FindPersonMemberPageReturn> findPersonMemberPage(FindPersonMemberPage findPersonMemberPage)
			throws TsfaServiceException {
		logger.debug("findPersonMemberPage(FindPersonMemberPage findPersonMemberPage={}) - start",
				findPersonMemberPage);

		AssertUtils.notNull(findPersonMemberPage);
		List<FindPersonMemberPageReturn> returnList = null;
		int count = 0;
		try {
			returnList = personMemberDao.findPersonMemberPage(findPersonMemberPage);
			count = personMemberDao.findPersonMemberPageCount(findPersonMemberPage);
			// 设置最新的导购名称
			if (org.apache.commons.collections.CollectionUtils.isNotEmpty(returnList)) {
				FindGuidMember guidMember = new FindGuidMember();
				for (FindPersonMemberPageReturn findPersonMemberPageReturn : returnList) {
					guidMember.setMemberNo(findPersonMemberPageReturn.getMemberNoGm());
					FindGuidMemberReturn guidMemberReturn = guidMemberService.findGuidMember(guidMember);
					if (guidMemberReturn != null)
						findPersonMemberPageReturn.setMemberNameGm(guidMemberReturn.getMemberName());
				}
			}
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		Page<FindPersonMemberPageReturn> returnPage = new Page<FindPersonMemberPageReturn>(returnList, count,
				findPersonMemberPage);

		logger.debug("findPersonMemberPage(FindPersonMemberPage) - end - return value={}", returnPage);
		return returnPage;
	}

	/**
	 * 方法说明：插入客户扩展表数据及其相关信息
	 *
	 * @param addPersonMemberBase
	 * @param merchantNo
	 * @param memberNameGm
	 * @param now
	 * @param pmb
	 * @param pm
	 * @throws ParseException
	 * @author 彭阳 CreateDate: 2017年10月11日
	 */
	private void insertPmData(AddPersonMemberBase addPersonMemberBase, String merchantNo, String memberNameGm, Date now,
			PersonMemberBase pmb, PersonMember pm) throws ParseException {
		logger.debug("插入客户扩展表");
		pm.setCode(GUID.getPreUUID());
		pm.setMerchantNo(merchantNo);
		pm.setMemberNoGm(addPersonMemberBase.getMemberNoGm());
		pm.setMemberNo(pmb.getMemberNo());
		pm.setMemberName(addPersonMemberBase.getMemberName());
		FindGuidMemberReturn findGuidMemberReturn = null;
		// 导购名称
		if (StringUtils.isEmpty(addPersonMemberBase.getMemberNameGm())) {
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(addPersonMemberBase.getMemberNoGm());
			findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
			if (findGuidMemberReturn != null) {
				memberNameGm = findGuidMemberReturn.getMemberName();
			}
		} else {
			memberNameGm = addPersonMemberBase.getMemberNameGm();
		}
		pm.setMemberNameGm(memberNameGm);
		// 商户名称
		String merchantName = "";
		if (StringUtils.isEmpty(addPersonMemberBase.getMerchantName())) {
			FindMerchantDto findMerchantDto = new FindMerchantDto();
			findMerchantDto.setMerchantNo(merchantNo);
			FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
			if (findMerchantReturnDto != null) {
				merchantName = findMerchantReturnDto.getMerchantName();
			}
		} else {
			merchantName = addPersonMemberBase.getMerchantName();
		}
		pm.setMerchantName(merchantName);
		pm.setRemark(addPersonMemberBase.getRemark());
		pm.setConsumeFrequency(addPersonMemberBase.getConsumeFrequency());
		pm.setCreateId(addPersonMemberBase.getMemberNoGm());
		pm.setCreateDate(now);
//        pm.setShopName(addPersonMemberBase.getShopName());
//        pm.setShopNo(addPersonMemberBase.getShopNo());
		pm.setUpdateDate(now);
		pm.setUpdateId(addPersonMemberBase.getMemberNoGm());
		pm.setBomName(addPersonMemberBase.getBomName());
		pm.setHouses(addPersonMemberBase.getHouses());
		pm.setBrandCompare(addPersonMemberBase.getBrandCompare());
		pm.setUrgencyPm(addPersonMemberBase.getUrgencyPm());
		pm.setContactDateLast(new Date());
		pm.setClientSpecial(addPersonMemberBase.getClientSpecial());
		pm.setSpruceUp(addPersonMemberBase.getSpruceUp());
		pm.setExpectPurchaseTime(StringUtils.isEmpty(addPersonMemberBase.getExpectPurchaseTime()) ? null
				: DateUtils.parseDate(addPersonMemberBase.getExpectPurchaseTime(),
						DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
		pm.setExpTime(StringUtils.isEmpty(addPersonMemberBase.getExpTime()) ? null
				: DateUtils.parseDate(addPersonMemberBase.getExpTime(), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
		pm.setRemark(addPersonMemberBase.getRemark());

		// 修改为意向客户--初次介绍变更 update by leopeng 2017.08.03
		if (!StringUtils.isEmpty(addPersonMemberBase.getPmTypeCode())) {
			logger.debug("修改为没有微信客户没有初次介绍！");
			// if(PmTypeType.INTENTION.toString().equals(pmType.getPmTypeType())||
			// PmTypeType.OTHER.toString().equals(pmType.getPmTypeType())){
			pm.setFirstIntroduce(FirstIntroduce.N.toString());
			// }
		}

		// update by 杨杰 2017-09-05 begin
		String nickNameRemarkLocal = "";
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(pm.getMemberName())) {
			nickNameRemarkLocal += pm.getMemberName();
		}
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(pmb.getMobile())) {
			nickNameRemarkLocal += ("-" + pmb.getMobile());
		}
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(pm.getBomName())) {
			nickNameRemarkLocal += ("-"
					+ (StringUtils.toString(pm.getBomName()).length() > 9 ? pm.getBomName().substring(0, 10)
							: StringUtils.toString(pm.getBomName())));
		}
		pm.setNickNameRemarkLocal(nickNameRemarkLocal);
		// update by 杨杰 2017-09-05 end

		pm.setWxFriends(0); // 是否微信好友:0不是
		pm.setAddType(3); // 客户新增方式：3手动新增
		pm.setVersion(System.currentTimeMillis()); // 版本号
		personMemberDao.insertSelective(pm);

		// 添加交叉客户
		DoRepeatMemberDto doRepeatMemberDto = new DoRepeatMemberDto();
		doRepeatMemberDto.setMemberNo(pmb.getMemberNo());
		doRepeatMemberDto.setMemberNoGm(addPersonMemberBase.getMemberNoGm());
		doRepeatMemberDto.setMerchantNo(merchantNo);
		doRepeatMember(doRepeatMemberDto);
	}

	/**
	 * 方法说明：插入客户基本表及其相关信息
	 *
	 * @param addPersonMemberBase
	 * @param now
	 * @return
	 * @author 彭阳 CreateDate: 2017年10月11日
	 */
	private PersonMemberBase insertPmb(AddPersonMemberBase addPersonMemberBase, Date now) {
		PersonMemberBase pmb;
		logger.debug("插入客户基本表");
		pmb = new PersonMemberBase();
		pmb.setCode(GUID.getPreUUID());
		pmb.setMemberName(addPersonMemberBase.getMemberName());
		pmb.setMobile(addPersonMemberBase.getMobile());
		pmb.setSex(addPersonMemberBase.getSex());
		pmb.setAge(addPersonMemberBase.getAge());
		pmb.setJob(addPersonMemberBase.getJob());
		pmb.setMemberNo(GUID.generateByUUID());
		pmb.setProvinceCode(addPersonMemberBase.getProvinceCode());// 省
		pmb.setCityCode(addPersonMemberBase.getCityCode());// 市
		pmb.setCityAreaCode(addPersonMemberBase.getCityAreaCode());// 区
		pmb.setAreaCode(addPersonMemberBase.getAreaCode());
		pmb.setJob(addPersonMemberBase.getJob());
		if (addPersonMemberBase.getAreaName() == null && "".equals(addPersonMemberBase.getAreaName())) {
			String areaName = areaHessianService.getAreaNameByCode(addPersonMemberBase.getAreaCode());
			pmb.setAreaName(areaName);
		}
		pmb.setMemberSrc(addPersonMemberBase.getMemberSrc());
		// 地址为空时，获取楼盘地址为地址
		if (StringUtils.isEmpty(addPersonMemberBase.getAddress()))
			pmb.setAddress(addPersonMemberBase.getHouses());
		else
			pmb.setAddress(addPersonMemberBase.getAddress());
		pmb.setStatus(MemberStatus.NORMAL.toString());
		pmb.setNameAuthFlag(NameAuthFlag.N.toString());
		pmb.setCreateId(addPersonMemberBase.getMemberNoGm());
		pmb.setCreateDate(new Date());
		pmb.setBirthday(addPersonMemberBase.getBirthday());
		pmb.setHeadAddress(addPersonMemberBase.getHeadAddress());
		pmb.setConstellation(addPersonMemberBase.getConstellation());
		pmb.setRemark(addPersonMemberBase.getRemark());
		personMemberBaseDao.insertSelective(pmb);

		// 添加客户动态
		AddBehaviorPm addBehaviorPm = new AddBehaviorPm();
		addBehaviorPm.setMemberNo(pmb.getMemberNo());
		addBehaviorPm.setMemberName(pmb.getMemberName());
		addBehaviorPm.setBehaviorDesc("暂无动态");
		addBehaviorPm.setBehaviorDate(now);
		behaviorPmService.addBehaviorPm(addBehaviorPm);
		return pmb;
	}

	@Override
	public void computeRate(FindPersonMemberBaseReturn findPersonMemberBaseReturn) throws TsfaServiceException {
		logger.debug("computeRate(FindPersonMemberBaseReturn findPersonMemberBaseReturn={}) - start",
				findPersonMemberBaseReturn);

		// 拼装设置条件
		PersonMemberRateDto personMemberRateDto = new PersonMemberRateDto();
		personMemberRateDto.setBirthday(findPersonMemberBaseReturn.getBirthday());
//        personMemberRateDto.setBomName(findPersonMemberReturn.getBomName());
//        personMemberRateDto.setBrandCompare(findPersonMemberReturn.getBrandCompare());
//        personMemberRateDto.setClientSpecial(findPersonMemberReturn.getClientSpecial());
		personMemberRateDto.setHeadAddress(findPersonMemberBaseReturn.getHeadAddress());
//        personMemberRateDto.setHouses(findPersonMemberReturn.getHouses());
		personMemberRateDto.setJob(findPersonMemberBaseReturn.getJob());
		personMemberRateDto.setMemberName(findPersonMemberBaseReturn.getMemberName());
		personMemberRateDto.setMemberSrc(findPersonMemberBaseReturn.getMemberSrc());
		personMemberRateDto.setMobile(findPersonMemberBaseReturn.getMobile());
		personMemberRateDto.setNickNameWx(findPersonMemberBaseReturn.getNickNameWx());
		personMemberRateDto.setNoWx(findPersonMemberBaseReturn.getNoWx());
		personMemberRateDto.setSex(findPersonMemberBaseReturn.getSex());
//        personMemberRateDto.setSpruceUp(findPersonMemberReturn.getSpruceUp());

		logger.debug("computeRate(==== personMemberRateDto={}", personMemberRateDto);

		// 计算完成度
		Double rate = MemberUtils.completeRateNew(personMemberRateDto);
		// 修改完成度
		UpdatePersonMemberBaseRatioClientInfoDto dto = new UpdatePersonMemberBaseRatioClientInfoDto();
		dto.setRatioClientInfo(rate);
		dto.setMemberNo(findPersonMemberBaseReturn.getMemberNo());
		personMemberBaseService.updatePersonMemberBaseRatioClientInfo(dto);

		logger.debug("computeRate() - end");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#getByCode(java.lang
	 * .String)
	 */
	@Override
	public FindPersonMemberPageReturn getByCond(Map<String, String> param) {
		AssertUtils.notNull(param);
		FindPersonMemberPageReturn findPersonMemberPageReturn = null;
		try {
			findPersonMemberPageReturn = personMemberDao.getByCond(param);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		return findPersonMemberPageReturn;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#findPmTypeIndexPage
	 * (com.lj.business.member.dto .FindPmTypeIndexPage)
	 */
	@Override
	public Page<FindPmTypeIndexPageReturn> findPmTypeIndexPage(FindPmTypeIndexPage findPmTypeIndexPage)
			throws TsfaServiceException {
		logger.debug("findPmTypeIndexPage(FindPmTypeIndexPage findPmTypeIndexPage={}) - start", findPmTypeIndexPage);

		AssertUtils.notNull(findPmTypeIndexPage);
		AssertUtils.notNullAndEmpty(findPmTypeIndexPage.getMerchantNo(), "商户编号不能为空");
//        AssertUtils.notAllNullAndEmpty(findPmTypeIndexPage.getShopNo(), findPmTypeIndexPage.getMemberNoGm());
		AssertUtils.notNullAndEmpty(findPmTypeIndexPage.getPmTypeCode(), "客户分类CODE不能为空");
		List<FindPmTypeIndexPageReturn> returnList = null;
		int count = 0;
		try {
			returnList = personMemberDao.findPmTypeIndexPage(findPmTypeIndexPage);
			count = personMemberDao.findPmTypeIndexPageCount(findPmTypeIndexPage);
		} catch (Exception e) {
			logger.error("客户管理首页分页查询错误!", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMTYPE_INDEX_PAGE_ERROR, "客户管理首页分页查询错误!", e);
		}
		Page<FindPmTypeIndexPageReturn> returnPage = new Page<FindPmTypeIndexPageReturn>(returnList, count,
				findPmTypeIndexPage);

		logger.debug("findPmTypeIndexPage(FindPmTypeIndexPage) - end - return value={}", returnPage);
		return returnPage;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#findPmTypeIndexPage
	 * (com.lj.business.member.dto .FindPmTypeIndexPage)
	 */
	@Override
	public Page<FindPmTypeIndexPageReturn> findPmTypeIndexPageLoho(FindPmTypeIndexPage findPmTypeIndexPage)
			throws TsfaServiceException {
		logger.debug("findPmTypeIndexPage(FindPmTypeIndexPage findPmTypeIndexPage={}) - start", findPmTypeIndexPage);

		AssertUtils.notNull(findPmTypeIndexPage);
		AssertUtils.notNullAndEmpty(findPmTypeIndexPage.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findPmTypeIndexPage.getPmTypeCode(), "客户分类CODE不能为空");
		List<FindPmTypeIndexPageReturn> returnList = null;
		int count = 0;
		try {
			returnList = personMemberDao.findPmTypeIndexPage(findPmTypeIndexPage);
			count = personMemberDao.findPmTypeIndexPageCount(findPmTypeIndexPage);

			FindPmLabel findPmLabel = new FindPmLabel();

			Date nowDate = new Date();
			for (FindPmTypeIndexPageReturn findPmTypeIndexPageReturn : returnList) {

				// 查询标签
				findPmLabel.setMerchantNo(findPmTypeIndexPage.getMerchantNo());
				findPmLabel.setMemberNo(findPmTypeIndexPageReturn.getMemberNo());
				findPmLabel.setShopWx(findPmTypeIndexPageReturn.getShopWx());
				List<PmLabelDto> labels = pmLabelService.findPmLabelByMemberNoAndMerchantNo(findPmLabel);
				findPmTypeIndexPageReturn.setLabels(labels);

				// 查询客户邀约情况
				if (findPmTypeIndexPageReturn.getConsumeFrequency() == null) {
					findPmTypeIndexPageReturn.setConsumeFrequency(7); // 默认消费频率为7天
				}

				// 查询顾客最后一次邀约信息
				boolean notInvite = false;
				boolean consume = false;

				findPmTypeIndexPageReturn.setLastInviteStatus("N");
				findPmTypeIndexPageReturn.setCanInvite(Boolean.TRUE);
				findPmTypeIndexPageReturn.setTextName("邀约");
				notInvite = true;

				// 判断邀约状态
				if (findPmTypeIndexPageReturn.getLastActionTime() == null) {
					findPmTypeIndexPageReturn.setLastInviteStatus("N");
					findPmTypeIndexPageReturn.setCanInvite(Boolean.TRUE);
					findPmTypeIndexPageReturn.setTextName("邀约");
				} else if (DateUtils.differentDays(findPmTypeIndexPageReturn.getLastActionTime(),
						nowDate) >= findPmTypeIndexPageReturn.getConsumeFrequency()) { //
					// 如果距离上次邀约（上次邀约时间和上次消费时间中取最大值）的天数超过邀约周期，则可以继续邀约，否则不能
					findPmTypeIndexPageReturn.setCanInvite(Boolean.TRUE);
					findPmTypeIndexPageReturn.setTextName("邀约");
				} else {
					if (notInvite && consume) {
						findPmTypeIndexPageReturn.setLastInviteStatus("N");
						findPmTypeIndexPageReturn.setCanInvite(Boolean.TRUE);
						findPmTypeIndexPageReturn.setTextName("邀约");
					} else {
						findPmTypeIndexPageReturn.setCanInvite(Boolean.FALSE);
						findPmTypeIndexPageReturn.setTextName(
								"Y".equals(findPmTypeIndexPageReturn.getLastInviteStatus()) ? "应邀" : "未应邀");
					}
				}
			}

		} catch (Exception e) {
			logger.error("客户管理首页分页查询错误!", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMTYPE_INDEX_PAGE_ERROR, "客户管理首页分页查询错误!", e);
		}
		Page<FindPmTypeIndexPageReturn> returnPage = new Page<FindPmTypeIndexPageReturn>(returnList, count,
				findPmTypeIndexPage);

		logger.debug("findPmTypeIndexPage(FindPmTypeIndexPage) - end - return value={}", returnPage);
		return returnPage;

	}

	@Override
	public Page<FindPmTypeIndexPageReturn> findPmTypeIndexPageHc(FindPmTypeIndexPage findPmTypeIndexPage)
			throws TsfaServiceException {
		logger.debug("findPmTypeIndexPage(FindPmTypeIndexPage findPmTypeIndexPage={}) - start", findPmTypeIndexPage);

		AssertUtils.notNull(findPmTypeIndexPage);
		AssertUtils.notNullAndEmpty(findPmTypeIndexPage.getMerchantNo(), "商户编号不能为空");
//        AssertUtils.notAllNullAndEmpty(findPmTypeIndexPage.getShopNo(), findPmTypeIndexPage.getMemberNoGm());
		AssertUtils.notNullAndEmpty(findPmTypeIndexPage.getPmTypeCode(), "客户分类CODE不能为空");
		List<FindPmTypeIndexPageReturn> returnList = null;
		int count = 0;
		try {
			returnList = personMemberDao.findPmTypeIndexPage(findPmTypeIndexPage);
			count = personMemberDao.findPmTypeIndexPageCount(findPmTypeIndexPage);

			/*
			 * FindPmType findPmType = new FindPmType();
			 * findPmType.setMerchantNo(findPmTypeIndexPage.getMerchantNo());
			 * findPmType.setPmTypeType(PmTypeType.REPEAT.toString()); FindPmTypeReturn
			 * findPmTypeReturn = pmTypeService.findPmTypeByMP(findPmType); if
			 * (findPmTypeReturn != null) { if
			 * (findPmTypeReturn.getCode().equals(findPmTypeIndexPage.getPmTypeCode())) {
			 * logger.debug("交叉客户数量查询！"); PersonMember record = new PersonMember(); for
			 * (FindPmTypeIndexPageReturn findPmTypeIndexPageReturn : returnList) {
			 * record.setMemberNo(findPmTypeIndexPageReturn.getMemberNo());
			 * record.setMerchantNo(findPmTypeIndexPage.getMerchantNo()); int repeatCount =
			 * personMemberDao.findPmTypeRepeatCount(record);
			 * findPmTypeIndexPageReturn.setRepeatCount(repeatCount); } } }
			 */
			Date nowDate = new Date();
//            FindLastClientInviteHc findLastClientInviteHc = new FindLastClientInviteHc();
//            FindClientConsumeCount findClientConsumeCount = new FindClientConsumeCount();
			for (FindPmTypeIndexPageReturn client : returnList) {

				// 查询客户邀约情况
				if (client.getConsumeFrequency() == null) {
					client.setConsumeFrequency(7); // 默认消费频率为7天
				}

				// 查询顾客最后一次邀约信息
				boolean notInvite = false;
				boolean consume = false;

				client.setLastInviteStatus("N");
				client.setCanInvite(Boolean.TRUE);
				client.setTextName("邀约");
				notInvite = true;

				// 判断邀约状态
				if (client.getLastActionTime() == null) {
					client.setLastInviteStatus("N");
					client.setCanInvite(Boolean.TRUE);
					client.setTextName("邀约");
				} else if (DateUtils.differentDays(client.getLastActionTime(), nowDate) >= client
						.getConsumeFrequency()) { // 如果距离上次邀约（上次邀约时间和上次消费时间中取最大值）的天数超过邀约周期，则可以继续邀约，否则不能
					client.setCanInvite(Boolean.TRUE);
					client.setTextName("邀约");
				} else {
					if (notInvite && consume) {
						client.setLastInviteStatus("N");
						client.setCanInvite(Boolean.TRUE);
						client.setTextName("邀约");
					} else {
						client.setCanInvite(Boolean.FALSE);
						client.setTextName("Y".equals(client.getLastInviteStatus()) ? "应邀" : "未应邀");
					}
				}

			}

		} catch (Exception e) {
			logger.error("客户管理首页分页查询错误!", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMTYPE_INDEX_PAGE_ERROR, "客户管理首页分页查询错误!", e);
		}
		Page<FindPmTypeIndexPageReturn> returnPage = new Page<FindPmTypeIndexPageReturn>(returnList, count,
				findPmTypeIndexPage);

		logger.debug("findPmTypeIndexPage(FindPmTypeIndexPage) - end - return value={}", returnPage);
		return returnPage;

	}

	@Override
	public Page<FindPmSeachPageReturn> findPmSeachPage(FindPmSeachPage findPmSeachPage) throws TsfaServiceException {
		logger.debug("findPmSeachPage(FindPmSeachPage findPmSeachPage={}) - start", findPmSeachPage);

		AssertUtils.notNull(findPmSeachPage);
		AssertUtils.notNullAndEmpty(findPmSeachPage.getMerchantNo(), "商户编号不能为空");
//        AssertUtils.notAllNullAndEmpty(findPmSeachPage.getShopNo(), findPmSeachPage.getMemberNoGm());
		List<FindPmSeachPageReturn> returnList = null;
		FindPersonMemberBaseList memberBaseList = new FindPersonMemberBaseList();
		int count = 0;
		try {
			returnList = personMemberDao.findPmSeachPage(findPmSeachPage);
			for (FindPmSeachPageReturn findPmSeachPageReturn : returnList) {
				findPmSeachPageReturn.setBehaviorDesc(
						"客户资料完成" + (int) (findPmSeachPageReturn.getRatioClientInfo().doubleValue() * 100) + "%");
				memberBaseList.setMemberNo(findPmSeachPageReturn.getMemberNo());
				FindPersonMemberBaseReturnList baseReturnList = personMemberBaseService
						.findPersonMemberBaseList(memberBaseList);
				findPmSeachPageReturn.setMobile(baseReturnList.getMobile());

				// 查询标签列表
				Map<String, String> map = new HashMap<>();
				map.put("merchantNo", findPmSeachPage.getMerchantNo());
				map.put("memberNo", findPmSeachPageReturn.getMemberNo());
				map.put("shopWx", findPmSeachPageReturn.getShopWx());
				List<PmLabelDto> labels = pmLabelPmDao.findPmLabelByCond(map);
				findPmSeachPageReturn.setLabels(labels);
			}
			count = personMemberDao.findPmSeachPageCount(findPmSeachPage);
		} catch (Exception e) {
			logger.error("客户管理首页分页查询错误!", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMTYPE_INDEX_PAGE_ERROR, "客户管理首页分页查询错误!", e);
		}
		Page<FindPmSeachPageReturn> returnPage = new Page<FindPmSeachPageReturn>(returnList, count, findPmSeachPage);

		logger.debug("findPmSeachPage(FindPmSeachPage) - end - return value={}", returnPage);
		return returnPage;
	}

	/**
	 * 
	 * @Override public Page<FindUrgentMbrPageReturn>
	 *           findUrgentMbrPage(FindUrgentMbrPage findUrgentMbrPage) throws
	 *           TsfaServiceException {
	 *           logger.debug("findUrgentMbrPage(FindUrgentMbrPage
	 *           findUrgentMbrPage={}) - start", findUrgentMbrPage);
	 * 
	 *           AssertUtils.notNull(findUrgentMbrPage);
	 *           AssertUtils.notNullAndEmpty(findUrgentMbrPage.getMerchantNo(),
	 *           "商户编号不能为空"); //
	 *           AssertUtils.notAllNullAndEmpty(findUrgentMbrPage.getShopNo(),
	 *           findUrgentMbrPage.getMemberNoGm()); List<FindUrgentMbrPageReturn>
	 *           returnList = null; int count = 0; try { returnList =
	 *           personMemberDao.findUrgentMbrPage(findUrgentMbrPage); count =
	 *           personMemberDao.findUrgentMbrPageCount(findUrgentMbrPage); } catch
	 *           (Exception e) { logger.error("客户管理首页分页查询错误!", e); throw new
	 *           TsfaServiceException(ErrorCode.FIND_PMTYPE_INDEX_PAGE_ERROR,
	 *           "客户管理首页分页查询错误!", e); } Page<FindUrgentMbrPageReturn> returnPage =
	 *           new Page<FindUrgentMbrPageReturn>(returnList, count,
	 *           findUrgentMbrPage);
	 * 
	 *           logger.debug("findUrgentMbrPage(FindUrgentMbrPage) - end - return
	 *           value={}", returnPage); return returnPage; }
	 * 
	 *           public boolean isToShop(String shopLongitude, String shopLatitude,
	 *           String pmLongitude, String pmLatitude, String distance) { boolean
	 *           toShopFlag = false; if (!StringUtils.isEmpty(shopLongitude) &&
	 *           !StringUtils.isEmpty(shopLatitude) &&
	 *           !StringUtils.isEmpty(pmLongitude) &&
	 *           !StringUtils.isEmpty(pmLatitude) && !StringUtils.isEmpty(distance))
	 *           { double dis =
	 *           DistanceUtil.getDistance(Double.valueOf(shopLongitude),
	 *           Double.valueOf(shopLatitude), Double.valueOf(pmLongitude),
	 *           Double.valueOf(pmLatitude)); if (dis <= Double.valueOf(distance)) {
	 *           toShopFlag = true; }
	 * 
	 *           logger.info("dis=" + dis + ",toShopFlag=" + toShopFlag); } return
	 *           toShopFlag; }
	 * 
	 *           /* (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#addPersonMemberFromHook
	 *      (java.lang.String)
	 */
	/*
	 * @Override
	 * 
	 * @Deprecated public void addPersonMemberFromHook(String infos) throws
	 * TsfaServiceException {
	 * logger.debug("addPersonMemberFromHook(String infos={}) - start", infos); try
	 * { // 先插入客户基础表 JSONObject jsonObject = JSONObject.fromObject(infos); String
	 * noWxGM = jsonObject.getString("noWxGM"); String memberNoGuid = null; if
	 * (jsonObject.containsKey("memberNoGuid")) memberNoGuid =
	 * jsonObject.getString("memberNoGuid");// 导购编号 JSONArray ja =
	 * jsonObject.getJSONArray("data"); // 根据导购微信查询导购和终端信息 GuidMember gm = new
	 * GuidMember(); if (!StringUtils.isEmpty(noWxGM))// 预防NO_WX为空，造成全表扫描
	 * gm.setNoWx(noWxGM); if (!StringUtils.isEmpty(memberNoGuid))
	 * gm.setMemberNo(memberNoGuid); else {
	 * logger.error("【同步客户信息】同步客户信息出错,微信号，导购会员编号全部为空！"); return; }
	 * 
	 * GuidMember guidMember = iguidMemberDao.selectByParams(gm); if (guidMember ==
	 * null) { throw new TsfaServiceException(ErrorCode.GUID_MEMBER_NOT_EXIST_ERROR,
	 * "导购信息不存在"); }
	 * 
	 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	 * 
	 * for (int i = 0; i < ja.size(); i++) { JSONObject jObj = null; try { jObj =
	 * ja.getJSONObject(i); logger.debug("jObj:" + jObj.toString());
	 * 
	 * AddPersonMemberByWx addPersonMemberByWx = new AddPersonMemberByWx();
	 * addPersonMemberByWx.setMemberNoGuid(memberNoGuid);
	 * addPersonMemberByWx.setNoWxGm(noWxGM); String noWx = jObj.containsKey("noWx")
	 * ? jObj.getString("noWx") : ""; addPersonMemberByWx.setNoWx(noWx); String
	 * alias = jObj.containsKey("alias") ? jObj.getString("alias") : "";
	 * addPersonMemberByWx.setAlias(alias); String nickNameWx =
	 * jObj.containsKey("nickNameWx") ? jObj.getString("nickNameWx") : "";
	 * addPersonMemberByWx.setNickNameWx(nickNameWx); String headAddress =
	 * jObj.containsKey("headAddress") ? jObj.getString("headAddress") : "";
	 * addPersonMemberByWx.setHeadAddress(headAddress);
	 * 
	 * 扩展表参数 String nickNameRemarkWx = jObj.containsKey("nickNameRemarkWx") ?
	 * jObj.getString("nickNameRemarkWx") : "";
	 * addPersonMemberByWx.setNickNameRemarkWx(nickNameRemarkWx); String longitude =
	 * jObj.containsKey("longitude") ? jObj.getString("longitude") : "";
	 * addPersonMemberByWx.setLongitude(longitude); String latitude =
	 * jObj.containsKey("latitude") ? jObj.getString("latitude") : "";
	 * addPersonMemberByWx.setLatitude(latitude); String scanAddress =
	 * jObj.containsKey("scanAddress") ? jObj.getString("scanAddress") : "";
	 * addPersonMemberByWx.setScanAddress(scanAddress);
	 * addPersonMemberByWx.setAddType(4); // 客户新增方式：4勾子 try { if
	 * (jObj.containsKey("subsribeTime"))
	 * addPersonMemberByWx.setSubsribeTime(df.parse(jObj.getString("subsribeTime")))
	 * ; } catch (Exception e) { logger.error("时间转换错误!", e); }
	 * 
	 *//**
		 * 获取终端信息
		 *//*
			 * FindShopTerminalReturn findShopTerminalReturn = shopTerminalService
			 * .findShopTerminalByWx(addPersonMemberByWx.getNoWxGm()); if
			 * (findShopTerminalReturn == null) { logger.error("终端微信不存在或者无效：{}",
			 * addPersonMemberByWx.getNoWxGm()); throw new
			 * TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, "无效的终端微信"); }
			 * // 利用AOP，引入自身，调用子方法才有事务
			 * personMemberServiceImpl.addPersonMemberByWx(guidMember,addPersonMemberByWx,
			 * findShopTerminalReturn); } catch (Exception e) {
			 * logger.error("【同步客户信息】同步客户信息出错，抛弃客户:" + (jObj == null ? null :
			 * jObj.toString())); logger.error("【同步客户信息】同步客户信息出错！", e); } }
			 * logger.debug("addPersonMemberFromHook(String) - end"); } catch (Exception e)
			 * { logger.error("同步客户信息出错！", e); throw new
			 * TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "【同步客户信息】同步客户信息出错！",
			 * e); }
			 * 
			 * }
			 */

	@Override
	public AddPersonMemberByWxReturn addPersonMemberByWx(AddPersonMemberByWx addPersonMemberByWx)
			throws TsfaServiceException {
		logger.debug("addPersonMemberByWx(AddPersonMemberByWx addPersonMemberByWx={}) - start", addPersonMemberByWx);

		AssertUtils.notNull(addPersonMemberByWx);
		AssertUtils.notNullAndEmpty(addPersonMemberByWx.getNoWxGm(), "终端微信号不能为空！");

		/**
		 * 获取终端信息
		 */
		FindShopTerminalReturn findShopTerminalReturn = shopTerminalService
				.findShopTerminalByWx(addPersonMemberByWx.getNoWxGm());
		if (findShopTerminalReturn == null) {
			logger.error("终端微信不存在或者无效：{}", addPersonMemberByWx.getNoWxGm());
			throw new TsfaServiceException(ErrorCode.SHOP_TERMINAL_NOT_NORMAL_ERROR, "无效的终端微信");
		}

		// 根据导购微信查询导购信息
		FindGmAssistantShopReturn guidMember = gmAssistantShopService.findGmByWxAndNo(addPersonMemberByWx.getNoWxGm(),
				addPersonMemberByWx.getMemberNoGuid());
		if (guidMember == null) {
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_NOT_EXIST_ERROR, "导购信息不存在");
		}

		// 利用AOP，引入自身，调用子方法才有事务
		AddPersonMemberByWxReturn personMember = personMemberServiceImpl.addPersonMemberByWx(guidMember,
				addPersonMemberByWx, findShopTerminalReturn);
		logger.debug("addPersonMemberByWx(AddPersonMemberByWx) - end - return value={}", personMember);
		return personMember;
	}

	public AddPersonMemberByWxReturn addPersonMemberByWx(FindGmAssistantShopReturn guidMember,
			AddPersonMemberByWx addPersonMemberByWx, FindShopTerminalReturn findShopTerminalReturn)
			throws TsfaServiceException {
		try {
			logger.info(
					"AddPersonMemberByWxReturn addPersonMemberByWx(AddPersonMemberByWx addPersonMemberByWx={},FindShopTerminalReturn findShopTerminalReturn={})",
					addPersonMemberByWx, findShopTerminalReturn);

			Date now = new Date();

			// 根据微信号查询客户基础信息
			FindPersonMemberBase fmb = new FindPersonMemberBase();
			fmb.setNoWx(addPersonMemberByWx.getNoWx());
			PersonMemberBase personMemberBase = personMemberBaseDao.selectByParams(fmb);

			AddPersonMemberByWxReturn addPersonMemberByWxReturn = null;

			/* 客户基础数据存在 */
			boolean firstAdd = Boolean.TRUE; // 是否首次添加
			if (personMemberBase != null) {
				logger.debug("基础数据存在");

				/* 修改客户基础信息 */
				logger.debug("修改客户微信昵称--code={}--nickNameWx={}--start", personMemberBase.getCode(),
						addPersonMemberByWx.getNickNameWx());
				UpdatePersonMemberBase updatePersonMemberBase = new UpdatePersonMemberBase();

				updatePersonMemberBase.setCode(personMemberBase.getCode());
				updatePersonMemberBase.setNickNameWx(addPersonMemberByWx.getNickNameWx());

				// update by LeoPeng 2017.10.16 如果微信别名不存在，且有新的微信别名，修改微信号别名
				if (StringUtils.isEmpty(personMemberBase.getNoWxAlias())
						&& !StringUtils.isEmpty(addPersonMemberByWx.getAlias())) {
					updatePersonMemberBase.setNoWxAlias(addPersonMemberByWx.getAlias());
					personMemberBase.setNoWxAlias(updatePersonMemberBase.getNoWxAlias());
				}
				// 性别
				if (StringUtils.isNotEmpty(addPersonMemberByWx.getSex())) {
					updatePersonMemberBase.setSex(addPersonMemberByWx.getSex());
					personMemberBase.setSex(updatePersonMemberBase.getSex());
				}
				// 头像
				if (StringUtils.isNotEmpty(addPersonMemberByWx.getHeadAddress())) {
					updatePersonMemberBase.setHeadAddress(addPersonMemberByWx.getHeadAddress());
					personMemberBase.setHeadAddress(updatePersonMemberBase.getHeadAddress());
				}
				// 手机号
				if (StringUtils.isNotEmpty(addPersonMemberByWx.getMobile())
						&& StringUtils.isEmpty(personMemberBase.getMobile())) {
					updatePersonMemberBase.setMobile(addPersonMemberByWx.getMobile());
				}

				if (StringUtils.isEmpty(personMemberBase.getMemberName())) {
					updatePersonMemberBase.setMemberName(addPersonMemberByWx.getMemberName());
					personMemberBase.setMemberName(addPersonMemberByWx.getMemberName());
				}

				/**
				 * 新增字段：微信OPENID wxOpenId、QQ号 noQQ update by zengchuiyu update by 2018-01-11
				 */
//				if (StringUtils.isEmpty(personMemberBase.getNoWx())) {
//					updatePersonMemberBase.setWxOpenId(WxOpenIdUtils.generateWxOpenId(personMemberBase.getNoWx()));
//				}
				updatePersonMemberBase.setNoQQ(addPersonMemberByWx.getNoQQ());
				updatePersonMemberBase.setMemberSrc(addPersonMemberByWx.getMemberSrc());
				personMemberBaseService.updatePersonMemberBase(updatePersonMemberBase);
				/* 修改客户基础信息 */

			} else {
				logger.debug("基础数据不存在");
				personMemberBase = new PersonMemberBase();
				personMemberBase.setCode(GUID.generateCode());// 主键
				personMemberBase.setMemberNo(GUID.generateCode());
				personMemberBase.setMemberName(addPersonMemberByWx.getMemberName());
				personMemberBase.setHeadAddress(addPersonMemberByWx.getHeadAddress());
				personMemberBase.setNickNameWx(addPersonMemberByWx.getNickNameWx());
				personMemberBase.setNoWx(addPersonMemberByWx.getNoWx());
				personMemberBase.setNoWxAlias(addPersonMemberByWx.getAlias());
				personMemberBase.setSubsribeTime(addPersonMemberByWx.getSubsribeTime());
				personMemberBase.setCreateDate(now);
				personMemberBase.setCreateId(addPersonMemberByWx.getNoWxGm());
				personMemberBase.setUpdateDate(now);
				personMemberBase.setUpdateId(addPersonMemberByWx.getNoWxGm());
				personMemberBase.setNameAuthFlag(NameAuthFlag.N.toString());
				personMemberBase.setStatus(MemberStatus.NORMAL.toString());
				personMemberBase.setRatioClientInfo(MemberUtils.completeRate(personMemberBase));
				personMemberBase.setSex(addPersonMemberByWx.getSex());
				// 强行生成wx_openId
				if (StringUtils.isNotEmpty(addPersonMemberByWx.getNoWx())) {
					personMemberBase.setWxOpenId(WxOpenIdUtils.generateWxOpenId(addPersonMemberByWx.getNoWx()));
				}
				personMemberBase.setNoQQ(addPersonMemberByWx.getNoQQ());
				personMemberBase.setMemberSrc(addPersonMemberByWx.getMemberSrc());
				personMemberBase.setMobile(addPersonMemberByWx.getMobile());
				personMemberBaseDao.insertSelective(personMemberBase);

			}

			logger.debug("插入客户扩展表数据");
			// 客户关联表数据是否存在
			PersonMember personMember = new PersonMember();
			personMember.setMerchantNo(findShopTerminalReturn.getMerchantNo());
			personMember.setShopWx(addPersonMemberByWx.getNoWxGm());
			personMember.setMemberNo(personMemberBase.getMemberNo());
			personMember.setMemberNoGm(guidMember.getAssistantNo());
			PersonMember personMemberTemp = personMemberDao.selectByParamKey(personMember);
			if (personMemberTemp != null) {// 数据已存在
				logger.debug("客户扩展表数据已存在");
				firstAdd = Boolean.FALSE;
				logger.debug("修改客户微信昵称备注--code={}--nickNameRemarkWx={}--start", personMemberTemp.getCode(),
						addPersonMemberByWx.getNickNameRemarkWx());
				UpdatePersonMember updatePersonMember = new UpdatePersonMember();
				updatePersonMember.setCode(personMemberTemp.getCode());
				updatePersonMember.setWxFriends(1); // 是否微信好友：1是
				updatePersonMember.setVersion(System.currentTimeMillis()); // 客户版本号

				if (StringUtils.isEmpty(personMemberTemp.getMemberName())) {
					updatePersonMember.setMemberName(personMemberBase.getMemberName());
				}

				if (addPersonMemberByWx.getAddType() != null) {
					updatePersonMember.setAddType(addPersonMemberByWx.getAddType());
				}
				personMemberService.updatePersonMember(updatePersonMember);

				addPersonMemberByWxReturn = new AddPersonMemberByWxReturn();
				addPersonMemberByWxReturn.setMemberNo(personMemberTemp.getMemberNo());
				addPersonMemberByWxReturn.setMemberName(personMemberBase.getMemberName());
				addPersonMemberByWxReturn.setMobile(personMemberBase.getMobile());
				addPersonMemberByWxReturn.setVersion(updatePersonMember.getVersion());
				addPersonMemberByWxReturn.setCodePm(personMemberTemp.getCode());
				addPersonMemberByWxReturn.setPmTypeCode(personMemberTemp.getPmTypeCode());
				addPersonMemberByWxReturn.setPmTypeName(personMemberTemp.getPmTypeName());
			} else {
				logger.debug("插入客户扩展表数据");
				PersonMember addPersonMember = new PersonMember();

				/* 扩展表参数 */
				addPersonMember.setNickNameRemarkWx(addPersonMemberByWx.getNickNameRemarkWx());
				addPersonMember.setLatitude(addPersonMemberByWx.getLatitude());
				addPersonMember.setLongitude(addPersonMemberByWx.getLongitude());
				addPersonMember.setScanAddress(addPersonMemberByWx.getScanAddress());
				addPersonMember.setMerchantNo(findShopTerminalReturn.getMerchantNo());
				addPersonMember.setMerchantName(findShopTerminalReturn.getMerchantName());
				addPersonMember.setMemberNoGm(guidMember.getAssistantNo());
				addPersonMember.setMemberNameGm(guidMember.getAssistantName());
				/* 扩展表参数 */
				addPersonMemberByWxReturn = insertPm(addPersonMemberByWx, personMemberBase, addPersonMember);
			}

			/**
			 * 给用户添加标签
			 */
			addLabels(addPersonMemberByWx.getLabelName(), findShopTerminalReturn.getMerchantNo(),
					addPersonMemberByWxReturn.getMemberNo(), findShopTerminalReturn.getNoWx(),
					guidMember.getAssistantNo(), guidMember.getAssistantName());

			final PersonMemberBase finalPersonMemberBaseMessage = personMemberBase;
			final Boolean fianlfirstAdd = firstAdd;
			taskExecutor.execute(new Runnable() { // 通过线程池通知,应改为异步消息
				@Override
				public void run() {
					try {
						// 异步发送优惠券、名片等
						ayncSendMessageByNewMemberService.sendMessage(guidMember, findShopTerminalReturn,
								finalPersonMemberBaseMessage, addPersonMemberByWx, fianlfirstAdd);
					} catch (Exception e) {
						logger.error("插入各种任务分组与统计失败", e);
					}
				}
			});
			return addPersonMemberByWxReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("【同步客户信息】同步客户信息出错，抛弃客户");
			logger.error("【同步客户信息】同步客户信息出错！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_ADD_ERROR, "同步客户信息出错！");
		}

	}

	/**
	 * @param addPersonMemberByWx
	 * @param findShopTerminalReturn
	 * @param addPersonMemberByWxReturn
	 * @throws TsfaServiceException
	 */
	@Override
	public void addLabels(String labelsName, String merchantNo, String memberNo, String shopWx, String memberNoGm,
			String memberNameGm) throws TsfaServiceException {
		if (StringUtils.isNotEmpty(labelsName)) {
			taskExecutor.execute(new Runnable() { // 通过线程池通知,应改为异步消息 TODO
				@Override
				public void run() {
					try {
						// 查询商户已有标签
						List<PmLabelDto> oldLabels = pmLabelService.findPmLabelByMerchantNo(merchantNo);
						/**
						 * 服务器已存在的标签库 key = 标签名称 value = 标签对象
						 */
						Map<String, PmLabelDto> oldLabelsMap = new HashMap<String, PmLabelDto>();
						for (PmLabelDto pmLabelDto : oldLabels) {
							oldLabelsMap.put(pmLabelDto.getLabelName(), pmLabelDto);
						}
						// 查询或插入标签
						String[] lableNams = labelsName.split(";");
						for (String str : lableNams) {
							PmLabelDto pmLabelDto = null;
							int type = 0; // 公共标签
							if (oldLabelsMap.containsKey(str)) {
								// 存在
								pmLabelDto = oldLabelsMap.get(str);
							} else {
								type = 1; // 个人标签
								// 不存在，则创建个人标签插入
								GmLabelDto gmLabelDto = new GmLabelDto();
								gmLabelDto.setLabelName(str);
								gmLabelDto.setMerchantNo(merchantNo);
								gmLabelDto.setCreateId(shopWx);
								gmLabelDto.setMemberNoGm(memberNoGm);
								gmLabelDto.setMemberNameGm(memberNameGm);
								GmLabelDto addReturn = gmLabelService.addGmLabel(gmLabelDto);
								pmLabelDto = new PmLabelDto();
								pmLabelDto.setCode(addReturn.getCode());
								pmLabelDto.setLabelName(str);
							}

							PmLabelPm pmLabelPm = new PmLabelPm();
							pmLabelPm.setMemberNo(memberNo);
							pmLabelPm.setPmLabelCode(pmLabelDto.getCode());
							pmLabelPm.setPmLabelName(pmLabelDto.getLabelName());
							pmLabelPm.setShopWx(shopWx);
							pmLabelPm.setCreateDate(new Date());
							pmLabelPm.setType(type);
							pmLabelPmDao.insertOrUpdate(pmLabelPm);
						}
					} catch (Exception e) {
						logger.error("添加标签错误 e={}", e);
					}
				}
			});
		}
	}

	public void innerProcess(boolean toShopFlag, GuidMember guidMember, PersonMemberBase personMemberBase,
			PersonMember addPersonMember) {

		// 添加积分
		GuidMemberIntegralDto guidMemberIntegralDto = new GuidMemberIntegralDto();
		guidMemberIntegralDto.setMerchantNo(guidMember.getMerchantNo());
		guidMemberIntegralDto.setMemberNo(addPersonMember.getMemberNoGm());
		guidMemberIntegralDto.setAreaCode(guidMember.getAreaCode());
		guidMemberIntegralDto.setIntegralType(IntegralType.NEW.toString());
		guidMemberIntegralDto.setAmount(1d);
		guidMemberIntegralService.doIntegral(guidMemberIntegralDto);

		// 添加到导购行为统计表
		AddGuidmemberActionInfo addGuidmemberActionInfo = new AddGuidmemberActionInfo();
		addGuidmemberActionInfo.setCode(GUID.generateCode());
		addGuidmemberActionInfo.setActionType(GuidmemberActionType.NEW.toString());
		addGuidmemberActionInfo.setActionTime(new Date());
		addGuidmemberActionInfo.setCreateDate(new Date());
		addGuidmemberActionInfo.setMerchantNo(guidMember.getMerchantNo());
		addGuidmemberActionInfo.setMemberNoGm(guidMember.getMemberNo());
		addGuidmemberActionInfo.setMemberNameGm(guidMember.getMemberName());
		addGuidmemberActionInfo.setActionDetail(guidMember.getMemberName() + "新增了一个用户");
		guidmemberActionInfoService.addGuidmemberActionInfo(addGuidmemberActionInfo);
	}

	/**
	 * Insert pm.
	 *
	 * @param guidMember       the guid member
	 * @param personMemberBase the person member base
	 * @param addPersonMember  the add person member
	 */
	private AddPersonMemberByWxReturn insertPm(AddPersonMemberByWx addPersonMemberByWx,
			PersonMemberBase personMemberBase, PersonMember addPersonMember) {
		addPersonMember.setCode(GUID.generateCode());
		addPersonMember.setMemberNo(personMemberBase.getMemberNo());
		addPersonMember.setMemberName(personMemberBase.getMemberName());
		addPersonMember.setCreateId(addPersonMemberByWx.getNoWxGm());
		addPersonMember.setKeepEye("Y");
		addPersonMember.setUrgencyPm(UrgentFlagType.N.toString());
		addPersonMember.setFirstIntroduce(FirstIntroduce.Y.toString());
		addPersonMember.setNickNameRemarkWx(addPersonMemberByWx.getNickNameRemarkWx());
		// update by 杨杰 2017-09-05 begin
		String nickNameRemarkLocal = "";
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(personMemberBase.getMemberName())) {
			nickNameRemarkLocal += personMemberBase.getMemberName();
		}
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(personMemberBase.getMobile())) {
			nickNameRemarkLocal += ("-" + personMemberBase.getMobile());
		}
		addPersonMember.setNickNameRemarkLocal(nickNameRemarkLocal);
		// update by 杨杰 2017-09-05 end
		addPersonMember.setWxFriends(1); // 是否微信好友：1是
		addPersonMember.setAddType(addPersonMemberByWx.getAddType()); // 客户新增方式
		addPersonMember.setVersion(System.currentTimeMillis()); // 客户版本号
		addPersonMember.setShopWx(addPersonMemberByWx.getNoWxGm()); // 终端微信
		personMemberDao.insertSelective(addPersonMember);

		/*
		 * GmMemberRelateInfoDto gmMemberInfo = new GmMemberRelateInfoDto();
		 * gmMemberInfo.setMemberNoGm(addPersonMember.getMemberNoGm());
		 * gmMemberInfo.setMemberNameGm(addPersonMember.getMemberNameGm());
		 * gmMemberInfo.setMemberNo(addPersonMember.getMemberNo());
		 * gmMemberInfo.setMemberName(addPersonMember.getMemberName());
		 * gmMemberInfo.setGmWx(addPersonMemberByWx.getNoWxGm());
		 * gmMemberInfo.setNoWx(personMemberBase.getNoWx());
		 * gmMemberInfo.setNoWxAlias(personMemberBase.getNoWxAlias());
		 * gmMemberInfo.setMerchantNo(addPersonMember.getMerchantNo());
		 * gmMemberInfo.setMerchantName(addPersonMember.getMerchantName()); try {
		 * personMemberImService.cacheGmMemberRelateInfo(gmMemberInfo); } catch
		 * (Exception e) { logger.error("缓存用户关联信息失败！", e); }
		 */

		AddPersonMemberByWxReturn addPersonMemberByWxReturn = new AddPersonMemberByWxReturn();
		addPersonMemberByWxReturn.setMemberNo(personMemberBase.getMemberNo());
		addPersonMemberByWxReturn.setMemberName(personMemberBase.getMemberName());
		addPersonMemberByWxReturn.setNickNameRemarkLocal(nickNameRemarkLocal);
		addPersonMemberByWxReturn.setMobile(personMemberBase.getMobile());
		addPersonMemberByWxReturn.setVersion(addPersonMember.getVersion());
		/**
		 * 客户分类相关信息 DZP 2018-12-14
		 */
		addPersonMemberByWxReturn.setCodePm(addPersonMember.getCode());
		addPersonMemberByWxReturn.setPmTypeCode(addPersonMember.getPmTypeCode());
		addPersonMemberByWxReturn.setPmTypeName(addPersonMember.getPmTypeName());
		return addPersonMemberByWxReturn;
	}

	/**
	 * 查找该客户是和导购关联过.
	 *
	 * @param findPersonMember the find person member
	 * @return the int
	 * @throws TsfaServiceException the tsfa service exception
	 */
	@Override
	public int findCountByMemberNo(FindPersonMember findPersonMember) throws TsfaServiceException {
		logger.debug("findCountByMemberNo(FindPersonMember findPersonMember={}) - start", findPersonMember);

		AssertUtils.notNull(findPersonMember);
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNo(), "客户号不能为空");
		int count = 0;
		try {
			count = personMemberDao.findCountByMemberNo(findPersonMember);
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找该客户是和导购关联过错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查找该客户是和导购关联过错误！", e);
		}

		logger.debug("findCountByMemberNo(FindPersonMember findPersonMember={}) - end", findPersonMember);
		return count;
	}

	@Override
	public Page<FindUnContactMemberReturn> findUnContactMemberPage(FindUnContactMember findUnContactMember)
			throws TsfaServiceException {
		logger.debug("findUnContactMemberPage(findUnContactMemberPage={}) - start", findUnContactMember);
		AssertUtils.notNull(findUnContactMember);

		List<FindUnContactMemberReturn> returnList;
		int count = 0;
		try {
			// 计算开始日期和结束日期
			UnContactCodeEnum unContactCodeEnum = UnContactCodeEnum
					.getUnContactCodeEnum(findUnContactMember.getCodeUnContact());
			findUnContactMember.setStartDate(unContactCodeEnum.getStartDate());
			findUnContactMember.setEndDate(unContactCodeEnum.getEndDate());

			// 查询列表
			returnList = personMemberDao.findUnContactMember(findUnContactMember);
			if (!CollectionUtils.isEmpty(returnList)) {
				List<String> memberNoList = new ArrayList<String>();
				for (FindUnContactMemberReturn each : returnList) {
					// 客户姓名的顺序：微信备注 -> 微信昵称 -> 客户名称
					String memberName = StringUtils.isEmpty(each.getNickNameRemarkWx())
							? (StringUtils.isEmpty(each.getNickNameWx()) ? each.getMemberName() : each.getNickNameWx())
							: each.getNickNameRemarkWx();
					each.setMemberName(memberName);

					memberNoList.add(each.getMemberNo());
				}
			}
			count = personMemberDao.findUnContactMemberCount(findUnContactMember);
		} catch (Exception e) {
			logger.error("查询未联系客户分页时错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_ERROR, "查询未联系客户时错误.！", e);
		}
		Page<FindUnContactMemberReturn> returnPage = new Page<>(returnList, count, findUnContactMember);
		logger.debug("findUnContactMemberPage(findUnContactMemberPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public FindGroupedUnContactMemberReturn findGroupedUnContactMember(FindUnContactMember findUnContactMember)
			throws TsfaServiceException {
		logger.debug("findGroupedUnContactMember(FindUnContactMember findUnContactMember={}) - start",
				findUnContactMember);

		AssertUtils.notNull(findUnContactMember);

		FindGroupedUnContactMemberReturn findGroupedUnContactMemberReturn = new FindGroupedUnContactMemberReturn();
		try {
			// 计算开始日期和结束日期
			UnContactCodeEnum unContactCodeEnum = UnContactCodeEnum
					.getUnContactCodeEnum(findUnContactMember.getCodeUnContact());
			findUnContactMember.setStartDate(unContactCodeEnum.getStartDate());
			findUnContactMember.setEndDate(unContactCodeEnum.getEndDate());

			int count = personMemberDao.findUnContactMemberCount(findUnContactMember);
			findGroupedUnContactMemberReturn.setPmTotal(count);

			// 查询未联系顾客分组
			FindUnContactPmType findUnContactPmType = new FindUnContactPmType();
			findUnContactPmType.setMerchantNo(findUnContactMember.getMerchantNo());
			findUnContactPmType.setMemberNoGm(findUnContactMember.getMemberNoGm());
			findUnContactPmType.setStartDate(findUnContactMember.getStartDate());
			findUnContactPmType.setEndDate(findUnContactMember.getEndDate());
			List<FindPmTypeIndexReturn> findUnContactMemberPmTypes = pmTypeService
					.findUnContactMemberPmTypes(findUnContactPmType);// 查询分组

			List<FindGroupedUnContactMemberPmType> findGroupedUnContactMemberPmTypes = new ArrayList<>();

			if (!CollectionUtils.isEmpty(findUnContactMemberPmTypes)) {
				for (FindPmTypeIndexReturn findPmTypeIndexReturn : findUnContactMemberPmTypes) {
					FindGroupedUnContactMemberPmType findGroupedUnContactMemberPmType = new FindGroupedUnContactMemberPmType();// 分组及详情dto

					FindGroupedUnContactMemberHeader header = new FindGroupedUnContactMemberHeader();
					header.setCode(findPmTypeIndexReturn.getCode());// 分类code
					header.setTypeName(findPmTypeIndexReturn.getTypeName());
					header.setPmTypeType(findPmTypeIndexReturn.getPmTypeType());
					header.setPmCount(findPmTypeIndexReturn.getNumClient());

					findGroupedUnContactMemberPmType.setHeader(header);// 设置分组头信息，包含分组和统计

					// 查询分组下的顾客详情，包含标签信息
					findUnContactMember.setPmTypeCode(findPmTypeIndexReturn.getCode());
					List<FindUnContactMemberReturn> findUnContactMemberByPmTypes = personMemberDao
							.findUnContactMemberByPmType(findUnContactMember);
					List<String> memberNoList = new ArrayList<String>(findUnContactMemberByPmTypes.size());
					if (!CollectionUtils.isEmpty(findUnContactMemberByPmTypes)) {
						for (FindUnContactMemberReturn each : findUnContactMemberByPmTypes) {
							// 客户姓名的顺序：微信备注 -> 微信昵称 -> 客户名称
							String memberName = StringUtils.isEmpty(each.getNickNameRemarkWx())
									? (StringUtils.isEmpty(each.getNickNameWx()) ? each.getMemberName()
											: each.getNickNameWx())
									: each.getNickNameRemarkWx();
							each.setMemberName(memberName);

							memberNoList.add(each.getMemberNo());
						}
					}
					// 查询消费次数
					/*
					 * if (memberNoList.size() > 0) { FindClientConsumeCount findClientConsumeCount
					 * = new FindClientConsumeCount();
					 * findClientConsumeCount.setMemberNoGm(findUnContactMember.getMemberNoGm());
					 * findClientConsumeCount.setMerchantNo(findUnContactMember.getMerchantNo());
					 * findClientConsumeCount.setShopNo(findUnContactMember.getShopNo());
					 * findClientConsumeCount.setMemberNoList(memberNoList);
					 * List<FindClientConsumeCountReturn> countList =
					 * clientConsumeService.findClientConsumeCount (findClientConsumeCount);
					 * 
					 * if (!CollectionUtils.isEmpty(countList)) { Map<String, Integer> map = new
					 * HashMap<String, Integer>(); for (FindClientConsumeCountReturn consumeCount :
					 * countList) { map.put(consumeCount.getMemberNo(),
					 * consumeCount.getConsumeCount()); } for (FindUnContactMemberReturn each :
					 * findUnContactMemberByPmTypes) { if (map.get(each.getMemberNo()) != null) {
					 * each.setConsumeCount(map.get(each.getMemberNo())); } } } }
					 */
					findGroupedUnContactMemberPmType.setDetails(findUnContactMemberByPmTypes);

					findGroupedUnContactMemberPmTypes.add(findGroupedUnContactMemberPmType);
				}
				findGroupedUnContactMemberReturn.setPmTypes(findGroupedUnContactMemberPmTypes);
			}

			logger.debug("findGroupedUnContactMember(FindUnContactMember) - end - return value={}",
					findGroupedUnContactMemberReturn);
			return findGroupedUnContactMemberReturn;
		} catch (Exception e) {
			logger.error("查询分组的未联系客户错误", e);
			throw new TsfaServiceException(ErrorCode.GROUPED_UNCONTACT_MEMBER_ERROR, "查询分组的未联系客户错误.！", e);
		}

	}

	@Override
	public int findCountByMemberNoGm(String merchantNo, String memberNoGm) {
		logger.debug("findCountByMemberNoGm(merchantNo={} memberNoGm={}) - start", merchantNo, memberNoGm);
		return personMemberDao.findCountByMemberNoGm(merchantNo, memberNoGm);
	}

	@Override
	public int findUnContactMemberCount(FindUnContactMember findUnContactMember) {
		AssertUtils.notNull(findUnContactMember);

		// 计算开始日期和结束日期
		UnContactCodeEnum unContactCodeEnum = UnContactCodeEnum
				.getUnContactCodeEnum(findUnContactMember.getCodeUnContact());
		findUnContactMember.setStartDate(unContactCodeEnum.getStartDate());
		findUnContactMember.setEndDate(unContactCodeEnum.getEndDate());

		return personMemberDao.findUnContactMemberCount(findUnContactMember);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#findNewPmPage(com
	 * .lj.business.member.dto.FindNewPmPage)
	 */
	@Override
	public Page<FindNewPmPageReturn> findNewPmPage(FindNewPmPage findNewPmPage) throws TsfaServiceException {

		logger.debug("findNewPmPage(FindNewPmPage findNewPmPage={}) - start", findNewPmPage);

		AssertUtils.notNull(findNewPmPage);
		AssertUtils.notNullAndEmpty(findNewPmPage.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findNewPmPage.getMemberNoGm(), "导购编号不能为空");
		List<FindNewPmPageReturn> returnList = null;
		int count = 0;
		try {
			returnList = personMemberDao.findNewPmPage(findNewPmPage);
			count = personMemberDao.findNewPmPageCount(findNewPmPage);
		} catch (Exception e) {
			logger.error("客户管理首页分页查询错误!", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMTYPE_INDEX_PAGE_ERROR, "客户管理首页分页查询错误!", e);
		}
		Page<FindNewPmPageReturn> returnPage = new Page<FindNewPmPageReturn>(returnList, count, findNewPmPage);

		logger.debug("findNewPmPage(FindNewPmPage) - end - return value={}", returnPage);
		return returnPage;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#findPmWxBpInfo(com
	 * .lj.business.member.dto .FindPmWxBpInfo)
	 */
	@Override
	public List<FindPmWxBpInfoReturn> findPmWxBpInfo(FindPmWxBpInfo findPmWxBpInfo) throws TsfaServiceException {
		logger.debug("findPmWxBpInfo(FindPmWxBpInfo findPmWxBpInfo={}) - start", findPmWxBpInfo);
		AssertUtils.notNull(findPmWxBpInfo);
		AssertUtils.notNullAndEmpty(findPmWxBpInfo.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(findPmWxBpInfo.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findPmWxBpInfo.getMemberNoAr(), "客户编号不能为空");
		try {
			List<FindPmWxBpInfoReturn> returnList = personMemberDao.findPmWxBpInfo(findPmWxBpInfo);
			logger.debug("findPmWxBpInfo(FindPmWxBpInfo) - end - return value={}", returnList);
			return returnList;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户微信会员信息错误", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMWXBP_INFO, "查找客户微信会员信息错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#findPmWxInfo(com.
	 * lj.business.member.dto.FindPmWxInfo)
	 */
	@Override
	public List<FindPmWxInfoReturn> findPmWxInfo(FindPmWxInfo findPmWxInfo) throws TsfaServiceException {
		logger.debug("findPmWxInfo(FindPmWxInfo findPmWxInfo={}) - start", findPmWxInfo);
		AssertUtils.notNull(findPmWxInfo);
		AssertUtils.notNullAndEmpty(findPmWxInfo.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(findPmWxInfo.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findPmWxInfo.getMemberNoAr(), "客户编号不能为空");
		try {
			List<FindPmWxInfoReturn> returnList = personMemberDao.findPmWxInfo(findPmWxInfo);
			logger.debug("findPmWxInfo(FindPmWxInfo) - end - return value={}", returnList);
			return returnList;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户微信会员信息错误", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMWXBP_INFO, "查找客户微信会员信息错误", e);
		}
	}

	@Override
	public int findPersonMemberSums(FindUrgentMbrPage findUrgentMbrPage) {
		AssertUtils.notNull(findUrgentMbrPage);
		AssertUtils.notNullAndEmpty(findUrgentMbrPage.getMerchantNo(), "商户编号不能为空");
		int count = 0;
		try {
			count = personMemberDao.findPersonMemberSums(findUrgentMbrPage);
		} catch (Exception e) {
			logger.error("统计客户数量信息错误", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMWXBP_INFO, "统计客户数量信息错误", e);
		}
		return count;
	}

	@Override
	public List<FindPersonMemberSexStatisticsReturn> selectSexStatisticsByShopNo() {
		logger.debug("selectSexStatisticsByShopNo() - start");

		try {
			List<FindPersonMemberSexStatisticsReturn> result = new ArrayList<>();
			List<Map<String, Object>> sexStatisticsList = personMemberDao.selectSexStatisticsByShopNo();
			if (!CollectionUtils.isEmpty(sexStatisticsList)) {
				for (Map<String, Object> each : sexStatisticsList) {
					FindPersonMemberSexStatisticsReturn item = new FindPersonMemberSexStatisticsReturn();
					item.setAreaCode((String) each.get("areaCode"));
					item.setAreaName((String) each.get("areaName"));
					item.setMerchantNo((String) each.get("merchantNo"));
					item.setSex((String) each.get("sex"));
					Long sexCount = (Long) each.get("sexCount");
					item.setSexCount(sexCount == null ? 0 : sexCount.intValue());
//                    item.setShopName((String) each.get("shopName"));
//                    item.setShopNo((String) each.get("shopNo"));
					result.add(item);
				}
			}

			logger.debug("selectSexStatisticsByShopNo() - end - return value={}", result);
			return result;
		} catch (Exception e) {
			logger.error("统计客户性别时错误", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMWXBP_INFO, "统计客户性别时错误", e);
		}
	}

	@Override
	public List<FindPersonMemberAgeStatisticsReturn> selectAgeStatisticsByShopNo(String beginDate, String endDate) {
		try {
			List<FindPersonMemberAgeStatisticsReturn> result = new ArrayList<>();
			List<Map<String, Object>> ageStatisticsList = personMemberDao.selectAgeStatisticsByShopNo(beginDate,
					endDate);
			if (!CollectionUtils.isEmpty(ageStatisticsList)) {
				for (Map<String, Object> each : ageStatisticsList) {
					FindPersonMemberAgeStatisticsReturn item = new FindPersonMemberAgeStatisticsReturn();
					Long ageCount = (Long) each.get("ageCount");
					item.setAgeCount(ageCount == null ? 0 : ageCount.intValue());
//                    item.setShopNo((String) each.get("shopNo"));
					result.add(item);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("统计客户年龄时错误", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMWXBP_INFO, "统计客户年龄时错误", e);
		}
	}

	@Override
	public List<FindPersonMemberInterestStatisticsReturn> selectInterestStatisticsByShopNo() {
		try {
			List<FindPersonMemberInterestStatisticsReturn> result = new ArrayList<>();
			List<Map<String, Object>> lineStatisticsList = personMemberDao.selectInterestStatisticsByShopNo();
			if (!CollectionUtils.isEmpty(lineStatisticsList)) {
				for (Map<String, Object> each : lineStatisticsList) {
					FindPersonMemberInterestStatisticsReturn item = new FindPersonMemberInterestStatisticsReturn();
					item.setAreaCode((String) each.get("areaCode"));
					item.setMerchantNo((String) each.get("merchantNo"));
//                    item.setShopName((String) each.get("shopName"));
//                    item.setShopNo((String) each.get("shopNo"));

					Long numInterest = (Long) each.get("numInterest");
					item.setNumInterest(numInterest == null ? 0 : numInterest.intValue());
					item.setCodeInterest((String) each.get("codeInterest"));
					item.setInterestName((String) each.get("interestName"));
					result.add(item);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("统计客户兴趣时错误", e);
			throw new TsfaServiceException(ErrorCode.FIND_PMWXBP_INFO, "统计客户兴趣时错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.lj.business.member.service.IPersonMemberService#doRepeatMember(com
	 * .lj.business.member.dto .DoRepeatMemberDto)
	 */
	@Override
	@Deprecated
	public void doRepeatMember(DoRepeatMemberDto doRepeatMemberDto) throws TsfaServiceException {
		/*
		 * FindPersonMember fp = new FindPersonMember();
		 * fp.setMemberNo(doRepeatMemberDto.getMemberNo());
		 * fp.setMerchantNo(doRepeatMemberDto.getMerchantNo()); int count =
		 * findCountByMemberNo(fp);
		 * 
		 * if (count > 1) { logger.debug("如果是交叉用户则插入客户分类"); FindPmType fpt = new
		 * FindPmType(); fpt.setPmTypeType(PmTypeType.REPEAT.toString());
		 * fpt.setMerchantNo(doRepeatMemberDto.getMerchantNo()); FindPmTypeReturn fptr =
		 * pmTypeService.findPmTypeByMP(fpt);
		 * 
		 * List<FindPersonMemberReturn> list = findList(doRepeatMemberDto); if (list !=
		 * null && list.size() > 0) { for (FindPersonMemberReturn pm : list) { if (fptr
		 * != null) { AddPmTypePmDto addPmTypePmDto = new AddPmTypePmDto();
		 * addPmTypePmDto.setCodePm(pm.getCode());
		 * addPmTypePmDto.setPmTypeCode(fptr.getCode());
		 * pmTypeService.addPmTypePmRepeat(addPmTypePmDto); } } } }
		 */

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.lj.business.member.service.IPersonMemberService#findList(com.lj.business
	 * .member.dto.DoRepeatMemberDto)
	 */
	@Override
	public List<FindPersonMemberReturn> findList(DoRepeatMemberDto doRepeatMemberDto) throws TsfaServiceException {
		AssertUtils.notNull(doRepeatMemberDto);
		AssertUtils.notNullAndEmpty(doRepeatMemberDto.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(doRepeatMemberDto.getMemberNo(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(doRepeatMemberDto.getMemberNoGm(), "导购编号不能为空");
		try {
			List<FindPersonMemberReturn> list = personMemberDao.findList(doRepeatMemberDto);
			return list;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找客户会员信息错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查找客户会员信息错误", e);
		}
	}

	@Override
	public List<FindPersonMemberPageReturn> findPersonMemberList(FindPersonMemberPage findPersonMemberPage)
			throws TsfaServiceException {
		logger.debug("findPersonMemberPage(FindPersonMemberPage findPersonMemberPage={}) - start",
				findPersonMemberPage);
		AssertUtils.notNull(findPersonMemberPage);
		List<FindPersonMemberPageReturn> returnList = null;
		try {
			returnList = personMemberDao.findPersonMemberPage(findPersonMemberPage);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		logger.debug("findPersonMemberPage(FindPersonMemberPage) - end - return value={}", returnList);
		return returnList;
	}

	@Override
	public List<FindPersonMemberPageReturn> findPersonMemberListForApi(FindPersonMemberPage findPersonMemberPage)
			throws TsfaServiceException {
		logger.debug("findPersonMemberListForApi(FindPersonMemberPage findPersonMemberPage={}) - start",
				findPersonMemberPage);
		AssertUtils.notNull(findPersonMemberPage);
		List<FindPersonMemberPageReturn> returnList = null;
		try {
			returnList = personMemberDao.findPersonMemberList(findPersonMemberPage);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		logger.debug("findPersonMemberListForApi(FindPersonMemberPage) - end - return value={}", returnList);
		return returnList;
	}

	@Override
	public List<FindTodayManageShopReturn> todayManageShopNew(FindTodayManageShop findTodayManageShop)
			throws TsfaServiceException {
		AssertUtils.notNull(findTodayManageShop);
//        AssertUtils.notNullAndEmpty(findTodayManageShop.getShopNo(), "分店编号不能为空");
		AssertUtils.notNullAndEmpty(findTodayManageShop.getMerchantNo(), "商户编号不能为空");
		try {
			List<FindTodayManageShopReturn> list = new ArrayList<FindTodayManageShopReturn>();
			list = personMemberDao.todayManageShopNew(findTodayManageShop);
			for (FindTodayManageShopReturn findTodayManageShopReturn : list) {
				BigDecimal big = new BigDecimal(findTodayManageShopReturn.getRatioWork());
				findTodayManageShopReturn.setRatioWork(big.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			return list;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找 导购信息错误", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR, "查找导购信息错误", e);
		}
	}

	@Override
	public long findCountPmAddByGmDay(String memberNo, Date date) {
		AssertUtils.notNullAndEmpty(memberNo, "导购号不能为空");
		AssertUtils.notNullAndEmpty(date, "查询时间不能为空");
		try {
			return personMemberDao.findCountPmAddByGmDay(memberNo, date);
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找 导购信息错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查找客户会员信息错误", e);
		}
	}

	@Override
	public FindPersonMemberReturnList findPersonMemberTypeNum(FindPersonMember findPersonMember)
			throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(findPersonMember.getMerchantNo(), "商户编号编号不能为空");
		AssertUtils.notNullAndEmpty(findPersonMember.getAreaCode(), "区域编号不能为空");
		FindPersonMemberReturnList memberReturnList = null;
		try {
			memberReturnList = personMemberDao.findPersonMemberTypeNum(findPersonMember);
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找 导购信息错误", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR, "查找导购信息错误", e);
		}
		return memberReturnList;
	}

	@Override
	public List<FindUrgentMbrPageReturn> findPersonMemberSexCount(FindPersonMember findPersonMember)
			throws TsfaServiceException {
		AssertUtils.notNull(findPersonMember);
		List<FindUrgentMbrPageReturn> findUrgentMbrPageReturn = null;
		try {
			findUrgentMbrPageReturn = personMemberDao.findPersonMemberSexCount(findPersonMember);
		} catch (Exception e) {
			logger.error("统计客户信息错误", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR, "统计客户息错误", e);
		}
		return findUrgentMbrPageReturn;
	}

	@Override
	public int findNewPmCount(FindNewPmCountDto findNewPmCountDto) throws TsfaServiceException {
		try {
			int count = personMemberDao.findNewPmCount(findNewPmCountDto);
			return count;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找 导购信息错误", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR, "查找导购信息错误", e);
		}
	}

	@Override
	public int findCountPmByType(FindPmType findPmType) {
		logger.debug("findCountPmByType(FindPmType findPmType={}) - start", findPmType);
		AssertUtils.notNull(findPmType);
		AssertUtils.notNullAndEmpty(findPmType.getMemberNo(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(findPmType.getPmTypeType(), "客户类型不能为空");
		int count = personMemberDao.findCountPmByType(findPmType);
		logger.debug("findCountPmByType(count = {}) - end", count);
		return count;
	}

	@Override
	public List<FindPersonMemberReturn> findPersonMemberByGm(FindPersonMember personMember) {
		try {
			List<FindPersonMemberReturn> list = personMemberDao.findPersonMemberByGm(personMember);
			return list;
		} catch (Exception e) {
			logger.error("查找 导购信息错误", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_FIND_ERROR, "查找导购信息错误", e);
		}
	}

	@Override
	public List<FindPersonMemberReturnList> findPersonMemberTypeList(FindPersonMember findPersonMember) {
		logger.debug("findPersonMemberTypeList(FindPersonMember findPersonMember) - start");
		AssertUtils.notNull(findPersonMember);
		List<FindPersonMemberReturnList> list = null;
		try {
			list = personMemberDao.findPersonMemberTypeList(findPersonMember);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		return list;
	}

	@Override
	public FindPersonMemberReturnList findPersonMemberType(FindPersonMember findPersonMember)
			throws TsfaServiceException {
		logger.debug("findPersonMemberTypeList(FindPersonMember findPersonMember) - start");
		AssertUtils.notNull(findPersonMember);
		FindPersonMemberReturnList list = null;
		try {
			list = personMemberDao.findPersonMemberType(findPersonMember);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		return list;
	}

	@Override
	public int findPersonMemberTypeCount(FindPersonMember findPersonMember) {
		logger.debug("findPersonMemberTypeCount(FindPersonMember findPersonMember) - start");
		AssertUtils.notNull(findPersonMember);
		int count = 0;
		try {
			count = personMemberDao.findPersonMemberTypeCount(findPersonMember);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		return count;
	}

	@Override
	public List<FindMemberInfoReturn> findMemberRecord(FindMemberRecord findMemberRecord) {
		return personMemberDao.findMemberRecord(findMemberRecord);
	}

	@Override
	public List<CountPersonMemberReturn> findGroupCountByDay(FindCountPersonMember findCountPersonMember) {
		AssertUtils.notNull(findCountPersonMember);
		return personMemberDao.findGroupCountByDay(findCountPersonMember);
	}

	@Override
	public int findPersonMemberCont(FindPersonMember findPersonMember) throws TsfaServiceException {
		logger.debug("findPersonMemberTypeCount(FindPersonMember findPersonMember) - start");
		AssertUtils.notNull(findPersonMember);
		int count = 0;
		try {
			count = personMemberDao.findPersonMemberCont(findPersonMember);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> findShopPmNum(Map<String, Object> map) {
		logger.debug("findShopPmNum(Map<String, Object> map={}) - start", map);
		return personMemberDao.findShopPmNum(map);
	}

	@Override
	public List<Map<String, Object>> findCountAddIntention(Map<String, Object> map) {
		logger.debug("findCountAddIntention(Map<String, Object> map={}) - start", map);
		return personMemberDao.findCountAddIntention(map);
	}

	@Override
	public List<Map<String, Object>> findCountAddOther(Map<String, Object> map) {
		logger.debug("findCountAddOther(Map<String, Object> map={}) - start", map);
		return personMemberDao.findCountAddOther(map);
	}

	@Override
	public List<Map<String, Object>> findCountPmOrder(Map<String, Object> map) {
		logger.debug("findCountPmOrder(Map<String, Object> map={}) - start", map);
		return personMemberDao.findCountPmOrder(map);
	}

	@Override
	public List<Map<String, Object>> finCountAddPmAbandon(Map<String, Object> map) {
		logger.debug("finCountAddPmAbandon(Map<String, Object> map={}) - start", map);
		return personMemberDao.finCountAddPmAbandon(map);
	}

	@Override
	public List<Map<String, Object>> findcountAddPmUngroup(Map<String, Object> map) {
		logger.debug("findcountAddPmUngroup(Map<String, Object> map={}) - start", map);
		return personMemberDao.findcountAddPmUngroup(map);
	}

	@Override
	public Page<FindPersonMemberPageReturn> queryPersonMemberPage(FindPersonMemberPage findPersonMemberPage) {
		logger.debug(" queryPersonMemberPage(FindPersonMemberPage findPersonMemberPage){} - start",
				findPersonMemberPage);
		AssertUtils.notNull(findPersonMemberPage);
		List<FindPersonMemberPageReturn> list = new ArrayList<>();
		int count = 0;
		try {
			count = personMemberDao.queryPersonMemberPageCount(findPersonMemberPage);

			if (count > 0) {
				list = personMemberDao.queryPersonMemberPage(findPersonMemberPage);
			}

		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		Page<FindPersonMemberPageReturn> returnPage = new Page<FindPersonMemberPageReturn>(list, count,
				findPersonMemberPage);

		logger.debug("queryPersonMemberPage(FindPersonMemberPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public FindPersonMemberReturnList queryPersonMemberPmType(FindPersonMember findPersonMember)
			throws TsfaServiceException {
		AssertUtils.notNull(findPersonMember);
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNo(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNoGm(), "导购编号不能为空");
		try {
			return personMemberDao.queryPersonMemberPmType(findPersonMember);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
	}

	@Override
	public FindPersonMemberReturnList queryPersonMemberPmTypeExceptRepeatAndUrgency(FindPersonMember findPersonMember)
			throws TsfaServiceException {
		AssertUtils.notNull(findPersonMember);
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNo(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNoGm(), "导购编号不能为空");
		try {
			return personMemberDao.queryPersonMemberPmTypeExceptRepeatAndUrgency(findPersonMember);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
	}

	@Override
	public List<FindPersonMemberReturn> findPersonMemberByNoWx(FindPersonMember findPersonMember)
			throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNoGm(), "导购编号不能为空");
		List<FindPersonMemberReturn> list;
		try {
			list = personMemberDao.findPersonMemberByNoWx(findPersonMember);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IPersonMemberService#updatePersonMemberMobile(
	 * com.lj.business.member.dto.EditPersonMember)
	 */
	@Override
	public UpdatePersonMemberReturn updatePersonMemberMobile(EditPersonMember editPersonMember)
			throws TsfaServiceException {
		logger.debug("updatePersonMemberMobile(EditPersonMember editPersonMember={}) - start", editPersonMember);
		AssertUtils.notNull(editPersonMember);
		AssertUtils.notNullAndEmpty(editPersonMember.getMemberNo(), "客户号不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getNoWxGm(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getMobile(), "手机号不能为空");
		try {
			FindPersonMember findPersonMember = new FindPersonMember();
			findPersonMember.setMemberNo(editPersonMember.getMemberNo());
//            findPersonMember.setMemberNoGm(editPersonMember.getMemberNoGm());
			findPersonMember.setShopWx(editPersonMember.getNoWxGm());
			FindPersonMemberReturn findPersonMemberReturn = findPersonMember(findPersonMember);
			if (findPersonMemberReturn != null) {
				UpdatePersonMemberBase updatePersonMemberBase = new UpdatePersonMemberBase();
				updatePersonMemberBase.setMemberNo(editPersonMember.getMemberNo());
				updatePersonMemberBase.setMobile(editPersonMember.getMobile());
				personMemberBaseService.updatePersonMemberMobile(updatePersonMemberBase);

				PersonMember updatePersonMember = new PersonMember();
				updatePersonMember.setMemberNo(editPersonMember.getMemberNo());
//                updatePersonMember.setMemberNoGm(editPersonMember.getMemberNoGm());
				updatePersonMember.setShopWx(editPersonMember.getNoWxGm());
				String nickNameRemarkLocal = "";
				if (org.apache.commons.lang3.StringUtils.isNotEmpty(findPersonMemberReturn.getMemberName())) {
					nickNameRemarkLocal += findPersonMemberReturn.getMemberName();
				}
				if (org.apache.commons.lang3.StringUtils.isNotEmpty(updatePersonMemberBase.getMobile())) {
					nickNameRemarkLocal += ("-" + updatePersonMemberBase.getMobile());
				}
				if (org.apache.commons.lang3.StringUtils.isNotEmpty(findPersonMemberReturn.getBomName())) {
					nickNameRemarkLocal += ("-"
							+ (StringUtils.toString(findPersonMemberReturn.getBomName()).length() > 9
									? findPersonMemberReturn.getBomName().substring(0, 10)
									: StringUtils.toString(findPersonMemberReturn.getBomName())));
				}
				updatePersonMember.setNickNameRemarkLocal(nickNameRemarkLocal);
				updatePersonMember.setUpdateDate(new Date());
				updatePersonMember.setVersion(System.currentTimeMillis()); // 客户版本号
				AssertUtils.notUpdateMoreThanOne(personMemberDao.updateByMGM(updatePersonMember));
			}

			UpdatePersonMemberReturn updatePersonMemberReturn = new UpdatePersonMemberReturn();
			logger.debug("updatePersonMemberMobile(EditPersonMember) - end - return value={}",
					updatePersonMemberReturn);
			return updatePersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "客户会员信息更新信息错误！", e);
		}
	}

	@Override
	public List<String> findIntentionMemberNo(String merchantNo) {
		return personMemberDao.findIntentionMemberNo(merchantNo);
	}

	@Override
	public UpdatePersonMemberReturn updatePersonMemberName(EditPersonMember editPersonMember)
			throws TsfaServiceException {
		logger.debug("updatePersonMemberName(EditPersonMember editPersonMember={}) - start", editPersonMember);
		AssertUtils.notNull(editPersonMember);
		AssertUtils.notNullAndEmpty(editPersonMember.getMemberNo(), "客户号不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getNoWxGm(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getMemberName(), "客户名称不能为空");
		AssertUtils.notNullAndEmpty(editPersonMember.getMemberNoGm(), "导购编号不能为空");
		try {
			FindPersonMember findPersonMember = new FindPersonMember();
			findPersonMember.setMemberNo(editPersonMember.getMemberNo());
			findPersonMember.setMemberNoGm(editPersonMember.getMemberNoGm());
			findPersonMember.setShopWx(editPersonMember.getNoWxGm());
			FindPersonMemberReturn findPersonMemberReturn = findPersonMember(findPersonMember);
			if (findPersonMemberReturn != null) {
				FindPersonMemberBase findPersonMemberBase2 = new FindPersonMemberBase();
				findPersonMemberBase2.setMemberNo(editPersonMember.getMemberNo());

				PersonMember updatePersonMember = new PersonMember();
				updatePersonMember.setMemberNo(editPersonMember.getMemberNo());
				updatePersonMember.setMemberNoGm(editPersonMember.getMemberNoGm());
				updatePersonMember.setShopWx(editPersonMember.getNoWxGm());
				updatePersonMember.setUpdateDate(new Date());
				updatePersonMember.setVersion(System.currentTimeMillis()); // 客户版本号
				updatePersonMember.setNickNameRemarkWx(editPersonMember.getMemberName());
				AssertUtils.notUpdateMoreThanOne(personMemberDao.updateByMGM(updatePersonMember));
				// 同步群成员备注名
				if (StringUtils.isNotEmpty(editPersonMember.getMemberName())) {
					final String noWxZk = editPersonMember.getNoWxGm();
					final String memberNo = editPersonMember.getMemberNo();
					final String memberName = editPersonMember.getMemberName();
					taskExecutor.execute(new Runnable() {

						@Override
						public void run() {
							UpdateChatRoomMember updateChatRoomMember = new UpdateChatRoomMember();
							updateChatRoomMember.setNoWxZk(noWxZk);
							updateChatRoomMember.setMemberNo(memberNo);
							updateChatRoomMember.setMemberName(memberName);
							chatRoomMemberService.synChatRoomMember(updateChatRoomMember);

						}
					});
				}

			}

			UpdatePersonMemberReturn updatePersonMemberReturn = new UpdatePersonMemberReturn();
			logger.debug("updatePersonMemberName(EditPersonMember) - end - return value={}", updatePersonMemberReturn);
			return updatePersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "客户会员信息更新信息错误！", e);
		}
	}

	@Override
	public List<FindImIndexPageReturn> findImIndexList(FindImIndexPage findImIndex) {
		logger.debug("findImIndexList(FindImIndexPage findImIndex={}) - start", findImIndex);
		List<FindImIndexPageReturn> findImIndexReturn = null;
		findImIndexReturn = personMemberDao.findImIndexList(findImIndex);
		logger.debug("findImIndexList(FindImIndex) - end - return value={}", findImIndexReturn);
		return findImIndexReturn;
	}

	@Override
	public Long findForecastNameIndexListCount(FindImIndexPage findImIndex) {
		logger.debug("findForecastNameIndexListCount(FindImIndexPage findImIndex={}) - start", findImIndex);
		Long count = 0L;
		count = personMemberDao.findForecastNameIndexListCount(findImIndex);
		logger.debug("findForecastNameIndexListCount(FindImIndex) - end - return value={}", count);
		return count;
	}

	@Override
	public List<FindImIndexPageReturn> findPmListByShopTerminals(FindImIndexPage findImIndex) {
		AssertUtils.notNull(findImIndex);
		AssertUtils.notNullAndEmpty(findImIndex.getNoWx(), "终端微信不能为空");
		logger.debug("findPmListByShopTerminals(FindImIndexPage findImIndex={}) - start", findImIndex);

		List<FindImIndexPageReturn> findImIndexReturn = null;
		findImIndexReturn = personMemberDao.findPmListByShopTerminals(findImIndex);
		logger.debug("findPmListByShopTerminals(FindImIndexPage) - end - return value={}", findImIndexReturn);
		return findImIndexReturn;
	}

	@Override
	public Long findPmListByShopTerminalsCount(FindImIndexPage findImIndex) {
		logger.debug("findPmListByShopTerminalsCount(FindImIndexPage findImIndex={}) - start", findImIndex);
		Long count = 0L;
		count = personMemberDao.findPmListByShopTerminalsCount(findImIndex);
		logger.debug("findPmListByShopTerminalsCount(FindImIndexPage) - end - return value={}", count);
		return count;
	}

	@Override
	public Long findImIndexListCount(FindImIndexPage findImIndex) {
		logger.debug("findImIndexListCount(FindImIndexPage findImIndex={}) - start", findImIndex);
		Long count = 0L;
		count = personMemberDao.findImIndexListCount(findImIndex);
		logger.debug("findImIndexListCount(FindImIndex) - end - return value={}", count);
		return count;
	}

	@Override
	public List<String> findPersonMemberByWx(String noWx) {
		logger.debug("findPersonMemberByWx(String userName={}) - start", noWx);
		List<String> noWxGm = personMemberDao.findPersonMemberByWx(noWx);
		logger.debug("findPersonMemberByWx(List<String>) - end - return value={}", noWxGm);
		return noWxGm;
	}

	@Override
	public FindPersonMemberReturn findPersonMemberByNoWxAndShopWx(String noWx, String shopWx) {
		AssertUtils.notNullAndEmpty(noWx, "客户微信不能为空");
		AssertUtils.notNullAndEmpty(shopWx, "终端微信不能为空");
		logger.debug("findPersonMemberByNoWxAndShopWx(String noWx={}, String shopWx={}) - start", noWx, shopWx);
		return personMemberDao.findPersonMemberByNoWxAndShopWx(noWx, shopWx);
	}

	@Override
	public Integer findAllCustomerCount(FindPmTypeIndexPage findPmTypeIndexPage) {
		logger.debug("findAllCustomerCount(FindPmTypeIndexPage findPmTypeIndexPage={}) - start", findPmTypeIndexPage);

		AssertUtils.notNull(findPmTypeIndexPage);
		AssertUtils.notNullAndEmpty(findPmTypeIndexPage.getMemberNoGm(), "导购号不能为空");
//        AssertUtils.notNullAndEmpty(findPmTypeIndexPage.getShopNo(), "终端编号不能为空");

		Integer customerCount = personMemberDao.findAllCustomerCount(findPmTypeIndexPage);

		logger.debug("findAllCustomerCount(FindPmTypeIndexPage) - end - return value={}", customerCount);
		return customerCount;
	}

	@Override
	public List<FindPersonMemberPageReturn> findPmbListByLabelCode(Map<String, Object> paramMap) {
		logger.debug("findPmbListByLabelCode(Map<String,Object> paramMap={}) - start", paramMap);
		List<FindPersonMemberPageReturn> returnList = personMemberDao.findPmbListByLabelCode(paramMap);
		logger.debug("findPmbListByLabelCode(Map<String,Object>) - end - return value={}", returnList);
		return returnList;
	}

	@Override
	public FindPersonMemberReturn findPersonMemberByMemberNoAndGM(FindPersonMember findPersonMember) {
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNoGm(), "导购号不能为空");
		AssertUtils.notNullAndEmpty(findPersonMember.getMemberNo(), "客户编号不能为空");
		return personMemberDao.findPersonMemberByNoAndGM(findPersonMember);
	}

	/**
	 * 根据手机号、微信号、会员编号、微信openid分别查询客户信息，当不同条件对应不同客户信息时，优先使用规则如下： 1、根据会员编号查询获取的客户信息
	 * 2、根据手机号查询获取的客户信息 3、根据微信号查询获取的客户信息 4、根据微信openId查询获取的客户信息 5、都没有，创建新的客户信息
	 * 6、获取的客户信息与根据微信openId查询获取的客户信息不一致，则需更改微信openId绑定的客户编号
	 * 
	 * update by zengchuiyu update date 2018-06-05
	 */
	@Override
	@Deprecated
	public MecMemberNoReturn addPersonAllByMec(MecMemberDto mecMemberDto) throws TsfaServiceException {
		/*
		 * logger.debug("addPersonAllByMec(MecMemberDto mecMemberDto={}) - start",
		 * mecMemberDto); AssertUtils.notNull(mecMemberDto);
		 * AssertUtils.notAllNullAndEmpty(mecMemberDto.getMobile(),
		 * mecMemberDto.getOpenId(), "客户手机号或OPENID不能都为空"); //
		 * AssertUtils.notNullAndEmpty(mecMemberDto.getShopNo(), "终端编号不能为空");
		 * 
		 * FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
		 * FindPersonMemberBaseReturn pmbrR = null; // 客户信息
		 * 
		 * // 1、根据会员编号查询获取客户信息 if (StringUtils.isNotEmpty(mecMemberDto.getMemberNo())) {
		 * findPersonMemberBase.setMemberNo(mecMemberDto.getMemberNo()); pmbrR =
		 * personMemberBaseService.findPersonMemberBase(findPersonMemberBase); }
		 * if(pmbrR == null) { // 根据会员编号没有找到客户信息 // 2、根据手机号查询获取客户信息 if
		 * (StringUtils.isNotEmpty(mecMemberDto.getMobile())) {
		 * findPersonMemberBase.setMemberNo(null);
		 * findPersonMemberBase.setMobile(mecMemberDto.getMobile()); pmbrR =
		 * personMemberBaseService.findByMobile(findPersonMemberBase); } if(pmbrR ==
		 * null) { // 根据手机号没有找到客户信息 // 3、根据微信号查询获取客户信息 if
		 * (StringUtils.isNotEmpty(mecMemberDto.getNoWx())) {
		 * findPersonMemberBase.setMemberNo(null); findPersonMemberBase.setMobile(null);
		 * findPersonMemberBase.setNoWx(mecMemberDto.getNoWx()); pmbrR =
		 * personMemberBaseService.findPersonMemberBase(findPersonMemberBase); } } }
		 * 
		 * // 4、根据微信openId查询获取客户信息 MecMemberNoReturn mecMemberNoReturn = null; if
		 * (StringUtils.isNotEmpty(mecMemberDto.getOpenId())) { mecMemberNoReturn =
		 * personMemberExtService.findPmbByOpenId(mecMemberDto.getOpenId()); }
		 * FindPersonMemberBaseReturn pmbrByOpenId = null; // 根据微信openId查询获取的客户信息
		 * if(mecMemberNoReturn != null) { findPersonMemberBase.setMobile(null);
		 * findPersonMemberBase.setNoWx(null);
		 * findPersonMemberBase.setMemberNo(mecMemberNoReturn.getMemberNo());
		 * pmbrByOpenId =
		 * personMemberBaseService.findPersonMemberBase(findPersonMemberBase); //
		 * 使用根据微信openId查询获取的客户信息 if(pmbrR == null && pmbrByOpenId != null) { pmbrR =
		 * pmbrByOpenId; } }
		 * 
		 * // 客户存在 if (pmbrR != null) { // 判断该客户是否在pm表存在 int count =
		 * personMemberService.findListByShopNo(mecMemberDto.getShopNo(),
		 * pmbrR.getMemberNo()); if (count == 0) { // 在该店未绑定过导购则创建pm 绑定到该店的店长上
		 * FindShopGmDtoReturn findShopGmDtoReturn =
		 * guidMemberService.findGmDto(mecMemberDto.getShopNo()); if
		 * (findShopGmDtoReturn != null) { AddPersonMember addPersonMember = new
		 * AddPersonMember(); addPersonMember.setMemberNo(pmbrR.getMemberNo());
		 * addPersonMember.setMemberName(pmbrR.getMemberName());
		 * addPersonMember.setMemberNoGm(findShopGmDtoReturn.getMemberNo());
		 * addPersonMember.setMemberNameGm(findShopGmDtoReturn.getMemberName());
		 * addPersonMember.setMerchantNo(findShopGmDtoReturn.getMerchantNo());
		 * addPersonMember.setMerchantName(findShopGmDtoReturn.getMerchantName()); //
		 * addPersonMember.setShopNo(findShopGmDtoReturn.getShopNo()); //
		 * addPersonMember.setShopName(findShopGmDtoReturn.getShopName());
		 * personMemberService.addPersonMember(addPersonMember); } }
		 * 
		 * // 绑定 if(pmbrByOpenId == null) {
		 * if(StringUtils.isNotEmpty(mecMemberDto.getOpenId())) {
		 * personMemberExtService.bindMemberNo(pmbrR.getMemberNo(),
		 * mecMemberDto.getOpenId()); } } else
		 * if(!pmbrByOpenId.getMemberNo().equals(pmbrR.getMemberNo())) { //
		 * 6、已获取的客户信息pmbrR与根据微信openId查询获取的客户信息不一致，则需更改微信openId绑定的客户编号
		 * personMemberExtService.bindMemberNo(pmbrR.getMemberNo(),
		 * mecMemberDto.getOpenId()); }
		 * 
		 * // 组装返回客户信息 if (null == mecMemberNoReturn) { mecMemberNoReturn = new
		 * MecMemberNoReturn(); }
		 * mecMemberNoReturn.setMemberName(pmbrR.getMemberName());
		 * mecMemberNoReturn.setMemberNo(pmbrR.getMemberNo()); } else { // 客户不存在 //
		 * 不创建新客户，直接返回 if(!mecMemberDto.isAddMbrBool()) { logger.
		 * debug("addPersonAllByMec(MecMemberDto mecMemberDto=null, 不创建新客户}) - end");
		 * return null; }
		 * 
		 * // 创建客户基础信息 pmb AddPersonMemberBase addPersonMemberBase = new
		 * AddPersonMemberBase();
		 * addPersonMemberBase.setMobile(mecMemberDto.getMobile());
		 * addPersonMemberBase.setNoWx(mecMemberDto.getNoWx());
		 * addPersonMemberBase.setNickNameWx(mecMemberDto.getNickNameWx());
		 * addPersonMemberBase.setMemberName(mecMemberDto.getNickNameWx());
		 * addPersonMemberBase.setSex(Gender.UNKNOWN.toString());
		 * addPersonMemberBase.setStatus(MemberStatus.NORMAL);
		 * addPersonMemberBase.setMemberSrc(MemerSourceType.NET.toString());
		 * addPersonMemberBase.setHeadAddress(mecMemberDto.getHeadImage());
		 * AddPersonMemberBaseReturn addPersonMemberBaseReturn =
		 * personMemberBaseService.addPersonMemberBase(addPersonMemberBase);
		 * 
		 * // 在该店未绑定过导购则创建pm 绑定到该店的店长上 FindShopGmDtoReturn findShopGmDtoReturn =
		 * guidMemberService.findGmDto(mecMemberDto.getShopNo()); if
		 * (findShopGmDtoReturn != null) { AddPersonMember addPersonMember = new
		 * AddPersonMember();
		 * addPersonMember.setMemberNo(addPersonMemberBaseReturn.getMemberNo());
		 * addPersonMember.setMemberName(addPersonMemberBase.getMemberName());
		 * addPersonMember.setMemberNoGm(findShopGmDtoReturn.getMemberNo());
		 * addPersonMember.setMemberNameGm(findShopGmDtoReturn.getMemberName());
		 * addPersonMember.setMerchantNo(findShopGmDtoReturn.getMerchantNo());
		 * addPersonMember.setMerchantName(findShopGmDtoReturn.getMerchantName()); //
		 * addPersonMember.setShopNo(findShopGmDtoReturn.getShopNo()); //
		 * addPersonMember.setShopName(findShopGmDtoReturn.getShopName());
		 * personMemberService.addPersonMember(addPersonMember); }
		 * 
		 * // 绑定 if(pmbrByOpenId == null) {
		 * if(StringUtils.isNotEmpty(mecMemberDto.getOpenId())) {
		 * personMemberExtService.bindMemberNo(addPersonMemberBaseReturn.getMemberNo(),
		 * mecMemberDto.getOpenId()); } } else
		 * if(!pmbrByOpenId.getMemberNo().equals(addPersonMemberBaseReturn.getMemberNo()
		 * )) { // 6、已获取的客户信息pmbrR与根据微信openId查询获取的客户信息不一致，则需更改微信openId绑定的客户编号
		 * personMemberExtService.bindMemberNo(addPersonMemberBaseReturn.getMemberNo(),
		 * mecMemberDto.getOpenId()); }
		 * 
		 * // 组装返回客户信息 if (null == mecMemberNoReturn) { mecMemberNoReturn = new
		 * MecMemberNoReturn(); }
		 * mecMemberNoReturn.setMemberNo(addPersonMemberBaseReturn.getMemberNo());
		 * mecMemberNoReturn.setMemberName(addPersonMemberBase.getMemberName()); }
		 * 
		 * if (StringUtils.isEmpty(mecMemberNoReturn.getMemberName())) {
		 * mecMemberNoReturn.setMemberName(mecMemberDto.getNickNameWx()); }
		 * 
		 * logger.debug("addPersonAllByMec(MecMemberDto mecMemberDto={}) - end",
		 * mecMemberNoReturn); return mecMemberNoReturn;
		 */
		return null;
	}

	@Override
	public int findListByShopNo(String shopNo, String memberNo) {
		int count = personMemberDao.findListByShopNo(shopNo, memberNo);
		return count;
	}

	@Override
	public Page<FindGmDistantPageReturn> findByMemberNoAndShopNo(String shopNo, String memberNo)
			throws TsfaServiceException {
		List<FindGmDistantPageReturn> returnList = null;
		MemberNoAndShopNoPageDto memberNoAndShopNoPageDto = new MemberNoAndShopNoPageDto();
//        memberNoAndShopNoPageDto.setShopNo(shopNo);
		memberNoAndShopNoPageDto.setMemberNo(memberNo);
		returnList = personMemberDao.findPageByMemberNoAndShopNo(memberNoAndShopNoPageDto);
		logger.debug("findByMemberNoAndShopNo : returnList{} ", returnList);
		Page<FindGmDistantPageReturn> returnPage = new Page<FindGmDistantPageReturn>(returnList,
				returnList == null || returnList.size() == 0 ? 0 : 1, memberNoAndShopNoPageDto);
		return returnPage;
	}

	@Override
	public List<FindGmDistantPageReturn> findByGuidNoAndShopNo(String shopNo, String guidMemberNo)
			throws TsfaServiceException {
		MemberNoAndShopNoPageDto memberNoAndShopNoPageDto = new MemberNoAndShopNoPageDto();
//        memberNoAndShopNoPageDto.setShopNo(shopNo);
		memberNoAndShopNoPageDto.setGuidNo(guidMemberNo);
		List<FindGmDistantPageReturn> returnList = personMemberDao
				.findPageByMemberNoAndShopNo(memberNoAndShopNoPageDto);
		return returnList;
	}

	@Override
	public List<Map<String, Object>> getCustomerInfo(Map<String, Object> paramMap) throws TsfaServiceException {
		logger.debug("getCustomerInfo(Map<String, Object> paramMap={}) - start", paramMap);
		AssertUtils.notNull(paramMap);
		List<Map<String, Object>> returnList = null;
		try {
			returnList = personMemberDao.getCustomerInfo(paramMap);
		} catch (Exception e) {
			logger.error("客户会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "客户会员信息不存在错误.！", e);
		}
		logger.debug("findPersonMemberListForApi(FindPersonMemberPage) - end - return value={}", returnList);
		return returnList;
	}

	/**
	 * 
	 *
	 * 方法说明：商户下按终端统计客户情况
	 *
	 * @param merchantNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月13日
	 *
	 */
	@Override
	public List<PersonMemberStsGroupByShop> memberStsGroupByShop(String merchantNo, Date beginTime, Date endTime) {
		logger.debug("memberStsGroupByShop(String merchantNo={}， Date beginTime={}, Date endTime={}) - start",
				merchantNo, beginTime, endTime);
		AssertUtils.notNullAndEmpty(merchantNo);
		AssertUtils.notNull(beginTime);
		AssertUtils.notNull(endTime);

		List<PersonMemberStsGroupByShop> returnList = null;
		try {
			returnList = personMemberDao.memberStsGroupByShop(merchantNo, beginTime, endTime);
		} catch (Exception e) {
			logger.error("商户下按终端统计客户情况错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "商户下按终端统计客户情况错误.！", e);
		}
		logger.debug("memberStsGroupByShop(String) - end - return value={}",
				returnList == null ? 0 : returnList.size());
		return returnList;
	}

	@Override
	public List<FindPersonMemberReturn> findPersonMemberByMemberNoAndMerchantNo(FindPersonMember findPersonMember) {
		logger.debug("findPersonMemberByMemberNoAndMerchantNo(FindPersonMember findPersonMember={}) - start",
				findPersonMember);

		AssertUtils.notNull(findPersonMember);
		AssertUtils.notAllNull(findPersonMember.getMemberNo(), "客户编号不能为空");
		AssertUtils.notAllNull(findPersonMember.getMerchantNo(), "商户编号不能为空");
		try {
			List<FindPersonMemberReturn> list = personMemberDao.findPersonMemberByGm(findPersonMember);
			logger.debug("findPersonMemberByMemberNoAndMerchantNo(FindPersonMember) - end - return value={}", list);
			return list;
		} catch (Exception e) {
			logger.error("查找客户会员信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查找客户会员信息错误！", e);
		}
	}

	@Override
	public List<FindUnchatMemberPageReturn> findUnchatMemberList(FindUnchatMemberPage findUnchatMemberPage) {
		logger.debug("findUnchatMemberList(FindUnchatMemberPage findUnchatMemberPage={}) - start",
				findUnchatMemberPage);

		List<FindUnchatMemberPageReturn> returnList = null;
		try {
			List<FindUnchatMemberPageReturn> list = personMemberDao.findUnchatMemberList(findUnchatMemberPage);
			if (null != list && !list.isEmpty()) {
				returnList = new ArrayList<>();
				FindMemberChatCount findMemberChatCount = new FindMemberChatCount();
//				findMemberChatCount.setShopNo(findUnchatMemberPage.getShopNo());
//				findMemberChatCount.setShopNos(findUnchatMemberPage.getShopNos());
				findMemberChatCount.setMerchantNo(findUnchatMemberPage.getMerchantNo());
				findMemberChatCount.setChatBeginTime(findUnchatMemberPage.getChatBeginTime());
				findMemberChatCount.setChatEndTime(findUnchatMemberPage.getChatEndTime());
				List<FindMemberChatCountReturn> countList = imChatInfoService.stsMemberChatCount(findMemberChatCount);
				if (countList != null && !countList.isEmpty()) {
					Map<String, Integer> countMap = new HashMap<>();
					for (FindMemberChatCountReturn count : countList) {
						countMap.put(count.getMemberNo() + count.getMemberNoGm(), count.getChatCount());
					}
					// 循环查询客户记录
					Integer chatCount = null;
					for (FindUnchatMemberPageReturn member : list) {
						chatCount = countMap.get(member.getMemberNo() + member.getMemberNoGm());
						if (chatCount == null) {
							returnList.add(member);
						} else if (findUnchatMemberPage.getLeChatCount() != null) {
							if (findUnchatMemberPage.getLeChatCount() >= chatCount) {
								member.setChatCount(chatCount);
								returnList.add(member);
							}
						} else {
							member.setChatCount(chatCount);
							returnList.add(member);
						}
					}
				} else {
					returnList = list;
				}

			}
		} catch (Exception e) {
			logger.error("查询未联系客户列表错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "查询未联系客户列表错误.！", e);
		}

		logger.debug("findUnchatMemberList(FindUnchatMemberPage) - end - return value={}", returnList);
		return returnList;
	}

	@Override
	public Long findWxPmCountByGm(FindWxPmByGm findWxPmByGm) throws TsfaServiceException {
		try {
			Long num = personMemberDao.findWxPmCountByGm(findWxPmByGm);
			if (num == null) {
				num = 0L;
			}
			return num;
		} catch (Exception e) {
			logger.error("根据导购查询客户统计数错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "根据导购查询客户统计数错误.！", e);
		}
	}

	@Override
	public List<FindPersonMemberReturn> findWxPmByGm(FindWxPmByGm findWxPmByGm) {
		List<FindPersonMemberReturn> list = null;
		try {
			list = personMemberDao.findWxPmByGm(findWxPmByGm);
			return list;
		} catch (Exception e) {
			logger.error("根据导购查询客户错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "根据导购查询客户错误.！", e);
		}
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<FindPersonMemberReturn> findPersonMember(String noWxGm, String gmNo) {
		List<FindPersonMemberReturn> list = null;
		try {
			com.lj.business.member.dto.FindPersonMember pm = new com.lj.business.member.dto.FindPersonMember();
			pm.setShopWx(noWxGm);
			pm.setMemberNoGm(gmNo);
			list = personMemberDao.findPersonMemberByGm(pm);
			return list;
		} catch (Exception e) {
			logger.error("根据导购查询客户错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_PAGE_ERROR, "根据导购查询客户错误.！", e);
		}
	}

	@Override
	public UpdatePersonMemberReturn updatePmType(UpdatePersonMember updatePersonMember) throws TsfaServiceException {
		logger.debug("updatePmType(UpdatePersonMember updatePersonMember={}) - start", updatePersonMember);

		AssertUtils.notNull(updatePersonMember);
		AssertUtils.notNullAndEmpty(updatePersonMember.getCode(), "CODE不能为空");
		AssertUtils.notNullAndEmpty(updatePersonMember.getPmTypeCode(), "PmTypeCode不能为空");
		try {
			PersonMember personMember = new PersonMember();
			personMember.setCode(updatePersonMember.getCode());
			// 客户分类
			personMember.setPmTypeCode(updatePersonMember.getPmTypeCode());
			if (StringUtils.isEmpty(updatePersonMember.getPmTypeName())) {
				FindPmType findPmType = new FindPmType();
				findPmType.setCode(updatePersonMember.getPmTypeCode());
				FindPmTypeReturn findPmTypeReturn = pmTypeService.findPmType(findPmType);
				if (null == findPmTypeReturn) {
					logger.error("客户分类不存在！");
					throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_ERROR, "客户分类不存在！");
				}
				personMember.setPmTypeName(findPmTypeReturn.getTypeName());
			} else {
				personMember.setPmTypeName(updatePersonMember.getPmTypeName());
			}
			personMember.setVersion(System.currentTimeMillis()); // 版本号
			AssertUtils.notUpdateMoreThanOne(personMemberDao.updateByPrimaryKeySelective(personMember));

			UpdatePersonMemberReturn updatePersonMemberReturn = new UpdatePersonMemberReturn();
			logger.debug("updatePmType(UpdatePersonMember) - end - return value={}", updatePersonMemberReturn);
			return updatePersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "客户会员信息更新信息错误！", e);
		}
	}

	@Override
	public UpdatePersonMemberReturn changePmTypeBatch(String[] codePms, String pmTypeCode, String pmTypeName) {
		logger.debug("changePmTypeBatch(String[] codePms={}, String pmTypeCode={}, String pmTypeName={}) - start",
				codePms, pmTypeCode, pmTypeName);

		AssertUtils.notEmpty(codePms);
		AssertUtils.notNullAndEmpty(pmTypeCode, "pmTypeCode不能为空");
		try {
			if (StringUtils.isEmpty(pmTypeName)) {
				FindPmType findPmType = new FindPmType();
				findPmType.setCode(pmTypeCode);
				FindPmTypeReturn findPmTypeReturn = pmTypeService.findPmType(findPmType);
				if (null == findPmTypeReturn) {
					logger.error("客户分类不存在！");
					throw new TsfaServiceException(ErrorCode.PM_TYPE_FIND_ERROR, "客户分类不存在！");
				}
				pmTypeName = findPmTypeReturn.getTypeName();
			}
			// 版本号也一起修改了
			personMemberDao.changePmTypeBatch(codePms, pmTypeCode, pmTypeName);

			UpdatePersonMemberReturn updatePersonMemberReturn = new UpdatePersonMemberReturn();
			logger.debug("updatePmType(UpdatePersonMember) - end - return value={}", updatePersonMemberReturn);
			return updatePersonMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("客户会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "客户会员信息更新信息错误！", e);
		}

	}

	@Override
	public FindPersonMemberReturn findPersonMemberByMoblieAndShopWx(FindPersonMember member) {
		AssertUtils.notNull(member);
		AssertUtils.notNullAndEmpty(member.getMobile());
		AssertUtils.notNullAndEmpty(member.getShopWx());
		AssertUtils.notNullAndEmpty(member.getMerchantNo());
		FindPersonMemberReturn findPersonMemberBaseReturn = null;
		try {
			findPersonMemberBaseReturn = personMemberDao.findPersonMemberByMoblieAndShopWx(member);
			return findPersonMemberBaseReturn;
		} catch (NullPointerException e) {
			logger.error("客户会员基础信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR, "客户会员基础信息查询错误.！", e);
		}
	}

	@Override
	public String findPersonMemberCodeByMemberNo(String memberNo) {
		AssertUtils.notNullAndEmpty(memberNo, "客户编号不能为空");
		return personMemberDao.findPersonMemberCodeByMemberNo(memberNo);
	}

	@Override
	public void delete(UpdatePersonMember updatePersonMember) {
		personMemberDao.delete(updatePersonMember);

	}

	@Override
	public List<PersonMemberDto> findPersonMemberByMoblies(PersonMemberDto personMemberDto)
			throws TsfaServiceException {
		AssertUtils.notNull(personMemberDto);
		AssertUtils.notNullAndEmpty(personMemberDto.getMerchantNo());
//		AssertUtils.notNullAndEmpty(personMemberDto.getMemberNoGm());
		List<PersonMemberDto> findPersonMemberBaseReturn = null;
		try {
			findPersonMemberBaseReturn = personMemberDao.findPersonMemberByMoblies(personMemberDto);

			if (findPersonMemberBaseReturn != null && findPersonMemberBaseReturn.size() > 0) {
				List<String> noWxGms = new ArrayList<>();// 終端微信。用于查询終端头像等信息
				// 转换对象
				for (PersonMemberDto pm : findPersonMemberBaseReturn) {
					noWxGms.add(pm.getShopWx());// 终端WX
				}
				// 2.终端微信头像
				FindShopTerminal findShopTerminal = new FindShopTerminal();
				findShopTerminal.setNoWxList(noWxGms);
				findShopTerminal.setMerchantNo(personMemberDto.getMerchantNo());
				List<FindShopTerminalReturn> sts = shopTerminalService.findShopTerminalSelect(findShopTerminal);
				Map<String, FindShopTerminalReturn> terMap = new HashMap<>();
				for (FindShopTerminalReturn base : sts) {
					terMap.put(base.getNoWx(), base);
				}
				// 3.组装返回
				for (PersonMemberDto pm : findPersonMemberBaseReturn) {
					FindShopTerminalReturn ter = terMap.get(pm.getShopWx());
					if (ter != null) {
						pm.setTerminalHeadurl(ter.getHeadurl());
					}
				}
			}
			return findPersonMemberBaseReturn;
		} catch (NullPointerException e) {
			logger.error("客户会员基础信息查询错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_BASE_FIND_PAGE_ERROR, "客户会员基础信息查询错误.！", e);
		}
	}

	@Override
	public int findTotalMemberPhone(Map mapPhone) {
		int count = 0;
		try {
			count = personMemberDao.findTotalMemberPhone(mapPhone);
		} catch (Exception e) {
			logger.error("查询手机号码客户错误!", e);
		}
		return count;
	}

	@Override
	public int findTotalMember(Map map) throws TsfaServiceException {
		int count = 0;
		try {
			count = personMemberDao.findTotalMember(map);
		} catch (Exception e) {
			logger.error("查询客户数量错误!", e);
		}
		return count;
	}

}
