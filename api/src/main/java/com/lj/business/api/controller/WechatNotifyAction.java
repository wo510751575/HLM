package com.lj.business.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WechatNotifyAction {
		
	
	private static Logger LOG  = LoggerFactory.getLogger(WechatNotifyAction.class);
	 @RequestMapping("/wechat/notify")
	 public @ResponseBody String notify(HttpServletRequest request) {
	    	
	        String data = "";
	        try {
	            ServletInputStream in = request.getInputStream();
	            data = inputStream2String(in);
	            LOG.info("weixin pay notify msg:{}",data);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "failed";
	        }
	        return "success";
	    }
	 private String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException{
	        if(in == null)
	            return "";

	        StringBuffer out = new StringBuffer();
	        byte[] b = new byte[4096];
	        for (int n; (n = in.read(b)) != -1;) {
	            out.append(new String(b, 0, n, "UTF-8"));
	        }
	        return out.toString();
	    }
}
