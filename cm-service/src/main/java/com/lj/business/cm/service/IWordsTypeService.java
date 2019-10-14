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
import com.lj.business.cm.dto.wordsInfo.FindWordsAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoApp;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWeb;
import com.lj.business.cm.dto.wordsType.AddWordsType;
import com.lj.business.cm.dto.wordsType.AddWordsTypeReturn;
import com.lj.business.cm.dto.wordsType.DelWordsType;
import com.lj.business.cm.dto.wordsType.DelWordsTypeReturn;
import com.lj.business.cm.dto.wordsType.FindWordsType;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;
import com.lj.business.cm.dto.wordsType.FindWordsTypePageReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypeReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypeSelectReturn;
import com.lj.business.cm.dto.wordsType.UpdateWordsType;
import com.lj.business.cm.dto.wordsType.UpdateWordsTypeReturn;


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
public interface IWordsTypeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加话术类型信息
	 *
	 * @param addWordsType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public AddWordsTypeReturn addWordsType(AddWordsType addWordsType) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找话术类型信息
	 *
	 * @param findWordsType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public FindWordsTypeReturn findWordsType(FindWordsType findWordsType) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除话术类型信息
	 *
	 * @param delWordsType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public DelWordsTypeReturn delWordsType(DelWordsType delWordsType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改话术类型信息
	 *
	 * @param updateWordsType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 * @param findWordsTypeReturn 
	 *
	 */
	public UpdateWordsTypeReturn updateWordsType(UpdateWordsType updateWordsType, FindWordsTypeReturn findWordsTypeReturn)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找话术类型信息
	 *
	 * @param findWordsTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public Page<FindWordsTypePageReturn> findWordsTypePage(FindWordsTypePage findWordsTypePage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：话术管理-app
	 *
	 * @param findWordsInfoApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	public List<FindWordsAppReturn> findWords(FindWordsInfoApp findWordsInfoApp) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：话术管理-web-更多-查询商户话术类型
	 *
	 * @param findWordsInfoApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	public List<FindWordsTypeSelectReturn> findWordsTypes(FindWordsInfoWeb findWordsInfoWeb) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：话术类型-类型名称重复校验
	 *
	 * @param merchantNo
	 * @param typeName
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	public int hasTypeName(String memberNoGm,String typeName) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：话术类型-排序重复校验
	 *
	 * @param merchantNo
	 * @param seq
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	public int hasSeq(String memberNoGm,Integer seq) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：增加类型下话术数量
	 *
	 * @param 
	 * @param 
	 * @return
	 *
	 * @author 
	 *
	 */
	public int incrementTypeCountByPrimaryKey(String typeCode, Integer increment) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查询个人话术类型-APP
	 *
	 * @param 
	 * @param 
	 * @return
	 *
	 * @author 
	 *
	 */
	public List<FindWordsAppReturn> findPersonWords(FindWordsTypePage findWordsTypePage) throws TsfaServiceException;


}
