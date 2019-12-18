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
import com.ye.business.hx.dao.IOrthodonticsProcessDao;
import com.ye.business.hx.domain.OrthodonticsProcess;
import com.ye.business.hx.dto.FindOrthodonticsProcessPage;
import com.ye.business.hx.dto.OrthodonticsProcessDto;
import com.ye.business.hx.service.IOrthodonticsProcessService;
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
public class OrthodonticsProcessServiceImpl implements IOrthodonticsProcessService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(OrthodonticsProcessServiceImpl.class);
	

	@Resource
	private IOrthodonticsProcessDao orthodonticsProcessDao;
	
	
	@Override
	public void addOrthodonticsProcess(
			OrthodonticsProcessDto orthodonticsProcessDto) throws TsfaServiceException {
		logger.debug("addOrthodonticsProcess(AddOrthodonticsProcess addOrthodonticsProcess={}) - start", orthodonticsProcessDto); 

		AssertUtils.notNull(orthodonticsProcessDto);
		try {
			OrthodonticsProcess orthodonticsProcess = new OrthodonticsProcess();
			//add数据录入
			orthodonticsProcess.setCode(orthodonticsProcessDto.getCode());
			orthodonticsProcess.setType(orthodonticsProcessDto.getType());
			orthodonticsProcess.setToothPosition(orthodonticsProcessDto.getToothPosition());
			orthodonticsProcess.setExplain(orthodonticsProcessDto.getExplain());
			orthodonticsProcess.setPatientNo(orthodonticsProcessDto.getPatientNo());
			orthodonticsProcess.setAttendingDoctor(orthodonticsProcessDto.getAttendingDoctor());
			orthodonticsProcess.setNurse(orthodonticsProcessDto.getNurse());
			orthodonticsProcess.setCreateDate(orthodonticsProcessDto.getCreateDate());
			orthodonticsProcess.setRemark(orthodonticsProcessDto.getRemark());
			orthodonticsProcess.setRecordCode(orthodonticsProcessDto.getRecordCode());
			orthodonticsProcessDao.insertSelective(orthodonticsProcess);
			logger.debug("addOrthodonticsProcess(OrthodonticsProcessDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增正畸病历/正畸过程信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_ADD_ERROR,"新增正畸病历/正畸过程信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸病历/正畸过程信息
	 *
	 * @param findOrthodonticsProcessPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<OrthodonticsProcessDto>  findOrthodonticsProcesss(FindOrthodonticsProcessPage findOrthodonticsProcessPage)throws TsfaServiceException{
		AssertUtils.notNull(findOrthodonticsProcessPage);
		List<OrthodonticsProcessDto> returnList=null;
		try {
			returnList = orthodonticsProcessDao.findOrthodonticsProcesss(findOrthodonticsProcessPage);
		} catch (Exception e) {
			logger.error("查找正畸病历/正畸过程信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_NOT_EXIST_ERROR,"正畸病历/正畸过程信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateOrthodonticsProcess(
			OrthodonticsProcessDto orthodonticsProcessDto)
			throws TsfaServiceException {
		logger.debug("updateOrthodonticsProcess(OrthodonticsProcessDto orthodonticsProcessDto={}) - start", orthodonticsProcessDto); 

		AssertUtils.notNull(orthodonticsProcessDto);
		AssertUtils.notNullAndEmpty(orthodonticsProcessDto.getCode(),"Code不能为空");
		try {
			OrthodonticsProcess orthodonticsProcess = new OrthodonticsProcess();
			//update数据录入
			orthodonticsProcess.setCode(orthodonticsProcessDto.getCode());
			orthodonticsProcess.setType(orthodonticsProcessDto.getType());
			orthodonticsProcess.setToothPosition(orthodonticsProcessDto.getToothPosition());
			orthodonticsProcess.setExplain(orthodonticsProcessDto.getExplain());
			orthodonticsProcess.setPatientNo(orthodonticsProcessDto.getPatientNo());
			orthodonticsProcess.setAttendingDoctor(orthodonticsProcessDto.getAttendingDoctor());
			orthodonticsProcess.setNurse(orthodonticsProcessDto.getNurse());
			orthodonticsProcess.setCreateDate(orthodonticsProcessDto.getCreateDate());
			orthodonticsProcess.setRemark(orthodonticsProcessDto.getRemark());
			orthodonticsProcess.setUpdateDate(orthodonticsProcessDto.getUpdateDate());
			AssertUtils.notUpdateMoreThanOne(orthodonticsProcessDao.updateByPrimaryKeySelective(orthodonticsProcess));
			logger.debug("updateOrthodonticsProcess(OrthodonticsProcessDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("正畸病历/正畸过程信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_UPDATE_ERROR,"正畸病历/正畸过程信息更新信息错误！",e);
		}
	}

	

	@Override
	public List<OrthodonticsProcessDto> findOrthodonticsProcess(
			OrthodonticsProcessDto orthodonticsProcessDto) throws TsfaServiceException {
		logger.debug("findOrthodonticsProcess(FindOrthodonticsProcess findOrthodonticsProcess={}) - start", orthodonticsProcessDto); 

		AssertUtils.notNull(orthodonticsProcessDto);
		try {
			List<OrthodonticsProcessDto> list = orthodonticsProcessDao.selectByPrimaryKey(orthodonticsProcessDto.getRecordCode());
			
			
			logger.debug("findOrthodonticsProcess(OrthodonticsProcessDto) - end - return value={}", list); 
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找正畸病历/正畸过程信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_FIND_ERROR,"查找正畸病历/正畸过程信息信息错误！",e);
		}


	}

	
	@Override
	public Page<OrthodonticsProcessDto> findOrthodonticsProcessPage(
			FindOrthodonticsProcessPage findOrthodonticsProcessPage)
			throws TsfaServiceException {
		logger.debug("findOrthodonticsProcessPage(FindOrthodonticsProcessPage findOrthodonticsProcessPage={}) - start", findOrthodonticsProcessPage); 

		AssertUtils.notNull(findOrthodonticsProcessPage);
		List<OrthodonticsProcessDto> returnList=null;
		int count = 0;
		try {
			returnList = orthodonticsProcessDao.findOrthodonticsProcessPage(findOrthodonticsProcessPage);
			count = orthodonticsProcessDao.findOrthodonticsProcessPageCount(findOrthodonticsProcessPage);
		}  catch (Exception e) {
			logger.error("正畸病历/正畸过程信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.ORTHODONTICS_PROCESS_FIND_PAGE_ERROR,"正畸病历/正畸过程信息不存在错误.！",e);
		}
		Page<OrthodonticsProcessDto> returnPage = new Page<OrthodonticsProcessDto>(returnList, count, findOrthodonticsProcessPage);

		logger.debug("findOrthodonticsProcessPage(FindOrthodonticsProcessPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
