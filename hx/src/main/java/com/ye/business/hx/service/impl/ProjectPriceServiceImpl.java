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
import com.ye.business.hx.dao.IProjectPriceDao;
import com.ye.business.hx.domain.ProjectPrice;
import com.ye.business.hx.dto.FindProjectPricePage;
import com.ye.business.hx.dto.ProjectPriceDto;
import com.ye.business.hx.service.IProjectPriceService;
import com.ye.business.hx.utils.PinYinUtils;
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
public class ProjectPriceServiceImpl implements IProjectPriceService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ProjectPriceServiceImpl.class);
	

	@Resource
	private IProjectPriceDao projectPriceDao;
	
	
	@Override
	public ProjectPriceDto addProjectPrice(
			ProjectPriceDto projectPriceDto) throws TsfaServiceException {
		logger.debug("addProjectPrice(AddProjectPrice addProjectPrice={}) - start", projectPriceDto); 

		AssertUtils.notNull(projectPriceDto);
		try {
			ProjectPrice projectPrice = new ProjectPrice();
			//add数据录入
			projectPrice.setCode(GUID.getPreUUID());
			projectPrice.setShopNo(projectPriceDto.getShopNo());
			projectPrice.setShopName(projectPriceDto.getShopName());
			projectPrice.setMerchantNo(projectPriceDto.getMerchantNo());
			projectPrice.setMerchantName(projectPriceDto.getMerchantName());
			projectPrice.setType1Name(projectPriceDto.getType1Name());
			projectPrice.setType1Code(projectPriceDto.getType1Code());
			projectPrice.setType2Name(projectPriceDto.getType2Name());
			projectPrice.setType2Code(projectPriceDto.getType2Code());
			projectPrice.setProjectName(projectPriceDto.getProjectName());
			projectPrice.setProjectNo(projectPriceDto.getProjectNo());
			//转换拼音首字母
			projectPrice.setPinyin(PinYinUtils.getStringFirstPinYin(projectPriceDto.getProjectName()));
			projectPrice.setProjectUnit(projectPriceDto.getProjectUnit());
			projectPrice.setPrice(projectPriceDto.getPrice());
			projectPrice.setAllowItemDiscount(projectPriceDto.getAllowItemDiscount());
			projectPrice.setAllowOrderDiscount(projectPriceDto.getAllowOrderDiscount());
			if(null==projectPriceDto.getIndexNo()) {
				projectPrice.setIndexNo(0);
			}else {
				projectPrice.setIndexNo(projectPriceDto.getIndexNo());
			}
			projectPrice.setEnname(projectPriceDto.getEnname());
			projectPrice.setMinPrice(projectPriceDto.getMinPrice());
			projectPrice.setMaxPrice(projectPriceDto.getMaxPrice());
			projectPrice.setMinDiscount(projectPriceDto.getMinDiscount());
			projectPrice.setStatus(projectPriceDto.getStatus());
			projectPrice.setAllowDeal(projectPriceDto.getAllowDeal());
			projectPrice.setUpdateId(projectPriceDto.getUpdateId());
			projectPrice.setUpdateDate(new Date());
			projectPrice.setCreateId(projectPriceDto.getCreateId());
			projectPrice.setCreateDate(new Date());
			projectPriceDao.insertSelective(projectPrice);
			
			ProjectPriceDto rt=new ProjectPriceDto();
			rt.setCode(projectPrice.getCode());
			rt.setPinyin(projectPrice.getPinyin());
			logger.debug("addProjectPrice(ProjectPriceDto) - end - return"); 
			return rt;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增项目单价信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PRICE_ADD_ERROR,"新增项目单价信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询项目单价信息
	 *
	 * @param findProjectPricePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<ProjectPriceDto>  findProjectPrices(FindProjectPricePage findProjectPricePage)throws TsfaServiceException{
		AssertUtils.notNull(findProjectPricePage);
		List<ProjectPriceDto> returnList=null;
		try {
			returnList = projectPriceDao.findProjectPrices(findProjectPricePage);
		} catch (Exception e) {
			logger.error("查找项目单价信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PRICE_NOT_EXIST_ERROR,"项目单价信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateProjectPrice(
			ProjectPriceDto projectPriceDto)
			throws TsfaServiceException {
		logger.debug("updateProjectPrice(ProjectPriceDto projectPriceDto={}) - start", projectPriceDto); //$NON-NLS-1$

		AssertUtils.notNull(projectPriceDto);
		AssertUtils.notNullAndEmpty(projectPriceDto.getCode(),"Code不能为空");
		try {
			ProjectPrice projectPrice = new ProjectPrice();
			//update数据录入
			projectPrice.setCode(projectPriceDto.getCode());
			projectPrice.setShopNo(projectPriceDto.getShopNo());
			projectPrice.setShopName(projectPriceDto.getShopName());
			projectPrice.setMerchantNo(projectPriceDto.getMerchantNo());
			projectPrice.setMerchantName(projectPriceDto.getMerchantName());
			projectPrice.setType1Name(projectPriceDto.getType1Name());
			projectPrice.setType1Code(projectPriceDto.getType1Code());
			projectPrice.setType2Name(projectPriceDto.getType2Name());
			projectPrice.setType2Code(projectPriceDto.getType2Code());
			projectPrice.setProjectName(projectPriceDto.getProjectName());
			projectPrice.setProjectNo(projectPriceDto.getProjectNo());
			//转换拼音首字母
			projectPrice.setPinyin(PinYinUtils.getStringFirstPinYin(projectPriceDto.getProjectName()));
			projectPrice.setProjectUnit(projectPriceDto.getProjectUnit());
			projectPrice.setPrice(projectPriceDto.getPrice());
			projectPrice.setAllowItemDiscount(projectPriceDto.getAllowItemDiscount());
			projectPrice.setAllowOrderDiscount(projectPriceDto.getAllowOrderDiscount());
			projectPrice.setIndexNo(projectPriceDto.getIndexNo());
			projectPrice.setEnname(projectPriceDto.getEnname());
			projectPrice.setMinPrice(projectPriceDto.getMinPrice());
			projectPrice.setMaxPrice(projectPriceDto.getMaxPrice());
			projectPrice.setMinDiscount(projectPriceDto.getMinDiscount());
			projectPrice.setStatus(projectPriceDto.getStatus());
			projectPrice.setAllowDeal(projectPriceDto.getAllowDeal());
			projectPrice.setUpdateId(projectPriceDto.getUpdateId());
			projectPrice.setUpdateDate(new Date());
//			projectPrice.setCreateId(projectPriceDto.getCreateId());
//			projectPrice.setCreateDate(projectPriceDto.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(projectPriceDao.updateByPrimaryKeySelective(projectPrice));
			logger.debug("updateProjectPrice(ProjectPriceDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("项目单价信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PRICE_UPDATE_ERROR,"项目单价信息更新信息错误！",e);
		}
	}

	

	@Override
	public ProjectPriceDto findProjectPrice(
			ProjectPriceDto projectPriceDto) throws TsfaServiceException {
		logger.debug("findProjectPrice(FindProjectPrice findProjectPrice={}) - start", projectPriceDto); //$NON-NLS-1$

		AssertUtils.notNull(projectPriceDto);
		AssertUtils.notAllNull(projectPriceDto.getCode(),"Code不能为空");
		try {
			ProjectPrice projectPrice = projectPriceDao.selectByPrimaryKey(projectPriceDto.getCode());
			if(projectPrice == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PROJECT_PRICE_NOT_EXIST_ERROR,"项目单价信息不存在");
			}
			ProjectPriceDto findProjectPriceReturn = new ProjectPriceDto();
			//find数据录入
			findProjectPriceReturn.setCode(projectPrice.getCode());
			findProjectPriceReturn.setShopNo(projectPrice.getShopNo());
			findProjectPriceReturn.setShopName(projectPrice.getShopName());
			findProjectPriceReturn.setMerchantNo(projectPrice.getMerchantNo());
			findProjectPriceReturn.setMerchantName(projectPrice.getMerchantName());
			findProjectPriceReturn.setType1Name(projectPrice.getType1Name());
			findProjectPriceReturn.setType1Code(projectPrice.getType1Code());
			findProjectPriceReturn.setType2Name(projectPrice.getType2Name());
			findProjectPriceReturn.setType2Code(projectPrice.getType2Code());
			findProjectPriceReturn.setProjectName(projectPrice.getProjectName());
			findProjectPriceReturn.setProjectNo(projectPrice.getProjectNo());
			findProjectPriceReturn.setPinyin(projectPrice.getPinyin());
			findProjectPriceReturn.setProjectUnit(projectPrice.getProjectUnit());
			findProjectPriceReturn.setPrice(projectPrice.getPrice());
			findProjectPriceReturn.setAllowItemDiscount(projectPrice.getAllowItemDiscount());
			findProjectPriceReturn.setAllowOrderDiscount(projectPrice.getAllowOrderDiscount());
			findProjectPriceReturn.setIndexNo(projectPrice.getIndexNo());
			findProjectPriceReturn.setEnname(projectPrice.getEnname());
			findProjectPriceReturn.setMinPrice(projectPrice.getMinPrice());
			findProjectPriceReturn.setMaxPrice(projectPrice.getMaxPrice());
			findProjectPriceReturn.setMinDiscount(projectPrice.getMinDiscount());
			findProjectPriceReturn.setStatus(projectPrice.getStatus());
			findProjectPriceReturn.setAllowDeal(projectPrice.getAllowDeal());
			findProjectPriceReturn.setUpdateId(projectPrice.getUpdateId());
			findProjectPriceReturn.setUpdateDate(projectPrice.getUpdateDate());
			findProjectPriceReturn.setCreateId(projectPrice.getCreateId());
			findProjectPriceReturn.setCreateDate(projectPrice.getCreateDate());
			
			logger.debug("findProjectPrice(ProjectPriceDto) - end - return value={}", findProjectPriceReturn); //$NON-NLS-1$
			return findProjectPriceReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找项目单价信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PRICE_FIND_ERROR,"查找项目单价信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ProjectPriceDto> findProjectPricePage(
			FindProjectPricePage findProjectPricePage)
			throws TsfaServiceException {
		logger.debug("findProjectPricePage(FindProjectPricePage findProjectPricePage={}) - start", findProjectPricePage); //$NON-NLS-1$

		AssertUtils.notNull(findProjectPricePage);
		List<ProjectPriceDto> returnList=null;
		int count = 0;
		try {
			//搜索名称非空则，转换为拼音搜索
//			if(StringUtils.isNotEmpty(findProjectPricePage.getParam().getProjectName())) {
//				findProjectPricePage.getParam().setPinyin(PinYinUtils.getStringFirstPinYin(findProjectPricePage.getParam().getProjectName()));
//				findProjectPricePage.getParam().setProjectName(null);
//			}
			returnList = projectPriceDao.findProjectPricePage(findProjectPricePage);
			count = projectPriceDao.findProjectPricePageCount(findProjectPricePage);
		}  catch (Exception e) {
			logger.error("项目单价信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PRICE_FIND_PAGE_ERROR,"项目单价信息不存在错误.！",e);
		}
		Page<ProjectPriceDto> returnPage = new Page<ProjectPriceDto>(returnList, count, findProjectPricePage);

		logger.debug("findProjectPricePage(FindProjectPricePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public int deleteProjectPrice(ProjectPriceDto projectPriceDto) throws TsfaServiceException {
		logger.debug("deleteProjectPrice(ProjectPriceDto projectPriceDto={}) - start", projectPriceDto); //$NON-NLS-1$
		AssertUtils.notNullAndEmpty(projectPriceDto.getCode(),"Code不能为空");
		AssertUtils.notNull(projectPriceDto);
		int count = 0;
		try {
			count = projectPriceDao.deleteProjectPrice(projectPriceDto);
		}  catch (Exception e) {
			logger.error("项目单价信息删除错误",e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PRICE_UPDATE_ERROR,"项目单价信息修改错误.！",e);
		}
		logger.debug("deleteProjectPrice(ProjectPriceDto) - end - return value={}", count); //$NON-NLS-1$
		return  count;
	
	} 


}
