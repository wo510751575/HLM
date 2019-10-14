package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.PmTalkTotal.AddPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalAllReturnList;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalReturn;
import com.lj.business.st.service.IPmTalkTotalService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class PmTalkTotalServiceImplTest extends SpringTestCase{

	@Resource
	IPmTalkTotalService pmTalkTotalService;



	/**
	 * 
	 *
	 * 方法说明：添加客户咨询统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addPmTalkTotal() throws TsfaServiceException{
		AddPmTalkTotal addPmTalkTotal = new AddPmTalkTotal();
		//add数据录入
		addPmTalkTotal.setCode("Code");
		addPmTalkTotal.setMerchantNo("MerchantNo");
		addPmTalkTotal.setAreaCode("AreaCode");
		addPmTalkTotal.setAreaName("AreaName");
		addPmTalkTotal.setShopNo("ShopNo");
		addPmTalkTotal.setShopName("ShopName");
		addPmTalkTotal.setMemberNoGm("MemberNoGm");
		addPmTalkTotal.setMemberNameGm("MemberNameGm");
		addPmTalkTotal.setStartDate(new Date());
		addPmTalkTotal.setEndDate(new Date());
		addPmTalkTotal.setNumTalk(1);
		addPmTalkTotal.setDaySt(new Date());
		addPmTalkTotal.setDimensionSt("DimensionSt");
		addPmTalkTotal.setCreateDate(new Date());
		
		pmTalkTotalService.addPmTalkTotal(addPmTalkTotal );
		
	}
	@Test
	public void findPmTalkTotalMax(){
		
		FindPmTalkTotal findPmTalkTotal=new FindPmTalkTotal();
		findPmTalkTotal.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findPmTalkTotal.setMemberNoGm("efbc3bb7be534d24b9cb6d72ac21f666");
		FindPmTalkTotalReturn list=pmTalkTotalService.findPmTalkTotalMax(findPmTalkTotal);
		System.out.println(list.toString());
	}
	@Test
	public void findPmLabelTotalMax(){
	 FindPmTalkTotalAllReturnList list=pmTalkTotalService.findPmTalkTotaReturnMax();
	 Date date=list.getStartDate();
	 System.out.println(date.toString());
	}
	@Test
    public void findPmTalkTotaReturnList(){
	 FindPmTalkTotal findPmTalkTotal=new FindPmTalkTotal();
	 findPmTalkTotal.setMerchantNo("0");
	 List<FindPmTalkTotalAllReturnList> list= pmTalkTotalService.findPmTalkTotaReturnList(findPmTalkTotal);
	 System.out.println(list.toString());
      }
	@Test
	public void findPmTalkTotaReturnData()throws TsfaServiceException{
		FindPmTalkTotal findPmTalkTotal=new FindPmTalkTotal();
		findPmTalkTotal.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		List<FindPmTalkTotalAllReturnList> list=pmTalkTotalService.findPmTalkTotaReturnData(findPmTalkTotal);
		if(list.size()>0){
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		System.out.println( sdf.format(list.get(0).getStartDate())+"-"+ sdf.format(list.get(0).getEndDate()));
		}
		System.out.println(list.size());
		
	}
	
}
