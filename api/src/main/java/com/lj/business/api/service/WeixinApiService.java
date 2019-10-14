/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ape.common.utils.StringUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;
import com.lj.base.mvc.web.util.Util;
import com.lj.base.mvc.web.util.XMLParser;
import com.lj.base.mvc.web.util.XmlUtils;
import com.lj.base.mvc.wx.WeixinConfigDto;
import com.lj.base.mvc.wx.WeixinSignUtil;
import com.lj.base.mvc.wx.WxCfgConstant;
import com.lj.base.mvc.wx.WxUserInfoDto;
import com.lj.base.mvc.wx.redpack.SendRedPackPo;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.common.Constants;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindTmallOrderPage;
import com.lj.business.member.dto.TmallBonusRecordDto;
import com.lj.business.member.dto.TmallOrderDto;
import com.lj.business.member.emus.BonusStatus;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.ITmallBonusRecordService;
import com.lj.business.member.service.ITmallOrderService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.GroupName;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.IDistributeCache;
 



/**
 * 
 * 类说明：微信公众号授权API对接。<p>
 *  
 * 	1 第一步：用户同意授权，获取code
 *	2 第二步：通过code换取网页授权access_token
 *	3 第三步：刷新access_token（如果需要）
 *	4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author lhy
 *   
 * CreateDate: 2017年9月6日
 */
@Service
public class WeixinApiService {
	private static final Logger LOG = LoggerFactory .getLogger(WeixinApiService.class);
	
	public static final String TICKET_REDIS_KEY_PREFIX = "MECWX_TICKETREDISKEY_";
	public static final String ACCESS_TOKEN_BIN_PREFIX = "MECWX_ACCESSTOKENBIN_";
	
	public static final int TIME = 7200;
	
	@Resource 
	private IDistributeCache distributeCache;
	@Autowired 
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Autowired 
	private ITmallBonusRecordService tmallBonusRecordService;
	@Autowired 
	private IPersonMemberBaseService personMemberBaseService;
	@Autowired 
	private ITmallOrderService tmallOrderService;
	
	/**
	 *
	 * 方法说明：根据微信的商户号找到电商系统的商户公众号配置信息。
	 *
	 * @param wxMchId 微信商户号
	 * @return
	 *
	 * @author lhy  2017年9月11日
	 *
	 */
	public WeixinConfigDto getWeixinConfigByWxMchId(String wxMchId){
//		FindMerchantSettingPage find=new FindMerchantSettingPage();
//		MerchantSettingDto param=new MerchantSettingDto();
//		find.setParam(param);
//		param.setName(WEIXIN_MCHID);
//		param.setValue(wxMchId);
//		param.setStatus(MerchantSettingStatus.USE.getValue());
//		List<MerchantSettingDto> configs = merchantSettingService.findMerchantSettings(find);
//		if (configs != null && !configs.isEmpty()) {
//			MerchantSettingDto cfg=configs.get(0);
//			return getWeixinConfig(cfg.getMerchantCode());
//		}else{
//			//检测未配置该商户号则提示。
//			throw new TsfaServiceException(ResponseCode.MERCHANT_WXCFG_NULL.getCode(), ResponseCode.MERCHANT_WXCFG_NULL.getMsg());
//		}
		//商户号传入null,该处统一公众号配置
		return getWeixinConfig(null);
	}
	
