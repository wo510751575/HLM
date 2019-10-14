package com.lj.business.member.service.impl;

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
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IGuidMemberRwDao;
import com.lj.business.member.domain.GuidMemberRw;
import com.lj.business.member.dto.FindGuidMemberRwPage;
import com.lj.business.member.dto.GuidMemberRwDto;
import com.lj.business.member.service.IGuidMemberRwService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author sjiying
 * 
 * 
 * CreateDate: 2019.04.18
 */
@Service
public class GuidMemberRwServiceImpl implements IGuidMemberRwService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GuidMemberRwServiceImpl.class);

	@Resource
	private IGuidMemberRwDao guidMemberRwDao;

	@Override
	public String addGuidMemberRw(GuidMemberRwDto guidMemberRwDto) throws TsfaServiceException {
		logger.debug("addGuidMemberRw(AddGuidMemberRw addGuidMemberRw={}) - start", guidMemberRwDto);

		AssertUtils.notNull(guidMemberRwDto);
		try {
			GuidMemberRw guidMemberRw = new GuidMemberRw();
			// add数据录入
			guidMemberRw.setCode(guidMemberRwDto.getCode());
			guidMemberRw.setMerchantNo(guidMemberRwDto.getMerchantNo());
			guidMemberRw.setLoginName(guidMemberRwDto.getLoginName());
			guidMemberRw.setMemberName(guidMemberRwDto.getMemberName());
			guidMemberRw.setOpenLevelDate(guidMemberRwDto.getOpenLevelDate());
			guidMemberRw.setEndLevelDate(guidMemberRwDto.getEndLevelDate());
			guidMemberRw.setUserLevel(guidMemberRwDto.getUserLevel());
			guidMemberRw.setCreateTime(guidMemberRwDto.getCreateTime());
			guidMemberRw.setUpdateTime(guidMemberRwDto.getUpdateTime());
			guidMemberRw.setBirthDate(guidMemberRwDto.getBirthDate());
			guidMemberRw.setUserLike(guidMemberRwDto.getUserLike());

//			guidMemberRw.setCode(GUID.getPreUUID());
			guidMemberRwDao.insertSelective(guidMemberRw);
			logger.debug("addGuidMemberRw(GuidMemberRwDto) - end - return");
			return guidMemberRw.getCode();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购扩展热文用户记录信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_RW_ADD_ERROR, "新增导购扩展热文用户记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询导购扩展热文用户记录信息
	 *
	 * @param findGuidMemberRwPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<GuidMemberRwDto> findGuidMemberRws(FindGuidMemberRwPage findGuidMemberRwPage) throws TsfaServiceException {
		AssertUtils.notNull(findGuidMemberRwPage);
		List<GuidMemberRwDto> returnList = null;
		try {
			returnList = guidMemberRwDao.findGuidMemberRws(findGuidMemberRwPage);
		} catch (Exception e) {
			logger.error("查找导购扩展热文用户记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_RW_NOT_EXIST_ERROR, "导购扩展热文用户记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateGuidMemberRw(GuidMemberRwDto guidMemberRwDto) throws TsfaServiceException {
		logger.debug("updateGuidMemberRw(GuidMemberRwDto guidMemberRwDto={}) - start", guidMemberRwDto);

		AssertUtils.notNull(guidMemberRwDto);
		AssertUtils.notNullAndEmpty(guidMemberRwDto.getCode(), "Code不能为空");
		try {
			GuidMemberRw guidMemberRw = new GuidMemberRw();
			// update数据录入
			guidMemberRw.setCode(guidMemberRwDto.getCode());
			guidMemberRw.setMerchantNo(guidMemberRwDto.getMerchantNo());
			guidMemberRw.setLoginName(guidMemberRwDto.getLoginName());
			guidMemberRw.setMemberName(guidMemberRwDto.getMemberName());
			guidMemberRw.setOpenLevelDate(guidMemberRwDto.getOpenLevelDate());
			guidMemberRw.setEndLevelDate(guidMemberRwDto.getEndLevelDate());
			guidMemberRw.setUserLevel(guidMemberRwDto.getUserLevel());
			guidMemberRw.setCreateTime(guidMemberRwDto.getCreateTime());
			guidMemberRw.setUpdateTime(guidMemberRwDto.getUpdateTime());
			guidMemberRw.setBirthDate(guidMemberRwDto.getBirthDate());
			guidMemberRw.setUserLike(guidMemberRwDto.getUserLike());

			if (guidMemberRwDto.isHasUpdateAll()) {
				AssertUtils.notUpdateMoreThanOne(guidMemberRwDao.updateByPrimaryKey(guidMemberRw));
			} else {
				AssertUtils.notUpdateMoreThanOne(guidMemberRwDao.updateByPrimaryKeySelective(guidMemberRw));
			}

			logger.debug("updateGuidMemberRw(GuidMemberRwDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购扩展热文用户记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_RW_UPDATE_ERROR, "导购扩展热文用户记录信息更新信息错误！", e);
		}
	}

	@Override
	public GuidMemberRwDto findGuidMemberRw(GuidMemberRwDto guidMemberRwDto) throws TsfaServiceException {
		logger.debug("findGuidMemberRw(FindGuidMemberRw findGuidMemberRw={}) - start", guidMemberRwDto);

		AssertUtils.notNull(guidMemberRwDto);
		AssertUtils.notAllNull(guidMemberRwDto.getCode(), "Code不能为空");
		try {
			GuidMemberRw guidMemberRw = guidMemberRwDao.selectByPrimaryKey(guidMemberRwDto.getCode());
			if (guidMemberRw == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.GUID_MEMBER_RW_NOT_EXIST_ERROR,"导购扩展热文用户记录信息不存在");
			}
			GuidMemberRwDto findGuidMemberRwReturn = new GuidMemberRwDto();
			// find数据录入
			findGuidMemberRwReturn.setCode(guidMemberRw.getCode());
			findGuidMemberRwReturn.setMemberName(guidMemberRw.getMemberName());
			findGuidMemberRwReturn.setLoginName(guidMemberRw.getLoginName());
			findGuidMemberRwReturn.setOpenLevelDate(guidMemberRw.getOpenLevelDate());
			findGuidMemberRwReturn.setEndLevelDate(guidMemberRw.getEndLevelDate());
			findGuidMemberRwReturn.setUserLevel(guidMemberRw.getUserLevel());
			findGuidMemberRwReturn.setCreateTime(guidMemberRw.getCreateTime());
			findGuidMemberRwReturn.setUpdateTime(guidMemberRw.getUpdateTime());
			findGuidMemberRwReturn.setBirthDate(guidMemberRw.getBirthDate());
			findGuidMemberRwReturn.setUserLike(guidMemberRw.getUserLike());
			findGuidMemberRwReturn.setMerchantNo(guidMemberRw.getMerchantNo());

			logger.debug("findGuidMemberRw(GuidMemberRwDto) - end - return value={}", findGuidMemberRwReturn);
			return findGuidMemberRwReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购扩展热文用户记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_RW_FIND_ERROR, "查找导购扩展热文用户记录信息信息错误！", e);
		}

	}

	@Override
	public Page<GuidMemberRwDto> findGuidMemberRwPage(FindGuidMemberRwPage findGuidMemberRwPage) throws TsfaServiceException {
		logger.debug("findGuidMemberRwPage(FindGuidMemberRwPage findGuidMemberRwPage={}) - start", findGuidMemberRwPage);

		AssertUtils.notNull(findGuidMemberRwPage);
		List<GuidMemberRwDto> returnList = null;
		int count = 0;
		try {
			returnList = guidMemberRwDao.findGuidMemberRwPage(findGuidMemberRwPage);
			count = guidMemberRwDao.findGuidMemberRwPageCount(findGuidMemberRwPage);
		} catch (Exception e) {
			logger.error("导购扩展热文用户记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_RW_FIND_PAGE_ERROR, "导购扩展热文用户记录信息不存在错误.！", e);
		}
		Page<GuidMemberRwDto> returnPage = new Page<GuidMemberRwDto>(returnList, count, findGuidMemberRwPage);

		logger.debug("findGuidMemberRwPage(FindGuidMemberRwPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(guidMemberRwDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购扩展热文用户记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUID_MEMBER_RW_UPDATE_ERROR, "导购扩展热文用户记录信息刪除信息错误！", e);
		}
	}

}
