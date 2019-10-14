package com.lj.business.cm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IMaterialVideoDao;
import com.lj.business.cm.dto.AddMaterialVideo;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPage;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPageReturn;
import com.lj.business.cm.service.IMaterialVideoService;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月22日下午2:51:10
 */
@Service
public class MaterialVideoServiceImpl implements IMaterialVideoService{

	private static final Logger logger = LoggerFactory.getLogger(MaterialVideoServiceImpl.class);
	
	@Resource
	private IMaterialVideoDao materialVideoDao;
	
	@Override
	public Page<FindMaterialVideoPageReturn> findMaterialVideoPage(FindMaterialVideoPage page) throws TsfaServiceException {
		logger.debug("findMaterialVideoPage(FindMaterialVideoPage page={}) - start", page);
		try {
			List<FindMaterialVideoPageReturn> list = materialVideoDao.findMaterialVideoPage(page);
			int count = materialVideoDao.findMaterialVideoCount(page);
			Page<FindMaterialVideoPageReturn> pages = new Page<FindMaterialVideoPageReturn>(list, count, page);
			return pages;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询视频库列表错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"查询视频库列表错误！",e);
		}
	}

	@Override
	public void addMaterialVideo(AddMaterialVideo addMaterialVideo)throws TsfaServiceException {
		logger.debug("addMaterialVideo(addMaterialVideo(AddMaterial addMaterial={})) -start",addMaterialVideo);
		try {
			materialVideoDao.addMaterialVideo(addMaterialVideo);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			logger.error("保存错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"保存错误！",e);
		}
	}

	@Override
	public void delete(AddMaterialVideo materialVideo) {
		logger.debug("delete(AddMaterialVideo materialVideo = {})", materialVideo);
		try {
			materialVideoDao.delete(materialVideo);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			logger.error("删除错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"删除错误！",e);
		}
	}

	@Override
	public String selectParentIds(String parentId) {
		logger.debug("selectParentIds(String parentId = {})", parentId);
		try {
			String parentIds = materialVideoDao.selectParentIds(parentId);
			return parentIds;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			logger.error("删除错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"删除错误！",e);
		}
	}

	@Override
	public int getCount(String parentId) {
		logger.debug("getCount(String parentId = {})", parentId);
		try {
			int count = materialVideoDao.getCount(parentId);
			return count;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			logger.error("获取数量错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"获取数量错误！",e);
		}
	}

	@Override
	public void updateCount(AddMaterialVideo parent) {
		logger.debug("updateCount(AddMaterialVideo parent = {})", parent);
		try {
			materialVideoDao.updateCount(parent);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			logger.error("更新数量错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"更新数量错误！",e);
		}
	}

}
