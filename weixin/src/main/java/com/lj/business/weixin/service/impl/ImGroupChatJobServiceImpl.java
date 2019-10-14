package com.lj.business.weixin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cp.dto.coupon.AddCoupon;
import com.lj.business.cp.dto.couponMemberRelation.AddCouponMemberRelation;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.weixin.constant.ErrorCodeImGroupChatInfo;
import com.lj.business.weixin.dao.IImGroupChatJobDao;
import com.lj.business.weixin.domain.ImGroupChatJob;
import com.lj.business.weixin.dto.FindImGroupChatJobPage;
import com.lj.business.weixin.dto.ImGroupChatInfoDto;
import com.lj.business.weixin.dto.ImGroupChatJobDto;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.emus.ChatRoomType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImGroupChatInfoService;
import com.lj.business.weixin.service.IImGroupChatJobService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxSmallProgramService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
@Service
public class ImGroupChatJobServiceImpl implements IImGroupChatJobService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ImGroupChatJobServiceImpl.class);
	

	@Resource
	private IImGroupChatJobDao imGroupChatJobDao;
	
	@Resource
	private IImGroupChatJobService iImGroupChatJobService;
	
	@Resource
	private IImGroupChatInfoService iImGroupChatInfoService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Resource
	private IImChatInfoService imChatInfoService;
	
	@Autowired
	private IWxSmallProgramService wxSmallProgramService;
	
	@Autowired
	private IWxPublicAccountService wxPublicAccountService;
	
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	
	@Resource
	private ICouponRuleService couponRuleService;
	
	@Resource
	private ICouponService couponService;
	
	@Resource
	private ICouponMemberRelationService couponMemberRelationService;// 优惠券用户关联service
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	@Resource
	private IMerchantService merchantService;
	
	@Resource
	private IPersonMemberService personMemberService; // 客户信息服务

	/**   
	 * <p>Title: addImGroupChatJob</p>   
	 * <p>Description: </p>   
	 * @param imGroupChatJobDto
	 * @return
	 * @throws TsfaServiceException   
	 * @see com.lj.business.weixin.service.IImGroupChatJobService#addImGroupChatJob(com.lj.business.weixin.dto.ImGroupChatJobDto)   
	 */
	@Override
	public String addImGroupChatJob(ImGroupChatJobDto imGroupChatJobDto) throws TsfaServiceException {
		logger.debug("addImGroupChatJob(ImGroupChatJobDto imGroupChatJobDto={}) - start", imGroupChatJobDto); 

		AssertUtils.notNull(imGroupChatJobDto);
		try {
			ImGroupChatJob imGroupChatJob = new ImGroupChatJob();
			//add数据录入
			imGroupChatJob.setCode(GUID.generateCode());
			imGroupChatJob.setMerchantNo(imGroupChatJobDto.getMerchantNo());
			imGroupChatJob.setMerchantName(imGroupChatJobDto.getMerchantName());
			imGroupChatJob.setMemberNos(imGroupChatJobDto.getMemberNos());
			imGroupChatJob.setMemberNames(imGroupChatJobDto.getMemberNames());
			imGroupChatJob.setMemberNoWxs(imGroupChatJobDto.getMemberNoWxs());
//			imGroupChatInfo.setShopNo(imGroupChatInfoDto.getShopNo());
//			imGroupChatInfo.setShopName(imGroupChatInfoDto.getShopName());
			imGroupChatJob.setMemberNoGm(imGroupChatJobDto.getMemberNoGm());
			imGroupChatJob.setMemberNameGm(imGroupChatJobDto.getMemberNameGm());
			imGroupChatJob.setNoWxGm(imGroupChatJobDto.getNoWxGm());
			imGroupChatJob.setType(imGroupChatJobDto.getType());
			imGroupChatJob.setStatus(imGroupChatJobDto.getStatus());
			imGroupChatJob.setResourcesPath(imGroupChatJobDto.getResourcesPath());
			imGroupChatJob.setChatroomType(imGroupChatJobDto.getChatroomType());
			imGroupChatJob.setChatroomNoWx(imGroupChatJobDto.getChatroomNoWx());
			imGroupChatJob.setChatAssistantCode(imGroupChatJobDto.getChatAssistantCode());
			imGroupChatJob.setCreateDate(new Date());
			imGroupChatJob.setRemark(imGroupChatJobDto.getRemark());
			imGroupChatJob.setRemark2(imGroupChatJobDto.getRemark2());
			imGroupChatJob.setRemark3(imGroupChatJobDto.getRemark3());
			imGroupChatJob.setRemark4(imGroupChatJobDto.getRemark4());
			imGroupChatJob.setContent(imGroupChatJobDto.getContent());
			imGroupChatJob.setRepetition(imGroupChatJobDto.getRepetition());
			imGroupChatJob.setSendDate(imGroupChatJobDto.getSendDate());
			imGroupChatJob.setSourceCode(imGroupChatJobDto.getSourceCode());
			imGroupChatJobDao.insertSelective(imGroupChatJob);
			logger.debug("addImGroupChatJob(ImGroupChatJobDto imGroupChatJobDto={}) - end - return={}",imGroupChatJob.getCode()); 
			return imGroupChatJob.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增群发任务错误！",e);
			throw new TsfaServiceException(ErrorCodeImGroupChatInfo.IM_GROUP_CHAT_INFO_ADD_ERROR,"新增群发任务错误！",e);
		}
	}


	/**   
	 * <p>Title: doImGroupChat</p>   
	 * <p>Description:执行群发定时任务 </p>   
	 * @param imGroupChatJobDto
	 * @return
	 * @throws TsfaServiceException   
	 * @see com.lj.business.weixin.service.IImGroupChatJobService#doImGroupChat(com.lj.business.weixin.dto.ImGroupChatJobDto)   
	 */
	@Override
	public void doImGroupChat(String code) throws TsfaServiceException {
		AssertUtils.notNull(code,"code不能为空");
		ImGroupChatJob imGroupChatJob = imGroupChatJobDao.selectByPrimaryKey(code);
		
		ImGroupChatInfoDto imGroupChatInfoDto = new ImGroupChatInfoDto();
		imGroupChatInfoDto.setSourceCode(imGroupChatInfoDto.getSourceCode());
		imGroupChatInfoDto.setMerchantNo(imGroupChatJob.getMerchantNo());
		imGroupChatInfoDto.setMerchantName(imGroupChatJob.getMerchantName());
		imGroupChatInfoDto.setMemberNos(imGroupChatJob.getMemberNos());
		imGroupChatInfoDto.setMemberNames(imGroupChatJob.getMemberNames());
		imGroupChatInfoDto.setMemberNoGm(imGroupChatJob.getMemberNoGm());
		imGroupChatInfoDto.setMemberNameGm(imGroupChatJob.getMemberNameGm());
		imGroupChatInfoDto.setType(imGroupChatJob.getType());
		imGroupChatInfoDto.setChatroomType(imGroupChatJob.getChatroomType());
		imGroupChatInfoDto.setContent(imGroupChatJob.getContent());
		imGroupChatInfoDto.setResourcesPath(imGroupChatJob.getResourcesPath());
		
		if(imGroupChatInfoDto.getResourcesPath() !=null && !imGroupChatInfoDto.getResourcesPath().equals("")) {
		   String imagePathArray[] = imGroupChatInfoDto.getResourcesPath().split(",");
		   for(String s : imagePathArray) {
			   if(s != null && !s.equals("")) {
				   imGroupChatInfoDto.setResourcesPath(s);
				   String infoCode =iImGroupChatInfoService.addImGroupChatInfo(imGroupChatInfoDto);
				   sendGroupChatInfo(infoCode);
			   }
		   }
		   //发文字
		   String infoCode =iImGroupChatInfoService.addImGroupChatInfo(imGroupChatInfoDto);
		   sendGroupChatInfo(infoCode);
		}else{
			String infoCode =iImGroupChatInfoService.addImGroupChatInfo(imGroupChatInfoDto);
			sendGroupChatInfo(infoCode);
		}
	}
	
	
	private void sendGroupChatInfo(String code) {
		ImGroupChatInfoDto imGroupChatInfoDto = new ImGroupChatInfoDto();
		imGroupChatInfoDto.setCode(code);
		ImGroupChatInfoDto groupChatInfoDto = iImGroupChatInfoService.findImGroupChatInfo(imGroupChatInfoDto);
		AssertUtils.notNull(groupChatInfoDto,"群发信息不存在！");

		String[] memberNos = groupChatInfoDto.getMemberNos().split(",");
		String[] memberNoGms = groupChatInfoDto.getMemberNoGm().split(",");
		for (int i = 0; i < memberNos.length; i++) {
			
			String str = memberNos[i];
			String memberNoGm = memberNoGms[i];
			logger.info("循环群发消息客户：{}，导购：{}",str,memberNoGm);
			taskExecutor.execute(new Runnable() { // 通过线程池发送
				@Override
				public void run() {
					logger.info("向客户{}发送群发消息", str);
					/**
					 * 线程沉睡5秒
					 */
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						logger.error("沉睡当前线程失败", e);
					}

					SendImChatInfo sendImChatInfo = new SendImChatInfo();
					sendImChatInfo.setType(groupChatInfoDto.getType());
					sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
					sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
					sendImChatInfo.setChatroomType(ChatRoomType.PERSONAL.getCode());
					sendImChatInfo.setMemberNo(str);
					sendImChatInfo.setMemberNoGm(memberNoGm);
					sendImChatInfo.setNoWxGm(groupChatInfoDto.getNoWxGm());
					
					FindPersonMember findPersonMember = new FindPersonMember();
					findPersonMember.setMemberNo(sendImChatInfo.getMemberNo());
					findPersonMember.setMemberNoGm(sendImChatInfo.getMemberNoGm());
					findPersonMember.setShopWx(sendImChatInfo.getNoWxGm());
					FindPersonMemberReturn personMemberBase = personMemberService.findPersonMemberByMemberNoAndGM(findPersonMember);
					switch (sendImChatInfo.getType()) {
					case "1": // 文本 文本消息内容不转义
						sendImChatInfo.setContent(StringEscapeUtils.unescapeHtml4(groupChatInfoDto.getContent()).toString());
						imChatInfoService.sendChat(sendImChatInfo);
						break;
						
					case "49":	// 链接 文章  文本消息内容不转义
						String shareImage = localCacheSystemParams.getSystemParam("ms","image", "shareImage");
						logger.info("分享图片链接：" + shareImage);
						sendImChatInfo.setResources(shareImage);
						sendImChatInfo.setContent(StringEscapeUtils.unescapeHtml4(sendImChatInfo.getContent()).toString());
						break;
						
					case "491":	// 优惠券
						// 查询导购信息
						AssertUtils.notNullAndEmpty(groupChatInfoDto.getSourceCode(),"优惠券规则编号不能为空");
						FindGuidMember findGuidMember = new FindGuidMember();
						findGuidMember.setMemberNo(sendImChatInfo.getMemberNoGm());
						FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);
						
						FindCouponRule findCouponRule = new FindCouponRule();
						findCouponRule.setCode(groupChatInfoDto.getSourceCode());
						
						FindCouponRuleReturn findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
						
						AddCoupon addCoupon = new AddCoupon();
						addCoupon.setMerchantNo(guidMember.getMerchantNo());
						addCoupon.setMerchantName(guidMember.getMerchantName());
//						addCoupon.setShopNo(guidMember.getShopNo());
//						addCoupon.setShopName(guidMember.getShopName());
						addCoupon.setRuleNo(findCouponRuleReturn.getCode());
						addCoupon.setCouponNo(GUID.generateCode());
						addCoupon.setCouponStatus(CouponStatus.USED.toString());
						addCoupon.setUseDate(new Date());
						addCoupon.setCreateDate(new Date());
						couponService.addCoupon(addCoupon); // 新增优惠券
						
						AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
						addCouponMemberRelation.setMemberNoGm(guidMember.getMemberNo());
						addCouponMemberRelation.setMemberNameGm(guidMember.getMemberName());
						addCouponMemberRelation.setMemberNo(personMemberBase.getMemberNo());
						addCouponMemberRelation.setMemberName(personMemberBase.getMemberName());
						addCouponMemberRelation.setCouponNo(addCoupon.getCouponNo());
						addCouponMemberRelation.setCouponStatus(addCoupon.getCouponStatus());
						addCouponMemberRelation.setUseDate(new Date());
						addCouponMemberRelation.setCreateDate(new Date());
						couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation); // 新增优惠券用户绑定关系
						
						String cp_result_url = localCacheSystemParams.getSystemParam("cp", "result_url", "cp_result_url");
						// 优惠券领取url
						String resultUrl = String.format(cp_result_url, guidMember.getMemberNo(), personMemberBase.getMemberNo(), addCoupon.getCouponNo());
						
						FindMerchantDto findMerchantDto = new FindMerchantDto();
						findMerchantDto.setMerchantNo(findCouponRuleReturn.getMerchantNo());
						FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
						
						sendImChatInfo.setType("491");	// 分享优惠券
						if (findMerchantReturnDto != null && StringUtils.isNotEmpty(findMerchantReturnDto.getLogoAddr())) {
							String uploadUrl = localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl");	//商户图片静态地址
							sendImChatInfo.setResources(uploadUrl + findMerchantReturnDto.getLogoAddr()); // 商户图标
						} else {
							String couponUrl = localCacheSystemParams.getSystemParam("api", "logo", "couponLogoUrl");
							sendImChatInfo.setResources(couponUrl);
						}
						sendImChatInfo.setShareTitle(findCouponRuleReturn.getCouponName());
						sendImChatInfo.setShareDes(/*"使用范围:" + findCouponRuleReturn.getShopName() + */"说明:" + findCouponRuleReturn.getCouponRemark());
						sendImChatInfo.setShareUrl(resultUrl);
						logger.info("发送优惠券：{}", sendImChatInfo);

						break;
						
					case "494":
						AssertUtils.notNullAndEmpty(groupChatInfoDto.getSourceCode(),"小程序编号不能为空");
						FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
						findWxSmallProgram.setCode(groupChatInfoDto.getSourceCode());
						FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService.findWxSmallProgram(findWxSmallProgram);
						sendImChatInfo.setResources(findWxSmallProgramReturn.getSpLogo());
						sendImChatInfo.setShareTitle(findWxSmallProgramReturn.getSpName());
						sendImChatInfo.setShareDes(findWxSmallProgramReturn.getSpDesc());
						sendImChatInfo.setShareUrl(findWxSmallProgramReturn.getSpUrl());
						Map<String, String> smallProgramMap = new HashMap<>();
						smallProgramMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY, findWxSmallProgramReturn.getWxParam());
						sendImChatInfo.setContent(JsonUtils.jsonFromObject(smallProgramMap));
						logger.info("发送小程序：{}", sendImChatInfo);
						break;
						
					case "42":	// 发送名片，包括个人名片和公众号
						/**
						 * content字段以json格式保存以下参数：
						 * 1、发送个人名片时(前端组装参数)：
						 * 	发送人微信号（noWx）：username
						 * 	发送人微信别名：alias
						 * 	发送人客户编号：memberNo
						 * 	发送人所属导购：memberNoGm
						 * 	名片标识：certflag=0
						 * 2、发送公众号时(后台组装参数)：
						 *  公众号xml参数: wxParam
						 * 	公众号微信名：username
						 * 	公众号微信别名：alias
						 *  名片标识：certflag !=0
						 * resources：个人名片时为微信头像、公众号时为logo
						 * shareTitle：个人名片时为客户名称（优先）或微信昵称、公众号时为公众号名字
						 * shareDes：个人名片时先不设置、公众号时为公众号
						 */
						AssertUtils.notNullAndEmpty(groupChatInfoDto.getSourceCode(),"名片编号不能为空");
						
						FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
						findWxPublicAccount.setCode(groupChatInfoDto.getSourceCode());
						FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService.findWxPublicAccount(findWxPublicAccount);
						sendImChatInfo.setResources(findWxPublicAccountReturn.getPaLogo());
						sendImChatInfo.setShareTitle(findWxPublicAccountReturn.getPaName());
						sendImChatInfo.setShareDes(findWxPublicAccountReturn.getPaDesc());
						Map<String, String> contentMap = new HashMap<>();
						contentMap.put("certflag", findWxPublicAccountReturn.getPaCertflag());
						contentMap.put("username", findWxPublicAccountReturn.getPaUsername());
						contentMap.put("alias", findWxPublicAccountReturn.getPaAlias());
						contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY, findWxPublicAccountReturn.getWxParam());
						sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
						logger.info("发送公众号名片：{}", sendImChatInfo);
						
						break;
						
					default: // 其他：语音、图片等
						sendImChatInfo.setResources(groupChatInfoDto.getResourcesPath());
						imChatInfoService.sendChat(sendImChatInfo);
						break;
					}
				}
			});
		}
	}


	/**   
	 * <p>Title: findImGroupChatJobPage</p>   
	 * <p>Description: </p>   
	 * @param page
	 * @return
	 * @throws TsfaServiceException   
	 * @see com.lj.business.weixin.service.IImGroupChatJobService#findImGroupChatJobPage(com.lj.business.weixin.dto.FindImGroupChatJobPage)   
	 */
	@Override
	public Page<ImGroupChatJobDto> findImGroupChatJobPage(FindImGroupChatJobPage page) throws TsfaServiceException {
		logger.debug("findImGroupChatJobPage(FindImGroupChatJobPage page = {})-start", page);
		List<ImGroupChatJobDto> list = new ArrayList<>();
		int count = 0;
		try {
			count = imGroupChatJobDao.findImGroupChatJobPageCount(page);
			if(count>0){
				list = imGroupChatJobDao.findImGroupChatJobPage(page);
			}
		} catch (Exception e) {
			logger.error("查询群发任务错误",e);
			throw new TsfaServiceException(ErrorCodeImGroupChatInfo.IM_GROUP_CHAT_INFO_FIND_PAGE_ERROR,"查询群发任务错误",e);
		}
		Page<ImGroupChatJobDto> returnPage = new Page<ImGroupChatJobDto>(list, count, page);
		logger.debug("findImGroupChatJobPage(FindImGroupChatJobPage page)-start - return value = {}", returnPage);
		return returnPage;
	}

}
