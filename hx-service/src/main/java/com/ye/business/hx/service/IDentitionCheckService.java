package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.DentitionCheckDto;
import com.ye.business.hx.dto.FindDentitionCheckPage;


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
public interface IDentitionCheckService {
	
	/**
	 * 
	 *
	 * 方法说明：添加牙列检查信息
	 *
	 * @param dentitionCheckDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addDentitionCheck(DentitionCheckDto dentitionCheckDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找牙列检查信息
	 *
	 * @param findDentitionCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public DentitionCheckDto findDentitionCheck(DentitionCheckDto dentitionCheckDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询牙列检查信息
	 *
	 * @param findDentitionCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<DentitionCheckDto>  findDentitionChecks(FindDentitionCheckPage findDentitionCheckPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改牙列检查信息
	 *
	 * @param updateDentitionCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateDentitionCheck(DentitionCheckDto dentitionCheckDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询牙列检查信息
	 *
	 * @param findDentitionCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<DentitionCheckDto> findDentitionCheckPage(FindDentitionCheckPage findDentitionCheckPage) throws TsfaServiceException;
	

}
