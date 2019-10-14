package com.lj.business.cf.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cf.dto.clientInvite.AddClientInvite;
import com.lj.business.cf.dto.clientInvite.DelClientInvite;
import com.lj.business.cf.dto.clientInvite.FindClientInvite;
import com.lj.business.cf.dto.clientInvite.UpdateClientInvite;
import com.lj.business.cf.emus.ExpResult;
import com.lj.business.cf.service.IClientInviteService;
import com.lj.business.cf.util.TestHelp;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class ClientInviteServiceImplTest extends SpringTestCase{

	@Resource
	IClientInviteService clientInviteService;



	/**
	 * 
	 *
	 * 方法说明：添加客户邀约表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addClientInvite() throws TsfaServiceException{
		AddClientInvite addClientInvite = new AddClientInvite();
		//add数据录入
		addClientInvite.setMerchantNo("1f169ad6143d46f5832535642ce2d331");
		addClientInvite.setMemberNo("7dd89cc45a474626bdc871c27c3b5b0x");
		addClientInvite.setMemberName("Ninix");
		addClientInvite.setMemberNoGm("421e31676b784957b8baa3015f1b52d0");
		addClientInvite.setMemberNameGm("峰火");
		addClientInvite.setShopNo("LJ_82039188265241e0bd8f87651db6ab3c");
		addClientInvite.setShopName("天津市市区直营店");
		addClientInvite.setReachTime(new Date());
		addClientInvite.setFailReason("FailReason");
		addClientInvite.setInviteResult("Y");
		addClientInvite.setCfNo("CfNo");
		addClientInvite.setCfCode("CfCode");
		addClientInvite.setCreateId("CreateId");
		addClientInvite.setCreateDate(new Date());
		addClientInvite.setRemark("Remark");
		addClientInvite.setRemark4("Remark4");
		addClientInvite.setRemark3("Remark3");
		addClientInvite.setRemark2("Remark2");
		
		clientInviteService.addClientInvite(addClientInvite );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改客户邀约表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void updateClientInvite() throws TsfaServiceException{
		UpdateClientInvite updateClientInvite = new UpdateClientInvite();
		//update数据录入
		updateClientInvite.setCode("Code");
		updateClientInvite.setMerchantNo("MerchantNo");
		updateClientInvite.setMemberNo("MemberNo");
		updateClientInvite.setMemberName("MemberName");
		updateClientInvite.setMemberNoGm("MemberNoGm");
		updateClientInvite.setMemberNameGm("MemberNameGm");
		updateClientInvite.setShopNo("ShopNo");
		updateClientInvite.setShopName("ShopName");
		updateClientInvite.setReachTime(new Date());
		updateClientInvite.setFailReason("FailReason");
		updateClientInvite.setInviteResult("InviteResult");
		updateClientInvite.setCfNo("CfNo");
		updateClientInvite.setCfCode("CfCode");
		updateClientInvite.setCreateId("CreateId");
		updateClientInvite.setCreateDate(new Date());
		updateClientInvite.setRemark("Remark");
		updateClientInvite.setRemark4("Remark4");
		updateClientInvite.setRemark3("Remark3");
		updateClientInvite.setRemark2("Remark2");

		clientInviteService.updateClientInvite(updateClientInvite );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找客户邀约表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findClientInvite() throws TsfaServiceException{
		FindClientInvite findClientInvite = new FindClientInvite();
		findClientInvite.setCode("111");
		clientInviteService.findClientInvite(findClientInvite);
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除客户邀约表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void delClientInvite() throws TsfaServiceException{
		DelClientInvite delClientInvite = new DelClientInvite();
		delClientInvite.setCode("111");
		clientInviteService.delClientInvite(delClientInvite);
		
	}
	
	@Test
	public void findInviteResults() throws TsfaServiceException{
		Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("merchantNo", TestHelp.merchantNo_test);
		parmMap.put("inviteResult", ExpResult.Y.toString());
		List<Map<String,Object>> list =clientInviteService.findInviteResults(parmMap);
		System.out.println(list.toString());
	}
	
	@Test
	public void findInviteNum(){
		FindClientInvite clientInvite=new FindClientInvite();
		clientInvite.setMemberNoGm("33d5250eade0439d960dd835584d7e47");
		clientInvite.setMemberNo("");
		System.out.println(clientInviteService.findClientInvinteReachTime(clientInvite));
		
	}
}
