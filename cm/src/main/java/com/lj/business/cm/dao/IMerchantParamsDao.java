package com.lj.business.cm.dao;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.domain.MerchantParams;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsPage;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsPageReturn;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsReturn;

public interface IMerchantParamsDao {
    int deleteByPrimaryKey(String code);

    int insert(MerchantParams record);

    int insertSelective(MerchantParams record);

    MerchantParams selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MerchantParams record);

    int updateByPrimaryKey(MerchantParams record);
    
    List<FindMerchantParamsPageReturn> findMerchantParamsPage(FindMerchantParamsPage findMerchantParamsPage);

   	int findMerchantParamsPageCount(FindMerchantParamsPage findMerchantParamsPage);

	List<FindMerchantParamsReturn> findMerchantParamsByGN(
			FindMerchantParams findMerchantParams);

	/**
	 * 
	 *
	 * 方法说明：根据参数查询商户参数
	 *
	 * @param findMerchantParams
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博  CreateDate: 2017年10月24日
	 *
	 */
	MerchantParams findMerchantParamsSelect(
			FindMerchantParams findMerchantParams);
	
	/**
	 * 
	 *
	 * 方法说明：获取所有商户
	 *
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月10日
	 *
	 */
	List<FindMerchantParamsReturn> queryMerchantList();
    
}