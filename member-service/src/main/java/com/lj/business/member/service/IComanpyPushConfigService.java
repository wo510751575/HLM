package com.lj.business.member.service;

import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.company.AddComanpyPushConfig;
import com.lj.business.member.dto.company.AddComanpyPushConfigReturn;
import com.lj.business.member.dto.company.DelComanpyPushConfig;
import com.lj.business.member.dto.company.DelComanpyPushConfigReturn;
import com.lj.business.member.dto.company.FindComanpyPushConfig;
import com.lj.business.member.dto.company.FindCompanyPushConfigPage;
import com.lj.business.member.dto.company.FindComanpyPushConfigPageReturn;
import com.lj.business.member.dto.company.FindComanpyPushConfigSelective;
import com.lj.business.member.dto.company.FindComanpyPushConfigSelectiveReturn;
import com.lj.business.member.dto.company.FindCompanyPushConfigReturn;
import com.lj.business.member.dto.company.UpdateComanpyPushConfig;
import com.lj.business.member.dto.company.UpdateComanpyPushConfigReturn;


/**
 * 
 * 
 * 类说明：经销商推送配置接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年07月24日
 */
public interface IComanpyPushConfigService {
	
	/**
	 * 
	 *
	 * 方法说明：添加经销商推送配置信息
	 *
	 * @param addComanpyPushConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public AddComanpyPushConfigReturn addComanpyPushConfig(AddComanpyPushConfig addComanpyPushConfig) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找经销商推送配置信息
	 *
	 * @param findComanpyPushConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public FindCompanyPushConfigReturn findComanpyPushConfig(FindComanpyPushConfig findComanpyPushConfig) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除经销商推送配置信息
	 *
	 * @param delComanpyPushConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public DelComanpyPushConfigReturn delComanpyPushConfig(DelComanpyPushConfig delComanpyPushConfig) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改经销商推送配置信息
	 *
	 * @param updateComanpyPushConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public UpdateComanpyPushConfigReturn updateComanpyPushConfig(UpdateComanpyPushConfig updateComanpyPushConfig)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找经销商推送配置信息
	 *
	 * @param findComanpyPushConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年07月24日
	 *
	 */
	public Page<FindComanpyPushConfigPageReturn> findComanpyPushConfigPage(FindCompanyPushConfigPage findComanpyPushConfigPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据条件查询推送配置
	 *
	 * @param findComanpyPushConfigSelective
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年7月25日
	 *
	 */
	List<FindComanpyPushConfigSelectiveReturn> findComanpyPushConfigSelective(FindComanpyPushConfigSelective findComanpyPushConfigSelective) throws TsfaServiceException;
}
