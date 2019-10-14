package com.ye.business.ad.service.impl.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.cc.clientintf.IJob;
import com.ye.business.ad.service.IAdvertiseService;
import com.ye.business.ad.service.ICarouselService;

/**
 * 
 * *类说明：广告定时下架
 *
 * </p>
 * *详细描述：每隔1小时验证广告下架时间，广告豆是否充足，
 * 
 * @author sjiying
 * @CeateDate 2019年7月11日
 */
@Service
public class AdvertiseJobServiceImpl implements IJob {

	private static final Logger logger = LoggerFactory.getLogger(AdvertiseJobServiceImpl.class);

	@Autowired
	private IAdvertiseService advertiseService;

	@Autowired
	private ICarouselService carouselService;

	@Override
	public void runJob() {
		
		String batchNum = System.currentTimeMillis() + "";
		
		logger.info("批量更新广告上下架信息 定时任务开始,批次{}。", batchNum);
		advertiseService.batchUpdateAdvertiseForUpOrDown(batchNum);
		logger.info("批量更新广告上下架信息 定时任务结束,批次{}。", batchNum);

		logger.info("批量更新轮播广告上下架信息 定时任务开始,批次{}。", batchNum);
		carouselService.batchUpdateCarouselForUpOrDown(batchNum);
		logger.info("批量更新轮播广告上下架信息 定时任务结束,批次{}。", batchNum);
	}

}
