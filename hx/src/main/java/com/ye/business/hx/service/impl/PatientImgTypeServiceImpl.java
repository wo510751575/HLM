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

import com.ye.business.hx.dto.PatientImgTypeDto;
import com.ye.business.hx.dto.FindPatientImgTypePage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientImgTypeDao;
import com.ye.business.hx.domain.PatientImgType;
import com.ye.business.hx.service.IPatientImgTypeService;
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
public class PatientImgTypeServiceImpl implements IPatientImgTypeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientImgTypeServiceImpl.class);
	

	@Resource
	private IPatientImgTypeDao patientImgTypeDao;
	
	
	@Override
	public void addPatientImgType(
			PatientImgTypeDto patientImgTypeDto) throws TsfaServiceException {
		logger.debug("addPatientImgType(AddPatientImgType addPatientImgType={}) - start", patientImgTypeDto); 

		AssertUtils.notNull(patientImgTypeDto);
		try {
			PatientImgType patientImgType = new PatientImgType();
			//add数据录入
			patientImgType.setCode(patientImgTypeDto.getCode());
			patientImgType.setName(patientImgTypeDto.getName());
			patientImgType.setCreateDate(patientImgTypeDto.getCreateDate());
			patientImgType.setCreateId(patientImgTypeDto.getCreateId());
			patientImgType.setRemark(patientImgTypeDto.getRemark());
			patientImgType.setRemark2(patientImgTypeDto.getRemark2());
			patientImgType.setRemark3(patientImgTypeDto.getRemark3());
			patientImgType.setRemark4(patientImgTypeDto.getRemark4());
			patientImgType.setIsDelete(patientImgTypeDto.getIsDelete());
			patientImgTypeDao.insertSelective(patientImgType);
			logger.debug("addPatientImgType(PatientImgTypeDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者影像类型信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_TYPE_ADD_ERROR,"新增患者影像类型信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询患者影像类型信息
	 *
	 * @param findPatientImgTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientImgTypeDto>  findPatientImgTypes(FindPatientImgTypePage findPatientImgTypePage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientImgTypePage);
		List<PatientImgTypeDto> returnList=null;
		try {
			returnList = patientImgTypeDao.findPatientImgTypes(findPatientImgTypePage);
		} catch (Exception e) {
			logger.error("查找患者影像类型信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_TYPE_NOT_EXIST_ERROR,"患者影像类型信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientImgType(
			PatientImgTypeDto patientImgTypeDto)
			throws TsfaServiceException {
		logger.debug("updatePatientImgType(PatientImgTypeDto patientImgTypeDto={}) - start", patientImgTypeDto); 

		AssertUtils.notNull(patientImgTypeDto);
		AssertUtils.notNullAndEmpty(patientImgTypeDto.getCode(),"Code不能为空");
		try {
			PatientImgType patientImgType = new PatientImgType();
			//update数据录入
			patientImgType.setCode(patientImgTypeDto.getCode());
			patientImgType.setName(patientImgTypeDto.getName());
			patientImgType.setCreateDate(patientImgTypeDto.getCreateDate());
			patientImgType.setCreateId(patientImgTypeDto.getCreateId());
			patientImgType.setRemark(patientImgTypeDto.getRemark());
			patientImgType.setRemark2(patientImgTypeDto.getRemark2());
			patientImgType.setRemark3(patientImgTypeDto.getRemark3());
			patientImgType.setRemark4(patientImgTypeDto.getRemark4());
			patientImgType.setIsDelete(patientImgTypeDto.getIsDelete());
			AssertUtils.notUpdateMoreThanOne(patientImgTypeDao.updateByPrimaryKeySelective(patientImgType));
			logger.debug("updatePatientImgType(PatientImgTypeDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者影像类型信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_TYPE_UPDATE_ERROR,"患者影像类型信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientImgTypeDto findPatientImgType(
			PatientImgTypeDto patientImgTypeDto) throws TsfaServiceException {
		logger.debug("findPatientImgType(FindPatientImgType findPatientImgType={}) - start", patientImgTypeDto); 

		AssertUtils.notNull(patientImgTypeDto);
		AssertUtils.notAllNull(patientImgTypeDto.getCode(),"Code不能为空");
		try {
			PatientImgType patientImgType = patientImgTypeDao.selectByPrimaryKey(patientImgTypeDto.getCode());
			if(patientImgType == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_IMG_TYPE_NOT_EXIST_ERROR,"患者影像类型信息不存在");
			}
			PatientImgTypeDto findPatientImgTypeReturn = new PatientImgTypeDto();
			//find数据录入
			findPatientImgTypeReturn.setCode(patientImgType.getCode());
			findPatientImgTypeReturn.setName(patientImgType.getName());
			findPatientImgTypeReturn.setCreateDate(patientImgType.getCreateDate());
			findPatientImgTypeReturn.setCreateId(patientImgType.getCreateId());
			findPatientImgTypeReturn.setRemark(patientImgType.getRemark());
			findPatientImgTypeReturn.setRemark2(patientImgType.getRemark2());
			findPatientImgTypeReturn.setRemark3(patientImgType.getRemark3());
			findPatientImgTypeReturn.setRemark4(patientImgType.getRemark4());
			findPatientImgTypeReturn.setIsDelete(patientImgType.getIsDelete());
			
			logger.debug("findPatientImgType(PatientImgTypeDto) - end - return value={}", findPatientImgTypeReturn); 
			return findPatientImgTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者影像类型信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_TYPE_FIND_ERROR,"查找患者影像类型信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientImgTypeDto> findPatientImgTypePage(
			FindPatientImgTypePage findPatientImgTypePage)
			throws TsfaServiceException {
		logger.debug("findPatientImgTypePage(FindPatientImgTypePage findPatientImgTypePage={}) - start", findPatientImgTypePage); 

		AssertUtils.notNull(findPatientImgTypePage);
		List<PatientImgTypeDto> returnList=null;
		int count = 0;
		try {
			returnList = patientImgTypeDao.findPatientImgTypePage(findPatientImgTypePage);
			count = patientImgTypeDao.findPatientImgTypePageCount(findPatientImgTypePage);
		}  catch (Exception e) {
			logger.error("患者影像类型信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_TYPE_FIND_PAGE_ERROR,"患者影像类型信息不存在错误.！",e);
		}
		Page<PatientImgTypeDto> returnPage = new Page<PatientImgTypeDto>(returnList, count, findPatientImgTypePage);

		logger.debug("findPatientImgTypePage(FindPatientImgTypePage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
