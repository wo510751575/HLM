package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientMedicalDm;
import com.ye.business.hx.dto.FindPatientMedicalDmPage;
import com.ye.business.hx.dto.PatientMedicalDmDto;

public interface IPatientMedicalDmDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(PatientMedicalDm record);

    PatientMedicalDm selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientMedicalDm record);

	List<PatientMedicalDmDto> findPatientMedicalDms(FindPatientMedicalDmPage findPatientMedicalDmPage);

	int findPatientMedicalDmPageCount(FindPatientMedicalDmPage findPatientMedicalDmPage);

	List<PatientMedicalDmDto> findPatientMedicalDmPage(FindPatientMedicalDmPage findPatientMedicalDmPage);

	/**   
	 * @Title: deleteByMedicalCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param code      
	 * @return: void      
	 * @throws   
	 */
	void deleteByMedicalCode(String code);
}