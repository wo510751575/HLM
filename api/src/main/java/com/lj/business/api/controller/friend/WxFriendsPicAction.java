package com.lj.business.api.controller.friend;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.dto.friend.FriendsDownRequestDto;
import com.lj.business.weixin.dto.ToDownloadPic;
import com.lj.business.weixin.service.IImFriendsFacade;

@Controller
@RequestMapping("/friendsPic/")
public class WxFriendsPicAction extends Action {
	
	
	@Resource
	IImFriendsFacade friendsFacade;
	
	/**
	 * 图片下载请求
	 * @param friendsDownRequestDto
	 * @return
	 */
	@RequestMapping("toDownload.do")
	@ResponseBody
	public GeneralResponse toDownload(FriendsDownRequestDto  friendsDownRequestDto){
		logger.info("WxFriendsPicAction.toDownload------------ >{}",friendsDownRequestDto);
		AssertUtils.notNullAndEmpty(friendsDownRequestDto.getNoWx(),"终端微信不能为空");
		AssertUtils.notNullAndEmpty(friendsDownRequestDto.getFriendsId(),"朋友圈ID不能为空");
		AssertUtils.notNullAndEmpty(friendsDownRequestDto.getMemberNoGm(),"导购编号不能为空");
		ToDownloadPic toDownload = new ToDownloadPic();
		toDownload.setNoWx(friendsDownRequestDto.getNoWx());
		toDownload.setFriendsId(friendsDownRequestDto.getFriendsId());
//		toDownload.setImgUrl(friendsDownRequestDto.getPicUrl());	由服务端控制，只下载未解密的图
		toDownload.setMemberNoGm(friendsDownRequestDto.getMemberNoGm());
		
		friendsFacade.toDownloadPic(toDownload);
		return GeneralResponse.generateSuccessResponse();
	}

}
