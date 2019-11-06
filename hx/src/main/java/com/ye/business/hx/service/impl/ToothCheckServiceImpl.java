package com.ye.business.hx.service.impl;

import java.text.SimpleDateFormat;
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

import com.ye.business.hx.dto.ToothCheckDto;
import com.ye.business.hx.dto.FindToothCheckPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientSymptomDao;
import com.ye.business.hx.dao.IToothCheckDao;
import com.ye.business.hx.domain.PatientSymptom;
import com.ye.business.hx.domain.ToothCheck;
import com.ye.business.hx.service.IToothCheckService;
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
public class ToothCheckServiceImpl implements IToothCheckService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ToothCheckServiceImpl.class);
	

	@Resource
	private IToothCheckDao toothCheckDao;
	@Resource
	private IPatientSymptomDao patientSymptomDao;
	
	@Override
	public void addToothCheck(
			ToothCheckDto toothCheckDto) throws TsfaServiceException {
		logger.debug("addToothCheck(AddToothCheck addToothCheck={}) - start", toothCheckDto); 

		AssertUtils.notNull(toothCheckDto);
		try {
			ToothCheck toothCheck = new ToothCheck();
			//add数据录入
			toothCheck.setCode(toothCheckDto.getCode());
			toothCheck.setDentalPosition(toothCheckDto.getDentalPosition());
			toothCheck.setSymptom(toothCheckDto.getSymptom());
			toothCheck.setRemark(toothCheckDto.getRemark());
			toothCheck.setRemark2(toothCheckDto.getRemark2());
			toothCheck.setRemark3(toothCheckDto.getRemark3());
			toothCheck.setRemark4(toothCheckDto.getRemark4());
			toothCheck.setCreateDate(toothCheckDto.getCreateDate());
			toothCheck.setPatientNo(toothCheckDto.getPatientNo());
			toothCheckDao.insertSelective(toothCheck);
			logger.debug("addToothCheck(ToothCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增牙齿检查信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TOOTH_CHECK_ADD_ERROR,"新增牙齿检查信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询牙齿检查信息
	 *
	 * @param findToothCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<ToothCheckDto>  findToothChecks(FindToothCheckPage findToothCheckPage)throws TsfaServiceException{
		AssertUtils.notNull(findToothCheckPage);
		List<ToothCheckDto> returnList=null;
		try {
			returnList = toothCheckDao.findToothChecks(findToothCheckPage);
		} catch (Exception e) {
			logger.error("查找牙齿检查信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.TOOTH_CHECK_NOT_EXIST_ERROR,"牙齿检查信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateToothCheck(
			ToothCheckDto toothCheckDto)
			throws TsfaServiceException {
		logger.debug("updateToothCheck(ToothCheckDto toothCheckDto={}) - start", toothCheckDto); 

		AssertUtils.notNull(toothCheckDto);
		AssertUtils.notNullAndEmpty(toothCheckDto.getCode(),"Code不能为空");
		try {
			ToothCheck toothCheck = new ToothCheck();
			//update数据录入
			toothCheck.setCode(toothCheckDto.getCode());
			toothCheck.setDentalPosition(toothCheckDto.getDentalPosition());
			toothCheck.setSymptom(toothCheckDto.getSymptom());
			toothCheck.setRemark(toothCheckDto.getRemark());
			toothCheck.setRemark2(toothCheckDto.getRemark2());
			toothCheck.setRemark3(toothCheckDto.getRemark3());
			toothCheck.setRemark4(toothCheckDto.getRemark4());
			toothCheck.setCreateDate(toothCheckDto.getCreateDate());
			toothCheck.setPatientNo(toothCheckDto.getPatientNo());
			AssertUtils.notUpdateMoreThanOne(toothCheckDao.updateByPrimaryKeySelective(toothCheck));
			logger.debug("updateToothCheck(ToothCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("牙齿检查信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TOOTH_CHECK_UPDATE_ERROR,"牙齿检查信息更新信息错误！",e);
		}
	}

	

	@Override
	public ToothCheckDto findToothCheck(
			ToothCheckDto toothCheckDto) throws TsfaServiceException {
		logger.debug("findToothCheck(FindToothCheck findToothCheck={}) - start", toothCheckDto); 

		AssertUtils.notNull(toothCheckDto);
		AssertUtils.notAllNull(toothCheckDto.getCode(),"Code不能为空");
		try {
			ToothCheck toothCheck = toothCheckDao.selectByPrimaryKey(toothCheckDto.getCode());
			if(toothCheck == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.TOOTH_CHECK_NOT_EXIST_ERROR,"牙齿检查信息不存在");
			}
			ToothCheckDto findToothCheckReturn = new ToothCheckDto();
			//find数据录入
			findToothCheckReturn.setCode(toothCheck.getCode());
			findToothCheckReturn.setDentalPosition(toothCheck.getDentalPosition());
			findToothCheckReturn.setSymptom(toothCheck.getSymptom());
			findToothCheckReturn.setRemark(toothCheck.getRemark());
			findToothCheckReturn.setRemark2(toothCheck.getRemark2());
			findToothCheckReturn.setRemark3(toothCheck.getRemark3());
			findToothCheckReturn.setRemark4(toothCheck.getRemark4());
			findToothCheckReturn.setCreateDate(toothCheck.getCreateDate());
			findToothCheckReturn.setPatientNo(toothCheck.getPatientNo());
			
			logger.debug("findToothCheck(ToothCheckDto) - end - return value={}", findToothCheckReturn); 
			return findToothCheckReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找牙齿检查信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TOOTH_CHECK_FIND_ERROR,"查找牙齿检查信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ToothCheckDto> findToothCheckPage(
			FindToothCheckPage findToothCheckPage)
			throws TsfaServiceException {
		logger.debug("findToothCheckPage(FindToothCheckPage findToothCheckPage={}) - start", findToothCheckPage); 

		AssertUtils.notNull(findToothCheckPage);
		List<ToothCheckDto> returnList=null;
		int count = 0;
		try {
			Date createDate = findToothCheckPage.getParam().getCreateDate();
			if(createDate!=null) {
				SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String str = sft.format(createDate).substring(0,10);
				findToothCheckPage.getParam().setCreateDateStart(sft.parse(str+" 00:00:00"));
				findToothCheckPage.getParam().setCreateDateEnd(sft.parse(str+" 23:59:59"));
			}
			count = toothCheckDao.findToothCheckPageCount(findToothCheckPage);
			if(count>0) {
				returnList = toothCheckDao.findToothCheckPage(findToothCheckPage);
				for (ToothCheckDto toothCheckDto : returnList) {
					PatientSymptom patientSymptom = patientSymptomDao.selectByPrimaryKey(toothCheckDto.getRemark2());
					toothCheckDto.setSymptom(patientSymptom.getName());
				}
			}
			
		}  catch (Exception e) {
			logger.error("牙齿检查信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.TOOTH_CHECK_FIND_PAGE_ERROR,"牙齿检查信息不存在错误.！",e);
		}
		Page<ToothCheckDto> returnPage = new Page<ToothCheckDto>(returnList, count, findToothCheckPage);

		logger.debug("findToothCheckPage(FindToothCheckPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	/**   
	 * <p>Title: delete</p>   
	 * <p>Description: </p>   
	 * @param toothCheckDto
	 * @throws TsfaServiceException   
	 * @see com.ye.business.hx.service.IToothCheckService#delete(com.ye.business.hx.dto.ToothCheckDto)   
	 */
	@Override
	public void delete(ToothCheckDto toothCheckDto) throws TsfaServiceException {
		logger.debug("delete(ToothCheckDto toothCheckDto = {})-start",toothCheckDto);
		try {
			toothCheckDao.deleteByPrimaryKey(toothCheckDto.getCode());
		} catch (Exception e) {
			logger.error("删除牙齿检查信息错误!",e);
			throw new TsfaServiceException(ErrorCode.TOOTH_CHECK_DELETE_ERROR,"删除牙齿检查信息错误!",e);
		}
		
	}


	
	@Override
	public List<Date> findTimeList(FindToothCheckPage findToothCheckPage) throws TsfaServiceException {
		logger.debug("findTimeList(FindToothCheckPage findToothCheckPage = {})-start",findToothCheckPage);
		try {
			List<Date> list = toothCheckDao.findTimeList(findToothCheckPage);
			return list;
		} catch (Exception e) {
			logger.error("查询时间列表信息错误!",e);
			throw new TsfaServiceException(ErrorCode.TOOTH_CHECK_TIME_LIST_ERROR,"查询时间列表信息错误!",e);
		}
	} 


}
