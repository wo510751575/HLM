package com.ye.business.hx.service.impl;

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
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;

import com.ye.business.hx.dto.GumCheckDto;
import com.ye.business.hx.dto.FindGumCheckPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IGumCheckDao;
import com.ye.business.hx.domain.GumCheck;
import com.ye.business.hx.service.IGumCheckService;
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
 * CreateDate: 2017.12.14
 */
@Service
public class GumCheckServiceImpl implements IGumCheckService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GumCheckServiceImpl.class);
	

	@Resource
	private IGumCheckDao gumCheckDao;
	
	
	@Override
	public void addGumCheck(
			GumCheckDto gumCheckDto) throws TsfaServiceException {
		logger.debug("addGumCheck(AddGumCheck addGumCheck={}) - start", gumCheckDto); 

		AssertUtils.notNull(gumCheckDto);
		try {
			GumCheck gumCheck = new GumCheck();
			//add数据录入
			gumCheck.setCode(gumCheckDto.getCode());
			gumCheck.setPatientNo(gumCheckDto.getPatientNo());
			gumCheck.setCreateDate(gumCheckDto.getCreateDate());
			gumCheck.setCreateId(gumCheckDto.getCreateId());
			gumCheck.setPosition(gumCheckDto.getPosition());
			gumCheck.setPlaqueS(gumCheckDto.getPlaqueS());
			gumCheck.setPlaqueM(gumCheckDto.getPlaqueM());
			gumCheck.setPlaqueL(gumCheckDto.getPlaqueL());
			gumCheck.setBleedingS(gumCheckDto.getBleedingS());
			gumCheck.setBleedingM(gumCheckDto.getBleedingM());
			gumCheck.setBleedingL(gumCheckDto.getBleedingL());
			gumCheck.setPdS(gumCheckDto.getPdS());
			gumCheck.setPdM(gumCheckDto.getPdM());
			gumCheck.setPdL(gumCheckDto.getPdL());
			gumCheck.setGmS(gumCheckDto.getGmS());
			gumCheck.setGmM(gumCheckDto.getGmM());
			gumCheck.setGmL(gumCheckDto.getGmL());
			gumCheck.setRemark(gumCheckDto.getRemark());
			gumCheck.setRemark2(gumCheckDto.getRemark2());
			gumCheck.setRemark3(gumCheckDto.getRemark3());
			gumCheck.setRemark4(gumCheckDto.getRemark4());
			gumCheckDao.insertSelective(gumCheck);
			logger.debug("addGumCheck(GumCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增牙周检查信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUM_CHECK_ADD_ERROR,"新增牙周检查信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询牙周检查信息
	 *
	 * @param findGumCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<GumCheckDto>  findGumChecks(FindGumCheckPage findGumCheckPage)throws TsfaServiceException{
		AssertUtils.notNull(findGumCheckPage);
		List<GumCheckDto> returnList=null;
		try {
			returnList = gumCheckDao.findGumChecks(findGumCheckPage);
		} catch (Exception e) {
			logger.error("查找牙周检查信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GUM_CHECK_NOT_EXIST_ERROR,"牙周检查信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateGumCheck(
			GumCheckDto gumCheckDto)
			throws TsfaServiceException {
		logger.debug("updateGumCheck(GumCheckDto gumCheckDto={}) - start", gumCheckDto); 

		AssertUtils.notNull(gumCheckDto);
		AssertUtils.notNullAndEmpty(gumCheckDto.getCode(),"Code不能为空");
		try {
			GumCheck gumCheck = new GumCheck();
			//update数据录入
			gumCheck.setCode(gumCheckDto.getCode());
			gumCheck.setPatientNo(gumCheckDto.getPatientNo());
			gumCheck.setCreateDate(gumCheckDto.getCreateDate());
			gumCheck.setCreateId(gumCheckDto.getCreateId());
			gumCheck.setPosition(gumCheckDto.getPosition());
			gumCheck.setPlaqueS(gumCheckDto.getPlaqueS());
			gumCheck.setPlaqueM(gumCheckDto.getPlaqueM());
			gumCheck.setPlaqueL(gumCheckDto.getPlaqueL());
			gumCheck.setBleedingS(gumCheckDto.getBleedingS());
			gumCheck.setBleedingM(gumCheckDto.getBleedingM());
			gumCheck.setBleedingL(gumCheckDto.getBleedingL());
			gumCheck.setPdS(gumCheckDto.getPdS());
			gumCheck.setPdM(gumCheckDto.getPdM());
			gumCheck.setPdL(gumCheckDto.getPdL());
			gumCheck.setGmS(gumCheckDto.getGmS());
			gumCheck.setGmM(gumCheckDto.getGmM());
			gumCheck.setGmL(gumCheckDto.getGmL());
			gumCheck.setRemark(gumCheckDto.getRemark());
			gumCheck.setRemark2(gumCheckDto.getRemark2());
			gumCheck.setRemark3(gumCheckDto.getRemark3());
			gumCheck.setRemark4(gumCheckDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(gumCheckDao.updateByPrimaryKeySelective(gumCheck));
			logger.debug("updateGumCheck(GumCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("牙周检查信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUM_CHECK_UPDATE_ERROR,"牙周检查信息更新信息错误！",e);
		}
	}

	

	@Override
	public GumCheckDto findGumCheck(
			GumCheckDto gumCheckDto) throws TsfaServiceException {
		logger.debug("findGumCheck(FindGumCheck findGumCheck={}) - start", gumCheckDto); 

		AssertUtils.notNull(gumCheckDto);
		AssertUtils.notAllNull(gumCheckDto.getCode(),"Code不能为空");
		try {
			GumCheck gumCheck = gumCheckDao.selectByPrimaryKey(gumCheckDto.getCode());
			if(gumCheck == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.GUM_CHECK_NOT_EXIST_ERROR,"牙周检查信息不存在");
			}
			GumCheckDto findGumCheckReturn = new GumCheckDto();
			//find数据录入
			findGumCheckReturn.setCode(gumCheck.getCode());
			findGumCheckReturn.setPatientNo(gumCheck.getPatientNo());
			findGumCheckReturn.setCreateDate(gumCheck.getCreateDate());
			findGumCheckReturn.setCreateId(gumCheck.getCreateId());
			findGumCheckReturn.setPosition(gumCheck.getPosition());
			findGumCheckReturn.setPlaqueS(gumCheck.getPlaqueS());
			findGumCheckReturn.setPlaqueM(gumCheck.getPlaqueM());
			findGumCheckReturn.setPlaqueL(gumCheck.getPlaqueL());
			findGumCheckReturn.setBleedingS(gumCheck.getBleedingS());
			findGumCheckReturn.setBleedingM(gumCheck.getBleedingM());
			findGumCheckReturn.setBleedingL(gumCheck.getBleedingL());
			findGumCheckReturn.setPdS(gumCheck.getPdS());
			findGumCheckReturn.setPdM(gumCheck.getPdM());
			findGumCheckReturn.setPdL(gumCheck.getPdL());
			findGumCheckReturn.setGmS(gumCheck.getGmS());
			findGumCheckReturn.setGmM(gumCheck.getGmM());
			findGumCheckReturn.setGmL(gumCheck.getGmL());
			findGumCheckReturn.setRemark(gumCheck.getRemark());
			findGumCheckReturn.setRemark2(gumCheck.getRemark2());
			findGumCheckReturn.setRemark3(gumCheck.getRemark3());
			findGumCheckReturn.setRemark4(gumCheck.getRemark4());
			
			logger.debug("findGumCheck(GumCheckDto) - end - return value={}", findGumCheckReturn); 
			return findGumCheckReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找牙周检查信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUM_CHECK_FIND_ERROR,"查找牙周检查信息信息错误！",e);
		}


	}

	
	@Override
	public Page<GumCheckDto> findGumCheckPage(
			FindGumCheckPage findGumCheckPage)
			throws TsfaServiceException {
		logger.debug("findGumCheckPage(FindGumCheckPage findGumCheckPage={}) - start", findGumCheckPage); 

		AssertUtils.notNull(findGumCheckPage);
		List<GumCheckDto> returnList=null;
		int count = 0;
		try {
			returnList = gumCheckDao.findGumCheckPage(findGumCheckPage);
			count = gumCheckDao.findGumCheckPageCount(findGumCheckPage);
		}  catch (Exception e) {
			logger.error("牙周检查信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GUM_CHECK_FIND_PAGE_ERROR,"牙周检查信息不存在错误.！",e);
		}
		Page<GumCheckDto> returnPage = new Page<GumCheckDto>(returnList, count, findGumCheckPage);

		logger.debug("findGumCheckPage(FindGumCheckPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	
	@Override
	public List<Date> findTimeList(FindGumCheckPage findGumCheckPage) throws TsfaServiceException {
		logger.debug("findTimeList(FindGumCheckPage findGumCheckPage = {})-start",findGumCheckPage);
		try {
			List<Date> list = gumCheckDao.findTimeList(findGumCheckPage);
			return list;
		} catch (Exception e) {
			logger.error("查询时间列表信息错误!");
			throw new TsfaServiceException(ErrorCode.GUM_CHECK_TIME_LIST_ERROR,"查询时间列表信息错误.！",e);
		}
	} 


}
