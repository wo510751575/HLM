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
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientMedicalTemplateDao;
import com.ye.business.hx.domain.PatientMedicalTemplate;
import com.ye.business.hx.dto.FindPatientMedicalTemplatePage;
import com.ye.business.hx.dto.PatientMedicalTemplateDto;
import com.ye.business.hx.service.IPatientMedicalTemplateService;
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
public class PatientMedicalTemplateServiceImpl implements IPatientMedicalTemplateService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientMedicalTemplateServiceImpl.class);
	

	@Resource
	private IPatientMedicalTemplateDao patientMedicalTemplateDao;
	
	
	@Override
	public void addPatientMedicalTemplate(
			PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException {
		logger.debug("addPatientMedicalTemplate(AddPatientMedicalTemplate addPatientMedicalTemplate={}) - start", patientMedicalTemplateDto); 

		AssertUtils.notNull(patientMedicalTemplateDto);
		try {
			PatientMedicalTemplate patientMedicalTemplate = new PatientMedicalTemplate();
			//add数据录入
			patientMedicalTemplate.setCode(GUID.generateCode());
			patientMedicalTemplate.setName(patientMedicalTemplateDto.getName());
			patientMedicalTemplate.setMainRemark(patientMedicalTemplateDto.getMainRemark());
			patientMedicalTemplate.setMainCurrentRemark(patientMedicalTemplateDto.getMainCurrentRemark());
			patientMedicalTemplate.setMainPastRemark(patientMedicalTemplateDto.getMainPastRemark());
			patientMedicalTemplate.setCheckOralRemark(patientMedicalTemplateDto.getCheckOralRemark());
			patientMedicalTemplate.setCheckAuxiliaryRemark(patientMedicalTemplateDto.getCheckAuxiliaryRemark());
			patientMedicalTemplate.setPlanDiagnosisRemark(patientMedicalTemplateDto.getPlanDiagnosisRemark());
			patientMedicalTemplate.setPlanTreatmentRemark(patientMedicalTemplateDto.getPlanTreatmentRemark());
			patientMedicalTemplate.setDmDisposalRemark(patientMedicalTemplateDto.getDmDisposalRemark());
			patientMedicalTemplate.setDmMedicalRemark(patientMedicalTemplateDto.getDmMedicalRemark());
			patientMedicalTemplate.setOtherLabelRemark(patientMedicalTemplateDto.getOtherLabelRemark());
			patientMedicalTemplate.setOtherRemark(patientMedicalTemplateDto.getOtherRemark());
			patientMedicalTemplate.setCreateDate(new Date());
			patientMedicalTemplate.setCreateId(patientMedicalTemplateDto.getCreateId());
			patientMedicalTemplate.setCreateName(patientMedicalTemplateDto.getCreateName());
			patientMedicalTemplate.setRemark(patientMedicalTemplateDto.getRemark());
			patientMedicalTemplate.setRemark2(patientMedicalTemplateDto.getRemark2());
			patientMedicalTemplate.setRemark3(patientMedicalTemplateDto.getRemark3());
			patientMedicalTemplate.setRemark4(patientMedicalTemplateDto.getRemark4());
			patientMedicalTemplate.setListCode(patientMedicalTemplateDto.getListCode());
			patientMedicalTemplateDao.insertSelective(patientMedicalTemplate);
			logger.debug("addPatientMedicalTemplate(PatientMedicalTemplateDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增模版信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_ADD_ERROR,"新增模版信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询模版信息
	 *
	 * @param findPatientMedicalTemplatePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientMedicalTemplateDto>  findPatientMedicalTemplates(FindPatientMedicalTemplatePage findPatientMedicalTemplatePage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientMedicalTemplatePage);
		List<PatientMedicalTemplateDto> returnList=null;
		try {
			returnList = patientMedicalTemplateDao.findPatientMedicalTemplates(findPatientMedicalTemplatePage);
		} catch (Exception e) {
			logger.error("查找模版信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_NOT_EXIST_ERROR,"模版信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientMedicalTemplate(
			PatientMedicalTemplateDto patientMedicalTemplateDto)
			throws TsfaServiceException {
		logger.debug("updatePatientMedicalTemplate(PatientMedicalTemplateDto patientMedicalTemplateDto={}) - start", patientMedicalTemplateDto); 

		AssertUtils.notNull(patientMedicalTemplateDto);
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getCode(),"Code不能为空");
		try {
			PatientMedicalTemplate patientMedicalTemplate = new PatientMedicalTemplate();
			//update数据录入
			patientMedicalTemplate.setCode(patientMedicalTemplateDto.getCode());
			patientMedicalTemplate.setName(patientMedicalTemplateDto.getName());
			patientMedicalTemplate.setMainRemark(patientMedicalTemplateDto.getMainRemark());
			patientMedicalTemplate.setMainCurrentRemark(patientMedicalTemplateDto.getMainCurrentRemark());
			patientMedicalTemplate.setMainPastRemark(patientMedicalTemplateDto.getMainPastRemark());
			patientMedicalTemplate.setCheckOralRemark(patientMedicalTemplateDto.getCheckOralRemark());
			patientMedicalTemplate.setCheckAuxiliaryRemark(patientMedicalTemplateDto.getCheckAuxiliaryRemark());
			patientMedicalTemplate.setPlanDiagnosisRemark(patientMedicalTemplateDto.getPlanDiagnosisRemark());
			patientMedicalTemplate.setPlanTreatmentRemark(patientMedicalTemplateDto.getPlanTreatmentRemark());
			patientMedicalTemplate.setDmDisposalRemark(patientMedicalTemplateDto.getDmDisposalRemark());
			patientMedicalTemplate.setDmMedicalRemark(patientMedicalTemplateDto.getDmMedicalRemark());
			patientMedicalTemplate.setOtherLabelRemark(patientMedicalTemplateDto.getOtherLabelRemark());
			patientMedicalTemplate.setOtherRemark(patientMedicalTemplateDto.getOtherRemark());
			patientMedicalTemplate.setRemark(patientMedicalTemplateDto.getRemark());
			patientMedicalTemplate.setRemark2(patientMedicalTemplateDto.getRemark2());
			patientMedicalTemplate.setRemark3(patientMedicalTemplateDto.getRemark3());
			patientMedicalTemplate.setRemark4(patientMedicalTemplateDto.getRemark4());
			patientMedicalTemplate.setUpdateId(patientMedicalTemplateDto.getUpdateId());
			patientMedicalTemplate.setUpdateName(patientMedicalTemplateDto.getUpdateName());
			patientMedicalTemplate.setUpdateDate(new Date());
			AssertUtils.notUpdateMoreThanOne(patientMedicalTemplateDao.updateByPrimaryKeySelective(patientMedicalTemplate));
			logger.debug("updatePatientMedicalTemplate(PatientMedicalTemplateDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("模版信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_UPDATE_ERROR,"模版信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientMedicalTemplateDto findPatientMedicalTemplate(
			PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException {
		logger.debug("findPatientMedicalTemplate(FindPatientMedicalTemplate findPatientMedicalTemplate={}) - start", patientMedicalTemplateDto); 

		AssertUtils.notNull(patientMedicalTemplateDto);
		AssertUtils.notAllNull(patientMedicalTemplateDto.getCode(),"Code不能为空");
		try {
			PatientMedicalTemplate patientMedicalTemplate = patientMedicalTemplateDao.selectByPrimaryKey(patientMedicalTemplateDto.getCode());
			if(patientMedicalTemplate == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_NOT_EXIST_ERROR,"模版信息不存在");
			}
			PatientMedicalTemplateDto findPatientMedicalTemplateReturn = new PatientMedicalTemplateDto();
			//find数据录入
			findPatientMedicalTemplateReturn.setCode(patientMedicalTemplate.getCode());
			findPatientMedicalTemplateReturn.setName(patientMedicalTemplate.getName());
			findPatientMedicalTemplateReturn.setMainRemark(patientMedicalTemplate.getMainRemark());
			findPatientMedicalTemplateReturn.setMainCurrentRemark(patientMedicalTemplate.getMainCurrentRemark());
			findPatientMedicalTemplateReturn.setMainPastRemark(patientMedicalTemplate.getMainPastRemark());
			findPatientMedicalTemplateReturn.setCheckOralRemark(patientMedicalTemplate.getCheckOralRemark());
			findPatientMedicalTemplateReturn.setCheckAuxiliaryRemark(patientMedicalTemplate.getCheckAuxiliaryRemark());
			findPatientMedicalTemplateReturn.setPlanDiagnosisRemark(patientMedicalTemplate.getPlanDiagnosisRemark());
			findPatientMedicalTemplateReturn.setPlanTreatmentRemark(patientMedicalTemplate.getPlanTreatmentRemark());
			findPatientMedicalTemplateReturn.setDmDisposalRemark(patientMedicalTemplate.getDmDisposalRemark());
			findPatientMedicalTemplateReturn.setDmMedicalRemark(patientMedicalTemplate.getDmMedicalRemark());
			findPatientMedicalTemplateReturn.setOtherLabelRemark(patientMedicalTemplate.getOtherLabelRemark());
			findPatientMedicalTemplateReturn.setOtherRemark(patientMedicalTemplate.getOtherRemark());
			findPatientMedicalTemplateReturn.setCreateDate(patientMedicalTemplate.getCreateDate());
			findPatientMedicalTemplateReturn.setCreateId(patientMedicalTemplate.getCreateId());
			findPatientMedicalTemplateReturn.setCreateName(patientMedicalTemplate.getCreateName());
			findPatientMedicalTemplateReturn.setRemark(patientMedicalTemplate.getRemark());
			findPatientMedicalTemplateReturn.setRemark2(patientMedicalTemplate.getRemark2());
			findPatientMedicalTemplateReturn.setRemark3(patientMedicalTemplate.getRemark3());
			findPatientMedicalTemplateReturn.setRemark4(patientMedicalTemplate.getRemark4());
			findPatientMedicalTemplateReturn.setUpdateId(patientMedicalTemplate.getUpdateId());
			findPatientMedicalTemplateReturn.setUpdateName(patientMedicalTemplate.getUpdateName());
			findPatientMedicalTemplateReturn.setUpdateDate(patientMedicalTemplate.getUpdateDate());
			
			logger.debug("findPatientMedicalTemplate(PatientMedicalTemplateDto) - end - return value={}", findPatientMedicalTemplateReturn); 
			return findPatientMedicalTemplateReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找模版信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_FIND_ERROR,"查找模版信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientMedicalTemplateDto> findPatientMedicalTemplatePage(
			FindPatientMedicalTemplatePage findPatientMedicalTemplatePage)
			throws TsfaServiceException {
		logger.debug("findPatientMedicalTemplatePage(FindPatientMedicalTemplatePage findPatientMedicalTemplatePage={}) - start", findPatientMedicalTemplatePage); 

		AssertUtils.notNull(findPatientMedicalTemplatePage);
		List<PatientMedicalTemplateDto> returnList=null;
		int count = 0;
		try {
			count = patientMedicalTemplateDao.findPatientMedicalTemplatePageCount(findPatientMedicalTemplatePage);
			if (count > 0) {
				returnList = patientMedicalTemplateDao.findPatientMedicalTemplatePage(findPatientMedicalTemplatePage);
			}
		}  catch (Exception e) {
			logger.error("模版信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_FIND_PAGE_ERROR,"模版信息不存在错误.！",e);
		}
		Page<PatientMedicalTemplateDto> returnPage = new Page<PatientMedicalTemplateDto>(returnList, count, findPatientMedicalTemplatePage);

		logger.debug("findPatientMedicalTemplatePage(FindPatientMedicalTemplatePage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public void del(PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException {
		logger.debug("del(FindPatientMedicalTemplate findPatientMedicalTemplate={}) - start", patientMedicalTemplateDto); 

		AssertUtils.notNull(patientMedicalTemplateDto);
		AssertUtils.notAllNull(patientMedicalTemplateDto.getCode(),"Code不能为空");
		try {
			int i =patientMedicalTemplateDao.deleteByPrimaryKey(patientMedicalTemplateDto.getCode());
			logger.debug("del(PatientMedicalTemplateDto) - end - return value={}", i); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("删除模版信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_FIND_ERROR,"删除模版信息信息错误！",e);
		}
	} 


}
