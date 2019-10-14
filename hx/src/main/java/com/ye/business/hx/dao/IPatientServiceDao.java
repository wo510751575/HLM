package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientService;
import com.ye.business.hx.dto.FindPatientServicePage;
import com.ye.business.hx.dto.PatientServiceDto;

public interface IPatientServiceDao {
    int deleteByPrimaryKey(byte[] code);

    int insert(PatientService record);

    int insertSelective(PatientService record);

    PatientService selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientService record);

    int updateByPrimaryKey(PatientService record);
    
    List<PatientServiceDto> findPatientServices(FindPatientServicePage findPatientServicePage);
    
    List<PatientServiceDto> findPatientServicePage(FindPatientServicePage findPatientServicePage);
    
    int findPatientServicePageCount(FindPatientServicePage findPatientServicePage);
    
    /**
     * 修改预约
     * @param record
     * @return
     */
    int updateReservationByPrimaryKey(PatientService record);
    
    /**
     * 修改挂号
     * @param record
     * @return
     */
    int updateRegisteredByPrimaryKey(PatientService record);
    
    /**
     * 查询接诊记录
     * @param code 患者code
     * @return
     */
    List<PatientServiceDto> queryVisitRecords(String code);
    
    /**
     * 查询一条记录
     * @param findPatientServicePage
     * @return
     */
    PatientServiceDto getPatientServiceByExample(FindPatientServicePage findPatientServicePage);
}