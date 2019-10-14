package com.lj.business.member.dao;

import com.lj.business.member.domain.GuidMemberExt;
import com.lj.business.member.dto.GuidMemberExtDto;

public interface IGuidMemberExtDao {
    int deleteByPrimaryKey(String code);

    int insert(GuidMemberExt record);

    int insertSelective(GuidMemberExt record);

    GuidMemberExt selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(GuidMemberExt record);

    int updateByPrimaryKey(GuidMemberExt record);

	GuidMemberExt findGuidMemberExtByMobile(GuidMemberExtDto guidMemberExtDto);
}