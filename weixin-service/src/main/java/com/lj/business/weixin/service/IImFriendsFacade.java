package com.lj.business.weixin.service;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.AddWxCommentInfoReturn;
import com.lj.business.weixin.dto.AddWxFriendsInfoReturn;
import com.lj.business.weixin.dto.AddWxLikeInfoReturn;
import com.lj.business.weixin.dto.ToDownloadPic;
import com.lj.business.weixin.dto.ToFriendsCommentDto;
import com.lj.business.weixin.dto.ToFriendsInfosDto;
import com.lj.business.weixin.dto.ToFriendsLikeDto;

/**
 * 
 * @author ldq
 *
 */
public interface IImFriendsFacade {
	
	
	/**
	 * 点赞
	 * @return
	 */
	public AddWxLikeInfoReturn toImLike(ToFriendsLikeDto toLikeDto)throws TsfaServiceException;
	
	/**
	 * 评论
	 * @return
	 */
	public AddWxCommentInfoReturn toComment(ToFriendsCommentDto toFriendsCommentDto)throws TsfaServiceException;
	
	
	/**
	 * 发朋友圈
	 * @return
	 */
	public AddWxFriendsInfoReturn toFriendsInfo(ToFriendsInfosDto toFriendsInfosDto)throws TsfaServiceException;
	
	/**
	 * 朋友圈图片下载
	 * @param toDownload
	 */
	
	public void toDownloadPic(ToDownloadPic toDownload)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：重发朋友圈
	 *
	 * @param friendsCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月22日
	 *
	 */
	public AddWxFriendsInfoReturn resendFriendsInfo(String friendsCode)throws TsfaServiceException;
	
	
	

}
