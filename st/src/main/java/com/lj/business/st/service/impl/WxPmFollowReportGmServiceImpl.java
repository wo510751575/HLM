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
import com.lj.business.st.dao.IWxPmFollowReportGmDao;
import com.lj.business.st.domain.WxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportGmReturn;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportGmReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmPageReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportGmReturn;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportGm;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportGmReturn;
import com.lj.business.st.service.IWxPmFollowReportGmService;

/**
 * 
 * 
 * 类说明：导购微信客户跟踪日报实现类
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
public class WxPmFollowReportGmServiceImpl implements IWxPmFollowReportGmService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxPmFollowReportGmServiceImpl.class);
	
	@Resource
	private IWxPmFollowReportGmDao wxPmFollowReportGmDao;
	
	@Override
	public AddWxPmFollowReportGmReturn addWxPmFollowReportGm(AddWxPmFollowReportGm addWxPmFollowReportGm) throws TsfaServiceException {
		logger.debug("addWxPmFollowReportGm(AddWxPmFollowReportGm addWxPmFollowReportGm={}) - start", addWxPmFollowReportGm); 

		AssertUtils.notNull(addWxPmFollowReportGm);
		try {
			WxPmFollowReportGm wxPmFollowReportGm = new WxPmFollowReportGm();
			//add数据录入
			wxPmFollowReportGm.setCode(GUID.getPreUUID());
			wxPmFollowReportGm.setReportDate(addWxPmFollowReportGm.getReportDate());
			wxPmFollowReportGm.setMerchantNo(addWxPmFollowReportGm.getMerchantNo());
			wxPmFollowReportGm.setMerchantName(addWxPmFollowReportGm.getMerchantName());
			wxPmFollowReportGm.setCompanyNo(addWxPmFollowReportGm.getCompanyNo());
			wxPmFollowReportGm.setCompanyName(addWxPmFollowReportGm.getCompanyName());
			wxPmFollowReportGm.setDealerCode(addWxPmFollowReportGm.getDealerCode());
			wxPmFollowReportGm.setShopNo(addWxPmFollowReportGm.getShopNo());
			wxPmFollowReportGm.setShopName(addWxPmFollowReportGm.getShopName());
			wxPmFollowReportGm.setShopCode(addWxPmFollowReportGm.getShopCode());
			wxPmFollowReportGm.setMemberNoGm(addWxPmFollowReportGm.getMemberNoGm());
			wxPmFollowReportGm.setMemberNameGm(addWxPmFollowReportGm.getMemberNameGm());
			wxPmFollowReportGm.setHeadAddress(addWxPmFollowReportGm.getHeadAddress());
			wxPmFollowReportGm.setNumPmNew(addWxPmFollowReportGm.getNumPmNew());
			wxPmFollowReportGm.setNumPmNewNotFollow(addWxPmFollowReportGm.getNumPmNewNotFollow());
			wxPmFollowReportGm.setNumPmNewFollow(addWxPmFollowReportGm.getNumPmNewFollow());
			wxPmFollowReportGm.setNumPmOld(addWxPmFollowReportGm.getNumPmOld());
			wxPmFollowReportGm.setNumPmOldNotFollow(addWxPmFollowReportGm.getNumPmOldNotFollow());
			wxPmFollowReportGm.setNumPmOldFollow(addWxPmFollowReportGm.getNumPmOldFollow());
			wxPmFollowReportGm.setNumPmTotal(addWxPmFollowReportGm.getNumPmTotal());
			wxPmFollowReportGm.setCreateDate(new Date());
			wxPmFollowReportGm.setUpdateDate(wxPmFollowReportGm.getCreateDate());
			wxPmFollowReportGm.setRemark(addWxPmFollowReportGm.getRemark());
			wxPmFollowReportGmDao.insert(wxPmFollowReportGm);
			AddWxPmFollowReportGmReturn addWxPmFollowReportGmReturn = new AddWxPmFollowReportGmReturn();
			
			logger.debug("addWxPmFollowReportGm(AddWxPmFollowReportGm) - end - return value={}", addWxPmFollowReportGmReturn); 
			return addWxPmFollowReportGmReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购微信客户跟踪日报信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_GM_ADD_ERROR,"新增导购微信客户跟踪日报信息错误！",e);
		}
	}
	
	@Override
	public DelWxPmFollowReportGmReturn delWxPmFollowReportGm(DelWxPmFollowReportGm delWxPmFollowReportGm) throws TsfaServiceException {
		logger.debug("delWxPmFollowReportGm(DelWxPmFollowReportGm delWxPmFollowReportGm={}) - start", delWxPmFollowReportGm); 

		AssertUtils.notNull(delWxPmFollowReportGm);
		AssertUtils.notNull(delWxPmFollowReportGm.getCode(),"Code不能为空！");
		try {
			wxPmFollowReportGmDao.deleteByPrimaryKey(delWxPmFollowReportGm.getCode());
			DelWxPmFollowReportGmReturn delWxPmFollowReportGmReturn  = new DelWxPmFollowReportGmReturn();

			logger.debug("delWxPmFollowReportGm(DelWxPmFollowReportGm) - end - return value={}", delWxPmFollowReportGmReturn); //$NON-NLS-1$
			return delWxPmFollowReportGmReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除导购微信客户跟踪日报信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_GM_DEL_ERROR,"删除导购微信客户跟踪日报信息错误！",e);
		}
	}

	@Override
	public UpdateWxPmFollowReportGmReturn updateWxPmFollowReportGm(UpdateWxPmFollowReportGm updateWxPmFollowReportGm) throws TsfaServiceException {
		logger.debug("updateWxPmFollowReportGm(UpdateWxPmFollowReportGm updateWxPmFollowReportGm={}) - start", updateWxPmFollowReportGm); //$NON-NLS-1$

		AssertUtils.notNull(updateWxPmFollowReportGm);
		AssertUtils.notNullAndEmpty(updateWxPmFollowReportGm.getCode(),"Code不能为空");
		try {
			WxPmFollowReportGm wxPmFollowReportGm = new WxPmFollowReportGm();
			//update数据录入
			wxPmFollowReportGm.setCode(updateWxPmFollowReportGm.getCode());
			wxPmFollowReportGm.setReportDate(updateWxPmFollowReportGm.getReportDate());
			wxPmFollowReportGm.setMerchantNo(updateWxPmFollowReportGm.getMerchantNo());
			wxPmFollowReportGm.setMerchantName(updateWxPmFollowReportGm.getMerchantName());
			wxPmFollowReportGm.setCompanyNo(updateWxPmFollowReportGm.getCompanyNo());
			wxPmFollowReportGm.setCompanyName(updateWxPmFollowReportGm.getCompanyName());
			wxPmFollowReportGm.setDealerCode(updateWxPmFollowReportGm.getDealerCode());
			wxPmFollowReportGm.setShopNo(updateWxPmFollowReportGm.getShopNo());
			wxPmFollowReportGm.setShopName(updateWxPmFollowReportGm.getShopName());
			wxPmFollowReportGm.setShopCode(updateWxPmFollowReportGm.getShopCode());
			wxPmFollowReportGm.setMemberNoGm(updateWxPmFollowReportGm.getMemberNoGm());
			wxPmFollowReportGm.setMemberNameGm(updateWxPmFollowReportGm.getMemberNameGm());
			wxPmFollowReportGm.setHeadAddress(updateWxPmFollowReportGm.getHeadAddress());
			wxPmFollowReportGm.setNumPmNew(updateWxPmFollowReportGm.getNumPmNew());
			wxPmFollowReportGm.setNumPmNewNotFollow(updateWxPmFollowReportGm.getNumPmNewNotFollow());
			wxPmFollowReportGm.setNumPmNewFollow(updateWxPmFollowReportGm.getNumPmNewFollow());
			wxPmFollowReportGm.setNumPmOld(updateWxPmFollowReportGm.getNumPmOld());
			wxPmFollowReportGm.setNumPmOldNotFollow(updateWxPmFollowReportGm.getNumPmOldNotFollow());
			wxPmFollowReportGm.setNumPmOldFollow(updateWxPmFollowReportGm.getNumPmOldFollow());
			wxPmFollowReportGm.setNumPmTotal(updateWxPmFollowReportGm.getNumPmTotal());
			wxPmFollowReportGm.setUpdateDate(new Date());
			wxPmFollowReportGm.setRemark(updateWxPmFollowReportGm.getRemark());
			AssertUtils.notUpdateMoreThanOne(wxPmFollowReportGmDao.updateByPrimaryKeySelective(wxPmFollowReportGm));
			UpdateWxPmFollowReportGmReturn updateWxPmFollowReportGmReturn = new UpdateWxPmFollowReportGmReturn();

			logger.debug("updateWxPmFollowReportGm(UpdateWxPmFollowReportGm) - end - return value={}", updateWxPmFollowReportGmReturn); //$NON-NLS-1$
			return updateWxPmFollowReportGmReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购微信客户跟踪日报信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_GM_UPDATE_ERROR,"导购微信客户跟踪日报信息更新信息错误！",e);
		}
	}

	@Override
	public FindWxPmFollowReportGmReturn findWxPmFollowReportGm(FindWxPmFollowReportGm findWxPmFollowReportGm) throws TsfaServiceException {
		logger.debug("findWxPmFollowReportGm(FindWxPmFollowReportGm findWxPmFollowReportGm={}) - start", findWxPmFollowReportGm); //$NON-NLS-1$

		AssertUtils.notNull(findWxPmFollowReportGm);
		AssertUtils.notAllNull(findWxPmFollowReportGm.getCode(),"Code不能为空");
		try {
			WxPmFollowReportGm wxPmFollowReportGm = wxPmFollowReportGmDao.selectByPrimaryKey(findWxPmFollowReportGm.getCode());
			if(wxPmFollowReportGm == null){
				throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_GM_NOT_EXIST_ERROR,"导购微信客户跟踪日报信息不存在");
			}
			FindWxPmFollowReportGmReturn findWxPmFollowReportGmReturn = new FindWxPmFollowReportGmReturn();
			//find数据录入
			findWxPmFollowReportGmReturn.setCode(wxPmFollowReportGm.getCode());
			findWxPmFollowReportGmReturn.setReportDate(wxPmFollowReportGm.getReportDate());
			findWxPmFollowReportGmReturn.setMerchantNo(wxPmFollowReportGm.getMerchantNo());
			findWxPmFollowReportGmReturn.setMerchantName(wxPmFollowReportGm.getMerchantName());
			findWxPmFollowReportGmReturn.setCompanyNo(wxPmFollowReportGm.getCompanyNo());
			findWxPmFollowReportGmReturn.setCompanyName(wxPmFollowReportGm.getCompanyName());
			findWxPmFollowReportGmReturn.setDealerCode(wxPmFollowReportGm.getDealerCode());
			findWxPmFollowReportGmReturn.setShopNo(wxPmFollowReportGm.getShopNo());
			findWxPmFollowReportGmReturn.setShopName(wxPmFollowReportGm.getShopName());
			findWxPmFollowReportGmReturn.setShopCode(wxPmFollowReportGm.getShopCode());
			findWxPmFollowReportGmReturn.setMemberNoGm(wxPmFollowReportGm.getMemberNoGm());
			findWxPmFollowReportGmReturn.setMemberNameGm(wxPmFollowReportGm.getMemberNameGm());
			findWxPmFollowReportGmReturn.setHeadAddress(wxPmFollowReportGm.getHeadAddress());
			findWxPmFollowReportGmReturn.setNumPmNew(wxPmFollowReportGm.getNumPmNew());
			findWxPmFollowReportGmReturn.setNumPmNewNotFollow(wxPmFollowReportGm.getNumPmNewNotFollow());
			findWxPmFollowReportGmReturn.setNumPmNewFollow(wxPmFollowReportGm.getNumPmNewFollow());
			findWxPmFollowReportGmReturn.setNumPmOld(wxPmFollowReportGm.getNumPmOld());
			findWxPmFollowReportGmReturn.setNumPmOldNotFollow(wxPmFollowReportGm.getNumPmOldNotFollow());
			findWxPmFollowReportGmReturn.setNumPmOldFollow(wxPmFollowReportGm.getNumPmOldFollow());
			findWxPmFollowReportGmReturn.setNumPmTotal(wxPmFollowReportGm.getNumPmTotal());
			findWxPmFollowReportGmReturn.setCreateDate(wxPmFollowReportGm.getCreateDate());
			findWxPmFollowReportGmReturn.setUpdateDate(wxPmFollowReportGm.getUpdateDate());
			findWxPmFollowReportGmReturn.setRemark(wxPmFollowReportGm.getRemark());
			
			logger.debug("findWxPmFollowReportGm(FindWxPmFollowReportGm) - end - return value={}", findWxPmFollowReportGmReturn); //$NON-NLS-1$
			return findWxPmFollowReportGmReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找导购微信客户跟踪日报信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_GM_FIND_ERROR,"查找导购微信客户跟踪日报信息信息错误！",e);
		}
	}

	@Override
	public Page<FindWxPmFollowReportGmPageReturn> findWxPmFollowReportGmPage(FindWxPmFollowReportGmPage findWxPmFollowReportGmPage) throws TsfaServiceException {
		logger.debug("findWxPmFollowReportGmPage(FindWxPmFollowReportGmPage findWxPmFollowReportGmPage={}) - start", findWxPmFollowReportGmPage); //$NON-NLS-1$

		AssertUtils.notNull(findWxPmFollowReportGmPage);
		List<FindWxPmFollowReportGmPageReturn> returnList = null;
		int count = 0;
		try {
			count = wxPmFollowReportGmDao.findWxPmFollowReportGmPageCount(findWxPmFollowReportGmPage);
			if(count > 0) {
				returnList = wxPmFollowReportGmDao.findWxPmFollowReportGmPage(findWxPmFollowReportGmPage);
			}
		}  catch (Exception e) {
			logger.error("导购微信客户跟踪日报信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_GM_FIND_PAGE_ERROR,"导购微信客户跟踪日报信息不存在错误.！",e);
		}
		Page<FindWxPmFollowReportGmPageReturn> returnPage = new Page<FindWxPmFollowReportGmPageReturn>(returnList, count, findWxPmFollowReportGmPage);

		logger.debug("findWxPmFollowReportGmPage(FindWxPmFollowReportGmPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 
}
