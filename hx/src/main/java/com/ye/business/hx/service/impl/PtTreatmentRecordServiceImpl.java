package com.ye.business.hx.service.impl;

import java.util.Date;
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
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IPtTreatmentRecordDao;
import com.ye.business.hx.domain.PtTreatmentRecord;
import com.ye.business.hx.dto.FindPtTreatmentRecordPage;
import com.ye.business.hx.dto.PtTreatmentRecordDto;
import com.ye.business.hx.service.IPtTreatmentRecordService;
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
public class PtTreatmentRecordServiceImpl implements IPtTreatmentRecordService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PtTreatmentRecordServiceImpl.class);
	

	@Resource
	private IPtTreatmentRecordDao ptTreatmentRecordDao;
	
	
	@Override
	public PtTreatmentRecordDto addPtTreatmentRecord(
			PtTreatmentRecordDto ptTreatmentRecordDto) throws TsfaServiceException {
		logger.debug("addPtTreatmentRecord(AddPtTreatmentRecord addPtTreatmentRecord={}) - start", ptTreatmentRecordDto); 

		AssertUtils.notNull(ptTreatmentRecordDto);
		try {
			PtTreatmentRecord ptTreatmentRecord = new PtTreatmentRecord();
			//add数据录入
			ptTreatmentRecord.setCode(GUID.getPreUUID());
			ptTreatmentRecord.setMemberNo(ptTreatmentRecordDto.getMemberNo());
			ptTreatmentRecord.setPatientNo(ptTreatmentRecordDto.getPatientNo());
			ptTreatmentRecord.setPatientName(ptTreatmentRecordDto.getPatientName());
			ptTreatmentRecord.setMedicalNo(ptTreatmentRecordDto.getMedicalNo());
			ptTreatmentRecord.setShopNo(ptTreatmentRecordDto.getShopNo());
			ptTreatmentRecord.setShopName(ptTreatmentRecordDto.getShopName());
			ptTreatmentRecord.setMerchantNo(ptTreatmentRecordDto.getMerchantNo());
			ptTreatmentRecord.setMerchantName(ptTreatmentRecordDto.getMerchantName());
			ptTreatmentRecord.setClinicTime(ptTreatmentRecordDto.getClinicTime());
			ptTreatmentRecord.setProjectCode(ptTreatmentRecordDto.getProjectCode());
			ptTreatmentRecord.setProjectName(ptTreatmentRecordDto.getProjectName());
			ptTreatmentRecord.setImg(ptTreatmentRecordDto.getImg());
			ptTreatmentRecord.setUpdateId(ptTreatmentRecordDto.getUpdateId());
			ptTreatmentRecord.setUpdateDate(new Date());
			ptTreatmentRecord.setCreateId(ptTreatmentRecordDto.getCreateId());
			ptTreatmentRecord.setCreateDate(new Date());
			ptTreatmentRecord.setRemark(ptTreatmentRecordDto.getRemark());
			ptTreatmentRecord.setRemark1(ptTreatmentRecordDto.getRemark1());
			ptTreatmentRecord.setRemark2(ptTreatmentRecordDto.getRemark2());
			ptTreatmentRecord.setRemark3(ptTreatmentRecordDto.getRemark3());
			ptTreatmentRecord.setRemark4(ptTreatmentRecordDto.getRemark4());
			ptTreatmentRecordDao.insertSelective(ptTreatmentRecord);
			
			PtTreatmentRecordDto rt = new PtTreatmentRecordDto();
			rt.setCode(ptTreatmentRecord.getCode());
			logger.debug("addPtTreatmentRecord(PtTreatmentRecordDto) - end - return"); 
			return rt;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增患者就诊记录信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PT_TREATMENT_RECORD_ADD_ERROR,"新增患者就诊记录信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询患者就诊记录信息
	 *
	 * @param findPtTreatmentRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<PtTreatmentRecordDto>  findPtTreatmentRecords(FindPtTreatmentRecordPage findPtTreatmentRecordPage)throws TsfaServiceException{
		AssertUtils.notNull(findPtTreatmentRecordPage);
		List<PtTreatmentRecordDto> returnList=null;
		try {
			returnList = ptTreatmentRecordDao.findPtTreatmentRecords(findPtTreatmentRecordPage);
		} catch (Exception e) {
			logger.error("查找患者就诊记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PT_TREATMENT_RECORD_NOT_EXIST_ERROR,"患者就诊记录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePtTreatmentRecord(
			PtTreatmentRecordDto ptTreatmentRecordDto)
			throws TsfaServiceException {
		logger.debug("updatePtTreatmentRecord(PtTreatmentRecordDto ptTreatmentRecordDto={}) - start", ptTreatmentRecordDto); //$NON-NLS-1$

		AssertUtils.notNull(ptTreatmentRecordDto);
		AssertUtils.notNullAndEmpty(ptTreatmentRecordDto.getCode(),"Code不能为空");
		try {
			PtTreatmentRecord ptTreatmentRecord = new PtTreatmentRecord();
			//update数据录入
			ptTreatmentRecord.setCode(ptTreatmentRecordDto.getCode());
			ptTreatmentRecord.setMemberNo(ptTreatmentRecordDto.getMemberNo());
			ptTreatmentRecord.setPatientNo(ptTreatmentRecordDto.getPatientNo());
			ptTreatmentRecord.setPatientName(ptTreatmentRecordDto.getPatientName());
			ptTreatmentRecord.setMedicalNo(ptTreatmentRecordDto.getMedicalNo());
			ptTreatmentRecord.setShopNo(ptTreatmentRecordDto.getShopNo());
			ptTreatmentRecord.setShopName(ptTreatmentRecordDto.getShopName());
			ptTreatmentRecord.setMerchantNo(ptTreatmentRecordDto.getMerchantNo());
			ptTreatmentRecord.setMerchantName(ptTreatmentRecordDto.getMerchantName());
			ptTreatmentRecord.setClinicTime(ptTreatmentRecordDto.getClinicTime());
			ptTreatmentRecord.setProjectCode(ptTreatmentRecordDto.getProjectCode());
			ptTreatmentRecord.setProjectName(ptTreatmentRecordDto.getProjectName());
			ptTreatmentRecord.setImg(ptTreatmentRecordDto.getImg());
			ptTreatmentRecord.setUpdateId(ptTreatmentRecordDto.getUpdateId());
			ptTreatmentRecord.setUpdateDate(ptTreatmentRecordDto.getUpdateDate());
//			ptTreatmentRecord.setCreateId(ptTreatmentRecordDto.getCreateId());
//			ptTreatmentRecord.setCreateDate(new Date());
			ptTreatmentRecord.setRemark(ptTreatmentRecordDto.getRemark());
			ptTreatmentRecord.setRemark1(ptTreatmentRecordDto.getRemark1());
			ptTreatmentRecord.setRemark2(ptTreatmentRecordDto.getRemark2());
			ptTreatmentRecord.setRemark3(ptTreatmentRecordDto.getRemark3());
			ptTreatmentRecord.setRemark4(ptTreatmentRecordDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(ptTreatmentRecordDao.updateByPrimaryKeySelective(ptTreatmentRecord));
			logger.debug("updatePtTreatmentRecord(PtTreatmentRecordDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("患者就诊记录信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PT_TREATMENT_RECORD_UPDATE_ERROR,"患者就诊记录信息更新信息错误！",e);
		}
	}

	

	@Override
	public PtTreatmentRecordDto findPtTreatmentRecord(
			PtTreatmentRecordDto ptTreatmentRecordDto) throws TsfaServiceException {
		logger.debug("findPtTreatmentRecord(FindPtTreatmentRecord findPtTreatmentRecord={}) - start", ptTreatmentRecordDto); //$NON-NLS-1$

		AssertUtils.notNull(ptTreatmentRecordDto);
		AssertUtils.notAllNull(ptTreatmentRecordDto.getCode(),"Code不能为空");
		try {
			PtTreatmentRecord ptTreatmentRecord = ptTreatmentRecordDao.selectByPrimaryKey(ptTreatmentRecordDto.getCode());
			if(ptTreatmentRecord == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PT_TREATMENT_RECORD_NOT_EXIST_ERROR,"患者就诊记录信息不存在");
			}
			PtTreatmentRecordDto findPtTreatmentRecordReturn = new PtTreatmentRecordDto();
			//find数据录入
			findPtTreatmentRecordReturn.setCode(ptTreatmentRecord.getCode());
			findPtTreatmentRecordReturn.setMemberNo(ptTreatmentRecord.getMemberNo());
			findPtTreatmentRecordReturn.setPatientNo(ptTreatmentRecord.getPatientNo());
			findPtTreatmentRecordReturn.setPatientName(ptTreatmentRecord.getPatientName());
			findPtTreatmentRecordReturn.setMedicalNo(ptTreatmentRecord.getMedicalNo());
			findPtTreatmentRecordReturn.setShopNo(ptTreatmentRecord.getShopNo());
			findPtTreatmentRecordReturn.setShopName(ptTreatmentRecord.getShopName());
			findPtTreatmentRecordReturn.setMerchantNo(ptTreatmentRecord.getMerchantNo());
			findPtTreatmentRecordReturn.setMerchantName(ptTreatmentRecord.getMerchantName());
			findPtTreatmentRecordReturn.setClinicTime(ptTreatmentRecord.getClinicTime());
			findPtTreatmentRecordReturn.setProjectCode(ptTreatmentRecord.getProjectCode());
			findPtTreatmentRecordReturn.setProjectName(ptTreatmentRecord.getProjectName());
			findPtTreatmentRecordReturn.setImg(ptTreatmentRecord.getImg());
			findPtTreatmentRecordReturn.setUpdateId(ptTreatmentRecord.getUpdateId());
			findPtTreatmentRecordReturn.setUpdateDate(ptTreatmentRecord.getUpdateDate());
			findPtTreatmentRecordReturn.setCreateId(ptTreatmentRecord.getCreateId());
			findPtTreatmentRecordReturn.setCreateDate(ptTreatmentRecord.getCreateDate());
			findPtTreatmentRecordReturn.setRemark(ptTreatmentRecord.getRemark());
			findPtTreatmentRecordReturn.setRemark1(ptTreatmentRecord.getRemark1());
			findPtTreatmentRecordReturn.setRemark2(ptTreatmentRecord.getRemark2());
			findPtTreatmentRecordReturn.setRemark3(ptTreatmentRecord.getRemark3());
			findPtTreatmentRecordReturn.setRemark4(ptTreatmentRecord.getRemark4());
			
			logger.debug("findPtTreatmentRecord(PtTreatmentRecordDto) - end - return value={}", findPtTreatmentRecordReturn); //$NON-NLS-1$
			return findPtTreatmentRecordReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找患者就诊记录信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PT_TREATMENT_RECORD_FIND_ERROR,"查找患者就诊记录信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PtTreatmentRecordDto> findPtTreatmentRecordPage(
			FindPtTreatmentRecordPage findPtTreatmentRecordPage)
			throws TsfaServiceException {
		logger.debug("findPtTreatmentRecordPage(FindPtTreatmentRecordPage findPtTreatmentRecordPage={}) - start", findPtTreatmentRecordPage); //$NON-NLS-1$

		AssertUtils.notNull(findPtTreatmentRecordPage);
		List<PtTreatmentRecordDto> returnList=null;
		int count = 0;
		try {
			returnList = ptTreatmentRecordDao.findPtTreatmentRecordPage(findPtTreatmentRecordPage);
			count = ptTreatmentRecordDao.findPtTreatmentRecordPageCount(findPtTreatmentRecordPage);
		}  catch (Exception e) {
			logger.error("患者就诊记录信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PT_TREATMENT_RECORD_FIND_PAGE_ERROR,"患者就诊记录信息不存在错误.！",e);
		}
		Page<PtTreatmentRecordDto> returnPage = new Page<PtTreatmentRecordDto>(returnList, count, findPtTreatmentRecordPage);

		logger.debug("findPtTreatmentRecordPage(FindPtTreatmentRecordPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


}
