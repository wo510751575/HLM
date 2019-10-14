/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.im.FindImFriendsPage;
import com.lj.business.member.dto.im.FindMaxVersion;
import com.lj.business.member.dto.im.FindMemberWxByNoWxGm;
import com.lj.business.member.service.IPersonMemberImService;

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
 * CreateDate: 2017年10月28日
 */
public class PersonMemberImServiceImplTest extends SpringTestCase {
	
	@Resource
	private IPersonMemberImService personMemberImService;
	
	@Test
	public void testFindMaxVersion() {
		FindMaxVersion findMaxVersion = new FindMaxVersion();
		findMaxVersion.setMerchantNo("fcecbfa097944565a58134d170f474af");
		findMaxVersion.setMemberNoGm("71deb4191b764764a5927c7f93d5e142");
		findMaxVersion.setNoWxGm("");
		logger.info("maxVersion: {}", personMemberImService.findMaxVersion(findMaxVersion));
	}
	
	@Test
	public void testFindImFriends() {
		FindImFriendsPage findImFriendsPage = new FindImFriendsPage();
		findImFriendsPage.setMerchantNo("fcecbfa097944565a58134d170f474af");
		findImFriendsPage.setMemberNoGm("71deb4191b764764a5927c7f93d5e142");
		findImFriendsPage.setNoWxGm("");
		findImFriendsPage.setVersion(11L);
		logger.info(personMemberImService.findImFriends(findImFriendsPage).toString());
	}

	@Test
	public void testFindMemberWxByNoWxGm() {
		FindMemberWxByNoWxGm findMemberWxByNoWxGm = new FindMemberWxByNoWxGm();
//		findMemberWxByNoWxGm.setShopNo("LJ_82039188265241e0bd8f87651db6ab3c");
		String[] noWxGms = {"lylj0725", "myymclob", "amsc10"};
		findMemberWxByNoWxGm.setNoWxGms(noWxGms);
		personMemberImService.findMemberWxByNoWxGm(findMemberWxByNoWxGm);
	}
}
