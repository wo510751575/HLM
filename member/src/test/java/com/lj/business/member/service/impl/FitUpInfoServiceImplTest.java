package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.fitUpInfo.AddFitUpInfo;
import com.lj.business.member.dto.fitUpInfo.DelFitUpInfo;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfo;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPage;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPageReturn;
import com.lj.business.member.dto.fitUpInfo.UpdateFitUpInfo;
import com.lj.business.member.service.IFitUpInfoService;


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
public class FitUpInfoServiceImplTest extends SpringTestCase{

	@Resource
	IFitUpInfoService fitUpInfoService;



	/**
	 * 
	 *
	 * 方法说明：添加装修需求信息表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addFitUpInfo() throws TsfaServiceException{
		AddFitUpInfo addFitUpInfo = new AddFitUpInfo();
		//add数据录入
		addFitUpInfo.setStyle("Style");
		addFitUpInfo.setFullName("FullName");
		addFitUpInfo.setHouseType("HouseType");
		addFitUpInfo.setMobile("Mobile");
		addFitUpInfo.setImgAddr("ImgAddr");
		addFitUpInfo.setStatus("Status");
		addFitUpInfo.setRemark("Remark");
		addFitUpInfo.setCreateId("CreateId");
		
		fitUpInfoService.addFitUpInfo(addFitUpInfo );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改装修需求信息表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	/*@Test
	public void updateFitUpInfo() throws TsfaServiceException{
		UpdateFitUpInfo updateFitUpInfo = new UpdateFitUpInfo();
		//update数据录入
		updateFitUpInfo.setCode("Code");
		updateFitUpInfo.setStyle("Style");
		updateFitUpInfo.setFullName("FullName");
		updateFitUpInfo.setHouseType("HouseType");
		updateFitUpInfo.setMobile("Mobile");
		updateFitUpInfo.setImgAddr("ImgAddr");
		updateFitUpInfo.setStatus("Status");
		updateFitUpInfo.setRemark("Remark");
		updateFitUpInfo.setCreateId("CreateId");
		updateFitUpInfo.setCreateDate("CreateDate");

		fitUpInfoService.updateFitUpInfo(updateFitUpInfo );
		
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：查找装修需求信息表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findFitUpInfo() throws TsfaServiceException{
		FindFitUpInfo findFitUpInfo = new FindFitUpInfo();
		findFitUpInfo.setCode("111");
		fitUpInfoService.findFitUpInfo(findFitUpInfo);
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除装修需求信息表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	/*@Test
	public void delFitUpInfo() throws TsfaServiceException{
		DelFitUpInfo delFitUpInfo = new DelFitUpInfo();
		delFitUpInfo.setCode("111");
		fitUpInfoService.delFitUpInfo(delFitUpInfo);
		
	}
	
	*/
	/**
	 * 
	 *
	 * 方法说明：查找装修需求信息表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findFitUpInfoPage() throws TsfaServiceException{
		FindFitUpInfoPage findFitUpInfoPage = new FindFitUpInfoPage();
		findFitUpInfoPage.setFullName("FullNa");
		Page<FindFitUpInfoPageReturn> page = fitUpInfoService.findFitUpInfoPage(findFitUpInfoPage);
		Assert.assertNotNull(page);
		
	}
	
	
	
	
}
