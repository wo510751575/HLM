package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportGmReturn;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportGmReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPageReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmReturn;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportGmReturn;


/**
 * 
 * 
 * 类说明：导购微信客户跟踪日报接口类
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
public interface IWxPmFollowReportGmService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购微信客户跟踪日报信息
	 *
	 * @param addWxPmFollowReportGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public AddWxPmFollowReportGmReturn addWxPmFollowReportGm(AddWxPmFollowReportGm addWxPmFollowReportGm) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购微信客户跟踪日报信息
	 *
	 * @param findWxPmFollowReportGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public FindWxPmFollowReportGmReturn findWxPmFollowReportGm(FindWxPmFollowReportGm findWxPmFollowReportGm) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购微信客户跟踪日报信息
	 *
	 * @param delWxPmFollowReportGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public DelWxPmFollowReportGmReturn delWxPmFollowReportGm(DelWxPmFollowReportGm delWxPmFollowReportGm) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购微信客户跟踪日报信息
	 *
	 * @param updateWxPmFollowReportGm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public UpdateWxPmFollowReportGmReturn updateWxPmFollowReportGm(UpdateWxPmFollowReportGm updateWxPmFollowReportGm)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找导购微信客户跟踪日报信息
	 *
	 * @param findWxPmFollowReportGmPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public Page<FindWxPmFollowReportGmPageReturn> findWxPmFollowReportGmPage(FindWxPmFollowReportGmPage findWxPmFollowReportGmPage) throws TsfaServiceException;
}
