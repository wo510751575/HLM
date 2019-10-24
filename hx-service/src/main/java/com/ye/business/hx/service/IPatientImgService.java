package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientImgDto;
import com.ye.business.hx.dto.FindPatientImgPage;


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
public interface IPatientImgService {
	
	/**
	 * 
	 *
	 * 方法说明：添加患者影像信息
	 *
	 * @param patientImgDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addPatientImg(PatientImgDto patientImgDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找患者影像信息
	 *
	 * @param findPatientImg
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientImgDto findPatientImg(PatientImgDto patientImgDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询患者影像信息
	 *
	 * @param findPatientImgPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientImgDto>  findPatientImgs(FindPatientImgPage findPatientImgPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改患者影像信息
	 *
	 * @param updatePatientImg
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientImg(PatientImgDto patientImgDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询患者影像信息
	 *
	 * @param findPatientImgPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientImgDto> findPatientImgPage(FindPatientImgPage findPatientImgPage) throws TsfaServiceException;

	/**   
	 * @Title: deleteImg   
	 * @Description: TODO(删除影像图片)   
	 * @param: @param dto      
	 * @return: void      
	 * @throws   
	 */
	public void deleteImg(PatientImgDto dto)throws TsfaServiceException;
	

}
