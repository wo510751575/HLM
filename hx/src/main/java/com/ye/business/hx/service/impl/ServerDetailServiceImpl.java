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
import com.ye.business.hx.dao.IServerDetailDao;
import com.ye.business.hx.domain.ServerDetail;
import com.ye.business.hx.dto.FindServerDetailPage;
import com.ye.business.hx.dto.ServerDetailDto;
import com.ye.business.hx.service.IServerDetailService;
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
public class ServerDetailServiceImpl implements IServerDetailService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ServerDetailServiceImpl.class);
	

	@Resource
	private IServerDetailDao serverDetailDao;
	
	
	@Override
	public void addServerDetail(
			ServerDetailDto serverDetailDto) throws TsfaServiceException {
		logger.debug("addServerDetail(AddServerDetail addServerDetail={}) - start", serverDetailDto); 

		AssertUtils.notNull(serverDetailDto);
		try {
			ServerDetail serverDetail = new ServerDetail();
			//add数据录入
			serverDetail.setCode(GUID.getPreUUID());
			serverDetail.setServerCode(serverDetailDto.getServerCode());
			serverDetail.setUserType(serverDetailDto.getUserType());
			serverDetail.setServerNum(serverDetailDto.getServerNum());
			serverDetail.setPrice(serverDetailDto.getPrice());
			serverDetail.setIsShop(serverDetailDto.getIsShop());
			serverDetail.setCreateDate(new Date());
			serverDetail.setServerDesc(serverDetailDto.getServerDesc());
			serverDetailDao.insertSelective(serverDetail);
			logger.debug("addServerDetail(ServerDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增系统服务项信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVER_DETAIL_ADD_ERROR,"新增系统服务项信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询系统服务项信息
	 *
	 * @param findServerDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ServerDetailDto>  findServerDetails(FindServerDetailPage findServerDetailPage)throws TsfaServiceException{
		AssertUtils.notNull(findServerDetailPage);
		List<ServerDetailDto> returnList=null;
		try {
			returnList = serverDetailDao.findServerDetails(findServerDetailPage);
		} catch (Exception e) {
			logger.error("查找系统服务项信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVER_DETAIL_NOT_EXIST_ERROR,"系统服务项信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateServerDetail(
			ServerDetailDto serverDetailDto)
			throws TsfaServiceException {
		logger.debug("updateServerDetail(ServerDetailDto serverDetailDto={}) - start", serverDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(serverDetailDto);
		AssertUtils.notNullAndEmpty(serverDetailDto.getCode(),"Code不能为空");
		try {
			ServerDetail serverDetail = new ServerDetail();
			//update数据录入
			serverDetail.setCode(serverDetailDto.getCode());
			serverDetail.setServerCode(serverDetailDto.getServerCode());
			serverDetail.setUserType(serverDetailDto.getUserType());
			serverDetail.setServerNum(serverDetailDto.getServerNum());
			serverDetail.setPrice(serverDetailDto.getPrice());
			serverDetail.setIsShop(serverDetailDto.getIsShop());
//			serverDetail.setCreateDate(new Date());
			serverDetail.setServerDesc(serverDetailDto.getServerDesc());
			AssertUtils.notUpdateMoreThanOne(serverDetailDao.updateByPrimaryKeySelective(serverDetail));
			logger.debug("updateServerDetail(ServerDetailDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("系统服务项信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVER_DETAIL_UPDATE_ERROR,"系统服务项信息更新信息错误！",e);
		}
	}

	

	@Override
	public ServerDetailDto findServerDetail(
			ServerDetailDto serverDetailDto) throws TsfaServiceException {
		logger.debug("findServerDetail(FindServerDetail findServerDetail={}) - start", serverDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(serverDetailDto);
		AssertUtils.notAllNull(serverDetailDto.getCode(),"Code不能为空");
		try {
			ServerDetail serverDetail = serverDetailDao.selectByPrimaryKey(serverDetailDto.getCode());
			if(serverDetail == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SERVER_DETAIL_NOT_EXIST_ERROR,"系统服务项信息不存在");
			}
			ServerDetailDto findServerDetailReturn = new ServerDetailDto();
			//find数据录入
			findServerDetailReturn.setCode(serverDetail.getCode());
			findServerDetailReturn.setServerCode(serverDetail.getServerCode());
			findServerDetailReturn.setUserType(serverDetail.getUserType());
			findServerDetailReturn.setServerNum(serverDetail.getServerNum());
			findServerDetailReturn.setPrice(serverDetail.getPrice());
			findServerDetailReturn.setIsShop(serverDetail.getIsShop());
			findServerDetailReturn.setCreateDate(new Date());
			findServerDetailReturn.setServerDesc(serverDetail.getServerDesc());
			logger.debug("findServerDetail(ServerDetailDto) - end - return value={}", findServerDetailReturn); //$NON-NLS-1$
			return findServerDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找系统服务项信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVER_DETAIL_FIND_ERROR,"查找系统服务项信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ServerDetailDto> findServerDetailPage(
			FindServerDetailPage findServerDetailPage)
			throws TsfaServiceException {
		logger.debug("findServerDetailPage(FindServerDetailPage findServerDetailPage={}) - start", findServerDetailPage); //$NON-NLS-1$

		AssertUtils.notNull(findServerDetailPage);
		List<ServerDetailDto> returnList=null;
		int count = 0;
		try {
			returnList = serverDetailDao.findServerDetailPage(findServerDetailPage);
			count = serverDetailDao.findServerDetailPageCount(findServerDetailPage);
		}  catch (Exception e) {
			logger.error("系统服务项信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SERVER_DETAIL_FIND_PAGE_ERROR,"系统服务项信息不存在错误.！",e);
		}
		Page<ServerDetailDto> returnPage = new Page<ServerDetailDto>(returnList, count, findServerDetailPage);

		logger.debug("findServerDetailPage(FindServerDetailPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 

	@Override
	public void removeByServerCode(String serverCode) throws TsfaServiceException {
		logger.debug("removeByServerCode(String serverCode={}) - start", serverCode); //$NON-NLS-1$
		AssertUtils.notNull(serverCode);
		serverDetailDao.deleteByServerCode(serverCode);
		logger.debug("findServerDetailPage(serverCode) - end"); //$NON-NLS-1$
	}
}
