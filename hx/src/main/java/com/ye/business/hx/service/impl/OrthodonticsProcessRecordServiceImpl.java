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

import com.ye.business.hx.dto.OrthodonticsProcessRecordDto;
import com.ye.business.hx.dto.FindOrthodonticsProcessRecordPage;
import com.ye.business.hx.dto.OrthodonticsProcessDto;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IOrthodonticsProcessRecordDao;
import com.ye.business.hx.domain.OrthodonticsProcessRecord;
import com.ye.business.hx.service.IOrthodonticsProcessRecordService;
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
public class OrthodonticsProcessRecordServiceImpl implements IOrthodonticsProcessRecordService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(OrthodonticsProcessRecordServiceImpl.class);
	

	@Resource
	private IOrthodonticsProcessRecordDao orthodonticsProcessRecordDao;
	
	
	@Override
	public void addOrthodonticsProcessRecord(
			OrthodonticsProcessRecordDto orthodonticsProcessRecordDto) throws TsfaServiceException {
		logger.debug("addOrthodonticsProcessRecord(AddOrthodonticsProcessRecord addOrthodonticsProcessRecord={}) - start", orthodonticsProcessRecordDto); 

		AssertUtils.notNull(orthodonticsProcessRecordDto);
		try {
			OrthodonticsProcessRecord orthodonticsProcessRecord = new OrthodonticsProcessRecord();
			//add数据录入
			orthodonticsProcessRecord.setCode(orthodonticsProcessRecordDto.getCode());
			orthodonticsProcessRecord.setCreateDate(orthodonticsProcessRecordDto.getCreateDate());
			orthodonticsProcessRecord.setPatientNo(orthodonticsProcessRecordDto.getPatientNo());
			orthodonticsProcessRecord.setAttendingDoctor(orthodonticsProcessRecordDto.getAttendingDoctor());
			orthodonticsProcessRecord.setNurse(orthodonticsProcessRecordDto.getNurse());
			orthodonticsProcessRecord.setClinic(orthodonticsProcessRecordDto.getClinic());
			orthodonticsProcessRecord.setCreateBy(orthodonticsProcessRecordDto.getCreateBy());
			orthodonticsProcessRecord.setUpdateDate(orthodonticsProcessRecordDto.getUpdateDate());
			orthodonticsProcessRecord.setRemark(orthodonticsProcessRecordDto.getRemark());
			orthodonticsProcessRecordDao.insertSelective(orthodonticsProcessRecord);
			logger.debug("addOrthodonticsProcessRecord(OrthodonticsProcessRecordDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增正畸过程信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_RECORD_ADD_ERROR,"新增正畸过程信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸过程信息
	 *
	 * @param findOrthodonticsProcessRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<OrthodonticsProcessRecordDto>  findOrthodonticsProcessRecords(FindOrthodonticsProcessRecordPage findOrthodonticsProcessRecordPage)throws TsfaServiceException{
		AssertUtils.notNull(findOrthodonticsProcessRecordPage);
		List<OrthodonticsProcessRecordDto> returnList=null;
		try {
			returnList = orthodonticsProcessRecordDao.findOrthodonticsProcessRecords(findOrthodonticsProcessRecordPage);
		} catch (Exception e) {
			logger.error("查找正畸过程信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_RECORD_NOT_EXIST_ERROR,"正畸过程信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateOrthodonticsProcessRecord(
			OrthodonticsProcessRecordDto orthodonticsProcessRecordDto)
			throws TsfaServiceException {
		logger.debug("updateOrthodonticsProcessRecord(OrthodonticsProcessRecordDto orthodonticsProcessRecordDto={}) - start", orthodonticsProcessRecordDto); 

		AssertUtils.notNull(orthodonticsProcessRecordDto);
		AssertUtils.notNullAndEmpty(orthodonticsProcessRecordDto.getCode(),"Code不能为空");
		try {
			OrthodonticsProcessRecord orthodonticsProcessRecord = new OrthodonticsProcessRecord();
			//update数据录入
			orthodonticsProcessRecord.setCode(orthodonticsProcessRecordDto.getCode());
			orthodonticsProcessRecord.setCreateDate(orthodonticsProcessRecordDto.getCreateDate());
			orthodonticsProcessRecord.setPatientNo(orthodonticsProcessRecordDto.getPatientNo());
			orthodonticsProcessRecord.setAttendingDoctor(orthodonticsProcessRecordDto.getAttendingDoctor());
			orthodonticsProcessRecord.setNurse(orthodonticsProcessRecordDto.getNurse());
			orthodonticsProcessRecord.setClinic(orthodonticsProcessRecordDto.getClinic());
			orthodonticsProcessRecord.setCreateBy(orthodonticsProcessRecordDto.getCreateBy());
			orthodonticsProcessRecord.setUpdateDate(orthodonticsProcessRecordDto.getUpdateDate());
			orthodonticsProcessRecord.setRemark(orthodonticsProcessRecordDto.getRemark());
			AssertUtils.notUpdateMoreThanOne(orthodonticsProcessRecordDao.updateByPrimaryKeySelective(orthodonticsProcessRecord));
			logger.debug("updateOrthodonticsProcessRecord(OrthodonticsProcessRecordDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("正畸过程信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_RECORD_UPDATE_ERROR,"正畸过程信息更新信息错误！",e);
		}
	}

	

	@Override
	public OrthodonticsProcessRecordDto findOrthodonticsProcessRecord(
			OrthodonticsProcessRecordDto orthodonticsProcessRecordDto) throws TsfaServiceException {
		logger.debug("findOrthodonticsProcessRecord(FindOrthodonticsProcessRecord findOrthodonticsProcessRecord={}) - start", orthodonticsProcessRecordDto); 

		AssertUtils.notNull(orthodonticsProcessRecordDto);
		AssertUtils.notAllNull(orthodonticsProcessRecordDto.getCode(),"Code不能为空");
		try {
			OrthodonticsProcessRecord orthodonticsProcessRecord = orthodonticsProcessRecordDao.selectByPrimaryKey(orthodonticsProcessRecordDto.getCode());
			if(orthodonticsProcessRecord == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_RECORD_NOT_EXIST_ERROR,"正畸过程信息不存在");
			}
			OrthodonticsProcessRecordDto findOrthodonticsProcessRecordReturn = new OrthodonticsProcessRecordDto();
			//find数据录入
			findOrthodonticsProcessRecordReturn.setCode(orthodonticsProcessRecord.getCode());
			findOrthodonticsProcessRecordReturn.setCreateDate(orthodonticsProcessRecord.getCreateDate());
			findOrthodonticsProcessRecordReturn.setPatientNo(orthodonticsProcessRecord.getPatientNo());
			findOrthodonticsProcessRecordReturn.setAttendingDoctor(orthodonticsProcessRecord.getAttendingDoctor());
			findOrthodonticsProcessRecordReturn.setNurse(orthodonticsProcessRecord.getNurse());
			findOrthodonticsProcessRecordReturn.setClinic(orthodonticsProcessRecord.getClinic());
			findOrthodonticsProcessRecordReturn.setCreateBy(orthodonticsProcessRecord.getCreateBy());
			findOrthodonticsProcessRecordReturn.setUpdateDate(orthodonticsProcessRecord.getUpdateDate());
			findOrthodonticsProcessRecordReturn.setRemark(orthodonticsProcessRecord.getRemark());
			
			logger.debug("findOrthodonticsProcessRecord(OrthodonticsProcessRecordDto) - end - return value={}", findOrthodonticsProcessRecordReturn); 
			return findOrthodonticsProcessRecordReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找正畸过程信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_RECORD_FIND_ERROR,"查找正畸过程信息信息错误！",e);
		}


	}

	
	@Override
	public Page<OrthodonticsProcessRecordDto> findOrthodonticsProcessRecordPage(
			FindOrthodonticsProcessRecordPage findOrthodonticsProcessRecordPage)
			throws TsfaServiceException {
		logger.debug("findOrthodonticsProcessRecordPage(FindOrthodonticsProcessRecordPage findOrthodonticsProcessRecordPage={}) - start", findOrthodonticsProcessRecordPage); 

		AssertUtils.notNull(findOrthodonticsProcessRecordPage);
		List<OrthodonticsProcessRecordDto> returnList=null;
		int count = 0;
		try {
			returnList = orthodonticsProcessRecordDao.findOrthodonticsProcessRecordPage(findOrthodonticsProcessRecordPage);
			count = orthodonticsProcessRecordDao.findOrthodonticsProcessRecordPageCount(findOrthodonticsProcessRecordPage);
		}  catch (Exception e) {
			logger.error("正畸过程信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_RECORD_FIND_PAGE_ERROR,"正畸过程信息不存在错误.！",e);
		}
		Page<OrthodonticsProcessRecordDto> returnPage = new Page<OrthodonticsProcessRecordDto>(returnList, count, findOrthodonticsProcessRecordPage);

		logger.debug("findOrthodonticsProcessRecordPage(FindOrthodonticsProcessRecordPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	
	@Override
	public void del(OrthodonticsProcessDto orthodonticsProcessDto) {
		orthodonticsProcessRecordDao.deleteByPrimaryKey(orthodonticsProcessDto.getCode());
		
	} 


}
