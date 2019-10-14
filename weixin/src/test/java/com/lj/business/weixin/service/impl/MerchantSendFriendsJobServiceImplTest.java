package com.lj.business.weixin.service.impl;

import java.util.Date;
import java.util.List;

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
import com.lj.business.weixin.dto.FindMerchantSendFriendsJobPage;
import com.lj.business.weixin.dto.MerchantSendFriendsJobDto;
import com.lj.business.weixin.service.IMerchantSendFriendsJobService;
/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.11
 */
public class MerchantSendFriendsJobServiceImplTest extends SpringTestCase{

	@Resource
	IMerchantSendFriendsJobService merchantSendFriendsJobService;



	/**
	 * 
	 *
	 * 方法说明：添加商户发朋友圈任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addMerchantSendFriendsJob() throws TsfaServiceException{
		MerchantSendFriendsJobDto merchantSendFriendsJobDto = new MerchantSendFriendsJobDto();
		//add数据录入
		merchantSendFriendsJobDto.setCode("Code");
		merchantSendFriendsJobDto.setMerchantNo("MerchantNo");
		merchantSendFriendsJobDto.setMerchantName("MerchantName");
		merchantSendFriendsJobDto.setContent("Content");
		merchantSendFriendsJobDto.setImgAddr("ImgAddr");
		merchantSendFriendsJobDto.setLinkUrl("LinkUrl");
		merchantSendFriendsJobDto.setAutoComment(1);
		merchantSendFriendsJobDto.setAutoContent("AutoContent");
		merchantSendFriendsJobDto.setNoWxs("NoWxs");
		merchantSendFriendsJobDto.setExecuteTime(new Date());
		merchantSendFriendsJobDto.setRealExecuteTime(new Date());
		merchantSendFriendsJobDto.setJobState(1);
		merchantSendFriendsJobDto.setSentNoWxs("SentNoWxs");
		merchantSendFriendsJobDto.setCreateId("CreateId");
		merchantSendFriendsJobDto.setCreateUserLevel("1");
		merchantSendFriendsJobDto.setCreateDate(new Date());
		merchantSendFriendsJobDto.setType("1");
		merchantSendFriendsJobDto.setRemark("Remark");
		merchantSendFriendsJobDto.setRemark2("Remark2");
		merchantSendFriendsJobDto.setRemark3("Remark3");
		merchantSendFriendsJobDto.setRemark4("Remark4");
		merchantSendFriendsJobDto.setCreateName("CreateName");
		
		merchantSendFriendsJobService.addMerchantSendFriendsJob(merchantSendFriendsJobDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改商户发朋友圈任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateMerchantSendFriendsJob() throws TsfaServiceException{
		MerchantSendFriendsJobDto merchantSendFriendsJobDto = new MerchantSendFriendsJobDto();
		//update数据录入
		merchantSendFriendsJobDto.setCode("Code");
		merchantSendFriendsJobDto.setMerchantNo("MerchantNo");
		merchantSendFriendsJobDto.setMerchantName("MerchantName");
		merchantSendFriendsJobDto.setContent("Content");
		merchantSendFriendsJobDto.setImgAddr("ImgAddr");
		merchantSendFriendsJobDto.setLinkUrl("LinkUrl");
		merchantSendFriendsJobDto.setAutoComment(1);
		merchantSendFriendsJobDto.setAutoContent("AutoContent");
		merchantSendFriendsJobDto.setNoWxs("NoWxs");
		merchantSendFriendsJobDto.setExecuteTime(new Date());
		merchantSendFriendsJobDto.setRealExecuteTime(new Date());
		merchantSendFriendsJobDto.setJobState(1);
		merchantSendFriendsJobDto.setSentNoWxs("SentNoWxs");
		merchantSendFriendsJobDto.setCreateId("CreateId");
		merchantSendFriendsJobDto.setCreateUserLevel("1");
		merchantSendFriendsJobDto.setCreateDate(new Date());
		merchantSendFriendsJobDto.setType("2");
		merchantSendFriendsJobDto.setRemark("Remark");
		merchantSendFriendsJobDto.setRemark2("Remark2");
		merchantSendFriendsJobDto.setRemark3("Remark3");
		merchantSendFriendsJobDto.setRemark4("Remark4");
		merchantSendFriendsJobDto.setCreateName("CreateName");

		merchantSendFriendsJobService.updateMerchantSendFriendsJob(merchantSendFriendsJobDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找商户发朋友圈任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findMerchantSendFriendsJob() throws TsfaServiceException{
		MerchantSendFriendsJobDto findMerchantSendFriendsJob = new MerchantSendFriendsJobDto();
		findMerchantSendFriendsJob.setCode("111");
		merchantSendFriendsJobService.findMerchantSendFriendsJob(findMerchantSendFriendsJob);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找商户发朋友圈任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findMerchantSendFriendsJobPage() throws TsfaServiceException{
		FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage = new FindMerchantSendFriendsJobPage();
		Page<MerchantSendFriendsJobDto> page = merchantSendFriendsJobService.findMerchantSendFriendsJobPage(findMerchantSendFriendsJobPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找商户发朋友圈任务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findMerchantSendFriendsJobs() throws TsfaServiceException{
		FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage = new FindMerchantSendFriendsJobPage();
		List<MerchantSendFriendsJobDto> page = merchantSendFriendsJobService.findMerchantSendFriendsJobs(findMerchantSendFriendsJobPage);
		Assert.assertNotNull(page);
		
	}
	
}
