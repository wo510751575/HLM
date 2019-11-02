package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.DiagnosisDto;
import com.ye.business.hx.dto.FindDiagnosisPage;


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
public interface IDiagnosisService {
	
	/**
	 * 
	 *
	 * 方法说明：添加牙型/牙骨信息
	 *
	 * @param diagnosisDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addDiagnosis(DiagnosisDto diagnosisDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找牙型/牙骨信息
	 *
	 * @param findDiagnosis
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public DiagnosisDto findDiagnosis(DiagnosisDto diagnosisDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询牙型/牙骨信息
	 *
	 * @param findDiagnosisPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<DiagnosisDto>  findDiagnosiss(FindDiagnosisPage findDiagnosisPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改牙型/牙骨信息
	 *
	 * @param updateDiagnosis
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateDiagnosis(DiagnosisDto diagnosisDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询牙型/牙骨信息
	 *
	 * @param findDiagnosisPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<DiagnosisDto> findDiagnosisPage(FindDiagnosisPage findDiagnosisPage) throws TsfaServiceException;

	/**   
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param diagnosisDto      
	 * @return: void      
	 * @throws   
	 */
	public void delete(DiagnosisDto diagnosisDto);
	

}
