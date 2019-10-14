package com.lj.business.cm.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cm.friendsLinkMaterial.AddFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.DelFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPage;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPageReturn;
import com.lj.business.cm.friendsLinkMaterial.UpdateFriendsLinkMaterial;
import com.lj.business.cm.service.IFriendsLinkMaterialService;


/**
 * 类说明：测试类
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
public class FriendsLinkMaterialServiceImplTest extends SpringTestCase{

	@Resource
	IFriendsLinkMaterialService friendsLinkMaterialService;



	/**
	 * 
	 *
	 * 方法说明：添加朋友圈链接素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addFriendsLinkMaterial() throws TsfaServiceException{
		AddFriendsLinkMaterial addFriendsLinkMaterial = new AddFriendsLinkMaterial();
		//add数据录入
		addFriendsLinkMaterial.setMerchantNo("MerchantNo");
		addFriendsLinkMaterial.setTitle("Title");
		addFriendsLinkMaterial.setContent("Content");
		addFriendsLinkMaterial.setMaterialType("MaterialType");
		addFriendsLinkMaterial.setLinkUrl("LinkUrl");
		addFriendsLinkMaterial.setAutoComment(1);
		addFriendsLinkMaterial.setCommentContent("CommentContent");
		addFriendsLinkMaterial.setDeleteFlag(3);
		addFriendsLinkMaterial.setCreateId("CreateId");
		addFriendsLinkMaterial.setRemark("Remark");
		addFriendsLinkMaterial.setRemark2("Remark2");
		addFriendsLinkMaterial.setRemark3("Remark3");
		addFriendsLinkMaterial.setRemark4("Remark4");
		
		Assert.assertNotNull(friendsLinkMaterialService.addFriendsLinkMaterial(addFriendsLinkMaterial ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改朋友圈链接素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateFriendsLinkMaterial() throws TsfaServiceException{
		UpdateFriendsLinkMaterial updateFriendsLinkMaterial = new UpdateFriendsLinkMaterial();
		//update数据录入
		updateFriendsLinkMaterial.setCode("LJ_bbd717dfdf8c4b02961348f159aaa62f");
		updateFriendsLinkMaterial.setMerchantNo("MerchantNo");
		updateFriendsLinkMaterial.setTitle("Title");
		updateFriendsLinkMaterial.setContent("Content");
		updateFriendsLinkMaterial.setMaterialType("MaterialType");
		updateFriendsLinkMaterial.setLinkUrl("LinkUrl");
		updateFriendsLinkMaterial.setAutoComment(1);
		updateFriendsLinkMaterial.setCommentContent("CommentContent");
		updateFriendsLinkMaterial.setDeleteFlag(2);
		updateFriendsLinkMaterial.setCreateId("CreateId");
		updateFriendsLinkMaterial.setCreateDate(new Date());
		updateFriendsLinkMaterial.setRemark("Remark");
		updateFriendsLinkMaterial.setRemark2("Remark2");
		updateFriendsLinkMaterial.setRemark3("Remark3");
		updateFriendsLinkMaterial.setRemark4("Remark4");

		friendsLinkMaterialService.updateFriendsLinkMaterial(updateFriendsLinkMaterial );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找朋友圈链接素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findFriendsLinkMaterial() throws TsfaServiceException{
		FindFriendsLinkMaterial findFriendsLinkMaterial = new FindFriendsLinkMaterial();
		findFriendsLinkMaterial.setCode("LJ_bbd717dfdf8c4b02961348f159aaa62f");
		friendsLinkMaterialService.findFriendsLinkMaterial(findFriendsLinkMaterial);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找朋友圈链接素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findFriendsLinkMaterialPage() throws TsfaServiceException{
		FindFriendsLinkMaterialPage findFriendsLinkMaterialPage = new FindFriendsLinkMaterialPage();
		findFriendsLinkMaterialPage.setMerchantNo("MerchantNo");
		Page<FindFriendsLinkMaterialPageReturn> page = friendsLinkMaterialService.findFriendsLinkMaterialPage(findFriendsLinkMaterialPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除朋友圈链接素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void delFriendsLinkMaterial() throws TsfaServiceException{
		DelFriendsLinkMaterial delFriendsLinkMaterial = new DelFriendsLinkMaterial();
		delFriendsLinkMaterial.setCode("LJ_bbd717dfdf8c4b02961348f159aaa62f");
		Assert.assertNotNull(friendsLinkMaterialService.delFriendsLinkMaterial(delFriendsLinkMaterial));
		
	}
	
	
}
