package com.lj.business.supcon.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.dto.friends.AddCommentInfoMessage;
import com.lj.business.supcon.dto.friends.AddFriendsInfoMessage;
import com.lj.business.supcon.dto.friends.AddFriendsNotifyMessage;
import com.lj.business.supcon.dto.friends.AddFriendsPicDownMessage;
import com.lj.business.supcon.dto.friends.AddLikesInfoMessage;
import com.lj.business.supcon.dto.friends.DeleteFriendsInfoMessage;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.contacts.AddFriendResultResponse;
import com.lj.business.supcon.netty.message.friends.DeleteFriends;
import com.lj.business.supcon.netty.message.friends.SendFriendsComment;
import com.lj.business.supcon.netty.message.friends.SendFriendsInfo;
import com.lj.business.supcon.netty.message.friends.SendFriendsLike;
import com.lj.business.supcon.netty.message.friends.SendFriendsPicDown;
import com.lj.business.supcon.netty.message.friends.SendFriendsPicResultNotify;
import com.lj.business.supcon.service.IChatFriendsFacade;
import com.lj.business.utils.BeanUtils;
import com.lj.business.weixin.emus.FriendsInfoType;

@Service
public class ChatFriendsFacadeImpl implements IChatFriendsFacade {
	
	private static Logger LOG = LoggerFactory.getLogger(ChatFriendsFacadeImpl.class);
	
	@Resource
	ServerManager serverManger;
	
	@Resource
	IGmAssistantShopService gmAssistantShopService;
	
	@Override
	public void sendFriendsInfo(AddFriendsInfoMessage addFriendsInfoMessage)throws TsfaServiceException {
		LOG.debug("send friends info :{}",addFriendsInfoMessage);
		AssertUtils.notNull(addFriendsInfoMessage, "参数为空"); 
		AssertUtils.notNullAndEmpty(addFriendsInfoMessage.getNoWxShop(),"发送微信号不能为空");
		AssertUtils.notNullAndEmpty(addFriendsInfoMessage.getNickName(),"发送微信昵称不能为空");
		if(FriendsInfoType.MESSAGE.getValue().equals(addFriendsInfoMessage.getType())){
			AssertUtils.notNullAndEmpty(addFriendsInfoMessage.getContent(),"文字朋友圈内容不能为空");
		}else if(FriendsInfoType.SHARE.getValue().equals(addFriendsInfoMessage.getType())){
			AssertUtils.notNullAndEmpty(addFriendsInfoMessage.getShareUrl());
			AssertUtils.notNullAndEmpty(addFriendsInfoMessage.getShareTitle());
		}
		SendFriendsInfo sendFriendsInfo = new SendFriendsInfo();
		sendFriendsInfo.setCode(addFriendsInfoMessage.getFriendsCode());
		sendFriendsInfo.setContent(addFriendsInfoMessage.getContent());
		sendFriendsInfo.setNoWx(addFriendsInfoMessage.getNoWxShop());
		sendFriendsInfo.setResources(addFriendsInfoMessage.getResources());
		sendFriendsInfo.setShareTitle(addFriendsInfoMessage.getShareTitle());
		sendFriendsInfo.setShareUrl(addFriendsInfoMessage.getShareUrl());
		sendFriendsInfo.setType(addFriendsInfoMessage.getType());
		sendFriendsInfo.setWhoType(addFriendsInfoMessage.getWhoType());
		sendFriendsInfo.setWhoNoWxs(addFriendsInfoMessage.getWhoNoWxs());
		sendFriendsInfo.setRemindNoWxs(addFriendsInfoMessage.getRemindNoWxs());
		IoSession ioSession = ChannelUtils.getZkSessionByNoWx(addFriendsInfoMessage.getNoWxShop());	
		Message message = new  Message(MessageCode.SendFriendsInfo,GUID.generateByUUID(),sendFriendsInfo.toJson());
		if(ioSession!=null && ioSession.isLogin()){  
			serverManger.sendMessageAndCache(sendFriendsInfo.getNoWx(), ioSession.getChannel(), message);
			LOG.info(" send addFriendsInfo success :{}",sendFriendsInfo);
		}else {
			serverManger.cacheMsg(sendFriendsInfo.getNoWx(), message);
		}
		
		
	}

