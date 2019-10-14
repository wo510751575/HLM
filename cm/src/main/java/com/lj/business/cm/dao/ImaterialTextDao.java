package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.dto.FindMaterialTextPage;
import com.lj.business.cm.dto.FindMaterialTextPageReturn;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月25日下午3:58:17
 */
public interface ImaterialTextDao {

	/**
	 *@Desc 
	 *@param findMaterialTextPage
	 *@return
	 *@return List<FindMaterialTextPageReturn>
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午3:59:23
	 */
	List<FindMaterialTextPageReturn> findMaterialTextPage(FindMaterialTextPage findMaterialTextPage);

	/**
	 *@Desc 
	 *@param findMaterialTextPage
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午3:59:33
	 */
	int findMaterialTextPageCount(FindMaterialTextPage findMaterialTextPage);

	/**
	 *@Desc 
	 *@param findMaterialTextPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午5:20:27
	 */
	void addText(FindMaterialTextPage findMaterialTextPage);

	/**
	 *@Desc 
	 *@param findMaterialTextPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午7:21:04
	 */
	void updateText(FindMaterialTextPage findMaterialTextPage);

	/**
	 *@Desc 
	 *@param findMaterialTextPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午7:21:58
	 */
	void deleteText(FindMaterialTextPage findMaterialTextPage);

}
