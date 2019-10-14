package com.lj.business.st.service.impl.job;


import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.service.IReportGeneratorService;

/**
 *
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年8月21日
 */
public class ReportGeneratorJobServiceImplTest extends SpringTestCase{
	
	@Autowired
	private ReportGeneratorJobServiceImpl reportgeneratorJobServiceImpl;
	
	@Resource
	IReportGeneratorService reportGeneratorService;
	
	
	@Test
	public void triggerReportGeneratorJob()throws TsfaServiceException{ 
		reportgeneratorJobServiceImpl.triggerReportGeneratorJob();
	}

	
	@Test
	public void reportGeneratorService()throws TsfaServiceException{ 
		//增加或更新客户画像统计
//		Date queryDate = DateUtils.getPreday(org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		reportGeneratorService.generatorAreaOrderAnalyze();
	}
	
	
}
