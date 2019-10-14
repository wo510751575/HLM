package com.lj.business.supcon.netty;

import java.util.HashMap;
import java.util.Map;

import com.lj.base.core.encryption.MD5;
import com.lj.base.core.util.GUID;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;
import com.lj.business.supcon.dto.chatroom.CreateChatRoomMessage;
import com.lj.business.supcon.netty.common.ClientBaseService;
import com.lj.business.supcon.netty.enums.ConnectSource;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chat.ChatInfoRequest;
import com.lj.business.supcon.netty.message.chat.FindChatImageRequest;
import com.lj.business.supcon.netty.message.chatroom.CreateChatRoomRequest;
import com.lj.business.supcon.netty.message.common.TerminalBatteryNotify;
import com.lj.business.supcon.netty.message.contacts.FindWxInfoRequest;
import com.lj.business.supcon.netty.message.contacts.ScanAddFriendRequest;
import com.lj.business.supcon.netty.message.contacts.SyncWxInfoRequest;
import com.lj.business.supcon.netty.message.login.LoginRequest;


public class ClientStartup {
	
	public static final String URL = "http://192.168.3.7/api/";
	public static final String MOBILE = "18612311111";
	public static final String PWD = MOBILE.substring(MOBILE.length()-6);
	
	public static final String MEMBER_NO_GM =  "LJ_4b4192b4936044738dd00d3ddcd091c8";

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		MessageCode.init();
		//与服务端建立连接
		new Thread() {
			public void run() {
				new SocketClient().start();
			};
		}.start();
		
		Thread.currentThread().sleep(2000);
		
//		final String token = appLogin();
//		final String token = zkLogin();
		
		final String token="030358358f5c49a1bed33ba22cdedcbb";//中控key
//		final String token="7264d5917a5943219141ca428d1b3bee";//客戶key
		// 登录
		LoginRequest request = new LoginRequest();
		//request.setMemberNoGm("fanfan558989");
		request.setToken(token);
//		request.setToken("a132063138ca4fff8449535e7c275480");
		request.setTimestamp(System.currentTimeMillis());
		request.setType(ConnectSource.ZK.name());
//		request.setImei("866036033088987");
		Message message = new Message(MessageCode.LoginRequest, GUID.generateByUUID(), request.toJson());
		ClientBaseService.INSTANCE.sendServerRequest(message);
		terminalStatus();
		// 查询客户微信基本信息
//		findWxInfo();

		// 导购扫客户微信二维码加好友
//		scanAddFriend();
		
		// 发送聊天消息
//		chatInfo();
		
		// 终端状态信息
//		terminalStatus();
		
		// 客户微信基本信息同步
//		syncWxInfo();
		
		// 获取聊天记录图片
//		findChatImage();
		
