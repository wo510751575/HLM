package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.salesGmDayFirst.AddSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.DelSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.UpdateSalesGmDayFirst;
import com.lj.business.st.service.ISalesGmDayFirstService;


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
public class SalesGmDayFirstServiceImplTest extends SpringTestCase{

	@Resource
	ISalesGmDayFirstService salesGmDayFirstService;



	/**
	 * 
	 *
	 * 方法说明：添加其他任务完成情况表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addSalesGmDayFirst() throws TsfaServiceException{
		AddSalesGmDayFirst addSalesGmDayFirst = new AddSalesGmDayFirst();
		//add数据录入
		addSalesGmDayFirst.setCode("Code");
		addSalesGmDayFirst.setMerchantNo("MerchantNo");
		addSalesGmDayFirst.setAreaCode("AreaCode");
		addSalesGmDayFirst.setAreaName("AreaName");
		addSalesGmDayFirst.setShopNo("ShopNo");
		addSalesGmDayFirst.setShopName("ShopName");
		addSalesGmDayFirst.setMemberNoGm("MemberNoGm");
		addSalesGmDayFirst.setMemberNameGm("MemberNameGm");
		addSalesGmDayFirst.setHeadAddress("HeadAddress");
		addSalesGmDayFirst.setNumSale(1L);
		addSalesGmDayFirst.setNumSaleTarget(1L);
		addSalesGmDayFirst.setDaySt(new Date());
		addSalesGmDayFirst.setUpdateDate(new Date());
		addSalesGmDayFirst.setCreateDate(new Date());
		
		salesGmDayFirstService.addSalesGmDayFirst(addSalesGmDayFirst );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改其他任务完成情况表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void updateSalesGmDayFirst() throws TsfaServiceException{
		UpdateSalesGmDayFirst updateSalesGmDayFirst = new UpdateSalesGmDayFirst();
		//update数据录入
		updateSalesGmDayFirst.setCode("Code");
		updateSalesGmDayFirst.setMerchantNo("MerchantNo");
		updateSalesGmDayFirst.setAreaCode("AreaCode");
		updateSalesGmDayFirst.setAreaName("AreaName");
		updateSalesGmDayFirst.setShopNo("ShopNo");
		updateSalesGmDayFirst.setShopName("ShopName");
		updateSalesGmDayFirst.setMemberNoGm("MemberNoGm");
		updateSalesGmDayFirst.setMemberNameGm("MemberNameGm");
		updateSalesGmDayFirst.setHeadAddress("HeadAddress");
		updateSalesGmDayFirst.setNumSale(1L);
		updateSalesGmDayFirst.setNumSaleTarget(1L);
		updateSalesGmDayFirst.setDaySt(new Date());
		updateSalesGmDayFirst.setUpdateDate(new Date());
		updateSalesGmDayFirst.setCreateDate(new Date());

		salesGmDayFirstService.updateSalesGmDayFirst(updateSalesGmDayFirst );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找其他任务完成情况表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findSalesGmDayFirst() throws TsfaServiceException{
		FindSalesGmDayFirst findSalesGmDayFirst = new FindSalesGmDayFirst();
		findSalesGmDayFirst.setCode("111");
		salesGmDayFirstService.findSalesGmDayFirst(findSalesGmDayFirst);
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除其他任务完成情况表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void delSalesGmDayFirst() throws TsfaServiceException{
		DelSalesGmDayFirst delSalesGmDayFirst = new DelSalesGmDayFirst();
		delSalesGmDayFirst.setCode("111");
		salesGmDayFirstService.delSalesGmDayFirst(delSalesGmDayFirst);
		
	}
	
	
}
