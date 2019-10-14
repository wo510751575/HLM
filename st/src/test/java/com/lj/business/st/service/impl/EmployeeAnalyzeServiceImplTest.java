package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.AddEmployeeAnalyze;
import com.lj.business.st.service.IEmployeeAnalyzeService;
import com.lj.business.st.util.TestHelp;


/**
 * 
 * 
 * 类说明：员工画像统计
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月31日
 */
public class EmployeeAnalyzeServiceImplTest extends SpringTestCase{

	@Resource
  private IEmployeeAnalyzeService employeeAnalyzeService;



    /**
     * 
     *
     * 方法说明：新增客户画像
     *
     * @throws TsfaServiceException
     *
     * @author 罗书明 CreateDate: 2017年7月31日
     *
     */
	@Test
	public void addEmployeeAnalyze() throws TsfaServiceException{
		AddEmployeeAnalyze addEmployeeAnalyze = new AddEmployeeAnalyze();
		//add数据录入
		addEmployeeAnalyze.setCode("Code");
		addEmployeeAnalyze.setMerchantNo("MerchantNo");
		addEmployeeAnalyze.setAreaCode("AreaCode");
		addEmployeeAnalyze.setAreaName("AreaName");
		addEmployeeAnalyze.setShopNo("ShopNo");
		addEmployeeAnalyze.setShopName("ShopName");
		addEmployeeAnalyze.setStDate(new Date());
		addEmployeeAnalyze.setNumEmployee(2l);
		addEmployeeAnalyze.setRatioAgeTwenty(2.2);
		addEmployeeAnalyze.setNumAgeTwenty(2);
		addEmployeeAnalyze.setRatioAgeThirty(5.2);
		addEmployeeAnalyze.setNumAgeThirty(2);
		addEmployeeAnalyze.setRatioAgeForty(2.2);
		addEmployeeAnalyze.setNumAgeForty(2);
		addEmployeeAnalyze.setDimensionSt("2");
		addEmployeeAnalyze.setNumAgeFifty(2);
		addEmployeeAnalyze.setRatioAgeFifty(1.1);
		addEmployeeAnalyze.setCreateDate(new Date());		
		Assert.assertNotNull(employeeAnalyzeService.addEmployeeAnalyze(addEmployeeAnalyze ));
		
	}
	
   /**
    * 
    *
    * 方法说明：查询员工画像统计
    * 
    *
    * @throws TsfaServiceException
    *
    * @author 罗书明 CreateDate: 2017年7月31日
    *
    */
	@Test
    public void findEmployeeAnalyzeList() throws TsfaServiceException{
	   Map<String,Object> map=new HashMap<>();
	   map.put("merchantNo", TestHelp.merchantNo_test);
	  List<Map<String,Object>> maps=employeeAnalyzeService.findEmployeeAnalyzeList(map);
	  System.out.println(maps);
		
   }
	
	
}
