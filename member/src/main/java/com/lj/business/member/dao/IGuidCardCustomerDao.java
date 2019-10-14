package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.GuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.DelGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomer;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerPage;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerPageReturn;
import com.lj.business.member.dto.guidCardCustomer.FindGuidCardCustomerReturn;

public interface IGuidCardCustomerDao {
    int deleteByPrimaryKey(String code);

    int insert(GuidCardCustomer record);

    int insertSelective(GuidCardCustomer record);

    GuidCardCustomer selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(GuidCardCustomer record);

    int updateByPrimaryKey(GuidCardCustomer record);

	List<FindGuidCardCustomerPageReturn> findGuidCardCustomerPage(
			FindGuidCardCustomerPage findGuidCardCustomerPage);

	int findGuidCardCustomerPageCount(
			FindGuidCardCustomerPage findGuidCardCustomerPage);

	/**
	 * 
	 *
	 * 方法说明：根据条件查找名片明细
	 *
	 * @param findGuidCardCustomer
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年11月3日
	 *
	 */
	List<FindGuidCardCustomerReturn> findGuidCardCustomerSelect(
			FindGuidCardCustomer findGuidCardCustomer);

	/**
	 * 
	 *
	 * 方法说明：删除客户与导购名片的关系
	 *
	 * @param delGuidCardCustomer
	 *
	 * @author 梅宏博  CreateDate: 2017年11月8日
	 *
	 */
	int delGuidCardCustomerSelect(DelGuidCardCustomer delGuidCardCustomer);
}