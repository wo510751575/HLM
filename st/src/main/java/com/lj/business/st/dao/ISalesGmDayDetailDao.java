package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.SalesGmDayDetail;
import com.lj.business.st.dto.FindSalesGmDayFirstIndex;
import com.lj.business.st.dto.salesGmDayDetail.AddSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailFirstList;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailReturn;

public interface ISalesGmDayDetailDao {
    int deleteByPrimaryKey(String code);

    int insert(SalesGmDayDetail record);

    int insertSelective(SalesGmDayDetail record);

    SalesGmDayDetail selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SalesGmDayDetail record);

    int updateByPrimaryKey(SalesGmDayDetail record);
    
    FindSalesGmDayDetailReturn findSalesGmDayDetailFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex);
    
    FindSalesGmDayDetailReturn findSalesGmDayDetailFirstByDetail(AddSalesGmDayDetail addSalesGmDayDetail);
    
    List<FindSalesGmDayDetailReturn> findSalesGmDayDetailFirstList(FindSalesGmDayDetailFirstList findSalesGmDayDetailFirstList);
}