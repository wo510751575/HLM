package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.ProjectPrice;
import com.ye.business.hx.dto.FindProjectPricePage;
import com.ye.business.hx.dto.ProjectPriceDto;

public interface IProjectPriceDao {
    int deleteByPrimaryKey(String code);

    int insert(ProjectPrice record);

    int insertSelective(ProjectPrice record);

    ProjectPrice selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ProjectPrice record);

    int updateByPrimaryKey(ProjectPrice record);
    
    List<ProjectPriceDto> findProjectPrices(FindProjectPricePage findProjectPricePage);
    
    List<ProjectPriceDto> findProjectPricePage(FindProjectPricePage findProjectPricePage);
    
    int findProjectPricePageCount(FindProjectPricePage findProjectPricePage);
    
    int deleteProjectPrice(ProjectPriceDto record);
}