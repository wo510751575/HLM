package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.BestGmChoose;
import com.lj.business.st.dto.FindBgcIndex;
import com.lj.business.st.dto.FindBgcIndexReturn;

public interface IBestGmChooseDao {
    int deleteByPrimaryKey(String str);

    int insert(BestGmChoose record);

    int insertSelective(BestGmChoose record);

    BestGmChoose selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(BestGmChoose record);

    int updateByPrimaryKey(BestGmChoose record);
    
    List<FindBgcIndexReturn> findBgcIndex(FindBgcIndex findBgcIndex);
}