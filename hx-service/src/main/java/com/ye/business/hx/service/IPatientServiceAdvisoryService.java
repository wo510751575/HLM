package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientServiceAdvisoryDto;
import com.ye.business.hx.dto.FindPatientServiceAdvisoryPage;


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
public interface IPatientServiceAdvisoryService {
	
	/**
	 * 
	 *
	 * 方法说明：添加患者服务咨询信息
	 *
	 * @param patientServiceAdvisoryDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addPatientServiceAdvisory(PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务咨询信息
	 *
	 * @param findPatientServiceAdvisory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PatientServiceAdvisoryDto findPatientServiceAdvisory(PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询患者服务咨询信息
	 *
	 * @param findPatientServiceAdvisoryPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<PatientServiceAdvisoryDto>  findPatientServiceAdvisorys(FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改患者服务咨询信息
	 *
	 * @param updatePatientServiceAdvisory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updatePatientServiceAdvisory(PatientServiceAdvisoryDto patientServiceAdvisoryDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询患者服务咨询信息
	 *
	 * @param findPatientServiceAdvisoryPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<PatientServiceAdvisoryDto> findPatientServiceAdvisoryPage(FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：添加患者服务咨询信息：预约服务
	 *
	 * @param patientServiceAdvisoryDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public String addPatientServiceAdvisoryByReservation(PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务咨询信息
	 *
	 * @param findPatientServiceAdvisory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PatientServiceAdvisoryDto findPatientServiceAdvisoryByPatientReservationCode(PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException;
}
