package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.Carousel;
import com.ye.business.ad.dto.CarouselDto;
import com.ye.business.ad.dto.FindCarouselPage;

public interface ICarouselDao {
	int deleteByPrimaryKey(String code);

	int insert(Carousel record);

	int insertSelective(Carousel record);

	Carousel selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(Carousel record);

	int updateByPrimaryKey(Carousel record);

	List<CarouselDto> findCarousels(FindCarouselPage findCarouselPage);

	List<CarouselDto> findCarouselPage(FindCarouselPage findCarouselPage);

	int findCarouselPageCount(FindCarouselPage findCarouselPage);

	/**
	 * 
	 * *方法说明：主键批量修改记录
	 *
	 * @param record
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	int updateByPrimaryKeyBatchSelective(CarouselDto record);
}