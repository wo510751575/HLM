package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.FestivalPoster;
import com.ye.business.hx.dto.FindFestivalPosterPage;
import com.ye.business.hx.dto.FestivalPosterDto;

public interface IFestivalPosterDao {
    int deleteByPrimaryKey(String code);

    int insert(FestivalPoster record);

    int insertSelective(FestivalPoster record);

    FestivalPoster selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(FestivalPoster record);

    int updateByPrimaryKey(FestivalPoster record);
    
    List<FestivalPosterDto> findFestivalPosters(FindFestivalPosterPage findFestivalPosterPage);
    
    List<FestivalPosterDto> findFestivalPosterPage(FindFestivalPosterPage findFestivalPosterPage);
    
    int findFestivalPosterPageCount(FindFestivalPosterPage findFestivalPosterPage);
}