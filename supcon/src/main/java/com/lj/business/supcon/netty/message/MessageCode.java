/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message;

import java.util.HashMap;
import java.util.Map;

import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.exception.IllegalMessageException;
import com.lj.business.supcon.netty.message.account.FindAccountBalanceRequest;
import com.lj.business.supcon.netty.message.account.FindAccountBalanceResponse;
import com.lj.business.supcon.netty.message.chat.CancelChatInfoRequest;
import com.lj.business.supcon.netty.message.chat.CancelChatInfoResponse;
import com.lj.business.supcon.netty.message.chat.ChatInfoReadNumRequest;
import com.lj.business.supcon.netty.message.chat.ChatInfoRequest;
import com.lj.business.supcon.netty.message.chat.ChatInfoResponse;
import com.lj.business.supcon.netty.message.chat.FindChatImageRequest;
import com.lj.business.supcon.netty.message.chat.FindChatImageResponse;
import com.lj.business.supcon.netty.message.chat.MemberCancelChatInfoResponse;
import com.lj.business.supcon.netty.message.chat.OfflineChatInfo;
import com.lj.business.supcon.netty.message.chat.UploadChatFileRequest;
import com.lj.business.supcon.netty.message.chat.UploadChatFileResponse;
import com.lj.business.supcon.netty.message.chat.UploadChatVideoRequest;
import com.lj.business.supcon.netty.message.chat.UploadChatVideoResponse;
import com.lj.business.supcon.netty.message.chatroom.AddChatRoomRequest;
import com.lj.business.supcon.netty.message.chatroom.AddChatRoomResponse;
import com.lj.business.supcon.netty.message.chatroom.CreateChatRoomRequest;
import com.lj.business.supcon.netty.message.chatroom.CreateChatRoomResponse;
import com.lj.business.supcon.netty.message.chatroom.DelChatRoomRequest;
import com.lj.business.supcon.netty.message.chatroom.DelChatRoomResponse;
import com.lj.business.supcon.netty.message.chatroom.DismissChatRoomRequest;
import com.lj.business.supcon.netty.message.chatroom.DismissChatRoomResponse;
import com.lj.business.supcon.netty.message.chatroom.GetChatRoomQRCodeRequest;
import com.lj.business.supcon.netty.message.chatroom.GetChatRoomQRCodeResponse;
import com.lj.business.supcon.netty.message.chatroom.SyncChatRoomRequest;
import com.lj.business.supcon.netty.message.common.CommonResponse;
import com.lj.business.supcon.netty.message.common.HeartBeatRequest;
import com.lj.business.supcon.netty.message.common.NotifyMessage;
import com.lj.business.supcon.netty.message.common.SendSetUpUserToGm;
import com.lj.business.supcon.netty.message.common.SendVersionInfoToZk;
import com.lj.business.supcon.netty.message.common.TerminalBatteryNotify;
import com.lj.business.supcon.netty.message.common.TerminalLogFilesUpload;
import com.lj.business.supcon.netty.message.contacts.AddFriendByPhoneRequest;
import com.lj.business.supcon.netty.message.contacts.AddFriendResult;
import com.lj.business.supcon.netty.message.contacts.AddFriendResultResponse;
import com.lj.business.supcon.netty.message.contacts.FindWxInfoRequest;
import com.lj.business.supcon.netty.message.contacts.FindWxInfoReturn;
import com.lj.business.supcon.netty.message.contacts.ForcedLogout;
import com.lj.business.supcon.netty.message.contacts.MemberClaimNotify;
import com.lj.business.supcon.netty.message.contacts.MemberClaimRequest;
import com.lj.business.supcon.netty.message.contacts.MemberClaimResponse;
import com.lj.business.supcon.netty.message.contacts.MemberClaimedNotify;
import com.lj.business.supcon.netty.message.contacts.ScanAddFriendRequest;
import com.lj.business.supcon.netty.message.contacts.StartAddFriendByPhoneResponse;
import com.lj.business.supcon.netty.message.contacts.SyncShopWxFriendsRequest;
import com.lj.business.supcon.netty.message.contacts.SyncWxChatDataRequest;
import com.lj.business.supcon.netty.message.contacts.SyncWxInfoRequest;
import com.lj.business.supcon.netty.message.contacts.SyncWxInfoResponse;
import com.lj.business.supcon.netty.message.friends.DeleteFriends;
import com.lj.business.supcon.netty.message.friends.DeleteFriendsResult;
import com.lj.business.supcon.netty.message.friends.SendFriendsComment;
import com.lj.business.supcon.netty.message.friends.SendFriendsCommentResult;
import com.lj.business.supcon.netty.message.friends.SendFriendsInfo;
import com.lj.business.supcon.netty.message.friends.SendFriendsInfoResult;
import com.lj.business.supcon.netty.message.friends.SendFriendsLike;
import com.lj.business.supcon.netty.message.friends.SendFriendsLikeResult;
import com.lj.business.supcon.netty.message.friends.SendFriendsPicDown;
import com.lj.business.supcon.netty.message.friends.SendFriendsPicDownResult;
import com.lj.business.supcon.netty.message.friends.SendFriendsPicResultNotify;
import com.lj.business.supcon.netty.message.friends.UploadFriendsFlagCommand;
import com.lj.business.supcon.netty.message.login.LoginRequest;
import com.lj.business.supcon.netty.message.login.LoginResult;
import com.lj.business.supcon.netty.message.redpacket.FindRedpacketRequest;
import com.lj.business.supcon.netty.message.redpacket.FindRedpacketResponse;
import com.lj.business.supcon.netty.message.redpacket.RedpacketReceived;
import com.lj.business.supcon.netty.message.redpacket.RedpacketSendBack;
import com.lj.business.supcon.netty.message.redpacket.SendRedpacketRequest;
import com.lj.business.supcon.netty.message.redpacket.SendRedpacketResult;
import com.lj.business.supcon.netty.message.sign.SignCommand;
import com.lj.business.supcon.netty.message.sign.SignRequest;
import com.lj.business.supcon.netty.message.sign.SignResponse;
import com.lj.business.supcon.netty.service.AddFriendResultService;
import com.lj.business.supcon.netty.service.CommonService;
import com.lj.business.supcon.netty.service.FindWxInfoReturnService;
import com.lj.business.supcon.netty.service.FindWxInfoService;
import com.lj.business.supcon.netty.service.HeartBeatService;
import com.lj.business.supcon.netty.service.LoginService;
import com.lj.business.supcon.netty.service.MemberClaimService;
import com.lj.business.supcon.netty.service.ScanAddFriendService;
import com.lj.business.supcon.netty.service.StartAddFriendByPhoneResponseService;
import com.lj.business.supcon.netty.service.SyncWxInfoRequestService;
import com.lj.business.supcon.netty.service.SyncWxInfoResponseService;
import com.lj.business.supcon.netty.service.TerminalBatteryNotifyService;
import com.lj.business.supcon.netty.service.ZkSignService;
import com.lj.business.supcon.netty.service.chat.CancelChatInfoRequestService;
import com.lj.business.supcon.netty.service.chat.CancelChatInfoResponseService;
import com.lj.business.supcon.netty.service.chat.ChatInfoReadNumRequestService;
import com.lj.business.supcon.netty.service.chat.ChatInfoResponseService;
import com.lj.business.supcon.netty.service.chat.FindChatImageRequestService;
import com.lj.business.supcon.netty.service.chat.FindChatImageResponseService;
import com.lj.business.supcon.netty.service.chat.MemberCancelChatInfoResponseService;
import com.lj.business.supcon.netty.service.chat.SendChatInfoService;
import com.lj.business.supcon.netty.service.chat.UploadChatFileRequestService;
import com.lj.business.supcon.netty.service.chat.UploadChatFileResponseService;
import com.lj.business.supcon.netty.service.chat.UploadChatVideoRequestService;
import com.lj.business.supcon.netty.service.chat.UploadChatVideoResponseService;
import com.lj.business.supcon.netty.service.chatroom.AddChatRoomResponseService;
import com.lj.business.supcon.netty.service.chatroom.CreateChatRoomRequestService;
import com.lj.business.supcon.netty.service.chatroom.CreateChatRoomResponseService;
import com.lj.business.supcon.netty.service.chatroom.DelChatRoomResponseService;
import com.lj.business.supcon.netty.service.chatroom.DismissChatRoomResponseService;
import com.lj.business.supcon.netty.service.chatroom.GetChatRoomQRCodeRequestService;
import com.lj.business.supcon.netty.service.chatroom.GetChatRoomQRCodeResponseService;
import com.lj.business.supcon.netty.service.friends.DeleteFriendsResultService;
import com.lj.business.supcon.netty.service.friends.DeleteFriendsService;
import com.lj.business.supcon.netty.service.friends.SendFriendsCommentResultService;
import com.lj.business.supcon.netty.service.friends.SendFriendsInfoResultService;
import com.lj.business.supcon.netty.service.friends.SendFriendsLikeResultService;
import com.lj.business.supcon.netty.service.friends.SendFriendsPicDownResultService;
import com.lj.business.supcon.netty.service.friends.SendFriendsPicDownService;
import com.lj.business.supcon.netty.service.redpacket.FindAccountBalanceResponseService;
import com.lj.business.supcon.netty.service.redpacket.FindRedpacketResponseService;
import com.lj.business.supcon.netty.service.redpacket.RedpacketReceivedService;
import com.lj.business.supcon.netty.service.redpacket.RedpacketSendBackService;
import com.lj.business.supcon.netty.service.redpacket.SendRedpacketResultService;

