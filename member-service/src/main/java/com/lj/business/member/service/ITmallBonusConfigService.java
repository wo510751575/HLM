package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.member.dto.TmallBonusConfigDto;
import com.lj.business.member.dto.FindTmallBonusConfigPage;


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
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public interface ITmallBonusConfigService {
	
	/**
	 * 
	 *
	 * 方法说明：添加天猫订单红包配置信息
	 *
	 * @param tmallBonusConfigDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addTmallBonusConfig(TmallBonusConfigDto tmallBonusConfigDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单红包配置信息
	 *
	 * @param findTmallBonusConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public TmallBonusConfigDto findTmallBonusConfig(TmallBonusConfigDto tmallBonusConfigDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询天猫订单红包配置信息
	 *
	 * @param findTmallBonusConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<TmallBonusConfigDto>  findTmallBonusConfigs(FindTmallBonusConfigPage findTmallBonusConfigPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改天猫订单红包配置信息
	 *
	 * @param updateTmallBonusConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateTmallBonusConfig(TmallBonusConfigDto tmallBonusConfigDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询天猫订单红包配置信息
	 *
	 * @param findTmallBonusConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<TmallBonusConfigDto> findTmallBonusConfigPage(FindTmallBonusConfigPage findTmallBonusConfigPage) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明：删除天猫订单红包配置信息
	 *
	 * @param updateTmallBonusConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void deleteTmallBonusConfig(TmallBonusConfigDto tmallBonusConfigDto)throws TsfaServiceException;

}
