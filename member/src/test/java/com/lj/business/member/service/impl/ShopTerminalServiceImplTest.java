package com.lj.business.member.service.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.shopterminal.AddShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPage;
import com.lj.business.member.dto.shopterminal.FindShopTerminalPageReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.TerminalSign;
import com.lj.business.member.dto.shopterminal.TerminalSignReturn;
import com.lj.business.member.dto.shopterminal.UpdateShopTerminal;
import com.lj.business.member.dto.shopterminal.UpdateTerminalWxPwd;
import com.lj.business.member.dto.shopterminal.UpdateWorkKey;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.service.IWxAccountService;
import com.lj.cc.clientintf.IJob;
import com.lj.oms.utils.WxPwdEncryptUtils;


/**
 * 
 * 
 * 类说明：测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月1日
 */
public class ShopTerminalServiceImplTest extends SpringTestCase{

	@Resource
	IShopTerminalService shopTerminalService;

	@Resource(name="shopTerminalServiceImpl")
	IJob shopTerminalVersionCheckJob;
	
	@Resource
	private IWxAccountService wxAccountService;

	/**
	 * 
	 *
	 * 方法说明：添加终端终端信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	@Test
	public void addShopTerminal() throws TsfaServiceException{
		AddShopTerminal addShopTerminal = new AddShopTerminal();
		//add数据录入
		addShopTerminal.setMerchantNo("1f169ad6143d46f5832535642ce2d331");
		addShopTerminal.setMerchantName("海雅集团");
//		addShopTerminal.setShopNo("LJ_82039188265241e0bd8f87651db6ab3c");
//		addShopTerminal.setShopName("天津市市区直营店");
//		addShopTerminal.setMobileName("15878978520");
		addShopTerminal.setNoWx("lylj0725");
		addShopTerminal.setWxNickname("lylj0725");
		addShopTerminal.setImei("866036039541244");
		addShopTerminal.setStatus(1);
		addShopTerminal.setCreateId("CreateId");
		addShopTerminal.setCreateDate(new Date());
		addShopTerminal.setRemark("Remark");
		addShopTerminal.setRemark2("Remark2");
		addShopTerminal.setRemark3("Remark3");
		addShopTerminal.setRemark4("Remark4");
		Assert.assertNotNull(shopTerminalService.addShopTerminal(addShopTerminal ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改终端终端信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	@Test
	public void updateShopTerminal() throws TsfaServiceException{
		UpdateShopTerminal updateShopTerminal = new UpdateShopTerminal();
		//update数据录入
		updateShopTerminal.setCode("382a5a766237486282d275f5450b00ac");
//		updateShopTerminal.setMerchantNo("MerchantNo");
//		updateShopTerminal.setMerchantName("MerchantName");
//		updateShopTerminal.setShopNo("ShopNo");
//		updateShopTerminal.setShopName("ShopName");
//		updateShopTerminal.setMobileName("MobileName");
//		updateShopTerminal.setImei("Imei");
//		updateShopTerminal.setStatus(1);
//		updateShopTerminal.setCreateId("CreateId");
//		updateShopTerminal.setCreateDate(new Date());
//		updateShopTerminal.setRemark("Remark");
//		updateShopTerminal.setRemark2("Remark2");
//		updateShopTerminal.setRemark3("Remark3");
//		updateShopTerminal.setRemark4("Remark4");
		updateShopTerminal.setAlias("alias");
		updateShopTerminal.setHeadAddress("headAddress");
		updateShopTerminal.setNickname("nickname");
		shopTerminalService.updateShopTerminal(updateShopTerminal );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找终端终端信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	@Test
	public void findShopTerminal() throws TsfaServiceException{
		FindShopTerminal findShopTerminal = new FindShopTerminal();
		findShopTerminal.setCode(GUID.generateCode());
		shopTerminalService.findShopTerminal(findShopTerminal);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找终端终端信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月1日
	 *
	 */
	@Test
	public void findShopTerminalPage() throws TsfaServiceException{
		FindShopTerminalPage findShopTerminalPage = new FindShopTerminalPage();
		List<String> shopNos = new ArrayList<>();
		shopNos.add("1");
		shopNos.add("aaa");
		findShopTerminalPage.setShopNos(shopNos);
		Page<FindShopTerminalPageReturn> page = shopTerminalService.findShopTerminalPage(findShopTerminalPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void testFindShopTerminalFromWeb() {
		FindShopTidFromWeb findShopTidFromWeb = new FindShopTidFromWeb();
		findShopTidFromWeb.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		findShopTidFromWeb.setAssistantNo("3bb507afa4c9408d824f24473d8fcd3d");
//		findShopTidFromWeb.setSearchWords("14");
		findShopTidFromWeb.setProvinceCode("104");
//		findShopTidFromWeb.setShopNo("LJ_dbfd184dbe6e4233b03596177c465aaf");
//		findShopTidFromWeb.setPmTypeCode("LJ_fa73d02fad684e588fb44e44327ddacc");
		shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
	}
	
	@Test
	public void testUpdateVersion() {
		//shopTerminalService.updateVersion("a8ddbede9e3c4627a0205d259651d6ae");
	}
	
	@Test
	public void testRunShopTerminalVersionCheckJob() {
		shopTerminalVersionCheckJob.runJob();
	}
	
	@Test
	public void findShopTerminalsByNotBind() throws TsfaServiceException{
		FindShopTerminalPage findShopTerminalPage = new FindShopTerminalPage();
		findShopTerminalPage.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		findShopTerminalPage.setNoWx("222");
//		findShopTerminalPage.setShopNo("shopno");
		findShopTerminalPage.setAssistantNo("222");
		List<FindShopTerminalPageReturn> page = shopTerminalService.findShopTerminalsByNotBind(findShopTerminalPage);
		for (FindShopTerminalPageReturn findShopTerminalPageReturn : page) {
			System.out.println(findShopTerminalPageReturn.getTerminalCode());
		}
	}
	
	@Test
	public void findShopTerminalsByBind() throws TsfaServiceException{
		FindShopTerminalPage findShopTerminalPage = new FindShopTerminalPage();
		findShopTerminalPage.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		List<FindShopTerminalPageReturn> page = shopTerminalService.findShopTerminalsByBind(findShopTerminalPage);
		for (FindShopTerminalPageReturn findShopTerminalPageReturn : page) {
			System.out.println(findShopTerminalPageReturn.getTerminalCode());
		}
	}
	
	@Test
	public void sendFindWxAccountBalance() {
		wxAccountService.sendFindWxAccountBalanceMessage("czm5374");
	}
	
	@Test
	public void testUpdateWorkKey() {
		UpdateWorkKey updateWorkKey = new UpdateWorkKey();
		updateWorkKey.setCode("da9a591323e149a29f0abb1310a33899");
		updateWorkKey.setWorkKey(GUID.generateByUUID());
		shopTerminalService.updateWorkKey(updateWorkKey);
	}
	
	@Test
	public void testSign() {
//		TerminalSign terminalSign = new TerminalSign();
//		terminalSign.setImei("866036033088987");
//		terminalSign.setWorkKey("2174dfa6f93c4216a4d7a58b1bac84e9");
//		terminalSign.setWorkKey(GUID.generateByUUID());
//		TerminalSignReturn signReturn = shopTerminalService.sign(terminalSign);
//		System.out.println(WxPwdEncryptUtils.decryptBySign(signReturn.getEncrypt(), terminalSign.getWorkKey(), signReturn.getTimestamp()));
	}
	
	@Test
	public void testSendTerminalSignCommand() {
		shopTerminalService.sendTerminalSignCommand("a8ddbede9e3c4627a0205d259651d6ae");
	}
	
	@Test
	public void testUpdateTerminalWxPwd() {
		UpdateTerminalWxPwd updateTerminalWxPwd = new UpdateTerminalWxPwd();
		updateTerminalWxPwd.setCode("a8ddbede9e3c4627a0205d259651d6ae");
		updateTerminalWxPwd.setPwd("000111");
		shopTerminalService.updateTerminalWxPwd(updateTerminalWxPwd);
	}
	
	@Test
	public void testName1() throws Exception {
		Float f=new BigDecimal("1.22").add(new BigDecimal("-1.23")).floatValue();
		System.out.println(f);
	}
}
