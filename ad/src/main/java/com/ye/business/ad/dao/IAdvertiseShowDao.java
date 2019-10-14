package com.ye.business.ad.dao;

import java.util.List;
import java.util.Map;

import com.ye.business.ad.domain.AdvertiseShow;
import com.ye.business.ad.dto.AdvertiseDto;
import com.ye.business.ad.dto.AdvertiseShowDto;
import com.ye.business.ad.dto.FindAdvertiseShowPage;

public interface IAdvertiseShowDao {
	int deleteByPrimaryKey(String code);

	int insert(AdvertiseShow record);

	int insertSelective(AdvertiseShow record);

	AdvertiseShow selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(AdvertiseShow record);

	int updateByPrimaryKey(AdvertiseShow record);

	List<AdvertiseShowDto> findAdvertiseShows(FindAdvertiseShowPage findAdvertiseShowPage);

	List<AdvertiseShowDto> findAdvertiseShowPage(FindAdvertiseShowPage findAdvertiseShowPage);

	int findAdvertiseShowPageCount(FindAdvertiseShowPage findAdvertiseShowPage);

	/**
	 * 
	 * *方法说明：根据广告code分组统计次数
	 *
	 * @param findAdvertiseShowPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	List<Map<String, Object>> findAdvertiseShowPageCountForGroupAdvertiseCode(FindAdvertiseShowPage findAdvertiseShowPage);

	/**
	 * 
	 * *方法说明：根据条件统计点击量
	 *
	 * @param record AdvertiseDto
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	List<AdvertiseShowDto> findGroupTotalByExample(AdvertiseDto record);

	/**
	 * 
	 * *方法说明：广告显示记录统计
	 *
	 * @param findAdvertiseShowPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	List<AdvertiseShowDto> findAdvertiseShowForGroupList(FindAdvertiseShowPage findAdvertiseShowPage);
	/**
	 * 
	 * *方法说明：广告显示记录统计
	 *
	 * @param findAdvertiseShowPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	List<AdvertiseShowDto> findAdvertiseShowInfoForGroupList(FindAdvertiseShowPage findAdvertiseShowPage);
	
	/**
	 * 
	 * *方法说明：
	 *
	 * @param record
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	List<String> findCodeListByExample(AdvertiseShowDto record);
}