/**
 * 
 * 
 * 类说明：消息编码定义
 * 
 * 
 * <p>
 * 详细描述：每新增一种消息编码都应在此定义并初始化
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年10月10日
 */
@SuppressWarnings("rawtypes")
public enum MessageCode {

	/** 通讯响应报文 */
	CommonResponse((short) 9999, (short) 0, Boolean.FALSE, Boolean.TRUE, CommonResponse.class, CommonService.class),

	/** 心跳包 */
	HeartBeatRequest((short) 1000, (short) 0, Boolean.FALSE, Boolean.TRUE, HeartBeatRequest.class,
			HeartBeatService.class),

	/** 中控客户端电量通知 */
	TerminalBatteryNotify((short) 1010, (short) 0, Boolean.FALSE, Boolean.TRUE, TerminalBatteryNotify.class,
			TerminalBatteryNotifyService.class),

	/** 推送通知 */
	NotifyMessage((short) 1020, (short) 0, Boolean.FALSE, Boolean.TRUE, NotifyMessage.class, null),

	/** 请求登录 */
	LoginRequest((short) 2000, (short) 2001, Boolean.FALSE, Boolean.TRUE, LoginRequest.class, LoginService.class),
	/** 登录结果返回 */
	LoginResult((short) 2001, (short) 0, Boolean.FALSE, Boolean.TRUE, LoginResult.class, null),
	/** 强制退出IM和APP */
	ForcedLogout((short) 2002, (short) 0, Boolean.FALSE, Boolean.TRUE, ForcedLogout.class, null),

