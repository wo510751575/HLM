package com.lj.business.cf.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.cf.domain.ClientInvite;
import com.lj.business.cf.dto.FindAddInviteRecordDto;
import com.lj.business.cf.dto.clientInvite.FindClientInvite;
import com.lj.business.cf.dto.clientInvite.FindClientInviteReturn;

public interface IClientInviteDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(ClientInvite record);

    ClientInvite selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ClientInvite record);
    
    List<Map<String,Object>> findInviteResults(Map<String,Object> parmMap);
    
    int inviteRecordCount(FindAddInviteRecordDto findAddInviteRecordDto);

	List<Map<String, Object>> findInviteFinish(Map<String, Object> map);

	List<Map<String, Object>> findPromiseInvite(Map<String, Object> map);
	
	List<FindClientInviteReturn> findClientInviteMemberNo(FindClientInvite clientInvite);
	
	FindClientInviteReturn findClientInvinteReachTime(FindClientInvite clientInvite);
}