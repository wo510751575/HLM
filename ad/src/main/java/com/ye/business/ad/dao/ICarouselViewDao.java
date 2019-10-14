package com.ye.business.ad.dao;

import java.util.List;
import java.util.Map;

import com.ye.business.ad.domain.CarouselView;
import com.ye.business.ad.dto.CarouselDto;
import com.ye.business.ad.dto.CarouselViewDto;
import com.ye.business.ad.dto.FindCarouselViewPage;

public interface ICarouselViewDao {
	int deleteByPrimaryKey(String code);

	int insert(CarouselView record);

	int insertSelective(CarouselView record);

	CarouselView selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(CarouselView record);

	int updateByPrimaryKey(CarouselView record);

	List<CarouselViewDto> findCarouselViews(FindCarouselViewPage findCarouselViewPage);

	List<CarouselViewDto> findCarouselViewPage(FindCarouselViewPage findCarouselViewPage);

	int findCarouselViewPageCount(FindCarouselViewPage findCarouselViewPage);

	/**
	 * 
	 * *方法说明：根据广告code分组统计次数
	 *
	 * @param findCarouselViewPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	List<Map<String, Object>> findCarouselViewPageCountForGroupCarouselCode(FindCarouselViewPage findCarouselViewPage);

	/**
	 * 
	 * *方法说明：根据条件统计点击量
	 *
	 * @param record CarouselDto
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	List<CarouselViewDto> findGroupTotalByExample(CarouselDto record);
}