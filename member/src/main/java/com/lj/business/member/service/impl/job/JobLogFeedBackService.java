package com.lj.business.member.service.impl.job;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.cc.domain.JobExecuteLog;
import com.lj.cc.service.IJobExecuteFeedbackService;

/**
 * 
 * 
 * 类说明：定时任务日志回写服务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月10日
 */
@Service
public class JobLogFeedBackService {
	
	private static Logger log = LoggerFactory.getLogger(JobLogFeedBackService.class);

	@Resource
	private IJobExecuteFeedbackService jobExecuteFeedbackService;
	
	/**
	 * 
	 *
	 * 方法说明：往调度中心回写定时任务执行日志
	 *
	 * @param jobName
	 * @param remark
	 * @param flag
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月10日
	 *
	 */
	public void pushLog(String jobName, String remark, String flag) {
		try {
			jobExecuteFeedbackService.pushJobExecuteInfo(new JobExecuteLog(jobName, new Date(), remark, flag));
		} catch(Exception e) {
			log.warn("[" + jobName + "]回写任务日志失败", e);
		}
	}
}
