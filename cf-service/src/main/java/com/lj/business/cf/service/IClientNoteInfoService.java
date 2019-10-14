package com.lj.business.cf.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cf.domain.ClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.AddClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.AddClientNoteInfoReturn;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoList;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoPage;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoPageReturn;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoReturn;
import com.lj.business.cf.dto.clientNoteInfo.UpdateClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.UpdateClientNoteInfoReturn;


/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年10月10日
 */
public interface IClientNoteInfoService {
	
   /**
    * 
    *
    * 方法说明：方法说明：添加短信信息记录表信息
    *
    * @param addClientNoteInfo
    * @return
    * @throws TsfaServiceException
    *
    * @author 罗书明 CreateDate: 2017年10月10日
    *
    */
	public void addClientNoteInfo(String info) throws TsfaServiceException;
	
    /**
     * 
     *
     * 方法说明：方法说明：查找短信信息记录表信息
     *
     * @param findClientNoteInfo
     * @return
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年10月10日
     *
     */
	public FindClientNoteInfoReturn findClientNoteInfo(FindClientNoteInfo findClientNoteInfo) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明： 方法说明：修改短信信息记录表信息
	 *
	 * @param updateClientNoteInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年10月10日
	 *
	 */
	public UpdateClientNoteInfoReturn updateClientNoteInfo(UpdateClientNoteInfo updateClientNoteInfo)throws TsfaServiceException;

	 /**
	  * 
	  *
	  * 方法说明： 方法说明：修改短信信息记录表信息
	  *
	  * @param findClientNoteInfoPage
	  * @return
	  * @throws TsfaServiceException
	  *
	  * @author 罗书明 CreateDate: 2017年10月10日
	  *
	  */
	public Page<FindClientNoteInfoPageReturn> findClientNoteInfoPage(FindClientNoteInfoPage findClientNoteInfoPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：返回上传数据的最后时间
	 *
	 * @param findClientNoteInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年10月10日
	 *
	 */
	public long clientNoteInfoSendTime(FindClientNoteInfo findClientNoteInfo)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据导购号查询某天导购和客户的短信沟通信息
	 *
	 * @param dateMap
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月12日
	 *
	 */
	public List<Map<String, Object>> findCountNoteByGm(
			Map<String, Object> dateMap);

	/**
	 * 
	 *
	 * 方法说明：查找某天导购与客户的第一条短信信息
	 *
	 * @param findClientNoteInfo
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月12日
	 *
	 */
	public ClientNoteInfo findFristNoteInfo(
			FindClientNoteInfo findClientNoteInfo);
	
	/**
	 * 
	 *
	 * 方法说明：查询有短信记录的所有导购
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年10月13日
	 *
	 */
	public List<FindClientNoteInfoList> findClientInfoMemberNoGm()throws TsfaServiceException;

}
