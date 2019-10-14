/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.im;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.chatroom.CreateChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPage;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.chatroom.AddChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.CreateChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.DelChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.DismissChatRoomMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.oms.dto.ResponseDto;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 类说明：微信群
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 * 
 *         CreateDate: 2018年9月28日
 */
@Controller
@RequestMapping(value = "${adminPath}/im/chatroom/")
public class ChatRoomController {

	private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

	@Autowired
	private IChatRoomService chatRoomService;

	@Autowired
	private IChatRoomMemberService chatRoomMemberService;

	@Autowired
	private IShopTerminalService shopTerminalService;

    @Autowired 
	ICommonService commonService;

	/**
	 * 
	 *
	 * 方法说明：查询微信群信息
	 *
	 * @param code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = "form")
	@ResponseBody
	public ResponseDto form(String code) {
		try {
			if (StringUtils.isBlank(code)) {
				return ResponseDto.failureResp("0", "群编号为空");
			}
			FindChatRoom findChatRoom = new FindChatRoom();
			findChatRoom.setCode(code);
			FindChatRoomReturn findChatRoomReturn = chatRoomService.findChatRoom(findChatRoom);
			return ResponseDto.successResp(findChatRoomReturn);
		} catch (TsfaServiceException e) {
			logger.error("查询群聊失败！", e);
			return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("查询群聊失败！", e);
			return ResponseDto.failureResp("0", "查询群聊失败！");
		}
	}

	/**
	 * 
	 *
	 * 方法说明：查询群成员信息
	 *
	 * @param code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = "memberList")
	@ResponseBody
	public ResponseDto memberList(FindChatRoomMemberPage findChatRoomMemberPage, Integer pageNo, Integer pageSize) {
		try {
			if (pageNo != null) {
				findChatRoomMemberPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findChatRoomMemberPage.setLimit(pageSize);
			}
			findChatRoomMemberPage.setMerchantNo(UserUtils.getMerchantNo());

			Page<FindChatRoomMemberPageReturn> pages = chatRoomMemberService.findChatRoomMemberPage(findChatRoomMemberPage);
			List<FindChatRoomMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			
			/**
			 * 群主排第一位
			 */
			FindChatRoom findChatRoom = new FindChatRoom();
			findChatRoom.setCode(findChatRoomMemberPage.getRoomCode());
			FindChatRoomReturn findChatRoomReturn= chatRoomService.findChatRoomBySelective(findChatRoom);
			for (int i = 0; i < list.size(); i++) {
				FindChatRoomMemberPageReturn findChatRoomMemberPageReturn =list.get(i);
				if(i ==0 && findChatRoomReturn.getRoomOwner().equals(findChatRoomMemberPageReturn.getUserName())) {
					break;
				}
				if(findChatRoomReturn.getRoomOwner().equals(findChatRoomMemberPageReturn.getUserName())){
					list.add(0, findChatRoomMemberPageReturn);
					list.remove(i+1);
				}
			}
			com.ape.common.persistence.Page<FindChatRoomMemberPageReturn> page = new com.ape.common.persistence.Page<FindChatRoomMemberPageReturn>(pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			return ResponseDto.successResp(page);
		} catch (TsfaServiceException e) {
			logger.error("查询群成员失败！", e);
			return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("查询群成员失败！", e);
			return ResponseDto.failureResp("0", "查询群成员失败！");
		}
	}

	/**
	 * 
	 *
	 * 方法说明：进入群聊页面
	 *
	 * @param model
	 * @param code
	 *            群code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = { "chatroom" })
	public String chatroom(Model model, String code) {
		this.buildChatroom(model, code);
		return "modules/im/chatroom";
	}

	/**
	 * 
	 *
	 * 方法说明：进入群聊历史记录页面
	 *
	 * @param model
	 * @param code
	 *            群code
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = { "chatroomHistory" })
	public String chatroomHistory(Model model, String code) {
		this.buildChatroom(model, code);
		return "modules/im/chatroomHistory";
	}

	private void buildChatroom(Model model, String code) {
		try {

			// 查询群信息
			FindChatRoom findChatRoom = new FindChatRoom();
			findChatRoom.setCode(code);
			FindChatRoomReturn findChatRoomReturn = chatRoomService.findChatRoom(findChatRoom);
			model.addAttribute("chatroom", findChatRoomReturn);

			// 查询群成员
			/*FindChatRoomMemberPage findChatRoomMemberPage = new FindChatRoomMemberPage();
			findChatRoomMemberPage.setRoomCode(code);
			findChatRoomMemberPage.setLimit(10);
			findChatRoomMemberPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindChatRoomMemberPageReturn> pages = chatRoomMemberService.findChatRoomMemberPage(findChatRoomMemberPage);
			List<FindChatRoomMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindChatRoomMemberPageReturn> page = new com.ape.common.persistence.Page<FindChatRoomMemberPageReturn>(0, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("memberList", page);
			
			// 同步微信群信息
			SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
			syncChatRoomMessage.setNoWxZk(findChatRoomReturn.getNoWxZk());
			syncChatRoomMessage.setChatRoomName(findChatRoomReturn.getChatRoomName());
			
			IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(syncChatRoomMessage.getNoWxZk());
			basic.sendSyncChatRoom(syncChatRoomMessage);*/
		} catch (Exception e) {
			logger.error("查询群信息错误", e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询指群成员详细信息
	 *
	 * @param roomCode		群code
	 * @param chatroomNoWx	群成员微信号
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = "findChatRoomMember")
	@ResponseBody
	public ResponseDto findChatRoomMember(String roomCode, String chatroomNoWx) {
		try {
			FindChatRoomMemberReturn findChatRoomMemberReturn = chatRoomMemberService.findChatRoomMemberByNoWx(roomCode, chatroomNoWx);
			return ResponseDto.successResp(findChatRoomMemberReturn);
		} catch (TsfaServiceException e) {
			logger.error("查询群成员失败！", e);
			return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("查询群成员失败！", e);
			return ResponseDto.failureResp("0", "查询群成员失败！");
		}
	}

	
	
	/**
	 * 
	 *
	 * 方法说明：创建群聊
	 * @param noWxZk 		中控微信号
	 * @param roomNickName	群昵称
	 * @param userNames		群成员微信号（以,分隔）
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = "createCharRoom")
	@ResponseBody
	public ResponseDto createCharRoom(CreateChatRoom createChatRoom) {
		try {
			CreateChatRoomMessage createChatRoomMessage = new CreateChatRoomMessage();
			createChatRoomMessage.setNoWxZk(createChatRoom.getNoWxZk());
			createChatRoomMessage.setRoomNickName(createChatRoom.getRoomNickName());
			createChatRoomMessage.setUserNames(createChatRoom.getUserNames());
			createChatRoomMessage.setMemberNoGm(UserUtils.getUser().getId());
			String[] usernames = createChatRoom.getUserNames().split(",");
			if(usernames.length>38){
				StringBuffer username=new StringBuffer("");
				for (int i = 0; i < usernames.length; i++) {
					username=username.append(usernames[i]).append(",");
					if(i!=0 && ((i+1)%38==0 || i==usernames.length-1)){
						username.deleteCharAt(username.length()-1);
						createChatRoomMessage.setUserNames(username.toString());
						
						IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(createChatRoomMessage.getNoWxZk());
						if(!basic.sendCreateChatRoom(createChatRoomMessage)) {
							 return ResponseDto.failureResp("0", "中控微信已离线！");	
						}
						username = new StringBuffer("");
					}
				}
			}else{
				IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(createChatRoomMessage.getNoWxZk());
				if(!basic.sendCreateChatRoom(createChatRoomMessage)) {
					  return ResponseDto.failureResp("0", "中控微信已离线！");	
				}
			}
			
			return ResponseDto.successResp(null);
		} catch (TsfaServiceException e) {
			logger.error("创建群失败！", e);
			return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("创建群失败！", e);
			return ResponseDto.failureResp("0", "创建群失败！");
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：添加群成员
	 * @param noWxZk 		中控微信号
	 * @param chatRoomName	群名
	 * @param userNames		群成员微信号（以,分隔）
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = "addChatRoomMember")
	@ResponseBody
	public ResponseDto addChatRoomMember(AddChatRoomMessage addChatRoomMessage,String nickNames) {
		try {
			
			IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(addChatRoomMessage.getNoWxZk());
			if(!basic.sendAddChatRoom(addChatRoomMessage)) {
				  return ResponseDto.failureResp("0", "中控微信已离线！");	
			  }
			return ResponseDto.successResp("你邀请"+nickNames+"加入了群聊");
		} catch (TsfaServiceException e) {
			logger.error("添加群成员失败！", e);
			return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("添加群成员失败！", e);
			return ResponseDto.failureResp("0", "添加群成员失败！");
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除群成员
	 * @param noWxZk 		中控微信号
	 * @param chatRoomName	群名
	 * @param userNames		群成员微信号（以,分隔）
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = "delChatRoomMember")
	@ResponseBody
	public ResponseDto delChatRoomMember(DelChatRoomMessage delChatRoomMessage,String nickNames) {
		try {
			/**
			 * 只有群主才能删除成员
			 */
			FindChatRoom findChatRoom = new FindChatRoom();
			findChatRoom.setChatRoomName(delChatRoomMessage.getChatRoomName());
			FindChatRoomReturn chatRoomReturn= chatRoomService.findChatRoomBySelective(findChatRoom);
			
			/**
			 * 获取中控微信username
			 */
			FindShopTerminal findShopTerminal = new FindShopTerminal();
			findShopTerminal.setNoWx(delChatRoomMessage.getNoWxZk());
			List<FindShopTerminalReturn> list = shopTerminalService.findShopTerminalSelect(findShopTerminal);
			FindShopTerminalReturn findShopTerminalReturn =null;
			if(null!=list && list.size()>0){
				findShopTerminalReturn= list.get(0);
			}
			if(null !=findShopTerminalReturn && !findShopTerminalReturn.getUsernameWx().isEmpty()){
				if(findShopTerminalReturn.getUsernameWx().equals(chatRoomReturn.getRoomOwner()) || chatRoomReturn.getRoomOwner().equals(delChatRoomMessage.getNoWxZk())){
					
					  IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(delChatRoomMessage.getNoWxZk());
					  if(!basic.sendDelChatRoom(delChatRoomMessage)) {
						  return ResponseDto.failureResp("0", "中控微信已离线！");	
					  }
				}else{
					return ResponseDto.failureResp("0", "只有群主才能删除成员！");	
				}
			}
			return ResponseDto.successResp("你将"+nickNames+"移出群聊");
		} catch (TsfaServiceException e) {
			logger.error("删除群成员失败！", e);
			return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("删除群成员失败！", e);
			return ResponseDto.failureResp("0", "删除群成员失败！");
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：解散群聊
	 * @param noWxZk 		中控微信号
	 * @param chatRoomName	群名
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2018年9月28日
	 *
	 */
	@RequestMapping(value = "dismissChatRoom")
	@ResponseBody
	public ResponseDto DismissChatRoom(DismissChatRoomMessage dismissChatRoomMessage) {
		try {
			IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(dismissChatRoomMessage.getNoWxZk());
			if(!basic.sendDismissChatRoom(dismissChatRoomMessage)) {
				  return ResponseDto.failureResp("0", "中控微信已离线！");	
			}
			return ResponseDto.successResp(null);
		} catch (TsfaServiceException e) {
			logger.error("解散群聊失败！", e);
			return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("解散群聊失败！", e);
			return ResponseDto.failureResp("0", "解散群聊失败！");
		}
	}
	
	
	/**
	 * 跳转到群聊页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list" })
	public String list(Model model) {
		model.addAttribute("assistantNo", UserUtils.getUser().getId());
        model.addAttribute("merchantNo", UserUtils.getMerchantNo());
        model.addAttribute("merchantName", UserUtils.getUser().getCompany().getName());
		return "modules/im/creatGroup";
	}
	
	
	
	@RequestMapping(value = "createCharRoomByPmType")
	@ResponseBody
	public ResponseDto createCharRoomByPmType(CreateChatRoom createChatRoom) {
		try {
			CreateChatRoomMessage createChatRoomMessage = new CreateChatRoomMessage();
			createChatRoomMessage.setNoWxZk(createChatRoom.getNoWxZk());
			createChatRoomMessage.setRoomNickName(createChatRoom.getRoomNickName());
			createChatRoomMessage.setUserNames(createChatRoom.getUserNames());
			
			IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(createChatRoomMessage.getNoWxZk());
			if(!basic.sendCreateChatRoom(createChatRoomMessage)) {
				  return ResponseDto.failureResp("0", "中控微信已离线！");	
			  }
			return ResponseDto.successResp(null);
		} catch (TsfaServiceException e) {
			logger.error("创建群失败！", e);
			return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("创建群失败！", e);
			return ResponseDto.failureResp("0", "创建群失败！");
		}
	}
	


}
