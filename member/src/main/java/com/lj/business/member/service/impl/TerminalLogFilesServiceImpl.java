package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.ITerminalLogFilesDao;
import com.lj.business.member.domain.TerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.AddTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.AddTerminalLogFilesReturn;
import com.lj.business.member.dto.terminallogfiles.DelTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.DelTerminalLogFilesReturn;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPage;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesPageReturn;
import com.lj.business.member.dto.terminallogfiles.FindTerminalLogFilesReturn;
import com.lj.business.member.dto.terminallogfiles.UpdateTerminalLogFiles;
import com.lj.business.member.dto.terminallogfiles.UpdateTerminalLogFilesReturn;
import com.lj.business.member.service.ITerminalLogFilesService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
@Service
public class TerminalLogFilesServiceImpl implements ITerminalLogFilesService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(TerminalLogFilesServiceImpl.class);
	

	@Resource
	private ITerminalLogFilesDao terminalLogFilesDao;
	
	
	@Override
	public AddTerminalLogFilesReturn addTerminalLogFiles(
			AddTerminalLogFiles addTerminalLogFiles) throws TsfaServiceException {
		logger.debug("addTerminalLogFiles(AddTerminalLogFiles addTerminalLogFiles={}) - start", addTerminalLogFiles); 

		AssertUtils.notNull(addTerminalLogFiles);
		try {
			TerminalLogFiles terminalLogFiles = new TerminalLogFiles();
			//add数据录入
			terminalLogFiles.setCode(GUID.generateByUUID());
			terminalLogFiles.setImei(addTerminalLogFiles.getImei());
			terminalLogFiles.setTerminalCode(addTerminalLogFiles.getTerminalCode());
			terminalLogFiles.setLogBeginTime(addTerminalLogFiles.getLogBeginTime());
			terminalLogFiles.setLogFileName(addTerminalLogFiles.getLogFileName());
			terminalLogFiles.setLogAddr(addTerminalLogFiles.getLogAddr());
			terminalLogFiles.setUploadTime(new Date());
			terminalLogFiles.setMerchantNo(addTerminalLogFiles.getMerchantNo());
			terminalLogFiles.setMerchantName(addTerminalLogFiles.getMerchantName());
//			terminalLogFiles.setShopNo(addTerminalLogFiles.getShopNo());
//			terminalLogFiles.setShopName(addTerminalLogFiles.getShopName());
			terminalLogFiles.setCreateDate(new Date());
			
			
			FindTerminalLogFilesReturn findTerminalLogFilesReturn = findLogByFileName(addTerminalLogFiles.getLogFileName());
			if(findTerminalLogFilesReturn==null){
				// 如果没有同名日志文件,新增
				terminalLogFilesDao.insertSelective(terminalLogFiles);
			}else{
				// 如果已有同名的日志文件,更新终端日志文件的上传时间
				UpdateTerminalLogFiles updateTerminalLogFiles=new UpdateTerminalLogFiles();
				updateTerminalLogFiles.setCode(findTerminalLogFilesReturn.getCode());
				updateTerminalLogFiles.setUploadTime(new Date());
				updateTerminalLogFiles(updateTerminalLogFiles);
			}
			
			AddTerminalLogFilesReturn addTerminalLogFilesReturn = new AddTerminalLogFilesReturn();
			
			logger.debug("addTerminalLogFiles(AddTerminalLogFiles) - end - return value={}", addTerminalLogFilesReturn); 
			return addTerminalLogFilesReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增终端日志文件信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_LOG_FILES_ADD_ERROR,"新增终端日志文件信息错误！",e);
		}
	}
	

	@Override
	public DelTerminalLogFilesReturn delTerminalLogFiles(
			DelTerminalLogFiles delTerminalLogFiles) throws TsfaServiceException {
		logger.debug("delTerminalLogFiles(DelTerminalLogFiles delTerminalLogFiles={}) - start", delTerminalLogFiles); 

		AssertUtils.notNull(delTerminalLogFiles);
		AssertUtils.notNullAndEmpty(delTerminalLogFiles.getCode(),"CODE不能为空！");
		try {
			terminalLogFilesDao.deleteByPrimaryKey(delTerminalLogFiles.getCode());
			DelTerminalLogFilesReturn delTerminalLogFilesReturn  = new DelTerminalLogFilesReturn();

			logger.debug("delTerminalLogFiles(DelTerminalLogFiles) - end - return value={}", delTerminalLogFilesReturn); 
			return delTerminalLogFilesReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除终端日志文件信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_LOG_FILES_DEL_ERROR,"删除终端日志文件信息错误！",e);

		}
	}

	@Override
	public UpdateTerminalLogFilesReturn updateTerminalLogFiles(
			UpdateTerminalLogFiles updateTerminalLogFiles)
			throws TsfaServiceException {
		logger.debug("updateTerminalLogFiles(UpdateTerminalLogFiles updateTerminalLogFiles={}) - start", updateTerminalLogFiles); 

		AssertUtils.notNull(updateTerminalLogFiles);
		AssertUtils.notNullAndEmpty(updateTerminalLogFiles.getCode(),"CODE不能为空");
		try {
			TerminalLogFiles terminalLogFiles = new TerminalLogFiles();
			//update数据录入
			terminalLogFiles.setCode(updateTerminalLogFiles.getCode());
			terminalLogFiles.setImei(updateTerminalLogFiles.getImei());
			terminalLogFiles.setTerminalCode(updateTerminalLogFiles.getTerminalCode());
			terminalLogFiles.setLogBeginTime(updateTerminalLogFiles.getLogBeginTime());
			terminalLogFiles.setLogFileName(updateTerminalLogFiles.getLogFileName());
			terminalLogFiles.setLogAddr(updateTerminalLogFiles.getLogAddr());
			terminalLogFiles.setUploadTime(updateTerminalLogFiles.getUploadTime());
			terminalLogFiles.setMerchantNo(updateTerminalLogFiles.getMerchantNo());
			terminalLogFiles.setMerchantName(updateTerminalLogFiles.getMerchantName());
//			terminalLogFiles.setShopNo(updateTerminalLogFiles.getShopNo());
//			terminalLogFiles.setShopName(updateTerminalLogFiles.getShopName());
			terminalLogFiles.setCreateDate(updateTerminalLogFiles.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(terminalLogFilesDao.updateByPrimaryKeySelective(terminalLogFiles));
			UpdateTerminalLogFilesReturn updateTerminalLogFilesReturn = new UpdateTerminalLogFilesReturn();

			logger.debug("updateTerminalLogFiles(UpdateTerminalLogFiles) - end - return value={}", updateTerminalLogFilesReturn); 
			return updateTerminalLogFilesReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("终端日志文件信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_LOG_FILES_UPDATE_ERROR,"终端日志文件信息更新信息错误！",e);
		}
	}


	@Override
	public FindTerminalLogFilesReturn findTerminalLogFiles(
			FindTerminalLogFiles findTerminalLogFiles) throws TsfaServiceException {
		logger.debug("findTerminalLogFiles(FindTerminalLogFiles findTerminalLogFiles={}) - start", findTerminalLogFiles); 

		AssertUtils.notNull(findTerminalLogFiles);
		AssertUtils.notNullAndEmpty(findTerminalLogFiles.getCode(),"CODE不能为空");
		try {
			TerminalLogFiles terminalLogFiles = terminalLogFilesDao.selectByPrimaryKey(findTerminalLogFiles.getCode());
			if(terminalLogFiles == null){
				throw new TsfaServiceException(ErrorCode.TERMINAL_LOG_FILES_NOT_EXIST_ERROR,"终端日志文件信息不存在");
			}
			FindTerminalLogFilesReturn findTerminalLogFilesReturn = new FindTerminalLogFilesReturn();
			//find数据录入
			findTerminalLogFilesReturn.setCode(terminalLogFiles.getCode());
			findTerminalLogFilesReturn.setImei(terminalLogFiles.getImei());
			findTerminalLogFilesReturn.setTerminalCode(terminalLogFiles.getTerminalCode());
			findTerminalLogFilesReturn.setLogBeginTime(terminalLogFiles.getLogBeginTime());
			findTerminalLogFilesReturn.setLogFileName(terminalLogFiles.getLogFileName());
			findTerminalLogFilesReturn.setLogAddr(terminalLogFiles.getLogAddr());
			findTerminalLogFilesReturn.setUploadTime(terminalLogFiles.getUploadTime());
			findTerminalLogFilesReturn.setMerchantNo(terminalLogFiles.getMerchantNo());
			findTerminalLogFilesReturn.setMerchantName(terminalLogFiles.getMerchantName());
//			findTerminalLogFilesReturn.setShopNo(terminalLogFiles.getShopNo());
//			findTerminalLogFilesReturn.setShopName(terminalLogFiles.getShopName());
			findTerminalLogFilesReturn.setCreateDate(terminalLogFiles.getCreateDate());
			
			logger.debug("findTerminalLogFiles(FindTerminalLogFiles) - end - return value={}", findTerminalLogFilesReturn); 
			return findTerminalLogFilesReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找终端日志文件信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_LOG_FILES_FIND_ERROR,"查找终端日志文件信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindTerminalLogFilesPageReturn> findTerminalLogFilesPage(
			FindTerminalLogFilesPage findTerminalLogFilesPage)
			throws TsfaServiceException {
		logger.debug("findTerminalLogFilesPage(FindTerminalLogFilesPage findTerminalLogFilesPage={}) - start", findTerminalLogFilesPage); 

		AssertUtils.notNull(findTerminalLogFilesPage);
		List<FindTerminalLogFilesPageReturn> returnList=Lists.newArrayList();
		int count = 0;
		try {
			returnList = terminalLogFilesDao.findTerminalLogFilesPage(findTerminalLogFilesPage);
			count = terminalLogFilesDao.findTerminalLogFilesPageCount(findTerminalLogFilesPage);
		}  catch (Exception e) {
			logger.error("终端日志文件信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_LOG_FILES_FIND_PAGE_ERROR,"终端日志文件信息不存在错误.！",e);
		}
		Page<FindTerminalLogFilesPageReturn> returnPage = new Page<FindTerminalLogFilesPageReturn>(returnList, count, findTerminalLogFilesPage);

		logger.debug("findTerminalLogFilesPage(FindTerminalLogFilesPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	@Override
	public FindTerminalLogFilesReturn findLogByFileName(String logFileName) throws TsfaServiceException {
		return terminalLogFilesDao.selectByLogFileName(logFileName);
	}

	@Override
	public String getLastFileDate(String imei)  {
		logger.debug("getLastFileDate(imei ={}) - start", imei); 
		AssertUtils.notNullAndEmpty(imei, "IMEI不能为空");
		String lastFileDate="";
		try {
			Date date=terminalLogFilesDao.getLastFileDate(imei);
			if(date!=null){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				lastFileDate=sdf.format(date);
			}
			logger.debug("getLastFileDate(lastFileDate) - end - return value={}", lastFileDate); 
			return lastFileDate;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("获取上次当前终端日志的最后一个日志文件的时间错误！", e);
			throw new TsfaServiceException(ErrorCode.TERMINAL_LOG_FILES_FIND_PAGE_ERROR, "获取上次当前终端日志的最后一个日志文件的时间错误！", e);
		}
	} 

}
