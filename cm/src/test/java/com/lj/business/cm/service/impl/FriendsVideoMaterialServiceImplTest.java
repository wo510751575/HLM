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
import com.lj.business.cm.dto.friends.AddFriendsVideoMaterial;
import com.lj.business.cm.dto.friends.DelFriendsVideoMaterial;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterial;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialPage;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialPageReturn;
import com.lj.business.cm.dto.friends.UpdateFriendsVideoMaterial;
import com.lj.business.cm.service.IFriendsVideoMaterialService;


/**
 * 
 * 
 * 类说明：朋友圈视频素材测试类
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
public class FriendsVideoMaterialServiceImplTest extends SpringTestCase{

	@Resource
	private IFriendsVideoMaterialService friendsVideoMaterialService;

	/**
	 * 
	 *
	 * 方法说明：添加朋友圈视频素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void addFriendsVideoMaterial() throws TsfaServiceException{
		AddFriendsVideoMaterial addFriendsVideoMaterial = new AddFriendsVideoMaterial();
		//add数据录入
		addFriendsVideoMaterial.setCode("Code");
		addFriendsVideoMaterial.setMerchantNo("MerchantNo");
		addFriendsVideoMaterial.setTitle("Title");
		addFriendsVideoMaterial.setShareTitle("ShareTitle");
		addFriendsVideoMaterial.setContent("Content");
		addFriendsVideoMaterial.setMaterialType("MaterialType");
		addFriendsVideoMaterial.setImageUrl("ImageUrl");
		addFriendsVideoMaterial.setLinkUrl("LinkUrl");
		addFriendsVideoMaterial.setAutoComment(1);
		addFriendsVideoMaterial.setCommentContent("CommentContent");
		addFriendsVideoMaterial.setDeleteFlag(1);
		addFriendsVideoMaterial.setCreateId("CreateId");
		addFriendsVideoMaterial.setCreateDate(new Date());
		addFriendsVideoMaterial.setRemark("Remark");
		addFriendsVideoMaterial.setRemark2("Remark2");
		addFriendsVideoMaterial.setRemark3("Remark3");
		addFriendsVideoMaterial.setRemark4("Remark4");
		
		Assert.assertNotNull(friendsVideoMaterialService.addFriendsVideoMaterial(addFriendsVideoMaterial ));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改朋友圈视频素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void updateFriendsVideoMaterial() throws TsfaServiceException{
		UpdateFriendsVideoMaterial updateFriendsVideoMaterial = new UpdateFriendsVideoMaterial();
		//update数据录入
		updateFriendsVideoMaterial.setCode("Code");
		updateFriendsVideoMaterial.setMerchantNo("MerchantNo");
		updateFriendsVideoMaterial.setTitle("Title");
		updateFriendsVideoMaterial.setShareTitle("ShareTitle");
		updateFriendsVideoMaterial.setContent("Content");
		updateFriendsVideoMaterial.setMaterialType("MaterialType");
		updateFriendsVideoMaterial.setImageUrl("ImageUrl");
		updateFriendsVideoMaterial.setLinkUrl("LinkUrl");
		updateFriendsVideoMaterial.setAutoComment(1);
		updateFriendsVideoMaterial.setCommentContent("CommentContent");
		updateFriendsVideoMaterial.setDeleteFlag(1);
		updateFriendsVideoMaterial.setCreateId("CreateId");
		updateFriendsVideoMaterial.setCreateDate(new Date());
		updateFriendsVideoMaterial.setRemark("Remark");
		updateFriendsVideoMaterial.setRemark2("Remark2");
		updateFriendsVideoMaterial.setRemark3("Remark3");
		updateFriendsVideoMaterial.setRemark4("Remark4");

		friendsVideoMaterialService.updateFriendsVideoMaterial(updateFriendsVideoMaterial );
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找朋友圈视频素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findFriendsVideoMaterial() throws TsfaServiceException{
		FindFriendsVideoMaterial findFriendsVideoMaterial = new FindFriendsVideoMaterial();
		findFriendsVideoMaterial.setCode("");
		friendsVideoMaterialService.findFriendsVideoMaterial(findFriendsVideoMaterial);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找朋友圈视频素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void findFriendsVideoMaterialPage() throws TsfaServiceException{
		FindFriendsVideoMaterialPage findFriendsVideoMaterialPage = new FindFriendsVideoMaterialPage();
		Page<FindFriendsVideoMaterialPageReturn> page = friendsVideoMaterialService.findFriendsVideoMaterialPage(findFriendsVideoMaterialPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除朋友圈视频素材信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Test
	public void delFriendsVideoMaterial() throws TsfaServiceException{
		DelFriendsVideoMaterial delFriendsVideoMaterial = new DelFriendsVideoMaterial();
		delFriendsVideoMaterial.setCode("");
		Assert.assertNotNull(friendsVideoMaterialService.delFriendsVideoMaterial(delFriendsVideoMaterial));
		
	}	
}
