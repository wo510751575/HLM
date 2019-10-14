/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.hx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.InitBinder;

import com.lj.business.api.ApiHelp;


/**
 * 病历模版
 * @author wo510
 *
 */
public class MedicalTemplateActionTest {

	Map<String, Object> businessParamMap = new HashMap<String, Object>();
	@Before
	public void init() {
		businessParamMap.put("token", ApiHelp.login_HLM());
	}
	
	@Test
	public void get() {
		businessParamMap.put("code", "YE_843e64ef375f4adb9497635125594750");
		ApiHelp.doPost("hx/medical/template/get.do", businessParamMap);
	}
}
