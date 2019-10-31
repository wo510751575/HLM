package com.ye.business.hx.service.impl;

import java.util.ArrayList;
import java.util.Date;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientImgDao;
import com.ye.business.hx.dao.IPatientMedicalCheckDao;
import com.ye.business.hx.dao.IPatientMedicalDao;
import com.ye.business.hx.dao.IPatientMedicalDmDao;
import com.ye.business.hx.dao.IPatientMedicalPlanDao;
import com.ye.business.hx.domain.PatientImg;
import com.ye.business.hx.domain.PatientMedical;
import com.ye.business.hx.domain.PatientMedicalCheck;
import com.ye.business.hx.domain.PatientMedicalDm;
import com.ye.business.hx.domain.PatientMedicalPlan;
import com.ye.business.hx.dto.FindPatientMedicalPage;
import com.ye.business.hx.dto.PatientImgDto;
import com.ye.business.hx.dto.PatientMedicalCheckDto;
import com.ye.business.hx.dto.PatientMedicalDmDto;
import com.ye.business.hx.dto.PatientMedicalDto;
import com.ye.business.hx.dto.PatientMedicalPlanDto;
import com.ye.business.hx.dto.PatientServiceDto;
import com.ye.business.hx.service.IPatientMedicalService;
import com.ye.business.hx.service.IPatientServiceService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
@Service
public class PatientMedicalServiceImpl implements IPatientMedicalService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientMedicalServiceImpl.class);
	

	@Resource
	private IPatientMedicalDao patientMedicalDao;
	
	// 服务预约/挂号/就诊
	@Autowired
	private IPatientServiceService patientServiceService;
	@Resource
	private IPatientMedicalCheckDao patientMedicalCheckDao;
	@Resource
	private IPatientMedicalDmDao patientMedicalDmDao;
	@Resource
	private IPatientMedicalPlanDao patientMedicalPlanDao;
	@Resource
	private IPatientImgDao patientImgDao;
	
	@Override
	public void addPatientMedical(
			PatientMedicalDto patientMedicalDto) throws TsfaServiceException {
		logger.debug("addPatientMedical(AddPatientMedical addPatientMedical={}) - start", patientMedicalDto); 

		AssertUtils.notNull(patientMedicalDto);
		try {
			PatientMedical patientMedical = new PatientMedical();
			//add数据录入
			patientMedical.setCode(GUID.getPreUUID());
			patientMedical.setPatientReservationCode(patientMedicalDto.getPatientReservationCode());
			patientMedical.setPatientNo(patientMedicalDto.getPatientNo());
			patientMedical.setPatientName(patientMedicalDto.getPatientName());
			patientMedical.setDoctorNo(patientMedicalDto.getDoctorNo());
			patientMedical.setDoctorName(patientMedicalDto.getDoctorName());
			patientMedical.setAssistantNo(patientMedicalDto.getAssistantNo());
			patientMedical.setAssistantName(patientMedicalDto.getAssistantName());
			patientMedical.setVisitingDate(patientMedicalDto.getVisitingDate());
			patientMedical.setVisitingType(patientMedicalDto.getVisitingType());
			patientMedical.setMainRemark(patientMedicalDto.getMainRemark());
			patientMedical.setMainCurrentRemark(patientMedicalDto.getMainCurrentRemark());
			patientMedical.setMainPastRemark(patientMedicalDto.getMainPastRemark());
			patientMedical.setCheckOralRemark(patientMedicalDto.getCheckOralRemark());
			patientMedical.setCheckAuxiliaryRemark(patientMedicalDto.getCheckAuxiliaryRemark());
			patientMedical.setPlanDiagnosisRemark(patientMedicalDto.getPlanDiagnosisRemark());
			patientMedical.setPlanTreatmentRemark(patientMedicalDto.getPlanTreatmentRemark());
			patientMedical.setDmDisposalRemark(patientMedicalDto.getDmDisposalRemark());
			patientMedical.setDmMedicalRemark(patientMedicalDto.getDmMedicalRemark());
			patientMedical.setOtherLabelRemark(patientMedicalDto.getOtherLabelRemark());
			patientMedical.setOtherRemark(patientMedicalDto.getOtherRemark());
			patientMedical.setCreateDate(new Date());
			patientMedical.setCreateId(patientMedicalDto.getCreateId());
			patientMedical.setCreateName(patientMedicalDto.getCreateName());
			patientMedical.setRemark(patientMedicalDto.getRemark());
			patientMedical.setRemark2(patientMedicalDto.getRemark2());
			patientMedical.setRemark3(patientMedicalDto.getRemark3());
			patientMedical.setRemark4(patientMedicalDto.getRemark4());
			patientMedicalDao.insertSelective(patientMedical);
			logger.debug("addPatientMedical(PatientMedicalDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者病历信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_ADD_ERROR,"新增患者病历信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询患者病历信息
	 *
	 * @param findPatientMedicalPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<PatientMedicalDto>  findPatientMedicals(FindPatientMedicalPage findPatientMedicalPage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientMedicalPage);
		List<PatientMedicalDto> returnList=null;
		try {
			returnList = patientMedicalDao.findPatientMedicals(findPatientMedicalPage);
		} catch (Exception e) {
			logger.error("查找患者病历信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_NOT_EXIST_ERROR,"患者病历信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientMedical(
			PatientMedicalDto patientMedicalDto)
			throws TsfaServiceException {
		logger.debug("updatePatientMedical(PatientMedicalDto patientMedicalDto={}) - start", patientMedicalDto); 

		AssertUtils.notNull(patientMedicalDto);
		AssertUtils.notNullAndEmpty(patientMedicalDto.getCode(),"Code不能为空");
		try {
			PatientMedical patientMedical = new PatientMedical();
			//update数据录入
			patientMedical.setCode(patientMedicalDto.getCode());
			patientMedical.setPatientReservationCode(patientMedicalDto.getPatientReservationCode());
			patientMedical.setPatientNo(patientMedicalDto.getPatientNo());
			patientMedical.setPatientName(patientMedicalDto.getPatientName());
			patientMedical.setDoctorNo(patientMedicalDto.getDoctorNo());
			patientMedical.setDoctorName(patientMedicalDto.getDoctorName());
			patientMedical.setAssistantNo(patientMedicalDto.getAssistantNo());
			patientMedical.setAssistantName(patientMedicalDto.getAssistantName());
			patientMedical.setVisitingDate(patientMedicalDto.getVisitingDate());
			patientMedical.setVisitingType(patientMedicalDto.getVisitingType());
			patientMedical.setMainRemark(patientMedicalDto.getMainRemark());
			patientMedical.setMainCurrentRemark(patientMedicalDto.getMainCurrentRemark());
			patientMedical.setMainPastRemark(patientMedicalDto.getMainPastRemark());
			patientMedical.setCheckOralRemark(patientMedicalDto.getCheckOralRemark());
			patientMedical.setCheckAuxiliaryRemark(patientMedicalDto.getCheckAuxiliaryRemark());
			patientMedical.setPlanDiagnosisRemark(patientMedicalDto.getPlanDiagnosisRemark());
			patientMedical.setPlanTreatmentRemark(patientMedicalDto.getPlanTreatmentRemark());
			patientMedical.setDmDisposalRemark(patientMedicalDto.getDmDisposalRemark());
			patientMedical.setDmMedicalRemark(patientMedicalDto.getDmMedicalRemark());
			patientMedical.setOtherLabelRemark(patientMedicalDto.getOtherLabelRemark());
			patientMedical.setOtherRemark(patientMedicalDto.getOtherRemark());
			patientMedical.setRemark(patientMedicalDto.getRemark());
			patientMedical.setRemark2(patientMedicalDto.getRemark2());
			patientMedical.setRemark3(patientMedicalDto.getRemark3());
			patientMedical.setRemark4(patientMedicalDto.getRemark4());
			patientMedical.setUpdateId(patientMedicalDto.getUpdateId());
			patientMedical.setUpdateName(patientMedicalDto.getUpdateName());
			patientMedical.setUpdateDate(new Date());
			AssertUtils.notUpdateMoreThanOne(patientMedicalDao.updateByPrimaryKeySelective(patientMedical));
			
			//口腔检查 
			patientMedicalCheckDao.deleteByMedicalCode(patientMedicalDto.getCode());
			if(patientMedicalDto.getChecks().size()>0) {
				for (PatientMedicalCheckDto check : patientMedicalDto.getChecks()) {
					PatientMedicalCheck record = new PatientMedicalCheck();
					record.setCode(GUID.generateByUUID());
					record.setRemark(check.getRemark());
					record.setMedicalCode(patientMedical.getCode());
					record.setCheckAuxiliaryRemark(check.getCheckAuxiliaryRemark());
					record.setCheckOralRemark(check.getCheckOralRemark());
					record.setDentalPosition(check.getDentalPosition());
					record.setDentalSurface(check.getDentalSurface());
					patientMedicalCheckDao.insertSelective(record);
				}
			}
			
			//处置与医嘱
			patientMedicalDmDao.deleteByMedicalCode(patientMedicalDto.getCode());
			if(patientMedicalDto.getDms().size()>0) {
				for (PatientMedicalDmDto dm : patientMedicalDto.getDms()) {
					PatientMedicalDm record = new PatientMedicalDm();
					record.setCode(GUID.generateByUUID());
					record.setRemark(dm.getRemark());
					record.setMedicalCode(patientMedical.getCode());
					record.setDmDisposalRemark(dm.getDmDisposalRemark());
					record.setDentalPosition(dm.getDentalPosition());
					record.setDentalSurface(dm.getDentalSurface());
					patientMedicalDmDao.insertSelective(record);
				}
			}
			
			//诊断与治疗计划
			patientMedicalPlanDao.deleteByMedicalCode(patientMedicalDto.getCode());
			if(patientMedicalDto.getPlans().size()>0) {
				for (PatientMedicalPlanDto plan : patientMedicalDto.getPlans()) {
					PatientMedicalPlan record = new PatientMedicalPlan();
					record.setCode(GUID.generateByUUID());
					record.setRemark(plan.getRemark());
					record.setMedicalCode(patientMedical.getCode());
					record.setPlanDiagnosisRemark(plan.getPlanDiagnosisRemark());
					record.setPlanTreatmentRemark(plan.getPlanTreatmentRemark());
					record.setDentalPosition(plan.getDentalPosition());
					record.setDentalSurface(plan.getDentalSurface());
					patientMedicalPlanDao.insertSelective(record);
				}
			}
			
			logger.debug("updatePatientMedical(PatientMedicalDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者病历信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_UPDATE_ERROR,"患者病历信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientMedicalDto findPatientMedical(
			PatientMedicalDto patientMedicalDto) throws TsfaServiceException {
		logger.debug("findPatientMedical(FindPatientMedical findPatientMedical={}) - start", patientMedicalDto); 

		AssertUtils.notNull(patientMedicalDto);
		AssertUtils.notAllNull(patientMedicalDto.getCode(),"Code不能为空");
		try {
			PatientMedical patientMedical = patientMedicalDao.selectByPrimaryKey(patientMedicalDto.getCode());
			if(patientMedical == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_NOT_EXIST_ERROR,"患者病历信息不存在");
			}
			PatientMedicalDto findPatientMedicalReturn = new PatientMedicalDto();
			//find数据录入
			findPatientMedicalReturn.setCode(patientMedical.getCode());
			findPatientMedicalReturn.setPatientReservationCode(patientMedical.getPatientReservationCode());
			findPatientMedicalReturn.setPatientNo(patientMedical.getPatientNo());
			findPatientMedicalReturn.setPatientName(patientMedical.getPatientName());
			findPatientMedicalReturn.setDoctorNo(patientMedical.getDoctorNo());
			findPatientMedicalReturn.setDoctorName(patientMedical.getDoctorName());
			findPatientMedicalReturn.setAssistantNo(patientMedical.getAssistantNo());
			findPatientMedicalReturn.setAssistantName(patientMedical.getAssistantName());
			findPatientMedicalReturn.setVisitingDate(patientMedical.getVisitingDate());
			findPatientMedicalReturn.setVisitingType(patientMedical.getVisitingType());
			findPatientMedicalReturn.setMainRemark(patientMedical.getMainRemark());
			findPatientMedicalReturn.setMainCurrentRemark(patientMedical.getMainCurrentRemark());
			findPatientMedicalReturn.setMainPastRemark(patientMedical.getMainPastRemark());
			findPatientMedicalReturn.setCheckOralRemark(patientMedical.getCheckOralRemark());
			findPatientMedicalReturn.setCheckAuxiliaryRemark(patientMedical.getCheckAuxiliaryRemark());
			findPatientMedicalReturn.setPlanDiagnosisRemark(patientMedical.getPlanDiagnosisRemark());
			findPatientMedicalReturn.setPlanTreatmentRemark(patientMedical.getPlanTreatmentRemark());
			findPatientMedicalReturn.setDmDisposalRemark(patientMedical.getDmDisposalRemark());
			findPatientMedicalReturn.setDmMedicalRemark(patientMedical.getDmMedicalRemark());
			findPatientMedicalReturn.setOtherLabelRemark(patientMedical.getOtherLabelRemark());
			findPatientMedicalReturn.setOtherRemark(patientMedical.getOtherRemark());
			findPatientMedicalReturn.setCreateDate(patientMedical.getCreateDate());
			findPatientMedicalReturn.setCreateId(patientMedical.getCreateId());
			findPatientMedicalReturn.setCreateName(patientMedical.getCreateName());
			findPatientMedicalReturn.setRemark(patientMedical.getRemark());
			findPatientMedicalReturn.setRemark2(patientMedical.getRemark2());
			findPatientMedicalReturn.setRemark3(patientMedical.getRemark3());
			findPatientMedicalReturn.setRemark4(patientMedical.getRemark4());
			findPatientMedicalReturn.setUpdateId(patientMedical.getUpdateId());
			findPatientMedicalReturn.setUpdateName(patientMedical.getUpdateName());
			findPatientMedicalReturn.setUpdateDate(patientMedical.getUpdateDate());
			
			logger.debug("findPatientMedical(PatientMedicalDto) - end - return value={}", findPatientMedicalReturn); 
			return findPatientMedicalReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者病历信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_FIND_ERROR,"查找患者病历信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientMedicalDto> findPatientMedicalPage(
			FindPatientMedicalPage findPatientMedicalPage)
			throws TsfaServiceException {
		logger.debug("findPatientMedicalPage(FindPatientMedicalPage findPatientMedicalPage={}) - start", findPatientMedicalPage); 

		AssertUtils.notNull(findPatientMedicalPage);
		List<PatientMedicalDto> returnList=null;
		int count = 0;
		try {
			returnList = patientMedicalDao.findPatientMedicalPage(findPatientMedicalPage);
			count = patientMedicalDao.findPatientMedicalPageCount(findPatientMedicalPage);
		}  catch (Exception e) {
			logger.error("患者病历信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_FIND_PAGE_ERROR,"患者病历信息不存在错误.！",e);
		}
		Page<PatientMedicalDto> returnPage = new Page<PatientMedicalDto>(returnList, count, findPatientMedicalPage);

		logger.debug("findPatientMedicalPage(FindPatientMedicalPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 

	@Override
	public String addPatientMedicalByReservation(PatientMedicalDto patientMedicalDto) throws TsfaServiceException {
		logger.debug("addPatientMedicalByReservation(AddPatientMedical addPatientMedical={}) - start", patientMedicalDto); 

		AssertUtils.notNull(patientMedicalDto);
		try {
			PatientMedical patientMedical = new PatientMedical();
			//add数据录入
			patientMedical.setCode(GUID.getPreUUID());
			patientMedical.setPatientReservationCode(patientMedicalDto.getPatientReservationCode());
			patientMedical.setPatientNo(patientMedicalDto.getPatientNo());
			patientMedical.setPatientName(patientMedicalDto.getPatientName());
			patientMedical.setDoctorNo(patientMedicalDto.getDoctorNo());
			patientMedical.setDoctorName(patientMedicalDto.getDoctorName());
			patientMedical.setAssistantNo(patientMedicalDto.getAssistantNo());
			patientMedical.setAssistantName(patientMedicalDto.getAssistantName());
			patientMedical.setVisitingDate(patientMedicalDto.getVisitingDate());
			patientMedical.setVisitingType(patientMedicalDto.getVisitingType());
			patientMedical.setMainRemark(patientMedicalDto.getMainRemark());
			patientMedical.setMainCurrentRemark(patientMedicalDto.getMainCurrentRemark());
			patientMedical.setMainPastRemark(patientMedicalDto.getMainPastRemark());
			patientMedical.setCheckOralRemark(patientMedicalDto.getCheckOralRemark());
			patientMedical.setCheckAuxiliaryRemark(patientMedicalDto.getCheckAuxiliaryRemark());
			patientMedical.setPlanDiagnosisRemark(patientMedicalDto.getPlanDiagnosisRemark());
			patientMedical.setPlanTreatmentRemark(patientMedicalDto.getPlanTreatmentRemark());
			patientMedical.setDmDisposalRemark(patientMedicalDto.getDmDisposalRemark());
			patientMedical.setDmMedicalRemark(patientMedicalDto.getDmMedicalRemark());
			patientMedical.setOtherLabelRemark(patientMedicalDto.getOtherLabelRemark());
			patientMedical.setOtherRemark(patientMedicalDto.getOtherRemark());
			patientMedical.setCreateDate(new Date());
			patientMedical.setCreateId(patientMedicalDto.getCreateId());
			patientMedical.setCreateName(patientMedicalDto.getCreateName());
			patientMedical.setRemark(patientMedicalDto.getRemark());
			patientMedical.setRemark2(patientMedicalDto.getRemark2());
			patientMedical.setRemark3(patientMedicalDto.getRemark3());
			patientMedical.setRemark4(patientMedicalDto.getRemark4());
			patientMedicalDao.insertSelective(patientMedical);
			
			// 患者预约code不为空，修改患者预约咨询时间
			if (StringUtils.isNotBlank(patientMedicalDto.getPatientReservationCode())) {
				
				PatientServiceDto patientServiceDto = new PatientServiceDto();
				
				patientServiceDto.setCode(patientMedicalDto.getPatientReservationCode());
				patientServiceDto.setUpdateId(patientMedicalDto.getUpdateId());
				patientServiceDto.setUpdateName(patientMedicalDto.getUpdateName());
				patientServiceDto.setUpdateDate(patientMedicalDto.getUpdateDate());
				patientServiceDto.setMedicalDate(patientMedicalDto.getCreateDate());
				this.patientServiceService.updatePatientService(patientServiceDto);
			}
			
			//口腔检查 
			if(patientMedicalDto.getChecks().size()>0) {
				for (PatientMedicalCheckDto check : patientMedicalDto.getChecks()) {
					PatientMedicalCheck record = new PatientMedicalCheck();
					record.setCode(GUID.generateByUUID());
					record.setRemark(check.getRemark());
					record.setMedicalCode(patientMedical.getCode());
					record.setCheckAuxiliaryRemark(check.getCheckAuxiliaryRemark());
					record.setCheckOralRemark(check.getCheckOralRemark());
					record.setDentalPosition(check.getDentalPosition());
					record.setDentalSurface(check.getDentalSurface());
					patientMedicalCheckDao.insertSelective(record);
				}
			}
			
			//处置与医嘱
			if(patientMedicalDto.getDms().size()>0) {
				for (PatientMedicalDmDto dm : patientMedicalDto.getDms()) {
					PatientMedicalDm record = new PatientMedicalDm();
					record.setCode(GUID.generateByUUID());
					record.setRemark(dm.getRemark());
					record.setMedicalCode(patientMedical.getCode());
					record.setDmDisposalRemark(dm.getDmDisposalRemark());
					record.setDentalPosition(dm.getDentalPosition());
					record.setDentalSurface(dm.getDentalSurface());
					patientMedicalDmDao.insertSelective(record);
				}
			}
			
			//诊断与治疗计划
			if(patientMedicalDto.getPlans().size()>0) {
				for (PatientMedicalPlanDto plan : patientMedicalDto.getPlans()) {
					PatientMedicalPlan record = new PatientMedicalPlan();
					record.setCode(GUID.generateByUUID());
					record.setRemark(plan.getRemark());
					record.setMedicalCode(patientMedical.getCode());
					record.setPlanDiagnosisRemark(plan.getPlanDiagnosisRemark());
					record.setPlanTreatmentRemark(plan.getPlanTreatmentRemark());
					record.setDentalPosition(plan.getDentalPosition());
					record.setDentalSurface(plan.getDentalSurface());
					patientMedicalPlanDao.insertSelective(record);
				}
			}
			
			logger.debug("addPatientMedicalByReservation(PatientMedicalDto) - end - return"); 
			
			return patientMedical.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者病历信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_ADD_ERROR,"新增患者病历信息错误！",e);
		}
	}
	
	@Override
	public PatientMedicalDto findPatientMedicalByPatientReservationCode(PatientMedicalDto patientMedicalDto) throws TsfaServiceException {
		logger.debug("findPatientMedicalByPatientReservationCode(FindPatientMedical findPatientMedical={}) - start", patientMedicalDto); 

		AssertUtils.notNull(patientMedicalDto);
		AssertUtils.notAllNull(patientMedicalDto.getPatientReservationCode(),"患者服务Code不能为空");
		try {
			PatientMedical patientMedical = patientMedicalDao.selectByPatientReservationCode(patientMedicalDto.getPatientReservationCode());
			if(patientMedical == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_NOT_EXIST_ERROR,"患者病历信息不存在");
			}
			PatientMedicalDto findPatientMedicalReturn = new PatientMedicalDto();
			//find数据录入
			findPatientMedicalReturn.setCode(patientMedical.getCode());
			findPatientMedicalReturn.setPatientReservationCode(patientMedical.getPatientReservationCode());
			findPatientMedicalReturn.setPatientNo(patientMedical.getPatientNo());
			findPatientMedicalReturn.setPatientName(patientMedical.getPatientName());
			findPatientMedicalReturn.setDoctorNo(patientMedical.getDoctorNo());
			findPatientMedicalReturn.setDoctorName(patientMedical.getDoctorName());
			findPatientMedicalReturn.setAssistantNo(patientMedical.getAssistantNo());
			findPatientMedicalReturn.setAssistantName(patientMedical.getAssistantName());
			findPatientMedicalReturn.setVisitingDate(patientMedical.getVisitingDate());
			findPatientMedicalReturn.setVisitingType(patientMedical.getVisitingType());
			findPatientMedicalReturn.setMainRemark(patientMedical.getMainRemark());
			findPatientMedicalReturn.setMainCurrentRemark(patientMedical.getMainCurrentRemark());
			findPatientMedicalReturn.setMainPastRemark(patientMedical.getMainPastRemark());
			findPatientMedicalReturn.setCheckOralRemark(patientMedical.getCheckOralRemark());
			findPatientMedicalReturn.setCheckAuxiliaryRemark(patientMedical.getCheckAuxiliaryRemark());
			findPatientMedicalReturn.setPlanDiagnosisRemark(patientMedical.getPlanDiagnosisRemark());
			findPatientMedicalReturn.setPlanTreatmentRemark(patientMedical.getPlanTreatmentRemark());
			findPatientMedicalReturn.setDmDisposalRemark(patientMedical.getDmDisposalRemark());
			findPatientMedicalReturn.setDmMedicalRemark(patientMedical.getDmMedicalRemark());
			findPatientMedicalReturn.setOtherLabelRemark(patientMedical.getOtherLabelRemark());
			findPatientMedicalReturn.setOtherRemark(patientMedical.getOtherRemark());
			findPatientMedicalReturn.setCreateDate(patientMedical.getCreateDate());
			findPatientMedicalReturn.setCreateId(patientMedical.getCreateId());
			findPatientMedicalReturn.setCreateName(patientMedical.getCreateName());
			findPatientMedicalReturn.setRemark(patientMedical.getRemark());
			//图片地址
			String imgCodes = patientMedical.getRemark2();
			if(StringUtils.isNotEmpty(imgCodes)) {
				List<PatientImg> list = new ArrayList<PatientImg>();
				String[] imgArr = imgCodes.split(",");
				for (String imgCode : imgArr) {
					PatientImg patientImg = patientImgDao.selectByPrimaryKey(imgCode);
					list.add(patientImg);
				}
				patientMedical.setImgs(list);
			}
			findPatientMedicalReturn.setRemark2(patientMedical.getRemark2());
			findPatientMedicalReturn.setRemark3(patientMedical.getRemark3());
			findPatientMedicalReturn.setRemark4(patientMedical.getRemark4());
			findPatientMedicalReturn.setUpdateId(patientMedical.getUpdateId());
			findPatientMedicalReturn.setUpdateName(patientMedical.getUpdateName());
			findPatientMedicalReturn.setUpdateDate(patientMedical.getUpdateDate());
			
			logger.debug("findPatientMedicalByPatientReservationCode(PatientMedicalDto) - end - return value={}", findPatientMedicalReturn); 
			return findPatientMedicalReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者病历信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_FIND_ERROR,"查找患者病历信息信息错误！",e);
		}

	}
	
	@Override
	public PatientMedicalDto findPatientMedical(String code) throws TsfaServiceException {
		logger.debug("findPatientMedical(String code={}) - start", code); 
		AssertUtils.notAllNull(code,"Code不能为空");
		try {
			PatientMedicalDto patientMedical = patientMedicalDao.selectByCode(code);
			String imgCodes = patientMedical.getRemark2();
			if(StringUtils.isNotEmpty(imgCodes)) {
				List<PatientImg> list = new ArrayList<PatientImg>();
				String[] imgArr = imgCodes.split(",");
				for (String imgCode : imgArr) {
					PatientImg patientImg = patientImgDao.selectByPrimaryKey(imgCode);
					list.add(patientImg);
				}
				patientMedical.setImgs(list);
			}
			logger.debug("findPatientMedical(String code) - end - return value={}", patientMedical); 
			return patientMedical;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者病历信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_FIND_ERROR,"查找患者病历信息信息错误！",e);
		}


	}


	@Override
	public void delPatientMedical(PatientMedicalDto patientMedicalDto) throws TsfaServiceException {
		logger.debug("delPatientMedical(PatientMedicalDto patientMedicalDto={}) - start", patientMedicalDto); 
		AssertUtils.notNull(patientMedicalDto);
		AssertUtils.notAllNull(patientMedicalDto.getCode(),"Code不能为空");
		int i =0;
		try {
			i = patientMedicalDao.deleteByPrimaryKey(patientMedicalDto.getCode());
		}  catch (Exception e) {
			logger.error("患者病历信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_FIND_PAGE_ERROR,"患者病历信息不存在错误.！",e);
		}

		logger.debug("delPatientMedical(patientMedicalDto) - end - return value={}", i); 
		
	}
}
