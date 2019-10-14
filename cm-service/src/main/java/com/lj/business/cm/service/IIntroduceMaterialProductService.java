package com.lj.business.cm.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.business.cm.domain.IntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.AddIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.DelIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductPage;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductPageReturn;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductReturn;
import com.lj.business.cm.dto.introduceMaterialProduct.UpdateIntroduceMaterialProduct;
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
public interface IIntroduceMaterialProductService {
	
	/**
	 * 
	 *
	 * 方法说明：添加个人素材介绍产品关联表信息
	 *
	 * @param addIntroduceMaterialProduct
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addIntroduceMaterialProduct(AddIntroduceMaterialProduct addIntroduceMaterialProduct) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找个人素材介绍产品关联表信息
	 *
	 * @param findIntroduceMaterialProduct
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindIntroduceMaterialProductReturn findIntroduceMaterialProduct(FindIntroduceMaterialProduct findIntroduceMaterialProduct) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除个人素材介绍产品关联表信息
	 *
	 * @param delIntroduceMaterialProduct
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delIntroduceMaterialProduct(DelIntroduceMaterialProduct delIntroduceMaterialProduct) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改个人素材介绍产品关联表信息
	 *
	 * @param updateIntroduceMaterialProduct
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateIntroduceMaterialProduct(UpdateIntroduceMaterialProduct updateIntroduceMaterialProduct)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询个人素材介绍产品关联表信息
	 *
	 * @param findIntroduceMaterialProductPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindIntroduceMaterialProductPageReturn> findIntroduceMaterialProductPage(FindIntroduceMaterialProductPage findIntroduceMaterialProductPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据素材编号查找个人素材介绍产品关联表信息
	 *
	 * @param findIntroduceMaterialProduct
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月11日
	 *
	 */
	public List<IntroduceMaterialProduct> findIntroduceMaterialProductByMaterCode(
			FindIntroduceMaterialProduct findIntroduceMaterialProduct);

	/**
	 * 
	 *
	 * 方法说明：根据素材编号删除个人素材介绍产品关联表信息
	 *
	 * @param findIntroduceMaterialProduct
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月11日
	 *
	 */
	public void delIntroduceMaterialProductByMaterCode(
			DelIntroduceMaterialProduct delIntroduceMaterialProduct);
	

}
