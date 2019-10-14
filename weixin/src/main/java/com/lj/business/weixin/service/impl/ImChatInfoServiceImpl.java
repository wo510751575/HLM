package com.lj.business.weixin.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 * <p>
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaContextServiceException;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.ai.service.IProblemService;
import com.lj.business.common.KeyConstant;
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.UpdatePmChatBehavior;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.im.FindPersonMemberByChat;
import com.lj.business.member.dto.im.FindPersonMemberByChatReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmChatBehaviorService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.chat.CancelChatMessage;
import com.lj.business.supcon.dto.chat.ChatMessageRequest;
import com.lj.business.supcon.dto.chat.UploadChatFileMessage;
import com.lj.business.supcon.dto.chat.UploadChatVideoMessage;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;
import com.lj.business.supcon.dto.contacts.SyncWxChatDataMessage;
import com.lj.business.supcon.service.IChatService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.business.weixin.constant.ErrorCode;
import com.lj.business.weixin.dao.IImChatInfoDao;
import com.lj.business.weixin.dao.IImChatInfoSensitiveDao;
import com.lj.business.weixin.dao.IImChatInfoTempDao;
import com.lj.business.weixin.data.AsyncStorer;
import com.lj.business.weixin.domain.ImChatInfo;
import com.lj.business.weixin.domain.ImChatInfoSensitive;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.dto.imchat.AddImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.DelImChatInfo;
import com.lj.business.weixin.dto.imchat.DelImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindAutoAnswerChatInfo;
import com.lj.business.weixin.dto.imchat.FindAutoAnswerChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindChatInfoPageFromWeb;
import com.lj.business.weixin.dto.imchat.FindChatInfoPageFromWebReturn;
import com.lj.business.weixin.dto.imchat.FindChatTimesByGmAndMemberNo;
import com.lj.business.weixin.dto.imchat.FindHistoryChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoPage;
import com.lj.business.weixin.dto.imchat.FindImChatInfoPageReturn;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindMemberChatCount;
import com.lj.business.weixin.dto.imchat.FindMemberChatCountReturn;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfo;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfoDetail;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfoGroup;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMember;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMemberReturn;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByTerminal;
import com.lj.business.weixin.dto.imchat.ReceivedOfflineChatInfo;
import com.lj.business.weixin.dto.imchat.SendChatByNewPerson;
import com.lj.business.weixin.dto.imchat.SendChatToGm;
import com.lj.business.weixin.dto.imchat.SendImChatByNonMember;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfoReceived;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.UpdateThirdHaveReadFromWeb;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.ChatRoomType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.MessageStatus;
import com.lj.business.weixin.emus.ReadFlag;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.emus.SenderSyncStatus;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.utils.SensitiveWordsUtils;
import com.lj.distributecache.IQueue;
import com.lj.distributelock.IDistributeLock;

/**
 * 类说明：实现类
 * <p>
 * <p>
 * <p>
 * 详细描述：.
 *
 * @author 曾垂瑜
 * <p>
 * <p>
 * CreateDate: 2017-06-14
 */
@Service
public class ImChatInfoServiceImpl implements IImChatInfoService {

    /**
     * Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(ImChatInfoServiceImpl.class);

    @Resource
    private IImChatInfoDao imChatInfoDao;

    @Resource
    private IGuidMemberService guidMemberService;
    @Resource
    private IPersonMemberService personMemberService;
    @Resource
    private IPersonMemberImService personMemberImService;
    @Resource
    private IChatRoomMemberService iChatRoomMemberService;

    @Resource
    private IImChatInfoTempDao imChatInfoTempDao;

    @Resource
    private IShopTerminalService shopTerminalService;

    @Resource
    private IImChatInfoService imChatInfoService;

    @Resource
    private IProblemService problemService;
    
	@Resource 
	private IDistributeLock redisLock;
    /** * 系统信息服务. */
//    @Autowired 
//	private ISystemInfoService systemInfo;
    
    @Autowired 
	private IChatRoomMemberService chatRoomMemberService;

    @Resource
    private IPmChatBehaviorService pmChatBehaviorService;

    @Resource
    private IImChatInfoSensitiveDao imChatInfoSensitiveDao;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired 
	ICommonService commonService;
    
    @Resource
    AsyncStorer asyncStorer;
    // add by dengxiudong on 2018-1-3
    @Resource
    private IQueue queue;
    @Autowired
    private IChatRoomService chatRoomService;
    @Autowired
    private IWxChatRoomService wxChatRoomService;
    @Autowired
    private IPersonMemberBaseService personMemberBaseService;

//    private final String REDISAUTOREPLYKEY= "REDIS-AUTOREPLY-KEY-";
    /**
     * 方法说明：消息重发.
     * 如果消息为重发，则检查数据库
     * @param addImChatInfo
     * @return 
     */
    private AddImChatInfoReturn resendImChatInfo(AddImChatInfo addImChatInfo){
        ImChatInfo chatInfo = imChatInfoDao.selectByPrimaryKey(addImChatInfo.getCode());
        if (chatInfo != null) {
            logger.info("消息重复发送", chatInfo);
            // 如果是导购发送的聊天记录,且状态不是发送成功,则重置chatTime
            if (SenderFlag.GM.getCode().equals(chatInfo.getSenderFlag()) && !MessageStatus.SUCCESS.getCode().equals(chatInfo.getStatus())) {
                ImChatInfo update = new ImChatInfo();
                update.setCode(addImChatInfo.getCode());
                update.setStatus(MessageStatus.OFFLINE.getCode());
                update.setChatTime(new Date());
                imChatInfoDao.updateByPrimaryKeySelective(update);

                chatInfo.setChatTime(update.getChatTime());
            }
            AddImChatInfoReturn infoReturn = new AddImChatInfoReturn();
            infoReturn.setMemberNoGm(chatInfo.getMemberNoGm());
            infoReturn.setMemberNo(chatInfo.getMemberNo());
            infoReturn.setCreateDate(chatInfo.getChatTime());
            infoReturn.setStatus(chatInfo.getStatus());
            if (StringUtils.isEmpty(chatInfo.getMemberNo())) {
                infoReturn.setTempBool(Boolean.TRUE);
            }
            logger.info("addImChatInfo(AddImChatInfo) - end - return value={}", infoReturn);
            return infoReturn;
        }else{
        	return null;
        }
    
    }
    /**
     * 方法说明：添加IM聊天记录信息.
     *
     * @param addImChatInfo
     * @return
     */
    private AddImChatInfoReturn addNewImChatInfo(AddImChatInfo addImChatInfo){
        ImChatInfo imChatInfo = new ImChatInfo();

        imChatInfo.setMemberNoGm(addImChatInfo.getMemberNoGm());
        imChatInfo.setMemberNo(addImChatInfo.getMemberNo());
        imChatInfo.setNoWxGm(addImChatInfo.getNoWxGm());
        imChatInfo.setNoWx(addImChatInfo.getNoWx());
        imChatInfo.setAlias(addImChatInfo.getAlias());

        // 单聊
        if(addImChatInfo.getChatroomType() == 1) {
        	// 构建导购信息和客户信息
	        AddImChatInfoReturn chatReturn = this.buildChatInfoByChatPersonal(addImChatInfo, imChatInfo);
	        if(chatReturn != null) {
	        	return chatReturn;
	        }
        } else {	// 群聊
        	this.buildChatInfoByChatRoom(addImChatInfo, imChatInfo);
        }
        
      
	        if (SenderFlag.ZK.getCode().equals(addImChatInfo.getSenderFlag())) { // 客户发送
	            imChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode()); // 中控客户端发送消息,不用通过同步方式发送到导购客户端
	            imChatInfo.setThirdReadFlag(ReadFlag.NO.getCode()); // 第三方未读
	            
	            // 没有客户信息,则根据终端微信查询终端终端并填充终端、商户信息
	            if(StringUtils.isEmpty(imChatInfo.getMemberNo())) {
	            	FindShopTerminalReturn shopTerminalReturn = shopTerminalService.findShopTerminalNormal(imChatInfo.getNoWxGm());
	            	if(shopTerminalReturn != null) {
	                    imChatInfo.setMerchantNo(shopTerminalReturn.getMerchantNo());
	                    imChatInfo.setMerchantName(shopTerminalReturn.getMerchantName());
	            	}
	            }
	        } else {
	            if (addImChatInfo.getMsgSource() < MessageSource.SA.getCode()) {
	                imChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode()); // 从导购客户端或中控客户端发送消息,不用通过同步方式发送到导购客户端
	            } else {
	                imChatInfo.setSenderSyncStatus(SenderSyncStatus.NO.getCode()); // 从第三方（导购助手发送）发送消息,必须同步到导购客户端,让导购能看到聊天记录
	            }
	            if(addImChatInfo.getThirdReadFlag()==null) {
	            	imChatInfo.setThirdReadFlag(ReadFlag.YES.getCode()); // 导购发送，不管是由导购客户端还是第三方（导购助手发送），都默认为已读
	            }else {
	            	imChatInfo.setThirdReadFlag(addImChatInfo.getThirdReadFlag());
	            }
	            
	            // 导购手机客户端发送的文本或分享聊天记录才需要敏感词过滤（中控客户端已由微信做了过滤，在此就不重复过滤了）
	    		if((ChatInfoType.TEXT.getCode().equals(addImChatInfo.getType()) && SensitiveWordsUtils.contains(addImChatInfo.getContent())) || (ChatInfoType.SHARE.getCode().equals(addImChatInfo.getType()) && (SensitiveWordsUtils.contains(addImChatInfo.getShareTitle()) || SensitiveWordsUtils.contains(addImChatInfo.getShareDes())))) {
	    			//未认领的取当时发消息的导购,表设计导购号必填
	    			if(StringUtils.isEmpty(imChatInfo.getMemberNoGm())) {
	    				imChatInfo.setMemberNoGm(StringUtils.isEmpty(addImChatInfo.getMemberNoGm())?"0":addImChatInfo.getMemberNoGm());
	    			}
    				this.createChatInfoSensitive(imChatInfo);
	                throw new TsfaServiceException(ErrorCode.INCLUDE_SENSITIVE_WORDS, "聊天记录包含敏感词");
	             }
	        }
	        
	        if(StringUtils.isEmpty(addImChatInfo.getStatus())) {
	        	imChatInfo.setStatus(MessageStatus.OFFLINE.getCode());//未发送(离线)
	        }else {
	        	imChatInfo.setStatus(addImChatInfo.getStatus());
	        }
        
        

        
        imChatInfo.setCode(addImChatInfo.getCode());
        imChatInfo.setType(addImChatInfo.getType());
        
        imChatInfo.setChatTime(addImChatInfo.getChatTime() != null ? addImChatInfo.getChatTime() : new Date());
        imChatInfo.setResourcesPath(addImChatInfo.getResourcesPath());
        imChatInfo.setShareTitle(addImChatInfo.getShareTitle());
        imChatInfo.setShareDes(addImChatInfo.getShareDes());
        imChatInfo.setShareUrl(addImChatInfo.getShareUrl());
        imChatInfo.setChatroomType(addImChatInfo.getChatroomType());
        imChatInfo.setChatroomNoWx(addImChatInfo.getChatroomNoWx());
        imChatInfo.setMsgSource(addImChatInfo.getMsgSource());
        imChatInfo.setImei(addImChatInfo.getImei());
        imChatInfo.setCreateDate(new Date());
        imChatInfo.setSenderFlag(addImChatInfo.getSenderFlag());
        imChatInfo.setContent(addImChatInfo.getContent());
        
   
        AddImChatInfoReturn addImChatInfoReturn = new AddImChatInfoReturn();
        addImChatInfoReturn.setMemberNoGm(imChatInfo.getMemberNoGm());
        addImChatInfoReturn.setMemberNo(imChatInfo.getMemberNo());
        addImChatInfoReturn.setCreateDate(imChatInfo.getChatTime());
        
		  //如果为插入红包或者转账信息，因为有中控自动收取红包和转账功能，需要特殊处理
		if(addImChatInfo.getType().equals(ChatInfoType.S_REDPACKET.getCode())
				|| addImChatInfo.getType().equals(ChatInfoType.TRANSFER.getCode())) {
			imChatInfo.setStatus(addImChatInfo.getStatus());
			imChatInfo.setSenderSyncStatus(addImChatInfo.getSenderSyncStatus());
		}
//        if (!asyncStorer.saveImChatInfo(imChatInfo)) {
//            logger.warn("写入redis失败，将im_chat_info数据直接写入MySQL。");
            imChatInfoDao.insertSelective(imChatInfo);
//        }
        
        

        // 更新客户最新聊天动态
        if(StringUtils.isNotEmpty(imChatInfo.getMemberNo()) && StringUtils.isNotEmpty(imChatInfo.getMemberNoGm())) {
            Integer unreadFlag = ReadFlag.NO.getCode().equals(imChatInfo.getThirdReadFlag()) ? ReadFlag.YES.getCode() : ReadFlag.NO.getCode();
            UpdatePmChatBehavior chatBehavior = null;
        	chatBehavior = new UpdatePmChatBehavior(imChatInfo.getMemberNo(),imChatInfo.getMemberNoGm(), imChatInfo.getNoWxGm(), new Date(), unreadFlag);
        	
            if(SenderFlag.ZK.getCode().equals(imChatInfo.getSenderFlag())) {	// 客户发的，需回复
            	chatBehavior.setMemberChatTime(chatBehavior.getChatTime());
            	chatBehavior.setUnrespFlag(ReadFlag.YES.getCode());
            } else {
            	chatBehavior.setUnrespFlag(ReadFlag.NO.getCode());
            }
            // if (!asyncStorer.updatePmChatBehavior(chatBehavior)) {
            //     logger.warn("写入redis失败，将pm_chat_behavior数据直接写入MySQL。");
            //     pmChatBehaviorService.updateBehavior(chatBehavior);
            // }
            pmChatBehaviorService.updateBehavior(chatBehavior);
        }
        
        logger.info("addImChatInfo(AddImChatInfo) - end - return value={}", addImChatInfoReturn);
        return addImChatInfoReturn;
    }
    
   
    
    /**
     * 
     *
     * 方法说明：群聊时构建相关信息
     *
     * @param addImChatInfo
     * @param imChatInfo
     *
     * @author 曾垂瑜 CreateDate: 2018年9月26日
     *
     */
	private void buildChatInfoByChatRoom(AddImChatInfo addImChatInfo, ImChatInfo imChatInfo) {
		FindChatRoom findChatRoom = new FindChatRoom();
		// 获取群信息
		findChatRoom.setNoWxZk(addImChatInfo.getNoWxGm());
		findChatRoom.setChatRoomName(addImChatInfo.getNoWx());
		FindChatRoomReturn findChatRoomReturn = chatRoomService.findChatRoomAndInitWhileNon(findChatRoom);
		imChatInfo.setMemberNo(findChatRoomReturn.getCode());	// 将群记录code写入到会员编号memberNo字段
		imChatInfo.setMemberName(findChatRoomReturn.getRoomNickName());
		imChatInfo.setMemberNoGm(findChatRoomReturn.getMemberNoGm());
		imChatInfo.setMemberNameGm(findChatRoomReturn.getMemberNameGm());
        imChatInfo.setMerchantNo(findChatRoomReturn.getMerchantNo());
        imChatInfo.setMerchantName(findChatRoomReturn.getMerchantName());
        imChatInfo.setNoWx(findChatRoomReturn.getChatRoomName());
        imChatInfo.setAlias(findChatRoomReturn.getChatRoomName());
	}
    
    /**
     * 
     *
     * 方法说明：单聊时构建导购和客户信息
     *
     * @param addImChatInfo
     * @param imChatInfo
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年9月8日
     *
     */
	private AddImChatInfoReturn buildChatInfoByChatPersonal(AddImChatInfo addImChatInfo, ImChatInfo imChatInfo) {
		 // 查询客户信息
        FindPersonMemberByChatReturn findPersonMemberByChatReturn = null;
        boolean isCache = true;
		String hashKey = null;
		if (SenderFlag.GM.getCode().equals(addImChatInfo.getSenderFlag())) {    // 导购客户端发送给客户，则按客户编号和导购编号查询客户信息
            /*try {
                hashKey = "gm:mem:" + addImChatInfo.getMemberNoGm() + ":" + addImChatInfo.getMemberNo();
                GmMemberRelateInfoDto gmRelateInfo = personMemberImService.getGmMemberRelateCacheByNo(hashKey);
                if (gmRelateInfo == null) {
                    logger.error("Redis中没有缓存客户导购信息：" + hashKey);
                    isCache = false;
                } else {
                    imChatInfo.setMemberNameGm(gmRelateInfo.getMemberNameGm());
                    imChatInfo.setMemberName(gmRelateInfo.getMemberName());
                    imChatInfo.setNoWxGm(gmRelateInfo.getGmWx());
                    imChatInfo.setNoWx(gmRelateInfo.getNoWx());
                    imChatInfo.setAlias(gmRelateInfo.getNoWxAlias());
//                    imChatInfo.setShopNo(gmRelateInfo.getShopNo());
//                    imChatInfo.setShopName(gmRelateInfo.getShopName());
                    imChatInfo.setMerchantNo(gmRelateInfo.getMerchantNo());
                    imChatInfo.setMerchantName(gmRelateInfo.getMerchantName());
                }
            } catch (Exception e) {
                logger.error("从Redis中获取数据错误,将直接从MySQL中获取。" + e.toString());
                isCache = false;
            }*/
//            if (isCache == false) {
                FindPersonMemberByChat findPersonMemberByChat = new FindPersonMemberByChat();
                findPersonMemberByChat.setMemberNo(addImChatInfo.getMemberNo());
                findPersonMemberByChat.setNoWxGm(imChatInfo.getNoWxGm());
                findPersonMemberByChatReturn = personMemberImService.findPersonMemberByChat(findPersonMemberByChat);
                if (findPersonMemberByChatReturn == null) {
                    throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_NOT_EXIST_ERROR, "客户会员信息不存在");
                }
//            }
        } else {    // 中控客户端发送给导购，则按客户微信号和导购微信号查询客户信息
            /*try {
                hashKey = "gmwx:memwx:" + addImChatInfo.getNoWxGm() + ":" + addImChatInfo.getNoWx();
                GmMemberRelateInfoDto gmRelateInfo = personMemberImService.getGmMemberRelateCacheByWx(hashKey);
                if (gmRelateInfo == null) {
                    logger.error("Redis中没有缓存客户导购信息：" + hashKey);
                    isCache = false;
                } else { // 中控客户端发送给导购，则按客户微信号和导购微信号查询客户信息
                    imChatInfo.setMemberNoGm(gmRelateInfo.getMemberNoGm());
                    imChatInfo.setMemberNo(gmRelateInfo.getMemberNo());
                    imChatInfo.setMemberNameGm(gmRelateInfo.getMemberNameGm());
                    imChatInfo.setMemberName(gmRelateInfo.getMemberName());
                    imChatInfo.setNoWxGm(addImChatInfo.getNoWxGm());
                    imChatInfo.setNoWx(addImChatInfo.getNoWx());
                    imChatInfo.setAlias(gmRelateInfo.getNoWxAlias());
//                    imChatInfo.setShopNo(gmRelateInfo.getShopNo());
//                    imChatInfo.setShopName(gmRelateInfo.getShopName());
                    imChatInfo.setMerchantNo(gmRelateInfo.getMerchantNo());
                    imChatInfo.setMerchantName(gmRelateInfo.getMerchantName());
                }
            } catch (Exception e) {
                logger.error("从Redis中获取数据错误,将直接从MySQL中获取。" + e.toString());
                isCache = false;
            }*/
         //   if (isCache == false) {
                FindPersonMemberByChat findPersonMemberByChat = new FindPersonMemberByChat();
                findPersonMemberByChat.setNoWxGm(addImChatInfo.getNoWxGm());
                findPersonMemberByChat.setMemberNo(addImChatInfo.getMemberNo());
                findPersonMemberByChat.setNoWx(addImChatInfo.getNoWx());
                findPersonMemberByChatReturn = personMemberImService.findPersonMemberByChat(findPersonMemberByChat);

                /**
                 * 如果发生客户不存在的情况，有可能是因为客户聊天记录先到达，新增好友后达引起，比如客户扫码且导购未认领前发出的聊天记录
                 * 解决方案：
                 * 待触发客户认领{@link AddFriendsServiceImpl#distributionMember()}后将聊天记录发送给导购
                 */
                if (findPersonMemberByChatReturn == null) {
                    logger.info("客户发给导购，客户不存在：{}", addImChatInfo.getCode());
                    AddImChatInfoReturn infoReturn = new AddImChatInfoReturn();
                    this.createChatInfoByNonMemberFromZk(addImChatInfo);
                    infoReturn.setTempBool(Boolean.TRUE);
                    logger.info("addImChatInfo(AddImChatInfo) - end - return value={}", infoReturn);
                    return infoReturn;
                }
         //   }
        }
        
