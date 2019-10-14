package com.lj.business.st.dao;

import com.lj.business.st.domain.ReadTotal;

public interface IReadTotalDao {
    int deleteByPrimaryKey(String code);

    int insert(ReadTotal record);

    int insertSelective(ReadTotal record);

    ReadTotal selectByPrimaryKey(String code);
    
    ReadTotal selectByParamKey(ReadTotal  readTotal);

    int updateVisitNum(ReadTotal record);
    
    int updateVisitNumPerson(ReadTotal record);
    
    int updateByPrimaryKeySelective(ReadTotal record);

    int updateByPrimaryKey(ReadTotal record);
}