package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientMedicalDmDto;
import com.ye.business.hx.dto.FindPatientMedicalDmPage;

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
public interface IPatientMedicalDmService {
	
	/**
	 * 
	 *
	 * 方法说明：添加处置与医嘱信息
	 *
	 * @param patientMedicalDmDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addPatientMedicalDm(PatientMedicalDmDto patientMedicalDmDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找处置与医嘱信息
	 *
	 * @param findPatientMedicalDm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientMedicalDmDto findPatientMedicalDm(PatientMedicalDmDto patientMedicalDmDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询处置与医嘱信息
	 *
	 * @param findPatientMedicalDmPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientMedicalDmDto>  findPatientMedicalDms(FindPatientMedicalDmPage findPatientMedicalDmPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改处置与医嘱信息
	 *
	 * @param updatePatientMedicalDm
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientMedicalDm(PatientMedicalDmDto patientMedicalDmDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询处置与医嘱信息
	 *
	 * @param findPatientMedicalDmPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientMedicalDmDto> findPatientMedicalDmPage(FindPatientMedicalDmPage findPatientMedicalDmPage) throws TsfaServiceException;
	

}