		//创建群聊
//		createChatRoom();
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取聊天记录图片
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月31日
	 *
	 */
	public static void findChatImage() {
		FindChatImageRequest request = new FindChatImageRequest();
		request.setMsgId("91334da663b0458e8ca14783df89a329");
		request.setType(1);
		Message message = new Message(MessageCode.FindChatImageRequest, request.toJson());
		ClientBaseService.INSTANCE.sendServerRequest(message);
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户微信基本信息同步请求
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月10日
	 *
	 */
	public static void syncWxInfo() {
		SyncWxInfoRequest request = new SyncWxInfoRequest();
		request.setMemberNoGm("506c01c0fe2e4c0e87e50305d88d8e04");
		request.setMemberNo("5f06ab8c628348d59662a04e6985abb3");
		request.setNoWx("jiao11yu");
		request.setVersion(1515125094663L);
		Message message = new Message(MessageCode.SyncWxInfoRequest, request.toJson());
		ClientBaseService.INSTANCE.sendServerRequest(message);
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送聊天消息
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月2日
	 *
	 */
	public static void chatInfo() {
		ChatInfoRequest chatInfoRequest=new ChatInfoRequest();
		chatInfoRequest.setMemberNoGm("da08e545a349485aa8faa42a34d43978");
		chatInfoRequest.setNoWxGm("fhly75");
		chatInfoRequest.setMemberNo("dd6e8a94b9e04f018883d72a7be3de1e");
		chatInfoRequest.setNoWx("KENNYvip6688");
		chatInfoRequest.setType("1");
		chatInfoRequest.setContent("contentttttttt");
		chatInfoRequest.setResources("resourcesPath");
		chatInfoRequest.setShareTitle("shareTitle");
		chatInfoRequest.setShareDes("shareDes");
		chatInfoRequest.setShareUrl("shareUrl");
		Message message = new Message(MessageCode.ChatInfoRequest, chatInfoRequest.toJson());
		ClientBaseService.INSTANCE.sendServerRequest(message);
	}
	
	/**
	 * 
	 *
	 * 方法说明：终端状态信息
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月2日
	 *
	 */
	public static void terminalStatus() {
		TerminalBatteryNotify request = new TerminalBatteryNotify();
		request.setBatteryLevel(10);
		Message message = new Message(MessageCode.TerminalBatteryNotify, request.toJson());
		ClientBaseService.INSTANCE.sendServerRequest(message);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询客户微信基本信息
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月2日
	 *
	 */
	public static void findWxInfo() {
		FindWxInfoRequest findWxInfoRequest = new FindWxInfoRequest();
		findWxInfoRequest.setMemberNoGm(MEMBER_NO_GM);
		findWxInfoRequest.setWxQrCode("ddddd");
		Message message = new Message(MessageCode.FindWxInfoRequest, findWxInfoRequest.toJson());
		ClientBaseService.INSTANCE.sendServerRequest(message);
	}
	
	/**
	 * 
	 *
	 * 方法说明：导购扫客户微信二维码加好友
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月2日
	 *
	 */
	public static void scanAddFriend() {
		ScanAddFriendRequest scanRequest = new ScanAddFriendRequest();
		scanRequest.setMemberNoGm(MEMBER_NO_GM);
		scanRequest.setWxQrCode("ddddd");
		scanRequest.setNickRemark("小小" + System.currentTimeMillis());
		scanRequest.setMobile("15878945687");
		scanRequest.setValidation("你好");
		Message message = new Message(MessageCode.ScanAddFriendRequest, scanRequest.toJson());
		ClientBaseService.INSTANCE.sendServerRequest(message);
	}
	
	/**
	 * 创建群聊
	 */
	public static void createChatRoom() {
		CreateChatRoomRequest createChatRoomRequest = new CreateChatRoomRequest();
//		createChatRoomRequest.setMemberNoGm(memberNoGm);
		createChatRoomRequest.setUserNames("boer00001,KENNYvip6688,wxid_wv737da4uw1i21");
//		for (int i = 0; i < 50; i++) {
//			createChatRoomRequest.setRoomNickName("test"+i);
			createChatRoomRequest.setRoomNickName("test");
			Message message = new Message(MessageCode.CreateChatRoomRequest, createChatRoomRequest.toJson());
			ClientBaseService.INSTANCE.sendServerRequest(message);
//		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：APP登录
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月8日
	 *
	 */
	public static String appLogin() {
		Map<String,String> params = new HashMap<String,String>();
		params.put("appKey", "zhongkong");
		String timestamp = String.valueOf(System.currentTimeMillis());
		params.put("timestamp", timestamp);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("mobile", MOBILE);
		paramMap.put("pwd", MD5.encryptByMD5(PWD));
		String paramJson = JsonUtils.jsonFromObject(paramMap);
		params.put("paramJson", paramJson);
		String signature = MD5.encryptByMD5Twice(paramJson, timestamp + "013cXuH9vf584W0x");
		params.put("signature", signature);
		params.put("token", "");
		String result = HttpClientUtils.postToWeb(URL + "member/appLogin.do", params);
		System.out.println("result= " + result); 
		Map<String, String> responseMap = JsonUtils.mapFromJson(result);
		Map<String, String> returnObjectMap = JsonUtils.mapFromJson(responseMap.get("returnObject"));
		String token = returnObjectMap.get("token");
//		System.out.println(token);
		return token;
	}
	
	/**
	 * 中控登录
	 * @return
	 */
	public static String zkLogin() {
		Map<String,String> params = new HashMap<String,String>();
		params.put("appKey", "zhongkong");
		String timestamp = String.valueOf(System.currentTimeMillis());
		params.put("timestamp", timestamp);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("mobile", MOBILE);
		paramMap.put("pwd", MD5.encryptByMD5(PWD));
		String paramJson = JsonUtils.jsonFromObject(paramMap);
		params.put("paramJson", paramJson);
		String signature = MD5.encryptByMD5Twice(paramJson, timestamp + "013cXuH9vf584W0x");
		params.put("signature", signature);
		params.put("token", "");
		String result = HttpClientUtils.postToWeb(URL + "member/zkLogin.do", params);
		System.out.println("result= " + result); 
		Map<String, String> responseMap = JsonUtils.mapFromJson(result);
		Map<String, String> returnObjectMap = JsonUtils.mapFromJson(responseMap.get("returnObject"));
		String token = returnObjectMap.get("token");
//		System.out.println(token);
		return token;
	}
	
}
