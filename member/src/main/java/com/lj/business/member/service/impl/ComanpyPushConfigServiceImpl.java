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
import com.lj.business.member.dao.IComanpyPushConfigDao;
import com.lj.business.member.domain.ComanpyPushConfig;
import com.lj.business.member.dto.company.AddComanpyPushConfig;
import com.lj.business.member.dto.company.AddComanpyPushConfigReturn;
import com.lj.business.member.dto.company.DelComanpyPushConfig;
import com.lj.business.member.dto.company.DelComanpyPushConfigReturn;
import com.lj.business.member.dto.company.FindComanpyPushConfig;
import com.lj.business.member.dto.company.FindComanpyPushConfigPageReturn;
import com.lj.business.member.dto.company.FindComanpyPushConfigSelective;
import com.lj.business.member.dto.company.FindComanpyPushConfigSelectiveReturn;
import com.lj.business.member.dto.company.FindCompanyPushConfigPage;
import com.lj.business.member.dto.company.FindCompanyPushConfigReturn;
import com.lj.business.member.dto.company.UpdateComanpyPushConfig;
import com.lj.business.member.dto.company.UpdateComanpyPushConfigReturn;
import com.lj.business.member.service.IComanpyPushConfigService;

/**
 * 
 * 
 * 类说明：经销商推送配置实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年07月24日
 */
