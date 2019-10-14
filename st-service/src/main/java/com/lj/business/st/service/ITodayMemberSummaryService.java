package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.tms.AddTodayMemberSummary;
import com.lj.business.st.dto.tms.AddTodayMemberSummaryReturn;
import com.lj.business.st.dto.tms.DelTodayMemberSummary;
import com.lj.business.st.dto.tms.DelTodayMemberSummaryReturn;
import com.lj.business.st.dto.tms.FindTodayMemberSummary;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryPage;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryPageReturn;
import com.lj.business.st.dto.tms.FindTodayMemberSummaryReturn;
import com.lj.business.st.dto.tms.UpdateTodayMemberSummary;
import com.lj.business.st.dto.tms.UpdateTodayMemberSummaryReturn;


/**
 * 
 * 
 * 类说明：今日客户汇总接口类
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
public interface ITodayMemberSummaryService {
	
	/**
	 * 
	 *
	 * 方法说明：添加今日客户汇总信息
	 *
	 * @param addTodayMemberSummary
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddTodayMemberSummaryReturn addTodayMemberSummary(AddTodayMemberSummary addTodayMemberSummary) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找今日客户汇总信息
	 *
	 * @param findTodayMemberSummary
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindTodayMemberSummaryReturn findTodayMemberSummary(FindTodayMemberSummary findTodayMemberSummary) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除今日客户汇总信息
	 *
	 * @param delTodayMemberSummary
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelTodayMemberSummaryReturn delTodayMemberSummary(DelTodayMemberSummary delTodayMemberSummary) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改今日客户汇总信息
	 *
	 * @param updateTodayMemberSummary
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateTodayMemberSummaryReturn updateTodayMemberSummary(UpdateTodayMemberSummary updateTodayMemberSummary)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找今日客户汇总信息
	 *
	 * @param findTodayMemberSummaryPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindTodayMemberSummaryPageReturn> findTodayMemberSummaryPage(FindTodayMemberSummaryPage findTodayMemberSummaryPage) throws TsfaServiceException;
}
