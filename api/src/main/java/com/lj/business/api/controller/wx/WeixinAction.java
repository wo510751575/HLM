/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ape.common.utils.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;
import com.lj.base.mvc.web.util.XMLParser;
import com.lj.base.mvc.web.util.XmlUtils;
import com.lj.base.mvc.wx.WeixinSignUtil;
import com.lj.base.mvc.wx.WxUserInfoDto;
import com.lj.base.mvc.wx.aes.AesException;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.service.WeixinApiService;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberExtService;
import com.lj.business.member.service.ITmallOrderService;

/**
 * 
 * 类说明：微信公众号授权接口。
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 领居科技有限公司
 * @author lhy
 * 
 *         CreateDate: 2017年11月20日
 */
@RestController
@RequestMapping("/wx")
public class WeixinAction extends Action {

	@Autowired
	private WeixinApiService weixinApiService;
	@Resource
	private IPersonMemberExtService personMemberExtService;
	@Autowired
	private ITmallOrderService tmallOrderService;
	@Autowired
	private IPersonMemberBaseService personMemberBaseService;
	/**
	 * 方法说明：静默授权认证页。
	 * <p>
	 *
	 * @param url        微信回传code的URL
	 * @param merchantNo 商户号
	 * @return
	 * @author lhy 2017年9月6日
	 *
	 */
	@RequestMapping(value = "/base/isAuthorize.do")
	@ResponseBody
	public GeneralResponse getOpenIdAuthorize(String url, String merchantNo) {
		if (StringUtils.isBlank(url) || StringUtils.isBlank(merchantNo)) {
			return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR, "请求参数错误！");
		}
		String wxUrl = weixinApiService.getBaseCode(url, merchantNo);
		return GeneralResponse.generateSuccessResponse(wxUrl);
	}

	/**
	 * 方法说明：导购发送订单链接给客户后，用户打开链接绑定openid信息。
	 * 
	 * @param openId     openId
	 * @param merchantNo 商户号
	 * @param memberNo   会员号
	 * @return
	 *
	 * @author
	 *
	 */
	@RequestMapping(value = "/openid/bind.do", method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse bindOpenId(String openId, String merchantNo, String memberNo) {
		if (StringUtils.isBlank(openId)) {
			return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR, "请求参数错误！");
		}
		if (StringUtils.isBlank(merchantNo)) {
			return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR, "请求参数错误！");
		}
		if (StringUtils.isBlank(memberNo)) {
			return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR, "请求参数错误！");
		}
		logger.info("绑定微信openId[openId:" + openId + ",merchantNo:" + merchantNo);

		// String openId =weixinApiService.getOpenId(wxCode,merchantNo);

		// 绑定用户的逻辑在此写
		personMemberExtService.bindMemberNo(memberNo, openId);

		logger.info("绑定微信openId end.");
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 方法说明：认证授权认证页。
	 * <p>
	 *
	 * @param url        微信回传code的URL
	 * @param merchantNo 商户号
	 * @return
	 * @author lhy 2017年9月6日
	 *
	 */
	@RequestMapping(value = "/userinfo/isAuthorize.do")
	@ResponseBody
	public GeneralResponse getUserinfoAuthorize(String url, String merchantNo) {
		if (StringUtils.isBlank(url)/* || StringUtils.isBlank(merchantNo) */) {
			return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR, "请求参数错误！");
		}
		String wxUrl = weixinApiService.getCode(url, merchantNo);
		return GeneralResponse.generateSuccessResponse(wxUrl);
	}

	/**
	 * 方法说明：获取微信用户。
	 * <p>
	 *
	 * @param url        微信回传code的URL
	 * @param merchantNo 商户号
	 * @return
	 * @author lhy 2017年9月6日
	 *
	 */
	@RequestMapping(value = "/getuserinfo.do")
	@ResponseBody
	public GeneralResponse getWxUserinfo(String wxCode, String merchantNo) {
		if (StringUtils.isBlank(wxCode)/* || StringUtils.isBlank(merchantNo) */) {
			return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR, "请求参数错误！");
		}
		WxUserInfoDto userInfo = weixinApiService.getUserInfo(wxCode, merchantNo);
		return GeneralResponse.generateSuccessResponse(userInfo);
	}

	/**
	 * 方法说明： js sdk 签名。
	 * <p>
	 * 用于 微信 JSSDK使用，比如分享。
	 * 
	 * @param url          当前网页的URL，不包含#及其后面部分
	 * @param merchantCode 电商系统商户号
	 * @return
	 *
	 * @author lhy 2017年9月12日
	 *
	 */
	@RequestMapping(value = "/jsconfig.do", method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse jsSdkSign(String url, String merchantCode) {
		logger.info("Url:" + url + ",merchantCode:" + merchantCode);
		try {
			if (StringUtils.isBlank(url) || StringUtils.isBlank(merchantCode)) {
				return GeneralResponse.generateFailureResponse(ErrorCode.PARAM_ERROR, "请求参数错误！");
			}
			Map<String, Object> jsSdk = weixinApiService.getJsapiSign(url, merchantCode);
			return GeneralResponse.generateSuccessResponse(jsSdk);
		} catch (Exception e) {
			Map map = new HashMap<>();
			map.put("appId", "wx92ef5476a9dbe24e");
			map.put("nonceStr", "3j1ysdovkm52zkj5gqkd4852voz2azjn");
			map.put("signature", "40460477b785cdb3a4b858101af7bc08311c86a3");
			map.put("timestamp", "1560926371");
			return GeneralResponse.generateSuccessResponse(map);
		}
	}

	/**
	 * 给微信验证Token 1.微信基本配置 2.Token 3.接收消息，关注，或普通消息
	 * 
	 * @param request
	 * @return
	 * @throws AesException
	 */
	@RequestMapping(value = "/verifyWxToken.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public void verifyWXToken(HttpServletRequest request, HttpServletResponse response) throws AesException {
		logger.info("verifyWXToken(HttpServletRequest request={}", request.getParameterMap().toString());
		String msgSignature = request.getParameter("signature");
		String msgTimestamp = request.getParameter("timestamp");
		String msgNonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		echostr = StringUtils.isEmpty(echostr) ? "success" : echostr;
		if (WeixinSignUtil.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
			logger.info("verifyWXToken return echostr={}", echostr);
			String openId="";
			String wx="";
			try {
				// 接受微信服务器发送过来的XML形式的消息
				String sReqData = streamToString(request);
				logger.info("收到消息：{}", sReqData);

//        		String fromXML = sReqData;
				// 第三方收到公众号平台发送的消息
//        		WXBizMsgCrypt pc = new WXBizMsgCrypt(Constants.TOKEN, Constants.EncodingAESKey, Constants.appid);
//        		String result = pc.decryptMsg(msgSignature, msgTimestamp, msgNonce, fromXML);
//        		logger.info("解密后明文:{}",result);

				if(StringUtils.isNotEmpty(sReqData)) {
					Map<String, Object> map = XMLParser.getMapFromXML(sReqData);
					logger.info("解密后MAP:{}", map);
					openId = map.get("FromUserName").toString();
					wx = map.get("ToUserName").toString();
					String MsgType = map.get("MsgType").toString();
					
					//{CreateTime=1550826919, EventKey=, Event=subscribe, ToUserName=gh_05c668d3ac8c, FromUserName=ozeQl5zf3nJBcdorBs15cBt6XfJ0, MsgType=event}
					if (MsgType.equals("text")) {
//						String orderNo = map.containsKey("Content")&&map.get("Content")!=null?map.get("Content").toString():"";
//						logger.info("普通消息内容 ：{}", orderNo);
//
//						String amount = tmallOrderService.validateOrder(orderNo,openId);
//						if (StringUtils.isNotBlank(amount) && Integer.valueOf(amount)> 0) {
//							// 发送红包
//							String ipAddr = HttpClientUtils.getIpAddress(request);
//							logger.info("开始发送红包 ={}", openId);
//							weixinApiService.sendRedPack(openId, ipAddr,amount,orderNo);
//						} else {
//							logger.info("验证不成功，订单不满足活动要求");
//							echostr = getReplyTextMessage("验证不成功，订单不满足活动要求！",wx , openId);
//						}

					} else if (MsgType.equals("event") && map.get("Event").toString().equals("subscribe")) {
						// 点击关注：{CreateTime=1550217584, EventKey=, Event=subscribe,
						// ToUserName=gh_05c668d3ac8c, FromUserName=ozeQl5zD99qwiUeg_OZBzDMm61KM,
						// MsgType=event}
						// 取消关注：{CreateTime=1550477097, EventKey=, Event=unsubscribe,
						// ToUserName=gh_05c668d3ac8c, FromUserName=ozeQl5zf3nJBcdorBs15cBt6XfJ0,
						// MsgType=event}
						logger.info("点击关注：感谢关注碧生源，回复订单号领取红包！");
//						echostr =getReplyTextMessage("感谢关注碧生源，回复订单号领取红包！",wx , openId);
						
						//关注发红包
						//获取用户基本信息
						FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
						findPersonMemberBase.setOpenIdGzhWx(openId);
						FindPersonMemberBaseReturn baseReturn= personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
						if(null !=baseReturn && StringUtils.isNotEmpty(baseReturn.getOrderNo())) {
							String orderNo = baseReturn.getOrderNo();
							String amount = tmallOrderService.validateOrder(orderNo,openId);
							if (StringUtils.isNotBlank(amount) && Integer.valueOf(amount)> 0) {
								// 发送红包
								String ipAddr = HttpClientUtils.getIpAddress(request);
								logger.info("开始发送红包 ={}", openId);
								weixinApiService.sendRedPack(openId, ipAddr,amount,orderNo);
							} else {
								logger.info("验证不成功，订单不满足活动要求");
								echostr = getReplyTextMessage("验证不成功，订单不满足活动要求！",wx , openId);
							}
						}
						
						
					}
				}
			} catch (TsfaServiceException e) {
				e.printStackTrace();
				logger.error("TsfaServiceException={}", e.getExceptionInfo());
				echostr = getReplyTextMessage(e.getExceptionInfo(),wx , openId);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception={}", e);
			}finally {
				try {
					// 防止中文乱码
					response.setCharacterEncoding("UTF-8");
					PrintWriter print = response.getWriter();
					print.write(echostr);
					print.flush();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("writeException={}", e.getMessage());
				}
				
			}

		}
	}

	/**
	 * 回复文本消息
	 * 
	 * @param content	内容
	 * @param fromUserName	开发者微信号
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @return
	 */
	private String getReplyTextMessage(String content, String fromUserName, String toUserName) {
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("ToUserName",toUserName);
		parameters.put("FromUserName",fromUserName);
		parameters.put("CreateTime",new Date().getTime());
		parameters.put("MsgType","text");
		parameters.put("Content",content);
		String xml = XmlUtils.toXml(parameters, null);
		return xml;
	}

	private String streamToString(HttpServletRequest request) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
