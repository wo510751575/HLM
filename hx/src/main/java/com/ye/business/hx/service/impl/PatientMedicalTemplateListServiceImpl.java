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

import com.ye.business.hx.dto.PatientMedicalTemplateListDto;
import com.ye.business.hx.dto.FindPatientMedicalTemplateListPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientMedicalTemplateListDao;
import com.ye.business.hx.domain.PatientMedicalTemplateList;
import com.ye.business.hx.service.IPatientMedicalTemplateListService;
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
public class PatientMedicalTemplateListServiceImpl implements IPatientMedicalTemplateListService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientMedicalTemplateListServiceImpl.class);
	

	@Resource
	private IPatientMedicalTemplateListDao patientMedicalTemplateListDao;
	
	
	@Override
	public void addPatientMedicalTemplateList(
			PatientMedicalTemplateListDto patientMedicalTemplateListDto) throws TsfaServiceException {
		logger.debug("addPatientMedicalTemplateList(AddPatientMedicalTemplateList addPatientMedicalTemplateList={}) - start", patientMedicalTemplateListDto); 

		AssertUtils.notNull(patientMedicalTemplateListDto);
		try {
			PatientMedicalTemplateList patientMedicalTemplateList = new PatientMedicalTemplateList();
			//add数据录入
			patientMedicalTemplateList.setCode(GUID.generateCode());
			patientMedicalTemplateList.setName(patientMedicalTemplateListDto.getName());
			patientMedicalTemplateList.setParentCode(patientMedicalTemplateListDto.getParentCode());
			patientMedicalTemplateList.setParentName(patientMedicalTemplateListDto.getParentName());
			patientMedicalTemplateList.setOrderNo(patientMedicalTemplateListDto.getOrderNo());
			patientMedicalTemplateList.setCreater(patientMedicalTemplateListDto.getCreater());
			patientMedicalTemplateList.setCreateTime(new Date());
			patientMedicalTemplateListDao.insertSelective(patientMedicalTemplateList);
			logger.debug("addPatientMedicalTemplateList(PatientMedicalTemplateListDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增模版目录信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_LIST_ADD_ERROR,"新增模版目录信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询模版目录信息
	 *
	 * @param findPatientMedicalTemplateListPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientMedicalTemplateListDto>  findPatientMedicalTemplateLists(FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientMedicalTemplateListPage);
		List<PatientMedicalTemplateListDto> returnList=null;
		try {
			returnList = patientMedicalTemplateListDao.findPatientMedicalTemplateLists(findPatientMedicalTemplateListPage);
		} catch (Exception e) {
			logger.error("查找模版目录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_LIST_NOT_EXIST_ERROR,"模版目录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientMedicalTemplateList(
			PatientMedicalTemplateListDto patientMedicalTemplateListDto)
			throws TsfaServiceException {
		logger.debug("updatePatientMedicalTemplateList(PatientMedicalTemplateListDto patientMedicalTemplateListDto={}) - start", patientMedicalTemplateListDto); 

		AssertUtils.notNull(patientMedicalTemplateListDto);
		AssertUtils.notNullAndEmpty(patientMedicalTemplateListDto.getCode(),"Code不能为空");
		try {
			PatientMedicalTemplateList patientMedicalTemplateList = new PatientMedicalTemplateList();
			//update数据录入
			patientMedicalTemplateList.setCode(patientMedicalTemplateListDto.getCode());
			patientMedicalTemplateList.setName(patientMedicalTemplateListDto.getName());
			patientMedicalTemplateList.setParentCode(patientMedicalTemplateListDto.getParentCode());
			patientMedicalTemplateList.setParentName(patientMedicalTemplateListDto.getParentName());
			patientMedicalTemplateList.setOrderNo(patientMedicalTemplateListDto.getOrderNo());
			AssertUtils.notUpdateMoreThanOne(patientMedicalTemplateListDao.updateByPrimaryKeySelective(patientMedicalTemplateList));
			logger.debug("updatePatientMedicalTemplateList(PatientMedicalTemplateListDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("模版目录信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_LIST_UPDATE_ERROR,"模版目录信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientMedicalTemplateListDto findPatientMedicalTemplateList(
			PatientMedicalTemplateListDto patientMedicalTemplateListDto) throws TsfaServiceException {
		logger.debug("findPatientMedicalTemplateList(FindPatientMedicalTemplateList findPatientMedicalTemplateList={}) - start", patientMedicalTemplateListDto); 

		AssertUtils.notNull(patientMedicalTemplateListDto);
		AssertUtils.notAllNull(patientMedicalTemplateListDto.getCode(),"Code不能为空");
		try {
			PatientMedicalTemplateList patientMedicalTemplateList = patientMedicalTemplateListDao.selectByPrimaryKey(patientMedicalTemplateListDto.getCode());
			if(patientMedicalTemplateList == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_LIST_NOT_EXIST_ERROR,"模版目录信息不存在");
			}
			PatientMedicalTemplateListDto findPatientMedicalTemplateListReturn = new PatientMedicalTemplateListDto();
			//find数据录入
			findPatientMedicalTemplateListReturn.setCode(patientMedicalTemplateList.getCode());
			findPatientMedicalTemplateListReturn.setName(patientMedicalTemplateList.getName());
			findPatientMedicalTemplateListReturn.setParentCode(patientMedicalTemplateList.getParentCode());
			findPatientMedicalTemplateListReturn.setParentName(patientMedicalTemplateList.getParentName());
			findPatientMedicalTemplateListReturn.setOrderNo(patientMedicalTemplateList.getOrderNo());
			findPatientMedicalTemplateListReturn.setCreater(patientMedicalTemplateList.getCreater());
			findPatientMedicalTemplateListReturn.setCreateTime(patientMedicalTemplateList.getCreateTime());
			
			logger.debug("findPatientMedicalTemplateList(PatientMedicalTemplateListDto) - end - return value={}", findPatientMedicalTemplateListReturn); 
			return findPatientMedicalTemplateListReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找模版目录信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_LIST_FIND_ERROR,"查找模版目录信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientMedicalTemplateListDto> findPatientMedicalTemplateListPage(
			FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage)
			throws TsfaServiceException {
		logger.debug("findPatientMedicalTemplateListPage(FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage={}) - start", findPatientMedicalTemplateListPage); 

		AssertUtils.notNull(findPatientMedicalTemplateListPage);
		List<PatientMedicalTemplateListDto> returnList=null;
		int count = 0;
		try {
			count = patientMedicalTemplateListDao.findPatientMedicalTemplateListPageCount(findPatientMedicalTemplateListPage);
			if (count > 0) {
				returnList = patientMedicalTemplateListDao.findPatientMedicalTemplateListPage(findPatientMedicalTemplateListPage);
			}
		}  catch (Exception e) {
			logger.error("模版目录信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_LIST_FIND_PAGE_ERROR,"模版目录信息不存在错误.！",e);
		}
		Page<PatientMedicalTemplateListDto> returnPage = new Page<PatientMedicalTemplateListDto>(returnList, count, findPatientMedicalTemplateListPage);

		logger.debug("findPatientMedicalTemplateListPage(FindPatientMedicalTemplateListPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public void del(PatientMedicalTemplateListDto patientMedicalTemplateListDto) throws TsfaServiceException {
		logger.debug("del(AddPatientMedicalTemplateList addPatientMedicalTemplateList={}) - start", patientMedicalTemplateListDto); 

		AssertUtils.notNull(patientMedicalTemplateListDto);
		AssertUtils.notAllNull(patientMedicalTemplateListDto.getCode(),"Code不能为空");
		try {
			int i = patientMedicalTemplateListDao.deleteByPrimaryKey(patientMedicalTemplateListDto.getCode());
			logger.debug("del(PatientMedicalTemplateListDto) - end - return={}",i); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除模版目录信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_MEDICAL_TEMPLATE_LIST_ADD_ERROR,"删除模版目录信息错误！",e);
		}
	} 


}
