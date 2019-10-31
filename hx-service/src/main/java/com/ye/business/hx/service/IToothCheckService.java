package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.ToothCheckDto;
import com.ye.business.hx.dto.FindToothCheckPage;


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
public interface IToothCheckService {
	
	/**
	 * 
	 *
	 * 方法说明：添加牙齿检查信息
	 *
	 * @param toothCheckDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addToothCheck(ToothCheckDto toothCheckDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找牙齿检查信息
	 *
	 * @param findToothCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public ToothCheckDto findToothCheck(ToothCheckDto toothCheckDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询牙齿检查信息
	 *
	 * @param findToothCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<ToothCheckDto>  findToothChecks(FindToothCheckPage findToothCheckPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改牙齿检查信息
	 *
	 * @param updateToothCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateToothCheck(ToothCheckDto toothCheckDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询牙齿检查信息
	 *
	 * @param findToothCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<ToothCheckDto> findToothCheckPage(FindToothCheckPage findToothCheckPage) throws TsfaServiceException;

	/**   
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param toothCheckDto      
	 * @return: void      
	 * @throws   
	 */
	public void delete(ToothCheckDto toothCheckDto)throws TsfaServiceException;
	

}
