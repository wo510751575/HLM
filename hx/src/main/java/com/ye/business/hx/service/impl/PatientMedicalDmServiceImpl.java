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

import com.ye.business.hx.dto.PatientMedicalDmDto;
import com.ye.business.hx.dto.FindPatientMedicalDmPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientMedicalDmDao;
import com.ye.business.hx.domain.PatientMedicalDm;
import com.ye.business.hx.service.IPatientMedicalDmService;
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
public class PatientMedicalDmServiceImpl implements IPatientMedicalDmService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientMedicalDmServiceImpl.class);
	

	@Resource
	private IPatientMedicalDmDao patientMedicalDmDao;
	
	
	@Override
	public void addPatientMedicalDm(
			PatientMedicalDmDto patientMedicalDmDto) throws TsfaServiceException {
		logger.debug("addPatientMedicalDm(AddPatientMedicalDm addPatientMedicalDm={}) - start", patientMedicalDmDto); 

		AssertUtils.notNull(patientMedicalDmDto);
		try {
			PatientMedicalDm patientMedicalDm = new PatientMedicalDm();
			//add数据录入
			patientMedicalDm.setCode(GUID.generateCode());
			patientMedicalDm.setMedicalCode(patientMedicalDmDto.getMedicalCode());
			patientMedicalDm.setCreateDate(new Date());
			patientMedicalDm.setCreateId(patientMedicalDmDto.getCreateId());
			patientMedicalDm.setCreateName(patientMedicalDmDto.getCreateName());
			patientMedicalDm.setRemark(patientMedicalDmDto.getRemark());
			patientMedicalDm.setRemark2(patientMedicalDmDto.getRemark2());
			patientMedicalDm.setRemark3(patientMedicalDmDto.getRemark3());
			patientMedicalDm.setRemark4(patientMedicalDmDto.getRemark4());
			patientMedicalDm.setDentalPosition(patientMedicalDmDto.getDentalPosition());
			patientMedicalDm.setDentalSurface(patientMedicalDmDto.getDentalSurface());
			patientMedicalDm.setDmDisposalRemark(patientMedicalDmDto.getDmDisposalRemark());
			patientMedicalDmDao.insertSelective(patientMedicalDm);
			logger.debug("addPatientMedicalDm(PatientMedicalDmDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增处置与医嘱信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_DM_ADD_ERROR,"新增处置与医嘱信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询处置与医嘱信息
	 *
	 * @param findPatientMedicalDmPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientMedicalDmDto>  findPatientMedicalDms(FindPatientMedicalDmPage findPatientMedicalDmPage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientMedicalDmPage);
		List<PatientMedicalDmDto> returnList=null;
		try {
			returnList = patientMedicalDmDao.findPatientMedicalDms(findPatientMedicalDmPage);
		} catch (Exception e) {
			logger.error("查找处置与医嘱信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_DM_NOT_EXIST_ERROR,"处置与医嘱信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientMedicalDm(
			PatientMedicalDmDto patientMedicalDmDto)
			throws TsfaServiceException {
		logger.debug("updatePatientMedicalDm(PatientMedicalDmDto patientMedicalDmDto={}) - start", patientMedicalDmDto); 

		AssertUtils.notNull(patientMedicalDmDto);
		AssertUtils.notNullAndEmpty(patientMedicalDmDto.getCode(),"Code不能为空");
		try {
			PatientMedicalDm patientMedicalDm = new PatientMedicalDm();
			//update数据录入
			patientMedicalDm.setCode(patientMedicalDmDto.getCode());
			patientMedicalDm.setMedicalCode(patientMedicalDmDto.getMedicalCode());
			patientMedicalDm.setRemark(patientMedicalDmDto.getRemark());
			patientMedicalDm.setRemark2(patientMedicalDmDto.getRemark2());
			patientMedicalDm.setRemark3(patientMedicalDmDto.getRemark3());
			patientMedicalDm.setRemark4(patientMedicalDmDto.getRemark4());
			patientMedicalDm.setUpdateId(patientMedicalDmDto.getUpdateId());
			patientMedicalDm.setUpdateName(patientMedicalDmDto.getUpdateName());
			patientMedicalDm.setUpdateDate(new Date());
			patientMedicalDm.setDentalPosition(patientMedicalDmDto.getDentalPosition());
			patientMedicalDm.setDentalSurface(patientMedicalDmDto.getDentalSurface());
			patientMedicalDm.setDmDisposalRemark(patientMedicalDmDto.getDmDisposalRemark());
			AssertUtils.notUpdateMoreThanOne(patientMedicalDmDao.updateByPrimaryKeySelective(patientMedicalDm));
			logger.debug("updatePatientMedicalDm(PatientMedicalDmDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("处置与医嘱信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_DM_UPDATE_ERROR,"处置与医嘱信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientMedicalDmDto findPatientMedicalDm(
			PatientMedicalDmDto patientMedicalDmDto) throws TsfaServiceException {
		logger.debug("findPatientMedicalDm(FindPatientMedicalDm findPatientMedicalDm={}) - start", patientMedicalDmDto); 

		AssertUtils.notNull(patientMedicalDmDto);
		AssertUtils.notAllNull(patientMedicalDmDto.getCode(),"Code不能为空");
		try {
			PatientMedicalDm patientMedicalDm = patientMedicalDmDao.selectByPrimaryKey(patientMedicalDmDto.getCode());
			if(patientMedicalDm == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_DM_NOT_EXIST_ERROR,"处置与医嘱信息不存在");
			}
			PatientMedicalDmDto findPatientMedicalDmReturn = new PatientMedicalDmDto();
			//find数据录入
			findPatientMedicalDmReturn.setCode(patientMedicalDm.getCode());
			findPatientMedicalDmReturn.setMedicalCode(patientMedicalDm.getMedicalCode());
			findPatientMedicalDmReturn.setCreateDate(patientMedicalDm.getCreateDate());
			findPatientMedicalDmReturn.setCreateId(patientMedicalDm.getCreateId());
			findPatientMedicalDmReturn.setCreateName(patientMedicalDm.getCreateName());
			findPatientMedicalDmReturn.setRemark(patientMedicalDm.getRemark());
			findPatientMedicalDmReturn.setRemark2(patientMedicalDm.getRemark2());
			findPatientMedicalDmReturn.setRemark3(patientMedicalDm.getRemark3());
			findPatientMedicalDmReturn.setRemark4(patientMedicalDm.getRemark4());
			findPatientMedicalDmReturn.setUpdateId(patientMedicalDm.getUpdateId());
			findPatientMedicalDmReturn.setUpdateName(patientMedicalDm.getUpdateName());
			findPatientMedicalDmReturn.setUpdateDate(patientMedicalDm.getUpdateDate());
			findPatientMedicalDmReturn.setDentalPosition(patientMedicalDm.getDentalPosition());
			findPatientMedicalDmReturn.setDentalSurface(patientMedicalDm.getDentalSurface());
			findPatientMedicalDmReturn.setDmDisposalRemark(patientMedicalDm.getDmDisposalRemark());
			
			logger.debug("findPatientMedicalDm(PatientMedicalDmDto) - end - return value={}", findPatientMedicalDmReturn); 
			return findPatientMedicalDmReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找处置与医嘱信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_DM_FIND_ERROR,"查找处置与医嘱信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientMedicalDmDto> findPatientMedicalDmPage(
			FindPatientMedicalDmPage findPatientMedicalDmPage)
			throws TsfaServiceException {
		logger.debug("findPatientMedicalDmPage(FindPatientMedicalDmPage findPatientMedicalDmPage={}) - start", findPatientMedicalDmPage); 

		AssertUtils.notNull(findPatientMedicalDmPage);
		List<PatientMedicalDmDto> returnList=null;
		int count = 0;
		try {
			count = patientMedicalDmDao.findPatientMedicalDmPageCount(findPatientMedicalDmPage);
			if (count > 0) {
				returnList = patientMedicalDmDao.findPatientMedicalDmPage(findPatientMedicalDmPage);
			}
		}  catch (Exception e) {
			logger.error("处置与医嘱信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_DM_FIND_PAGE_ERROR,"处置与医嘱信息不存在错误.！",e);
		}
		Page<PatientMedicalDmDto> returnPage = new Page<PatientMedicalDmDto>(returnList, count, findPatientMedicalDmPage);

		logger.debug("findPatientMedicalDmPage(FindPatientMedicalDmPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
