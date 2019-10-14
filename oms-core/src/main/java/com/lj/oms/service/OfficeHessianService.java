package com.lj.oms.service;

import java.util.List;

import com.lj.oms.entity.sys.Office;

public interface OfficeHessianService {
	
	/**
	 * 根据shopNo查询终端所属机构
	 * @param shopNo
	 * @return
	 */
	Office findByShopNo(String shopNo);
	
	/**
	 * 查询3级机构列表
	 * @return
	 */
	List<Office> findThirdOfficeList(String userId);
	

	/**
	 * 方法说明：通过商户号找商户信息。
	 *
	 * @param merchantNo 商户号
	 * @return
	 *
	 * @author lhy  2018年3月23日
	 *
	 */
	public Office findOfficeByMerchantNo(String merchantNo);
	
	/**
	 * 
	 *
	 * 方法说明：添加机构
	 *
	 * @param office
	 *
	 * @author 许新龙 CreateDate: 2018年5月29日
	 *
	 */
	void addOffice(Office office);
	
	/**
	 * 
	 * *方法说明：根据用户查找机构
	 *
	 * @param userId
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月25日
	 */
	List<Office> findOfficeListByUserId(String userId);

	/**
	 * 获取商户列表
	 * 1.直属ROOT下面的机构
	 * @return
	 */
	List<Office> findMerchants();
	/**
	 * 查询所有商户（门诊）集合
	 * @param office null 则查询所有 商户<br>
	 *  office.name 非空 则按name模糊搜索 商户<br>
	 *  office.address 非空 则按address模糊搜索 商户
	 * @return
	 * @author 刘红艳 2019年3月16日 add by 焕新：查询所有门诊集合
	 */
	public List<Office> findMerchantsByOffice(Office office);
	 
}
