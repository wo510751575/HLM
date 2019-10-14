package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dao.IAreaOrderAnalyzeDao;
import com.lj.business.st.domain.AreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyzeReturn;
import com.lj.business.st.service.IAreaOrderAnalyzeService;


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
public class AreaOrderAnalyzeServiceImplTest extends SpringTestCase{

	@Resource
	IAreaOrderAnalyzeService areaOrderAnalyzeService;
	
	@Resource
	private IAreaOrderAnalyzeDao areaOrderAnalyzeDao;

  


	/**
	 * 
	 *
	 * 方法说明：添加区域订单分析表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addAreaOrderAnalyze() throws TsfaServiceException{
/*		AddAreaOrderAnalyze addAreaOrderAnalyze = new AddAreaOrderAnalyze();
		//add数据录入
		addAreaOrderAnalyze.setCode("Code");
		addAreaOrderAnalyze.setMerchantNo("MerchantNo");
		addAreaOrderAnalyze.setAreaCode("AreaCode");
		addAreaOrderAnalyze.setAreaName("AreaName");
		addAreaOrderAnalyze.setStDate(new Date());
		addAreaOrderAnalyze.setNumOrder(1L);
		addAreaOrderAnalyze.setCreateDate(new Date());
		
		areaOrderAnalyzeService.addAreaOrderAnalyze(addAreaOrderAnalyze );*/
		
		System.out.println((double)5/100);
		
	}
	/**
	 * 
	 *
	 * 方法说明：查找区域订单分析表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
//	@Test
//	public void findAreaOrderAnalyze() throws TsfaServiceException{
//		FindAreaOrderAnalyze findAreaOrderAnalyze = new FindAreaOrderAnalyze();
//		findAreaOrderAnalyze.setCode("111");
//		areaOrderAnalyzeService.findAreaOrderAnalyze(findAreaOrderAnalyze);
//	}
	
	@Test
	public void findAreaCodeMaxNum(){
		FindAreaOrderAnalyze findAreaOrderAnalyze=new FindAreaOrderAnalyze();
		findAreaOrderAnalyze.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		List<FindAreaOrderAnalyzeReturn> find	=areaOrderAnalyzeService.findAreaCodeMaxNum(findAreaOrderAnalyze);
		if(find.size()>0){
			String areaName=find.get(0).getAreaName();
			System.out.println(areaName);
		}
		
	}
	
	@Test
	public void findAreaOrderAnalyzeList() throws Exception {
		FindAreaOrderAnalyze findAreaOrderAnalyze = new FindAreaOrderAnalyze();
		findAreaOrderAnalyze.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findAreaOrderAnalyze.setBeginDate("2017-09-03");
		findAreaOrderAnalyze.setEndDate("2017-09-03");
		List<AreaOrderAnalyze> list = areaOrderAnalyzeDao.findAreaOrderAnalyzeList(findAreaOrderAnalyze);
		for (AreaOrderAnalyze areaOrderAnalyze : list) {
			System.out.println(areaOrderAnalyze.getAreaCode());
			System.out.println(areaOrderAnalyze.getAreaName());
			System.out.println(areaOrderAnalyze.getNumOrder());
		}
	}
}
