package com.lj.business.st.service.impl;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.st.service.IReportGeneratorService;

public class ReportGeneratorServiceImplTest extends SpringTestCase{

	@Autowired
	IReportGeneratorService	reportGeneratorService;
	
	@Test
	public void generatorClientExpTotal() throws TsfaServiceException{
		reportGeneratorService.generatorClientExpTotal(new Date());
	}
	
	@Test
	public void generatorEmployeeAnalyze() throws TsfaServiceException{
		reportGeneratorService.generatorEmployeeAnalyze();
	}
	
	@Test
	public void generatorSocialAnalyze() throws TsfaServiceException{
		reportGeneratorService.generatorSocialAnalyze(new Date());
	}
	
	@Test
	public void generatorMaterialTotal() throws Exception {
		reportGeneratorService.generatorMaterialTotal();
	}
	
	@Test
	public void generatorCfAnalyze() throws Exception {
		reportGeneratorService.generatorCfAnalyze(new Date());
	}
	
	@Test
	public void generatorPmLabelTotal() throws Exception {
		reportGeneratorService.generatorPmLabelTotal();
	}
	
	@Test
	public void generatorWorkRatioGm() throws Exception {
		reportGeneratorService.generatorWorkRatioGm(new Date());
	}
	
	@Test
	public void generatorWorkRatioShop() throws Exception {
		reportGeneratorService.generatorWorkRatioShop(DateUtils.getPreday(new Date()));
	}
	
	@Test
	public void generatorWorkRatioArea() throws Exception {
		reportGeneratorService.generatorWorkRatioArea();
	}
	
	@Test
	public void generatorUnContactTotal() throws Exception {
		reportGeneratorService.generatorUnContactAnalyze();
	}
	
	@Test
	public void generatorPmTypeTotal() throws Exception {
		reportGeneratorService.generatorPmTypeTotal();
	}
	
	@Test
	public void generatorPmTalkTotal() throws Exception {
		reportGeneratorService.generatorPmTalkTotal();
	}
	
	@Test
	public void generatorCfCountAnalyze() throws Exception {
		reportGeneratorService.generatorCfCountAnalyze();
	}
	
	@Test
	public void generatorAreaOrderAnalyze() throws Exception {
		reportGeneratorService.generatorAreaOrderAnalyze();
	}
}
