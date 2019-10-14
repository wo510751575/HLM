package com.ye.business.hx.service.impl;

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
import com.ye.business.hx.dao.IPatientMedicalDao;
import com.ye.business.hx.domain.PatientMedical;
import com.ye.business.hx.dto.FindPatientMedicalPage;
import com.ye.business.hx.dto.PatientMedicalDto;
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
			patientMedical.setCreateDate(patientMedicalDto.getCreateDate());
			patientMedical.setCreateId(patientMedicalDto.getCreateId());
			patientMedical.setCreateName(patientMedicalDto.getCreateName());
			patientMedical.setRemark(patientMedicalDto.getRemark());
			patientMedical.setRemark2(patientMedicalDto.getRemark2());
			patientMedical.setRemark3(patientMedicalDto.getRemark3());
			patientMedical.setRemark4(patientMedicalDto.getRemark4());
			patientMedical.setUpdateId(patientMedicalDto.getUpdateId());
			patientMedical.setUpdateName(patientMedicalDto.getUpdateName());
			patientMedical.setUpdateDate(patientMedicalDto.getUpdateDate());
			patientMedicalDao.insert(patientMedical);
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
		logger.debug("updatePatientMedical(PatientMedicalDto patientMedicalDto={}) - start", patientMedicalDto); //$NON-NLS-1$

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
			patientMedical.setCreateDate(patientMedicalDto.getCreateDate());
			patientMedical.setCreateId(patientMedicalDto.getCreateId());
			patientMedical.setCreateName(patientMedicalDto.getCreateName());
			patientMedical.setRemark(patientMedicalDto.getRemark());
			patientMedical.setRemark2(patientMedicalDto.getRemark2());
			patientMedical.setRemark3(patientMedicalDto.getRemark3());
			patientMedical.setRemark4(patientMedicalDto.getRemark4());
			patientMedical.setUpdateId(patientMedicalDto.getUpdateId());
			patientMedical.setUpdateName(patientMedicalDto.getUpdateName());
			patientMedical.setUpdateDate(patientMedicalDto.getUpdateDate());
			AssertUtils.notUpdateMoreThanOne(patientMedicalDao.updateByPrimaryKeySelective(patientMedical));
			logger.debug("updatePatientMedical(PatientMedicalDto) - end - return"); //$NON-NLS-1$
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
		logger.debug("findPatientMedical(FindPatientMedical findPatientMedical={}) - start", patientMedicalDto); //$NON-NLS-1$

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
			
			logger.debug("findPatientMedical(PatientMedicalDto) - end - return value={}", findPatientMedicalReturn); //$NON-NLS-1$
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
		logger.debug("findPatientMedicalPage(FindPatientMedicalPage findPatientMedicalPage={}) - start", findPatientMedicalPage); //$NON-NLS-1$

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

		logger.debug("findPatientMedicalPage(FindPatientMedicalPage) - end - return value={}", returnPage); //$NON-NLS-1$
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
			patientMedical.setCreateDate(patientMedicalDto.getCreateDate());
			patientMedical.setCreateId(patientMedicalDto.getCreateId());
			patientMedical.setCreateName(patientMedicalDto.getCreateName());
			patientMedical.setRemark(patientMedicalDto.getRemark());
			patientMedical.setRemark2(patientMedicalDto.getRemark2());
			patientMedical.setRemark3(patientMedicalDto.getRemark3());
			patientMedical.setRemark4(patientMedicalDto.getRemark4());
			patientMedical.setUpdateId(patientMedicalDto.getUpdateId());
			patientMedical.setUpdateName(patientMedicalDto.getUpdateName());
			patientMedical.setUpdateDate(patientMedicalDto.getUpdateDate());
			patientMedicalDao.insert(patientMedical);
			
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
		logger.debug("findPatientMedicalByPatientReservationCode(FindPatientMedical findPatientMedical={}) - start", patientMedicalDto); //$NON-NLS-1$

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
			findPatientMedicalReturn.setRemark2(patientMedical.getRemark2());
			findPatientMedicalReturn.setRemark3(patientMedical.getRemark3());
			findPatientMedicalReturn.setRemark4(patientMedical.getRemark4());
			findPatientMedicalReturn.setUpdateId(patientMedical.getUpdateId());
			findPatientMedicalReturn.setUpdateName(patientMedical.getUpdateName());
			findPatientMedicalReturn.setUpdateDate(patientMedical.getUpdateDate());
			
			logger.debug("findPatientMedicalByPatientReservationCode(PatientMedicalDto) - end - return value={}", findPatientMedicalReturn); //$NON-NLS-1$
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
	public void updatePatientMedicalByCode(PatientMedicalDto patientMedicalDto) throws TsfaServiceException {
		logger.debug("updatePatientMedicalByCode(PatientMedicalDto patientMedicalDto={}) - start", patientMedicalDto); //$NON-NLS-1$

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
			patientMedical.setCreateDate(patientMedicalDto.getCreateDate());
			patientMedical.setCreateId(patientMedicalDto.getCreateId());
			patientMedical.setCreateName(patientMedicalDto.getCreateName());
			patientMedical.setRemark(patientMedicalDto.getRemark());
			patientMedical.setRemark2(patientMedicalDto.getRemark2());
			patientMedical.setRemark3(patientMedicalDto.getRemark3());
			patientMedical.setRemark4(patientMedicalDto.getRemark4());
			patientMedical.setUpdateId(patientMedicalDto.getUpdateId());
			patientMedical.setUpdateName(patientMedicalDto.getUpdateName());
			patientMedical.setUpdateDate(patientMedicalDto.getUpdateDate());
			AssertUtils.notUpdateMoreThanOne(patientMedicalDao.updateByPrimaryKeyMedical(patientMedical));
			logger.debug("updatePatientMedicalByCode(PatientMedicalDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者病历信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_UPDATE_ERROR,"患者病历信息更新信息错误！",e);
		}
	}
}