//        if (isCache == false) {
            imChatInfo.setMemberNoGm(findPersonMemberByChatReturn.getMemberNoGm());
            imChatInfo.setMemberNameGm(findPersonMemberByChatReturn.getMemberNameGm());
            imChatInfo.setNoWxGm(addImChatInfo.getNoWxGm());
            imChatInfo.setMemberNo(findPersonMemberByChatReturn.getMemberNo());
            imChatInfo.setMemberName(findPersonMemberByChatReturn.getMemberName());
            imChatInfo.setNoWx(findPersonMemberByChatReturn.getNoWx());
            imChatInfo.setAlias(findPersonMemberByChatReturn.getNoWxAlias());
//            imChatInfo.setShopNo(findPersonMemberByChatReturn.getShopNo());
//            imChatInfo.setShopName(findPersonMemberByChatReturn.getShopName());
            imChatInfo.setMerchantNo(findPersonMemberByChatReturn.getMerchantNo());
            imChatInfo.setMerchantName(findPersonMemberByChatReturn.getMerchantName());
//        }
        
        return null;
	}
    
    /**
     * 方法说明：添加IM聊天记录信息
     *
     * @param addImChatInfo
     * @return
     * @throws TsfaServiceException
     * @author 曾垂瑜 CreateDate: 2017-06-14
     */
    @Override
    public AddImChatInfoReturn addImChatInfo(AddImChatInfo addImChatInfo) throws TsfaServiceException {
        logger.info("addImChatInfo(AddImChatInfo addImChatInfo={}) - start", addImChatInfo);

        AssertUtils.notNull(addImChatInfo);
        try {
            // 如果消息为重发，则检查数据库
            if (addImChatInfo.isResend()) { 
				AddImChatInfoReturn rt = resendImChatInfo(addImChatInfo);
				if (null != rt) {
					return rt;
				}
            }
            // 消息为新消息处理
            return addNewImChatInfo(addImChatInfo);
           
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("新增IM聊天记录信息错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_ADD_ERROR, "新增IM聊天记录信息错误！", e);
        }
    }

	/*@Override
    public AddImChatInfoReturn addImChatInfo(AddImChatInfo addImChatInfo) throws TsfaServiceException {
		logger.info("addImChatInfo(AddImChatInfo addImChatInfo={}) - start", addImChatInfo);

		AssertUtils.notNull(addImChatInfo);
		try {
			// 如果消息为重发，则检查数据库
			if (addImChatInfo.isResend()) {
				ImChatInfo chatInfo = imChatInfoDao.selectByPrimaryKey(addImChatInfo.getCode());
				if (chatInfo != null) {
					logger.info("消息重复发送", chatInfo);
					// 如果是导购发送的聊天记录,且状态不是发送成功,则重置chatTime
					if (chatInfo.getSenderFlag() == SenderFlag.GM.getCode() && !MessageStatus.SUCCESS.getCode().equals(chatInfo.getStatus())) {
						ImChatInfo update = new ImChatInfo();
						update.setCode(addImChatInfo.getCode());
						update.setStatus(MessageStatus.OFFLINE.getCode());
						update.setChatTime(new Date());
						imChatInfoDao.updateByPrimaryKeySelective(update);

						chatInfo.setChatTime(update.getChatTime());
					}
					AddImChatInfoReturn infoReturn = new AddImChatInfoReturn();
					infoReturn.setMemberNoGm(chatInfo.getMemberNoGm());
					infoReturn.setMemberNo(chatInfo.getMemberNo());
					infoReturn.setCreateDate(chatInfo.getChatTime());
					infoReturn.setStatus(chatInfo.getStatus());
					if(StringUtils.isEmpty(chatInfo.getMemberNo())) {
						infoReturn.setTempBool(Boolean.TRUE);
					}
					logger.info("addImChatInfo(AddImChatInfo) - end - return value={}", infoReturn);
					return infoReturn;
				}
			}

			// 查询客户信息
			FindPersonMemberByChatReturn findPersonMemberByChatReturn = null;
			String hashKey = null;
			// 是否从缓存中获取数据
			boolean isCache = true;
//			Map<String, String> cacheData = null;
			ImChatInfo imChatInfo = new ImChatInfo();

			// modif by dengxiudong
			// 敏感词过滤
			// 查询客户信息
			if (SenderFlag.GM.getCode().intValue() == addImChatInfo.getSenderFlag().intValue()) { // 导购客户端发送给客户，则按客户编号和导购编号查询客户信息

				// 导购手机客户端发送的聊天记录才需要敏感词过滤（中控客户端已由微信做了过滤，在此就不重复过滤了）
				if ((ChatInfoType.TEXT.getCode().equals(addImChatInfo.getType()) && SensitiveWordsUtils.contains(addImChatInfo.getContent())) || (StringUtils.isNotEmpty(addImChatInfo.getShareTitle()) && SensitiveWordsUtils.contains(addImChatInfo.getShareTitle())) || (StringUtils.isNotEmpty(addImChatInfo.getShareDes()) && SensitiveWordsUtils.contains(addImChatInfo.getShareDes()))) {
					this.createChatInfoSensitive(addImChatInfo, findPersonMemberByChatReturn);
					throw new TsfaServiceException(ErrorCode.INCLUDE_SENSITIVE_WORDS, "聊天记录包含敏感词");
				}
				hashKey = addImChatInfo.getMemberNo() + addImChatInfo.getMemberNoGm();
				// 如果缓存中有数据则直接从缓存中获取，没有的话则从数据库中查询
				try {
					GmMemberRelateInfoDto gmRelateInfo = personMemberImService.getGmMemberRelateCacheByNo(hashKey);
					if (gmRelateInfo == null) {
						logger.error("Redis中没有缓存客户导购信息：" + hashKey);
						isCache = false;
					} else {
						imChatInfo.setCode(addImChatInfo.getCode());
						imChatInfo.setMemberNoGm(addImChatInfo.getMemberNoGm());
						imChatInfo.setMemberNo(addImChatInfo.getMemberNo());
						imChatInfo.setMemberNameGm(gmRelateInfo.getMemberNameGm());
						imChatInfo.setMemberName(gmRelateInfo.getMemberName());
						imChatInfo.setNoWxGm(gmRelateInfo.getGmWx());
						imChatInfo.setNoWx(gmRelateInfo.getNoWx());
						imChatInfo.setAlias(gmRelateInfo.getNoWxAlias());
						imChatInfo.setShopNo(gmRelateInfo.getShopNo());
						imChatInfo.setShopName(gmRelateInfo.getShopName());
						imChatInfo.setMerchantNo(gmRelateInfo.getMerchantNo());
						imChatInfo.setMerchantName(gmRelateInfo.getMerchantName());
					}
				} catch (Exception e) {
					logger.error("从Redis中获取数据错误,将直接从MySQL中获取。" + e.toString());
					isCache = false;
				}
				if (isCache == false) {
					FindPersonMemberByChat findPersonMemberByChat = new FindPersonMemberByChat();
					findPersonMemberByChat.setMemberNo(addImChatInfo.getMemberNo());
					findPersonMemberByChat.setMemberNoGm(addImChatInfo.getMemberNoGm());
					findPersonMemberByChatReturn = personMemberImService.findPersonMemberByChat(findPersonMemberByChat);
					if (findPersonMemberByChatReturn == null) {
						throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_NOT_EXIST_ERROR, "客户会员信息不存在");
					}
				}
			} else {// modif by dengxiudong // 中控客户端发送给导购，则按客户微信号和导购微信号查询客户信息
				
				try {
					GmMemberRelateInfoDto gmRelateInfo = personMemberImService.getGmMemberRelateCacheByWx(hashKey);
					if (gmRelateInfo == null) {
						logger.error("Redis中没有缓存客户导购信息：" + hashKey);
						isCache = false;
					} else { // 中控客户端发送给导购，则按客户微信号和导购微信号查询客户信息
						imChatInfo.setCode(addImChatInfo.getCode());
						imChatInfo.setMemberNoGm(gmRelateInfo.getMemberNoGm());
						imChatInfo.setMemberNo(gmRelateInfo.getMemberNo());
						imChatInfo.setMemberNameGm(gmRelateInfo.getMemberNameGm());
						imChatInfo.setMemberName(gmRelateInfo.getMemberName());
						imChatInfo.setNoWxGm(addImChatInfo.getNoWxGm());
						imChatInfo.setNoWx(addImChatInfo.getNoWx());
						imChatInfo.setAlias(gmRelateInfo.getNoWxAlias());
						imChatInfo.setShopNo(gmRelateInfo.getShopNo());
						imChatInfo.setShopName(gmRelateInfo.getShopName());
						imChatInfo.setMerchantNo(gmRelateInfo.getMerchantNo());
						imChatInfo.setMerchantName(gmRelateInfo.getMerchantName());
					}
				} catch (Exception e) {
					logger.error("从Redis中获取数据错误,将直接从MySQL中获取。" + e.toString());
					isCache = false;
				}
				if (isCache == false) {
					FindPersonMemberByChat findPersonMemberByChat = new FindPersonMemberByChat();
					findPersonMemberByChat.setNoWxGm(addImChatInfo.getNoWxGm());
					findPersonMemberByChat.setNoWx(addImChatInfo.getNoWx());
					findPersonMemberByChatReturn = personMemberImService.findPersonMemberByChat(findPersonMemberByChat);

					*//**
     * 如果发生客户不存在的情况，有可能是因为客户聊天记录先到达，新增好友后达引起，
     * 比如客户扫码且导购未认领前发出的聊天记录 解决方案： 待触发客户认领
     * {@link AddFriendsServiceImpl#distributionMember()}
     * 后将聊天记录发送给导购
     *//*
                    if (findPersonMemberByChatReturn == null) {
						logger.info("客户发给导购，客户不存在：{}", addImChatInfo.getCode());
						this.createChatInfoByNonMemberFromZk(addImChatInfo);
						AddImChatInfoReturn infoReturn = new AddImChatInfoReturn();
						infoReturn.setTempBool(Boolean.TRUE);
						logger.info("addImChatInfo(AddImChatInfo) - end - return value={}", infoReturn);
						return infoReturn;
					}
				}
			}

			// 缓存无数据之情形
			if (isCache == false) {
				imChatInfo.setCode(addImChatInfo.getCode());
				imChatInfo.setMemberNoGm(findPersonMemberByChatReturn.getMemberNoGm());
				imChatInfo.setMemberNameGm(findPersonMemberByChatReturn.getMemberNameGm());
				imChatInfo.setNoWxGm(addImChatInfo.getNoWxGm());
				imChatInfo.setMemberNo(findPersonMemberByChatReturn.getMemberNo());
				imChatInfo.setMemberName(findPersonMemberByChatReturn.getMemberName());
				imChatInfo.setNoWx(findPersonMemberByChatReturn.getNoWx());
				imChatInfo.setAlias(findPersonMemberByChatReturn.getNoWxAlias());
				imChatInfo.setShopNo(findPersonMemberByChatReturn.getShopNo());
				imChatInfo.setShopName(findPersonMemberByChatReturn.getShopName());
				imChatInfo.setMerchantNo(findPersonMemberByChatReturn.getMerchantNo());
				imChatInfo.setMerchantName(findPersonMemberByChatReturn.getMerchantName());
			}
			imChatInfo.setSenderFlag(addImChatInfo.getSenderFlag());
			if (addImChatInfo.getSenderFlag() == SenderFlag.ZK.getCode()) { // 客户发送
				imChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode()); // 中控客户端发送消息,不用通过同步方式发送到导购客户端
				imChatInfo.setThirdReadFlag(ReadFlag.NO.getCode()); // 第三方未读
			} else {
				if (addImChatInfo.getMsgSource() < MessageSource.SA.getCode()) {
					imChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode()); // 从导购客户端或中控客户端发送消息,不用通过同步方式发送到导购客户端
				} else {
					imChatInfo.setSenderSyncStatus(SenderSyncStatus.NO.getCode()); // 从第三方（导购助手发送）发送消息,必须同步到导购客户端,让导购能看到聊天记录
				}
				imChatInfo.setThirdReadFlag(ReadFlag.YES.getCode()); // 导购发送，不管是由导购客户端还是第三方（导购助手发送），都默认为已读
			}
			imChatInfo.setType(addImChatInfo.getType());
			imChatInfo.setStatus(MessageStatus.OFFLINE.getCode());// 未发送(离线)
			imChatInfo.setChatTime(addImChatInfo.getChatTime() != null ? addImChatInfo.getChatTime() : new Date());
			imChatInfo.setResourcesPath(addImChatInfo.getResourcesPath());
			imChatInfo.setShareTitle(addImChatInfo.getShareTitle());
			imChatInfo.setShareDes(addImChatInfo.getShareDes());
			imChatInfo.setShareUrl(addImChatInfo.getShareUrl());
			imChatInfo.setMsgSource(addImChatInfo.getMsgSource());
			imChatInfo.setImei(addImChatInfo.getImei());
			imChatInfo.setCreateDate(new Date());
			imChatInfo.setContent(addImChatInfo.getContent());
			// test modif by dengxiudong
			// 将聊天记录缓存到Redis中，然后异步写入MySQL
			String jsonChatInfo = JSON.toJSONString(imChatInfo);
			// String jsonChatInfo = JsonUtils.jsonFromObject(imChatInfo);
			try {
				queue.lpush("im_chat_info", jsonChatInfo);
			} catch (Exception e) {
				logger.error("写入redis失败，将im_chat_info数据直接写入MySQL。");
				imChatInfoDao.insertSelective(imChatInfo);
			}

			AddImChatInfoReturn addImChatInfoReturn = new AddImChatInfoReturn();
			addImChatInfoReturn.setMemberNoGm(imChatInfo.getMemberNoGm());
			addImChatInfoReturn.setMemberNo(imChatInfo.getMemberNo());
			addImChatInfoReturn.setCreateDate(imChatInfo.getChatTime());

			// 更新客户最新聊天动态
			Integer unreadFlag =  ReadFlag.NO.getCode().equals(imChatInfo.getThirdReadFlag()) ? ReadFlag.YES.getCode() : null;
			// pmChatBehaviorService.updateBehavior(new
			// UpdatePmChatBehavior(imChatInfo.getMemberNo(),
			// imChatInfo.getMemberNoGm(), new Date(), unreadFlag));
			UpdatePmChatBehavior chatBehavior = new UpdatePmChatBehavior(imChatInfo.getMemberNo(), imChatInfo.getMemberNoGm(), new Date(), unreadFlag);
			String jsonChatBehavior = JSON.toJSONString(chatBehavior);
			try {
				queue.lpush("pm_chat_behavior", jsonChatBehavior);
			} catch (Exception e) {
				logger.error("写入redis失败，将pm_chat_behavior数据直接写入MySQL。");
				pmChatBehaviorService.updateBehavior(chatBehavior);
			}
			logger.info("addImChatInfo(AddImChatInfo) - end - return value={}", addImChatInfoReturn);
			return addImChatInfoReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增IM聊天记录信息错误！", e);
			throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_ADD_ERROR, "新增IM聊天记录信息错误！", e);
		}
	}*/

    /**
     * 方法说明：保存包含敏感词的聊天记录到敏感词聊天记录表
     *
     * @param addImChatInfo
     * @param findPersonMemberByChatReturn
     * @author 曾垂瑜 CreateDate: 2018年1月8日
     */

    private void createChatInfoSensitive(final ImChatInfo imChatInfo) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // 获取消息内包含的敏感词列表
                Set<String> sensitiveWordList = new HashSet<>();
                if (ChatInfoType.TEXT.getCode().equals(imChatInfo.getType())) { // 聊天文本内容
                    sensitiveWordList.addAll(SensitiveWordsUtils.getSensitiveWord(imChatInfo.getContent()));
                } else if (ChatInfoType.SHARE.getCode().equals(imChatInfo.getType())) {    // 分享
                    if (StringUtils.isNotEmpty(imChatInfo.getShareTitle())) { // 分享标题
                        sensitiveWordList.addAll(SensitiveWordsUtils.getSensitiveWord(imChatInfo.getShareTitle()));
                    }
                    if (StringUtils.isNotEmpty(imChatInfo.getShareDes())) { // 分享描述
                        sensitiveWordList.addAll(SensitiveWordsUtils.getSensitiveWord(imChatInfo.getShareDes()));
                    }
                }

                ImChatInfoSensitive chat = new ImChatInfoSensitive();
                chat.setCode(imChatInfo.getCode());
                chat.setMemberNoGm(imChatInfo.getMemberNoGm());
                chat.setMemberNameGm(imChatInfo.getMemberNameGm());
                chat.setNoWxGm(imChatInfo.getNoWxGm());
                chat.setMemberNo(imChatInfo.getMemberNo());
                chat.setMemberName(imChatInfo.getMemberName());
                chat.setNoWx(imChatInfo.getNoWx());
                chat.setAlias(imChatInfo.getAlias());
//                chat.setShopNo(imChatInfo.getShopNo());
//                chat.setShopName(imChatInfo.getShopName());
                chat.setMerchantNo(imChatInfo.getMerchantNo());
                chat.setMerchantName(imChatInfo.getMerchantName());
                chat.setType(imChatInfo.getType());
                chat.setChatTime(imChatInfo.getChatTime() != null ? imChatInfo.getChatTime() : new Date());
                chat.setResourcesPath(imChatInfo.getResourcesPath());
                chat.setShareTitle(imChatInfo.getShareTitle());
                chat.setShareDes(imChatInfo.getShareDes());
                chat.setShareUrl(imChatInfo.getShareUrl());
                chat.setChatroomType(imChatInfo.getChatroomType());
                chat.setChatroomNoWx(imChatInfo.getChatroomNoWx());
                chat.setMsgSource(imChatInfo.getMsgSource());
                chat.setCreateDate(new Date());
                chat.setContent(imChatInfo.getContent());
                String words = "";
                for (String word : sensitiveWordList) {
                    words = "," + word;
                }
                chat.setSensitiveWords(words.substring(1));
                try {
                    imChatInfoSensitiveDao.insertSelective(chat);
                } catch (Exception e) {
                    logger.error("保存包含敏感词的聊天记录到敏感词聊天记录表失败", e);
                }
            }
        });
    }

    /**
     * 方法说明：将聊天记录保存为未认领客户聊天数据，即聊天记录没有客户信息（memberNo为空）
     *
     * @param addImChatInfo
     * @author 曾垂瑜 CreateDate: 2017年12月22日
     */
    private void createChatInfoByNonMemberFromZk(AddImChatInfo addImChatInfo) {
        FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalByWx(addImChatInfo.getNoWxGm());
        if (shopTerminal == null) {
            throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "终端终端不存在");
        }
        if (!addImChatInfo.getNoWxGm().equals(shopTerminal.getNoWx())) {
            throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_SAME_WX, "终端绑定微信与请求微信不一致");
        }
        ImChatInfo imChatInfo = new ImChatInfo();
        imChatInfo.setCode(addImChatInfo.getCode());
        imChatInfo.setNoWxGm(shopTerminal.getNoWx());
        imChatInfo.setNoWx(addImChatInfo.getNoWx());
        imChatInfo.setAlias(addImChatInfo.getAlias());
