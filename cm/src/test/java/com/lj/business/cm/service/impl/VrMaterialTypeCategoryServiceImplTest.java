package com.lj.business.cm.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cm.dto.vrMaterialTypeCategory.AddVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.DelVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryPage;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryPageReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.UpdateVrMaterialTypeCategory;
import com.lj.business.cm.service.IVrMaterialTypeCategoryService;


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
public class VrMaterialTypeCategoryServiceImplTest extends SpringTestCase{

	@Resource
	IVrMaterialTypeCategoryService vrMaterialTypeCategoryService;



	/**
	 * 
	 *
	 * 方法说明：添加VR素材类型分类信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addVrMaterialTypeCategory() throws TsfaServiceException{
		AddVrMaterialTypeCategory addVrMaterialTypeCategory = new AddVrMaterialTypeCategory();
		//add数据录入
		for(int i = 0;i<=3;i++){
			addVrMaterialTypeCategory.setTypeCode("LJ_bd70f8f5fcd94ea9ae6d2521ce791546");
			addVrMaterialTypeCategory.setCategoryName("50m-80m");
			addVrMaterialTypeCategory.setShowIndex(1);
			addVrMaterialTypeCategory.setCreateId("CreateId");
			addVrMaterialTypeCategory.setRemark("Remark");
			addVrMaterialTypeCategory.setRemark2("Remark2");
			addVrMaterialTypeCategory.setRemark3("Remark3");
			addVrMaterialTypeCategory.setRemark4("Remark4");
			
			Assert.assertNotNull(vrMaterialTypeCategoryService.addVrMaterialTypeCategory(addVrMaterialTypeCategory ));
		}
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改VR素材类型分类信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateVrMaterialTypeCategory() throws TsfaServiceException{
		UpdateVrMaterialTypeCategory updateVrMaterialTypeCategory = new UpdateVrMaterialTypeCategory();
		//update数据录入
		updateVrMaterialTypeCategory.setCode("LJ_d9e14d6a7dbe4ed0b13f035aae5784a1");
		updateVrMaterialTypeCategory.setTypeCode("TypeCode");
		updateVrMaterialTypeCategory.setCategoryName("CategoryName");
		updateVrMaterialTypeCategory.setShowIndex(1);
		updateVrMaterialTypeCategory.setCreateId("CreateId");
		updateVrMaterialTypeCategory.setCreateDate(new Date());
		updateVrMaterialTypeCategory.setRemark("Remark");
		updateVrMaterialTypeCategory.setRemark2("Remark2");
		updateVrMaterialTypeCategory.setRemark3("Remark3");
		updateVrMaterialTypeCategory.setRemark4("Remark4");

		vrMaterialTypeCategoryService.updateVrMaterialTypeCategory(updateVrMaterialTypeCategory );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找VR素材类型分类信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findVrMaterialTypeCategory() throws TsfaServiceException{
		FindVrMaterialTypeCategory findVrMaterialTypeCategory = new FindVrMaterialTypeCategory();
		findVrMaterialTypeCategory.setTypeCode("LJ_c6255ad52ad84dd4973a97482b53cfb7");
		vrMaterialTypeCategoryService.findVrMaterialTypeCategoryReturnList(findVrMaterialTypeCategory);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找VR素材类型分类信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findVrMaterialTypeCategoryPage() throws TsfaServiceException{
		FindVrMaterialTypeCategoryPage findVrMaterialTypeCategoryPage = new FindVrMaterialTypeCategoryPage();
		Page<FindVrMaterialTypeCategoryPageReturn> page = vrMaterialTypeCategoryService.findVrMaterialTypeCategoryPage(findVrMaterialTypeCategoryPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除VR素材类型分类信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void delVrMaterialTypeCategory() throws TsfaServiceException{
		DelVrMaterialTypeCategory delVrMaterialTypeCategory = new DelVrMaterialTypeCategory();
		delVrMaterialTypeCategory.setCode("LJ_d9e14d6a7dbe4ed0b13f035aae5784a1");
		Assert.assertNotNull(vrMaterialTypeCategoryService.delVrMaterialTypeCategory(delVrMaterialTypeCategory));
		
	}
	
	
}
