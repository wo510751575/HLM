package com.lj.business.weixin.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.dto.imchat.FindChatInfoPageFromWeb;
import com.lj.business.weixin.dto.imchat.FindMemberChatCount;
import com.lj.business.weixin.dto.imchat.FindMemberChatCountReturn;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfo;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMember;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByTerminal;
import com.lj.business.weixin.dto.imchat.ReceivedOfflineChatInfo;
import com.lj.business.weixin.dto.imchat.SendChatByNewPerson;
import com.lj.business.weixin.dto.imchat.SendImChatByNonMember;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateThirdHaveReadFromWeb;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IWxSmallProgramService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class ImChatInfoServiceImplTest extends SpringTestCase{

	@Resource
	IImChatInfoService imChatInfoService;
	
	@Resource
	private ICommonService chatService;
	
	@Resource
	private IWxSmallProgramService wxSmallProgramService;
	
	@Test
	public void testAddImChatInfo() {
		for (int i = 0; i < 10000; i++) {
			AddImChatInfo addImChatInfo = new AddImChatInfo();
			addImChatInfo.setCode(GUID.generateByUUID());
			addImChatInfo.setNoWxGm("ljkj6485");
			addImChatInfo.setNoWx("duanzhipeng520");
			addImChatInfo.setMemberNo("LJ_f2ca33d2da0848e1835f58373bf7bd2e");
			addImChatInfo.setMemberNoGm("40fc7136e05a469e9cf2977d468faeb4");
			addImChatInfo.setSenderFlag(2);
			addImChatInfo.setType("1");
			addImChatInfo.setChatTime(new Date());
			addImChatInfo.setContent("eeddd" + System.currentTimeMillis());
			addImChatInfo.setMsgSource(2);
			addImChatInfo.setStatus("1");
			imChatInfoService.addImChatInfo(addImChatInfo);
		}
	}

	@Test
	public void testFindOfflineChatInfo() throws ParseException {
		FindOfflineChatInfo findOfflineChatInfo = new FindOfflineChatInfo();
		findOfflineChatInfo.setMemberNoGm("LJ_46cd02d357c44a9cab943623bc323e03");
//		findOfflineChatInfo.setNoWxGm("fhly75");
		findOfflineChatInfo.setClientFlag(SenderFlag.GM.getCode());
//		findOfflineChatInfo.setLastLoginTime(DateUtils.parseDate("2018-11-12 16:28:00", DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
		imChatInfoService.findOfflineChatInfo(findOfflineChatInfo);
	}
	
	@Test
	public void testReceivedOfflineChatInfo() throws ParseException {
		ReceivedOfflineChatInfo receivedOfflineChatInfo = new ReceivedOfflineChatInfo();
		receivedOfflineChatInfo.setMemberNoGm("da08e545a349485aa8faa42a34d43978");
//		receivedOfflineChatInfo.setNoWxGm("fhly75");
		receivedOfflineChatInfo.setChatTimeBegin(DateUtils.parseDate("2017-11-02 05:54:25", DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
		receivedOfflineChatInfo.setChatTimeEnd(DateUtils.parseDate("2017-11-12 05:54:25", DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
		imChatInfoService.receivedOfflineChatInfo(receivedOfflineChatInfo);
	}
	
	@Test
	public void testFindUnreadPersonCountByWx() {
		FindUnreadCountByTerminal findUnreadCountByTerminal = new FindUnreadCountByTerminal();
		findUnreadCountByTerminal.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		List<String> noWxList = new ArrayList<String>();
//		noWxList.add("lj8740722");
		noWxList.add("meilihome2017");
//		noWxList.add("cherrynizi610");
//		noWxList.add("cherrynizi610www");
//		noWxList.add("lj224480");
		findUnreadCountByTerminal.setNoWxList(noWxList);
		System.out.println(imChatInfoService.findUnreadPersonCountByWx(findUnreadCountByTerminal));
	}
	
	@Test
	public void testFindUnreadCountByMemberFromWeb() {
		FindUnreadCountByMember findUnreadCountByMember = new FindUnreadCountByMember();
//		findUnreadCountByMember.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
//		findUnreadCountByMember.setShopNo("LJ_accb0893636244d19e1bf4bf86120e41");
		findUnreadCountByMember.setNoWxShop("lj87407");
		List<String> memberNoList = new ArrayList<String>();
		memberNoList.add("c37758dff62648f8bf6aeba066531a02");
		memberNoList.add("ffab22973a004e62b7fa01c31fc8b107");
		findUnreadCountByMember.setMemberNoList(memberNoList);
//		findUnreadCountByMember.setChatTimeBegin(new Date());
		imChatInfoService.findUnreadCountByMemberFromWeb(findUnreadCountByMember);
	}
	
	@Test
	public void testFindChatInfoPageFromWeb() {
		FindChatInfoPageFromWeb findChatInfoPageFromWeb = new FindChatInfoPageFromWeb();
//		findChatInfoPageFromWeb.setMemberNoGm("2cdfe34df0bc4c6c8563e5e92b469b5c");
		findChatInfoPageFromWeb.setMemberNo("5f06ab8c628348d59662a04e6985abb3");
		//findChatInfoPageFromWeb.setThirdReadFlag(0);
		//findChatInfoPageFromWeb.setChatTimeBegin(DateUtils.getPreday(new Date()));
		System.out.println(imChatInfoService.findChatInfoPageFromWeb(findChatInfoPageFromWeb));
	}
	
	@Test
	public void testUpdateThirdHaveReadFromWeb() {
		UpdateThirdHaveReadFromWeb updateThirdHaveReadFromWeb = new UpdateThirdHaveReadFromWeb();
		updateThirdHaveReadFromWeb.setMemberNoGm("da08e545a349485aa8faa42a34d43978");
		updateThirdHaveReadFromWeb.setMemberNo("2169d1820e254e86b110dff73e1bd58f");
		updateThirdHaveReadFromWeb.setChatTimeBegin(DateUtils.getPreday(new Date()));
		updateThirdHaveReadFromWeb.setChatTimeEnd(new Date());
		imChatInfoService.updateThirdHaveReadFromWeb(updateThirdHaveReadFromWeb);
	}
	
	@Test
	public void testSendChat() {
//		SendImChatInfo chat = new SendImChatInfo();
//		chat.setMsgId(GUID.generateByUUID());
//		chat.setResend(Boolean.FALSE);
////		chat.setMemberNoGm("LJ_ddf9e09479c2415182707c1dcb79d2b1");
//		chat.setMemberNo("28742694f2824197afedf29dc9a8c519");
//		chat.setType("494");
//		// 查询小程序信息
//		FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
//		findWxSmallProgram.setCode("07e420a0b6bc4075a00f4f777b65072d");
//		FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService.findWxSmallProgram(findWxSmallProgram);
//		chat.setResources(findWxSmallProgramReturn.getSpLogo());
//		chat.setShareTitle(findWxSmallProgramReturn.getSpName());
//		chat.setShareDes(findWxSmallProgramReturn.getSpDesc());
//		chat.setShareUrl(findWxSmallProgramReturn.getSpUrl());
//		Map<String, String> contentMap = new HashMap<>();
//		contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY, findWxSmallProgramReturn.getWxParam());
//		chat.setContent(JsonUtils.jsonFromObject(contentMap));
//		chat.setMsgSource(1);
		
		
		SendImChatInfo sendImChatInfo = new SendImChatInfo();
        sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
        sendImChatInfo.setNoWxGm("wxid_eygaiwtbotfj22");
        sendImChatInfo.setMemberNo("LJ_6417ccc79d184ed2bed181d8687efabb");
        sendImChatInfo.setType(ChatInfoType.SHARE.getCode());
        sendImChatInfo.setResources("http://192.168.3.7/oms/image/activity/3bb7a8a7-2fee-4085-aad4-60196c2233b1.jpg");
        sendImChatInfo.setShareTitle("百度");
        sendImChatInfo.setShareDes("百度一下，你就知道");
        sendImChatInfo.setShareUrl("www.baidu.com");
        sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
        sendImChatInfo.setAllowZkOffline(Boolean.TRUE);    // 允许离线发送到中控客户端
		imChatInfoService.sendChat(sendImChatInfo);
	}
	
	@Test
	public void testSendChatByNonMember() {
		SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
		chatByNonMember.setAllowZkOffline(true);
		chatByNonMember.setNoWxShop("czm5374");
		chatByNonMember.setNoWx("qiashinidewenrou9351");
		chatByNonMember.setAlias("qiashinidewenrou9351");
		chatByNonMember.setType("1");
		chatByNonMember.setContent("测试" + System.currentTimeMillis());
		chatByNonMember.setMsgSource(MessageSource.SA.getCode());
		imChatInfoService.sendChatByNonMember(chatByNonMember);
	}
	
	@Test
	public void testSendChatByNewPerson() {
		SendChatByNewPerson sendChatByNewPerson = new SendChatByNewPerson();
		sendChatByNewPerson.setMemberNoGm("71deb4191b764764a5927c7f93d5e142");
		sendChatByNewPerson.setMemberNameGm("车仔面");
		sendChatByNewPerson.setMemberNo("55aa2fa8070f46cc971877d1d40d2301");
		sendChatByNewPerson.setMemberName("Dovesshan");
		sendChatByNewPerson.setNoWxGm("czm5374");
		sendChatByNewPerson.setNoWx("qiashinidewenrou9351");
		sendChatByNewPerson.setAlias("qiashinidewenrou9351");
//		sendChatByNewPerson.setShopNo("LJ_7347955bc5b64d8dbddf07d8cd4228d5");
		imChatInfoService.sendChatByNewPerson(sendChatByNewPerson);
	}
	
	@Test
	public void findImChatInfoByMecCount() {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantNo", "87b828cab9f64d37b2eacc4ce5cc0eab");
		paramMap.put("noWxGm", "lj224480");
		paramMap.put("noWx", "wxid_hexyz4z1c7th12");
		int count = imChatInfoService.findImChatInfoByMecCount(paramMap);
		System.out.println(count);
	}
	
	@Test
	public void stsMemberChatCount() {
		FindMemberChatCount findMemberChatCount = new FindMemberChatCount();
//		findMemberChatCount.setShopNo(null);
//		findMemberChatCount.setShopNos(null);
		findMemberChatCount.setMerchantNo("96f4d5ddad504998af92bbfda6dd4153");
		int flag = 4;	// 1-24小时内、2-7天内、3-14天内、4-一个月（30天）内
		switch (flag) {
		case 1:		// 24小时内
			findMemberChatCount.setChatBeginTime(DateUtils.addHours(new Date(), -24));
			break;
		case 2:		// 7天内
			findMemberChatCount.setChatBeginTime(DateUtils.getDateByFirstSeconds(DateUtils.addDays(new Date(), -6)));
			break;
		case 3:		// 14天内
			findMemberChatCount.setChatBeginTime(DateUtils.getDateByFirstSeconds(DateUtils.addDays(new Date(), -13)));
			break;
		case 4:		// 一个月（30天）内
		default:
			findMemberChatCount.setChatBeginTime(DateUtils.getDateByFirstSeconds(DateUtils.addDays(new Date(), -29)));
			break;
		}
		findMemberChatCount.setChatEndTime(null);
		List<FindMemberChatCountReturn> list = imChatInfoService.stsMemberChatCount(findMemberChatCount);
		logger.info("" + list);
	}
	
	/**
	 * 
	 *
	 * 方法说明：请求中控获取视频消息视频文件
	 *
	 * @param uploadChatVideoMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月3日
	 *
	 */
//	@Test
//	public void sendUploadChatVideoMessage() {
//		FindImChatInfo findImChatInfo = new FindImChatInfo();
//		findImChatInfo.setCode("5702570cbb6943fba6b42e28133bf302");
//		FindImChatInfoReturn findImChatInfoReturn = imChatInfoService.findImChatInfo(findImChatInfo);
//		
//		UploadChatVideoMessage uploadChatVideoMessage = new UploadChatVideoMessage();
//		uploadChatVideoMessage.setNoWxShop(findImChatInfoReturn.getNoWxGm());
//		uploadChatVideoMessage.setMsgId(findImChatInfoReturn.getCode());
//		uploadChatVideoMessage.setContent(findImChatInfoReturn.getContent());
//		chatService.sendUploadChatVideoMessage(uploadChatVideoMessage);
//	}
	
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
	@Test
	public void toCancelChatInfo() {
		imChatInfoService.toCancelChatInfo("");
	}
}
