package com.lj.business.member.service.impl;

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
import com.lj.business.member.dto.FindGmLabelPage;
import com.lj.business.member.dto.GmLabelDto;
import com.lj.business.member.service.IGmLabelService;

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
 *         CreateDate: 2017.12.11
 */
public class GmLabelServiceImplTest extends SpringTestCase {

	@Resource
	IGmLabelService gmLabelService;

	/**
	 * 
	 *
	 * 方法说明：添加个人标签信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addGmLabel() throws TsfaServiceException {
		GmLabelDto gmLabelDto = new GmLabelDto();
		// add数据录入
		gmLabelDto.setCode("Code");
		gmLabelDto.setMerchantNo("MerchantNo");
		gmLabelDto.setLabelName("LabelName");
		gmLabelDto.setCreateId("CreateId");
		gmLabelDto.setRemark("Remark");
		gmLabelDto.setRemark2("Remark2");
		gmLabelDto.setRemark3("Remark3");
		gmLabelDto.setRemark4("Remark4");
		gmLabelDto.setMemberNoGm("MemberNoGm");
		gmLabelDto.setMemberNameGm("MemberNameGm");

		gmLabelService.addGmLabel(gmLabelDto);

	}

	/**
	 * 
	 *
	 * 方法说明：修改个人标签信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateGmLabel() throws TsfaServiceException {
		GmLabelDto gmLabelDto = new GmLabelDto();
		// update数据录入
		gmLabelDto.setCode("Code");
		gmLabelDto.setMerchantNo("MerchantNo");
		gmLabelDto.setLabelName("LabelName");
		gmLabelDto.setRemark("Remark");
		gmLabelDto.setRemark2("Remark2");
		gmLabelDto.setRemark3("Remark3");
		gmLabelDto.setRemark4("Remark4");
		gmLabelDto.setMemberNoGm("MemberNoGm");
		gmLabelDto.setMemberNameGm("MemberNameGm");

		gmLabelService.updateGmLabel(gmLabelDto);

	}

	/**
	 * 
	 *
	 * 方法说明：查找个人标签信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findGmLabel() throws TsfaServiceException {
		GmLabelDto findGmLabel = new GmLabelDto();
		findGmLabel.setCode("111");
		gmLabelService.findGmLabel(findGmLabel);
	}

	/**
	 * 
	 *
	 * 方法说明：查找个人标签信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findGmLabelPage() throws TsfaServiceException {
		FindGmLabelPage findGmLabelPage = new FindGmLabelPage();
		Page<GmLabelDto> page = gmLabelService.findGmLabelPage(findGmLabelPage);
		Assert.assertNotNull(page);

	}

	/**
	 * 
	 *
	 * 方法说明：查找个人标签信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findGmLabels() throws TsfaServiceException {
		FindGmLabelPage findGmLabelPage = new FindGmLabelPage();
		List<GmLabelDto> page = gmLabelService.findGmLabels(findGmLabelPage);
		Assert.assertNotNull(page);

	}

}
