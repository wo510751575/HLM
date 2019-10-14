package com.lj.business.cm.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IFriendsVideoMaterialDao;
import com.lj.business.cm.domain.FriendsVideoMaterial;
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
import com.lj.business.cm.service.IFriendsVideoMaterialService;

/**
 * 
 * 
 * 类说明：朋友圈视频素材实现类
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
@Service
public class FriendsVideoMaterialServiceImpl implements IFriendsVideoMaterialService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(FriendsVideoMaterialServiceImpl.class);
	
	@Resource
	private IFriendsVideoMaterialDao friendsVideoMaterialDao;
	
	@Override
	public AddFriendsVideoMaterialReturn addFriendsVideoMaterial(AddFriendsVideoMaterial addFriendsVideoMaterial) throws TsfaServiceException {
		logger.debug("addFriendsVideoMaterial(AddFriendsVideoMaterial addFriendsVideoMaterial={}) - start", addFriendsVideoMaterial); 

		AssertUtils.notNull(addFriendsVideoMaterial);
		try {
			FriendsVideoMaterial friendsVideoMaterial = new FriendsVideoMaterial();
			//add数据录入
			friendsVideoMaterial.setCode(GUID.getPreUUID());
			friendsVideoMaterial.setMerchantNo(addFriendsVideoMaterial.getMerchantNo());
			friendsVideoMaterial.setTitle(addFriendsVideoMaterial.getTitle());
			friendsVideoMaterial.setShareTitle(addFriendsVideoMaterial.getShareTitle());
			friendsVideoMaterial.setContent(addFriendsVideoMaterial.getContent());
			friendsVideoMaterial.setMaterialType(addFriendsVideoMaterial.getMaterialType());
			friendsVideoMaterial.setImageUrl(addFriendsVideoMaterial.getImageUrl());
			friendsVideoMaterial.setLinkUrl(addFriendsVideoMaterial.getLinkUrl());
			friendsVideoMaterial.setAutoComment(addFriendsVideoMaterial.getAutoComment());
			friendsVideoMaterial.setCommentContent(addFriendsVideoMaterial.getCommentContent());
			friendsVideoMaterial.setDeleteFlag(addFriendsVideoMaterial.getDeleteFlag());
			friendsVideoMaterial.setCreateId(addFriendsVideoMaterial.getCreateId());
			friendsVideoMaterial.setCreateDate(new Date());
			friendsVideoMaterial.setRemark(addFriendsVideoMaterial.getRemark());
			friendsVideoMaterial.setRemark2(addFriendsVideoMaterial.getRemark2());
			friendsVideoMaterial.setRemark3(addFriendsVideoMaterial.getRemark3());
			friendsVideoMaterial.setRemark4(addFriendsVideoMaterial.getRemark4());
			friendsVideoMaterialDao.insert(friendsVideoMaterial);
			AddFriendsVideoMaterialReturn addFriendsVideoMaterialReturn = new AddFriendsVideoMaterialReturn();
			
			logger.debug("addFriendsVideoMaterial(AddFriendsVideoMaterial) - end - return value={}", addFriendsVideoMaterialReturn); 
			return addFriendsVideoMaterialReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增朋友圈视频素材信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_VIDEO_MATERIAL_ADD_ERROR,"新增朋友圈视频素材信息错误！",e);
		}
	}
	
	@Override
	public DelFriendsVideoMaterialReturn delFriendsVideoMaterial(DelFriendsVideoMaterial delFriendsVideoMaterial) throws TsfaServiceException {
		logger.debug("delFriendsVideoMaterial(DelFriendsVideoMaterial delFriendsVideoMaterial={}) - start", delFriendsVideoMaterial); 

		AssertUtils.notNull(delFriendsVideoMaterial);
		AssertUtils.notNull(delFriendsVideoMaterial.getCode(),"Code不能为空！");
		try {
			friendsVideoMaterialDao.deleteByPrimaryKey(delFriendsVideoMaterial.getCode());
			DelFriendsVideoMaterialReturn delFriendsVideoMaterialReturn  = new DelFriendsVideoMaterialReturn();

			logger.debug("delFriendsVideoMaterial(DelFriendsVideoMaterial) - end - return value={}", delFriendsVideoMaterialReturn); 
			return delFriendsVideoMaterialReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除朋友圈视频素材信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_VIDEO_MATERIAL_DEL_ERROR,"删除朋友圈视频素材信息错误！",e);
		}
	}

	@Override
	public UpdateFriendsVideoMaterialReturn updateFriendsVideoMaterial(UpdateFriendsVideoMaterial updateFriendsVideoMaterial) throws TsfaServiceException {
		logger.debug("updateFriendsVideoMaterial(UpdateFriendsVideoMaterial updateFriendsVideoMaterial={}) - start", updateFriendsVideoMaterial); 

		AssertUtils.notNull(updateFriendsVideoMaterial);
		AssertUtils.notNullAndEmpty(updateFriendsVideoMaterial.getCode(),"Code不能为空");
		try {
			FriendsVideoMaterial friendsVideoMaterial = new FriendsVideoMaterial();
			//update数据录入
			friendsVideoMaterial.setCode(updateFriendsVideoMaterial.getCode());
			friendsVideoMaterial.setMerchantNo(updateFriendsVideoMaterial.getMerchantNo());
			friendsVideoMaterial.setTitle(updateFriendsVideoMaterial.getTitle());
			friendsVideoMaterial.setShareTitle(updateFriendsVideoMaterial.getShareTitle());
			friendsVideoMaterial.setContent(updateFriendsVideoMaterial.getContent());
			friendsVideoMaterial.setMaterialType(updateFriendsVideoMaterial.getMaterialType());
			friendsVideoMaterial.setImageUrl(updateFriendsVideoMaterial.getImageUrl());
			friendsVideoMaterial.setLinkUrl(updateFriendsVideoMaterial.getLinkUrl());
			friendsVideoMaterial.setAutoComment(updateFriendsVideoMaterial.getAutoComment());
			friendsVideoMaterial.setCommentContent(updateFriendsVideoMaterial.getCommentContent());
			friendsVideoMaterial.setDeleteFlag(updateFriendsVideoMaterial.getDeleteFlag());
			friendsVideoMaterial.setCreateId(updateFriendsVideoMaterial.getCreateId());
			friendsVideoMaterial.setCreateDate(updateFriendsVideoMaterial.getCreateDate());
			friendsVideoMaterial.setRemark(updateFriendsVideoMaterial.getRemark());
			friendsVideoMaterial.setRemark2(updateFriendsVideoMaterial.getRemark2());
			friendsVideoMaterial.setRemark3(updateFriendsVideoMaterial.getRemark3());
			friendsVideoMaterial.setRemark4(updateFriendsVideoMaterial.getRemark4());
			AssertUtils.notUpdateMoreThanOne(friendsVideoMaterialDao.updateByPrimaryKeySelective(friendsVideoMaterial));
			UpdateFriendsVideoMaterialReturn updateFriendsVideoMaterialReturn = new UpdateFriendsVideoMaterialReturn();

			logger.debug("updateFriendsVideoMaterial(UpdateFriendsVideoMaterial) - end - return value={}", updateFriendsVideoMaterialReturn); 
			return updateFriendsVideoMaterialReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("朋友圈视频素材信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_VIDEO_MATERIAL_UPDATE_ERROR,"朋友圈视频素材信息更新信息错误！",e);
		}
	}

	@Override
	public FindFriendsVideoMaterialReturn findFriendsVideoMaterial(FindFriendsVideoMaterial findFriendsVideoMaterial) throws TsfaServiceException {
		logger.debug("findFriendsVideoMaterial(FindFriendsVideoMaterial findFriendsVideoMaterial={}) - start", findFriendsVideoMaterial); 

		AssertUtils.notNull(findFriendsVideoMaterial);
		AssertUtils.notAllNull(findFriendsVideoMaterial.getCode(),"Code不能为空");
		try {
			FriendsVideoMaterial friendsVideoMaterial = friendsVideoMaterialDao.selectByPrimaryKey(findFriendsVideoMaterial.getCode());
			if(friendsVideoMaterial == null){
				throw new TsfaServiceException(ErrorCode.FRIENDS_VIDEO_MATERIAL_NOT_EXIST_ERROR,"朋友圈视频素材信息不存在");
			}
			FindFriendsVideoMaterialReturn findFriendsVideoMaterialReturn = new FindFriendsVideoMaterialReturn();
			//find数据录入
			findFriendsVideoMaterialReturn.setCode(friendsVideoMaterial.getCode());
			findFriendsVideoMaterialReturn.setMerchantNo(friendsVideoMaterial.getMerchantNo());
			findFriendsVideoMaterialReturn.setTitle(friendsVideoMaterial.getTitle());
			findFriendsVideoMaterialReturn.setShareTitle(friendsVideoMaterial.getShareTitle());
			findFriendsVideoMaterialReturn.setContent(friendsVideoMaterial.getContent());
			findFriendsVideoMaterialReturn.setMaterialType(friendsVideoMaterial.getMaterialType());
			findFriendsVideoMaterialReturn.setImageUrl(friendsVideoMaterial.getImageUrl());
			findFriendsVideoMaterialReturn.setLinkUrl(friendsVideoMaterial.getLinkUrl());
			findFriendsVideoMaterialReturn.setAutoComment(friendsVideoMaterial.getAutoComment());
			findFriendsVideoMaterialReturn.setCommentContent(friendsVideoMaterial.getCommentContent());
			findFriendsVideoMaterialReturn.setDeleteFlag(friendsVideoMaterial.getDeleteFlag());
			findFriendsVideoMaterialReturn.setCreateId(friendsVideoMaterial.getCreateId());
			findFriendsVideoMaterialReturn.setCreateDate(friendsVideoMaterial.getCreateDate());
			findFriendsVideoMaterialReturn.setRemark(friendsVideoMaterial.getRemark());
			findFriendsVideoMaterialReturn.setRemark2(friendsVideoMaterial.getRemark2());
			findFriendsVideoMaterialReturn.setRemark3(friendsVideoMaterial.getRemark3());
			findFriendsVideoMaterialReturn.setRemark4(friendsVideoMaterial.getRemark4());
			
			logger.debug("findFriendsVideoMaterial(FindFriendsVideoMaterial) - end - return value={}", findFriendsVideoMaterialReturn); 
			return findFriendsVideoMaterialReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找朋友圈视频素材信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_VIDEO_MATERIAL_FIND_ERROR,"查找朋友圈视频素材信息信息错误！",e);
		}
	}

	@Override
	public Page<FindFriendsVideoMaterialPageReturn> findFriendsVideoMaterialPage(FindFriendsVideoMaterialPage findFriendsVideoMaterialPage) throws TsfaServiceException {
		logger.debug("findFriendsVideoMaterialPage(FindFriendsVideoMaterialPage findFriendsVideoMaterialPage={}) - start", findFriendsVideoMaterialPage); 

		AssertUtils.notNull(findFriendsVideoMaterialPage);
		List<FindFriendsVideoMaterialPageReturn> returnList = null;
		int count = 0;
		try {
			count = friendsVideoMaterialDao.findFriendsVideoMaterialPageCount(findFriendsVideoMaterialPage);
			if(count > 0) {
				returnList = friendsVideoMaterialDao.findFriendsVideoMaterialPage(findFriendsVideoMaterialPage);
			}
		}  catch (Exception e) {
			logger.error("朋友圈视频素材信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_VIDEO_MATERIAL_FIND_PAGE_ERROR,"朋友圈视频素材信息不存在错误.！",e);
		}
		Page<FindFriendsVideoMaterialPageReturn> returnPage = new Page<FindFriendsVideoMaterialPageReturn>(returnList, count, findFriendsVideoMaterialPage);

		logger.debug("findFriendsVideoMaterialPage(FindFriendsVideoMaterialPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 
}
