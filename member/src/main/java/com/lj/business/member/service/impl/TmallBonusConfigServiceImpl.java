package com.lj.business.member.service.impl;

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
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.ITmallBonusConfigDao;
import com.lj.business.member.domain.TmallBonusConfig;
import com.lj.business.member.dto.FindTmallBonusConfigPage;
import com.lj.business.member.dto.TmallBonusConfigDto;
import com.lj.business.member.service.ITmallBonusConfigService;
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
public class TmallBonusConfigServiceImpl implements ITmallBonusConfigService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(TmallBonusConfigServiceImpl.class);
	

	@Resource
	private ITmallBonusConfigDao tmallBonusConfigDao;
	
	
	@Override
	public void addTmallBonusConfig(
			TmallBonusConfigDto tmallBonusConfigDto) throws TsfaServiceException {
		logger.debug("addTmallBonusConfig(AddTmallBonusConfig addTmallBonusConfig={}) - start", tmallBonusConfigDto); 

		AssertUtils.notNull(tmallBonusConfigDto);
		try {
			TmallBonusConfig tmallBonusConfig = new TmallBonusConfig();
			//add数据录入
			tmallBonusConfig.setCode(GUID.generateByUUID());
			tmallBonusConfig.setMerchantNo(tmallBonusConfigDto.getMerchantNo());
			tmallBonusConfig.setOrdAmtMin(tmallBonusConfigDto.getOrdAmtMin());
			tmallBonusConfig.setOrdAmtMax(tmallBonusConfigDto.getOrdAmtMax());
			tmallBonusConfig.setBonuxMin(tmallBonusConfigDto.getBonuxMin());
			tmallBonusConfig.setBonuxMax(tmallBonusConfigDto.getBonuxMax());
			tmallBonusConfig.setCreateDate(new Date());
			tmallBonusConfig.setCreateId(tmallBonusConfigDto.getCreateId());
			tmallBonusConfigDao.insert(tmallBonusConfig);
			logger.debug("addTmallBonusConfig(TmallBonusConfigDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增天猫订单红包配置信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_CONFIG_ADD_ERROR,"新增天猫订单红包配置信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询天猫订单红包配置信息
	 *
	 * @param findTmallBonusConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<TmallBonusConfigDto>  findTmallBonusConfigs(FindTmallBonusConfigPage findTmallBonusConfigPage)throws TsfaServiceException{
		logger.debug("findTmallBonusConfigs(FindTmallBonusConfigPage findTmallBonusConfigPage={}) - start", findTmallBonusConfigPage); 
		AssertUtils.notNull(findTmallBonusConfigPage);
		List<TmallBonusConfigDto> returnList=null;
		try {
			returnList = tmallBonusConfigDao.findTmallBonusConfigs(findTmallBonusConfigPage);
			logger.debug("findTmallBonusConfigs(TmallBonusConfigDto) - end - return value={}", returnList); 
		} catch (Exception e) {
			logger.error("查找天猫订单红包配置信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_CONFIG_NOT_EXIST_ERROR,"天猫订单红包配置信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateTmallBonusConfig(
			TmallBonusConfigDto tmallBonusConfigDto)
			throws TsfaServiceException {
		logger.debug("updateTmallBonusConfig(TmallBonusConfigDto tmallBonusConfigDto={}) - start", tmallBonusConfigDto); //$NON-NLS-1$

		AssertUtils.notNull(tmallBonusConfigDto);
		AssertUtils.notNullAndEmpty(tmallBonusConfigDto.getCode(),"Code不能为空");
		try {
			TmallBonusConfig tmallBonusConfig = new TmallBonusConfig();
			//update数据录入
			tmallBonusConfig.setCode(tmallBonusConfigDto.getCode());
			tmallBonusConfig.setMerchantNo(tmallBonusConfigDto.getMerchantNo());
			tmallBonusConfig.setOrdAmtMin(tmallBonusConfigDto.getOrdAmtMin());
			tmallBonusConfig.setOrdAmtMax(tmallBonusConfigDto.getOrdAmtMax());
			tmallBonusConfig.setBonuxMin(tmallBonusConfigDto.getBonuxMin());
			tmallBonusConfig.setBonuxMax(tmallBonusConfigDto.getBonuxMax());
			tmallBonusConfig.setCreateDate(new Date());
//			tmallBonusConfig.setCreateId(tmallBonusConfigDto.getCreateId());
			AssertUtils.notUpdateMoreThanOne(tmallBonusConfigDao.updateByPrimaryKeySelective(tmallBonusConfig));
			logger.debug("updateTmallBonusConfig(TmallBonusConfigDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("天猫订单红包配置信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_CONFIG_UPDATE_ERROR,"天猫订单红包配置信息更新信息错误！",e);
		}
	}

	

	@Override
	public TmallBonusConfigDto findTmallBonusConfig(
			TmallBonusConfigDto tmallBonusConfigDto) throws TsfaServiceException {
		logger.debug("findTmallBonusConfig(FindTmallBonusConfig findTmallBonusConfig={}) - start", tmallBonusConfigDto); //$NON-NLS-1$

		AssertUtils.notNull(tmallBonusConfigDto);
		AssertUtils.notAllNull(tmallBonusConfigDto.getCode(),"Code不能为空");
		try {
			TmallBonusConfig tmallBonusConfig = tmallBonusConfigDao.selectByPrimaryKey(tmallBonusConfigDto.getCode());
			if(tmallBonusConfig == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.TMALL_BONUS_CONFIG_NOT_EXIST_ERROR,"天猫订单红包配置信息不存在");
			}
			TmallBonusConfigDto findTmallBonusConfigReturn = new TmallBonusConfigDto();
			//find数据录入
			findTmallBonusConfigReturn.setCode(tmallBonusConfig.getCode());
			findTmallBonusConfigReturn.setMerchantNo(tmallBonusConfig.getMerchantNo());
			findTmallBonusConfigReturn.setOrdAmtMin(tmallBonusConfig.getOrdAmtMin());
			findTmallBonusConfigReturn.setOrdAmtMax(tmallBonusConfig.getOrdAmtMax());
			findTmallBonusConfigReturn.setBonuxMin(tmallBonusConfig.getBonuxMin());
			findTmallBonusConfigReturn.setBonuxMax(tmallBonusConfig.getBonuxMax());
			findTmallBonusConfigReturn.setCreateDate(tmallBonusConfig.getCreateDate());
			findTmallBonusConfigReturn.setCreateId(tmallBonusConfig.getCreateId());
			
			logger.debug("findTmallBonusConfig(TmallBonusConfigDto) - end - return value={}", findTmallBonusConfigReturn); //$NON-NLS-1$
			return findTmallBonusConfigReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找天猫订单红包配置信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_CONFIG_FIND_ERROR,"查找天猫订单红包配置信息信息错误！",e);
		}


	}

	
	@Override
	public Page<TmallBonusConfigDto> findTmallBonusConfigPage(
			FindTmallBonusConfigPage findTmallBonusConfigPage)
			throws TsfaServiceException {
		logger.debug("findTmallBonusConfigPage(FindTmallBonusConfigPage findTmallBonusConfigPage={}) - start", findTmallBonusConfigPage); //$NON-NLS-1$

		AssertUtils.notNull(findTmallBonusConfigPage);
		List<TmallBonusConfigDto> returnList=null;
		int count = 0;
		try {
			returnList = tmallBonusConfigDao.findTmallBonusConfigPage(findTmallBonusConfigPage);
			count = tmallBonusConfigDao.findTmallBonusConfigPageCount(findTmallBonusConfigPage);
		}  catch (Exception e) {
			logger.error("天猫订单红包配置信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_CONFIG_FIND_PAGE_ERROR,"天猫订单红包配置信息不存在错误.！",e);
		}
		Page<TmallBonusConfigDto> returnPage = new Page<TmallBonusConfigDto>(returnList, count, findTmallBonusConfigPage);

		logger.debug("findTmallBonusConfigPage(FindTmallBonusConfigPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public void deleteTmallBonusConfig(TmallBonusConfigDto tmallBonusConfigDto) throws TsfaServiceException {
		logger.debug("updateTmallBonusConfig(TmallBonusConfigDto tmallBonusConfigDto={}) - start", tmallBonusConfigDto); //$NON-NLS-1$
		AssertUtils.notNull(tmallBonusConfigDto);
		AssertUtils.notNullAndEmpty(tmallBonusConfigDto.getCode(),"Code不能为空");
		try {
			AssertUtils.notUpdateMoreThanOne(tmallBonusConfigDao.deleteByPrimaryKey(tmallBonusConfigDto.getCode()));
			logger.debug("updateTmallBonusConfig(TmallBonusConfigDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("天猫订单红包配置信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_CONFIG_UPDATE_ERROR,"天猫订单红包配置信息更新信息错误！",e);
		}
	
	} 


}
