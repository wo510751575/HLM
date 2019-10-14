package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.MaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotalReturn;

public interface IMaterialTotalDao {
    int deleteByPrimaryKey(String code);

    int insert(MaterialTotal record);

    int insertSelective(MaterialTotal record);

    MaterialTotal selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MaterialTotal record);

    int updateByPrimaryKey(MaterialTotal record);
    
    List<FindMaterialTotalReturn> findMaterialTotal(FindMaterialTotal findMaterialTotal);
    
    List<FindMaterialTotalReturn> findMaterialTotalApp(FindMaterialTotal findMaterialTotal);
    
    List<FindMaterialTotalReturn> findMaterialTotalCount(FindMaterialTotal findMaterialTotal);
}