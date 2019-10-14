package com.lj.business.weixin.service;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.AddWxCommentInfoReturn;
import com.lj.business.weixin.dto.AddWxFriendsInfoReturn;
import com.lj.business.weixin.dto.AddWxLikeInfoReturn;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;

public interface IWxFriendsDataService {
	
	/**
	 * 批量导入朋友圈信息	
	 * @param wxFriendsInfosList
	 * @return
	 */
	public AddWxFriendsInfoReturn addWxFriendsInfoData(List<ImFriendsInfoDto> wxFriendsInfosList)throws TsfaServiceException;
	
	
	/**
	 * 导入点赞数据
	 * @param likesList
	 * @return
	 */
	public AddWxLikeInfoReturn addWxLikeInfoData(List<ImLikeInfoDto> likesList,String noWxShop)throws TsfaServiceException;
	
	
	
	/**
	 * 导入评论信息
	 * @param commentInfList
	 * @return
	 */
	public AddWxCommentInfoReturn  addWxCommentInfoData(List<ImCommentInfoDto> commentInfList,String noWxShop)throws TsfaServiceException;
	
	/**
	 * 解绑认领的时候去除朋友圈
	 * @param memberNoGm	导购编号
	 * @param noWx	客户微信
	 * @param memberNo 客户编号
	 * @param noWxGm	终端微信
	 * @throws TsfaServiceException
	 */
	public void updateCancleFriendsCommentData(String memberNoGm, String noWx, String memberNo,String noWxGm)throws TsfaServiceException;
	
	
	
	
	
	

}
