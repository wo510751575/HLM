package com.lj.business.cm.service.impl;

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
import com.lj.business.cm.dto.guidIntroduceMaterial.AddGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.DelGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPage;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPageReturn;
import com.lj.business.cm.dto.guidIntroduceMaterial.UpdateGuidIntroduceMaterial;
import com.lj.business.cm.service.IGuidIntroduceMaterialService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class GuidIntroduceMaterialServiceImplTest extends SpringTestCase{

	@Resource
	IGuidIntroduceMaterialService guidIntroduceMaterialService;



	/**
	 * 
	 *
	 * 方法说明：添加导购个人介绍素材表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addGuidIntroduceMaterial() throws TsfaServiceException{
		AddGuidIntroduceMaterial addGuidIntroduceMaterial = new AddGuidIntroduceMaterial();
		//add数据录入
		addGuidIntroduceMaterial.setCode("Code");
		addGuidIntroduceMaterial.setMerchantNo("MerchantNo");
//		addGuidIntroduceMaterial.setShopNo("ShopNo");
//		addGuidIntroduceMaterial.setShopName("ShopName");
		addGuidIntroduceMaterial.setMemberNoGm("MemberNoGm");
		addGuidIntroduceMaterial.setMemberNameGm("MemberNameGm");
		addGuidIntroduceMaterial.setName("Name");
		addGuidIntroduceMaterial.setPosition("Position");
		addGuidIntroduceMaterial.setServeLevel(0D);
		addGuidIntroduceMaterial.setProfessionalLevel(0D);
		addGuidIntroduceMaterial.setMobile("Mobile");
		addGuidIntroduceMaterial.setCompanyName("CompanyName");
		addGuidIntroduceMaterial.setCompanyAddress("CompanyAddress");
		addGuidIntroduceMaterial.setSlogan("Slogan");
		addGuidIntroduceMaterial.setRemark("Remark");
		addGuidIntroduceMaterial.setRemark2("Remark2");
		addGuidIntroduceMaterial.setRemark3("Remark3");
		addGuidIntroduceMaterial.setRemark4("Remark4");
		
		guidIntroduceMaterialService.addGuidIntroduceMaterial(addGuidIntroduceMaterial );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改导购个人介绍素材表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void updateGuidIntroduceMaterial() throws TsfaServiceException{
		UpdateGuidIntroduceMaterial updateGuidIntroduceMaterial = new UpdateGuidIntroduceMaterial();
		//update数据录入
		updateGuidIntroduceMaterial.setCode("Code");
		updateGuidIntroduceMaterial.setMerchantNo("MerchantNo");
//		updateGuidIntroduceMaterial.setShopNo("ShopNo");
//		updateGuidIntroduceMaterial.setShopName("ShopName");
		updateGuidIntroduceMaterial.setMemberNoGm("MemberNoGm");
		updateGuidIntroduceMaterial.setMemberNameGm("MemberNameGm");
		updateGuidIntroduceMaterial.setName("Name");
		updateGuidIntroduceMaterial.setPosition("Position");
		updateGuidIntroduceMaterial.setServeLevel(0D);
		updateGuidIntroduceMaterial.setProfessionalLevel(0D);
		updateGuidIntroduceMaterial.setMobile("Mobile");
		updateGuidIntroduceMaterial.setCompanyName("CompanyName");
		updateGuidIntroduceMaterial.setCompanyAddress("CompanyAddress");
		updateGuidIntroduceMaterial.setSlogan("Slogan");
		updateGuidIntroduceMaterial.setRemark("Remark");
		updateGuidIntroduceMaterial.setRemark2("Remark2");
		updateGuidIntroduceMaterial.setRemark3("Remark3");
		updateGuidIntroduceMaterial.setRemark4("Remark4");

		guidIntroduceMaterialService.updateGuidIntroduceMaterial(updateGuidIntroduceMaterial );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购个人介绍素材表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findGuidIntroduceMaterial() throws TsfaServiceException{
		FindGuidIntroduceMaterial findGuidIntroduceMaterial = new FindGuidIntroduceMaterial();
		findGuidIntroduceMaterial.setCode("111");
		guidIntroduceMaterialService.findGuidIntroduceMaterial(findGuidIntroduceMaterial);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购个人介绍素材表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findGuidIntroduceMaterialPage() throws TsfaServiceException{
		FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage = new FindGuidIntroduceMaterialPage();
		Page<FindGuidIntroduceMaterialPageReturn> page = guidIntroduceMaterialService.findGuidIntroduceMaterialPage(findGuidIntroduceMaterialPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除导购个人介绍素材表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void delGuidIntroduceMaterial() throws TsfaServiceException{
		DelGuidIntroduceMaterial delGuidIntroduceMaterial = new DelGuidIntroduceMaterial();
		delGuidIntroduceMaterial.setCode("111");
		guidIntroduceMaterialService.delGuidIntroduceMaterial(delGuidIntroduceMaterial);
		
	}
	
	
}
