package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.GuidMemberRw;
import com.lj.business.member.dto.FindGuidMemberRwPage;
import com.lj.business.member.dto.GuidMemberRwDto;

public interface IGuidMemberRwDao {
	int deleteByPrimaryKey(String code);

	int insert(GuidMemberRw record);

	int insertSelective(GuidMemberRw record);

	GuidMemberRw selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(GuidMemberRw record);

	int updateByPrimaryKey(GuidMemberRw record);

	List<GuidMemberRwDto> findGuidMemberRws(FindGuidMemberRwPage findGuidMemberRwPage);

	List<GuidMemberRwDto> findGuidMemberRwPage(FindGuidMemberRwPage findGuidMemberRwPage);

	int findGuidMemberRwPageCount(FindGuidMemberRwPage findGuidMemberRwPage);
}