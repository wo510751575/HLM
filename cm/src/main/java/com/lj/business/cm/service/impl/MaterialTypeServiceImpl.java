package com.lj.business.cm.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IMaterialDao;
import com.lj.business.cm.dao.IMaterialTypeDao;
import com.lj.business.cm.domain.MaterialType;
import com.lj.business.cm.dto.AddMaterialType;
import com.lj.business.cm.dto.AddMaterialTypeReturn;
import com.lj.business.cm.dto.CountMaterialCommenDto;
import com.lj.business.cm.dto.CountMaterialDto;
import com.lj.business.cm.dto.DelMaterialType;
import com.lj.business.cm.dto.DelMaterialTypeReturn;
import com.lj.business.cm.dto.FindMaterialType;
import com.lj.business.cm.dto.FindMaterialTypePage;
import com.lj.business.cm.dto.FindMaterialTypePageReturn;
import com.lj.business.cm.dto.FindMaterialTypeReturn;
import com.lj.business.cm.dto.FindMaterialTypesApp;
import com.lj.business.cm.dto.FindMaterialTypesAppReturn;
import com.lj.business.cm.dto.UpdateMaterial;
import com.lj.business.cm.dto.UpdateMaterialType;
import com.lj.business.cm.dto.UpdateMaterialTypeReturn;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterial;
import com.lj.business.cm.service.IGuidIntroduceMaterialService;
import com.lj.business.cm.service.IMaterialCommenService;
import com.lj.business.cm.service.IMaterialService;
import com.lj.business.cm.service.IMaterialTypeService;
import com.lj.business.member.service.IGuidMemberService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 罗书明
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class MaterialTypeServiceImpl implements IMaterialTypeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MaterialTypeServiceImpl.class);
	

	/** The material type dao. */
	@Resource
	private IMaterialTypeDao materialTypeDao;
	
	@Resource
	private IMaterialDao materialDao;
	
	/** The i guid member service. */
	@Resource
	private IGuidMemberService iGuidMemberService;
	
	/** The material service. */
	@Resource
	private IMaterialService materialService;
	
	/** The material commen service. */
	@Resource
	private IMaterialCommenService materialCommenService;
	
	/** The guid introduce service. */
	@Resource
	private IGuidIntroduceMaterialService guidIntroduceMaterialService;
	
	/**
	 * 方法说明：添加素材类型表信息.
	 *
	 * @param addMaterialType the add material type
	 * @return the adds the material type return
	 * @throws TsfaServiceException the tsfa service exception
	 */
	@Override
	public AddMaterialTypeReturn addMaterialType(AddMaterialType addMaterialType) throws TsfaServiceException {
		logger.debug("addMaterialType(AddMaterialType addMaterialType={}) - start", addMaterialType);

		AssertUtils.notNull(addMaterialType);
		try {
			
			MaterialType materialType = new MaterialType();
			//add数据录入
			materialType.setCode(GUID.getPreUUID());
			materialType.setMerchantNo(addMaterialType.getMerchantNo());
			materialType.setMemberNoGm(addMaterialType.getMemberNoGm());
			materialType.setMemberNameGm(addMaterialType.getMemberNameGm());
			materialType.setTypeName(addMaterialType.getTypeName());
			materialType.setCreateId(addMaterialType.getCreateId());
			materialType.setRemark(addMaterialType.getRemark());
			materialType.setCreateDate(new Date());
			materialType.setTypeCount(0);
			materialType.setImgAddr(addMaterialType.getImgAddr());
			materialType.setCustomerAttentionRate(addMaterialType.getCustomerAttentionRate());
			
			materialTypeDao.insertSelective(materialType);
			AddMaterialTypeReturn addMaterialTypeReturn = new AddMaterialTypeReturn();
			addMaterialTypeReturn.setCode(materialType.getCode());
			logger.debug("addMaterialType(AddMaterialType) - end - return value={}", addMaterialTypeReturn); 
			return addMaterialTypeReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增素材类型表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_ADD_ERROR, "新增素材类型表信息错误！", e);
		}
	}
	
	/**
	 * 方法说明：删除素材类型表信息.
	 *
	 * @param delMaterialType the del material type
	 * @return the del material type return
	 * @throws TsfaServiceException the tsfa service exception
	 */
	@Override
	public DelMaterialTypeReturn delMaterialType(
			DelMaterialType delMaterialType) throws TsfaServiceException {
		logger.debug("delMaterialType(DelMaterialType delMaterialType={}) - start", delMaterialType); 

		AssertUtils.notNull(delMaterialType);
		AssertUtils.notNull(delMaterialType.getId(),"ID不能为空！");
		try {
			materialTypeDao.deleteByPrimaryKey(delMaterialType.getId());
			DelMaterialTypeReturn delMaterialTypeReturn  = new DelMaterialTypeReturn();

			logger.debug("delMaterialType(DelMaterialType) - end - return value={}", delMaterialTypeReturn); 
			return delMaterialTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除素材类型表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_DEL_ERROR,"删除素材类型表信息错误！",e);

		}
	}
	
	/**
	 * 方法说明：更新添加素材类型表信息.
	 *
	 * @param updateMaterialType the update material type
	 * @return the update material type return
	 * @throws TsfaServiceException the tsfa service exception
	 */
	@Override
	public UpdateMaterialTypeReturn updateMaterialType(UpdateMaterialType updateMaterialType,FindMaterialTypeReturn findMaterialTypeReturn)
			throws TsfaServiceException {
		logger.debug("updateMaterialType(UpdateMaterialType updateMaterialType={}) - start", updateMaterialType); 

		AssertUtils.notNull(updateMaterialType);
		AssertUtils.notNullAndEmpty(updateMaterialType.getCode(),"ID不能为空");
		try {
			MaterialType materialType = new MaterialType();
			//update数据录入
			materialType.setCode(updateMaterialType.getCode());
			materialType.setMerchantNo(updateMaterialType.getMerchantNo());
			materialType.setMemberNoGm(updateMaterialType.getMemberNoGm());
			materialType.setMemberNameGm(updateMaterialType.getMemberNameGm());
			materialType.setTypeName(updateMaterialType.getTypeName());
			materialType.setCreateId(updateMaterialType.getCreateId());
			materialType.setRemark(updateMaterialType.getRemark());
			materialType.setCreateDate(updateMaterialType.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(materialTypeDao.updateByPrimaryKeySelective(materialType));
			UpdateMaterialTypeReturn updateMaterialTypeReturn = new UpdateMaterialTypeReturn();
			
			if(StringUtils.isNotEmpty(updateMaterialType.getTypeName())&&!updateMaterialType.getTypeName().equals(findMaterialTypeReturn.getTypeName())){
				UpdateMaterial updateMaterial = new UpdateMaterial();
				updateMaterial.setMaterialTypeCode(updateMaterialType.getCode());
				updateMaterial.setMaterialTypeName(updateMaterialType.getTypeName());
				materialDao.updateTypeName(updateMaterial);
			}
			
			

			logger.debug("updateMaterialType(UpdateMaterialType) - end - return value={}", updateMaterialTypeReturn); 
			return updateMaterialTypeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("素材类型表信息更新错误！",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_UPDATE_ERROR,"素材类型表信息更新错误！",e);
		}
	}

	/**
	 * 方法说明：查询素材类型表信息.
	 *
	 * @param findMaterialType
	 *            the find material type
	 * @return the find material type return
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 */
	@Override
	public FindMaterialTypeReturn findMaterialType(FindMaterialType findMaterialType) throws TsfaServiceException {
		logger.debug("findMaterialType(FindMaterialType findMaterialType={}) - start", findMaterialType); 

		AssertUtils.notNull(findMaterialType);
		AssertUtils.notAllNull(findMaterialType.getCode(), "MemberNoGm不能为空");
		try {
			MaterialType materialType = materialTypeDao.selectByPrimaryKey(findMaterialType.getCode());
			if (materialType == null) {
				throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_NOT_EXIST_ERROR, "素材类型表信息不存在");
			}
			FindMaterialTypeReturn findMaterialTypeReturn = new FindMaterialTypeReturn();
			// find数据录入
			findMaterialTypeReturn.setCode(materialType.getCode());
			findMaterialTypeReturn.setMerchantNo(materialType.getMerchantNo());
			findMaterialTypeReturn.setMemberNoGm(materialType.getMemberNoGm());
			findMaterialTypeReturn.setMemberNameGm(materialType.getMemberNameGm());
			findMaterialTypeReturn.setTypeName(materialType.getTypeName());
			findMaterialTypeReturn.setRemark(materialType.getRemark());
			findMaterialTypeReturn.setCreateId(materialType.getCreateId());

			// findMaterialTypeReturn.setCreateDate(materialType.getCreateDate());

			logger.debug("findMaterialType(FindMaterialType) - end - return value={}", findMaterialTypeReturn); 
			return findMaterialTypeReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找素材类型表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_FIND_ERROR, "查找素材类型表信息错误！", e);
		}
	}

	/**
	 * 方法说明：分页查询素材类型表信息.
	 *
	 * @param findMaterialTypePage
	 *            the find material type page
	 * @return the page< find material type page return>
	 * @throws TsfaServiceException
	 *             the tsfa service exception
	 */
	@Override
	public Page<FindMaterialTypePageReturn> findMaterialTypePage(FindMaterialTypePage findMaterialTypePage)
			throws TsfaServiceException {
		logger.debug("findMaterialTypePage(FindMaterialTypePage findMaterialTypePage={}) - start", findMaterialTypePage); 

		AssertUtils.notNull(findMaterialTypePage);
		List<FindMaterialTypePageReturn> returnList;
		int count = 0;
		try {
			returnList = materialTypeDao.findMaterialTypePage(findMaterialTypePage);
			count = materialTypeDao.findMaterialTypePageCount(findMaterialTypePage);
		} catch (Exception e) {
			logger.error("素材类型表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_FIND_PAGE_ERROR, "素材类型表信息分页查询错误.！", e);
		}
		Page<FindMaterialTypePageReturn> returnPage = new Page<FindMaterialTypePageReturn>(returnList, count,
				findMaterialTypePage);

		logger.debug("findMaterialTypePage(FindMaterialTypePage) - end - return value={}", returnPage); 
		return returnPage;
	}


	/* (non-Javadoc)
	 * @see com.lj.business.cm.service.IMaterialTypeService#findMaterialTypes(com.lj.business.cm.dto.FindMaterialTypePage)
	 */
	@Override
	public List<FindMaterialTypePageReturn> findMaterialTypes(
			FindMaterialTypePage findMaterialTypePage) {

		AssertUtils.notNull(findMaterialTypePage);
		List<FindMaterialTypePageReturn> returnList=null;
		try {
			returnList = materialTypeDao.findMaterialTypePage(findMaterialTypePage);
		}  catch (Exception e) {
			logger.error("素材类型表信息分页查询错误",e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_FIND_PAGE_ERROR,"素材类型表信息分页查询错误.！",e);
		}
		return  returnList;
	}

	
	@Override
	public List<FindMaterialTypesAppReturn> findMaterialTypesApp(FindMaterialTypesApp findMaterialTypesApp) {
		logger.debug("findMaterialTypesApp(FindMaterialTypesApp findMaterialTypesApp={}) - start", findMaterialTypesApp); 

		AssertUtils.notNull(findMaterialTypesApp);
		AssertUtils.notNullAndEmpty(findMaterialTypesApp.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findMaterialTypesApp.getMemberNoGm(), "导购编号不能为空");
		try {
			List<FindMaterialTypesAppReturn> list = materialTypeDao.findMaterialTypesApp(findMaterialTypesApp);
			for (FindMaterialTypesAppReturn findMaterialTypesAppReturn : list) {
				findMaterialTypesAppReturn.setAttention("90%");
				findMaterialTypesAppReturn.setMaterialType("CHAN_PIN_PING_PAI");
			}
			logger.debug("findMaterialTypesApp(FindMaterialTypesApp) - end - return value={}", list); 
			return list;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找素材类型表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_FIND_ERROR, "查找素材类型表信息错误！", e);
		}

	}

	@Override
	public int incrementTypeCountByPrimaryKey(String code, Integer increment) {
		logger.debug("incrementTypeCountByPrimaryKey(increment={}) - start", increment);
		if (increment == null || increment <= 0) {
			increment = 1;
		}
		return materialTypeDao.incrementTypeCountByPrimaryKey(code, increment);
	}

	@Override
	public int decrementTypeCountByPrimaryKey(String code, Integer decrement) {
		logger.debug("decrementTypeCountByPrimaryKey(increment={}) - start", decrement);
		if (decrement == null || decrement <= 0) {
			decrement = 1;
		}
		return materialTypeDao.decrementTypeCountByPrimaryKey(code, decrement);
	}

	@Override
	public Page<FindMaterialTypePageReturn> findMaterialTypeForMemberPage(FindMaterialTypePage findMaterialTypePage)
			throws TsfaServiceException {
		logger.debug("findMaterialTypePage(findMaterialTypeForMemberPage={}) - start", findMaterialTypePage);

		AssertUtils.notNull(findMaterialTypePage);
		List<FindMaterialTypePageReturn> returnList;
		int count = 0;
		try {
			returnList = materialTypeDao.findMaterialTypeForMemberPage(findMaterialTypePage);
			for (FindMaterialTypePageReturn findMaterialTypePageReturn : returnList) {
				CountMaterialDto countMaterialDto = new CountMaterialDto();
				countMaterialDto.setMemberNoGm(findMaterialTypePage.getMemberNoGm());
				countMaterialDto.setMaterialTypeCode(findMaterialTypePageReturn.getCode());
				int materialCount = materialService.countMaterialSelective(countMaterialDto);
				
				CountMaterialCommenDto countMaterialCommenDto = new CountMaterialCommenDto();
//				countMaterialCommenDto.setShopNo(findMaterialTypePage.getShopNo());
				countMaterialCommenDto.setShopType(findMaterialTypePage.getShopType());
				countMaterialCommenDto.setMerchantNo(findMaterialTypePage.getMerchantNo());
				countMaterialCommenDto.setMaterialTypeCode(findMaterialTypePageReturn.getCode());
				int materialCommenCount = materialCommenService.countMaterialCommenSelective(countMaterialCommenDto);
				
				findMaterialTypePageReturn.setTypeCount(materialCount + materialCommenCount);
			}
			
			//导购个人素材
			FindMaterialTypePageReturn findMaterialTypePageReturn = getGuidIntroduceMaterial(findMaterialTypePage);
			returnList.add(findMaterialTypePageReturn);
			count = materialTypeDao.findMaterialTypeForMemberPageCount(findMaterialTypePage) + 1;
		} catch (Exception e) {
			logger.error("素材类型表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_FIND_PAGE_ERROR, "素材类型表信息分页查询错误.！", e);
		}
		Page<FindMaterialTypePageReturn> returnPage = new Page<>(returnList, count, findMaterialTypePage);

		logger.debug("findMaterialTypeForMemberPage(findMaterialTypeForMemberPage) - end - return value={}", returnPage);
		return returnPage;
	}

	private FindMaterialTypePageReturn getGuidIntroduceMaterial(
			FindMaterialTypePage findMaterialTypePage) {
		FindMaterialTypePageReturn findMaterialTypePageReturn = new FindMaterialTypePageReturn();
		findMaterialTypePageReturn.setMerchantNo(findMaterialTypePage.getMerchantNo());
		findMaterialTypePageReturn.setMemberNoGm(findMaterialTypePage.getMemberNoGm());
		findMaterialTypePageReturn.setMemberNameGm(findMaterialTypePage.getMemberNameGm());
		findMaterialTypePageReturn.setCode("guid_introduce");
		findMaterialTypePageReturn.setTypeName("个人介绍");
		FindGuidIntroduceMaterial findGuidIntroduceMaterial = new FindGuidIntroduceMaterial();
		findGuidIntroduceMaterial.setMerchantNo(findMaterialTypePage.getMerchantNo());
		findGuidIntroduceMaterial.setMemberNoGm(findMaterialTypePage.getMemberNoGm());
		int guidIntroduceCount = guidIntroduceMaterialService.findCountMaterial(findGuidIntroduceMaterial);
		findMaterialTypePageReturn.setTypeCount(guidIntroduceCount);
		return findMaterialTypePageReturn;
	}

	@Override
	public List<FindMaterialTypePageReturn> findMateriaTypeList(
			FindMaterialTypePage findMaterialTypePage)throws TsfaServiceException {
		AssertUtils.notNull(findMaterialTypePage);
		List<FindMaterialTypePageReturn> list=null;
		try {
			list=materialTypeDao.findMateriaTypeList(findMaterialTypePage);
		} catch (Exception e) {
			logger.error("素材类型表信息分页查询错误", e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TYPE_FIND_PAGE_ERROR, "素材类型表信息分页查询错误.！", e);
		}
		
		return list;
	}


}
