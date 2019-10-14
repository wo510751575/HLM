package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.TmallBonusRecord;
import com.lj.business.member.dto.FindTmallBonusRecordPage;
import com.lj.business.member.dto.TmallBonusRecordDto;

public interface ITmallBonusRecordDao {
    int deleteByPrimaryKey(String code);

    int insert(TmallBonusRecord record);

    int insertSelective(TmallBonusRecord record);

    TmallBonusRecord selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(TmallBonusRecord record);

    int updateByPrimaryKey(TmallBonusRecord record);
    
    List<TmallBonusRecordDto> findTmallBonusRecords(FindTmallBonusRecordPage findTmallBonusRecordPage);
    
    List<TmallBonusRecordDto> findTmallBonusRecordPage(FindTmallBonusRecordPage findTmallBonusRecordPage);
    
    int findTmallBonusRecordPageCount(FindTmallBonusRecordPage findTmallBonusRecordPage);
}