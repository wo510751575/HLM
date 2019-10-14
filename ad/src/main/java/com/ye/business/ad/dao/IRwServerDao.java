package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.RwServer;
import com.ye.business.ad.dto.FindRwServerPage;
import com.ye.business.ad.dto.RwServerDto;

public interface IRwServerDao {
	int deleteByPrimaryKey(String code);

	int insert(RwServer record);

	int insertSelective(RwServer record);

	RwServer selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(RwServer record);

	int updateByPrimaryKey(RwServer record);

	List<RwServerDto> findRwServers(FindRwServerPage findRwServerPage);

	List<RwServerDto> findRwServerPage(FindRwServerPage findRwServerPage);

	int findRwServerPageCount(FindRwServerPage findRwServerPage);
}