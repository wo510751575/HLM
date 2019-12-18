package com.ye.business.hx.service.impl;

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
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IDentitionCheckDao;
import com.ye.business.hx.domain.DentitionCheck;
import com.ye.business.hx.dto.DentitionCheckDto;
import com.ye.business.hx.dto.FindDentitionCheckPage;
import com.ye.business.hx.service.IDentitionCheckService;
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
public class DentitionCheckServiceImpl implements IDentitionCheckService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(DentitionCheckServiceImpl.class);
	

	@Resource
	private IDentitionCheckDao dentitionCheckDao;
	
	
	@Override
	public void addDentitionCheck(
			DentitionCheckDto dentitionCheckDto) throws TsfaServiceException {
		logger.debug("addDentitionCheck(AddDentitionCheck addDentitionCheck={}) - start", dentitionCheckDto); 

		AssertUtils.notNull(dentitionCheckDto);
		try {
			DentitionCheck dentitionCheck = new DentitionCheck();
			//add数据录入
			dentitionCheck.setCode(dentitionCheckDto.getCode());
			dentitionCheck.setFront(dentitionCheckDto.getFront());
			dentitionCheck.setFrontUrl(dentitionCheckDto.getFrontUrl());
			dentitionCheck.setUp(dentitionCheckDto.getUp());
			dentitionCheck.setUpUrl(dentitionCheckDto.getUpUrl());
			dentitionCheck.setDown(dentitionCheckDto.getDown());
			dentitionCheck.setDownUrl(dentitionCheckDto.getDownUrl());
			dentitionCheck.setLeft(dentitionCheckDto.getLeft());
			dentitionCheck.setLeftUrl(dentitionCheckDto.getLeftUrl());
			dentitionCheck.setRight(dentitionCheckDto.getRight());
			dentitionCheck.setRightUrl(dentitionCheckDto.getRightUrl());
			dentitionCheck.setFrontTeeth(dentitionCheckDto.getFrontTeeth());
			dentitionCheck.setFrontTeethUrl(dentitionCheckDto.getFrontTeethUrl());
			dentitionCheck.setOtherCode(dentitionCheckDto.getOtherCode());
			dentitionCheck.setOtherUrl(dentitionCheckDto.getOtherUrl());
			dentitionCheck.setUpTeeth(dentitionCheckDto.getUpTeeth());
			dentitionCheck.setDownTeeth(dentitionCheckDto.getDownTeeth());
			dentitionCheck.setMoreTeeth(dentitionCheckDto.getMoreTeeth());
			dentitionCheck.setLoss(dentitionCheckDto.getLoss());
			dentitionCheck.setDislocation(dentitionCheckDto.getDislocation());
			dentitionCheck.setAnteriorRatio(dentitionCheckDto.getAnteriorRatio());
			dentitionCheck.setOverallRatio(dentitionCheckDto.getOverallRatio());
			dentitionCheck.setLeftMolar(dentitionCheckDto.getLeftMolar());
			dentitionCheck.setRightMolar(dentitionCheckDto.getRightMolar());
			dentitionCheck.setLeftFangs(dentitionCheckDto.getLeftFangs());
			dentitionCheck.setRightFangs(dentitionCheckDto.getRightFangs());
			dentitionCheck.setAnteriorTeeth(dentitionCheckDto.getAnteriorTeeth());
			dentitionCheck.setArchForm(dentitionCheckDto.getArchForm());
			dentitionCheck.setPosteriorTeeth(dentitionCheckDto.getPosteriorTeeth());
			dentitionCheck.setCanineTeeth(dentitionCheckDto.getCanineTeeth());
			dentitionCheck.setMaxillaryOverbite(dentitionCheckDto.getMaxillaryOverbite());
			dentitionCheck.setMaxillary(dentitionCheckDto.getMaxillary());
			dentitionCheck.setMandible(dentitionCheckDto.getMandible());
			dentitionCheck.setOther(dentitionCheckDto.getOther());
			dentitionCheck.setPatientNo(dentitionCheckDto.getPatientNo());
			dentitionCheck.setCreateDate(dentitionCheckDto.getCreateDate());
			dentitionCheck.setCreateId(dentitionCheckDto.getCreateId());
			dentitionCheck.setRemark(dentitionCheckDto.getRemark());
			dentitionCheckDao.insertSelective(dentitionCheck);
			logger.debug("addDentitionCheck(DentitionCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增牙列检查信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DENTITION_CHECK_ADD_ERROR,"新增牙列检查信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询牙列检查信息
	 *
	 * @param findDentitionCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<DentitionCheckDto>  findDentitionChecks(FindDentitionCheckPage findDentitionCheckPage)throws TsfaServiceException{
		AssertUtils.notNull(findDentitionCheckPage);
		List<DentitionCheckDto> returnList=null;
		try {
			returnList = dentitionCheckDao.findDentitionChecks(findDentitionCheckPage);
		} catch (Exception e) {
			logger.error("查找牙列检查信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.DENTITION_CHECK_NOT_EXIST_ERROR,"牙列检查信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateDentitionCheck(
			DentitionCheckDto dentitionCheckDto)
			throws TsfaServiceException {
		logger.debug("updateDentitionCheck(DentitionCheckDto dentitionCheckDto={}) - start", dentitionCheckDto); 

		AssertUtils.notNull(dentitionCheckDto);
		AssertUtils.notNullAndEmpty(dentitionCheckDto.getCode(),"Code不能为空");
		try {
			DentitionCheck dentitionCheck = new DentitionCheck();
			//update数据录入
			dentitionCheck.setCode(dentitionCheckDto.getCode());
			dentitionCheck.setFront(dentitionCheckDto.getFront());
			dentitionCheck.setFrontUrl(dentitionCheckDto.getFrontUrl());
			dentitionCheck.setUp(dentitionCheckDto.getUp());
			dentitionCheck.setUpUrl(dentitionCheckDto.getUpUrl());
			dentitionCheck.setDown(dentitionCheckDto.getDown());
			dentitionCheck.setDownUrl(dentitionCheckDto.getDownUrl());
			dentitionCheck.setLeft(dentitionCheckDto.getLeft());
			dentitionCheck.setLeftUrl(dentitionCheckDto.getLeftUrl());
			dentitionCheck.setRight(dentitionCheckDto.getRight());
			dentitionCheck.setRightUrl(dentitionCheckDto.getRightUrl());
			dentitionCheck.setFrontTeeth(dentitionCheckDto.getFrontTeeth());
			dentitionCheck.setFrontTeethUrl(dentitionCheckDto.getFrontTeethUrl());
			dentitionCheck.setOtherCode(dentitionCheckDto.getOtherCode());
			dentitionCheck.setOtherUrl(dentitionCheckDto.getOtherUrl());
			dentitionCheck.setUpTeeth(dentitionCheckDto.getUpTeeth());
			dentitionCheck.setDownTeeth(dentitionCheckDto.getDownTeeth());
			dentitionCheck.setMoreTeeth(dentitionCheckDto.getMoreTeeth());
			dentitionCheck.setLoss(dentitionCheckDto.getLoss());
			dentitionCheck.setDislocation(dentitionCheckDto.getDislocation());
			dentitionCheck.setAnteriorRatio(dentitionCheckDto.getAnteriorRatio());
			dentitionCheck.setOverallRatio(dentitionCheckDto.getOverallRatio());
			dentitionCheck.setLeftMolar(dentitionCheckDto.getLeftMolar());
			dentitionCheck.setRightMolar(dentitionCheckDto.getRightMolar());
			dentitionCheck.setLeftFangs(dentitionCheckDto.getLeftFangs());
			dentitionCheck.setRightFangs(dentitionCheckDto.getRightFangs());
			dentitionCheck.setAnteriorTeeth(dentitionCheckDto.getAnteriorTeeth());
			dentitionCheck.setArchForm(dentitionCheckDto.getArchForm());
			dentitionCheck.setPosteriorTeeth(dentitionCheckDto.getPosteriorTeeth());
			dentitionCheck.setCanineTeeth(dentitionCheckDto.getCanineTeeth());
			dentitionCheck.setMaxillaryOverbite(dentitionCheckDto.getMaxillaryOverbite());
			dentitionCheck.setMaxillary(dentitionCheckDto.getMaxillary());
			dentitionCheck.setMandible(dentitionCheckDto.getMandible());
			dentitionCheck.setOther(dentitionCheckDto.getOther());
			dentitionCheck.setPatientNo(dentitionCheckDto.getPatientNo());
			dentitionCheck.setCreateDate(dentitionCheckDto.getCreateDate());
			dentitionCheck.setCreateId(dentitionCheckDto.getCreateId());
			dentitionCheck.setRemark(dentitionCheckDto.getRemark());
			AssertUtils.notUpdateMoreThanOne(dentitionCheckDao.updateByPrimaryKeySelective(dentitionCheck));
			logger.debug("updateDentitionCheck(DentitionCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("牙列检查信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DENTITION_CHECK_UPDATE_ERROR,"牙列检查信息更新信息错误！",e);
		}
	}

	

	@Override
	public DentitionCheckDto findDentitionCheck(
			DentitionCheckDto dentitionCheckDto) throws TsfaServiceException {
		logger.debug("findDentitionCheck(FindDentitionCheck findDentitionCheck={}) - start", dentitionCheckDto); 

		AssertUtils.notNull(dentitionCheckDto);
		try {
			DentitionCheck dentitionCheck = dentitionCheckDao.selectByPrimaryKey(dentitionCheckDto.getPatientNo());
			if(dentitionCheck == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.DENTITION_CHECK_NOT_EXIST_ERROR,"牙列检查信息不存在");
			}
			DentitionCheckDto findDentitionCheckReturn = new DentitionCheckDto();
			//find数据录入
			findDentitionCheckReturn.setCode(dentitionCheck.getCode());
			findDentitionCheckReturn.setFront(dentitionCheck.getFront());
			findDentitionCheckReturn.setFrontUrl(dentitionCheck.getFrontUrl());
			findDentitionCheckReturn.setUp(dentitionCheck.getUp());
			findDentitionCheckReturn.setUpUrl(dentitionCheck.getUpUrl());
			findDentitionCheckReturn.setDown(dentitionCheck.getDown());
			findDentitionCheckReturn.setDownUrl(dentitionCheck.getDownUrl());
			findDentitionCheckReturn.setLeft(dentitionCheck.getLeft());
			findDentitionCheckReturn.setLeftUrl(dentitionCheck.getLeftUrl());
			findDentitionCheckReturn.setRight(dentitionCheck.getRight());
			findDentitionCheckReturn.setRightUrl(dentitionCheck.getRightUrl());
			findDentitionCheckReturn.setFrontTeeth(dentitionCheck.getFrontTeeth());
			findDentitionCheckReturn.setFrontTeethUrl(dentitionCheck.getFrontTeethUrl());
			findDentitionCheckReturn.setOtherCode(dentitionCheck.getOtherCode());
			findDentitionCheckReturn.setOtherUrl(dentitionCheck.getOtherUrl());
			findDentitionCheckReturn.setUpTeeth(dentitionCheck.getUpTeeth());
			findDentitionCheckReturn.setDownTeeth(dentitionCheck.getDownTeeth());
			findDentitionCheckReturn.setMoreTeeth(dentitionCheck.getMoreTeeth());
			findDentitionCheckReturn.setLoss(dentitionCheck.getLoss());
			findDentitionCheckReturn.setDislocation(dentitionCheck.getDislocation());
			findDentitionCheckReturn.setAnteriorRatio(dentitionCheck.getAnteriorRatio());
			findDentitionCheckReturn.setOverallRatio(dentitionCheck.getOverallRatio());
			findDentitionCheckReturn.setLeftMolar(dentitionCheck.getLeftMolar());
			findDentitionCheckReturn.setRightMolar(dentitionCheck.getRightMolar());
			findDentitionCheckReturn.setLeftFangs(dentitionCheck.getLeftFangs());
			findDentitionCheckReturn.setRightFangs(dentitionCheck.getRightFangs());
			findDentitionCheckReturn.setAnteriorTeeth(dentitionCheck.getAnteriorTeeth());
			findDentitionCheckReturn.setArchForm(dentitionCheck.getArchForm());
			findDentitionCheckReturn.setPosteriorTeeth(dentitionCheck.getPosteriorTeeth());
			findDentitionCheckReturn.setCanineTeeth(dentitionCheck.getCanineTeeth());
			findDentitionCheckReturn.setMaxillaryOverbite(dentitionCheck.getMaxillaryOverbite());
			findDentitionCheckReturn.setMaxillary(dentitionCheck.getMaxillary());
			findDentitionCheckReturn.setMandible(dentitionCheck.getMandible());
			findDentitionCheckReturn.setOther(dentitionCheck.getOther());
			findDentitionCheckReturn.setPatientNo(dentitionCheck.getPatientNo());
			findDentitionCheckReturn.setCreateDate(dentitionCheck.getCreateDate());
			findDentitionCheckReturn.setCreateId(dentitionCheck.getCreateId());
			findDentitionCheckReturn.setRemark(dentitionCheck.getRemark());
			
			logger.debug("findDentitionCheck(DentitionCheckDto) - end - return value={}", findDentitionCheckReturn); 
			return findDentitionCheckReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找牙列检查信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DENTITION_CHECK_FIND_ERROR,"查找牙列检查信息信息错误！",e);
		}


	}

	
	@Override
	public Page<DentitionCheckDto> findDentitionCheckPage(
			FindDentitionCheckPage findDentitionCheckPage)
			throws TsfaServiceException {
		logger.debug("findDentitionCheckPage(FindDentitionCheckPage findDentitionCheckPage={}) - start", findDentitionCheckPage); 

		AssertUtils.notNull(findDentitionCheckPage);
		List<DentitionCheckDto> returnList=null;
		int count = 0;
		try {
			returnList = dentitionCheckDao.findDentitionCheckPage(findDentitionCheckPage);
			count = dentitionCheckDao.findDentitionCheckPageCount(findDentitionCheckPage);
		}  catch (Exception e) {
			logger.error("牙列检查信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.DENTITION_CHECK_FIND_PAGE_ERROR,"牙列检查信息不存在错误.！",e);
		}
		Page<DentitionCheckDto> returnPage = new Page<DentitionCheckDto>(returnList, count, findDentitionCheckPage);

		logger.debug("findDentitionCheckPage(FindDentitionCheckPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
