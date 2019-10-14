package com.lj.business.weixin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.FindImLikeInfoPage;
import com.lj.business.weixin.dto.ImLikeInfoDto;

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
public interface IImLikeInfoService {

	/**
	 * 
	 *
	 * 方法说明：添加评论信息信息
	 *
	 * @param imLikeInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addImLikeInfo(ImLikeInfoDto imLikeInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找评论信息信息
	 *
	 * @param findImLikeInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public ImLikeInfoDto findImLikeInfo(ImLikeInfoDto imLikeInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询评论信息信息
	 *
	 * @param findImLikeInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<ImLikeInfoDto> findImLikeInfos(FindImLikeInfoPage findImLikeInfoPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改评论信息信息
	 *
	 * @param updateImLikeInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateImLikeInfo(ImLikeInfoDto imLikeInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据朋友圈code查询点赞信息
	 *
	 * @param friendCode
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月22日
	 *
	 */
	public List<ImLikeInfoDto> findImLikeInfoByFC(List<String> friendCode);

	public List<ImLikeInfoDto> findImLikeByFC(String friendsCode, String noWxShop);

	/**
	 * 查询APP导购未读点赞数据
	 * 
	 * @param noWxShop
	 * @param memberNoGm
	 * @return
	 */
	public int findImLikeAppNotRead(ImLikeInfoDto imLikeInfoDto);

	/**
	 * 查询WEB导购未读点赞数据
	 * 
	 * @param noWxShop
	 * @param memberNoGm
	 * @return
	 */
	public int findImLikeWebNotRead(ImLikeInfoDto imLikeInfoDto);

	public int findImLikeAppNotReadCount(ImLikeInfoDto imLikeInfoDto);

	public int findImLikeWebNotReadCount(ImLikeInfoDto imLikeInfoDto);

	public ImLikeInfoDto getImlikeInfoByNoWx(String noWxShop, String friendsCode, String noWx);

	public ImLikeInfoDto findImLikeInfoByNowxAndId(String noWxShop, String friendsId, String noWx);

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈下所有点赞客户username
	 *
	 * @param friendsCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月21日
	 *
	 */
	public List<String> findUsernameByFriendsCode(String friendsCode);

	/**
	 * @Desc
	 * @param imLikeInfoDto
	 * @return void
	 * @author 贾光朝
	 * @createDate 2019年5月7日下午6:15:12
	 */
	public void delete(ImLikeInfoDto imLikeInfoDto);

}
