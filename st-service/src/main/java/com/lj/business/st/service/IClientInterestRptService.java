package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.ClientInterestRpt;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;
import com.lj.business.st.dto.ClientInterestRpt.AddClientInterestRpt;
import com.lj.business.st.dto.ClientInterestRpt.FindClientInterestRpt;
import com.lj.business.st.dto.ClientInterestRpt.FindClientInterestRptReturn;


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
public interface IClientInterestRptService {
	
	/**
	 * 
	 *
	 * 方法说明：添加客户兴趣统计表信息
	 *
	 * @param addClientInterestRpt
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addClientInterestRpt(AddClientInterestRpt addClientInterestRpt) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找客户兴趣统计表信息
	 *
	 * @param findClientInterestRpt
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindClientInterestRptReturn findClientInterestRpt(FindClientInterestRpt findClientInterestRpt) throws TsfaServiceException;

	/**
	 * 查找客户兴趣统计表信息
	 * @param findClientAnalyzeAndOthers
	 * @return
	 */
	List<ClientInterestRpt> selectClientInterestRptList(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers);

	/**
	 * 更新客户兴趣
	 * @param clientInterestRpt
	 * @return
	 * @throws TsfaServiceException
	 */
	int updateClientInterestRpt(ClientInterestRpt clientInterestRpt) throws TsfaServiceException;

	/**
	 * 获取分店维度的所有数据
	 * @return
	 */
	List<ClientInterestRpt> selectAllShopData();

    
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
    List<ClientInterestRpt> selectMerchantTotalByArea();
    
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
    List<ClientInterestRpt> selectAreaTotalByShop();
    
}
