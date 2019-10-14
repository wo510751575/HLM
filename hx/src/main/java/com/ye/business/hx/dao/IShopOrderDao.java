package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.ShopOrder;
import com.ye.business.hx.dto.FindShopOrderPage;
import com.ye.business.hx.dto.ShopOrderDto;

public interface IShopOrderDao {
    int deleteByPrimaryKey(String code);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    ShopOrder selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
    
    List<ShopOrderDto> findShopOrders(FindShopOrderPage findShopOrderPage);
    
    List<ShopOrderDto> findShopOrderPage(FindShopOrderPage findShopOrderPage);
    
    int findShopOrderPageCount(FindShopOrderPage findShopOrderPage);
    
}