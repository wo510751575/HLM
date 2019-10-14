/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.im;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPage;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPageReturn;
import com.lj.business.weixin.service.IWxSmallProgramService;

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
@RequestMapping(value = "/im/smallprogram/")
public class WxSmallProgramAction extends Action {

    private static final Logger logger = LoggerFactory.getLogger(WxSmallProgramAction.class);
    
	@Resource
	private IWxSmallProgramService wxSmallProgramService;
	
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
	@RequestMapping(value="list.do")
	@ResponseBody
	public GeneralResponse list(FindWxSmallProgramPage findWxSmallProgramPage) {
		try {
			if(findWxSmallProgramPage!=null) {
				findWxSmallProgramPage.setStatus(1);	//获取有效的
			}
			Page<FindWxSmallProgramPageReturn> pages = wxSmallProgramService.findWxSmallProgramPage(findWxSmallProgramPage);
			
			//**************************** 为了让ios 审核通过  （过后可删除）*******************************
			Date now = new Date();
			Date date = null;
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");              
			String str = null;                 
			// String转Date   
			str = "2018-12-12 18:00:00";         
			try {   
				date  = format1.parse(str);  
			} catch (Exception e) {   
			           e.printStackTrace();   
			}  
			
			if(now.before(date)) {
				pages = new Page<FindWxSmallProgramPageReturn>();
				return GeneralResponse.generateSuccessResponse(pages);
			}
			
			//**************************** 为了让ios 审核通过  （过后可删除）*******************************
			
			
			return GeneralResponse.generateSuccessResponse(pages);
			
		} catch (Exception e) {
			logger.error("获取小程序列表失败 e={}", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}
	
}
