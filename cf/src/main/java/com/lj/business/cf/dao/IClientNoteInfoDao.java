package com.lj.business.cf.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.cf.domain.ClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoList;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoPage;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoPageReturn;

public interface IClientNoteInfoDao {
    int deleteByPrimaryKey(String code);

    int insert(ClientNoteInfo record);

    int insertSelective(ClientNoteInfo record);

    ClientNoteInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ClientNoteInfo record);

    int updateByPrimaryKey(ClientNoteInfo record);

	List<FindClientNoteInfoPageReturn> findClientNoteInfoPage(FindClientNoteInfoPage findClientNoteInfoPage);

	int findClientNoteInfoPageCount(FindClientNoteInfoPage findClientNoteInfoPage);
	  
	FindClientNoteInfoList  clientNoteInfoSendTime(FindClientNoteInfo findClientNoteInfo);

	List<Map<String, Object>> findCountNoteByGm(Map<String, Object> map);

	ClientNoteInfo findFristNoteInfo(FindClientNoteInfo findClientNoteInfo);
	
	 List<FindClientNoteInfoList> findClientInfoMemberNoGm();
}