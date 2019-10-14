package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.PmLabelTotal.AddPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotalReturn;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotalReturnDto;


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
public interface IPmLabelTotalService {
	
	/**
	 * 
	 *
	 * 方法说明：添加客户标签统计表信息
	 *
	 * @param addPmLabelTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addPmLabelTotal(AddPmLabelTotal addPmLabelTotal) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找客户标签统计表信息
	 *
	 * @param findPmLabelTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindPmLabelTotalReturn findPmLabelTotal(FindPmLabelTotal findPmLabelTotal) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：客户分类报表
	 *
	 * @param findPmLabelTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月28日
	 *
	 */
	public List<FindPmLabelTotalReturnDto> findPmLabelTotalList(FindPmLabelTotal findPmLabelTotal) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：客户分类报表
	 *
	 * @param findPmLabelTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月28日
	 *
	 */
	public List<FindPmLabelTotalReturnDto> findPmLabelTotalListApp(FindPmLabelTotal findPmLabelTotal) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询客户标签数量最大的一条记录
	 *
	 * @param findPmLabelTotal
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月3日
	 *
	 */
	public FindPmLabelTotalReturnDto findPmLabelTotalMax(FindPmLabelTotal findPmLabelTotal);
	
	
}
