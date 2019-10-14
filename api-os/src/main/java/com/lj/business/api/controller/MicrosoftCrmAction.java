package com.lj.business.api.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.utils.VisitLimitUtil;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.distributecache.IDistributeCache;

/**
 * 
 * 
 * 类说明：微软CRM接口
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2018年2月28日
 */
@Controller
@RequestMapping(value="microsoftCrm")
public class MicrosoftCrmAction {
	private static final Logger logger = LoggerFactory.getLogger(MicrosoftCrmAction.class);
	public static final String TYPE_ALL = "ALL";
	public static final String TYPE_ADD = "ADD";
	public static final String PARAM_MERCHANTNO = "merchantNo";
	public static final String PARAM_UPDATEDATE = "updateDate";
	public static final String PARAM_MEMBERNO = "memberNo";
	public static final String PARAM_LASTTIME = "lastTime";
	public static final String GMNO = "gmno";
	public static final String GMPHONE = "gmphone";
	public static final String CUSTOMERPHONE = "customerphone";
	public static final String CUSTOMERNO = "customerno";
	
	public static final String GMPMCODE = "gmpmcode";
	

	@Autowired
	private IPersonMemberService personMemberService;
	@Autowired
	private IPersonMemberBaseService personMemberBaseService;
	@Autowired
	private IMerchantParamsService merchantParamsService;//商户接口参数服务
	@Autowired 
	private IDistributeCache distributeCache;//缓存接口
//	@Autowired
//	private IClientFollowService clientFollowService;
	@Autowired
	private IGuidMemberService guidMemberService;
	
	
	
