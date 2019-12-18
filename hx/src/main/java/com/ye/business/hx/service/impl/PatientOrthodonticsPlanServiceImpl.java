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
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientOrthodonticsPlanDao;
import com.ye.business.hx.domain.PatientOrthodonticsPlan;
import com.ye.business.hx.dto.FindPatientOrthodonticsPlanPage;
import com.ye.business.hx.dto.PatientOrthodonticsPlanDto;
import com.ye.business.hx.service.IPatientOrthodonticsPlanService;
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
public class PatientOrthodonticsPlanServiceImpl implements IPatientOrthodonticsPlanService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientOrthodonticsPlanServiceImpl.class);
	

	@Resource
	private IPatientOrthodonticsPlanDao patientOrthodonticsPlanDao;
	
	
	@Override
	public void addPatientOrthodonticsPlan(
			PatientOrthodonticsPlanDto patientOrthodonticsPlanDto) throws TsfaServiceException {
		logger.debug("addPatientOrthodonticsPlan(AddPatientOrthodonticsPlan addPatientOrthodonticsPlan={}) - start", patientOrthodonticsPlanDto); 

		AssertUtils.notNull(patientOrthodonticsPlanDto);
		try {
			PatientOrthodonticsPlan patientOrthodonticsPlan = new PatientOrthodonticsPlan();
			//add数据录入
			patientOrthodonticsPlan.setCode(GUID.generateByUUID());
			patientOrthodonticsPlan.setChiefComplaint(patientOrthodonticsPlanDto.getChiefComplaint());
			patientOrthodonticsPlan.setProblem(patientOrthodonticsPlanDto.getProblem());
			patientOrthodonticsPlan.setTarget(patientOrthodonticsPlanDto.getTarget());
			patientOrthodonticsPlan.setToothType(patientOrthodonticsPlanDto.getToothType());
			patientOrthodonticsPlan.setDentalBone(patientOrthodonticsPlanDto.getDentalBone());
			patientOrthodonticsPlan.setTreatmentPlan(patientOrthodonticsPlanDto.getTreatmentPlan());
			patientOrthodonticsPlan.setSure(patientOrthodonticsPlanDto.getSure());
			patientOrthodonticsPlan.setPatientNo(patientOrthodonticsPlanDto.getPatientNo());
			patientOrthodonticsPlan.setRemark(patientOrthodonticsPlanDto.getRemark());
			patientOrthodonticsPlanDao.insertSelective(patientOrthodonticsPlan);
			logger.debug("addPatientOrthodonticsPlan(PatientOrthodonticsPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增正畸病历-正畸计划信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_ORTHODONTICS_PLAN_ADD_ERROR,"新增正畸病历-正畸计划信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸病历-正畸计划信息
	 *
	 * @param findPatientOrthodonticsPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientOrthodonticsPlanDto>  findPatientOrthodonticsPlans(FindPatientOrthodonticsPlanPage findPatientOrthodonticsPlanPage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientOrthodonticsPlanPage);
		List<PatientOrthodonticsPlanDto> returnList=null;
		try {
			returnList = patientOrthodonticsPlanDao.findPatientOrthodonticsPlans(findPatientOrthodonticsPlanPage);
		} catch (Exception e) {
			logger.error("查找正畸病历-正畸计划信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_ORTHODONTICS_PLAN_NOT_EXIST_ERROR,"正畸病历-正畸计划信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientOrthodonticsPlan(
			PatientOrthodonticsPlanDto patientOrthodonticsPlanDto)
			throws TsfaServiceException {
		logger.debug("updatePatientOrthodonticsPlan(PatientOrthodonticsPlanDto patientOrthodonticsPlanDto={}) - start", patientOrthodonticsPlanDto); 

		AssertUtils.notNull(patientOrthodonticsPlanDto);
		try {
			PatientOrthodonticsPlan patientOrthodonticsPlan = new PatientOrthodonticsPlan();
			//update数据录入
			patientOrthodonticsPlan.setCode(patientOrthodonticsPlanDto.getCode());
			patientOrthodonticsPlan.setChiefComplaint(patientOrthodonticsPlanDto.getChiefComplaint());
			patientOrthodonticsPlan.setProblem(patientOrthodonticsPlanDto.getProblem());
			patientOrthodonticsPlan.setTarget(patientOrthodonticsPlanDto.getTarget());
			patientOrthodonticsPlan.setToothType(patientOrthodonticsPlanDto.getToothType());
			patientOrthodonticsPlan.setDentalBone(patientOrthodonticsPlanDto.getDentalBone());
			patientOrthodonticsPlan.setTreatmentPlan(patientOrthodonticsPlanDto.getTreatmentPlan());
			patientOrthodonticsPlan.setSure(patientOrthodonticsPlanDto.getSure());
			patientOrthodonticsPlan.setPatientNo(patientOrthodonticsPlanDto.getPatientNo());
			patientOrthodonticsPlan.setRemark(patientOrthodonticsPlanDto.getRemark());
			AssertUtils.notUpdateMoreThanOne(patientOrthodonticsPlanDao.updateByPrimaryKeySelective(patientOrthodonticsPlan));
			logger.debug("updatePatientOrthodonticsPlan(PatientOrthodonticsPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("正畸病历-正畸计划信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_ORTHODONTICS_PLAN_UPDATE_ERROR,"正畸病历-正畸计划信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientOrthodonticsPlanDto findPatientOrthodonticsPlan(
			PatientOrthodonticsPlanDto patientOrthodonticsPlanDto) throws TsfaServiceException {
		logger.debug("findPatientOrthodonticsPlan(FindPatientOrthodonticsPlan findPatientOrthodonticsPlan={}) - start", patientOrthodonticsPlanDto); 

		AssertUtils.notNull(patientOrthodonticsPlanDto);
		try {
			PatientOrthodonticsPlan patientOrthodonticsPlan = patientOrthodonticsPlanDao.selectByPrimaryKey(patientOrthodonticsPlanDto.getPatientNo());
			if(patientOrthodonticsPlan == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_ORTHODONTICS_PLAN_NOT_EXIST_ERROR,"正畸病历-正畸计划信息不存在");
			}
			PatientOrthodonticsPlanDto findPatientOrthodonticsPlanReturn = new PatientOrthodonticsPlanDto();
			//find数据录入
			findPatientOrthodonticsPlanReturn.setCode(patientOrthodonticsPlan.getCode());
			findPatientOrthodonticsPlanReturn.setChiefComplaint(patientOrthodonticsPlan.getChiefComplaint());
			findPatientOrthodonticsPlanReturn.setProblem(patientOrthodonticsPlan.getProblem());
			findPatientOrthodonticsPlanReturn.setTarget(patientOrthodonticsPlan.getTarget());
			findPatientOrthodonticsPlanReturn.setToothType(patientOrthodonticsPlan.getToothType());
			findPatientOrthodonticsPlanReturn.setDentalBone(patientOrthodonticsPlan.getDentalBone());
			findPatientOrthodonticsPlanReturn.setTreatmentPlan(patientOrthodonticsPlan.getTreatmentPlan());
			findPatientOrthodonticsPlanReturn.setSure(patientOrthodonticsPlan.getSure());
			findPatientOrthodonticsPlanReturn.setPatientNo(patientOrthodonticsPlan.getPatientNo());
			findPatientOrthodonticsPlanReturn.setRemark(patientOrthodonticsPlan.getRemark());
			
			logger.debug("findPatientOrthodonticsPlan(PatientOrthodonticsPlanDto) - end - return value={}", findPatientOrthodonticsPlanReturn); 
			return findPatientOrthodonticsPlanReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找正畸病历-正畸计划信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_ORTHODONTICS_PLAN_FIND_ERROR,"查找正畸病历-正畸计划信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientOrthodonticsPlanDto> findPatientOrthodonticsPlanPage(
			FindPatientOrthodonticsPlanPage findPatientOrthodonticsPlanPage)
			throws TsfaServiceException {
		logger.debug("findPatientOrthodonticsPlanPage(FindPatientOrthodonticsPlanPage findPatientOrthodonticsPlanPage={}) - start", findPatientOrthodonticsPlanPage); 

		AssertUtils.notNull(findPatientOrthodonticsPlanPage);
		List<PatientOrthodonticsPlanDto> returnList=null;
		int count = 0;
		try {
			returnList = patientOrthodonticsPlanDao.findPatientOrthodonticsPlanPage(findPatientOrthodonticsPlanPage);
			count = patientOrthodonticsPlanDao.findPatientOrthodonticsPlanPageCount(findPatientOrthodonticsPlanPage);
		}  catch (Exception e) {
			logger.error("正畸病历-正畸计划信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_ORTHODONTICS_PLAN_FIND_PAGE_ERROR,"正畸病历-正畸计划信息不存在错误.！",e);
		}
		Page<PatientOrthodonticsPlanDto> returnPage = new Page<PatientOrthodonticsPlanDto>(returnList, count, findPatientOrthodonticsPlanPage);

		logger.debug("findPatientOrthodonticsPlanPage(FindPatientOrthodonticsPlanPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
