package com.lj.business.cp.dao;

import java.util.List;

import com.lj.business.cp.domain.AwardCondition;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionPage;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionPageReturn;

public interface IAwardConditionDao {
	int deleteByPrimaryKey(String code);

	int insert(AwardCondition record);

	int insertSelective(AwardCondition record);

	AwardCondition selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(AwardCondition record);

	int updateByPrimaryKey(AwardCondition record);

	List<FindAwardConditionPageReturn> findAwardConditionPage(FindAwardConditionPage findAwardConditionPage);

	int findAwardConditionPageCount(FindAwardConditionPage findAwardConditionPage);
	
}