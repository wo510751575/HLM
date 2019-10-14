package com.lj.business.cm.service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.FindMaterialTextPage;
import com.lj.business.cm.dto.FindMaterialTextPageReturn;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月25日下午3:34:24
 */
public interface IMaterialTextService {

	/**
	 *@Desc 
	 *@param findMaterialTextPage
	 *@return
	 *@return Page<FindMaterialTextPageReturn>
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午3:43:29
	 */
	Page<FindMaterialTextPageReturn> findWordsInfoPage(FindMaterialTextPage findMaterialTextPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param findMaterialTextPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午5:17:52
	 */
	void addText(FindMaterialTextPage findMaterialTextPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param findMaterialTextPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午7:18:02
	 */
	void updateText(FindMaterialTextPage findMaterialTextPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param findMaterialTextPage
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年4月25日下午7:19:17
	 */
	void deleteText(FindMaterialTextPage findMaterialTextPage) throws TsfaServiceException;

}