	@Override
	public void sendCommentInfo(AddCommentInfoMessage addCommentInfoMessage) throws TsfaServiceException {
		LOG.debug("begin send comment info :{}",addCommentInfoMessage);
		AssertUtils.notNull(addCommentInfoMessage);
		AssertUtils.notNullAndEmpty(addCommentInfoMessage.getFriendsId(),"评论朋友圈ID不能为空");
		AssertUtils.notNullAndEmpty(addCommentInfoMessage.getCommentCode(),"评论信息CODE不能为空");
		AssertUtils.notNullAndEmpty(addCommentInfoMessage.getContent(),"内容不能为空");
		AssertUtils.notNullAndEmpty(addCommentInfoMessage.getNoWxShop(),"终端微信号不能为空");
		SendFriendsComment sendComment = new SendFriendsComment();
		sendComment.setCode(addCommentInfoMessage.getCommentCode());
		sendComment.setFriendsId(addCommentInfoMessage.getFriendsId());
		sendComment.setNickName(addCommentInfoMessage.getNickName());
		sendComment.setNoWx(addCommentInfoMessage.getNoWxShop());
		sendComment.setToNickName(addCommentInfoMessage.getToNickName());
		sendComment.setToNoWx(addCommentInfoMessage.getToNoWx());
		sendComment.setTimestamp(System.currentTimeMillis());
		sendComment.setContent(addCommentInfoMessage.getContent());
		sendComment.setCommentSvrId(addCommentInfoMessage.getCommentSerId());
		IoSession ioSession = ChannelUtils.getZkSessionByNoWx(sendComment.getNoWx());
		if(ioSession!=null && ioSession.isLogin()){
			Message message = new  Message();
			message.setBody(sendComment.toJson());
			message.setCode(MessageCode.SendFriendsComment);
			message.setMsgId(GUID.generateByUUID());
			serverManger.sendMessageAndCache(sendComment.getNoWx(), ioSession.getChannel(), message);
			LOG.info("sendComment success :{}",sendComment);
		}else {
			throw  new  TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE,"该中控设备不在线");
		}
		
	}

	@Override
	public void sendLikeInfo(AddLikesInfoMessage addLikesInfoMessage)throws TsfaServiceException {
		LOG.debug("begin send like  info :{}",addLikesInfoMessage);
		AssertUtils.notNull(addLikesInfoMessage);
		AssertUtils.notNullAndEmpty(addLikesInfoMessage.getFriendsId(),"评论朋友圈ID不能为空");
		AssertUtils.notNullAndEmpty(addLikesInfoMessage.getLikesCode(),"评论信息CODE不能为空");
		AssertUtils.notNullAndEmpty(addLikesInfoMessage.getNoWxShop(),"中控微信号不能为空");
		SendFriendsLike sendFriendsLike  = new SendFriendsLike();
		sendFriendsLike.setCode(addLikesInfoMessage.getLikesCode());
		sendFriendsLike.setFriendsId(addLikesInfoMessage.getFriendsId());
		sendFriendsLike.setNickName(addLikesInfoMessage.getNickName());
		sendFriendsLike.setNoWx(addLikesInfoMessage.getNoWxShop());
		sendFriendsLike.setTimestamp(System.currentTimeMillis());
		IoSession ioSession = ChannelUtils.getZkSessionByNoWx(sendFriendsLike.getNoWx());
		if(ioSession!=null && ioSession.isLogin()){
			Message message = new  Message();
			message.setBody(sendFriendsLike.toJson());
			message.setCode(MessageCode.SendFriendsLike);
			message.setMsgId(GUID.generateByUUID());
			serverManger.sendMessageAndCache(sendFriendsLike.getNoWx(), ioSession.getChannel(), message);
			LOG.info("sendFriendsLike success :{}",sendFriendsLike);
		}else {
			throw  new  TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE,"该中控设备不在线");
		}
		
		
	}

	@Override
	public void sendPicUrlDownLoad(AddFriendsPicDownMessage addFriendsPicDownMessage)
			throws TsfaServiceException {
		LOG.info("begin sendPicUrlDownLoad :{}",addFriendsPicDownMessage);
		AssertUtils.notNull(addFriendsPicDownMessage);
		AssertUtils.notNullAndEmpty(addFriendsPicDownMessage.getFriendsId(),"朋友圈ID不能为空");
		 
		AssertUtils.notNullAndEmpty(addFriendsPicDownMessage.getNoWx(),"中控微信号不能为空");
	 
		SendFriendsPicDown sendFriendsPicDown = new SendFriendsPicDown();
		try {
			BeanUtils.copyPropertiesNull(sendFriendsPicDown, addFriendsPicDownMessage, true);
		} catch (Exception e) {
			LOG.error(" 复制 图片信息错误",e);
			throw new TsfaServiceException(ErrorCode.ACCESS_INVAILD, "请求信息有误");
		}
		
		String cacheAccountNo = sendFriendsPicDown.getNoWx();
		IoSession ioSession = ChannelUtils.getZkSessionByNoWx(cacheAccountNo);
		
		Message message = new  Message();
		message.setBody(sendFriendsPicDown.toJson());
		message.setCode(MessageCode.SendFriendsPicDown);
		message.setMsgId(GUID.generateByUUID());
		if(ioSession!=null && ioSession.isLogin()){
			serverManger.sendMessageAndCache(cacheAccountNo, ioSession.getChannel(), message);
			LOG.info("sendPicUrlDownLoad success :{}",sendFriendsPicDown);
		}else {
//			throw  new  TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE,"该中控设备不在线");
			LOG.error("中控不在线,缓存报文!");
			serverManger.sendLoginMsgAndCache(cacheAccountNo, message);
		}
		LOG.info("end sendPicUrlDownLoad :{}",addFriendsPicDownMessage);

	}

	@Override
	public void sendPicDownloadResult(AddFriendsNotifyMessage addFriendsNotifyMessage) {
		LOG.info(" sendPicDownloadResult ---------------->:{}",addFriendsNotifyMessage);
		AssertUtils.notNull(addFriendsNotifyMessage);
		AssertUtils.notNullAndEmpty(addFriendsNotifyMessage.getFriendsId(),"朋友圈ID不能为空");
		SendFriendsPicResultNotify sendFriendsPicResultNotify = new SendFriendsPicResultNotify();
		sendFriendsPicResultNotify.setFriendsId(addFriendsNotifyMessage.getFriendsId());
		sendFriendsPicResultNotify.setNoWx(addFriendsNotifyMessage.getNoWx());
		sendFriendsPicResultNotify.setFriendsCode(addFriendsNotifyMessage.getFriendsCode());
		sendFriendsPicResultNotify.setImgUrls(addFriendsNotifyMessage.getImgUrls());
		sendFriendsPicResultNotify.setImgStatus(addFriendsNotifyMessage.getImgStatus());
		
		/**
		 * 只通知给请求的导购
		 */
		if(StringUtils.isNotEmpty(addFriendsNotifyMessage.getMemberNoGm())) {
			IoSession sessionRec = ChannelUtils.getSession(addFriendsNotifyMessage.getMemberNoGm());
			if(sessionRec!=null && sessionRec.isLogin()){
				Message message = new  Message();
				message.setBody(sendFriendsPicResultNotify.toJson());
				message.setCode(MessageCode.SendFriendsPicNotify);
				message.setMsgId(GUID.generateByUUID());
				serverManger.sendMessageNoCache(sessionRec.getChannel(), message);
				LOG.info("sendPicUrlDownLoad notify success :{}",sendFriendsPicResultNotify);
			}else {
				LOG.info("sendPicUrlDownLoad notify fail  , gm is not online :{}",sendFriendsPicResultNotify);
			}
		}
	}

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
	@Override
	public void sendDeleteFriends(DeleteFriendsInfoMessage deleteFriendsInfoMessage) {
		LOG.info("sendDeleteFriends ---------------->:{}", deleteFriendsInfoMessage);
		AssertUtils.notNull(deleteFriendsInfoMessage);
		AssertUtils.notNullAndEmpty(deleteFriendsInfoMessage.getFriendsId(), "朋友圈ID不能为空");
		AssertUtils.notNullAndEmpty(deleteFriendsInfoMessage.getFriendsCode(), "朋友圈code不能为空");
		AssertUtils.notNullAndEmpty(deleteFriendsInfoMessage.getNoWx(), "微信不能为空");
		
		IoSession ioSession = ChannelUtils.getZkSessionByNoWx(deleteFriendsInfoMessage.getNoWx());
		if(ioSession!=null && ioSession.isLogin()){
			DeleteFriends deleteFriends = new DeleteFriends();
			deleteFriends.setCode(deleteFriendsInfoMessage.getFriendsCode());
			deleteFriends.setFriendsId(deleteFriendsInfoMessage.getFriendsId());
			
			Message message = new  Message();
			message.setBody(deleteFriends.toJson());
			message.setCode(MessageCode.DeleteFriends);
			message.setMsgId(GUID.generateByUUID());
			serverManger.sendMessageAndCache(deleteFriendsInfoMessage.getNoWx(), ioSession.getChannel(), message);
		}else {
			throw  new  TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE,"该中控设备不在线");
		}
		
		LOG.info("sendDeleteFriends ----- end");
	}

	@Override
	public void sendFriendsInfoForGm(String memberNoGm, String memberNo,String state,String noWx)throws TsfaServiceException {
		LOG.debug("sendFriendsInfoForGm String memberNoGm={}, String memberNo={},String state:{}",memberNoGm,memberNo,state);
		AssertUtils.notAllNullAndEmpty(memberNoGm, "导购编号不能为空");
		AssertUtils.notAllNullAndEmpty(memberNo, "客户编号不能为空");
		AssertUtils.notAllNullAndEmpty(state, "状态值不能为空");
		
		AddFriendResultResponse addFriendResultResponse =new AddFriendResultResponse();
		addFriendResultResponse.setMemberNo(memberNo);
		addFriendResultResponse.setMemberNoGm(memberNoGm);
		addFriendResultResponse.setNoWx(noWx);
		addFriendResultResponse.setState(state);
		
		IoSession ioSession = ChannelUtils.getSession(memberNoGm);	
		Message message = new  Message(MessageCode.AddFriendResultResponse,GUID.generateByUUID(),addFriendResultResponse.toJson());
		if(ioSession!=null && ioSession.isLogin()){  
			serverManger.sendMessageAndCache(memberNoGm, ioSession.getChannel(), message);
		}else {
			LOG.info("缓存好友信息");
			serverManger.cacheMsg(memberNoGm, message);
		}
	}

}
