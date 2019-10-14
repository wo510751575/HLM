package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.RwUserBeansChange;
import com.ye.business.ad.dto.RwUserBeansChangeDto;
import com.ye.business.ad.dto.FindRwUserBeansChangePage;

public interface IRwUserBeansChangeDao {
    int deleteByPrimaryKey(String code);

    int insert(RwUserBeansChange record);

    int insertSelective(RwUserBeansChange record);

    RwUserBeansChange selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(RwUserBeansChange record);

    int updateByPrimaryKey(RwUserBeansChange record);

	List<RwUserBeansChangeDto> findRwUserBeansChanges(FindRwUserBeansChangePage findRwUserBeansChangePage);

	List<RwUserBeansChangeDto> findRwUserBeansChangePage(FindRwUserBeansChangePage findRwUserBeansChangePage);

	int findRwUserBeansChangePageCount(FindRwUserBeansChangePage findRwUserBeansChangePage);
}