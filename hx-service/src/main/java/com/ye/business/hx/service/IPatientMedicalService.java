package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientMedicalDto;
import com.ye.business.hx.dto.FindPatientMedicalPage;


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
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public interface IPatientMedicalService {
	
	/**
	 * 
	 *
	 * 方法说明：添加患者病历信息
	 *
	 * @param patientMedicalDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addPatientMedical(PatientMedicalDto patientMedicalDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找患者病历信息
	 *
	 * @param findPatientMedical
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PatientMedicalDto findPatientMedical(PatientMedicalDto patientMedicalDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询患者病历信息
	 *
	 * @param findPatientMedicalPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<PatientMedicalDto>  findPatientMedicals(FindPatientMedicalPage findPatientMedicalPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改患者病历信息
	 *
	 * @param updatePatientMedical
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updatePatientMedical(PatientMedicalDto patientMedicalDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询患者病历信息
	 *
	 * @param findPatientMedicalPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<PatientMedicalDto> findPatientMedicalPage(FindPatientMedicalPage findPatientMedicalPage) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明：添加患者病历信息
	 *
	 * @param patientMedicalDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public String addPatientMedicalByReservation(PatientMedicalDto patientMedicalDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找患者病历信息
	 *
	 * @param findPatientMedical
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PatientMedicalDto findPatientMedicalByPatientReservationCode(PatientMedicalDto patientMedicalDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：修改患者病历信息
	 *
	 * @param updatePatientMedical
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updatePatientMedicalByCode(PatientMedicalDto patientMedicalDto)throws TsfaServiceException;
}
