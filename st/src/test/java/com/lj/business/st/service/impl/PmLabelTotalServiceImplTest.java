package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.PmLabelTotal.AddPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotalReturnDto;
import com.lj.business.st.service.IPmLabelTotalService;


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
public class PmLabelTotalServiceImplTest extends SpringTestCase{

	@Resource
	IPmLabelTotalService pmLabelTotalService;



	/**
	 * 
	 *
	 * 方法说明：添加客户标签统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addPmLabelTotal() throws TsfaServiceException{
		AddPmLabelTotal addPmLabelTotal = new AddPmLabelTotal();
		//add数据录入
		addPmLabelTotal.setCode(GUID.generateCode());
		addPmLabelTotal.setMerchantNo(GUID.getPreUUID());
		addPmLabelTotal.setShopNo(GUID.getPreUUID());
		addPmLabelTotal.setShopName("南山分店");
		addPmLabelTotal.setMemberNoGm(GUID.getPreUUID());
		addPmLabelTotal.setMemberNameGm("邹磊");
		addPmLabelTotal.setLabelId(GUID.getPreUUID());
		addPmLabelTotal.setLabelName("敏华");
		addPmLabelTotal.setPmNum(1);
		//addPmLabelTotal.setRatioPm();
		addPmLabelTotal.setDimensionSt("商户");
		addPmLabelTotal.setCreateDate(new Date());
		
		pmLabelTotalService.addPmLabelTotal(addPmLabelTotal );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找客户标签统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findPmLabelTotal() throws TsfaServiceException{
		FindPmLabelTotal findPmLabelTotal = new FindPmLabelTotal();
		//findPmLabelTotal.setCode("111");
		pmLabelTotalService.findPmLabelTotal(findPmLabelTotal);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找客户标签统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findPmLabelTotalList() throws TsfaServiceException{
		FindPmLabelTotal findPmLabelTotal = new FindPmLabelTotal();
		findPmLabelTotal.setShopNo("LJ_315791ccff9f40bdb5f420e8517c3856");
		pmLabelTotalService.findPmLabelTotal(findPmLabelTotal);
	}
	
	@Test
	public void findPmLabelTotals() throws TsfaServiceException{
		FindPmLabelTotal findPmLabelTotal = new FindPmLabelTotal();
		findPmLabelTotal.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findPmLabelTotal.setMemberNoGm("efbc3bb7be534d24b9cb6d72ac21f666");
		FindPmLabelTotalReturnDto list=pmLabelTotalService.findPmLabelTotalMax(findPmLabelTotal);
		System.out.println(list.toString());
	}
	
	@Test
	public void findPmLabelTotalListApp() throws Exception {
		FindPmLabelTotal findPmLabelTotal = new FindPmLabelTotal();
		findPmLabelTotal.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findPmLabelTotal.setShopNo("LJ_c269478af52646b692fcc48deb10a7ad");
		findPmLabelTotal.setMemberNoGm("d7b963349b8f4bcbbed9a36fe41ae626");
		List<FindPmLabelTotalReturnDto> list = pmLabelTotalService.findPmLabelTotalListApp(findPmLabelTotal);
		System.out.println(list);
	}
	
}
