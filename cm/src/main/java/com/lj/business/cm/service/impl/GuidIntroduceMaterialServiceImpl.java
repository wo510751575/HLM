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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IGuidIntroduceMaterialDao;
import com.lj.business.cm.domain.GuidIntroduceMaterial;
import com.lj.business.cm.domain.IntroduceMaterialProduct;
import com.lj.business.cm.dto.guidIntroduceMaterial.AddGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.DelGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterial;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPage;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialPageReturn;
import com.lj.business.cm.dto.guidIntroduceMaterial.FindGuidIntroduceMaterialReturn;
import com.lj.business.cm.dto.guidIntroduceMaterial.UpdateGuidIntroduceMaterial;
import com.lj.business.cm.dto.introduceMaterialProduct.AddIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.DelIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProduct;
import com.lj.business.cm.service.IGuidIntroduceMaterialService;
import com.lj.business.cm.service.IIntroduceMaterialProductService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class GuidIntroduceMaterialServiceImpl implements IGuidIntroduceMaterialService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(GuidIntroduceMaterialServiceImpl.class);
	

	@Resource
	private IGuidIntroduceMaterialDao guidIntroduceMaterialDao;
	
	@Resource
	private IIntroduceMaterialProductService introduceMaterialProductService;
	
	
	@Override
	public void addGuidIntroduceMaterial(
			AddGuidIntroduceMaterial addGuidIntroduceMaterial) throws TsfaServiceException {
		logger.debug("addGuidIntroduceMaterial(AddGuidIntroduceMaterial addGuidIntroduceMaterial={}) - start", addGuidIntroduceMaterial); 

		AssertUtils.notNull(addGuidIntroduceMaterial);
		AssertUtils.notNull(addGuidIntroduceMaterial.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNull(addGuidIntroduceMaterial.getMemberNoGm(),"导购号不能为空！");
//		AssertUtils.notNull(addGuidIntroduceMaterial.getShopNo(),"终端号不能为空！");
		try {
			GuidIntroduceMaterial guidIntroduceMaterial = new GuidIntroduceMaterial();
			//add数据录入
			guidIntroduceMaterial.setCode(GUID.getPreUUID());
			guidIntroduceMaterial.setMerchantNo(addGuidIntroduceMaterial.getMerchantNo());
//			guidIntroduceMaterial.setShopNo(addGuidIntroduceMaterial.getShopNo());
//			guidIntroduceMaterial.setShopName(addGuidIntroduceMaterial.getShopName());
			guidIntroduceMaterial.setMemberNoGm(addGuidIntroduceMaterial.getMemberNoGm());
			guidIntroduceMaterial.setMemberNameGm(addGuidIntroduceMaterial.getMemberNameGm());
			guidIntroduceMaterial.setHeadAddress(addGuidIntroduceMaterial.getHeadAddress());
			guidIntroduceMaterial.setTemplateNo(addGuidIntroduceMaterial.getTemplateNo());
			guidIntroduceMaterial.setName(addGuidIntroduceMaterial.getName());
			guidIntroduceMaterial.setPosition(addGuidIntroduceMaterial.getPosition());
			guidIntroduceMaterial.setServeLevel(5D);
			guidIntroduceMaterial.setProfessionalLevel(5D);
			guidIntroduceMaterial.setMobile(addGuidIntroduceMaterial.getMobile());
			guidIntroduceMaterial.setCompanyName(addGuidIntroduceMaterial.getCompanyName());
			guidIntroduceMaterial.setCompanyAddress(addGuidIntroduceMaterial.getCompanyAddress());
			guidIntroduceMaterial.setSlogan(addGuidIntroduceMaterial.getSlogan());
			guidIntroduceMaterial.setRemark(addGuidIntroduceMaterial.getRemark());
			guidIntroduceMaterial.setRemark2(addGuidIntroduceMaterial.getRemark2());
			guidIntroduceMaterial.setRemark3(addGuidIntroduceMaterial.getRemark3());
			guidIntroduceMaterial.setRemark4(addGuidIntroduceMaterial.getRemark4());
			guidIntroduceMaterial.setCreateDate(new Date());
			
			if (StringUtils.isNotBlank(addGuidIntroduceMaterial.getBomName())) {
				String[] bomNames = addGuidIntroduceMaterial.getBomName().split(",");
				String[] bomAddresses = addGuidIntroduceMaterial.getBomAddress().split(",");
				for (int i = 0; i < bomNames.length; i++) {
					AddIntroduceMaterialProduct addIntroduceMaterialProduct = new AddIntroduceMaterialProduct();
					addIntroduceMaterialProduct.setMaterialCode(guidIntroduceMaterial.getCode());
					addIntroduceMaterialProduct.setBomName(bomNames[i]);
					addIntroduceMaterialProduct.setBomAddress(bomAddresses[i]);
					addIntroduceMaterialProduct.setSort(i + "");
					addIntroduceMaterialProduct.setCreateId(addGuidIntroduceMaterial.getMemberNoGm());
					addIntroduceMaterialProduct.setCreateDate(new Date());
					introduceMaterialProductService.addIntroduceMaterialProduct(addIntroduceMaterialProduct);
				}
			}
			
			guidIntroduceMaterialDao.insert(guidIntroduceMaterial);
			logger.debug("addGuidIntroduceMaterial(AddGuidIntroduceMaterial) - end - return");
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购个人介绍素材表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_INTRODUCE_MATERIAL_ADD_ERROR,"新增导购个人介绍素材表信息错误！",e);
		}
	}
	
	
	@Override
	public void delGuidIntroduceMaterial(
			DelGuidIntroduceMaterial delGuidIntroduceMaterial) throws TsfaServiceException {
		logger.debug("delGuidIntroduceMaterial(DelGuidIntroduceMaterial delGuidIntroduceMaterial={}) - start", delGuidIntroduceMaterial); 

		AssertUtils.notNull(delGuidIntroduceMaterial);
		AssertUtils.notNull(delGuidIntroduceMaterial.getCode(),"Code不能为空！");
		try {
			guidIntroduceMaterialDao.deleteByPrimaryKey(delGuidIntroduceMaterial.getCode());
			//删除关联产品
			DelIntroduceMaterialProduct delIntroduceMaterialProduct = new DelIntroduceMaterialProduct();
			delIntroduceMaterialProduct.setMaterialCode(delGuidIntroduceMaterial.getCode());
			introduceMaterialProductService.delIntroduceMaterialProductByMaterCode(delIntroduceMaterialProduct);
			logger.debug("delGuidIntroduceMaterial(DelGuidIntroduceMaterial) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除导购个人介绍素材表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_INTRODUCE_MATERIAL_DEL_ERROR,"删除导购个人介绍素材表信息错误！",e);

		}
	}

	@Override
	public void updateGuidIntroduceMaterial(
			UpdateGuidIntroduceMaterial updateGuidIntroduceMaterial)
			throws TsfaServiceException {
		logger.debug("updateGuidIntroduceMaterial(UpdateGuidIntroduceMaterial updateGuidIntroduceMaterial={}) - start", updateGuidIntroduceMaterial); 

		AssertUtils.notNull(updateGuidIntroduceMaterial);
		AssertUtils.notNullAndEmpty(updateGuidIntroduceMaterial.getCode(),"Code不能为空");
		try {
			GuidIntroduceMaterial guidIntroduceMaterial = new GuidIntroduceMaterial();
			//update数据录入
			guidIntroduceMaterial.setCode(updateGuidIntroduceMaterial.getCode());
			guidIntroduceMaterial.setMerchantNo(updateGuidIntroduceMaterial.getMerchantNo());
//			guidIntroduceMaterial.setShopNo(updateGuidIntroduceMaterial.getShopNo());
//			guidIntroduceMaterial.setShopName(updateGuidIntroduceMaterial.getShopName());
			guidIntroduceMaterial.setMemberNoGm(updateGuidIntroduceMaterial.getMemberNoGm());
			guidIntroduceMaterial.setMemberNameGm(updateGuidIntroduceMaterial.getMemberNameGm());
			guidIntroduceMaterial.setHeadAddress(updateGuidIntroduceMaterial.getHeadAddress());
			guidIntroduceMaterial.setTemplateNo(updateGuidIntroduceMaterial.getTemplateNo());
			guidIntroduceMaterial.setName(updateGuidIntroduceMaterial.getName());
			guidIntroduceMaterial.setPosition(updateGuidIntroduceMaterial.getPosition());
			guidIntroduceMaterial.setServeLevel(updateGuidIntroduceMaterial.getServeLevel());
			guidIntroduceMaterial.setProfessionalLevel(updateGuidIntroduceMaterial.getProfessionalLevel());
			guidIntroduceMaterial.setMobile(updateGuidIntroduceMaterial.getMobile());
			guidIntroduceMaterial.setCompanyName(updateGuidIntroduceMaterial.getCompanyName());
			guidIntroduceMaterial.setCompanyAddress(updateGuidIntroduceMaterial.getCompanyAddress());
			guidIntroduceMaterial.setSlogan(updateGuidIntroduceMaterial.getSlogan());
			guidIntroduceMaterial.setRemark(updateGuidIntroduceMaterial.getRemark());
			guidIntroduceMaterial.setRemark2(updateGuidIntroduceMaterial.getRemark2());
			guidIntroduceMaterial.setRemark3(updateGuidIntroduceMaterial.getRemark3());
			guidIntroduceMaterial.setRemark4(updateGuidIntroduceMaterial.getRemark4());
			AssertUtils.notUpdateMoreThanOne(guidIntroduceMaterialDao.updateByPrimaryKeySelective(guidIntroduceMaterial));
			//删除关联产品
			DelIntroduceMaterialProduct delIntroduceMaterialProduct = new DelIntroduceMaterialProduct();
			delIntroduceMaterialProduct.setMaterialCode(guidIntroduceMaterial.getCode());
			introduceMaterialProductService.delIntroduceMaterialProductByMaterCode(delIntroduceMaterialProduct);
			//增加修改关联产品
			if (StringUtils.isNotBlank(updateGuidIntroduceMaterial.getBomName())) {
				String[] bomNames = updateGuidIntroduceMaterial.getBomName().split(",");
				String[] bomAddresses = updateGuidIntroduceMaterial.getBomAddress().split(",");
				for (int i = 0; i < bomNames.length; i++) {
					AddIntroduceMaterialProduct addIntroduceMaterialProduct = new AddIntroduceMaterialProduct();
					addIntroduceMaterialProduct.setMaterialCode(guidIntroduceMaterial.getCode());
					addIntroduceMaterialProduct.setBomName(bomNames[i]);
					addIntroduceMaterialProduct.setBomAddress(bomAddresses[i]);
					addIntroduceMaterialProduct.setSort(i + "");
					addIntroduceMaterialProduct.setCreateId(updateGuidIntroduceMaterial.getMemberNoGm());
					addIntroduceMaterialProduct.setCreateDate(new Date());
					introduceMaterialProductService.addIntroduceMaterialProduct(addIntroduceMaterialProduct);
				}
			}
			
			logger.debug("updateGuidIntroduceMaterial(UpdateGuidIntroduceMaterial) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购个人介绍素材表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_INTRODUCE_MATERIAL_UPDATE_ERROR,"导购个人介绍素材表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindGuidIntroduceMaterialReturn findGuidIntroduceMaterial(
			FindGuidIntroduceMaterial findGuidIntroduceMaterial) throws TsfaServiceException {
		logger.debug("findGuidIntroduceMaterial(FindGuidIntroduceMaterial findGuidIntroduceMaterial={}) - start", findGuidIntroduceMaterial); 

		AssertUtils.notNull(findGuidIntroduceMaterial);
		AssertUtils.notAllNull(findGuidIntroduceMaterial.getMemberNoGm(),"导购号不能为空");
		try {
			GuidIntroduceMaterial guidIntroduceMaterial = guidIntroduceMaterialDao.selectByPrimaryKey(findGuidIntroduceMaterial.getCode());
			if(guidIntroduceMaterial == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.GUID_INTRODUCE_MATERIAL_NOT_EXIST_ERROR,"导购个人介绍素材表信息不存在");
			}
			FindGuidIntroduceMaterialReturn findGuidIntroduceMaterialReturn = new FindGuidIntroduceMaterialReturn();
			//find数据录入
			findGuidIntroduceMaterialReturn.setCode(guidIntroduceMaterial.getCode());
			findGuidIntroduceMaterialReturn.setMerchantNo(guidIntroduceMaterial.getMerchantNo());
//			findGuidIntroduceMaterialReturn.setShopNo(guidIntroduceMaterial.getShopNo());
//			findGuidIntroduceMaterialReturn.setShopName(guidIntroduceMaterial.getShopName());
			findGuidIntroduceMaterialReturn.setMemberNoGm(guidIntroduceMaterial.getMemberNoGm());
			findGuidIntroduceMaterialReturn.setMemberNameGm(guidIntroduceMaterial.getMemberNameGm());
			findGuidIntroduceMaterialReturn.setHeadAddress(guidIntroduceMaterial.getHeadAddress());
			findGuidIntroduceMaterialReturn.setTemplateNo(guidIntroduceMaterial.getTemplateNo());
			findGuidIntroduceMaterialReturn.setName(guidIntroduceMaterial.getName());
			findGuidIntroduceMaterialReturn.setPosition(guidIntroduceMaterial.getPosition());
			findGuidIntroduceMaterialReturn.setServeLevel(guidIntroduceMaterial.getServeLevel());
			findGuidIntroduceMaterialReturn.setProfessionalLevel(guidIntroduceMaterial.getProfessionalLevel());
			findGuidIntroduceMaterialReturn.setMobile(guidIntroduceMaterial.getMobile());
			findGuidIntroduceMaterialReturn.setCompanyName(guidIntroduceMaterial.getCompanyName());
			findGuidIntroduceMaterialReturn.setCompanyAddress(guidIntroduceMaterial.getCompanyAddress());
			findGuidIntroduceMaterialReturn.setSlogan(guidIntroduceMaterial.getSlogan());
			findGuidIntroduceMaterialReturn.setRemark(guidIntroduceMaterial.getRemark());
			findGuidIntroduceMaterialReturn.setRemark2(guidIntroduceMaterial.getRemark2());
			findGuidIntroduceMaterialReturn.setRemark3(guidIntroduceMaterial.getRemark3());
			findGuidIntroduceMaterialReturn.setRemark4(guidIntroduceMaterial.getRemark4());
			findGuidIntroduceMaterialReturn.setCreateDate(guidIntroduceMaterial.getCreateDate());
			
			//设置产品名称和地址
			FindIntroduceMaterialProduct findIntroduceMaterialProduct = new FindIntroduceMaterialProduct();
			findIntroduceMaterialProduct.setMaterialCode(findGuidIntroduceMaterialReturn.getCode());
			
			List<IntroduceMaterialProduct> introduceMaterialProducts = introduceMaterialProductService.findIntroduceMaterialProductByMaterCode(findIntroduceMaterialProduct);
			StringBuilder bomNames = new StringBuilder(200);
			StringBuilder bomAddresses = new StringBuilder(1000);
			for (IntroduceMaterialProduct introduceMaterialProduct : introduceMaterialProducts) {
				if (bomNames.length() > 0) {
					bomNames.append(",");
					bomAddresses.append(",");
				}
				bomNames.append(introduceMaterialProduct.getBomName());
				bomAddresses.append(introduceMaterialProduct.getBomAddress());
			}
			if (bomNames.length() > 0) {
				findGuidIntroduceMaterialReturn.setBomName(bomNames.toString());
				findGuidIntroduceMaterialReturn.setBomAddress(bomAddresses.toString());
			}
			logger.debug("findGuidIntroduceMaterial(FindGuidIntroduceMaterial) - end - return value={}", findGuidIntroduceMaterialReturn); 
			return findGuidIntroduceMaterialReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购个人介绍素材表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.GUID_INTRODUCE_MATERIAL_FIND_ERROR,"查找导购个人介绍素材表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindGuidIntroduceMaterialPageReturn> findGuidIntroduceMaterialPage(
			FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage)
			throws TsfaServiceException {
		logger.debug("findGuidIntroduceMaterialPage(FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage={}) - start", findGuidIntroduceMaterialPage); 

		AssertUtils.notNull(findGuidIntroduceMaterialPage);
		List<FindGuidIntroduceMaterialPageReturn> returnList;
		int count = 0;
		try {
			returnList = guidIntroduceMaterialDao.findGuidIntroduceMaterialPage(findGuidIntroduceMaterialPage);
			count = guidIntroduceMaterialDao.findGuidIntroduceMaterialPageCount(findGuidIntroduceMaterialPage);
		}  catch (Exception e) {
			logger.error("导购个人介绍素材表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.GUID_INTRODUCE_MATERIAL_FIND_PAGE_ERROR,"导购个人介绍素材表信息不存在错误.！",e);
		}
		Page<FindGuidIntroduceMaterialPageReturn> returnPage = new Page<FindGuidIntroduceMaterialPageReturn>(returnList, count, findGuidIntroduceMaterialPage);

		logger.debug("findGuidIntroduceMaterialPage(FindGuidIntroduceMaterialPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public List<FindGuidIntroduceMaterialPageReturn> findGuidIntroduceMaterialList(
			FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage) {
		logger.debug("findGuidIntroduceMaterialList(FindGuidIntroduceMaterialPage findGuidIntroduceMaterialPage={}) - start", findGuidIntroduceMaterialPage); 
		List<FindGuidIntroduceMaterialPageReturn> returnList = guidIntroduceMaterialDao.findGuidIntroduceMaterialPage(findGuidIntroduceMaterialPage);
		for (FindGuidIntroduceMaterialPageReturn findGuidIntroduceMaterialPageReturn : returnList) {
			FindIntroduceMaterialProduct findIntroduceMaterialProduct = new FindIntroduceMaterialProduct();
			findIntroduceMaterialProduct.setMaterialCode(findGuidIntroduceMaterialPageReturn.getCode());
			List<IntroduceMaterialProduct> introduceMaterialProducts = introduceMaterialProductService.findIntroduceMaterialProductByMaterCode(findIntroduceMaterialProduct);
			StringBuilder bomNames = new StringBuilder(200);
			StringBuilder bomAddresses = new StringBuilder(1000);
			for (IntroduceMaterialProduct introduceMaterialProduct : introduceMaterialProducts) {
				if (bomNames.length() > 0) {
					bomNames.append(",");
					bomAddresses.append(",");
				}
				bomNames.append(introduceMaterialProduct.getBomName());
				bomAddresses.append(introduceMaterialProduct.getBomAddress());
			}
			if (bomNames.length() > 0) {
				findGuidIntroduceMaterialPageReturn.setBomName(bomNames.toString());
				findGuidIntroduceMaterialPageReturn.setBomAddress(bomAddresses.toString());
			}
		}
		logger.debug("findGuidIntroduceMaterialPage(FindGuidIntroduceMaterialPage) - end - return value={}", returnList); 
		return returnList;
	}


	@Override
	public int findCountMaterial(
			FindGuidIntroduceMaterial findGuidIntroduceMaterial) {
		logger.debug("findCountMaterial(FindGuidIntroduceMaterial findGuidIntroduceMaterial={}) - start", findGuidIntroduceMaterial); 
		int count = guidIntroduceMaterialDao.findCountMaterial(findGuidIntroduceMaterial);
		logger.debug("findCountMaterial(FindGuidIntroduceMaterial) - end - return value={}", count); 
		return count;
	} 


}