	/** 签到 XXX 废弃 */
	SignRequest((short) 2003, (short) 2004, Boolean.FALSE, Boolean.TRUE, SignRequest.class, ZkSignService.class),
	/** 签到结果 TCP 登录完直接下发结果，并且后台修改支付密码，直接下发结果 */
	SignResponse((short) 2004, (short) 0, Boolean.FALSE, Boolean.TRUE, SignResponse.class, null),
	/** 下发签到命令 XXX 废弃 */
	SignCommand((short) 2005, (short) 0, Boolean.FALSE, Boolean.TRUE, SignCommand.class, null),
	/** 下发打开或关闭朋友圈上传功能命令 */
	UploadFriendsFlagCommand((short) 2006, (short) 0, Boolean.FALSE, Boolean.TRUE, UploadFriendsFlagCommand.class,
			null),

	/** 获取客户微信基本信息 */
	FindWxInfoRequest((short) 2010, (short) 2011, Boolean.FALSE, Boolean.TRUE, FindWxInfoRequest.class,
			FindWxInfoService.class),
	/** 返回客户微信基本信息 */
	FindWxInfoReturn((short) 2011, (short) 0, Boolean.FALSE, Boolean.TRUE, FindWxInfoReturn.class,
			FindWxInfoReturnService.class),

