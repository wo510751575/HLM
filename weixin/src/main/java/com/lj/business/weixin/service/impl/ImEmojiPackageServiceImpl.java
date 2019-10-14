package com.lj.business.weixin.service.impl;

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
import com.lj.business.weixin.constant.ErrorCode;
import com.lj.business.weixin.dao.IImEmojiPackageDao;
import com.lj.business.weixin.domain.ImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.AddImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.AddImEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.DelImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.DelImEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePageReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.FindNewEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.NewEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.UpdateImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.UpdateImEmojiPackageReturn;
import com.lj.business.weixin.service.IImEmojiPackageService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
@Service
public class ImEmojiPackageServiceImpl implements IImEmojiPackageService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ImEmojiPackageServiceImpl.class);
	

	@Resource
	private IImEmojiPackageDao imEmojiPackageDao;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	@Override
	public AddImEmojiPackageReturn addImEmojiPackage(
			AddImEmojiPackage addImEmojiPackage) throws TsfaServiceException {
		logger.debug("addImEmojiPackage(AddImEmojiPackage addImEmojiPackage={}) - start", addImEmojiPackage); 

		AssertUtils.notNull(addImEmojiPackage);
		try {
			ImEmojiPackage imEmojiPackage = new ImEmojiPackage();
			//add数据录入
			imEmojiPackage.setCode(GUID.generateByUUID());
			imEmojiPackage.setPackageName(addImEmojiPackage.getPackageName());
			imEmojiPackage.setPackageLogo(addImEmojiPackage.getPackageLogo());
			imEmojiPackage.setVersion(System.currentTimeMillis());
			imEmojiPackage.setStatus(addImEmojiPackage.getStatus());
			imEmojiPackage.setShowIndex(addImEmojiPackage.getShowIndex());
			imEmojiPackage.setCreateId(addImEmojiPackage.getCreateId());
			imEmojiPackage.setCreateDate(new Date());
			imEmojiPackage.setRemark(addImEmojiPackage.getRemark());
			imEmojiPackage.setRemark2(addImEmojiPackage.getRemark2());
			imEmojiPackage.setRemark3(addImEmojiPackage.getRemark3());
			imEmojiPackage.setRemark4(addImEmojiPackage.getRemark4());
			imEmojiPackageDao.insert(imEmojiPackage);
			AddImEmojiPackageReturn addImEmojiPackageReturn = new AddImEmojiPackageReturn();
			
			logger.debug("addImEmojiPackage(AddImEmojiPackage) - end - return value={}", addImEmojiPackageReturn); 
			return addImEmojiPackageReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增IM表情包信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_PACKAGE_ADD_ERROR,"新增IM表情包信息错误！",e);
		}
	}
	
	
	@Override
	public DelImEmojiPackageReturn delImEmojiPackage(
			DelImEmojiPackage delImEmojiPackage) throws TsfaServiceException {
		logger.debug("delImEmojiPackage(DelImEmojiPackage delImEmojiPackage={}) - start", delImEmojiPackage); 

		AssertUtils.notNull(delImEmojiPackage);
		AssertUtils.notNull(delImEmojiPackage.getCode(),"ID不能为空！");
		try {
			imEmojiPackageDao.deleteByPrimaryKey(delImEmojiPackage.getCode());
			DelImEmojiPackageReturn delImEmojiPackageReturn  = new DelImEmojiPackageReturn();

			logger.debug("delImEmojiPackage(DelImEmojiPackage) - end - return value={}", delImEmojiPackageReturn); 
			return delImEmojiPackageReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除IM表情包信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_PACKAGE_DEL_ERROR,"删除IM表情包信息错误！",e);

		}
	}

	@Override
	public UpdateImEmojiPackageReturn updateImEmojiPackage(
			UpdateImEmojiPackage updateImEmojiPackage)
			throws TsfaServiceException {
		logger.debug("updateImEmojiPackage(UpdateImEmojiPackage updateImEmojiPackage={}) - start", updateImEmojiPackage); 

		AssertUtils.notNull(updateImEmojiPackage);
		AssertUtils.notNullAndEmpty(updateImEmojiPackage.getCode(),"ID不能为空");
		try {
			ImEmojiPackage imEmojiPackage = new ImEmojiPackage();
			//update数据录入
			imEmojiPackage.setCode(updateImEmojiPackage.getCode());
			imEmojiPackage.setPackageName(updateImEmojiPackage.getPackageName());
			imEmojiPackage.setPackageLogo(updateImEmojiPackage.getPackageLogo());
			imEmojiPackage.setVersion(System.currentTimeMillis());
			imEmojiPackage.setStatus(updateImEmojiPackage.getStatus());
			imEmojiPackage.setShowIndex(updateImEmojiPackage.getShowIndex());
			imEmojiPackage.setCreateId(updateImEmojiPackage.getCreateId());
			imEmojiPackage.setCreateDate(updateImEmojiPackage.getCreateDate());
			imEmojiPackage.setRemark(updateImEmojiPackage.getRemark());
			imEmojiPackage.setRemark2(updateImEmojiPackage.getRemark2());
			imEmojiPackage.setRemark3(updateImEmojiPackage.getRemark3());
			imEmojiPackage.setRemark4(updateImEmojiPackage.getRemark4());
			AssertUtils.notUpdateMoreThanOne(imEmojiPackageDao.updateByPrimaryKeySelective(imEmojiPackage));
			UpdateImEmojiPackageReturn updateImEmojiPackageReturn = new UpdateImEmojiPackageReturn();

			logger.debug("updateImEmojiPackage(UpdateImEmojiPackage) - end - return value={}", updateImEmojiPackageReturn); 
			return updateImEmojiPackageReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("IM表情包信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_PACKAGE_UPDATE_ERROR,"IM表情包信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindImEmojiPackageReturn findImEmojiPackage(
			FindImEmojiPackage findImEmojiPackage) throws TsfaServiceException {
		logger.debug("findImEmojiPackage(FindImEmojiPackage findImEmojiPackage={}) - start", findImEmojiPackage); 

		AssertUtils.notNull(findImEmojiPackage);
		AssertUtils.notNullAndEmpty(findImEmojiPackage.getCode(),"ID不能为空");
		try {
			ImEmojiPackage imEmojiPackage = imEmojiPackageDao.selectByPrimaryKey(findImEmojiPackage.getCode());
			if(imEmojiPackage == null){
				throw new TsfaServiceException(ErrorCode.IM_EMOJI_PACKAGE_NOT_EXIST_ERROR,"IM表情包信息不存在");
			}
			FindImEmojiPackageReturn findImEmojiPackageReturn = new FindImEmojiPackageReturn();
			//find数据录入
			findImEmojiPackageReturn.setCode(imEmojiPackage.getCode());
			findImEmojiPackageReturn.setPackageName(imEmojiPackage.getPackageName());
			findImEmojiPackageReturn.setPackageLogo(imEmojiPackage.getPackageLogo());
			findImEmojiPackageReturn.setVersion(imEmojiPackage.getVersion());
			findImEmojiPackageReturn.setStatus(imEmojiPackage.getStatus());
			findImEmojiPackageReturn.setShowIndex(imEmojiPackage.getShowIndex());
			findImEmojiPackageReturn.setCreateId(imEmojiPackage.getCreateId());
			findImEmojiPackageReturn.setCreateDate(imEmojiPackage.getCreateDate());
			findImEmojiPackageReturn.setRemark(imEmojiPackage.getRemark());
			findImEmojiPackageReturn.setRemark2(imEmojiPackage.getRemark2());
			findImEmojiPackageReturn.setRemark3(imEmojiPackage.getRemark3());
			findImEmojiPackageReturn.setRemark4(imEmojiPackage.getRemark4());
			
			logger.debug("findImEmojiPackage(FindImEmojiPackage) - end - return value={}", findImEmojiPackageReturn); 
			return findImEmojiPackageReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找IM表情包信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_PACKAGE_FIND_ERROR,"查找IM表情包信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindImEmojiPackagePageReturn> findImEmojiPackagePage(
			FindImEmojiPackagePage findImEmojiPackagePage)
			throws TsfaServiceException {
		logger.debug("findImEmojiPackagePage(FindImEmojiPackagePage findImEmojiPackagePage={}) - start", findImEmojiPackagePage); 

		AssertUtils.notNull(findImEmojiPackagePage);
		List<FindImEmojiPackagePageReturn> returnList;
		int count = 0;
		try {
			returnList = imEmojiPackageDao.findImEmojiPackagePage(findImEmojiPackagePage);
			count = imEmojiPackageDao.findImEmojiPackagePageCount(findImEmojiPackagePage);
		}  catch (Exception e) {
			logger.error("IM表情包信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_PACKAGE_FIND_PAGE_ERROR,"IM表情包信息不存在错误.！",e);
		}
		Page<FindImEmojiPackagePageReturn> returnPage = new Page<FindImEmojiPackagePageReturn>(returnList, count, findImEmojiPackagePage);

		logger.debug("findImEmojiPackagePage(FindImEmojiPackagePage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public FindNewEmojiPackageReturn findNewEmojiPackage(FindImEmojiPackage findImEmojiPackage) throws TsfaServiceException {
		logger.debug("findNewEmojiPackage(FindImEmojiPackage findImEmojiPackage={}) - start", findImEmojiPackage); 

		AssertUtils.notNull(findImEmojiPackage);
		AssertUtils.notNullAndEmpty(findImEmojiPackage.getVersion(),"版本号不能为空");
		List<NewEmojiPackageReturn> packageList=null;
		try {
			FindNewEmojiPackageReturn findNewEmojiPackageReturn = new FindNewEmojiPackageReturn();
			//APP查找新version表情包
			packageList=imEmojiPackageDao.findNewEmojiPackage(findImEmojiPackage.getVersion());
			if(packageList != null&&packageList.size()>0){
				findNewEmojiPackageReturn.setPackageList(packageList);
				logger.debug("表情包数:"+packageList.size());
			}else{
				logger.debug("暂无新表情包!");
			}
			//查找访问域名
			String accessUrl = localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl");
			findNewEmojiPackageReturn.setAccessUrl(accessUrl);
			
			logger.debug("findNewEmojiPackage(FindImEmojiPackage) - end - return value={}", findNewEmojiPackageReturn); 
			return findNewEmojiPackageReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找APP表情包信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_PACKAGE_FIND_ERROR,"查找APP表情包信息信息错误！",e);
		}

	}
	
	@Override
	public List<NewEmojiPackageReturn> findImWebEmojiPackage(FindImEmojiPackage findImEmojiPackage) throws TsfaServiceException {
		logger.debug("findImWebEmojiPackage(FindImEmojiPackage findImEmojiPackage={}) - start", findImEmojiPackage); 
		
		AssertUtils.notNull(findImEmojiPackage);
		List<NewEmojiPackageReturn> packageList=null;
		try {
			findImEmojiPackage.setVersion(0L);//全量查询
			packageList=imEmojiPackageDao.findNewEmojiPackage(findImEmojiPackage.getVersion());
			
			logger.debug("findImWebEmojiPackage(FindImEmojiPackage findImEmojiPackage) - end - return value={}", packageList); 
			return packageList;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找IM_WEB表情包信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_PACKAGE_FIND_ERROR,"查找IM_WEB表情包信息信息错误！",e);
		}
		
	}

	@Override
	public long findMaxVersion() throws TsfaServiceException {
		try {
			return imEmojiPackageDao.findMaxVersion();
		} catch(Exception e) {
			logger.error("查询表情包最大版本号错误", e);
			throw new TsfaServiceException(ErrorCode.IM_EMOJI_PACKAGE_FIND_ERROR,"查询表情包最大版本号错误",e);
		}
	}

	@Override
	public List<Integer> findAllShowIndex() {
		return imEmojiPackageDao.findAllShowIndex();
	} 

}
