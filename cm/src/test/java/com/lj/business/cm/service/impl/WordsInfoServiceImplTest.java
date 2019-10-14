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

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cm.dto.wordsInfo.AddWordsInfo;
import com.lj.business.cm.dto.wordsInfo.DelWordsInfo;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfo;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPage;
import com.lj.business.cm.dto.wordsInfo.UpdateWordsInfo;
import com.lj.business.cm.service.IWordsInfoService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
public class WordsInfoServiceImplTest extends SpringTestCase{

	@Resource
	IWordsInfoService wordsInfoService;



	/**
	 * 
	 *
	 * 方法说明：添加话术信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void addWordsInfo() throws TsfaServiceException{
		AddWordsInfo addWordsInfo = new AddWordsInfo();
		//add数据录入
		addWordsInfo.setCode("Code4");
		addWordsInfo.setContent("Content4");
		addWordsInfo.setDefaultFlag(1);
		addWordsInfo.setMerchantNo("fcecbfa097944565a58134d170f474af");
		addWordsInfo.setTypeCode("TypeCode2");
		addWordsInfo.setTypeName("TypeName2");
		addWordsInfo.setCreateId("CreateId4");
		addWordsInfo.setCreateDate(new Date());
		addWordsInfo.setUpdateTime(new Date());
		
		Assert.assertNotNull(wordsInfoService.addWordsInfo(addWordsInfo ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改话术信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void updateWordsInfo() throws TsfaServiceException{
		UpdateWordsInfo updateWordsInfo = new UpdateWordsInfo();
		//update数据录入
		updateWordsInfo.setCode("Code");
		updateWordsInfo.setContent("Content");
		updateWordsInfo.setDefaultFlag(1);
		updateWordsInfo.setMerchantNo("MerchantNo");
		updateWordsInfo.setTypeCode("TypeCode");
		updateWordsInfo.setTypeName("TypeName");
		updateWordsInfo.setCreateId("CreateId");
		updateWordsInfo.setCreateDate(new Date());
		updateWordsInfo.setUpdateTime(new Date());
		updateWordsInfo.setRemark("Remark");
		updateWordsInfo.setRemark2("Remark2");
		updateWordsInfo.setRemark3("Remark3");
		updateWordsInfo.setRemark4("Remark4");

		wordsInfoService.updateWordsInfo(updateWordsInfo );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找话术信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findWordsInfo() throws TsfaServiceException{
		FindWordsInfo findWordsInfo = new FindWordsInfo();
//		findWordsInfo.setCode("code");
		wordsInfoService.findWordsInfo(findWordsInfo);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找话术信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findWordsInfoPage() throws TsfaServiceException{
		FindWordsInfoPage findWordsInfoPage = new FindWordsInfoPage();
//		Page<FindWordsInfoPageReturn> page = wordsInfoService.findWordsInfoPage(findWordsInfoPage);
//		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除话术信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void delWordsInfo() throws TsfaServiceException{
		DelWordsInfo delWordsInfo = new DelWordsInfo();
//		delWordsInfo.setCode("code");
		Assert.assertNotNull(wordsInfoService.delWordsInfo(delWordsInfo));
		
	}
	
	@Test
	public void testBoolean() throws Exception {
		System.out.println(Boolean.TRUE.toString());
	}
}