//        imChatInfo.setShopNo(shopTerminal.getShopNo());
//        imChatInfo.setShopName(shopTerminal.getShopName());
        imChatInfo.setMerchantNo(shopTerminal.getMerchantNo());
        imChatInfo.setMerchantName(shopTerminal.getMerchantName());
        imChatInfo.setSenderFlag(addImChatInfo.getSenderFlag());
        imChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode()); // 中控客户端发送消息,不用通过同步方式发送到导购客户端
        imChatInfo.setThirdReadFlag(ReadFlag.NO.getCode()); // 第三方未读
        imChatInfo.setType(addImChatInfo.getType());
        imChatInfo.setStatus(MessageStatus.OFFLINE.getCode());// 未发送(离线)
        imChatInfo.setChatTime(addImChatInfo.getChatTime() != null ? addImChatInfo.getChatTime() : new Date());
        imChatInfo.setResourcesPath(addImChatInfo.getResourcesPath());
        imChatInfo.setShareTitle(addImChatInfo.getShareTitle());
        imChatInfo.setShareDes(addImChatInfo.getShareDes());
        imChatInfo.setShareUrl(addImChatInfo.getShareUrl());
        imChatInfo.setChatroomType(addImChatInfo.getChatroomType());
        imChatInfo.setChatroomNoWx(addImChatInfo.getChatroomNoWx());
        imChatInfo.setMsgSource(addImChatInfo.getMsgSource());
        imChatInfo.setImei(addImChatInfo.getImei());
        imChatInfo.setCreateDate(new Date());
        imChatInfo.setContent(addImChatInfo.getContent());
        imChatInfo.setRemark("未认领客户聊天记录");
        imChatInfoDao.insert(imChatInfo);
        logger.info("已保存未认领客户的聊天记录{}", addImChatInfo.getCode());
    }

    /**
     * 方法说明：删除IM聊天记录信息
     *
     * @param delImChatInfo
     * @return
     * @throws TsfaServiceException
     * @author 曾垂瑜 CreateDate: 2017-06-14
     */
    @Override
    public DelImChatInfoReturn delImChatInfo(DelImChatInfo delImChatInfo) throws TsfaServiceException {
        logger.debug("delImChatInfo(DelImChatInfo delImChatInfo={}) - start", delImChatInfo);

        AssertUtils.notNull(delImChatInfo);
        AssertUtils.notNull(delImChatInfo.getCode(), "CODE不能为空！");
        try {
            imChatInfoDao.deleteByPrimaryKey(delImChatInfo.getCode());
            DelImChatInfoReturn delImChatInfoReturn = new DelImChatInfoReturn();

            logger.debug("delImChatInfo(DelImChatInfo) - end - return value={}", delImChatInfoReturn); 
            return delImChatInfoReturn;
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("删除IM聊天记录信息错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_DEL_ERROR, "删除IM聊天记录信息错误！", e);

        }
    }

    /**
     * 方法说明：修改IM聊天记录信息
     *
     * @param updateImChatInfo
     * @return
     * @throws TsfaServiceException
     * @author 曾垂瑜 CreateDate: 2017-06-14
     */
    @Override
    public UpdateImChatInfoReturn updateImChatInfo(UpdateImChatInfo updateImChatInfo) throws TsfaServiceException {
        logger.debug("updateImChatInfo(UpdateImChatInfo updateImChatInfo={}) - start", updateImChatInfo); 

        AssertUtils.notNull(updateImChatInfo);
        AssertUtils.notNullAndEmpty(updateImChatInfo.getCode(), "CODE不能为空");
        try {
            ImChatInfo imChatInfo = new ImChatInfo();
            // update数据录入
            imChatInfo.setCode(updateImChatInfo.getCode());
            imChatInfo.setMemberNoGm(updateImChatInfo.getMemberNoGm());
            imChatInfo.setMemberNameGm(updateImChatInfo.getMemberNameGm());
            imChatInfo.setNoWxGm(updateImChatInfo.getNoWxGm());
            imChatInfo.setMemberNo(updateImChatInfo.getMemberNo());
            imChatInfo.setMemberName(updateImChatInfo.getMemberName());
            imChatInfo.setNoWx(updateImChatInfo.getNoWx());
            imChatInfo.setAlias(updateImChatInfo.getAlias());
//            imChatInfo.setShopNo(updateImChatInfo.getShopNo());
//            imChatInfo.setShopName(updateImChatInfo.getShopName());
            imChatInfo.setMerchantNo(updateImChatInfo.getMerchantNo());
            imChatInfo.setMerchantName(updateImChatInfo.getMerchantName());
            imChatInfo.setSenderFlag(updateImChatInfo.getSenderFlag());
            imChatInfo.setType(updateImChatInfo.getType());
            imChatInfo.setStatus(updateImChatInfo.getStatus());
            imChatInfo.setChatTime(updateImChatInfo.getChatTime());
            imChatInfo.setReceivedTime(updateImChatInfo.getReceivedTime());
            imChatInfo.setResourcesPath(updateImChatInfo.getResourcesPath());
            imChatInfo.setShareTitle(updateImChatInfo.getShareTitle());
            imChatInfo.setShareDes(updateImChatInfo.getShareDes());
            imChatInfo.setShareUrl(updateImChatInfo.getShareUrl());
            imChatInfo.setChatroomType(updateImChatInfo.getChatroomType());
            imChatInfo.setChatroomNoWx(updateImChatInfo.getChatroomNoWx());
            imChatInfo.setImei(updateImChatInfo.getImei());
            imChatInfo.setErrorCode(updateImChatInfo.getErrorCode());
            imChatInfo.setErrorMessage(updateImChatInfo.getErrorMessage());
            imChatInfo.setCreateDate(updateImChatInfo.getCreateDate());
            imChatInfo.setRemark(updateImChatInfo.getRemark());
            imChatInfo.setRemark2(updateImChatInfo.getRemark2());
            imChatInfo.setRemark3(updateImChatInfo.getRemark3());
            imChatInfo.setRemark4(updateImChatInfo.getRemark4());
            imChatInfo.setContent(updateImChatInfo.getContent());
            imChatInfo.setBigImg(updateImChatInfo.getBigImg());
            imChatInfo.setMidImg(updateImChatInfo.getMidImg());
            AssertUtils.notUpdateMoreThanOne(imChatInfoDao.updateByPrimaryKeySelective(imChatInfo));
            UpdateImChatInfoReturn updateImChatInfoReturn = new UpdateImChatInfoReturn();

            logger.debug("updateImChatInfo(UpdateImChatInfo) - end - return value={}", updateImChatInfoReturn); 
            return updateImChatInfoReturn;
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("IM聊天记录信息更新信息错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_UPDATE_ERROR, "IM聊天记录信息更新信息错误！", e);
        }
    }

    /**
     * 方法说明：根据msgId更新IM聊天记录信息状态
     *
     * @param updateImChatInfo
     * @return
     * @throws TsfaServiceException
     * @author 彭俊霖 CreateDate: 2017年10月27日
     */
    @Override
    public UpdateImChatInfoReturn updateImChatInfoStatus(UpdateImChatInfo updateImChatInfo) throws
            TsfaServiceException {
        logger.info("updateImChatInfoStatus(UpdateImChatInfo updateImChatInfo={}) - start", updateImChatInfo); 

        AssertUtils.notNull(updateImChatInfo);
        AssertUtils.notNullAndEmpty(updateImChatInfo.getCode(), "CODE不能为空");
        AssertUtils.notNullAndEmpty(updateImChatInfo.getStatus(), "消息状态不能为空");
        try {
            ImChatInfo imChatInfo = new ImChatInfo();
            // update数据录入
            imChatInfo.setCode(updateImChatInfo.getCode());
            imChatInfo.setStatus(updateImChatInfo.getStatus());
            imChatInfo.setErrorCode(updateImChatInfo.getErrorCode());
            imChatInfo.setErrorMessage(updateImChatInfo.getErrorMessage());
            imChatInfo.setReceivedTime(new Date());
            AssertUtils.notUpdateMoreThanOne(imChatInfoDao.updateByPrimaryKeySelective(imChatInfo));
            UpdateImChatInfoReturn updateImChatInfoReturn = new UpdateImChatInfoReturn();

            logger.info("updateImChatInfoStatus(UpdateImChatInfo) - end - return value={}", updateImChatInfoReturn); 
            return updateImChatInfoReturn;
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("IM聊天记录信息更新信息错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_UPDATE_ERROR, "IM聊天记录信息更新信息错误！", e);
        }
    }

    /**
     * 方法说明：更新聊天记录已接收
     *
     * @param updateImChatInfoReceived
     * @author 曾垂瑜 CreateDate: 2017年12月5日
     */
    @Override
    public void updateImChatInfoReceived(UpdateImChatInfoReceived updateImChatInfoReceived) {
        logger.info("updateImChatInfoReceived(UpdateImChatInfoReceived updateImChatInfoReceived={}) - start", updateImChatInfoReceived); 

        AssertUtils.notNull(updateImChatInfoReceived);
        AssertUtils.notNullAndEmpty(updateImChatInfoReceived.getMsgId(), "消息ID不能为空");
        AssertUtils.notNullAndEmpty(updateImChatInfoReceived.getReceivedFlag(), "接收端标识不能为空");
        try {
        	//截取错误信息保留100为字符
        	if(StringUtils.isNotEmpty(updateImChatInfoReceived.getErrorMessage()) && updateImChatInfoReceived.getErrorMessage().length()>100) {
        		updateImChatInfoReceived.setErrorMessage(updateImChatInfoReceived.getErrorMessage().substring(0, 100));
        	}
            ImChatInfo udpateChatInfo = new ImChatInfo();
            // update数据录入
            udpateChatInfo.setCode(updateImChatInfoReceived.getMsgId());

            // 聊天记录接收方为导购端，则还需检查聊天记录是否是由第三方（如导购助手或系统自动发送名片、优惠券等）发送，如果是则只更新其同步状态
            if (SenderFlag.GM.getCode().equals(updateImChatInfoReceived.getReceivedFlag())) { // 接收方为导购客户端
                ImChatInfo imChatInfo = imChatInfoDao.selectByPrimaryKey(updateImChatInfoReceived.getMsgId());
                if (imChatInfo == null) {
                    logger.warn("没有找到聊天记录{}", updateImChatInfoReceived.getMsgId());
                    logger.info("updateImChatInfoReceived(UpdateImChatInfoReceived) - end"); 
                    return;
                }
                if (SenderFlag.ZK.getCode().equals(imChatInfo.getSenderFlag())) { // 客户发给导购
                    udpateChatInfo.setStatus(updateImChatInfoReceived.isSuccess() ? MessageStatus.SUCCESS.getCode() : MessageStatus.FAILURE.getCode()); // 发送成功或失败
                    udpateChatInfo.setErrorCode(updateImChatInfoReceived.getErrorCode());
                    udpateChatInfo.setErrorMessage(updateImChatInfoReceived.getErrorMessage());
                    udpateChatInfo.setReceivedTime(new Date());
                    udpateChatInfo.setThirdReadFlag(ReadFlag.NO.getCode());//接收到标记为未读-点开后才为已读
                } else { // 系统代替导购发给客户，将聊天记录同步到导购客户端，导购客户端收到消息后的回调
                    if (updateImChatInfoReceived.isSuccess()) {
                        udpateChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode());
                    } else { // 接收失败不更新
                        logger.warn("系统代替导购发给客户，将聊天记录同步到导购客户端，服务器收到导购客户端接收失败的回调,不更新聊天记录");
                        logger.info("updateImChatInfoReceived(UpdateImChatInfoReceived) - end"); 
                        return;
                    }
                }
            } else { // 接收方为中控客户端
                udpateChatInfo.setStatus(updateImChatInfoReceived.isSuccess() ? MessageStatus.SUCCESS.getCode() : MessageStatus.FAILURE.getCode()); // 发送成功或失败
                udpateChatInfo.setErrorCode(updateImChatInfoReceived.getErrorCode());
                udpateChatInfo.setErrorMessage(updateImChatInfoReceived.getErrorMessage());
                udpateChatInfo.setReceivedTime(new Date());
            }
//            if (!asyncStorer.updateImChatInfo(udpateChatInfo)) {
//                logger.warn("写入redis失败，将udpateChatInfo数据直接写入MySQL。");
                imChatInfoDao.updateByPrimaryKeySelective(udpateChatInfo);
//            }
        
            logger.info("updateImChatInfoReceived(UpdateImChatInfoReceived) - end"); 
        } catch (TsfaServiceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("更新聊天记录已接收错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_UPDATE_ERROR, "更新聊天记录已接收错误！", e);
        }
    }

    /**
     * 方法说明：查找IM聊天记录信息
     *
     * @param findImChatInfoPage
     * @return
     * @throws TsfaServiceException
     * @author 曾垂瑜 CreateDate: 2017-06-14
     */
    @Override
    public FindImChatInfoReturn findImChatInfo(FindImChatInfo findImChatInfo) throws TsfaServiceException {
        logger.debug("findImChatInfo(FindImChatInfo findImChatInfo={}) - start", findImChatInfo); 

        AssertUtils.notNull(findImChatInfo);
        AssertUtils.notNullAndEmpty(findImChatInfo.getCode(), "CODE不能为空");
        try {
            ImChatInfo imChatInfo = imChatInfoDao.selectByPrimaryKey(findImChatInfo.getCode());
            FindImChatInfoReturn findImChatInfoReturn = null;
            if (imChatInfo != null) {
//                throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_NOT_EXIST_ERROR, "IM聊天记录信息不存在");
            	
            	// find数据录入
            	findImChatInfoReturn = new FindImChatInfoReturn();
            	findImChatInfoReturn.setCode(imChatInfo.getCode());
            	findImChatInfoReturn.setMemberNoGm(imChatInfo.getMemberNoGm());
            	findImChatInfoReturn.setMemberNameGm(imChatInfo.getMemberNameGm());
            	findImChatInfoReturn.setNoWxGm(imChatInfo.getNoWxGm());
            	findImChatInfoReturn.setMemberNo(imChatInfo.getMemberNo());
            	findImChatInfoReturn.setMemberName(imChatInfo.getMemberName());
            	findImChatInfoReturn.setNoWx(imChatInfo.getNoWx());
            	findImChatInfoReturn.setAlias(imChatInfo.getAlias());
//            	findImChatInfoReturn.setShopNo(imChatInfo.getShopNo());
//            	findImChatInfoReturn.setShopName(imChatInfo.getShopName());
            	findImChatInfoReturn.setMerchantNo(imChatInfo.getMerchantNo());
            	findImChatInfoReturn.setMerchantName(imChatInfo.getMerchantName());
            	findImChatInfoReturn.setSenderFlag(imChatInfo.getSenderFlag());
            	findImChatInfoReturn.setSenderSyncStatus(imChatInfo.getSenderSyncStatus());
            	findImChatInfoReturn.setType(imChatInfo.getType());
            	findImChatInfoReturn.setStatus(imChatInfo.getStatus());
            	findImChatInfoReturn.setChatTime(imChatInfo.getChatTime());
            	findImChatInfoReturn.setReceivedTime(imChatInfo.getReceivedTime());
            	findImChatInfoReturn.setResourcesPath(imChatInfo.getResourcesPath());
            	findImChatInfoReturn.setShareTitle(imChatInfo.getShareTitle());
            	findImChatInfoReturn.setShareDes(imChatInfo.getShareDes());
            	findImChatInfoReturn.setShareUrl(imChatInfo.getShareUrl());
            	findImChatInfoReturn.setChatroomType(imChatInfo.getChatroomType());
            	findImChatInfoReturn.setChatroomNoWx(imChatInfo.getChatroomNoWx());
            	findImChatInfoReturn.setMsgSource(imChatInfo.getMsgSource());
            	findImChatInfoReturn.setThirdReadFlag(findImChatInfoReturn.getThirdReadFlag());
            	findImChatInfoReturn.setImei(imChatInfo.getImei());
            	findImChatInfoReturn.setErrorCode(imChatInfo.getErrorCode());
            	findImChatInfoReturn.setErrorMessage(imChatInfo.getErrorMessage());
            	findImChatInfoReturn.setCreateDate(imChatInfo.getCreateDate());
            	findImChatInfoReturn.setRemark(imChatInfo.getRemark());
            	findImChatInfoReturn.setRemark2(imChatInfo.getRemark2());
            	findImChatInfoReturn.setRemark3(imChatInfo.getRemark3());
            	findImChatInfoReturn.setRemark4(imChatInfo.getRemark4());
            	findImChatInfoReturn.setContent(imChatInfo.getContent());
            	findImChatInfoReturn.setBigImg(imChatInfo.getBigImg());
            	findImChatInfoReturn.setMidImg(imChatInfo.getMidImg());
            }

            logger.debug("findImChatInfo(FindImChatInfo) - end - return value={}", findImChatInfoReturn); 
            return findImChatInfoReturn;
        } catch (TsfaServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("查找IM聊天记录信息信息错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "查找IM聊天记录信息信息错误！", e);
        }
    }
    
    
    
    /**
     * 方法说明：查找最后一条聊天记录
     *
     * @param findImChatInfoPage
     * @return
     * @throws TsfaServiceException
     * @author 曾垂瑜 CreateDate: 2017-06-14
     */
    @Override
    public FindImChatInfoReturn findLastImChatInfo(FindImChatInfo findImChatInfo) throws TsfaServiceException {
        logger.debug("findLastImChatInfo(FindImChatInfo findImChatInfo={}) - start", findImChatInfo); 

        AssertUtils.notNull(findImChatInfo);
        AssertUtils.notNullAndEmpty(findImChatInfo.getNoWxGm(), "NoWxGm不能为空");
        try {
        	FindImChatInfoReturn imChatInfo = imChatInfoDao.findLastImChatInfo(findImChatInfo);
            FindImChatInfoReturn findImChatInfoReturn = null;
            if (imChatInfo != null) {
//                throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_NOT_EXIST_ERROR, "IM聊天记录信息不存在");
            	
            	// find数据录入
            	findImChatInfoReturn = new FindImChatInfoReturn();
            	findImChatInfoReturn.setCode(imChatInfo.getCode());
            	findImChatInfoReturn.setMemberNoGm(imChatInfo.getMemberNoGm());
            	findImChatInfoReturn.setMemberNameGm(imChatInfo.getMemberNameGm());
            	findImChatInfoReturn.setNoWxGm(imChatInfo.getNoWxGm());
            	findImChatInfoReturn.setMemberNo(imChatInfo.getMemberNo());
            	findImChatInfoReturn.setMemberName(imChatInfo.getMemberName());
            	findImChatInfoReturn.setNoWx(imChatInfo.getNoWx());
            	findImChatInfoReturn.setAlias(imChatInfo.getAlias());
//            	findImChatInfoReturn.setShopNo(imChatInfo.getShopNo());
//            	findImChatInfoReturn.setShopName(imChatInfo.getShopName());
            	findImChatInfoReturn.setMerchantNo(imChatInfo.getMerchantNo());
            	findImChatInfoReturn.setMerchantName(imChatInfo.getMerchantName());
            	findImChatInfoReturn.setSenderFlag(imChatInfo.getSenderFlag());
            	findImChatInfoReturn.setSenderSyncStatus(imChatInfo.getSenderSyncStatus());
            	findImChatInfoReturn.setType(imChatInfo.getType());
            	findImChatInfoReturn.setStatus(imChatInfo.getStatus());
            	findImChatInfoReturn.setChatTime(imChatInfo.getChatTime());
            	findImChatInfoReturn.setReceivedTime(imChatInfo.getReceivedTime());
            	findImChatInfoReturn.setResourcesPath(imChatInfo.getResourcesPath());
            	findImChatInfoReturn.setShareTitle(imChatInfo.getShareTitle());
            	findImChatInfoReturn.setShareDes(imChatInfo.getShareDes());
            	findImChatInfoReturn.setShareUrl(imChatInfo.getShareUrl());
            	findImChatInfoReturn.setChatroomType(imChatInfo.getChatroomType());
            	findImChatInfoReturn.setChatroomNoWx(imChatInfo.getChatroomNoWx());
            	findImChatInfoReturn.setMsgSource(imChatInfo.getMsgSource());
            	findImChatInfoReturn.setThirdReadFlag(findImChatInfoReturn.getThirdReadFlag());
            	findImChatInfoReturn.setImei(imChatInfo.getImei());
            	findImChatInfoReturn.setErrorCode(imChatInfo.getErrorCode());
            	findImChatInfoReturn.setErrorMessage(imChatInfo.getErrorMessage());
            	findImChatInfoReturn.setCreateDate(imChatInfo.getCreateDate());
            	findImChatInfoReturn.setRemark(imChatInfo.getRemark());
            	findImChatInfoReturn.setRemark2(imChatInfo.getRemark2());
            	findImChatInfoReturn.setRemark3(imChatInfo.getRemark3());
            	findImChatInfoReturn.setRemark4(imChatInfo.getRemark4());
            	findImChatInfoReturn.setContent(imChatInfo.getContent());
            }

            logger.debug("findImChatInfo(FindImChatInfo) - end - return value={}", findImChatInfoReturn); 
            return findImChatInfoReturn;
        } catch (TsfaServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("查找IM聊天记录信息信息错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "查找IM聊天记录信息信息错误！", e);
        }
    }

    /**
     * 方法说明：查找IM聊天记录信息
     *
     * @param findImChatInfoPage
     * @return
     * @throws TsfaServiceException
     * @author 曾垂瑜 CreateDate: 2017-06-14
     */
    @Override
    public Page<FindImChatInfoPageReturn> findImChatInfoPage(FindImChatInfoPage findImChatInfoPage) throws
            TsfaServiceException {
        logger.debug("findImChatInfoPage(FindImChatInfoPage findImChatInfoPage={}) - start", findImChatInfoPage); 

        AssertUtils.notNull(findImChatInfoPage);
        List<FindImChatInfoPageReturn> returnList;
        int count = 0;
        try {
            count = imChatInfoDao.findImChatInfoPageCount(findImChatInfoPage);
            if (count > 0) {
                returnList = imChatInfoDao.findImChatInfoPage(findImChatInfoPage);
            } else {
                returnList = new ArrayList<FindImChatInfoPageReturn>();
            }
        } catch (Exception e) {
            logger.error("IM聊天记录信息不存在错误", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_PAGE_ERROR, "IM聊天记录信息不存在错误.！", e);
        }
        Page<FindImChatInfoPageReturn> returnPage = new Page<FindImChatInfoPageReturn>(returnList, count, findImChatInfoPage);

        logger.debug("findImChatInfoPage(FindImChatInfoPage) - end - return value={}", returnPage); 
        return returnPage;
    }

    /**
     * 方法说明：查找IM聊天记录信息数量
     *
     * @param findImChatInfoPage
     * @return
     * @throws TsfaServiceException
     * @author 彭俊霖 CreateDate: 2017年10月27日
     */
    @Override
    public int findImChatInfoPageCount(FindImChatInfoPage findImChatInfoPage) throws TsfaServiceException {
        logger.debug("findImChatInfoPageCount(FindImChatInfoPage findImChatInfoPage={}) - start", findImChatInfoPage); 

        AssertUtils.notNull(findImChatInfoPage);
        AssertUtils.notAllNullAndEmpty(findImChatInfoPage.getMemberNoGm(), findImChatInfoPage.getNoWxGm(), "导购编号和微信号不能同时为空");
        int count = 0;
        try {
            count = imChatInfoDao.findImChatInfoPageCount(findImChatInfoPage);
        } catch (Exception e) {
            logger.error("IM聊天记录信息不存在错误", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_PAGE_ERROR, "IM聊天记录信息不存在错误.！", e);
        }
        logger.debug("findImChatInfoPageCount(FindImChatInfoPage) - end - return value={}", count); 
        return count;
    }

    /**
     * 方法说明：手机客户端查询离线消息
     *
     * @param findOfflineChatInfo
     * @return
     * @author 曾垂瑜 CreateDate: 2017年11月4日
     */
    @Override
    public FindOfflineChatInfoReturn findOfflineChatInfo(FindOfflineChatInfo findOfflineChatInfo) throws
            TsfaServiceException {
        logger.debug("findOfflineChatInfo(FindOfflineChatInfo findOfflineChatInfo={}) - start", findOfflineChatInfo); 

        AssertUtils.notNull(findOfflineChatInfo);
        AssertUtils.notNull(findOfflineChatInfo.getClientFlag(), "客户端标识不能为空");

        AssertUtils.notNullAndEmpty(findOfflineChatInfo.getNoWxGm(), "微信号不能为空");
        FindOfflineChatInfoReturn findOfflineChatInfoReturn = new FindOfflineChatInfoReturn();
        try {
            List<FindOfflineChatInfoGroup> groupList = imChatInfoDao.findOfflineChatInfo(findOfflineChatInfo);
            if (groupList != null && !groupList.isEmpty()) {
                findOfflineChatInfoReturn.setGroupList(groupList);
                List<FindOfflineChatInfoGroup> groupListRemove = new ArrayList<FindOfflineChatInfoGroup>();
                for (FindOfflineChatInfoGroup group : groupList) {
                    group.setCount(group.getChatList().size());
                    findOfflineChatInfoReturn.setTotal(findOfflineChatInfoReturn.getTotal() + group.getCount());
                    
                    //群聊返回相关信息
                    if(group.getChatroomType().equals(ChatRoomType.ROOM.getCode().toString())){
                    	/**
                    	 * 获取群聊信息
                    	 */
                    	FindChatRoom findChatRoom = new FindChatRoom();
                    	findChatRoom.setCode(group.getMemberNo());
                    	FindChatRoomReturn findChatRoomReturn= chatRoomService.findChatRoom(findChatRoom);
                    	//群聊不存在，直接过滤掉
                    	if(findChatRoomReturn == null) {
                    		logger.warn("群聊不存在，过滤掉：{}",group.getMemberNo());
                    		groupListRemove.add(group);
                    		continue;
                    	}
                    	//设置消息免打扰
                    	group.setNoDisturb(findChatRoomReturn.getNoDisturb());
                    	if(StringUtils.isNotEmpty(findChatRoomReturn.getHeadUrl())) {
                    		group.setHeadUrl(findChatRoomReturn.getHeadUrl());
                    	}
                    	if(StringUtils.isNotEmpty(findChatRoomReturn.getRoomNickName())) {
                    		group.setRoomNickName(findChatRoomReturn.getRoomNickName());
                    	}
                    	for (FindOfflineChatInfoDetail  findOfflineChatInfoDetail : group.getChatList()) {
                    		if(StringUtils.isNotEmpty(findOfflineChatInfoDetail.getGroupUserName())){
                    			FindChatRoomMemberReturn findChatRoomMemberReturn= iChatRoomMemberService.findChatRoomMemberByNoWx(findChatRoomReturn.getCode(), findOfflineChatInfoDetail.getGroupUserName());
                				if(null !=findChatRoomMemberReturn){
                					findOfflineChatInfoDetail.setMemberNickName(findChatRoomMemberReturn.getNickName());
                					findOfflineChatInfoDetail.setMemberHeadUrl(findChatRoomMemberReturn.getHeadUrl());
                				}
                        		
                        	}
                    		findOfflineChatInfoDetail.setCreateTime(findOfflineChatInfoDetail.getChatTime().getTime());
						}
                    }else {
                    	//单聊
                    	for (FindOfflineChatInfoDetail  findOfflineChatInfoDetail : group.getChatList()) {
                    		findOfflineChatInfoDetail.setCreateTime(findOfflineChatInfoDetail.getChatTime().getTime());
						}
                    }
                }
                
                if(groupListRemove.size()>0) {
                	groupList.removeAll(groupListRemove);
                }
                
            }
        } catch (Exception e) {
            logger.error("查询离线消息失败", e);
            throw new TsfaServiceException(ErrorCode.IM_OFFLINE_CHAT_INFO_FIND_ERROR, "查询离线消息失败", e);
        }

        logger.debug("findOfflineChatInfo(FindOfflineChatInfo) - end - return Offline-Chat-Count={}", findOfflineChatInfoReturn.getTotal()); 
        return findOfflineChatInfoReturn;
    }

    /**
     * 方法说明：客户端已接收到服务器推送的离线聊天记录，则更新这些离线记录为已发送
     *
     * @param receivedOfflineChatInfo
     * @author 曾垂瑜 CreateDate: 2017年11月4日
     */
    @Override
    public void receivedOfflineChatInfo(ReceivedOfflineChatInfo receivedOfflineChatInfo) {
        logger.info("receivedOfflineChatInfo(FindOfflineChatInfo receivedOfflineChatInfo={}) - start", receivedOfflineChatInfo); 

        AssertUtils.notNull(receivedOfflineChatInfo);
        AssertUtils.notAllNullAndEmpty(receivedOfflineChatInfo.getMemberNoGm(), receivedOfflineChatInfo.getNoWxGm(), "导购编号和微信号不能同时为空");

        try {
            imChatInfoDao.updateOfflineChatInfoToSend(receivedOfflineChatInfo);

            // 导购客户端，需更新第三方（导购助手）发送的记录
            if (SenderFlag.GM.getCode().equals(receivedOfflineChatInfo.getClientFlag())) {
                imChatInfoDao.updateOfflineChatInfoToSync(receivedOfflineChatInfo);
            }
        } catch (Exception e) {
            logger.error("修改离线聊天记录已发送失败", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_UPDATE_ERROR, "修改离线聊天记录已发送失败", e);
        }

        logger.info("receivedOfflineChatInfo(ReceivedOfflineChatInfo) - end"); 
    }

    /**
     * 方法说明：按年月日分组查询OMS所需
     * 1.memberNo 导购编号
     * 2.startTime 开始时间
     * 3.endTime 结束时间
     * 4.talker 客户微信号
     * 5.start 开始行
     * 6.limit 查询条数
     *
     * @param parmMap
     * @return
     * @author 彭俊霖 CreateDate: 2017年11月06日
     */
    @Override
    public Page<Map<String, Object>> findImChatInfoPageOMS(Map<String, Object> parmMap) throws
            TsfaServiceException {
        logger.debug("findImChatInfoPageOMS(parmMap={}) - start", parmMap); 

        AssertUtils.notNull(parmMap);
        AssertUtils.notNull(parmMap.get("start"));
        AssertUtils.notNull(parmMap.get("limit"));
        List<Map<String, Object>> returnList = null;
        int count = 0;
        try {
            returnList = imChatInfoDao.findImChatInfoPageOMS(parmMap);
            count = returnList.size();
        } catch (Exception e) {
            logger.error("查找OMS IM聊天记录信息不存在错误.！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_PAGE_ERROR, "查找OMS IM聊天记录信息不存在错误.！", e);
        }
        Page<Map<String, Object>> returnPage = new Page<Map<String, Object>>(returnList, count, Integer.parseInt(parmMap.get("start").toString()), Integer.parseInt(parmMap.get("limit").toString()));
        logger.debug("findImChatInfoPageOMS(parmMap) - end - return value={}", returnList); 
        return returnPage;
    }

    /**
     * 方法说明：未读客户数
     *
     * @param findUnreadCountByTerminal
     * @return
     * @author 曾垂瑜 CreateDate: 2017年12月4日
     */
    @Override
    public List<Map<String, Object>> findUnreadPersonCountByWx(FindUnreadCountByTerminal findUnreadCountByTerminal) {
        logger.debug("findUnreadCountByTerminalFromWeb(FindUnreadCountByTerminal findUnreadCountByTerminal={}) - start", findUnreadCountByTerminal); 

        AssertUtils.notNull(findUnreadCountByTerminal);
        AssertUtils.notNullAndEmpty(findUnreadCountByTerminal.getMerchantNo(), "商户编号不能为空");
        AssertUtils.notNullAndEmpty(findUnreadCountByTerminal.getNoWxList(), "微信号不能为空");
        List<Map<String, Object>> countMap = null;
        try {
            countMap = imChatInfoDao.findUnreadPersonCountByWx(findUnreadCountByTerminal);
        } catch (Exception e) {
            logger.error("终端微信（终端）未读聊天记录数统计（导购助手）错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "终端微信（终端）未读聊天记录数统计（导购助手）错误！", e);
        }

        logger.debug("findUnreadCountByTerminalFromWeb(FindUnreadCountByTerminal) - end - return value={}", countMap); 
        return countMap;
    }

    
    @Override
    public List<Map<String, Object>> findUnreadPersonCountByGm(FindUnreadCountByTerminal findUnreadCountByTerminal) {
        logger.debug("findUnreadPersonCountByGm(FindUnreadCountByTerminal findUnreadCountByTerminal={}) - start", findUnreadCountByTerminal); 

        AssertUtils.notNull(findUnreadCountByTerminal);
        AssertUtils.notNullAndEmpty(findUnreadCountByTerminal.getMerchantNo(), "商户编号不能为空");
        AssertUtils.notNullAndEmpty(findUnreadCountByTerminal.getNoWxGm(), "终端微信不能为空");
        AssertUtils.notNullAndEmpty(findUnreadCountByTerminal.getMemberNoGms(), "导购集合不能为空");
        List<Map<String, Object>> countMap = null;
        try {
            countMap = imChatInfoDao.findUnreadPersonCountByGm(findUnreadCountByTerminal);
        } catch (Exception e) {
            logger.error("导购未读聊天记录数统计错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "导购未读聊天记录数统计错误！", e);
        }

        logger.debug("findUnreadPersonCountByGm(FindUnreadCountByTerminal) - end - return value={}", countMap); 
        return countMap;
    }
    
    /**
     * 方法说明：客户未读聊天记录数统计（导购助手）
     *
     * @param findUnreadCountByTerminal
     * @return
     * @author 曾垂瑜 CreateDate: 2017年12月4日
     */
    @Override
    public List<FindUnreadCountByMemberReturn> findUnreadCountByMemberFromWeb(FindUnreadCountByMember findUnreadCountByMember) {
        logger.debug("findUnreadCountByMemberFromWeb(FindUnreadCountByMember findUnreadCountByMember={}) - start", findUnreadCountByMember); 

        AssertUtils.notNull(findUnreadCountByMember);
        AssertUtils.notNullAndEmpty(findUnreadCountByMember.getNoWxShop(), "终端微信不能为空");
        AssertUtils.notNullAndEmpty(findUnreadCountByMember.getMemberNoGm(), "导购编号不能为空");
        AssertUtils.notNullAndEmpty(findUnreadCountByMember.getMemberNoList(), "客户列表不能为空");

        List<FindUnreadCountByMemberReturn> countMap = null;
        try {
            countMap = imChatInfoDao.findUnreadCountByMemberFromWeb(findUnreadCountByMember);
        } catch (Exception e) {
            logger.error("终端微信（终端）未读聊天记录数统计（导购助手）错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "终端微信（终端）未读聊天记录数统计（导购助手）错误！", e);
        }

        logger.debug("findUnreadCountByMemberFromWeb(FindUnreadCountByMember) - end - return value={}", countMap); 
        return countMap;
    }

    /**
     * 方法说明：查询客户聊天记录（导购助手）-按聊天时间倒序排序
     *
     * @param findChatInfoPageFromWeb
     * @return
     * @author 曾垂瑜 CreateDate: 2017年12月4日
     */
    @Override
	public IPage<FindChatInfoPageFromWebReturn> findChatInfoPageFromWeb(FindChatInfoPageFromWeb findChatInfoPageFromWeb) {
        logger.debug("findChatInfoPageFromWeb(FindChatInfoPageFromWeb findChatInfoPageFromWeb={}) - start", findChatInfoPageFromWeb); 

        AssertUtils.notNull(findChatInfoPageFromWeb);
        AssertUtils.notNullAndEmpty(findChatInfoPageFromWeb.getMemberNo(), "客户编号不能为空");
//        AssertUtils.notNullAndEmpty(findChatInfoPageFromWeb.getNoWxGm(), "终端微信不能为空");

        List<FindChatInfoPageFromWebReturn> returnList = null;
        int count = 0;
        try {
            count = imChatInfoDao.findChatInfoPageFromWebCount(findChatInfoPageFromWeb);
            if (count > 0) {
                returnList = imChatInfoDao.findChatInfoPageFromWebList(findChatInfoPageFromWeb);
            }
        } catch (Exception e) {
            logger.error("查询客户聊天记录（导购助手）错误", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_PAGE_ERROR, "查询客户聊天记录（导购助手）错误！", e);
        }
        Page<FindChatInfoPageFromWebReturn> returnPage = new Page<FindChatInfoPageFromWebReturn>(returnList, count, findChatInfoPageFromWeb);

        logger.debug("findChatInfoPageFromWeb(FindChatInfoPageFromWeb) - end - return value={}", returnPage); 
        return returnPage;
    }

    /**
     * 方法说明：更新聊天记录为已读
     *
     * @param updateThirdHaveReadFromWeb
     * @author 曾垂瑜 CreateDate: 2017年12月4日
     */
    @Override
    public void updateThirdHaveReadFromWeb(UpdateThirdHaveReadFromWeb updateThirdHaveReadFromWeb) {
        logger.info("updateThirdHaveReadFromWeb(UpdateThirdHaveReadFromWeb updateThirdHaveReadFromWeb={}) - start", updateThirdHaveReadFromWeb); 

        AssertUtils.notNull(updateThirdHaveReadFromWeb);
        AssertUtils.notNullAndEmpty(updateThirdHaveReadFromWeb.getMemberNo(), "客户编号不能为空");
        AssertUtils.notNullAndEmpty(updateThirdHaveReadFromWeb.getMemberNoGm(), "导购编号不能为空");
        AssertUtils.notNullAndEmpty(updateThirdHaveReadFromWeb.getNoWxGm(), "终端微信不能为空");
        try {
            imChatInfoDao.updateThirdHaveReadFromWeb(updateThirdHaveReadFromWeb);
            // 更新客户最新聊天动态
        	pmChatBehaviorService.updateBehavior(new UpdatePmChatBehavior(updateThirdHaveReadFromWeb.getMemberNo(),updateThirdHaveReadFromWeb.getMemberNoGm(), updateThirdHaveReadFromWeb.getNoWxGm(), null, ReadFlag.NO.getCode()));
        } catch (Exception e) {
            logger.error("更新第三方已读（导购助手）错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_UPDATE_ERROR, "更新第三方已读（导购助手）错误！", e);
        }

        logger.info("updateThirdHaveReadFromWeb(UpdateThirdHaveReadFromWeb) - end "); 
    }

    /**
     * 方法说明：查找该段时间类微信聊天的导购
     *
     * @param param
     * @return
     * @author 梅宏博  CreateDate: 2017年12月7日
     */
    @Override
    public List<String> findImByDate(Map<String, Object> param) throws TsfaServiceException {
        logger.debug("findImByDate(Map<String, Object> param={}) - start", param); 
        return imChatInfoDao.findImByDate(param);
    }

    /**
     * 方法说明：查询导购某天微信聊天统计
     *
     * @param param
     * @return
     * @author 梅宏博  CreateDate: 2017年12月7日
     */
    @Override
    public List<Map<String, Object>> findCountImChatByGM(Map<String, Object> map) {
        logger.debug("findCountImChatByGM(Map<String, Object> map={}) - start", map); 
        return imChatInfoDao.findCountImChatByGM(map);
    }

    /**
     * 方法说明：查询im聊天的第一条聊天记录
     *
     * @param findImChatInfo
     * @return
     * @author 梅宏博  CreateDate: 2017年12月7日
     */
    @Override
    public ImChatInfo findImFristInfo(FindImChatInfo findImChatInfo) {
        logger.debug("findImFristInfo(FindImChatInfo findImChatInfo={}) - start", findImChatInfo); 
        return imChatInfoDao.findImFristInfo(findImChatInfo);
    }

    /**
     * 方法说明：导购与客户沟通次数统计(单个导购与几个客户沟通)
     *
     * @param 1.memberNamegm 导购姓名 2.shopName 终端名称 3.startTime 聊天开始日期 4.endTime
     *                       聊天结束日期 5.start 分页开始 6.limit 每页尺寸 7.merchantNo 商户编号
     * @return
     * @throws TsfaServiceException
     * @author 李端强 CreateDate: 2017年12月12日
     */
    public Page<Map<String, Object>> findImChatStatisticsOMS(Map<String, Object> parmMap) throws
            TsfaServiceException {
        logger.debug("findImChatStatisticsOMS(parmMap={}) - start", parmMap);
        AssertUtils.notNull(parmMap);
        AssertUtils.notNull(parmMap.get("start"));
        AssertUtils.notNull(parmMap.get("limit"));
        List<Map<String, Object>> returnList = null;
        int count = 0;
        try {
            returnList = imChatInfoDao.findImChatStatisticsOMS(parmMap);
            count = returnList.size();
        } catch (Exception e) {
            logger.error("查找OMS IM聊天记录信息不存在错误.！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_PAGE_ERROR, "查找OMS IM聊天记录信息不存在错误.！", e);
        }
        Page<Map<String, Object>> returnPage = new Page<Map<String, Object>>(returnList, count, Integer.parseInt(parmMap.get("start").toString()), Integer.parseInt(parmMap.get("limit").toString()));
        logger.debug("findImChatStatisticsOMS(parmMap) - end - return value={}", returnList);
        return returnPage;
    }

    /**
     * 新增客户后 1、将客户发送给导购保存在临时表中的聊天记录发给导购（临时表以后将弃用） 2、将只有客户微信没有客户编号的聊天记录发送给导购
     */
    @Override
    public void sendChatByNewPerson(SendChatByNewPerson sendChatByNewPerson) {
        logger.info("sendChatByNewPerson(SendChatByNewPerson sendChatByNewPerson={}) - start", sendChatByNewPerson);

        AssertUtils.notNull(sendChatByNewPerson);
  
        AssertUtils.notNullAndEmpty(sendChatByNewPerson.getNoWxGm(), "导购微信不能为空");
        AssertUtils.notNullAndEmpty(sendChatByNewPerson.getMemberNo(), "客户编号不能为空");
        AssertUtils.notNullAndEmpty(sendChatByNewPerson.getMemberName(), "客户姓名不能为空");
        AssertUtils.notNullAndEmpty(sendChatByNewPerson.getNoWx(), "客户微信不能为空");
//        AssertUtils.notNullAndEmpty(sendChatByNewPerson.getShopNo(), "分店编号不能为空");

        ICommonService basic =  commonService.getHessianCommonService(sendChatByNewPerson.getMemberNoGm());
        // 导购是否在线
        boolean onlineGm = basic.getClientStatus(sendChatByNewPerson.getMemberNoGm());

        // 将聊天临时表中的记录转到聊天表中，并发给导购(以后去除 XXX)
//        this.sendChatByTemp(sendChatByNewPerson, guidMember, onlineGm);

        // 将没有客户信息的聊天记录发送给导购，并填写客户信息
        List<ImChatInfo> chatList = null;
        try {
            chatList = imChatInfoDao.findImChatInfoByNonMember(sendChatByNewPerson.getNoWxGm(), sendChatByNewPerson.getNoWx());
        } catch (Exception e) {
            logger.error("查询终端微信新增客户所有无客户信息的聊天记录错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "查询终端微信新增客户所有无客户信息的聊天记录错误！", e);
        }

        // 逐条发送
        if (chatList != null && chatList.size() > 0) {
            for (ImChatInfo chatInfo : chatList) {
                try {
                    // 填入聊天记录的客户信息
                    ImChatInfo updateChatInfo = new ImChatInfo();
                    updateChatInfo.setCode(chatInfo.getCode());
                    updateChatInfo.setMemberNoGm(sendChatByNewPerson.getMemberNoGm());
                    updateChatInfo.setMemberNameGm(sendChatByNewPerson.getMemberNameGm());
                    updateChatInfo.setMemberNo(sendChatByNewPerson.getMemberNo());
                    updateChatInfo.setMemberName(sendChatByNewPerson.getMemberName());
                    imChatInfoDao.updateByPrimaryKeySelective(updateChatInfo);

                    // 发送聊天记录:导购已登录
                    if (onlineGm) {
                        ChatMessageRequest chatRequest = new ChatMessageRequest();
                        chatRequest.setSenderFlag(chatInfo.getSenderFlag());
                        chatRequest.setReceiveFlag(SenderFlag.GM.getCode()); // 导购接收
                        chatRequest.setResend(Boolean.FALSE);
                        chatRequest.setMsgId(chatInfo.getCode());
                        chatRequest.setMemberNoGm(sendChatByNewPerson.getMemberNoGm());
                        chatRequest.setNoWxGm(chatInfo.getNoWxGm());
                        chatRequest.setMemberNo(sendChatByNewPerson.getMemberNo());
                        chatRequest.setNoWx(chatInfo.getNoWx());
                        chatRequest.setAlias(chatInfo.getAlias());
                        chatRequest.setType(chatInfo.getType());
                        chatRequest.setContent(chatInfo.getContent());
                        chatRequest.setResources(chatInfo.getResourcesPath());
                        chatRequest.setShareTitle(chatInfo.getShareTitle());
                        chatRequest.setShareDes(chatInfo.getShareDes());
                        chatRequest.setShareUrl(chatInfo.getShareUrl());
                        chatRequest.setCreateTime(chatInfo.getChatTime().getTime());
                        
                        
                        
                        IChatService basics = null;
                		if(chatRequest.getReceiveFlag() == 1) {	// 导购接收
                			 basics = commonService.getHessianChatService(chatRequest.getMemberNoGm());
                		} else {	// 客户接收
                			 basics = commonService.getHessianChatService(chatRequest.getNoWxGm());
                		}
                        basics.sendChatMessage(chatRequest);
                        logger.info("已向导购同步聊天记录：{}", chatInfo.getCode());
                    }

                    // 更新客户最新聊天动态
                    if(StringUtils.isNotEmpty(sendChatByNewPerson.getMemberNo()) && StringUtils.isNotEmpty(sendChatByNewPerson.getMemberNoGm())) {
                    	Integer unreadFlag = ReadFlag.NO.getCode().equals(chatInfo.getThirdReadFlag()) ? ReadFlag.YES.getCode() : ReadFlag.NO.getCode();
                        UpdatePmChatBehavior chatBehavior =  new UpdatePmChatBehavior(sendChatByNewPerson.getMemberNo(),sendChatByNewPerson.getMemberNoGm(), sendChatByNewPerson.getNoWxGm(), new Date(), unreadFlag);
                        if(SenderFlag.ZK.getCode().equals(chatInfo.getSenderFlag())) {	// 客户发的，需回复
                        	chatBehavior.setMemberChatTime(chatBehavior.getChatTime());
                        	chatBehavior.setUnrespFlag(ReadFlag.YES.getCode());
                        } else {
                        	chatBehavior.setUnrespFlag(ReadFlag.NO.getCode());
                        }
                        pmChatBehaviorService.updateBehavior(chatBehavior);
                    }
                    
                } catch (Exception e) {
                    logger.error("向导购同步聊天记录" + chatInfo.getCode() + "失败", e);
                    break;    // 中断发送
                }
            }
        }

        logger.info("sendChatByNewPerson(SendChatByNewPerson) - end");
    }

    

	
    /**
     * 方法说明：将聊天临时表中的记录转到聊天表中，并发给导购
     * 注：以后未认领客户的聊天记录也将保存到IM_CHAT_INFO表中，而不再存到临时表，所以此方法以后将废弃
     *
     * @param sendChatByNewPerson
     * @param guidMember
     * @param onlineGm
     * @author 曾垂瑜 CreateDate: 2018年1月29日
     */
//	private void sendChatByTemp(SendChatByNewPerson sendChatByNewPerson, FindGuidMemberReturn guidMember, boolean onlineGm) {
       /* List<ImChatInfoTemp> tempList = null;
        try {
            tempList = imChatInfoTempDao.findImChatInfoTempByNewPerson(sendChatByNewPerson.getShopNo(), sendChatByNewPerson.getNoWxGm(), sendChatByNewPerson.getNoWx());
        } catch (Exception e) {
            logger.error("查询终端微信新增客户所有临时聊天记录错误！", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_TEMP_FIND_ERROR, "查询终端微信新增客户所有临时聊天记录错误！", e);
        }

        // 逐条发送
        if (tempList != null && tempList.size() > 0) {
            for (ImChatInfoTemp temp : tempList) {
                try {
                    // 删除临时记录
                    imChatInfoTempDao.deleteByPrimaryKey(temp.getCode());

                    // 保存临时记录到聊天记录表
                    ImChatInfo imChatInfo = new ImChatInfo();
                    imChatInfo.setCode(temp.getCode());
                    imChatInfo.setMemberNoGm(guidMember.getMemberNo());
                    imChatInfo.setMemberNameGm(guidMember.getMemberName());
                    imChatInfo.setNoWxGm(guidMember.getNoWx());
                    imChatInfo.setMemberNo(sendChatByNewPerson.getMemberNo());
                    imChatInfo.setMemberName(sendChatByNewPerson.getMemberName());
                    imChatInfo.setNoWx(sendChatByNewPerson.getNoWx());
                    imChatInfo.setAlias(sendChatByNewPerson.getAlias());
//                    imChatInfo.setShopNo(guidMember.getShopNo());
//                    imChatInfo.setShopName(guidMember.getShopName());
                    imChatInfo.setMerchantNo(guidMember.getMerchantNo());
                    imChatInfo.setMerchantName(guidMember.getMerchantName());
                    imChatInfo.setSenderFlag(SenderFlag.ZK.getCode()); // 客户发送
                    // if(imChatInfo.getSenderFlag() == 2) { // 客户发送
                    imChatInfo.setThirdReadFlag(ReadFlag.NO.getCode()); // 第三方未读
                    // } else {
                    // imChatInfo.setThirdReadFlag(ReadFlag.YES.getCode()); //
                    // 导购发送，不管是由导购客户端还是第三方（导购助手发送），都默认为已读
                    // }
                    imChatInfo.setSenderSyncStatus(SenderSyncStatus.NO.getCode());
                    imChatInfo.setType(temp.getType());
                    imChatInfo.setStatus(MessageStatus.OFFLINE.getCode());// 未发送(离线)
                    imChatInfo.setChatTime(temp.getChatTime() != null ? temp.getChatTime() : new Date());
                    imChatInfo.setResourcesPath(temp.getResourcesPath());
                    imChatInfo.setShareTitle(temp.getShareTitle());
                    imChatInfo.setShareDes(temp.getShareDes());
                    imChatInfo.setShareUrl(temp.getShareUrl());
                    imChatInfo.setImei(temp.getImei());
                    imChatInfo.setCreateDate(new Date());
                    imChatInfo.setContent(temp.getContent());
                    imChatInfoDao.insertSelective(imChatInfo);
                    logger.info("已保存临时记录到聊天记录表：{}", temp.getCode());

                    // 发送聊天记录:导购已登录
                    if (onlineGm) {
                        ChatMessageRequest chatRequest = new ChatMessageRequest();
                        chatRequest.setSenderFlag(imChatInfo.getSenderFlag());
                        chatRequest.setResend(Boolean.FALSE);
                        chatRequest.setReceiveFlag(SenderFlag.GM.getCode());
                        chatRequest.setMsgId(imChatInfo.getCode());
                        chatRequest.setMemberNoGm(imChatInfo.getMemberNoGm());
                        chatRequest.setNoWxGm(imChatInfo.getNoWxGm());
                        chatRequest.setMemberNo(imChatInfo.getMemberNo());
                        chatRequest.setNoWx(imChatInfo.getNoWx());
                        chatRequest.setAlias(imChatInfo.getAlias());
                        chatRequest.setType(imChatInfo.getType());
                        chatRequest.setContent(imChatInfo.getContent());
                        chatRequest.setResources(imChatInfo.getResourcesPath());
                        chatRequest.setShareTitle(imChatInfo.getShareTitle());
                        chatRequest.setShareDes(imChatInfo.getShareDes());
                        chatRequest.setShareUrl(imChatInfo.getShareUrl());
                        chatRequest.setCreateTime(imChatInfo.getChatTime().getTime());
                        
                        
                        IChatService basics = null;
                		if(chatRequest.getReceiveFlag() == 1) {	// 导购接收
                			 basics = commonService.getHessianChatService(chatRequest.getMemberNoGm());
                		} else {	// 客户接收
                			 basics = commonService.getHessianChatService(chatRequest.getNoWxGm());
                		}
     
                        basics.sendChatMessage(chatRequest);
                        logger.info("已向导购发送临时聊天记录{}", temp.getCode());
                    }

                    // 更新客户最新聊天动态
                    Integer unreadFlag = imChatInfo.getThirdReadFlag() == null || ReadFlag.NO.getCode().equals(imChatInfo.getThirdReadFlag()) ? ReadFlag.YES.getCode() : null;
                    UpdatePmChatBehavior chatBehavior = new UpdatePmChatBehavior(imChatInfo.getMemberNo(), imChatInfo.getMemberNoGm(), new Date(), unreadFlag);
                    if(SenderFlag.ZK.getCode().equals(imChatInfo.getSenderFlag())) {	// 客户发的，需回复
                    	chatBehavior.setMemberChatTime(chatBehavior.getChatTime());
                    	chatBehavior.setUnrespFlag(ReadFlag.YES.getCode());
                    } else {
                    	chatBehavior.setUnrespFlag(ReadFlag.NO.getCode());
                    }
                    pmChatBehaviorService.updateBehavior(chatBehavior);
                } catch (Exception e) {
                    logger.error("向导购发送临时聊天记录" + temp.getCode() + "失败", e);
                }
            }
        }*/
//    }

    /**
	 * 
	 *
	 * 方法说明：向客户发送IM聊天记录(无事务)<br>
	 * 适用场景：<br>
	 * 1、导购助手与客户聊天<br>
	 * 2、系统级应用向客户自动发送消息
	 *
	 * @param chat
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月25日
	 *
	 */
    @Override
    public void sendChat(SendImChatInfo chat) {
        logger.info("sendChat(SendImChatInfo chat={})", chat);

        AssertUtils.notNull(chat);
        AssertUtils.notNull(chat.getSenderFlag(), "发送人标识不能为空");
        AssertUtils.notNull(chat.getChatroomType(), "群聊类型不能为空");
    	AssertUtils.notNullAndEmpty(chat.getNoWxGm(), "终端微信不能为空");
        AssertUtils.notNullAndEmpty(chat.getMemberNo(), "客户编号不能为空");
        AssertUtils.notNullAndEmpty(chat.getType(), "消息类型不能为空");
        AssertUtils.notNullAndEmpty(chat.getMsgSource(), "消息来源不能为空");
        
        FindShopTerminalReturn findShopTerminalReturn= shopTerminalService.findShopTerminalByWx(chat.getNoWxGm());
        AssertUtils.notNull(findShopTerminalReturn,"终端不存在");
        FindChatRoomReturn findChatRoomReturn = new FindChatRoomReturn();
        if(chat.getChatroomType() == ChatRoomType.ROOM.getCode()) {
        	FindChatRoom findChatRoom = new FindChatRoom();
        	findChatRoom.setCode(chat.getMemberNo());
        	findChatRoomReturn = chatRoomService.findChatRoom(findChatRoom);
        	AssertUtils.notNull(findChatRoomReturn,"群不存在");
        	String NoWxZk = chat.getNoWxGm();
			String ChatRoomName = findChatRoomReturn.getChatRoomName();
			//群聊头像没有，立即同步群聊信息
			if(StringUtils.isEmpty(findChatRoomReturn.getHeadUrl())){
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
						syncChatRoomMessage.setNoWxZk(NoWxZk);
						syncChatRoomMessage.setChatRoomName(ChatRoomName);
						syncChatRoomMessage.setNowSync(false);
						wxChatRoomService.sendSyncChatRoom(syncChatRoomMessage);
					}
				});
			}
        }
        
        ICommonService basic =  commonService.getHessianCommonService(chat.getNoWxGm());
        // 发给客户时不允许中控离线，直接抛出异常，不再保存聊天记录
        if (SenderFlag.GM.getCode().equals(chat.getSenderFlag()) && !chat.isAllowZkOffline() && !basic.getZkTerminalStatus(chat.getNoWxGm())) {
            logger.error("导购客户端已离线，发送失败！");
            throw new TsfaServiceException(com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE, "中控客户端已离线，发送失败！");
        }

        ImChatInfo chatInfo = null;
        try {
            // 如果消息为重发，则检查数据库
            if (chat.isResend()) {
                chatInfo = imChatInfoDao.selectByPrimaryKey(chat.getMsgId());
                if (chatInfo != null) {
                    logger.info("消息重复发送", chatInfo);
                    // 发送成功
                    if (MessageStatus.SUCCESS.getCode().equals(chatInfo.getStatus())) {
                        logger.info("消息已发送成功{}", chat.getMsgId());
                        logger.info("sendChat(SendImChatInfo) - end");
                        return;
                    } else if (SenderFlag.GM.getCode().equals(chatInfo.getSenderFlag())) { // 如果是导购发送的聊天记录,且状态不是发送成功,则重置chatTime
                        ImChatInfo update = new ImChatInfo();
                        update.setCode(chat.getMsgId());
                        update.setChatTime(new Date());
                        imChatInfoDao.updateByPrimaryKeySelective(update);
                        chatInfo.setChatTime(update.getChatTime());
                    }
                } else {
                    chatInfo = this.saveChatInfo(chat, findShopTerminalReturn, findChatRoomReturn);
                }
            } else {
                chatInfo = this.saveChatInfo(chat, findShopTerminalReturn, findChatRoomReturn);
            }
        } catch (TsfaServiceException e) {
            logger.error("保存聊天记录失败", e);
            throw e;
        } catch (Exception e) {
            logger.error("保存聊天记录失败", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_SEND_ERROR, "发送聊天记录失败", e);
        }

        // 发送聊天记录
        ChatMessageRequest chatRequest = new ChatMessageRequest();
        chatRequest.setSenderFlag(chatInfo.getSenderFlag());
        chatRequest.setResend(chat.isResend());
        chatRequest.setMsgId(chatInfo.getCode());
        chatRequest.setMemberNoGm(chatInfo.getMemberNoGm());
        chatRequest.setNoWxGm(chatInfo.getNoWxGm());
        chatRequest.setMemberNo(chatInfo.getMemberNo());
        chatRequest.setNoWx(chatInfo.getNoWx());
        chatRequest.setAlias(chatInfo.getAlias());
        chatRequest.setGroupUserName(chatInfo.getChatroomNoWx());
        chatRequest.setType(chatInfo.getType());
        chatRequest.setContent(chatInfo.getContent());
        chatRequest.setResources(chatInfo.getResourcesPath());
        chatRequest.setShareTitle(chatInfo.getShareTitle());
        chatRequest.setShareDes(chatInfo.getShareDes());
        chatRequest.setShareUrl(chatInfo.getShareUrl());
        chatRequest.setCreateTime(chatInfo.getChatTime().getTime());
        chatRequest.setHeadUrl(chatInfo.getHeadUrl());
        chatRequest.setRoomNickName(chatInfo.getRoomNickName());
        chatRequest.setMemberNickName(chatInfo.getMemberNickName());
        chatRequest.setMemberHeadUrl(chatInfo.getMemberHeadUrl());
        chatRequest.setNoDisturb(chatInfo.getNoDisturb());
        
        logger.info("判断是否需要发给导购SenderFlag ：" + chatInfo.getSenderFlag());
        logger.info("判断是否需要发给导购MsgSource ：" + chatInfo.getMsgSource());
        if (SenderFlag.GM.getCode().equals(chatInfo.getSenderFlag())) { // 导购发送给客户，需同时将聊天记录同步到导购客户端
            // 1、系统发送则需先同步给导购
            if (MessageSource.SA.getCode().equals(chatInfo.getMsgSource())) {
                try {
                    chatRequest.setReceiveFlag(SenderFlag.GM.getCode());
        			IChatService basics = commonService.getHessianChatService(chatRequest.getMemberNoGm());
                    basics.sendChatMessage(chatRequest);
                } catch (TsfaServiceException e) {
                    logger.warn("向导购同步聊天记录失败", e.getExceptionInfo());
                } catch (Exception e) {
                    logger.warn("向导购同步聊天记录失败", e);
                }
            }

            
            try {
            	// 2、再发给客户（发送到中控客户端）
                chatRequest.setReceiveFlag(SenderFlag.ZK.getCode());
            	IChatService basics = commonService.getHessianChatService(chatRequest.getNoWxGm());
            	basics.sendChatMessage(chatRequest);
            } catch (TsfaContextServiceException e) {
                if (com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE.equals(e.getExceptionCode())) {
                    if (!chat.isAllowZkOffline()) { // 不允许离线发送到中控客户端
                        UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
                        updateImChatInfo.setCode(chatInfo.getCode());
                        updateImChatInfo.setStatus(MessageStatus.FAILURE.getCode());
                        updateImChatInfo.setErrorCode(e.getExceptionMap().get("code"));
                        updateImChatInfo.setErrorMessage(e.getExceptionMap().get("message"));
                        imChatInfoService.updateImChatInfoStatus(updateImChatInfo);
                        throw e;
                    } else {
                        logger.info("中控客户端{}离线{}", chatInfo.getNoWxGm(), chatInfo.getCode());
                    }
                } else {
                    UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
                    updateImChatInfo.setCode(chatInfo.getCode());
                    updateImChatInfo.setStatus(MessageStatus.FAILURE.getCode());
                    updateImChatInfo.setErrorCode(e.getExceptionMap().get("code"));
                    updateImChatInfo.setErrorMessage(e.getExceptionMap().get("message"));
                    imChatInfoService.updateImChatInfoStatus(updateImChatInfo);
                    throw e;
                }
            } catch (TsfaServiceException e) {
                logger.error("发送聊天记录失败", e);
                UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
                updateImChatInfo.setCode(chatInfo.getCode());
                updateImChatInfo.setStatus(MessageStatus.FAILURE.getCode());
                if (StringUtils.isNotEmpty(e.getExceptionInfo())) {
                    updateImChatInfo.setErrorMessage(e.getExceptionInfo().length() > 40 ? e.getExceptionInfo().substring(0, 40) : e.getExceptionInfo());
                }
                imChatInfoService.updateImChatInfoStatus(updateImChatInfo);
                throw e;
            } catch (Exception e) {
                logger.error("发送聊天记录失败", e);
                UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
                updateImChatInfo.setCode(chatInfo.getCode());
                updateImChatInfo.setStatus(MessageStatus.FAILURE.getCode());
                imChatInfoService.updateImChatInfoStatus(updateImChatInfo);
                throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_SEND_ERROR, "发送聊天记录失败", e);
            }
        } else { // 客户发送给导购
            try {
                chatRequest.setReceiveFlag(SenderFlag.GM.getCode());
                IChatService basics = commonService.getHessianChatService(chatRequest.getMemberNoGm());
                basics.sendChatMessage(chatRequest);
                
            } catch (TsfaServiceException e) {
                logger.warn("向导购同步聊天记录失败", e.getExceptionInfo());
            } catch (Exception e) {
                logger.warn("向导购同步聊天记录失败", e);
            }
        }

        // 更新客户最新聊天动态
        if(StringUtils.isNotEmpty(chatInfo.getMemberNo()) && StringUtils.isNotEmpty(chatInfo.getMemberNoGm())) {
        	Integer unreadFlag = ReadFlag.NO.getCode().equals(chatInfo.getThirdReadFlag()) ? ReadFlag.YES.getCode() : ReadFlag.NO.getCode();
            UpdatePmChatBehavior chatBehavior = new UpdatePmChatBehavior(chatInfo.getMemberNo(),chatInfo.getMemberNoGm(), chatInfo.getNoWxGm(), new Date(), unreadFlag);
        	
            if(SenderFlag.ZK.getCode().equals(chatRequest.getSenderFlag())) {	// 客户发的，需回复
            	chatBehavior.setMemberChatTime(chatBehavior.getChatTime());
            	chatBehavior.setUnrespFlag(ReadFlag.YES.getCode());
            } else {
            	chatBehavior.setUnrespFlag(ReadFlag.NO.getCode());
            }
            pmChatBehaviorService.updateBehavior(chatBehavior);
        }
        logger.info("sendChat(SendImChatInfo) - end");
    }

    /**
     * 方法说明：保存聊天记录
     *
     * @param chat
     * @param guidMember
     * @param findChatRoomReturn
     * @return
     * @author 曾垂瑜 CreateDate: 2017年12月25日
     */
    private ImChatInfo saveChatInfo(SendImChatInfo chat, FindShopTerminalReturn findShopTerminalReturn, FindChatRoomReturn findChatRoomReturn) {
        // 为空则自动生成
        if (StringUtils.isEmpty(chat.getMsgId())) {
            chat.setMsgId(GUID.generateByUUID());
        }
        
        // 构建聊天记录
        ImChatInfo imChatInfo = new ImChatInfo();
        imChatInfo.setCode(chat.getMsgId());
        imChatInfo.setSenderFlag(chat.getSenderFlag());
        if (SenderFlag.GM.getCode().equals(chat.getSenderFlag())) { // 导购发送给客户
        	if (chat.getMsgSource() < MessageSource.SA.getCode()) {
        		imChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode()); // 从导购客户端或中控客户端发送消息,不用通过同步方式发送到导购客户端
        	} else {
        		imChatInfo.setSenderSyncStatus(SenderSyncStatus.NO.getCode()); // 从第三方（导购助手发送）发送消息,必须同步到导购客户端,让导购能看到聊天记录
        	}
    		imChatInfo.setThirdReadFlag(ReadFlag.YES.getCode()); // 导购发送，不管是由导购客户端还是第三方（导购助手发送），都默认为已读
        	
        } else { // 客户发送给导购
        	imChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode()); // 中控客户端发送消息,不用通过同步方式发送到导购客户端
        	imChatInfo.setThirdReadFlag(ReadFlag.NO.getCode()); // 第三方未读
        }
        imChatInfo.setType(chat.getType());
        imChatInfo.setStatus(MessageStatus.OFFLINE.getCode());// 未发送(离线)
        imChatInfo.setChatTime(chat.getChatTime() != null ? chat.getChatTime() : new Date());
        imChatInfo.setResourcesPath(chat.getResources());
        imChatInfo.setShareTitle(chat.getShareTitle());
        imChatInfo.setShareDes(chat.getShareDes());
        imChatInfo.setShareUrl(chat.getShareUrl());
        imChatInfo.setChatroomType(chat.getChatroomType());
        imChatInfo.setChatroomNoWx(chat.getChatroomNoWx());
        imChatInfo.setMsgSource(chat.getMsgSource());
        imChatInfo.setImei(chat.getImei());
        imChatInfo.setChatAssistantCode(chat.getChatAssistantCode());
        imChatInfo.setCreateDate(new Date());
        imChatInfo.setContent(chat.getContent());
        imChatInfo.setRemark3(chat.getChatAssistantCode()); // 导购助手编号
        imChatInfo.setRemark4(chat.getChatAssistantName()); // 导购助手名称

        if(ChatRoomType.PERSONAL.getCode().equals(imChatInfo.getChatroomType())) {	// 单聊
        	this.buildChatInfoByPersonal(chat, findShopTerminalReturn, imChatInfo);
        } else {	// 群聊
        	imChatInfo.setNoDisturb(findChatRoomReturn.getNoDisturb());
        	this.buildChatInfoByChatRoom(chat, findChatRoomReturn, imChatInfo);
        }
        
        // 判断敏感词
        if (SenderFlag.GM.getCode().intValue() == chat.getSenderFlag().intValue()) { // 导购客户端发送给客户
            // 导购手机客户端发送的文本或分享聊天记录才需要敏感词过滤（中控客户端已由微信做了过滤，在此就不重复过滤了）
            if ((ChatInfoType.TEXT.getCode().equals(chat.getType()) && SensitiveWordsUtils.contains(chat.getContent())) || (ChatInfoType.SHARE.getCode().equals(chat.getType()) && (SensitiveWordsUtils.contains(chat.getShareTitle()) || SensitiveWordsUtils.contains(chat.getShareDes())))) {
                // 保存到敏感词聊天记录表
                this.createChatInfoSensitive(imChatInfo);
                throw new TsfaServiceException(ErrorCode.INCLUDE_SENSITIVE_WORDS, "聊天记录包含敏感词");
            }
        }
        
        // 保存聊天记录
        imChatInfoDao.insertSelective(imChatInfo);
        logger.info("已保存聊天记录：{}", chat.getMsgId());
        return imChatInfo;
    }
    
    /**
     * 
     *
     * 方法说明：单聊时构建相关信息
     *
     * @param chat
     * @param guidMember
     * @param imChatInfo
     *
     * @author 曾垂瑜 CreateDate: 2018年9月27日
     *
     */
	private void buildChatInfoByPersonal(SendImChatInfo chat, FindShopTerminalReturn findShopTerminalReturn, ImChatInfo imChatInfo) {
		// 查询客户信息
        FindPersonMemberByChat findPersonMemberByChat = new FindPersonMemberByChat();
        findPersonMemberByChat.setMemberNo(chat.getMemberNo());
        findPersonMemberByChat.setNoWxGm(chat.getNoWxGm());
        FindPersonMemberByChatReturn findPersonMemberByChatReturn = personMemberImService.findPersonMemberByChat(findPersonMemberByChat);
        if (findPersonMemberByChatReturn == null) {
            throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_NOT_EXIST_ERROR, "客户信息不存在");
        }
        imChatInfo.setMemberNoGm(findPersonMemberByChatReturn.getMemberNoGm());
        imChatInfo.setMemberNameGm(findPersonMemberByChatReturn.getMemberNameGm());
        imChatInfo.setNoWxGm(chat.getNoWxGm());
        imChatInfo.setMemberNo(findPersonMemberByChatReturn.getMemberNo());
        imChatInfo.setMemberName(findPersonMemberByChatReturn.getMemberName());
        imChatInfo.setNoWx(findPersonMemberByChatReturn.getNoWx());
        imChatInfo.setAlias(findPersonMemberByChatReturn.getNoWxAlias());
        imChatInfo.setMerchantNo(findShopTerminalReturn.getMerchantNo());
        imChatInfo.setMerchantName(findShopTerminalReturn.getMerchantName());
	}
	
    /**
     * 
     *
     * 方法说明：群聊时构建相关信息
     *
     * @param chat
     * @param findChatRoomReturn
     * @param imChatInfo
     *
     * @author 曾垂瑜 CreateDate: 2018年9月27日
     *
     */
	private void buildChatInfoByChatRoom(SendImChatInfo chat, FindChatRoomReturn findChatRoomReturn, ImChatInfo imChatInfo) {
		imChatInfo.setMemberNo(findChatRoomReturn.getCode());	// 将群记录code写入到会员编号memberNo字段
		imChatInfo.setMemberName(findChatRoomReturn.getRoomNickName());
		imChatInfo.setMemberNoGm(findChatRoomReturn.getMemberNoGm());
		imChatInfo.setMemberNameGm(findChatRoomReturn.getMemberNameGm());
		imChatInfo.setNoWxGm(chat.getNoWxGm());
		imChatInfo.setNoWx(findChatRoomReturn.getChatRoomName());
        imChatInfo.setMerchantNo(findChatRoomReturn.getMerchantNo());
        imChatInfo.setMerchantName(findChatRoomReturn.getMerchantName());
        if(StringUtils.isEmpty(imChatInfo.getChatroomNoWx())) {		// 群聊时消息发送人微信
        	imChatInfo.setChatroomNoWx(findChatRoomReturn.getNoWxZk());
        }else {
        	imChatInfo.setChatroomNoWx(imChatInfo.getChatroomNoWx());
        }
        imChatInfo.setHeadUrl(findChatRoomReturn.getHeadUrl());
        imChatInfo.setRoomNickName(findChatRoomReturn.getRoomNickName());
        //获取群成员信息
		FindChatRoomMemberReturn chatRoomMemberReturn= chatRoomMemberService.findChatRoomMemberByNoWx(findChatRoomReturn.getCode(), imChatInfo.getChatroomNoWx());
		if(null !=chatRoomMemberReturn){
			imChatInfo.setMemberHeadUrl(chatRoomMemberReturn.getHeadUrl());
			imChatInfo.setMemberNickName(chatRoomMemberReturn.getNickName());
		}
	}

    /**
     * 方法说明：保存包含敏感词的聊天记录到敏感词聊天记录表
     *
     * @param addImChatInfo
     * @param guidMember
     * @param findPersonMemberByChatReturn
     * @author 曾垂瑜 CreateDate: 2018年1月8日
     */
    /*private void createChatInfoSensitiveBySys(final SendImChatInfo sendImChatInfo,
                                              final FindGuidMemberReturn guidMember, final FindPersonMemberByChatReturn findPersonMemberByChatReturn) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // 获取消息内包含的敏感词列表
                Set<String> sensitiveWordList = new HashSet<>();
                if (ChatInfoType.TEXT.getCode().equals(sendImChatInfo.getType())) { // 聊天文本内容
                    sensitiveWordList.addAll(SensitiveWordsUtils.getSensitiveWord(sendImChatInfo.getContent()));
                } else if (ChatInfoType.SHARE.getCode().equals(sendImChatInfo.getType())) {    // 分享
                    if (StringUtils.isNotEmpty(sendImChatInfo.getShareTitle())) { // 分享标题
                        sensitiveWordList.addAll(SensitiveWordsUtils.getSensitiveWord(sendImChatInfo.getShareTitle()));
                    }
                    if (StringUtils.isNotEmpty(sendImChatInfo.getShareDes())) { // 分享描述
                        sensitiveWordList.addAll(SensitiveWordsUtils.getSensitiveWord(sendImChatInfo.getShareDes()));
                    }
                }

                ImChatInfoSensitive chat = new ImChatInfoSensitive();
                chat.setCode(sendImChatInfo.getMsgId());
                chat.setMemberNoGm(findPersonMemberByChatReturn.getMemberNoGm());
                chat.setMemberNameGm(findPersonMemberByChatReturn.getMemberNameGm());
                chat.setNoWxGm(guidMember.getNoWx());
                chat.setMemberNo(findPersonMemberByChatReturn.getMemberNo());
                chat.setMemberName(findPersonMemberByChatReturn.getMemberName());
                chat.setNoWx(findPersonMemberByChatReturn.getNoWx());
                chat.setAlias(findPersonMemberByChatReturn.getNoWxAlias());
                chat.setShopNo(findPersonMemberByChatReturn.getShopNo());
                chat.setShopName(findPersonMemberByChatReturn.getShopName());
                chat.setMerchantNo(findPersonMemberByChatReturn.getMerchantNo());
                chat.setMerchantName(findPersonMemberByChatReturn.getMerchantName());
                chat.setType(sendImChatInfo.getType());
                chat.setChatTime(sendImChatInfo.getChatTime() != null ? sendImChatInfo.getChatTime() : new Date());
                chat.setResourcesPath(sendImChatInfo.getResources());
                chat.setShareTitle(sendImChatInfo.getShareTitle());
                chat.setShareDes(sendImChatInfo.getShareDes());
                chat.setShareUrl(sendImChatInfo.getShareUrl());
                chat.setChatroomType(sendImChatInfo.getChatroomType());
                chat.setChatroomNoWx(sendImChatInfo.getChatroomNoWx());
                chat.setMsgSource(sendImChatInfo.getMsgSource());
                chat.setCreateDate(new Date());
                chat.setContent(sendImChatInfo.getContent());
                chat.setChatAssistantCode(sendImChatInfo.getChatAssistantCode()); // 导购助手编号
                chat.setChatAssistantName(sendImChatInfo.getChatAssistantName()); // 导购助手名称
                String words = "";
                for (String word : sensitiveWordList) {
                    words += "," + word;
                }
                chat.setSensitiveWords(words.substring(1));
                try {
                    imChatInfoSensitiveDao.insertSelective(chat);
                } catch (Exception e) {
                    logger.error("保存包含敏感词的聊天记录到敏感词聊天记录表失败", e);
                }
            }
        });
    }*/

    /**
     * 向未认领客户发送IM聊天记录（没有客户信息，memberNo为空）
     * 保存到聊天记录表中，待客户被认领且生成客户信息后，再更新聊天记录中的客户信息，并同时同步给导购
     * 应用场景如：客户主动添加终端微信为好友后，系统自动向客户发送一条优惠券聊天记录
     */
    @Override
    public void sendChatByNonMember(SendImChatByNonMember chatByNonMember) {
        logger.info("sendChatByNonMember(SendImChatByNonMember chatByNonMember={})", chatByNonMember);

        AssertUtils.notNull(chatByNonMember);
        AssertUtils.notNullAndEmpty(chatByNonMember.getNoWxShop(), "终端微信不能为空");
        AssertUtils.notNullAndEmpty(chatByNonMember.getNoWx(), "客户微信不能为空");
        AssertUtils.notNullAndEmpty(chatByNonMember.getType(), "消息类型不能为空");
        AssertUtils.notNullAndEmpty(chatByNonMember.getMsgSource(), "消息来源不能为空");

        FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalNormal(chatByNonMember.getNoWxShop());
        if (shopTerminal == null) {
            logger.error("终端终端不存在:", chatByNonMember.getNoWxShop());
            throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.SHOP_TERMINAL_NOT_EXIST_ERROR, "终端终端不存在");
        }

        ImChatInfo chatInfo = null;
        try {
            // 为空则自动生成
            if (StringUtils.isEmpty(chatByNonMember.getMsgId())) {
                chatByNonMember.setMsgId(GUID.generateByUUID());
            }

            // 如果消息为重发，则检查数据库
            if (chatByNonMember.isResend()) {
                chatInfo = imChatInfoDao.selectByPrimaryKey(chatByNonMember.getMsgId());
                if (chatInfo != null) {
                    logger.info("消息重复发送", chatInfo);
                    // 发送成功
                    if (MessageStatus.SUCCESS.getCode().equals(chatInfo.getStatus())) {
                        logger.info("消息已发送成功{}", chatByNonMember.getMsgId());
                        logger.info("sendChat(SendImChatInfo) - end");
                        return;
                    } else if (SenderFlag.GM.getCode().equals(chatInfo.getSenderFlag())) { // 如果是导购发送的聊天记录,且状态不是发送成功,则重置chatTime
                        ImChatInfo update = new ImChatInfo();
                        update.setCode(chatByNonMember.getMsgId());
                        update.setChatTime(new Date());
                        imChatInfoDao.updateByPrimaryKeySelective(update);
                        chatInfo.setChatTime(update.getChatTime());
                    }
                } else { // 保存未认领客户聊天记录
                    chatInfo = this.saveImChatInfoByNonMember(chatByNonMember, shopTerminal);
                }
            } else { // 保存未认领客户聊天记录
                chatInfo = this.saveImChatInfoByNonMember(chatByNonMember, shopTerminal);
            }
        } catch (TsfaServiceException e) {
            logger.error("保存未认领客户的聊天记录失败", e);
            throw e;
        } catch (Exception e) {
            logger.error("保存未认领客户的聊天记录失败", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_SEND_ERROR, "发送未认领客户的聊天记录失败", e);
        }

        ICommonService basic =  commonService.getHessianCommonService(chatByNonMember.getNoWxShop());
        // 中控客户端登录才发送聊天记录
        if (basic.getZkTerminalStatus(chatByNonMember.getNoWxShop())) {
            ChatMessageRequest chatRequest = new ChatMessageRequest();
            chatRequest.setSenderFlag(chatInfo.getSenderFlag());
            chatRequest.setReceiveFlag(SenderFlag.ZK.getCode());
            chatRequest.setResend(chatByNonMember.isResend());
            chatRequest.setMsgId(chatInfo.getCode());
            chatRequest.setNoWxGm(chatInfo.getNoWxGm());
            chatRequest.setNoWx(chatInfo.getNoWx());
            chatRequest.setAlias(chatInfo.getAlias());
            chatRequest.setType(chatInfo.getType());
            chatRequest.setContent(chatInfo.getContent());
            chatRequest.setResources(chatInfo.getResourcesPath());
            chatRequest.setShareTitle(chatInfo.getShareTitle());
            chatRequest.setShareDes(chatInfo.getShareDes());
            chatRequest.setShareUrl(chatInfo.getShareUrl());
            chatRequest.setCreateTime(chatInfo.getChatTime().getTime());
            try { // 将聊天记录发送给未认领客户
            	 IChatService basics = null;
         		if(chatRequest.getReceiveFlag() == 1) {	// 导购接收
         			 basics = commonService.getHessianChatService(chatRequest.getMemberNoGm());
         		} else {	// 客户接收
         			 basics = commonService.getHessianChatService(chatRequest.getNoWxGm());
         		}
            	 basics.sendChatMessage(chatRequest);
                logger.info("已向未认领客户发送聊天记录{}", chatByNonMember.getMsgId());
            } catch (TsfaServiceException e) {
                logger.warn("向未认领客户发送聊天记录失败", e.getExceptionInfo());
            } catch (Exception e) {
                logger.warn("向未认领客户发送聊天记录失败", e);
            }
        } else {
            if (!chatByNonMember.isAllowZkOffline()) { // 不允许中控离线
                logger.error("导购客户端已离线，发送失败！");
                throw new TsfaServiceException(com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE, "中控客户端已离线，发送失败！");
            } else {
                logger.warn("终端微信{}所属终端未登录，不发送聊天记录：{}", chatByNonMember.getNoWxShop(), chatByNonMember.getMsgId());
            }
        }

        logger.info("sendChatByNonMember(SendImChatByNonMember) - end");
    }

    /**
     * 方法说明：保存未认领客户聊天记录
     *
     * @param chatByNonMember
     * @param shopTerminal
     * @return
     * @author 曾垂瑜 CreateDate: 2018年1月30日
     */
    private ImChatInfo saveImChatInfoByNonMember(SendImChatByNonMember chatByNonMember, FindShopTerminalReturn
            shopTerminal) {
        // 保存聊天记录
        ImChatInfo imChatInfo = new ImChatInfo();
        imChatInfo.setCode(chatByNonMember.getMsgId());
        imChatInfo.setNoWxGm(chatByNonMember.getNoWxShop());
        imChatInfo.setNoWx(chatByNonMember.getNoWx());
        imChatInfo.setAlias(chatByNonMember.getAlias());
//        imChatInfo.setShopNo(shopTerminal.getShopNo());
//        imChatInfo.setShopName(shopTerminal.getShopName());
        imChatInfo.setMerchantNo(shopTerminal.getMerchantNo());
        imChatInfo.setMerchantName(shopTerminal.getMerchantName());
        imChatInfo.setSenderFlag(SenderFlag.GM.getCode());
        if (chatByNonMember.getMsgSource() < MessageSource.SA.getCode()) {
            imChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode()); // 从导购客户端或中控客户端发送消息,不用通过同步方式发送到导购客户端
        } else {
            imChatInfo.setSenderSyncStatus(SenderSyncStatus.NO.getCode()); // 从第三方（导购助手发送）发送消息,必须同步到导购客户端,让导购能看到聊天记录
        }
        imChatInfo.setThirdReadFlag(ReadFlag.YES.getCode()); // 导购发送，不管是由导购客户端还是第三方（导购助手发送），都默认为已读
        imChatInfo.setType(chatByNonMember.getType());
        imChatInfo.setStatus(MessageStatus.OFFLINE.getCode());// 未发送(离线)
        imChatInfo.setChatTime(chatByNonMember.getChatTime() != null ? chatByNonMember.getChatTime() : new Date());
        imChatInfo.setResourcesPath(chatByNonMember.getResources());
        imChatInfo.setShareTitle(chatByNonMember.getShareTitle());
        imChatInfo.setShareDes(chatByNonMember.getShareDes());
        imChatInfo.setShareUrl(chatByNonMember.getShareUrl());
        imChatInfo.setChatroomType(1);	// 单聊
        imChatInfo.setMsgSource(chatByNonMember.getMsgSource());
        imChatInfo.setCreateDate(new Date());
        imChatInfo.setContent(chatByNonMember.getContent());
        imChatInfo.setRemark("未认领客户聊天记录");
        imChatInfoDao.insertSelective(imChatInfo);
        logger.info("已保存未认领客户的聊天记录：{}", chatByNonMember.getMsgId());

        return imChatInfo;
    }

    /**
     * 方法说明：向导购发送聊天记录 应用场景：给客户发送红包成功后，生成一条红包类型的聊天记录发送给导购
     *
     * @param sendChatToGm
     * @author 曾垂瑜 CreateDate: 2018年1月30日
     */
    @Override
    public void sendChatToGm(SendChatToGm sendChatToGm) {
        logger.info("sendChatToGm(SendChatToGm sendChatToGm={}) - start", sendChatToGm);

        AssertUtils.notNull(sendChatToGm);
        AssertUtils.notNull(sendChatToGm.getSenderFlag(), "发送人标识不能为空");
        AssertUtils.notNullAndEmpty(sendChatToGm.getMemberNoGm(), "导购编号不能为空");
        AssertUtils.notNullAndEmpty(sendChatToGm.getMemberNo(), "客户编号不能为空");
        AssertUtils.notNullAndEmpty(sendChatToGm.getType(), "消息类型不能为空");
        AssertUtils.notNullAndEmpty(sendChatToGm.getMsgSource(), "消息来源不能为空");
        AssertUtils.notNullAndEmpty(sendChatToGm.getNoWxGm(), "终端微信不能为空");

        // 查询导购信息
        FindGuidMember findGuidMember = new FindGuidMember();
        findGuidMember.setMemberNo(sendChatToGm.getMemberNoGm());
        FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);

        ImChatInfo chatInfo = null;
        try {
            // 如果消息为重发，则检查数据库
            if (sendChatToGm.isResend()) {
                chatInfo = imChatInfoDao.selectByPrimaryKey(sendChatToGm.getMsgId());
                if (chatInfo != null) {
                    logger.info("消息重复发送", chatInfo);
                    // 发送成功
                    if (MessageStatus.SUCCESS.getCode().equals(chatInfo.getStatus())) {
                        logger.info("消息已发送成功{}", sendChatToGm.getMsgId());
                        logger.info("sendChatToGm(SendChatToGm) - end");
                        return;
                    } else if (SenderFlag.GM.getCode().equals(chatInfo.getSenderFlag())) { // 如果是导购发送的聊天记录,且状态不是发送成功,则重置chatTime
                        ImChatInfo update = new ImChatInfo();
                        update.setCode(sendChatToGm.getMsgId());
                        update.setChatTime(new Date());
                        imChatInfoDao.updateByPrimaryKeySelective(update);
                        chatInfo.setChatTime(update.getChatTime());
                    }
                } else {
                    chatInfo = this.saveChatInfoToGm(sendChatToGm, guidMember);
                }
            } else {
                chatInfo = this.saveChatInfoToGm(sendChatToGm, guidMember);
            }
        } catch (TsfaServiceException e) {
            logger.error("保存聊天记录失败", e);
            throw e;
        } catch (Exception e) {
            logger.error("保存聊天记录失败", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_SEND_ERROR, "发送聊天记录失败", e);
        }

        ICommonService basic =  commonService.getHessianCommonService(chatInfo.getMemberNoGm());
        
        // 导购登录客户则发送聊天记录
        if (basic.getClientStatus(chatInfo.getMemberNoGm())) {
            ChatMessageRequest chatRequest = new ChatMessageRequest();
            chatRequest.setSenderFlag(chatInfo.getSenderFlag());
            chatRequest.setResend(sendChatToGm.isResend());
            chatRequest.setMsgId(chatInfo.getCode());
            chatRequest.setMemberNoGm(chatInfo.getMemberNoGm());
            chatRequest.setNoWxGm(chatInfo.getNoWxGm());
            chatRequest.setMemberNo(chatInfo.getMemberNo());
            chatRequest.setNoWx(chatInfo.getNoWx());
            chatRequest.setAlias(chatInfo.getAlias());
            chatRequest.setType(chatInfo.getType());
            chatRequest.setContent(chatInfo.getContent());
            chatRequest.setResources(chatInfo.getResourcesPath());
            chatRequest.setShareTitle(chatInfo.getShareTitle());
            chatRequest.setShareDes(chatInfo.getShareDes());
            chatRequest.setShareUrl(chatInfo.getShareUrl());
            chatRequest.setCreateTime(chatInfo.getChatTime().getTime());
            try {
                chatRequest.setReceiveFlag(SenderFlag.GM.getCode());
                
                IChatService basics  = commonService.getHessianChatService(chatRequest.getMemberNoGm());
                basics.sendChatMessage(chatRequest);
            } catch (TsfaServiceException e) {
                logger.warn("向导购发送聊天记录失败", e.getExceptionInfo());
            } catch (Exception e) {
                logger.warn("向导购发送聊天记录失败", e);
            }
        }

        // 更新客户最新聊天动态
        if(StringUtils.isNotEmpty(chatInfo.getMemberNo()) && StringUtils.isNotEmpty(chatInfo.getMemberNoGm())) {
        	Integer unreadFlag = ReadFlag.NO.getCode().equals(chatInfo.getThirdReadFlag()) ? ReadFlag.YES.getCode() : ReadFlag.NO.getCode();
            UpdatePmChatBehavior chatBehavior = new UpdatePmChatBehavior(chatInfo.getMemberNo(),chatInfo.getMemberNoGm(), chatInfo.getNoWxGm(), new Date(), unreadFlag);
            if(SenderFlag.ZK.getCode().equals(chatInfo.getSenderFlag())) {	// 客户发的，需回复
            	chatBehavior.setMemberChatTime(chatBehavior.getChatTime());
            	chatBehavior.setUnrespFlag(ReadFlag.YES.getCode());
            } else {
            	chatBehavior.setUnrespFlag(ReadFlag.NO.getCode());
            }
            pmChatBehaviorService.updateBehavior(chatBehavior);
        }

        logger.info("sendChatToGm(SendChatToGm) - end");
    }

    /**
     * 方法说明：保存聊天记录（发送给导购）
     *
     * @param sendChatToGm
     * @param guidMember
     * @return
     * @author 曾垂瑜 CreateDate: 2018年1月30日
     */
    private ImChatInfo saveChatInfoToGm(SendChatToGm sendChatToGm, FindGuidMemberReturn guidMember) {
        // 为空则自动生成
        if (StringUtils.isEmpty(sendChatToGm.getMsgId())) {
            sendChatToGm.setMsgId(GUID.generateByUUID());
        }

        // 查询客户信息
        FindPersonMemberByChat findPersonMemberByChat = new FindPersonMemberByChat();
        findPersonMemberByChat.setMemberNo(sendChatToGm.getMemberNo());
        findPersonMemberByChat.setMemberNoGm(sendChatToGm.getMemberNoGm());
        FindPersonMemberByChatReturn findPersonMemberByChatReturn = personMemberImService.findPersonMemberByChat(findPersonMemberByChat);
        if (findPersonMemberByChatReturn == null) {
            throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_NOT_EXIST_ERROR, "客户信息不存在");
        }

        // 保存聊天记录
        ImChatInfo imChatInfo = new ImChatInfo();
        imChatInfo.setCode(sendChatToGm.getMsgId());
        imChatInfo.setMemberNoGm(guidMember.getMemberNo());
        imChatInfo.setMemberNameGm(guidMember.getMemberName());
        imChatInfo.setNoWxGm(sendChatToGm.getNoWxGm());
        imChatInfo.setMemberNo(findPersonMemberByChatReturn.getMemberNo());
        imChatInfo.setMemberName(findPersonMemberByChatReturn.getMemberName());
        imChatInfo.setNoWx(findPersonMemberByChatReturn.getNoWx());
        imChatInfo.setAlias(findPersonMemberByChatReturn.getNoWxAlias());
        imChatInfo.setMerchantNo(guidMember.getMerchantNo());
        imChatInfo.setMerchantName(guidMember.getMerchantName());
        imChatInfo.setSenderFlag(sendChatToGm.getSenderFlag());
        imChatInfo.setChatTime(sendChatToGm.getChatTime() != null ? sendChatToGm.getChatTime() : new Date());
        if (SenderFlag.ZK.getCode().equals(sendChatToGm.getSenderFlag())) { // 客户发送
            imChatInfo.setThirdReadFlag(ReadFlag.NO.getCode()); // 第三方未读
            imChatInfo.setStatus(MessageStatus.OFFLINE.getCode());// 已发送(离线)
        } else {
            imChatInfo.setThirdReadFlag(ReadFlag.YES.getCode()); // 导购发送，不管是由导购客户端还是第三方（导购助手发送），都默认为已读
            imChatInfo.setStatus(MessageStatus.SUCCESS.getCode());// 发送成功
            imChatInfo.setReceivedTime(imChatInfo.getChatTime());
        }
        imChatInfo.setSenderSyncStatus(SenderSyncStatus.NO.getCode());
        imChatInfo.setType(sendChatToGm.getType());
        imChatInfo.setResourcesPath(sendChatToGm.getResources());
        imChatInfo.setShareTitle(sendChatToGm.getShareTitle());
        imChatInfo.setShareDes(sendChatToGm.getShareDes());
        imChatInfo.setShareUrl(sendChatToGm.getShareUrl());
        imChatInfo.setChatroomType(1);	// 单聊
        imChatInfo.setMsgSource(sendChatToGm.getMsgSource());
        imChatInfo.setImei(sendChatToGm.getImei());
        imChatInfo.setCreateDate(new Date());
        imChatInfo.setContent(sendChatToGm.getContent());
        imChatInfo.setRemark3(sendChatToGm.getChatAssistantCode()); // 导购助手编号
        imChatInfo.setRemark4(sendChatToGm.getChatAssistantName()); // 导购助手名称
        imChatInfoDao.insertSelective(imChatInfo);
        logger.info("已保存聊天记录：{}", sendChatToGm.getMsgId());
        return imChatInfo;
    }

    /**
     * 方法说明：查找导购与客户的最后一条聊天记录
     *
     * @param findAutoAnswerChatInfo
     * @author 彭俊霖 CreateDate: 2018年01月30日
     */
    @Override
    public FindAutoAnswerChatInfoReturn getLastChatInfo(FindAutoAnswerChatInfo findAutoAnswerChatInfo) {
        logger.debug("getLastChatInfo(FindAutoAnswerChatInfo findAutoAnswerChatInfo={}) - start", findAutoAnswerChatInfo); 

        AssertUtils.notNull(findAutoAnswerChatInfo);
        AssertUtils.notNullAndEmpty(findAutoAnswerChatInfo.getMemberNo(), "客户编号不能为空");
        AssertUtils.notNullAndEmpty(findAutoAnswerChatInfo.getMemberNoGm(), "导购编号不能为空");
        FindAutoAnswerChatInfoReturn chatInfo = new FindAutoAnswerChatInfoReturn();
        try {
            chatInfo = imChatInfoDao.getLastChatInfo(findAutoAnswerChatInfo);
        } catch (Exception e) {
            logger.error("查找导购与客户的最后一条聊天记录失败", e);
            throw new TsfaServiceException(ErrorCode.IM_LAST_MATCH_CHAT_INFO_FIND_ERROR, "查找导购与客户的最后一条聊天记录失败", e);
        }

        logger.debug("getLastChatInfo(FindAutoAnswerChatInfo) - end"); 
        return chatInfo;
    }

	@Override
	public int findImChatInfoByMecCount(Map<String, Object> paramMap)
			throws TsfaServiceException {
		logger.debug("findImChatInfoByMecCount(Map<String, Object> paramMap)={}) - start", paramMap); 
        AssertUtils.notNull(paramMap);
        AssertUtils.notNullAndEmpty(paramMap.get("merchantNo"), "商户号不能为空");
        AssertUtils.notNullAndEmpty(paramMap.get("noWxGm"), "导购微信号不能为空");
        AssertUtils.notNullAndEmpty(paramMap.get("noWx"), "客户微信号不能为空");
        int count = 0;
        try {
            count = imChatInfoDao.findImChatInfoByMecCount(paramMap);
        } catch (Exception e) {
            logger.error("IM聊天记录信息不存在错误", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_PAGE_ERROR, "IM聊天记录信息不存在错误.！", e);
        }
        logger.debug("findImChatInfoPageCount(FindImChatInfoPage) - end - return value={}", count); 
        return count;
	}
	
	@Override
	public List<FindMemberChatCountReturn> stsMemberChatCount(FindMemberChatCount findMemberChatCount) {
		logger.debug("stsMemberChatCount(FindMemberChatCount findMemberChatCount={}) - start", findMemberChatCount); 

        AssertUtils.notNull(findMemberChatCount);
        List<FindMemberChatCountReturn> list = null;
        try {
            list = imChatInfoDao.stsMemberChatCount(findMemberChatCount);
        } catch (Exception e) {
            logger.error("统计客户与导购的聊天记录数失败", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "统计客户与导购的聊天记录数失败", e);
        }

        logger.debug("stsMemberChatCount(FindMemberChatCount) - end - return value={}", list != null ? list.size() : 0); 
        return list;
	}
    @Override
    public int findChatTimesByGmAndMemberNo(FindChatTimesByGmAndMemberNo findChatTimesByGmAndMemberNo) {
        logger.debug("findChatTimesByGmAndMemberNo(FindChatTimesByGmAndMemberNo findChatTimesByGmAndMemberNo={}) - start", findChatTimesByGmAndMemberNo); 

        AssertUtils.notNull(findChatTimesByGmAndMemberNo);
        AssertUtils.notNullAndEmpty(findChatTimesByGmAndMemberNo.getMemberNo(), "客户编号不能为空");
        AssertUtils.notNullAndEmpty(findChatTimesByGmAndMemberNo.getMemberNoGm(), "导购编号不能为空");
        
        int count = 0;
        try {
            count = imChatInfoDao.findChatTimesByGmAndMemberNo(findChatTimesByGmAndMemberNo);
        } catch (Exception e) {
            logger.error("统计客户与导购的聊天记录数错误", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_PAGE_ERROR, "统计客户与导购的聊天记录数错误.！", e);
        }

        logger.debug("findChatTimesByGmAndMemberNo(FindChatTimesByGmAndMemberNo) - end - return value={}", count); 
        return count;
    }
    
    /**
	 * 
	 *
	 * 方法说明： 请求撤销聊天记录
	 *
	 * @param code
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月30日
	 *
	 */
	@Override
	public void toCancelChatInfo(String code) {
		logger.debug("toCancelChatInfo(String code={}) - start", code); 

        AssertUtils.notNullAndEmpty(code, "消息ID不能为空");
        
        ImChatInfo imChatInfo = null;
        try {
        	imChatInfo = imChatInfoDao.selectByPrimaryKey(code);
        } catch (Exception e) {
            logger.error("查询聊天记录错误", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "查询聊天记录错误.！", e);
        }
        if(imChatInfo == null) {
        	logger.error("聊天记录不存在");
        	throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_NOT_EXIST_ERROR, "聊天记录不存在");
        }
        if(!SenderFlag.GM.getCode().equals(imChatInfo.getSenderFlag())) {
        	logger.error("只能撤回自己发送的聊天记录{}", code);
        	throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_CANCEL_ERROR, "只能撤回自己发送的信息");
        }
        // 超过两分钟不能撤回
        if(System.currentTimeMillis() - imChatInfo.getChatTime().getTime() > 120000) {
        	logger.error("超过两分钟消息不能撤回：{}", imChatInfo.getChatTime());
        	throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_CANCEL_TIMEOUT_ERROR, "发送时间超过2分钟的消息，不能撤回。");
        }
        
        // 请求中控撤销聊天记录
        CancelChatMessage cancelChatMessage = new CancelChatMessage();
        cancelChatMessage.setNoWxShop(imChatInfo.getNoWxGm());
        cancelChatMessage.setMsgId(imChatInfo.getCode());
        
        IChatService basics = commonService.getHessianChatService(cancelChatMessage.getNoWxShop());
        basics.sendCancelChatMessage(cancelChatMessage);
        logger.info("已向中控请求撤回聊天记录：{}", cancelChatMessage);

        logger.debug("toCancelChatInfo(String) - end"); 
	}
	
	
	/**
	 * 同步微信号历史聊天记录
	 * @param code
	 */
	@Override
	public void syncNoWxGmRequest(String noWxGm,String chatTime) {
		logger.debug("syncNoWxGmRequest(String noWxGm={}) - start", noWxGm); 

		AssertUtils.notNullAndEmpty(noWxGm, "noWxGm不能为空");
		

		// 获取同步锁（前缀 + 商户号 + 微信号），检查终端微信是否正在同步通讯录
		final String lockName = KeyConstant.MEMBER_SYNC_CACHE_PREFIX  + noWxGm;
		String lockValue = null;
		
		try {
			lockValue = redisLock.getLockNoWait(lockName);
		} catch(Exception e) {
			logger.error("终端微信{}正在同步历史聊天记录", noWxGm);
			throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_TEMP_SYS_ERROR, "终端微信正在同步历史聊天记录");
		}
		logger.info("请求同步-得到同步锁：{} - {}", lockName, lockValue);
		
		// 向中控系统发送同步通讯录请求
		try {
			SyncWxChatDataMessage syncWxChatDataMessage = new SyncWxChatDataMessage();
			syncWxChatDataMessage.setNoWxGm(noWxGm);
			syncWxChatDataMessage.setChatTime(chatTime);
			IContactsService basic = commonService.getHessianContactsService(noWxGm);
			basic.sendSyncWxChatDataMessage(syncWxChatDataMessage);
		} catch(TsfaServiceException e) {
			logger.error("向中控系统发送同步历史聊天记录请求失败", e);
			throw e;
		} catch(Exception e) {
			logger.error("向中控系统发送同步历史聊天记录请求失败", e);
			throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_TEMP_SYS_ERROR, "向中控系统发送同步历史聊天记录请求失败");
		} finally {		// 不管发送请求成功与否，保证锁能释放
			// 释放锁
			logger.info("请求同步-释放同步锁：{} - {}", lockName, lockValue);
			redisLock.releaseLock(lockName, lockValue);
		}
		
		logger.debug("syncNoWxGmRequest(String) - end"); 
	}
	
	@Override
	public AddImChatInfoReturn addImOldChatInfo(ImChatInfo imChatInfo) throws TsfaServiceException {
		imChatInfoDao.insertSelective(imChatInfo);
		return null;
	}

	@Override
	public IPage<FindHistoryChatInfo> findHistoryChatInfoPage(FindImChatInfoPage findHistoryChatInfo){
		 logger.debug("findHistoryChatInfoPage(FindHistoryChatInfo findHistoryChatInfo={}) - start", findHistoryChatInfo); 

	        AssertUtils.notNull(findHistoryChatInfo);
	        AssertUtils.notNullAndEmpty(findHistoryChatInfo.getMemberNo(), "客户编号不能为空");
	        AssertUtils.notNullAndEmpty(findHistoryChatInfo.getNoWxGm(), "终端微信不能为空");

	        List<FindHistoryChatInfo> returnList = new ArrayList<FindHistoryChatInfo>();
	        int count = 0;
	        try {
//	        	findHistoryChatInfo.setStatus("2");       //发送成功
	        	findHistoryChatInfo.setLeStatus(Integer.valueOf(MessageStatus.SUCCESS.getCode()));
	        	findHistoryChatInfo.setMemberNoGm(null);
	            count = imChatInfoDao.findImChatInfoPageCount(findHistoryChatInfo);
	            if (count > 0) {
	            	List<FindImChatInfoPageReturn>  list = imChatInfoDao.findImChatInfoPage(findHistoryChatInfo);
	            	FindHistoryChatInfo fChatInfo = null;
	            	//转换对象
	            	for(FindImChatInfoPageReturn chat : list) {
	            		fChatInfo = new FindHistoryChatInfo();
	            		fChatInfo.setMsgId(chat.getCode());
	            		fChatInfo.setContent(chat.getContent());
	            		fChatInfo.setMemberNo(chat.getMemberNo());
	            		fChatInfo.setCreateTime(chat.getChatTime().getTime());
	            		fChatInfo.setShareTitle(chat.getShareTitle());
	            		fChatInfo.setMemberNickName("");
	            		fChatInfo.setMemberHeadUrl("");
	            		fChatInfo.setShareUrl(chat.getShareUrl());
	            		fChatInfo.setSenderFlag(chat.getSenderFlag());
	            		fChatInfo.setShareDes(chat.getShareDes());
	            		fChatInfo.setResources(chat.getResourcesPath());
	            		fChatInfo.setAlias(chat.getNoWx());
	            		fChatInfo.setType(chat.getType());
	            		fChatInfo.setResend(false);
	            		fChatInfo.setBigImg(chat.getBigImg());
	            		fChatInfo.setMidImg(chat.getMidImg());
	            		returnList.add(fChatInfo);
	            		
	            	}
	            }
	        } catch (Exception e) {
	            logger.error("查询客户聊天记录（移动端历史记录）错误", e);
	            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_PAGE_ERROR, "查询客户聊天记录（移动端历史记录）错误！", e);
	        }
	        Page<FindHistoryChatInfo> returnPage = new Page<FindHistoryChatInfo>(returnList, count, findHistoryChatInfo);

	        logger.debug("findHistoryChatInfoPage(findHistoryChatInfoPage) - end - return value={}", returnPage); 
	        return returnPage;
	}
	
	@Override
	public int findUnreadPersonCount(FindUnreadCountByTerminal findUnreadCountByTerminal) throws TsfaServiceException {
		 AssertUtils.notNull(findUnreadCountByTerminal);
	        AssertUtils.notNullAndEmpty(findUnreadCountByTerminal.getMerchantNo(), "商户编号不能为空");
	        AssertUtils.notNullAndEmpty(findUnreadCountByTerminal.getNoWxList(), "微信号不能为空");
        int count = 0;
	        try {
	        	count = imChatInfoDao.findUnreadPersonCount(findUnreadCountByTerminal);
	        } catch (Exception e) {
	            logger.error("终端微信（终端）未读聊天记录数统计（导购助手）错误！", e);
	            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "终端微信（终端）未读聊天记录数统计（导购助手）错误！", e);
	        }
	        logger.debug("findUnreadPersonCount(FindUnreadCountByTerminal) - end - return value={}", count); 
	        return count;
	}

	/**
     * 请求下载文件
     * @param msgId
     * @param content
     * @param findFlag
     */
    @Override
    public void requestZkUploadChatFile(String msgId, String content, int findFlag) {

        logger.debug("requestZkUploadChatFile(String msgId={}, content={}, findFlag={}) - start", msgId, content, findFlag); //$NON-NLS-1$

        AssertUtils.notNullAndEmpty(msgId, "消息ID不能为空");
        AssertUtils.notNullAndEmpty(content, "content不能为空");

        ImChatInfo imChatInfo = null;
        try {
            imChatInfo = imChatInfoDao.selectByPrimaryKey(msgId);
        } catch (Exception e) {
            logger.error("查询聊天记录错误", e);
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "查询聊天记录错误.！", e);
        }
        if(imChatInfo == null) {
            logger.error("聊天记录不存在");
            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_NOT_EXIST_ERROR, "聊天记录不存在");
        }

        if (StringUtils.isEmpty(imChatInfo.getResourcesPath())){
            UploadChatFileMessage uploadChatFileMessage = new UploadChatFileMessage();
            uploadChatFileMessage.setMsgId(msgId);
            uploadChatFileMessage.setContent(content);
            uploadChatFileMessage.setFindFlag(findFlag);
            uploadChatFileMessage.setNoWxShop(imChatInfo.getNoWxGm());

            IChatService basics = commonService.getHessianChatService(uploadChatFileMessage.getNoWxShop());
            basics.requestZkUploadChatFile(uploadChatFileMessage);

            logger.info("已向中控请求下载文件：{}", uploadChatFileMessage);
        }
        logger.debug("requestZkUploadChatFile(String) - end"); //$NON-NLS-1$
    }

    @Override
    public void requestZkUploadChatVideo(String msgId, String content, int findFlag) {
        FindImChatInfo findImChatInfo = new FindImChatInfo();
        findImChatInfo.setCode(msgId);
        FindImChatInfoReturn findImChatInfoReturn = this.findImChatInfo(findImChatInfo);
        if(StringUtils.isEmpty(findImChatInfoReturn.getResourcesPath())) {	// 请求中控端上传视频
            UploadChatVideoMessage uploadChatVideoMessage = new UploadChatVideoMessage();
            uploadChatVideoMessage.setNoWxShop(findImChatInfoReturn.getNoWxGm());
            uploadChatVideoMessage.setMsgId(findImChatInfoReturn.getCode());
            uploadChatVideoMessage.setContent(findImChatInfoReturn.getContent());
            uploadChatVideoMessage.setFindFlag(findFlag);

            IChatService basic = commonService.getHessianChatService(uploadChatVideoMessage.getNoWxShop());
            basic.sendUploadChatVideoMessage(uploadChatVideoMessage);
            logger.debug("已向中控请求上传视频聊天记录视频文件");
        }
        logger.debug("requestZkUploadChatVideo(String) - end"); //$NON-NLS-1$
    }
	
	@Override
	public void delete(UpdateImChatInfo updateImChatInfo) {
		imChatInfoDao.delete(updateImChatInfo);
		
	}
	
	
	@Override
	public void deleteTemp(String merchantNo, String noWx) {
		imChatInfoTempDao.delete(merchantNo,noWx);
		
	}
	
	@Override
	public IPage<FindHistoryChatInfo> findLastHistoryChatInfoPage(FindImChatInfoPage findHistoryChatInfo) {
		 logger.debug("findHistoryChatInfoPage(FindHistoryChatInfo findHistoryChatInfo={}) - start", findHistoryChatInfo); 

	        AssertUtils.notNull(findHistoryChatInfo);
	        AssertUtils.notNullAndEmpty(findHistoryChatInfo.getMerchantNo(), "商户编号不能为空");

	        List<FindHistoryChatInfo> returnList = new ArrayList<FindHistoryChatInfo>();
	        int count = 0;
	        
        	//0.手机查询客户，则先检索客户
        	if(StringUtils.isNotEmpty(findHistoryChatInfo.getMobile())) {
        		FindPersonMemberBase personMemberBase = new FindPersonMemberBase();
            	personMemberBase.setMobile(findHistoryChatInfo.getMobile());
            	List<PersonMemberBase> pbs = personMemberBaseService.findMemberBaseByMemberNos(personMemberBase);
            	if(pbs==null||pbs.isEmpty()) {
            		throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_ERROR, "手机号未检索到客户");
            	}
            	List<String> memberNos=new ArrayList<>();
            	for (PersonMemberBase base : pbs) {
            		memberNos.add(base.getMemberNo());
				}
            	findHistoryChatInfo.setMemberNos(memberNos);
        	}
	        try {	
//	        	findHistoryChatInfo.setStatus("2");       //发送成功
//	        	findHistoryChatInfo.setMemberNoGm(null);
	            count = imChatInfoDao.findLastImChatInfoPageCount(findHistoryChatInfo);
	            if (count > 0) {
	            	List<String> memberNos=new ArrayList<>();//客户编号。用于查询客户头像等信息
	            	List<String> noWxGms=new ArrayList<>();//終端微信。用于查询終端头像等信息
	            	
	            	List<FindImChatInfoPageReturn>  list = imChatInfoDao.findLastImChatInfoPage(findHistoryChatInfo);
	            	FindHistoryChatInfo fChatInfo = null;
	            	//转换对象
	            	for(FindImChatInfoPageReturn chat : list) {
	            		memberNos.add(chat.getMemberNo());//客户编号
	            		noWxGms.add(chat.getNoWxGm());//终端WX
	            		
	            		fChatInfo = new FindHistoryChatInfo();
	            		fChatInfo.setMsgId(chat.getCode());
	            		fChatInfo.setContent(chat.getContent());
	            		fChatInfo.setMemberNo(chat.getMemberNo());
	            		fChatInfo.setCreateTime(chat.getChatTime().getTime());
	            		fChatInfo.setShareTitle(chat.getShareTitle());
//	            		fChatInfo.setMemberNickName("");
//	            		fChatInfo.setMemberHeadUrl("");
	            		fChatInfo.setShareUrl(chat.getShareUrl());
	            		fChatInfo.setSenderFlag(chat.getSenderFlag());
	            		fChatInfo.setShareDes(chat.getShareDes());
	            		fChatInfo.setResources(chat.getResourcesPath());
	            		fChatInfo.setAlias(chat.getAlias());
	            		fChatInfo.setNoWx(chat.getNoWx());
	            		fChatInfo.setType(chat.getType());
	            		fChatInfo.setResend(false);
	            		
	            		fChatInfo.setMemberName(chat.getMemberName());
	            		fChatInfo.setMemberNameGm(chat.getMemberNameGm());
	            		fChatInfo.setMemberNoGm(chat.getMemberNoGm());
	            		fChatInfo.setNoWxGm(chat.getNoWxGm());//终端微信
	            		returnList.add(fChatInfo);
	            	}
	            	//2.客户手机号 微信昵称 头像 
	            	FindPersonMemberBase personMemberBase = new FindPersonMemberBase();
	            	personMemberBase.setMemberNos(memberNos);
	            	List<PersonMemberBase> pbs = personMemberBaseService.findMemberBaseByMemberNos(personMemberBase);
	            	Map<String, PersonMemberBase> baseMap=new HashMap<>();
	            	for (PersonMemberBase base : pbs) {
	            		baseMap.put(base.getMemberNo(), base);
					}
	            	//3.终端微信头像
	            	FindShopTerminal findShopTerminal=new FindShopTerminal();
	            	findShopTerminal.setNoWxList(noWxGms);
	            	findShopTerminal.setMerchantNo(findHistoryChatInfo.getMerchantNo());
	            	List<FindShopTerminalReturn> sts = shopTerminalService.findShopTerminalSelect(findShopTerminal);
	            	Map<String, FindShopTerminalReturn> terMap=new HashMap<>();
	            	for (FindShopTerminalReturn base : sts) {
	            		terMap.put(base.getNoWx(), base);
					}
	            	//4.将客户微信和终端微信信息组装到返回信息中
	            	for(FindHistoryChatInfo chat : returnList) {
	            		PersonMemberBase base=baseMap.get(chat.getMemberNo());
	            		if(base!=null) {
		            		chat.setMobile(base.getMobile());
		            		chat.setMemberNickName(base.getNickNameWx());
		            		chat.setMemberHeadUrl(base.getHeadAddress());
	            		}
	            		FindShopTerminalReturn ter=terMap.get(chat.getNoWxGm());
	            		if(ter!=null) {
	            			chat.setTerminalHeadurl(ter.getHeadurl());
	            		}
	            	}//4 end
	            	
	            }
	        } catch (Exception e) {
	            logger.error("查询客户聊天记录（移动端历史记录）错误", e);
	            throw new TsfaServiceException(ErrorCode.IM_CHAT_INFO_FIND_PAGE_ERROR, "查询客户聊天记录（移动端历史记录）错误！", e);
	        }
	        Page<FindHistoryChatInfo> returnPage = new Page<FindHistoryChatInfo>(returnList, count, findHistoryChatInfo);

	        logger.debug("findHistoryChatInfoPage(findHistoryChatInfoPage) - end - return value={}", returnPage); 
	        return returnPage;
	}
	
	@Override
	public int selectChatCount(Map map) throws TsfaServiceException {
		int count = imChatInfoDao.selectChatCount(map);
		return count;
	}
	
	@Override
	public int findChatCount(Map mapChat) {
		int count = imChatInfoDao.findChatCount(mapChat);
		return count;
	}
	
	
}
