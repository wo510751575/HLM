package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientMedical;
import com.ye.business.hx.dto.FindPatientMedicalPage;
import com.ye.business.hx.dto.PatientMedicalDto;

public interface IPatientMedicalDao {
    int deleteByPrimaryKey(String code);

    int insert(PatientMedical record);

    int insertSelective(PatientMedical record);

    PatientMedical selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientMedical record);

    int updateByPrimaryKey(PatientMedical record);
    
    List<PatientMedicalDto> findPatientMedicals(FindPatientMedicalPage findPatientMedicalPage);
    
    List<PatientMedicalDto> findPatientMedicalPage(FindPatientMedicalPage findPatientMedicalPage);
    
    int findPatientMedicalPageCount(FindPatientMedicalPage findPatientMedicalPage);
    
    PatientMedical selectByPatientReservationCode(String code);
    
    int updateByPrimaryKeyMedical(PatientMedical record);
}