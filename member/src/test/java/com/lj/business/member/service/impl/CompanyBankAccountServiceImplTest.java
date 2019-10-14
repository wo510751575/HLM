package com.lj.business.member.service.impl;

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
import com.lj.business.enums.BankCode;
import com.lj.business.member.dto.company.AddCompanyBankAccount;
import com.lj.business.member.dto.company.DelCompanyBankAccount;
import com.lj.business.member.dto.company.FindCompanyBankAccount;
import com.lj.business.member.dto.company.FindCompanyBankAccountPage;
import com.lj.business.member.dto.company.FindCompanyBankAccountPageReturn;
import com.lj.business.member.dto.company.UpdateCompanyBankAccount;
import com.lj.business.member.service.ICompanyBankAccountService;


/**
 * 
 * 
 * 类说明：分公司银行账户测试类
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
public class CompanyBankAccountServiceImplTest extends SpringTestCase{

	@Resource
	private ICompanyBankAccountService companyBankAccountService;

	/**
	 * 
	 *
	 * 方法说明：添加分公司银行账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addCompanyBankAccount() throws TsfaServiceException{
		AddCompanyBankAccount addCompanyBankAccount = new AddCompanyBankAccount();
		//add数据录入
		addCompanyBankAccount.setCompanyNo("47a9c4d75d154b42bc008a9cfd6b1799");
		addCompanyBankAccount.setCompanyName("经销商");
		addCompanyBankAccount.setBankcardNo("6222035465278953211");
		addCompanyBankAccount.setBankCode(BankCode.ICBC);
		addCompanyBankAccount.setCustName("姓名");
		addCompanyBankAccount.setBranch("荔枝分行");
		addCompanyBankAccount.setIsDefault(true);
		addCompanyBankAccount.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		
		Assert.assertNotNull(companyBankAccountService.addCompanyBankAccount(addCompanyBankAccount ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改分公司银行账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateCompanyBankAccount() throws TsfaServiceException{
		UpdateCompanyBankAccount updateCompanyBankAccount = new UpdateCompanyBankAccount();
		//update数据录入
		updateCompanyBankAccount.setCode("b3bcd17e29434e7a9bc5f9da7c257c2f");
		updateCompanyBankAccount.setBankcardNo("6222035465278953211");
		updateCompanyBankAccount.setBankCode(BankCode.ICBC);
		updateCompanyBankAccount.setCustName("经销商姓名");
		updateCompanyBankAccount.setBranch("荔枝分行");
		updateCompanyBankAccount.setIsDefault(true);

		companyBankAccountService.updateCompanyBankAccount(updateCompanyBankAccount );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找分公司银行账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findCompanyBankAccount() throws TsfaServiceException{
		FindCompanyBankAccount findCompanyBankAccount = new FindCompanyBankAccount();
		findCompanyBankAccount.setCode("b3bcd17e29434e7a9bc5f9da7c257c2f");
		companyBankAccountService.findCompanyBankAccount(findCompanyBankAccount);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找分公司银行账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findCompanyBankAccountPage() throws TsfaServiceException{
		FindCompanyBankAccountPage findCompanyBankAccountPage = new FindCompanyBankAccountPage();
		findCompanyBankAccountPage.setCompanyNo("47a9c4d75d154b42bc008a9cfd6b1799");
		findCompanyBankAccountPage.setCompanyName("商");
//		findCompanyBankAccountPage.setIsDefault(1);
		findCompanyBankAccountPage.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		findCompanyBankAccountPage.setBankCode(BankCode.ICBC.name());
		Page<FindCompanyBankAccountPageReturn> page = companyBankAccountService.findCompanyBankAccountPage(findCompanyBankAccountPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除分公司银行账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delCompanyBankAccount() throws TsfaServiceException{
		DelCompanyBankAccount delCompanyBankAccount = new DelCompanyBankAccount();
		delCompanyBankAccount.setCode("b3bcd17e29434e7a9bc5f9da7c257c2f");
		Assert.assertNotNull(companyBankAccountService.delCompanyBankAccount(delCompanyBankAccount));
	}	
	
	/**
	 * 
	 *
	 * 方法说明：设置为默认账户
	 *
	 * @param code
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月26日
	 *
	 */
	@Test
	public void updateDefaultAccount() {
		companyBankAccountService.updateDefaultAccount("b3bcd17e29434e7a9bc5f9da7c257c2f");
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询分公司结算账户：
	 * <li>1、如果有默认账户，则返回默认账户</li>
	 * <li>2、如果没有默认账户，则返回创建时间最早的账户</li>
	 *
	 * @param companyNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月26日
	 *
	 */
	@Test
	public void findSettleAccountByCompany() {
		companyBankAccountService.findSettleAccountByCompany("47a9c4d75d154b42bc008a9cfd6b1799");
	}
}
