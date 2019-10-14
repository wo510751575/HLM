package com.lj.business.st.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.st.domain.PmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotalReturn;

public interface IPmTypeTotalDao {
    int deleteByPrimaryKey(String code);

    int insert(PmTypeTotal record);

    int insertSelective(PmTypeTotal record);

    PmTypeTotal selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PmTypeTotal record);

    int updateByPrimaryKey(PmTypeTotal record);

    List<PmTypeTotal> findPmTypeTotalList(FindPmTypeTotal findPmTypeTotal);
    
    List<FindPmTypeTotalReturn> findPmTypeTotalListApp(FindPmTypeTotal findPmTypeTotal);
    
    List<Map<String,Object>> findIntentionPmList(Map<String,Object> parmMap);
    
    List<Map<String,Object>> findPmTypeList(Map<String,Object> parmMap);
    
    List<FindPmTypeTotalReturn> findPmTypeListSum(FindPmTypeTotal findPmTypeTotal);
    
    FindPmTypeTotalReturn findPmTypeMaxList(FindPmTypeTotal findPmTypeTotal);
    
    List<FindPmTypeTotalReturn> findPmTypeDaySt(FindPmTypeTotal findPmTypeTotal);
    
    List<FindPmTypeTotalReturn>  queryPmType(FindPmTypeTotal findPmTypeTotal);
    
}