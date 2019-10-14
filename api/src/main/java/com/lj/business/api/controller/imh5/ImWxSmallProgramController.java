/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.imh5;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.StringUtils;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.emus.PmTypeTimeFlag;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPageReturn;
import com.lj.business.weixin.dto.smallprogram.DelWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPage;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPageReturn;
import com.lj.business.weixin.dto.smallprogram.UpdateWxSmallProgram;
import com.lj.business.weixin.service.IWxSmallProgramService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;

import com.lj.oms.utils.UserUtils;

/**
 * 
 * 类说明：微信小程序
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月3日
 */
@Controller
@RequestMapping(value = "/imh5/smallprogram/")
public class ImWxSmallProgramController {

    private static final Logger logger = LoggerFactory.getLogger(ImWxSmallProgramController.class);
    
//    private static final String LIST = "modules/im/smallprogramList";
	
	@Resource
	private IWxSmallProgramService wxSmallProgramService;
	
	@Resource
	private IPmTypeService pmTypeService; // 客户分类服务
	
	@Resource
	private IGmAssistantShopService gmAssistantShopService;
	
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	
	@Resource
	private IShopTerminalService shopTerminalService;
	
	@Autowired
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	@Resource
	private IMerchantParamsService merchantParamsService;

	/**
	 * 
	 *
	 * 方法说明：分页查询
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月3日
	 *
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> list( FindWxSmallProgramPage findWxSmallProgramPage,String paramJson) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			Map maps = (Map)JSON.parse(paramJson); 
			String merchantNo = maps.get("merchantNo") == null ? null : maps.get("merchantNo").toString();
			String paName = maps.get("paName") == null ? null : maps.get("paName").toString();
			Integer pageNo =maps.get("pageNo") == null ? 1 : Integer.valueOf(maps.get("pageNo").toString());
			Integer pageSize = maps.get("pageSize") == null ? 10 : Integer.valueOf(maps.get("pageSize").toString());
			String status = maps.get("status") == null ? null : maps.get("status").toString();
			if(status != null && !status.equals("")) {
				findWxSmallProgramPage.setStatus(Integer.valueOf(status));
				}
			if (pageNo != null) {
				findWxSmallProgramPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findWxSmallProgramPage.setLimit(pageSize);
			}
			findWxSmallProgramPage.setSpName(paName);
			findWxSmallProgramPage.setMerchantNo(merchantNo);

			Page<FindWxSmallProgramPageReturn> pages = wxSmallProgramService.findWxSmallProgramPage(findWxSmallProgramPage);
		
			Page<FindWxSmallProgramPageReturn> page =
					new Page<FindWxSmallProgramPageReturn>(new ArrayList<>(pages.getRows()), pages.getTotal(),pages.getStart(),pages.getLimit() );


			dataMap.put("page", page);
			
			// 小程序分类
			FindMerchantParams findMerchantParams = new FindMerchantParams();
			findMerchantParams.setGroupName("SMALL_PROGRAM_TYPE");
			findMerchantParams.setMerchantNo(findWxSmallProgramPage.getMerchantNo());
			Map<String, String> paramsMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);
			if(paramsMap == null) {
				paramsMap = new HashMap<>();
			}
			if(!paramsMap.containsKey("UNKNOWN")) {
				paramsMap.put("UNKNOWN", "未分类");
			}
			dataMap.put("smallProgramTypes", paramsMap);
	
			map.put("result", true);
		} catch (Exception e) {
//			addMessage(redirectAttributes, "加载数据失败,系统出现异常！");
			logger.error(e.getMessage(), e);
			map.put("result", false);
			map.put("message", false);
		}
		map.put("dataMap",dataMap);
//		return LIST;
		return map;
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改小程序
	 *
	 * @param updateWxSmallProgram
 	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月5日
	 *
	 */
	@RequestMapping(value="edit")
	@ResponseBody
    public Map<String, Object> edit(UpdateWxSmallProgram updateWxSmallProgram,String paramJson){
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			
			Map maps = (Map)JSON.parse(paramJson); 
			String code = maps.get("code") == null ? null : maps.get("code").toString();
			Integer status = maps.get("status") == null ? null : Integer.valueOf(maps.get("status").toString());
			
			updateWxSmallProgram.setCode(code);
			updateWxSmallProgram.setStatus(status);
			if(StringUtils.isNotEmpty(updateWxSmallProgram.getSpUrl()) && !StringUtils.isHttp(updateWxSmallProgram.getSpUrl())) {
				String uploadUrl = localCacheSystemParams.getSystemParam(SystemAliasName.weixin.name(), "imfile", "uploadUrl");
				updateWxSmallProgram.setSpUrl(uploadUrl + updateWxSmallProgram.getSpUrl());	// 使用全路径
			}
			wxSmallProgramService.updateWxSmallProgram(updateWxSmallProgram);
			map.put("result", true);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			map.put("result", false);
			map.put("message","编辑数据失败");
		}
		return map;
	}
	
	/**
	 * 方法说明:删除小程序
	 * @param code
	 * @return
	 */
	@RequestMapping(value="delete.do")
	@ResponseBody
	public GeneralResponse delete(String code){
		try {
			DelWxSmallProgram delWxSmallProgram = new DelWxSmallProgram();
			delWxSmallProgram.setCode(code);
			wxSmallProgramService.delWxSmallProgram(delWxSmallProgram);
		} catch (Exception e) {
			logger.error("删除小程序出错!", e);
			return GeneralResponse.generateFailureResponse();
		}
		return GeneralResponse.generateSuccessResponse();
	}
	

	

}
