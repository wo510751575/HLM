package com.lj.business.weixin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.domain.ImFriendsInfo;
import com.lj.business.weixin.dto.FindImFriendsInfoPage;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.UpdateFriendsMemberInfo;

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
 *         CreateDate: 2017-08-22
 */
public interface IImFriendsInfoService {

	/**
	 * 
	 *
	 * 方法说明：添加朋友圈信息信息
	 *
	 * @param imFriendsInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addImFriendsInfo(ImFriendsInfoDto imFriendsInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找朋友圈信息信息
	 *
	 * @param findImFriendsInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public ImFriendsInfoDto findImFriendsInfo(ImFriendsInfoDto imFriendsInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询朋友圈信息信息
	 *
	 * @param findImFriendsInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<ImFriendsInfoDto> findImFriendsInfos(FindImFriendsInfoPage findImFriendsInfoPage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改朋友圈信息信息
	 *
	 * @param updateImFriendsInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateImFriendsInfo(ImFriendsInfoDto imFriendsInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询朋友圈信息信息
	 *
	 * @param findImFriendsInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<ImFriendsInfoDto> findImFriendsInfoPage(FindImFriendsInfoPage findImFriendsInfoPage)
			throws TsfaServiceException;

	/**
	 * 方法说明：查询周期内待评论的朋友圈信息数量
	 * 
	 * @param noWxShop
	 * @param shopNo
	 * @return
	 * @author 李端强 CreateDate: 2018年2月28日
	 */
	public Integer findImFriendsInfoPendingCommentCount(String noWxShop) throws TsfaServiceException;

	/**
	 * 方法说明：查询周期内待评论的朋友圈信息
	 * 
	 * @param noWxShop
	 * @param shopNo
	 * @param start
	 * @param limit
	 * @return
	 * @author 李端强 CreateDate: 2018年2月28日
	 */
	public Page<ImFriendsInfoDto> findImFriendsInfoPendingComment(String noWxShop, Integer start, Integer limit)
			throws TsfaServiceException;

	/**
	 * 根据朋友圈ID查询朋友圈信息
	 * 
	 * @param friendsId
	 * @return
	 */
	public ImFriendsInfoDto getImFriendsInfoByFriendsId(String friendsId, String noWxShop);

	/**
	 * 查询朋友圈信息
	 * 
	 * @param findImFriendsInfoPage
	 * @return
	 * @throws TsfaServiceException
	 */
	public Page<ImFriendsInfoDto> findImFriendsInfo(FindImFriendsInfoPage findImFriendsInfoPage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈信息Web端
	 *
	 * @param findImFriendsInfoPage
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2018年1月2日
	 *
	 */
	public Page<ImFriendsInfoDto> findImFriendsInfoWeb(FindImFriendsInfoPage findImFriendsInfoPage);

	/**
	 * 查询朋友圈是否有未读
	 * 
	 * @param findImFriendsInfoPage
	 * @return
	 */
	public Integer findImFriendsNotRead(FindImFriendsInfoPage findImFriendsInfoPage);

	/**
	 * 客户发送朋友圈是否给提醒
	 * 
	 * @param memberNoGm
	 * @param memberNo
	 * @author 李端强 2018年1月26日21:59:00
	 * @return
	 */
	boolean findFriendPointByMemberNo(String memberNoGm, String memberNo);

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈待回复数量
	 *
	 * @param findImFriendsInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年3月12日
	 *
	 */
	Integer findImFriendsInfoToReplyCount(FindImFriendsInfoPage findImFriendsInfoPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：更新朋友圈待回复数量
	 *
	 * @param friendsCode
	 * @param delta       变化量
	 *
	 * @author 许新龙 CreateDate: 2018年3月12日
	 *
	 */
	void updateImFriendsInfoToReplyCount(String friendsCode, Integer delta);

	/**
	 * 
	 *
	 * 方法说明：客户未认领前发的朋友圈、评论和点赞没有客户信息，认领后需更新其客户信息
	 *
	 * @param updateFriendsMemberInfo
	 *
	 * @author 曾垂瑜 CreateDate: 2018年5月4日
	 *
	 */
	public void updateFriendsMemberInfo(UpdateFriendsMemberInfo updateFriendsMemberInfo);

	/**
	 * 
	 *
	 * 方法说明：向中控请求删除朋友圈
	 *
	 * @param friendsCode
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月1日
	 *
	 */
	public void sendDeleteFriendsInfo(String friendsCode, String memberNoGm);

	/**
	 * 
	 *
	 * 方法说明：删除朋友圈
	 *
	 * @param friendsCode
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月1日
	 *
	 */
	public void doDeleteFriendsInfo(String friendsCode);

	/**
	 * 根据微信号
	 * 
	 * @param memberNo
	 * @param memberNoGm
	 * @return
	 */
	ImFriendsInfoDto findImFriendsInfoByNoWx(String nowx);

	/**
	 * 查询朋友圈数据（含点赞和评论总数统计）
	 * 
	 * @param findImFriendsInfoPage
	 * @return
	 * @throws TsfaServiceException
	 */
	public Page<ImFriendsInfo> selectFriendsDatasPage(FindImFriendsInfoPage findImFriendsInfoPage)
			throws TsfaServiceException;

	/**
	 * @Desc
	 * @param merchantNo
	 * @param noWx
	 * @return void
	 * @author 贾光朝
	 * @createDate 2019年5月8日上午11:50:37
	 */
	public void delete(String merchantNo, String noWx);
}
