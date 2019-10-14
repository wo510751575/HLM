package com.lj.business.cm.service;

import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.AddMaterialPhoto;
import com.lj.business.cm.dto.wordsType.FindMaterialPhotoPage;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月24日下午3:30:15
 */
public interface IMaterialPhotoService {

	/**
	 *@Desc 
	 *@param findMaterialPhotoPage
	 *@return
	 *@return Page<FindMaterialPhotoPage>
	 *@author 贾光朝
	 *@createDate 2019年4月24日下午3:39:20
	 */
	Page<FindMaterialPhotoPage> findMaterialPhotoPage(FindMaterialPhotoPage findMaterialPhotoPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param addMaterialPhoto
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月24日下午5:07:15
	 */
	void addMaterialPhoto(AddMaterialPhoto addMaterialPhoto) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param addMaterialPhoto
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日上午9:56:10
	 */
	void delete(AddMaterialPhoto addMaterialPhoto) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param parentId
	 *@return
	 *@return String
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午12:02:54
	 */
	String selectParentIds(String parentId) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param parentId
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午6:11:36
	 */
	int getCount(String parentId) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param parent
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日下午6:11:41
	 */
	void updateCount(AddMaterialPhoto parent) throws TsfaServiceException;

	/**
	 * 获取子级，OMS用
	 * @param parentId
	 * @return
	 */
	List<Map<String,Object>> findChild(String parentId)throws TsfaServiceException;
	
}
