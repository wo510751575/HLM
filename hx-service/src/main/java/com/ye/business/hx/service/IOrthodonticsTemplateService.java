package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.OrthodonticsTemplateDto;
import com.ye.business.hx.dto.OrthodonticsTemplateVo;
import com.ye.business.hx.dto.FindOrthodonticsTemplatePage;


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
public interface IOrthodonticsTemplateService {
	
	/**
	 * 
	 *
	 * 方法说明：添加正畸过程模板信息
	 *
	 * @param orthodonticsTemplateDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Boolean addOrthodonticsTemplate(OrthodonticsTemplateDto orthodonticsTemplateDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找正畸过程模板信息
	 *
	 * @param findOrthodonticsTemplate
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public OrthodonticsTemplateDto findOrthodonticsTemplate(OrthodonticsTemplateDto orthodonticsTemplateDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸过程模板信息
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<OrthodonticsTemplateVo>  findOrthodonticsTemplates()throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改正畸过程模板信息
	 *
	 * @param updateOrthodonticsTemplate
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateOrthodonticsTemplate(OrthodonticsTemplateDto orthodonticsTemplateDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询正畸过程模板信息
	 *
	 * @param findOrthodonticsTemplatePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<OrthodonticsTemplateDto> findOrthodonticsTemplatePage(FindOrthodonticsTemplatePage findOrthodonticsTemplatePage) throws TsfaServiceException;

	/**   
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param orthodonticsTemplateDto      
	 * @return: void      
	 * @throws   
	 */
	public void delete(OrthodonticsTemplateDto orthodonticsTemplateDto)throws TsfaServiceException;
	

}
