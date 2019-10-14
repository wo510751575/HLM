package com.ye.business.hx.test;

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

import java.util.List;

import com.ye.business.hx.dto.PayDetailDto;
import com.ye.business.hx.dto.FindPayDetailPage;
import com.ye.business.hx.service.IPayDetailService;

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
public class PayDetailServiceImplTest extends SpringTestCase{

	@Resource
	IPayDetailService payDetailService;



	/**
	 * 
	 *
	 * 方法说明：添加收费项目详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addPayDetail() throws TsfaServiceException{
		PayDetailDto payDetailDto = new PayDetailDto();
		//add数据录入
		payDetailDto.setCode("Code");
		payDetailDto.setBillCode("BillCode");
		payDetailDto.setOperateCode("OperateCode");
		payDetailDto.setProjectCode("ProjectCode");
		payDetailDto.setProjectName("ProjectName");
		payDetailDto.setPayAmount(0L);
		payDetailDto.setReallyAmount(0L);
		payDetailDto.setDebtAmount(0L);
		payDetailDto.setOriginalPayAmount(0L);
		payDetailDto.setOriginalReallyAmount(0L);
		payDetailDto.setOriginalDebtAmount(0L);
		
		payDetailService.addPayDetail(payDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改收费项目详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updatePayDetail() throws TsfaServiceException{
		PayDetailDto payDetailDto = new PayDetailDto();
		//update数据录入
		payDetailDto.setCode("Code");
		payDetailDto.setBillCode("BillCode");
		payDetailDto.setOperateCode("OperateCode");
		payDetailDto.setProjectCode("ProjectCode");
		payDetailDto.setProjectName("ProjectName");
		payDetailDto.setPayAmount(0L);
		payDetailDto.setReallyAmount(0L);
		payDetailDto.setDebtAmount(0L);
		payDetailDto.setOriginalPayAmount(0L);
		payDetailDto.setOriginalReallyAmount(0L);
		payDetailDto.setOriginalDebtAmount(0L);

		payDetailService.updatePayDetail(payDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找收费项目详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPayDetail() throws TsfaServiceException{
		PayDetailDto findPayDetail = new PayDetailDto();
		findPayDetail.setCode("111");
		payDetailService.findPayDetail(findPayDetail);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找收费项目详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPayDetailPage() throws TsfaServiceException{
		FindPayDetailPage findPayDetailPage = new FindPayDetailPage();
		Page<PayDetailDto> page = payDetailService.findPayDetailPage(findPayDetailPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找收费项目详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPayDetails() throws TsfaServiceException{
		FindPayDetailPage findPayDetailPage = new FindPayDetailPage();
		List<PayDetailDto> page = payDetailService.findPayDetails(findPayDetailPage);
		Assert.assertNotNull(page);
		
	}
	
}
