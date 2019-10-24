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
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientImgDao;
import com.ye.business.hx.domain.PatientImg;
import com.ye.business.hx.dto.FindPatientImgPage;
import com.ye.business.hx.dto.PatientImgDto;
import com.ye.business.hx.service.IPatientImgService;
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
public class PatientImgServiceImpl implements IPatientImgService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientImgServiceImpl.class);
	

	@Resource
	private IPatientImgDao patientImgDao;
	
	
	@Override
	public void addPatientImg(
			PatientImgDto patientImgDto) throws TsfaServiceException {
		logger.debug("addPatientImg(AddPatientImg addPatientImg={}) - start", patientImgDto); 

		AssertUtils.notNull(patientImgDto);
		try {
			PatientImg patientImg = new PatientImg();
			//add数据录入
			patientImg.setCode(patientImgDto.getCode());
			patientImg.setPatientCode(patientImgDto.getPatientCode());
			patientImg.setImgUrl(patientImgDto.getImgUrl());
			patientImg.setCreateDate(patientImgDto.getCreateDate());
			patientImg.setCreateId(patientImgDto.getCreateId());
			patientImg.setRemark(patientImgDto.getRemark());
			patientImg.setRemark2(patientImgDto.getRemark2());
			patientImg.setRemark3(patientImgDto.getRemark3());
			patientImg.setRemark4(patientImgDto.getRemark4());
			patientImg.setUpdateDate(patientImgDto.getUpdateDate());
			patientImg.setUpdateId(patientImgDto.getUpdateId());
			patientImg.setImgTypeCode(patientImgDto.getImgTypeCode());
			patientImg.setReservationDate(patientImgDto.getReservationDate());
			patientImg.setIsDelete(patientImgDto.getIsDelete());
			patientImgDao.insertSelective(patientImg);
			logger.debug("addPatientImg(PatientImgDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者影像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_ADD_ERROR,"新增患者影像信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询患者影像信息
	 *
	 * @param findPatientImgPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientImgDto>  findPatientImgs(FindPatientImgPage findPatientImgPage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientImgPage);
		List<PatientImgDto> returnList=null;
		try {
			returnList = patientImgDao.findPatientImgs(findPatientImgPage);
		} catch (Exception e) {
			logger.error("查找患者影像信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_NOT_EXIST_ERROR,"患者影像信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientImg(
			PatientImgDto patientImgDto)
			throws TsfaServiceException {
		logger.debug("updatePatientImg(PatientImgDto patientImgDto={}) - start", patientImgDto); 

		AssertUtils.notNull(patientImgDto);
		AssertUtils.notNullAndEmpty(patientImgDto.getCode(),"Code不能为空");
		try {
			PatientImg patientImg = new PatientImg();
			//update数据录入
			patientImg.setCode(patientImgDto.getCode());
			patientImg.setPatientCode(patientImgDto.getPatientCode());
			patientImg.setImgUrl(patientImgDto.getImgUrl());
			patientImg.setCreateDate(patientImgDto.getCreateDate());
			patientImg.setCreateId(patientImgDto.getCreateId());
			patientImg.setRemark(patientImgDto.getRemark());
			patientImg.setRemark2(patientImgDto.getRemark2());
			patientImg.setRemark3(patientImgDto.getRemark3());
			patientImg.setRemark4(patientImgDto.getRemark4());
			patientImg.setUpdateDate(patientImgDto.getUpdateDate());
			patientImg.setUpdateId(patientImgDto.getUpdateId());
			patientImg.setImgTypeCode(patientImgDto.getImgTypeCode());
			patientImg.setReservationDate(patientImgDto.getReservationDate());
			patientImg.setIsDelete(patientImgDto.getIsDelete());
			AssertUtils.notUpdateMoreThanOne(patientImgDao.updateByPrimaryKeySelective(patientImg));
			logger.debug("updatePatientImg(PatientImgDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者影像信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_UPDATE_ERROR,"患者影像信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientImgDto findPatientImg(
			PatientImgDto patientImgDto) throws TsfaServiceException {
		logger.debug("findPatientImg(FindPatientImg findPatientImg={}) - start", patientImgDto); 

		AssertUtils.notNull(patientImgDto);
		AssertUtils.notAllNull(patientImgDto.getCode(),"Code不能为空");
		try {
			PatientImg patientImg = patientImgDao.selectByPrimaryKey(patientImgDto.getCode());
			if(patientImg == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_IMG_NOT_EXIST_ERROR,"患者影像信息不存在");
			}
			PatientImgDto findPatientImgReturn = new PatientImgDto();
			//find数据录入
			findPatientImgReturn.setCode(patientImg.getCode());
			findPatientImgReturn.setPatientCode(patientImg.getPatientCode());
			findPatientImgReturn.setImgUrl(patientImg.getImgUrl());
			findPatientImgReturn.setCreateDate(patientImg.getCreateDate());
			findPatientImgReturn.setCreateId(patientImg.getCreateId());
			findPatientImgReturn.setRemark(patientImg.getRemark());
			findPatientImgReturn.setRemark2(patientImg.getRemark2());
			findPatientImgReturn.setRemark3(patientImg.getRemark3());
			findPatientImgReturn.setRemark4(patientImg.getRemark4());
			findPatientImgReturn.setUpdateDate(patientImg.getUpdateDate());
			findPatientImgReturn.setUpdateId(patientImg.getUpdateId());
			findPatientImgReturn.setImgTypeCode(patientImg.getImgTypeCode());
			findPatientImgReturn.setReservationDate(patientImg.getReservationDate());
			findPatientImgReturn.setIsDelete(patientImg.getIsDelete());
			
			logger.debug("findPatientImg(PatientImgDto) - end - return value={}", findPatientImgReturn); 
			return findPatientImgReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者影像信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_FIND_ERROR,"查找患者影像信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientImgDto> findPatientImgPage(
			FindPatientImgPage findPatientImgPage)
			throws TsfaServiceException {
		logger.debug("findPatientImgPage(FindPatientImgPage findPatientImgPage={}) - start", findPatientImgPage); 

		AssertUtils.notNull(findPatientImgPage);
		List<PatientImgDto> returnList=null;
		int count = 0;
		try {
			returnList = patientImgDao.findPatientImgPage(findPatientImgPage);
			count = patientImgDao.findPatientImgPageCount(findPatientImgPage);
		}  catch (Exception e) {
			logger.error("患者影像信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_IMG_FIND_PAGE_ERROR,"患者影像信息不存在错误.！",e);
		}
		Page<PatientImgDto> returnPage = new Page<PatientImgDto>(returnList, count, findPatientImgPage);

		logger.debug("findPatientImgPage(FindPatientImgPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	/**   
	 * <p>Title: deleteImg</p>   
	 * <p>Description: </p>   
	 * @param dto
	 * @throws TsfaServiceException   
	 * @see com.ye.business.hx.service.IPatientImgService#deleteImg(com.ye.business.hx.dto.PatientImgDto)   
	 */
	@Override
	public void deleteImg(PatientImgDto dto) throws TsfaServiceException {
		logger.debug("deleteImg(PatientImgDto dto = {}) - start",dto);
		try {
			patientImgDao.deleteByPrimaryKey(dto.getCode());
		} catch (Exception e) {
			logger.error("删除影像图片错误!");
			throw e;
		}
		logger.debug("deleteImg(PatientImgDto dto = {}) - end");
	} 


}
