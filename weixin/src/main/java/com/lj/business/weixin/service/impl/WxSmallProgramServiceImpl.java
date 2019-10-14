package com.lj.business.weixin.service.impl;

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
import com.lj.business.weixin.constant.ErrorCode;
import com.lj.business.weixin.dao.IWxSmallProgramDao;
import com.lj.business.weixin.domain.WxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.AddWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.AddWxSmallProgramReturn;
import com.lj.business.weixin.dto.smallprogram.DelWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.DelWxSmallProgramReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPage;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramPageReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.dto.smallprogram.UpdateWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.UpdateWxSmallProgramReturn;
import com.lj.business.weixin.service.IWxSmallProgramService;

/**
 * 
 * 
 * 类说明：微信小程序实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
@Service
public class WxSmallProgramServiceImpl implements IWxSmallProgramService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxSmallProgramServiceImpl.class);
	
	@Resource
	private IWxSmallProgramDao wxSmallProgramDao;
	
	@Override
	public AddWxSmallProgramReturn addWxSmallProgram(AddWxSmallProgram addWxSmallProgram) throws TsfaServiceException {
		logger.debug("addWxSmallProgram(AddWxSmallProgram addWxSmallProgram={}) - start", addWxSmallProgram); 

		AssertUtils.notNull(addWxSmallProgram);
		try {
			WxSmallProgram wxSmallProgram = new WxSmallProgram();
			//add数据录入
			wxSmallProgram.setCode(GUID.generateByUUID());
			wxSmallProgram.setNoWxZk(addWxSmallProgram.getNoWxZk());
			wxSmallProgram.setType(addWxSmallProgram.getType());
			wxSmallProgram.setSpAppid(addWxSmallProgram.getSpAppid());
			wxSmallProgram.setSpName(addWxSmallProgram.getSpName());
			wxSmallProgram.setSpLogo(addWxSmallProgram.getSpLogo());
			wxSmallProgram.setSpDesc(addWxSmallProgram.getSpDesc());
			wxSmallProgram.setSpUrl(addWxSmallProgram.getSpUrl());
			wxSmallProgram.setSpPagePath(addWxSmallProgram.getSpPagePath());
			wxSmallProgram.setWxParam(addWxSmallProgram.getWxParam());
			wxSmallProgram.setStatus(addWxSmallProgram.getStatus());
//			wxSmallProgram.setShopNo(addWxSmallProgram.getShopNo());
//			wxSmallProgram.setShopName(addWxSmallProgram.getShopName());
			wxSmallProgram.setMerchantNo(addWxSmallProgram.getMerchantNo());
			wxSmallProgram.setMerchantName(addWxSmallProgram.getMerchantName());
			wxSmallProgram.setCreateDate(addWxSmallProgram.getCreateDate());
			wxSmallProgram.setRemark(addWxSmallProgram.getRemark());
			wxSmallProgram.setRemark2(addWxSmallProgram.getRemark2());
			wxSmallProgram.setRemark3(addWxSmallProgram.getRemark3());
			wxSmallProgram.setRemark4(addWxSmallProgram.getRemark4());
			wxSmallProgramDao.insert(wxSmallProgram);
			AddWxSmallProgramReturn addWxSmallProgramReturn = new AddWxSmallProgramReturn();
			
			logger.debug("addWxSmallProgram(AddWxSmallProgram) - end - return value={}", addWxSmallProgramReturn); 
			return addWxSmallProgramReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增微信小程序信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_SMALL_PROGRAM_ADD_ERROR,"新增微信小程序信息错误！",e);
		}
	}
	
	@Override
	public DelWxSmallProgramReturn delWxSmallProgram(DelWxSmallProgram delWxSmallProgram) throws TsfaServiceException {
		logger.debug("delWxSmallProgram(DelWxSmallProgram delWxSmallProgram={}) - start", delWxSmallProgram); 

		AssertUtils.notNull(delWxSmallProgram);
		AssertUtils.notNull(delWxSmallProgram.getCode(),"Code不能为空！");
		try {
			wxSmallProgramDao.deleteByPrimaryKey(delWxSmallProgram.getCode());
			DelWxSmallProgramReturn delWxSmallProgramReturn  = new DelWxSmallProgramReturn();

			logger.debug("delWxSmallProgram(DelWxSmallProgram) - end - return value={}", delWxSmallProgramReturn); 
			return delWxSmallProgramReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除微信小程序信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_SMALL_PROGRAM_DEL_ERROR,"删除微信小程序信息错误！",e);
		}
	}

	@Override
	public UpdateWxSmallProgramReturn updateWxSmallProgram(UpdateWxSmallProgram updateWxSmallProgram) throws TsfaServiceException {
		logger.debug("updateWxSmallProgram(UpdateWxSmallProgram updateWxSmallProgram={}) - start", updateWxSmallProgram); 

		AssertUtils.notNull(updateWxSmallProgram);
		AssertUtils.notNullAndEmpty(updateWxSmallProgram.getCode(),"Code不能为空");
		try {
			WxSmallProgram wxSmallProgram = new WxSmallProgram();
			//update数据录入
			wxSmallProgram.setCode(updateWxSmallProgram.getCode());
			wxSmallProgram.setNoWxZk(updateWxSmallProgram.getNoWxZk());
			wxSmallProgram.setType(updateWxSmallProgram.getType());
			wxSmallProgram.setSpAppid(updateWxSmallProgram.getSpAppid());
			wxSmallProgram.setSpName(updateWxSmallProgram.getSpName());
			wxSmallProgram.setSpLogo(updateWxSmallProgram.getSpLogo());
			wxSmallProgram.setSpDesc(updateWxSmallProgram.getSpDesc());
			wxSmallProgram.setSpUrl(updateWxSmallProgram.getSpUrl());
			wxSmallProgram.setSpPagePath(updateWxSmallProgram.getSpPagePath());
			wxSmallProgram.setWxParam(updateWxSmallProgram.getWxParam());
			wxSmallProgram.setStatus(updateWxSmallProgram.getStatus());
//			wxSmallProgram.setShopNo(updateWxSmallProgram.getShopNo());
//			wxSmallProgram.setShopName(updateWxSmallProgram.getShopName());
			wxSmallProgram.setMerchantNo(updateWxSmallProgram.getMerchantNo());
			wxSmallProgram.setMerchantName(updateWxSmallProgram.getMerchantName());
			wxSmallProgram.setCreateDate(updateWxSmallProgram.getCreateDate());
			wxSmallProgram.setRemark(updateWxSmallProgram.getRemark());
			wxSmallProgram.setRemark2(updateWxSmallProgram.getRemark2());
			wxSmallProgram.setRemark3(updateWxSmallProgram.getRemark3());
			wxSmallProgram.setRemark4(updateWxSmallProgram.getRemark4());
			AssertUtils.notUpdateMoreThanOne(wxSmallProgramDao.updateByPrimaryKeySelective(wxSmallProgram));
			UpdateWxSmallProgramReturn updateWxSmallProgramReturn = new UpdateWxSmallProgramReturn();

			logger.debug("updateWxSmallProgram(UpdateWxSmallProgram) - end - return value={}", updateWxSmallProgramReturn); 
			return updateWxSmallProgramReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("微信小程序信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_SMALL_PROGRAM_UPDATE_ERROR,"微信小程序信息更新信息错误！",e);
		}
	}

	@Override
	public FindWxSmallProgramReturn findWxSmallProgram(FindWxSmallProgram findWxSmallProgram) throws TsfaServiceException {
		logger.debug("findWxSmallProgram(FindWxSmallProgram findWxSmallProgram={}) - start", findWxSmallProgram); 

		AssertUtils.notNull(findWxSmallProgram);
		AssertUtils.notAllNull(findWxSmallProgram.getCode(),"Code不能为空");
		try {
			WxSmallProgram wxSmallProgram = wxSmallProgramDao.selectByPrimaryKey(findWxSmallProgram.getCode());
			if(wxSmallProgram == null){
				throw new TsfaServiceException(ErrorCode.WX_SMALL_PROGRAM_NOT_EXIST_ERROR,"微信小程序信息不存在");
			}
			FindWxSmallProgramReturn findWxSmallProgramReturn = new FindWxSmallProgramReturn();
			//find数据录入
			findWxSmallProgramReturn.setCode(wxSmallProgram.getCode());
			findWxSmallProgramReturn.setNoWxZk(wxSmallProgram.getNoWxZk());
			findWxSmallProgramReturn.setType(wxSmallProgram.getType());
			findWxSmallProgramReturn.setSpAppid(wxSmallProgram.getSpAppid());
			findWxSmallProgramReturn.setSpName(wxSmallProgram.getSpName());
			findWxSmallProgramReturn.setSpLogo(wxSmallProgram.getSpLogo());
			findWxSmallProgramReturn.setSpDesc(wxSmallProgram.getSpDesc());
			findWxSmallProgramReturn.setSpUrl(wxSmallProgram.getSpUrl());
			findWxSmallProgramReturn.setSpPagePath(wxSmallProgram.getSpPagePath());
			findWxSmallProgramReturn.setWxParam(wxSmallProgram.getWxParam());
			findWxSmallProgramReturn.setStatus(wxSmallProgram.getStatus());
//			findWxSmallProgramReturn.setShopNo(wxSmallProgram.getShopNo());
//			findWxSmallProgramReturn.setShopName(wxSmallProgram.getShopName());
			findWxSmallProgramReturn.setMerchantNo(wxSmallProgram.getMerchantNo());
			findWxSmallProgramReturn.setMerchantName(wxSmallProgram.getMerchantName());
			findWxSmallProgramReturn.setCreateDate(wxSmallProgram.getCreateDate());
			findWxSmallProgramReturn.setRemark(wxSmallProgram.getRemark());
			findWxSmallProgramReturn.setRemark2(wxSmallProgram.getRemark2());
			findWxSmallProgramReturn.setRemark3(wxSmallProgram.getRemark3());
			findWxSmallProgramReturn.setRemark4(wxSmallProgram.getRemark4());
			
			logger.debug("findWxSmallProgram(FindWxSmallProgram) - end - return value={}", findWxSmallProgramReturn); 
			return findWxSmallProgramReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找微信小程序信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_SMALL_PROGRAM_FIND_ERROR,"查找微信小程序信息信息错误！",e);
		}
	}

	@Override
	public Page<FindWxSmallProgramPageReturn> findWxSmallProgramPage(FindWxSmallProgramPage findWxSmallProgramPage) throws TsfaServiceException {
		logger.debug("findWxSmallProgramPage(FindWxSmallProgramPage findWxSmallProgramPage={}) - start", findWxSmallProgramPage); 

		AssertUtils.notNull(findWxSmallProgramPage);
		List<FindWxSmallProgramPageReturn> returnList = null;
		int count = 0;
		try {
			count = wxSmallProgramDao.findWxSmallProgramPageCount(findWxSmallProgramPage);
			if(count > 0) {
				returnList = wxSmallProgramDao.findWxSmallProgramPage(findWxSmallProgramPage);
			}
		}  catch (Exception e) {
			logger.error("微信小程序信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WX_SMALL_PROGRAM_FIND_PAGE_ERROR,"微信小程序信息不存在错误.！",e);
		}
		Page<FindWxSmallProgramPageReturn> returnPage = new Page<FindWxSmallProgramPageReturn>(returnList, count, findWxSmallProgramPage);

		logger.debug("findWxSmallProgramPage(FindWxSmallProgramPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 	
	
	@Override
	public FindWxSmallProgramReturn findByAppidAndNoWxZk(FindWxSmallProgram findWxSmallProgram) throws TsfaServiceException {
		logger.debug("findByAppidAndNoWxZk(FindWxSmallProgram findWxSmallProgram={}) - start", findWxSmallProgram); 

		AssertUtils.notNull(findWxSmallProgram);
		AssertUtils.notAllNull(findWxSmallProgram.getSpAppid(),"appid不能为空");
		AssertUtils.notAllNull(findWxSmallProgram.getSpPagePath(),"页面地址不能为空");
		AssertUtils.notAllNull(findWxSmallProgram.getNoWxZk(),"终端微信不能为空");
		AssertUtils.notAllNull(findWxSmallProgram.getMerchantNo(),"商户编号不能为空");
		try {
			FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramDao.findByAppidAndNoWxZk(findWxSmallProgram);
			logger.debug("findByAppidAndNoWxZk(FindWxSmallProgram) - end - return value={}", findWxSmallProgramReturn); 
			return findWxSmallProgramReturn;
		} catch (Exception e) {
			logger.error("查找微信小程序信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_SMALL_PROGRAM_FIND_ERROR,"查找微信小程序信息信息错误！",e);
		}
	}

	
	@Override
	public void delete(UpdateWxSmallProgram updateWxSmallProgram) {
		wxSmallProgramDao.delete(updateWxSmallProgram);
		
	}
}
