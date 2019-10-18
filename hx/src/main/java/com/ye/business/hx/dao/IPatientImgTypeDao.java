package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientImgType;
import com.ye.business.hx.dto.FindPatientImgTypePage;
import com.ye.business.hx.dto.PatientImgTypeDto;

public interface IPatientImgTypeDao {
    int deleteByPrimaryKey(String code);

    int insert(PatientImgType record);

    int insertSelective(PatientImgType record);

    PatientImgType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientImgType record);

    int updateByPrimaryKey(PatientImgType record);

	/**   
	 * @Title: findPatientImgTypes   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientImgTypePage
	 * @param: @return      
	 * @return: List<PatientImgTypeDto>      
	 * @throws   
	 */
	List<PatientImgTypeDto> findPatientImgTypes(FindPatientImgTypePage findPatientImgTypePage);

	/**   
	 * @Title: findPatientImgTypePage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientImgTypePage
	 * @param: @return      
	 * @return: List<PatientImgTypeDto>      
	 * @throws   
	 */
	List<PatientImgTypeDto> findPatientImgTypePage(FindPatientImgTypePage findPatientImgTypePage);

	/**   
	 * @Title: findPatientImgTypePageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientImgTypePage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findPatientImgTypePageCount(FindPatientImgTypePage findPatientImgTypePage);
}