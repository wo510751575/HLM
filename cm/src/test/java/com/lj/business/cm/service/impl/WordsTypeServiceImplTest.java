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
import com.lj.business.cm.dto.wordsType.AddWordsType;
import com.lj.business.cm.dto.wordsType.DelWordsType;
import com.lj.business.cm.dto.wordsType.FindWordsType;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;
import com.lj.business.cm.dto.wordsType.FindWordsTypePageReturn;
import com.lj.business.cm.dto.wordsType.UpdateWordsType;
import com.lj.business.cm.service.IWordsTypeService;


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
public class WordsTypeServiceImplTest extends SpringTestCase{

	@Resource
	IWordsTypeService wordsTypeService;



	/**
	 * 
	 *
	 * 方法说明：添加话术类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void addWordsType() throws TsfaServiceException{
		AddWordsType addWordsType = new AddWordsType();
		//add数据录入
		addWordsType.setCode("TypeCode3");
		addWordsType.setMerchantNo("fcecbfa097944565a58134d170f474af");
		addWordsType.setTypeName("TypeName3");
		addWordsType.setSeq(3);
		addWordsType.setCreateId("CreateId3");
		addWordsType.setCreateDate(new Date());
		
		Assert.assertNotNull(wordsTypeService.addWordsType(addWordsType ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改话术类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void updateWordsType() throws TsfaServiceException{
		UpdateWordsType updateWordsType = new UpdateWordsType();
		//update数据录入
		updateWordsType.setCode("Code");
		updateWordsType.setMerchantNo("MerchantNo");
		updateWordsType.setTypeName("TypeName");
		updateWordsType.setSeq(1);
		updateWordsType.setCreateId("CreateId");
		updateWordsType.setCreateDate(new Date());
		updateWordsType.setRemark("Remark");
		updateWordsType.setRemark2("Remark2");
		updateWordsType.setRemark3("Remark3");
		updateWordsType.setRemark4("Remark4");

//		wordsTypeService.updateWordsType(updateWordsType );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找话术类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findWordsType() throws TsfaServiceException{
		FindWordsType findWordsType = new FindWordsType();
		findWordsType.setCode("code");
		wordsTypeService.findWordsType(findWordsType);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找话术类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findWordsTypePage() throws TsfaServiceException{
		FindWordsTypePage findWordsTypePage = new FindWordsTypePage();
		Page<FindWordsTypePageReturn> page = wordsTypeService.findWordsTypePage(findWordsTypePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除话术类型信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void delWordsType() throws TsfaServiceException{
		DelWordsType delWordsType = new DelWordsType();
		delWordsType.setCode("code");
		Assert.assertNotNull(wordsTypeService.delWordsType(delWordsType));
		
	}
	
	
}
