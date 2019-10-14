package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 * <p>
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 */

import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.WorkRatioArea;
import com.lj.business.st.domain.WorkRatioShop;
import com.lj.business.st.dto.FindWorkRatioArea;
import com.lj.business.st.dto.FindWorkRatioAreaReturn;
import com.lj.business.st.dto.WorkRatioShop.AddWorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.FindExcellentShop;
import com.lj.business.st.dto.WorkRatioShop.FindExcellentShopReturn;
import com.lj.business.st.dto.WorkRatioShop.FindTopTenShop;
import com.lj.business.st.dto.WorkRatioShop.FindTopTenShopReturn;
import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShopReturn;


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
public interface IWorkRatioShopService {

	/**
	 * 
	 *
	 * 方法说明：优秀门店_H5
	 * 			BOSS看所有门店，区域经理看所属区域，店长看所属城市
	 *
	 * @param findExcellentShop
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月31日
	 *
	 */
    List<FindExcellentShopReturn> findExcellentShop(FindExcellentShop findExcellentShop);
	/**
	 * 
	 *
	 * 方法说明：查询TOP 10店长_H5
	 *
	 * @param findTopTenShop
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月31日
	 *
	 */
    List<FindTopTenShopReturn> findTopTenShop(FindTopTenShop findTopTenShop);
    
    /**
     *
     *
     * 方法说明：添加导购工作统计表信息
     *
     * @param addWorkRatioShop
     * @return
     * @throws TsfaServiceException
     *
     * @author 彭阳 CreateDate: 2017年07月10日
     *
     */
    void addWorkRatioShop(AddWorkRatioShop addWorkRatioShop) throws TsfaServiceException;

    /**
     * 查询工作统计
     * @param findWorkRatioShop
     * @return
     *
     * @author 武鹏飞 CreateDate: 2017年07月27日
     */
    List<WorkRatioShop> findWorkRatioShopList(FindWorkRatioShop findWorkRatioShop);

    /**
	 * 
	 *
	 * 方法说明：查询列表分页
	 *
	 * @param parmMap
	 * 1.merchantNo 商户编号
	 * 2.areaCode 区域编号
	 * 3.shopName 门店名称
	 * 4.provinceCode 省份编号
	 * 5.dimensionSt 维度（商户：MERCHANT\r\n            区域：AREA\r\n            门店：SHOP\r\n            导购：GUID',）
	 * 6.startTime/endTime 查询 统计日期
	 * 7.openDateStart/openDateEnd 查询开店日期
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月27日
	 *
	 */
	public Page<WorkRatioShop> findWorkRatioShopPage(Map<String,Object> parmMap);
	
	/**
	 * 
	 *
	 * 方法说明：统计数量
	 *
	 * @param parmMap
	 * 1.merchantNo 商户编号
	 * 2.areaCode 区域编号
	 * 3.shopName 门店名称
	 * 4.provinceCode 省份编号
	 * 5.dimensionSt 维度（商户：MERCHANT\r\n            区域：AREA\r\n            门店：SHOP\r\n            导购：GUID',）
	 * 6.startTime/endTime 查询 统计日期
	 * 7.openDateStart/openDateEnd 查询开店日期
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月27日
	 *
	 */
	public int findWorkRatioShopCount(Map<String,Object> parmMap);
	
	 /**
		 * 
		 *
		 * 方法说明：查询列表不分页
		 *
		 * @param parmMap
		 * 1.merchantNo 商户编号
		 * 2.areaCode 区域编号
		 * 3.shopName 门店名称
		 * 4.provinceCode 省份编号
		 * 5.dimensionSt 维度（商户：MERCHANT\r\n            区域：AREA\r\n            门店：SHOP\r\n            导购：GUID',）
		 * 6.startTime/endTime 查询 统计日期
		 * 7.openDateStart/openDateEnd 查询开店日期
		 * @return
		 *
		 * @author 段志鹏 CreateDate: 2017年7月27日
		 *
		 */
	public List<Map<String,Object>> findWorkRatioShops(Map<String,Object> parmMap);
	
	/**
	 * 
	 *
	 * 方法说明：首页销售额和同比接口
	 *1.merchantNo 商户编号
	 * @param parmMap
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月2日
	 *
	 */
	public Map<String,Object> indexSale(Map<String,Object> parmMap);
	
	/**
	 * 
	 *
	 * 方法说明：oms首页销售额接口
	 *
	 * @param findWorkRatioShop
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月31日
	 *
	 */
	public List<FindExcellentShopReturn> findWorkRatioShopReturnList(FindWorkRatioShop findWorkRatioShop);
	
	/**
	 * 
	 *
	 * 方法说明：销售漏斗
	 *
	 * @param parmMap
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月1日
	 *
	 */
	public  List<FindWorkRatioShopReturn> findWorkRatioShopNum(Map<String,Object> parmMap);
}
