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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IFriendsLinkMaterialDao;
import com.lj.business.cm.domain.FriendsLinkMaterial;
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
import com.lj.business.cm.service.IFriendsLinkMaterialService;

/**
 * 类说明：实现类
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
@Service
public class FriendsLinkMaterialServiceImpl implements IFriendsLinkMaterialService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(FriendsLinkMaterialServiceImpl.class);
	

	@Resource
	private IFriendsLinkMaterialDao friendsLinkMaterialDao;
	
	
	@Override
	public AddFriendsLinkMaterialReturn addFriendsLinkMaterial(
			AddFriendsLinkMaterial addFriendsLinkMaterial) throws TsfaServiceException {
		logger.debug("addFriendsLinkMaterial(AddFriendsLinkMaterial addFriendsLinkMaterial={}) - start", addFriendsLinkMaterial); 

		AssertUtils.notNull(addFriendsLinkMaterial);
		try {
			FriendsLinkMaterial friendsLinkMaterial = new FriendsLinkMaterial();
			//add数据录入
			friendsLinkMaterial.setCode(GUID.getPreUUID());
			friendsLinkMaterial.setMerchantNo(addFriendsLinkMaterial.getMerchantNo());
			friendsLinkMaterial.setTitle(addFriendsLinkMaterial.getTitle());
			friendsLinkMaterial.setContent(addFriendsLinkMaterial.getContent());
			friendsLinkMaterial.setMaterialType(addFriendsLinkMaterial.getMaterialType());
			friendsLinkMaterial.setImageUrl(addFriendsLinkMaterial.getImageUrl());
			friendsLinkMaterial.setLinkUrl(addFriendsLinkMaterial.getLinkUrl());
			friendsLinkMaterial.setAutoComment(addFriendsLinkMaterial.getAutoComment());
			friendsLinkMaterial.setCommentContent(addFriendsLinkMaterial.getCommentContent());
			friendsLinkMaterial.setDeleteFlag(addFriendsLinkMaterial.getDeleteFlag());
			friendsLinkMaterial.setCreateId(addFriendsLinkMaterial.getCreateId());
			friendsLinkMaterial.setCreateDate(new Date());
			friendsLinkMaterial.setRemark(addFriendsLinkMaterial.getRemark());
			friendsLinkMaterial.setRemark2(addFriendsLinkMaterial.getRemark2());
			friendsLinkMaterial.setRemark3(addFriendsLinkMaterial.getRemark3());
			friendsLinkMaterial.setRemark4(addFriendsLinkMaterial.getRemark4());
			friendsLinkMaterial.setShareTitle(addFriendsLinkMaterial.getShareTitle());
			friendsLinkMaterialDao.insert(friendsLinkMaterial);
			AddFriendsLinkMaterialReturn addFriendsLinkMaterialReturn = new AddFriendsLinkMaterialReturn();
			
			logger.debug("addFriendsLinkMaterial(AddFriendsLinkMaterial) - end - return value={}", addFriendsLinkMaterialReturn); 
			return addFriendsLinkMaterialReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增朋友圈链接素材信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_LINK_MATERIAL_ADD_ERROR,"新增朋友圈链接素材信息错误！",e);
		}
	}
	
	
	@Override
	public DelFriendsLinkMaterialReturn delFriendsLinkMaterial(
			DelFriendsLinkMaterial delFriendsLinkMaterial) throws TsfaServiceException {
		logger.debug("delFriendsLinkMaterial(DelFriendsLinkMaterial delFriendsLinkMaterial={}) - start", delFriendsLinkMaterial); 

		AssertUtils.notNull(delFriendsLinkMaterial);
		AssertUtils.notNull(delFriendsLinkMaterial.getCode(),"CODE不能为空！");
		try {
			friendsLinkMaterialDao.deleteByPrimaryKey(delFriendsLinkMaterial.getCode());
			DelFriendsLinkMaterialReturn delFriendsLinkMaterialReturn  = new DelFriendsLinkMaterialReturn();

			logger.debug("delFriendsLinkMaterial(DelFriendsLinkMaterial) - end - return value={}", delFriendsLinkMaterialReturn); 
			return delFriendsLinkMaterialReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除朋友圈链接素材信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_LINK_MATERIAL_DEL_ERROR,"删除朋友圈链接素材信息错误！",e);

		}
	}

	@Override
	public UpdateFriendsLinkMaterialReturn updateFriendsLinkMaterial(
			UpdateFriendsLinkMaterial updateFriendsLinkMaterial)
			throws TsfaServiceException {
		logger.debug("updateFriendsLinkMaterial(UpdateFriendsLinkMaterial updateFriendsLinkMaterial={}) - start", updateFriendsLinkMaterial); 

		AssertUtils.notNull(updateFriendsLinkMaterial);
		AssertUtils.notNullAndEmpty(updateFriendsLinkMaterial.getCode(),"CODE不能为空");
		try {
			FriendsLinkMaterial friendsLinkMaterial = new FriendsLinkMaterial();
			//update数据录入
			friendsLinkMaterial.setCode(updateFriendsLinkMaterial.getCode());
			friendsLinkMaterial.setMerchantNo(updateFriendsLinkMaterial.getMerchantNo());
			friendsLinkMaterial.setTitle(updateFriendsLinkMaterial.getTitle());
			friendsLinkMaterial.setContent(updateFriendsLinkMaterial.getContent());
			friendsLinkMaterial.setMaterialType(updateFriendsLinkMaterial.getMaterialType());
			friendsLinkMaterial.setImageUrl(updateFriendsLinkMaterial.getImageUrl());
			friendsLinkMaterial.setLinkUrl(updateFriendsLinkMaterial.getLinkUrl());
			friendsLinkMaterial.setAutoComment(updateFriendsLinkMaterial.getAutoComment());
			friendsLinkMaterial.setCommentContent(updateFriendsLinkMaterial.getCommentContent());
			friendsLinkMaterial.setDeleteFlag(updateFriendsLinkMaterial.getDeleteFlag());
			friendsLinkMaterial.setCreateId(updateFriendsLinkMaterial.getCreateId());
			friendsLinkMaterial.setCreateDate(new Date());
			friendsLinkMaterial.setRemark(updateFriendsLinkMaterial.getRemark());
			friendsLinkMaterial.setRemark2(updateFriendsLinkMaterial.getRemark2());
			friendsLinkMaterial.setRemark3(updateFriendsLinkMaterial.getRemark3());
			friendsLinkMaterial.setRemark4(updateFriendsLinkMaterial.getRemark4());
			friendsLinkMaterial.setShareTitle(updateFriendsLinkMaterial.getShareTitle());
			AssertUtils.notUpdateMoreThanOne(friendsLinkMaterialDao.updateByPrimaryKeySelective(friendsLinkMaterial));
			UpdateFriendsLinkMaterialReturn updateFriendsLinkMaterialReturn = new UpdateFriendsLinkMaterialReturn();

			logger.debug("updateFriendsLinkMaterial(UpdateFriendsLinkMaterial) - end - return value={}", updateFriendsLinkMaterialReturn); 
			return updateFriendsLinkMaterialReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("朋友圈链接素材信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_LINK_MATERIAL_UPDATE_ERROR,"朋友圈链接素材信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindFriendsLinkMaterialReturn findFriendsLinkMaterial(
			FindFriendsLinkMaterial findFriendsLinkMaterial) throws TsfaServiceException {
		logger.debug("findFriendsLinkMaterial(FindFriendsLinkMaterial findFriendsLinkMaterial={}) - start", findFriendsLinkMaterial); 

		AssertUtils.notNull(findFriendsLinkMaterial);
		AssertUtils.notAllNull(findFriendsLinkMaterial.getCode(),"CODE不能为空");
		try {
			FriendsLinkMaterial friendsLinkMaterial = friendsLinkMaterialDao.selectByPrimaryKey(findFriendsLinkMaterial.getCode());
			if(friendsLinkMaterial == null){
				throw new TsfaServiceException(ErrorCode.FRIENDS_LINK_MATERIAL_NOT_EXIST_ERROR,"朋友圈链接素材信息不存在");
			}
			FindFriendsLinkMaterialReturn findFriendsLinkMaterialReturn = new FindFriendsLinkMaterialReturn();
			//find数据录入
			findFriendsLinkMaterialReturn.setCode(friendsLinkMaterial.getCode());
			findFriendsLinkMaterialReturn.setMerchantNo(friendsLinkMaterial.getMerchantNo());
			findFriendsLinkMaterialReturn.setTitle(friendsLinkMaterial.getTitle());
			findFriendsLinkMaterialReturn.setContent(friendsLinkMaterial.getContent());
			findFriendsLinkMaterialReturn.setMaterialType(friendsLinkMaterial.getMaterialType());
			findFriendsLinkMaterialReturn.setImageUrl(friendsLinkMaterial.getImageUrl());
			findFriendsLinkMaterialReturn.setLinkUrl(friendsLinkMaterial.getLinkUrl());
			findFriendsLinkMaterialReturn.setAutoComment(friendsLinkMaterial.getAutoComment());
			findFriendsLinkMaterialReturn.setCommentContent(friendsLinkMaterial.getCommentContent());
			findFriendsLinkMaterialReturn.setDeleteFlag(friendsLinkMaterial.getDeleteFlag());
			findFriendsLinkMaterialReturn.setCreateId(friendsLinkMaterial.getCreateId());
			findFriendsLinkMaterialReturn.setCreateDate(friendsLinkMaterial.getCreateDate());
			findFriendsLinkMaterialReturn.setRemark(friendsLinkMaterial.getRemark());
			findFriendsLinkMaterialReturn.setRemark2(friendsLinkMaterial.getRemark2());
			findFriendsLinkMaterialReturn.setRemark3(friendsLinkMaterial.getRemark3());
			findFriendsLinkMaterialReturn.setRemark4(friendsLinkMaterial.getRemark4());
			findFriendsLinkMaterialReturn.setShareTitle(friendsLinkMaterial.getShareTitle());
			
			logger.debug("findFriendsLinkMaterial(FindFriendsLinkMaterial) - end - return value={}", findFriendsLinkMaterialReturn); 
			return findFriendsLinkMaterialReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找朋友圈链接素材信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_LINK_MATERIAL_FIND_ERROR,"查找朋友圈链接素材信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindFriendsLinkMaterialPageReturn> findFriendsLinkMaterialPage(
			FindFriendsLinkMaterialPage findFriendsLinkMaterialPage)
			throws TsfaServiceException {
		logger.debug("findFriendsLinkMaterialPage(FindFriendsLinkMaterialPage findFriendsLinkMaterialPage={}) - start", findFriendsLinkMaterialPage); 

		AssertUtils.notNull(findFriendsLinkMaterialPage);
		List<FindFriendsLinkMaterialPageReturn> returnList;
		int count = 0;
		try {
			returnList = friendsLinkMaterialDao.findFriendsLinkMaterialPage(findFriendsLinkMaterialPage);
			//处理老的链接素材没有分享标题的情况
			for (FindFriendsLinkMaterialPageReturn findFriendsLinkMaterialPageReturn : returnList) {
			    if (StringUtils.isEmpty(findFriendsLinkMaterialPageReturn.getShareTitle())) {//老的链接素材remark为空，取content的前30个字符
                    String shareTitle = findFriendsLinkMaterialPageReturn.getContent().length() > 30 ? findFriendsLinkMaterialPageReturn.getContent().substring(0, 30) : findFriendsLinkMaterialPageReturn.getContent();
                    findFriendsLinkMaterialPageReturn.setShareTitle(shareTitle);
                }
            }
			count = friendsLinkMaterialDao.findFriendsLinkMaterialPageCount(findFriendsLinkMaterialPage);
		}  catch (Exception e) {
			logger.error("朋友圈链接素材信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.FRIENDS_LINK_MATERIAL_FIND_PAGE_ERROR,"朋友圈链接素材信息不存在错误.！",e);
		}
		Page<FindFriendsLinkMaterialPageReturn> returnPage = new Page<FindFriendsLinkMaterialPageReturn>(returnList, count, findFriendsLinkMaterialPage);

		logger.debug("findFriendsLinkMaterialPage(FindFriendsLinkMaterialPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