	/** 扫码添加客户 */
	ScanAddFriendRequest((short) 2020, (short) 2021, Boolean.FALSE, Boolean.TRUE, ScanAddFriendRequest.class,
			ScanAddFriendService.class),
	/** 添加客户结果通知 */
	AddFriendResult((short) 2021, (short) 2022, Boolean.FALSE, Boolean.TRUE, AddFriendResult.class,
			AddFriendResultService.class),
	/** 添加客户结果通知返回 */
	AddFriendResultResponse((short) 2022, (short) 0, Boolean.FALSE, Boolean.TRUE, AddFriendResultResponse.class, null),
	/** 通过手机号请求加粉 */
	AddFriendByPhoneRequest((short) 2023, (short) 2024, Boolean.FALSE, Boolean.TRUE, AddFriendByPhoneRequest.class,
			null),
	/** 开始通过手机号加粉通知返回 */
	StartAddFriendByPhoneResponse((short) 2024, (short) 0, Boolean.FALSE, Boolean.TRUE,
			StartAddFriendByPhoneResponse.class, StartAddFriendByPhoneResponseService.class),

	/** 客户微信基本信息同步请求 */
	SyncWxInfoRequest((short) 2030, (short) 0, Boolean.FALSE, Boolean.TRUE, SyncWxInfoRequest.class,
			SyncWxInfoRequestService.class),
	/** 客户微信基本信息同步返回 */
	SyncWxInfoResponse((short) 2031, (short) 0, Boolean.FALSE, Boolean.TRUE, SyncWxInfoResponse.class,
			SyncWxInfoResponseService.class),
	/** 请求中控同步终端微信通讯录 */
	SyncShopWxFriendsRequest((short) 2035, (short) 0, Boolean.FALSE, Boolean.TRUE, SyncShopWxFriendsRequest.class,
			null),
	/** 同步微信歷史聊天記錄 **/
	SyncWxChatDataRequest((short) 2036, (short) 0, Boolean.FALSE, Boolean.TRUE, SyncWxChatDataRequest.class, null),

	/** 通知导购认领客户 */
	MemberClaimNotify((short) 2040, (short) 0, Boolean.FALSE, Boolean.TRUE, MemberClaimNotify.class, null),
	/** 认领客户请求 */
	MemberClaimRequest((short) 2041, (short) 2042, Boolean.FALSE, Boolean.TRUE, MemberClaimRequest.class,
			MemberClaimService.class),
	/** 认领客户返回 */
	MemberClaimResponse((short) 2042, (short) 0, Boolean.FALSE, Boolean.TRUE, MemberClaimResponse.class, null),
	/** 通知其他导购客户已认领 */
	MemberClaimedNotify((short) 2043, (short) 0, Boolean.FALSE, Boolean.TRUE, MemberClaimedNotify.class, null),
	/** 发送置顶用户消息 */
	MemberSetUpUserRequest((short) 2044, (short) 0, Boolean.FALSE, Boolean.TRUE, SendSetUpUserToGm.class, null),

