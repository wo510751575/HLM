package com.lj.business.cf.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cf.dto.messagePush.AddMessagePushDto;
import com.lj.business.cf.dto.messagePush.FindMessagePushPage;
import com.lj.business.cf.dto.messagePush.FindMessagePushPageReturn;
import com.lj.business.cf.service.IMessagePushService;

public class MessagePushServiceTest extends SpringTestCase {
	
	@Resource
	private IMessagePushService messagePushService;
	
	@Test
	public void addMessagePush() throws Exception {
		//"memberNameGm":"姚平","memberNamesMsg":"李婧雯","memberNoGm":"80d70b396a594f8b906c844b13e8c9f7",
		//"memberNoMsg":"7d7b7e49c87843f58d7182760e3f4b55","memberWxNos":"F100007052403272",
		//"merchantName":"芝华士","merchantNo":"5ea0c014cbbd49a6a56ab73c6d1772ae","msgContent":"染发膏",
		//"msgTitle":"大福源","pushDate":"Sep 18, 2017 16:08:00",
		//"shopName":"芝华仕深圳欧洲城贵族店","shopNo":"LJ_2e9e1da0e2a94a1a8c0f652e9a80bba8"
		AddMessagePushDto addMessagePushDto = new AddMessagePushDto();
		addMessagePushDto.setMemberNameGm("姚平");
		addMessagePushDto.setMemberNamesMsg("李婧雯");
		addMessagePushDto.setMemberNoGm("80d70b396a594f8b906c844b13e8c9f7");
		addMessagePushDto.setMemberNoMsg("7d7b7e49c87843f58d7182760e3f4b55");
		addMessagePushDto.setMemberWxNos("F100007052403272");
		addMessagePushDto.setMerchantName("芝华士");
		addMessagePushDto.setMerchantNo("5ea0c014cbbd49a6a56ab73c6d1772ae");
		addMessagePushDto.setMsgContent("染发膏");
		addMessagePushDto.setMsgTitle("大福源");
		addMessagePushDto.setPushDate("2017-09-18");
		addMessagePushDto.setShopName("芝华仕深圳欧洲城贵族店");
		addMessagePushDto.setShopNo("LJ_2e9e1da0e2a94a1a8c0f652e9a80bba8");
		messagePushService.addMessagePush(addMessagePushDto);
	}
	
	@Test
	public void findMessagePushByGm() throws Exception {
		FindMessagePushPage findMessagePushPage = new FindMessagePushPage();
		findMessagePushPage.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
		findMessagePushPage.setShopNo("LJ_dbfd184dbe6e4233b03596177c465aaf");
		findMessagePushPage.setMemberNoGm("421e31676b784957b8baa3015f1b52d0");
		findMessagePushPage.setMsgStatus("INVALID");
		findMessagePushPage.setBeginDate("2017-09-26 00:00:00");
		findMessagePushPage.setEndDate("2017-09-26 23:59:59");
		List<FindMessagePushPageReturn> list = messagePushService.findMessagePushByGm(findMessagePushPage);
		for (FindMessagePushPageReturn findMessagePushPageReturn : list) {
			System.out.println(findMessagePushPageReturn);
		}
	}

}
