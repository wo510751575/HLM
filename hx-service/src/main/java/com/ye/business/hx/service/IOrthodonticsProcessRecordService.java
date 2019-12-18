package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.OrthodonticsProcessRecordDto;
import com.ye.business.hx.dto.FindOrthodonticsProcessRecordPage;
import com.ye.business.hx.dto.OrthodonticsProcessDto;
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
public interface IOrthodonticsProcessRecordService {
	
	/**
	 * 
	 *
	 * 方法说明：添加正畸过程信息
	 *
	 * @param orthodonticsProcessRecordDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addOrthodonticsProcessRecord(OrthodonticsProcessRecordDto orthodonticsProcessRecordDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找正畸过程信息
	 *
	 * @param findOrthodonticsProcessRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public OrthodonticsProcessRecordDto findOrthodonticsProcessRecord(OrthodonticsProcessRecordDto orthodonticsProcessRecordDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸过程信息
	 *
	 * @param findOrthodonticsProcessRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<OrthodonticsProcessRecordDto>  findOrthodonticsProcessRecords(FindOrthodonticsProcessRecordPage findOrthodonticsProcessRecordPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改正畸过程信息
	 *
	 * @param updateOrthodonticsProcessRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateOrthodonticsProcessRecord(OrthodonticsProcessRecordDto orthodonticsProcessRecordDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询正畸过程信息
	 *
	 * @param findOrthodonticsProcessRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<OrthodonticsProcessRecordDto> findOrthodonticsProcessRecordPage(FindOrthodonticsProcessRecordPage findOrthodonticsProcessRecordPage) throws TsfaServiceException;

	/**   
	 * @Title: del   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param orthodonticsProcessDto      
	 * @return: void      
	 * @throws   
	 */
	public void del(OrthodonticsProcessDto orthodonticsProcessDto);
	

}
