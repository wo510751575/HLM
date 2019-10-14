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
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IWxPushConfigDao;
import com.lj.business.member.domain.WxPushConfig;
import com.lj.business.member.dto.pushconfig.AddWxPushConfig;
import com.lj.business.member.dto.pushconfig.AddWxPushConfigReturn;
import com.lj.business.member.dto.pushconfig.DelWxPushConfig;
import com.lj.business.member.dto.pushconfig.DelWxPushConfigReturn;
import com.lj.business.member.dto.pushconfig.FindWxPushConfig;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPage;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPageReturn;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigReturn;
import com.lj.business.member.dto.pushconfig.UpdateWxPushConfig;
import com.lj.business.member.dto.pushconfig.UpdateWxPushConfigReturn;
import com.lj.business.member.emus.PushConfigType;
import com.lj.business.member.service.IWxPushConfigService;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxSmallProgramService;

/**
 * 
 * 
 * 类说明：微信推送配置实现类
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
public class WxPushConfigServiceImpl implements IWxPushConfigService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxPushConfigServiceImpl.class);
	
	@Resource
	private IWxPushConfigDao wxPushConfigDao;
    
    @Resource
    private IWxPublicAccountService wxPublicAccountService;
    
    @Resource
    private IWxSmallProgramService wxSmallProgramService;
	
	@Override
	public AddWxPushConfigReturn addWxPushConfig(AddWxPushConfig addWxPushConfig) throws TsfaServiceException {
		logger.debug("addWxPushConfig(AddWxPushConfig addWxPushConfig={}) - start", addWxPushConfig); 

		AssertUtils.notNull(addWxPushConfig);
		try {
			WxPushConfig wxPushConfig = new WxPushConfig();
			//add数据录入
			wxPushConfig.setCode(GUID.generateByUUID());
			wxPushConfig.setNoWx(addWxPushConfig.getNoWx());
			wxPushConfig.setStatus(addWxPushConfig.getStatus());
			wxPushConfig.setSort(addWxPushConfig.getSort());
			wxPushConfig.setType(addWxPushConfig.getType());
			wxPushConfig.setContent(addWxPushConfig.getContent());
			wxPushConfig.setShareCode(addWxPushConfig.getShareCode());
			wxPushConfig.setShareTitle(addWxPushConfig.getShareTitle());
			wxPushConfig.setShareDes(addWxPushConfig.getShareDes());
			wxPushConfig.setShareIcon(addWxPushConfig.getShareIcon());
			wxPushConfig.setShareUrl(addWxPushConfig.getShareUrl());
			wxPushConfig.setPushTime(addWxPushConfig.getPushTime());
			wxPushConfig.setOrgType(addWxPushConfig.getOrgType());
			wxPushConfig.setMerchantNo(addWxPushConfig.getMerchantNo());
			wxPushConfig.setCreateDate(new Date());
			wxPushConfig.setRemark(addWxPushConfig.getRemark());
			
			switch (PushConfigType.valueOf(wxPushConfig.getType())) {
			case PA:		// 公众号
				FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
				findWxPublicAccount.setCode(wxPushConfig.getShareCode());
				FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService.findWxPublicAccount(findWxPublicAccount);
				if(findWxPublicAccountReturn != null && CommonConstant.I_YES == findWxPublicAccountReturn.getStatus()) {
					wxPushConfig.setShareTitle(findWxPublicAccountReturn.getPaName());
					wxPushConfig.setShareDes(findWxPublicAccountReturn.getPaDesc());
					wxPushConfig.setShareIcon(findWxPublicAccountReturn.getPaLogo());
				}
				break;
			case SP:		// 小程序
				FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
				findWxSmallProgram.setCode(wxPushConfig.getShareCode());
				FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService.findWxSmallProgram(findWxSmallProgram);
				wxPushConfig.setShareTitle(findWxSmallProgramReturn.getSpName());
				wxPushConfig.setShareDes(findWxSmallProgramReturn.getSpDesc());
				wxPushConfig.setShareIcon(findWxSmallProgramReturn.getSpLogo());
				wxPushConfig.setShareUrl(findWxSmallProgramReturn.getSpUrl());
				break;
			default:
				break;
			}
			
			wxPushConfigDao.insert(wxPushConfig);
			AddWxPushConfigReturn addWxPushConfigReturn = new AddWxPushConfigReturn();
			
			logger.debug("addWxPushConfig(AddWxPushConfig) - end - return value={}", addWxPushConfigReturn); 
			return addWxPushConfigReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增微信推送配置信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PUSH_CONFIG_ADD_ERROR,"新增微信推送配置信息错误！",e);
		}
	}
	
	@Override
	public DelWxPushConfigReturn delWxPushConfig(DelWxPushConfig delWxPushConfig) throws TsfaServiceException {
		logger.debug("delWxPushConfig(DelWxPushConfig delWxPushConfig={}) - start", delWxPushConfig); 

		AssertUtils.notNull(delWxPushConfig);
		AssertUtils.notNull(delWxPushConfig.getCode(),"Code不能为空！");
		try {
			wxPushConfigDao.deleteByPrimaryKey(delWxPushConfig.getCode());
			DelWxPushConfigReturn delWxPushConfigReturn  = new DelWxPushConfigReturn();

			logger.debug("delWxPushConfig(DelWxPushConfig) - end - return value={}", delWxPushConfigReturn); 
			return delWxPushConfigReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除微信推送配置信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PUSH_CONFIG_DEL_ERROR,"删除微信推送配置信息错误！",e);
		}
	}

	@Override
	public UpdateWxPushConfigReturn updateWxPushConfig(UpdateWxPushConfig updateWxPushConfig) throws TsfaServiceException {
		logger.debug("updateWxPushConfig(UpdateWxPushConfig updateWxPushConfig={}) - start", updateWxPushConfig); 

		AssertUtils.notNull(updateWxPushConfig);
		AssertUtils.notNullAndEmpty(updateWxPushConfig.getCode(),"Code不能为空");
		try {
			WxPushConfig wxPushConfig = new WxPushConfig();
			//update数据录入
			wxPushConfig.setCode(updateWxPushConfig.getCode());
			wxPushConfig.setNoWx(updateWxPushConfig.getNoWx());
			wxPushConfig.setStatus(updateWxPushConfig.getStatus());
			wxPushConfig.setSort(updateWxPushConfig.getSort());
			wxPushConfig.setType(updateWxPushConfig.getType());
			wxPushConfig.setContent(updateWxPushConfig.getContent());
			wxPushConfig.setShareCode(updateWxPushConfig.getShareCode());
			wxPushConfig.setShareTitle(updateWxPushConfig.getShareTitle());
			wxPushConfig.setShareDes(updateWxPushConfig.getShareDes());
			wxPushConfig.setShareIcon(updateWxPushConfig.getShareIcon());
			wxPushConfig.setShareUrl(updateWxPushConfig.getShareUrl());
			wxPushConfig.setPushTime(updateWxPushConfig.getPushTime());
			wxPushConfig.setOrgType(updateWxPushConfig.getOrgType());
			wxPushConfig.setMerchantNo(updateWxPushConfig.getMerchantNo());
			wxPushConfig.setUpdateDate(new Date());
			wxPushConfig.setRemark(updateWxPushConfig.getRemark());
			
			if(StringUtils.isNotEmpty(wxPushConfig.getType())) {
				switch (PushConfigType.valueOf(wxPushConfig.getType())) {
				case PA:		// 公众号
					FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
					findWxPublicAccount.setCode(wxPushConfig.getShareCode());
					FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService.findWxPublicAccount(findWxPublicAccount);
					if(findWxPublicAccountReturn != null && CommonConstant.I_YES == findWxPublicAccountReturn.getStatus()) {
						wxPushConfig.setShareTitle(findWxPublicAccountReturn.getPaName());
						wxPushConfig.setShareDes(findWxPublicAccountReturn.getPaDesc());
						wxPushConfig.setShareIcon(findWxPublicAccountReturn.getPaLogo());
					}
					break;
				case SP:		// 小程序
					FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
					findWxSmallProgram.setCode(wxPushConfig.getShareCode());
					FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService.findWxSmallProgram(findWxSmallProgram);
					wxPushConfig.setShareTitle(findWxSmallProgramReturn.getSpName());
					wxPushConfig.setShareDes(findWxSmallProgramReturn.getSpDesc());
					wxPushConfig.setShareIcon(findWxSmallProgramReturn.getSpLogo());
					wxPushConfig.setShareUrl(findWxSmallProgramReturn.getSpUrl());
					break;
				default:
					break;
				}
			}
			
			AssertUtils.notUpdateMoreThanOne(wxPushConfigDao.updateByPrimaryKeySelective(wxPushConfig));
			UpdateWxPushConfigReturn updateWxPushConfigReturn = new UpdateWxPushConfigReturn();

			logger.debug("updateWxPushConfig(UpdateWxPushConfig) - end - return value={}", updateWxPushConfigReturn); 
			return updateWxPushConfigReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("微信推送配置信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PUSH_CONFIG_UPDATE_ERROR,"微信推送配置信息更新信息错误！",e);
		}
	}

	@Override
	public FindWxPushConfigReturn findWxPushConfig(FindWxPushConfig findWxPushConfig) throws TsfaServiceException {
		logger.debug("findWxPushConfig(FindWxPushConfig findWxPushConfig={}) - start", findWxPushConfig); 

		AssertUtils.notNull(findWxPushConfig);
		AssertUtils.notAllNull(findWxPushConfig.getCode(),"Code不能为空");
		try {
			WxPushConfig wxPushConfig = wxPushConfigDao.selectByPrimaryKey(findWxPushConfig.getCode());
			if(wxPushConfig == null){
				throw new TsfaServiceException(ErrorCode.WX_PUSH_CONFIG_NOT_EXIST_ERROR,"微信推送配置信息不存在");
			}
			FindWxPushConfigReturn findWxPushConfigReturn = new FindWxPushConfigReturn();
			//find数据录入
			findWxPushConfigReturn.setCode(wxPushConfig.getCode());
			findWxPushConfigReturn.setNoWx(wxPushConfig.getNoWx());
			findWxPushConfigReturn.setStatus(wxPushConfig.getStatus());
			findWxPushConfigReturn.setSort(wxPushConfig.getSort());
			findWxPushConfigReturn.setType(wxPushConfig.getType());
			findWxPushConfigReturn.setContent(wxPushConfig.getContent());
			findWxPushConfigReturn.setShareCode(wxPushConfig.getShareCode());
			findWxPushConfigReturn.setShareTitle(wxPushConfig.getShareTitle());
			findWxPushConfigReturn.setShareDes(wxPushConfig.getShareDes());
			findWxPushConfigReturn.setShareIcon(wxPushConfig.getShareIcon());
			findWxPushConfigReturn.setShareUrl(wxPushConfig.getShareUrl());
			findWxPushConfigReturn.setPushTime(wxPushConfig.getPushTime());
			findWxPushConfigReturn.setOrgType(wxPushConfig.getOrgType());
//			findWxPushConfigReturn.setShopNo(wxPushConfig.getShopNo());
//			findWxPushConfigReturn.setShopName(wxPushConfig.getShopName());
			findWxPushConfigReturn.setMerchantNo(wxPushConfig.getMerchantNo());
			findWxPushConfigReturn.setCreateDate(wxPushConfig.getCreateDate());
			findWxPushConfigReturn.setUpdateDate(wxPushConfig.getUpdateDate());
			findWxPushConfigReturn.setRemark(wxPushConfig.getRemark());
			
			logger.debug("findWxPushConfig(FindWxPushConfig) - end - return value={}", findWxPushConfigReturn); 
			return findWxPushConfigReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找微信推送配置信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PUSH_CONFIG_FIND_ERROR,"查找微信推送配置信息信息错误！",e);
		}
	}

	@Override
	public Page<FindWxPushConfigPageReturn> findWxPushConfigPage(FindWxPushConfigPage findWxPushConfigPage) throws TsfaServiceException {
		logger.debug("findWxPushConfigPage(FindWxPushConfigPage findWxPushConfigPage={}) - start", findWxPushConfigPage); 

		AssertUtils.notNull(findWxPushConfigPage);
		List<FindWxPushConfigPageReturn> returnList = null;
		int count = 0;
		try {
			count = wxPushConfigDao.findWxPushConfigPageCount(findWxPushConfigPage);
			if(count > 0) {
				returnList = wxPushConfigDao.findWxPushConfigPage(findWxPushConfigPage);
			}
		}  catch (Exception e) {
			logger.error("微信推送配置信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WX_PUSH_CONFIG_FIND_PAGE_ERROR,"微信推送配置信息不存在错误.！",e);
		}
		Page<FindWxPushConfigPageReturn> returnPage = new Page<FindWxPushConfigPageReturn>(returnList, count, findWxPushConfigPage);

		logger.debug("findWxPushConfigPage(FindWxPushConfigPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	@Override
	public List<FindWxPushConfigPageReturn> findWxPushConfigToPush(FindWxPushConfigPage findWxPushConfigPage) throws TsfaServiceException {
		logger.debug("findWxPushConfigToPush(FindWxPushConfigPage findWxPushConfigPage={}) - start", findWxPushConfigPage); 

		AssertUtils.notNull(findWxPushConfigPage);
		List<FindWxPushConfigPageReturn> returnList = null;
		try {
			returnList = wxPushConfigDao.findWxPushConfigToPush(findWxPushConfigPage);
		}  catch (Exception e) {
			logger.error("查询终端待推送配置列表错误",e);
			throw new TsfaServiceException(ErrorCode.WX_PUSH_CONFIG_FIND_ERROR,"查询终端待推送配置列表错误.！",e);
		}

		logger.debug("findWxPushConfigToPush(FindWxPushConfigPage) - end - return value={}", returnList); 
		return returnList;
	}

	
	@Override
	public void delete(UpdateWxPushConfig updateWxPushConfig) {
		wxPushConfigDao.delete(updateWxPushConfig);
		
	} 
	
}
