package com.lj.business.st.dao;

import com.lj.business.st.domain.CfAnalyze;

public interface ICfAnalyzeDao {
    int deleteByPrimaryKey(String code);

    int insert(CfAnalyze record);

    int insertSelective(CfAnalyze record);

    CfAnalyze selectByPrimaryKey(String code);
    
    CfAnalyze selectByParamKey(CfAnalyze cfAnalyze);

    int updateByPrimaryKeySelective(CfAnalyze record);

    int updateByPrimaryKey(CfAnalyze record);
}