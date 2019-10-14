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
import com.ye.business.hx.dao.IPatientServiceAdvisoryDao;
import com.ye.business.hx.domain.PatientServiceAdvisory;
import com.ye.business.hx.dto.FindPatientServiceAdvisoryPage;
import com.ye.business.hx.dto.PatientServiceAdvisoryDto;
import com.ye.business.hx.dto.PatientServiceDto;
import com.ye.business.hx.service.IPatientServiceAdvisoryService;
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
public class PatientServiceAdvisoryServiceImpl implements IPatientServiceAdvisoryService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientServiceAdvisoryServiceImpl.class);
	

	@Resource
	private IPatientServiceAdvisoryDao patientServiceAdvisoryDao;
	
	// 服务预约/挂号/就诊
	@Autowired
	private IPatientServiceService patientServiceService;
	
	
	@Override
	public void addPatientServiceAdvisory(
			PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException {
		logger.debug("addPatientServiceAdvisory(AddPatientServiceAdvisory addPatientServiceAdvisory={}) - start", patientServiceAdvisoryDto); 

		AssertUtils.notNull(patientServiceAdvisoryDto);
		try {
			PatientServiceAdvisory patientServiceAdvisory = new PatientServiceAdvisory();
			//add数据录入
			patientServiceAdvisory.setCode(GUID.getPreUUID());
			patientServiceAdvisory.setPatientReservationCode(patientServiceAdvisoryDto.getPatientReservationCode());
			patientServiceAdvisory.setPatientNo(patientServiceAdvisoryDto.getPatientNo());
			patientServiceAdvisory.setPatientName(patientServiceAdvisoryDto.getPatientName());
			patientServiceAdvisory.setVisitingDate(patientServiceAdvisoryDto.getVisitingDate());
			patientServiceAdvisory.setProjectCodes(patientServiceAdvisoryDto.getProjectCodes());
			patientServiceAdvisory.setProjectNames(patientServiceAdvisoryDto.getProjectNames());
			patientServiceAdvisory.setAdvisoryContent(patientServiceAdvisoryDto.getAdvisoryContent());
			patientServiceAdvisory.setExplainRemark(patientServiceAdvisoryDto.getExplainRemark());
			patientServiceAdvisory.setWant(patientServiceAdvisoryDto.getWant());
			patientServiceAdvisory.setCreateDate(patientServiceAdvisoryDto.getCreateDate());
			patientServiceAdvisory.setCreateId(patientServiceAdvisoryDto.getCreateId());
			patientServiceAdvisory.setRemark(patientServiceAdvisoryDto.getRemark());
			patientServiceAdvisory.setRemark2(patientServiceAdvisoryDto.getRemark2());
			patientServiceAdvisory.setRemark3(patientServiceAdvisoryDto.getRemark3());
			patientServiceAdvisory.setRemark4(patientServiceAdvisoryDto.getRemark4());
			patientServiceAdvisoryDao.insert(patientServiceAdvisory);
			logger.debug("addPatientServiceAdvisory(PatientServiceAdvisoryDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者服务咨询信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADVISORY_ADD_ERROR,"新增患者服务咨询信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询患者服务咨询信息
	 *
	 * @param findPatientServiceAdvisoryPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<PatientServiceAdvisoryDto>  findPatientServiceAdvisorys(FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientServiceAdvisoryPage);
		List<PatientServiceAdvisoryDto> returnList=null;
		try {
			returnList = patientServiceAdvisoryDao.findPatientServiceAdvisorys(findPatientServiceAdvisoryPage);
		} catch (Exception e) {
			logger.error("查找患者服务咨询信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADVISORY_NOT_EXIST_ERROR,"患者服务咨询信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientServiceAdvisory(
			PatientServiceAdvisoryDto patientServiceAdvisoryDto)
			throws TsfaServiceException {
		logger.debug("updatePatientServiceAdvisory(PatientServiceAdvisoryDto patientServiceAdvisoryDto={}) - start", patientServiceAdvisoryDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceAdvisoryDto);
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getCode(),"Code不能为空");
		try {
			PatientServiceAdvisory patientServiceAdvisory = new PatientServiceAdvisory();
			//update数据录入
			patientServiceAdvisory.setCode(patientServiceAdvisoryDto.getCode());
			patientServiceAdvisory.setPatientReservationCode(patientServiceAdvisoryDto.getPatientReservationCode());
			patientServiceAdvisory.setPatientNo(patientServiceAdvisoryDto.getPatientNo());
			patientServiceAdvisory.setPatientName(patientServiceAdvisoryDto.getPatientName());
			patientServiceAdvisory.setVisitingDate(patientServiceAdvisoryDto.getVisitingDate());
			patientServiceAdvisory.setProjectCodes(patientServiceAdvisoryDto.getProjectCodes());
			patientServiceAdvisory.setProjectNames(patientServiceAdvisoryDto.getProjectNames());
			patientServiceAdvisory.setAdvisoryContent(patientServiceAdvisoryDto.getAdvisoryContent());
			patientServiceAdvisory.setExplainRemark(patientServiceAdvisoryDto.getExplainRemark());
			patientServiceAdvisory.setWant(patientServiceAdvisoryDto.getWant());
			patientServiceAdvisory.setCreateDate(patientServiceAdvisoryDto.getCreateDate());
			patientServiceAdvisory.setCreateId(patientServiceAdvisoryDto.getCreateId());
			patientServiceAdvisory.setRemark(patientServiceAdvisoryDto.getRemark());
			patientServiceAdvisory.setRemark2(patientServiceAdvisoryDto.getRemark2());
			patientServiceAdvisory.setRemark3(patientServiceAdvisoryDto.getRemark3());
			patientServiceAdvisory.setRemark4(patientServiceAdvisoryDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(patientServiceAdvisoryDao.updateByPrimaryKeySelective(patientServiceAdvisory));
			logger.debug("updatePatientServiceAdvisory(PatientServiceAdvisoryDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者服务咨询信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADVISORY_UPDATE_ERROR,"患者服务咨询信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientServiceAdvisoryDto findPatientServiceAdvisory(
			PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException {
		logger.debug("findPatientServiceAdvisory(FindPatientServiceAdvisory findPatientServiceAdvisory={}) - start", patientServiceAdvisoryDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceAdvisoryDto);
		AssertUtils.notAllNull(patientServiceAdvisoryDto.getCode(),"Code不能为空");
		try {
			PatientServiceAdvisory patientServiceAdvisory = patientServiceAdvisoryDao.selectByPrimaryKey(patientServiceAdvisoryDto.getCode());
			if(patientServiceAdvisory == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADVISORY_NOT_EXIST_ERROR,"患者服务咨询信息不存在");
			}
			PatientServiceAdvisoryDto findPatientServiceAdvisoryReturn = new PatientServiceAdvisoryDto();
			//find数据录入
			findPatientServiceAdvisoryReturn.setCode(patientServiceAdvisory.getCode());
			findPatientServiceAdvisoryReturn.setPatientReservationCode(patientServiceAdvisory.getPatientReservationCode());
			findPatientServiceAdvisoryReturn.setPatientNo(patientServiceAdvisory.getPatientNo());
			findPatientServiceAdvisoryReturn.setPatientName(patientServiceAdvisory.getPatientName());
			findPatientServiceAdvisoryReturn.setVisitingDate(patientServiceAdvisory.getVisitingDate());
			findPatientServiceAdvisoryReturn.setProjectCodes(patientServiceAdvisory.getProjectCodes());
			findPatientServiceAdvisoryReturn.setProjectNames(patientServiceAdvisory.getProjectNames());
			findPatientServiceAdvisoryReturn.setAdvisoryContent(patientServiceAdvisory.getAdvisoryContent());
			findPatientServiceAdvisoryReturn.setExplainRemark(patientServiceAdvisory.getExplainRemark());
			findPatientServiceAdvisoryReturn.setWant(patientServiceAdvisory.getWant());
			findPatientServiceAdvisoryReturn.setCreateDate(patientServiceAdvisory.getCreateDate());
			findPatientServiceAdvisoryReturn.setCreateId(patientServiceAdvisory.getCreateId());
			findPatientServiceAdvisoryReturn.setRemark(patientServiceAdvisory.getRemark());
			findPatientServiceAdvisoryReturn.setRemark2(patientServiceAdvisory.getRemark2());
			findPatientServiceAdvisoryReturn.setRemark3(patientServiceAdvisory.getRemark3());
			findPatientServiceAdvisoryReturn.setRemark4(patientServiceAdvisory.getRemark4());
			
			logger.debug("findPatientServiceAdvisory(PatientServiceAdvisoryDto) - end - return value={}", findPatientServiceAdvisoryReturn); //$NON-NLS-1$
			return findPatientServiceAdvisoryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者服务咨询信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADVISORY_FIND_ERROR,"查找患者服务咨询信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientServiceAdvisoryDto> findPatientServiceAdvisoryPage(
			FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage)
			throws TsfaServiceException {
		logger.debug("findPatientServiceAdvisoryPage(FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage={}) - start", findPatientServiceAdvisoryPage); //$NON-NLS-1$

		AssertUtils.notNull(findPatientServiceAdvisoryPage);
		List<PatientServiceAdvisoryDto> returnList=null;
		int count = 0;
		try {
			returnList = patientServiceAdvisoryDao.findPatientServiceAdvisoryPage(findPatientServiceAdvisoryPage);
			count = patientServiceAdvisoryDao.findPatientServiceAdvisoryPageCount(findPatientServiceAdvisoryPage);
		}  catch (Exception e) {
			logger.error("患者服务咨询信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADVISORY_FIND_PAGE_ERROR,"患者服务咨询信息不存在错误.！",e);
		}
		Page<PatientServiceAdvisoryDto> returnPage = new Page<PatientServiceAdvisoryDto>(returnList, count, findPatientServiceAdvisoryPage);

		logger.debug("findPatientServiceAdvisoryPage(FindPatientServiceAdvisoryPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 

	@Override
	public String addPatientServiceAdvisoryByReservation(PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException {
		logger.debug("addPatientServiceAdvisoryByReservation(AddPatientServiceAdvisory addPatientServiceAdvisory={}) - start", patientServiceAdvisoryDto); 

		AssertUtils.notNull(patientServiceAdvisoryDto);
		try {
			PatientServiceAdvisory patientServiceAdvisory = new PatientServiceAdvisory();
			//add数据录入
			patientServiceAdvisory.setCode(GUID.getPreUUID());
			patientServiceAdvisory.setPatientReservationCode(patientServiceAdvisoryDto.getPatientReservationCode());
			patientServiceAdvisory.setPatientNo(patientServiceAdvisoryDto.getPatientNo());
			patientServiceAdvisory.setPatientName(patientServiceAdvisoryDto.getPatientName());
			patientServiceAdvisory.setVisitingDate(patientServiceAdvisoryDto.getVisitingDate());
			patientServiceAdvisory.setProjectCodes(patientServiceAdvisoryDto.getProjectCodes());
			patientServiceAdvisory.setProjectNames(patientServiceAdvisoryDto.getProjectNames());
			patientServiceAdvisory.setAdvisoryContent(patientServiceAdvisoryDto.getAdvisoryContent());
			patientServiceAdvisory.setExplainRemark(patientServiceAdvisoryDto.getExplainRemark());
			patientServiceAdvisory.setWant(patientServiceAdvisoryDto.getWant());
			patientServiceAdvisory.setCreateDate(patientServiceAdvisoryDto.getCreateDate());
			patientServiceAdvisory.setCreateId(patientServiceAdvisoryDto.getCreateId());
			patientServiceAdvisory.setRemark(patientServiceAdvisoryDto.getRemark());
			patientServiceAdvisory.setRemark2(patientServiceAdvisoryDto.getRemark2());
			patientServiceAdvisory.setRemark3(patientServiceAdvisoryDto.getRemark3());
			patientServiceAdvisory.setRemark4(patientServiceAdvisoryDto.getRemark4());
			patientServiceAdvisoryDao.insert(patientServiceAdvisory);
			
			// 患者预约code不为空，修改患者预约咨询时间
			if (StringUtils.isNotBlank(patientServiceAdvisoryDto.getPatientReservationCode())) {
				
				PatientServiceDto patientServiceDto = new PatientServiceDto();
				
				patientServiceDto.setCode(patientServiceAdvisoryDto.getPatientReservationCode());
				patientServiceDto.setUpdateId(patientServiceAdvisoryDto.getCreateId());
				patientServiceDto.setUpdateDate(patientServiceAdvisoryDto.getCreateDate());
				patientServiceDto.setAdvisoryDate(patientServiceAdvisoryDto.getCreateDate());
				
				this.patientServiceService.updatePatientService(patientServiceDto);
				
			}
			
			logger.debug("addPatientServiceAdvisoryByReservation(PatientServiceAdvisoryDto) - end - return");
			
			return patientServiceAdvisory.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者服务咨询信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADVISORY_ADD_ERROR,"新增患者服务咨询信息错误！",e);
		}
	}
	
	@Override
	public PatientServiceAdvisoryDto findPatientServiceAdvisoryByPatientReservationCode(PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException {
		logger.debug("findPatientServiceAdvisoryByPatientReservationCode(FindPatientServiceAdvisory findPatientServiceAdvisory={}) - start", patientServiceAdvisoryDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceAdvisoryDto);
		AssertUtils.notAllNull(patientServiceAdvisoryDto.getPatientReservationCode(),"患者服务CODE不能为空");
		try {
			PatientServiceAdvisory patientServiceAdvisory = patientServiceAdvisoryDao.selectByPatientReservationCode(patientServiceAdvisoryDto.getPatientReservationCode());
			
			if(patientServiceAdvisory == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADVISORY_NOT_EXIST_ERROR,"患者服务咨询信息不存在");
			}
			PatientServiceAdvisoryDto findPatientServiceAdvisoryReturn = new PatientServiceAdvisoryDto();
			//find数据录入
			findPatientServiceAdvisoryReturn.setCode(patientServiceAdvisory.getCode());
			findPatientServiceAdvisoryReturn.setPatientReservationCode(patientServiceAdvisory.getPatientReservationCode());
			findPatientServiceAdvisoryReturn.setPatientNo(patientServiceAdvisory.getPatientNo());
			findPatientServiceAdvisoryReturn.setPatientName(patientServiceAdvisory.getPatientName());
			findPatientServiceAdvisoryReturn.setVisitingDate(patientServiceAdvisory.getVisitingDate());
			findPatientServiceAdvisoryReturn.setProjectCodes(patientServiceAdvisory.getProjectCodes());
			findPatientServiceAdvisoryReturn.setProjectNames(patientServiceAdvisory.getProjectNames());
			findPatientServiceAdvisoryReturn.setAdvisoryContent(patientServiceAdvisory.getAdvisoryContent());
			findPatientServiceAdvisoryReturn.setExplainRemark(patientServiceAdvisory.getExplainRemark());
			findPatientServiceAdvisoryReturn.setWant(patientServiceAdvisory.getWant());
			findPatientServiceAdvisoryReturn.setCreateDate(patientServiceAdvisory.getCreateDate());
			findPatientServiceAdvisoryReturn.setCreateId(patientServiceAdvisory.getCreateId());
			findPatientServiceAdvisoryReturn.setRemark(patientServiceAdvisory.getRemark());
			findPatientServiceAdvisoryReturn.setRemark2(patientServiceAdvisory.getRemark2());
			findPatientServiceAdvisoryReturn.setRemark3(patientServiceAdvisory.getRemark3());
			findPatientServiceAdvisoryReturn.setRemark4(patientServiceAdvisory.getRemark4());
			
			logger.debug("findPatientServiceAdvisoryByPatientReservationCode(PatientServiceAdvisoryDto) - end - return value={}", findPatientServiceAdvisoryReturn); //$NON-NLS-1$
			return findPatientServiceAdvisoryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者服务咨询信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_ADVISORY_FIND_ERROR,"查找患者服务咨询信息信息错误！",e);
		}


	}
}
