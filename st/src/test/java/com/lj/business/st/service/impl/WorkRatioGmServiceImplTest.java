package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.dao.IWorkRatioGmDao;
import com.lj.business.st.domain.WorkRatioGm;
import com.lj.business.st.dto.AddWorkRatioGm;
import com.lj.business.st.dto.DelWorkRatioGm;
import com.lj.business.st.dto.FindFollowClientTotalIndex;
import com.lj.business.st.dto.FindOperateAnalyzeIndexReturn;
import com.lj.business.st.dto.FindOperateDayReport;
import com.lj.business.st.dto.FindWorkDayGmIndex;
import com.lj.business.st.dto.FindWorkRatioGm;
import com.lj.business.st.dto.FindWorkRatioGmPage;
import com.lj.business.st.dto.FindWorkRatioGmPageReturn;
import com.lj.business.st.dto.FindWrgTotal;
import com.lj.business.st.dto.FindWrgTotalReturn;
import com.lj.business.st.dto.UpdateWorkRatioGm;
import com.lj.business.st.service.IWorkRatioGmService;
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
public class WorkRatioGmServiceImplTest extends SpringTestCase{

	@Resource
	IWorkRatioGmService workRatioGmService;

	@Resource
	IWorkRatioGmDao workRatioGmDao;

	/**
	 * 
	 *
	 * 方法说明：添加导购工作统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void addWorkRatioGm() throws TsfaServiceException{
		for (int i = 1; i < 2; i++) {
			AddWorkRatioGm addWorkRatioGm = new AddWorkRatioGm();
			//add数据录入
			addWorkRatioGm.setMerchantNo(TestHelp.merchantNo_test);
			addWorkRatioGm.setAreaCode("AreaCode");
			addWorkRatioGm.setAreaName("AreaName");
			addWorkRatioGm.setShopNo(TestHelp.shopNo_test);
			addWorkRatioGm.setShopName("ShopName");
			addWorkRatioGm.setMemberNoGm("fa49303dfaf34be2be9c8add34b630f8");
			addWorkRatioGm.setMemberNameGm("卖沙发的小强");
			addWorkRatioGm.setHeadAddress("/headImg/gm_head.jpg");
			addWorkRatioGm.setRatioWork(0.88);
			addWorkRatioGm.setNumPmIntention(20L);
			addWorkRatioGm.setNumPmAbandon(10L);
			addWorkRatioGm.setNumSocial(20L);
			addWorkRatioGm.setNumPm(80L);
			addWorkRatioGm.setNumPmAdd(90L);
			addWorkRatioGm.setNumSale(5000000L);
			addWorkRatioGm.setNumOrder(60L);
			addWorkRatioGm.setNumRead(70L);
			addWorkRatioGm.setRatioWorkRank(10);
			addWorkRatioGm.setNumPmRank(20);
			addWorkRatioGm.setNumPmAddRank(30);
			addWorkRatioGm.setNumSaleRank(40);
			addWorkRatioGm.setNumOrderRank(50);
			addWorkRatioGm.setNumReadRank(60);
			Date workDate =  org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
			workDate = com.lj.base.core.util.DateUtils.addDays(workDate, -i);
			addWorkRatioGm.setDaySt(workDate);
			addWorkRatioGm.setDimensionSt("DimensionSt");

			workRatioGmService.addWorkRatioGm(addWorkRatioGm);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：修改导购工作统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void updateWorkRatioGm() throws TsfaServiceException{
		UpdateWorkRatioGm updateWorkRatioGm = new UpdateWorkRatioGm();
		//update数据录入
		updateWorkRatioGm.setCode("Code");
		updateWorkRatioGm.setMerchantNo("MerchantNo");
		updateWorkRatioGm.setAreaCode("AreaCode");
		updateWorkRatioGm.setAreaName("AreaName");
		updateWorkRatioGm.setShopNo("ShopNo");
		updateWorkRatioGm.setShopName("ShopName");
		updateWorkRatioGm.setMemberNoGm("MemberNoGm");
		updateWorkRatioGm.setMemberNameGm("MemberNameGm");
		updateWorkRatioGm.setHeadAddress("HeadAddress");
		updateWorkRatioGm.setRatioWork(0.88);
		updateWorkRatioGm.setNumPm(100L);
		updateWorkRatioGm.setNumPmAdd(100L);
		updateWorkRatioGm.setNumSale(100L);
		updateWorkRatioGm.setNumOrder(100L);
		updateWorkRatioGm.setNumRead(100L);
		updateWorkRatioGm.setRatioWorkRank(100);
		updateWorkRatioGm.setNumPmRank(100);
		updateWorkRatioGm.setNumPmAddRank(100);
		updateWorkRatioGm.setNumSaleRank(100);
		updateWorkRatioGm.setNumOrderRank(100);
		updateWorkRatioGm.setNumReadRank(100);
		workRatioGmService.updateWorkRatioGm(updateWorkRatioGm );

	}

	/**
	 * 
	 *
	 * 方法说明：查找导购工作统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findWorkRatioGm() throws TsfaServiceException{
		FindWorkRatioGm findWorkRatioGm = new FindWorkRatioGm();
		findWorkRatioGm.setCode("111");
		workRatioGmService.findWorkRatioGm(findWorkRatioGm);
	}

	/**
	 * 
	 *
	 * 方法说明：查找导购工作统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findWorkRatioGmPage() throws TsfaServiceException{
		FindWorkRatioGmPage findWorkRatioGmPage = new FindWorkRatioGmPage();
		Page<FindWorkRatioGmPageReturn> page = workRatioGmService.findWorkRatioGmPage(findWorkRatioGmPage);
		Assert.assertNotNull(page);

	}

	/**
	 * 
	 *
	 * 方法说明：删除导购工作统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void delWorkRatioGm() throws TsfaServiceException{
		DelWorkRatioGm delWorkRatioGm = new DelWorkRatioGm();
		delWorkRatioGm.setCode("111");
		workRatioGmService.delWorkRatioGm(delWorkRatioGm);

	}



	/**
	 * 
	 *
	 * 方法说明：查找导购工作统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月10日
	 *
	 */
	@Test
	public void findWrgTotal() throws TsfaServiceException{
		FindWrgTotal findWrgTotal = new FindWrgTotal();
		findWrgTotal.setMerchantNo("1f169ad6143d46f5832535642ce2d331");
		Date beginDate = new Date();
		beginDate = DateUtils.truncate(beginDate, Calendar.DAY_OF_MONTH);
		findWrgTotal.setBeginDate(beginDate);
		Date endDate = beginDate;
		findWrgTotal.setEndDate(endDate );
		findWrgTotal.setShopNo("LJ_82039188265241e0bd8f87651db6ab3c");
		findWrgTotal.setFlag("CUSTOMER_MAX");
		List<FindWrgTotalReturn> page = workRatioGmService.findWrgTotal(findWrgTotal);
		Assert.assertNotNull(page);

	}
	
