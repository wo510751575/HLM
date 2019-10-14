package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */


import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.AddUnContactTotal;
import com.lj.business.st.dto.AddUnContactTotalReturn;
import com.lj.business.st.dto.DelUnContactTotal;
import com.lj.business.st.dto.DelUnContactTotalReturn;
import com.lj.business.st.dto.FindUnContactTotal;
import com.lj.business.st.dto.FindUnContactTotalInfo;
import com.lj.business.st.dto.FindUnContactTotalInfoReturn;
import com.lj.business.st.dto.FindUnContactTotalPage;
import com.lj.business.st.dto.FindUnContactTotalPageReturn;
import com.lj.business.st.dto.FindUnContactTotalReturn;
import com.lj.business.st.dto.UpdateUnContactTotal;
import com.lj.business.st.dto.UpdateUnContactTotalReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IUnContactTotalService {
	
	/**
	 * 
	 *
	 * 方法说明：添加未联系客户统计信息信息
	 *
	 * @param addUnContactTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public AddUnContactTotalReturn addUnContactTotal(AddUnContactTotal addUnContactTotal) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找未联系客户统计信息信息
	 *
	 * @param findUnContactTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public FindUnContactTotalReturn findUnContactTotal(FindUnContactTotal findUnContactTotal) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除未联系客户统计信息信息
	 *
	 * @param delUnContactTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public DelUnContactTotalReturn delUnContactTotal(DelUnContactTotal delUnContactTotal) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改未联系客户统计信息信息
	 *
	 * @param updateUnContactTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public UpdateUnContactTotalReturn updateUnContactTotal(UpdateUnContactTotal updateUnContactTotal)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找未联系客户统计信息信息
	 *
	 * @param findUnContactTotalPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public Page<FindUnContactTotalPageReturn> findUnContactTotalPage(FindUnContactTotalPage findUnContactTotalPage) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明：查找未联系客户统计信息信息_客户提醒用
	 *
	 * @param findUnContactTotalPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public FindUnContactTotalInfoReturn findUnContactTotalInfo(FindUnContactTotalInfo findUnContactTotalInfo) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询所有导购的记录
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月10日
	 *
	 */
	public List<FindUnContactTotalReturn> findList()throws TsfaServiceException;

}
