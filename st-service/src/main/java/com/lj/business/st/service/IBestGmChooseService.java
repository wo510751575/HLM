package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.FindBgcIndex;
import com.lj.business.st.dto.FindBgcIndexReturn;
import com.lj.business.st.dto.bestGmChoose.AddBestGmChoose;
import com.lj.business.st.dto.bestGmChoose.FindBestGmChoose;
import com.lj.business.st.dto.bestGmChoose.FindBestGmChooseReturn;


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
public interface IBestGmChooseService {
	

	/**
	 * 
	 *
	 * 方法说明：优秀导购展示项选择_H5员工英雄榜
	 *
	 * @param findBgcIndex
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年8月1日
	 *
	 */
	public List<FindBgcIndexReturn> findBgcIndex(FindBgcIndex findBgcIndex) throws TsfaServiceException;

	
	/**
	 * 
	 *
	 * 方法说明：添加优秀导购选择表信息
	 *
	 * @param addBestGmChoose
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addBestGmChoose(AddBestGmChoose addBestGmChoose) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找优秀导购选择表信息
	 *
	 * @param findBestGmChoose
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindBestGmChooseReturn findBestGmChoose(FindBestGmChoose findBestGmChoose) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明根据商户编号删除
	 *
	 * @param str
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月2日
	 *
	 */
	public  int deleteByPrimaryKey(String str);
	

}
