package com.lj.business.st.dao;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.domain.CfCountAnalyze;
import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyze;
import com.lj.business.st.dto.CfCountAnalyze.FindCfCountAnalyzeReturn;

import java.util.List;

public interface ICfCountAnalyzeDao {
    int deleteByPrimaryKey(String code);

    int insert(CfCountAnalyze record);

    int insertSelective(CfCountAnalyze record);

    CfCountAnalyze selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CfCountAnalyze record);

    int updateByPrimaryKey(CfCountAnalyze record);

    List<CfCountAnalyze> findCfCountAnalyzeList(FindCfCountAnalyze findCfCountAnalyze) throws TsfaServiceException;

}