package com.lj.business.cm.service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.FindMaterialLinkPage;
import com.lj.business.cm.dto.FindMaterialLinkPageReturn;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月26日上午10:07:38
 */
public interface IMaterialLinkService {

	/**
	 *@Desc 
	 *@param findMaterialLinkPage
	 *@return
	 *@return Page<FindMaterialLinkPageReturn>
	 *@author 贾光朝
	 *@createDate 2019年4月26日上午10:09:49
	 */
	Page<FindMaterialLinkPageReturn> findLinkInfoPage(FindMaterialLinkPage findMaterialLinkPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param findMaterialLinkPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日上午11:45:39
	 */
	void add(FindMaterialLinkPage findMaterialLinkPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param findMaterialLinkPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日上午11:45:45
	 */
	void update(FindMaterialLinkPage findMaterialLinkPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param findMaterialLinkPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月26日上午11:46:41
	 */
	void delete(FindMaterialLinkPage findMaterialLinkPage) throws TsfaServiceException;

}
