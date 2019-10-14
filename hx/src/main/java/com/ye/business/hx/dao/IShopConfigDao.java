package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.ShopConfig;
import com.ye.business.hx.dto.FindShopConfigPage;
import com.ye.business.hx.dto.ShopConfigDto;

public interface IShopConfigDao {
    int deleteByPrimaryKey(String code);

    int insert(ShopConfig record);

    int insertSelective(ShopConfig record);

    ShopConfig selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ShopConfig record);

    int updateByPrimaryKey(ShopConfig record);
    
    List<ShopConfigDto> findShopConfigs(FindShopConfigPage findShopConfigPage);
    
    List<ShopConfigDto> findShopConfigPage(FindShopConfigPage findShopConfigPage);
    
    int findShopConfigPageCount(FindShopConfigPage findShopConfigPage);
    
    /**
     * 查找下一个排序值
     * */
    Integer findNextShopConfigIndexNo(ShopConfig record);
    
    List<ShopConfigDto> findSecondShopConfigPage(FindShopConfigPage findShopConfigPage);
    
    int findSecondShopConfigPageCount(FindShopConfigPage findShopConfigPage);
    
    int deleteShopConfig(ShopConfigDto record);
    
}