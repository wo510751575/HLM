package com.lj.business.st.service;

import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.business.st.dto.AddWorkRatioArea;
import com.lj.business.st.dto.FindWorkRatioArea;
import com.lj.business.st.dto.FindWorkRatioAreaReturn;
import com.lj.business.st.dto.WorkRatioAreaDto;

/**
 * 
 * 
 * 类说明：区域工作统计Service
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月27日
 */
public interface IWorkRatioAreaService {
	
	/**
	 * 
	 *
	 * 方法说明：新增
	 *
	 * @param addWorkRatioArea
	 *
	 * @author 段志鹏 CreateDate: 2017年7月27日
	 *
	 */
	public void addWorkRatioArea(AddWorkRatioArea addWorkRatioArea);
	
	/**
	 * 
	 *
	 * 方法说明：查询列表分页
	 *
	 * @param parmMap
	 * 1.memberNoMerchant 商户编号
	 * 2.areaCode 区域编号
	 * 3.areaName 区域名称
	 * 4.provinceCode 省份编号
	 * 5.dimensionSt 维度（商户：MERCHANT\r\n            区域：AREA\r\n            门店：SHOP\r\n            导购：GUID',）
	 * 6.startTime/endTime 查询 统计日期
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月27日
	 *
	 */
	public Page<Map<String,Object>> findWorkRatioAreaPage(Map<String,Object> parmMap);
	
	/**
	 * 
	 *
	 * 方法说明：查询列表
	 *
	 * @param parmMap
	 * 1.memberNoMerchant 商户编号
	 * 2.areaCode 区域编号
	 * 3.areaName 区域名称
	 * 4.provinceCode 省份编号
	 * 5.dimensionSt 维度（商户：MERCHANT\r\n            区域：AREA\r\n            门店：SHOP\r\n            导购：GUID',）
	 * 6.startTime/endTime 查询 统计日期
	 * 7.provinceName 省份名称
	 * @return
	 * @author 段志鹏 CreateDate: 2017年7月27日
	 *
	 */
	public List<Map<String,Object>> findWorkRatioAreas(Map<String,Object> parmMap);
	
	public List<WorkRatioAreaDto> findWorkRatioAreas2(Map<String, Object> parmMap);
	
	/**
	 * 
	 *
	 * 方法说明：按省份分组
	 *
	 * @param parmMap
	 * 1.memberNoMerchant 商户编号
	 * 2.areaCode 区域编号
	 * 3.areaName 区域名称
	 * 4.provinceCode 省份编号
	 * 5.dimensionSt 维度（商户：MERCHANT\r\n            区域：AREA\r\n            门店：SHOP\r\n            导购：GUID',）
	 * 6.startTime/endTime 查询 统计日期
	 * 7.provinceName 省份名称
	 * @return
	 *	NUM_SHOP 门店数量
	 *	PROVINCE_CODE 省份code
	 *	PROVINCE_NAME 省份名称
	 * @author 段志鹏 CreateDate: 2017年7月28日
	 *
	 */
	List<Map<String,Object>> findBroupProvince(Map<String,Object> parmMap);

	/**
	 *
	 *
	 * 方法说明：门店分布统计-按区域
	 *	1.memberNoMerchant 商户编号
	 *	2.areaCode 区域编号
	 *	3.startTime/endTime 查询 统计日期
	 * @param parmMap
	 * @return
	 *	1.List areaNames 区域名称列表
	 *	2.List areaCodes 区域CODE
	 *	3.List numShops 门店数量
	 *	4.List ratioShops 门店占比
	 * @author 段志鹏 CreateDate: 2017年8月1日
	 *
	 */
	Map<String,Object> findShopGroupArea(Map<String,Object> parmMap);

	/**
	 *
	 *
	 * 方法说明：门店区域分布
	 *
	 * @param areaCode
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月1日
	 *
	 */
	List<Map<String,Object>> findShopByAreaCode(String areaCode,String merchantNo);

	/**
	 * 查询客户区域分析报表
	 * @param findWorkRatioArea
	 * @return
	 */
	FindWorkRatioAreaReturn findWorkRatioAreaList(FindWorkRatioArea findWorkRatioArea);
}
