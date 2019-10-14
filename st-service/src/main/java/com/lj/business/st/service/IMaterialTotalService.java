package com.lj.business.st.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.dto.MaterialTotal.AddMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotalReturn;


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
public interface IMaterialTotalService {
	
	/**
	 * 
	 *
	 * 方法说明：添加素材中心统计表信息
	 *
	 * @param addMaterialTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addMaterialTotal(AddMaterialTotal addMaterialTotal) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找素材中心统计表信息
	 *
	 * @param findMaterialTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public List<FindMaterialTotalReturn> findMaterialTotal(FindMaterialTotal findMaterialTotal) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：查找素材中心统计表信息
	 *
	 * @param findMaterialTotal
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public List<FindMaterialTotalReturn> findMaterialTotalApp(FindMaterialTotal findMaterialTotal) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：查找素材中心统计信息(查找最大数量)
	 *
	 * @param findMaterialTotal
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月3日
	 *
	 */
	public List<FindMaterialTotalReturn> findMaterialTotalCount(FindMaterialTotal findMaterialTotal)throws TsfaServiceException;
	
}
