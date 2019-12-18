package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.OrthodonticsProcessDto;
import com.ye.business.hx.domain.OrthodonticsProcess;
import com.ye.business.hx.dto.FindOrthodonticsProcessPage;


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
public interface IOrthodonticsProcessService {
	
	/**
	 * 
	 *
	 * 方法说明：添加正畸病历/正畸过程信息
	 *
	 * @param orthodonticsProcessDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addOrthodonticsProcess(OrthodonticsProcessDto orthodonticsProcessDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找正畸病历/正畸过程信息
	 *
	 * @param findOrthodonticsProcess
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<OrthodonticsProcessDto> findOrthodonticsProcess(OrthodonticsProcessDto orthodonticsProcessDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸病历/正畸过程信息
	 *
	 * @param findOrthodonticsProcessPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<OrthodonticsProcessDto>  findOrthodonticsProcesss(FindOrthodonticsProcessPage findOrthodonticsProcessPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改正畸病历/正畸过程信息
	 *
	 * @param updateOrthodonticsProcess
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateOrthodonticsProcess(OrthodonticsProcessDto orthodonticsProcessDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询正畸病历/正畸过程信息
	 *
	 * @param findOrthodonticsProcessPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<OrthodonticsProcessDto> findOrthodonticsProcessPage(FindOrthodonticsProcessPage findOrthodonticsProcessPage) throws TsfaServiceException;
	

}
