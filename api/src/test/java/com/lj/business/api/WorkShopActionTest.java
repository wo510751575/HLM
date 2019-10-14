//package com.lj.business.api;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.lj.base.mvc.web.test.SpringTestCase;
//import com.lj.business.api.controller.WorkShopAction;
//import com.lj.business.st.domain.WorkRatioShop;
//import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShop;
//
//public class WorkShopActionTest extends SpringTestCase {
//	
//	
//	@Autowired
//	private WorkShopAction workShopAction;
//	
//	@Test
//	public void findWorkRatioShopList() throws Exception {
//		FindWorkRatioShop findWorkRatioShop = new FindWorkRatioShop();
//		findWorkRatioShop.setDimensionSt("AREA");
//		findWorkRatioShop.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
//		findWorkRatioShop.setAreaCode("2");
//		findWorkRatioShop.setBeginDate("2017-09-08 00:00:00");
//		findWorkRatioShop.setEndDate("2017-09-08 23:59:59");
//		List<WorkRatioShop> findWorkRatioShopList = workShopAction.findWorkRatioShopList(findWorkRatioShop);
//		for (WorkRatioShop workRatioShop : findWorkRatioShopList) {
//			System.out.println(workRatioShop);
//		}
//	}
//
//}
