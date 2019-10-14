package com.lj.business.st.dao;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.AreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyzeReturn;

import java.util.List;

public interface IAreaOrderAnalyzeDao {
    int deleteByPrimaryKey(String code);

    int insert(AreaOrderAnalyze record);

    int insertSelective(AreaOrderAnalyze record);

    AreaOrderAnalyze selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(AreaOrderAnalyze record);

    int updateByPrimaryKey(AreaOrderAnalyze record);

    List<AreaOrderAnalyze> findAreaOrderAnalyzeList(FindAreaOrderAnalyze findAreaOrderAnalyze) throws TsfaServiceException;
    
    List<FindAreaOrderAnalyzeReturn> findAreaCodeMaxNum(FindAreaOrderAnalyze findAreaOrderAnalyze);
}