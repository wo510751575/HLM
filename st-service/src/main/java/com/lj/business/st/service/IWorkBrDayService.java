package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.WorkBrDay.AddWorkBrDay;
import com.lj.business.st.dto.WorkBrDay.FindWorkBrDay;
import com.lj.business.st.dto.WorkBrDay.FindWorkBrDayReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IWorkBrDayService {
	
	/**
	 * 
	 *
	 * 方法说明：添加日工作简报表信息
	 *
	 * @param addWorkBrDay
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addWorkBrDay(AddWorkBrDay addWorkBrDay) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找日工作简报表信息
	 *
	 * @param findWorkBrDay
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindWorkBrDayReturn findWorkBrDay(FindWorkBrDay findWorkBrDay) throws TsfaServiceException;
	
	
	

}
