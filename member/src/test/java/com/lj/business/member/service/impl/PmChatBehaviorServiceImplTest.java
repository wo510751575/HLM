/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.UpdatePmChatBehavior;
import com.lj.business.member.service.IPmChatBehaviorService;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月29日
 */
public class PmChatBehaviorServiceImplTest extends SpringTestCase {

	@Resource
	private IPmChatBehaviorService pmChatBehaviorService;
	
	@Test
	public void testUpdateBehavior() {
		UpdatePmChatBehavior updatePmChatBehavior = new UpdatePmChatBehavior();
		updatePmChatBehavior.setMemberNo("5207ea8dd3484b19b4e64a8c3915c56d");
		updatePmChatBehavior.setMemberNoGm("7e1245c1a8294b8aad522f5d091e8b47");
		updatePmChatBehavior.setChatTime(new Date());
//		updatePmChatBehavior.setThirdUnreadFlag(0);
		pmChatBehaviorService.updateBehavior(updatePmChatBehavior);
	}
}
