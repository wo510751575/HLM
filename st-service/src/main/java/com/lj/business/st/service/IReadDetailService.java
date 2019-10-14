package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.readDetail.AddReadDetail;
import com.lj.business.st.dto.readDetail.AddReadDetailReturn;


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
public interface IReadDetailService {
	
	/**
	 * 
	 *
	 * 方法说明：添加阅读明细表信息
	 *
	 * @param addReadDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public AddReadDetailReturn addReadDetail(AddReadDetail addReadDetail) throws TsfaServiceException;
	

}
