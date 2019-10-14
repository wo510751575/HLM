package com.lj.business.member.dao;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.domain.PersonMemberExt;
import com.lj.business.member.dto.MecMemberNoReturn;

public interface IPersonMemberExtDao {
    int insertSelective(PersonMemberExt record);

    PersonMemberExt selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PersonMemberExt record);

    int findCountByMemberNo(@Param("memberNo") String memberNo);
    
    MecMemberNoReturn findPmbByOpenId(@Param("openId") String openId);
    
    PersonMemberExt findByMemberNo(@Param("memberNo") String memberNo);
}