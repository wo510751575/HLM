package com.lj.business.cp.dao;

import com.lj.business.cp.domain.AwardVeidoo;

public interface IAwardVeidooDao {

	int deleteByPrimaryKey(String code);

	int insert(AwardVeidoo record);

	int insertSelective(AwardVeidoo record);

	AwardVeidoo selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(AwardVeidoo record);

	int updateByPrimaryKey(AwardVeidoo record);
}