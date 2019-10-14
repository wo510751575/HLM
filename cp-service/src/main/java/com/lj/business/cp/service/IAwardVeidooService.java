package com.lj.business.cp.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.cp.dto.awardVeidoo.AddAwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.DelAwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidooPage;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidooPageReturn;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidooReturn;
import com.lj.business.cp.dto.awardVeidoo.UpdateAwardVeidoo;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IAwardVeidooService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购行为信息记录表信息
	 *
	 * @param addAwardVeidoo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addAwardVeidoo(AddAwardVeidoo addAwardVeidoo) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @param findAwardVeidoo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindAwardVeidooReturn findAwardVeidoo(FindAwardVeidoo findAwardVeidoo) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @param delAwardVeidoo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delAwardVeidoo(DelAwardVeidoo delAwardVeidoo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @param updateAwardVeidoo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateAwardVeidoo(UpdateAwardVeidoo updateAwardVeidoo)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购行为信息记录表信息
	 *
	 * @param findAwardVeidooPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindAwardVeidooPageReturn> findAwardVeidooPage(FindAwardVeidooPage findAwardVeidooPage) throws TsfaServiceException;
	

}
