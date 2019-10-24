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
 * 病历
 * @author wo510
 *
 */
public class MedicalActionTest {

	Map<String, Object> businessParamMap = new HashMap<String, Object>();
	@Before
	public void init() {
		businessParamMap.put("token", ApiHelp.login_HLM());
	}
	
	@Test
	public void findMedicalByCode() {
		businessParamMap.put("code", "LJ_03424ccb661044dc86df2cb38fb45e27");
		ApiHelp.doPost("hx/medical/findMedicalByCode.do", businessParamMap);
	}
	
	@Test
	public void addMedical() {
		businessParamMap.put("patientNo", "no");
		businessParamMap.put("patientName", "name");
		businessParamMap.put("visitingDate", "2019-10-12");
		businessParamMap.put("createId", "1");
		businessParamMap.put("createName", "1");
		
		Map<String, Object> checkDto = new HashMap<String, Object>();
		checkDto.put("checkAuxiliaryRemark","checkAuxiliaryRemark");
		checkDto.put("checkOralRemark","checkOralRemark");
		checkDto.put("dentalPosition","dentalPosition");
		checkDto.put("dentalSurface","dentalSurface");
		List<Map<String, Object>> checks = new ArrayList<Map<String, Object>>();
		checks.add(checkDto);
		businessParamMap.put("checks", checks);
		ApiHelp.doPost("hx/medical/addMedical.do", businessParamMap);
	}
	
	
	@Test
	public void editMedical() {
		businessParamMap.put("code", "YE_62836225167c40e39dba31e46e9a3c97");
		businessParamMap.put("updateId", "1");
		businessParamMap.put("updateName", "1");
		ApiHelp.doPost("hx/medical/editMedical.do", businessParamMap);
	}
	
	@Test
	public void list() {
		businessParamMap.put("patientNo", "LJ_cec0e1e91b3542438b0f5ad394a76677");
		ApiHelp.doPost("hx/medical/list.do", businessParamMap);
	}
	
	@Test
	public void del() {
		businessParamMap.put("code", "YE_62836225167c40e39dba31e46e9a3c97");
		ApiHelp.doPost("hx/medical/del.do", businessParamMap);
	}
}
