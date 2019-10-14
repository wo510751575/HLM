package com.lj.business.cm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.business.cm.dto.AddMaterialPhoto;
import com.lj.business.cm.dto.AddMaterialVideo;
import com.lj.business.cm.dto.wordsType.FindMaterialPhotoPage;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月24日下午3:41:44
 */
public interface IMaterialPhotoDao {

	/**
	 *@Desc 
	 *@param findMaterialPhotoPage
	 *@return
	 *@return List<FindMaterialPhotoPage>
	 *@author 贾光朝
	 *@createDate 2019年4月24日下午3:44:43
	 */
	List<FindMaterialPhotoPage> findMaterialPhotoPage(FindMaterialPhotoPage findMaterialPhotoPage);

	/**
	 *@Desc 
	 *@param findMaterialPhotoPage
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年4月24日下午3:44:55
	 */
	int findMaterialPhotoCount(FindMaterialPhotoPage findMaterialPhotoPage);

	/**
	 *@Desc 
	 *@param addMaterialPhoto
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月24日下午5:09:39
	 */
	void addMaterialVideo(AddMaterialPhoto addMaterialPhoto);

	/**
	 *@Desc 
	 *@param addMaterialPhoto
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日上午9:57:39
	 */
	void delete(AddMaterialPhoto addMaterialPhoto);

	/**
	 *@Desc 
	 *@param parentId
	 *@return
	 *@return String
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午12:04:15
	 */
	String selectParentIds(String parentId);

	/**
	 *@Desc 
	 *@param parentId
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午6:12:52
	 */
	int getCount(String parentId);

	/**
	 *@Desc 
	 *@param parent
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午6:14:16
	 */
	void updateCount(AddMaterialPhoto parent);
	/**
	 * 获取子级，OMS用
	 * @param parentId
	 * @return
	 */
	List<Map<String,Object>> findChild(@Param("parentId") String parentId);
}
