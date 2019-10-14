package com.ye.business.hx.service.impl;

import java.util.Date;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
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
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IFestivalPosterDao;
import com.ye.business.hx.domain.FestivalPoster;
import com.ye.business.hx.dto.FestivalPosterDto;
import com.ye.business.hx.dto.FindFestivalPosterPage;
import com.ye.business.hx.service.IFestivalPosterService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
@Service
public class FestivalPosterServiceImpl implements IFestivalPosterService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(FestivalPosterServiceImpl.class);
	

	@Resource
	private IFestivalPosterDao festivalPosterDao;
	
	
	@Override
	public void addFestivalPoster(
			FestivalPosterDto festivalPosterDto) throws TsfaServiceException {
		logger.debug("addFestivalPoster(AddFestivalPoster addFestivalPoster={}) - start", festivalPosterDto); 

		AssertUtils.notNull(festivalPosterDto);
		try {
			FestivalPoster festivalPoster = new FestivalPoster();
			//add数据录入
			festivalPoster.setCode(GUID.getPreUUID());
			festivalPoster.setTypeName(festivalPosterDto.getTypeName());
			festivalPoster.setImgs(festivalPosterDto.getImgs());
			festivalPoster.setUpdateId(festivalPosterDto.getUpdateId());
			festivalPoster.setUpdateDate(new Date());
			festivalPoster.setCreateId(festivalPosterDto.getCreateId());
			festivalPoster.setCreateDate(new Date());
			festivalPoster.setRemark(festivalPosterDto.getRemark());
			festivalPoster.setRemark1(festivalPosterDto.getRemark1());
			festivalPoster.setRemark2(festivalPosterDto.getRemark2());
			festivalPoster.setRemark3(festivalPosterDto.getRemark3());
			festivalPoster.setRemark4(festivalPosterDto.getRemark4());
			festivalPosterDao.insertSelective(festivalPoster);
			logger.debug("addFestivalPoster(FestivalPosterDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增节日问候海报模板信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FESTIVAL_POSTER_ADD_ERROR,"新增节日问候海报模板信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询节日问候海报模板信息
	 *
	 * @param findFestivalPosterPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<FestivalPosterDto>  findFestivalPosters(FindFestivalPosterPage findFestivalPosterPage)throws TsfaServiceException{
		AssertUtils.notNull(findFestivalPosterPage);
		List<FestivalPosterDto> returnList=null;
		try {
			returnList = festivalPosterDao.findFestivalPosters(findFestivalPosterPage);
		} catch (Exception e) {
			logger.error("查找节日问候海报模板信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.FESTIVAL_POSTER_NOT_EXIST_ERROR,"节日问候海报模板信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateFestivalPoster(
			FestivalPosterDto festivalPosterDto)
			throws TsfaServiceException {
		logger.debug("updateFestivalPoster(FestivalPosterDto festivalPosterDto={}) - start", festivalPosterDto); //$NON-NLS-1$

		AssertUtils.notNull(festivalPosterDto);
		AssertUtils.notNullAndEmpty(festivalPosterDto.getCode(),"Code不能为空");
		try {
			FestivalPoster festivalPoster = new FestivalPoster();
			//update数据录入
			festivalPoster.setCode(festivalPosterDto.getCode());
			festivalPoster.setTypeName(festivalPosterDto.getTypeName());
			festivalPoster.setImgs(festivalPosterDto.getImgs());
			festivalPoster.setUpdateId(festivalPosterDto.getUpdateId());
			festivalPoster.setUpdateDate(new Date());
//			festivalPoster.setCreateId(festivalPosterDto.getCreateId());
//			festivalPoster.setCreateDate(festivalPosterDto.getCreateDate());
			festivalPoster.setRemark(festivalPosterDto.getRemark());
			festivalPoster.setRemark1(festivalPosterDto.getRemark1());
			festivalPoster.setRemark2(festivalPosterDto.getRemark2());
			festivalPoster.setRemark3(festivalPosterDto.getRemark3());
			festivalPoster.setRemark4(festivalPosterDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(festivalPosterDao.updateByPrimaryKeySelective(festivalPoster));
			logger.debug("updateFestivalPoster(FestivalPosterDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("节日问候海报模板信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FESTIVAL_POSTER_UPDATE_ERROR,"节日问候海报模板信息更新信息错误！",e);
		}
	}

	

	@Override
	public FestivalPosterDto findFestivalPoster(
			FestivalPosterDto festivalPosterDto) throws TsfaServiceException {
		logger.debug("findFestivalPoster(FindFestivalPoster findFestivalPoster={}) - start", festivalPosterDto); //$NON-NLS-1$

		AssertUtils.notNull(festivalPosterDto);
		AssertUtils.notAllNull(festivalPosterDto.getCode(),"Code不能为空");
		try {
			FestivalPoster festivalPoster = festivalPosterDao.selectByPrimaryKey(festivalPosterDto.getCode());
			if(festivalPoster == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.FESTIVAL_POSTER_NOT_EXIST_ERROR,"节日问候海报模板信息不存在");
			}
			FestivalPosterDto findFestivalPosterReturn = new FestivalPosterDto();
			//find数据录入
			findFestivalPosterReturn.setCode(festivalPoster.getCode());
			findFestivalPosterReturn.setTypeName(festivalPoster.getTypeName());
			findFestivalPosterReturn.setImgs(festivalPoster.getImgs());
			findFestivalPosterReturn.setUpdateId(festivalPoster.getUpdateId());
			findFestivalPosterReturn.setUpdateDate(festivalPoster.getUpdateDate());
			findFestivalPosterReturn.setCreateId(festivalPoster.getCreateId());
			findFestivalPosterReturn.setCreateDate(festivalPoster.getCreateDate());
			findFestivalPosterReturn.setRemark(festivalPoster.getRemark());
			findFestivalPosterReturn.setRemark1(festivalPoster.getRemark1());
			findFestivalPosterReturn.setRemark2(festivalPoster.getRemark2());
			findFestivalPosterReturn.setRemark3(festivalPoster.getRemark3());
			findFestivalPosterReturn.setRemark4(festivalPoster.getRemark4());
			
			logger.debug("findFestivalPoster(FestivalPosterDto) - end - return value={}", findFestivalPosterReturn); //$NON-NLS-1$
			return findFestivalPosterReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找节日问候海报模板信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.FESTIVAL_POSTER_FIND_ERROR,"查找节日问候海报模板信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FestivalPosterDto> findFestivalPosterPage(
			FindFestivalPosterPage findFestivalPosterPage)
			throws TsfaServiceException {
		logger.debug("findFestivalPosterPage(FindFestivalPosterPage findFestivalPosterPage={}) - start", findFestivalPosterPage); //$NON-NLS-1$

		AssertUtils.notNull(findFestivalPosterPage);
		List<FestivalPosterDto> returnList=null;
		int count = 0;
		try {
			returnList = festivalPosterDao.findFestivalPosterPage(findFestivalPosterPage);
			count = festivalPosterDao.findFestivalPosterPageCount(findFestivalPosterPage);
		}  catch (Exception e) {
			logger.error("节日问候海报模板信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.FESTIVAL_POSTER_FIND_PAGE_ERROR,"节日问候海报模板信息不存在错误.！",e);
		}
		Page<FestivalPosterDto> returnPage = new Page<FestivalPosterDto>(returnList, count, findFestivalPosterPage);

		logger.debug("findFestivalPosterPage(FindFestivalPosterPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public void deleteFestivalPoster(FestivalPosterDto festivalPosterDto) throws TsfaServiceException {
		logger.debug("deleteFestivalPoster(FestivalPosterDto festivalPosterDto={}) - start", festivalPosterDto); //$NON-NLS-1$
		
		AssertUtils.notNull(festivalPosterDto);
		AssertUtils.notNullAndEmpty(festivalPosterDto.getCode(),"Code不能为空");
		int count = 0;
		try {
			count = festivalPosterDao.deleteByPrimaryKey(festivalPosterDto.getCode());
		}  catch (Exception e) {
			logger.error("节日问候海报模板信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.FESTIVAL_POSTER_FIND_PAGE_ERROR,"节日问候海报模板信息不存在错误.！",e);
		}

		logger.debug("deleteFestivalPoster(FestivalPosterDto) - end - return value={}", count); //$NON-NLS-1$
	} 


}
