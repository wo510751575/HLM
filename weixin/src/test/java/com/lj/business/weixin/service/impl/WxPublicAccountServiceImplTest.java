package com.lj.business.weixin.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.weixin.dto.publicaccount.AddWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.DelWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPage;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPageReturn;
import com.lj.business.weixin.dto.publicaccount.UpdateWxPublicAccount;
import com.lj.business.weixin.service.IWxPublicAccountService;


/**
 * 
 * 
 * 类说明：微信公众号测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
public class WxPublicAccountServiceImplTest extends SpringTestCase{

	@Resource
	private IWxPublicAccountService wxPublicAccountService;

	/**
	 * 
	 *
	 * 方法说明：添加微信公众号信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addWxPublicAccount() throws TsfaServiceException{
		AddWxPublicAccount addWxPublicAccount = new AddWxPublicAccount();
		//add数据录入
		addWxPublicAccount.setCode("Code");
		addWxPublicAccount.setNoWxZk("NoWxZk");
		addWxPublicAccount.setPaUsername("PaUsername");
		addWxPublicAccount.setPaAlias("PaAlias");
		addWxPublicAccount.setPaName("PaName");
		addWxPublicAccount.setPaLogo("PaLogo");
		addWxPublicAccount.setPaDesc("PaDesc");
		addWxPublicAccount.setWxParam("WxParam");
		addWxPublicAccount.setStatus(1);
//		addWxPublicAccount.setShopNo("ShopNo");
//		addWxPublicAccount.setShopName("ShopName");
		addWxPublicAccount.setMerchantNo("MerchantNo");
		addWxPublicAccount.setMerchantName("MerchantName");
		addWxPublicAccount.setCreateDate(new Date());
		addWxPublicAccount.setRemark("Remark");
		addWxPublicAccount.setRemark2("Remark2");
		addWxPublicAccount.setRemark3("Remark3");
		addWxPublicAccount.setRemark4("Remark4");
		
		Assert.assertNotNull(wxPublicAccountService.addWxPublicAccount(addWxPublicAccount ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改微信公众号信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateWxPublicAccount() throws TsfaServiceException{
		UpdateWxPublicAccount updateWxPublicAccount = new UpdateWxPublicAccount();
		//update数据录入
		updateWxPublicAccount.setCode("Code");
		updateWxPublicAccount.setNoWxZk("NoWxZk");
		updateWxPublicAccount.setPaUsername("PaUsername");
		updateWxPublicAccount.setPaAlias("PaAlias");
		updateWxPublicAccount.setPaName("PaName");
		updateWxPublicAccount.setPaLogo("PaLogo");
		updateWxPublicAccount.setPaDesc("PaDesc");
		updateWxPublicAccount.setWxParam("WxParam");
		updateWxPublicAccount.setStatus(1);
//		updateWxPublicAccount.setShopNo("ShopNo");
//		updateWxPublicAccount.setShopName("ShopName");
		updateWxPublicAccount.setMerchantNo("MerchantNo");
		updateWxPublicAccount.setMerchantName("MerchantName");
		updateWxPublicAccount.setCreateDate(new Date());
		updateWxPublicAccount.setRemark("Remark");
		updateWxPublicAccount.setRemark2("Remark2");
		updateWxPublicAccount.setRemark3("Remark3");
		updateWxPublicAccount.setRemark4("Remark4");

		wxPublicAccountService.updateWxPublicAccount(updateWxPublicAccount );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找微信公众号信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxPublicAccount() throws TsfaServiceException{
		FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
		findWxPublicAccount.setCode("");
		wxPublicAccountService.findWxPublicAccount(findWxPublicAccount);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找微信公众号信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxPublicAccountPage() throws TsfaServiceException{
		FindWxPublicAccountPage findWxPublicAccountPage = new FindWxPublicAccountPage();
		Page<FindWxPublicAccountPageReturn> page = wxPublicAccountService.findWxPublicAccountPage(findWxPublicAccountPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除微信公众号信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delWxPublicAccount() throws TsfaServiceException{
		DelWxPublicAccount delWxPublicAccount = new DelWxPublicAccount();
		delWxPublicAccount.setCode("");
		Assert.assertNotNull(wxPublicAccountService.delWxPublicAccount(delWxPublicAccount));
		
	}	
}
