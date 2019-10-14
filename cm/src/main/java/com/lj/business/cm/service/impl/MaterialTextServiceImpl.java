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
import com.lj.business.cm.dao.ImaterialTextDao;
import com.lj.business.cm.dto.FindMaterialTextPage;
import com.lj.business.cm.dto.FindMaterialTextPageReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPageReturn;
import com.lj.business.cm.service.IMaterialTextService;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月25日下午3:47:20
 */
@Service
public class MaterialTextServiceImpl implements IMaterialTextService {
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialTextServiceImpl.class);
	
	@Resource
	private ImaterialTextDao materialTextDao;
	
	@Override
	public Page<FindMaterialTextPageReturn> findWordsInfoPage(FindMaterialTextPage findMaterialTextPage) {
		logger.debug("findWordsInfoPage(FindMaterialTextPage findMaterialTextPage = {})", findMaterialTextPage);
		int count = 0;
		List<FindMaterialTextPageReturn> returnList = new ArrayList<>();
		try {
			returnList = materialTextDao.findMaterialTextPage(findMaterialTextPage);
			count = materialTextDao.findMaterialTextPageCount(findMaterialTextPage);
		}  catch (Exception e) {
			logger.error("查询文本库错误",e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"查询文本库错误！",e);
		}
		Page<FindMaterialTextPageReturn> returnPage = new Page<FindMaterialTextPageReturn>(returnList, count, findMaterialTextPage);
		return returnPage;
	}

	@Override
	public void addText(FindMaterialTextPage findMaterialTextPage) {
		logger.debug("addText(FindMaterialTextPage findMaterialTextPage = {})", findMaterialTextPage);
		try {
			materialTextDao.addText(findMaterialTextPage);
		} catch (Exception e) {
			logger.error("添加文本内容错误!", e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"添加文本内容错误!",e);
		}
	}

	@Override
	public void updateText(FindMaterialTextPage findMaterialTextPage) {
		logger.debug("updateText(FindMaterialTextPage findMaterialTextPage = {})", findMaterialTextPage);
		try {
			materialTextDao.updateText(findMaterialTextPage);
		} catch (Exception e) {
			logger.error("更新文本错误!", e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"更新文本错误!",e);
		}
	}

	@Override
	public void deleteText(FindMaterialTextPage findMaterialTextPage) {
		logger.debug("deleteText(FindMaterialTextPage findMaterialTextPage = {})", findMaterialTextPage);
		try {
			materialTextDao.deleteText(findMaterialTextPage);
		} catch (Exception e) {
			logger.error("删除文本错误!", e);
			throw new TsfaServiceException(ErrorCode.WORDS_INFO_FIND_PAGE_ERROR,"删除文本错误!",e);
		}
		
	}

}
