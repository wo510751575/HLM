/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.imh5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lj.base.core.pagination.Page;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dto.publicaccount.DelWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPage;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPageReturn;
import com.lj.business.weixin.dto.publicaccount.UpdateWxPublicAccount;
import com.lj.business.weixin.service.IWxPublicAccountService;

/**
 * 
 * 类说明：微信公众号
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
@RequestMapping(value = "/imh5/publicaccount/")
public class ImWxPublicAccountController {

    private static final Logger logger = LoggerFactory.getLogger(ImWxPublicAccountController.class);
    
//    private static final String LIST = "modules/im/publicaccountList";
	
	@Resource
	private IWxPublicAccountService wxPublicAccountService;
	
	@Resource
	private IPmTypeService pmTypeService; // 客户分类服务
	
	@Resource
	private IGmAssistantShopService gmAssistantShopService;
	
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	
	@Resource
	private IShopTerminalService shopTerminalService;

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
	public Map<String, Object> list( FindWxPublicAccountPage findWxPublicAccountPage,String paramJson) {
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			
			Map maps = (Map)JSON.parse(paramJson); 
			String merchantNo = maps.get("merchantNo") == null ? null : maps.get("merchantNo").toString();
			String paName = maps.get("paName") == null ? null : maps.get("paName").toString();
			String status = maps.get("status") == null ? null : maps.get("status").toString();
			String noWxShop = maps.get("noWxShop") == null ? null : maps.get("noWxShop").toString();
			String startTime =maps.get("startTime") == null ? null : maps.get("startTime").toString();
			String endTime = maps.get("endTime") == null ? null : maps.get("endTime").toString();
			Integer pageNo =maps.get("pageNo") == null ? 1 : Integer.valueOf(maps.get("pageNo").toString());
			Integer pageSize = maps.get("pageSize") == null ? 10 : Integer.valueOf(maps.get("pageSize").toString());
			
			if (pageNo != null) {
				findWxPublicAccountPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findWxPublicAccountPage.setLimit(pageSize);
			}
			
			if(status != null && !status.equals("")) {
			  findWxPublicAccountPage.setStatus(Integer.valueOf(status));
			}
			findWxPublicAccountPage.setPaName(paName);
			findWxPublicAccountPage.setEndTime(endTime);
			findWxPublicAccountPage.setStartTime(startTime);
			findWxPublicAccountPage.setNoWxZk(noWxShop);
			findWxPublicAccountPage.setMerchantNo(merchantNo);

			Page<FindWxPublicAccountPageReturn> pages = wxPublicAccountService.findWxPublicAccountPage(findWxPublicAccountPage);


			Page<FindWxPublicAccountPageReturn> page =
					new Page<FindWxPublicAccountPageReturn>(new ArrayList<>(pages.getRows()), pages.getTotal(),pages.getStart(),pages.getLimit() );

	
			map.put("page",page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改公众号
	 *
	 * @param updateWxPublicAccount
 	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月5日
	 *
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
    public Map<String, Object> edit(UpdateWxPublicAccount updateWxPublicAccount,String paramJson){
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			
			Map maps = (Map)JSON.parse(paramJson); 
			String code = maps.get("code") == null ? null : maps.get("code").toString();
			String status = maps.get("status") == null ? null : maps.get("status").toString();
			updateWxPublicAccount.setCode(code);
			if(status != null && !status.equals("")) {
				updateWxPublicAccount.setStatus(Integer.valueOf(status));
			}
			wxPublicAccountService.updateWxPublicAccount(updateWxPublicAccount);
			map.put("result",true);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			map.put("result",false);
			map.put("message", "错误");
		}
		return map;
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除公众号
	 *
	 * @param updateWxPublicAccount
 	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月5日
	 *
	 */
	@RequestMapping(value="/delete.do")
	@ResponseBody
    public GeneralResponse delete(String code){
		try {
			DelWxPublicAccount delWxPublicAccount = new DelWxPublicAccount();
			delWxPublicAccount.setCode(code);
			wxPublicAccountService.delWxPublicAccount(delWxPublicAccount);
		} catch(Exception e) {
			logger.error("删除公众号出错!", e);
			return GeneralResponse.generateFailureResponse();
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
}
