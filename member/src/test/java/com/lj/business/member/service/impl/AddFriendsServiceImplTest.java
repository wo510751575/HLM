/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.addfriends.AddAddFriends;
import com.lj.business.member.dto.addfriends.DistributionMember;
import com.lj.business.member.dto.addfriends.FindClaimMemberPage;
import com.lj.business.member.dto.addfriends.UpdateAddFriendStatus;
import com.lj.business.member.service.IAddFriendsService;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月7日
 */
public class AddFriendsServiceImplTest extends SpringTestCase {

	@Resource
	private IAddFriendsService addFriendsService;
	
	@Test
	public void testAddAddFriends() {
		AddAddFriends addAddFriends = new AddAddFriends();
		addAddFriends.setMemberNoGm("da08e545a349485aa8faa42a34d43978");
		addAddFriends.setWxQrCode("dddddwerwd");
		addAddFriends.setNoWx("qiaosinidewenrou");
		addAddFriends.setNickRemark("nickname");
		addAddFriends.setMobile("15889399687");
		addAddFriends.setValidation("你好");
		addFriendsService.addAddFriends(addAddFriends);
	}
	
	@Test
	public void testUpdateAddFriendStatus() throws InterruptedException {
		UpdateAddFriendStatus updateAddFriendStatus = new UpdateAddFriendStatus();
		updateAddFriendStatus.setStatus(Boolean.TRUE);	// 通过
//		updateAddFriendStatus.setShopNo("LJ_b65258f14b2e49198751f08b866710d2");
//		updateAddFriendStatus.setMemberNoGm("282fcb12fd7744b6a6aa7f68405f9ed0");
		updateAddFriendStatus.setNoWxGm("MANWAHHR002");
		int count = 34;
		updateAddFriendStatus.setNoWx("qiashinidewenrou9351");
		updateAddFriendStatus.setAlias("");
		updateAddFriendStatus.setNickNameWx("客户认领测试账号" + count);
//		updateAddFriendStatus.setNickNameRemarkWx("恰似-15898756987");
		updateAddFriendStatus.setHeadAddress("http://wx.qlogo.cn/mmhead/ver_1/IAqicD5BOSN4UenpbF1pkQwicQ1oZ16c4gkG4ByuvzicE7Icia86OQGap1WRXNKT9nXlekkLF4bialeY1oicDQoWxcBJszYQK1mZpeDllYX1JNpAE/96");
		updateAddFriendStatus.setSex("MALE");
		updateAddFriendStatus.setLongitude("23.324234");
		updateAddFriendStatus.setLatitude("34.33333");
		addFriendsService.updateAddFriendsStatus(updateAddFriendStatus);
	}
	
	@Test
	public void testDistributionMember() {
		DistributionMember distributionMember = new DistributionMember();
		distributionMember.setCode("LJ_c0c475274b594c5dbea243b27d73cd98");
		distributionMember.setMemberNoGm("282fcb12fd7744b6a6aa7f68405f9ed0");
		addFriendsService.distributionMember(distributionMember);
	}
	
	@Test
	public void testFindClaimMemberPage() {
//		FindClaimMemberPage findClaimMemberPage = new FindClaimMemberPage();
//		findClaimMemberPage.setMemberNoGm("da08e545a349485aa8faa42a34d43978");
//		findClaimMemberPage.setFlag(true);
//		addFriendsService.findClaimMemberPage(findClaimMemberPage,false);
	}
	
	@Test
	public void testSyncFriendsRequest() {
		addFriendsService.syncFriendsRequest("da9a591323e149a29f0abb1310a33899");
	}
	
	@Test
	public void sendQcordByMec() {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("merchantNo", "fcecbfa097944565a58134d170f474af");
		paramMap.put("diffNum", "5");
		paramMap.put("qcordUrl", "123");
		addFriendsService.sendQcordByMec(paramMap);
	}
	
	@Test
	public void testStrip() throws Exception {
		StringUtils.strip(null, "null");
	}
}
