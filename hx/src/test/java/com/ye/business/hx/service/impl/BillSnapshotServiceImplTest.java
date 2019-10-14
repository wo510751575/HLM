package com.ye.business.hx.service.impl;

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

import java.util.Date;
import java.util.List;

import com.ye.business.hx.dto.BillSnapshotDto;
import com.ye.business.hx.dto.FindBillSnapshotPage;
import com.ye.business.hx.service.IBillSnapshotService;

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
public class BillSnapshotServiceImplTest extends SpringTestCase{

	@Resource
	IBillSnapshotService billSnapshotService;



	/**
	 * 
	 *
	 * 方法说明：添加账单快照信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addBillSnapshot() throws TsfaServiceException{
		BillSnapshotDto billSnapshotDto = new BillSnapshotDto();
		//add数据录入
		billSnapshotDto.setCode("Code");
		billSnapshotDto.setBillCode("BillCode");
		billSnapshotDto.setOperateCode("OperateCode");
		billSnapshotDto.setPatientNo("PatientNo");
		billSnapshotDto.setPatientName("PatientName");
		billSnapshotDto.setMedicalNo("MedicalNo");
		billSnapshotDto.setShopNo("ShopNo");
		billSnapshotDto.setShopName("ShopName");
		billSnapshotDto.setMerchantNo("MerchantNo");
		billSnapshotDto.setMerchantName("MerchantName");
		billSnapshotDto.setOriginalAmount(10000L);
		billSnapshotDto.setReallyAmount(10000L);
		billSnapshotDto.setDiscountNum(10000);
		billSnapshotDto.setPayAmount(10000L);
		billSnapshotDto.setDebtAmount(0L);
		billSnapshotDto.setRtAmount(0L);
		billSnapshotDto.setPayStatus("PayStatus");
		billSnapshotDto.setRtStatus("RtStatus");
		billSnapshotDto.setStatus("Status");
		billSnapshotDto.setClinicTime(new Date());
		billSnapshotDto.setUpdateId("UpdateId");
		billSnapshotDto.setUpdateDate(new Date());
		billSnapshotDto.setCreateId("CreateId");
		billSnapshotDto.setCreateName("CreateName");
		billSnapshotDto.setCreateDate(new Date());
		billSnapshotDto.setRemark("Remark");
		billSnapshotDto.setRemark1("Remark1");
		billSnapshotDto.setRemark2("Remark2");
		billSnapshotDto.setRemark3("Remark3");
		billSnapshotDto.setRemark4("Remark4");
		billSnapshotDto.setSnapshotTime(new Date());
		
		billSnapshotService.addBillSnapshot(billSnapshotDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改账单快照信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateBillSnapshot() throws TsfaServiceException{
		BillSnapshotDto billSnapshotDto = new BillSnapshotDto();
		//update数据录入
		billSnapshotDto.setCode("Code");
		billSnapshotDto.setBillCode("BillCode");
		billSnapshotDto.setOperateCode("OperateCode");
		billSnapshotDto.setPatientNo("PatientNo");
		billSnapshotDto.setPatientName("PatientName");
		billSnapshotDto.setMedicalNo("MedicalNo");
		billSnapshotDto.setShopNo("ShopNo");
		billSnapshotDto.setShopName("ShopName");
		billSnapshotDto.setMerchantNo("MerchantNo");
		billSnapshotDto.setMerchantName("MerchantName");
		billSnapshotDto.setOriginalAmount(10000L);
		billSnapshotDto.setReallyAmount(10000L);
		billSnapshotDto.setDiscountNum(10000);
		billSnapshotDto.setPayAmount(10000L);
		billSnapshotDto.setDebtAmount(0L);
		billSnapshotDto.setRtAmount(0L);
		billSnapshotDto.setPayStatus("PayStatus");
		billSnapshotDto.setRtStatus("RtStatus");
		billSnapshotDto.setStatus("Status");
		billSnapshotDto.setClinicTime(new Date());
		billSnapshotDto.setUpdateId("UpdateId");
		billSnapshotDto.setUpdateDate(new Date());
		billSnapshotDto.setCreateId("CreateId");
		billSnapshotDto.setCreateName("CreateName");
		billSnapshotDto.setCreateDate(new Date());
		billSnapshotDto.setRemark("Remark");
		billSnapshotDto.setRemark1("Remark1");
		billSnapshotDto.setRemark2("Remark2");
		billSnapshotDto.setRemark3("Remark3");
		billSnapshotDto.setRemark4("Remark4");
		billSnapshotDto.setSnapshotTime(new Date());
		
		billSnapshotService.updateBillSnapshot(billSnapshotDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单快照信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillSnapshot() throws TsfaServiceException{
		BillSnapshotDto findBillSnapshot = new BillSnapshotDto();
		findBillSnapshot.setCode("111");
		billSnapshotService.findBillSnapshot(findBillSnapshot);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单快照信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillSnapshotPage() throws TsfaServiceException{
		FindBillSnapshotPage findBillSnapshotPage = new FindBillSnapshotPage();
		Page<BillSnapshotDto> page = billSnapshotService.findBillSnapshotPage(findBillSnapshotPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单快照信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillSnapshots() throws TsfaServiceException{
		FindBillSnapshotPage findBillSnapshotPage = new FindBillSnapshotPage();
		List<BillSnapshotDto> page = billSnapshotService.findBillSnapshots(findBillSnapshotPage);
		Assert.assertNotNull(page);
		
	}
	
}
