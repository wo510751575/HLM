package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.Diagnosis;
import com.ye.business.hx.dto.DiagnosisDto;
import com.ye.business.hx.dto.FindDiagnosisPage;

public interface IDiagnosisDao {
    int deleteByPrimaryKey(String code);

    int insert(Diagnosis record);

    int insertSelective(Diagnosis record);

    Diagnosis selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Diagnosis record);

    int updateByPrimaryKey(Diagnosis record);

	/**   
	 * @Title: findDiagnosiss   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findDiagnosisPage
	 * @param: @return      
	 * @return: List<DiagnosisDto>      
	 * @throws   
	 */
	List<DiagnosisDto> findDiagnosiss(FindDiagnosisPage findDiagnosisPage);

	/**   
	 * @Title: findDiagnosisPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findDiagnosisPage
	 * @param: @return      
	 * @return: List<DiagnosisDto>      
	 * @throws   
	 */
	List<DiagnosisDto> findDiagnosisPage(FindDiagnosisPage findDiagnosisPage);

	/**   
	 * @Title: findDiagnosisPageCount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findDiagnosisPage
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */
	int findDiagnosisPageCount(FindDiagnosisPage findDiagnosisPage);
}