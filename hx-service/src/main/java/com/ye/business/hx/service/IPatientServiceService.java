package com.ye.business.hx.service;

import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.dto.FindPatientServicePage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientServiceDto;
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
public interface IPatientServiceService {
	
	/**
	 * 
	 *
	 * 方法说明：添加患者服务（预约/挂号/就诊）信息
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addPatientService(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务（预约/挂号/就诊）信息
	 *
	 * @param findPatientService
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PatientServiceDto findPatientService(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询患者服务（预约/挂号/就诊）信息
	 *
	 * @param findPatientServicePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<PatientServiceDto>  findPatientServices(FindPatientServicePage findPatientServicePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改患者服务（预约/挂号/就诊）信息
	 *
	 * @param updatePatientService
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updatePatientService(PatientServiceDto patientServiceDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询患者服务（预约/挂号/就诊）信息
	 *
	 * @param findPatientServicePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<PatientServiceDto> findPatientServicePage(FindPatientServicePage findPatientServicePage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：预约列表视图
	 *
	 * @param findPatientServicePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-08
	 *
	 */
	public Map<String, Object> findPatientReservationView(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：分页查询患者服务（预约/挂号/就诊）信息：关联服务项目
	 *
	 * @param findPatientServicePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-11
	 *
	 */
	public Page<PatientServiceDto> findPatientReservationPage(FindPatientServicePage findPatientServicePage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：添加患者服务（预约）信息
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-11
	 *
	 */
	public String addPatientReservationService(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：修改患者服务（预约）信息
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-11
	 *
	 */
	public void updatePatientReservationService(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：取消预约，取消挂号
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-11
	 *
	 */
	public void cancelPatientService(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务（预约/挂号/就诊）信息
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PatientServiceDto findPatientServiceByCode(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：咨询师接诊
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-31
	 *
	 */
	public void updateVisitingForAdvisory(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：医生接诊
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-31
	 *
	 */
	public void updateVisitingForDoctor(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：咨询师分诊
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-31
	 *
	 */
	public void updateTriageForAdvisory(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：医生转诊
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-31
	 *
	 */
	public void updateReferralForDoctor(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：治疗完成；医生，咨询师
	 *
	 * @param patientServiceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-03-31
	 *
	 */
	public void updateFinished(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
	/**
	 * 查询第一条记录
	 * @param findPatientServicePage
	 * @return
	 * @throws TsfaServiceException
	 */
	public PatientServiceDto getPatientServiceDtoByExample(FindPatientServicePage findPatientServicePage) throws TsfaServiceException;

	/**
	 * 移动端 简易修改预约信息
	 * @param patientServiceDto
	 * @throws TsfaServiceException
	 */
	public void editApplyByPhone(PatientServiceDto patientServiceDto) throws TsfaServiceException;
	
}
