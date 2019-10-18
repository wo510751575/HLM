package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientImgTypeDto;
import com.ye.business.hx.dto.FindPatientImgTypePage;


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
public interface IPatientImgTypeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加患者影像类型信息
	 *
	 * @param patientImgTypeDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addPatientImgType(PatientImgTypeDto patientImgTypeDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找患者影像类型信息
	 *
	 * @param findPatientImgType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientImgTypeDto findPatientImgType(PatientImgTypeDto patientImgTypeDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询患者影像类型信息
	 *
	 * @param findPatientImgTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientImgTypeDto>  findPatientImgTypes(FindPatientImgTypePage findPatientImgTypePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改患者影像类型信息
	 *
	 * @param updatePatientImgType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientImgType(PatientImgTypeDto patientImgTypeDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询患者影像类型信息
	 *
	 * @param findPatientImgTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientImgTypeDto> findPatientImgTypePage(FindPatientImgTypePage findPatientImgTypePage) throws TsfaServiceException;
	

}
