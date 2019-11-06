package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.SoftCheckDto;
import com.ye.business.hx.dto.FindSoftCheckPage;


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
public interface ISoftCheckService {
	
	/**
	 * 
	 *
	 * 方法说明：添加软组织检查信息
	 *
	 * @param softCheckDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addSoftCheck(SoftCheckDto softCheckDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找软组织检查信息
	 *
	 * @param findSoftCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public SoftCheckDto findSoftCheck(SoftCheckDto softCheckDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询软组织检查信息
	 *
	 * @param findSoftCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<SoftCheckDto>  findSoftChecks(FindSoftCheckPage findSoftCheckPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改软组织检查信息
	 *
	 * @param updateSoftCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateSoftCheck(SoftCheckDto softCheckDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询软组织检查信息
	 *
	 * @param findSoftCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<SoftCheckDto> findSoftCheckPage(FindSoftCheckPage findSoftCheckPage) throws TsfaServiceException;
	

}