	/** 发送聊天记录 */
	ChatInfoRequest((short) 3000, (short) 3001, Boolean.FALSE, Boolean.TRUE, ChatInfoRequest.class,
			SendChatInfoService.class),
	/** 发送聊天记录结果返回（只返回失败结果） */
	ChatInfoResponse((short) 3001, (short) 0, Boolean.FALSE, Boolean.TRUE, ChatInfoResponse.class,
			ChatInfoResponseService.class),
	/** 撤回聊天记录 */
	CancelChatInfoRequest((short) 3002, (short) 3003, Boolean.FALSE, Boolean.TRUE, CancelChatInfoRequest.class,
			CancelChatInfoRequestService.class),
	/** 撤回聊天记录结果返回 */
	CancelChatInfoResponse((short) 3003, (short) 0, Boolean.FALSE, Boolean.TRUE, CancelChatInfoResponse.class,
			CancelChatInfoResponseService.class),
	/** 聊天记录未读数 */
	ChatInfoReadNumRequest((short) 3004, (short) 0, Boolean.FALSE, Boolean.TRUE, ChatInfoReadNumRequest.class,
			ChatInfoReadNumRequestService.class),
	/** 客户撤回聊天记录结果返回 */
	MemberCancelChatInfoResponse((short) 3005, (short) 0, Boolean.FALSE, Boolean.TRUE,
			MemberCancelChatInfoResponse.class, MemberCancelChatInfoResponseService.class),
	/** 向客户端推送离线聊天记录 */
	OfflineChatInfo((short) 3010, (short) 0, Boolean.FALSE, Boolean.TRUE, OfflineChatInfo.class, null),

	/** 获取图片消息图片路径 */
	FindChatImageRequest((short) 3020, (short) 3021, Boolean.FALSE, Boolean.TRUE, FindChatImageRequest.class,
			FindChatImageRequestService.class),
	/** 返回图片消息图片路径 */
	FindChatImageResponse((short) 3021, (short) 0, Boolean.FALSE, Boolean.TRUE, FindChatImageResponse.class,
			FindChatImageResponseService.class),

	/** 上传视频消息视频文件 */
	UploadChatVideoRequest((short) 3030, (short) 3031, Boolean.FALSE, Boolean.TRUE, UploadChatVideoRequest.class,
			UploadChatVideoRequestService.class),
	/** 返回视频消息视频文件 */
	UploadChatVideoResponse((short) 3031, (short) 0, Boolean.FALSE, Boolean.TRUE, UploadChatVideoResponse.class,
			UploadChatVideoResponseService.class),

	/** 下载文件消息文件 */
	UploadChatFileRequest((short) 3040, (short) 3041, Boolean.FALSE, Boolean.TRUE, UploadChatFileRequest.class,
			UploadChatFileRequestService.class),
	/** 返回文件url */
	UploadChatFileResponse((short) 3041, (short) 0, Boolean.FALSE, Boolean.TRUE, UploadChatFileResponse.class,
			UploadChatFileResponseService.class),

