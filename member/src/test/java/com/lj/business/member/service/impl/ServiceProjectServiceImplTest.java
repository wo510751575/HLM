package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.service.project.AddServiceProject;
import com.lj.business.member.dto.service.project.FindServiceProject;
import com.lj.business.member.dto.service.project.FindServiceProjectApp;
import com.lj.business.member.dto.service.project.FindServiceProjectAppReturn;
import com.lj.business.member.dto.service.project.FindServiceProjectPage;
import com.lj.business.member.dto.service.project.FindServiceProjectPageReturn;
import com.lj.business.member.dto.service.project.UpdateServiceProject;
import com.lj.business.member.service.IServiceProjectService;


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
public class ServiceProjectServiceImplTest extends SpringTestCase{

	@Resource
	private  IServiceProjectService serviceProjectService;



	/**
	 * 
	 *
	 * 方法说明：添加服务项目表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addServiceProject() throws TsfaServiceException{
		AddServiceProject addServiceProject = new AddServiceProject();
		//add数据录入
		addServiceProject.setProjectName("ProjectName");
		addServiceProject.setShowIndex(1);
//		addServiceProject.setShopNo("ShopNo");
//		addServiceProject.setShopName("ShopName");
		addServiceProject.setMerchantNo("MerchantNo");
		addServiceProject.setMerchantName("MerchantName");
		addServiceProject.setCreateId("CreateId");
		addServiceProject.setCreateDate(new Date());
		addServiceProject.setRemark("Remark");
		addServiceProject.setRemark2("Remark2");
		addServiceProject.setRemark3("Remark3");
		addServiceProject.setRemark4("Remark4");
		
		serviceProjectService.addServiceProject(addServiceProject);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改服务项目表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateServiceProject() throws TsfaServiceException{
		UpdateServiceProject updateServiceProject = new UpdateServiceProject();
		//update数据录入
		updateServiceProject.setCode("LJ_1fddcb454c134641ac2ffd9ac68f0fd0");
		updateServiceProject.setProjectName("ProjectName");
		updateServiceProject.setShowIndex(1);
//		updateServiceProject.setShopNo("ShopNo");
//		updateServiceProject.setShopName("ShopName");
		updateServiceProject.setMerchantNo("MerchantNo");
		updateServiceProject.setMerchantName("MerchantName");
		updateServiceProject.setCreateId("CreateId");
		updateServiceProject.setCreateDate(new Date());
		updateServiceProject.setRemark("Remark");
		updateServiceProject.setRemark2("Remark2");
		updateServiceProject.setRemark3("Remark3");
		updateServiceProject.setRemark4("Remark4");

		serviceProjectService.updateServiceProject(updateServiceProject );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找服务项目表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findServiceProject() throws TsfaServiceException{
		FindServiceProject findServiceProject = new FindServiceProject();
		findServiceProject.setCode("LJ_1fddcb454c134641ac2ffd9ac68f0fd0");
		serviceProjectService.findServiceProject(findServiceProject);
	}
	
	@Test
	public void findServiceProjectList() throws TsfaServiceException{
		FindServiceProjectApp findServiceProjectApp = new FindServiceProjectApp();
		findServiceProjectApp.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		List<FindServiceProjectAppReturn> list=serviceProjectService.findServiceProjectList(findServiceProjectApp);
		System.out.println(list);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找服务项目表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findServiceProjectPage() throws TsfaServiceException{
		FindServiceProjectPage findServiceProjectPage = new FindServiceProjectPage();
		Page<FindServiceProjectPageReturn> page = serviceProjectService.findServiceProjectPage(findServiceProjectPage);
		Assert.assertNotNull(page);
		
	}
	
/*	@Test
	public void testFindServiceProjectApp() {
		FindServiceProjectApp findServiceProjectApp = new FindServiceProjectApp();
		findServiceProjectApp.setShopNo("LJ_dbfd184dbe6e4233b03596177c465aaf");
		logger.info(serviceProjectService.findServiceProjectApp(findServiceProjectApp).toString());
	}
	*/
}
