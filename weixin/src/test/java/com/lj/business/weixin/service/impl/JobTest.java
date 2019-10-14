/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.cc.clientintf.IJob;

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
 * CreateDate: 2018年4月18日
 */
public class JobTest extends SpringTestCase {

	@Resource(name="redPackagePollJob")
	private IJob redPackagePollJob;
	
	@Test
	public void testRedPackagePollJob() {
		redPackagePollJob.runJob();
	}
}
