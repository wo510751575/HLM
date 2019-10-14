package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.PmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.AddPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotalReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IPmTypeTotalService {
	
	/**
	 * 
	 *
	 * 方法说明：添加客户分类统计表信息
	 *
	 * @param addPmTypeTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addPmTypeTotal(AddPmTypeTotal addPmTypeTotal) throws TsfaServiceException;


	PmTypeTotal selectByPrimaryKey(String code);

	/**
	 *
	 *
	 * 方法说明：查找客户分类统计表信息
	 *
	 * @param findPmTypeTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 */
	public List<FindPmTypeTotalReturn> findPmTypeTotalList(FindPmTypeTotal findPmTypeTotal) throws TsfaServiceException;
	/**
	 *
	 *
	 * 方法说明：查找客户分类统计表信息
	 *
	 * @param findPmTypeTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 */
	public List<FindPmTypeTotalReturn> findPmTypeTotalListApp(FindPmTypeTotal findPmTypeTotal) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：按统计时间分组
	 * merchantNo	商户编号
	 * areaCode		区域code
	 * areaName		区域名称
	 * pmTypeType	客户类型
	 * dimensionSt	维度
	 * startTime	开始时间
	 * endTime		结束时间
	 * @param parmMap
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月28日
	 *
	 */
	List<Map<String,Object>> findIntentionPmList(Map<String,Object> parmMap);
	
	/**
	 * 
	 *
	 * 方法说明：按客户类型和统计时间分组
	 *
	 * @param parmMap
	 * merchantNo	商户编号
	 * areaCode		区域code
	 * areaName		区域名称
	 * pmTypeType	客户类型
	 * dimensionSt	维度
	 * startTime	开始时间
	 * endTime		结束时间
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月28日
	 *
	 */
	List<Map<String,Object>> findPmTypeList(Map<String,Object> paramMap);

	/**
	 * 
	 *
	 * 方法说明：查询分类客户数最多的一条记录
	 *
	 * @param findPmTypeTotal
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月3日
	 *
	 */
	FindPmTypeTotalReturn findPmTypeMaxList(FindPmTypeTotal findPmTypeTotal);
	/**
	 * 
	 *
	 * 方法说明：統計客戶類型
	 *
	 * @param findPmTypeTotal
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月29日
	 *
	 */
	public List<FindPmTypeTotalReturn> findPmTypeListSum(FindPmTypeTotal findPmTypeTotal);
	/**
	 * 查询统计时间 
	 * @param findPmTypeTotal
	 * @return
	 */
	public  List<FindPmTypeTotalReturn> findPmTypeDaySt(FindPmTypeTotal findPmTypeTotal);
	 /**
	  * 按日期查找统计分类数据
	  * @param findPmTypeTotal
	  * @return
	  */
	public List<FindPmTypeTotalReturn>  queryPmType(FindPmTypeTotal findPmTypeTotal);
	/**
	 * 
	 *
	 * 方法说明：初始化为空的数据
	 *
	 * @param findPmTypeTotal
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年4月12日
	 *
	 */
	public boolean  initializePmTypeTota ()throws TsfaServiceException;
}

