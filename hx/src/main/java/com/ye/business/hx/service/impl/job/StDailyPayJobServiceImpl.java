package com.ye.business.hx.service.impl.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.cc.clientintf.IJob;
import com.ye.business.hx.service.IStDailyPayService;

/**
 * 
 * 类说明：门诊每日支付方式的收支统计。
 * 
 * 
 * <p>
 * 隔天统计门诊每种支付方式前一天的收支金额。
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月15日
 */
@Service
public class StDailyPayJobServiceImpl implements IJob {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(StDailyPayJobServiceImpl.class);
	
	@Autowired
	private IStDailyPayService stDailyPayService;
	
	@Override
	public void runJob() {
		String batchNum=(new Date().getTime())+"";
		logger.info("账单支付方式统计,批次{}。",batchNum);
		stDailyPayService.batchAddStDailyPay(batchNum);
		logger.info("账单支付方式统计 定时任务结束,批次{}。",batchNum);
	}

}
