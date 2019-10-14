package com.lj.business.weixin.service.impl.handler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.friends.AddCommentInfoMessage;
import com.lj.business.supcon.dto.friends.AddFriendsInfoMessage;
import com.lj.business.supcon.dto.friends.AddFriendsPicDownMessage;
import com.lj.business.supcon.dto.friends.AddLikesInfoMessage;
import com.lj.business.supcon.service.IChatFriendsFacade;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.dto.ToDownloadPic;

@Component
public class ChatFriendsHandler {

	private static Logger LOG = LoggerFactory.getLogger(ChatFriendsHandler.class);
	

	@Autowired 
	ICommonService commonService;
	@Resource
	FriendsQueryHandler friendsQueryHandler;
	
	@Resource
	IShopTerminalService shopTerminalService;
	
	public boolean sendFriendsMessage(ImFriendsInfoDto imFriendsInfoDto){
		try {
			 LOG.debug(" begin send add friends info to wx :{} ,noWxShop:{} , imei:{} ",imFriendsInfoDto,imFriendsInfoDto.getNoWxShop());
			 FindShopTerminalReturn shopTerminal = friendsQueryHandler.getShopTerminalServiceByNoWx(imFriendsInfoDto.getNoWx());

			 AddFriendsInfoMessage addFriendsInfoMessage = new AddFriendsInfoMessage();
			 addFriendsInfoMessage.setFriendsCode(imFriendsInfoDto.getCode());
			 addFriendsInfoMessage.setMemberNoGm(imFriendsInfoDto.getMemberNo());
			 addFriendsInfoMessage.setNickName(imFriendsInfoDto.getNickName());
			 addFriendsInfoMessage.setNoWxShop(imFriendsInfoDto.getNoWxShop());
			 addFriendsInfoMessage.setResources(imFriendsInfoDto.getImgAddr());
			 addFriendsInfoMessage.setShareTitle(imFriendsInfoDto.getSharetitle());
			 addFriendsInfoMessage.setShareUrl(imFriendsInfoDto.getShareurl());
			 addFriendsInfoMessage.setType(imFriendsInfoDto.getType());
			 addFriendsInfoMessage.setContent(imFriendsInfoDto.getContent());
			 addFriendsInfoMessage.setHeadImg(shopTerminal.getHeadAddress());
			 addFriendsInfoMessage.setWhoType(imFriendsInfoDto.getWhoType());
			 addFriendsInfoMessage.setWhoNoWxs(imFriendsInfoDto.getWhoNoWxs());
			 addFriendsInfoMessage.setRemindNoWxs(imFriendsInfoDto.getRemindNoWxs());
			 IChatFriendsFacade basic =  commonService.getHessianIChatFriendsFacade(addFriendsInfoMessage.getNoWxShop());
			 basic.sendFriendsInfo(addFriendsInfoMessage);
			 LOG.debug("send friends success wait result callback :{}",addFriendsInfoMessage);
			 return true;
		} catch (Exception e) {
			LOG.error("send friends fail , :{}",imFriendsInfoDto);
		}
		return false;
	}
	
	
	public boolean sendLikesMessage(ImLikeInfoDto imLikeInfoDto,String memberNoGm){
		try {
			 LOG.debug(" begin send add likes  info to wx :{}",imLikeInfoDto);
			 AddLikesInfoMessage addLikesInfoMessage = new AddLikesInfoMessage();
			 addLikesInfoMessage.setFriendsId(imLikeInfoDto.getFriendsId());
			 addLikesInfoMessage.setLikesCode(imLikeInfoDto.getCode());
			 addLikesInfoMessage.setImei(imLikeInfoDto.getImei());
			 addLikesInfoMessage.setMemberNo(imLikeInfoDto.getMemberNo());
			 addLikesInfoMessage.setNickName(imLikeInfoDto.getNickname());
			 addLikesInfoMessage.setNoWxShop(imLikeInfoDto.getNoWxShop());
			 addLikesInfoMessage.setMemberNoGm(memberNoGm);
			 
			 IChatFriendsFacade basic =  commonService.getHessianIChatFriendsFacade(addLikesInfoMessage.getNoWxShop());
			 basic.sendLikeInfo(addLikesInfoMessage);
			 LOG.debug(" end send add likes info :{}",addLikesInfoMessage);
			 return true;
			} catch (Exception e) {
				LOG.error("send friends fail , :{}",imLikeInfoDto);
			}
		
		return false;
	}
	
	
	public boolean sendCommentMessage(ImCommentInfoDto imCommentInfoDto,String memberNoGm,String commentSvrId){
		
		try {
			LOG.debug(" begin send comment to zk wx :{}  ,data :{} ",imCommentInfoDto.getNoWxShop(),imCommentInfoDto);
			AddCommentInfoMessage addCommentInfoMessage = new AddCommentInfoMessage();
			addCommentInfoMessage.setCommentCode(imCommentInfoDto.getCode());
			addCommentInfoMessage.setFriendsId(imCommentInfoDto.getFriendsId());
			addCommentInfoMessage.setContent(imCommentInfoDto.getContent());
			addCommentInfoMessage.setImei(imCommentInfoDto.getImei());
			addCommentInfoMessage.setMemberNoGm(memberNoGm);
			addCommentInfoMessage.setNickName(imCommentInfoDto.getNickname());
			addCommentInfoMessage.setNoWxShop(imCommentInfoDto.getNoWxShop());
			addCommentInfoMessage.setToNickName(imCommentInfoDto.getTonickname());
			addCommentInfoMessage.setToNoWx(imCommentInfoDto.getTousername());
//			addCommentInfoMessage.setShopNo(imCommentInfoDto.getShopNo());
			addCommentInfoMessage.setCommentSerId(commentSvrId);
			
			IChatFriendsFacade basic =  commonService.getHessianIChatFriendsFacade(addCommentInfoMessage.getNoWxShop());
			basic.sendCommentInfo(addCommentInfoMessage);
			LOG.debug("end send comment to zk wx :{}  ", addCommentInfoMessage);
			return true;
		} catch (Exception e) {
			LOG.error("send  comment  friends fail , :{}",e,imCommentInfoDto);
			
		}
		return false;
	}
	
	
	public  boolean sendFriendsPicDownMessage(ToDownloadPic toDownloadPic){
		try {
			LOG.debug(" begin send download img  to zk wx data :{} ",toDownloadPic);
			AddFriendsPicDownMessage addFriendsPicDownMessage = new AddFriendsPicDownMessage();
			addFriendsPicDownMessage.setEncKey(toDownloadPic.getEncKey());
			addFriendsPicDownMessage.setFriendsId(toDownloadPic.getFriendsId());
			addFriendsPicDownMessage.setImgUrl(toDownloadPic.getImgUrl());
			addFriendsPicDownMessage.setNoWx(toDownloadPic.getNoWx());
			
			IChatFriendsFacade basic =  commonService.getHessianIChatFriendsFacade(addFriendsPicDownMessage.getNoWx());
			basic.sendPicUrlDownLoad(addFriendsPicDownMessage);
		} catch (Exception e) { 
			LOG.error("  SEND DOWNLOAD IMG error , {}",toDownloadPic,e);
		}
		return false;
	}
	
	
	


}
