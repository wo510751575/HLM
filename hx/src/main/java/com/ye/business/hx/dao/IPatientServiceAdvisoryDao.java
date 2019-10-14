package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientServiceAdvisory;
import com.ye.business.hx.dto.FindPatientServiceAdvisoryPage;
import com.ye.business.hx.dto.PatientServiceAdvisoryDto;

public interface IPatientServiceAdvisoryDao {
    int deleteByPrimaryKey(String code);

    int insert(PatientServiceAdvisory record);

    int insertSelective(PatientServiceAdvisory record);

    PatientServiceAdvisory selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientServiceAdvisory record);

    int updateByPrimaryKey(PatientServiceAdvisory record);
    
    List<PatientServiceAdvisoryDto> findPatientServiceAdvisorys(FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage);
    
    List<PatientServiceAdvisoryDto> findPatientServiceAdvisoryPage(FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage);
    
    int findPatientServiceAdvisoryPageCount(FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage);
    
    /**
     * 预约服务code查询
     * @param patientReservationCode
     * @return
     */
    PatientServiceAdvisory selectByPatientReservationCode(String patientReservationCode);
    
    /**
     * 查询咨询记录
     * @param code
     * @return
     */
    List<PatientServiceAdvisoryDto> queryAdvisoryRecords(String code);
}