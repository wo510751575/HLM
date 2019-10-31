package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientSymptom;
import com.ye.business.hx.dto.FindPatientSymptomPage;
import com.ye.business.hx.dto.PatientSymptomDto;
import com.ye.business.hx.dto.PatientSymptomVo;

public interface IPatientSymptomDao {
    int deleteByPrimaryKey(String code);

    int insert(PatientSymptom record);

    int insertSelective(PatientSymptom record);

    PatientSymptom selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientSymptom record);

    int updateByPrimaryKey(PatientSymptom record);

	/**   
	 * @Title: findPatientSymptoms   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientSymptomPage
	 * @param: @return      
	 * @return: List<PatientSymptomDto>      
	 * @throws   
	 */
	List<PatientSymptomVo> findPatientSymptoms(FindPatientSymptomPage findPatientSymptomPage);

	/**   
	 * @Title: findPatientSymptomPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientSymptomPage
	 * @param: @return      
	 * @return: List<PatientSymptomDto>      
	 * @throws   
	 */
	List<PatientSymptomDto> findPatientSymptomPage(FindPatientSymptomPage findPatientSymptomPage);

	/**   
	 * @Title: findPatientSymptomPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientSymptomPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findPatientSymptomPageCount(FindPatientSymptomPage findPatientSymptomPage);
}