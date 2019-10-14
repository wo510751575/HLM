package com.lj.business.cf.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.cf.dto.callChatInfo.AddCallChatInfo;
import com.lj.business.cf.dto.callChatInfo.DelCallChatInfo;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfo;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoPage;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoPageReturn;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoReturn;
import com.lj.business.cf.dto.callChatInfo.UpdateCallChatInfo;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;


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
public interface ICallChatInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加电话聊天信息记录表信息
	 *
	 * @param addCallChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addCallChatInfo(AddCallChatInfo addCallChatInfo) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找电话聊天信息记录表信息
	 *
	 * @param findCallChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindCallChatInfoReturn findCallChatInfo(FindCallChatInfo findCallChatInfo) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除电话聊天信息记录表信息
	 *
	 * @param delCallChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delCallChatInfo(DelCallChatInfo delCallChatInfo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改电话聊天信息记录表信息
	 *
	 * @param updateCallChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateCallChatInfo(UpdateCallChatInfo updateCallChatInfo)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询电话聊天信息记录表信息
	 *
	 * @param findCallChatInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindCallChatInfoPageReturn> findCallChatInfoPage(FindCallChatInfoPage findCallChatInfoPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：上传通话记录信息
	 *
	 * @param paramJson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年07月10日
	 *
	 */
	public int uploadCallChatInfo(String paramJson) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询通话上传最大时间
	 *
	 * @param memberNoGuid
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博  CreateDate: 2017年10月10日
	 *
	 */
	public long getMaxCallDateByGuidNo(String memberNoGuid) throws TsfaServiceException;

}
