package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.FaceCheckDto;
import com.ye.business.hx.dto.FindFaceCheckPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
public interface IFaceCheckService {
	
	/**
	 * 
	 *
	 * 方法说明：添加面部检查信息
	 *
	 * @param faceCheckDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addFaceCheck(FaceCheckDto faceCheckDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找面部检查信息
	 *
	 * @param findFaceCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public FaceCheckDto findFaceCheck(FaceCheckDto faceCheckDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询面部检查信息
	 *
	 * @param findFaceCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<FaceCheckDto>  findFaceChecks(FindFaceCheckPage findFaceCheckPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改面部检查信息
	 *
	 * @param updateFaceCheck
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateFaceCheck(FaceCheckDto faceCheckDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询面部检查信息
	 *
	 * @param findFaceCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<FaceCheckDto> findFaceCheckPage(FindFaceCheckPage findFaceCheckPage) throws TsfaServiceException;
	

}