	/**
	 *
	 * 方法说明：根据电商商户号找微信公众号配置信息。
	 *
	 * @param merchantNo 电商商户code
	 * @return
	 *
	 * @author lhy  2017年9月11日
	 *
	 */
	protected WeixinConfigDto getWeixinConfig(String merchantNo){
//		WeixinConfigDto config=null;
//		
//		FindMerchantSettingPage find=new FindMerchantSettingPage();
//		MerchantSettingDto param=new MerchantSettingDto();
//		find.setParam(param);
//		param.setName(WEIXIN_PREFIX);
//		param.setMerchantCode(merchantNo);
//		param.setStatus(MerchantSettingStatus.USE.getValue());
//		
//		Map<String, String> wxMap=new HashMap<String, String>();
//		List<MerchantSettingDto> configs = merchantSettingService.findMerchantSettings(find);
//		if (configs != null && !configs.isEmpty()) {
//			for (Iterator<MerchantSettingDto> iterator = configs.iterator(); iterator.hasNext();) {
//				MerchantSettingDto ele = (MerchantSettingDto) iterator.next();
//				wxMap.put(ele.getName(), ele.getValue());
//			}
//			config=new WeixinConfigDto();
//			config.setApiKey(wxMap.get(WEIXIN_APIKEY));
//			config.setAppid(wxMap.get(WEIXIN_APPID));
//			config.setMchId(wxMap.get(WEIXIN_MCHID));
//			config.setAppSecret(wxMap.get(WEIXIN_APPSECRET));
//		}
//		
//		if(config==null || StringUtils.isBlank(config.getApiKey())
//				|| StringUtils.isBlank(config.getAppid())
//				|| StringUtils.isBlank(config.getMchId())
//				|| StringUtils.isBlank(config.getAppSecret())){
//			//检测未配置完整微信公众号信息则抛异常。
//			throw new TsfaServiceException(ResponseCode .MERCHANT_WXCFG_NULL.getCode(), ResponseCode.MERCHANT_WXCFG_NULL.getMsg());
//		}
//		return config;
		
		//统一公众号，不区分商户号
		Map<String, String> wxMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.api.toString(),GroupName.mec_weixin.toString());
		WeixinConfigDto config=new WeixinConfigDto();
		config.setApiKey(wxMap.get(Constants.WEIXIN_APIKEY));
		config.setAppid(wxMap.get(Constants.WEIXIN_APPID));
		config.setMchId(wxMap.get(Constants.WEIXIN_MCHID));
		config.setAppSecret(wxMap.get(Constants.WEIXIN_APPSECRET));
		
