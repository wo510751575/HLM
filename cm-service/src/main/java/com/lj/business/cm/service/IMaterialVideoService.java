package com.lj.business.cm.service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.AddMaterialVideo;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPage;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPageReturn;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月22日下午2:42:13
 */
public interface IMaterialVideoService {

	/**
	 *@Desc 
	 *@param page
	 *@return
	 *@return Page<FindMaterialVideoPageReturn>
	 *@author 贾光朝
	 *@createDate 2019年4月22日下午2:46:36
	 */
	Page<FindMaterialVideoPageReturn> findMaterialVideoPage(FindMaterialVideoPage page) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param addMaterialVideo
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月23日上午9:55:09
	 */
	void addMaterialVideo(AddMaterialVideo addMaterialVideo) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param materialVideo
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日上午9:46:09
	 */
	void delete(AddMaterialVideo materialVideo) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param parentId
	 *@return
	 *@return String
	 *@author 贾光朝
	 *@createDate 2019年4月25日上午11:04:47
	 */
	String selectParentIds(String parentId) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param parentId
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午5:36:34
	 */
	int getCount(String parentId) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param parent
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午5:50:03
	 */
	void updateCount(AddMaterialVideo parent) throws TsfaServiceException;

	
}
