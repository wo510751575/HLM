package com.lj.business.api.controller.wx;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.business.api.controller.Action;
import com.lj.business.api.service.WeixinApiService;

/**
 * 发送公众号红包控制器
 * 
 * @author wo510
 *
 */
@Controller
@RequestMapping(value = "/wx/redPack")
public class RedPackAction extends Action{

	@Autowired
	WeixinApiService weixinApiService;
	
	/**
	 * 发送红包
	 * 
	 * @param openid
	 * @param redenveLopes_id
	 * @param mch_billno
	 * @return
	 */
	@RequestMapping(value = "/sendRedPack")
	@ResponseBody
	public String sendRedPack(String openid,HttpServletRequest request) {
//		weixinApiService.sendRedPack(openid,HttpClientUtils.getIpAddr(request));
		return null;
	}

}
