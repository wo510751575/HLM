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
import com.lj.business.member.dto.company.AddBranchCompany;
import com.lj.business.member.dto.company.DelBranchCompany;
import com.lj.business.member.dto.company.FindBranchCompany;
import com.lj.business.member.dto.company.FindBranchCompanyPage;
import com.lj.business.member.dto.company.FindBranchCompanyPageReturn;
import com.lj.business.member.dto.company.UpdateBranchCompany;
import com.lj.business.member.emus.CompanyType;
import com.lj.business.member.service.IBranchCompanyService;


/**
 * 
 * 
 * 类说明：分公司测试类
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
public class BranchCompanyServiceImplTest extends SpringTestCase{

	@Resource
	private IBranchCompanyService branchCompanyService;

	/**
	 * 
	 *
	 * 方法说明：添加分公司信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addBranchCompany() throws TsfaServiceException{
		AddBranchCompany addBranchCompany = new AddBranchCompany();
		//add数据录入
		addBranchCompany.setCompanyNo("06f6e4e4405f48a498d477f682c9c3b6");
		addBranchCompany.setCompanyName("深圳分公司");
		addBranchCompany.setCompanyType(CompanyType.DEALER);
		addBranchCompany.setMerchantNo("96f4d5ddad504998af92bbfda6dd4153");
		
		Assert.assertNotNull(branchCompanyService.addBranchCompany(addBranchCompany ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改分公司信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateBranchCompany() throws TsfaServiceException{
		UpdateBranchCompany updateBranchCompany = new UpdateBranchCompany();
		//update数据录入
		updateBranchCompany.setCode("Code");
		updateBranchCompany.setCompanyNo("CompanyNo");
		updateBranchCompany.setCompanyName("CompanyName");
		updateBranchCompany.setStatus("Status");
		updateBranchCompany.setCompanyType(CompanyType.BRANCH);
		updateBranchCompany.setRemark("Remark");
		updateBranchCompany.setRemark2("Remark2");
		updateBranchCompany.setRemark3("Remark3");
		updateBranchCompany.setRemark4("Remark4");

		branchCompanyService.updateBranchCompany(updateBranchCompany );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找分公司信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findBranchCompany() throws TsfaServiceException{
		FindBranchCompany findBranchCompany = new FindBranchCompany();
		findBranchCompany.setCode("");
		branchCompanyService.findBranchCompany(findBranchCompany);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找分公司信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findBranchCompanyPage() throws TsfaServiceException{
		FindBranchCompanyPage findBranchCompanyPage = new FindBranchCompanyPage();
		Page<FindBranchCompanyPageReturn> page = branchCompanyService.findBranchCompanyPage(findBranchCompanyPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除分公司信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delBranchCompany() throws TsfaServiceException{
		DelBranchCompany delBranchCompany = new DelBranchCompany();
		delBranchCompany.setCode("");
		Assert.assertNotNull(branchCompanyService.delBranchCompany(delBranchCompany));
		
	}	
}
