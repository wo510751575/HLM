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
import com.lj.business.cm.dto.vrMaterialTypeCategory.AddVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.AddVrMaterialTypeCategoryReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.DelVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.DelVrMaterialTypeCategoryReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryPage;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryPageReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.UpdateVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.UpdateVrMaterialTypeCategoryReturn;


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
public interface IVrMaterialTypeCategoryService {
	
	/**
	 * 
	 *
	 * 方法说明：添加VR素材类型分类信息
	 *
	 * @param addVrMaterialTypeCategory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddVrMaterialTypeCategoryReturn addVrMaterialTypeCategory(AddVrMaterialTypeCategory addVrMaterialTypeCategory) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找VR素材类型分类信息
	 *
	 * @param findVrMaterialTypeCategory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindVrMaterialTypeCategoryReturn findVrMaterialTypeCategory(FindVrMaterialTypeCategory findVrMaterialTypeCategory) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除VR素材类型分类信息
	 *
	 * @param delVrMaterialTypeCategory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelVrMaterialTypeCategoryReturn delVrMaterialTypeCategory(DelVrMaterialTypeCategory delVrMaterialTypeCategory) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改VR素材类型分类信息
	 *
	 * @param updateVrMaterialTypeCategory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateVrMaterialTypeCategoryReturn updateVrMaterialTypeCategory(UpdateVrMaterialTypeCategory updateVrMaterialTypeCategory)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找VR素材类型分类信息
	 *
	 * @param findVrMaterialTypeCategoryPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindVrMaterialTypeCategoryPageReturn> findVrMaterialTypeCategoryPage(FindVrMaterialTypeCategoryPage findVrMaterialTypeCategoryPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：获取父类型下的子类分类
	 *
	 * @param findVrMaterialTypeCategory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年12月13日
	 *
	 */
	public List<FindVrMaterialTypeCategoryReturn> findVrMaterialTypeCategoryReturnList(FindVrMaterialTypeCategory findVrMaterialTypeCategory)throws TsfaServiceException;

}
