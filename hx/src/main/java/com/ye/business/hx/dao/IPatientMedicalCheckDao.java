package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientMedicalCheck;
import com.ye.business.hx.dto.FindPatientMedicalCheckPage;
import com.ye.business.hx.dto.PatientMedicalCheckDto;

public interface IPatientMedicalCheckDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(PatientMedicalCheck record);

    PatientMedicalCheck selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientMedicalCheck record);

	List<PatientMedicalCheckDto> findPatientMedicalChecks(FindPatientMedicalCheckPage findPatientMedicalCheckPage);

	int findPatientMedicalCheckPageCount(FindPatientMedicalCheckPage findPatientMedicalCheckPage);

	List<PatientMedicalCheckDto> findPatientMedicalCheckPage(FindPatientMedicalCheckPage findPatientMedicalCheckPage);

	/**   
	 * @Title: deleteByMedicalCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param code      
	 * @return: void      
	 * @throws   
	 */
	void deleteByMedicalCode(String code);
}