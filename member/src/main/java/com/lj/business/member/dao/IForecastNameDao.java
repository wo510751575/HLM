package com.lj.business.member.dao;

import java.util.List;
import com.lj.business.member.domain.ForecastName;
import com.lj.business.member.dto.forecastName.FindForecastName;
import com.lj.business.member.dto.forecastName.FindForecastNameReturn;

public interface IForecastNameDao {
    int deleteByPrimaryKey(String code);

    int insert(ForecastName record);

    int insertSelective(ForecastName record);

    ForecastName selectByPrimaryKey(String code);
    
    ForecastName selectByMemberCode(String memberCode);

    int updateByPrimaryKeySelective(ForecastName record);

    int updateByPrimaryKey(ForecastName record);

    List<FindForecastNameReturn> findForecastNameByCondition(FindForecastName findForecastName);
}