	/** 发送朋友圈 */
	SendFriendsInfo((short) 3100, (short) 3101, Boolean.FALSE, Boolean.TRUE, SendFriendsInfo.class, null),
	/** 发送朋友圈结果返回 */
	SendFriendsInfoResult((short) 3101, (short) 0, Boolean.FALSE, Boolean.TRUE, SendFriendsInfoResult.class,
			SendFriendsInfoResultService.class),
	/** 发送朋友圈评论 */
	SendFriendsComment((short) 3110, (short) 3111, Boolean.FALSE, Boolean.TRUE, SendFriendsComment.class, null),
	/** 发送朋友圈评论结果返回 */
	SendFriendsCommentResult((short) 3111, (short) 0, Boolean.FALSE, Boolean.TRUE, SendFriendsCommentResult.class,
			SendFriendsCommentResultService.class),
	/** 发送朋友圈点赞 */
	SendFriendsLike((short) 3120, (short) 3121, Boolean.FALSE, Boolean.TRUE, SendFriendsLike.class, null),
	/** 发送朋友圈点赞结果返回 */
	SendFriendsLikeResult((short) 3121, (short) 0, Boolean.FALSE, Boolean.TRUE, SendFriendsLikeResult.class,
			SendFriendsLikeResultService.class),
	/** 发送朋友圈下载图片请求 */
	SendFriendsPicDown((short) 3131, (short) 0, Boolean.FALSE, Boolean.TRUE, SendFriendsPicDown.class,
			SendFriendsPicDownService.class),
	/** 朋友圈下载图片结果 */
	SendFriendsPicDownResult((short) 3132, (short) 0, Boolean.FALSE, Boolean.TRUE, SendFriendsPicDownResult.class,
			SendFriendsPicDownResultService.class),
	/** 朋友圈下载图片通知导购 */
	SendFriendsPicNotify((short) 3133, (short) 0, Boolean.FALSE, Boolean.TRUE, SendFriendsPicResultNotify.class, null),
	/** 删除朋友圈 */
	DeleteFriends((short) 3140, (short) 3141, Boolean.FALSE, Boolean.TRUE, DeleteFriends.class,
			DeleteFriendsService.class),
	/** 删除朋友圈结果返回 */
	DeleteFriendsResult((short) 3141, (short) 0, Boolean.FALSE, Boolean.TRUE, DeleteFriendsResult.class,
			DeleteFriendsResultService.class),

	/** 请求同步群通讯录 */
	SyncChatRoomRequest((short) 3200, (short) 0, Boolean.FALSE, Boolean.TRUE, SyncChatRoomRequest.class, null),
	/** 请求创建群通讯录 */
	CreateChatRoomRequest((short) 3210, (short) 3211, Boolean.FALSE, Boolean.TRUE, CreateChatRoomRequest.class,
			CreateChatRoomRequestService.class),
	/** 请求创建群通讯录结果返回 */
	CreateChatRoomResponse((short) 3211, (short) 0, Boolean.FALSE, Boolean.TRUE, CreateChatRoomResponse.class,
			CreateChatRoomResponseService.class),
	/** 请求添加群成员通讯录 */
	AddChatRoomRequest((short) 3220, (short) 0, Boolean.FALSE, Boolean.TRUE, AddChatRoomRequest.class, null),
	/** 请求添加群成员通讯录返回 */
	AddChatRoomResponse((short) 3221, (short) 0, Boolean.FALSE, Boolean.TRUE, AddChatRoomResponse.class,
			AddChatRoomResponseService.class),
	/** 请求删除群成员通讯录 */
	DelChatRoomRequest((short) 3230, (short) 0, Boolean.FALSE, Boolean.TRUE, DelChatRoomRequest.class, null),
	/** 请求删除群成员通讯录返回 */
	DelChatRoomResponse((short) 3231, (short) 0, Boolean.FALSE, Boolean.TRUE, DelChatRoomResponse.class,
			DelChatRoomResponseService.class),
	/** 请求解散群聊 */
	DismissChatRoomRequest((short) 3240, (short) 0, Boolean.FALSE, Boolean.TRUE, DismissChatRoomRequest.class, null),
	/** 请求解散群聊返回 */
	DismissChatRoomResponse((short) 3241, (short) 0, Boolean.FALSE, Boolean.TRUE, DismissChatRoomResponse.class,
			DismissChatRoomResponseService.class),
	/** 请求获取群消息-群二维码和修改群昵称 */
	GetChatRoomQRCodeRequest((short) 3242, (short) 3243, Boolean.FALSE, Boolean.TRUE, GetChatRoomQRCodeRequest.class,
			GetChatRoomQRCodeRequestService.class),
	/** 请求获取群消息返回 */
	GetChatRoomQRCodeResponse((short) 3243, (short) 0, Boolean.FALSE, Boolean.TRUE, GetChatRoomQRCodeResponse.class,
			GetChatRoomQRCodeResponseService.class),

