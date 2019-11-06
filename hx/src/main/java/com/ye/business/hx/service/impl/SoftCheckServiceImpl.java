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
import com.ye.business.hx.dao.ISoftCheckDao;
import com.ye.business.hx.domain.SoftCheck;
import com.ye.business.hx.dto.FindSoftCheckPage;
import com.ye.business.hx.dto.SoftCheckDto;
import com.ye.business.hx.service.ISoftCheckService;
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
public class SoftCheckServiceImpl implements ISoftCheckService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(SoftCheckServiceImpl.class);
	

	@Resource
	private ISoftCheckDao softCheckDao;
	
	
	@Override
	public void addSoftCheck(
			SoftCheckDto softCheckDto) throws TsfaServiceException {
		logger.debug("addSoftCheck(AddSoftCheck addSoftCheck={}) - start", softCheckDto); 

		AssertUtils.notNull(softCheckDto);
		try {
			SoftCheck softCheck = new SoftCheck();
			//add数据录入
			softCheck.setCode(softCheckDto.getCode());
			softCheck.setMaxilla(softCheckDto.getMaxilla());
			softCheck.setMandible(softCheckDto.getMandible());
			softCheck.setMandibularAngle(softCheckDto.getMandibularAngle());
			softCheck.setTongueBody(softCheckDto.getTongueBody());
			softCheck.setUpperLip(softCheckDto.getUpperLip());
			softCheck.setLowerLip(softCheckDto.getLowerLip());
			softCheck.setLingualFrenulum(softCheckDto.getLingualFrenulum());
			softCheck.setLabialFrenulum(softCheckDto.getLabialFrenulum());
			softCheck.setTonsil(softCheckDto.getTonsil());
			softCheck.setAdenoid(softCheckDto.getAdenoid());
			softCheck.setPalatalLid(softCheckDto.getPalatalLid());
			softCheck.setPainLeft(softCheckDto.getPainLeft());
			softCheck.setPainRight(softCheckDto.getPainRight());
			softCheck.setBangLeft(softCheckDto.getBangLeft());
			softCheck.setBangRight(softCheckDto.getBangRight());
			softCheck.setOpenMouth(softCheckDto.getOpenMouth());
			softCheck.setCloseMouth(softCheckDto.getCloseMouth());
			softCheck.setAperture(softCheckDto.getAperture());
			softCheck.setRespiratoryTract(softCheckDto.getRespiratoryTract());
			softCheck.setDegree(softCheckDto.getDegree());
			softCheck.setOther(softCheckDto.getOther());
			softCheck.setPatientNo(softCheckDto.getPatientNo());
			softCheck.setCreateDate(softCheckDto.getCreateDate());
			softCheck.setCreateId(softCheckDto.getCreateId());
			softCheck.setRemark(softCheckDto.getRemark());
			softCheckDao.insertSelective(softCheck);
			logger.debug("addSoftCheck(SoftCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增软组织检查信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SOFT_CHECK_ADD_ERROR,"新增软组织检查信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询软组织检查信息
	 *
	 * @param findSoftCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<SoftCheckDto>  findSoftChecks(FindSoftCheckPage findSoftCheckPage)throws TsfaServiceException{
		AssertUtils.notNull(findSoftCheckPage);
		List<SoftCheckDto> returnList=null;
		try {
			returnList = softCheckDao.findSoftChecks(findSoftCheckPage);
		} catch (Exception e) {
			logger.error("查找软组织检查信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SOFT_CHECK_NOT_EXIST_ERROR,"软组织检查信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateSoftCheck(
			SoftCheckDto softCheckDto)
			throws TsfaServiceException {
		logger.debug("updateSoftCheck(SoftCheckDto softCheckDto={}) - start", softCheckDto); 

		AssertUtils.notNull(softCheckDto);
		AssertUtils.notNullAndEmpty(softCheckDto.getCode(),"Code不能为空");
		try {
			SoftCheck softCheck = new SoftCheck();
			//update数据录入
			softCheck.setCode(softCheckDto.getCode());
			softCheck.setMaxilla(softCheckDto.getMaxilla());
			softCheck.setMandible(softCheckDto.getMandible());
			softCheck.setMandibularAngle(softCheckDto.getMandibularAngle());
			softCheck.setTongueBody(softCheckDto.getTongueBody());
			softCheck.setUpperLip(softCheckDto.getUpperLip());
			softCheck.setLowerLip(softCheckDto.getLowerLip());
			softCheck.setLingualFrenulum(softCheckDto.getLingualFrenulum());
			softCheck.setLabialFrenulum(softCheckDto.getLabialFrenulum());
			softCheck.setTonsil(softCheckDto.getTonsil());
			softCheck.setAdenoid(softCheckDto.getAdenoid());
			softCheck.setPalatalLid(softCheckDto.getPalatalLid());
			softCheck.setPainLeft(softCheckDto.getPainLeft());
			softCheck.setPainRight(softCheckDto.getPainRight());
			softCheck.setBangLeft(softCheckDto.getBangLeft());
			softCheck.setBangRight(softCheckDto.getBangRight());
			softCheck.setOpenMouth(softCheckDto.getOpenMouth());
			softCheck.setCloseMouth(softCheckDto.getCloseMouth());
			softCheck.setAperture(softCheckDto.getAperture());
			softCheck.setRespiratoryTract(softCheckDto.getRespiratoryTract());
			softCheck.setDegree(softCheckDto.getDegree());
			softCheck.setOther(softCheckDto.getOther());
			softCheck.setPatientNo(softCheckDto.getPatientNo());
			softCheck.setCreateDate(softCheckDto.getCreateDate());
			softCheck.setCreateId(softCheckDto.getCreateId());
			softCheck.setRemark(softCheckDto.getRemark());
			AssertUtils.notUpdateMoreThanOne(softCheckDao.updateByPrimaryKeySelective(softCheck));
			logger.debug("updateSoftCheck(SoftCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("软组织检查信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SOFT_CHECK_UPDATE_ERROR,"软组织检查信息更新信息错误！",e);
		}
	}

	

	@Override
	public SoftCheckDto findSoftCheck(
			SoftCheckDto softCheckDto) throws TsfaServiceException {
		logger.debug("findSoftCheck(FindSoftCheck findSoftCheck={}) - start", softCheckDto); 

		AssertUtils.notNull(softCheckDto);
		AssertUtils.notAllNull(softCheckDto.getCode(),"Code不能为空");
		try {
			SoftCheck softCheck = softCheckDao.selectByPrimaryKey(softCheckDto.getCode());
			if(softCheck == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SOFT_CHECK_NOT_EXIST_ERROR,"软组织检查信息不存在");
			}
			SoftCheckDto findSoftCheckReturn = new SoftCheckDto();
			//find数据录入
			findSoftCheckReturn.setCode(softCheck.getCode());
			findSoftCheckReturn.setMaxilla(softCheck.getMaxilla());
			findSoftCheckReturn.setMandible(softCheck.getMandible());
			findSoftCheckReturn.setMandibularAngle(softCheck.getMandibularAngle());
			findSoftCheckReturn.setTongueBody(softCheck.getTongueBody());
			findSoftCheckReturn.setUpperLip(softCheck.getUpperLip());
			findSoftCheckReturn.setLowerLip(softCheck.getLowerLip());
			findSoftCheckReturn.setLingualFrenulum(softCheck.getLingualFrenulum());
			findSoftCheckReturn.setLabialFrenulum(softCheck.getLabialFrenulum());
			findSoftCheckReturn.setTonsil(softCheck.getTonsil());
			findSoftCheckReturn.setAdenoid(softCheck.getAdenoid());
			findSoftCheckReturn.setPalatalLid(softCheck.getPalatalLid());
			findSoftCheckReturn.setPainLeft(softCheck.getPainLeft());
			findSoftCheckReturn.setPainRight(softCheck.getPainRight());
			findSoftCheckReturn.setBangLeft(softCheck.getBangLeft());
			findSoftCheckReturn.setBangRight(softCheck.getBangRight());
			findSoftCheckReturn.setOpenMouth(softCheck.getOpenMouth());
			findSoftCheckReturn.setCloseMouth(softCheck.getCloseMouth());
			findSoftCheckReturn.setAperture(softCheck.getAperture());
			findSoftCheckReturn.setRespiratoryTract(softCheck.getRespiratoryTract());
			findSoftCheckReturn.setDegree(softCheck.getDegree());
			findSoftCheckReturn.setOther(softCheck.getOther());
			findSoftCheckReturn.setPatientNo(softCheck.getPatientNo());
			findSoftCheckReturn.setCreateDate(softCheck.getCreateDate());
			findSoftCheckReturn.setCreateId(softCheck.getCreateId());
			findSoftCheckReturn.setRemark(softCheck.getRemark());
			
			logger.debug("findSoftCheck(SoftCheckDto) - end - return value={}", findSoftCheckReturn); 
			return findSoftCheckReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找软组织检查信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SOFT_CHECK_FIND_ERROR,"查找软组织检查信息信息错误！",e);
		}


	}

	
	@Override
	public Page<SoftCheckDto> findSoftCheckPage(
			FindSoftCheckPage findSoftCheckPage)
			throws TsfaServiceException {
		logger.debug("findSoftCheckPage(FindSoftCheckPage findSoftCheckPage={}) - start", findSoftCheckPage); 

		AssertUtils.notNull(findSoftCheckPage);
		List<SoftCheckDto> returnList=null;
		int count = 0;
		try {
			returnList = softCheckDao.findSoftCheckPage(findSoftCheckPage);
			count = softCheckDao.findSoftCheckPageCount(findSoftCheckPage);
		}  catch (Exception e) {
			logger.error("软组织检查信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SOFT_CHECK_FIND_PAGE_ERROR,"软组织检查信息不存在错误.！",e);
		}
		Page<SoftCheckDto> returnPage = new Page<SoftCheckDto>(returnList, count, findSoftCheckPage);

		logger.debug("findSoftCheckPage(FindSoftCheckPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
