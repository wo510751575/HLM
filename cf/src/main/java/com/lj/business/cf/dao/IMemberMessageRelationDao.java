package com.lj.business.cf.dao;

import java.util.List;

import com.lj.business.cf.domain.MemberMessageRelation;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelation;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelationPage;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelationPageReturn;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelationReturn;

public interface IMemberMessageRelationDao {
    int deleteByPrimaryKey(String code);

    int insert(MemberMessageRelation record);

    int insertSelective(MemberMessageRelation record);

    MemberMessageRelation selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MemberMessageRelation record);

    int updateByPrimaryKey(MemberMessageRelation record);

	List<FindMemberMessageRelationPageReturn> findMemberMessageRelationPage(
			FindMemberMessageRelationPage findMemberMessageRelationPage);

	int findMemberMessageRelationPageCount(
			FindMemberMessageRelationPage findMemberMessageRelationPage);

	List<FindMemberMessageRelationReturn> findMemberMessageRelationByMerDay(
			FindMemberMessageRelation findMemberMessageRelation);
}