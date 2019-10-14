/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ape.common.utils.StringUtils;
import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.bean.BeanMapConverter;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.utils.SignUtil;
import com.lj.business.cm.dto.activity.AddActivity;
import com.lj.business.cm.dto.activity.AddActivityReturn;
import com.lj.business.cm.dto.activity.DelActivity;
import com.lj.business.cm.dto.activity.FindActivity;
import com.lj.business.cm.dto.activity.FindActivityPage;
import com.lj.business.cm.dto.activity.FindActivityPageReturn;
import com.lj.business.cm.dto.activity.FindActivityReturn;
import com.lj.business.cm.dto.activity.UpdateActivity;
import com.lj.business.cm.dto.wordsInfo.AddWordsInfo;
import com.lj.business.cm.dto.wordsInfo.AddWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.DelWordsInfo;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfo;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPage;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPageReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.UpdateWordsInfo;
import com.lj.business.cm.dto.wordsType.AddWordsType;
import com.lj.business.cm.dto.wordsType.AddWordsTypeReturn;
import com.lj.business.cm.dto.wordsType.DelWordsType;
import com.lj.business.cm.dto.wordsType.FindWordsType;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;
import com.lj.business.cm.dto.wordsType.FindWordsTypePageReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypeReturn;
import com.lj.business.cm.dto.wordsType.UpdateWordsType;
import com.lj.business.cm.service.IActivityService;
import com.lj.business.cm.service.IWordsInfoService;
import com.lj.business.cm.service.IWordsTypeService;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.AddGuidMember;
import com.lj.business.member.dto.AddGuidMemberReturn;
import com.lj.business.member.dto.AddShop;
import com.lj.business.member.dto.AddShopReturn;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.ManagerMemberDto;
import com.lj.business.member.dto.ManagerMemberReturnDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.dto.UpdateManagerMember;
import com.lj.business.member.dto.UpdateMerchant;
import com.lj.business.member.dto.UpdateShop;
import com.lj.business.member.dto.guidCard.UpdateGuidCard;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.emus.ShopStatus;
import com.lj.business.member.emus.Status;
import com.lj.business.member.service.IGuidCardService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.contacts.ForcedLogoutMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.emus.AreaType;
import com.lj.oms.entity.dto.kuaitui.KuaiTuiDto;
import com.lj.oms.entity.dto.kuaitui.KuaiTuiDtoReturn;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.service.DictHessianService;
import com.lj.oms.service.KuaiTuiHessianService;
import com.lj.oms.utils.Validator;

/**
 * 快推对外接口
 * @author wo510
 *
 */
@Controller
@RequestMapping(value = "/kuaitui/")
public class KuaiTuiAction {
	private static Logger logger = LoggerFactory.getLogger(KuaiTuiAction.class);	
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Autowired
	private KuaiTuiHessianService kuaiTuiHessianService;
	@Autowired
	private DictHessianService dictHessianService;
	@Autowired
	private AreaHessianService areaHessianService;
	@Autowired
	private IGuidMemberService guidMemberService;
	@Autowired
	private IShopTerminalService shopTerminalService;
	@Autowired
	private IManagerMemberService managerMemberService;
	@Autowired
	private IGuidCardService guidCardService;
	@Autowired
	private IActivityService activityService;			//活动服务
	@Autowired
	private IWordsTypeService wordsTypeService;			//话术类型服务
	@Autowired
	private IWordsInfoService wordsInfoService;			//话术服务
//	@Autowired
//	private IImSensitiveWordsService imSensitiveWordsService;			//敏感词服务
	@Autowired 
	ICommonService commonService;

    /** * 系统信息服务. */
//    @Autowired 
//	private ISystemInfoService systemInfo;
    
//    @Autowired 
//	private RedisCache redisCache;
	/**
	 * 方法说明：oms token登录。
	 *
	 * @param token token 
	 * @param model
	 * @return
	 *
	 * @author lhy CreateDate: 2018年1月29日
	 *
	 */
	@RequestMapping(value = "/login.do")
	public ModelAndView tokenLogin(String token, Model model){
		AssertUtils.notNullAndEmpty(token, "token不能为空");
		logger.info("tokenLogin(token={}) - start");
		String omsTokenLoginUrl=localCacheSystemParams.getSystemParam("api-os", "oms.token", "loginUrl");
		logger.info("omsTokenLoginUrl:"+omsTokenLoginUrl);
		return new ModelAndView(new RedirectView(omsTokenLoginUrl+"?token=" + token));
	}
	
