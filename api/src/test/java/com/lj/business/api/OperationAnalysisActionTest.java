//package com.lj.business.api;
//
//import static org.junit.Assert.*;
//
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//
//import com.lj.base.mvc.web.test.SpringTestCase;
//import com.lj.business.api.controller.OperationAnalysisAction;
//import com.lj.business.st.dto.FindNewCustomerReturn;
//import com.lj.business.st.dto.FindSalesFunnelReturn;
//import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyze;
//import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShop;
//
//public class OperationAnalysisActionTest extends SpringTestCase {
//	
//	@Resource
//	OperationAnalysisAction operationAnalysisAction;
//	
//	@Test
//	public void findNewCustomer() throws Exception {
//		FindWorkRatioShop findWorkRatioShop = new FindWorkRatioShop();
//		findWorkRatioShop.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
//		findWorkRatioShop.setShopNo("LJ_c269478af52646b692fcc48deb10a7ad");
//		FindNewCustomerReturn findNewCustomer = operationAnalysisAction.findNewCustomer(findWorkRatioShop);
//		System.out.println(findNewCustomer);
//	}
//	
//	@Test
//	public void findAreaOrderAnalyzeList() throws Exception {
//		FindAreaOrderAnalyze findAreaOrderAnalyze = new FindAreaOrderAnalyze();
//		findAreaOrderAnalyze.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
//		operationAnalysisAction.findAreaOrderAnalyzeList(findAreaOrderAnalyze);
//	}
//	
//	@Test
//	public void indexSale() throws Exception {
//		Map<String, Object> map = operationAnalysisAction.indexSale("e79d975846ee41ba8c3c55738fda66a9", "", "", "");
//		for (Entry<String, Object> entry : map.entrySet()) {
//			System.out.println(entry.getKey());
//			System.out.println(entry.getValue());
//		}
//	}
//	
//	@Test
//	public void findSalesFunnel() throws Exception {
//		FindWorkRatioShop findWorkRatioShop = new FindWorkRatioShop();
//		findWorkRatioShop.setDimensionSt("SHOP");
//		findWorkRatioShop.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
//		findWorkRatioShop.setShopNo("LJ_4732bfda70fb4bc0b6ad0e38dcc4986b");
//		FindSalesFunnelReturn salesFunnel = operationAnalysisAction.findSalesFunnel(findWorkRatioShop);
//		
//	}
//
//}
