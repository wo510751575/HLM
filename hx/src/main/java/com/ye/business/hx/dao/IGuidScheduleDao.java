package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.GuidSchedule;
import com.ye.business.hx.dto.FindGuidSchedulePage;
import com.ye.business.hx.dto.GuidScheduleCycleDto;
import com.ye.business.hx.dto.GuidScheduleDto;

public interface IGuidScheduleDao {
    int deleteByPrimaryKey(String code);
    
    int deleteGuidSchedule(GuidScheduleDto record);

    int insert(GuidSchedule record);

    int insertSelective(GuidSchedule record);

    GuidSchedule selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(GuidSchedule record);

    int updateByPrimaryKey(GuidSchedule record);
    
    
    List<GuidScheduleDto> findGuidSchedules(FindGuidSchedulePage findGuidSchedulePage);
    
    List<GuidScheduleDto> findGuidSchedulePage(FindGuidSchedulePage findGuidSchedulePage);
    
    int findGuidSchedulePageCount(FindGuidSchedulePage findGuidSchedulePage);
    
    List<GuidScheduleDto> findGuidSchedulePageGroupByGuid(FindGuidSchedulePage findGuidSchedulePage);
    
    int findGuidSchedulePageGroupByGuidCount(FindGuidSchedulePage findGuidSchedulePage);
    
    /**按人查出所有排班*/
    List<GuidScheduleCycleDto> findGuidScheduleByGm(GuidScheduleDto guidScheduleDto);
    /**查可预约的员工*/
    List<GuidScheduleCycleDto> findEnableGuidSchedule(GuidScheduleDto guidScheduleDto);
    
    /**
     * 删除当周排班
     * @return
     */
    int deleteWeekGuidSchedule();
    
    /**
     * 员工固定排班转移到当周
     * @return
     */
    int batchAddGuidScheduleWeek();
    
}