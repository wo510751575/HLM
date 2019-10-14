package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.PmTalkTotal.AddPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalAllReturnList;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalReturn;


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
public interface IPmTalkTotalService {
	
	/**
	 * 
	 *
	 * 方法说明：添加客户咨询统计表信息
	 *
	 * @param addPmTalkTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addPmTalkTotal(AddPmTalkTotal addPmTalkTotal) throws TsfaServiceException;

	/**
	 * 查找客户咨询统计
	 * @param findPmTalkTotal
	 * @return
	 */
	List<FindPmTalkTotalReturn> findPmTalkTotalList(FindPmTalkTotal findPmTalkTotal);
	/**
	 * 查找客户咨询统计
	 * @param findPmTalkTotal
	 * @return
	 */
	List<FindPmTalkTotalReturn> findPmTalkTotalListApp(FindPmTalkTotal findPmTalkTotal);
	
	List<FindPmTalkTotalReturn> findPmTalkTotalOrgListApp(FindPmTalkTotal findPmTalkTotal);
	/**
	 * 
	 *
	 * 方法说明：查询咨询数量最大的一条记录
	 *
	 * @param findPmLabelTotal
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月3日
	 *
	 */
	 FindPmTalkTotalReturn findPmTalkTotalMax(FindPmTalkTotal findPmTalkTotal);
	/**
	 * 
	 *
	 * 方法说明：根据不同维度统计客户咨询信息
	 *
	 * @param findPmTalkTotal
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月14日
	 *
	 */
	public  List<FindPmTalkTotalAllReturnList> findPmTalkTotaReturnList(FindPmTalkTotal findPmTalkTotal)throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：区域统计 客户咨询量最大的时间
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年8月18日
	 *
	 */
	public  FindPmTalkTotalAllReturnList findPmTalkTotaReturnMax()throws TsfaServiceException;
	
    /**
     * 
     *
     * 方法说明：统计咨询量最多的一条
     *
     * @param findPmTalkTotal
     * @return
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年8月18日
     *
     */
	public List<FindPmTalkTotalAllReturnList> findPmTalkTotaReturnData(FindPmTalkTotal findPmTalkTotal)throws TsfaServiceException;
}
