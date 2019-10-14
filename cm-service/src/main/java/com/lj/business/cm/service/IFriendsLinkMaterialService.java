package com.lj.business.cm.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.friendsLinkMaterial.AddFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.AddFriendsLinkMaterialReturn;
import com.lj.business.cm.friendsLinkMaterial.DelFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.DelFriendsLinkMaterialReturn;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPage;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPageReturn;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialReturn;
import com.lj.business.cm.friendsLinkMaterial.UpdateFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.UpdateFriendsLinkMaterialReturn;


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
public interface IFriendsLinkMaterialService {
	
	/**
	 * 
	 *
	 * 方法说明：添加朋友圈链接素材信息
	 *
	 * @param addFriendsLinkMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddFriendsLinkMaterialReturn addFriendsLinkMaterial(AddFriendsLinkMaterial addFriendsLinkMaterial) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找朋友圈链接素材信息
	 *
	 * @param findFriendsLinkMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindFriendsLinkMaterialReturn findFriendsLinkMaterial(FindFriendsLinkMaterial findFriendsLinkMaterial) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除朋友圈链接素材信息
	 *
	 * @param delFriendsLinkMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelFriendsLinkMaterialReturn delFriendsLinkMaterial(DelFriendsLinkMaterial delFriendsLinkMaterial) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改朋友圈链接素材信息
	 *
	 * @param updateFriendsLinkMaterial
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateFriendsLinkMaterialReturn updateFriendsLinkMaterial(UpdateFriendsLinkMaterial updateFriendsLinkMaterial)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找朋友圈链接素材信息
	 *
	 * @param findFriendsLinkMaterialPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindFriendsLinkMaterialPageReturn> findFriendsLinkMaterialPage(FindFriendsLinkMaterialPage findFriendsLinkMaterialPage) throws TsfaServiceException;
	

}
