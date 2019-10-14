package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.RwUserBeans;
import com.ye.business.ad.dto.FindRwUserBeansPage;
import com.ye.business.ad.dto.RwUserBeansDto;

public interface IRwUserBeansDao {
	int deleteByPrimaryKey(String code);

	int insert(RwUserBeans record);

	int insertSelective(RwUserBeans record);

	RwUserBeans selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(RwUserBeans record);

	int updateByPrimaryKey(RwUserBeans record);

	List<RwUserBeansDto> findRwUserBeanss(FindRwUserBeansPage findRwUserBeansPage);

	List<RwUserBeansDto> findRwUserBeansPage(FindRwUserBeansPage findRwUserBeansPage);

	int findRwUserBeansPageCount(FindRwUserBeansPage findRwUserBeansPage);
	
	RwUserBeans selectByMemberNo(String memberNo);
	
	/**
	 * 
	 * *方法说明：变更金额豆子
	 *
	 * @param record
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	int updateIncreaseAmountByPrimaryKey(RwUserBeansDto record);
}