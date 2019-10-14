/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.im;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaException;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.dto.FindImGroupChatInfoPage;
import com.lj.business.weixin.dto.ImGroupChatInfoDto;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.ChatRoomType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImGroupChatInfoService;
import com.lj.oms.dto.ResponseDto;
import com.lj.oms.utils.UserUtils;

/**
 * 群发设置
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/im/groupChatInfo/")
public class ImGroupChatInfoController {

	private static final Logger logger = LoggerFactory.getLogger(ImGroupChatInfoController.class);

	@Autowired
	private IImGroupChatInfoService iImGroupChatInfoService;
	@Autowired
	private IImChatInfoService imChatInfoService;
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private IPersonMemberService personMemberService;
	@Autowired 
	private ICommonService commonService;
	/**
	 * 跳转到list页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list" })
	public String list(Model model) {
		return "modules/im/massTextingList";
	}
	
	@RequestMapping(value = { "listJson" })
	@ResponseBody
	public ResponseDto listJson(Model model, FindImGroupChatInfoPage findImGroupChatInfoPage,String content, Integer pageNo, Integer pageSize) {
		try {
			if (pageNo != null) {
				findImGroupChatInfoPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findImGroupChatInfoPage.setLimit(pageSize);
			}
			if(StringUtils.isNoneBlank(content)){
				ImGroupChatInfoDto param = new ImGroupChatInfoDto();
				param.setContent(content);
				findImGroupChatInfoPage.setParam(param);
			}
			Page<ImGroupChatInfoDto> pages = iImGroupChatInfoService.findImGroupChatInfoPage(findImGroupChatInfoPage);
			List<ImGroupChatInfoDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
	
			com.ape.common.persistence.Page<ImGroupChatInfoDto> page = new com.ape.common.persistence.Page<ImGroupChatInfoDto>(
					pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", pages);
			return ResponseDto.successResp(page);
	} catch (TsfaServiceException e) {
		logger.error("查询群发设置失败！", e);
		return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
	} catch (Exception e) {
		logger.error("查询群发设置失败！", e);
		return ResponseDto.failureResp("0", "查询群发设置失败！");
	}
	}

	/**
	 * 跳转到form页面
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(Model model) {
		model.addAttribute("assistantNo", UserUtils.getUser().getId());
        model.addAttribute("merchantNo", UserUtils.getMerchantNo());
        model.addAttribute("merchantName", UserUtils.getMerchantName());
		return "modules/im/massTexting";
	}

	/**
	 * 新增群发设置
	 * 
	 * @param imGroupChatInfoDto
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseDto save(ImGroupChatInfoDto imGroupChatInfoDto) {

		if(StringUtils.isEmpty(imGroupChatInfoDto.getType())
				||StringUtils.isEmpty(imGroupChatInfoDto.getMemberNos())
				||StringUtils.isEmpty(imGroupChatInfoDto.getMemberNames())
				||StringUtils.isEmpty(imGroupChatInfoDto.getMemberNoWxs())
				||StringUtils.isEmpty(imGroupChatInfoDto.getNoWxGm())){
			return ResponseDto.failureResp("0","参数错误!");
		}
		
		if(imGroupChatInfoDto.getType().equals(ChatInfoType.IMG.getCode())){
			if(StringUtils.isEmpty(imGroupChatInfoDto.getResourcesPath())){
				return ResponseDto.failureResp("0","图片消息，必须上传图片!");
			}
		}
		
		if(imGroupChatInfoDto.getType().equals(ChatInfoType.TEXT.getCode())){
			if(StringUtils.isEmpty(imGroupChatInfoDto.getContent())){
				return ResponseDto.failureResp("0","文本消息缺少内容!");
			}
		}
		
		ICommonService basic = commonService.getHessianCommonService(imGroupChatInfoDto.getNoWxGm());
		if(!basic.getZkTerminalStatus(imGroupChatInfoDto.getNoWxGm())) {
			return ResponseDto.failureResp("0", "中控微信已离线！");
		}
		
		try {
			/**
			 * 获取终端信息
			 */
			imGroupChatInfoDto.setMerchantNo(UserUtils.getMerchantNo());
			imGroupChatInfoDto.setMerchantName(UserUtils.getMerchantName());
			imGroupChatInfoDto.setChatAssistantCode(UserUtils.getUser().getId()); // 导购助手编号
			
			FindPersonMember findPersonMember = new FindPersonMember();
			findPersonMember.setMerchantNo(UserUtils.getMerchantNo());
			findPersonMember.setMemberNos(imGroupChatInfoDto.getMemberNos());
			List<FindPersonMemberReturn> list = personMemberService.findPersonMemberByMemberNoAndMerchantNo(findPersonMember);
			Map<String,FindPersonMemberReturn> map = new HashMap<>();
			if(null != list && list.size()>0){
				for (FindPersonMemberReturn findPersonMemberReturn : list) {
					map.put(findPersonMemberReturn.getMemberNo(), findPersonMemberReturn);
				}
				
			}
			
			String[] memberNos = imGroupChatInfoDto.getMemberNos().split(",");
			StringBuilder memberNoGm = new StringBuilder("");
			StringBuilder memberNameGm = new StringBuilder("");
			for (String string : memberNos) {
				memberNoGm = memberNoGm.append(map.get(string).getMemberNoGm()).append(",");
				memberNameGm = memberNameGm.append(map.get(string).getMemberNameGm()).append(",");
			}
			if(memberNoGm.length()>0){
				memberNoGm.deleteCharAt(memberNoGm.length()-1);
				memberNameGm.deleteCharAt(memberNameGm.length()-1);
			}
			imGroupChatInfoDto.setMemberNoGm(memberNoGm.toString());
			imGroupChatInfoDto.setMemberNameGm(memberNameGm.toString());
			String code =iImGroupChatInfoService.addImGroupChatInfo(imGroupChatInfoDto);
			
			sendGroupChatInfo(code);
			return ResponseDto.successResp(null);
		} catch (TsfaException e) {
			logger.error("新增群发设置失败 {}",e);
			return ResponseDto.failureResp(e.getExceptionCode(), e.getExceptionInfo());
		}
	}

	/**
	 * 再发一条
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = { "sendChatMessage" })
	@ResponseBody
	public ResponseDto sendChatMessage(Model model, String code) {
		if (StringUtils.isEmpty(code)) {
			return ResponseDto.failureResp("0", "参数错误！");
		}

		try {
			return sendGroupChatInfo(code);
		} catch (TsfaServiceException e) {
			if (com.lj.business.weixin.constant.ErrorCode.INCLUDE_SENSITIVE_WORDS.equals(e.getExceptionCode())) {
				return ResponseDto.failureResp(e.getExceptionCode(), "发送内容包含敏感字符，发送失败");
			} else if (com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE.equals(e.getExceptionCode())) {
				return ResponseDto.failureResp(e.getExceptionCode(), "中控客户端已离线，发送失败！");
			}
			return ResponseDto.failureResp("0", "发送失败!");
		} catch (Exception e) {
			logger.error("群发聊天记录错误：" + e);
			return ResponseDto.failureResp("0", "发送失败!");
		}
	}

	private ResponseDto sendGroupChatInfo(String code) {
		ImGroupChatInfoDto imGroupChatInfoDto = new ImGroupChatInfoDto();
		imGroupChatInfoDto.setCode(code);
		ImGroupChatInfoDto groupChatInfoDto = iImGroupChatInfoService.findImGroupChatInfo(imGroupChatInfoDto);
		if (null == groupChatInfoDto) {
			return ResponseDto.failureResp("0", "群发设置不存在！");
		}
		
		String[] memberNos = groupChatInfoDto.getMemberNos().split(",");
		for (int i = 0; i < memberNos.length; i++) {
			String str = memberNos[i];
			taskExecutor.execute(new Runnable() { // 通过线程池发送
				@Override
				public void run() {
					logger.info("向客户{}发送群发消息", str);
					/**
					 * 线程沉睡5秒
					 */
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						logger.error("沉睡当前线程失败", e);
					}

					SendImChatInfo sendImChatInfo = new SendImChatInfo();
					sendImChatInfo.setType(groupChatInfoDto.getType());
					sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
					sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
					sendImChatInfo.setChatAssistantCode(UserUtils.getUser().getId()); // 导购助手编号
					sendImChatInfo.setChatAssistantName(UserUtils.getUser().getName()); // 导购助手名称
					sendImChatInfo.setChatroomType(ChatRoomType.PERSONAL.getCode());
					sendImChatInfo.setMemberNo(str);
					sendImChatInfo.setNoWxGm(groupChatInfoDto.getNoWxGm());
					
					switch (sendImChatInfo.getType()) {
					case "1": // 文本 文本消息内容不转义
						sendImChatInfo.setContent(StringEscapeUtils.unescapeHtml4(groupChatInfoDto.getContent()).toString());
						imChatInfoService.sendChat(sendImChatInfo);
						break;
					default: // 其他：语音、图片等
						sendImChatInfo.setResources(groupChatInfoDto.getResourcesPath());
						imChatInfoService.sendChat(sendImChatInfo);
						break;
					}

				}
			});
		}
		
		return ResponseDto.successResp(null);
	}

}
