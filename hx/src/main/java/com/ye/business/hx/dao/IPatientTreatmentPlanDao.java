package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientTreatmentPlan;
import com.ye.business.hx.dto.FindPatientTreatmentPlanPage;
import com.ye.business.hx.dto.PatientTreatmentPlanDto;

public interface IPatientTreatmentPlanDao {
    int deleteByPrimaryKey(String code);

    int insert(PatientTreatmentPlan record);

    int insertSelective(PatientTreatmentPlan record);

    PatientTreatmentPlan selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientTreatmentPlan record);

    int updateByPrimaryKeyWithBLOBs(PatientTreatmentPlan record);

    int updateByPrimaryKey(PatientTreatmentPlan record);

	/**   
	 * @Title: findPatientTreatmentPlans   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientTreatmentPlanPage
	 * @param: @return      
	 * @return: List<PatientTreatmentPlanDto>      
	 * @throws   
	 */
	List<PatientTreatmentPlanDto> findPatientTreatmentPlans(FindPatientTreatmentPlanPage findPatientTreatmentPlanPage);

	/**   
	 * @Title: findPatientTreatmentPlanPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientTreatmentPlanPage
	 * @param: @return      
	 * @return: List<PatientTreatmentPlanDto>      
	 * @throws   
	 */
	List<PatientTreatmentPlanDto> findPatientTreatmentPlanPage(
			FindPatientTreatmentPlanPage findPatientTreatmentPlanPage);

	/**   
	 * @Title: findPatientTreatmentPlanPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientTreatmentPlanPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findPatientTreatmentPlanPageCount(FindPatientTreatmentPlanPage findPatientTreatmentPlanPage);
}