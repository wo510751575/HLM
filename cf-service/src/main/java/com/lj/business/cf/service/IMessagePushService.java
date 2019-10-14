package com.lj.business.cf.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cf.dto.messagePush.AddMessagePushDto;
import com.lj.business.cf.dto.messagePush.DelMessagePush;
import com.lj.business.cf.dto.messagePush.FindMessagePush;
import com.lj.business.cf.dto.messagePush.FindMessagePushPage;
import com.lj.business.cf.dto.messagePush.FindMessagePushPageReturn;
import com.lj.business.cf.dto.messagePush.FindMessagePushReturn;
import com.lj.business.cf.dto.messagePush.MessagePushCodeDto;
import com.lj.business.cf.dto.messagePush.UpdateMessagePush;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IMessagePushService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购行为信息记录表信息
	 *
	 * @param addMessagePush
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public List<MessagePushCodeDto> addMessagePush(AddMessagePushDto addMessagePushDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @param findMessagePush
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindMessagePushReturn findMessagePush(FindMessagePush findMessagePush) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @param delMessagePush
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delMessagePush(DelMessagePush delMessagePush) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @param updateMessagePush
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateMessagePush(UpdateMessagePush updateMessagePush)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购行为信息记录表信息
	 *
	 * @param findMessagePushPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindMessagePushPageReturn> findMessagePushPage(FindMessagePushPage findMessagePushPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查询推送记录
	 *
	 * @param findMessagePushPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年09月18日
	 *
	 */
	public List<FindMessagePushPageReturn> findMessagePushByGm(
			FindMessagePushPage findMessagePushPage);

	/**
	 * 
	 *
	 * 方法说明：查询推送记录
	 *
	 * @param findMessagePushPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年09月18日
	 *
	 */
	public List<FindMessagePushPageReturn> findMessagePushByPm(
			FindMessagePushPage findMessagePushPage);
}
