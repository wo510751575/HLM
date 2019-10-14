package com.lj.business.member.service.impl;

import java.util.Date;
/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IGmLabelDao;
import com.lj.business.member.domain.GmLabel;
import com.lj.business.member.dto.FindGmLabelPage;
import com.lj.business.member.dto.GmLabelDto;
import com.lj.business.member.service.IGmLabelService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 *         CreateDate: 2017.12.14
 */
@Service
public class GmLabelServiceImpl implements IGmLabelService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GmLabelServiceImpl.class);

	@Resource
	private IGmLabelDao gmLabelDao;

	@Override
	public GmLabelDto addGmLabel(GmLabelDto gmLabelDto) throws TsfaServiceException {
		logger.debug("addGmLabel(AddGmLabel addGmLabel={}) - start", gmLabelDto);

		AssertUtils.notNull(gmLabelDto);
		try {
			GmLabel gmLabel = new GmLabel();
			// add数据录入
			gmLabel.setCode(GUID.generateCode());
			gmLabel.setMerchantNo(gmLabelDto.getMerchantNo());
			gmLabel.setLabelName(gmLabelDto.getLabelName());
			gmLabel.setCreateId(gmLabelDto.getCreateId());
			gmLabel.setCreateDate(new Date());
			gmLabel.setRemark(gmLabelDto.getRemark());
			gmLabel.setRemark2(gmLabelDto.getRemark2());
			gmLabel.setRemark3(gmLabelDto.getRemark3());
			gmLabel.setRemark4(gmLabelDto.getRemark4());
			gmLabel.setMemberNoGm(gmLabelDto.getMemberNoGm());
			gmLabel.setMemberNameGm(gmLabelDto.getMemberNameGm());
			gmLabelDao.insertSelective(gmLabel);

			GmLabelDto returnDto = new GmLabelDto();
			returnDto.setCode(gmLabel.getCode());
			logger.debug("addGmLabel(GmLabelDto) - end - return");
			return returnDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增个人标签信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GM_LABEL_ADD_ERROR, "新增个人标签信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询个人标签信息
	 *
	 * @param findGmLabelPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<GmLabelDto> findGmLabels(FindGmLabelPage findGmLabelPage) throws TsfaServiceException {
		AssertUtils.notNull(findGmLabelPage);
		List<GmLabelDto> returnList = null;
		try {
			returnList = gmLabelDao.findGmLabels(findGmLabelPage);
		} catch (Exception e) {
			logger.error("查找个人标签信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GM_LABEL_NOT_EXIST_ERROR, "个人标签信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateGmLabel(GmLabelDto gmLabelDto) throws TsfaServiceException {
		logger.debug("updateGmLabel(GmLabelDto gmLabelDto={}) - start", gmLabelDto);

		AssertUtils.notNull(gmLabelDto);
		AssertUtils.notNullAndEmpty(gmLabelDto.getCode(), "Code不能为空");
		try {
			GmLabel gmLabel = new GmLabel();
			// update数据录入
			gmLabel.setCode(gmLabelDto.getCode());
			gmLabel.setMerchantNo(gmLabelDto.getMerchantNo());
			gmLabel.setLabelName(gmLabelDto.getLabelName());
			gmLabel.setRemark(gmLabelDto.getRemark());
			gmLabel.setRemark2(gmLabelDto.getRemark2());
			gmLabel.setRemark3(gmLabelDto.getRemark3());
			gmLabel.setRemark4(gmLabelDto.getRemark4());
			gmLabel.setUpdateTime(new Date());
			gmLabel.setMemberNoGm(gmLabelDto.getMemberNoGm());
			gmLabel.setMemberNameGm(gmLabelDto.getMemberNameGm());
			AssertUtils.notUpdateMoreThanOne(gmLabelDao.updateByPrimaryKeySelective(gmLabel));
			logger.debug("updateGmLabel(GmLabelDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("个人标签信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GM_LABEL_UPDATE_ERROR, "个人标签信息更新信息错误！", e);
		}
	}

	@Override
	public GmLabelDto findGmLabel(GmLabelDto gmLabelDto) throws TsfaServiceException {
		logger.debug("findGmLabel(FindGmLabel findGmLabel={}) - start", gmLabelDto);

		AssertUtils.notNull(gmLabelDto);
		AssertUtils.notAllNull(gmLabelDto.getCode(), "Code不能为空");
		try {
			GmLabel gmLabel = gmLabelDao.selectByPrimaryKey(gmLabelDto.getCode());
			if (gmLabel == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCode.GM_LABEL_NOT_EXIST_ERROR,"个人标签信息不存在");
			}
			GmLabelDto findGmLabelReturn = new GmLabelDto();
			// find数据录入
			findGmLabelReturn.setCode(gmLabel.getCode());
			findGmLabelReturn.setMerchantNo(gmLabel.getMerchantNo());
			findGmLabelReturn.setLabelName(gmLabel.getLabelName());
			findGmLabelReturn.setCreateId(gmLabel.getCreateId());
			findGmLabelReturn.setCreateDate(gmLabel.getCreateDate());
			findGmLabelReturn.setRemark(gmLabel.getRemark());
			findGmLabelReturn.setRemark2(gmLabel.getRemark2());
			findGmLabelReturn.setRemark3(gmLabel.getRemark3());
			findGmLabelReturn.setRemark4(gmLabel.getRemark4());
			findGmLabelReturn.setUpdateTime(gmLabel.getUpdateTime());
			findGmLabelReturn.setMemberNoGm(gmLabel.getMemberNoGm());
			findGmLabelReturn.setMemberNameGm(gmLabel.getMemberNameGm());

			logger.debug("findGmLabel(GmLabelDto) - end - return value={}", findGmLabelReturn);
			return findGmLabelReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找个人标签信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GM_LABEL_FIND_ERROR, "查找个人标签信息信息错误！", e);
		}

	}

	@Override
	public Page<GmLabelDto> findGmLabelPage(FindGmLabelPage findGmLabelPage) throws TsfaServiceException {
		logger.debug("findGmLabelPage(FindGmLabelPage findGmLabelPage={}) - start", findGmLabelPage);

		AssertUtils.notNull(findGmLabelPage);
		List<GmLabelDto> returnList = null;
		int count = 0;
		try {
			count = gmLabelDao.findGmLabelPageCount(findGmLabelPage);
			if (count > 0) {
				returnList = gmLabelDao.findGmLabelPage(findGmLabelPage);
			}

		} catch (Exception e) {
			logger.error("个人标签信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.GM_LABEL_FIND_PAGE_ERROR, "个人标签信息不存在错误.！", e);
		}
		Page<GmLabelDto> returnPage = new Page<GmLabelDto>(returnList, count, findGmLabelPage);

		logger.debug("findGmLabelPage(FindGmLabelPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void delGmLabel(GmLabelDto gmLabelDto) throws TsfaServiceException {
		logger.debug("updateGmLabel(GmLabelDto gmLabelDto={}) - start", gmLabelDto);

		AssertUtils.notNull(gmLabelDto);
		AssertUtils.notNullAndEmpty(gmLabelDto.getCode(), "Code不能为空");
		try {
			AssertUtils.notUpdateMoreThanOne(gmLabelDao.deleteByPrimaryKey(gmLabelDto.getCode()));
			logger.debug("updateGmLabel(GmLabelDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("个人标签信息删除信息错误！", e);
			throw new TsfaServiceException("GM_LABEL_DEL_ERROR", "个人标签信息删除信息错误！", e);
		}
	}

}
