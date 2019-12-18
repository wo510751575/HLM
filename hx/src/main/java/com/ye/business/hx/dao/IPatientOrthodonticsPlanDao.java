package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientOrthodonticsPlan;
import com.ye.business.hx.dto.FindPatientOrthodonticsPlanPage;
import com.ye.business.hx.dto.PatientOrthodonticsPlanDto;

public interface IPatientOrthodonticsPlanDao {
    int deleteByPrimaryKey(String code);

    int insert(PatientOrthodonticsPlan record);

    int insertSelective(PatientOrthodonticsPlan record);

    PatientOrthodonticsPlan selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientOrthodonticsPlan record);

    int updateByPrimaryKey(PatientOrthodonticsPlan record);

	/**   
	 * @Title: findPatientOrthodonticsPlans   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientOrthodonticsPlanPage
	 * @param: @return      
	 * @return: List<PatientOrthodonticsPlanDto>      
	 * @throws   
	 */
	List<PatientOrthodonticsPlanDto> findPatientOrthodonticsPlans(
			FindPatientOrthodonticsPlanPage findPatientOrthodonticsPlanPage);

	/**   
	 * @Title: findPatientOrthodonticsPlanPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientOrthodonticsPlanPage
	 * @param: @return      
	 * @return: List<PatientOrthodonticsPlanDto>      
	 * @throws   
	 */
	List<PatientOrthodonticsPlanDto> findPatientOrthodonticsPlanPage(
			FindPatientOrthodonticsPlanPage findPatientOrthodonticsPlanPage);

	/**   
	 * @Title: findPatientOrthodonticsPlanPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientOrthodonticsPlanPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findPatientOrthodonticsPlanPageCount(FindPatientOrthodonticsPlanPage findPatientOrthodonticsPlanPage);
}