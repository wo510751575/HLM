package com.lj.business.st.service.impl.job;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.service.IReportGeneratorService;
import com.lj.cc.clientintf.IJob;

@Service
public class ReportGeneratorJobServiceImpl implements IJob{

	private static final Logger logger = LoggerFactory.getLogger(ReportGeneratorJobServiceImpl.class);
	@Resource
	private IReportGeneratorService reportGeneratorService;

	@Override
	public void runJob() {
		this.triggerReportGeneratorJob();
	}


	public synchronized void triggerReportGeneratorJob()throws TsfaServiceException{
		logger.debug("【报表调度】triggerReportGeneratorJob() - start"); //$NON-NLS-1$
		try {
			
			Date queryDate = DateUtils.getPreday(org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));//系统报表统计前一天的

			try {
				logger.debug("统计跟踪分析");
				reportGeneratorService.generatorCfCountAnalyze(); 
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("区域工作统计");
				reportGeneratorService.generatorWorkRatioArea(); //1 
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			try {
				logger.debug("生成导购工作统计");
				reportGeneratorService.generatorWorkRatioGm(queryDate);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("生成门店工作统计");
				reportGeneratorService.generatorWorkRatioShop(DateUtils.getPreday(new Date()));
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("生成到店体验统计");
				reportGeneratorService.generatorClientExpTotal(queryDate);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("生成员工画像");
				reportGeneratorService.generatorEmployeeAnalyze(); //2 T
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("生成客户标签统计");
				reportGeneratorService.generatorPmLabelTotal();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("素材中心统计");
				reportGeneratorService.generatorMaterialTotal();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}


			try {
				logger.debug("区域订单统计");
				reportGeneratorService.generatorAreaOrderAnalyze(); //2 T
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

		
/*            不需要统计这个报表    
			try {
				logger.debug("客户咨询统计");
				reportGeneratorService.generatorPmTalkTotal(); //2 T
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}*/

			try {
				logger.debug("社交分析统计");
				reportGeneratorService.generatorSocialAnalyze(queryDate); //2 T
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("客户分类");
				reportGeneratorService. generatorPmTypeTotal(); //2 T
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("增加或更新客户画像统计");
				reportGeneratorService.addOrUpdateClientAnalyzeStatistics();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("跟进分析摘要");
				reportGeneratorService.generatorCfAnalyze(queryDate); //2  T
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("运营分析摘要统计");
				reportGeneratorService.generatorOperationAnalysisDayBrief(queryDate); //2T
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

			try {
				logger.debug("未联系客户分析统计");
				reportGeneratorService.generatorUnContactAnalyze();//T
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

		} catch (Exception e) {
			logger.error("triggerDayChange()", e); //$NON-NLS-1$
			throw new TsfaServiceException(ErrorCode.REMIND_JOB_ERROR,"客户提醒JOB错误",e);
		}

		logger.debug("【报表调度】triggerReportGeneratorJob() - end"); //$NON-NLS-1$
	}

}
