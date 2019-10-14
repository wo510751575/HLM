package com.lj.business.st.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.st.domain.WorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.FindExcellentShop;
import com.lj.business.st.dto.WorkRatioShop.FindExcellentShopReturn;
import com.lj.business.st.dto.WorkRatioShop.FindTopTenShop;
import com.lj.business.st.dto.WorkRatioShop.FindTopTenShopReturn;
import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShopReturn;

public interface IWorkRatioShopDao {
    int insertSelective(WorkRatioShop record);

    WorkRatioShop selectByPrimaryKey(String code);

    List<WorkRatioShop> findWorkRatioShopList(FindWorkRatioShop findWorkRatioShop);
    
    List<WorkRatioShop> findWorkRatioShopPage(Map<String,Object> parmMap);
    
    int findWorkRatioShopCount(Map<String,Object> parmMap);
    
    List<Map<String,Object>> findWorkRatioShops(Map<String,Object> parmMap);
    
    List<FindTopTenShopReturn> findTopTenShop(FindTopTenShop findTopTenShop);
    
    List<FindExcellentShopReturn> findExcellentShop(FindExcellentShop findExcellentShop);
    
    List<FindExcellentShopReturn> findWorkRatioShopReturnList(FindWorkRatioShop findWorkRatioShop);
    
    List<FindWorkRatioShopReturn> findWorkRatioShopNum(Map<String,Object> parmMap);

	int findWorkRatioShopRankCount(Map<String, Object> parmMap);
}