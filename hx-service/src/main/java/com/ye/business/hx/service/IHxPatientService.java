package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.domain.HxPatient;
import com.ye.business.hx.dto.FindHxPatientPage;
import com.ye.business.hx.dto.HxPatientDto;
import com.ye.business.hx.dto.PatientServiceAdvisoryDto;
import com.ye.business.hx.dto.PatientServiceDto;
import com.ye.business.hx.dto.params.PatientParams;

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
 *         CreateDate: 2019.02.19
 */
public interface IHxPatientService {

	/**
	 * 
	 *
	 * 方法说明：添加患者信息
	 *
	 * @param hxPatientDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addHxPatient(HxPatientDto hxPatientDto)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找患者信息
	 *
	 * @param findHxPatient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public HxPatientDto findHxPatient(HxPatientDto hxPatientDto)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询患者信息
	 *
	 * @param findHxPatientPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<HxPatientDto> findHxPatients(FindHxPatientPage findHxPatientPage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改患者信息
	 *
	 * @param updateHxPatient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateHxPatient(HxPatientDto hxPatientDto)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询患者信息
	 *
	 * @param findHxPatientPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<HxPatientDto> findHxPatientPage(
			FindHxPatientPage findHxPatientPage) throws TsfaServiceException;

	/**
	 * 添加/编辑患者
	 * 
	 * @param patient
	 * @return
	 */
	public String save(HxPatient patient) throws TsfaServiceException;

	/**
	 * 患者列表
	 * 
	 * @param params
	 * @return
	 */
	public Page<HxPatientDto> list(PatientParams params);
	
	/**
	 * 患者咨询记录
	 * @param dto
	 * @return
	 */
	public List<PatientServiceAdvisoryDto> advisoryrecords(String code);
	
	/**
	 * 接诊记录
	 * @param code
	 * @return
	 */
	public List<PatientServiceDto> visitrecords(String code);
	
	/**
	 * 
	 *
	 * 方法说明：根据直通车客户编号查询患者
	 *
	 * @param findHxPatient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019年7月4日
	 *
	 */
	public HxPatientDto findHxPatientByMemberNo(HxPatientDto hxPatientDto)
			throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据姓名+手机号+商户号 查患者
	 *
	 * @param findHxPatient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019年7月4日
	 *
	 */
	public HxPatientDto findHxPatientByPhoneAndName(HxPatientDto hxPatientDto)
			throws TsfaServiceException;

	/**
	 * 给患者绑定微信
	 * @param hxPatientDto
	 * @throws TsfaServiceException
	 * @author lhy 2019.07.05 好乐美_APP_1.2
	 */
	public void bindWx(HxPatientDto hxPatientDto) throws TsfaServiceException;
	 
}
