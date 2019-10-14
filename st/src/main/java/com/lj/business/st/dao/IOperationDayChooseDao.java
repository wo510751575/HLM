package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.OperationDayChoose;
import com.lj.business.st.dto.FindOperateDayReport;
import com.lj.business.st.dto.FindOperateDayReportReturn;
import com.lj.business.st.dto.FindOperationDayChoose;
import com.lj.business.st.dto.OperationDayChooseDto;

public interface IOperationDayChooseDao {
	
    int deleteByPrimaryKey(String str);

    int insert(OperationDayChoose record);

    int insertSelective(OperationDayChoose record);

    OperationDayChoose selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(OperationDayChoose record);

    int updateByPrimaryKey(OperationDayChoose record);
     
    List<OperationDayChooseDto> findOperationDayChoose(FindOperationDayChoose findOperationDayChoose);

    List<FindOperateDayReportReturn> findOperationDayChooseList(FindOperateDayReport findOperateDayReport);
}