package com.ye.business.rw.dao;

import java.util.List;

import com.ye.business.rw.domain.RwUser;
import com.ye.business.rw.dto.FindRwUserPage;
import com.ye.business.rw.dto.RwUserDto;

public interface IRwUserDao {
	int deleteByPrimaryKey(String code);

	int insert(RwUser record);

	int insertSelective(RwUser record);

	RwUser selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(RwUser record);

	int updateByPrimaryKey(RwUser record);

	/**
	 * 
	 * @param findRwUserPage
	 * @return
	 */
	List<RwUserDto> findRwUsers(FindRwUserPage findRwUserPage);

	/**
	 * 
	 * @param findRwUserPage
	 * @return
	 */
	List<RwUserDto> findRwUserPage(FindRwUserPage findRwUserPage);

	int findRwUserPageCount(FindRwUserPage findRwUserPage);
	
	/**
	 * 
	 * *方法说明：
	 *
	 * @param loginName
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月31日
	 */
	RwUser selectByLoginName(String loginName);
}