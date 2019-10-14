package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.AddMerchant;
import com.lj.business.member.dto.DelMerchant;
import com.lj.business.member.dto.FindMerchant;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.UpdateMerchant;
import com.lj.business.member.emus.Status;
import com.lj.business.member.service.IMerchantService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class MerchantServiceImplTest extends SpringTestCase{

	@Resource
	IMerchantService merchantService;
	
	@Resource
	MerchantServiceImpl merchantServiceImpl;


	 @Resource
	    private LocalCacheSystemParamsFromCC localCacheSystemParams;
		
		    
		@Test
		public void test_py() {
			//String callbackUrl = localCacheSystemParams.getSystemParam("weixin", "sendFriendsJob", "sendFriendsJobCallbackUrl");
			
			 Map<String, String> groupMap = localCacheSystemParams.getSystemParamGroup("weixin", "sendFriendsJob");
	         String callbackUrl = groupMap.get("sendFriendsJobCallbackUrl");
			System.out.println("callbackUrl:"+callbackUrl);
		}

	/**
	 * 
	 *
	 * 方法说明：添加商户表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void addMerchant() throws TsfaServiceException{
		AddMerchant addMerchant = new AddMerchant();
		//add数据录入
		addMerchant.setMerchantName("扬恩科技有限公司_2");
		addMerchant.setStatus(Status.NORMAL);
		addMerchant.setWebsiteUrl("www.ljkeji.com");
		addMerchant.setBusinessType("businessType");
		addMerchant.setLogoAddr("http://a2.qpic.cn/psb?/V12hnLlA1zyPXC/KE5N7CUwXHZOMzH3zYM0AZ8X8LgBBRdlGn8czQ7KOSQ!/b/dD8BAAAAAAAA&bo=kACQAAAAAAADByI!&rf=viewer_4");
		addMerchant.setTelephone("0755-8888 8888");
		addMerchant.setCreateId("py");
		
		Assert.assertNotNull(merchantService.addMerchant(addMerchant ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改商户表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void updateMerchant() throws TsfaServiceException{
		UpdateMerchant updateMerchant = new UpdateMerchant();
		//update数据录入
		updateMerchant.setCode(GUID.getPreUUID());
		updateMerchant.setMerchantNo(GUID.getPreUUID());
		updateMerchant.setMerchantName("扬恩科技有限公司");
		updateMerchant.setStatus(Status.NORMAL);
		updateMerchant.setAddress("Address");
		updateMerchant.setEmail("Email");
		updateMerchant.setBusinessType("扬恩");
		updateMerchant.setLogoAddr("logoAddr");
		updateMerchant.setRemark("Remark");
		updateMerchant.setUpdateId("邹磊");

		merchantService.updateMerchant(updateMerchant );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找商户表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findMerchant() throws TsfaServiceException{
		FindMerchant findMerchant = new FindMerchant();
		findMerchant.setCode("LJ_881a5b59553a4150a30f7587334e4195");
		merchantService.findMerchant(findMerchant);
	}
	/**
	 * 
	 *
	 * 方法说明：查找商户表信息(个人中心)
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月7日
	 *
	 */
	@Test
	public void selectMerchant() throws TsfaServiceException{
		FindMerchantDto findMerchantDto = new FindMerchantDto();
		findMerchantDto.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		merchantService.selectMerchant(findMerchantDto);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找商户表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findMerchantPage() throws TsfaServiceException{
		FindMerchantPage findMerchantPage = new FindMerchantPage();
		Page<FindMerchantPageReturn> page = merchantService.findMerchantPage(findMerchantPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除商户表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void delMerchant() throws TsfaServiceException{
		DelMerchant delMerchant = new DelMerchant();
		delMerchant.setCode(GUID.getPreUUID());
		Assert.assertNotNull(merchantService.delMerchant(delMerchant));
		
	}
	
	@Test
	public void findMerchants() throws TsfaServiceException{
		FindMerchantPage findMerchantPage = new FindMerchantPage();
		List<FindMerchantPageReturn> list = merchantService.findMerchants(findMerchantPage);
		System.out.println(list.toString());
	}
	
	@Test
	public void testfindAllMerchant() {
	/*	logger.info("" + merchantService.findAllMerchant());
		merchantService.findAllMerchant();
		merchantService.findAllMerchant();*/
		
		
	}
	

	
	@Test
	public void getMessage() throws TsfaServiceException, InterruptedException {
		String str = 	merchantServiceImpl.getMessage("ttt");
		System.out.println("getMessage:" + str);
		//Thread.sleep(8000);
		str = 	merchantServiceImpl.getMessage("ttt");
		System.out.println("getMessage:" + str);

	}
	
	
	@Test
	public void getMessageStr() throws TsfaServiceException {
		String str = 	merchantServiceImpl.getMessageStr("ttt");
		System.out.println("getMessageStr:" + str);
		str = 	merchantServiceImpl.getMessageStr("ttt");
		System.out.println("getMessageStr:" + str);
		
		str = 	merchantServiceImpl.resetMessageStr("ttt");
		System.out.println("getMessageStr:" + str);
		
		str = 	merchantServiceImpl.getMessageStr("ttt");
		System.out.println("getMessageStr:" + str);
		
		str = 	merchantServiceImpl.getMessageStr("ttt");
		System.out.println("getMessageStr:" + str);

	}

	@Test
	public void getMessageObject() throws TsfaServiceException {
		AddMerchant checkAccountInfo = new AddMerchant();
		checkAccountInfo.setAddress("地址");
		AddMerchant str = 	merchantServiceImpl.getMessageObject(checkAccountInfo );
		System.out.println("getMessage:" + str);

		str = merchantServiceImpl.getMessageObject(checkAccountInfo );
		System.out.println("getMessage:" + str);
	}
}
