package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PatientMedicalTemplate;
import com.ye.business.hx.dto.FindPatientMedicalTemplatePage;
import com.ye.business.hx.dto.PatientMedicalTemplateDto;

public interface IPatientMedicalTemplateDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(PatientMedicalTemplate record);

    PatientMedicalTemplate selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PatientMedicalTemplate record);

	List<PatientMedicalTemplateDto> findPatientMedicalTemplates(
			FindPatientMedicalTemplatePage findPatientMedicalTemplatePage);

	int findPatientMedicalTemplatePageCount(FindPatientMedicalTemplatePage findPatientMedicalTemplatePage);

	List<PatientMedicalTemplateDto> findPatientMedicalTemplatePage(
			FindPatientMedicalTemplatePage findPatientMedicalTemplatePage);
}