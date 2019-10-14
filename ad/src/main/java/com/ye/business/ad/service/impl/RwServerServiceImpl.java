package com.ye.business.ad.service.impl;

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
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.constant.ErrorCodeRwServer;
import com.ye.business.ad.dao.IRwServerDao;
import com.ye.business.ad.domain.RwServer;
import com.ye.business.ad.dto.FindRwServerPage;
import com.ye.business.ad.dto.RwServerDto;
import com.ye.business.ad.service.IRwServerService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author sjiying
 * 
 * 
 * CreateDate: 2019.04.18
 */
@Service
public class RwServerServiceImpl implements IRwServerService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(RwServerServiceImpl.class);
	

	@Resource
	private IRwServerDao rwServerDao;
	
	
	@Override
	public String addRwServer(
			RwServerDto rwServerDto) throws TsfaServiceException {
		logger.debug("addRwServer(AddRwServer addRwServer={}) - start", rwServerDto); 

		AssertUtils.notNull(rwServerDto);
		try {
			RwServer rwServer = new RwServer();
			//add数据录入
			rwServer.setCode(rwServerDto.getCode());
			rwServer.setShopNo(rwServerDto.getShopNo());
			rwServer.setShopName(rwServerDto.getShopName());
			rwServer.setMerchantNo(rwServerDto.getMerchantNo());
			rwServer.setMerchantName(rwServerDto.getMerchantName());
			rwServer.setMemberNoGuid(rwServerDto.getMemberNoGuid());
			rwServer.setMemberNameGuid(rwServerDto.getMemberNameGuid());
			rwServer.setMobile(rwServerDto.getMobile());
			rwServer.setServerCode(rwServerDto.getServerCode());
			rwServer.setServerName(rwServerDto.getServerName());
			rwServer.setPrice(rwServerDto.getPrice());
			rwServer.setOriginalPrice(rwServerDto.getOriginalPrice());
			rwServer.setOrderNo(rwServerDto.getOrderNo());
			rwServer.setUpdateId(rwServerDto.getUpdateId());
			rwServer.setUpdateDate(rwServerDto.getUpdateDate());
			rwServer.setCreateId(rwServerDto.getCreateId());
			rwServer.setCreateDate(rwServerDto.getCreateDate());
			
			rwServer.setCode(GUID.getPreUUID());
			rwServerDao.insertSelective(rwServer);
			logger.debug("addRwServer(RwServerDto) - end - return"); 
			return rwServer.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增热文用户服务记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwServer.RW_SERVER_ADD_ERROR, "新增热文用户服务记录信息错误！", e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询热文用户服务记录信息
	 *
	 * @param findRwServerPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<RwServerDto>  findRwServers(FindRwServerPage findRwServerPage)throws TsfaServiceException{
		AssertUtils.notNull(findRwServerPage);
		List<RwServerDto> returnList=null;
		try {
			returnList = rwServerDao.findRwServers(findRwServerPage);
		} catch (Exception e) {
			logger.error("查找热文用户服务记录信息信息错误！",  e);
			throw new TsfaServiceException(ErrorCodeRwServer.RW_SERVER_NOT_EXIST_ERROR, "热文用户服务记录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateRwServer(
			RwServerDto rwServerDto)
			throws TsfaServiceException {
		logger.debug("updateRwServer(RwServerDto rwServerDto={}) - start", rwServerDto); 

		AssertUtils.notNull(rwServerDto);
		AssertUtils.notNullAndEmpty(rwServerDto.getCode(),"Code不能为空");
		try {
			RwServer rwServer = new RwServer();
			//update数据录入
			rwServer.setCode(rwServerDto.getCode());
			rwServer.setShopNo(rwServerDto.getShopNo());
			rwServer.setShopName(rwServerDto.getShopName());
			rwServer.setMerchantNo(rwServerDto.getMerchantNo());
			rwServer.setMerchantName(rwServerDto.getMerchantName());
			rwServer.setMemberNoGuid(rwServerDto.getMemberNoGuid());
			rwServer.setMemberNameGuid(rwServerDto.getMemberNameGuid());
			rwServer.setMobile(rwServerDto.getMobile());
			rwServer.setServerCode(rwServerDto.getServerCode());
			rwServer.setServerName(rwServerDto.getServerName());
			rwServer.setPrice(rwServerDto.getPrice());
			rwServer.setOriginalPrice(rwServerDto.getOriginalPrice());
			rwServer.setOrderNo(rwServerDto.getOrderNo());
			rwServer.setUpdateId(rwServerDto.getUpdateId());
			rwServer.setUpdateDate(rwServerDto.getUpdateDate());
			rwServer.setCreateId(rwServerDto.getCreateId());
			rwServer.setCreateDate(rwServerDto.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(rwServerDao.updateByPrimaryKeySelective(rwServer));
			logger.debug("updateRwServer(RwServerDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("热文用户服务记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwServer.RW_SERVER_UPDATE_ERROR, "热文用户服务记录信息更新信息错误！", e);
		}
	}

	

	@Override
	public RwServerDto findRwServer(
			RwServerDto rwServerDto) throws TsfaServiceException {
		logger.debug("findRwServer(FindRwServer findRwServer={}) - start", rwServerDto); 

		AssertUtils.notNull(rwServerDto);
		AssertUtils.notAllNull(rwServerDto.getCode(),"Code不能为空");
		try {
			RwServer rwServer = rwServerDao.selectByPrimaryKey(rwServerDto.getCode());
			if(rwServer == null){
				return null;
				//throw new TsfaServiceException(ErrorCodeRwServer.RW_SERVER_NOT_EXIST_ERROR,"热文用户服务记录信息不存在");
			}
			RwServerDto findRwServerReturn = new RwServerDto();
			//find数据录入
			findRwServerReturn.setCode(rwServer.getCode());
			findRwServerReturn.setShopNo(rwServer.getShopNo());
			findRwServerReturn.setShopName(rwServer.getShopName());
			findRwServerReturn.setMerchantNo(rwServer.getMerchantNo());
			findRwServerReturn.setMerchantName(rwServer.getMerchantName());
			findRwServerReturn.setMemberNoGuid(rwServer.getMemberNoGuid());
			findRwServerReturn.setMemberNameGuid(rwServer.getMemberNameGuid());
			findRwServerReturn.setMobile(rwServer.getMobile());
			findRwServerReturn.setServerCode(rwServer.getServerCode());
			findRwServerReturn.setServerName(rwServer.getServerName());
			findRwServerReturn.setPrice(rwServer.getPrice());
			findRwServerReturn.setOriginalPrice(rwServer.getOriginalPrice());
			findRwServerReturn.setOrderNo(rwServer.getOrderNo());
			findRwServerReturn.setUpdateId(rwServer.getUpdateId());
			findRwServerReturn.setUpdateDate(rwServer.getUpdateDate());
			findRwServerReturn.setCreateId(rwServer.getCreateId());
			findRwServerReturn.setCreateDate(rwServer.getCreateDate());
			
			logger.debug("findRwServer(RwServerDto) - end - return value={}", findRwServerReturn); 
			return findRwServerReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找热文用户服务记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwServer.RW_SERVER_FIND_ERROR, "查找热文用户服务记录信息信息错误！", e);
		}


	}

	
	@Override
	public Page<RwServerDto> findRwServerPage(
			FindRwServerPage findRwServerPage)
			throws TsfaServiceException {
		logger.debug("findRwServerPage(FindRwServerPage findRwServerPage={}) - start", findRwServerPage); 

		AssertUtils.notNull(findRwServerPage);
		List<RwServerDto> returnList=null;
		int count = 0;
		try {
			returnList = rwServerDao.findRwServerPage(findRwServerPage);
			count = rwServerDao.findRwServerPageCount(findRwServerPage);
		}  catch (Exception e) {
			logger.error("热文用户服务记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeRwServer.RW_SERVER_FIND_PAGE_ERROR, "热文用户服务记录信息不存在错误.！", e);
		}
		Page<RwServerDto> returnPage = new Page<RwServerDto>(returnList, count, findRwServerPage);

		logger.debug("findRwServerPage(FindRwServerPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(rwServerDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("热文用户服务记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwServer.RW_SERVER_UPDATE_ERROR, "热文用户服务记录信息刪除信息错误！", e);
		}
	}


}
