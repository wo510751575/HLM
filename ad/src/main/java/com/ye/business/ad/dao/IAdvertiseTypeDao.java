package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.AdvertiseType;
import com.ye.business.ad.dto.AdvertiseTypeDto;
import com.ye.business.ad.dto.FindAdvertiseTypePage;

public interface IAdvertiseTypeDao {
	int deleteByPrimaryKey(String code);

	int insert(AdvertiseType record);

	int insertSelective(AdvertiseType record);

	AdvertiseType selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(AdvertiseType record);

	int updateByPrimaryKey(AdvertiseType record);

	List<AdvertiseTypeDto> findAdvertiseTypes(FindAdvertiseTypePage findAdvertiseTypePage);

	List<AdvertiseTypeDto> findAdvertiseTypePage(FindAdvertiseTypePage findAdvertiseTypePage);

	int findAdvertiseTypePageCount(FindAdvertiseTypePage findAdvertiseTypePage);
}