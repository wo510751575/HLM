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
import com.lj.business.cm.dto.vrMaterialType.AddVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.AddVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialType.DelVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.DelVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeApiReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypePage;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypePageReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialType.UpdateVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.UpdateVrMaterialTypeReturn;


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
public interface IVrMaterialTypeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加VR素材类型信息
	 *
	 * @param addVrMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddVrMaterialTypeReturn addVrMaterialType(AddVrMaterialType addVrMaterialType) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找VR素材类型信息
	 *
	 * @param findVrMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindVrMaterialTypeReturn findVrMaterialType(FindVrMaterialType findVrMaterialType) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除VR素材类型信息
	 *
	 * @param delVrMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelVrMaterialTypeReturn delVrMaterialType(DelVrMaterialType delVrMaterialType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改VR素材类型信息
	 *
	 * @param updateVrMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateVrMaterialTypeReturn updateVrMaterialType(UpdateVrMaterialType updateVrMaterialType)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找VR素材类型信息
	 *
	 * @param findVrMaterialTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindVrMaterialTypePageReturn> findVrMaterialTypePage(FindVrMaterialTypePage findVrMaterialTypePage) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：查找商户或终端下所有的VR素材类型
	 *
	 * @param findVrMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年12月13日
	 *
	 */
	public List<FindVrMaterialTypeApiReturn>  findVrMaterialTypeReturnList(FindVrMaterialType findVrMaterialType) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：获取商户下的类型
	 *
	 * @param findVrMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年12月15日
	 *
	 */
	public List<FindVrMaterialTypeReturn> findVrMaterialTypeList(FindVrMaterialType findVrMaterialType)throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：获取商户下类型的显示序号
	 *
	 * @param findVrMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2018年02月02日
	 *
	 */
	public List<Integer> findVrMaterialTypeShowIndexList(FindVrMaterialType findVrMaterialType)throws TsfaServiceException;
	

}
