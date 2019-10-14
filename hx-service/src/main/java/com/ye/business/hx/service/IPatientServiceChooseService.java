package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientServiceChooseDto;
import com.ye.business.hx.dto.FindPatientServiceChoosePage;


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
public interface IPatientServiceChooseService {
	
	/**
	 * 
	 *
	 * 方法说明：添加患者服务选择（预约项目）信息
	 *
	 * @param patientServiceChooseDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addPatientServiceChoose(PatientServiceChooseDto patientServiceChooseDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务选择（预约项目）信息
	 *
	 * @param findPatientServiceChoose
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PatientServiceChooseDto findPatientServiceChoose(PatientServiceChooseDto patientServiceChooseDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询患者服务选择（预约项目）信息
	 *
	 * @param findPatientServiceChoosePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<PatientServiceChooseDto>  findPatientServiceChooses(FindPatientServiceChoosePage findPatientServiceChoosePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改患者服务选择（预约项目）信息
	 *
	 * @param updatePatientServiceChoose
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updatePatientServiceChoose(PatientServiceChooseDto patientServiceChooseDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询患者服务选择（预约项目）信息
	 *
	 * @param findPatientServiceChoosePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<PatientServiceChooseDto> findPatientServiceChoosePage(FindPatientServiceChoosePage findPatientServiceChoosePage) throws TsfaServiceException;
	
	/**
	 * 方法说明：删除患者服务选择（预约项目）信息
	 * @param patientReservationCode
	 * @return
	 * @throws TsfaServiceException
	 * 
	 * * @author sjiying CreateDate: 2019-03-11
	 */
	public Integer deleteByPatientReservationCode(String patientReservationCode) throws TsfaServiceException;
}
