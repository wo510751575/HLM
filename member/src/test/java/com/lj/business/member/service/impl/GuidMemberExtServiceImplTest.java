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

import java.util.Date;
import java.util.List;

import com.lj.business.member.dto.GuidMemberExtDto;
import com.lj.business.member.dto.FindGuidMemberExtPage;
import com.lj.business.member.service.IGuidMemberExtService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 林进权
 * 
 * 
 * CreateDate: 2017-08-22
 */
public class GuidMemberExtServiceImplTest extends SpringTestCase{

	@Resource
	IGuidMemberExtService guidMemberExtService;



	/**
	 * 
	 *
	 * 方法说明：添加导购开放平台扩展信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	@Test
	public void addGuidMemberExt() throws TsfaServiceException{
		GuidMemberExtDto guidMemberExtDto = new GuidMemberExtDto();
		//add数据录入
		guidMemberExtDto.setCode("Code");
		guidMemberExtDto.setMemberNoMerchant("1f169ad6143d46f5832535642ce2d331");
		guidMemberExtDto.setMobile("18526458151");
		guidMemberExtDto.setMemberName("memberName");
		
		guidMemberExtService.addGuidMemberExt(guidMemberExtDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改导购开放平台扩展信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	@Test
	public void updateGuidMemberExt() throws TsfaServiceException{
		GuidMemberExtDto guidMemberExtDto = new GuidMemberExtDto();
		//update数据录入
		guidMemberExtDto.setMemberNoMerchant("1f169ad6143d46f5832535642ce2d331");
		guidMemberExtDto.setMobile("18526458151");
		guidMemberExtDto.setMemberName("阿米菜园");
		guidMemberExtDto.setCode("LJ_777052cdf9274e5b865202bce74cf12a");

		guidMemberExtService.updateGuidMemberExt(guidMemberExtDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购开放平台扩展信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	@Test
	public void findGuidMemberExt() throws TsfaServiceException{
		GuidMemberExtDto findGuidMemberExt = new GuidMemberExtDto();
		findGuidMemberExt.setCode("111");
		guidMemberExtService.findGuidMemberExt(findGuidMemberExt);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购开放平台扩展信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	@Test
	public void findGuidMemberExtPage() throws TsfaServiceException{
		FindGuidMemberExtPage findGuidMemberExtPage = new FindGuidMemberExtPage();
		Page<GuidMemberExtDto> page = guidMemberExtService.findGuidMemberExtPage(findGuidMemberExtPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购开放平台扩展信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017-08-22
	 *
	 */
	@Test
	public void findGuidMemberExts() throws TsfaServiceException{
		FindGuidMemberExtPage findGuidMemberExtPage = new FindGuidMemberExtPage();
		List<GuidMemberExtDto> page = guidMemberExtService.findGuidMemberExts(findGuidMemberExtPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void findGuidMemberExtByJobNum() throws TsfaServiceException{
		GuidMemberExtDto findGuidMemberExt = new GuidMemberExtDto();
		findGuidMemberExt.setJobNum("222");
//		findGuidMemberExt.setShopNo("shopNo");
		findGuidMemberExt.setMemberNoMerchant("merchant");
		guidMemberExtService.findGuidMemberExtByMobile(findGuidMemberExt);
	}
	
}
