package com.ye.business.ad.service.impl.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.cc.clientintf.IParamJob;
import com.ye.business.ad.dto.AdvertiseTaskDto;
import com.ye.business.ad.service.IAdvertiseService;
import com.ye.business.ad.service.ICarouselService;

/**
 * 
 * *类说明：广告上下架，临时任务
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年7月18日
 */
@Service
public class AdvertiseJobTask implements IParamJob {

	private static final Logger logger = LoggerFactory.getLogger(AdvertiseJobTask.class);

	@Autowired
	private IAdvertiseService advertiseService;
	@Autowired
	private ICarouselService carouselService;

	@Override
	public void runParamJob(String param) {

		this.doJob(param);
	}

	/**
	 * 
	 * *方法说明：临时任务，上下架
	 *
	 * @param param
	 * @author sjiying
	 * @CreateDate 2019年7月18日
	 */
	public synchronized void doJob(String param) {

		logger.info("广告上下架任务调度开始：String param = {}", param);

		try {
			AdvertiseTaskDto task = (AdvertiseTaskDto) JsonUtils.objectFromJson(param, AdvertiseTaskDto.class);
			if (AdvertiseTaskDto.TYPE_ADVERTISE.equals(task.getType())) {
				// 广告
				advertiseService.updateForTask(task);
			} else if (AdvertiseTaskDto.TYPE_CAROUSEL.equals(task.getType())) {
				// 轮播广告
				carouselService.updateForTask(task);
			}
		} catch (Exception e) {
			logger.error("广告上下架失败", e);
		}

		logger.info("广告上下架任务调度结束：String param = {}", param);
	}

}
