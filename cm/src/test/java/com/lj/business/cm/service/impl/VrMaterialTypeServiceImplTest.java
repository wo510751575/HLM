package com.lj.business.cm.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cm.dto.vrMaterialType.AddVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.DelVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeApiReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypePage;
import com.lj.business.cm.dto.vrMaterialType.UpdateVrMaterialType;
import com.lj.business.cm.service.IVrMaterialTypeService;


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
public class VrMaterialTypeServiceImplTest extends SpringTestCase{

	@Resource
	IVrMaterialTypeService vrMaterialTypeService;



	/**
	 * 
	 *
	 * 方法说明：添加VR素材类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addVrMaterialType() throws TsfaServiceException{
		AddVrMaterialType addVrMaterialType = new AddVrMaterialType();
		//add数据录入
		for(int i =0 ;i<=3 ;i++){
			addVrMaterialType.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
			addVrMaterialType.setTypeName("户型");
			addVrMaterialType.setTypeCount(1);
			addVrMaterialType.setImgAddr("http://www.baidu.com");
			addVrMaterialType.setCustomerAttentionRate(1.1);
			addVrMaterialType.setMaterialDimension("SHOP");
			addVrMaterialType.setShowIndex(1);
			addVrMaterialType.setCreateId("CreateId");
			addVrMaterialType.setRemark("Remark");
			addVrMaterialType.setRemark2("Remark2");
			addVrMaterialType.setRemark3("Remark3");
			addVrMaterialType.setRemark4("Remark4");
			
			Assert.assertNotNull(vrMaterialTypeService.addVrMaterialType(addVrMaterialType ));
		}
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改VR素材类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateVrMaterialType() throws TsfaServiceException{
		UpdateVrMaterialType updateVrMaterialType = new UpdateVrMaterialType();
		//update数据录入
		updateVrMaterialType.setCode("LJ_7c79796c76d34b088ae549c88caf6b37");
		updateVrMaterialType.setMerchantNo("MerchantNo");
		updateVrMaterialType.setTypeName("TypeName");
		updateVrMaterialType.setTypeCount(1);
		updateVrMaterialType.setImgAddr("ImgAddr");
		updateVrMaterialType.setCustomerAttentionRate(1.1);
		updateVrMaterialType.setMaterialDimension("SHOP");
		updateVrMaterialType.setShowIndex(1);
		updateVrMaterialType.setCreateId("CreateId");
		updateVrMaterialType.setRemark("Remark");
		updateVrMaterialType.setRemark2("Remark2");
		updateVrMaterialType.setRemark3("Remark3");
		updateVrMaterialType.setRemark4("Remark4");

		vrMaterialTypeService.updateVrMaterialType(updateVrMaterialType );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找VR素材类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findVrMaterialType() throws TsfaServiceException{
		FindVrMaterialType findVrMaterialType = new FindVrMaterialType();
		findVrMaterialType.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		List<FindVrMaterialTypeApiReturn>  list =vrMaterialTypeService.findVrMaterialTypeReturnList(findVrMaterialType);
		System.out.println(list.toString());
	}
	
/*	
	*//**
	 * 
	 *
	 * 方法说明：查找VR素材类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findVrMaterialTypePage() throws TsfaServiceException{
		FindVrMaterialTypePage findVrMaterialTypePage = new FindVrMaterialTypePage();
		findVrMaterialTypePage.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		 vrMaterialTypeService.findVrMaterialTypePage(findVrMaterialTypePage);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除VR素材类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void delVrMaterialType() throws TsfaServiceException{
		DelVrMaterialType delVrMaterialType = new DelVrMaterialType();
		delVrMaterialType.setCode("LJ_7c79796c76d34b088ae549c88caf6b37");
		Assert.assertNotNull(vrMaterialTypeService.delVrMaterialType(delVrMaterialType));
		
	}
	
	
}
