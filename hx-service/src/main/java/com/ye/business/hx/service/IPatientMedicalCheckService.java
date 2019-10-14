package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientMedicalCheckDto;
import com.ye.business.hx.dto.FindPatientMedicalCheckPage;

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
public interface IPatientMedicalCheckService {
	
	/**
	 * 
	 *
	 * 方法说明：添加病历检查信息
	 *
	 * @param patientMedicalCheckDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addPatientMedicalCheck(PatientMedicalCheckDto patientMedicalCheckDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找病历检查信息
	 *
	 * @param findPatientMedicalCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientMedicalCheckDto findPatientMedicalCheck(PatientMedicalCheckDto patientMedicalCheckDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询病历检查信息
	 *
	 * @param findPatientMedicalCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientMedicalCheckDto>  findPatientMedicalChecks(FindPatientMedicalCheckPage findPatientMedicalCheckPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改病历检查信息
	 *
	 * @param updatePatientMedicalCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientMedicalCheck(PatientMedicalCheckDto patientMedicalCheckDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询病历检查信息
	 *
	 * @param findPatientMedicalCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientMedicalCheckDto> findPatientMedicalCheckPage(FindPatientMedicalCheckPage findPatientMedicalCheckPage) throws TsfaServiceException;
	

}
