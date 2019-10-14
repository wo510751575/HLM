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

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.MaterialTotal.AddMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotalReturn;
import com.lj.business.st.service.IMaterialTotalService;


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
public class MaterialTotalServiceImplTest extends SpringTestCase{

	@Resource
	IMaterialTotalService materialTotalService;



	/**
	 * 
	 *
	 * 方法说明：添加素材中心统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addMaterialTotal() throws TsfaServiceException{
		AddMaterialTotal addMaterialTotal = new AddMaterialTotal();
		//add数据录入
		addMaterialTotal.setCode(GUID.generateCode());
		addMaterialTotal.setMerchantNo(GUID.getPreUUID());
		addMaterialTotal.setShopNo(GUID.getPreUUID());
		addMaterialTotal.setShopName("南山分店");
		addMaterialTotal.setMemberNoGm(GUID.getPreUUID());
		addMaterialTotal.setMemberNameGm("邹磊");
		addMaterialTotal.setRespondNum(1);
		//addMaterialTotal.setRatioRespond(12);
		addMaterialTotal.setDaySt(new Date());
		addMaterialTotal.setMaterialTypeCode(GUID.generateCode());
		addMaterialTotal.setMaterialTypeName("产品舒适度");
		addMaterialTotal.setDimensionSt("GUID");
		addMaterialTotal.setCreateDate(new Date());
		
		materialTotalService.addMaterialTotal(addMaterialTotal );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找素材中心统计表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findMaterialTotal() throws TsfaServiceException{
		FindMaterialTotal findMaterialTotal = new FindMaterialTotal();
		materialTotalService.findMaterialTotal(findMaterialTotal);
	}
	
	@Test
	public void findMaterialTotalCount() throws TsfaServiceException{
		FindMaterialTotal findMaterialTotal = new FindMaterialTotal();
		findMaterialTotal.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findMaterialTotal.setMemberNoGm("efbc3bb7be534d24b9cb6d72ac21f666");
		List<FindMaterialTotalReturn> list=materialTotalService.findMaterialTotalCount(findMaterialTotal);
		System.out.println(list.toString());
	}
	
	@Test
	public void findMaterialTotalApp() throws Exception {
		FindMaterialTotal findMaterialTotal = new FindMaterialTotal();
		findMaterialTotal.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findMaterialTotal.setShopNo("LJ_c269478af52646b692fcc48deb10a7ad");
		findMaterialTotal.setMemberNoGm("d7b963349b8f4bcbbed9a36fe41ae626");
		materialTotalService.findMaterialTotalApp(findMaterialTotal);
	}
	
}