	/** 余额查询 */
	FindAccountBalanceRequest((short) 8000, (short) 0, Boolean.FALSE, Boolean.TRUE, FindAccountBalanceRequest.class,
			null),
	/** 余额查询返回 */
	FindAccountBalanceResponse((short) 8001, (short) 0, Boolean.FALSE, Boolean.TRUE, FindAccountBalanceResponse.class,
			FindAccountBalanceResponseService.class),
	/** 发红包(仅用于后台) 前端走的是聊天发送红包 */
	SendRedpacketRequest((short) 8010, (short) 0, Boolean.FALSE, Boolean.TRUE, SendRedpacketRequest.class, null),
	/** 发红包结果返回 */
	SendRedpacketResult((short) 8011, (short) 0, Boolean.FALSE, Boolean.TRUE, SendRedpacketResult.class,
			SendRedpacketResultService.class),
	/** 红包已领取 */
	RedpacketReceived((short) 8012, (short) 0, Boolean.FALSE, Boolean.TRUE, RedpacketReceived.class,
			RedpacketReceivedService.class),
	/** 红包超时已退回 */
	RedpacketSendBack((short) 8013, (short) 0, Boolean.FALSE, Boolean.TRUE, RedpacketSendBack.class,
			RedpacketSendBackService.class),
	/** 查询红包 */
	FindRedpacketRequest((short) 8014, (short) 0, Boolean.FALSE, Boolean.TRUE, FindRedpacketRequest.class, null),
	/** 查询红包返回 */
	FindRedpacketResponse((short) 8015, (short) 0, Boolean.FALSE, Boolean.TRUE, FindRedpacketResponse.class,
			FindRedpacketResponseService.class),

	/** 中控端上传日志文件指令 */
	TerminalLogFilesUpload((short) 9000, (short) 0, Boolean.FALSE, Boolean.TRUE, TerminalLogFilesUpload.class, null),

	/** 向中控端下发服务器最新版本信息 */
	SendVersionInfoToZk((short) 9010, (short) 0, Boolean.FALSE, Boolean.TRUE, SendVersionInfoToZk.class, null),

	;

	private short code; // 消息编码
	private short refCode; // 关联消息编码，为0时表示没有关联报文
	private boolean compression; // 是否开启gzip压缩(默认关闭)，消息体数据大的时候才开启，非常小的包压缩后体积反而变大，而且耗时
	private boolean jsonCoverToObject; // 消息内容是否从JSON格式转为对象
	private Class<? extends BaseDto> messageClass; // 消息对象
	private Class<? extends IService> serviceClass; // 消息处理服务

	private static Map<Short, MessageCode> CODE_CLASS_MAP = new HashMap<Short, MessageCode>(); // 消息编码-消息编码对象映射
	private static Map<Short, Class<? extends BaseDto>> MESSAGE_CLASS_MAP = new HashMap<Short, Class<? extends BaseDto>>(); // 消息编码-消息对象映射
	private static Map<Short, Class<? extends IService>> SERVICE_CLASS_MAP = new HashMap<Short, Class<? extends IService>>(); // 消息编码-消息处理服务映射

	public static void init() {
		for (MessageCode messageCode : MessageCode.values()) {
			CODE_CLASS_MAP.put(messageCode.getCode(), messageCode);
			MESSAGE_CLASS_MAP.put(messageCode.getCode(), messageCode.getMessageClass());
			SERVICE_CLASS_MAP.put(messageCode.getCode(), messageCode.getServiceClass());
		}
	}

