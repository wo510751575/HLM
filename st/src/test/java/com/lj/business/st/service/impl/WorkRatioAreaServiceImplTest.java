package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.emus.DimensionStType;
import com.lj.business.st.dto.AddWorkRatioArea;
import com.lj.business.st.dto.FindWorkRatioArea;
import com.lj.business.st.dto.FindWorkRatioAreaReturn;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.service.IWorkRatioAreaService;
import com.lj.business.st.util.TestHelp;


/**
 * 
 * 
 * 类说明：门店区域分布统计测试类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月27日
 */
public class WorkRatioAreaServiceImplTest extends SpringTestCase{

	@Resource
	IWorkRatioAreaService workRatioAreaService;

	@Test
	public void addWorkRatioArea() throws TsfaServiceException{
		AddWorkRatioArea addWorkRatioArea = new AddWorkRatioArea();
		//add数据录入
		addWorkRatioArea.setAreaCode("7");
		addWorkRatioArea.setAreaName("东北地区");
		addWorkRatioArea.setDimensionSt(DimensionStType.PROVINCE.toString());
		addWorkRatioArea.setMemberNoMerchant(TestHelp.merchantNo_test);
		addWorkRatioArea.setNumShop(2);
		addWorkRatioArea.setNumPm(10L);
		addWorkRatioArea.setProvinceCode("112");
		addWorkRatioArea.setProvinceName("吉林省");
		addWorkRatioArea.setRatioPm(null);
		addWorkRatioArea.setRatioShop(null);
		addWorkRatioArea.setStDate(new Date());
		workRatioAreaService.addWorkRatioArea(addWorkRatioArea);
		
	}
	
	@Test
	public void findWorkRatioAreaPage() throws TsfaServiceException{
		Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("start", 1);
		parmMap.put("limit", 10);
		parmMap.put("memberNoMerchant", TestHelp.merchantNo_test);
		parmMap.put("dimensionSt", DimensionStType.PROVINCE);
		Page<Map<String, Object>> page =workRatioAreaService.findWorkRatioAreaPage(parmMap);
		System.out.println(page.toString());
	}
		
	
	@Test
	public void findWorkRatioAreas() throws TsfaServiceException{
		Map<String,Object> parmMap = new HashMap<String, Object>();
//		parmMap.put("start", 1);
//		parmMap.put("limit", 10);
		parmMap.put("memberNoMerchant", "e79d975846ee41ba8c3c55738fda66a9");
		parmMap.put("startTime",DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd));
		parmMap.put("endTime",DateUtils.formatDate(new Date(), DateUtils.PATTERN_yyyy_MM_dd));
		parmMap.put("dimensionSt", DimensionSt.AREA.toString());
		List<Map<String, Object>> list =workRatioAreaService.findWorkRatioAreas(parmMap);
		for (Map<String, Object> map : list) {
			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
			System.out.println("=======================");
		}
	}
		
	@Test
	public void findBroupProvince() throws TsfaServiceException{
		Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("memberNoMerchant", TestHelp.merchantNo_test);
		parmMap.put("dimensionSt", DimensionStType.PROVINCE);
		List<Map<String, Object>> list =workRatioAreaService.findBroupProvince(parmMap);
		System.out.println(list.toString());
	}
	
	@Test
	public void findShopGroupArea() throws TsfaServiceException{
		Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("memberNoMerchant", "e79d975846ee41ba8c3c55738fda66a9");
		parmMap.put("dimensionSt", "AREA");
		Map<String, Object> list =workRatioAreaService.findShopGroupArea(parmMap);
		System.out.println(list.toString());
	}
     /**
      * "merchantNo":"e79d975846ee41ba8c3c55738fda66a9","dimensionSt":"PROVINCE"}
      */
	@Test
	public void findWorkRatioAreaList()throws TsfaServiceException{
		FindWorkRatioArea findWorkRatioArea=new FindWorkRatioArea();
		findWorkRatioArea.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
		findWorkRatioArea.setDimensionSt("PROVINCE");
		FindWorkRatioAreaReturn areaReturn= workRatioAreaService.findWorkRatioAreaList(findWorkRatioArea);
		System.out.println(areaReturn);
		
	}
}

