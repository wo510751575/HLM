/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller;

import java.util.HashMap;
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

import com.lj.base.core.util.AssertUtils;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.dto.AuthorizeDto;
import com.lj.business.api.utils.SignUtil;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.entity.dto.Maike51Dto;
import com.lj.oms.entity.dto.Maike51DtoReturn;
import com.lj.oms.service.Maike51HessianService;

/**
 * 
 * 类说明：oms token登录。
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author lhy
 *   
 * CreateDate: 2018年1月29日
 */
@Controller
public class MaikeAction {
	private static Logger logger = LoggerFactory.getLogger(MaikeAction.class);	
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Autowired
	private Maike51HessianService maike51HessianService;
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
	@RequestMapping(value = "/maike/login.do")
	public ModelAndView tokenLogin(String token, Model model){
		AssertUtils.notNullAndEmpty(token, "token不能为空");
		logger.info("tokenLogin(token={}) - start");
		String omsTokenLoginUrl=localCacheSystemParams.getSystemParam("api-os", "oms.token", "loginUrl");
		logger.info("omsTokenLoginUrl:"+omsTokenLoginUrl);
		return new ModelAndView(new RedirectView(omsTokenLoginUrl+"?token=" + token));
	}
	
	/**
	 * 
	 *
	 * 方法说明：同步账户信息
	 *
	 * @param authorizeDto
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年1月30日
	 *
	 */
	@RequestMapping(value = "/maike/acctAuthorize.do")
	@ResponseBody
	public GeneralResponse acctAuthorize(AuthorizeDto authorizeDto){
		logger.debug("acctAuthorize(AuthorizeDto authorizeDto={})",authorizeDto);
		AssertUtils.notNullAndEmpty(authorizeDto.getSign(), "签名不能为空");
		AssertUtils.notNullAndEmpty(authorizeDto.getPhone(), "手机号不能为空");
		AssertUtils.notNullAndEmpty(authorizeDto.getUserNo(), "用户编号不能为空");
		AssertUtils.notNullAndEmpty(authorizeDto.getNickname(), "昵称不能为空");
		AssertUtils.notNullAndEmpty(authorizeDto.getShopId(), "门店编号不能为空");
		//AssertUtils.notNullAndEmpty(authorizeDto.getTimeStamp(), "时间戳不能为空");
		try {
			/**
			 * 密钥
			 */
			String secret=localCacheSystemParams.getSystemParam("api-os", "maike51", "secret");
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("secret", secret);
			map.put("userNo", authorizeDto.getUserNo());
			map.put("phone", authorizeDto.getPhone());
			map.put("nickname", authorizeDto.getNickname());
			map.put("shopId", authorizeDto.getShopId());
			//map.put("timeStamp", authorizeDto.getTimeStamp());
			String sign = SignUtil.getSha1Sign(map);
			logger.debug("sign:"+sign);
			if (!sign.equals(authorizeDto.getSign())) {
				return GeneralResponse.generateResponse(Boolean.FALSE, Maike51DtoReturn.SECRET_ERROR_CODE, "签名错误!", "");
			}
			
			Maike51Dto maike51Dto = new Maike51Dto();
			maike51Dto.setNickname(authorizeDto.getNickname());
			maike51Dto.setPhone(authorizeDto.getPhone());
			maike51Dto.setShopId(authorizeDto.getShopId());
			maike51Dto.setUserNo(authorizeDto.getUserNo());
			Maike51DtoReturn maike51DtoReturn= maike51HessianService.createUserToKen(maike51Dto);
			return GeneralResponse.generateResponse(maike51DtoReturn.isResult(), maike51DtoReturn.getCode(),maike51DtoReturn.getMsg(), maike51DtoReturn.getData());
		} catch (Exception e) {
			logger.error("同步账户信息错误！！e={}",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
}

