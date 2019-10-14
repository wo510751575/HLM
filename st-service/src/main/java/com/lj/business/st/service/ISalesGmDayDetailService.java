package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.FindSalesGmDayFirstCompleteRateDto;
import com.lj.business.st.dto.FindSalesGmDayFirstCompleteRateReturn;
import com.lj.business.st.dto.FindSalesGmDayFirstIndex;
import com.lj.business.st.dto.salesGmDayDetail.AddSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.DelSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailFirstList;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailReturn;
import com.lj.business.st.dto.salesGmDayDetail.UpdateSalesGmDayDetail;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface ISalesGmDayDetailService {
	
	/**
	 * 
	 *
	 * 方法说明：添加其他任务完成情况表信息
	 *
	 * @param addSalesGmDayDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addSalesGmDayDetail(AddSalesGmDayDetail addSalesGmDayDetail) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找其他任务完成情况表信息
	 *
	 * @param findSalesGmDayDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindSalesGmDayDetailReturn findSalesGmDayDetail(FindSalesGmDayDetail findSalesGmDayDetail) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除其他任务完成情况表信息
	 *
	 * @param delSalesGmDayDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delSalesGmDayDetail(DelSalesGmDayDetail delSalesGmDayDetail) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改其他任务完成情况表信息
	 *
	 * @param updateSalesGmDayDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateSalesGmDayDetail(UpdateSalesGmDayDetail updateSalesGmDayDetail)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询当天区域的导购销售冠军
	 *
	 * @param findSalesGmDayFirstIndex
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月1日
	 *
	 */
	public FindSalesGmDayDetailReturn findSalesGmDayDetailFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：区域冠军完成率
	 *
	 * @param findSalesGmDayFirstCompleteRateDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月22日
	 *
	 */
	public FindSalesGmDayFirstCompleteRateReturn findSalesGmDayFirstCompleteRate(FindSalesGmDayFirstCompleteRateDto findSalesGmDayFirstCompleteRateDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：添加或修改导购销售额日明细表
	 *
	 * @param addSalesGmDayDetail
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月1日
	 *
	 */
	public void addOrUpdateSalesGmDayDetail(AddSalesGmDayDetail addSalesGmDayDetail) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询昨天的导购销售日冠军明细表
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月2日
	 *
	 */
	public List<FindSalesGmDayDetailReturn> findSalesGmDayDetailFirstList(FindSalesGmDayDetailFirstList findSalesGmDayDetailFirstList)throws TsfaServiceException; 

}
