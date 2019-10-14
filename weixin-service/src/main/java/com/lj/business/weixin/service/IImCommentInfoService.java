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
import com.lj.business.weixin.dto.FindImCommentInfoPage;
import com.lj.business.weixin.dto.FriendsMessageDto;
import com.lj.business.weixin.dto.ImCommentInfoDto;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author ldq
 * 
 * 
 *         CreateDate: 2017-08-22
 */
public interface IImCommentInfoService {

	/**
	 * 
	 *
	 * 方法说明：添加评论信息信息
	 *
	 * @param imCommentInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addImCommentInfo(ImCommentInfoDto imCommentInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找评论信息信息
	 *
	 * @param findImCommentInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public ImCommentInfoDto findImCommentInfo(ImCommentInfoDto imCommentInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改评论信息信息
	 *
	 * @param updateImCommentInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateImCommentInfo(ImCommentInfoDto imCommentInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询评论信息信息
	 *
	 * @param findImCommentInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<ImCommentInfoDto> findImCommentInfoPage(FindImCommentInfoPage findImCommentInfoPage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据朋友圈code查询评论
	 *
	 * @param friendCode
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月22日
	 *
	 */
	public List<ImCommentInfoDto> findImCommentInfoByFC(List<String> friendCode);

	public List<ImCommentInfoDto> findImCommentInfoByFC(String friendsCode, String noWxShop);

	/**
	 * 查询APP导购未读评论数据
	 * 
	 * @param noWxShop
	 * @param memberNoGm
	 * @return
	 */
	public Page<FriendsMessageDto> findFriendsMessage(FriendsMessageDto friendsMessage, Page<FriendsMessageDto> page);

	public Page<FriendsMessageDto> findFriendsMessageNotRelease(FriendsMessageDto friendsMessage,
			Page<FriendsMessageDto> page);

	public Integer findImCommentInfoAppNotReadCount(ImCommentInfoDto imCommentInfoDto);

	public Integer findImCommentInfoWebNotReadCount(ImCommentInfoDto imCommentInfoDto);

	/**
	 * 根据时间戳查询评论
	 * 
	 * @param friendsCode
	 * @param timestamp
	 * @return
	 */
	public ImCommentInfoDto getImCommentInfoTimestamp(String noWxShop, String friendsCode, String timestamp);

	/**
	 * 根据friendsId查询评论
	 * 
	 * @param noWxShop
	 * @param friendsId
	 * @param timestamp
	 * @return
	 */
	public ImCommentInfoDto getImCommentInfoByFriendsId(String noWxShop, String friendsId, String timestamp);

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈评论中commentSvrId最大的值
	 * 
	 * @param friendsCode
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月21日
	 *
	 */
	public int getMaxCommentSvrId(String friendsCode);

	/**
	 * @Desc
	 * @param imCommentInfoDto
	 * @return void
	 * @author 贾光朝
	 * @createDate 2019年5月7日下午5:55:07
	 */
	public void delete(ImCommentInfoDto imCommentInfoDto);

}
