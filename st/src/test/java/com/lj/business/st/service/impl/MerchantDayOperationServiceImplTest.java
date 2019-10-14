package com.lj.business.st.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dto.MerchantDayOperateDto;
import com.lj.business.st.dto.ShopDayOperateDto;
import com.lj.business.st.service.IMerchantDayOperationService;

public class MerchantDayOperationServiceImplTest extends SpringTestCase {
	
	@Resource
	private IMerchantDayOperationService merchantDayOperationService;
	
	@Test
	public void generatorMerChantDayOperate() throws Exception {
		MerchantDayOperateDto merchantDayOperate = merchantDayOperationService.generatorMerChantDayOperate("2169d1820e254e86b110dff73e1bd58f");
		Map<String, ShopDayOperateDto> shopData = merchantDayOperate.getShopData();
		for (Entry<String, ShopDayOperateDto> entry : shopData.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

}
