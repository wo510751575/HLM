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
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.constant.ErrorCodeWxJobRedPackInfo;
import com.lj.business.weixin.dao.IWxJobRedPackInfoDao;
import com.lj.business.weixin.domain.WxJobRedPackInfo;
import com.lj.business.weixin.dto.FindWxJobRedPackInfoPage;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;
import com.lj.business.weixin.service.IWxJobRedPackInfoService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 罗丹青
 * 
 * 
 * CreateDate: 2017-08-22
 */
@Service
public class WxJobRedPackInfoServiceImpl implements IWxJobRedPackInfoService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxJobRedPackInfoServiceImpl.class);
	

	@Resource
	private IWxJobRedPackInfoDao wxJobRedPackInfoDao;
	
	
	@Override
	public void addWxJobRedPackInfo(WxJobRedPackInfoDto wxJobRedPackInfoDto) throws TsfaServiceException {
		logger.debug("addWxJobRedPackInfo(AddWxJobRedPackInfo addWxJobRedPackInfo={}) - start", wxJobRedPackInfoDto); 

		AssertUtils.notNull(wxJobRedPackInfoDto);
		try {
			WxJobRedPackInfo wxJobRedPackInfo = new WxJobRedPackInfo();
			//add数据录入
			wxJobRedPackInfo.setCode(wxJobRedPackInfoDto.getCode());
			wxJobRedPackInfo.setJobCode(wxJobRedPackInfoDto.getJobCode());
			wxJobRedPackInfo.setMerchantNo(wxJobRedPackInfoDto.getMerchantNo());
			wxJobRedPackInfo.setWxNoShop(wxJobRedPackInfoDto.getWxNoShop());
//			wxJobRedPackInfo.setShopNo(wxJobRedPackInfoDto.getShopNo());
			wxJobRedPackInfo.setSendRedpackGm(wxJobRedPackInfoDto.getSendRedpackGm());
			wxJobRedPackInfo.setRedpackType(wxJobRedPackInfoDto.getRedpackType());
			wxJobRedPackInfo.setRedpackContent(wxJobRedPackInfoDto.getRedpackContent());
			wxJobRedPackInfo.setRedpackAmount(wxJobRedPackInfoDto.getRedpackAmount());
			wxJobRedPackInfo.setRedpackCount(wxJobRedPackInfoDto.getRedpackCount());
			wxJobRedPackInfo.setCreateDate(wxJobRedPackInfoDto.getCreateDate());
			wxJobRedPackInfo.setUpdateDate(wxJobRedPackInfoDto.getUpdateDate());
			wxJobRedPackInfo.setStatus(wxJobRedPackInfoDto.getStatus());
			wxJobRedPackInfo.setRemark(wxJobRedPackInfoDto.getRemark());
			wxJobRedPackInfoDao.insert(wxJobRedPackInfo);
			logger.debug("addWxJobRedPackInfo(WxJobRedPackInfoDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增微信红包任务信息错误！",e);
			throw new TsfaServiceException(ErrorCodeWxJobRedPackInfo.WX_JOB_RED_PACK_INFO_ADD_ERROR,"新增微信红包任务信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询微信红包任务信息
	 *
	 * @param findWxJobRedPackInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017年07月10日
	 *
	 */
	public List<WxJobRedPackInfoDto>  findWxJobRedPackInfos(FindWxJobRedPackInfoPage findWxJobRedPackInfoPage)throws TsfaServiceException{
		AssertUtils.notNull(findWxJobRedPackInfoPage);
		List<WxJobRedPackInfoDto> returnList=null;
		try {
			//returnList = wxJobRedPackInfoDao.findWxJobRedPackInfos(findWxJobRedPackInfoPage);
		} catch (Exception e) {
			logger.error("查找微信红包任务信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeWxJobRedPackInfo.WX_JOB_RED_PACK_INFO_NOT_EXIST_ERROR,"微信红包任务信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateWxJobRedPackInfo(
			WxJobRedPackInfoDto wxJobRedPackInfoDto)
			throws TsfaServiceException {
		logger.debug("updateWxJobRedPackInfo(WxJobRedPackInfoDto wxJobRedPackInfoDto={}) - start", wxJobRedPackInfoDto); 

		AssertUtils.notNull(wxJobRedPackInfoDto);
		AssertUtils.notNullAndEmpty(wxJobRedPackInfoDto.getCode(),"Code不能为空");
		try {
			WxJobRedPackInfo wxJobRedPackInfo = new WxJobRedPackInfo();
			//update数据录入
			wxJobRedPackInfo.setCode(wxJobRedPackInfoDto.getCode());
			wxJobRedPackInfo.setJobCode(wxJobRedPackInfoDto.getJobCode());
			wxJobRedPackInfo.setMerchantNo(wxJobRedPackInfoDto.getMerchantNo());
			wxJobRedPackInfo.setWxNoShop(wxJobRedPackInfoDto.getWxNoShop());
//			wxJobRedPackInfo.setShopNo(wxJobRedPackInfoDto.getShopNo());
			wxJobRedPackInfo.setSendRedpackGm(wxJobRedPackInfoDto.getSendRedpackGm());
			wxJobRedPackInfo.setRedpackType(wxJobRedPackInfoDto.getRedpackType());
			wxJobRedPackInfo.setRedpackContent(wxJobRedPackInfoDto.getRedpackContent());
			wxJobRedPackInfo.setRedpackAmount(wxJobRedPackInfoDto.getRedpackAmount());
			wxJobRedPackInfo.setRedpackCount(wxJobRedPackInfoDto.getRedpackCount());
			wxJobRedPackInfo.setCreateDate(wxJobRedPackInfoDto.getCreateDate());
			wxJobRedPackInfo.setUpdateDate(wxJobRedPackInfoDto.getUpdateDate());
			wxJobRedPackInfo.setStatus(wxJobRedPackInfoDto.getStatus());
			wxJobRedPackInfo.setRemark(wxJobRedPackInfoDto.getRemark());
			AssertUtils.notUpdateMoreThanOne(wxJobRedPackInfoDao.updateByPrimaryKeySelective(wxJobRedPackInfo));
			logger.debug("updateWxJobRedPackInfo(WxJobRedPackInfoDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("微信红包任务信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCodeWxJobRedPackInfo.WX_JOB_RED_PACK_INFO_UPDATE_ERROR,"微信红包任务信息更新信息错误！",e);
		}
	}

	

	@Override
	public WxJobRedPackInfoDto findWxJobRedPackInfo(
			WxJobRedPackInfoDto wxJobRedPackInfoDto) throws TsfaServiceException {
		logger.debug("findWxJobRedPackInfo(FindWxJobRedPackInfo findWxJobRedPackInfo={}) - start", wxJobRedPackInfoDto); 

		AssertUtils.notNull(wxJobRedPackInfoDto);
		AssertUtils.notAllNull(wxJobRedPackInfoDto.getCode(),"Code不能为空");
		try {
			WxJobRedPackInfo wxJobRedPackInfo = wxJobRedPackInfoDao.selectByPrimaryKey(wxJobRedPackInfoDto.getCode());
			if(wxJobRedPackInfo == null){
				return null;
				//throw new TsfaServiceException(ErrorCodeWxJobRedPackInfo.WX_JOB_RED_PACK_INFO_NOT_EXIST_ERROR,"微信红包任务信息不存在");
			}
			WxJobRedPackInfoDto findWxJobRedPackInfoReturn = new WxJobRedPackInfoDto();
			//find数据录入
			findWxJobRedPackInfoReturn.setCode(wxJobRedPackInfo.getCode());
			findWxJobRedPackInfoReturn.setJobCode(wxJobRedPackInfo.getJobCode());
			findWxJobRedPackInfoReturn.setMerchantNo(wxJobRedPackInfo.getMerchantNo());
			findWxJobRedPackInfoReturn.setWxNoShop(wxJobRedPackInfo.getWxNoShop());
//			findWxJobRedPackInfoReturn.setShopNo(wxJobRedPackInfo.getShopNo());
			findWxJobRedPackInfoReturn.setSendRedpackGm(wxJobRedPackInfo.getSendRedpackGm());
			findWxJobRedPackInfoReturn.setRedpackType(wxJobRedPackInfo.getRedpackType());
			findWxJobRedPackInfoReturn.setRedpackContent(wxJobRedPackInfo.getRedpackContent());
			findWxJobRedPackInfoReturn.setRedpackAmount(wxJobRedPackInfo.getRedpackAmount());
			findWxJobRedPackInfoReturn.setRedpackCount(wxJobRedPackInfo.getRedpackCount());
			findWxJobRedPackInfoReturn.setCreateDate(wxJobRedPackInfo.getCreateDate());
			findWxJobRedPackInfoReturn.setUpdateDate(wxJobRedPackInfo.getUpdateDate());
			findWxJobRedPackInfoReturn.setStatus(wxJobRedPackInfo.getStatus());
			findWxJobRedPackInfoReturn.setRemark(wxJobRedPackInfo.getRemark());
			
			logger.debug("findWxJobRedPackInfo(WxJobRedPackInfoDto) - end - return value={}", findWxJobRedPackInfoReturn); 
			return findWxJobRedPackInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找微信红包任务信息信息错误！",e);
			throw new TsfaServiceException(ErrorCodeWxJobRedPackInfo.WX_JOB_RED_PACK_INFO_FIND_ERROR,"查找微信红包任务信息信息错误！",e);
		}


	}

	
	@Override
	public Page<WxJobRedPackInfoDto> findWxJobRedPackInfoPage(
			FindWxJobRedPackInfoPage findWxJobRedPackInfoPage)
			throws TsfaServiceException {
		logger.debug("findWxJobRedPackInfoPage(FindWxJobRedPackInfoPage findWxJobRedPackInfoPage={}) - start", findWxJobRedPackInfoPage); 

		AssertUtils.notNull(findWxJobRedPackInfoPage);
		List<WxJobRedPackInfoDto> returnList=null;
		int count = 0;
		try {
			//returnList = wxJobRedPackInfoDao.findWxJobRedPackInfoPage(findWxJobRedPackInfoPage);
			//count = wxJobRedPackInfoDao.findWxJobRedPackInfoPageCount(findWxJobRedPackInfoPage);
		}  catch (Exception e) {
			logger.error("微信红包任务信息不存在错误",e);
			throw new TsfaServiceException(ErrorCodeWxJobRedPackInfo.WX_JOB_RED_PACK_INFO_FIND_PAGE_ERROR,"微信红包任务信息不存在错误.！",e);
		}
		Page<WxJobRedPackInfoDto> returnPage = new Page<WxJobRedPackInfoDto>(returnList, count, findWxJobRedPackInfoPage);

		logger.debug("findWxJobRedPackInfoPage(FindWxJobRedPackInfoPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	
	@Override
	public void delete(WxJobRedPackInfoDto wxJobRedPackInfoDto) {
		wxJobRedPackInfoDao.delete(wxJobRedPackInfoDto);
		
	} 


}
