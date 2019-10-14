package com.lj.business.st.dao;

import com.lj.business.st.domain.WorkBrDay;

public interface IWorkBrDayDao {
    int deleteByPrimaryKey(String code);

    int insert(WorkBrDay record);

    int insertSelective(WorkBrDay record);

    WorkBrDay selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WorkBrDay record);

    int updateByPrimaryKey(WorkBrDay record);
}