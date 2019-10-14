package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.company.AddDealerApply;
import com.lj.business.member.dto.company.Bank;
import com.lj.business.member.dto.company.DelDealerApply;
import com.lj.business.member.dto.company.FindDealerApply;
import com.lj.business.member.dto.company.FindDealerApplyPage;
import com.lj.business.member.dto.company.FindDealerApplyPageReturn;
import com.lj.business.member.dto.company.UpdateDealerApply;
import com.lj.business.member.emus.DealerApplyStatus;
import com.lj.business.member.service.IDealerApplyService;


/**
 * 
 * 
 * 类说明：经销商申请测试类
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
public class DealerApplyServiceImplTest extends SpringTestCase{

	@Resource
	private IDealerApplyService dealerApplyService;

	/**
	 * 
	 *
	 * 方法说明：添加经销商申请信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addDealerApply() throws TsfaServiceException{
		AddDealerApply addDealerApply = new AddDealerApply();
		//add数据录入
		addDealerApply.setDealerCode("DealerCode");
		addDealerApply.setDealerName("DealerName");
		addDealerApply.setBankcardNo("BankcardNo");
		addDealerApply.setBankCode("BankCode");
		addDealerApply.setBankName("BankName");
		addDealerApply.setCustName("CustName");
		addDealerApply.setProvinceCode("ProvinceCode");
		addDealerApply.setProvinceName("ProvinceName");
		addDealerApply.setCityCode("CityCode");
		addDealerApply.setCityName("CityName");
		addDealerApply.setBranch("Branch");
//		addDealerApply.setBankno("Bankno");
		addDealerApply.setMerchantNo("MerchantNo");
		
		Assert.assertNotNull(dealerApplyService.addDealerApply(addDealerApply));
	}
	
//	@Test
//    public void bankCodes() throws TsfaServiceException{
//        List<Bank> bankCodes = dealerApplyService.getBankCodes();
//        System.out.println(bankCodes);
//    }
	
	/**
	 * 
	 *
	 * 方法说明：修改经销商申请信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateDealerApply() throws TsfaServiceException{
		UpdateDealerApply updateDealerApply = new UpdateDealerApply();
		//update数据录入
		updateDealerApply.setCode("Code");
		updateDealerApply.setDealerNo("DealerNo");
		updateDealerApply.setDealerName("DealerName");
//		updateDealerApply.setApplyStatus(DealerApplyStatus.APPLY);
		updateDealerApply.setBankcardNo("BankcardNo");
		updateDealerApply.setBankCode("BankCode");
		updateDealerApply.setBankName("BankName");
		updateDealerApply.setCustName("CustName");
		updateDealerApply.setProvinceCode("ProvinceCode");
		updateDealerApply.setProvinceName("ProvinceName");
		updateDealerApply.setCityCode("CityCode");
		updateDealerApply.setCityName("CityName");
		updateDealerApply.setBranch("Branch");
//		updateDealerApply.setBankno("Bankno");
		updateDealerApply.setAuditNo("AuditNo");
		updateDealerApply.setAuditName("AuditName");
		updateDealerApply.setAuditTime(new Date());
		updateDealerApply.setRemark("Remark");
		updateDealerApply.setRemark2("Remark2");
		updateDealerApply.setRemark3("Remark3");
		updateDealerApply.setRemark4("Remark4");

		dealerApplyService.updateDealerApply(updateDealerApply );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找经销商申请信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findDealerApply() throws TsfaServiceException{
		FindDealerApply findDealerApply = new FindDealerApply();
		findDealerApply.setCode("");
		dealerApplyService.findDealerApply(findDealerApply);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找经销商申请信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findDealerApplyPage() throws TsfaServiceException{
		FindDealerApplyPage findDealerApplyPage = new FindDealerApplyPage();
		Page<FindDealerApplyPageReturn> page = dealerApplyService.findDealerApplyPage(findDealerApplyPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除经销商申请信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delDealerApply() throws TsfaServiceException{
		DelDealerApply delDealerApply = new DelDealerApply();
		delDealerApply.setCode("");
		Assert.assertNotNull(dealerApplyService.delDealerApply(delDealerApply));
		
	}	
}
