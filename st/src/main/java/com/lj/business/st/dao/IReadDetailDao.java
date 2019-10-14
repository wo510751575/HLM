package com.lj.business.st.dao;

import com.lj.business.st.domain.ReadDetail;

public interface IReadDetailDao {
    int deleteByPrimaryKey(String code);

    int insert(ReadDetail record);

    int insertSelective(ReadDetail record);

    ReadDetail selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ReadDetail record);

    int updateByPrimaryKey(ReadDetail record);
}