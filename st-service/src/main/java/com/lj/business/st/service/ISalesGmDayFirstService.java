package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.FindSalesGmDayFirstIndex;
import com.lj.business.st.dto.FindSalesGmDayFirstIndexReturn;
import com.lj.business.st.dto.salesGmDayFirst.AddSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.DelSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirstReturn;
import com.lj.business.st.dto.salesGmDayFirst.UpdateSalesGmDayFirst;


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
public interface ISalesGmDayFirstService {
	
	/**
	 * 
	 *
	 * 方法说明：添加其他任务完成情况表信息
	 *
	 * @param addSalesGmDayFirst
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addSalesGmDayFirst(AddSalesGmDayFirst addSalesGmDayFirst) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找其他任务完成情况表信息
	 *
	 * @param findSalesGmDayFirst
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindSalesGmDayFirstReturn findSalesGmDayFirst(FindSalesGmDayFirst findSalesGmDayFirst) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除其他任务完成情况表信息
	 *
	 * @param delSalesGmDayFirst
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delSalesGmDayFirst(DelSalesGmDayFirst delSalesGmDayFirst) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改其他任务完成情况表信息
	 *
	 * @param updateSalesGmDayFirst
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateSalesGmDayFirst(UpdateSalesGmDayFirst updateSalesGmDayFirst)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：app首页导购区域冠军
	 *
	 * @param findSalesGmDayFirstIndex
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月1日
	 *
	 */
	public List<FindSalesGmDayFirstIndexReturn> findSalesGmDayFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据商户区域和日期查询
	 *
	 * @param findSalesGmDayFirst
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 冯辉 CreateDate: 2017年8月2日
	 *
	 */
	public FindSalesGmDayFirstReturn findSalesGmDayFirstByMAD(FindSalesGmDayFirst findSalesGmDayFirst) throws TsfaServiceException;


}
