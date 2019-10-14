package com.lj.business.weixin.service;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.ImCommentCallBackDto;
import com.lj.business.weixin.dto.ImFriendsInfoCallBackDto;
import com.lj.business.weixin.dto.ImLikeCallBackDto;

public interface IImFriendsCallBackFacade {
	
	
	/**
	 * 点赞结果回调
	 * @param imLikeCallBackDto
	 */
	public void toLikeCallBack(ImLikeCallBackDto imLikeCallBackDto)throws TsfaServiceException;
	
	
	/**
	 * 评论结果回调
	 * @param imCommentCallBackDto
	 */
	public void toCommentCallBack(ImCommentCallBackDto imCommentCallBackDto)throws TsfaServiceException;
	
	
	/**
	 * 朋友圈发送结果回调
	 */
	public void toFriendsinfoCallBack(ImFriendsInfoCallBackDto imFriendsInfoCallBackDto)throws TsfaServiceException;
	
	
	/**
	 * 图片下载结果回调
	 * @param imFriendsPicDownCallBackDto
	 */
	public void toFriendsPicDownCallBack(ImFriendsPicDownCallBackDto imFriendsPicDownCallBackDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：中控回调删除朋友圈成功
	 *
	 * @param friendsCode
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月1日
	 *
	 */
	public void toDeleteFriendsCallBack(String friendsCode)throws TsfaServiceException;
}
