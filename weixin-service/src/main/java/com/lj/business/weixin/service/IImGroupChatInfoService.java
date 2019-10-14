package com.lj.business.weixin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.weixin.dto.ImGroupChatInfoDto;
import com.lj.business.weixin.dto.FindImGroupChatInfoPage;


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
public interface IImGroupChatInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加群发聊天设置信息
	 *
	 * @param imGroupChatInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public String addImGroupChatInfo(ImGroupChatInfoDto imGroupChatInfoDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找群发聊天设置信息
	 *
	 * @param findImGroupChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public ImGroupChatInfoDto findImGroupChatInfo(ImGroupChatInfoDto imGroupChatInfoDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询群发聊天设置信息
	 *
	 * @param findImGroupChatInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<ImGroupChatInfoDto>  findImGroupChatInfos(FindImGroupChatInfoPage findImGroupChatInfoPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改群发聊天设置信息
	 *
	 * @param updateImGroupChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateImGroupChatInfo(ImGroupChatInfoDto imGroupChatInfoDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询群发聊天设置信息
	 *
	 * @param findImGroupChatInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<ImGroupChatInfoDto> findImGroupChatInfoPage(FindImGroupChatInfoPage findImGroupChatInfoPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param imGroupChatInfoDto
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午6:08:49
	 */
	public void delete(ImGroupChatInfoDto imGroupChatInfoDto);
	

}