		//仅校验APPID 和 APPSECRET
		if(config==null|| StringUtils.isBlank(config.getAppid())
				|| StringUtils.isBlank(config.getAppSecret())){
			//检测未配置完整微信公众号信息则抛异常。
			throw new TsfaServiceException(ErrorCode.PARAM_IS_EMPTY, "微信公众号配置信息不全");
		}
		return config;
	}
	
	
	 /**
     * 刷新access_token
     *
     * @param refreshToken 
     * @param merchantNo 商户号
     * @return
     */
    public Map<String, String> getRefreshToken(String refreshToken,String merchantNo) {
		Map<String, String> map = new HashMap<>();
		if (refreshToken != null) {
			String result = this.refreshToken(refreshToken,merchantNo);
			if (!result.equalsIgnoreCase("") && refreshToken != null) {
				JSONObject jsonObject = JSONObject.parseObject(result);
				try {
					// access_token
					// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
					// expires_in access_token接口调用凭证超时时间，单位（秒）
					// refresh_token 用户刷新access_token
					// openid 用户唯一标识
					// scope
					map.put("openid", jsonObject.getString("openid"));
					map.put("refreshToken",jsonObject.getString("refresh_token"));
					map.put("accessToken", jsonObject.getString("access_token"));
				} catch (Exception e) {
					throw new RuntimeException("刷新token异常",e);
				}
			}
		}
		return map;
    }

    /**
     * 刷新refreshToken获取openid
     * 
     * @param refreshToken
     * @param merchantNo 商户号
     * @return
     */
    private String refreshToken(String refreshToken,String merchantNo) {
		WeixinConfigDto config = getWeixinConfig(merchantNo);
        Map<String, String> params = new HashMap<>();
        String param = "";
        try {
            params.put("appid", config.getAppid());
            params.put("grant_type", "refresh_token");
            params.put("refresh_token", refreshToken);
            param = WeixinSignUtil.createGetReqParam(params, false);
        } catch (Exception e) {
        	throw new RuntimeException("刷新refreshToken失败", e);
            //LOG.error("刷新refreshToken失败", e);
        }
        param = WxCfgConstant.REFRESH_TOKEN_URL + "?" + param;
        return HttpClientUtils.httpGet(param);
    }
    

    /**
     * 获取公众号access_token和openid
     * @param code 用户授权的code 
     * @param merchantNo 商户号
     * @return
     */
    private Map<String, String> getAccessToken(String code,String merchantNo) {
        String result = "";
        Map<String, String> resultMap = new HashMap<>();
        try {
        	WeixinConfigDto config = getWeixinConfig(merchantNo);
            Map<String, String> map = new HashMap<>();
            map.put("appid",config.getAppid());
            map.put("code", code);
            map.put("grant_type", "authorization_code");
            map.put("secret",config.getAppSecret());
            String url = WxCfgConstant.ACSTOKEN_URL + "?" + WeixinSignUtil.createGetReqParam(map, false);
            result = HttpClientUtils.httpGet(url);
            if (!result.equals("") && result != null) {
//              access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
//              expires_in	access_token接口调用凭证超时时间，单位（秒）
//              refresh_token	用户刷新access_token
//              openid	用户唯一标识
//              scope
                JSONObject jsonObject = JSONObject.parseObject(result);
                String errcode=jsonObject.getString("errcode");
                if(StringUtils.isNotBlank(errcode)){
                	 throw new TsfaServiceException(errcode,"获取微信公众号token失败"+ jsonObject.getString("errmsg"));
                }
                resultMap.put("openid", jsonObject.getString("openid"));
                resultMap.put("refreshToken", jsonObject.getString("refresh_token"));
                resultMap.put("accessToken", jsonObject.getString("access_token"));
            }
        } catch (Exception e) {
        	 throw new RuntimeException("获取微信公众号token失败", e);
        	 //LOG.error("获取微信公众号token失败", e);
        }
        return resultMap;
    }
    
    /**
     * 方法说明：拼接code获取url.<p>
     * 弹出授权页面,授权。
     * @param url
     * @param merchantNo 商户号
     * @return
     *
     * @author lhy  2017年9月7日
     *
     */
    public String getCode(String url,String merchantNo) {
        String param = "";
        try {
        	param = getCodes(url, merchantNo, false);
        } catch (Exception e) {
        	LOG.error("拼接字符串失败", e);
        	throw new RuntimeException("拼接字符串失败", e);
        }
        return WxCfgConstant.REQCODE_URL + "?" + param;
    }
    
    /**
     * 方法说明：拼接code获取url.<br>
     * 直接静默授权。
     * @param url
     * @param merchantNo 商户号
     * @return
     *
     * @author lhy  2017年9月7日
     *
     */
    public String getBaseCode(String url,String merchantNo) {
        String param = "";
        try {
        	param = getCodes(url, merchantNo, true);
        } catch (Exception e) {
        	LOG.error("拼接字符串失败", e);
        	throw new RuntimeException("拼接字符串失败", e);
        }
        return WxCfgConstant.REQCODE_URL + "?" + param;
    }
    
    /**
     * 
     * @param url
     * @param merchantNo
     * @param base
     * @return
     */
    private String getCodes(String url,String merchantNo,boolean base) {
        Map<String, String> params = new HashMap<>();
        String param = "";
        try {
        	WeixinConfigDto config = getWeixinConfig(merchantNo);
            params.put("appid", config.getAppid());
            params.put("redirect_uri", URLEncoder.encode(url, "UTF-8"));
            params.put("response_type", "code");
            if(base){
            	 params.put("scope", "snsapi_base"); // snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo
                 // （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
            }else{
            	 params.put("scope", "snsapi_userinfo"); // snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo
                  // （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
            }
           
            params.put("state", merchantNo+"#wechat_redirect");
            param = WeixinSignUtil.createGetReqParam(params, false);
        } catch (Exception e) {
        	LOG.error("拼接字符串失败", e);
        	throw new RuntimeException("拼接字符串失败", e);
        }
        return param;
    }
     
    /**
     * 方法说明：拿取微信用户信息。
     * @todo
     * @param openId
     * @param accessToken
     * @param merchantNo 商户号
     * @return
     *
     * @author lhy  2017年9月6日
     *
     */
	private WxUserInfoDto getUserInfo(String openId,String accessToken,String merchantNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("access_token", accessToken);
		map.put("openid", openId);
		map.put("lang", "zh_CN");
		String url;
		try {
			url = WxCfgConstant.GET_USER_INFO + "?"+ WeixinSignUtil.createGetReqParam(map, false);
		} catch (UnsupportedEncodingException e) {
			 throw new RuntimeException("获取用户信息异常。",e);
		}
		LOG.info("微信获取用户信息url："+url);
		String result = HttpClientUtils.httpGet(url);
		LOG.info("微信获取用户信息结果："+result);
		JSONObject jsonObject=JSONObject.parseObject(result);
		String errcode=jsonObject.getString("errcode");
        if(StringUtils.isNotBlank(errcode)){
        	 throw new TsfaServiceException(errcode,"微信获取用户信息失败,"+ jsonObject.getString("errmsg"));
        }
		WxUserInfoDto userInfoDto=new WxUserInfoDto();
		userInfoDto.setOpenid(jsonObject.getString("openid"));
		userInfoDto.setNickname(jsonObject.getString("nickname"));
		userInfoDto.setSex(jsonObject.getString("sex"));
		userInfoDto.setProvince(jsonObject.getString("province"));
		userInfoDto.setCity(jsonObject.getString("city"));
		userInfoDto.setHeadimgurl(jsonObject.getString("headimgurl"));
		userInfoDto.setUnionid(jsonObject.getString("unionid"));
		String ps=jsonObject.getString("privilege");
		if (StringUtils.isNoneBlank(ps)) {
			JSONArray p = JSONObject.parseArray(ps);
			if(p!=null){
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < p.size(); i++) {
					list.add(p.get(i).toString());
				}
				userInfoDto.setPrivilege(list);
			}
		}
		LOG.info("微信获取用户信息结果userInfoDto--->："+userInfoDto.toString());
		return userInfoDto;
	}
	/**
	 * 方法说明：通过微信code 获取微信用户信息。
	 *
	 * @param wxCode
	 * @param merchantNo 商户号
	 * @return
	 *
	 * @author lhy  2017年9月7日
	 *
	 */
	public WxUserInfoDto getUserInfo(String wxCode,String merchantNo){
		LOG.info("getUserInfo--->wxCode={},merchantNo={}："+wxCode,merchantNo);
		//1.获取 access_token及open id
		Map<String, String> tokens=getAccessToken(wxCode,merchantNo);
		//2.获取用户信息
		WxUserInfoDto user=getUserInfo(tokens.get("openid"),tokens.get("accessToken"),merchantNo);
		return user;
	}

	/**
	 * 方法说明：根据微信code获取openId.
	 * @param wxCode 微信code
	 * @param merchantNo 商户号
	 * @return
	 *
	 * @author lhy  2017年11月20日
	 *
	 */
	public String getOpenId(String wxCode,String merchantNo){
		//1.获取 access_token及open id
		Map<String, String> tokens=getAccessToken(wxCode,merchantNo);
		return tokens.get("openid");
	}
	
	 /**
     * 
     *
     * 方法说明：JS sdk 权限验证配置。
     *
     * @param url （当前网页的URL，不包含#及其后面部分） 
     * @param merchantCode 电商系统的商户号
     * @return JS sdk 权限验证配置相关数据。<link>https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115M</link>
     *
     * @author lhy  2017年9月12日
     *
     */
    public  Map<String, Object> getJsapiSign(String url ,String merchantCode){
    	String random = WeixinSignUtil.createRandomNumber(32); //生成随机数
    	WeixinConfigDto config = getWeixinConfig(merchantCode);
    	String timestamp = Long.toString(System.currentTimeMillis() / 1000); //时间戳
    	
    	String ticket = getJsapiTicket(config);
    	LOG.info("js ticket:"+ticket);
    	//采用sha1加密
    	String ticketSign = getJsapiSign(ticket, random, timestamp, url);
      	
    	Map<String, Object> jsSdkSign = new HashMap<>();
    	jsSdkSign.put("appId", config.getAppid());
    	jsSdkSign.put("nonceStr", random);
    	jsSdkSign.put("timestamp", timestamp);
    	jsSdkSign.put("signature", ticketSign);
        return jsSdkSign;
    }
    
    /**
     * 方法说明：获取微信JSSDK API签名。
     * @param config  公众号配置
     * @param random
     * @param timestamp
     * @param url
     * @return
     *
     * @author lhy  2017年9月8日
     *
     */
    private String getJsapiSign(WeixinConfigDto config,String random,String timestamp,String url){
    	 String ticket = getJsapiTicket(config);
         String ticketSign = getJsapiSign(ticket, random, timestamp, url);
         return ticketSign;
    }
    /**
     * 方法说明：获取微信JSSDK API签名。
     * @param jsapiTicket 
     * @param random
     * @param timestamp
     * @param url
     * @return
     *
     * @author lhy  2017年9月8日
     *
     */
    private String getJsapiSign(String jsapiTicket,String random,String timestamp,String url){
    	 Map<String, Object> ticketSignMap = new HashMap<>();
         ticketSignMap.put("jsapi_ticket", jsapiTicket);
         ticketSignMap.put("noncestr", random);
         ticketSignMap.put("timestamp", timestamp);
         ticketSignMap.put("url", url);//url（当前网页的URL，不包含#及其后面部分
         //采用sha1加密
         String ticketSign = WeixinSignUtil.getSha1Sign(ticketSignMap);
         return ticketSign;
    }
  
    /**
	 * 方法说明：获取JSAPI 鉴权的票据。
	 * @param config
	 * @return
	 *
	 * @author lhy  2017年9月8日
	 *
	 */
	private String getJsapiTicket(WeixinConfigDto config) {
		String tokenKey = ACCESS_TOKEN_BIN_PREFIX + config.getAppid();
		String token = distributeCache.get(tokenKey);
		if (StringUtils.isBlank(token)) {
			token = getBinAccessToken(config);
			distributeCache.set(tokenKey, token,TIME); // 将access_token做缓存设置7200秒过期时间
		}
		String ticketKey = TICKET_REDIS_KEY_PREFIX + config.getAppid();
		String ticket = distributeCache.get(ticketKey);
		if (StringUtils.isBlank(ticket)) {
			ticket = getAccessToken(token);
			distributeCache.set(ticketKey, ticket,TIME); // 将jsapi_ticket做缓存设置7200秒过期时间
		}
		return ticket;
	}
    
    /**
     * 获取普通token
     *
     * @return
     */
    private String getBinAccessToken(WeixinConfigDto config) {
        String accessToken = "";
        String acsTokenUrl = WxCfgConstant.ACCESSTOKEN_URL + "?grant_type=client_credential&appid=" +config.getAppid() + "&secret=" + config.getAppSecret();
        String acsTokenResult = HttpClientUtils.httpGet(acsTokenUrl);
        LOG.info("acsTokenUrl=========================================" + acsTokenUrl);
        if (!acsTokenResult.equals("") && acsTokenResult != null) {
            JSONObject jsonObject =JSONObject.parseObject(acsTokenResult);
            String errcode=jsonObject.getString("errcode");
			if (StringUtils.isNotBlank(errcode)) {
           	 	throw new TsfaServiceException(errcode,"获取微信公众号access_token失败"+ jsonObject.getString("errmsg"));
            }
            accessToken = jsonObject.getString("access_token");
        }
        
        return accessToken;
    }
    
    /**
     * 获取jsapi_ticket
     *
     * @return map
     */
    private String getAccessToken(String accessToken) {
        String ticketUrl = WxCfgConstant.TICKET_URL + "?access_token=" + accessToken + "&&type=jsapi";
        String ticketResult = HttpClientUtils.httpGet(ticketUrl);
        String ticket = null;
        if (!ticketResult.equals("") && ticketResult != null) {
            JSONObject jsonObject = JSONObject.parseObject(ticketResult);
            String errcode=jsonObject.getString("errcode");
            ticket = jsonObject.getString("ticket");
            if (StringUtils.isBlank(ticket)) {
           	 	throw new TsfaServiceException(errcode,"获取微信公众号getticket失败"+ jsonObject.getString("errmsg"));
            }
           
        }
        return ticket;
    }
    
    /**
     * 发送公众号红包
     * @param openid	用户openId
     * @param client_ip 用户IP地址
     * @param totalAmount 红包金额
     * @param orderNo 天猫订单号
     * @return
     */
    public void sendRedPack(String openid,String client_ip,String totalAmount,String orderNo) throws TsfaServiceException{
    	//统一公众号，不区分商户号
		Map<String, String> wxMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.api.toString(),GroupName.mec_weixin.toString());
		LOG.info("公众号配置：{}",wxMap);
		String mchId = wxMap.get(Constants.WEIXIN_MCHID);
		String appId = wxMap.get(Constants.WEIXIN_APPID);
		String appSecret = wxMap.get(Constants.WEIXIN_APPSECRET);
		String ssLfile = wxMap.get(Constants.WEIXIN_SSLFILE);
		
		//仅校验APPID 和 APPSECRET
		if(StringUtils.isBlank(appId)|| StringUtils.isBlank(appSecret) || StringUtils.isBlank(mchId)|| StringUtils.isBlank(ssLfile)){
			//检测未配置完整微信公众号信息则抛异常。
			throw new TsfaServiceException(ErrorCode.PARAM_IS_EMPTY, "微信公众号配置信息不全");
		}
		
		SendRedPackPo sendredpack = new SendRedPackPo();
		sendredpack.setAct_name("微信红包");
		sendredpack.setNonce_str(GUID.generateByUUID());
		sendredpack.setRe_openid(openid);
		sendredpack.setClient_ip(client_ip);
		sendredpack.setMch_billno(Util.getMchBillno(mchId));
		sendredpack.setMch_id(mchId);
		sendredpack.setRemark(orderNo);	//天猫订单
		sendredpack.setSend_name("碧生源");
		sendredpack.setTotal_amount(totalAmount);
		sendredpack.setTotal_num("1");
		sendredpack.setWishing("感谢您的参与，祝您生活愉快！");
		sendredpack.setWxappid(appId);
		sendredpack.setScene_id("PRODUCT_2");	//固定抽奖
		
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("act_name",sendredpack.getAct_name());
		parameters.put("client_ip",sendredpack.getClient_ip());
		parameters.put("mch_billno",sendredpack.getMch_billno());
		parameters.put("mch_id",sendredpack.getMch_id());
		parameters.put("nonce_str",sendredpack.getNonce_str());
		parameters.put("re_openid",sendredpack.getRe_openid());
		parameters.put("remark",sendredpack.getRemark());
		parameters.put("send_name",sendredpack.getSend_name());
		parameters.put("total_amount",sendredpack.getTotal_amount());
		parameters.put("total_num",sendredpack.getTotal_num());
		parameters.put("wishing",sendredpack.getWishing());
		parameters.put("wxappid",sendredpack.getWxappid());
		parameters.put("scene_id",sendredpack.getScene_id());
		// 生成签名
		WeixinConfigDto config = new WeixinConfigDto();
		config.setApiKey(appSecret);
		String sign = WeixinSignUtil.getSign(parameters, config);
		LOG.info("生成的签名：{}",sign);
		
		// 扩展xstream，使其支持CDATA块
		String requestXml = XmlUtils.toXml(parameters,sign);
		LOG.info("请求xml ={}",requestXml);
		String result;
		try {
			result = HttpClientUtils.sendPost(Constants.sendredpack_url, requestXml,HttpClientUtils.getSSLClientFile(Constants.keyStore_type, ssLfile, mchId));
			LOG.info("成功返回XML值：{}",result);
			Map<String, Object> map = XMLParser.getMapFromXML(result);
			LOG.info("成功返回MAP值：{}",map);
			
			String status =BonusStatus.N.toString();
			String return_msg = "";
			String send_listid ="";
			//通讯成功
			if(map.get("return_code").toString().equals("SUCCESS")) {
				//发红包成功
				if(map.get("result_code").toString().equals("SUCCESS")) {
					status =BonusStatus.Y.toString();
					send_listid =map.get("send_listid").toString();
				}else {
					return_msg = map.get("err_code_des").toString();
				}
			}else {
				return_msg= map.get("return_msg").toString();
			}
			
			//获取用户基本信息
			FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
			findPersonMemberBase.setOpenIdGzhWx(openid);
			FindPersonMemberBaseReturn baseReturn= personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
			if(null ==baseReturn) {
				LOG.error("客户信息不存在，请补充客户信息");
				throw new TsfaServiceException("","验证不成功，订单不满足活动要求！");
			}
			//获取天猫订单信息
			FindTmallOrderPage findTmallOrderPage = new FindTmallOrderPage();
			TmallOrderDto param = new TmallOrderDto();
			param.setMerchantNo(baseReturn.getMerchantNo());
			param.setOrderNo(orderNo);
			findTmallOrderPage.setParam(param);
			TmallOrderDto tmallOrderDto= tmallOrderService.get(findTmallOrderPage);
			
			TmallBonusRecordDto tmallBonusRecordDto = new TmallBonusRecordDto();
			tmallBonusRecordDto.setMerchantNo(tmallOrderDto.getMerchantNo());
			tmallBonusRecordDto.setMemberNo(baseReturn.getMemberNo());
			tmallBonusRecordDto.setMemberName(baseReturn.getMemberName());
			String noWx = StringUtils.isEmpty(baseReturn.getNoWxAlias())?baseReturn.getNoWx():baseReturn.getNoWxAlias();
			tmallBonusRecordDto.setNoWx(noWx);
			tmallBonusRecordDto.setOrderNo(orderNo);
			tmallBonusRecordDto.setOrderAmt(tmallOrderDto.getAmount());
			tmallBonusRecordDto.setBonuxAmt(Long.valueOf(totalAmount));
			tmallBonusRecordDto.setStatus(status);
			tmallBonusRecordDto.setRemark(return_msg);
			tmallBonusRecordDto.setMchBillno(sendredpack.getMch_billno());
			tmallBonusRecordDto.setSendListid(send_listid);
			tmallBonusRecordDto.setNoWw(tmallOrderDto.getNoWw());
//			tmallBonusRecordDto.setRemark2();	
//			tmallBonusRecordDto.setRemark3();					
//			tmallBonusRecordDto.setRemark4();
			tmallBonusRecordService.addTmallBonusRecord(tmallBonusRecordDto);
			if(StringUtils.isNotEmpty(return_msg)) {
				throw new TsfaServiceException("","发送公众号红包失败，稍后为您补发，请耐心等待，感谢您的支持。");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
    }
    
	
}
