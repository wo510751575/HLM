package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientMedicalTemplateList;
import com.ye.business.hx.dto.FindPatientMedicalTemplateListPage;
import com.ye.business.hx.dto.PatientMedicalTemplateListDto;

public interface IPatientMedicalTemplateListDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(PatientMedicalTemplateList record);

    PatientMedicalTemplateList selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientMedicalTemplateList record);

	List<PatientMedicalTemplateListDto> findPatientMedicalTemplateLists(
			FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage);

	int findPatientMedicalTemplateListPageCount(FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage);

	List<PatientMedicalTemplateListDto> findPatientMedicalTemplateListPage(
			FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage);
}