package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.pushconfig.AddWxPushConfig;
import com.lj.business.member.dto.pushconfig.AddWxPushConfigReturn;
import com.lj.business.member.dto.pushconfig.DelWxPushConfig;
import com.lj.business.member.dto.pushconfig.DelWxPushConfigReturn;
import com.lj.business.member.dto.pushconfig.FindWxPushConfig;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPage;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPageReturn;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigReturn;
import com.lj.business.member.dto.pushconfig.UpdateWxPushConfig;
import com.lj.business.member.dto.pushconfig.UpdateWxPushConfigReturn;


/**
 * 
 * 
 * 类说明：微信推送配置接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
public interface IWxPushConfigService {
	
	/**
	 * 
	 *
	 * 方法说明：添加微信推送配置信息
	 *
	 * @param addWxPushConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddWxPushConfigReturn addWxPushConfig(AddWxPushConfig addWxPushConfig) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找微信推送配置信息
	 *
	 * @param findWxPushConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindWxPushConfigReturn findWxPushConfig(FindWxPushConfig findWxPushConfig) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除微信推送配置信息
	 *
	 * @param delWxPushConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelWxPushConfigReturn delWxPushConfig(DelWxPushConfig delWxPushConfig) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改微信推送配置信息
	 *
	 * @param updateWxPushConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateWxPushConfigReturn updateWxPushConfig(UpdateWxPushConfig updateWxPushConfig)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找微信推送配置信息
	 *
	 * @param findWxPushConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindWxPushConfigPageReturn> findWxPushConfigPage(FindWxPushConfigPage findWxPushConfigPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询终端待推送配置列表，以机构类型按终端、商户为次序进行排序
	 *
	 * @param findWxPushConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月7日
	 *
	 */
	public List<FindWxPushConfigPageReturn> findWxPushConfigToPush(FindWxPushConfigPage findWxPushConfigPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param updateWxPushConfig
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午5:19:12
	 */
	public void delete(UpdateWxPushConfig updateWxPushConfig);
}
