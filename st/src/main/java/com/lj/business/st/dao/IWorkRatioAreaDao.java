package com.lj.business.st.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.st.domain.WorkRatioArea;
import com.lj.business.st.dto.FindWorkRatioArea;
import com.lj.business.st.dto.WorkRatioAreaDto;

public interface IWorkRatioAreaDao {

    int insertSelective(WorkRatioArea record);

    List<Map<String,Object>> findWorkRatioAreaPage(Map<String,Object> parmMap);
    
    int findWorkRatioAreaCount(Map<String,Object> parmMap);
    
    List<Map<String,Object>> findWorkRatioAreas(Map<String,Object> parmMap);
    
    List<WorkRatioAreaDto> findWorkRatioAreas2(Map<String,Object> parmMap);
    
    List<Map<String,Object>> findBroupProvince(Map<String,Object> parmMap);

    List<WorkRatioArea> findWorkRatioAreaList(FindWorkRatioArea findWorkRatioArea);
}