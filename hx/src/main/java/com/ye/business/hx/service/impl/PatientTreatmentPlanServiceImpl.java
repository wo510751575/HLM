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
import com.ye.business.hx.dao.IPatientTreatmentPlanDao;
import com.ye.business.hx.domain.PatientTreatmentPlan;
import com.ye.business.hx.dto.FindPatientTreatmentPlanPage;
import com.ye.business.hx.dto.PatientTreatmentPlanDto;
import com.ye.business.hx.service.IPatientTreatmentPlanService;
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
public class PatientTreatmentPlanServiceImpl implements IPatientTreatmentPlanService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientTreatmentPlanServiceImpl.class);
	

	@Resource
	private IPatientTreatmentPlanDao patientTreatmentPlanDao;
	
	
	@Override
	public void addPatientTreatmentPlan(
			PatientTreatmentPlanDto patientTreatmentPlanDto) throws TsfaServiceException {
		logger.debug("addPatientTreatmentPlan(AddPatientTreatmentPlan addPatientTreatmentPlan={}) - start", patientTreatmentPlanDto); 

		AssertUtils.notNull(patientTreatmentPlanDto);
		try {
			PatientTreatmentPlan patientTreatmentPlan = new PatientTreatmentPlan();
			//add数据录入
			patientTreatmentPlan.setCode(patientTreatmentPlanDto.getCode());
			patientTreatmentPlan.setName(patientTreatmentPlanDto.getName());
			patientTreatmentPlan.setStep(patientTreatmentPlanDto.getStep());
			patientTreatmentPlan.setAttention(patientTreatmentPlanDto.getAttention());
			patientTreatmentPlan.setExplain(patientTreatmentPlanDto.getExplain());
			patientTreatmentPlan.setPatientNo(patientTreatmentPlanDto.getPatientNo());
			patientTreatmentPlan.setSort(patientTreatmentPlanDto.getSort());
			patientTreatmentPlan.setCreateDate(patientTreatmentPlanDto.getCreateDate());
			patientTreatmentPlan.setInformedConsent(patientTreatmentPlanDto.getInformedConsent());
			patientTreatmentPlan.setInformedName(patientTreatmentPlanDto.getInformedName());
			patientTreatmentPlanDao.insertSelective(patientTreatmentPlan);
			logger.debug("addPatientTreatmentPlan(PatientTreatmentPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增正畸计划-治疗计划信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_TREATMENT_PLAN_ADD_ERROR,"新增正畸计划-治疗计划信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸计划-治疗计划信息
	 *
	 * @param findPatientTreatmentPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientTreatmentPlanDto>  findPatientTreatmentPlans(FindPatientTreatmentPlanPage findPatientTreatmentPlanPage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientTreatmentPlanPage);
		List<PatientTreatmentPlanDto> returnList=null;
		try {
			returnList = patientTreatmentPlanDao.findPatientTreatmentPlans(findPatientTreatmentPlanPage);
		} catch (Exception e) {
			logger.error("查找正畸计划-治疗计划信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_TREATMENT_PLAN_NOT_EXIST_ERROR,"正畸计划-治疗计划信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientTreatmentPlan(
			PatientTreatmentPlanDto patientTreatmentPlanDto)
			throws TsfaServiceException {
		logger.debug("updatePatientTreatmentPlan(PatientTreatmentPlanDto patientTreatmentPlanDto={}) - start", patientTreatmentPlanDto); 

		AssertUtils.notNull(patientTreatmentPlanDto);
		AssertUtils.notNullAndEmpty(patientTreatmentPlanDto.getCode(),"Code不能为空");
		try {
			PatientTreatmentPlan patientTreatmentPlan = new PatientTreatmentPlan();
			//update数据录入
			patientTreatmentPlan.setCode(patientTreatmentPlanDto.getCode());
			patientTreatmentPlan.setName(patientTreatmentPlanDto.getName());
			patientTreatmentPlan.setStep(patientTreatmentPlanDto.getStep());
			patientTreatmentPlan.setAttention(patientTreatmentPlanDto.getAttention());
			patientTreatmentPlan.setExplain(patientTreatmentPlanDto.getExplain());
			patientTreatmentPlan.setPatientNo(patientTreatmentPlanDto.getPatientNo());
			patientTreatmentPlan.setSort(patientTreatmentPlanDto.getSort());
			patientTreatmentPlan.setCreateDate(patientTreatmentPlanDto.getCreateDate());
			patientTreatmentPlan.setInformedConsent(patientTreatmentPlanDto.getInformedConsent());
			patientTreatmentPlan.setInformedName(patientTreatmentPlanDto.getInformedName());
			AssertUtils.notUpdateMoreThanOne(patientTreatmentPlanDao.updateByPrimaryKeySelective(patientTreatmentPlan));
			logger.debug("updatePatientTreatmentPlan(PatientTreatmentPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("正畸计划-治疗计划信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_TREATMENT_PLAN_UPDATE_ERROR,"正畸计划-治疗计划信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientTreatmentPlanDto findPatientTreatmentPlan(
			PatientTreatmentPlanDto patientTreatmentPlanDto) throws TsfaServiceException {
		logger.debug("findPatientTreatmentPlan(FindPatientTreatmentPlan findPatientTreatmentPlan={}) - start", patientTreatmentPlanDto); 

		AssertUtils.notNull(patientTreatmentPlanDto);
		try {
			PatientTreatmentPlan patientTreatmentPlan = patientTreatmentPlanDao.selectByPrimaryKey(patientTreatmentPlanDto.getPatientNo());
			if(patientTreatmentPlan == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_TREATMENT_PLAN_NOT_EXIST_ERROR,"正畸计划-治疗计划信息不存在");
			}
			PatientTreatmentPlanDto findPatientTreatmentPlanReturn = new PatientTreatmentPlanDto();
			//find数据录入
			findPatientTreatmentPlanReturn.setCode(patientTreatmentPlan.getCode());
			findPatientTreatmentPlanReturn.setStep(patientTreatmentPlan.getStep());
			findPatientTreatmentPlanReturn.setAttention(patientTreatmentPlan.getAttention());
			findPatientTreatmentPlanReturn.setExplain(patientTreatmentPlan.getExplain());
			findPatientTreatmentPlanReturn.setPatientNo(patientTreatmentPlan.getPatientNo());
			findPatientTreatmentPlanReturn.setSort(patientTreatmentPlan.getSort());
			findPatientTreatmentPlanReturn.setCreateDate(patientTreatmentPlan.getCreateDate());
			findPatientTreatmentPlanReturn.setInformedConsent(patientTreatmentPlan.getInformedConsent());
			
			logger.debug("findPatientTreatmentPlan(PatientTreatmentPlanDto) - end - return value={}", findPatientTreatmentPlanReturn); 
			return findPatientTreatmentPlanReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找正畸计划-治疗计划信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_TREATMENT_PLAN_FIND_ERROR,"查找正畸计划-治疗计划信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientTreatmentPlanDto> findPatientTreatmentPlanPage(
			FindPatientTreatmentPlanPage findPatientTreatmentPlanPage)
			throws TsfaServiceException {
		logger.debug("findPatientTreatmentPlanPage(FindPatientTreatmentPlanPage findPatientTreatmentPlanPage={}) - start", findPatientTreatmentPlanPage); 

		AssertUtils.notNull(findPatientTreatmentPlanPage);
		List<PatientTreatmentPlanDto> returnList=null;
		int count = 0;
		try {
			returnList = patientTreatmentPlanDao.findPatientTreatmentPlanPage(findPatientTreatmentPlanPage);
			count = patientTreatmentPlanDao.findPatientTreatmentPlanPageCount(findPatientTreatmentPlanPage);
		}  catch (Exception e) {
			logger.error("正畸计划-治疗计划信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_TREATMENT_PLAN_FIND_PAGE_ERROR,"正畸计划-治疗计划信息不存在错误.！",e);
		}
		Page<PatientTreatmentPlanDto> returnPage = new Page<PatientTreatmentPlanDto>(returnList, count, findPatientTreatmentPlanPage);

		logger.debug("findPatientTreatmentPlanPage(FindPatientTreatmentPlanPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	
	@Override
	public void del(PatientTreatmentPlanDto patientTreatmentPlanDto) {
		patientTreatmentPlanDao.deleteByPrimaryKey(patientTreatmentPlanDto.getCode());
	} 


}
