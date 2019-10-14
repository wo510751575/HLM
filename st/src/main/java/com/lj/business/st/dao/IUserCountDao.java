package com.lj.business.st.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.business.st.dto.FindUserCountPage;
import com.lj.business.st.dto.FindUserCountPageReturn;
import com.lj.business.st.dto.UserCount;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年5月30日下午5:31:55
 */
public interface IUserCountDao {

	/**
	 *@Desc 
	 *@param findUserCountPage
	 *@return
	 *@return List<FindUserCountPageReturn>
	 *@author 贾光朝
	 *@createDate 2019年5月31日下午3:34:45
	 */
	List<FindUserCountPageReturn> findUserCountList(FindUserCountPage findUserCountPage);

	/**
	 *@Desc 
	 *@param findUserCountPage
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年5月31日下午3:35:29
	 */
	int findUserCount(FindUserCountPage findUserCountPage);

	/**
	 *@Desc 
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年5月31日下午5:36:20
	 */
	int findCount();

	/**
	 *@Desc 
	 *@param userCount
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月31日下午6:08:32
	 */
	void addUserCount(UserCount userCount);

	/**
	 *@Desc 
	 *@param map
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月1日下午3:06:45
	 */
	int findTotalMember(@Param("map") Map map);

	/**
	 *@Desc 
	 *@param map
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月5日下午6:23:15
	 */
	int findTotalMemberPhone(@Param("map") Map map);

}