	/**
	 * 
	 *
	 * 方法说明：获取新增/更新的意向客户信息
	 * 由CRM定时调用，以获取有电话的意向客户的增量变化信息。
	 * @param getType
	 * @param lastTime
	 * @param currentMerchentNo
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年2月28日
	 *
	 */
	@RequestMapping(value="getCustomerInfo.do",method=RequestMethod.POST)
	@ResponseBody
	public GeneralResponse getCustomerInfo(String getType,String lastTime,String currentMerchentNo) {
		logger.debug("getCustomerInfo(String getType={}, String lastTime={}, String currentMerchentNo={}) - start", getType, lastTime, currentMerchentNo); //$NON-NLS-1$
		if(VisitLimitUtil.limited(currentMerchentNo,distributeCache, merchantParamsService, "getCustomerInfo")) {
			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
			return returnGeneralResponse;
		}
		if(StringUtils.isNullOrEmpty(getType)) {
			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00017", "客户信息获取方式参数错误", "");
			logger.debug("getCustomerInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
			return returnGeneralResponse;
		}
		if(getType.equals(TYPE_ADD) && StringUtils.isNullOrEmpty(lastTime)) {
			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00018", "客户信息获取更新起始时间参数错误", "");
			logger.debug("getCustomerInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
			return returnGeneralResponse;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();//参数
		paramMap.put(PARAM_MERCHANTNO, currentMerchentNo);//商户编号

		List<Map<String,Object>> retList = null;	//返回结果
		if(getType.equals(TYPE_ALL)) {
			retList = personMemberService.getCustomerInfo(paramMap);//查询结果
		}else if(getType.equals(TYPE_ADD)){
			Date queryDate = new Date();
			try {
				queryDate = DateUtils.parseDate(lastTime, "yyyy-MM-dd HH:mm:ss");
			} catch (ParseException e) {
				logger.error("getCustomerInfo(String, String, String)", e); 
				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00019", "查询时间格式错误", "");
				return returnGeneralResponse;
			}
			paramMap.put(PARAM_UPDATEDATE, queryDate);//更新时间
			retList = personMemberService.getCustomerInfo(paramMap);//查询结果
		}
		logger.debug("getCustomerInfo(String, String, String) - end - return value={}", retList); 
		return GeneralResponse.generateSuccessResponse(retList);
	}

	/**
	 * 
	 *
	 * 方法说明：获取客户的跟进记录
	 *由CRM定时调用，以获取客户的跟进记录增量变化数据。
	 * @param getType
	 * @param lastTime
	 * @param phone	客户手机号
	 * @param currentMerchentNo
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年3月1日
	 *
	 */
	@RequestMapping(value="getClientFollowInfo.do",method=RequestMethod.POST)
	@ResponseBody
	public GeneralResponse getClientFollowInfo(String getType,String lastTime,String phone,String currentMerchentNo) {
		logger.debug("getClientFollowInfo(String getType,String lastTime,String phone,String currentMerchentNo={}) - start", getType, lastTime,phone, currentMerchentNo); //$NON-NLS-1$
		if(VisitLimitUtil.limited(currentMerchentNo, distributeCache, merchantParamsService, "getClientFollowInfo")) {
			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00111", "接口访问频率和当日总次数受限", "");
			return returnGeneralResponse;
		}

//		if(StringUtils.isNullOrEmpty(phone)) {
//			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00016", "客户手机号参数错误", "");
//			logger.debug("getClientFollowInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
//			return returnGeneralResponse;
//		}
		if(StringUtils.isNullOrEmpty(getType)) {
			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00017", "客户信息获取方式参数错误", "");
			logger.debug("getClientFollowInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
			return returnGeneralResponse;
		}
		if(getType.equals(TYPE_ADD) && StringUtils.isNullOrEmpty(lastTime)) {
			GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00018", "客户信息获取更新起始时间参数错误", "");
			logger.debug("getClientFollowInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
			return returnGeneralResponse;
		}

		/**
		 * 根据客户手机号获取客户编号
		 */
//		FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
//		findPersonMemberBase.setMobile(phone);
//		FindPersonMemberBaseReturn baseReturn=  personMemberBaseService.findByMobile(findPersonMemberBase);
//		logger.debug("客户编号={}", baseReturn.getMemberNo()); 
		Map<String, Object> paramMap = new HashMap<String, Object>();//参数
		List<Map<String, Object>> retList = null;	//返回结果
		paramMap.put(PARAM_MERCHANTNO, currentMerchentNo);//商户编号
//		paramMap.put(PARAM_MEMBERNO, baseReturn.getMemberNo());//客户编号

		/*if(getType.equals(TYPE_ALL)) {
			retList = clientFollowService.getClientFollowInfo(paramMap);
		}else if(getType.equals(TYPE_ADD)){*/
			/*Date queryDate = new Date();
			try {
				queryDate = DateUtils.parseDate(lastTime, "yyyy-MM-dd HH:mm:ss");
			} catch (ParseException e) {
				logger.error("getClientFollowInfo(String, String, String)", e); //$NON-NLS-1$
				GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(false, "E00019", "查询时间格式错误", "");
				return returnGeneralResponse;
			}
			paramMap.put(PARAM_LASTTIME, queryDate);//更新时间
			retList = clientFollowService.getClientFollowInfo(paramMap);*/
//		}
		
		
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		/**
		 * 因member信息不允许跨库表关联
		 * 过滤客户手机号为空的数据，
		 */
		for (Map<String, Object> map : retList) {
			if(map.get(CUSTOMERNO)!=null && StringUtils.isNotEmpty(map.get(CUSTOMERNO).toString())){
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setMemberNo(map.get(CUSTOMERNO).toString());
				logger.debug("客户编号={}", map.get(CUSTOMERNO).toString()); 
				logger.debug("导购编号={}", map.get(GMNO).toString()); 
				FindPersonMemberBaseReturn baseReturn=  personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
				if(baseReturn!=null && StringUtils.isNotEmpty(baseReturn.getMobile())){
					logger.debug("客户手机号={}", baseReturn.getMobile()); 
					map.put(CUSTOMERPHONE, baseReturn.getMobile());
					
					//获取PM表CODE update by leopeng 2018-07-25
					FindPersonMember findPersonMember = new FindPersonMember();
					findPersonMember.setMemberNo(map.get(CUSTOMERNO).toString());
					findPersonMember.setMemberNoGm(map.get(GMNO).toString());
					FindPersonMemberReturn findPersonMemberReturn = personMemberService.findPersonMemberByMemberNoAndGM(findPersonMember);
					if(findPersonMemberReturn != null && StringUtils.isNotEmpty(findPersonMemberReturn.getCode()))
						map.put(GMPMCODE, findPersonMemberReturn.getCode());
					else
						logger.debug("导购和客户的关联关系不存在!");
					/**
					 * 获取导购手机号
					 */
					if(map.get(GMNO)!=null && StringUtils.isNotEmpty(map.get(GMNO).toString())){
						FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
						findGuidMemberDto.setMerchantNo(currentMerchentNo);
						findGuidMemberDto.setMemberNo(map.get(GMNO).toString());
						//logger.debug("导购编号={}", map.get(GMNO).toString()); 
						GuidMemberReturnDto guidMemberReturnDto= guidMemberService.findGuid(findGuidMemberDto);
						if(guidMemberReturnDto!=null && StringUtils.isNotEmpty(guidMemberReturnDto.getMobile())){
							logger.debug("导购手机号={}", guidMemberReturnDto.getMobile()); 
							map.put(GMPHONE, guidMemberReturnDto.getMobile());
						}else{
							logger.debug("导购手机号为空"); 
							map.put(GMPHONE, "");
						}
					}
					returnList.add(map);
				}else{
					logger.warn("客户手机号为空，直接过滤，不返回={}",map); 
				}
			}
		}
		
		GeneralResponse returnGeneralResponse = GeneralResponse.generateResponse(true, "", "", returnList);
		logger.debug("getClientFollowInfo(String, String, String) - end - return value={}", returnGeneralResponse); //$NON-NLS-1$
		return returnGeneralResponse;
	}

}
