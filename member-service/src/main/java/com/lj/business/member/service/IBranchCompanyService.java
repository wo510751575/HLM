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
import com.lj.business.member.dto.company.AddBranchCompany;
import com.lj.business.member.dto.company.AddBranchCompanyReturn;
import com.lj.business.member.dto.company.DelBranchCompany;
import com.lj.business.member.dto.company.DelBranchCompanyReturn;
import com.lj.business.member.dto.company.FindBranchCompany;
import com.lj.business.member.dto.company.FindBranchCompanyDetailReturn;
import com.lj.business.member.dto.company.FindBranchCompanyPage;
import com.lj.business.member.dto.company.FindBranchCompanyPageReturn;
import com.lj.business.member.dto.company.FindBranchCompanyReturn;
import com.lj.business.member.dto.company.UpdateBranchCompany;
import com.lj.business.member.dto.company.UpdateBranchCompanyReturn;


/**
 * 
 * 
 * 类说明：分公司接口类
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
public interface IBranchCompanyService {
	
	/**
	 * 
	 *
	 * 方法说明：添加分公司信息
	 *
	 * @param addBranchCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddBranchCompanyReturn addBranchCompany(AddBranchCompany addBranchCompany) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找分公司信息
	 *
	 * @param findBranchCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindBranchCompanyReturn findBranchCompany(FindBranchCompany findBranchCompany) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除分公司信息
	 *
	 * @param delBranchCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelBranchCompanyReturn delBranchCompany(DelBranchCompany delBranchCompany) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改分公司信息
	 *
	 * @param updateBranchCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateBranchCompanyReturn updateBranchCompany(UpdateBranchCompany updateBranchCompany)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找分公司信息
	 *
	 * @param findBranchCompanyPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindBranchCompanyPageReturn> findBranchCompanyPage(FindBranchCompanyPage findBranchCompanyPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查询商户下所有分公司
	 *
	 * @param merchantNo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年5月1日
	 *
	 */
	public List<FindBranchCompanyReturn> findBranchCompanyByMerchantNo(String merchantNo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据companyNo查找分公司信息
	 *
	 * @param findBranchCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2018年05月30日
	 *
	 */
	public FindBranchCompanyReturn findBranchCompanyByCompanyNo(FindBranchCompany findBranchCompany);

	/**
	 * 
	 *
	 * 方法说明：根据code查询分公司及分公司账号信息
	 *
	 * @param findBranchCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年5月31日
	 *
	 */
    FindBranchCompanyDetailReturn findBranchCompanyDetail(FindBranchCompany findBranchCompany) throws TsfaServiceException;
    
    /**
     * 
     *
     * 方法说明：根据经销商代码查询数量
     *
     * @param dealerCode
     * @return
     * @throws TsfaServiceException
     *
     * @author 许新龙 CreateDate: 2018年6月4日
     *
     */
    int findCountByDealerCode(String dealerCode) throws TsfaServiceException;
    
    /**
     * 
     *
     * 方法说明：根据经销商代码查询经销商列表
     *
     * @param dealerCode
     * @return
     * @throws TsfaServiceException
     *
     * @author 曾垂瑜 CreateDate: 2018年6月20日
     *
     */
    public List<FindBranchCompanyReturn> findBranchCompanyByDealerCode(String dealerCode) throws TsfaServiceException;
    
    /**
     * 
     *
     * 方法说明：查询所有经销商
     *
     * @return
     * @throws TsfaServiceException
     *
     * @author 许新龙 CreateDate: 2018年8月7日
     *
     */
    List<FindBranchCompanyReturn> findBranchCompanySelective(FindBranchCompanyPage findBranchCompanyPage) throws TsfaServiceException;
}
