package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.ShopServer;
import com.ye.business.hx.dto.FindShopServerPage;
import com.ye.business.hx.dto.ShopServerDto;

public interface IShopServerDao {
    int deleteByPrimaryKey(String code);

    int insert(ShopServer record);

    int insertSelective(ShopServer record);

    ShopServer selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ShopServer record);

    int updateByPrimaryKey(ShopServer record);
    
    List<ShopServerDto> findShopServers(FindShopServerPage findShopServerPage);
    
    List<ShopServerDto> findShopServerPage(FindShopServerPage findShopServerPage);
    
    int findShopServerPageCount(FindShopServerPage findShopServerPage);
    
}
