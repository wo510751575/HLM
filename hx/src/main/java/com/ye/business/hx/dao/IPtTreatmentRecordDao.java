package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PtTreatmentRecord;
import com.ye.business.hx.dto.FindPtTreatmentRecordPage;
import com.ye.business.hx.dto.PtTreatmentRecordDto;

public interface IPtTreatmentRecordDao {
    int deleteByPrimaryKey(String code);

    int insert(PtTreatmentRecord record);

    int insertSelective(PtTreatmentRecord record);

    PtTreatmentRecord selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PtTreatmentRecord record);

    int updateByPrimaryKey(PtTreatmentRecord record);
    
    List<PtTreatmentRecordDto> findPtTreatmentRecords(FindPtTreatmentRecordPage findPtTreatmentRecordPage);
    
    List<PtTreatmentRecordDto> findPtTreatmentRecordPage(FindPtTreatmentRecordPage findPtTreatmentRecordPage);
    
    int findPtTreatmentRecordPageCount(FindPtTreatmentRecordPage findPtTreatmentRecordPage);
}