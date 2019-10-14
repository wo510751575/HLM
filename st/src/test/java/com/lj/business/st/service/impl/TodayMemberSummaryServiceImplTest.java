package com.lj.business.st.service.impl;

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
import com.lj.business.st.dto.tms.AddTodayMemberSummary;
import com.lj.business.st.dto.tms.DelTodayMemberSummary;
import com.lj.business.st.dto.tms.FindTodayMemberSummary;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryPage;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryPageReturn;
import com.lj.business.st.dto.tms.UpdateTodayMemberSummary;
import com.lj.business.st.service.ITodayMemberSummaryService;


/**
 * 
 * 
 * 类说明：今日客户汇总测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
public class TodayMemberSummaryServiceImplTest extends SpringTestCase{

	@Resource
	private ITodayMemberSummaryService todayMemberSummaryService;

	/**
	 * 
	 *
	 * 方法说明：添加今日客户汇总信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addTodayMemberSummary() throws TsfaServiceException{
		AddTodayMemberSummary addTodayMemberSummary = new AddTodayMemberSummary();
		//add数据录入
		addTodayMemberSummary.setMemberNoGm("MemberNoGm");
		addTodayMemberSummary.setShopCount(1);
		addTodayMemberSummary.setIntentionCount(1);
		addTodayMemberSummary.setScanCount(1);
		addTodayMemberSummary.setUnscanReason("UnscanReason");
		addTodayMemberSummary.setInfoCount(1);
		addTodayMemberSummary.setUninfoReason("UninfoReason");
		
		Assert.assertNotNull(todayMemberSummaryService.addTodayMemberSummary(addTodayMemberSummary ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改今日客户汇总信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateTodayMemberSummary() throws TsfaServiceException{
		UpdateTodayMemberSummary updateTodayMemberSummary = new UpdateTodayMemberSummary();
		//update数据录入
		updateTodayMemberSummary.setCode("Code");
		updateTodayMemberSummary.setMemberNoGm("MemberNoGm");
		updateTodayMemberSummary.setShopCount(1);
		updateTodayMemberSummary.setIntentionCount(1);
		updateTodayMemberSummary.setScanCount(1);
		updateTodayMemberSummary.setUnscanReason("UnscanReason");
		updateTodayMemberSummary.setInfoCount(1);
		updateTodayMemberSummary.setUninfoReason("UninfoReason");

		todayMemberSummaryService.updateTodayMemberSummary(updateTodayMemberSummary );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找今日客户汇总信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findTodayMemberSummary() throws TsfaServiceException{
		FindTodayMemberSummary findTodayMemberSummary = new FindTodayMemberSummary();
		findTodayMemberSummary.setCode("");
		todayMemberSummaryService.findTodayMemberSummary(findTodayMemberSummary);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找今日客户汇总信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findTodayMemberSummaryPage() throws TsfaServiceException{
		FindTodayMemberSummaryPage findTodayMemberSummaryPage = new FindTodayMemberSummaryPage();
		Page<FindTodayMemberSummaryPageReturn> page = todayMemberSummaryService.findTodayMemberSummaryPage(findTodayMemberSummaryPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除今日客户汇总信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delTodayMemberSummary() throws TsfaServiceException{
		DelTodayMemberSummary delTodayMemberSummary = new DelTodayMemberSummary();
		delTodayMemberSummary.setCode("");
		Assert.assertNotNull(todayMemberSummaryService.delTodayMemberSummary(delTodayMemberSummary));
		
	}	
}
