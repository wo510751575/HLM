package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.PmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalAllReturnList;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalReturn;

public interface IPmTalkTotalDao {
    int deleteByPrimaryKey(String code);

    int insert(PmTalkTotal record);

    int insertSelective(PmTalkTotal record);

    PmTalkTotal selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PmTalkTotal record);

    int updateByPrimaryKey(PmTalkTotal record);

    List<PmTalkTotal> findPmTalkTotalList(FindPmTalkTotal findPmTalkTotal);
    
    List<PmTalkTotal> findPmTalkTotalListApp(FindPmTalkTotal findPmTalkTotal);
    
    List<PmTalkTotal> findPmTalkTotalOrgListApp(FindPmTalkTotal findPmTalkTotal);
   
    FindPmTalkTotalReturn findPmTalkTotalMax(FindPmTalkTotal findPmTalkTotal);
    
    List<FindPmTalkTotalAllReturnList> findPmTalkTotaReturnList(FindPmTalkTotal findPmTalkTotal);
    
    
    FindPmTalkTotalAllReturnList findPmTalkTotaReturnMax();
    
    List<FindPmTalkTotalAllReturnList> findPmTalkTotaReturnData(FindPmTalkTotal findPmTalkTotal);
    
    
    
}