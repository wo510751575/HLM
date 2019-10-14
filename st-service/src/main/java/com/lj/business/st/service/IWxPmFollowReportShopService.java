package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportShopReturn;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportShopReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPageReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopReturn;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportShopReturn;


/**
 * 
 * 
 * 类说明：门店微信客户跟踪日报接口类
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
public interface IWxPmFollowReportShopService {
	
	/**
	 * 
	 *
	 * 方法说明：添加门店微信客户跟踪日报信息
	 *
	 * @param addWxPmFollowReportShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public AddWxPmFollowReportShopReturn addWxPmFollowReportShop(AddWxPmFollowReportShop addWxPmFollowReportShop) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找门店微信客户跟踪日报信息
	 *
	 * @param findWxPmFollowReportShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public FindWxPmFollowReportShopReturn findWxPmFollowReportShop(FindWxPmFollowReportShop findWxPmFollowReportShop) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除门店微信客户跟踪日报信息
	 *
	 * @param delWxPmFollowReportShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public DelWxPmFollowReportShopReturn delWxPmFollowReportShop(DelWxPmFollowReportShop delWxPmFollowReportShop) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改门店微信客户跟踪日报信息
	 *
	 * @param updateWxPmFollowReportShop
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public UpdateWxPmFollowReportShopReturn updateWxPmFollowReportShop(UpdateWxPmFollowReportShop updateWxPmFollowReportShop)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找门店微信客户跟踪日报信息
	 *
	 * @param findWxPmFollowReportShopPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public Page<FindWxPmFollowReportShopPageReturn> findWxPmFollowReportShopPage(FindWxPmFollowReportShopPage findWxPmFollowReportShopPage) throws TsfaServiceException;
}
