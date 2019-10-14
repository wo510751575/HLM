package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.dto.FindMaterialLinkPage;
import com.lj.business.cm.dto.FindMaterialLinkPageReturn;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月26日上午10:12:35
 */
public interface IMaterialLinkDao {

	/**
	 *@Desc 
	 *@param findMaterialLinkPage
	 *@return
	 *@return List<FindMaterialLinkPageReturn>
	 *@author 贾光朝
	 *@createDate 2019年4月26日上午10:16:24
	 */
	List<FindMaterialLinkPageReturn> findMaterialLinkPage(FindMaterialLinkPage findMaterialLinkPage);

	/**
	 *@Desc 
	 *@param findMaterialLinkPage
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年4月26日上午10:16:40
	 */
	int findMaterialLinkPageCount(FindMaterialLinkPage findMaterialLinkPage);

	/**
	 *@Desc 
	 *@param findMaterialLinkPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日上午11:59:25
	 */
	void add(FindMaterialLinkPage findMaterialLinkPage);

	/**
	 *@Desc 
	 *@param findMaterialLinkPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午12:00:07
	 */
	void update(FindMaterialLinkPage findMaterialLinkPage);

	/**
	 *@Desc 
	 *@param findMaterialLinkPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午12:01:13
	 */
	void delete(FindMaterialLinkPage findMaterialLinkPage);

}