@Service
public class ComanpyPushConfigServiceImpl implements IComanpyPushConfigService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ComanpyPushConfigServiceImpl.class);
	
	@Resource
	private IComanpyPushConfigDao comanpyPushConfigDao;
	
	@Override
	public AddComanpyPushConfigReturn addComanpyPushConfig(AddComanpyPushConfig addComanpyPushConfig) throws TsfaServiceException {
		logger.debug("addComanpyPushConfig(AddComanpyPushConfig addComanpyPushConfig={}) - start", addComanpyPushConfig); 

		AssertUtils.notNull(addComanpyPushConfig);
		try {
			ComanpyPushConfig comanpyPushConfig = new ComanpyPushConfig();
			//add数据录入
			comanpyPushConfig.setCode(GUID.generateByUUID());
			comanpyPushConfig.setCompanyNo(addComanpyPushConfig.getCompanyNo());
			comanpyPushConfig.setCompanyName(addComanpyPushConfig.getCompanyName());
			comanpyPushConfig.setStatus(addComanpyPushConfig.getStatus());
			comanpyPushConfig.setSort(addComanpyPushConfig.getSort());
			comanpyPushConfig.setType(addComanpyPushConfig.getType());
			comanpyPushConfig.setContent(addComanpyPushConfig.getContent());
			comanpyPushConfig.setMerchantNo(addComanpyPushConfig.getMerchantNo());
			comanpyPushConfig.setCreateDate(new Date());
			comanpyPushConfig.setUpdateDate(new Date());
			comanpyPushConfig.setRemark(addComanpyPushConfig.getRemark());
			comanpyPushConfigDao.insert(comanpyPushConfig);
			AddComanpyPushConfigReturn addComanpyPushConfigReturn = new AddComanpyPushConfigReturn();
			
			logger.debug("addComanpyPushConfig(AddComanpyPushConfig) - end - return value={}", addComanpyPushConfigReturn); 
			return addComanpyPushConfigReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增经销商推送配置信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMANPY_PUSH_CONFIG_ADD_ERROR,"新增经销商推送配置信息错误！",e);
		}
	}
	
	@Override
	public DelComanpyPushConfigReturn delComanpyPushConfig(DelComanpyPushConfig delComanpyPushConfig) throws TsfaServiceException {
		logger.debug("delComanpyPushConfig(DelComanpyPushConfig delComanpyPushConfig={}) - start", delComanpyPushConfig); 

		AssertUtils.notNull(delComanpyPushConfig);
		AssertUtils.notNull(delComanpyPushConfig.getCode(),"Code不能为空！");
		try {
			comanpyPushConfigDao.deleteByPrimaryKey(delComanpyPushConfig.getCode());
			DelComanpyPushConfigReturn delComanpyPushConfigReturn  = new DelComanpyPushConfigReturn();

			logger.debug("delComanpyPushConfig(DelComanpyPushConfig) - end - return value={}", delComanpyPushConfigReturn); 
			return delComanpyPushConfigReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除经销商推送配置信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMANPY_PUSH_CONFIG_DEL_ERROR,"删除经销商推送配置信息错误！",e);
		}
	}

	@Override
	public UpdateComanpyPushConfigReturn updateComanpyPushConfig(UpdateComanpyPushConfig updateComanpyPushConfig) throws TsfaServiceException {
		logger.debug("updateComanpyPushConfig(UpdateComanpyPushConfig updateComanpyPushConfig={}) - start", updateComanpyPushConfig); 

		AssertUtils.notNull(updateComanpyPushConfig);
		AssertUtils.notNullAndEmpty(updateComanpyPushConfig.getCode(),"Code不能为空");
		try {
			ComanpyPushConfig comanpyPushConfig = new ComanpyPushConfig();
			//update数据录入
			comanpyPushConfig.setCode(updateComanpyPushConfig.getCode());
			comanpyPushConfig.setCompanyNo(updateComanpyPushConfig.getCompanyNo());
			comanpyPushConfig.setCompanyName(updateComanpyPushConfig.getCompanyName());
			comanpyPushConfig.setStatus(updateComanpyPushConfig.getStatus());
			comanpyPushConfig.setSort(updateComanpyPushConfig.getSort());
			comanpyPushConfig.setType(updateComanpyPushConfig.getType());
			comanpyPushConfig.setContent(updateComanpyPushConfig.getContent());
			comanpyPushConfig.setMerchantNo(updateComanpyPushConfig.getMerchantNo());
			comanpyPushConfig.setCreateDate(updateComanpyPushConfig.getCreateDate());
			comanpyPushConfig.setUpdateDate(new Date());
			comanpyPushConfig.setRemark(updateComanpyPushConfig.getRemark());
			AssertUtils.notUpdateMoreThanOne(comanpyPushConfigDao.updateByPrimaryKeySelective(comanpyPushConfig));
			UpdateComanpyPushConfigReturn updateComanpyPushConfigReturn = new UpdateComanpyPushConfigReturn();

			logger.debug("updateComanpyPushConfig(UpdateComanpyPushConfig) - end - return value={}", updateComanpyPushConfigReturn); 
			return updateComanpyPushConfigReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("经销商推送配置信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMANPY_PUSH_CONFIG_UPDATE_ERROR,"经销商推送配置信息更新信息错误！",e);
		}
	}

	@Override
	public FindCompanyPushConfigReturn findComanpyPushConfig(FindComanpyPushConfig findComanpyPushConfig) throws TsfaServiceException {
		logger.debug("findComanpyPushConfig(FindComanpyPushConfig findComanpyPushConfig={}) - start", findComanpyPushConfig); 

		AssertUtils.notNull(findComanpyPushConfig);
		AssertUtils.notAllNull(findComanpyPushConfig.getCode(),"Code不能为空");
		try {
			ComanpyPushConfig comanpyPushConfig = comanpyPushConfigDao.selectByPrimaryKey(findComanpyPushConfig.getCode());
			if(comanpyPushConfig == null){
				throw new TsfaServiceException(ErrorCode.COMANPY_PUSH_CONFIG_NOT_EXIST_ERROR,"经销商推送配置信息不存在");
			}
			FindCompanyPushConfigReturn findComanpyPushConfigReturn = new FindCompanyPushConfigReturn();
			//find数据录入
			findComanpyPushConfigReturn.setCode(comanpyPushConfig.getCode());
			findComanpyPushConfigReturn.setCompanyNo(comanpyPushConfig.getCompanyNo());
			findComanpyPushConfigReturn.setCompanyName(comanpyPushConfig.getCompanyName());
			findComanpyPushConfigReturn.setStatus(comanpyPushConfig.getStatus());
			findComanpyPushConfigReturn.setSort(comanpyPushConfig.getSort());
			findComanpyPushConfigReturn.setType(comanpyPushConfig.getType());
			findComanpyPushConfigReturn.setContent(comanpyPushConfig.getContent());
			findComanpyPushConfigReturn.setMerchantNo(comanpyPushConfig.getMerchantNo());
			findComanpyPushConfigReturn.setCreateDate(comanpyPushConfig.getCreateDate());
			findComanpyPushConfigReturn.setUpdateDate(comanpyPushConfig.getUpdateDate());
			findComanpyPushConfigReturn.setRemark(comanpyPushConfig.getRemark());
			
			logger.debug("findComanpyPushConfig(FindComanpyPushConfig) - end - return value={}", findComanpyPushConfigReturn); 
			return findComanpyPushConfigReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找经销商推送配置信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.COMANPY_PUSH_CONFIG_FIND_ERROR,"查找经销商推送配置信息信息错误！",e);
		}
	}

	@Override
	public Page<FindComanpyPushConfigPageReturn> findComanpyPushConfigPage(FindCompanyPushConfigPage findCompanyPushConfigPage) throws TsfaServiceException {
		logger.debug("findComanpyPushConfigPage(FindComanpyPushConfigPage findComanpyPushConfigPage={}) - start", findCompanyPushConfigPage); 

		AssertUtils.notNull(findCompanyPushConfigPage);
		List<FindComanpyPushConfigPageReturn> returnList = null;
		int count = 0;
		try {
			count = comanpyPushConfigDao.findComanpyPushConfigPageCount(findCompanyPushConfigPage);
			if(count > 0) {
				returnList = comanpyPushConfigDao.findComanpyPushConfigPage(findCompanyPushConfigPage);
			}
		}  catch (Exception e) {
			logger.error("经销商推送配置信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.COMANPY_PUSH_CONFIG_FIND_PAGE_ERROR,"经销商推送配置信息不存在错误.！",e);
		}
		Page<FindComanpyPushConfigPageReturn> returnPage = new Page<FindComanpyPushConfigPageReturn>(returnList, count, findCompanyPushConfigPage);

		logger.debug("findComanpyPushConfigPage(FindComanpyPushConfigPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

    @Override
    public List<FindComanpyPushConfigSelectiveReturn> findComanpyPushConfigSelective(FindComanpyPushConfigSelective findComanpyPushConfigSelective)
            throws TsfaServiceException {
        logger.debug("findComanpyPushConfigSelective(FindComanpyPushConfigSelective findComanpyPushConfigSelective={}) - start", findComanpyPushConfigSelective); 

        AssertUtils.notNull(findComanpyPushConfigSelective);
        List<FindComanpyPushConfigSelectiveReturn> returnList = null;
        try {
            returnList = comanpyPushConfigDao.findComanpyPushConfigSelective(findComanpyPushConfigSelective);
        }  catch (Exception e) {
            logger.error("查找经销商推送配置信息信息错误！",e);
            throw new TsfaServiceException(ErrorCode.COMANPY_PUSH_CONFIG_FIND_ERROR,"查找经销商推送配置信息信息错误！",e);
        }

        logger.debug("findComanpyPushConfigSelective(FindComanpyPushConfigSelective) - end - return value={}", returnList); 
        return returnList;
    } 
}
