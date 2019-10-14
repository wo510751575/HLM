package com.lj.business.weixin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Set;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.imSensitiveWords.AddImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.AddImSensitiveWordsReturn;
import com.lj.business.weixin.dto.imSensitiveWords.DelImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.DelImSensitiveWordsReturn;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPage;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPageReturn;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsReturn;
import com.lj.business.weixin.dto.imSensitiveWords.UpdateImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.UpdateImSensitiveWordsReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 罗书明
 * 
 * 
 * CreateDate: 2017-11-02
 */
public interface IImSensitiveWordsService {
	
	/**
	 * 
	 *
	 * 方法说明：添加IM敏感词表信息
	 *
	 * @param addImSensitiveWords
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明  CreateDate: 2017年11月2日
	 *
	 */
	public AddImSensitiveWordsReturn addImSensitiveWords(AddImSensitiveWords addImSensitiveWords) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找IM敏感词表信息
	 *
	 * @param findImSensitiveWords
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author  罗书明  CreateDate: 2017年11月2日
	 *
	 */
	public FindImSensitiveWordsReturn findImSensitiveWords(FindImSensitiveWords findImSensitiveWords) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除IM敏感词表信息
	 *
	 * @param delImSensitiveWords
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author  罗书明  CreateDate: 2017年11月2日
	 *
	 */
	public DelImSensitiveWordsReturn delImSensitiveWords(DelImSensitiveWords delImSensitiveWords) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改IM敏感词表信息
	 *
	 * @param updateImSensitiveWords
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author  罗书明  CreateDate: 2017年11月2日
	 *
	 */
	public UpdateImSensitiveWordsReturn updateImSensitiveWords(UpdateImSensitiveWords updateImSensitiveWords)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找IM敏感词表信息
	 *
	 * @param findImSensitiveWordsPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author  罗书明  CreateDate: 2017年11月2日
	 *
	 */
	public Page<FindImSensitiveWordsPageReturn> findImSensitiveWordsPage(FindImSensitiveWordsPage findImSensitiveWordsPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询所有有效的敏感词
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月3日
	 *
	 */
	public Set<String> findAllSensitiveWords();
	
	/**
	 * 
	 *
	 * 方法说明：判断文字是否包含敏感字符
	 *
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月12日
	 *
	 */
	public boolean contains(String txt);
}
