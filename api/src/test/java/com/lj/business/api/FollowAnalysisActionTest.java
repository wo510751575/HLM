//package com.lj.business.api;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//
//import com.lj.base.mvc.web.test.SpringTestCase;
//import com.lj.business.api.controller.FollowAnalysisAction;
//import com.lj.business.st.dto.FindFollowClientTotalIndex;
//import com.lj.business.st.dto.FindWorkDayGmIndex;
//
//public class FollowAnalysisActionTest extends SpringTestCase {
//
//	@Resource
//	FollowAnalysisAction followAnalysisAction;
//	
//	@Test
//	public void findFcTotalIndex() throws Exception {
//		FindFollowClientTotalIndex findFollowClientTotalIndex = new FindFollowClientTotalIndex();
//		findFollowClientTotalIndex.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
//		findFollowClientTotalIndex.setShopNo("LJ_cbe4848099454b6483028afbf2b7d98a");
//		findFollowClientTotalIndex.setMemberNoGm("c795e08cadac4867bb7fad496216efaf");
//		findFollowClientTotalIndex.setBeginDateDay("2017-08-30");
//		findFollowClientTotalIndex.setEndDateDay("2017-08-31");
//		findFollowClientTotalIndex.setBeginDateWeek("2017-08-24");
//		findFollowClientTotalIndex.setEndDateWeek("2017-08-31");
//		findFollowClientTotalIndex.setBeginDateMonth("2017-07-31");
//		findFollowClientTotalIndex.setEndDateMonth("2017-08-31");
//		followAnalysisAction.findFcTotalIndex(findFollowClientTotalIndex);
//	}
//	
//	@Test
//	public void findWorkDayGmCfAnalyzeIndex() throws Exception {
//		FindWorkDayGmIndex findWorkDayGmIndex = new FindWorkDayGmIndex();
//		findWorkDayGmIndex.setMemberNoGm("d7b963349b8f4bcbbed9a36fe41ae626");
//		findWorkDayGmIndex.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
//		findWorkDayGmIndex.setShopNo("LJ_c269478af52646b692fcc48deb10a7ad");
//		findWorkDayGmIndex.setStDate("2017-09-08");
//		followAnalysisAction.findWorkDayGmCfAnalyzeIndex(findWorkDayGmIndex);
//	}
//}
