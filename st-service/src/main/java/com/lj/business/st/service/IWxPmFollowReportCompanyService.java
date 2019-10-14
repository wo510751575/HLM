package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportCompanyReturn;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportCompanyReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyPageReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyReturn;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportCompanyReturn;


/**
 * 
 * 
 * 类说明：经销商微信客户跟踪日报接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年08月06日
 */
public interface IWxPmFollowReportCompanyService {
	
	/**
	 * 
	 *
	 * 方法说明：添加经销商微信客户跟踪日报信息
	 *
	 * @param addWxPmFollowReportCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年08月06日
	 *
	 */
	public AddWxPmFollowReportCompanyReturn addWxPmFollowReportCompany(AddWxPmFollowReportCompany addWxPmFollowReportCompany) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找经销商微信客户跟踪日报信息
	 *
	 * @param findWxPmFollowReportCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年08月06日
	 *
	 */
	public FindWxPmFollowReportCompanyReturn findWxPmFollowReportCompany(FindWxPmFollowReportCompany findWxPmFollowReportCompany) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除经销商微信客户跟踪日报信息
	 *
	 * @param delWxPmFollowReportCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年08月06日
	 *
	 */
	public DelWxPmFollowReportCompanyReturn delWxPmFollowReportCompany(DelWxPmFollowReportCompany delWxPmFollowReportCompany) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改经销商微信客户跟踪日报信息
	 *
	 * @param updateWxPmFollowReportCompany
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年08月06日
	 *
	 */
	public UpdateWxPmFollowReportCompanyReturn updateWxPmFollowReportCompany(UpdateWxPmFollowReportCompany updateWxPmFollowReportCompany)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找经销商微信客户跟踪日报信息
	 *
	 * @param findWxPmFollowReportCompanyPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年08月06日
	 *
	 */
	public Page<FindWxPmFollowReportCompanyPageReturn> findWxPmFollowReportCompanyPage(FindWxPmFollowReportCompanyPage findWxPmFollowReportCompanyPage) throws TsfaServiceException;
}
