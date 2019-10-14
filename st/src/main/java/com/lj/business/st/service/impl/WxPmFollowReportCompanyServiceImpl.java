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
import com.lj.business.st.dao.IWxPmFollowReportCompanyDao;
import com.lj.business.st.domain.WxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.AddWxPmFollowReportCompanyReturn;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.DelWxPmFollowReportCompanyReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyPage;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyPageReturn;
import com.lj.business.st.dto.wxPmFollow.FindWxPmFollowReportCompanyReturn;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportCompany;
import com.lj.business.st.dto.wxPmFollow.UpdateWxPmFollowReportCompanyReturn;
import com.lj.business.st.service.IWxPmFollowReportCompanyService;

/**
 * 
 * 
 * 类说明：经销商微信客户跟踪日报实现类
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
public class WxPmFollowReportCompanyServiceImpl implements IWxPmFollowReportCompanyService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxPmFollowReportCompanyServiceImpl.class);
	
	@Resource
	private IWxPmFollowReportCompanyDao wxPmFollowReportCompanyDao;
	
	@Override
	public AddWxPmFollowReportCompanyReturn addWxPmFollowReportCompany(AddWxPmFollowReportCompany addWxPmFollowReportCompany) throws TsfaServiceException {
		logger.debug("addWxPmFollowReportCompany(AddWxPmFollowReportCompany addWxPmFollowReportCompany={}) - start", addWxPmFollowReportCompany); 

		AssertUtils.notNull(addWxPmFollowReportCompany);
		try {
			WxPmFollowReportCompany wxPmFollowReportCompany = new WxPmFollowReportCompany();
			//add数据录入
			wxPmFollowReportCompany.setCode(GUID.getPreUUID());
			wxPmFollowReportCompany.setReportDate(addWxPmFollowReportCompany.getReportDate());
			wxPmFollowReportCompany.setMerchantNo(addWxPmFollowReportCompany.getMerchantNo());
			wxPmFollowReportCompany.setMerchantName(addWxPmFollowReportCompany.getMerchantName());
			wxPmFollowReportCompany.setCompanyNo(addWxPmFollowReportCompany.getCompanyNo());
			wxPmFollowReportCompany.setCompanyName(addWxPmFollowReportCompany.getCompanyName());
			wxPmFollowReportCompany.setDealerCode(addWxPmFollowReportCompany.getDealerCode());
			wxPmFollowReportCompany.setNumPmNew(addWxPmFollowReportCompany.getNumPmNew());
			wxPmFollowReportCompany.setNumPmNewNotFollow(addWxPmFollowReportCompany.getNumPmNewNotFollow());
			wxPmFollowReportCompany.setNumPmNewFollow(addWxPmFollowReportCompany.getNumPmNewFollow());
			wxPmFollowReportCompany.setNumPmOld(addWxPmFollowReportCompany.getNumPmOld());
			wxPmFollowReportCompany.setNumPmOldNotFollow(addWxPmFollowReportCompany.getNumPmOldNotFollow());
			wxPmFollowReportCompany.setNumPmOldFollow(addWxPmFollowReportCompany.getNumPmOldFollow());
			wxPmFollowReportCompany.setNumPmTotal(addWxPmFollowReportCompany.getNumPmTotal());
			wxPmFollowReportCompany.setCreateDate(new Date());
			wxPmFollowReportCompany.setUpdateDate(wxPmFollowReportCompany.getCreateDate());
			wxPmFollowReportCompany.setRemark(addWxPmFollowReportCompany.getRemark());
			wxPmFollowReportCompanyDao.insert(wxPmFollowReportCompany);
			AddWxPmFollowReportCompanyReturn addWxPmFollowReportCompanyReturn = new AddWxPmFollowReportCompanyReturn();
			
			logger.debug("addWxPmFollowReportCompany(AddWxPmFollowReportCompany) - end - return value={}", addWxPmFollowReportCompanyReturn); 
			return addWxPmFollowReportCompanyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增经销商微信客户跟踪日报信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_COMPANY_ADD_ERROR,"新增经销商微信客户跟踪日报信息错误！",e);
		}
	}
	
	@Override
	public DelWxPmFollowReportCompanyReturn delWxPmFollowReportCompany(DelWxPmFollowReportCompany delWxPmFollowReportCompany) throws TsfaServiceException {
		logger.debug("delWxPmFollowReportCompany(DelWxPmFollowReportCompany delWxPmFollowReportCompany={}) - start", delWxPmFollowReportCompany); 

		AssertUtils.notNull(delWxPmFollowReportCompany);
		AssertUtils.notNull(delWxPmFollowReportCompany.getCode(),"Code不能为空！");
		try {
			wxPmFollowReportCompanyDao.deleteByPrimaryKey(delWxPmFollowReportCompany.getCode());
			DelWxPmFollowReportCompanyReturn delWxPmFollowReportCompanyReturn  = new DelWxPmFollowReportCompanyReturn();

			logger.debug("delWxPmFollowReportCompany(DelWxPmFollowReportCompany) - end - return value={}", delWxPmFollowReportCompanyReturn); //$NON-NLS-1$
			return delWxPmFollowReportCompanyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除经销商微信客户跟踪日报信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_COMPANY_DEL_ERROR,"删除经销商微信客户跟踪日报信息错误！",e);
		}
	}

	@Override
	public UpdateWxPmFollowReportCompanyReturn updateWxPmFollowReportCompany(UpdateWxPmFollowReportCompany updateWxPmFollowReportCompany) throws TsfaServiceException {
		logger.debug("updateWxPmFollowReportCompany(UpdateWxPmFollowReportCompany updateWxPmFollowReportCompany={}) - start", updateWxPmFollowReportCompany); //$NON-NLS-1$

		AssertUtils.notNull(updateWxPmFollowReportCompany);
		AssertUtils.notNullAndEmpty(updateWxPmFollowReportCompany.getCode(),"Code不能为空");
		try {
			WxPmFollowReportCompany wxPmFollowReportCompany = new WxPmFollowReportCompany();
			//update数据录入
			wxPmFollowReportCompany.setCode(updateWxPmFollowReportCompany.getCode());
			wxPmFollowReportCompany.setReportDate(updateWxPmFollowReportCompany.getReportDate());
			wxPmFollowReportCompany.setMerchantNo(updateWxPmFollowReportCompany.getMerchantNo());
			wxPmFollowReportCompany.setMerchantName(updateWxPmFollowReportCompany.getMerchantName());
			wxPmFollowReportCompany.setCompanyNo(updateWxPmFollowReportCompany.getCompanyNo());
			wxPmFollowReportCompany.setCompanyName(updateWxPmFollowReportCompany.getCompanyName());
			wxPmFollowReportCompany.setDealerCode(updateWxPmFollowReportCompany.getDealerCode());
			wxPmFollowReportCompany.setNumPmNew(updateWxPmFollowReportCompany.getNumPmNew());
			wxPmFollowReportCompany.setNumPmNewNotFollow(updateWxPmFollowReportCompany.getNumPmNewNotFollow());
			wxPmFollowReportCompany.setNumPmNewFollow(updateWxPmFollowReportCompany.getNumPmNewFollow());
			wxPmFollowReportCompany.setNumPmOld(updateWxPmFollowReportCompany.getNumPmOld());
			wxPmFollowReportCompany.setNumPmOldNotFollow(updateWxPmFollowReportCompany.getNumPmOldNotFollow());
			wxPmFollowReportCompany.setNumPmOldFollow(updateWxPmFollowReportCompany.getNumPmOldFollow());
			wxPmFollowReportCompany.setNumPmTotal(updateWxPmFollowReportCompany.getNumPmTotal());
			wxPmFollowReportCompany.setUpdateDate(new Date());
			wxPmFollowReportCompany.setRemark(updateWxPmFollowReportCompany.getRemark());
			AssertUtils.notUpdateMoreThanOne(wxPmFollowReportCompanyDao.updateByPrimaryKeySelective(wxPmFollowReportCompany));
			UpdateWxPmFollowReportCompanyReturn updateWxPmFollowReportCompanyReturn = new UpdateWxPmFollowReportCompanyReturn();

			logger.debug("updateWxPmFollowReportCompany(UpdateWxPmFollowReportCompany) - end - return value={}", updateWxPmFollowReportCompanyReturn); //$NON-NLS-1$
			return updateWxPmFollowReportCompanyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("经销商微信客户跟踪日报信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_COMPANY_UPDATE_ERROR,"经销商微信客户跟踪日报信息更新信息错误！",e);
		}
	}

	@Override
	public FindWxPmFollowReportCompanyReturn findWxPmFollowReportCompany(FindWxPmFollowReportCompany findWxPmFollowReportCompany) throws TsfaServiceException {
		logger.debug("findWxPmFollowReportCompany(FindWxPmFollowReportCompany findWxPmFollowReportCompany={}) - start", findWxPmFollowReportCompany); //$NON-NLS-1$

		AssertUtils.notNull(findWxPmFollowReportCompany);
		AssertUtils.notAllNull(findWxPmFollowReportCompany.getCode(),"Code不能为空");
		try {
			WxPmFollowReportCompany wxPmFollowReportCompany = wxPmFollowReportCompanyDao.selectByPrimaryKey(findWxPmFollowReportCompany.getCode());
			if(wxPmFollowReportCompany == null){
				throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_COMPANY_NOT_EXIST_ERROR,"经销商微信客户跟踪日报信息不存在");
			}
			FindWxPmFollowReportCompanyReturn findWxPmFollowReportCompanyReturn = new FindWxPmFollowReportCompanyReturn();
			//find数据录入
			findWxPmFollowReportCompanyReturn.setCode(wxPmFollowReportCompany.getCode());
			findWxPmFollowReportCompanyReturn.setReportDate(wxPmFollowReportCompany.getReportDate());
			findWxPmFollowReportCompanyReturn.setMerchantNo(wxPmFollowReportCompany.getMerchantNo());
			findWxPmFollowReportCompanyReturn.setMerchantName(wxPmFollowReportCompany.getMerchantName());
			findWxPmFollowReportCompanyReturn.setCompanyNo(wxPmFollowReportCompany.getCompanyNo());
			findWxPmFollowReportCompanyReturn.setCompanyName(wxPmFollowReportCompany.getCompanyName());
			findWxPmFollowReportCompanyReturn.setDealerCode(wxPmFollowReportCompany.getDealerCode());
			findWxPmFollowReportCompanyReturn.setNumPmNew(wxPmFollowReportCompany.getNumPmNew());
			findWxPmFollowReportCompanyReturn.setNumPmNewNotFollow(wxPmFollowReportCompany.getNumPmNewNotFollow());
			findWxPmFollowReportCompanyReturn.setNumPmNewFollow(wxPmFollowReportCompany.getNumPmNewFollow());
			findWxPmFollowReportCompanyReturn.setNumPmOld(wxPmFollowReportCompany.getNumPmOld());
			findWxPmFollowReportCompanyReturn.setNumPmOldNotFollow(wxPmFollowReportCompany.getNumPmOldNotFollow());
			findWxPmFollowReportCompanyReturn.setNumPmOldFollow(wxPmFollowReportCompany.getNumPmOldFollow());
			findWxPmFollowReportCompanyReturn.setNumPmTotal(wxPmFollowReportCompany.getNumPmTotal());
			findWxPmFollowReportCompanyReturn.setCreateDate(wxPmFollowReportCompany.getCreateDate());
			findWxPmFollowReportCompanyReturn.setUpdateDate(wxPmFollowReportCompany.getUpdateDate());
			findWxPmFollowReportCompanyReturn.setRemark(wxPmFollowReportCompany.getRemark());
			
			logger.debug("findWxPmFollowReportCompany(FindWxPmFollowReportCompany) - end - return value={}", findWxPmFollowReportCompanyReturn); //$NON-NLS-1$
			return findWxPmFollowReportCompanyReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找经销商微信客户跟踪日报信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_COMPANY_FIND_ERROR,"查找经销商微信客户跟踪日报信息信息错误！",e);
		}
	}

	@Override
	public Page<FindWxPmFollowReportCompanyPageReturn> findWxPmFollowReportCompanyPage(FindWxPmFollowReportCompanyPage findWxPmFollowReportCompanyPage) throws TsfaServiceException {
		logger.debug("findWxPmFollowReportCompanyPage(FindWxPmFollowReportCompanyPage findWxPmFollowReportCompanyPage={}) - start", findWxPmFollowReportCompanyPage); //$NON-NLS-1$

		AssertUtils.notNull(findWxPmFollowReportCompanyPage);
		List<FindWxPmFollowReportCompanyPageReturn> returnList = null;
		int count = 0;
		try {
			count = wxPmFollowReportCompanyDao.findWxPmFollowReportCompanyPageCount(findWxPmFollowReportCompanyPage);
			if(count > 0) {
				returnList = wxPmFollowReportCompanyDao.findWxPmFollowReportCompanyPage(findWxPmFollowReportCompanyPage);
			}
		}  catch (Exception e) {
			logger.error("经销商微信客户跟踪日报信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.WX_PM_FOLLOW_REPORT_COMPANY_FIND_PAGE_ERROR,"经销商微信客户跟踪日报信息不存在错误.！",e);
		}
		Page<FindWxPmFollowReportCompanyPageReturn> returnPage = new Page<FindWxPmFollowReportCompanyPageReturn>(returnList, count, findWxPmFollowReportCompanyPage);

		logger.debug("findWxPmFollowReportCompanyPage(FindWxPmFollowReportCompanyPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 
}
