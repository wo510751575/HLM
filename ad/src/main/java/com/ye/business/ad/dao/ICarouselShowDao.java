package com.ye.business.ad.dao;

import java.util.List;
import java.util.Map;

import com.ye.business.ad.domain.CarouselShow;
import com.ye.business.ad.dto.CarouselDto;
import com.ye.business.ad.dto.CarouselShowDto;
import com.ye.business.ad.dto.FindCarouselShowPage;

public interface ICarouselShowDao {
	int deleteByPrimaryKey(String code);

	int insert(CarouselShow record);

	int insertSelective(CarouselShow record);

	CarouselShow selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(CarouselShow record);

	int updateByPrimaryKey(CarouselShow record);

	List<CarouselShowDto> findCarouselShows(FindCarouselShowPage findCarouselShowPage);

	List<CarouselShowDto> findCarouselShowPage(FindCarouselShowPage findCarouselShowPage);

	int findCarouselShowPageCount(FindCarouselShowPage findCarouselShowPage);

	/**
	 * 
	 * *方法说明：根据广告code分组统计次数
	 *
	 * @param findCarouselShowPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	List<Map<String, Object>> findCarouselShowPageCountForGroupCarouselCode(FindCarouselShowPage findCarouselShowPage);

	/**
	 * 
	 * *方法说明：根据条件统计点击量
	 *
	 * @param record CarouselDto
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	List<CarouselShowDto> findGroupTotalByExample(CarouselDto record);
}