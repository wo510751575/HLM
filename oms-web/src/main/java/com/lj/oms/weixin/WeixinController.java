package com.lj.oms.weixin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.CacheUtils;
import com.lj.oms.common.BaseController;
import com.lj.base.mvc.base.json.JsonUtils;

@Controller
@RequestMapping(value="${adminPath}/weixin")
public class WeixinController extends BaseController{
	
	/**
	 * 微信认证jsapi
	 * @param request
	 * @param response
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "weixinsdk")
	@ResponseBody
	public String weixinauthorization(HttpServletRequest request,HttpServletResponse response,String url) {
		String wx_ticket=(String) CacheUtils.get("weixin_wx_ticket");
		String appid=	"";
		String appsecret=	"";
		if(StringUtils.isEmpty(wx_ticket)){
			//获取access_token
			String access_token = WeixinUtil.access_token+appid+"&secret="+appsecret;
			JSONObject ACCESS_jsonObject = WeixinUtil.httpRequest(access_token, "POST", null);
			//获取ticket
			String ticket = WeixinUtil.ticket+ACCESS_jsonObject.get("access_token").toString()+"&type=jsapi";
			JSONObject weixin_ticket = WeixinUtil.httpRequest(ticket, "POST", null);
			wx_ticket=weixin_ticket.getString("ticket")	;
			
			CacheUtils.put("weixin_wx_ticket", wx_ticket, Integer.valueOf((int) (weixin_ticket.getInt("expires_in")*0.75)));
		}
        // 时间戮
        long timestamp = System.currentTimeMillis()/1000;
        // 随机字符串	
        String noncestr =UUID.randomUUID().toString(); 
        noncestr =  noncestr.substring(0,8)+noncestr.substring(9,13)+noncestr.substring(14,18)+noncestr.substring(19,23)+noncestr.substring(24);
        String content="jsapi_ticket="+wx_ticket+"&noncestr="+noncestr+"&timestamp="+String.valueOf(timestamp)+"&url="+url;
        MessageDigest md;
        String signature = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			 // 将三个参数字符串拼接成一个字符串进行 shal 加密
	        byte[] digest = md.digest(content.getBytes());
	        signature =SignUtil.byteToStr(digest);
	        
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		@SuppressWarnings("rawtypes")
		Map<String, Comparable> m=new HashMap<String, Comparable>();
		m.put("appId", appid);
		m.put("noncestr", noncestr);
		m.put("timestamp", timestamp);
		m.put("signature", signature);
		
        return JsonUtils.jsonFromObject_AllToString(m);
	}

}
