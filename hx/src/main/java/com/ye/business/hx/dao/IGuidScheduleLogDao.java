package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.GuidScheduleLog;
import com.ye.business.hx.dto.FindGuidScheduleLogPage;
import com.ye.business.hx.dto.FindGuidSchedulePage;
import com.ye.business.hx.dto.GuidScheduleCycleDto;
import com.ye.business.hx.dto.GuidScheduleDto;
import com.ye.business.hx.dto.GuidScheduleLogDto;

public interface IGuidScheduleLogDao {
    int deleteByPrimaryKey(String code);

    int insert(GuidScheduleLog record);

    int insertSelective(GuidScheduleLog record);

    GuidScheduleLog selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(GuidScheduleLog record);

    int updateByPrimaryKey(GuidScheduleLog record);
    
    List<GuidScheduleLogDto> findGuidScheduleLogs(FindGuidScheduleLogPage findGuidScheduleLogPage);
    
    List<GuidScheduleLogDto> findGuidScheduleLogPage(FindGuidScheduleLogPage findGuidScheduleLogPage);
    
    int findGuidScheduleLogPageCount(FindGuidScheduleLogPage findGuidScheduleLogPage);
    
    List<GuidScheduleLogDto> findGuidScheduleLogPageGroupByGuid(FindGuidSchedulePage findGuidSchedulePage);
    
    int findGuidScheduleLogPageGroupByGuidCount(FindGuidSchedulePage findGuidSchedulePage);
    
    /**按人查出所有排班*/
    List<GuidScheduleCycleDto> findGuidScheduleLogByGm(GuidScheduleDto guidScheduleDto);
    /**员工当周排班转移到历史*/
    int batchAddGuidScheduleLog();
    
}