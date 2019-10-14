package com.lj.business.st.service;

import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.FindUserCountPage;
import com.lj.business.st.dto.FindUserCountPageReturn;
import com.lj.business.st.dto.UserCount;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年5月30日下午5:30:59
 */
public interface IUserCountService {

	/**
	 *@Desc 
	 *@param findUserCountPage
	 *@return
	 *@return Page<FindUserCountPageReturn>
	 *@author 贾光朝
	 *@createDate 2019年5月31日下午3:18:09
	 */
	Page<FindUserCountPageReturn> findUserCountPage(FindUserCountPage findUserCountPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年5月31日下午5:34:59
	 */
	int findCount()throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param userCount
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月31日下午6:07:45
	 */
	void addUserCount(UserCount userCount)throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param map
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月1日下午3:04:52
	 */
	int findTotalMember(Map map)throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param map
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月5日下午6:21:53
	 */
	int findTotalMemberPhone(Map map);

}
