package com.lj.business.member.service.impl;

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
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.pushconfig.AddWxPushConfig;
import com.lj.business.member.dto.pushconfig.DelWxPushConfig;
import com.lj.business.member.dto.pushconfig.FindWxPushConfig;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPage;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPageReturn;
import com.lj.business.member.dto.pushconfig.UpdateWxPushConfig;
import com.lj.business.member.emus.PushConfigType;
import com.lj.business.member.emus.PushTime;
import com.lj.business.member.service.IWxPushConfigService;


/**
 * 
 * 
 * 类说明：微信推送配置测试类
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
public class WxPushConfigServiceImplTest extends SpringTestCase{

	@Resource
	private IWxPushConfigService wxPushConfigService;

	/**
	 * 
	 *
	 * 方法说明：添加微信推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addWxPushConfig() throws TsfaServiceException{
		AddWxPushConfig addWxPushConfig = new AddWxPushConfig();
		//add数据录入
		addWxPushConfig.setCode(GUID.generateByUUID());
		addWxPushConfig.setNoWx("MANWAHHR002");
		addWxPushConfig.setStatus("USE");
		addWxPushConfig.setSort(6);
		addWxPushConfig.setType(PushConfigType.SP.name());
//		addWxPushConfig.setContent("终端名称{shopName}，商城地址{shopUrl}，店长姓名{mgrName}{emoji}{emoji}{emoji}");
//		addWxPushConfig.setContent("http://ztc.manwahgroup.com/im/738f4e42a2674e70a35e876465c9b2c9/image/2ccf4fab202640b69fe58709955e66bd.png");
		addWxPushConfig.setShareCode("2a8d05a9d2ff496fa2477a01cc1f1beb");
		addWxPushConfig.setShareTitle("芝华仕商城500元优惠券");
		addWxPushConfig.setShareDes("优惠券、全民惊爆价！！！");
		addWxPushConfig.setShareIcon("http://ztc.manwahgroup.com/im/738f4e42a2674e70a35e876465c9b2c9/image/2ccf4fab202640b69fe58709955e66bd.png");
//		addWxPushConfig.setShareUrl("http://ztc.manwahgroup.com/api/mec/index?source=FocusMedia&shopCode=9999");
		addWxPushConfig.setPushTime(PushTime.PUSHNOW.name());
		addWxPushConfig.setOrgType("SHOP");
//		addWxPushConfig.setShopNo("LJ_b65258f14b2e49198751f08b866710d2");
//		addWxPushConfig.setShopName("测试终端");
		addWxPushConfig.setMerchantNo("738f4e42a2674e70a35e876465c9b2c9");
		addWxPushConfig.setCreateDate(new Date());
		addWxPushConfig.setUpdateDate(new Date());
		addWxPushConfig.setRemark("Remark");
		Assert.assertNotNull(wxPushConfigService.addWxPushConfig(addWxPushConfig ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改微信推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateWxPushConfig() throws TsfaServiceException{
		UpdateWxPushConfig updateWxPushConfig = new UpdateWxPushConfig();
		//update数据录入
		updateWxPushConfig.setCode("Code");
		updateWxPushConfig.setNoWx("NoWx");
		updateWxPushConfig.setStatus("Status");
		updateWxPushConfig.setSort(1);
		updateWxPushConfig.setType("Type");
		updateWxPushConfig.setContent("Content");
		updateWxPushConfig.setShareCode("ShareCode");
		updateWxPushConfig.setShareTitle("ShareTitle");
		updateWxPushConfig.setShareDes("ShareDes");
		updateWxPushConfig.setShareIcon("ShareIcon");
		updateWxPushConfig.setShareUrl("ShareUrl");
		updateWxPushConfig.setPushTime("PushTime");
		updateWxPushConfig.setOrgType("OrgType");
//		updateWxPushConfig.setShopNo("ShopNo");
//		updateWxPushConfig.setShopName("ShopName");
		updateWxPushConfig.setMerchantNo("MerchantNo");
		updateWxPushConfig.setCreateDate(new Date());
		updateWxPushConfig.setUpdateDate(new Date());
		updateWxPushConfig.setRemark("Remark");

		wxPushConfigService.updateWxPushConfig(updateWxPushConfig );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找微信推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxPushConfig() throws TsfaServiceException{
		FindWxPushConfig findWxPushConfig = new FindWxPushConfig();
		findWxPushConfig.setCode("");
		wxPushConfigService.findWxPushConfig(findWxPushConfig);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找微信推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxPushConfigPage() throws TsfaServiceException{
		FindWxPushConfigPage findWxPushConfigPage = new FindWxPushConfigPage();
		Page<FindWxPushConfigPageReturn> page = wxPushConfigService.findWxPushConfigPage(findWxPushConfigPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除微信推送配置信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delWxPushConfig() throws TsfaServiceException{
		DelWxPushConfig delWxPushConfig = new DelWxPushConfig();
		delWxPushConfig.setCode("");
		Assert.assertNotNull(wxPushConfigService.delWxPushConfig(delWxPushConfig));
		
	}	
}
