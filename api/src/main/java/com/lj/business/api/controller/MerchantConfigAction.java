package com.lj.business.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.business.api.controller.member.MemberAction;
import com.lj.distributecache.IDistributeCache;

/**
 * 提供给APP获取商户配置
 * @author zlh
 *
 */
@Controller
@RequestMapping(value = "/merchantconfig/")
public class MerchantConfigAction {

	private static Logger logger = LoggerFactory.getLogger(MemberAction.class);

	@Resource
	public IDistributeCache redis;
	
	private static String REDISKEY = "REDIS_KEY_MERCHANTCONFIG_";
	/**
	 * 
	 *
	 * 方法说明：查询商户设置
	 *
	 * @param managerMemberDto
	 * @return
	 *
	 * @author zlh CreateDate: 2017年7月13日
	 *
	 */
	@RequestMapping(value = "getMerchantConfigData.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String ,String > getMerchantConfigData(String merchantNo, String mobile) {
		
		Map<String ,String > map = new HashMap<String , String>();

		String autoClaimFlag  = redis.get(REDISKEY +"_autoClaimFlag_"+merchantNo);
		
		String  showClaimCode = redis.get(REDISKEY +"_showClaimCode_"+merchantNo);
		
		//是否开启自动认领
		if(autoClaimFlag != null && autoClaimFlag.equals("true")) {
			map.put("autoClaimFlag", "true");
		}
		if(autoClaimFlag != null && autoClaimFlag.equals("false")) {
			map.put("autoClaimFlag", "false");
		}
		if(autoClaimFlag == null) {
			map.put("autoClaimFlag", "false");
		}
		
		
		//优先展示的二维码
		//true 动态二维码
		if(showClaimCode != null && showClaimCode.equals("false")) {
			map.put("staticFirst", "false");
		}
		if(showClaimCode != null && showClaimCode.equals("true")) {
			map.put("staticFirst", "true");
		}
		//true 静态二维码
		if(showClaimCode == null) {
			map.put("staticFirst", "true");
		}
		return map;
	}
}
