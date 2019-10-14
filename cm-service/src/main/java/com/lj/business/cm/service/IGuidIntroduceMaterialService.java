package com.lj.business.cm.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.business.cm.dto.guidIntroduceMaterial.AddGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.DelGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPage;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPageReturn;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialReturn;
import com.lj.business.cm.dto.guidIntroduceMaterial.UpdateGuidIntroduceMaterial;
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
public interface IGuidIntroduceMaterialService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购个人介绍素材表信息
	 *
	 * @param addGuidIntroduceMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addGuidIntroduceMaterial(AddGuidIntroduceMaterial addGuidIntroduceMaterial) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购个人介绍素材表信息
	 *
	 * @param findGuidIntroduceMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindGuidIntroduceMaterialReturn findGuidIntroduceMaterial(FindGuidIntroduceMaterial findGuidIntroduceMaterial) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购个人介绍素材表信息
	 *
	 * @param delGuidIntroduceMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delGuidIntroduceMaterial(DelGuidIntroduceMaterial delGuidIntroduceMaterial) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购个人介绍素材表信息
	 *
	 * @param updateGuidIntroduceMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateGuidIntroduceMaterial(UpdateGuidIntroduceMaterial updateGuidIntroduceMaterial)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购个人介绍素材表信息
	 *
	 * @param findGuidIntroduceMaterialPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindGuidIntroduceMaterialPageReturn> findGuidIntroduceMaterialPage(FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查询导购个人介绍素材表信息
	 *
	 * @param findGuidIntroduceMaterialPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年09月19日
	 *
	 */
	public List<FindGuidIntroduceMaterialPageReturn> findGuidIntroduceMaterialList(
			FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage);

	/**
	 * 
	 *
	 * 方法说明：查询导购个人介绍素材表数量
	 *
	 * @param findGuidIntroduceMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年09月19日
	 *
	 */
	public int findCountMaterial(
			FindGuidIntroduceMaterial findGuidIntroduceMaterial);
	

}
