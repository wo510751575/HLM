package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.SetFriendNum;
import com.lj.business.member.dto.FindSetFriendNumPage;
import com.lj.business.member.dto.SetFriendNumDto;

public interface ISetFriendNumDao {
    int deleteByPrimaryKey(String code);

    int insert(SetFriendNum record);

    int insertSelective(SetFriendNum record);

    SetFriendNum selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SetFriendNum record);

    int updateByPrimaryKey(SetFriendNum record);
    
	List<SetFriendNumDto> findSetFriendNums(FindSetFriendNumPage findSetFriendNumPage);

	List<SetFriendNumDto> findSetFriendNumPage(FindSetFriendNumPage findSetFriendNumPage);

	int findSetFriendNumPageCount(FindSetFriendNumPage findSetFriendNumPage);
}