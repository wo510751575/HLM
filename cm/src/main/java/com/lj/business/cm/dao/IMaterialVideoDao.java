package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.dto.AddMaterialVideo;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPage;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPageReturn;

/**
 * 类说明:视频库Dao
 *@author 贾光朝
 *@createDate 2019年4月22日下午3:07:25
 */
public interface IMaterialVideoDao {

	/**
	 *@Desc 
	 *@param page
	 *@return
	 *@return Page<FindMaterialVideoPageReturn>
	 *@author 贾光朝
	 *@createDate 2019年4月22日下午3:10:43
	 */
	List<FindMaterialVideoPageReturn> findMaterialVideoPage(FindMaterialVideoPage page);

	/**
	 *@Desc 
	 *@param addMaterialVideo
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月23日上午10:07:22
	 */
	void addMaterialVideo(AddMaterialVideo addMaterialVideo);

	/**
	 *@Desc 
	 *@param page
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年4月23日下午12:15:55
	 */
	int findMaterialVideoCount(FindMaterialVideoPage page);

	/**
	 *@Desc 
	 *@param materialVideo
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日上午9:49:17
	 */
	void delete(AddMaterialVideo materialVideo);

	/**
	 *@Desc 
	 *@param parentId
	 *@return
	 *@return String
	 *@author 贾光朝
	 *@createDate 2019年4月25日上午11:06:12
	 */
	String selectParentIds(String parentId);

	/**
	 *@Desc 
	 *@param parentId
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午5:37:44
	 */
	int getCount(String parentId);

	/**
	 *@Desc 
	 *@param parent
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午5:51:12
	 */
	void updateCount(AddMaterialVideo parent);

}
