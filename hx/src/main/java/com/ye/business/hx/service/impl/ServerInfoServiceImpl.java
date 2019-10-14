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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;

import com.ye.business.hx.dto.ServerInfoDto;
import com.ye.business.hx.dto.FindServerInfoPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IServerInfoDao;
import com.ye.business.hx.domain.ServerInfo;
import com.ye.business.hx.service.IServerDetailService;
import com.ye.business.hx.service.IServerInfoService;
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
public class ServerInfoServiceImpl implements IServerInfoService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ServerInfoServiceImpl.class);
	

	@Resource
	private IServerInfoDao serverInfoDao;
	
	/** 服务明细 */
	@Autowired
	private IServerDetailService serverDetailService;
	
	@Override
	public void addServerInfo(
			ServerInfoDto serverInfoDto) throws TsfaServiceException {
		logger.debug("addServerInfo(AddServerInfo addServerInfo={}) - start", serverInfoDto); 

		AssertUtils.notNull(serverInfoDto);
		try {
			ServerInfo serverInfo = new ServerInfo();
			//add数据录入
			serverInfo.setCode(GUID.getPreUUID());
			serverInfo.setMerchantNo(serverInfoDto.getMerchantNo());
			serverInfo.setMerchantName(serverInfoDto.getMerchantName());
			serverInfo.setServerName(serverInfoDto.getServerName());
			serverInfo.setPrice(serverInfoDto.getPrice());
			serverInfo.setOriginalPrice(serverInfoDto.getOriginalPrice());
			serverInfo.setStatus(serverInfoDto.getStatus());
			serverInfo.setUpdateId(serverInfoDto.getUpdateId());
			serverInfo.setUpdateDate(new Date());
			serverInfo.setCreateId(serverInfoDto.getCreateId());
			serverInfo.setCreateDate(new Date());
			serverInfo.setCtx(serverInfoDto.getCtx());
			serverInfoDao.insertSelective(serverInfo);
			logger.debug("addServerInfo(ServerInfoDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增系统服务信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVER_INFO_ADD_ERROR,"新增系统服务信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询系统服务信息
	 *
	 * @param findServerInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ServerInfoDto>  findServerInfos(FindServerInfoPage findServerInfoPage)throws TsfaServiceException{
		AssertUtils.notNull(findServerInfoPage);
		List<ServerInfoDto> returnList=null;
		try {
			returnList = serverInfoDao.findServerInfos(findServerInfoPage);
		} catch (Exception e) {
			logger.error("查找系统服务信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVER_INFO_NOT_EXIST_ERROR,"系统服务信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateServerInfo(
			ServerInfoDto serverInfoDto)
			throws TsfaServiceException {
		logger.debug("updateServerInfo(ServerInfoDto serverInfoDto={}) - start", serverInfoDto); //$NON-NLS-1$

		AssertUtils.notNull(serverInfoDto);
		AssertUtils.notNullAndEmpty(serverInfoDto.getCode(),"Code不能为空");
		try {
			ServerInfo serverInfo = new ServerInfo();
			//update数据录入
			serverInfo.setCode(serverInfoDto.getCode());
			serverInfo.setMerchantNo(serverInfoDto.getMerchantNo());
			serverInfo.setMerchantName(serverInfoDto.getMerchantName());
			serverInfo.setServerName(serverInfoDto.getServerName());
			serverInfo.setPrice(serverInfoDto.getPrice());
			serverInfo.setOriginalPrice(serverInfoDto.getOriginalPrice());
			serverInfo.setStatus(serverInfoDto.getStatus());
			serverInfo.setUpdateId(serverInfoDto.getUpdateId());
			serverInfo.setUpdateDate(new Date());
//			serverInfo.setCreateId(serverInfoDto.getCreateId());
//			serverInfo.setCreateDate(serverInfoDto.getCreateDate());
			serverInfo.setCtx(serverInfoDto.getCtx());
			AssertUtils.notUpdateMoreThanOne(serverInfoDao.updateByPrimaryKeySelective(serverInfo));
			logger.debug("updateServerInfo(ServerInfoDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("系统服务信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVER_INFO_UPDATE_ERROR,"系统服务信息更新信息错误！",e);
		}
	}

	

	@Override
	public ServerInfoDto findServerInfo(
			ServerInfoDto serverInfoDto) throws TsfaServiceException {
		logger.debug("findServerInfo(FindServerInfo findServerInfo={}) - start", serverInfoDto); //$NON-NLS-1$

		AssertUtils.notNull(serverInfoDto);
		AssertUtils.notAllNull(serverInfoDto.getCode(),"Code不能为空");
		try {
			ServerInfo serverInfo = serverInfoDao.selectByPrimaryKey(serverInfoDto.getCode());
			if(serverInfo == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SERVER_INFO_NOT_EXIST_ERROR,"系统服务信息不存在");
			}
			ServerInfoDto findServerInfoReturn = new ServerInfoDto();
			//find数据录入
			findServerInfoReturn.setCode(serverInfo.getCode());
			findServerInfoReturn.setMerchantNo(serverInfo.getMerchantNo());
			findServerInfoReturn.setMerchantName(serverInfo.getMerchantName());
			findServerInfoReturn.setServerName(serverInfo.getServerName());
			findServerInfoReturn.setPrice(serverInfo.getPrice());
			findServerInfoReturn.setOriginalPrice(serverInfo.getOriginalPrice());
			findServerInfoReturn.setStatus(serverInfo.getStatus());
			findServerInfoReturn.setUpdateId(serverInfo.getUpdateId());
			findServerInfoReturn.setUpdateDate(serverInfo.getUpdateDate());
			findServerInfoReturn.setCreateId(serverInfo.getCreateId());
			findServerInfoReturn.setCreateDate(serverInfo.getCreateDate());
			findServerInfoReturn.setCtx(serverInfo.getCtx());
			
			logger.debug("findServerInfo(ServerInfoDto) - end - return value={}", findServerInfoReturn); //$NON-NLS-1$
			return findServerInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找系统服务信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVER_INFO_FIND_ERROR,"查找系统服务信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ServerInfoDto> findServerInfoPage(
			FindServerInfoPage findServerInfoPage)
			throws TsfaServiceException {
		logger.debug("findServerInfoPage(FindServerInfoPage findServerInfoPage={}) - start", findServerInfoPage); //$NON-NLS-1$

		AssertUtils.notNull(findServerInfoPage);
		List<ServerInfoDto> returnList=null;
		int count = 0;
		try {
			returnList = serverInfoDao.findServerInfoPage(findServerInfoPage);
			count = serverInfoDao.findServerInfoPageCount(findServerInfoPage);
		}  catch (Exception e) {
			logger.error("系统服务信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SERVER_INFO_FIND_PAGE_ERROR,"系统服务信息不存在错误.！",e);
		}
		Page<ServerInfoDto> returnPage = new Page<ServerInfoDto>(returnList, count, findServerInfoPage);

		logger.debug("findServerInfoPage(FindServerInfoPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


	@Override
	public String addServerInfoRecord(ServerInfoDto serverInfoDto) throws TsfaServiceException {
		logger.debug("addServerInfoRecord(AddServerInfo addServerInfo={}) - start", serverInfoDto); 

		AssertUtils.notNull(serverInfoDto);
		try {
			ServerInfo serverInfo = new ServerInfo();
			//add数据录入
			serverInfo.setCode(GUID.getPreUUID());
			serverInfo.setMerchantNo(serverInfoDto.getMerchantNo());
			serverInfo.setMerchantName(serverInfoDto.getMerchantName());
			serverInfo.setServerName(serverInfoDto.getServerName());
			serverInfo.setPrice(serverInfoDto.getPrice());
			serverInfo.setOriginalPrice(serverInfoDto.getOriginalPrice());
			serverInfo.setStatus(serverInfoDto.getStatus());
			serverInfo.setUpdateId(serverInfoDto.getUpdateId());
			serverInfo.setUpdateDate(serverInfoDto.getUpdateDate());
			serverInfo.setCreateId(serverInfoDto.getCreateId());
			serverInfo.setCreateDate(serverInfoDto.getCreateDate());
			serverInfo.setCtx(serverInfoDto.getCtx());
			serverInfoDao.insertSelective(serverInfo);
			
			serverInfoDto.getServerDetails().forEach(temp -> {
				temp.setServerCode(serverInfo.getCode());
				temp.setCreateDate(serverInfoDto.getCreateDate());
				serverDetailService.addServerDetail(temp);
			});
			
			logger.debug("addServerInfoRecord(ServerInfoDto) - end - return"); 
			
			return serverInfo.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增系统服务信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVER_INFO_ADD_ERROR,"新增系统服务信息错误！",e);
		}
	}
	
	@Override
	public void updateServerInfoRecord(ServerInfoDto serverInfoDto) throws TsfaServiceException {
		logger.debug("updateServerInfoRecord(ServerInfoDto serverInfoDto={}) - start", serverInfoDto); //$NON-NLS-1$

		AssertUtils.notNull(serverInfoDto);
		AssertUtils.notNullAndEmpty(serverInfoDto.getCode(),"Code不能为空");
		try {
			ServerInfo serverInfo = new ServerInfo();
			//update数据录入
			serverInfo.setCode(serverInfoDto.getCode());
			serverInfo.setMerchantNo(serverInfoDto.getMerchantNo());
			serverInfo.setMerchantName(serverInfoDto.getMerchantName());
			serverInfo.setServerName(serverInfoDto.getServerName());
			serverInfo.setPrice(serverInfoDto.getPrice());
			serverInfo.setOriginalPrice(serverInfoDto.getOriginalPrice());
			serverInfo.setStatus(serverInfoDto.getStatus());
			serverInfo.setUpdateId(serverInfoDto.getUpdateId());
			serverInfo.setUpdateDate(new Date());
			serverInfo.setCtx(serverInfoDto.getCtx());
			AssertUtils.notUpdateMoreThanOne(serverInfoDao.updateByPrimaryKeySelective(serverInfo));

			// 先删除旧数据
			serverDetailService.removeByServerCode(serverInfo.getCode());
			
			serverInfoDto.getServerDetails().forEach(temp -> {
				temp.setServerCode(serverInfo.getCode());
				temp.setCreateDate(serverInfoDto.getCreateDate());
				serverDetailService.addServerDetail(temp);
			});
			
			logger.debug("updateServerInfoRecord(ServerInfoDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("系统服务信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVER_INFO_UPDATE_ERROR,"系统服务信息更新信息错误！",e);
		}
	}
}
