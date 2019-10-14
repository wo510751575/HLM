/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.supcon.dto.common.VersionInfoMessage;
import com.lj.business.supcon.service.ICommonService;

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
 * CreateDate: 2018年1月10日
 */
public class CommonServiceImplTest extends SpringTestCase {

	@Resource
	private ICommonService commonService;
	
	@Test
	public void testSendVersionInfoMessage() {
		VersionInfoMessage versionInfoMessage = new VersionInfoMessage();
//		versionInfoMessage.setImei("866036033088987");
		versionInfoMessage.setCurrentVersion(178);
		versionInfoMessage.setLimitVersion(178);
		versionInfoMessage.setFileSize("25.60");
		versionInfoMessage.setForceUpdate(false);
		versionInfoMessage.setDownloadUrl("http://192.168.6.60/app/download/ZK_v2.1.0_jk178.apk");
		versionInfoMessage.setUpdateDesc("1、新增了朋友圈功能");
		versionInfoMessage.setVersionName("v2.1.0");
		commonService.sendVersionInfoMessage(versionInfoMessage);
	}
}
