package com.lj.business.cm.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.domain.VrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.AddVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.AddVrMaterialCommenCategoryReturn;
import com.lj.business.cm.dto.vrMaterialCommenCategory.DelVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.DelVrMaterialCommenCategoryReturn;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategoryReturn;
import com.lj.business.cm.dto.vrMaterialCommenCategory.UpdateVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.UpdateVrMaterialCommenCategoryReturn;


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
public interface IVrMaterialCommenCategoryService {
	
	/**
	 * 
	 *
	 * 方法说明：添加VR公用素材中心-分类关联表信息
	 *
	 * @param addVrMaterialCommenCategory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddVrMaterialCommenCategoryReturn addVrMaterialCommenCategory(AddVrMaterialCommenCategory addVrMaterialCommenCategory) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找VR公用素材中心-分类关联表信息
	 *
	 * @param findVrMaterialCommenCategory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindVrMaterialCommenCategoryReturn findVrMaterialCommenCategory(FindVrMaterialCommenCategory findVrMaterialCommenCategory) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除VR公用素材中心-分类关联表信息
	 *
	 * @param delVrMaterialCommenCategory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelVrMaterialCommenCategoryReturn delVrMaterialCommenCategory(DelVrMaterialCommenCategory delVrMaterialCommenCategory) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改VR公用素材中心-分类关联表信息
	 *
	 * @param updateVrMaterialCommenCategory
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateVrMaterialCommenCategoryReturn updateVrMaterialCommenCategory(UpdateVrMaterialCommenCategory updateVrMaterialCommenCategory)throws TsfaServiceException;

/*	*//**
	 * 
	 *
	 * 方法说明：查找VR公用素材中心-分类关联表信息
	 *
	 * @param findVrMaterialCommenCategoryPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 *//*
	public Page<FindVrMaterialCommenCategoryPageReturn> findVrMaterialCommenCategoryPage(FindVrMaterialCommenCategoryPage findVrMaterialCommenCategoryPage) throws TsfaServiceException;
	
	*/
  /**
   * 
   *
   * 方法说明：
   *
   * @param findVrMaterialCommenCategory
   * @return
   * @throws TsfaServiceException
   *
   * @author 罗书明 CreateDate: 2017年12月18日
   *
   */
  public List<FindVrMaterialCommenCategoryReturn> findVrMaterialCommenCategoryList(FindVrMaterialCommenCategory findVrMaterialCommenCategory)throws TsfaServiceException;
   
  /**
   * 
   *
   * 方法说明：根据素材中心code查找关联信息
   *
   * @param code
   * @return
   *
   * @author 罗书明 CreateDate: 2017年12月18日
   *
   */
  public  FindVrMaterialCommenCategoryReturn findByPrimaryKey(FindVrMaterialCommenCategory findVrMaterialCommenCategory);
  
}
