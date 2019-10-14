package com.lj.business.cm.service.impl;

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

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cm.dto.vrMaterialCommenCategory.AddVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.DelVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategoryReturn;
import com.lj.business.cm.dto.vrMaterialCommenCategory.UpdateVrMaterialCommenCategory;
import com.lj.business.cm.service.IVrMaterialCommenCategoryService;


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
public class VrMaterialCommenCategoryServiceImplTest extends SpringTestCase{

	@Resource
	IVrMaterialCommenCategoryService vrMaterialCommenCategoryService;



	/**
	 * 
	 *
	 * 方法说明：添加VR公用素材中心-分类关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addVrMaterialCommenCategory() throws TsfaServiceException{
		AddVrMaterialCommenCategory addVrMaterialCommenCategory = new AddVrMaterialCommenCategory();
		//add数据录入
		addVrMaterialCommenCategory.setCode("Code");
		addVrMaterialCommenCategory.setMaterialCode("MaterialCode");
		addVrMaterialCommenCategory.setMaterialTypeCode("MaterialTypeCode");
		addVrMaterialCommenCategory.setMaterialTypeName("MaterialTypeName");
		addVrMaterialCommenCategory.setTypeCategoryCode("TypeCategoryCode");
		addVrMaterialCommenCategory.setTypeCategoryName("TypeCategoryName");
		addVrMaterialCommenCategory.setCreateId("CreateId");
		addVrMaterialCommenCategory.setCreateDate(new Date());
		addVrMaterialCommenCategory.setRemark("Remark");
		addVrMaterialCommenCategory.setRemark2("Remark2");
		addVrMaterialCommenCategory.setRemark3("Remark3");
		addVrMaterialCommenCategory.setRemark4("Remark4");
		
		Assert.assertNotNull(vrMaterialCommenCategoryService.addVrMaterialCommenCategory(addVrMaterialCommenCategory ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改VR公用素材中心-分类关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateVrMaterialCommenCategory() throws TsfaServiceException{
		UpdateVrMaterialCommenCategory updateVrMaterialCommenCategory = new UpdateVrMaterialCommenCategory();
		//update数据录入
		updateVrMaterialCommenCategory.setCode("Code");
		updateVrMaterialCommenCategory.setMaterialCode("MaterialCode");
		updateVrMaterialCommenCategory.setMaterialTypeCode("MaterialTypeCode");
		updateVrMaterialCommenCategory.setMaterialTypeName("MaterialTypeName");
		updateVrMaterialCommenCategory.setTypeCategoryCode("TypeCategoryCode");
		updateVrMaterialCommenCategory.setTypeCategoryName("TypeCategoryName");
		updateVrMaterialCommenCategory.setCreateId("CreateId");
		updateVrMaterialCommenCategory.setCreateDate(new Date());
		updateVrMaterialCommenCategory.setRemark("Remark");
		updateVrMaterialCommenCategory.setRemark2("Remark2");
		updateVrMaterialCommenCategory.setRemark3("Remark3");
		updateVrMaterialCommenCategory.setRemark4("Remark4");

		vrMaterialCommenCategoryService.updateVrMaterialCommenCategory(updateVrMaterialCommenCategory );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找VR公用素材中心-分类关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findVrMaterialCommenCategory() throws TsfaServiceException{
		FindVrMaterialCommenCategory findVrMaterialCommenCategory = new FindVrMaterialCommenCategory();
		findVrMaterialCommenCategory.setMaterialCode("LJ_e5f9fc8f83fa4d33b3c38903b48fe418");
		List<FindVrMaterialCommenCategoryReturn> list = vrMaterialCommenCategoryService.findVrMaterialCommenCategoryList(findVrMaterialCommenCategory);
		System.out.println(list);
	}
	
/*	*//**
	 * 
	 *
	 * 方法说明：查找VR公用素材中心-分类关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 *//*
	@Test
	public void findVrMaterialCommenCategoryPage() throws TsfaServiceException{
		FindVrMaterialCommenCategoryPage findVrMaterialCommenCategoryPage = new FindVrMaterialCommenCategoryPage();
		Page<FindVrMaterialCommenCategoryPageReturn> page = vrMaterialCommenCategoryService.findVrMaterialCommenCategoryPage(findVrMaterialCommenCategoryPage);
		Assert.assertNotNull(page);
		
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：删除VR公用素材中心-分类关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void delVrMaterialCommenCategory() throws TsfaServiceException{
		DelVrMaterialCommenCategory delVrMaterialCommenCategory = new DelVrMaterialCommenCategory();
		delVrMaterialCommenCategory.setCode("");
		Assert.assertNotNull(vrMaterialCommenCategoryService.delVrMaterialCommenCategory(delVrMaterialCommenCategory));
		
	}
	
	
}
