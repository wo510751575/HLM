/**
 * 
 */
package com.ye.business.hx.service.impl.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.cc.clientintf.IJob;
import com.ye.business.hx.service.IGuidScheduleLogService;

/**
 * 
 * 类说明：员工历史排班定时任务。
 * 
 * 
 * <p>
 * 每周一凌晨将当周的排班转移到历史记录；将固定排班转移到当周；
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月15日
 */
@Service
public class GuidScheduleJobServiceImpl implements IJob {
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GuidScheduleJobServiceImpl.class);
	
	@Autowired
	private IGuidScheduleLogService guidScheduleLogService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lj.cc.clientintf.IJob#runJob()
	 */
	@Override
	public void runJob() {
		String batchNum=(new Date().getTime())+"";
		logger.info("批量更新历史排班信息 定时任务开始,批次{}。",batchNum);
//		 * 1.将当周排班转移到历史排班
//		 * 2.完成1后，将固定排班转移到当周排班
		guidScheduleLogService.batchAddGuidScheduleLog(batchNum);
		logger.info("批量更新历史排班信息 定时任务结束,批次{}。",batchNum);

	}

}
