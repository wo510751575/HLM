package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.ClientLineRpt;
import com.lj.business.st.dto.ClientLineRpt.AddClientLineRpt;
import com.lj.business.st.dto.ClientLineRpt.FindClientLineRpt;
import com.lj.business.st.dto.ClientLineRpt.FindClientLineRptReturn;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;

import java.util.List;
import java.util.Map;


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
public interface IClientLineRptService {
	
	/**
	 * 
	 *
	 * 方法说明：添加客户职业统计表信息
	 *
	 * @param addClientLineRpt
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addClientLineRpt(AddClientLineRpt addClientLineRpt) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找客户职业统计表信息
	 *
	 * @param findClientLineRpt
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindClientLineRptReturn findClientLineRpt(FindClientLineRpt findClientLineRpt) throws TsfaServiceException;

	/**
	 * 查询客户职业
	 * @param findClientAnalyzeAndOthers
	 * @return
	 * @throws TsfaServiceException
	 */
	List<ClientLineRpt> selectClientLineRptList(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers) throws TsfaServiceException;

	/**
	 * 更新客户职业
	 * @param clientLineRpt
	 * @return
	 * @throws TsfaServiceException
	 */
	int updateClientLineRpt(ClientLineRpt clientLineRpt) throws TsfaServiceException;

	/**
	 * 获取分店维度的所有数据
	 * @return
	 */
	List<ClientLineRpt> selectAllShopData(Map<String,String> map);
    
    /**
     * 
     *
     * 方法说明：根据商户区域维度数据统计商户数据
     *
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年9月18日
     *
     */
    List<ClientLineRpt> selectMerchantTotalByArea();
    
    /**
     * 
     *
     * 方法说明：根据分店维度数据统计商户区域数据
     *
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年9月18日
     *
     */
    List<ClientLineRpt> selectAreaTotalByShop();
	
}
