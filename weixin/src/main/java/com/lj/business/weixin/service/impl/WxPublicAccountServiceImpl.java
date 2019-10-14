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
import com.lj.business.weixin.dao.IWxPublicAccountDao;
import com.lj.business.weixin.domain.WxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.AddWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.AddWxPublicAccountReturn;
import com.lj.business.weixin.dto.publicaccount.DelWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.DelWxPublicAccountReturn;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPage;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountPageReturn;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.publicaccount.UpdateWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.UpdateWxPublicAccountReturn;
import com.lj.business.weixin.service.IWxPublicAccountService;

/**
 * 
 * 
 * 类说明：微信公众号实现类
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
public class WxPublicAccountServiceImpl implements IWxPublicAccountService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxPublicAccountServiceImpl.class);
	
	@Resource
	private IWxPublicAccountDao wxPublicAccountDao;
	
	@Override
	public AddWxPublicAccountReturn addWxPublicAccount(AddWxPublicAccount addWxPublicAccount) throws TsfaServiceException {
		logger.debug("addWxPublicAccount(AddWxPublicAccount addWxPublicAccount={}) - start", addWxPublicAccount); 

		AssertUtils.notNull(addWxPublicAccount);
		try {
			WxPublicAccount wxPublicAccount = new WxPublicAccount();
			//add数据录入
			wxPublicAccount.setCode(GUID.generateByUUID());
			wxPublicAccount.setNoWxZk(addWxPublicAccount.getNoWxZk());
			wxPublicAccount.setPaUsername(addWxPublicAccount.getPaUsername());
			wxPublicAccount.setPaAlias(addWxPublicAccount.getPaAlias());
			wxPublicAccount.setPaName(addWxPublicAccount.getPaName());
			wxPublicAccount.setPaLogo(addWxPublicAccount.getPaLogo());
			wxPublicAccount.setPaDesc(addWxPublicAccount.getPaDesc());
			wxPublicAccount.setPaCertflag(addWxPublicAccount.getPaCertflag());
			wxPublicAccount.setWxParam(addWxPublicAccount.getWxParam());
			wxPublicAccount.setStatus(addWxPublicAccount.getStatus());
//			wxPublicAccount.setShopNo(addWxPublicAccount.getShopNo());
//			wxPublicAccount.setShopName(addWxPublicAccount.getShopName());
			wxPublicAccount.setMerchantNo(addWxPublicAccount.getMerchantNo());
			wxPublicAccount.setMerchantName(addWxPublicAccount.getMerchantName());
			wxPublicAccount.setCreateDate(addWxPublicAccount.getCreateDate());
			wxPublicAccount.setRemark(addWxPublicAccount.getRemark());
			wxPublicAccount.setRemark2(addWxPublicAccount.getRemark2());
			wxPublicAccount.setRemark3(addWxPublicAccount.getRemark3());
			wxPublicAccount.setRemark4(addWxPublicAccount.getRemark4());
			wxPublicAccountDao.insert(wxPublicAccount);
			AddWxPublicAccountReturn addWxPublicAccountReturn = new AddWxPublicAccountReturn();
			
			logger.debug("addWxPublicAccount(AddWxPublicAccount) - end - return value={}", addWxPublicAccountReturn); 
			return addWxPublicAccountReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增微信公众号信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PUBLIC_ACCOUNT_ADD_ERROR,"新增微信公众号信息错误！",e);
		}
	}
	
	@Override
	public DelWxPublicAccountReturn delWxPublicAccount(DelWxPublicAccount delWxPublicAccount) throws TsfaServiceException {
		logger.debug("delWxPublicAccount(DelWxPublicAccount delWxPublicAccount={}) - start", delWxPublicAccount); 

		AssertUtils.notNull(delWxPublicAccount);
		AssertUtils.notNull(delWxPublicAccount.getCode(),"Code不能为空！");
		try {
			wxPublicAccountDao.deleteByPrimaryKey(delWxPublicAccount.getCode());
			DelWxPublicAccountReturn delWxPublicAccountReturn  = new DelWxPublicAccountReturn();

			logger.debug("delWxPublicAccount(DelWxPublicAccount) - end - return value={}", delWxPublicAccountReturn); 
			return delWxPublicAccountReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除微信公众号信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PUBLIC_ACCOUNT_DEL_ERROR,"删除微信公众号信息错误！",e);
		}
	}

	@Override
	public UpdateWxPublicAccountReturn updateWxPublicAccount(UpdateWxPublicAccount updateWxPublicAccount) throws TsfaServiceException {
		logger.debug("updateWxPublicAccount(UpdateWxPublicAccount updateWxPublicAccount={}) - start", updateWxPublicAccount); 

		AssertUtils.notNull(updateWxPublicAccount);
		AssertUtils.notNullAndEmpty(updateWxPublicAccount.getCode(),"Code不能为空");
		try {
			WxPublicAccount wxPublicAccount = new WxPublicAccount();
			//update数据录入
			wxPublicAccount.setCode(updateWxPublicAccount.getCode());
			wxPublicAccount.setNoWxZk(updateWxPublicAccount.getNoWxZk());
			wxPublicAccount.setPaUsername(updateWxPublicAccount.getPaUsername());
			wxPublicAccount.setPaAlias(updateWxPublicAccount.getPaAlias());
			wxPublicAccount.setPaName(updateWxPublicAccount.getPaName());
			wxPublicAccount.setPaLogo(updateWxPublicAccount.getPaLogo());
			wxPublicAccount.setPaDesc(updateWxPublicAccount.getPaDesc());
			wxPublicAccount.setPaCertflag(updateWxPublicAccount.getPaCertflag());
			wxPublicAccount.setWxParam(updateWxPublicAccount.getWxParam());
			wxPublicAccount.setStatus(updateWxPublicAccount.getStatus());
//			wxPublicAccount.setShopNo(updateWxPublicAccount.getShopNo());
//			wxPublicAccount.setShopName(updateWxPublicAccount.getShopName());
			wxPublicAccount.setMerchantNo(updateWxPublicAccount.getMerchantNo());
			wxPublicAccount.setMerchantName(updateWxPublicAccount.getMerchantName());
			wxPublicAccount.setCreateDate(updateWxPublicAccount.getCreateDate());
			wxPublicAccount.setRemark(updateWxPublicAccount.getRemark());
			wxPublicAccount.setRemark2(updateWxPublicAccount.getRemark2());
			wxPublicAccount.setRemark3(updateWxPublicAccount.getRemark3());
			wxPublicAccount.setRemark4(updateWxPublicAccount.getRemark4());
			AssertUtils.notUpdateMoreThanOne(wxPublicAccountDao.updateByPrimaryKeySelective(wxPublicAccount));
			UpdateWxPublicAccountReturn updateWxPublicAccountReturn = new UpdateWxPublicAccountReturn();

			logger.debug("updateWxPublicAccount(UpdateWxPublicAccount) - end - return value={}", updateWxPublicAccountReturn); 
			return updateWxPublicAccountReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("微信公众号信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PUBLIC_ACCOUNT_UPDATE_ERROR,"微信公众号信息更新信息错误！",e);
		}
	}

	@Override
	public FindWxPublicAccountReturn findWxPublicAccount(FindWxPublicAccount findWxPublicAccount) throws TsfaServiceException {
		logger.debug("findWxPublicAccount(FindWxPublicAccount findWxPublicAccount={}) - start", findWxPublicAccount); 

		AssertUtils.notNull(findWxPublicAccount);
		AssertUtils.notAllNull(findWxPublicAccount.getCode(),"Code不能为空");
		try {
			WxPublicAccount wxPublicAccount = wxPublicAccountDao.selectByPrimaryKey(findWxPublicAccount.getCode());
			if(wxPublicAccount == null){
				throw new TsfaServiceException(ErrorCode.WX_PUBLIC_ACCOUNT_NOT_EXIST_ERROR,"微信公众号信息不存在");
			}
			FindWxPublicAccountReturn findWxPublicAccountReturn = new FindWxPublicAccountReturn();
			//find数据录入
			findWxPublicAccountReturn.setCode(wxPublicAccount.getCode());
			findWxPublicAccountReturn.setNoWxZk(wxPublicAccount.getNoWxZk());
			findWxPublicAccountReturn.setPaUsername(wxPublicAccount.getPaUsername());
			findWxPublicAccountReturn.setPaAlias(wxPublicAccount.getPaAlias());
			findWxPublicAccountReturn.setPaName(wxPublicAccount.getPaName());
			findWxPublicAccountReturn.setPaLogo(wxPublicAccount.getPaLogo());
			findWxPublicAccountReturn.setPaDesc(wxPublicAccount.getPaDesc());
			findWxPublicAccountReturn.setPaCertflag(wxPublicAccount.getPaCertflag());
			findWxPublicAccountReturn.setWxParam(wxPublicAccount.getWxParam());
			findWxPublicAccountReturn.setStatus(wxPublicAccount.getStatus());
//			findWxPublicAccountReturn.setShopNo(wxPublicAccount.getShopNo());
//			findWxPublicAccountReturn.setShopName(wxPublicAccount.getShopName());
			findWxPublicAccountReturn.setMerchantNo(wxPublicAccount.getMerchantNo());
			findWxPublicAccountReturn.setMerchantName(wxPublicAccount.getMerchantName());
			findWxPublicAccountReturn.setCreateDate(wxPublicAccount.getCreateDate());
			findWxPublicAccountReturn.setRemark(wxPublicAccount.getRemark());
			findWxPublicAccountReturn.setRemark2(wxPublicAccount.getRemark2());
			findWxPublicAccountReturn.setRemark3(wxPublicAccount.getRemark3());
			findWxPublicAccountReturn.setRemark4(wxPublicAccount.getRemark4());
			
			logger.debug("findWxPublicAccount(FindWxPublicAccount) - end - return value={}", findWxPublicAccountReturn); 
			return findWxPublicAccountReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找微信公众号信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PUBLIC_ACCOUNT_FIND_ERROR,"查找微信公众号信息信息错误！",e);
		}
	}

	@Override
	public Page<FindWxPublicAccountPageReturn> findWxPublicAccountPage(FindWxPublicAccountPage findWxPublicAccountPage) throws TsfaServiceException {
		logger.debug("findWxPublicAccountPage(FindWxPublicAccountPage findWxPublicAccountPage={}) - start", findWxPublicAccountPage); 

		AssertUtils.notNull(findWxPublicAccountPage);
		List<FindWxPublicAccountPageReturn> returnList = null;
		int count = 0;
		try {
			count = wxPublicAccountDao.findWxPublicAccountPageCount(findWxPublicAccountPage);
			if(count > 0) {
				returnList = wxPublicAccountDao.findWxPublicAccountPage(findWxPublicAccountPage);
			}
		}  catch (Exception e) {
			logger.error("微信公众号信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WX_PUBLIC_ACCOUNT_FIND_PAGE_ERROR,"微信公众号信息不存在错误.！",e);
		}
		Page<FindWxPublicAccountPageReturn> returnPage = new Page<FindWxPublicAccountPageReturn>(returnList, count, findWxPublicAccountPage);

		logger.debug("findWxPublicAccountPage(FindWxPublicAccountPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	/* (non-Javadoc)
	 * @see com.lj.business.weixin.service.IWxPublicAccountService#findByUsernameAndNoWxZk(com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount)
	 */
	@Override
	public FindWxPublicAccountReturn findByUsernameAndNoWxZk(FindWxPublicAccount findWxPublicAccount) throws TsfaServiceException {
		logger.debug("findByUsernameAndNoWxZk(FindWxPublicAccount findWxPublicAccount={}) - start", findWxPublicAccount); 

		AssertUtils.notNull(findWxPublicAccount);
		AssertUtils.notAllNull(findWxPublicAccount.getPaUsername(),"username不能为空");
		AssertUtils.notAllNull(findWxPublicAccount.getNoWxZk(),"终端微信不能为空");
		AssertUtils.notAllNull(findWxPublicAccount.getMerchantNo(),"商户编号不能为空");
		try {
			FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountDao.findByUsernameAndNoWxZk(findWxPublicAccount);
			logger.debug("findByUsernameAndNoWxZk(FindWxPublicAccount) - end - return value={}", findWxPublicAccountReturn); 
			return findWxPublicAccountReturn;
		} catch (Exception e) {
			logger.error("查找微信公众号信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PUBLIC_ACCOUNT_FIND_ERROR,"查找微信公众号信息信息错误！",e);
		}
	}

	
	@Override
	public void delete(UpdateWxPublicAccount updateWxPublicAccount) {
		wxPublicAccountDao.delete(updateWxPublicAccount);
		
	} 
}
