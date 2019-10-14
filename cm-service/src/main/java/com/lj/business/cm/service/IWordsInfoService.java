package com.lj.business.cm.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.wordsInfo.AddWordsInfo;
import com.lj.business.cm.dto.wordsInfo.AddWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.DelWordsInfo;
import com.lj.business.cm.dto.wordsInfo.DelWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfo;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoApp;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPage;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPageReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWeb;
import com.lj.business.cm.dto.wordsInfo.UpdateWordsInfo;
import com.lj.business.cm.dto.wordsInfo.UpdateWordsInfoReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
public interface IWordsInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加话术信息信息
	 *
	 * @param addWordsInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public AddWordsInfoReturn addWordsInfo(AddWordsInfo addWordsInfo) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找话术信息信息
	 *
	 * @param findWordsInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public FindWordsInfoReturn findWordsInfo(FindWordsInfo findWordsInfo) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除话术信息信息
	 *
	 * @param delWordsInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public DelWordsInfoReturn delWordsInfo(DelWordsInfo delWordsInfo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改话术信息信息
	 *
	 * @param updateWordsInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public UpdateWordsInfoReturn updateWordsInfo(UpdateWordsInfo updateWordsInfo)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找话术信息信息
	 *
	 * @param findWordsInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public Page<FindWordsInfoPageReturn> findWordsInfoPage(FindWordsInfoPage findWordsInfoPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：话术选择-app
	 *
	 * @param findWordsInfoApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	public List<FindWordsInfoAppReturn> wordsSelect(FindWordsInfoApp findWordsInfoApp) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：话术搜索-app
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	public List<FindWordsInfoAppReturn> wordsSearch(FindWordsInfoApp findWordsInfoApp) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：话术-更多-web
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoWeb
	 * @return
	 */
	public List<FindWordsInfoReturn> moreWords(FindWordsInfoWeb findWordsInfoWeb) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查询默认话术数量
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-12
	 * 
	 * @param merchantNo
	 * @return
	 */
	public Integer findDefaultCount(String memberNoGm) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据类型CODE更新类型名称
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-13
	 * 
	 * @param updateWordsInfo
	 * @return
	 */
	public void updateTypeName(UpdateWordsInfo updateWordsInfo) throws TsfaServiceException ;
	
	/**
	 * 
	 *
	 * 方法说明：同步删除属于该话术类型的所有话术信息
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-13
	 * 
	 * @param typeCode
	 * @return
	 */
	public void deleteWordsInfoByTypeCode(String typeCode) throws TsfaServiceException ;

	/**
	 * 
	 *
	 * 方法说明：检验话术是否存在
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-17
	 * 
	 * @param code
	 * @return
	 */
	public Integer checkWords(String code) throws TsfaServiceException ;

	/**
	 * 
	 *
	 * 方法说明：查询个人话术信息
	 *
	 * @author 
	 *   
	 * CreateDate: 
	 * 
	 * @param 
	 * @return
	 */
	public List<FindWordsInfoAppReturn> wordsPersonSearch(FindWordsInfoPage findWordsInfoPage) throws TsfaServiceException;

	/**
	 * 方法说明：查询个人默认话术
	 * @param findWordsInfoPage
	 * @return
	 */
	public List<FindWordsInfoAppReturn> wordsPersonSelect(FindWordsInfoPage findWordsInfoPage) throws TsfaServiceException;

	/**
	 * 连表查询默认话术
	 * @param findWordsInfoPage
	 * @return 
	 */
	public List<FindWordsInfoAppReturn> findDefaultWords(FindWordsInfoPage findWordsInfoPage) throws TsfaServiceException;

	/**
	 * 连表查询默认话术不限制条数-H5页面专用
	 * @param findWordsInfoPage
	 * @return 
	 */
	public List<FindWordsInfoAppReturn> findDefaultWordsH5(FindWordsInfoPage findWordsInfoPage) throws TsfaServiceException;

}
