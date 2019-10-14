//package com.lj.business.api.controller;
//
//import java.text.ParseException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.lj.base.core.util.DateUtils;
//import com.lj.base.core.util.StringUtils;
//import com.lj.base.exception.TsfaServiceException;
//import com.lj.business.api.domain.GeneralResponse;
//import com.lj.business.api.utils.VisitLimitUtil;
//import com.lj.business.cm.service.IMerchantParamsService;
//import com.lj.business.common.KeyConstant;
//import com.lj.business.member.dto.FindGuidMember;
//import com.lj.business.member.dto.FindGuidMemberPage;
//import com.lj.business.member.dto.FindGuidMemberPageReturn;
//import com.lj.business.member.dto.FindGuidMemberReturn;
//import com.lj.business.member.dto.FindPersonMemberBase;
//import com.lj.business.member.dto.FindPersonMemberBaseReturn;
//import com.lj.business.member.dto.FindPersonMemberPage;
//import com.lj.business.member.dto.FindPersonMemberPageReturn;
//import com.lj.business.member.dto.PmLabelDto;
//import com.lj.business.member.service.IGuidMemberService;
//import com.lj.business.member.service.IPersonMemberBaseService;
//import com.lj.business.member.service.IPersonMemberService;
//import com.lj.business.member.service.IPmLabelService;
//import com.lj.business.supcon.dto.contacts.FindWxInfoRequestDto;
//import com.lj.business.supcon.service.IContactsService;
//import com.lj.business.weixin.dto.imchat.SendImChatInfo;
//import com.lj.business.weixin.service.IImChatInfoService;
//import com.lj.distributecache.IDistributeCache;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 类说明：对商户提供API接口
// * <p>
// * 详细描述：根据需求扩展该接口,提供给对外商户使用
// * @Company: 扬恩科技有限公司
// * @author 李端强
// * CreateDate: 2017年12月13日
// */
//@Controller
//@RequestMapping(value="apiInfoSearch")
//public class ApiInfoSearch {
//	private static final Logger logger = LoggerFactory.getLogger(ApiInfoSearch.class);
//	
//	@Autowired
//	private IPersonMemberService personMemberService;
//	@Autowired
//	private IPersonMemberBaseService personMemberBaseService;
//	@Autowired
//	private IGuidMemberService guidMemberService;
//	@Autowired
//	private IImChatInfoService imChatInfoService;
//	@Autowired
//	private IContactsService contactsService;
//	@Autowired
//	private IPmLabelService pmLabelService;
//	@Resource
//	private IMerchantParamsService merchantParamsService;//商户接口参数服务
//	@Resource 
//	private IDistributeCache distributeCache;//缓存接口
//	
//	
//	/**
//	 *
//	 * 方法说明1：提供外部接口,根据手机号查询客户的微信号
//	 * @param mobile 客户手机号
//	 * @return map=memberName,mobile,noWx
//	 * @author 李端强 CreateDate: 2017年12月13日
//	 *
//	 */
//	@RequestMapping(value="getInfoByMobile.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse getBaseInfoByMobile(@RequestParam(value="mobile") String mobile,String currentMerchentNo) {
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "getBaseInfoByMobile")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("getBaseInfoByMobile(String mobile={}) - start", mobile); //$NON-NLS-1$
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("mobile", mobile);//传入手机号
//		map.put("merchentNo", currentMerchentNo);//传入商户编号
//		if(StringUtils.isNullOrEmpty(mobile)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00001", "传入客户手机号缺失", "");
//			return returnGeneralResponse;
//		}
//		Map<String, Object> returnMap = personMemberBaseService.getBaseInfoByMobile(map);
//		logger.debug("getBaseInfoByMobile(String) - end - return value={}", returnMap); //$NON-NLS-1$
//		return GeneralResponse.generateResponse(true, "", "", returnMap);//成功返回内容
//	}
//	
//	/**
//	 *
//	 * 方法说明1：重写提供外部接口,根据手机号查询客户的微信号(通用)
//	 * @param mobile 客户手机号
//	 * @return map=memberName,mobile,wxOpenId,merchantWxNo
//	 * @author 李端强 CreateDate: 2017年12月13日
//	 *
//	 */
//	@RequestMapping(value="getInfoByMobileSecurity.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse getBaseInfoByMobileSecurity(String merchantWxNo, String mobile,String currentMerchentNo) {
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "getBaseInfoByMobileSecurity")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率或当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("getInfoByMobileSecurity(String mobile={},merchantWxNo={}) - start", mobile,merchantWxNo);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("mobile", mobile);//传入手机号
//		map.put("merchentNo", currentMerchentNo);//传入商户编号
//		map.put("merchantWxNo", merchantWxNo);//商户微信号
//		if(StringUtils.isNullOrEmpty(mobile)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00001", "传入客户手机号缺失", "");
//			return returnGeneralResponse;
//		}
//		Map<String, Object> returnMap = personMemberBaseService.getBaseInfoByMobile(map);
//		logger.debug("getInfoByMobileSecurity(String) - end - return value={}", returnMap);
//		return GeneralResponse.generateResponse(true, "", "", returnMap);//成功返回内容
//	}
//	
//	/**
//	 * 
//	 * 方法说明2：LOHO根据条件筛选之后发送微信消息 
//	 * @param mobileGM 店员手机号
//	 * @param mobile 客户手机号
//	 * @param msg 消息内容
//	 * @param currentMerchentNo 商户编号
//	 * @return
//	 * @author 李端强 CreateDate: 2017年12月13日
//	 */
//	@RequestMapping(value="sendWxByCondition.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse sendWxByCondition(String mobileGM,String mobile,String noWx,String msg,String currentMerchentNo){
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "sendWxByCondition")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("sendWxByCondition(String mobileGM={}, String mobile={}, String noWx={}, String msg={}, String currentMerchentNo={}) - start", mobileGM, mobile, noWx, msg, currentMerchentNo); //$NON-NLS-1$
//		try {
//			FindGuidMember findGuidMember = new FindGuidMember();
//			if(StringUtils.isNullOrEmpty(currentMerchentNo)) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00001", "传入商户编号缺失", "");
//				logger.debug("sendWxByCondition(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}
//			findGuidMember.setMerchantNo(currentMerchentNo);//导购所属商户编号
//			if(StringUtils.isNullOrEmpty(mobileGM)) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00001", "传入导购手机号缺失", "");
//				logger.debug("sendWxByCondition(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}
//			findGuidMember.setMobile(mobileGM);//导购手机
//			/*获取单个导购信息*/
//			final FindGuidMemberReturn guid = guidMemberService.findGuidMember(findGuidMember);
//			if(guid==null || StringUtils.isNullOrEmpty(guid.getNoWx())) {//导购信息缺失微信号
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00002", "导购缺失微信号", "");
//				logger.debug("sendWxByCondition(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}
//			if(StringUtils.isNullOrEmpty(msg)) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00004", "传入消息内容缺失", "");
//				logger.debug("sendWxByCondition(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}
//			FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
//			if(!StringUtils.isNullOrEmpty(mobile)) {
//				findPersonMemberBase.setMobile(mobile);//顾客手机号,查询WX号
//			}
//			if(!StringUtils.isNullOrEmpty(noWx)) {
//				findPersonMemberBase.setNoWx(noWx);//顾客手机号,查询WX号
//			}
//			FindPersonMemberBaseReturn personBase = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
//			
//			boolean ret = sendWxprompt(guid, personBase.getMemberNo(), msg);//发送微信消息
//			if(!ret) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00014", "发送客户微信消息异常", "客户信息缺失,无法发送消息.");
//				logger.debug("sendWxByCondition(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}
//			
//		} catch (TsfaServiceException e) {
//			logger.error("sendWxByCondition(String, String, String, String, String)", e); //$NON-NLS-1$
//
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00014", "发送客户微信消息异常", e.getMessage());
//			logger.debug("sendWxByCondition(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		} catch (Exception e) {
//			logger.error("sendWxByCondition(String, String, String, String, String)", e); //$NON-NLS-1$
//
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00014", "发送客户微信消息异常", e.getMessage());
//			logger.debug("sendWxByCondition(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		GeneralResponse returnGeneralResponse = GeneralResponse.generateSuccessResponse();
//		logger.debug("sendWxByCondition(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//		return returnGeneralResponse;
//	}
//	
//	/**
//	 * 
//	 * 方法说明2：重写指定客户微信openID发送微信消息 
//	 * @param merchantWxNo 商户门店微信号
//	 * @param wxOpenId 客户微信openId
//	 * @param msg 消息内容
//	 * @param currentMerchentNo 商户编号
//	 * @return
//	 * @author 李端强 CreateDate: 2018年1月10日11:44:56
//	 */
//	@RequestMapping(value="sendWxByOpenId.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse sendWxByOpenId(String merchantWxNo,String wxOpenId,String msg,String currentMerchentNo){
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "sendWxByOpenId")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率或当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("sendWxByOpenId(String merchantWxNo={}, String wxOpenId={}, String msg={}, String currentMerchentNo={}) - start", merchantWxNo, wxOpenId, msg, currentMerchentNo);
//		Map<String, Object> retMap = Maps.newHashMap();
//		try {
//			FindGuidMember findGuidMember = new FindGuidMember();
//			if(StringUtils.isNullOrEmpty(currentMerchentNo)) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00001", "传入商户编号缺失", "");
//				logger.debug("sendWxByOpenId(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}
//			findGuidMember.setMerchantNo(currentMerchentNo);//导购所属商户编号
//			if(StringUtils.isNullOrEmpty(merchantWxNo)) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00001", "传入商户微信号缺失", "");
//				logger.debug("sendWxByOpenId(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}
//			findGuidMember.setNoWx(merchantWxNo);//商户微信号设置
//			/*获取单个导购信息*/
//			final FindGuidMemberReturn guid = guidMemberService.findGuidMember(findGuidMember);
//			if(StringUtils.isNullOrEmpty(msg)) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00004", "传入消息内容缺失", "");
//				logger.debug("sendWxByOpenId(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}else if(msg.length()>500) {//群发内容长度限定
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00005", "传入消息内容长度超过限制,上限500", "");
//				logger.debug("sendWxByOpenId(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}
//			String[] wxArr = wxOpenId.split(",");
//			String retWxOpenId = "";
//			if(wxArr.length > 100) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00006", "单次群发人数过多,上限100", "");
//				logger.debug("sendWxByOpenId(String, String, String, String, String) - end - return value={}", returnGeneralResponse);
//				return returnGeneralResponse;
//			}else if(wxArr.length>1) {//2个以上微信openId
//				for (String wx : wxArr) {
//					FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
//					findPersonMemberBase.setWxOpenId(wx);//根据客户的微信openId查询,基础表唯一返回
//					FindPersonMemberBaseReturn personBase = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
//					boolean ret = sendWxprompt(guid, personBase.getMemberNo(), msg);//发送微信消息
//					if(!ret) {
//						retWxOpenId += "," + wx;
//					}
//					randomSleep(25);//随机休眠
//				}
//			}else {//单个微信openId
//				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
//				findPersonMemberBase.setWxOpenId(wxOpenId);//根据客户的微信openId查询,基础表唯一返回
//				FindPersonMemberBaseReturn personBase = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
//				boolean ret = sendWxprompt(guid, personBase.getMemberNo(), msg);//发送微信消息
//				if(!ret) {
//					retWxOpenId = wxOpenId;
//				}
//			}
//			retMap.put("wxOpenId", retWxOpenId);//返回发送失败的openId
//		} catch (TsfaServiceException e) {
//			logger.error("sendWxByOpenId(String, String, String, String, String)", e); //$NON-NLS-1$
//
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00014", "发送客户微信消息异常", e.getMessage());
//			logger.debug("sendWxByOpenId(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		} catch (Exception e) {
//			logger.error("sendWxByOpenId(String, String, String, String, String)", e); //$NON-NLS-1$
//
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00014", "发送客户微信消息异常", e.getMessage());
//			logger.debug("sendWxByOpenId(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		GeneralResponse returnGeneralResponse = GeneralResponse.generateSuccessResponse();
//		logger.debug("sendWxByOpenId(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//		return returnGeneralResponse;
//	}
//	
//	/**
//	 * 
//	 * 方法说明：根据指定的商户编号,筛选标签code后发送微信消息 
//	 * @param mobileGM 店员手机号
//	 * @param mobile 客户手机号
//	 * @param msg 消息内容
//	 * @param currentMerchentNo 商户编号
//	 * @return
//	 * @author 李端强 CreateDate: 2017年12月13日
//	 */
//	@RequestMapping(value="sendWxByTitles.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse sendWxByTitles(String merchantWxNo,String consumerTitles,String msg,String currentMerchentNo){
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "sendWxByCondition")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("sendWxByTitles(String mobileGM={}, String mobile={}, String noWx={}, String msg={}, String currentMerchentNo={}) - start", merchantWxNo, consumerTitles, msg, currentMerchentNo);
//		Map<String, Object> retMap = Maps.newHashMap();
//		try {
//			FindGuidMember findGuidMember = new FindGuidMember();
//			if(StringUtils.isNullOrEmpty(currentMerchentNo)) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00001", "传入商户编号缺失", "");
//				logger.debug("sendWxByTitles(String, String, String, String, String) - end - return value={}", returnGeneralResponse);
//				return returnGeneralResponse;
//			}
//			findGuidMember.setMerchantNo(currentMerchentNo);//导购所属商户编号
//			if(StringUtils.isNullOrEmpty(merchantWxNo)) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00001", "传入的商户微信号缺失", "");
//				logger.debug("sendWxByTitles(String, String, String, String, String) - end - return value={}", returnGeneralResponse);
//				return returnGeneralResponse;
//			}
//			findGuidMember.setNoWx(merchantWxNo);//商户微信号
//			/*获取单个导购信息*/
//			final FindGuidMemberReturn guid = guidMemberService.findGuidMember(findGuidMember);
//			if(StringUtils.isNullOrEmpty(msg)) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00004", "传入消息内容缺失", "");
//				logger.debug("sendWxByTitles(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}else if(msg.length()>500) {//群发内容长度限定
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00005", "传入消息内容长度超过限制,上限500", "");
//				logger.debug("sendWxByTitles(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//				return returnGeneralResponse;
//			}
//			Map<String, Object> paramMap = Maps.newHashMap();
//			paramMap.put("merchantNo", currentMerchentNo);
//			paramMap.put("pmLabelCode", consumerTitles);
//			List<FindPersonMemberPageReturn> pmbList = personMemberService.findPmbListByLabelCode(paramMap);
//			String wxOpenID = "" ;
//			for (FindPersonMemberPageReturn pmb : pmbList) {
//				boolean pmbRet = sendWxprompt(guid, pmb.getMemberNo(), msg);//发送微信消息
//				if(!pmbRet) {//发送失败
//					
//				}
//				randomSleep(25);
//			}
//			retMap.put("wxOpenID", wxOpenID);
//		} catch (TsfaServiceException e) {
//			logger.error("sendWxByTitles(String, String, String, String, String)", e); //$NON-NLS-1$
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00014", "发送客户微信消息异常", e.getMessage());
//			logger.debug("sendWxByTitles(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		} catch (Exception e) {
//			logger.error("sendWxByTitles(String, String, String, String, String)", e); //$NON-NLS-1$
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00014", "发送客户微信消息异常", e.getMessage());
//			logger.debug("sendWxByTitles(String, String, String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(true, "", "", retMap);//成功返回结果
//		logger.debug("sendWxByTitles(String, String, String, String, String) - end - return value={}", returnGeneralResponse);
//		return returnGeneralResponse;
//	}
//	/**
//	 *
//	 * 方法说明：随机休眠时间
//	 * @throws InterruptedException
//	 * @author 李端强 CreateDate: 2018年1月10日
//	 */
//	private void randomSleep(int second) throws InterruptedException {
//		int ranInt = new Random().nextInt(second)*1000;//随机休眠时间
//		if(ranInt==0) {
//			Thread.sleep(new Random().nextInt(second)*1000);
//		}else {
//			Thread.sleep(ranInt);
//		}
//	}
//	
//	/**
//	 *
//	 * 方法说明3：通过手机号添加微信好友
//	 * @param mobileGM 导购号
//	 * @param mobile 客户手机号
//	 * @param currentMerchentNo 商户编号
//	 * @return
//	 * @author 李端强 CreateDate: 2017年12月28日
//	 *
//	 */
//	@RequestMapping(value="addFriendByMobile.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse addFriendByMobile(String mobileGM,String mobile,String currentMerchentNo) {
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "addFriendByMobile")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("addFriendByMobile(String wxNoGM={}, String mobile={}, String currentMerchentNo={}) - start", mobileGM, mobile, currentMerchentNo); //$NON-NLS-1$
//		if(StringUtils.isNullOrEmpty(mobileGM) || StringUtils.isNullOrEmpty(mobile)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00001", "传入店员/客户手机号缺失", "");
//			return returnGeneralResponse;
//		}else {
//			try {
//				FindWxInfoRequestDto dto = new FindWxInfoRequestDto();
//				FindGuidMember findGuidMember = new FindGuidMember();
//				findGuidMember.setMobile(mobileGM);//导购手机号
//				findGuidMember.setMerchantNo(currentMerchentNo);//商户编号
//				FindGuidMemberReturn guid = guidMemberService.findGuidMember(findGuidMember);
//				dto.setMemberNoGm(guid.getMemberNo());
//				dto.setWxQrCode(mobile);//搜索客户手机号
//				contactsService.sendAddNewFriendMessage(dto,KeyConstant.API_SEARCH_ADD_PREFIX);
//			} catch (TsfaServiceException e) {
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00014", "添加客户好友异常", e.getMessage());
//				return returnGeneralResponse;
//			}
//		}
//		GeneralResponse returnGeneralResponse = GeneralResponse.generateSuccessResponse();
//		logger.debug("addFriendByMobile(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//		return returnGeneralResponse;
//	}
//	
//	/**
//	 *
//	 * 方法说明：批量获取JK系统的标签库
//	 * @param getType 获取方式  FULL/ADD
//	 * @param lastTime 上次更新时间
//	 * @param currentMerchentNo 商户编号
//	 * @return
//	 * @author 李端强 CreateDate: 2017年12月28日
//	 *
//	 */
//	@RequestMapping(value="findSysTitles.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse findSysTitles(String getType,String lastTime,String currentMerchentNo) {
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "findSysTitles")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("findSysTitles(String getType={}, String lastTime={}, String currentMerchentNo={}) - start", getType, lastTime, currentMerchentNo); //$NON-NLS-1$
//		if(StringUtils.isNullOrEmpty(getType)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00005", "标签获取方式不能为空", "");
//
//			logger.debug("findSysTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		if(getType.equals("ADD") && StringUtils.isNullOrEmpty(lastTime)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00006", "标签获取最后更新时间不能为空", "");
//
//			logger.debug("findSysTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		try {
//			DateUtils.parseDate(lastTime, "yyyy-MM-dd HH:mm:ss");
//		} catch (ParseException e1) {
//			logger.error("findSysTitles(String, String, String)", e1); //$NON-NLS-1$
//
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00019", "查询时间格式错误", "");
//
//			logger.debug("findSysTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		Map<String, Object> paramMap = new HashMap<String, Object>();//查询参数
//		paramMap.put("getType", getType);
//		paramMap.put("lastTime", getType.equals("FULL") ? null : lastTime);//上次更新时间
//		paramMap.put("merchantNo", currentMerchentNo);//商户编号
//		List<PmLabelDto> retList = null;
//		try {
//			retList = pmLabelService.findPmLabelByLastTime(paramMap);
//		} catch (TsfaServiceException e) {
//			logger.error("findSysTitles(String, String, String)", e); //$NON-NLS-1$
//
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "S0001", "系统通讯错误,请稍后再试", e.getMessage());
//
//			logger.debug("findSysTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		Map<String, Object> retMap = new HashMap<String, Object>();//返回参数
//		retMap.put("rows", retList);//标签集合
//		if(retList!=null && retList.size()>0) {
//			retMap.put("lastTime", retList.get(0).getUpdateTime());//最后更新时间
//		}else {
//			retMap.put("lastTime", 0);//无数据最后更新时间0
//		}
//		GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(true, "", "", retMap);
//
//		logger.debug("findSysTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//		return returnGeneralResponse;
//	}
//	
//	/**
//	 *
//	 * 方法说明：批量客户打标签
//	 * @param wxOpenID 获取方式  多个ID使用英文逗号,分隔;上限500
//	 * @param code 标签编号
//	 * @param currentMerchentNo 商户编号
//	 * @return
//	 * @author 李端强 CreateDate: 2017年12月28日
//	 *
//	 */
//	@RequestMapping(value="findSysTitles.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse markConsumerTitles(String wxOpenId,String code,String currentMerchentNo) {
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "markConsumerTitles")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("markConsumerTitles(String wxOpenID={}, String code={}, String currentMerchentNo={}) - start", wxOpenId, code, currentMerchentNo); //$NON-NLS-1$
//		if(StringUtils.isNullOrEmpty(wxOpenId)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00007", "微信wxOpenID不能为空", "");
//			logger.debug("markConsumerTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		if(StringUtils.isNullOrEmpty(code)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00008", "标签编号不能为空", "");
//			logger.debug("markConsumerTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		if(wxOpenId.split(",").length>500) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00010", "微信wxOpenID个数超限,上限500", "");
//			logger.debug("markConsumerTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		if(code.split(",").length>5) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00011", "标签编号个数超限,上限5", "");
//			logger.debug("markConsumerTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		Map<String, Object> paramMap = new HashMap<String, Object>();//查询参数
//		paramMap.put("wxOpenId", wxOpenId);
//		paramMap.put("code", code);//标签code
//		paramMap.put("merchantNo", currentMerchentNo);//商户编号
//		//TODO 执行打标签,并返回处理结果
//		String wxOpenIds = pmLabelService.markConsumerTitles(paramMap);//调用接口返回
//		Map<String, Object> retMap = new HashMap<String, Object>();//返回参数
//		retMap.put("wxOpenId", wxOpenIds);//标签集合
//		GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(true, "", "", retMap);
//		logger.debug("markConsumerTitles(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//		return returnGeneralResponse;
//	}
//	
//	/**
//	 *
//	 * 方法说明：全量/增量获取客户信息
//	 * @param getType
//	 * @param lastTime
//	 * @param currentMerchentNo
//	 * @return
//	 * @author 李端强 CreateDate: 2018年1月8日
//	 *
//	 */
//	@RequestMapping(value="findConsumerInfo.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse findConsumerInfo(String getType,String lastTime,String currentMerchentNo) {
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "findConsumerInfo")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("findConsumerInfo(String getType={}, String lastTime={}, String currentMerchentNo={}) - start", getType, lastTime, currentMerchentNo); //$NON-NLS-1$
//		if(StringUtils.isNullOrEmpty(getType)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00017", "客户信息获取方式不能为空", "");
//			logger.debug("findConsumerInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		if(getType.equals("ADD") && StringUtils.isNullOrEmpty(lastTime)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00018", "客户信息获取更新起始时间不能为空", "");
//			logger.debug("findConsumerInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		Map<String, Object> retMap = new HashMap<String, Object>();//返回参数
//		List<FindPersonMemberPageReturn> retList = null;
//		FindPersonMemberPage findPersonMemberPage = new FindPersonMemberPage();
//		if(getType.equals("FULL")) {
//			findPersonMemberPage.setMerchantNo(currentMerchentNo);//商户编号
//			retList = personMemberService.findPersonMemberListForApi(findPersonMemberPage);//查询结果
//		}else if(getType.equals("ADD")){
//			findPersonMemberPage.setMerchantNo(currentMerchentNo);//商户编号
//			Date queryDate = new Date();
//			try {
//				queryDate = DateUtils.parseDate(lastTime, "yyyy-MM-dd HH:mm:ss");
//			} catch (ParseException e) {
//				logger.error("findConsumerInfo(String, String, String)", e); //$NON-NLS-1$
//				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00019", "查询时间格式错误", "");
//				return returnGeneralResponse;
//			}
//			findPersonMemberPage.setUpdateDate(queryDate);//更新时间
//			retList = personMemberService.findPersonMemberListForApi(findPersonMemberPage);//查询结果
//		}
//		//过滤需要返回的信息
//		if(retList!=null && retList.size()>0) {
//			List<Map<String, Object>> filterList = Lists.newArrayList();
//			Date tempLastTime = retList.get(0).getUpdateDate();//更新时间
//			for (FindPersonMemberPageReturn originDto : retList) {
//				Map<String, Object> tempMap = Maps.newHashMap();
//				tempMap.put("wxOpenID", originDto.getWxOpenId());//TODO 隐藏真实微信号
//				tempMap.put("memberName", originDto.getMemberName());
//				tempMap.put("status", originDto.getStatus());
//				tempMap.put("certTypeCode", originDto.getCertTypeCode());
//				tempMap.put("certNo", originDto.getCertNo());
//				tempMap.put("mobile", originDto.getMobile());
//				tempMap.put("imei", originDto.getImei());
//				tempMap.put("email", originDto.getEmail());
//				tempMap.put("job", originDto.getJob());
//				tempMap.put("address", originDto.getAddress());
//				tempMap.put("age", originDto.getAge());
//				tempMap.put("sex", originDto.getSex());
//				tempMap.put("nameAuthFlag", originDto.getNameAuthFlag());
//				tempMap.put("birthday", originDto.getBirthday());
//				tempMap.put("updateDate", originDto.getUpdateDate());
//				filterList.add(tempMap);
//				if(tempLastTime==null && originDto.getUpdateDate()!=null) {//最大修改时间
//					tempLastTime = originDto.getUpdateDate();
//				}
//				if(tempLastTime!=null && originDto.getUpdateDate()!=null && tempLastTime.before(originDto.getUpdateDate())) {
//					tempLastTime = originDto.getUpdateDate();
//				}
//			}
//			//最终返回Map
//			retMap.put("lastTime", filterList.size()==0? 0 :tempLastTime);
//			retMap.put("rows", filterList);
//		}
//		GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(true, "", "", retMap);
//
//		logger.debug("findConsumerInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//		return returnGeneralResponse;
//	}
//	
//	/**
//	 *
//	 * 方法说明：全量/增量获取商户门店微信信息
//	 * @param getType
//	 * @param lastTime
//	 * @param currentMerchentNo
//	 * @return
//	 * @author 李端强 CreateDate: 2018年1月8日
//	 *
//	 */
//	@RequestMapping(value="findShopWxInfo.do",method=RequestMethod.POST)
//	@ResponseBody
//	public GeneralResponse findShopWxInfo(String getType,String lastTime,String currentMerchentNo) {
//		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "findShopWxInfo")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
//			return returnGeneralResponse;
//		}
//		logger.debug("findShopWxInfo(String getType={}, String lastTime={}, String currentMerchentNo={}) - start", getType, lastTime, currentMerchentNo); //$NON-NLS-1$
//		if(StringUtils.isNullOrEmpty(getType) || !getType.equals("FULL")) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00021", "门店微信信息获取方式不正确", "");
//
//			logger.debug("findShopWxInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
//		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
//		findGuidMemberPage.setMerchantNo(currentMerchentNo);//指定商户编号
//		List<FindGuidMemberPageReturn> retList = guidMemberService.findGuidMembers(findGuidMemberPage);
//		Map<String, Object> retMap = new HashMap<String, Object>();//返回参数
//		Date tempLastTime = null;
//		if(retList!=null && retList.size()>0) {
//			tempLastTime = retList.get(0).getUpdateDate();//更新时间
//			List<Map<String, Object>> filterList = Lists.newArrayList();
//			Map<String, Object> tempMap = Maps.newHashMap();//门店名=微信号1,微信号2
//			for (FindGuidMemberPageReturn retDto : retList) {//遍历所有导购信息
//				if(!retDto.getStatus().equals("NORMAL")) continue; //状态不可用,过滤 
//				String wxOpenIDs = (String) tempMap.get(retDto.getShopName());
//				if(wxOpenIDs==null) {
//					tempMap.put(retDto.getShopName(), retDto.getNoWx());//放置初始微信号
//				}else {
//					if(!wxOpenIDs.contains(retDto.getNoWx())) {//门店拥有不重复的微信号
//						tempMap.put(retDto.getShopName(), wxOpenIDs+","+retDto.getNoWx());//追加微信号
//					}
//				}
//				if(tempLastTime==null && retDto.getUpdateDate()!=null) {//最大修改时间
//					tempLastTime = retDto.getUpdateDate();
//				}
//				if(tempLastTime!=null && retDto.getUpdateDate()!=null && tempLastTime.before(retDto.getUpdateDate())) {
//					tempLastTime = retDto.getCreateDate();
//				}
//			}
//			if(tempMap.size()>0) {
//				for (String shopKey : tempMap.keySet()) {
//					Map<String, Object> innerRetMap = Maps.newHashMap();
//					innerRetMap.put("shopName", shopKey);
//					innerRetMap.put("wxOpenId", tempMap.get(shopKey));
//					filterList.add(innerRetMap);
//				}
//			}
//			//最终返回Map
//			retMap.put("lastTime", filterList.size()==0? 0 :tempLastTime);
//			retMap.put("rows", filterList);
//		}
//		
//		GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(true, "", "", retMap);
//
//		logger.debug("findShopWxInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//		return returnGeneralResponse;
//	}
//	
//	
//	/**
//	 * 方法说明：根据客户的消费项目发送温馨提示
//	 *
//	 * @param findPersonMember
//	 * @param project
//	 * @return 是否成功发送
//	 *
//	 * @author 李端强 CreateDate: 2017年12月7日
//	 */
//	private boolean sendWxprompt(FindGuidMemberReturn guid,String consumerMemberNo,String msg) {
//		logger.debug("sendWxprompt(FindGuidMemberReturn guid={}, FindPersonMemberBaseReturn personBase={}, String msg={}) - start", guid, consumerMemberNo, msg);
//
//		try {
//			String content = msg;//消息内容
//			SendImChatInfo chatMessage =  new SendImChatInfo();
//			chatMessage.setType("1");//消息内容为纯文本
//			chatMessage.setMemberNo(consumerMemberNo);//客户编号
//			chatMessage.setMemberNoGm(guid.getMemberNo());//导购编号
//			//chatMessage.setNoWx(personBase.getNoWx());//顾客微信
//			//chatMessage.setAlias(personBase.getNoWxAlias());//顾客微信别名
//			chatMessage.setMsgSource(1);//导购发送
//			chatMessage.setSenderFlag(1);//发送人标识，非空：1导购发送、2客户发送
//			//chatMessage.setNoWxGm(guid.getNoWx());//导购微信
//			chatMessage.setContent(content);//消息内容
//			imChatInfoService.sendChat(chatMessage);
//		} catch (Exception e) {
//			logger.error("导购:"+guid.getMemberNo()+"客户:"+consumerMemberNo+",微信消息发送失败:",e);
//			return false;
//		} 
//
//		logger.debug("sendWxprompt(FindGuidMemberReturn, FindPersonMemberBaseReturn, String) - end - return value={}", true); //$NON-NLS-1$
//		return true;
//	}
//	
//}
