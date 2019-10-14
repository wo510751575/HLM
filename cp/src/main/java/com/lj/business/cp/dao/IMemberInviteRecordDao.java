package com.lj.business.cp.dao;

import com.lj.business.cp.domain.MemberInviteRecord;

public interface IMemberInviteRecordDao {

	int deleteByPrimaryKey(String code);

	int insert(MemberInviteRecord record);

	int insertSelective(MemberInviteRecord record);

	MemberInviteRecord selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(MemberInviteRecord record);

	int updateByPrimaryKey(MemberInviteRecord record);
}