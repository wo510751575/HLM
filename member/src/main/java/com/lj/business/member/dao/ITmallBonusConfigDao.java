package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.TmallBonusConfig;
import com.lj.business.member.dto.FindTmallBonusConfigPage;
import com.lj.business.member.dto.TmallBonusConfigDto;

public interface ITmallBonusConfigDao {
    int deleteByPrimaryKey(String code);

    int insert(TmallBonusConfig record);

    int insertSelective(TmallBonusConfig record);

    TmallBonusConfig selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(TmallBonusConfig record);

    int updateByPrimaryKey(TmallBonusConfig record);
    
   List<TmallBonusConfigDto> findTmallBonusConfigs(FindTmallBonusConfigPage findTmallBonusConfigPage);
   
   List<TmallBonusConfigDto> findTmallBonusConfigPage(FindTmallBonusConfigPage findTmallBonusConfigPage);
   
   int findTmallBonusConfigPageCount(FindTmallBonusConfigPage findTmallBonusConfigPage);
}