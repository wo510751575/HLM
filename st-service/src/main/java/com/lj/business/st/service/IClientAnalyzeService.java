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
import com.lj.business.st.domain.ClientAnalyze;
import com.lj.business.st.dto.AddClientAnalyze;
import com.lj.business.st.dto.AddClientAnalyzeReturn;
import com.lj.business.st.dto.FindClientAnalyze;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;
import com.lj.business.st.dto.FindClientAnalyzeAndOthersReturn;
import com.lj.business.st.dto.FindClientAnalyzeReturn;


/**
 * 
 * 
 * 类说明：客户画像统计
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月31日
 */
public interface IClientAnalyzeService {
	
    /**
     * 
     *
     * 方法说明：新增客户画像统计信息
     *
     * @param addClientAnalyze
     * @return
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年7月31日
     *
     */
	public AddClientAnalyzeReturn addClientAnalyze(AddClientAnalyze addClientAnalyze) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：新增客户画像统计信息	
	 *
	 * @param addClientAnalyze
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年7月31日
	 *
	 */
	public AddClientAnalyzeReturn addClientAnalyze(ClientAnalyze clientAnalyze) throws TsfaServiceException;
    /**
     * 
     *
     * 方法说明：查询客户画像统计信息
     *
     * @param map
     * @return
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年7月31日
     *
     */
	public  List<Map<String,Object>> findClientAnalyzeList(Map<String,Object> map)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询客户性别数量
	 *
	 * @param findClientAnalyze
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月31日
	 *
	 */
    public List<FindClientAnalyzeReturn> findClientAnalyze(FindClientAnalyze findClientAnalyze);

	/**
	 * 查询客户画像
	 * @param findClientAnalyzeAndOthers
	 * @return
	 * @throws TsfaServiceException
	 */
	FindClientAnalyzeAndOthersReturn findClientAnalyzeAndOthersReturn(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers)throws TsfaServiceException;

	/**
	 * 根据分店编号获取客户画像
	 * @param shopNo
	 * @return
	 */
	ClientAnalyze findByShopNo(String shopNo);

	/**
	 * 根据商户编号获取客户画像
	 * @param merchantNo
	 * @return
	 */
	ClientAnalyze findByMerchantNo(String merchantNo);

	/**
	 * 根据区域编号获取客户画像
	 * @param areaCode
	 * @return
	 */
	ClientAnalyze findByAreaCode(String areaCode);

	/**
	 * 更新客户画像
	 * @param clientAnalyze
	 * @return
	 */
	int updateClientAnalyze(ClientAnalyze clientAnalyze);


	/**
	 * 获取商户维度的所有数据
	 * @return
	 */
	List<ClientAnalyze> selectAllShopData();


    
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
    List<ClientAnalyze> selectMerchantTotalByArea();
    
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
    List<ClientAnalyze> selectAreaTotalByShop();

    /**
     * 
     *
     * 方法说明：查询客户画像
     *
     * @param findClientAnalyzeAndOthers
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年9月19日
     *
     */
	ClientAnalyze findClientAnalyzeBase(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers);
}
