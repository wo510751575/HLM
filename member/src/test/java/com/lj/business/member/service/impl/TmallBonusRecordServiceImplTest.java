package com.lj.business.member.service.impl;

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
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.FindTmallBonusRecordPage;
import com.lj.business.member.dto.TmallBonusRecordDto;
import com.lj.business.member.service.ITmallBonusRecordService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public class TmallBonusRecordServiceImplTest extends SpringTestCase{

	@Resource
	ITmallBonusRecordService tmallBonusRecordService;



	/**
	 * 
	 *
	 * 方法说明：添加天猫订单红包记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addTmallBonusRecord() throws TsfaServiceException{
		TmallBonusRecordDto tmallBonusRecordDto = new TmallBonusRecordDto();
		//add数据录入
		tmallBonusRecordDto.setCode("Code");
		tmallBonusRecordDto.setMerchantNo("04cc884d305346b4ac00a5842b73cfa9");
		tmallBonusRecordDto.setMemberNo("MemberNo");
		tmallBonusRecordDto.setMemberName("MemberName");
		tmallBonusRecordDto.setNoWx("NoWx");
		tmallBonusRecordDto.setOrderNo("OrderNo");
		tmallBonusRecordDto.setOrderAmt(100L);
		tmallBonusRecordDto.setBonuxAmt(1L);
		tmallBonusRecordDto.setPushTime(new Date());
		tmallBonusRecordDto.setStatus("Status");
		tmallBonusRecordDto.setRemark("Remark");
		tmallBonusRecordDto.setRemark2("Remark2");
		tmallBonusRecordDto.setRemark3("Remark3");
		tmallBonusRecordDto.setRemark4("Remark4");
		tmallBonusRecordDto.setOrderDate(new Date());
		
		tmallBonusRecordService.addTmallBonusRecord(tmallBonusRecordDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改天猫订单红包记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateTmallBonusRecord() throws TsfaServiceException{
		TmallBonusRecordDto tmallBonusRecordDto = new TmallBonusRecordDto();
		//update数据录入
		tmallBonusRecordDto.setCode("Code");
		tmallBonusRecordDto.setMerchantNo("MerchantNo");
		tmallBonusRecordDto.setMemberNo("MemberNo");
		tmallBonusRecordDto.setMemberName("MemberName");
		tmallBonusRecordDto.setNoWx("NoWx");
		tmallBonusRecordDto.setOrderNo("OrderNo");
		tmallBonusRecordDto.setOrderAmt(100L);
		tmallBonusRecordDto.setBonuxAmt(1L);
		tmallBonusRecordDto.setPushTime(new Date());
		tmallBonusRecordDto.setStatus("Status");
		tmallBonusRecordDto.setRemark("Remark");
		tmallBonusRecordDto.setRemark2("Remark2");
		tmallBonusRecordDto.setRemark3("Remark3");
		tmallBonusRecordDto.setRemark4("Remark4");
		tmallBonusRecordDto.setOrderDate(new Date());

		tmallBonusRecordService.updateTmallBonusRecord(tmallBonusRecordDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单红包记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findTmallBonusRecord() throws TsfaServiceException{
		TmallBonusRecordDto findTmallBonusRecord = new TmallBonusRecordDto();
		findTmallBonusRecord.setCode("111");
		tmallBonusRecordService.findTmallBonusRecord(findTmallBonusRecord);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单红包记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findTmallBonusRecordPage() throws TsfaServiceException{
		FindTmallBonusRecordPage findTmallBonusRecordPage = new FindTmallBonusRecordPage();
		Page<TmallBonusRecordDto> page = tmallBonusRecordService.findTmallBonusRecordPage(findTmallBonusRecordPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单红包记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findTmallBonusRecords() throws TsfaServiceException{
		FindTmallBonusRecordPage findTmallBonusRecordPage = new FindTmallBonusRecordPage();
		List<TmallBonusRecordDto> page = tmallBonusRecordService.findTmallBonusRecords(findTmallBonusRecordPage);
		Assert.assertNotNull(page);
		System.out.println(JsonUtils.jsonFromList(page));
	}
	
}
