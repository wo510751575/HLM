package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientServiceChoose;
import com.ye.business.hx.dto.FindPatientServiceChoosePage;
import com.ye.business.hx.dto.PatientServiceChooseDto;

public interface IPatientServiceChooseDao {
    int deleteByPrimaryKey(String code);

    int insert(PatientServiceChoose record);

    int insertSelective(PatientServiceChoose record);

    PatientServiceChoose selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientServiceChoose record);

    int updateByPrimaryKey(PatientServiceChoose record);
    
    List<PatientServiceChooseDto> findPatientServiceChooses(FindPatientServiceChoosePage findPatientServiceChoosePage);
    
    List<PatientServiceChooseDto> findPatientServiceChoosePage(FindPatientServiceChoosePage findPatientServiceChoosePage);
    
    int findPatientServiceChoosePageCount(FindPatientServiceChoosePage findPatientServiceChoosePage);
    
    int deleteByPatientReservationCode(String patientReservationCode);
    
    /**
     * 查询列表
     * @param serviceCode
     * @return
     */
    List<PatientServiceChoose> queryByServiceCode(String serviceCode);
}