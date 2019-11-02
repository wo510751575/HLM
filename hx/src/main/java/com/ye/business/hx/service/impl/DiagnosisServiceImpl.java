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

import com.ye.business.hx.dto.DiagnosisDto;
import com.ye.business.hx.dto.FindDiagnosisPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IDiagnosisDao;
import com.ye.business.hx.domain.Diagnosis;
import com.ye.business.hx.service.IDiagnosisService;
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
public class DiagnosisServiceImpl implements IDiagnosisService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(DiagnosisServiceImpl.class);
	

	@Resource
	private IDiagnosisDao diagnosisDao;
	
	
	@Override
	public void addDiagnosis(
			DiagnosisDto diagnosisDto) throws TsfaServiceException {
		logger.debug("addDiagnosis(AddDiagnosis addDiagnosis={}) - start", diagnosisDto); 

		AssertUtils.notNull(diagnosisDto);
		try {
			Diagnosis diagnosis = new Diagnosis();
			//add数据录入
			diagnosis.setCode(diagnosisDto.getCode());
			diagnosis.setName(diagnosisDto.getName());
			diagnosis.setType(diagnosisDto.getType());
			diagnosis.setCreateDate(diagnosisDto.getCreateDate());
			diagnosis.setRemark(diagnosisDto.getRemark());
			diagnosis.setRemark2(diagnosisDto.getRemark2());
			diagnosis.setRemark3(diagnosisDto.getRemark3());
			diagnosis.setRemark4(diagnosisDto.getRemark4());
			diagnosisDao.insertSelective(diagnosis);
			logger.debug("addDiagnosis(DiagnosisDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增牙型/牙骨信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DIAGNOSIS_ADD_ERROR,"新增牙型/牙骨信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询牙型/牙骨信息
	 *
	 * @param findDiagnosisPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<DiagnosisDto>  findDiagnosiss(FindDiagnosisPage findDiagnosisPage)throws TsfaServiceException{
		AssertUtils.notNull(findDiagnosisPage);
		List<DiagnosisDto> returnList=null;
		try {
			returnList = diagnosisDao.findDiagnosiss(findDiagnosisPage);
		} catch (Exception e) {
			logger.error("查找牙型/牙骨信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.DIAGNOSIS_NOT_EXIST_ERROR,"牙型/牙骨信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateDiagnosis(
			DiagnosisDto diagnosisDto)
			throws TsfaServiceException {
		logger.debug("updateDiagnosis(DiagnosisDto diagnosisDto={}) - start", diagnosisDto); 

		AssertUtils.notNull(diagnosisDto);
		AssertUtils.notNullAndEmpty(diagnosisDto.getCode(),"Code不能为空");
		try {
			Diagnosis diagnosis = new Diagnosis();
			//update数据录入
			diagnosis.setCode(diagnosisDto.getCode());
			diagnosis.setName(diagnosisDto.getName());
			diagnosis.setType(diagnosisDto.getType());
			diagnosis.setCreateDate(diagnosisDto.getCreateDate());
			diagnosis.setRemark(diagnosisDto.getRemark());
			diagnosis.setRemark2(diagnosisDto.getRemark2());
			diagnosis.setRemark3(diagnosisDto.getRemark3());
			diagnosis.setRemark4(diagnosisDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(diagnosisDao.updateByPrimaryKeySelective(diagnosis));
			logger.debug("updateDiagnosis(DiagnosisDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("牙型/牙骨信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DIAGNOSIS_UPDATE_ERROR,"牙型/牙骨信息更新信息错误！",e);
		}
	}

	

	@Override
	public DiagnosisDto findDiagnosis(
			DiagnosisDto diagnosisDto) throws TsfaServiceException {
		logger.debug("findDiagnosis(FindDiagnosis findDiagnosis={}) - start", diagnosisDto); 

		AssertUtils.notNull(diagnosisDto);
		AssertUtils.notAllNull(diagnosisDto.getCode(),"Code不能为空");
		try {
			Diagnosis diagnosis = diagnosisDao.selectByPrimaryKey(diagnosisDto.getCode());
			if(diagnosis == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.DIAGNOSIS_NOT_EXIST_ERROR,"牙型/牙骨信息不存在");
			}
			DiagnosisDto findDiagnosisReturn = new DiagnosisDto();
			//find数据录入
			findDiagnosisReturn.setCode(diagnosis.getCode());
			findDiagnosisReturn.setName(diagnosis.getName());
			findDiagnosisReturn.setType(diagnosis.getType());
			findDiagnosisReturn.setCreateDate(diagnosis.getCreateDate());
			findDiagnosisReturn.setRemark(diagnosis.getRemark());
			findDiagnosisReturn.setRemark2(diagnosis.getRemark2());
			findDiagnosisReturn.setRemark3(diagnosis.getRemark3());
			findDiagnosisReturn.setRemark4(diagnosis.getRemark4());
			
			logger.debug("findDiagnosis(DiagnosisDto) - end - return value={}", findDiagnosisReturn); 
			return findDiagnosisReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找牙型/牙骨信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.DIAGNOSIS_FIND_ERROR,"查找牙型/牙骨信息信息错误！",e);
		}


	}

	
	@Override
	public Page<DiagnosisDto> findDiagnosisPage(
			FindDiagnosisPage findDiagnosisPage)
			throws TsfaServiceException {
		logger.debug("findDiagnosisPage(FindDiagnosisPage findDiagnosisPage={}) - start", findDiagnosisPage); 

		AssertUtils.notNull(findDiagnosisPage);
		List<DiagnosisDto> returnList=null;
		int count = 0;
		try {
			count = diagnosisDao.findDiagnosisPageCount(findDiagnosisPage);
			if(count>0) {
				returnList = diagnosisDao.findDiagnosisPage(findDiagnosisPage);
			}
		}  catch (Exception e) {
			logger.error("牙型/牙骨信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.DIAGNOSIS_FIND_PAGE_ERROR,"牙型/牙骨信息不存在错误.！",e);
		}
		Page<DiagnosisDto> returnPage = new Page<DiagnosisDto>(returnList, count, findDiagnosisPage);

		logger.debug("findDiagnosisPage(FindDiagnosisPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	
	@Override
	public void delete(DiagnosisDto diagnosisDto) {
		logger.debug("delete(DiagnosisDto diagnosisDto = {})-start",diagnosisDto);
		try {
			diagnosisDao.deleteByPrimaryKey(diagnosisDto.getCode());
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.DIAGNOSIS_DELETE_ERROR,"删除牙型/牙骨信息错误！",e);
		}
	} 


}
