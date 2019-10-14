package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.CfAnalyze.AddCfAnalyze;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyze;
import com.lj.business.st.dto.CfAnalyze.FindCfAnalyzeReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface ICfAnalyzeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加跟进分析摘要表信息
	 *
	 * @param addCfAnalyze
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addCfAnalyze(AddCfAnalyze addCfAnalyze) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找跟进分析摘要表信息_APP用
	 *
	 * @param findCfAnalyze
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public List<FindCfAnalyzeReturn> findCfAnalyze(FindCfAnalyze findCfAnalyze) throws TsfaServiceException;
	
}
