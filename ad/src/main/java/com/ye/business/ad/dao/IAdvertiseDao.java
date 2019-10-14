package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.Advertise;
import com.ye.business.ad.dto.AdvertiseDto;
import com.ye.business.ad.dto.FindAdvertisePage;

public interface IAdvertiseDao {
	int deleteByPrimaryKey(String code);

	int insert(Advertise record);

	int insertSelective(Advertise record);

	Advertise selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(Advertise record);

	int updateByPrimaryKey(Advertise record);

	List<AdvertiseDto> findAdvertises(FindAdvertisePage findAdvertisePage);

	List<AdvertiseDto> findAdvertisePage(FindAdvertisePage findAdvertisePage);

	int findAdvertisePageCount(FindAdvertisePage findAdvertisePage);

	List<AdvertiseDto> findOthersRandom(FindAdvertisePage findAdvertisePage);
	
	/**
	 * 
	 * *方法说明：主键批量修改记录
	 *
	 * @param record
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	int updateByPrimaryKeyBatchSelective(AdvertiseDto record);
	
	/**
	 * 
	 * *方法说明：扣除金额豆子
	 *
	 * @param record
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	int updateDeductionAmountByPrimaryKey(AdvertiseDto record);
}