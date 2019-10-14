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
import com.lj.business.member.dto.service.projectproperty.AddProjectProperty;
import com.lj.business.member.dto.service.projectproperty.FindProjectProperty;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPage;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPageReturn;
import com.lj.business.member.dto.service.projectproperty.UpdateProjectProperty;
import com.lj.business.member.service.IProjectPropertyService;


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
public class ProjectPropertyServiceImplTest extends SpringTestCase{

	@Resource
	private IProjectPropertyService projectPropertyService;



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
	public void addProjectProperty() throws TsfaServiceException{
		AddProjectProperty addProjectProperty = new AddProjectProperty();
		//add数据录入
		addProjectProperty.setPropertyName("PropertyName");
		addProjectProperty.setProjectCode("ProjectCode");
		addProjectProperty.setShowIndex(1);
		addProjectProperty.setCreateId("CreateId");
		addProjectProperty.setRemark("Remark");
		addProjectProperty.setRemark2("Remark2");
		addProjectProperty.setRemark3("Remark3");
		addProjectProperty.setRemark4("Remark4");
		
		Assert.assertNotNull(projectPropertyService.addProjectProperty(addProjectProperty ));
		
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
	public void updateProjectProperty() throws TsfaServiceException{
		UpdateProjectProperty updateProjectProperty = new UpdateProjectProperty();
		//update数据录入
		updateProjectProperty.setCode("LJ_0bba925163a944c5ba28eaee00aef4df");
		updateProjectProperty.setPropertyName("PropertyName");
		updateProjectProperty.setProjectCode("ProjectCode");
		updateProjectProperty.setShowIndex(1);
		updateProjectProperty.setCreateId("CreateId");
		updateProjectProperty.setRemark("Remark");
		updateProjectProperty.setRemark2("Remark2");
		updateProjectProperty.setRemark3("Remark3");
		updateProjectProperty.setRemark4("Remark4");

		projectPropertyService.updateProjectProperty(updateProjectProperty );
		
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
	public void findProjectProperty() throws TsfaServiceException{
		FindProjectProperty findProjectProperty = new FindProjectProperty();
		findProjectProperty.setCode("LJ_0bba925163a944c5ba28eaee00aef4df");
		projectPropertyService.findProjectProperty(findProjectProperty);
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
	public void findProjectPropertyPage() throws TsfaServiceException{
		FindProjectPropertyPage findProjectPropertyPage = new FindProjectPropertyPage();
		findProjectPropertyPage.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		Page<FindProjectPropertyPageReturn> page = projectPropertyService.findProjectPropertyPage(findProjectPropertyPage);
		System.out.println(page.toString());
		Assert.assertNotNull(page);
		
	}
	
}
