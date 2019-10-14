package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.company.AddCompanyBankAccount;
import com.lj.business.member.dto.company.AddCompanyBankAccountReturn;
import com.lj.business.member.dto.company.DelCompanyBankAccount;
import com.lj.business.member.dto.company.DelCompanyBankAccountReturn;
import com.lj.business.member.dto.company.FindCompanyBankAccount;
import com.lj.business.member.dto.company.FindCompanyBankAccountPage;
import com.lj.business.member.dto.company.FindCompanyBankAccountPageReturn;
import com.lj.business.member.dto.company.FindCompanyBankAccountReturn;
import com.lj.business.member.dto.company.UpdateCompanyBankAccount;
import com.lj.business.member.dto.company.UpdateCompanyBankAccountReturn;


/**
 * 
 * 
 * 类说明：分公司银行账户接口类
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
public interface ICompanyBankAccountService {
	
	/**
	 * 
	 *
	 * 方法说明：添加分公司银行账户信息
	 *
	 * @param addCompanyBankAccount
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddCompanyBankAccountReturn addCompanyBankAccount(AddCompanyBankAccount addCompanyBankAccount) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找分公司银行账户信息
	 *
	 * @param findCompanyBankAccount
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindCompanyBankAccountReturn findCompanyBankAccount(FindCompanyBankAccount findCompanyBankAccount) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除分公司银行账户信息
	 *
	 * @param delCompanyBankAccount
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelCompanyBankAccountReturn delCompanyBankAccount(DelCompanyBankAccount delCompanyBankAccount) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改分公司银行账户信息
	 *
	 * @param updateCompanyBankAccount
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateCompanyBankAccountReturn updateCompanyBankAccount(UpdateCompanyBankAccount updateCompanyBankAccount)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找分公司银行账户信息
	 *
	 * @param findCompanyBankAccountPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindCompanyBankAccountPageReturn> findCompanyBankAccountPage(FindCompanyBankAccountPage findCompanyBankAccountPage) throws TsfaServiceException;
	
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
	public void updateDefaultAccount(String code);
	
	/**
	 * 
	 *
	 * 方法说明：查询分公司结算账户：按默认账户、创建时间排序
	 *
	 * @param companyNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月26日
	 *
	 */
	public List<FindCompanyBankAccountReturn> findSettleAccountByCompany(String companyNo);

    UpdateCompanyBankAccountReturn updateCompanyBankAccountByCompanyNo(UpdateCompanyBankAccount updateCompanyBankAccount) throws TsfaServiceException;
}
