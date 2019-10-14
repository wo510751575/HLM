package com.lj.business.st.service.impl;

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
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IWxPmFollowReportShopDao;
import com.lj.business.st.domain.WxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportShopReturn;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportShopReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopPageReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportShopReturn;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportShop;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportShopReturn;
import com.lj.business.st.service.IWxPmFollowReportShopService;

/**
 * 
 * 
 * 类说明：门店微信客户跟踪日报实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年08月06日
 */
@Service
public class WxPmFollowReportShopServiceImpl implements IWxPmFollowReportShopService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxPmFollowReportShopServiceImpl.class);
	
	@Resource
	private IWxPmFollowReportShopDao wxPmFollowReportShopDao;
	
	@Override
	public AddWxPmFollowReportShopReturn addWxPmFollowReportShop(AddWxPmFollowReportShop addWxPmFollowReportShop) throws TsfaServiceException {
		logger.debug("addWxPmFollowReportShop(AddWxPmFollowReportShop addWxPmFollowReportShop={}) - start", addWxPmFollowReportShop); 

		AssertUtils.notNull(addWxPmFollowReportShop);
		try {
			WxPmFollowReportShop wxPmFollowReportShop = new WxPmFollowReportShop();
			//add数据录入
			wxPmFollowReportShop.setCode(GUID.getPreUUID());
			wxPmFollowReportShop.setReportDate(addWxPmFollowReportShop.getReportDate());
			wxPmFollowReportShop.setMerchantNo(addWxPmFollowReportShop.getMerchantNo());
			wxPmFollowReportShop.setMerchantName(addWxPmFollowReportShop.getMerchantName());
			wxPmFollowReportShop.setCompanyNo(addWxPmFollowReportShop.getCompanyNo());
			wxPmFollowReportShop.setCompanyName(addWxPmFollowReportShop.getCompanyName());
			wxPmFollowReportShop.setDealerCode(addWxPmFollowReportShop.getDealerCode());
			wxPmFollowReportShop.setShopNo(addWxPmFollowReportShop.getShopNo());
			wxPmFollowReportShop.setShopName(addWxPmFollowReportShop.getShopName());
			wxPmFollowReportShop.setShopCode(addWxPmFollowReportShop.getShopCode());
			wxPmFollowReportShop.setNumPmNew(addWxPmFollowReportShop.getNumPmNew());
			wxPmFollowReportShop.setNumPmNewNotFollow(addWxPmFollowReportShop.getNumPmNewNotFollow());
			wxPmFollowReportShop.setNumPmNewFollow(addWxPmFollowReportShop.getNumPmNewFollow());
			wxPmFollowReportShop.setNumPmOld(addWxPmFollowReportShop.getNumPmOld());
			wxPmFollowReportShop.setNumPmOldNotFollow(addWxPmFollowReportShop.getNumPmOldNotFollow());
			wxPmFollowReportShop.setNumPmOldFollow(addWxPmFollowReportShop.getNumPmOldFollow());
			wxPmFollowReportShop.setNumPmTotal(addWxPmFollowReportShop.getNumPmTotal());
			wxPmFollowReportShop.setCreateDate(new Date());
			wxPmFollowReportShop.setUpdateDate(wxPmFollowReportShop.getCreateDate());
			wxPmFollowReportShop.setRemark(addWxPmFollowReportShop.getRemark());
			wxPmFollowReportShopDao.insert(wxPmFollowReportShop);
			AddWxPmFollowReportShopReturn addWxPmFollowReportShopReturn = new AddWxPmFollowReportShopReturn();
			
			logger.debug("addWxPmFollowReportShop(AddWxPmFollowReportShop) - end - return value={}", addWxPmFollowReportShopReturn); 
			return addWxPmFollowReportShopReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增门店微信客户跟踪日报信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_SHOP_ADD_ERROR,"新增门店微信客户跟踪日报信息错误！",e);
		}
	}
	
	@Override
	public DelWxPmFollowReportShopReturn delWxPmFollowReportShop(DelWxPmFollowReportShop delWxPmFollowReportShop) throws TsfaServiceException {
		logger.debug("delWxPmFollowReportShop(DelWxPmFollowReportShop delWxPmFollowReportShop={}) - start", delWxPmFollowReportShop); 

		AssertUtils.notNull(delWxPmFollowReportShop);
		AssertUtils.notNull(delWxPmFollowReportShop.getCode(),"Code不能为空！");
		try {
			wxPmFollowReportShopDao.deleteByPrimaryKey(delWxPmFollowReportShop.getCode());
			DelWxPmFollowReportShopReturn delWxPmFollowReportShopReturn  = new DelWxPmFollowReportShopReturn();

			logger.debug("delWxPmFollowReportShop(DelWxPmFollowReportShop) - end - return value={}", delWxPmFollowReportShopReturn); //$NON-NLS-1$
			return delWxPmFollowReportShopReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除门店微信客户跟踪日报信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_SHOP_DEL_ERROR,"删除门店微信客户跟踪日报信息错误！",e);
		}
	}

	@Override
	public UpdateWxPmFollowReportShopReturn updateWxPmFollowReportShop(UpdateWxPmFollowReportShop updateWxPmFollowReportShop) throws TsfaServiceException {
		logger.debug("updateWxPmFollowReportShop(UpdateWxPmFollowReportShop updateWxPmFollowReportShop={}) - start", updateWxPmFollowReportShop); //$NON-NLS-1$

		AssertUtils.notNull(updateWxPmFollowReportShop);
		AssertUtils.notNullAndEmpty(updateWxPmFollowReportShop.getCode(),"Code不能为空");
		try {
			WxPmFollowReportShop wxPmFollowReportShop = new WxPmFollowReportShop();
			//update数据录入
			wxPmFollowReportShop.setCode(updateWxPmFollowReportShop.getCode());
			wxPmFollowReportShop.setReportDate(updateWxPmFollowReportShop.getReportDate());
			wxPmFollowReportShop.setMerchantNo(updateWxPmFollowReportShop.getMerchantNo());
			wxPmFollowReportShop.setMerchantName(updateWxPmFollowReportShop.getMerchantName());
			wxPmFollowReportShop.setCompanyNo(updateWxPmFollowReportShop.getCompanyNo());
			wxPmFollowReportShop.setCompanyName(updateWxPmFollowReportShop.getCompanyName());
			wxPmFollowReportShop.setDealerCode(updateWxPmFollowReportShop.getDealerCode());
			wxPmFollowReportShop.setShopNo(updateWxPmFollowReportShop.getShopNo());
			wxPmFollowReportShop.setShopName(updateWxPmFollowReportShop.getShopName());
			wxPmFollowReportShop.setShopCode(updateWxPmFollowReportShop.getShopCode());
			wxPmFollowReportShop.setNumPmNew(updateWxPmFollowReportShop.getNumPmNew());
			wxPmFollowReportShop.setNumPmNewNotFollow(updateWxPmFollowReportShop.getNumPmNewNotFollow());
			wxPmFollowReportShop.setNumPmNewFollow(updateWxPmFollowReportShop.getNumPmNewFollow());
			wxPmFollowReportShop.setNumPmOld(updateWxPmFollowReportShop.getNumPmOld());
			wxPmFollowReportShop.setNumPmOldNotFollow(updateWxPmFollowReportShop.getNumPmOldNotFollow());
			wxPmFollowReportShop.setNumPmOldFollow(updateWxPmFollowReportShop.getNumPmOldFollow());
			wxPmFollowReportShop.setNumPmTotal(updateWxPmFollowReportShop.getNumPmTotal());
			wxPmFollowReportShop.setUpdateDate(new Date());
			wxPmFollowReportShop.setRemark(updateWxPmFollowReportShop.getRemark());
			AssertUtils.notUpdateMoreThanOne(wxPmFollowReportShopDao.updateByPrimaryKeySelective(wxPmFollowReportShop));
			UpdateWxPmFollowReportShopReturn updateWxPmFollowReportShopReturn = new UpdateWxPmFollowReportShopReturn();

			logger.debug("updateWxPmFollowReportShop(UpdateWxPmFollowReportShop) - end - return value={}", updateWxPmFollowReportShopReturn); //$NON-NLS-1$
			return updateWxPmFollowReportShopReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("门店微信客户跟踪日报信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_SHOP_UPDATE_ERROR,"门店微信客户跟踪日报信息更新信息错误！",e);
		}
	}

	@Override
	public FindWxPmFollowReportShopReturn findWxPmFollowReportShop(FindWxPmFollowReportShop findWxPmFollowReportShop) throws TsfaServiceException {
		logger.debug("findWxPmFollowReportShop(FindWxPmFollowReportShop findWxPmFollowReportShop={}) - start", findWxPmFollowReportShop); //$NON-NLS-1$

		AssertUtils.notNull(findWxPmFollowReportShop);
		AssertUtils.notAllNull(findWxPmFollowReportShop.getCode(),"Code不能为空");
		try {
			WxPmFollowReportShop wxPmFollowReportShop = wxPmFollowReportShopDao.selectByPrimaryKey(findWxPmFollowReportShop.getCode());
			if(wxPmFollowReportShop == null){
				throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_SHOP_NOT_EXIST_ERROR,"门店微信客户跟踪日报信息不存在");
			}
			FindWxPmFollowReportShopReturn findWxPmFollowReportShopReturn = new FindWxPmFollowReportShopReturn();
			//find数据录入
			findWxPmFollowReportShopReturn.setCode(wxPmFollowReportShop.getCode());
			findWxPmFollowReportShopReturn.setReportDate(wxPmFollowReportShop.getReportDate());
			findWxPmFollowReportShopReturn.setMerchantNo(wxPmFollowReportShop.getMerchantNo());
			findWxPmFollowReportShopReturn.setMerchantName(wxPmFollowReportShop.getMerchantName());
			findWxPmFollowReportShopReturn.setCompanyNo(wxPmFollowReportShop.getCompanyNo());
			findWxPmFollowReportShopReturn.setCompanyName(wxPmFollowReportShop.getCompanyName());
			findWxPmFollowReportShopReturn.setDealerCode(wxPmFollowReportShop.getDealerCode());
			findWxPmFollowReportShopReturn.setShopNo(wxPmFollowReportShop.getShopNo());
			findWxPmFollowReportShopReturn.setShopName(wxPmFollowReportShop.getShopName());
			findWxPmFollowReportShopReturn.setShopCode(wxPmFollowReportShop.getShopCode());
			findWxPmFollowReportShopReturn.setNumPmNew(wxPmFollowReportShop.getNumPmNew());
			findWxPmFollowReportShopReturn.setNumPmNewNotFollow(wxPmFollowReportShop.getNumPmNewNotFollow());
			findWxPmFollowReportShopReturn.setNumPmNewFollow(wxPmFollowReportShop.getNumPmNewFollow());
			findWxPmFollowReportShopReturn.setNumPmOld(wxPmFollowReportShop.getNumPmOld());
			findWxPmFollowReportShopReturn.setNumPmOldNotFollow(wxPmFollowReportShop.getNumPmOldNotFollow());
			findWxPmFollowReportShopReturn.setNumPmOldFollow(wxPmFollowReportShop.getNumPmOldFollow());
			findWxPmFollowReportShopReturn.setNumPmTotal(wxPmFollowReportShop.getNumPmTotal());
			findWxPmFollowReportShopReturn.setCreateDate(wxPmFollowReportShop.getCreateDate());
			findWxPmFollowReportShopReturn.setUpdateDate(wxPmFollowReportShop.getUpdateDate());
			findWxPmFollowReportShopReturn.setRemark(wxPmFollowReportShop.getRemark());
			
			logger.debug("findWxPmFollowReportShop(FindWxPmFollowReportShop) - end - return value={}", findWxPmFollowReportShopReturn); //$NON-NLS-1$
			return findWxPmFollowReportShopReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找门店微信客户跟踪日报信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_SHOP_FIND_ERROR,"查找门店微信客户跟踪日报信息信息错误！",e);
		}
	}

	@Override
	public Page<FindWxPmFollowReportShopPageReturn> findWxPmFollowReportShopPage(FindWxPmFollowReportShopPage findWxPmFollowReportShopPage) throws TsfaServiceException {
		logger.debug("findWxPmFollowReportShopPage(FindWxPmFollowReportShopPage findWxPmFollowReportShopPage={}) - start", findWxPmFollowReportShopPage); //$NON-NLS-1$

		AssertUtils.notNull(findWxPmFollowReportShopPage);
		List<FindWxPmFollowReportShopPageReturn> returnList = null;
		int count = 0;
		try {
			count = wxPmFollowReportShopDao.findWxPmFollowReportShopPageCount(findWxPmFollowReportShopPage);
			if(count > 0) {
				returnList = wxPmFollowReportShopDao.findWxPmFollowReportShopPage(findWxPmFollowReportShopPage);
			}
		}  catch (Exception e) {
			logger.error("门店微信客户跟踪日报信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_SHOP_FIND_PAGE_ERROR,"门店微信客户跟踪日报信息不存在错误.！",e);
		}
		Page<FindWxPmFollowReportShopPageReturn> returnPage = new Page<FindWxPmFollowReportShopPageReturn>(returnList, count, findWxPmFollowReportShopPage);

		logger.debug("findWxPmFollowReportShopPage(FindWxPmFollowReportShopPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 
}
