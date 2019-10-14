/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service;

import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.IPage;
import com.lj.business.member.dto.im.*;
import com.lj.base.exception.TsfaServiceException;
/**
 * 
 * 类说明：客户服务接口类（IM相关）
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月28日
 */
public interface IPersonMemberImService {
	
	/**
	 * 
	 *
	 * 方法说明：查询客户最大版本号
	 *
	 * @param findMaxVersion
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月27日
	 *
	 */
	public long findMaxVersion(FindMaxVersion findMaxVersion);
	
	/**
	 * 
	 *
	 * 方法说明：分页查询IM微信好友
	 *
	 * @param findImFriendsPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月28日
	 *
	 */
	public IPage<FindImFriendsPageReturn> findImFriends(FindImFriendsPage findImFriendsPage);
	
	/**
	 * 
	 *
	 * 方法说明：查询客户信息(IM聊天)
	 *
	 * @param findPersonMemberByChat
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月10日
	 *
	 */
	public FindPersonMemberByChatReturn findPersonMemberByChat(FindPersonMemberByChat findPersonMemberByChat);

	/**
	 *
	 * 方法说明：加载导购与客户的关联信息到缓存
	 *
	 * @return
	 *
	 * @author dengxiudong CreateDate: 2018年1月31日
	 *
	 */
	@Deprecated
	public void cacheAllGmMemberRelateInfo() throws TsfaServiceException;

	/**
	 *
	 * 方法说明：加载导购与客户的关联信息到缓存
	 *
	 * @return
	 *
	 * @author dengxiudong CreateDate: 2018年1月31日
	 *
	 */
	@Deprecated
	public void cacheGmMemberRelateInfo(GmMemberRelateInfoDto gmMemberInfo) throws TsfaServiceException;

	/**
	 *
	 * 方法说明：通过导购和客户编号从缓存中获取关联信息
	 *
	 * @return
	 *
	 * @author dengxiudong CreateDate: 2018年1月31日
	 *
	 */
	@Deprecated
	public GmMemberRelateInfoDto getGmMemberRelateCacheByNo(String key) throws TsfaServiceException;

	/**
	 *
	 * 方法说明：通过微信从缓存中获取关联信息
	 *
	 * @return
	 *
	 * @author dengxiudong CreateDate: 2018年1月31日
	 *
	 */
	@Deprecated
	public GmMemberRelateInfoDto getGmMemberRelateCacheByWx(String key) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询绑定导购微信号所有客户的微信列表
	 *
	 * @param findMemberWxByNoWxGm
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月16日
	 *
	 */
	public Map<String, List<String>> findMemberWxByNoWxGm(FindMemberWxByNoWxGm findMemberWxByNoWxGm);

	/**
	 * 
	 *
	 * 方法说明：返回终端下添加了指定微信(或微信别名)的客户信息
	 *
	 * @param find
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月12日
	 *
	 */
	public FindPersonMemberByShopAndNoWxReturn findPersonMemberByShopWxAndNoWx(FindPersonMemberByShopAndNoWx find);
	
	/**
	 * 
	 *
	 * 法说明：查询客户微信基本信息
	 *
	 * @param memberNoGm
	 * @param memberNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月8日
	 *
	 */
	public PersonMemberWxInfo findPersonMemberWxInfo(String memberNoGm, String memberNo,String noWxGm);
	
	/**
	 * 
	 *
	 * 方法说明：同步客户微信基本信息
	 *
	 * @param syncPersonWxInfoRequest
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月8日
	 *
	 */
	public SyncPersonWxInfoResponse syncPersonWxInfo(SyncPersonWxInfoRequest syncPersonWxInfoRequest);
	
	/**
	 * 
	 *
	 * 方法说明：统计导购助手下未回复客户数
	 *
	 * @param assistantNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月18日
	 *
	 */
	@Deprecated
	public int findUnrespMemberCount(String assistantNo);
	
	
	/**
	 * 获取导购的客户数
	 * @param findImFriendsPage
	 * @return
	 * @throws TsfaServiceException
	 */
	public int findImFriendsCount(FindImFriendsPage findImFriendsPage) throws TsfaServiceException;
}
