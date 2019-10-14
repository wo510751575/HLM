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
import com.lj.business.st.dto.AddStList;
import com.lj.business.st.dto.AddStListReturn;
import com.lj.business.st.dto.DelStList;
import com.lj.business.st.dto.DelStListReturn;
import com.lj.business.st.dto.FindStList;
import com.lj.business.st.dto.FindStListPage;
import com.lj.business.st.dto.FindStListPageReturn;
import com.lj.business.st.dto.FindStListReturn;
import com.lj.business.st.dto.InitStListByMerchant;
import com.lj.business.st.dto.UpdateStList;
import com.lj.business.st.dto.UpdateStListReturn;


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
public interface IStListService {
	
	/**
	 * 
	 *
	 * 方法说明：添加报表项目信息
	 *
	 * @param addStList
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddStListReturn addStList(AddStList addStList) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找报表项目信息
	 *
	 * @param findStList
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindStListReturn findStList(FindStList findStList) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除报表项目信息
	 *
	 * @param delStList
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelStListReturn delStList(DelStList delStList) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改报表项目信息
	 *
	 * @param updateStList
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateStListReturn updateStList(UpdateStList updateStList)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找报表项目信息
	 *
	 * @param findStListPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindStListPageReturn> findStListPage(FindStListPage findStListPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询所有有效报表项目信息
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月19日
	 *
	 */
	public List<FindStListPageReturn> findAllVaildStList() throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：新增商户初始化统计报表
	 *
	 * @param initStListByMerchant
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月19日
	 *
	 */
	public void initStListByMerchant(InitStListByMerchant initStListByMerchant) throws TsfaServiceException;
} 
