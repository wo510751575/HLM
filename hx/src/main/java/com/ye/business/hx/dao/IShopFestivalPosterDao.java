package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.ShopFestivalPoster;
import com.ye.business.hx.dto.FindShopFestivalPosterPage;
import com.ye.business.hx.dto.ShopFestivalPosterDto;

public interface IShopFestivalPosterDao {
    int deleteByPrimaryKey(String code);

    int insert(ShopFestivalPoster record);

    int insertSelective(ShopFestivalPoster record);

    ShopFestivalPoster selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ShopFestivalPoster record);

    int updateByPrimaryKey(ShopFestivalPoster record);
    
    List<ShopFestivalPosterDto> findShopFestivalPosters(FindShopFestivalPosterPage findShopFestivalPosterPage);
    
    List<ShopFestivalPosterDto> findShopFestivalPosterPage(FindShopFestivalPosterPage findShopFestivalPosterPage);
    
    int findShopFestivalPosterPageCount(FindShopFestivalPosterPage findShopFestivalPosterPage);

    int deleteByShopFestivalPoster(ShopFestivalPoster record);
    
    List<ShopFestivalPosterDto> findShopFestivalPostersByTemplateImg(FindShopFestivalPosterPage findShopFestivalPosterPage);
    
}