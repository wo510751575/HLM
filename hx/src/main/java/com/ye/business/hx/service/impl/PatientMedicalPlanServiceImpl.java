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

import com.ye.business.hx.dto.PatientMedicalPlanDto;
import com.ye.business.hx.dto.FindPatientMedicalPlanPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientMedicalPlanDao;
import com.ye.business.hx.domain.PatientMedicalPlan;
import com.ye.business.hx.service.IPatientMedicalPlanService;
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
public class PatientMedicalPlanServiceImpl implements IPatientMedicalPlanService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientMedicalPlanServiceImpl.class);
	

	@Resource
	private IPatientMedicalPlanDao patientMedicalPlanDao;
	
	
	@Override
	public void addPatientMedicalPlan(
			PatientMedicalPlanDto patientMedicalPlanDto) throws TsfaServiceException {
		logger.debug("addPatientMedicalPlan(AddPatientMedicalPlan addPatientMedicalPlan={}) - start", patientMedicalPlanDto); 

		AssertUtils.notNull(patientMedicalPlanDto);
		try {
			PatientMedicalPlan patientMedicalPlan = new PatientMedicalPlan();
			//add数据录入
			patientMedicalPlan.setCode(GUID.generateCode());
			patientMedicalPlan.setMedicalCode(patientMedicalPlanDto.getMedicalCode());
			patientMedicalPlan.setCreateDate(new Date());
			patientMedicalPlan.setCreateId(patientMedicalPlanDto.getCreateId());
			patientMedicalPlan.setCreateName(patientMedicalPlanDto.getCreateName());
			patientMedicalPlan.setRemark(patientMedicalPlanDto.getRemark());
			patientMedicalPlan.setRemark2(patientMedicalPlanDto.getRemark2());
			patientMedicalPlan.setRemark3(patientMedicalPlanDto.getRemark3());
			patientMedicalPlan.setRemark4(patientMedicalPlanDto.getRemark4());
			patientMedicalPlan.setDentalPosition(patientMedicalPlanDto.getDentalPosition());
			patientMedicalPlan.setDentalSurface(patientMedicalPlanDto.getDentalSurface());
			patientMedicalPlan.setPlanDiagnosisRemark(patientMedicalPlanDto.getPlanDiagnosisRemark());
			patientMedicalPlan.setPlanTreatmentRemark(patientMedicalPlanDto.getPlanTreatmentRemark());
			patientMedicalPlanDao.insertSelective(patientMedicalPlan);
			logger.debug("addPatientMedicalPlan(PatientMedicalPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增诊断与治疗计划信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_PLAN_ADD_ERROR,"新增诊断与治疗计划信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询诊断与治疗计划信息
	 *
	 * @param findPatientMedicalPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientMedicalPlanDto>  findPatientMedicalPlans(FindPatientMedicalPlanPage findPatientMedicalPlanPage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientMedicalPlanPage);
		List<PatientMedicalPlanDto> returnList=null;
		try {
			returnList = patientMedicalPlanDao.findPatientMedicalPlans(findPatientMedicalPlanPage);
		} catch (Exception e) {
			logger.error("查找诊断与治疗计划信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_PLAN_NOT_EXIST_ERROR,"诊断与治疗计划信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientMedicalPlan(
			PatientMedicalPlanDto patientMedicalPlanDto)
			throws TsfaServiceException {
		logger.debug("updatePatientMedicalPlan(PatientMedicalPlanDto patientMedicalPlanDto={}) - start", patientMedicalPlanDto); 

		AssertUtils.notNull(patientMedicalPlanDto);
		AssertUtils.notNullAndEmpty(patientMedicalPlanDto.getCode(),"Code不能为空");
		try {
			PatientMedicalPlan patientMedicalPlan = new PatientMedicalPlan();
			//update数据录入
			patientMedicalPlan.setCode(patientMedicalPlanDto.getCode());
			patientMedicalPlan.setMedicalCode(patientMedicalPlanDto.getMedicalCode());
			patientMedicalPlan.setRemark(patientMedicalPlanDto.getRemark());
			patientMedicalPlan.setRemark2(patientMedicalPlanDto.getRemark2());
			patientMedicalPlan.setRemark3(patientMedicalPlanDto.getRemark3());
			patientMedicalPlan.setRemark4(patientMedicalPlanDto.getRemark4());
			patientMedicalPlan.setUpdateId(patientMedicalPlanDto.getUpdateId());
			patientMedicalPlan.setUpdateName(patientMedicalPlanDto.getUpdateName());
			patientMedicalPlan.setUpdateDate(new Date());
			patientMedicalPlan.setDentalPosition(patientMedicalPlanDto.getDentalPosition());
			patientMedicalPlan.setDentalSurface(patientMedicalPlanDto.getDentalSurface());
			patientMedicalPlan.setPlanDiagnosisRemark(patientMedicalPlanDto.getPlanDiagnosisRemark());
			patientMedicalPlan.setPlanTreatmentRemark(patientMedicalPlanDto.getPlanTreatmentRemark());
			AssertUtils.notUpdateMoreThanOne(patientMedicalPlanDao.updateByPrimaryKeySelective(patientMedicalPlan));
			logger.debug("updatePatientMedicalPlan(PatientMedicalPlanDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("诊断与治疗计划信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_PLAN_UPDATE_ERROR,"诊断与治疗计划信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientMedicalPlanDto findPatientMedicalPlan(
			PatientMedicalPlanDto patientMedicalPlanDto) throws TsfaServiceException {
		logger.debug("findPatientMedicalPlan(FindPatientMedicalPlan findPatientMedicalPlan={}) - start", patientMedicalPlanDto); 

		AssertUtils.notNull(patientMedicalPlanDto);
		AssertUtils.notAllNull(patientMedicalPlanDto.getCode(),"Code不能为空");
		try {
			PatientMedicalPlan patientMedicalPlan = patientMedicalPlanDao.selectByPrimaryKey(patientMedicalPlanDto.getCode());
			if(patientMedicalPlan == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_PLAN_NOT_EXIST_ERROR,"诊断与治疗计划信息不存在");
			}
			PatientMedicalPlanDto findPatientMedicalPlanReturn = new PatientMedicalPlanDto();
			//find数据录入
			findPatientMedicalPlanReturn.setCode(patientMedicalPlan.getCode());
			findPatientMedicalPlanReturn.setMedicalCode(patientMedicalPlan.getMedicalCode());
			findPatientMedicalPlanReturn.setCreateDate(patientMedicalPlan.getCreateDate());
			findPatientMedicalPlanReturn.setCreateId(patientMedicalPlan.getCreateId());
			findPatientMedicalPlanReturn.setCreateName(patientMedicalPlan.getCreateName());
			findPatientMedicalPlanReturn.setRemark(patientMedicalPlan.getRemark());
			findPatientMedicalPlanReturn.setRemark2(patientMedicalPlan.getRemark2());
			findPatientMedicalPlanReturn.setRemark3(patientMedicalPlan.getRemark3());
			findPatientMedicalPlanReturn.setRemark4(patientMedicalPlan.getRemark4());
			findPatientMedicalPlanReturn.setUpdateId(patientMedicalPlan.getUpdateId());
			findPatientMedicalPlanReturn.setUpdateName(patientMedicalPlan.getUpdateName());
			findPatientMedicalPlanReturn.setUpdateDate(patientMedicalPlan.getUpdateDate());
			findPatientMedicalPlanReturn.setDentalPosition(patientMedicalPlan.getDentalPosition());
			findPatientMedicalPlanReturn.setDentalSurface(patientMedicalPlan.getDentalSurface());
			findPatientMedicalPlanReturn.setPlanDiagnosisRemark(patientMedicalPlan.getPlanDiagnosisRemark());
			findPatientMedicalPlanReturn.setPlanTreatmentRemark(patientMedicalPlan.getPlanTreatmentRemark());
			
			logger.debug("findPatientMedicalPlan(PatientMedicalPlanDto) - end - return value={}", findPatientMedicalPlanReturn); 
			return findPatientMedicalPlanReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找诊断与治疗计划信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_PLAN_FIND_ERROR,"查找诊断与治疗计划信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientMedicalPlanDto> findPatientMedicalPlanPage(
			FindPatientMedicalPlanPage findPatientMedicalPlanPage)
			throws TsfaServiceException {
		logger.debug("findPatientMedicalPlanPage(FindPatientMedicalPlanPage findPatientMedicalPlanPage={}) - start", findPatientMedicalPlanPage); 

		AssertUtils.notNull(findPatientMedicalPlanPage);
		List<PatientMedicalPlanDto> returnList=null;
		int count = 0;
		try {
			count = patientMedicalPlanDao.findPatientMedicalPlanPageCount(findPatientMedicalPlanPage);
			if (count > 0) {
				returnList = patientMedicalPlanDao.findPatientMedicalPlanPage(findPatientMedicalPlanPage);
			}
		}  catch (Exception e) {
			logger.error("诊断与治疗计划信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_PLAN_FIND_PAGE_ERROR,"诊断与治疗计划信息不存在错误.！",e);
		}
		Page<PatientMedicalPlanDto> returnPage = new Page<PatientMedicalPlanDto>(returnList, count, findPatientMedicalPlanPage);

		logger.debug("findPatientMedicalPlanPage(FindPatientMedicalPlanPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
