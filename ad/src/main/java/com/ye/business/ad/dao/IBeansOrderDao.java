package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.BeansOrder;
import com.ye.business.ad.dto.BeansOrderDto;
import com.ye.business.ad.dto.FindBeansOrderPage;

public interface IBeansOrderDao {
	int deleteByPrimaryKey(String code);

	int insert(BeansOrder record);

	int insertSelective(BeansOrder record);

	BeansOrder selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(BeansOrder record);

	int updateByPrimaryKey(BeansOrder record);

	List<BeansOrderDto> findBeansOrders(FindBeansOrderPage findBeansOrderPage);

	List<BeansOrderDto> findBeansOrderPage(FindBeansOrderPage findBeansOrderPage);

	int findBeansOrderPageCount(FindBeansOrderPage findBeansOrderPage);
}