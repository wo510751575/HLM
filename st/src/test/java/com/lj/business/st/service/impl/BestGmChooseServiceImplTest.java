package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.bestGmChoose.AddBestGmChoose;
import com.lj.business.st.dto.bestGmChoose.FindBestGmChoose;
import com.lj.business.st.emus.TypeListBestGmChoose;
import com.lj.business.st.service.IBestGmChooseService;
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
public class BestGmChooseServiceImplTest extends SpringTestCase{

	@Resource
	IBestGmChooseService bestGmChooseService;



	/**
	 * 
	 *
	 * 方法说明：添加优秀导购选择表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addBestGmChoose() throws TsfaServiceException{
		TypeListBestGmChoose typeListBestGmChooseAr [] = TypeListBestGmChoose.values();
		int count = 0;
		for (TypeListBestGmChoose typeListBestGmChoose : typeListBestGmChooseAr) {
			AddBestGmChoose addBestGmChoose = new AddBestGmChoose();
			//add数据录入
			addBestGmChoose.setMerchantNo(TestHelp.merchantNo_test);
			addBestGmChoose.setShopNo(TestHelp.shopNo_test);
			addBestGmChoose.setShopName("ShopName");
			addBestGmChoose.setMemberNoGm(TestHelp.memberNoGm_test);
			addBestGmChoose.setMemberNameGm("MemberNameGm");
			addBestGmChoose.setCodeList("CodeList");
			addBestGmChoose.setNameList(typeListBestGmChoose.getName());
			addBestGmChoose.setTypeList(typeListBestGmChoose.toString());
			addBestGmChoose.setSeq(++count);
			addBestGmChoose.setImgAddr("/hero/EpRk_"+ count +".png");
			bestGmChooseService.addBestGmChoose(addBestGmChoose );
		}
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找优秀导购选择表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findBestGmChoose() throws TsfaServiceException{
		FindBestGmChoose findBestGmChoose = new FindBestGmChoose();
		findBestGmChoose.setCode("111");
		bestGmChooseService.findBestGmChoose(findBestGmChoose);
	}
	
	
}
