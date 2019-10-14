package com.lj.business.cm.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.friends.AddFriendsVideoMaterial;
import com.lj.business.cm.dto.friends.AddFriendsVideoMaterialReturn;
import com.lj.business.cm.dto.friends.DelFriendsVideoMaterial;
import com.lj.business.cm.dto.friends.DelFriendsVideoMaterialReturn;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterial;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialPage;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialPageReturn;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialReturn;
import com.lj.business.cm.dto.friends.UpdateFriendsVideoMaterial;
import com.lj.business.cm.dto.friends.UpdateFriendsVideoMaterialReturn;


/**
 * 
 * 
 * 类说明：朋友圈视频素材接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
public interface IFriendsVideoMaterialService {
	
	/**
	 * 
	 *
	 * 方法说明：添加朋友圈视频素材信息
	 *
	 * @param addFriendsVideoMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddFriendsVideoMaterialReturn addFriendsVideoMaterial(AddFriendsVideoMaterial addFriendsVideoMaterial) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找朋友圈视频素材信息
	 *
	 * @param findFriendsVideoMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindFriendsVideoMaterialReturn findFriendsVideoMaterial(FindFriendsVideoMaterial findFriendsVideoMaterial) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除朋友圈视频素材信息
	 *
	 * @param delFriendsVideoMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelFriendsVideoMaterialReturn delFriendsVideoMaterial(DelFriendsVideoMaterial delFriendsVideoMaterial) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改朋友圈视频素材信息
	 *
	 * @param updateFriendsVideoMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateFriendsVideoMaterialReturn updateFriendsVideoMaterial(UpdateFriendsVideoMaterial updateFriendsVideoMaterial)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找朋友圈视频素材信息
	 *
	 * @param findFriendsVideoMaterialPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindFriendsVideoMaterialPageReturn> findFriendsVideoMaterialPage(FindFriendsVideoMaterialPage findFriendsVideoMaterialPage) throws TsfaServiceException;
}
