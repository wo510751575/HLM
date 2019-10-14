package com.lj.business.supcon.service;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.supcon.dto.friends.AddCommentInfoMessage;
import com.lj.business.supcon.dto.friends.AddFriendsInfoMessage;
import com.lj.business.supcon.dto.friends.AddFriendsNotifyMessage;
import com.lj.business.supcon.dto.friends.AddFriendsPicDownMessage;
import com.lj.business.supcon.dto.friends.AddLikesInfoMessage;
import com.lj.business.supcon.dto.friends.DeleteFriendsInfoMessage;

public interface IChatFriendsFacade {

	/**
	 * 发布朋友圈信息
	 */
	public void sendFriendsInfo(AddFriendsInfoMessage addFriendsInfoMessage)throws TsfaServiceException;

	/**
	 * 发布评论信息
	 */
	public void sendCommentInfo(AddCommentInfoMessage addCommentInfoMessage)throws TsfaServiceException;

	/**
	 * 发布点赞信息
	 */
	public void sendLikeInfo(AddLikesInfoMessage addLikesInfoMessage)throws TsfaServiceException;
	
	/**
	 * 朋友圈图片下载请求
	 * @param addFriendsPicDownMessage
	 * @throws TsfaServiceException
	 */
	public void sendPicUrlDownLoad(AddFriendsPicDownMessage addFriendsPicDownMessage)throws TsfaServiceException;
	
	
	
	public void sendPicDownloadResult(AddFriendsNotifyMessage addFriendsNotifyMessage);

	/**
	 * 
	 *
	 * 方法说明：请求删除朋友圈
	 *
	 * @param deleteFriendsInfoMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月1日
	 *
	 */
	public void sendDeleteFriends(DeleteFriendsInfoMessage deleteFriendsInfoMessage);
	
	/**
	 * 通知导购端客户新增或移除
	 * @param memberNoGm
	 * @param memberNo
	 * @param state
	 * @param noWx
	 * @throws TsfaServiceException
	 */
	public void sendFriendsInfoForGm(String memberNoGm, String memberNo,String state,String noWx)throws TsfaServiceException;
}
