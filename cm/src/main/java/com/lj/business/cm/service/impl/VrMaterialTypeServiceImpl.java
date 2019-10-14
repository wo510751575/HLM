package com.lj.business.cm.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IVrMaterialTypeDao;
import com.lj.business.cm.domain.VrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.AddVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.AddVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialType.DelVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.DelVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeApiReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypePage;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypePageReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialType.UpdateVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.UpdateVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryReturn;
import com.lj.business.cm.service.IVrMaterialTypeCategoryService;
import com.lj.business.cm.service.IVrMaterialTypeService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class VrMaterialTypeServiceImpl implements IVrMaterialTypeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(VrMaterialTypeServiceImpl.class);
	

	@Resource
	private IVrMaterialTypeDao vrMaterialTypeDao;
	
	@Resource
	private IVrMaterialTypeCategoryService vrMaterialTypeCategoryService;
	
	
	@Override
	public AddVrMaterialTypeReturn addVrMaterialType(
			AddVrMaterialType addVrMaterialType) throws TsfaServiceException {
		logger.debug("addVrMaterialType(AddVrMaterialType addVrMaterialType={}) - start", addVrMaterialType); 

		AssertUtils.notNull(addVrMaterialType);
		try {
			VrMaterialType vrMaterialType = new VrMaterialType();
			//add数据录入
			vrMaterialType.setCode(addVrMaterialType.getCode());
			vrMaterialType.setMerchantNo(addVrMaterialType.getMerchantNo());
			vrMaterialType.setTypeName(addVrMaterialType.getTypeName());
			vrMaterialType.setTypeCount(addVrMaterialType.getTypeCount());
			vrMaterialType.setImgAddr(addVrMaterialType.getImgAddr());
			vrMaterialType.setCustomerAttentionRate(addVrMaterialType.getCustomerAttentionRate());
			vrMaterialType.setMaterialDimension(addVrMaterialType.getMaterialDimension());
			vrMaterialType.setShowIndex(addVrMaterialType.getShowIndex());
			vrMaterialType.setCreateId(addVrMaterialType.getCreateId());
			vrMaterialType.setCreateDate(new Date());
			vrMaterialType.setRemark(addVrMaterialType.getRemark());
			vrMaterialType.setRemark2(addVrMaterialType.getRemark2());
			vrMaterialType.setRemark3(addVrMaterialType.getRemark3());
			vrMaterialType.setRemark4(addVrMaterialType.getRemark4());
			vrMaterialTypeDao.insert(vrMaterialType);
			AddVrMaterialTypeReturn addVrMaterialTypeReturn = new AddVrMaterialTypeReturn();
			
			logger.debug("addVrMaterialType(AddVrMaterialType) - end - return value={}", addVrMaterialTypeReturn); 
			return addVrMaterialTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增VR素材类型信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_ADD_ERROR,"新增VR素材类型信息错误！",e);
		}
	}
	
	
	@Override
	public DelVrMaterialTypeReturn delVrMaterialType(
			DelVrMaterialType delVrMaterialType) throws TsfaServiceException {
		logger.debug("delVrMaterialType(DelVrMaterialType delVrMaterialType={}) - start", delVrMaterialType); 

		AssertUtils.notNull(delVrMaterialType);
		AssertUtils.notNull(delVrMaterialType.getCode(),"CODE不能为空！");
		try {
			vrMaterialTypeDao.deleteByPrimaryKey(delVrMaterialType.getCode());
			DelVrMaterialTypeReturn delVrMaterialTypeReturn  = new DelVrMaterialTypeReturn();

			logger.debug("delVrMaterialType(DelVrMaterialType) - end - return value={}", delVrMaterialTypeReturn); 
			return delVrMaterialTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除VR素材类型信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_DEL_ERROR,"删除VR素材类型信息错误！",e);

		}
	}

	@Override
	public UpdateVrMaterialTypeReturn updateVrMaterialType(
			UpdateVrMaterialType updateVrMaterialType)
			throws TsfaServiceException {
		logger.debug("updateVrMaterialType(UpdateVrMaterialType updateVrMaterialType={}) - start", updateVrMaterialType); 

		AssertUtils.notNull(updateVrMaterialType);
		AssertUtils.notNullAndEmpty(updateVrMaterialType.getCode(),"CODE不能为空");
		try {
			VrMaterialType vrMaterialType = new VrMaterialType();
			//update数据录入
			vrMaterialType.setCode(updateVrMaterialType.getCode());
			vrMaterialType.setMerchantNo(updateVrMaterialType.getMerchantNo());
			vrMaterialType.setTypeName(updateVrMaterialType.getTypeName());
			vrMaterialType.setTypeCount(updateVrMaterialType.getTypeCount());
			vrMaterialType.setImgAddr(updateVrMaterialType.getImgAddr());
			vrMaterialType.setCustomerAttentionRate(updateVrMaterialType.getCustomerAttentionRate());
			vrMaterialType.setMaterialDimension(updateVrMaterialType.getMaterialDimension());
			vrMaterialType.setShowIndex(updateVrMaterialType.getShowIndex());
			vrMaterialType.setCreateId(updateVrMaterialType.getCreateId());
			vrMaterialType.setCreateDate(updateVrMaterialType.getCreateDate());
			vrMaterialType.setRemark(updateVrMaterialType.getRemark());
			vrMaterialType.setRemark2(updateVrMaterialType.getRemark2());
			vrMaterialType.setRemark3(updateVrMaterialType.getRemark3());
			vrMaterialType.setRemark4(updateVrMaterialType.getRemark4());
			AssertUtils.notUpdateMoreThanOne(vrMaterialTypeDao.updateByPrimaryKeySelective(vrMaterialType));
			UpdateVrMaterialTypeReturn updateVrMaterialTypeReturn = new UpdateVrMaterialTypeReturn();

			logger.debug("updateVrMaterialType(UpdateVrMaterialType) - end - return value={}", updateVrMaterialTypeReturn); 
			return updateVrMaterialTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("VR素材类型信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_UPDATE_ERROR,"VR素材类型信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindVrMaterialTypeReturn findVrMaterialType(
			FindVrMaterialType findVrMaterialType) throws TsfaServiceException {
		logger.debug("findVrMaterialType(FindVrMaterialType findVrMaterialType={}) - start", findVrMaterialType); 

		AssertUtils.notNull(findVrMaterialType);
		AssertUtils.notAllNull(findVrMaterialType.getCode(),"CODE不能为空");
		try {
			VrMaterialType vrMaterialType = vrMaterialTypeDao.selectByPrimaryKey(findVrMaterialType.getCode());
			if(vrMaterialType == null){
				throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_NOT_EXIST_ERROR,"VR素材类型信息不存在");
			}
			FindVrMaterialTypeReturn findVrMaterialTypeReturn = new FindVrMaterialTypeReturn();
			//find数据录入
			findVrMaterialTypeReturn.setCode(vrMaterialType.getCode());
			findVrMaterialTypeReturn.setMerchantNo(vrMaterialType.getMerchantNo());
			findVrMaterialTypeReturn.setMemberNoGm(vrMaterialType.getMemberNoGm());
			findVrMaterialTypeReturn.setMemberNameGm(vrMaterialType.getMemberNameGm());
			findVrMaterialTypeReturn.setTypeName(vrMaterialType.getTypeName());
			findVrMaterialTypeReturn.setTypeCount(vrMaterialType.getTypeCount());
			findVrMaterialTypeReturn.setImgAddr(vrMaterialType.getImgAddr());
			findVrMaterialTypeReturn.setCustomerAttentionRate(vrMaterialType.getCustomerAttentionRate());
			findVrMaterialTypeReturn.setMaterialDimension(vrMaterialType.getMaterialDimension());
			findVrMaterialTypeReturn.setShowIndex(vrMaterialType.getShowIndex());
			findVrMaterialTypeReturn.setCreateId(vrMaterialType.getCreateId());
			findVrMaterialTypeReturn.setCreateDate(vrMaterialType.getCreateDate());
			findVrMaterialTypeReturn.setRemark(vrMaterialType.getRemark());
			findVrMaterialTypeReturn.setRemark2(vrMaterialType.getRemark2());
			findVrMaterialTypeReturn.setRemark3(vrMaterialType.getRemark3());
			findVrMaterialTypeReturn.setRemark4(vrMaterialType.getRemark4());
			
			logger.debug("findVrMaterialType(FindVrMaterialType) - end - return value={}", findVrMaterialTypeReturn); 
			return findVrMaterialTypeReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找VR素材类型信息信息错误！",e);
			throw 
			
			new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_FIND_ERROR,"查找VR素材类型信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindVrMaterialTypePageReturn> findVrMaterialTypePage(
			FindVrMaterialTypePage findVrMaterialTypePage)
			throws TsfaServiceException {
		logger.debug("findVrMaterialTypePage(FindVrMaterialTypePage findVrMaterialTypePage={}) - start", findVrMaterialTypePage); 

		AssertUtils.notNull(findVrMaterialTypePage);
		List<FindVrMaterialTypePageReturn> returnList;
		int count = 0;
		try {
			returnList = vrMaterialTypeDao.findVrMaterialTypePage(findVrMaterialTypePage);
			count = vrMaterialTypeDao.findVrMaterialTypePageCount(findVrMaterialTypePage);
		}  catch (Exception e) {
			logger.error("VR素材类型信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_FIND_PAGE_ERROR,"VR素材类型信息不存在错误.！",e);
		}
		Page<FindVrMaterialTypePageReturn> returnPage = new Page<FindVrMaterialTypePageReturn>(returnList, count, findVrMaterialTypePage);

		 logger.debug("findVrMaterialTypePage(FindVrMaterialTypePage) - end - return value={}", returnList); 
		return  returnPage;
	}
	


	@Override
	public List<FindVrMaterialTypeApiReturn>  findVrMaterialTypeReturnList(
			FindVrMaterialType findVrMaterialType) throws TsfaServiceException {
		List<FindVrMaterialTypeReturn> list;  
		List<FindVrMaterialTypeApiReturn>  returnList = new ArrayList<FindVrMaterialTypeApiReturn>(); ;
		try {
			list = vrMaterialTypeDao.findVrMaterialTypeReturnList(findVrMaterialType);
            for(FindVrMaterialTypeReturn findVrMaterialTypeReturn :list){
            	FindVrMaterialTypeApiReturn apiReturn =new FindVrMaterialTypeApiReturn();
            	FindVrMaterialTypeCategory findVrMaterialTypeCategory = new FindVrMaterialTypeCategory();
            	findVrMaterialTypeCategory.setTypeCode(findVrMaterialTypeReturn.getCode());
            	List<FindVrMaterialTypeCategoryReturn> typeCategoryReturns=vrMaterialTypeCategoryService.findVrMaterialTypeCategoryReturnList(findVrMaterialTypeCategory);
            	apiReturn.setCode(findVrMaterialTypeReturn.getCode());
            	apiReturn.setTypeName(findVrMaterialTypeReturn.getTypeName());
            	if(typeCategoryReturns.size()>0){
            		apiReturn.setList(typeCategoryReturns);
            	}
            	returnList.add(apiReturn);
            }
            logger.debug("findVrMaterialTypeReturnList(FindVrMaterialType) - end - return value={}", returnList); 
		} catch (Exception e) {
			logger.error("查找VR素材类型信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_FIND_ERROR,"查找VR素材类型信息信息错误！",e);
		}
		return returnList;
	}


	@Override
	public List<FindVrMaterialTypeReturn> findVrMaterialTypeList(
			FindVrMaterialType findVrMaterialType) throws TsfaServiceException {
		List<FindVrMaterialTypeReturn> list;
		try {
			list =vrMaterialTypeDao.findVrMaterialTypeReturnList(findVrMaterialType);
		} catch (Exception e) {
			logger.error("查找VR素材类型信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_FIND_ERROR,"查找VR素材类型信息信息错误！",e);
		}
		return list;
	} 
	
	@Override
	public List<Integer> findVrMaterialTypeShowIndexList(FindVrMaterialType findVrMaterialType) throws TsfaServiceException {
		List<Integer> showIndexList=Lists.newArrayList();
		try {
			showIndexList =vrMaterialTypeDao.findVrMaterialTypeShowIndexList(findVrMaterialType);
		} catch (Exception e) {
			logger.error("查找VR素材类型显示序号信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_FIND_ERROR,"查找VR素材类型显示序号信息错误！",e);
		}
		return showIndexList;
	} 


}
