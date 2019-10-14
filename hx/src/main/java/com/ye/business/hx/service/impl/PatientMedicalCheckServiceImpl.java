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
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;

import com.ye.business.hx.dto.PatientMedicalCheckDto;
import com.ye.business.hx.dto.FindPatientMedicalCheckPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientMedicalCheckDao;
import com.ye.business.hx.domain.PatientMedicalCheck;
import com.ye.business.hx.service.IPatientMedicalCheckService;
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
public class PatientMedicalCheckServiceImpl implements IPatientMedicalCheckService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientMedicalCheckServiceImpl.class);
	

	@Resource
	private IPatientMedicalCheckDao patientMedicalCheckDao;
	
	
	@Override
	public void addPatientMedicalCheck(
			PatientMedicalCheckDto patientMedicalCheckDto) throws TsfaServiceException {
		logger.debug("addPatientMedicalCheck(AddPatientMedicalCheck addPatientMedicalCheck={}) - start", patientMedicalCheckDto); 

		AssertUtils.notNull(patientMedicalCheckDto);
		try {
			PatientMedicalCheck patientMedicalCheck = new PatientMedicalCheck();
			//add数据录入
			patientMedicalCheck.setCode(GUID.generateCode());
			patientMedicalCheck.setMedicalCode(patientMedicalCheckDto.getMedicalCode());
			patientMedicalCheck.setCheckOralRemark(patientMedicalCheckDto.getCheckOralRemark());
			patientMedicalCheck.setCheckAuxiliaryRemark(patientMedicalCheckDto.getCheckAuxiliaryRemark());
			patientMedicalCheck.setCreateDate(new Date());
			patientMedicalCheck.setCreateId(patientMedicalCheckDto.getCreateId());
			patientMedicalCheck.setCreateName(patientMedicalCheckDto.getCreateName());
			patientMedicalCheck.setRemark(patientMedicalCheckDto.getRemark());
			patientMedicalCheck.setRemark2(patientMedicalCheckDto.getRemark2());
			patientMedicalCheck.setRemark3(patientMedicalCheckDto.getRemark3());
			patientMedicalCheck.setRemark4(patientMedicalCheckDto.getRemark4());
			patientMedicalCheck.setDentalPosition(patientMedicalCheckDto.getDentalPosition());
			patientMedicalCheck.setDentalSurface(patientMedicalCheckDto.getDentalSurface());
			patientMedicalCheckDao.insertSelective(patientMedicalCheck);
			logger.debug("addPatientMedicalCheck(PatientMedicalCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增病历检查信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_CHECK_ADD_ERROR,"新增病历检查信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询病历检查信息
	 *
	 * @param findPatientMedicalCheckPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientMedicalCheckDto>  findPatientMedicalChecks(FindPatientMedicalCheckPage findPatientMedicalCheckPage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientMedicalCheckPage);
		List<PatientMedicalCheckDto> returnList=null;
		try {
			returnList = patientMedicalCheckDao.findPatientMedicalChecks(findPatientMedicalCheckPage);
		} catch (Exception e) {
			logger.error("查找病历检查信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_CHECK_NOT_EXIST_ERROR,"病历检查信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientMedicalCheck(
			PatientMedicalCheckDto patientMedicalCheckDto)
			throws TsfaServiceException {
		logger.debug("updatePatientMedicalCheck(PatientMedicalCheckDto patientMedicalCheckDto={}) - start", patientMedicalCheckDto); 

		AssertUtils.notNull(patientMedicalCheckDto);
		AssertUtils.notNullAndEmpty(patientMedicalCheckDto.getCode(),"Code不能为空");
		try {
			PatientMedicalCheck patientMedicalCheck = new PatientMedicalCheck();
			//update数据录入
			patientMedicalCheck.setCode(patientMedicalCheckDto.getCode());
			patientMedicalCheck.setMedicalCode(patientMedicalCheckDto.getMedicalCode());
			patientMedicalCheck.setCheckOralRemark(patientMedicalCheckDto.getCheckOralRemark());
			patientMedicalCheck.setCheckAuxiliaryRemark(patientMedicalCheckDto.getCheckAuxiliaryRemark());
			patientMedicalCheck.setRemark(patientMedicalCheckDto.getRemark());
			patientMedicalCheck.setRemark2(patientMedicalCheckDto.getRemark2());
			patientMedicalCheck.setRemark3(patientMedicalCheckDto.getRemark3());
			patientMedicalCheck.setRemark4(patientMedicalCheckDto.getRemark4());
			patientMedicalCheck.setUpdateId(patientMedicalCheckDto.getUpdateId());
			patientMedicalCheck.setUpdateName(patientMedicalCheckDto.getUpdateName());
			patientMedicalCheck.setUpdateDate(new Date());
			patientMedicalCheck.setDentalPosition(patientMedicalCheckDto.getDentalPosition());
			patientMedicalCheck.setDentalSurface(patientMedicalCheckDto.getDentalSurface());
			AssertUtils.notUpdateMoreThanOne(patientMedicalCheckDao.updateByPrimaryKeySelective(patientMedicalCheck));
			logger.debug("updatePatientMedicalCheck(PatientMedicalCheckDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("病历检查信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_CHECK_UPDATE_ERROR,"病历检查信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientMedicalCheckDto findPatientMedicalCheck(
			PatientMedicalCheckDto patientMedicalCheckDto) throws TsfaServiceException {
		logger.debug("findPatientMedicalCheck(FindPatientMedicalCheck findPatientMedicalCheck={}) - start", patientMedicalCheckDto); 

		AssertUtils.notNull(patientMedicalCheckDto);
		AssertUtils.notAllNull(patientMedicalCheckDto.getCode(),"Code不能为空");
		try {
			PatientMedicalCheck patientMedicalCheck = patientMedicalCheckDao.selectByPrimaryKey(patientMedicalCheckDto.getCode());
			if(patientMedicalCheck == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_CHECK_NOT_EXIST_ERROR,"病历检查信息不存在");
			}
			PatientMedicalCheckDto findPatientMedicalCheckReturn = new PatientMedicalCheckDto();
			//find数据录入
			findPatientMedicalCheckReturn.setCode(patientMedicalCheck.getCode());
			findPatientMedicalCheckReturn.setMedicalCode(patientMedicalCheck.getMedicalCode());
			findPatientMedicalCheckReturn.setCheckOralRemark(patientMedicalCheck.getCheckOralRemark());
			findPatientMedicalCheckReturn.setCheckAuxiliaryRemark(patientMedicalCheck.getCheckAuxiliaryRemark());
			findPatientMedicalCheckReturn.setCreateDate(patientMedicalCheck.getCreateDate());
			findPatientMedicalCheckReturn.setCreateId(patientMedicalCheck.getCreateId());
			findPatientMedicalCheckReturn.setCreateName(patientMedicalCheck.getCreateName());
			findPatientMedicalCheckReturn.setRemark(patientMedicalCheck.getRemark());
			findPatientMedicalCheckReturn.setRemark2(patientMedicalCheck.getRemark2());
			findPatientMedicalCheckReturn.setRemark3(patientMedicalCheck.getRemark3());
			findPatientMedicalCheckReturn.setRemark4(patientMedicalCheck.getRemark4());
			findPatientMedicalCheckReturn.setUpdateId(patientMedicalCheck.getUpdateId());
			findPatientMedicalCheckReturn.setUpdateName(patientMedicalCheck.getUpdateName());
			findPatientMedicalCheckReturn.setUpdateDate(patientMedicalCheck.getUpdateDate());
			findPatientMedicalCheckReturn.setDentalPosition(patientMedicalCheck.getDentalPosition());
			findPatientMedicalCheckReturn.setDentalSurface(patientMedicalCheck.getDentalSurface());
			
			logger.debug("findPatientMedicalCheck(PatientMedicalCheckDto) - end - return value={}", findPatientMedicalCheckReturn); 
			return findPatientMedicalCheckReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找病历检查信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_CHECK_FIND_ERROR,"查找病历检查信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientMedicalCheckDto> findPatientMedicalCheckPage(
			FindPatientMedicalCheckPage findPatientMedicalCheckPage)
			throws TsfaServiceException {
		logger.debug("findPatientMedicalCheckPage(FindPatientMedicalCheckPage findPatientMedicalCheckPage={}) - start", findPatientMedicalCheckPage); 

		AssertUtils.notNull(findPatientMedicalCheckPage);
		List<PatientMedicalCheckDto> returnList=null;
		int count = 0;
		try {
			count = patientMedicalCheckDao.findPatientMedicalCheckPageCount(findPatientMedicalCheckPage);
			if (count > 0) {
				returnList = patientMedicalCheckDao.findPatientMedicalCheckPage(findPatientMedicalCheckPage);
			}
		}  catch (Exception e) {
			logger.error("病历检查信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_CHECK_FIND_PAGE_ERROR,"病历检查信息不存在错误.！",e);
		}
		Page<PatientMedicalCheckDto> returnPage = new Page<PatientMedicalCheckDto>(returnList, count, findPatientMedicalCheckPage);

		logger.debug("findPatientMedicalCheckPage(FindPatientMedicalCheckPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
