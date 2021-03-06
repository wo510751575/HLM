package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientMedicalPlan;
import com.ye.business.hx.dto.FindPatientMedicalPlanPage;
import com.ye.business.hx.dto.PatientMedicalPlanDto;

public interface IPatientMedicalPlanDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(PatientMedicalPlan record);

    PatientMedicalPlan selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientMedicalPlan record);

	List<PatientMedicalPlanDto> findPatientMedicalPlans(FindPatientMedicalPlanPage findPatientMedicalPlanPage);

	int findPatientMedicalPlanPageCount(FindPatientMedicalPlanPage findPatientMedicalPlanPage);

	List<PatientMedicalPlanDto> findPatientMedicalPlanPage(FindPatientMedicalPlanPage findPatientMedicalPlanPage);

	/**   
	 * @Title: deleteByMedicalCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param code      
	 * @return: void      
	 * @throws   
	 */
	void deleteByMedicalCode(String code);
}