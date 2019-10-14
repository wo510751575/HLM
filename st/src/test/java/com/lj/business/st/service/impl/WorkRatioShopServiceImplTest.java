package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.domain.WorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.AddWorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.FindExcellentShop;
import com.lj.business.st.dto.WorkRatioShop.FindExcellentShopReturn;
import com.lj.business.st.dto.WorkRatioShop.FindTopTenShop;
import com.lj.business.st.dto.WorkRatioShop.FindTopTenShopReturn;
import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShopReturn;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.service.IWorkRatioShopService;
import com.lj.business.st.util.TestHelp;


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
public class WorkRatioShopServiceImplTest extends SpringTestCase{

	@Resource
	IWorkRatioShopService workRatioShopService;



	/**
	 * 
	 *
	 * 方法说明：添加导购工作统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addWorkRatioShop() throws TsfaServiceException{
		AddWorkRatioShop addWorkRatioShop = new AddWorkRatioShop();
		//add数据录入
		addWorkRatioShop.setMerchantNo(TestHelp.merchantNo_test);
		addWorkRatioShop.setShopNo("LJ_11b0d6cfa5ca4d7e91251e09c7062001");
		addWorkRatioShop.setShopName("芝华仕深圳佳得宝店");
		addWorkRatioShop.setMemberNoSp(TestHelp.memberNoShop_test);
		addWorkRatioShop.setMemberNameSp("芝華仕_欧洲城建才");
		addWorkRatioShop.setAreaCode("1");
		addWorkRatioShop.setProvinceCode("ProvinceCode");
		addWorkRatioShop.setCityCode("CityCode");
		addWorkRatioShop.setCityAreaCode("CityAreaCode");
		addWorkRatioShop.setDimensionSt(DimensionSt.MERCHANT.toString());
		addWorkRatioShop.setHeadAddress("/headImg/gm_head.jpg");
		addWorkRatioShop.setLogoAddr("/headImg/picture.png");
		addWorkRatioShop.setNumOrder(1L);
		addWorkRatioShop.setNumPmAdd(1L);
		addWorkRatioShop.setNumPm(1L);
		addWorkRatioShop.setNumPmCf(1L);
		addWorkRatioShop.setNumPmKeep(1L);
		addWorkRatioShop.setNumPmAbandon(1L);
		addWorkRatioShop.setNumSale(1L);
		//addWorkRatioShop.setOrderRate();
		addWorkRatioShop.setNumOrderRank(1);
		addWorkRatioShop.setNumPmAddRank(1);
		addWorkRatioShop.setNumPmRank(1);
		addWorkRatioShop.setNumPmCfRank(1);
		addWorkRatioShop.setNumPmKeepRank(1);
		addWorkRatioShop.setNumPmAbandonRank(1);
		addWorkRatioShop.setNumSaleRank(1);
		addWorkRatioShop.setDaySt(new Date());
		addWorkRatioShop.setOpenDate(new Date());
		addWorkRatioShop.setCreateDate(new Date());
		workRatioShopService.addWorkRatioShop(addWorkRatioShop );
		
	}
	

	@Test
	public void findWorkRatioShopList() throws TsfaServiceException{
		FindWorkRatioShop findWorkRatioShop = new FindWorkRatioShop();
		findWorkRatioShop.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
		findWorkRatioShop.setAreaCode("4");
		findWorkRatioShop.setFlag("ORDER");
		findWorkRatioShop.setDimensionSt("SHOP");
		findWorkRatioShop.setBeginDate("2017-09-04 00:00:00");
		findWorkRatioShop.setEndDate("2017-09-10 23:59:59");
		List<WorkRatioShop> workRatioShopList = workRatioShopService.findWorkRatioShopList(findWorkRatioShop);
		System.out.println(workRatioShopList.toString());
	}
	
	
	@Test
	public void findWorkRatioShopPage() throws TsfaServiceException{
		Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("merchantNo", "1f169ad6143d46f5832535642ce2d331");
		parmMap.put("dimensionSt",DimensionSt.SHOP.toString());
		parmMap.put("start", 0);
		parmMap.put("limit", 10);
		Page<WorkRatioShop>  page= workRatioShopService.findWorkRatioShopPage(parmMap);
		System.out.println(page.toString());
	}
	
	@Test
	public void findWorkRatioShops() throws TsfaServiceException{
		Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("merchantNo", "e79d975846ee41ba8c3c55738fda66a9");
		parmMap.put("dimensionSt", DimensionSt.MERCHANT.toString());
		parmMap.put("startTime",DateUtils.getPreday(new Date()));
		parmMap.put("endTime",new Date());
		List<FindWorkRatioShopReturn>  page= workRatioShopService.findWorkRatioShopNum(parmMap);
		System.out.println(page.toString());
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询TOP 10店长_H5
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年8月1日
	 *
	 */
	@Test
	public void findTopTenShop() throws TsfaServiceException{
		FindTopTenShop findTopTenShop = new FindTopTenShop();
		findTopTenShop.setMerchantNo(TestHelp.merchantNo_test);
		List<FindTopTenShopReturn> workRatioShopList = workRatioShopService.findTopTenShop(findTopTenShop);
	}
	

	/**
	 * 
	 *
	 * 方法说明：优秀门店_H5 BOSS看所有门店，区域经理看所属区域，店长看所属城市
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年8月1日
	 *
	 */
	@Test
	public void findExcellentShop() throws TsfaServiceException{
		FindExcellentShop findExcellentShop = new FindExcellentShop();
		findExcellentShop.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findExcellentShop.setShopNo("LJ_c269478af52646b692fcc48deb10a7ad");
		List<FindExcellentShopReturn> workRatioShopList = workRatioShopService.findExcellentShop(findExcellentShop);
		System.out.println(workRatioShopList);
	}
	
	@Test
	public void indexSale() throws TsfaServiceException{
		Map<String,Object> parmMap = new HashMap<String, Object>();
    	parmMap.put("merchantNo", "e79d975846ee41ba8c3c55738fda66a9");
    	parmMap.put("shopNo", "LJ_c269478af52646b692fcc48deb10a7ad");
    	Map<String, Object> returnMap = workRatioShopService.indexSale(parmMap);
		System.out.println(returnMap);
	}
	@Test
	public void findWorkRatioShopReturnList() throws TsfaServiceException{
		FindWorkRatioShop findWorkRatioShop=new FindWorkRatioShop();
		findWorkRatioShop.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		List<FindExcellentShopReturn> list=workRatioShopService.findWorkRatioShopReturnList(findWorkRatioShop);
		System.out.println(list.toString());
		
	}
	
}
