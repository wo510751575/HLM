package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.ServerInfo;
import com.ye.business.ad.dto.FindServerInfoPage;
import com.ye.business.ad.dto.ServerInfoDto;

public interface IServerInfoDao {
	int deleteByPrimaryKey(String code);

	int insert(ServerInfo record);

	int insertSelective(ServerInfo record);

	ServerInfo selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(ServerInfo record);

	int updateByPrimaryKeyWithBLOBs(ServerInfo record);

	int updateByPrimaryKey(ServerInfo record);

	List<ServerInfoDto> findServerInfos(FindServerInfoPage findServerInfoPage);

	List<ServerInfoDto> findServerInfoPage(FindServerInfoPage findServerInfoPage);

	int findServerInfoPageCount(FindServerInfoPage findServerInfoPage);
}