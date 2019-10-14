package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.OperationAnalysisDayBrief;
import com.lj.business.st.dto.FindOperateDayReport;

public interface IOperationAnalysisDayBriefDao {

    int insert(OperationAnalysisDayBrief record);

    int insertSelective(OperationAnalysisDayBrief record);

    OperationAnalysisDayBrief selectByPrimaryKey(String code);

    List<OperationAnalysisDayBrief> selectList(FindOperateDayReport findOperateDayReport);
}