package com.lj.business.weixin.service.impl;

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
import com.lj.business.weixin.dto.imemoji.AddImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.DelImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePageReturn;
import com.lj.business.weixin.dto.imemoji.UpdateImEmojiPackage;
import com.lj.business.weixin.service.IImEmojiPackageService;


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
public class ImEmojiPackageServiceImplTest extends SpringTestCase{

	@Resource
	IImEmojiPackageService imEmojiPackageService;



	/**
	 * 
	 *
	 * 方法说明：添加IM表情包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void addImEmojiPackage() throws TsfaServiceException{
		AddImEmojiPackage addImEmojiPackage = new AddImEmojiPackage();
		//add数据录入
		addImEmojiPackage.setCode("Code");
		addImEmojiPackage.setPackageName("PackageName");
//		addImEmojiPackage.setVersion("Version");
//		addImEmojiPackage.setStatus("Status");
//		addImEmojiPackage.setShowIndex("ShowIndex");
		addImEmojiPackage.setCreateId("CreateId");
//		addImEmojiPackage.setCreateDate("CreateDate");
		addImEmojiPackage.setRemark("Remark");
		addImEmojiPackage.setRemark2("Remark2");
		addImEmojiPackage.setRemark3("Remark3");
		addImEmojiPackage.setRemark4("Remark4");
		
		Assert.assertNotNull(imEmojiPackageService.addImEmojiPackage(addImEmojiPackage ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改IM表情包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void updateImEmojiPackage() throws TsfaServiceException{
		UpdateImEmojiPackage updateImEmojiPackage = new UpdateImEmojiPackage();
		//update数据录入
		updateImEmojiPackage.setCode("Code");
		updateImEmojiPackage.setPackageName("PackageName");
//		updateImEmojiPackage.setVersion("Version");
//		updateImEmojiPackage.setStatus("Status");
//		updateImEmojiPackage.setShowIndex("ShowIndex");
		updateImEmojiPackage.setCreateId("CreateId");
//		updateImEmojiPackage.setCreateDate("CreateDate");
		updateImEmojiPackage.setRemark("Remark");
		updateImEmojiPackage.setRemark2("Remark2");
		updateImEmojiPackage.setRemark3("Remark3");
		updateImEmojiPackage.setRemark4("Remark4");

		imEmojiPackageService.updateImEmojiPackage(updateImEmojiPackage );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找IM表情包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findImEmojiPackage() throws TsfaServiceException{
		FindImEmojiPackage findImEmojiPackage = new FindImEmojiPackage();
//		findImEmojiPackage.setId(1L);
		imEmojiPackageService.findImEmojiPackage(findImEmojiPackage);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找IM表情包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findImEmojiPackagePage() throws TsfaServiceException{
		FindImEmojiPackagePage findImEmojiPackagePage = new FindImEmojiPackagePage();
		Page<FindImEmojiPackagePageReturn> page = imEmojiPackageService.findImEmojiPackagePage(findImEmojiPackagePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除IM表情包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void delImEmojiPackage() throws TsfaServiceException{
		DelImEmojiPackage delImEmojiPackage = new DelImEmojiPackage();
//		delImEmojiPackage.setId(2L);
		Assert.assertNotNull(imEmojiPackageService.delImEmojiPackage(delImEmojiPackage));
		
	}
	
	
}
