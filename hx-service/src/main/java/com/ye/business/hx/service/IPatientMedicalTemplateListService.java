package com.ye.business.hx.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.dto.FindPatientMedicalTemplateListPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientMedicalTemplateListDto;
import com.ye.business.hx.dto.PatientMedicalTemplateListVo;
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
public interface IPatientMedicalTemplateListService {
	
	/**
	 * 
	 *
	 * 方法说明：添加模版目录信息
	 *
	 * @param patientMedicalTemplateListDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public boolean addPatientMedicalTemplateList(PatientMedicalTemplateListDto patientMedicalTemplateListDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找模版目录信息
	 *
	 * @param findPatientMedicalTemplateList
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientMedicalTemplateListDto findPatientMedicalTemplateList(PatientMedicalTemplateListDto patientMedicalTemplateListDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询模版目录信息
	 *
	 * @param findPatientMedicalTemplateListPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientMedicalTemplateListVo>  findPatientMedicalTemplateLists(FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改模版目录信息
	 *
	 * @param updatePatientMedicalTemplateList
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientMedicalTemplateList(PatientMedicalTemplateListDto patientMedicalTemplateListDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询模版目录信息
	 *
	 * @param findPatientMedicalTemplateListPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientMedicalTemplateListDto> findPatientMedicalTemplateListPage(FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage) throws TsfaServiceException;
	
	/**
	 * 删除
	 * @param patientMedicalTemplateListDto
	 * @throws TsfaServiceException
	 */
	public void del(PatientMedicalTemplateListDto patientMedicalTemplateListDto)throws TsfaServiceException;

	
}
