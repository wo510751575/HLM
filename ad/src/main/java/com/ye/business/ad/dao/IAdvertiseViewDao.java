package com.ye.business.ad.dao;

import java.util.List;
import java.util.Map;

import com.ye.business.ad.domain.AdvertiseView;
import com.ye.business.ad.dto.AdvertiseDto;
import com.ye.business.ad.dto.AdvertiseViewDto;
import com.ye.business.ad.dto.FindAdvertiseViewPage;

public interface IAdvertiseViewDao {
	int deleteByPrimaryKey(String code);

	int insert(AdvertiseView record);

	int insertSelective(AdvertiseView record);

	AdvertiseView selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(AdvertiseView record);

	int updateByPrimaryKey(AdvertiseView record);

	List<AdvertiseViewDto> findAdvertiseViews(FindAdvertiseViewPage findAdvertiseViewPage);

	List<AdvertiseViewDto> findAdvertiseViewPage(FindAdvertiseViewPage findAdvertiseViewPage);

	int findAdvertiseViewPageCount(FindAdvertiseViewPage findAdvertiseViewPage);

	/**
	 * 
	 * *方法说明：根据广告code分组统计次数
	 *
	 * @param findAdvertiseViewPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	List<Map<String, Object>> findAdvertiseViewPageCountForGroupAdvertiseCode(FindAdvertiseViewPage findAdvertiseViewPage);

	int selectAdvertiseViewCount(AdvertiseDto advertiseDto);

	int getCountView(AdvertiseViewDto param);

	/**
	 * 
	 * *方法说明：根据条件统计点击量
	 *
	 * @param record AdvertiseDto
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	List<AdvertiseViewDto> findGroupTotalByExample(AdvertiseDto record);
}