	/**
	 * 创建商户
	 * @param kuaiTuiDto
	 * @return
	 */
	@RequestMapping(value = "/acctAuthorize.do")
	@ResponseBody
	public GeneralResponse acctAuthorize(String timestamp,String paramContent,String sign){
		logger.debug("acctAuthorize(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			KuaiTuiDto kuaiTuiDto =(KuaiTuiDto) JsonUtils.objectFromJson(paramContent, KuaiTuiDto.class);
			
			@SuppressWarnings("unchecked")
			Map<String, String> map = BeanMapConverter.bean2map(kuaiTuiDto);
			map.put("timestamp", timestamp);
			
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			KuaiTuiDtoReturn kuaiTuiDtoReturn= kuaiTuiHessianService.createUserToKen(kuaiTuiDto);
			if(kuaiTuiDtoReturn.isResult()){
				return GeneralResponse.generateSuccessResponse(kuaiTuiDtoReturn.getData());  
			}
			return GeneralResponse.generateFailureResponse(kuaiTuiDtoReturn.getCode());
		} catch (Exception e) {
			logger.error("创建商户信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	
	/**
	 * 获取商户
	 * @param kuaiTuiDto
	 * @return
	 */
	@RequestMapping(value = "/getMerchant.do")
	@ResponseBody
	public GeneralResponse getMerchant(String timestamp,String paramContent,String sign){
		logger.debug("getMerchant(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			map.put("timestamp", timestamp);
			if (!map.containsKey("id") || StringUtils.isEmpty(map.get("id"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
            }
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			FindMerchantPage findMerchantPage = new FindMerchantPage();
			findMerchantPage.setMerchantNo(map.get("id"));
			KuaiTuiDtoReturn kuaiTuiDtoReturn= kuaiTuiHessianService.getMerchant(findMerchantPage);
			if(kuaiTuiDtoReturn.isResult()){
				return GeneralResponse.generateSuccessResponse(kuaiTuiDtoReturn.getData()); 
			}
			return GeneralResponse.generateFailureResponse(kuaiTuiDtoReturn.getCode());
		} catch (Exception e) {
			logger.error("创建商户信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	/**
	 * 修改商户状态
	 * @param timestamp
	 * @param paramContent
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/editMerchantStatus.do")
	@ResponseBody
	public GeneralResponse editMerchantStatus(String timestamp,String paramContent,String sign){
		logger.debug("editMerchantStatus(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			if(!map.containsKey("status") 
					|| StringUtils.isEmpty(map.get("status"))
					|| (!map.get("status").equals(Status.NORMAL.toString()) && !map.get("status").equals(Status.CANCEL.toString()))) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
			}
			if (!map.containsKey("id") || StringUtils.isEmpty(map.get("id"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			FindMerchantPage findMerchantPage = new FindMerchantPage();
			findMerchantPage.setMerchantNo(map.get("id"));
			KuaiTuiDtoReturn kuaiTuiDtoReturn= kuaiTuiHessianService.getMerchant(findMerchantPage);
			if(null ==kuaiTuiDtoReturn){
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.USER_EXIST_CODE, "商户不存在!", "");
			}
			
			UpdateMerchant updateMerchant = new UpdateMerchant();
			updateMerchant.setMerchantNo(map.get("id"));
			updateMerchant.setStatus(Status.valueOf(map.get("status")));
			kuaiTuiDtoReturn= kuaiTuiHessianService.editMerchantStatus(updateMerchant);
			if(kuaiTuiDtoReturn.isResult()){
				return GeneralResponse.generateSuccessResponse(); 
			}
			return GeneralResponse.generateFailureResponse(kuaiTuiDtoReturn.getCode());
		} catch (Exception e) {
			logger.error("创建商户信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	/**
	 * 3.5获取门店区域选项接口
	 * @param timestamp
	 * @param paramContent
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/getAreas.do")
	@ResponseBody
	public GeneralResponse getAreas(){
		try {
			List<Dict> list = dictHessianService.findListByType(Dict.ERP_DICT_1);
			return GeneralResponse.generateSuccessResponse(list); 
		} catch (Exception e) {
			logger.error("创建商户信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	/**
	 * 3.6获取地区选项接口
	 * @param timestamp
	 * @param paramContent
	 * @param sign
	 * @return
	 */
	@RequestMapping(value = "/getCityAreas.do")
	@ResponseBody
	public GeneralResponse getCityAreas(){
		try {
			List<Area> list = areaHessianService.findAllList();
			return GeneralResponse.generateSuccessResponse(list); 
		} catch (Exception e) {
			logger.error("创建商户信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	
	
	
	
	
	/**
	 * 3.10添加店员接口
	 * @return
	 */
	@RequestMapping(value = "/addGuid.do")
	@ResponseBody
	public GeneralResponse addGuid(String timestamp,String paramContent,String sign){
		logger.debug("addGuid(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			
			if(!map.containsKey("status") || StringUtils.isEmpty(map.get("status"))) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "店员状态错误!", "");
			}
			boolean flag = true;
			for (MemberStatus status : MemberStatus.values()) {
				if(status.toString().equals(map.get("status"))) {
					flag =false;
					break;
				}
			}
			if(flag){
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "店员状态错误!", "");
			}
			
			/* 校验手机号格式 */
			if(!map.containsKey("mobile") || !Validator.isMobile(map.get("mobile"))) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "手机格式错误!", "");
			}
			
			/*校验导购手机号唯一性*/
			if(CommonConstant.FALSE.equals(checkLoginName("", map.get("mobile")))) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "手机号已存在!", "");
			}
			
			/* 校验门店编号 */
			if(!map.containsKey("shopNo") || StringUtils.isEmpty(map.get("shopNo"))) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "门店编号参数错误!", "");
			}
			
			/* 校验商户编号 */
			if(!map.containsKey("merchantNo") || StringUtils.isEmpty(map.get("merchantNo"))) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "商户编号参数错误!", "");
			}
			/* 校验微信号 	快推要求，后续手工录入*/
//			if(!map.containsKey("noWx") || StringUtils.isEmpty(map.get("noWx"))) {
//				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "微信帐号参数错误!", "");
//			}
			/* 校验区域 */
			if(!map.containsKey("areaCode") || StringUtils.isEmpty(map.get("areaCode"))) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "门店区域参数错误!", "");
			}
			/* 校验年龄 */
			if(!map.containsKey("age") || StringUtils.isEmpty(map.get("age")) ||!StringUtils.isNumeric(map.get("age"))) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "年龄参数错误!", "");
			}
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}

			AddGuidMember addGuidMember =(AddGuidMember)JsonUtils.objectFromJson(paramContent, AddGuidMember.class);
			
			// 默认手机后6位
			if(StringUtils.isEmpty(addGuidMember.getPwd())) {
				String pwd = addGuidMember.getMobile().substring(addGuidMember.getMobile().length() - 6);
				addGuidMember.setPwd(MD5.encryptByMD5(pwd));
			}

		

			addGuidMember.setAreaName(dictHessianService.getDictLabel(Dict.ERP_DICT_1, addGuidMember.getAreaCode()));
	

			// 根据工作微信号填入门店终端二维码
			if (StringUtils.isNotBlank(addGuidMember.getNoWx())) {
			    FindShopTerminal findShopTerminal = new FindShopTerminal();
                findShopTerminal.setNoWx(addGuidMember.getNoWx());
                List<FindShopTerminalReturn> sts = shopTerminalService.findShopTerminalSelect(findShopTerminal);
                if (sts != null && sts.size() > 0) {
                    addGuidMember.setQcord(StringUtils.isNotBlank(sts.get(0).getQcord()) ? sts.get(0).getQcord() : "");
                }
            }
//					try {
//						addGuidMember.setWorkDate(DateUtils.parseDate(addGuidMember.getWorkDate(), com.lj.base.core.util.DateUtils.PATTERN_yyyy_MM_dd));
//					} catch (ParseException pe) {
//	                    return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "日期格式错误", "");
//					}
			addGuidMember.setMemberNo(GUID.getPreUUID());
			addGuidMember.setCreateId("api-os");
			addGuidMember.setUpdateId("api-os");
					
			AddGuidMemberReturn addGuidMemberReturn=  guidMemberService.addGuidMember(addGuidMember);
			Map<String, String> map2 = new HashMap<>();
			map2.put("code", addGuidMemberReturn.getCode());
			return GeneralResponse.generateSuccessResponse(map2); 
		} catch (Exception e) {
			logger.error("创建店员信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	
	
	/**
	 * 3.11修改店员接口
	 * @return
	 */
	@RequestMapping(value = "/editGuid.do")
	@ResponseBody
	public GeneralResponse editGuid(String timestamp,String paramContent,String sign){
		logger.debug("editGuid(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}

			UpdateGuidMember updateGuidMember =(UpdateGuidMember)JsonUtils.objectFromJson(paramContent, UpdateGuidMember.class);
			/* 校验手机号格式 */
			if(StringUtils.isNotEmpty(updateGuidMember.getMobile()) && !Validator.isMobile(updateGuidMember.getMobile())) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "手机格式错误!", "");
			}
			
			if(StringUtils.isNotEmpty(updateGuidMember.getStatus())) {
				boolean flag = true;
				for (MemberStatus status : MemberStatus.values()) {
					if(status.toString().equals(map.get("status"))) {
						flag =false;
						break;
					}
				}
				if(flag){
					return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "店员状态错误!", "");
				}
			}

			if(StringUtils.isNotEmpty(updateGuidMember.getAreaCode())) {
				updateGuidMember.setAreaName(dictHessianService.getDictLabel(Dict.ERP_DICT_1, updateGuidMember.getAreaCode()));
			}
			
			/**
			 * 获取原有导购信息
			 */
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setCode(updateGuidMember.getCode());
			FindGuidMemberReturn guidMemberReturnDto= guidMemberService.findGuidMember(findGuidMember);
			
			
			/*校验初始化密码时，手机号是否传入*/
			if(StringUtils.isNotBlank(updateGuidMember.getInitial()) && updateGuidMember.getInitial().equals("Yes")){
				if(StringUtils.isEmpty(updateGuidMember.getMobile())){
					return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "密码初始化，必须传入手机号!", "");
				}
			}
			
			/*校验导购手机号唯一性*/
			if(StringUtils.isNotEmpty(updateGuidMember.getMobile()) && CommonConstant.FALSE.equals(checkLoginName(guidMemberReturnDto.getMobile(), updateGuidMember.getMobile()))) {
					return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "手机号已存在!", "");
			}
			
			
		

			// 根据工作微信号填入门店终端二维码
			if (StringUtils.isNotBlank(updateGuidMember.getNoWx())) {
			    FindShopTerminal findShopTerminal = new FindShopTerminal();
                findShopTerminal.setNoWx(updateGuidMember.getNoWx());
                List<FindShopTerminalReturn> sts = shopTerminalService.findShopTerminalSelect(findShopTerminal);
                if (sts != null && sts.size() > 0) {
                	updateGuidMember.setQcord(StringUtils.isNotBlank(sts.get(0).getQcord()) ? sts.get(0).getQcord() : "");
                }
            }
//					try {
//						addGuidMember.setWorkDate(DateUtils.parseDate(addGuidMember.getWorkDate(), com.lj.base.core.util.DateUtils.PATTERN_yyyy_MM_dd));
//					} catch (ParseException pe) {
//	                    return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "日期格式错误", "");
//					}
			
			//判断是否有店长身份
			ManagerMemberDto record = new ManagerMemberDto();
			record.setMobile(guidMemberReturnDto.getMobile());	
			ManagerMemberReturnDto managerMemberReturnDto = managerMemberService.findManagerMemberByMobile(record);
			
			//离职清空登录token
//			if(MemberStatus.LEAVE.toString().equals(updateGuidMember.getStatus())){
//				List<Dict> dict =dictService.getList(UserUtils.LOGIN_TOKEN_TYPE);
//				for(Dict dictList :dict){
//					tokenService.removeToken(updateGuidMember.getMemberNo(),dictList.getValue());	
//					logger.info("已删除{}对应token", updateGuidMember.getMemberNo());
//				}
//			}
			
			//离职
		    if(StringUtils.isNotEmpty(updateGuidMember.getStatus()) && MemberStatus.LEAVE.toString().equals(updateGuidMember.getStatus())){
		    	//强制退出APP登录状态
		    	try {
		    		if(StringUtils.isNotBlank(updateGuidMember.getMemberNo())){
		    			sendForcedLogout(updateGuidMember);
		    		}
				} catch (Exception e) {
					logger.error("强制退出APP错误 e={}",e);
				}
		    	
		    	
		    	
		    }else if(MemberStatus.FREEZE.toString().equals(updateGuidMember.getStatus())){
		    	//强制退出APP登录状态
		    	try {
		    		if(StringUtils.isNotBlank(updateGuidMember.getMemberNo())){
		    			sendForcedLogout(updateGuidMember);
		    		}
				} catch (Exception e) {
					logger.error("强制退出APP错误 e={}",e);
				}
		    }
		    
			//同步更新店长信息    中控版本1.01
			if(managerMemberReturnDto !=null){
				UpdateManagerMember updateManagerMember = new UpdateManagerMember();
				updateManagerMember.setCode(managerMemberReturnDto.getCode());
				updateManagerMember.setMemberNo(managerMemberReturnDto.getMemberNo());
				updateManagerMember.setMobile(updateGuidMember.getMobile());
				updateManagerMember.setNoWx(updateGuidMember.getNoWx());
				updateManagerMember.setAge(updateGuidMember.getAge());
				updateManagerMember.setImei(updateGuidMember.getImei());
	
				updateManagerMember.setStatus(updateGuidMember.getStatus());
				updateManagerMember.setWorkDate(updateGuidMember.getWorkDate());
				updateManagerMember.setAreaCode(updateGuidMember.getAreaCode());
				updateManagerMember.setAreaName(updateGuidMember.getAreaName());
				updateManagerMember.setSex(updateGuidMember.getGender());
				updateManagerMember.setMemberName(updateGuidMember.getMemberName());
				managerMemberService.updateManagerMember(updateManagerMember);
				updateGuidMember.setManagerCode(managerMemberReturnDto.getCode());
			}
			
			//更新个人名片
			if(StringUtils.isNotEmpty(updateGuidMember.getStatus())&& !MemberStatus.LEAVE.toString().equals(updateGuidMember.getStatus())){
				UpdateGuidCard updateGuidCard=new UpdateGuidCard();
				updateGuidCard.setMobile(updateGuidMember.getMobile());
				updateGuidCard.setMemberNameGm(updateGuidMember.getMemberName());
				updateGuidCard.setAge(updateGuidMember.getAge());

				updateGuidCard.setGender(updateGuidMember.getGender());
				updateGuidCard.setQcord(updateGuidMember.getQcord());
				updateGuidCard.setMemberNoGm(guidMemberReturnDto.getMemberNo());
				guidCardService.updateGuidCardByShop(updateGuidCard);
			}
            guidMemberService.updateGuidMember(updateGuidMember);
			return GeneralResponse.generateSuccessResponse(); 
		} catch (Exception e) {
			logger.error("修改店员信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	
	
	
	
	/**
	 * 3.12查询店员接口
	 * @return
	 */
	@RequestMapping(value = "/getGuid.do")
	@ResponseBody
	public GeneralResponse getGuid(String timestamp,String paramContent,String sign){
		logger.debug("getGuid(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			
			if ((!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) && (!map.containsKey("mobile") || StringUtils.isEmpty(map.get("mobile")))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "编号和手机号不能同时为空!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}

			
				FindGuidMember findGuidMember = new FindGuidMember();
				if(StringUtils.isNotBlank(map.get("code"))){
					findGuidMember.setCode(map.get("code"));
				}else{
					findGuidMember.setMobile(map.get("mobile"));
				}
				FindGuidMemberReturn guidMemberReturnDto= guidMemberService.findGuidMember(findGuidMember);
				if(null == guidMemberReturnDto) {
					return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.USER_EXIST_CODE, "导购不存在!", "");
				}
			return GeneralResponse.generateSuccessResponse(guidMemberReturnDto); 
		} catch (Exception e) {
			logger.error("查询店员信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	/**
	 * 3.13添加活动接口
	 * @return
	 */
	@RequestMapping(value = "/addActivity.do")
	@ResponseBody
	public GeneralResponse addActivity(String timestamp,String paramContent,String sign){
		logger.debug("addActivity(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			AddActivity addActivity =(AddActivity)JsonUtils.objectFromJson(paramContent, AddActivity.class);
			AddActivityReturn activityReturn= activityService.addActivity(addActivity);
			return GeneralResponse.generateSuccessResponse(activityReturn); 
		} catch (Exception e) {
			logger.error("添加活动信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	/**
	 * 3.14修改活动接口
	 * @return
	 */
	@RequestMapping(value = "/editActivity.do")
	@ResponseBody
	public GeneralResponse editActivity(String timestamp,String paramContent,String sign){
		logger.debug("editActivity(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			FindActivity findActivity = new FindActivity();
			findActivity.setCode(map.get("code"));
			FindActivityReturn findActivityReturn= activityService.findActivity(findActivity);
			if(null == findActivityReturn){
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.USER_EXIST_CODE, "活动不存在!", "");
			}
			UpdateActivity updateActivity =(UpdateActivity)JsonUtils.objectFromJson(paramContent, UpdateActivity.class);
			
			activityService.updateActivity(updateActivity);
			return GeneralResponse.generateSuccessResponse(); 
		} catch (Exception e) {
			logger.error("修改活动信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	/**
	 * 3.15查询活动接口
	 * @return
	 */
	@RequestMapping(value = "/getActivity.do")
	@ResponseBody
	public GeneralResponse getActivity(String timestamp,String paramContent,String sign){
		logger.debug("getActivity(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "code不能为空!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			FindActivity findActivity = new FindActivity();
			findActivity.setCode(map.get("code"));
			FindActivityReturn findActivityReturn= activityService.findActivity(findActivity);
			return GeneralResponse.generateSuccessResponse(findActivityReturn); 
		} catch (Exception e) {
			logger.error("查询活动信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	/**
	 * 3.16查询活动列表接口
	 * @return
	 */
	@RequestMapping(value = "/findActivitys.do")
	@ResponseBody
	public GeneralResponse findActivitys(String timestamp,String paramContent,String sign){
		logger.debug("findActivitys(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("merchantNo") || StringUtils.isEmpty(map.get("merchantNo"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "商户编号不能为空!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			FindActivityPage findActivityPage = new FindActivityPage();
			findActivityPage.setMerchantNo(map.get("merchantNo"));
			Integer pageNo = (map.containsKey("pageNo") && StringUtils.isNotBlank(map.get("pageNo"))&& StringUtils.isNumeric(map.get("pageNo")))? Integer.parseInt(map.get("pageNo")):1;
			Integer pageSize = (map.containsKey("pageSize") && StringUtils.isNotBlank(map.get("pageSize"))&& StringUtils.isNumeric(map.get("pageSize")))? Integer.parseInt(map.get("pageSize")):10;
			if(null !=pageNo){
				findActivityPage.setStart((pageNo-1)*pageSize);
			}
			if(null !=pageSize){
				findActivityPage.setLimit(pageSize);
			}
			Page<FindActivityPageReturn> list= activityService.findActivityPage(findActivityPage);
			return GeneralResponse.generateSuccessResponse(list); 
		} catch (Exception e) {
			logger.error("查询活动列表信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	/**
	 * 3.18删除活动接口
	 * @return
	 */
	@RequestMapping(value = "/delActivity.do")
	@ResponseBody
	public GeneralResponse delActivity(String timestamp,String paramContent,String sign){
		logger.debug("delActivity(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			FindActivity findActivity = new FindActivity();
			findActivity.setCode(map.get("code"));
			FindActivityReturn findActivityReturn= activityService.findActivity(findActivity);
			if(null == findActivityReturn){
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.USER_EXIST_CODE, "活动不存在!", "");
			}
			
			DelActivity delActivity = new DelActivity();
			delActivity.setCode(map.get("code"));
			activityService.delActivity(delActivity);
			return GeneralResponse.generateSuccessResponse(); 
		} catch (Exception e) {
			logger.error("删除活动信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	
	/**
	 * 3.19添加话术类型接口
	 * @return
	 */
	@RequestMapping(value = "/addWordsType.do")
	@ResponseBody
	public GeneralResponse addWordsType(String timestamp,String paramContent,String sign){
		logger.debug("addWordsType(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			AddWordsType addWordsType =(AddWordsType)JsonUtils.objectFromJson(paramContent, AddWordsType.class);
			AddWordsTypeReturn addWordsTypeReturn= wordsTypeService.addWordsType(addWordsType);
			return GeneralResponse.generateSuccessResponse(addWordsTypeReturn); 
		} catch (Exception e) {
			logger.error("添加话术类型信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	/**
	 * 3.20修改话术类型接口
	 * @return
	 */
	@RequestMapping(value = "/editWordsType.do")
	@ResponseBody
	public GeneralResponse editWordsType(String timestamp,String paramContent,String sign){
		logger.debug("editWordsType(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			
			UpdateWordsType updateWordsType = new UpdateWordsType();
			FindWordsTypeReturn findWordsTypeReturn = null;
			
			updateWordsType.setCode(map.get("code"));
			if(StringUtils.isNotEmpty(map.get("typeName"))) {
				updateWordsType.setTypeName(map.get("typeName"));
				/**
				 * 获取原来的typeName
				 */
				FindWordsType findWordsType=new FindWordsType();
				findWordsType.setCode(updateWordsType.getCode());
				findWordsTypeReturn = wordsTypeService.findWordsType(findWordsType);
			}
			
			if(StringUtils.isNotEmpty(map.get("seq"))) {
				updateWordsType.setSeq(Integer.parseInt(map.get("seq")));
			}
			
			
			wordsTypeService.updateWordsType(updateWordsType, findWordsTypeReturn);
			return GeneralResponse.generateSuccessResponse(); 
		} catch (Exception e) {
			logger.error("修改话术类型信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	/**
	 * 3.21查询话术类型接口
	 * @return
	 */
	@RequestMapping(value = "/getWordsType.do")
	@ResponseBody
	public GeneralResponse getWordsType(String timestamp,String paramContent,String sign){
		logger.debug("getWordsType(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "code不能为空!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			FindWordsType findWordsType=new FindWordsType();
			findWordsType.setCode(map.get("code"));
			FindWordsTypeReturn findWordsTypeReturn = wordsTypeService.findWordsType(findWordsType);
			return GeneralResponse.generateSuccessResponse(findWordsTypeReturn); 
		} catch (Exception e) {
			logger.error("查询话术类型信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	/**
	 * 3.22查询话术类型列表接口
	 * @return
	 */
	@RequestMapping(value = "/findWordsTypes.do")
	@ResponseBody
	public GeneralResponse findWordsTypes(String timestamp,String paramContent,String sign){
		logger.debug("findWordsTypes(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("merchantNo") || StringUtils.isEmpty(map.get("merchantNo"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "商户编号不能为空!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			FindWordsTypePage findWordsTypePage = new FindWordsTypePage();
			findWordsTypePage.setMerchantNo(map.get("merchantNo"));
			findWordsTypePage.setLimit(10000);
			Page<FindWordsTypePageReturn> list= wordsTypeService.findWordsTypePage(findWordsTypePage);
			return GeneralResponse.generateSuccessResponse(list.getRows()); 
		} catch (Exception e) {
			logger.error("查询话术类型列表信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	/**
	 * 3.23删除话术类型接口
	 * @return
	 */
	@RequestMapping(value = "/delWordsType.do")
	@ResponseBody
	public GeneralResponse delWordsType(String timestamp,String paramContent,String sign){
		logger.debug("delWordsType(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			DelWordsType delWordsType = new DelWordsType();
			delWordsType.setCode(map.get("code"));
			wordsTypeService.delWordsType(delWordsType);
			return GeneralResponse.generateSuccessResponse(); 
		} catch (Exception e) {
			logger.error("删除话术类型信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	
	
	/**
	 * 3.24添加话术接口
	 * @return
	 */
	@RequestMapping(value = "/addWords.do")
	@ResponseBody
	public GeneralResponse addWords(String timestamp,String paramContent,String sign){
		logger.debug("addWords(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("merchantNo") || StringUtils.isEmpty(map.get("merchantNo"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "商户编号不能为空!", "");
            }
			if (!map.containsKey("typeCode") || StringUtils.isEmpty(map.get("typeCode"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "类型CODE不能为空!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			AddWordsInfo addWordsInfo = (AddWordsInfo)JsonUtils.objectFromJson(paramContent, AddWordsInfo.class);
			
			int i=addWordsInfo.getContent().getBytes("utf-8").length;
			if(i>300){
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "当前话术内容超出100个字符数,请缩小内容！", "");
			}
			
//			if(imSensitiveWordsService.contains(addWordsInfo.getContent())){
//				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "当前话术内容含有敏感词无法保存", "");
//			}
			
			//如果默认话术数量大于等于四条,且本次新增话术为默认状态
			Integer count=wordsInfoService.findDefaultCount(addWordsInfo.getMerchantNo());
			if(addWordsInfo.getDefaultFlag()== CommonConstant.I_YES && count!=null && count>=4){
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "默认话术最多设置4条！", "");
			}
			//HTML特殊字符转义
			AddWordsInfoReturn addWordsInfoReturn=wordsInfoService.addWordsInfo(addWordsInfo);
			return GeneralResponse.generateSuccessResponse(addWordsInfoReturn); 
		} catch (Exception e) {
			logger.error("添加话术信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	/**
	 * 3.25修改话术接口
	 * @return
	 */
	@RequestMapping(value = "/editWords.do")
	@ResponseBody
	public GeneralResponse editWords(String timestamp,String paramContent,String sign){
		logger.debug("editWords(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			UpdateWordsInfo updateWordsInfo = (UpdateWordsInfo)JsonUtils.objectFromJson(paramContent, UpdateWordsInfo.class);
			
//			if(imSensitiveWordsService.contains(updateWordsInfo.getContent())){
//				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "当前话术内容含有敏感词无法保存!", "");
//			}
			
			if(null != updateWordsInfo.getDefaultFlag()) {
				//当非默认变为默认才校验数量
				FindWordsInfo findWordsInfo = new FindWordsInfo();
				findWordsInfo.setCode(updateWordsInfo.getCode());
				FindWordsInfoReturn findWordsInfoReturn = wordsInfoService.findWordsInfo(findWordsInfo);
				if(findWordsInfoReturn.getDefaultFlag()==CommonConstant.I_NO && updateWordsInfo.getDefaultFlag()==CommonConstant.I_YES){
					Integer count=wordsInfoService.findDefaultCount(findWordsInfoReturn.getMerchantNo());
					if(count!=null && count>=4){
						return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "默认话术最多设置4条！", "");
					}
				}
			}
			if(StringUtils.isNotEmpty(updateWordsInfo.getContent())) {
				if(updateWordsInfo.getContent().getBytes("utf-8").length>300){
					return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "当前话术内容超出100个字符数,请缩小内容！", "");
				}
			}
			
			wordsInfoService.updateWordsInfo(updateWordsInfo);
			return GeneralResponse.generateSuccessResponse(); 
		} catch (Exception e) {
			logger.error("修改话术信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	/**
	 * 3.26查询话术接口
	 * @return
	 */
	@RequestMapping(value = "/getWords.do")
	@ResponseBody
	public GeneralResponse getWords(String timestamp,String paramContent,String sign){
		logger.debug("getWords(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "code不能为空!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			FindWordsInfo findWordsInfo=new FindWordsInfo();
			findWordsInfo.setCode(map.get("code"));
			FindWordsInfoReturn findWordsInfoReturn = wordsInfoService.findWordsInfo(findWordsInfo);
			return GeneralResponse.generateSuccessResponse(findWordsInfoReturn); 
		} catch (Exception e) {
			logger.error("查询话术信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	/**
	 * 3.27查询话术列表接口
	 * @return
	 */
	@RequestMapping(value = "/findWordss.do")
	@ResponseBody
	public GeneralResponse findWordss(String timestamp,String paramContent,String sign){
		logger.debug("findWordss(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("merchantNo") || StringUtils.isEmpty(map.get("merchantNo"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "商户编号不能为空!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			FindWordsInfoPage findWordsInfoPage = new FindWordsInfoPage();
			findWordsInfoPage.setMerchantNo(map.get("merchantNo"));
			findWordsInfoPage.setTypeCode(map.get("typeCode"));
			
			Integer pageNo = (map.containsKey("pageNo") && StringUtils.isNotBlank(map.get("pageNo"))&& StringUtils.isNumeric(map.get("pageNo")))? Integer.parseInt(map.get("pageNo")):1;
			Integer pageSize = (map.containsKey("pageSize") && StringUtils.isNotBlank(map.get("pageSize"))&& StringUtils.isNumeric(map.get("pageSize")))? Integer.parseInt(map.get("pageSize")):10;
			if(null !=pageNo){
				findWordsInfoPage.setStart((pageNo-1)*pageSize);
			}
			if(null !=pageSize){
				findWordsInfoPage.setLimit(pageSize);
			}
			Page<FindWordsInfoPageReturn> list= wordsInfoService.findWordsInfoPage(findWordsInfoPage);
			return GeneralResponse.generateSuccessResponse(list); 
		} catch (Exception e) {
			logger.error("查询话术列表信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	/**
	 * 3.28删除话术接口
	 * @return
	 */
	@RequestMapping(value = "/delWords.do")
	@ResponseBody
	public GeneralResponse delWords(String timestamp,String paramContent,String sign){
		logger.debug("delWords(String timestamp={},String paramContent={},String sign={})",timestamp,paramContent,sign);
		checkParam(timestamp, paramContent, sign);
		try {
			
			Map<String, String> map = JsonUtils.mapFromJson(paramContent);
			for (Map.Entry<String, String> entry : map.entrySet()) {
	            if (StringUtils.isEmpty(entry.getValue())) {
	            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
	            }
	        }
			if (!map.containsKey("code") || StringUtils.isEmpty(map.get("code"))) {
            	return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.PARAM_ERROR_CODE, "参数错误!", "");
            }
			
			map.put("timestamp", timestamp);
			String getSign = getSign(map);
			logger.debug("getSign:"+getSign);
			
			if (!sign.equals(getSign)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, KuaiTuiDtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			DelWordsInfo delWordsInfo = new DelWordsInfo();
			delWordsInfo.setCode(map.get("code"));
			wordsInfoService.delWordsInfo(delWordsInfo);
			return GeneralResponse.generateSuccessResponse(); 
		} catch (Exception e) {
			logger.error("删除话术信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 强制APP退出登陆
	 * @param updateGuidMember
	 */
	private void sendForcedLogout(UpdateGuidMember updateGuidMember) {
		ForcedLogoutMessage forcedLogoutMessage = new ForcedLogoutMessage();
		forcedLogoutMessage.setMemberNoGm(updateGuidMember.getMemberNo());
		forcedLogoutMessage.setMessage("您的操作权限不足，即将退出登录！");
		
		IContactsService  basic = commonService.getHessianContactsService(forcedLogoutMessage.getMemberNoGm());
		basic.sendForcedLogoutMessage(forcedLogoutMessage);
	}
	

	
	
	/**
	 * 检查导购手机唯一性
	 * @param oldMobile
	 * @param mobile
	 * @return
	 */
	private String checkLoginName(String oldMobile, String mobile) {
		try {
			if (mobile !=null && mobile.equals(oldMobile)) {
				return CommonConstant.TRUE;
			}     
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMobile(mobile);
			List<FindGuidMemberPageReturn> guidMemberPages= guidMemberService.findGuidMembers(findGuidMemberPage);
			if(guidMemberPages !=null && guidMemberPages.size()==0){
				return CommonConstant.TRUE;
			}
			return CommonConstant.FALSE;
		}catch (Exception e) {
			logger.error("检查导购手机信息失败！", e);
			return CommonConstant.TRUE;
		}
	}
	
	
	/**
	 * 参数校验
	 * @param timestamp
	 * @param paramContent
	 * @param sign
	 */
	private void checkParam(String timestamp, String paramContent, String sign) {
		AssertUtils.notNullAndEmpty(timestamp, "时间戳不能为空");
		AssertUtils.notNullAndEmpty(paramContent, "业务参数不能为空");
		AssertUtils.notNullAndEmpty(sign, "签名不能为空");
	}
	
	/**
	 * 获取签名
	 * @param map
	 * @return
	 */
	private String getSign(Map<String, String> map) {
		/**
		 * 密钥
		 */
		String secret=localCacheSystemParams.getSystemParam("api-os", KuaiTuiDto.ROLE_KEY, "secret");
		logger.debug("获取密钥secret：{}",secret);
		map.put("secret", secret);
		if(map.containsKey("class")){
			map.remove("class");
		}
		logger.debug("获取签名map:{}",map);
		String sign = SignUtil.getSha1Sign(map);
		return sign;
	}
}

