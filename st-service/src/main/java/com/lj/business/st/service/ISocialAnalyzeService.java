package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.SocialAnalyze.AddSocialAnalyze;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyze;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeReturn;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeTotal;


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
public interface ISocialAnalyzeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加社交分析表信息
	 *
	 * @param addSocialAnalyze
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addSocialAnalyze(AddSocialAnalyze addSocialAnalyze) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找社交分析表信息
	 *
	 * @param findSocialAnalyzeTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public List<FindSocialAnalyzeReturn> findSocialAnalyze(FindSocialAnalyzeTotal findSocialAnalyzeTotal) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：查找社交分析表信息
	 *
	 * @param findSocialAnalyzeTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public List<FindSocialAnalyzeReturn> findSocialAnalyzeApp(FindSocialAnalyzeTotal findSocialAnalyzeTotal) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：查询最大数据量的就一条数据
	 *
	 * @param findSocialAnalyze
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月3日
	 *
	 */
	public  FindSocialAnalyzeReturn findSocialAnalyzeMax(FindSocialAnalyze findSocialAnalyze);
	
}
