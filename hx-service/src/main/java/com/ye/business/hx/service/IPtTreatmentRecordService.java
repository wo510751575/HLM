package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PtTreatmentRecordDto;
import com.ye.business.hx.dto.FindPtTreatmentRecordPage;


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
public interface IPtTreatmentRecordService {
	
	/**
	 * 
	 *
	 * 方法说明：添加患者就诊记录信息
	 *
	 * @param ptTreatmentRecordDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PtTreatmentRecordDto addPtTreatmentRecord(PtTreatmentRecordDto ptTreatmentRecordDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找患者就诊记录信息
	 *
	 * @param findPtTreatmentRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public PtTreatmentRecordDto findPtTreatmentRecord(PtTreatmentRecordDto ptTreatmentRecordDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询患者就诊记录信息
	 *
	 * @param findPtTreatmentRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<PtTreatmentRecordDto>  findPtTreatmentRecords(FindPtTreatmentRecordPage findPtTreatmentRecordPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改患者就诊记录信息
	 *
	 * @param updatePtTreatmentRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updatePtTreatmentRecord(PtTreatmentRecordDto ptTreatmentRecordDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询患者就诊记录信息
	 *
	 * @param findPtTreatmentRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<PtTreatmentRecordDto> findPtTreatmentRecordPage(FindPtTreatmentRecordPage findPtTreatmentRecordPage) throws TsfaServiceException;
	

}
