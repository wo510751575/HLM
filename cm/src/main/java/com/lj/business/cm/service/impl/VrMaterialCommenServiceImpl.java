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

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IVrMaterialCommenDao;
import com.lj.business.cm.domain.VrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.AddVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.AddVrMaterialCommenReturn;
import com.lj.business.cm.dto.vrMaterialCommen.DelVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.DelVrMaterialCommenReturn;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPage;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPageReturn;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenReturn;
import com.lj.business.cm.dto.vrMaterialCommen.UpdateVrMaterialCommen;
import com.lj.business.cm.dto.vrMaterialCommen.UpdateVrMaterialCommenReturn;
import com.lj.business.cm.emus.ShowChannel;
import com.lj.business.cm.service.IVrMaterialCommenService;

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
public class VrMaterialCommenServiceImpl implements IVrMaterialCommenService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(VrMaterialCommenServiceImpl.class);
	
	@Resource
	private IVrMaterialCommenDao vrMaterialCommenDao;
	
	@Override
	public AddVrMaterialCommenReturn addVrMaterialCommen(
			AddVrMaterialCommen addVrMaterialCommen) throws TsfaServiceException {
		logger.debug("addVrMaterialCommen(AddVrMaterialCommen addVrMaterialCommen={}) - start", addVrMaterialCommen); 

		AssertUtils.notNull(addVrMaterialCommen);
		try {
			VrMaterialCommen vrMaterialCommen = new VrMaterialCommen();
			//add数据录入
			vrMaterialCommen.setCode(addVrMaterialCommen.getCode());
			vrMaterialCommen.setMerchantNo(addVrMaterialCommen.getMerchantNo());
			vrMaterialCommen.setMerchantName(addVrMaterialCommen.getMerchantName());
			vrMaterialCommen.setTitle(addVrMaterialCommen.getTitle());
			vrMaterialCommen.setContent(addVrMaterialCommen.getContent());
			vrMaterialCommen.setBrief(addVrMaterialCommen.getBrief());
			vrMaterialCommen.setImgAddr(addVrMaterialCommen.getImgAddr());
			vrMaterialCommen.setDimensionSt(addVrMaterialCommen.getDimensionSt());
			vrMaterialCommen.setLinkUrl(addVrMaterialCommen.getLinkUrl());
			vrMaterialCommen.setRespondNum(addVrMaterialCommen.getRespondNum());
			if(ShowChannel.PART.name().equals(addVrMaterialCommen.getShowChannel())) {	// 按终端
//				vrMaterialCommen.setShopNo(addVrMaterialCommen.getShopNo());
//				vrMaterialCommen.setShopName(addVrMaterialCommen.getShopName());
			} else if(ShowChannel.CHANNEL.name().equals(addVrMaterialCommen.getShowChannel())) {	// 按销售渠道
				vrMaterialCommen.setShopType(addVrMaterialCommen.getShopType());
			}
			vrMaterialCommen.setShowChannel(addVrMaterialCommen.getShowChannel());
			vrMaterialCommen.setCreateId(addVrMaterialCommen.getCreateId());
			vrMaterialCommen.setCreateDate(new Date());
			vrMaterialCommen.setRemark(addVrMaterialCommen.getRemark());
			vrMaterialCommen.setRemark2(addVrMaterialCommen.getRemark2());
			vrMaterialCommen.setRemark3(addVrMaterialCommen.getRemark3());
			vrMaterialCommen.setRemark4(addVrMaterialCommen.getRemark4());
			vrMaterialCommenDao.insertSelective(vrMaterialCommen);
			AddVrMaterialCommenReturn addVrMaterialCommenReturn = new AddVrMaterialCommenReturn();
			
			logger.debug("addVrMaterialCommen(AddVrMaterialCommen) - end - return value={}", addVrMaterialCommenReturn); 
			return addVrMaterialCommenReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增VR公用素材中心信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_ADD_ERROR,"新增VR公用素材中心信息错误！",e);
		}
	}
	
	
	@Override
	public DelVrMaterialCommenReturn delVrMaterialCommen(
			DelVrMaterialCommen delVrMaterialCommen) throws TsfaServiceException {
		logger.debug("delVrMaterialCommen(DelVrMaterialCommen delVrMaterialCommen={}) - start", delVrMaterialCommen); 

		AssertUtils.notNull(delVrMaterialCommen);
		AssertUtils.notNullAndEmpty(delVrMaterialCommen.getCode(),"CODE不能为空！");
		try {
			vrMaterialCommenDao.deleteByPrimaryKey(delVrMaterialCommen.getCode());
			DelVrMaterialCommenReturn delVrMaterialCommenReturn  = new DelVrMaterialCommenReturn();

			logger.debug("delVrMaterialCommen(DelVrMaterialCommen) - end - return value={}", delVrMaterialCommenReturn); 
			return delVrMaterialCommenReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除VR公用素材中心信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_DEL_ERROR,"删除VR公用素材中心信息错误！",e);

		}
	}

	@Override
	public UpdateVrMaterialCommenReturn updateVrMaterialCommen(
			UpdateVrMaterialCommen updateVrMaterialCommen)
			throws TsfaServiceException {
		logger.debug("updateVrMaterialCommen(UpdateVrMaterialCommen updateVrMaterialCommen={}) - start", updateVrMaterialCommen); 

		AssertUtils.notNull(updateVrMaterialCommen);
		AssertUtils.notNullAndEmpty(updateVrMaterialCommen.getCode(),"CODE不能为空");
		try {
			VrMaterialCommen vrMaterialCommen = new VrMaterialCommen();
			//update数据录入
			vrMaterialCommen.setCode(updateVrMaterialCommen.getCode());
			vrMaterialCommen.setMerchantNo(updateVrMaterialCommen.getMerchantNo());
			vrMaterialCommen.setMerchantName(updateVrMaterialCommen.getMerchantName());
			vrMaterialCommen.setTitle(updateVrMaterialCommen.getTitle());
			vrMaterialCommen.setContent(updateVrMaterialCommen.getContent());
			vrMaterialCommen.setBrief(updateVrMaterialCommen.getBrief());
			vrMaterialCommen.setImgAddr(updateVrMaterialCommen.getImgAddr());
			vrMaterialCommen.setDimensionSt(updateVrMaterialCommen.getDimensionSt());
			vrMaterialCommen.setLinkUrl(updateVrMaterialCommen.getLinkUrl());
			vrMaterialCommen.setRespondNum(updateVrMaterialCommen.getRespondNum());
			if(StringUtils.isNotEmpty(updateVrMaterialCommen.getShowChannel())) {
				if(ShowChannel.PART.name().equals(updateVrMaterialCommen.getShowChannel())) {	// 按终端
//					vrMaterialCommen.setShopNo(updateVrMaterialCommen.getShopNo());
//					vrMaterialCommen.setShopName(updateVrMaterialCommen.getShopName());
					vrMaterialCommen.setShopType("");
				} else if(ShowChannel.CHANNEL.name().equals(updateVrMaterialCommen.getShowChannel())) {	// 按销售渠道
					vrMaterialCommen.setShopType(updateVrMaterialCommen.getShopType());
//					vrMaterialCommen.setShopNo("");
//					vrMaterialCommen.setShopName("");
				} else {	// 全部终端
//					vrMaterialCommen.setShopNo("");
//					vrMaterialCommen.setShopName("");
					vrMaterialCommen.setShopType("");
				}
			}			
			vrMaterialCommen.setShowChannel(updateVrMaterialCommen.getShowChannel());
			vrMaterialCommen.setCreateId(updateVrMaterialCommen.getCreateId());
			vrMaterialCommen.setCreateDate(updateVrMaterialCommen.getCreateDate());
			vrMaterialCommen.setRemark(updateVrMaterialCommen.getRemark());
			vrMaterialCommen.setRemark2(updateVrMaterialCommen.getRemark2());
			vrMaterialCommen.setRemark3(updateVrMaterialCommen.getRemark3());
			vrMaterialCommen.setRemark4(updateVrMaterialCommen.getRemark4());
			AssertUtils.notUpdateMoreThanOne(vrMaterialCommenDao.updateByPrimaryKeySelective(vrMaterialCommen));
			UpdateVrMaterialCommenReturn updateVrMaterialCommenReturn = new UpdateVrMaterialCommenReturn();

			logger.debug("updateVrMaterialCommen(UpdateVrMaterialCommen) - end - return value={}", updateVrMaterialCommenReturn); 
			return updateVrMaterialCommenReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("VR公用素材中心信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_UPDATE_ERROR,"VR公用素材中心信息更新信息错误！",e);
		}
	}

	@Override
	public FindVrMaterialCommenReturn findVrMaterialCommen(
			FindVrMaterialCommen findVrMaterialCommen) throws TsfaServiceException {
		logger.debug("findVrMaterialCommen(FindVrMaterialCommen findVrMaterialCommen={}) - start", findVrMaterialCommen); 

		AssertUtils.notNull(findVrMaterialCommen);
		AssertUtils.notAllNull(findVrMaterialCommen.getCode(),"CODE不能为空");
		try {
			VrMaterialCommen vrMaterialCommen = vrMaterialCommenDao.selectByPrimaryKey(findVrMaterialCommen.getCode());
			if(vrMaterialCommen == null){
				throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_NOT_EXIST_ERROR,"VR公用素材中心信息不存在");
			}
			FindVrMaterialCommenReturn findVrMaterialCommenReturn = new FindVrMaterialCommenReturn();
			//find数据录入
			findVrMaterialCommenReturn.setCode(vrMaterialCommen.getCode());
			findVrMaterialCommenReturn.setMerchantNo(vrMaterialCommen.getMerchantNo());
			findVrMaterialCommenReturn.setMerchantName(vrMaterialCommen.getMerchantName());
//			findVrMaterialCommenReturn.setShopNo(vrMaterialCommen.getShopNo());
//			findVrMaterialCommenReturn.setShopName(vrMaterialCommen.getShopName());
			findVrMaterialCommenReturn.setTitle(vrMaterialCommen.getTitle());
			findVrMaterialCommenReturn.setContent(vrMaterialCommen.getContent());
			findVrMaterialCommenReturn.setBrief(vrMaterialCommen.getBrief());
			findVrMaterialCommenReturn.setImgAddr(vrMaterialCommen.getImgAddr());
			findVrMaterialCommenReturn.setDimensionSt(vrMaterialCommen.getDimensionSt());
			findVrMaterialCommenReturn.setLinkUrl(vrMaterialCommen.getLinkUrl());
			findVrMaterialCommenReturn.setRespondNum(vrMaterialCommen.getRespondNum());
			findVrMaterialCommenReturn.setShopType(vrMaterialCommen.getShopType());
			findVrMaterialCommenReturn.setShowChannel(vrMaterialCommen.getShowChannel());
			findVrMaterialCommenReturn.setCreateId(vrMaterialCommen.getCreateId());
			findVrMaterialCommenReturn.setCreateDate(vrMaterialCommen.getCreateDate());
			findVrMaterialCommenReturn.setRemark(vrMaterialCommen.getRemark());
			findVrMaterialCommenReturn.setRemark2(vrMaterialCommen.getRemark2());
			findVrMaterialCommenReturn.setRemark3(vrMaterialCommen.getRemark3());
			findVrMaterialCommenReturn.setRemark4(vrMaterialCommen.getRemark4());
			
			logger.debug("findVrMaterialCommen(FindVrMaterialCommen) - end - return value={}", findVrMaterialCommenReturn); 
			return findVrMaterialCommenReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找VR公用素材中心信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_FIND_ERROR,"查找VR公用素材中心信息信息错误！",e);
		}

	}

	
	@Override
	public Page<FindVrMaterialCommenPageReturn> findVrMaterialCommenPage(
			FindVrMaterialCommenPage findVrMaterialCommenPage )
			throws TsfaServiceException {
		logger.debug("findVrMaterialCommenPage(FindVrMaterialCommenPage findVrMaterialCommenPage={}) - start", findVrMaterialCommenPage); 
		AssertUtils.notNull(findVrMaterialCommenPage);
		AssertUtils.notNullAndEmpty(findVrMaterialCommenPage.getMerchantNo(),"商户编号不能为空");
//		AssertUtils.notNullAndEmpty(findVrMaterialCommenPage.getShopNo(),"终端编号不能为空");
		AssertUtils.notAllNull(findVrMaterialCommenPage.getShopType(),findVrMaterialCommenPage.getShopTypes(),"终端类型不能为空");
		List<FindVrMaterialCommenPageReturn> returnList = null;
		int count = 0;
		try {
			count = vrMaterialCommenDao.findVrMaterialCommenPageCount(findVrMaterialCommenPage);
			if(count > 0) {
				returnList = vrMaterialCommenDao.findVrMaterialCommenPage(findVrMaterialCommenPage);
			} else {
				returnList = new ArrayList<>();
			}
		}  catch (Exception e) {
			logger.error("VR公用素材中心信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_FIND_PAGE_ERROR,"VR公用素材中心信息不存在错误.！",e);
		}
		Page<FindVrMaterialCommenPageReturn> returnPage = new Page<FindVrMaterialCommenPageReturn>(returnList, count, findVrMaterialCommenPage);

		logger.debug("findVrMaterialCommenPage(FindVrMaterialCommenPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 

	@Override
	public Page<FindVrMaterialCommenPageReturn> findVrMaterialCommenReturnPage(
			FindVrMaterialCommenPage findVrMaterialCommenPage )
			throws TsfaServiceException {
		logger.debug("findVrMaterialCommenPage(FindVrMaterialCommenPage findVrMaterialCommenPage={}) - start", findVrMaterialCommenPage); 
		AssertUtils.notNull(findVrMaterialCommenPage);
		List<FindVrMaterialCommenPageReturn> returnList;
		int count = 0;
		try {
			returnList = vrMaterialCommenDao.findVrMaterialCommenOmsPage(findVrMaterialCommenPage);
			count = vrMaterialCommenDao.findVrMaterialCommenPageOmsCount(findVrMaterialCommenPage);
		}  catch (Exception e) {
			logger.error("VR公用素材中心信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_FIND_PAGE_ERROR,"VR公用素材中心信息不存在错误.！",e);
		}
		Page<FindVrMaterialCommenPageReturn> returnPage = new Page<FindVrMaterialCommenPageReturn>(returnList, count, findVrMaterialCommenPage);

		logger.debug("findVrMaterialCommenPage(FindVrMaterialCommenPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 
	
}
