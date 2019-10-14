package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.RwOrder;
import com.ye.business.ad.dto.FindRwOrderPage;
import com.ye.business.ad.dto.RwOrderDto;

public interface IRwOrderDao {
	int deleteByPrimaryKey(String code);

	int insert(RwOrder record);

	int insertSelective(RwOrder record);

	RwOrder selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(RwOrder record);

	int updateByPrimaryKey(RwOrder record);

	List<RwOrderDto> findRwOrders(FindRwOrderPage findRwOrderPage);

	List<RwOrderDto> findRwOrderPage(FindRwOrderPage findRwOrderPage);

	int findRwOrderPageCount(FindRwOrderPage findRwOrderPage);
}