package com.lj.business.st.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.service.IReportGeneratorService;

public class JobTest extends SpringTestCase {
	
	@Resource
	private IReportGeneratorService reportGeneratorService;
	
	Date queryDate = new Date();
	
	@Test
	public void jobTest() throws Exception {

		try {
			
			try {
				//生成导购工作统计
				reportGeneratorService.generatorWorkRatioGm(queryDate);
			} catch (Exception e) {
				System.out.println(e + "1");
			}
			
			try {
			//生成门店工作统计
			reportGeneratorService.generatorWorkRatioShop(DateUtils.getPreday(new Date()));
			} catch (Exception e) {
				System.out.println(e + "2");
			}
			
			try {
			//生成到店体验统计
			reportGeneratorService.generatorClientExpTotal(queryDate);
			} catch (Exception e) {
				System.out.println(e + "3");
			}
			
			try {
			//生成员工画像
			reportGeneratorService.generatorEmployeeAnalyze(); //2 T
			} catch (Exception e) {
				System.out.println(e + "4");
			}
			
			try {
			//生成客户标签统计
			reportGeneratorService.generatorPmLabelTotal();
			} catch (Exception e) {
				System.out.println(e + "5");
			}
			
			try {
			//素材中心统计
			reportGeneratorService.generatorMaterialTotal();
			} catch (Exception e) {
				System.out.println(e + "6");
			}
			
			try {
			//统计跟踪分析
			reportGeneratorService.generatorCfCountAnalyze(); //2 T
			} catch (Exception e) {
				System.out.println(e + "7");
			}
			
			try {
		  //区域订单统计
		    reportGeneratorService.generatorAreaOrderAnalyze(); //2 T
			} catch (Exception e) {
				System.out.println(e + "9");
			}
			
			try {
		    //区域工作统计
		    reportGeneratorService.generatorWorkRatioArea(); //1 
			} catch (Exception e) {
				System.out.println(e + "10");
			}
			
			try {
			//客户咨询统计
		    reportGeneratorService.generatorPmTalkTotal(); //2 T
			} catch (Exception e) {
				System.out.println(e + "12");
			}
			
			try {
		    //社交分析统计
		    reportGeneratorService.generatorSocialAnalyze(queryDate); //2 T
			} catch (Exception e) {
				System.out.println(e + "13");
			}
			
			try {
			//客户分类
		    reportGeneratorService. generatorPmTypeTotal(); //2 T
			} catch (Exception e) {
				System.out.println(e + "15");
			}
			
			try {
		   //增加或更新客户画像统计
		    reportGeneratorService.addOrUpdateClientAnalyzeStatistics();
			} catch (Exception e) {
				System.out.println(e + "16");
			}
			
			try {
		    //客户画像
			reportGeneratorService.addOrUpdateClientAnalyzeStatistics();
			} catch (Exception e) {
				System.out.println(e + "17");
			}

			try {
			//跟进分析摘要
		    reportGeneratorService.generatorCfAnalyze(queryDate); //2  T
			} catch (Exception e) {
				System.out.println(e + "8");
			}

			try {
		    //运营分析摘要统计
		   reportGeneratorService.generatorOperationAnalysisDayBrief(queryDate); //2T
			} catch (Exception e) {
				System.out.println(e + "11");
			}

			try {
		   //未联系客户分析统计
		    reportGeneratorService.generatorUnContactAnalyze();//T
			} catch (Exception e) {
				System.out.println(e + "14");
			}
			
		} catch (Exception e) {
			logger.error("triggerDayChange()", e); //$NON-NLS-1$
			throw new TsfaServiceException(ErrorCode.REMIND_JOB_ERROR,"客户提醒JOB错误",e);
		}
	
	}

}