	MessageCode(short code, short refCode, boolean compression, boolean jsonCoverToObject,
			Class<? extends BaseDto> messageClass, Class<? extends IService> serviceClass) {
		this.code = code;
		this.refCode = refCode;
		this.compression = compression;
		this.jsonCoverToObject = jsonCoverToObject;
		this.messageClass = messageClass;
		this.serviceClass = serviceClass;
	}

	/**
	 * 
	 *
	 * 方法说明：根据消息编码获取消息编码定义
	 *
	 * @param code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月14日
	 *
	 */
	public static MessageCode getMessageCodeByCode(short code) {
		MessageCode messageCode = CODE_CLASS_MAP.get(code);
		if (messageCode == null) {
			throw new IllegalMessageException("没有找到消息编码[" + code + "]");
		}
		return messageCode;
	}

	/**
	 * 
	 *
	 * 方法说明：根据消息编码判断是否存对应消息定义
	 *
	 * @param code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月25日
	 *
	 */
	public static boolean exist(short code) {
		if (code == (short) 0) {
			return Boolean.FALSE;
		}
		return CODE_CLASS_MAP.get(code) != null;
	}

	/**
	 * 
	 *
	 * 方法说明：根据消息编码获取消息对象
	 *
	 * @param code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月10日
	 *
	 */
	public static Class getMessageClassByCode(short code) {
		Class clazz = MESSAGE_CLASS_MAP.get(code);
		if (clazz == null) {
			throw new IllegalMessageException("没有找到消息编码[" + code + "]对应的消息对象");
		}
		return clazz;
	}

	/**
	 * 
	 *
	 * 方法说明：根据消息编码获取消息处理服务
	 *
	 * @param code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static Class<? extends IService> getServiceClassByCode(short code) {
		Class<? extends IService> clazz = SERVICE_CLASS_MAP.get(code);
		if (clazz == null) {
			throw new IllegalMessageException("没有找到消息编码[" + code + "]对应的消息处理服务");
		}
		return clazz;
	}

	/**
	 * 
	 *
	 * 方法说明：创建一个新的消息对象
	 *
	 * @param code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public final static Message createNewMessage(short code) {
		MessageCode messageCode = CODE_CLASS_MAP.get(code);
		if (messageCode == null) {
			throw new IllegalMessageException("非法编码：" + code);
		}

		return new Message(messageCode);
	}

	/**
	 * @return the code
	 */
	public short getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(short code) {
		this.code = code;
	}

	/**
	 * @return the refCode
	 */
	public short getRefCode() {
		return refCode;
	}

	/**
	 * @param refCode the refCode to set
	 */
	public void setRefCode(short refCode) {
		this.refCode = refCode;
	}

	/**
	 * @return the compression
	 */
	public boolean isCompression() {
		return compression;
	}

	/**
	 * @param compression the compression to set
	 */
	public void setCompression(boolean compression) {
		this.compression = compression;
	}

	/**
	 * 是否将消息体（必须为JSON格式）转对象
	 */
	public boolean isJsonCoverToObject() {
		return jsonCoverToObject;
	}

	/**
	 * @param jsonCoverToObject the jsonCoverToObject to set
	 */
	public void setJsonCoverToObject(boolean jsonCoverToObject) {
		this.jsonCoverToObject = jsonCoverToObject;
	}

	/**
	 * @return the messageClass
	 */
	public Class<? extends BaseDto> getMessageClass() {
		return messageClass;
	}

	/**
	 * @param messageClass the messageClass to set
	 */
	public void setMessageClass(Class<? extends BaseDto> messageClass) {
		this.messageClass = messageClass;
	}

	/**
	 * @return the serviceClass
	 */
	public Class<? extends IService> getServiceClass() {
		return serviceClass;
	}

	/**
	 * @param serviceClass the serviceClass to set
	 */
	public void setServiceClass(Class<? extends IService> serviceClass) {
		this.serviceClass = serviceClass;
	}

}
