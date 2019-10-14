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
import com.lj.business.cm.dto.vrMaterialCommen.AddVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.AddVrMaterialCommenReturn;
import com.lj.business.cm.dto.vrMaterialCommen.DelVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.DelVrMaterialCommenReturn;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPage;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPageReturn;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenReturn;
import com.lj.business.cm.dto.vrMaterialCommen.UpdateVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.UpdateVrMaterialCommenReturn;


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
public interface IVrMaterialCommenService {
	
	/**
	 * 
	 *
	 * 方法说明：添加VR公用素材中心信息
	 *
	 * @param addVrMaterialCommen
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddVrMaterialCommenReturn addVrMaterialCommen(AddVrMaterialCommen addVrMaterialCommen) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找VR公用素材中心信息
	 *
	 * @param findVrMaterialCommen
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindVrMaterialCommenReturn findVrMaterialCommen(FindVrMaterialCommen findVrMaterialCommen) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除VR公用素材中心信息
	 *
	 * @param delVrMaterialCommen
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelVrMaterialCommenReturn delVrMaterialCommen(DelVrMaterialCommen delVrMaterialCommen) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改VR公用素材中心信息
	 *
	 * @param updateVrMaterialCommen
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateVrMaterialCommenReturn updateVrMaterialCommen(UpdateVrMaterialCommen updateVrMaterialCommen)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找VR公用素材中心信息
	 *
	 * @param findVrMaterialCommenPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *  
	 */
	public Page<FindVrMaterialCommenPageReturn> findVrMaterialCommenPage(FindVrMaterialCommenPage findVrMaterialCommenPage) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：oms分页查询
	 *
	 * @param findVrMaterialCommenPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年12月15日
	 *
	 */
	public Page<FindVrMaterialCommenPageReturn> findVrMaterialCommenReturnPage(FindVrMaterialCommenPage findVrMaterialCommenPage )throws TsfaServiceException;
	

}
