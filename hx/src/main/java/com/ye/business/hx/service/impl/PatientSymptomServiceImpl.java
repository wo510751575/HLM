package com.ye.business.hx.service.impl;

import java.util.ArrayList;
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

import com.ye.business.hx.dto.PatientSymptomDto;
import com.ye.business.hx.dto.PatientSymptomVo;
import com.ye.business.hx.dto.FindPatientSymptomPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientSymptomDao;
import com.ye.business.hx.domain.PatientSymptom;
import com.ye.business.hx.service.IPatientSymptomService;
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
public class PatientSymptomServiceImpl implements IPatientSymptomService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientSymptomServiceImpl.class);
	

	@Resource
	private IPatientSymptomDao patientSymptomDao;
	
	
	@Override
	public void addPatientSymptom(
			PatientSymptomDto patientSymptomDto) throws TsfaServiceException {
		logger.debug("addPatientSymptom(AddPatientSymptom addPatientSymptom={}) - start", patientSymptomDto); 

		AssertUtils.notNull(patientSymptomDto);
		try {
			PatientSymptom patientSymptom = new PatientSymptom();
			//add数据录入
			patientSymptom.setCode(patientSymptomDto.getCode());
			patientSymptom.setName(patientSymptomDto.getName());
			patientSymptom.setParentCode(patientSymptomDto.getParentCode());
			patientSymptom.setCreateDate(patientSymptomDto.getCreateDate());
			patientSymptom.setRemark(patientSymptomDto.getRemark());
			patientSymptom.setLevel(patientSymptomDto.getLevel());
			patientSymptomDao.insertSelective(patientSymptom);
			logger.debug("addPatientSymptom(PatientSymptomDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增症状信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SYMPTOM_ADD_ERROR,"新增症状信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询症状信息
	 *
	 * @param findPatientSymptomPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<PatientSymptomVo>  findPatientSymptoms(FindPatientSymptomPage findPatientSymptomPage)throws TsfaServiceException{
		List<PatientSymptomVo> returnList=null;
		List<PatientSymptomVo> listOne = new ArrayList<PatientSymptomVo>();
		try {
			returnList = patientSymptomDao.findPatientSymptoms(findPatientSymptomPage);
			//组装第一层
			if(returnList.size()>0) {
				for (PatientSymptomVo patientSymptomVo : returnList) {
					if(patientSymptomVo.getLevel()==1) {
						listOne.add(patientSymptomVo);
					}
				}
				//组装第二层
				if(listOne.size()>0) {
					for (PatientSymptomVo patientSymptomVo2 : listOne) {
						List<PatientSymptomVo> listTwo = new ArrayList<PatientSymptomVo>();
						for (PatientSymptomVo patientSymptomVo3 : returnList) {
							if(patientSymptomVo3.getLevel()==2&&patientSymptomVo3.getParentCode().equals(patientSymptomVo2.getCode())) {
								listTwo.add(patientSymptomVo3);
							}
						}
						patientSymptomVo2.setChildren(listTwo);
					}
				}
			}
		} catch (Exception e) {
			logger.error("查找症状信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SYMPTOM_NOT_EXIST_ERROR,"症状信息不存在");
		}
		return listOne;
	}
	

	@Override
	public void updatePatientSymptom(
			PatientSymptomDto patientSymptomDto)
			throws TsfaServiceException {
		logger.debug("updatePatientSymptom(PatientSymptomDto patientSymptomDto={}) - start", patientSymptomDto); 

		AssertUtils.notNull(patientSymptomDto);
		AssertUtils.notNullAndEmpty(patientSymptomDto.getCode(),"Code不能为空");
		try {
			PatientSymptom patientSymptom = new PatientSymptom();
			//update数据录入
			patientSymptom.setCode(patientSymptomDto.getCode());
			patientSymptom.setName(patientSymptomDto.getName());
			patientSymptom.setParentCode(patientSymptomDto.getParentCode());
			patientSymptom.setCreateDate(patientSymptomDto.getCreateDate());
			patientSymptom.setRemark(patientSymptomDto.getRemark());
			patientSymptom.setLevel(patientSymptomDto.getLevel());
			AssertUtils.notUpdateMoreThanOne(patientSymptomDao.updateByPrimaryKeySelective(patientSymptom));
			logger.debug("updatePatientSymptom(PatientSymptomDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("症状信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SYMPTOM_UPDATE_ERROR,"症状信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientSymptomDto findPatientSymptom(
			PatientSymptomDto patientSymptomDto) throws TsfaServiceException {
		logger.debug("findPatientSymptom(FindPatientSymptom findPatientSymptom={}) - start", patientSymptomDto); 

		AssertUtils.notNull(patientSymptomDto);
		AssertUtils.notAllNull(patientSymptomDto.getCode(),"Code不能为空");
		try {
			PatientSymptom patientSymptom = patientSymptomDao.selectByPrimaryKey(patientSymptomDto.getCode());
			if(patientSymptom == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_SYMPTOM_NOT_EXIST_ERROR,"症状信息不存在");
			}
			PatientSymptomDto findPatientSymptomReturn = new PatientSymptomDto();
			//find数据录入
			findPatientSymptomReturn.setCode(patientSymptom.getCode());
			findPatientSymptomReturn.setName(patientSymptom.getName());
			findPatientSymptomReturn.setParentCode(patientSymptom.getParentCode());
			findPatientSymptomReturn.setCreateDate(patientSymptom.getCreateDate());
			findPatientSymptomReturn.setRemark(patientSymptom.getRemark());
			findPatientSymptomReturn.setLevel(patientSymptom.getLevel());
			
			logger.debug("findPatientSymptom(PatientSymptomDto) - end - return value={}", findPatientSymptomReturn); 
			return findPatientSymptomReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找症状信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SYMPTOM_FIND_ERROR,"查找症状信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientSymptomDto> findPatientSymptomPage(
			FindPatientSymptomPage findPatientSymptomPage)
			throws TsfaServiceException {
		logger.debug("findPatientSymptomPage(FindPatientSymptomPage findPatientSymptomPage={}) - start", findPatientSymptomPage); 

		AssertUtils.notNull(findPatientSymptomPage);
		List<PatientSymptomDto> returnList=null;
		int count = 0;
		try {
			returnList = patientSymptomDao.findPatientSymptomPage(findPatientSymptomPage);
			count = patientSymptomDao.findPatientSymptomPageCount(findPatientSymptomPage);
		}  catch (Exception e) {
			logger.error("症状信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SYMPTOM_FIND_PAGE_ERROR,"症状信息不存在错误.！",e);
		}
		Page<PatientSymptomDto> returnPage = new Page<PatientSymptomDto>(returnList, count, findPatientSymptomPage);

		logger.debug("findPatientSymptomPage(FindPatientSymptomPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
