package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.readDetail.AddReadDetail;
import com.lj.business.st.service.IReadDetailService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class ReadDetailServiceImplTest extends SpringTestCase{

	@Resource
	IReadDetailService readDetailService;



	/**
	 * 
	 *
	 * 方法说明：添加阅读明细表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addReadDetail() throws TsfaServiceException{
		AddReadDetail addReadDetail = new AddReadDetail();
		//add数据录入
		addReadDetail.setName("Name");
		addReadDetail.setUrlAddress("UrlAddress");
		addReadDetail.setVisitIdentify("VisitIdentify");
		addReadDetail.setIpAddress("IpAddress");
		addReadDetail.setMac("Mac");
		addReadDetail.setNetType("NetType");
		addReadDetail.setEquipment("Equipment");
		addReadDetail.setAreaInfo("AreaInfo");
		addReadDetail.setLoginArea("LoginArea");
		
		readDetailService.addReadDetail(addReadDetail );
		
	}
	
	
	
	
}
