package com.ye.business.hx.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
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

import com.ye.business.hx.dto.PatientServiceChooseDto;
import com.ye.business.hx.dto.FindPatientServiceChoosePage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPatientServiceChooseDao;
import com.ye.business.hx.domain.PatientServiceChoose;
import com.ye.business.hx.service.IPatientServiceChooseService;
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
public class PatientServiceChooseServiceImpl implements IPatientServiceChooseService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PatientServiceChooseServiceImpl.class);
	

	@Resource
	private IPatientServiceChooseDao patientServiceChooseDao;
	
	
	@Override
	public void addPatientServiceChoose(
			PatientServiceChooseDto patientServiceChooseDto) throws TsfaServiceException {
		logger.debug("addPatientServiceChoose(AddPatientServiceChoose addPatientServiceChoose={}) - start", patientServiceChooseDto); 

		AssertUtils.notNull(patientServiceChooseDto);
		try {
			PatientServiceChoose patientServiceChoose = new PatientServiceChoose();
			//add数据录入
			patientServiceChoose.setCode(GUID.getPreUUID());
			patientServiceChoose.setPatientReservationCode(patientServiceChooseDto.getPatientReservationCode());
			patientServiceChoose.setProjectCode(patientServiceChooseDto.getProjectCode());
			patientServiceChoose.setProjectName(patientServiceChooseDto.getProjectName());
			patientServiceChoose.setProjectPropertyCode(patientServiceChooseDto.getProjectPropertyCode());
			patientServiceChoose.setProjectPropertyName(patientServiceChooseDto.getProjectPropertyName());
			patientServiceChoose.setCreateDate(patientServiceChooseDto.getCreateDate());
			patientServiceChoose.setCreateId(patientServiceChooseDto.getCreateId());
			patientServiceChoose.setRemark(patientServiceChooseDto.getRemark());
			patientServiceChoose.setRemark2(patientServiceChooseDto.getRemark2());
			patientServiceChoose.setRemark3(patientServiceChooseDto.getRemark3());
			patientServiceChoose.setRemark4(patientServiceChooseDto.getRemark4());
			patientServiceChooseDao.insert(patientServiceChoose);
			logger.debug("addPatientServiceChoose(PatientServiceChooseDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者服务选择（预约项目）信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_CHOOSE_ADD_ERROR,"新增患者服务选择（预约项目）信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询患者服务选择（预约项目）信息
	 *
	 * @param findPatientServiceChoosePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<PatientServiceChooseDto>  findPatientServiceChooses(FindPatientServiceChoosePage findPatientServiceChoosePage)throws TsfaServiceException{
		AssertUtils.notNull(findPatientServiceChoosePage);
		List<PatientServiceChooseDto> returnList=null;
		try {
			returnList = patientServiceChooseDao.findPatientServiceChooses(findPatientServiceChoosePage);
		} catch (Exception e) {
			logger.error("查找患者服务选择（预约项目）信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_CHOOSE_NOT_EXIST_ERROR,"患者服务选择（预约项目）信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePatientServiceChoose(
			PatientServiceChooseDto patientServiceChooseDto)
			throws TsfaServiceException {
		logger.debug("updatePatientServiceChoose(PatientServiceChooseDto patientServiceChooseDto={}) - start", patientServiceChooseDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceChooseDto);
		AssertUtils.notNullAndEmpty(patientServiceChooseDto.getCode(),"Code不能为空");
		try {
			PatientServiceChoose patientServiceChoose = new PatientServiceChoose();
			//update数据录入
			patientServiceChoose.setCode(patientServiceChooseDto.getCode());
			patientServiceChoose.setPatientReservationCode(patientServiceChooseDto.getPatientReservationCode());
			patientServiceChoose.setProjectCode(patientServiceChooseDto.getProjectCode());
			patientServiceChoose.setProjectName(patientServiceChooseDto.getProjectName());
			patientServiceChoose.setProjectPropertyCode(patientServiceChooseDto.getProjectPropertyCode());
			patientServiceChoose.setProjectPropertyName(patientServiceChooseDto.getProjectPropertyName());
			patientServiceChoose.setCreateDate(patientServiceChooseDto.getCreateDate());
			patientServiceChoose.setCreateId(patientServiceChooseDto.getCreateId());
			patientServiceChoose.setRemark(patientServiceChooseDto.getRemark());
			patientServiceChoose.setRemark2(patientServiceChooseDto.getRemark2());
			patientServiceChoose.setRemark3(patientServiceChooseDto.getRemark3());
			patientServiceChoose.setRemark4(patientServiceChooseDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(patientServiceChooseDao.updateByPrimaryKeySelective(patientServiceChoose));
			logger.debug("updatePatientServiceChoose(PatientServiceChooseDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者服务选择（预约项目）信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_CHOOSE_UPDATE_ERROR,"患者服务选择（预约项目）信息更新信息错误！",e);
		}
	}

	

	@Override
	public PatientServiceChooseDto findPatientServiceChoose(
			PatientServiceChooseDto patientServiceChooseDto) throws TsfaServiceException {
		logger.debug("findPatientServiceChoose(FindPatientServiceChoose findPatientServiceChoose={}) - start", patientServiceChooseDto); //$NON-NLS-1$

		AssertUtils.notNull(patientServiceChooseDto);
		AssertUtils.notAllNull(patientServiceChooseDto.getCode(),"Code不能为空");
		try {
			PatientServiceChoose patientServiceChoose = patientServiceChooseDao.selectByPrimaryKey(patientServiceChooseDto.getCode());
			if(patientServiceChoose == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_CHOOSE_NOT_EXIST_ERROR,"患者服务选择（预约项目）信息不存在");
			}
			PatientServiceChooseDto findPatientServiceChooseReturn = new PatientServiceChooseDto();
			//find数据录入
			findPatientServiceChooseReturn.setCode(patientServiceChoose.getCode());
			findPatientServiceChooseReturn.setPatientReservationCode(patientServiceChoose.getPatientReservationCode());
			findPatientServiceChooseReturn.setProjectCode(patientServiceChoose.getProjectCode());
			findPatientServiceChooseReturn.setProjectName(patientServiceChoose.getProjectName());
			findPatientServiceChooseReturn.setProjectPropertyCode(patientServiceChoose.getProjectPropertyCode());
			findPatientServiceChooseReturn.setProjectPropertyName(patientServiceChoose.getProjectPropertyName());
			findPatientServiceChooseReturn.setCreateDate(patientServiceChoose.getCreateDate());
			findPatientServiceChooseReturn.setCreateId(patientServiceChoose.getCreateId());
			findPatientServiceChooseReturn.setRemark(patientServiceChoose.getRemark());
			findPatientServiceChooseReturn.setRemark2(patientServiceChoose.getRemark2());
			findPatientServiceChooseReturn.setRemark3(patientServiceChoose.getRemark3());
			findPatientServiceChooseReturn.setRemark4(patientServiceChoose.getRemark4());
			
			logger.debug("findPatientServiceChoose(PatientServiceChooseDto) - end - return value={}", findPatientServiceChooseReturn); //$NON-NLS-1$
			return findPatientServiceChooseReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者服务选择（预约项目）信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_CHOOSE_FIND_ERROR,"查找患者服务选择（预约项目）信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PatientServiceChooseDto> findPatientServiceChoosePage(
			FindPatientServiceChoosePage findPatientServiceChoosePage)
			throws TsfaServiceException {
		logger.debug("findPatientServiceChoosePage(FindPatientServiceChoosePage findPatientServiceChoosePage={}) - start", findPatientServiceChoosePage); //$NON-NLS-1$

		AssertUtils.notNull(findPatientServiceChoosePage);
		List<PatientServiceChooseDto> returnList=null;
		int count = 0;
		try {
			returnList = patientServiceChooseDao.findPatientServiceChoosePage(findPatientServiceChoosePage);
			count = patientServiceChooseDao.findPatientServiceChoosePageCount(findPatientServiceChoosePage);
		}  catch (Exception e) {
			logger.error("患者服务选择（预约项目）信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PATIENT_SERVICE_CHOOSE_FIND_PAGE_ERROR,"患者服务选择（预约项目）信息不存在错误.！",e);
		}
		Page<PatientServiceChooseDto> returnPage = new Page<PatientServiceChooseDto>(returnList, count, findPatientServiceChoosePage);

		logger.debug("findPatientServiceChoosePage(FindPatientServiceChoosePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	
	@Override
	public Integer deleteByPatientReservationCode(String patientReservationCode) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientReservationCode);
		return this.patientServiceChooseDao.deleteByPatientReservationCode(patientReservationCode);
	} 


}
