package com.lj.business.cm.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cm.dto.vrMaterialCommen.AddVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.DelVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPage;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPageReturn;
import com.lj.business.cm.dto.vrMaterialCommen.UpdateVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommenCategory.AddVrMaterialCommenCategory;
import com.lj.business.cm.service.IVrMaterialCommenCategoryService;
import com.lj.business.cm.service.IVrMaterialCommenService;


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
public class VrMaterialCommenServiceImplTest extends SpringTestCase{

	@Resource
	IVrMaterialCommenService vrMaterialCommenService;


	@Resource
	IVrMaterialCommenCategoryService vrMaterialCommenCategoryService;

	/**
	 * 
	 *
	 * 方法说明：添加VR公用素材中心信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addVrMaterialCommen() throws TsfaServiceException{
		
		AddVrMaterialCommen addVrMaterialCommen = new AddVrMaterialCommen();
		//add数据录入
			addVrMaterialCommen.setCode(GUID.getPreUUID());
			addVrMaterialCommen.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
			addVrMaterialCommen.setMerchantName("中控");
//			addVrMaterialCommen.setShopNo("LJ_ffdf668444a94a82b614dc246c8e8e62");
//			addVrMaterialCommen.setShopName("深圳福田终端");
			addVrMaterialCommen.setTitle("芝华士120m-140m北欧风格");
			addVrMaterialCommen.setContent("测试");
			addVrMaterialCommen.setBrief("简介");
			addVrMaterialCommen.setImgAddr("https://www.2345.com/");
			addVrMaterialCommen.setDimensionSt("SHOP");
			addVrMaterialCommen.setLinkUrl("https://www.2345.com/");
			addVrMaterialCommen.setRespondNum(1);
			addVrMaterialCommen.setShopType("时尚店");
			addVrMaterialCommen.setCreateId("CreateId");
			addVrMaterialCommen.setRemark("Remark");
			addVrMaterialCommen.setRemark2("Remark2");
			addVrMaterialCommen.setRemark3("Remark3");
			addVrMaterialCommen.setRemark4("Remark4");
			
			AddVrMaterialCommenCategory addVrMaterialCommenCategory = new AddVrMaterialCommenCategory();
			addVrMaterialCommenCategory.setMaterialCode(addVrMaterialCommen.getCode());
			addVrMaterialCommenCategory.setMaterialTypeCode("LJ_2a85dfccf2684b7b98bfa9120c77d534");
			addVrMaterialCommenCategory.setMaterialTypeName("户型");
			addVrMaterialCommenCategory.setTypeCategoryCode("LJ_7efc3ac99c28410e8c3ad23b24ccac32");
			addVrMaterialCommenCategory.setTypeCategoryName("120m-140m");
			addVrMaterialCommenCategory.setCreateId("CreateId");
			addVrMaterialCommenCategory.setCreateDate(new Date());
			addVrMaterialCommenCategory.setRemark("Remark");
			addVrMaterialCommenCategory.setRemark2("Remark2");
			addVrMaterialCommenCategory.setRemark3("Remark3");
			addVrMaterialCommenCategory.setRemark4("Remark4");
			
			vrMaterialCommenCategoryService.addVrMaterialCommenCategory(addVrMaterialCommenCategory );
			
		vrMaterialCommenService.addVrMaterialCommen(addVrMaterialCommen );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改VR公用素材中心信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateVrMaterialCommen() throws TsfaServiceException{
		UpdateVrMaterialCommen updateVrMaterialCommen = new UpdateVrMaterialCommen();
		//update数据录入
		updateVrMaterialCommen.setCode("Code");
		updateVrMaterialCommen.setMerchantNo("MerchantNo");
		updateVrMaterialCommen.setMerchantName("MerchantName");
//		updateVrMaterialCommen.setShopNo("ShopNo");
//		updateVrMaterialCommen.setShopName("ShopName");
		updateVrMaterialCommen.setTitle("Title");
		updateVrMaterialCommen.setContent("Content");
		updateVrMaterialCommen.setBrief("Brief");
		updateVrMaterialCommen.setImgAddr("ImgAddr");
		updateVrMaterialCommen.setDimensionSt("DimensionSt");
		updateVrMaterialCommen.setLinkUrl("LinkUrl");
		updateVrMaterialCommen.setRespondNum(1);
		updateVrMaterialCommen.setShopType("ShopType");
		updateVrMaterialCommen.setCreateId("CreateId");
		updateVrMaterialCommen.setRemark("Remark");
		updateVrMaterialCommen.setRemark2("Remark2");
		updateVrMaterialCommen.setRemark3("Remark3");
		updateVrMaterialCommen.setRemark4("Remark4");

		vrMaterialCommenService.updateVrMaterialCommen(updateVrMaterialCommen );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找VR公用素材中心信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findVrMaterialCommen() throws TsfaServiceException{
		FindVrMaterialCommen findVrMaterialCommen = new FindVrMaterialCommen();
		findVrMaterialCommen.setCode("");
		vrMaterialCommenService.findVrMaterialCommen(findVrMaterialCommen);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找VR公用素材中心信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findVrMaterialCommenPage() throws TsfaServiceException{
		FindVrMaterialCommenPage findVrMaterialCommenPage = new FindVrMaterialCommenPage();
		findVrMaterialCommenPage.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
//		findVrMaterialCommenPage.setShopNo("LJ_2042831bd87644479980d8001b11fa45");
//		findVrMaterialCommenPage.setShopType("时尚店");
		List<String> typeCodes = new ArrayList<>();
//		typeCodes.add("LJ_e10ec57ca3314df6bef6c1e963d8e42b");
//		typeCodes.add("LJ_c9448a94bb594723a603f81bd284b318");
		findVrMaterialCommenPage.setTypeCodes(typeCodes);
		Page<FindVrMaterialCommenPageReturn> page = vrMaterialCommenService.findVrMaterialCommenPage(findVrMaterialCommenPage);
		System.out.println(page);
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除VR公用素材中心信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void delVrMaterialCommen() throws TsfaServiceException{
		DelVrMaterialCommen delVrMaterialCommen = new DelVrMaterialCommen();
		delVrMaterialCommen.setCode("");
		Assert.assertNotNull(vrMaterialCommenService.delVrMaterialCommen(delVrMaterialCommen));
		
	}
	
	
}
