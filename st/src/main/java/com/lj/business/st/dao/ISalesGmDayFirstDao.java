package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.SalesGmDayFirst;
import com.lj.business.st.dto.FindSalesGmDayFirstIndex;
import com.lj.business.st.dto.FindSalesGmDayFirstIndexReturn;
import com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirstReturn;

public interface ISalesGmDayFirstDao {
    int deleteByPrimaryKey(String code);

    int insert(SalesGmDayFirst record);

    int insertSelective(SalesGmDayFirst record);

    SalesGmDayFirst selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SalesGmDayFirst record);

    int updateByPrimaryKey(SalesGmDayFirst record);
    
    List<FindSalesGmDayFirstIndexReturn> findSalesGmDayFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex);
    
    FindSalesGmDayFirstReturn findSalesGmDayFirstByMAD(FindSalesGmDayFirst findSalesGmDayFirst);
    
}