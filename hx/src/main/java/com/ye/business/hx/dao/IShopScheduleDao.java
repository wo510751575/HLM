package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.ShopSchedule;
import com.ye.business.hx.dto.FindShopSchedulePage;
import com.ye.business.hx.dto.ShopScheduleDto;

public interface IShopScheduleDao {
    int deleteByPrimaryKey(String code);

    int insert(ShopSchedule record);

    int insertSelective(ShopSchedule record);

    ShopSchedule selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ShopSchedule record);

    int updateByPrimaryKey(ShopSchedule record);
    
    List<ShopScheduleDto> findShopSchedules(FindShopSchedulePage findShopSchedulePage);
    
    List<ShopScheduleDto> findShopSchedulePage(FindShopSchedulePage findShopSchedulePage);
    
    int findShopSchedulePageCount(FindShopSchedulePage findShopSchedulePage);
    
}