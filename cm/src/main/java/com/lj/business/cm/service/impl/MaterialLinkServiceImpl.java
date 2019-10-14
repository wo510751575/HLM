package com.lj.business.cm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IMaterialLinkDao;
import com.lj.business.cm.dto.FindMaterialLinkPage;
import com.lj.business.cm.dto.FindMaterialLinkPageReturn;
import com.lj.business.cm.service.IMaterialLinkService;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月26日上午10:10:36
 */
@Service
public class MaterialLinkServiceImpl implements IMaterialLinkService {

	private static final Logger logger = LoggerFactory.getLogger(MaterialLinkServiceImpl.class);
	
	@Resource
	private IMaterialLinkDao materialLinkDao;
	
	
	
	@Override
	public Page<FindMaterialLinkPageReturn> findLinkInfoPage(FindMaterialLinkPage findMaterialLinkPage) {
		logger.debug("findLinkInfoPage(FindMaterialLinkPage findMaterialLinkPage = {})", findMaterialLinkPage);
		int count = 0;
		List<FindMaterialLinkPageReturn> returnList = new ArrayList<>();
		try {
			returnList = materialLinkDao.findMaterialLinkPage(findMaterialLinkPage);
			count = materialLinkDao.findMaterialLinkPageCount(findMaterialLinkPage);
		}  catch (Exception e) {
			logger.error("查询文本库错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"查询文本库错误！",e);
		}
		Page<FindMaterialLinkPageReturn> returnPage = new Page<FindMaterialLinkPageReturn>(returnList, count, findMaterialLinkPage);
		return returnPage;
	}



	@Override
	public void add(FindMaterialLinkPage findMaterialLinkPage) {
		logger.debug("add(FindMaterialLinkPage findMaterialLinkPage = {})", findMaterialLinkPage);
		try {
			materialLinkDao.add(findMaterialLinkPage);
		} catch (Exception e) {
			logger.error("保存链接错误!", e);
		}
	}



	@Override
	public void update(FindMaterialLinkPage findMaterialLinkPage) {
		logger.debug("update(FindMaterialLinkPage findMaterialLinkPage = {})", findMaterialLinkPage);
		try {
			materialLinkDao.update(findMaterialLinkPage);
		} catch (Exception e) {
			logger.error("更新链接错误!", e);
		}
		
	}



	@Override
	public void delete(FindMaterialLinkPage findMaterialLinkPage) {
		logger.debug("delete(FindMaterialLinkPage findMaterialLinkPage = {})", findMaterialLinkPage);
		try {
			materialLinkDao.delete(findMaterialLinkPage);
		} catch (Exception e) {
			logger.error("删除链接错误!", e);
		}
	}

}
