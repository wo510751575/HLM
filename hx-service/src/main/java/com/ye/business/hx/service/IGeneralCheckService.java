package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.GeneralCheckDto;
import com.ye.business.hx.dto.FindGeneralCheckPage;


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
public interface IGeneralCheckService {
	
	/**
	 * 
	 *
	 * 方法说明：添加一般检查信息
	 *
	 * @param generalCheckDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addGeneralCheck(GeneralCheckDto generalCheckDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找一般检查信息
	 *
	 * @param findGeneralCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public GeneralCheckDto findGeneralCheck(GeneralCheckDto generalCheckDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询一般检查信息
	 *
	 * @param findGeneralCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<GeneralCheckDto>  findGeneralChecks(FindGeneralCheckPage findGeneralCheckPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改一般检查信息
	 *
	 * @param updateGeneralCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateGeneralCheck(GeneralCheckDto generalCheckDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询一般检查信息
	 *
	 * @param findGeneralCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<GeneralCheckDto> findGeneralCheckPage(FindGeneralCheckPage findGeneralCheckPage) throws TsfaServiceException;
	

}
