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
import com.lj.business.st.dto.salesGmDayDetail.AddSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.DelSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.UpdateSalesGmDayDetail;
import com.lj.business.st.service.ISalesGmDayDetailService;


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
public class SalesGmDayDetailServiceImplTest extends SpringTestCase{

	@Resource
	ISalesGmDayDetailService salesGmDayDetailService;



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
	public void addSalesGmDayDetail() throws TsfaServiceException{
		AddSalesGmDayDetail addSalesGmDayDetail = new AddSalesGmDayDetail();
		//add数据录入
		addSalesGmDayDetail.setCode("Code");
		addSalesGmDayDetail.setMerchantNo("MerchantNo");
		addSalesGmDayDetail.setAreaCode("AreaCode");
		addSalesGmDayDetail.setAreaName("AreaName");
		addSalesGmDayDetail.setShopNo("ShopNo");
		addSalesGmDayDetail.setShopName("ShopName");
		addSalesGmDayDetail.setMemberNoGm("MemberNoGm");
		addSalesGmDayDetail.setMemberNameGm("MemberNameGm");
		addSalesGmDayDetail.setHeadAddress("HeadAddress");
		addSalesGmDayDetail.setNumSale(1L);
		addSalesGmDayDetail.setNumSaleTarget(1L);
		addSalesGmDayDetail.setDaySt(new Date());
		addSalesGmDayDetail.setUpdateDate(new Date());
		addSalesGmDayDetail.setCreateDate(new Date());
		
		salesGmDayDetailService.addSalesGmDayDetail(addSalesGmDayDetail );
		
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
	public void updateSalesGmDayDetail() throws TsfaServiceException{
		UpdateSalesGmDayDetail updateSalesGmDayDetail = new UpdateSalesGmDayDetail();
		//update数据录入
		updateSalesGmDayDetail.setCode("Code");
		updateSalesGmDayDetail.setMerchantNo("MerchantNo");
		updateSalesGmDayDetail.setAreaCode("AreaCode");
		updateSalesGmDayDetail.setAreaName("AreaName");
		updateSalesGmDayDetail.setShopNo("ShopNo");
		updateSalesGmDayDetail.setShopName("ShopName");
		updateSalesGmDayDetail.setMemberNoGm("MemberNoGm");
		updateSalesGmDayDetail.setMemberNameGm("MemberNameGm");
		updateSalesGmDayDetail.setHeadAddress("HeadAddress");
		updateSalesGmDayDetail.setNumSale(1L);
		updateSalesGmDayDetail.setNumSaleTarget(1L);
		updateSalesGmDayDetail.setDaySt(new Date());
		updateSalesGmDayDetail.setUpdateDate(new Date());
		updateSalesGmDayDetail.setCreateDate(new Date());

		salesGmDayDetailService.updateSalesGmDayDetail(updateSalesGmDayDetail );
		
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
	public void findSalesGmDayDetail() throws TsfaServiceException{
		FindSalesGmDayDetail findSalesGmDayDetail = new FindSalesGmDayDetail();
		findSalesGmDayDetail.setCode("111");
		salesGmDayDetailService.findSalesGmDayDetail(findSalesGmDayDetail);
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
	public void delSalesGmDayDetail() throws TsfaServiceException{
		DelSalesGmDayDetail delSalesGmDayDetail = new DelSalesGmDayDetail();
		delSalesGmDayDetail.setCode("111");
		salesGmDayDetailService.delSalesGmDayDetail(delSalesGmDayDetail);
		
	}
	
	
}
