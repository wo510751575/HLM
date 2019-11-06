package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.GumCheckDto;
import com.ye.business.hx.dto.FindGumCheckPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.Date;
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
public interface IGumCheckService {
	
	/**
	 * 
	 *
	 * 方法说明：添加牙周检查信息
	 *
	 * @param gumCheckDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addGumCheck(GumCheckDto gumCheckDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找牙周检查信息
	 *
	 * @param findGumCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public GumCheckDto findGumCheck(GumCheckDto gumCheckDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询牙周检查信息
	 *
	 * @param findGumCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<GumCheckDto>  findGumChecks(FindGumCheckPage findGumCheckPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改牙周检查信息
	 *
	 * @param updateGumCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateGumCheck(GumCheckDto gumCheckDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询牙周检查信息
	 *
	 * @param findGumCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<GumCheckDto> findGumCheckPage(FindGumCheckPage findGumCheckPage) throws TsfaServiceException;

	/**   
	 * @Title: findTimeList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param findGumCheckPage
	 * @param: @return      
	 * @return: List<Date>      
	 * @throws   
	 */
	public List<Date> findTimeList(FindGumCheckPage findGumCheckPage)throws TsfaServiceException;
	

}
