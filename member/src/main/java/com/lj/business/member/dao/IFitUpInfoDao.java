package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.FitUpInfo;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPage;
import com.lj.business.member.dto.fitUpInfo.FindFitUpInfoPageReturn;

public interface IFitUpInfoDao {
	int deleteByPrimaryKey(String code);

	int insert(FitUpInfo record);

	int insertSelective(FitUpInfo record);

	FitUpInfo selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(FitUpInfo record);

	int updateByPrimaryKey(FitUpInfo record);

	public List<FindFitUpInfoPageReturn> findFitUpInfoPage(FindFitUpInfoPage findFitUpInfoPage);

	public int findFitUpInfoPageCount(FindFitUpInfoPage findFitUpInfoPage);
}