package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientMedicalTemplateDto;
import com.ye.business.hx.dto.FindPatientMedicalTemplatePage;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
public interface IPatientMedicalTemplateService {
	
	/**
	 * 
	 *
	 * 方法说明：添加模版信息
	 *
	 * @param patientMedicalTemplateDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addPatientMedicalTemplate(PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找模版信息
	 *
	 * @param findPatientMedicalTemplate
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientMedicalTemplateDto findPatientMedicalTemplate(PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询模版信息
	 *
	 * @param findPatientMedicalTemplatePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientMedicalTemplateDto>  findPatientMedicalTemplates(FindPatientMedicalTemplatePage findPatientMedicalTemplatePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改模版信息
	 *
	 * @param updatePatientMedicalTemplate
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientMedicalTemplate(PatientMedicalTemplateDto patientMedicalTemplateDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询模版信息
	 *
	 * @param findPatientMedicalTemplatePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientMedicalTemplateDto> findPatientMedicalTemplatePage(FindPatientMedicalTemplatePage findPatientMedicalTemplatePage) throws TsfaServiceException;
	
	/**
	 * 删除模版
	 * @param patientMedicalTemplateDto
	 * @throws TsfaServiceException
	 */
	public void del(PatientMedicalTemplateDto patientMedicalTemplateDto)throws TsfaServiceException;
}
