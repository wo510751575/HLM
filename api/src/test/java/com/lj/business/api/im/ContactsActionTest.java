/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.im;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lj.business.api.ApiHelp;

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
 * CreateDate: 2017年10月30日
 */
public class ContactsActionTest {

	/**
	 * 
	 *
	 * 方法说明：分页查询通讯录好友（增量）
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月30日
	 *
	 */
	@Test
	public void testfindFriends() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("merchantNo", "e79d975846ee41ba8c3c55738fda66a9");
		businessParamMap.put("memberNoGm", "975150e058564ab0afaa6f9c1a2af676");
		businessParamMap.put("noWxGm", "");
		businessParamMap.put("version", "1508837555");
		ApiHelp.doPost("im/contacts/findFriends.do", businessParamMap);
	}
	
}
