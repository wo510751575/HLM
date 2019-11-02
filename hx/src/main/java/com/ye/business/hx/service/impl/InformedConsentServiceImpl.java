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

import com.ye.business.hx.dto.InformedConsentDto;
import com.ye.business.hx.dto.FindInformedConsentPage;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IInformedConsentDao;
import com.ye.business.hx.domain.InformedConsent;
import com.ye.business.hx.service.IInformedConsentService;
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
public class InformedConsentServiceImpl implements IInformedConsentService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(InformedConsentServiceImpl.class);
	

	@Resource
	private IInformedConsentDao informedConsentDao;
	
	
	@Override
	public void addInformedConsent(
			InformedConsentDto informedConsentDto) throws TsfaServiceException {
		logger.debug("addInformedConsent(AddInformedConsent addInformedConsent={}) - start", informedConsentDto); 

		AssertUtils.notNull(informedConsentDto);
		try {
			InformedConsent informedConsent = new InformedConsent();
			//add数据录入
			informedConsent.setCode(informedConsentDto.getCode());
			informedConsent.setTitle(informedConsentDto.getTitle());
			informedConsent.setCreateDate(informedConsentDto.getCreateDate());
			informedConsent.setRemark(informedConsentDto.getRemark());
			informedConsent.setRemark2(informedConsentDto.getRemark2());
			informedConsent.setRemark3(informedConsentDto.getRemark3());
			informedConsent.setRemark4(informedConsentDto.getRemark4());
			informedConsent.setContent(informedConsentDto.getContent());
			informedConsentDao.insertSelective(informedConsent);
			logger.debug("addInformedConsent(InformedConsentDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增知情同意书信息错误！",e);
			throw new TsfaServiceException(ErrorCode.INFORMED_CONSENT_ADD_ERROR,"新增知情同意书信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询知情同意书信息
	 *
	 * @param findInformedConsentPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<InformedConsentDto>  findInformedConsents(FindInformedConsentPage findInformedConsentPage)throws TsfaServiceException{
		AssertUtils.notNull(findInformedConsentPage);
		List<InformedConsentDto> returnList=null;
		try {
			returnList = informedConsentDao.findInformedConsents(findInformedConsentPage);
		} catch (Exception e) {
			logger.error("查找知情同意书信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.INFORMED_CONSENT_NOT_EXIST_ERROR,"知情同意书信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateInformedConsent(
			InformedConsentDto informedConsentDto)
			throws TsfaServiceException {
		logger.debug("updateInformedConsent(InformedConsentDto informedConsentDto={}) - start", informedConsentDto); 

		AssertUtils.notNull(informedConsentDto);
		AssertUtils.notNullAndEmpty(informedConsentDto.getCode(),"Code不能为空");
		try {
			InformedConsent informedConsent = new InformedConsent();
			//update数据录入
			informedConsent.setCode(informedConsentDto.getCode());
			informedConsent.setTitle(informedConsentDto.getTitle());
			informedConsent.setCreateDate(informedConsentDto.getCreateDate());
			informedConsent.setRemark(informedConsentDto.getRemark());
			informedConsent.setRemark2(informedConsentDto.getRemark2());
			informedConsent.setRemark3(informedConsentDto.getRemark3());
			informedConsent.setRemark4(informedConsentDto.getRemark4());
			informedConsent.setContent(informedConsentDto.getContent());
			AssertUtils.notUpdateMoreThanOne(informedConsentDao.updateByPrimaryKeySelective(informedConsent));
			logger.debug("updateInformedConsent(InformedConsentDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("知情同意书信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.INFORMED_CONSENT_UPDATE_ERROR,"知情同意书信息更新信息错误！",e);
		}
	}

	

	@Override
	public InformedConsentDto findInformedConsent(
			InformedConsentDto informedConsentDto) throws TsfaServiceException {
		logger.debug("findInformedConsent(FindInformedConsent findInformedConsent={}) - start", informedConsentDto); 

		AssertUtils.notNull(informedConsentDto);
		AssertUtils.notAllNull(informedConsentDto.getCode(),"Code不能为空");
		try {
			InformedConsent informedConsent = informedConsentDao.selectByPrimaryKey(informedConsentDto.getCode());
			if(informedConsent == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.INFORMED_CONSENT_NOT_EXIST_ERROR,"知情同意书信息不存在");
			}
			InformedConsentDto findInformedConsentReturn = new InformedConsentDto();
			//find数据录入
			findInformedConsentReturn.setCode(informedConsent.getCode());
			findInformedConsentReturn.setTitle(informedConsent.getTitle());
			findInformedConsentReturn.setCreateDate(informedConsent.getCreateDate());
			findInformedConsentReturn.setRemark(informedConsent.getRemark());
			findInformedConsentReturn.setRemark2(informedConsent.getRemark2());
			findInformedConsentReturn.setRemark3(informedConsent.getRemark3());
			findInformedConsentReturn.setRemark4(informedConsent.getRemark4());
			findInformedConsentReturn.setContent(informedConsent.getContent());
			
			logger.debug("findInformedConsent(InformedConsentDto) - end - return value={}", findInformedConsentReturn); 
			return findInformedConsentReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找知情同意书信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.INFORMED_CONSENT_FIND_ERROR,"查找知情同意书信息信息错误！",e);
		}


	}

	
	@Override
	public Page<InformedConsentDto> findInformedConsentPage(
			FindInformedConsentPage findInformedConsentPage)
			throws TsfaServiceException {
		logger.debug("findInformedConsentPage(FindInformedConsentPage findInformedConsentPage={}) - start", findInformedConsentPage); 

		AssertUtils.notNull(findInformedConsentPage);
		List<InformedConsentDto> returnList=null;
		int count = 0;
		try {
			count = informedConsentDao.findInformedConsentPageCount(findInformedConsentPage);
			if(count>0) {
				returnList = informedConsentDao.findInformedConsentPage(findInformedConsentPage);
			}
		}  catch (Exception e) {
			logger.error("知情同意书信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.INFORMED_CONSENT_FIND_PAGE_ERROR,"知情同意书信息不存在错误.！",e);
		}
		Page<InformedConsentDto> returnPage = new Page<InformedConsentDto>(returnList, count, findInformedConsentPage);

		logger.debug("findInformedConsentPage(FindInformedConsentPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	/**   
	 * <p>Title: delete</p>   
	 * <p>Description: </p>   
	 * @param informedConsentDto
	 * @throws TsfaServiceException   
	 * @see com.ye.business.hx.service.IInformedConsentService#delete(com.ye.business.hx.dto.InformedConsentDto)   
	 */
	@Override
	public void delete(InformedConsentDto informedConsentDto) throws TsfaServiceException {
		logger.debug("delete(InformedConsentDto informedConsentDto = {})-start",informedConsentDto);
		try {
			informedConsentDao.deleteByPrimaryKey(informedConsentDto.getCode());
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.INFORMED_CONSENT_DELETE_ERROR,"删除知情同意书错误.！",e);
		}
	} 


}
