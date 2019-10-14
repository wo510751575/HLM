/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.im;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.domain.ChatRoom;
import com.lj.business.member.domain.ChatRoomPm;
import com.lj.business.member.dto.chatroom.CreateChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPage;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomPage;
import com.lj.business.member.dto.chatroom.FindChatRoomPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoom;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMember;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.chatroom.AddChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.CreateChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.DelChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.DismissChatRoomMessage;
import com.lj.business.supcon.service.IChatRoomMessageService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.business.weixin.emus.ChatRoomStatus;

/**
 * 
 * 类说明：微信群信息相关服务
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2018年9月8日
 */
@Controller
@RequestMapping(value = "/im/chatroom/")
public class ChatRoomAction extends Action {

	private static Logger logger = LoggerFactory.getLogger(ChatAction.class);

	@Autowired
	private IChatRoomService chatRoomService;
	@Autowired
	private IChatRoomMemberService chatRoomMemberService;
	@Autowired
	private IShopTerminalService shopTerminalService;
	@Autowired
	ICommonService commonService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Resource
	private IChatRoomMessageService chatRoomMessageService;
	
	/**
	 * 
	 *@Desc 获取群二维码/更新群昵称
	 *@param chatRoomName
	 *@param roomNickName
	 *@param noWxGm
	 *@param memberNoGm
	 *@return
	 *@return GeneralResponse
	 *@author 贾光朝
	 *@createDate 2019年5月13日下午2:39:59
	 */
	@RequestMapping(value = "getChatRoomQRCode.do")
	@ResponseBody
	public GeneralResponse getChatRoomQRCode(String chatRoomName,String roomNickName,String noWxGm,String memberNoGm){
		try {
			logger.info("发送获取群二维码/更新群昵称报文");
			chatRoomMessageService.requestChatRoomMessage(memberNoGm, noWxGm, chatRoomName, roomNickName);
		} catch (Exception e) {
			logger.error("获取群二维码/更新群昵称错误!", e);
			return GeneralResponse.generateFailureResponse();
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 *@Desc 获取群二维码/更新群昵称结果接口
	 *@param chatRoomName
	 *@param merchantNo
	 *@return
	 *@return GeneralResponse
	 *@author 贾光朝
	 *@createDate 2019年5月13日下午2:40:25
	 */
	@RequestMapping(value = "getUpdatedChatRoom.do")
	@ResponseBody
	public GeneralResponse getUpdatedChatRoom(String chatRoomName,String merchantNo,String noWxGm){
		FindChatRoomReturn chatRoom = new FindChatRoomReturn();
		try {
			FindChatRoom findChatRoom = new FindChatRoom();
			findChatRoom.setNoWxZk(noWxGm);
			findChatRoom.setChatRoomName(chatRoomName);
			findChatRoom.setMerchantNo(merchantNo);
			chatRoom = chatRoomService.findChatRoomBySelective(findChatRoom);
		} catch (Exception e) {
			logger.error("获取群二维码/更新群昵称错误!", e);
			return GeneralResponse.generateFailureResponse();
		}
		return GeneralResponse.generateSuccessResponse(chatRoom);
	}

	/**
	 * 
	 *
	 * 方法说明：同步群信息
	 *
	 * @param paramJson
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月8日
	 *
	 */
	@RequestMapping(value = "syncChatRoomFromZk.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse syncChatRoomFromZk(String paramJson) {
		if (StringUtils.isEmpty(paramJson)) {
			logger.error("待同步群数据为空");
			return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "群数据为空", null);
		}

		try {
			chatRoomService.syncChatRoomFromZk(paramJson);
		} catch (Exception e) {
			logger.error("同步群聊错误 e={}", e);
		}

		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 *
	 * 方法说明：查询指群成员详细信息
	 *
	 * @param roomCode     群code
	 * @param chatroomNoWx 群成员微信号
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = "findChatRoomMember.do")
	@ResponseBody
	public GeneralResponse findChatRoomMember(String roomCode, String chatRoomName, String noWxGm) {
		AssertUtils.notAllNullAndEmpty(roomCode, chatRoomName, "群code或群ID不能同时为空");
		try {
			FindChatRoomMemberPage findChatRoomMemberPage = new FindChatRoomMemberPage();
			if (StringUtils.isNotEmpty(roomCode)) {
				findChatRoomMemberPage.setRoomCode(roomCode);
			}
			if (StringUtils.isNotEmpty(chatRoomName)) {
				findChatRoomMemberPage.setChatRoomName(chatRoomName);
				findChatRoomMemberPage.setNoWxZk(noWxGm);
			}
			findChatRoomMemberPage.setStatus(ChatRoomStatus.Y.getCode());
			List<FindChatRoomMemberPageReturn> list = chatRoomMemberService
					.findChatRoomMemberList(findChatRoomMemberPage);
			/**
			 * 群主排第一位
			 */
			FindChatRoom findChatRoom = new FindChatRoom();
			if (StringUtils.isNotEmpty(roomCode)) {
				findChatRoom.setCode(findChatRoomMemberPage.getRoomCode());
			}
			if (StringUtils.isNotEmpty(chatRoomName)) {
				findChatRoom.setChatRoomName(chatRoomName);
				findChatRoom.setNoWxZk(noWxGm);
			}

			FindChatRoomReturn findChatRoomReturn = chatRoomService.findChatRoomBySelective(findChatRoom);
			for (int i = 0; i < list.size(); i++) {
				FindChatRoomMemberPageReturn findChatRoomMemberPageReturn = list.get(i);
				findChatRoomMemberPageReturn.setNoDisturb(findChatRoomReturn.getNoDisturb());
				if (findChatRoomReturn.getRoomOwner().equals(findChatRoomMemberPageReturn.getUserName())) {
					if (i == 0) {
						break;
					} else {
						list.add(0, findChatRoomMemberPageReturn);
						list.remove(i + 1);
					}
				}
			}
			return GeneralResponse.generateSuccessResponse(list);
		} catch (TsfaServiceException e) {
			logger.error("查询群成员失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("查询群成员失败！", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 
	 *
	 * 方法说明：添加群成员
	 * 
	 * @param noWxZk       中控微信号
	 * @param chatRoomName 群名
	 * @param userNames    群成员微信号（以,分隔）
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = { "addChatRoomMember", "addChatRoomMember.do" })
	@ResponseBody
	public GeneralResponse addChatRoomMember(AddChatRoomMessage addChatRoomMessage, String nickNames) {
		try {
			IWxChatRoomService basic = commonService.getHessianWxChatRoomService(addChatRoomMessage.getNoWxZk());
			if (!basic.sendAddChatRoom(addChatRoomMessage)) {
				return GeneralResponse.generateFailureResponse(com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE,
						"中控客户端已离线");
			}
			return GeneralResponse.generateSuccessResponse("你邀请" + nickNames + "加入了群聊");
		} catch (TsfaServiceException e) {
			logger.error("添加群成员失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("添加群成员失败！", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 
	 *
	 * 方法说明：删除群成员
	 * 
	 * @param noWxZk       中控微信号
	 * @param chatRoomName 群名
	 * @param userNames    群成员微信号（以,分隔）
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = { "delChatRoomMember", "delChatRoomMember.do" })
	@ResponseBody
	public GeneralResponse delChatRoomMember(DelChatRoomMessage delChatRoomMessage, String nickNames, String noWxGm) {
		try {
			AssertUtils.notNullAndEmpty(noWxGm, "中控微信不存在！");
			/**
			 * 只有群主才能删除成员
			 */
			FindChatRoom findChatRoom = new FindChatRoom();
			findChatRoom.setChatRoomName(delChatRoomMessage.getChatRoomName());
			findChatRoom.setNoWxZk(noWxGm);
			FindChatRoomReturn chatRoomReturn = chatRoomService.findChatRoomBySelective(findChatRoom);

			/**
			 * 获取中控微信username
			 */
			FindShopTerminalReturn findShopTerminalReturn = shopTerminalService.findShopTerminalByWx(noWxGm);

			if (StringUtils.isNotEmpty(findShopTerminalReturn.getUsernameWx())) {
				if (findShopTerminalReturn.getUsernameWx().equals(chatRoomReturn.getRoomOwner())
						|| chatRoomReturn.getRoomOwner().equals(noWxGm)) {
					IWxChatRoomService basic = commonService.getHessianWxChatRoomService(noWxGm);

					if (!basic.sendDelChatRoom(delChatRoomMessage)) {
						return GeneralResponse.generateFailureResponse(
								com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE, "中控客户端已离线");
					}
				} else {
					return GeneralResponse.generateFailureResponse("ROOM_OWNER", "只有群主才能删除成员！");
				}
			}
			return GeneralResponse.generateSuccessResponse("你将" + nickNames + "移出群聊");
		} catch (TsfaServiceException e) {
			logger.error("删除群成员失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("删除群成员失败！", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 
	 *
	 * 方法说明：解散群聊
	 * 
	 * @param noWxZk       中控微信号
	 * @param chatRoomName 群名
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = { "dismissChatRoom", "dismissChatRoom.do" })
	@ResponseBody
	public GeneralResponse DismissChatRoom(DismissChatRoomMessage dismissChatRoomMessage) {
		try {

			IWxChatRoomService basic = commonService.getHessianWxChatRoomService(dismissChatRoomMessage.getNoWxZk());
			if (!basic.sendDismissChatRoom(dismissChatRoomMessage)) {
				return GeneralResponse.generateFailureResponse(com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE,
						"中控客户端已离线");
			}
			return GeneralResponse.generateSuccessResponse("退出群聊成功！");
		} catch (TsfaServiceException e) {
			logger.error("解散群聊失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("解散群聊失败！", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 获取群聊信息根据导购编号
	 * 
	 * @param findChatRoomPage
	 * @return
	 */
	@RequestMapping(value = "findChatRoom.do")
	@ResponseBody
	public GeneralResponse findChatRoom(String noWxGm, FindChatRoomPage findChatRoomPage) {
		return findChatRooms(noWxGm, findChatRoomPage);
	}

	/**
	 * 获取群聊列表
	 * 
	 * @param findChatRoomPage
	 * @return
	 */
	private GeneralResponse findChatRooms(String noWxGm, FindChatRoomPage findChatRoomPage) {
		try {
			if (StringUtils.isEmpty(findChatRoomPage.getMerchantNo())) {
				return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "商户编号为空", null);
			}
			if (StringUtils.isEmpty(noWxGm)) {
				return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "终端微信为空", null);
			}
//			if (StringUtils.isEmpty(findChatRoomPage.getShopNo())) {
//				return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "门店编号为空", null);
//			}
			findChatRoomPage.setStatus(ChatRoomStatus.Y.getCode());
			findChatRoomPage.setNoWxZk(noWxGm);
			if (findChatRoomPage.isClaimed() != null) {
				findChatRoomPage.setMemberNoGm(null);
			}
			Page<FindChatRoomPageReturn> page = chatRoomService.findChatRoomPage(findChatRoomPage);
			return GeneralResponse.generateSuccessResponse(page);
		} catch (TsfaServiceException e) {
			logger.error("查询群成员失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("查询群成员失败！", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 群认领
	 * 
	 * @param findChatRoomPage
	 * @return
	 */
	@RequestMapping(value = "claimed.do")
	@ResponseBody
	public GeneralResponse claimed(UpdateChatRoom updateChatRoom) {
		try {
			if (StringUtils.isEmpty(updateChatRoom.getMemberNoGm())) {
				return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "导购编号为空", null);
			}
			if (StringUtils.isEmpty(updateChatRoom.getMemberNameGm())) {
				return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "导购名称为空", null);
			}
			if (StringUtils.isEmpty(updateChatRoom.getCode())) {
				return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "群编号为空", null);
			}
			chatRoomService.updateChatRoom(updateChatRoom);
			UpdateChatRoomMember updateChatRoomMember = new UpdateChatRoomMember();
			updateChatRoomMember.setRoomCode(updateChatRoom.getCode());
			updateChatRoomMember.setMemberNoGm(updateChatRoom.getMemberNoGm());
			updateChatRoomMember.setMemberNameGm(updateChatRoom.getMemberNameGm());
			chatRoomMemberService.updateChatRoomMemberByRoomCode(updateChatRoomMember);
			return GeneralResponse.generateSuccessResponse();
		} catch (TsfaServiceException e) {
			logger.error("添加群成员失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("添加群成员失败！", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 取消认领
	 * 
	 * @param updateChatRoom
	 * @return
	 */
	@RequestMapping(value = "cancelClaimed.do")
	@ResponseBody
	public GeneralResponse cancelClaimed(UpdateChatRoom updateChatRoom) {
		try {
			if (StringUtils.isEmpty(updateChatRoom.getCode())) {
				return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "群编号为空", null);
			}
			chatRoomService.cancelClaimed(updateChatRoom);
			UpdateChatRoomMember updateChatRoomMember = new UpdateChatRoomMember();
			updateChatRoomMember.setRoomCode(updateChatRoom.getCode());
			chatRoomMemberService.cancelClaimed(updateChatRoomMember);
			return GeneralResponse.generateSuccessResponse();
		} catch (TsfaServiceException e) {
			logger.error("添加群成员失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("添加群成员失败！", e);
			return GeneralResponse.generateFailureResponse();
		}
	}

	/**
	 * 创建群组
	 * 
	 * @param createChatRoom
	 * @return
	 */
	@RequestMapping(value = "/createChatRoomGroup.do")
	@ResponseBody
	public GeneralResponse createChatRoomGroup(CreateChatRoom createChatRoom) {
		AssertUtils.notAllNullAndEmpty(createChatRoom.getUserNames(), "用户名称不能为空");
		AssertUtils.notAllNullAndEmpty(createChatRoom.getNoWxZk(), "中控微信不能为空");
		AssertUtils.notAllNullAndEmpty(createChatRoom.getMemberNoGm(), "导购比那好不能为空");
		AssertUtils.notAllNullAndEmpty(createChatRoom.getRoomNickName(), "群名称不能为空");
		try {

			CreateChatRoomMessage createChatRoomMessage = new CreateChatRoomMessage();
			createChatRoomMessage.setNoWxZk(createChatRoom.getNoWxZk());
			createChatRoomMessage.setUserNames(createChatRoom.getUserNames());
			createChatRoomMessage.setMemberNoGm(createChatRoom.getMemberNoGm());
			createChatRoomMessage.setPmCode(GUID.generateCode());
			createChatRoomMessage.setPmName(createChatRoom.getRoomNickName());
			String[] usernames = createChatRoom.getUserNames().split(",");

			if (usernames.length > 38) {
				StringBuffer username = new StringBuffer("");
				ChatRoom room = new ChatRoom();
				room.setMemberNoGm(createChatRoom.getMemberNoGm());
				room.setCode(GUID.generateCode());
				room.setPmCode(createChatRoomMessage.getPmCode());
				room.setPmName(createChatRoom.getRoomNickName());
				room.setNoWxGm(createChatRoom.getNoWxZk());
				chatRoomService.createRoomGroup(room);
				logger.info("已经创建群组,微信长度:" + usernames.length);
				int num = 0;
				for (int i = 0; i < usernames.length; i++) {

					username = username.append(usernames[i]).append(",");

					if (i != 0 && ((i + 1) % 38 == 0 || i == usernames.length - 1)) {
						num = num + 1;
						username.deleteCharAt(username.length() - 1);
						createChatRoomMessage.setUserNames(username.toString());

						// 小于3个不建
						String usernameArray[] = username.toString().split(",");
						if (usernameArray.length < 3) {
							continue;
						}

						createChatRoomMessage.setRoomNickName(createChatRoom.getRoomNickName() + num);
						logger.info("开始建群" + createChatRoomMessage.getRoomNickName() + "(" + num + ")");

						// 300秒建一个群
						Integer integerMillis = 1000;
						if (num > 1) {
							integerMillis = num * 300 * 1000;
						}

						long millis = Long.valueOf(String.valueOf(integerMillis));

						// 开始创建群
						CreateChatRoomMessage createChatRoomNewMessage = new CreateChatRoomMessage();
						createChatRoomNewMessage.setNoWxZk(createChatRoomMessage.getNoWxZk());
						createChatRoomNewMessage.setUserNames(createChatRoomMessage.getUserNames());
						createChatRoomNewMessage.setMemberNoGm(createChatRoomMessage.getMemberNoGm());
						createChatRoomNewMessage.setPmCode(createChatRoomMessage.getPmCode());
						createChatRoomNewMessage.setPmName(createChatRoomMessage.getRoomNickName());
						createChatRoomNewMessage.setRoomNickName(createChatRoomMessage.getRoomNickName());

						notifyCreateChatRoom(createChatRoomNewMessage, millis);

						username = new StringBuffer("");
					}
				}
			} else {
				IWxChatRoomService basic = commonService.getHessianWxChatRoomService(createChatRoomMessage.getNoWxZk());
				if (!basic.sendCreateChatRoom(createChatRoomMessage)) {
					return GeneralResponse.generateFailureResponse(
							com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE, "中控微信已离线！");
				}
			}

			return GeneralResponse.generateSuccessResponse(null);
		} catch (TsfaServiceException e) {
			logger.error("创建群组失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("创建群组失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	/**
	 * 异步拉群，线程并睡眠
	 * 
	 * @param millis 毫秒
	 */
	public void notifyCreateChatRoom(CreateChatRoomMessage createChatRoomMessage, long millis) {

		// 异步发送
		taskExecutor.execute(new Runnable() {

			@Override
			public void run() {
				try {

					Thread.sleep(millis);

					logger.info("休眠完成，开始执行：" + createChatRoomMessage.getRoomNickName());
					logger.info("休眠完成，开始执行：" + createChatRoomMessage.getUserNames());
					IWxChatRoomService basic = commonService
							.getHessianWxChatRoomService(createChatRoomMessage.getNoWxZk());
					basic.sendCreateChatRoom(createChatRoomMessage);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * 获取群聊信息根据导购编号
	 * 
	 * @param findChatRoomPage
	 * @return
	 */
	@RequestMapping(value = "findChatRoomByPmCode.do")
	@ResponseBody
	public GeneralResponse findChatRoomGroup(FindChatRoomPage findChatRoomPage) {
		AssertUtils.notAllNullAndEmpty(findChatRoomPage.getPmCode(), "群组编号不能为空");
		AssertUtils.notAllNullAndEmpty(findChatRoomPage.getMerchantNo(), "商户号不能为空");
		AssertUtils.notAllNullAndEmpty(findChatRoomPage.getMemberNoGm(), "导购号不能为空");
		try {

			if (StringUtils.isEmpty(findChatRoomPage.getPmCode())) {
				return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "群组号为空", null);
			}

			findChatRoomPage.setStatus(ChatRoomStatus.Y.getCode());
			Page<FindChatRoomPageReturn> page = chatRoomService.findChatRoomPage(findChatRoomPage);
			return GeneralResponse.generateSuccessResponse(page);
		} catch (TsfaServiceException e) {
			logger.error("根据组code查询群失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("根据组code查询群失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	/**
	 * 获取群组信息根据导购编号
	 * 
	 * @param findChatRoomPage
	 * @return
	 */
	@RequestMapping(value = "findChatRoomPm.do")
	@ResponseBody
	public GeneralResponse findChatRoomPm(String memberNoGm, String noWxGm) {
		AssertUtils.notAllNullAndEmpty(memberNoGm, "导购号不能为空");
		try {
			ChatRoomPm chatroompm = new ChatRoomPm();
			chatroompm.setNoWxGm(noWxGm);
			chatroompm.setMemberNoGm(memberNoGm);
			List<ChatRoomPm> list = chatRoomService.selectRoomGroup(chatroompm);
			return GeneralResponse.generateSuccessResponse(list);
		} catch (TsfaServiceException e) {
			logger.error("查询群组失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("查询群组失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	@RequestMapping(value = "/createChatRoom.do")
	@ResponseBody
	public GeneralResponse createChatRoom(CreateChatRoom createChatRoom) {
		AssertUtils.notAllNullAndEmpty(createChatRoom.getUserNames(), "用户名称不能为空");
		AssertUtils.notAllNullAndEmpty(createChatRoom.getNoWxZk(), "中控微信不能为空");
		AssertUtils.notAllNullAndEmpty(createChatRoom.getMemberNoGm(), "导购比那好不能为空");
		AssertUtils.notAllNullAndEmpty(createChatRoom.getRoomNickName(), "群名称不能为空");
		try {

			CreateChatRoomMessage createChatRoomMessage = new CreateChatRoomMessage();
			createChatRoomMessage.setNoWxZk(createChatRoom.getNoWxZk());
			createChatRoomMessage.setUserNames(createChatRoom.getUserNames());
			createChatRoomMessage.setMemberNoGm(createChatRoom.getMemberNoGm());
			createChatRoomMessage.setRoomNickName(createChatRoom.getRoomNickName());
			String[] usernames = createChatRoom.getUserNames().split(",");

			if (usernames.length > 38) {
				StringBuffer username = new StringBuffer("");
				for (int i = 0; i < usernames.length; i++) {
					username = username.append(usernames[i]).append(",");
					if (i != 0 && ((i + 1) % 38 == 0 || i == usernames.length - 1)) {
						username.deleteCharAt(username.length() - 1);
						createChatRoomMessage.setUserNames(username.toString());

						IWxChatRoomService basic = commonService
								.getHessianWxChatRoomService(createChatRoomMessage.getNoWxZk());
						basic.sendCreateChatRoom(createChatRoomMessage);
						username = new StringBuffer("");
					}
				}
			} else {
				IWxChatRoomService basic = commonService.getHessianWxChatRoomService(createChatRoomMessage.getNoWxZk());
				if (!basic.sendCreateChatRoom(createChatRoomMessage)) {
					return GeneralResponse.generateFailureResponse(
							com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE, "中控微信已离线！");
				}
			}

			return GeneralResponse.generateSuccessResponse(null);
		} catch (TsfaServiceException e) {
			logger.error("创建群失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("创建群失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	/**
	 * 设置免打扰
	 * @param updateChatRoom
	 * @return
	 */
	@RequestMapping(value = "/setNoDisturb.do")
	@ResponseBody
	public GeneralResponse setNoDisturb(UpdateChatRoom updateChatRoom) {
		try {
			chatRoomService.updateNoDisturb(updateChatRoom);
			return GeneralResponse.generateSuccessResponse();
		} catch (TsfaServiceException e) {
			logger.error("创建群失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("创建群失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}
}