	@Test
	public void findWorkRatioShopByDay() throws Exception {
		List<WorkRatioGm> findWorkRatioShopByDay = workRatioGmService.findWorkRatioShopByDay(com.lj.base.core.util.DateUtils.getPreday(new Date()));
		for (WorkRatioGm workRatioGm : findWorkRatioShopByDay) {
			System.out.println(workRatioGm);
		}
	}
	
	@Test
	public void findWorkDayGmIndex() throws Exception {
		FindWorkDayGmIndex findWorkDayGmIndex = new FindWorkDayGmIndex();
		findWorkDayGmIndex.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
		findWorkDayGmIndex.setShopNo("LJ_accb0893636244d19e1bf4bf86120e41");
		findWorkDayGmIndex.setMemberNoGm("374c529663bd4de68dce27d6138a5114");
		findWorkDayGmIndex.setStDate("2017-10-27");
		System.out.println(workRatioGmService.findWorkDayGmIndex(findWorkDayGmIndex));
		
	}
	

	@Test
	public void findWorkRatioGmParams() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		String yesterday = com.lj.base.core.util.DateUtils.formatDate(cal.getTime(), com.lj.base.core.util.DateUtils.PATTERN_yyyy_MM_dd);
		FindOperateDayReport yesterdayFindOperateDayReport = new FindOperateDayReport();
		yesterdayFindOperateDayReport.setBeginDate(yesterday + " 00:00:00");
		yesterdayFindOperateDayReport.setEndDate(yesterday + " 23:59:59");
		List<WorkRatioGm> list = workRatioGmDao.findWorkRatioGmParams(yesterdayFindOperateDayReport);
		for (WorkRatioGm workRatioGm : list) {
			System.out.println(workRatioGm);
		}
	}
	
	@Test
	public void findOperateAnalyzeIndex() throws Exception {
		FindOperateDayReport findOperateDayReport = new FindOperateDayReport();
		findOperateDayReport.setMerchantNo("e79d975846ee41ba8c3c55738fda66a9");
		findOperateDayReport.setShopNo("LJ_c269478af52646b692fcc48deb10a7ad");
		findOperateDayReport.setBeginDate("2017-09-07 00:00:00");
		findOperateDayReport.setEndDate("2017-09-07 23:59:59");
		findOperateDayReport.setDimensionSt("SHOP");
		FindOperateAnalyzeIndexReturn analyzeIndex = workRatioGmService.findOperateAnalyzeIndex(findOperateDayReport);
		System.out.println(analyzeIndex);
	}
	
	@Test
	public void findFcTotalIndex() throws Exception {
		FindFollowClientTotalIndex findFollowClientTotalIndex = new FindFollowClientTotalIndex();
		findFollowClientTotalIndex.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
		findFollowClientTotalIndex.setMemberNoGm("421e31676b784957b8baa3015f1b52d0");
		findFollowClientTotalIndex.setShopNo("LJ_dbfd184dbe6e4233b03596177c465aaf");
		findFollowClientTotalIndex.setBeginDate("2017-08-11");
		findFollowClientTotalIndex.setEndDate("2017-09-10");
		workRatioGmService.findFcTotalIndex(findFollowClientTotalIndex);
	}

}
