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
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;

import com.ye.business.hx.dto.GeneralCheckDto;
import com.ye.business.hx.dto.FindGeneralCheckPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IGeneralCheckDao;
import com.ye.business.hx.domain.GeneralCheck;
import com.ye.business.hx.service.IGeneralCheckService;
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
public class GeneralCheckServiceImpl implements IGeneralCheckService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GeneralCheckServiceImpl.class);
	

	@Resource
	private IGeneralCheckDao generalCheckDao;
	
	
	@Override
	public void addGeneralCheck(
			GeneralCheckDto generalCheckDto) throws TsfaServiceException {
		logger.debug("addGeneralCheck(AddGeneralCheck addGeneralCheck={}) - start", generalCheckDto); 

		AssertUtils.notNull(generalCheckDto);
		try {
			GeneralCheck generalCheck = new GeneralCheck();
			//add数据录入
			generalCheck.setCode(generalCheckDto.getCode());
			generalCheck.setChiefComplaint(generalCheckDto.getChiefComplaint());
			generalCheck.setBadHabits(generalCheckDto.getBadHabits());
			generalCheck.setOralHygiene(generalCheckDto.getOralHygiene());
			generalCheck.setCervicalVertebra(generalCheckDto.getCervicalVertebra());
			generalCheck.setWristBone(generalCheckDto.getWristBone());
			generalCheck.setMenstruation(generalCheckDto.getMenstruation());
			generalCheck.setMenarche(generalCheckDto.getMenarche());
			generalCheck.setCorrectiveHistory(generalCheckDto.getCorrectiveHistory());
			generalCheck.setAttendingDoctor(generalCheckDto.getAttendingDoctor());
			generalCheck.setToothExtraction(generalCheckDto.getToothExtraction());
			generalCheck.setDentalCaries(generalCheckDto.getDentalCaries());
			generalCheck.setEarlyLoss(generalCheckDto.getEarlyLoss());
			generalCheck.setMalocclusion(generalCheckDto.getMalocclusion());
			generalCheck.setDentalCariesReplace(generalCheckDto.getDentalCariesReplace());
			generalCheck.setEarlyLossReplace(generalCheckDto.getEarlyLossReplace());
			generalCheck.setMalocclusionReplace(generalCheckDto.getMalocclusionReplace());
			generalCheck.setCleftLip(generalCheckDto.getCleftLip());
			generalCheck.setCleftPalate(generalCheckDto.getCleftPalate());
			generalCheck.setOthers(generalCheckDto.getOthers());
			generalCheck.setPatientNo(generalCheckDto.getPatientNo());
			generalCheck.setRemark(generalCheckDto.getRemark());
			generalCheck.setCreateDate(generalCheckDto.getCreateDate());
			generalCheck.setCreateId(generalCheckDto.getCreateId());
			generalCheckDao.insertSelective(generalCheck);
			logger.debug("addGeneralCheck(GeneralCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增一般检查信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GENERAL_CHECK_ADD_ERROR,"新增一般检查信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询一般检查信息
	 *
	 * @param findGeneralCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<GeneralCheckDto>  findGeneralChecks(FindGeneralCheckPage findGeneralCheckPage)throws TsfaServiceException{
		AssertUtils.notNull(findGeneralCheckPage);
		List<GeneralCheckDto> returnList=null;
		try {
			returnList = generalCheckDao.findGeneralChecks(findGeneralCheckPage);
		} catch (Exception e) {
			logger.error("查找一般检查信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.GENERAL_CHECK_NOT_EXIST_ERROR,"一般检查信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateGeneralCheck(
			GeneralCheckDto generalCheckDto)
			throws TsfaServiceException {
		logger.debug("updateGeneralCheck(GeneralCheckDto generalCheckDto={}) - start", generalCheckDto); 

		AssertUtils.notNull(generalCheckDto);
		AssertUtils.notNullAndEmpty(generalCheckDto.getCode(),"Code不能为空");
		try {
			GeneralCheck generalCheck = new GeneralCheck();
			//update数据录入
			generalCheck.setCode(generalCheckDto.getCode());
			generalCheck.setChiefComplaint(generalCheckDto.getChiefComplaint());
			generalCheck.setBadHabits(generalCheckDto.getBadHabits());
			generalCheck.setOralHygiene(generalCheckDto.getOralHygiene());
			generalCheck.setCervicalVertebra(generalCheckDto.getCervicalVertebra());
			generalCheck.setWristBone(generalCheckDto.getWristBone());
			generalCheck.setMenstruation(generalCheckDto.getMenstruation());
			generalCheck.setMenarche(generalCheckDto.getMenarche());
			generalCheck.setCorrectiveHistory(generalCheckDto.getCorrectiveHistory());
			generalCheck.setAttendingDoctor(generalCheckDto.getAttendingDoctor());
			generalCheck.setToothExtraction(generalCheckDto.getToothExtraction());
			generalCheck.setDentalCaries(generalCheckDto.getDentalCaries());
			generalCheck.setEarlyLoss(generalCheckDto.getEarlyLoss());
			generalCheck.setMalocclusion(generalCheckDto.getMalocclusion());
			generalCheck.setDentalCariesReplace(generalCheckDto.getDentalCariesReplace());
			generalCheck.setEarlyLossReplace(generalCheckDto.getEarlyLossReplace());
			generalCheck.setMalocclusionReplace(generalCheckDto.getMalocclusionReplace());
			generalCheck.setCleftLip(generalCheckDto.getCleftLip());
			generalCheck.setCleftPalate(generalCheckDto.getCleftPalate());
			generalCheck.setOthers(generalCheckDto.getOthers());
			generalCheck.setPatientNo(generalCheckDto.getPatientNo());
			generalCheck.setRemark(generalCheckDto.getRemark());
			generalCheck.setCreateDate(generalCheckDto.getCreateDate());
			generalCheck.setCreateId(generalCheckDto.getCreateId());
			AssertUtils.notUpdateMoreThanOne(generalCheckDao.updateByPrimaryKeySelective(generalCheck));
			logger.debug("updateGeneralCheck(GeneralCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("一般检查信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GENERAL_CHECK_UPDATE_ERROR,"一般检查信息更新信息错误！",e);
		}
	}

	

	@Override
	public GeneralCheckDto findGeneralCheck(
			GeneralCheckDto generalCheckDto) throws TsfaServiceException {
		logger.debug("findGeneralCheck(FindGeneralCheck findGeneralCheck={}) - start", generalCheckDto); 

		AssertUtils.notNull(generalCheckDto);
		AssertUtils.notAllNull(generalCheckDto.getCode(),"Code不能为空");
		try {
			GeneralCheck generalCheck = generalCheckDao.selectByPrimaryKey(generalCheckDto.getPatientNo());
			if(generalCheck == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.GENERAL_CHECK_NOT_EXIST_ERROR,"一般检查信息不存在");
			}
			GeneralCheckDto findGeneralCheckReturn = new GeneralCheckDto();
			//find数据录入
			findGeneralCheckReturn.setCode(generalCheck.getCode());
			findGeneralCheckReturn.setChiefComplaint(generalCheck.getChiefComplaint());
			findGeneralCheckReturn.setBadHabits(generalCheck.getBadHabits());
			findGeneralCheckReturn.setOralHygiene(generalCheck.getOralHygiene());
			findGeneralCheckReturn.setCervicalVertebra(generalCheck.getCervicalVertebra());
			findGeneralCheckReturn.setWristBone(generalCheck.getWristBone());
			findGeneralCheckReturn.setMenstruation(generalCheck.getMenstruation());
			findGeneralCheckReturn.setMenarche(generalCheck.getMenarche());
			findGeneralCheckReturn.setCorrectiveHistory(generalCheck.getCorrectiveHistory());
			findGeneralCheckReturn.setAttendingDoctor(generalCheck.getAttendingDoctor());
			findGeneralCheckReturn.setToothExtraction(generalCheck.getToothExtraction());
			findGeneralCheckReturn.setDentalCaries(generalCheck.getDentalCaries());
			findGeneralCheckReturn.setEarlyLoss(generalCheck.getEarlyLoss());
			findGeneralCheckReturn.setMalocclusion(generalCheck.getMalocclusion());
			findGeneralCheckReturn.setDentalCariesReplace(generalCheck.getDentalCariesReplace());
			findGeneralCheckReturn.setEarlyLossReplace(generalCheck.getEarlyLossReplace());
			findGeneralCheckReturn.setMalocclusionReplace(generalCheck.getMalocclusionReplace());
			findGeneralCheckReturn.setCleftLip(generalCheck.getCleftLip());
			findGeneralCheckReturn.setCleftPalate(generalCheck.getCleftPalate());
			findGeneralCheckReturn.setOthers(generalCheck.getOthers());
			findGeneralCheckReturn.setPatientNo(generalCheck.getPatientNo());
			findGeneralCheckReturn.setRemark(generalCheck.getRemark());
			findGeneralCheckReturn.setCreateDate(generalCheck.getCreateDate());
			findGeneralCheckReturn.setCreateId(generalCheck.getCreateId());
			
			logger.debug("findGeneralCheck(GeneralCheckDto) - end - return value={}", findGeneralCheckReturn); 
			return findGeneralCheckReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找一般检查信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GENERAL_CHECK_FIND_ERROR,"查找一般检查信息信息错误！",e);
		}


	}

	
	@Override
	public Page<GeneralCheckDto> findGeneralCheckPage(
			FindGeneralCheckPage findGeneralCheckPage)
			throws TsfaServiceException {
		logger.debug("findGeneralCheckPage(FindGeneralCheckPage findGeneralCheckPage={}) - start", findGeneralCheckPage); 

		AssertUtils.notNull(findGeneralCheckPage);
		List<GeneralCheckDto> returnList=null;
		int count = 0;
		try {
			returnList = generalCheckDao.findGeneralCheckPage(findGeneralCheckPage);
			count = generalCheckDao.findGeneralCheckPageCount(findGeneralCheckPage);
		}  catch (Exception e) {
			logger.error("一般检查信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GENERAL_CHECK_FIND_PAGE_ERROR,"一般检查信息不存在错误.！",e);
		}
		Page<GeneralCheckDto> returnPage = new Page<GeneralCheckDto>(returnList, count, findGeneralCheckPage);

		logger.debug("findGeneralCheckPage(FindGeneralCheckPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
