package com.lj.business.cm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IMaterialPhotoDao;
import com.lj.business.cm.dto.AddMaterialPhoto;
import com.lj.business.cm.dto.wordsType.FindMaterialPhotoPage;
import com.lj.business.cm.service.IMaterialPhotoService;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月22日下午2:51:10
 */
@Service
public class MaterialPhotoServiceImpl implements IMaterialPhotoService{

	private static final Logger logger = LoggerFactory.getLogger(MaterialPhotoServiceImpl.class);
	
	@Resource
	private IMaterialPhotoDao materialPhotoDao;

	@Override
	public Page<FindMaterialPhotoPage> findMaterialPhotoPage(FindMaterialPhotoPage findMaterialPhotoPage) {
		logger.debug("findMaterialVideoPage(FindMaterialPhotoPage findMaterialPhotoPage={}) - start", findMaterialPhotoPage);
		try {
			List<FindMaterialPhotoPage> list = materialPhotoDao.findMaterialPhotoPage(findMaterialPhotoPage);
			int count = materialPhotoDao.findMaterialPhotoCount(findMaterialPhotoPage);
			Page<FindMaterialPhotoPage> pages = new Page<FindMaterialPhotoPage>(list, count, findMaterialPhotoPage);
			return pages;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询图片库列表错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"查询图片库列表错误！",e);
		}
	}

	
	@Override
	public void addMaterialPhoto(AddMaterialPhoto addMaterialPhoto) {
		logger.debug("addMaterialPhoto(addMaterialPhoto(AddMaterialPhoto addMaterialPhoto={})) -start",addMaterialPhoto);
		try {
			materialPhotoDao.addMaterialVideo(addMaterialPhoto);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			logger.error("保存错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"保存错误！",e);
		}
		
	}


	@Override
	public void delete(AddMaterialPhoto addMaterialPhoto) {
		logger.debug("delete(AddMaterialPhoto addMaterialPhoto = {})", addMaterialPhoto);
		try {
			materialPhotoDao.delete(addMaterialPhoto);
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
			String parentIds = materialPhotoDao.selectParentIds(parentId);
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
		logger.debug("getCount(String parentId)", parentId);
		try {
			int count = materialPhotoDao.getCount(parentId);
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
	public void updateCount(AddMaterialPhoto parent) {
		logger.debug("updateCount(AddMaterialVideo parent = {})", parent);
		try {
			materialPhotoDao.updateCount(parent);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			logger.error("更新数量错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"更新数量错误！",e);
		}
		
	}


	@Override
	public List<Map<String, Object>> findChild(String parentId) throws TsfaServiceException {
		logger.debug("findChild(String parentId = {})", parentId);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list =materialPhotoDao.findChild(parentId);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			logger.error("更新数量错误！",e);
			throw new TsfaServiceException(ErrorCode.WORDS_TYPE_ADD_ERROR,"更新数量错误！",e);
		}
		return list;
	}
	
}
