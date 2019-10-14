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
import com.lj.business.weixin.dto.imemoji.AddImEmoji;
import com.lj.business.weixin.dto.imemoji.DelImEmoji;
import com.lj.business.weixin.dto.imemoji.FindImEmoji;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPageReturn;
import com.lj.business.weixin.dto.imemoji.UpdateImEmoji;
import com.lj.business.weixin.service.IImEmojiService;


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
public class ImEmojiServiceImplTest extends SpringTestCase{

	@Resource
	IImEmojiService imEmojiService;



	/**
	 * 
	 *
	 * 方法说明：添加IM表情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void addImEmoji() throws TsfaServiceException{
		AddImEmoji addImEmoji = new AddImEmoji();
		//add数据录入
		addImEmoji.setCode("Code");
		addImEmoji.setPackageCode("PackageCode");
		addImEmoji.setEmojiName("EmojiName");
		addImEmoji.setEmojiUrl("EmojiUrl");
//		addImEmoji.setVersion("Version");
//		addImEmoji.setStatus("Status");
//		addImEmoji.setShowIndex("ShowIndex");
		addImEmoji.setCreateId("CreateId");
//		addImEmoji.setCreateDate("CreateDate");
		addImEmoji.setRemark("Remark");
		addImEmoji.setRemark2("Remark2");
		addImEmoji.setRemark3("Remark3");
		addImEmoji.setRemark4("Remark4");
		
		Assert.assertNotNull(imEmojiService.addImEmoji(addImEmoji ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改IM表情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void updateImEmoji() throws TsfaServiceException{
		UpdateImEmoji updateImEmoji = new UpdateImEmoji();
		//update数据录入
		updateImEmoji.setCode("Code");
		updateImEmoji.setPackageCode("PackageCode");
		updateImEmoji.setEmojiName("EmojiName");
		updateImEmoji.setEmojiUrl("EmojiUrl");
//		updateImEmoji.setVersion("Version");
//		updateImEmoji.setStatus("Status");
//		updateImEmoji.setShowIndex("ShowIndex");
		updateImEmoji.setCreateId("CreateId");
//		updateImEmoji.setCreateDate("CreateDate");
		updateImEmoji.setRemark("Remark");
		updateImEmoji.setRemark2("Remark2");
		updateImEmoji.setRemark3("Remark3");
		updateImEmoji.setRemark4("Remark4");

		imEmojiService.updateImEmoji(updateImEmoji );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找IM表情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findImEmoji() throws TsfaServiceException{
		FindImEmoji findImEmoji = new FindImEmoji();
//		findImEmoji.setId(1L);
		imEmojiService.findImEmoji(findImEmoji);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找IM表情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void findImEmojiPage() throws TsfaServiceException{
		FindImEmojiPage findImEmojiPage = new FindImEmojiPage();
		Page<FindImEmojiPageReturn> page = imEmojiService.findImEmojiPage(findImEmojiPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除IM表情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@Test
	public void delImEmoji() throws TsfaServiceException{
		DelImEmoji delImEmoji = new DelImEmoji();
//		delImEmoji.setId(2L);
		Assert.assertNotNull(imEmojiService.delImEmoji(delImEmoji));
		
	}
	
	
}
