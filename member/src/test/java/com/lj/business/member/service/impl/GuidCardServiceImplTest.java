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
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.guidCard.AddGuidCard;
import com.lj.business.member.dto.guidCard.DelGuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCard;
import com.lj.business.member.dto.guidCard.FindGuidCardPage;
import com.lj.business.member.dto.guidCard.FindGuidCardPageReturn;
import com.lj.business.member.dto.guidCard.FindGuidCardReturn;
import com.lj.business.member.dto.guidCard.UpdateGuidCard;
import com.lj.business.member.dto.guidCardCustomer.GuidCardAddNumDto;
import com.lj.business.member.service.IGuidCardService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 梅宏博
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class GuidCardServiceImplTest extends SpringTestCase{

	@Resource
	IGuidCardService guidCardService;



	/**
	 * 
	 *
	 * 方法说明：添加导购名片表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addGuidCard() throws TsfaServiceException{
		AddGuidCard addGuidCard = new AddGuidCard();
		//add数据录入
		addGuidCard.setCode("Code");
		addGuidCard.setMemberNoGm("MemberNoGm");
		addGuidCard.setMemberNameGm("MemberNameGm");
//		addGuidCard.setShopNo("ShopNo");
//		addGuidCard.setShopName("ShopName");
		addGuidCard.setMerchantNo("MerchantNo");
		addGuidCard.setMerchantName("MerchantName");
		addGuidCard.setAddrInfo("AddrInfo");
		addGuidCard.setStatus("Status");
		addGuidCard.setMobile("Mobile");
		addGuidCard.setAge(15);
		addGuidCard.setGender("Gender");
		addGuidCard.setHeadAddress("HeadAddress");
		addGuidCard.setQcord("Qcord");
		addGuidCard.setPageView(12);
		addGuidCard.setNumPraise(12);
		addGuidCard.setNumCollection(12);
		addGuidCard.setCreateId("CreateId");
		addGuidCard.setCreateDate(new Date());
		
		guidCardService.addGuidCard(addGuidCard );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改导购名片表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void updateGuidCard() throws TsfaServiceException{
		UpdateGuidCard updateGuidCard = new UpdateGuidCard();
		//update数据录入
		updateGuidCard.setCode("Code");
		updateGuidCard.setMemberNoGm("MemberNoGm");
		updateGuidCard.setMemberNameGm("MemberNameGm");
//		updateGuidCard.setShopNo("ShopNo");
//		updateGuidCard.setShopName("ShopName");
		updateGuidCard.setMerchantNo("MerchantNo");
		updateGuidCard.setMerchantName("MerchantName");
		updateGuidCard.setAddrInfo("AddrInfo");
		updateGuidCard.setStatus("Status");
		updateGuidCard.setMobile("Mobile");
		updateGuidCard.setAge(12);
		updateGuidCard.setGender("Gender");
		updateGuidCard.setHeadAddress("HeadAddress");
		updateGuidCard.setQcord("Qcord");
		updateGuidCard.setPageView(12);
		updateGuidCard.setNumPraise(12);
		updateGuidCard.setNumCollection(12);
		updateGuidCard.setCreateId("CreateId");
		updateGuidCard.setCreateDate(new Date());

		guidCardService.updateGuidCard(updateGuidCard );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购名片表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findGuidCard() throws TsfaServiceException{
		FindGuidCard findGuidCard = new FindGuidCard();
		findGuidCard.setCode("111");
		guidCardService.findGuidCard(findGuidCard);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购名片表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findGuidCardPage() throws TsfaServiceException{
		FindGuidCardPage findGuidCardPage = new FindGuidCardPage();
		Page<FindGuidCardPageReturn> page = guidCardService.findGuidCardPage(findGuidCardPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除导购名片表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void delGuidCard() throws TsfaServiceException{
		DelGuidCard delGuidCard = new DelGuidCard();
		delGuidCard.setCode("111");
		guidCardService.delGuidCard(delGuidCard);
		
	}
	
	@Test
	public void findGuidCardByGm() throws Exception {
		FindGuidCard findGuidCard = new FindGuidCard();
		findGuidCard.setMemberNoGm("da08e545a349485aa8faa42a34d43978");
		//findGuidCard.setJsCode("003G5BFC1jjZ130wSKEC1AryFC1G5BF6");
		FindGuidCardReturn findGuidCardByGm = guidCardService.findGuidCardByGm(findGuidCard);
		System.out.println(findGuidCardByGm);
	}
	
	@Test
	public void findGuidCards() throws Exception {
		GuidCardAddNumDto guidCardAddNumDto = new GuidCardAddNumDto();
		//guidCardAddNumDto.setJsCode("1111");
		guidCardService.findGuidCards(guidCardAddNumDto);
	}
}
