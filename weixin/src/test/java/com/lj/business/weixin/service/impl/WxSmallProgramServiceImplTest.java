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
import com.lj.business.weixin.dto.smallprogram.AddWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.DelWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPage;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPageReturn;
import com.lj.business.weixin.dto.smallprogram.UpdateWxSmallProgram;
import com.lj.business.weixin.service.IWxSmallProgramService;


/**
 * 
 * 
 * 类说明：微信小程序测试类
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
public class WxSmallProgramServiceImplTest extends SpringTestCase{

	@Resource
	private IWxSmallProgramService wxSmallProgramService;

	/**
	 * 
	 *
	 * 方法说明：添加微信小程序信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addWxSmallProgram() throws TsfaServiceException{
		AddWxSmallProgram addWxSmallProgram = new AddWxSmallProgram();
		//add数据录入
		addWxSmallProgram.setCode("Code");
		addWxSmallProgram.setNoWxZk("NoWxZk");
		addWxSmallProgram.setType("Type");
		addWxSmallProgram.setSpAppid("SpAppid");
		addWxSmallProgram.setSpName("SpName");
		addWxSmallProgram.setSpLogo("SpLogo");
		addWxSmallProgram.setSpDesc("SpDesc");
		addWxSmallProgram.setWxParam("WxParam");
		addWxSmallProgram.setStatus(1);
//		addWxSmallProgram.setShopNo("ShopNo");
//		addWxSmallProgram.setShopName("ShopName");
		addWxSmallProgram.setMerchantNo("MerchantNo");
		addWxSmallProgram.setMerchantName("MerchantName");
		addWxSmallProgram.setCreateDate(new Date());
		addWxSmallProgram.setRemark("Remark");
		addWxSmallProgram.setRemark2("Remark2");
		addWxSmallProgram.setRemark3("Remark3");
		addWxSmallProgram.setRemark4("Remark4");
		
		Assert.assertNotNull(wxSmallProgramService.addWxSmallProgram(addWxSmallProgram ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改微信小程序信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateWxSmallProgram() throws TsfaServiceException{
		UpdateWxSmallProgram updateWxSmallProgram = new UpdateWxSmallProgram();
		//update数据录入
		updateWxSmallProgram.setCode("Code");
		updateWxSmallProgram.setNoWxZk("NoWxZk");
		updateWxSmallProgram.setType("Type");
		updateWxSmallProgram.setSpAppid("SpAppid");
		updateWxSmallProgram.setSpName("SpName");
		updateWxSmallProgram.setSpLogo("SpLogo");
		updateWxSmallProgram.setSpDesc("SpDesc");
		updateWxSmallProgram.setWxParam("WxParam");
		updateWxSmallProgram.setStatus(1);
//		updateWxSmallProgram.setShopNo("ShopNo");
//		updateWxSmallProgram.setShopName("ShopName");
		updateWxSmallProgram.setMerchantNo("MerchantNo");
		updateWxSmallProgram.setMerchantName("MerchantName");
		updateWxSmallProgram.setCreateDate(new Date());
		updateWxSmallProgram.setRemark("Remark");
		updateWxSmallProgram.setRemark2("Remark2");
		updateWxSmallProgram.setRemark3("Remark3");
		updateWxSmallProgram.setRemark4("Remark4");

		wxSmallProgramService.updateWxSmallProgram(updateWxSmallProgram );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找微信小程序信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxSmallProgram() throws TsfaServiceException{
		FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
		findWxSmallProgram.setCode("");
		wxSmallProgramService.findWxSmallProgram(findWxSmallProgram);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找微信小程序信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findWxSmallProgramPage() throws TsfaServiceException{
		FindWxSmallProgramPage findWxSmallProgramPage = new FindWxSmallProgramPage();
		Page<FindWxSmallProgramPageReturn> page = wxSmallProgramService.findWxSmallProgramPage(findWxSmallProgramPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除微信小程序信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delWxSmallProgram() throws TsfaServiceException{
		DelWxSmallProgram delWxSmallProgram = new DelWxSmallProgram();
		delWxSmallProgram.setCode("");
		Assert.assertNotNull(wxSmallProgramService.delWxSmallProgram(delWxSmallProgram));
		
	}	
}
