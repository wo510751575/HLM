package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientImg;
import com.ye.business.hx.dto.FindPatientImgPage;
import com.ye.business.hx.dto.PatientImgDto;

public interface IPatientImgDao {
    int deleteByPrimaryKey(String code);

    int insert(PatientImg record);

    int insertSelective(PatientImg record);

    PatientImg selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientImg record);

    int updateByPrimaryKey(PatientImg record);

	/**   
	 * @Title: findPatientImgs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientImgPage
	 * @param: @return      
	 * @return: List<PatientImgDto>      
	 * @throws   
	 */
	List<PatientImgDto> findPatientImgs(FindPatientImgPage findPatientImgPage);

	/**   
	 * @Title: findPatientImgPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientImgPage
	 * @param: @return      
	 * @return: List<PatientImgDto>      
	 * @throws   
	 */
	List<PatientImgDto> findPatientImgPage(FindPatientImgPage findPatientImgPage);

	/**   
	 * @Title: findPatientImgPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findPatientImgPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findPatientImgPageCount(FindPatientImgPage findPatientImgPage);
}