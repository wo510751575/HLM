package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.InformedConsentDto;
import com.ye.business.hx.dto.FindInformedConsentPage;


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
public interface IInformedConsentService {
	
	/**
	 * 
	 *
	 * 方法说明：添加知情同意书信息
	 *
	 * @param informedConsentDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addInformedConsent(InformedConsentDto informedConsentDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找知情同意书信息
	 *
	 * @param findInformedConsent
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public InformedConsentDto findInformedConsent(InformedConsentDto informedConsentDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询知情同意书信息
	 *
	 * @param findInformedConsentPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<InformedConsentDto>  findInformedConsents(FindInformedConsentPage findInformedConsentPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改知情同意书信息
	 *
	 * @param updateInformedConsent
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateInformedConsent(InformedConsentDto informedConsentDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询知情同意书信息
	 *
	 * @param findInformedConsentPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<InformedConsentDto> findInformedConsentPage(FindInformedConsentPage findInformedConsentPage) throws TsfaServiceException;

	/**   
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param informedConsentDto      
	 * @return: void      
	 * @throws   
	 */
	public void delete(InformedConsentDto informedConsentDto)throws TsfaServiceException;
	

}
