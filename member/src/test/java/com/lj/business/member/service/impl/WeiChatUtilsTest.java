package com.lj.business.member.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.utils.WeiChatUtils;

public class WeiChatUtilsTest extends SpringTestCase {

	@Test
	public void getOpenId() throws Exception {
		String openId = WeiChatUtils.getOpenId("003gY1H10WmdSF1Us6E10uDFG10gY1Hk");
		System.out.println(openId);
		Map<String, String> mapFromJson = JsonUtils.mapFromJson(openId);
		for (Entry<String, String> entry : mapFromJson.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
	
	@Test
	public void crypt() throws Exception {
		String openIdEncrypt = WeiChatUtils.openIdEncrypt("003gY1H10WmdSF1Us6E10uDFG10gY1Hk");
		System.out.println(openIdEncrypt);
		String openIdDecrypt = WeiChatUtils.openIdDecrypt(openIdEncrypt);
		System.out.println(openIdDecrypt);
	}
}
