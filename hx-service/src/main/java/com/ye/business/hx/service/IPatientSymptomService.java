package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientSymptomDto;
import com.ye.business.hx.dto.PatientSymptomVo;
import com.ye.business.hx.dto.FindPatientSymptomPage;


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
public interface IPatientSymptomService {
	
	/**
	 * 
	 *
	 * 方法说明：添加症状信息
	 *
	 * @param patientSymptomDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addPatientSymptom(PatientSymptomDto patientSymptomDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找症状信息
	 *
	 * @param findPatientSymptom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientSymptomDto findPatientSymptom(PatientSymptomDto patientSymptomDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询症状信息
	 *
	 * @param findPatientSymptomPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientSymptomVo>  findPatientSymptoms(FindPatientSymptomPage findPatientSymptomPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改症状信息
	 *
	 * @param updatePatientSymptom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientSymptom(PatientSymptomDto patientSymptomDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询症状信息
	 *
	 * @param findPatientSymptomPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientSymptomDto> findPatientSymptomPage(FindPatientSymptomPage findPatientSymptomPage) throws TsfaServiceException;
	

}
