package com.ye.business.hx.service.impl;

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
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IBillRefundDetailDao;
import com.ye.business.hx.domain.BillRefundDetail;
import com.ye.business.hx.dto.BillRefundDetailDto;
import com.ye.business.hx.dto.FindBillRefundDetailPage;
import com.ye.business.hx.service.IBillRefundDetailService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
@Service
public class BillRefundDetailServiceImpl implements IBillRefundDetailService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BillRefundDetailServiceImpl.class);
	

	@Resource
	private IBillRefundDetailDao billRefundDetailDao;
	
	
	@Override
	public void addBillRefundDetail(
			BillRefundDetailDto billRefundDetailDto) throws TsfaServiceException {
		logger.debug("addBillRefundDetail(AddBillRefundDetail addBillRefundDetail={}) - start", billRefundDetailDto); 

		AssertUtils.notNull(billRefundDetailDto);
		try {
			BillRefundDetail billRefundDetail = new BillRefundDetail();
			//add数据录入
			billRefundDetail.setCode(GUID.getPreUUID());
			billRefundDetail.setBillCode(billRefundDetailDto.getBillCode());
			billRefundDetail.setRefundCode(billRefundDetailDto.getRefundCode());
			billRefundDetail.setOperateCode(billRefundDetailDto.getOperateCode());
			billRefundDetail.setShopNo(billRefundDetailDto.getShopNo());
			billRefundDetail.setShopName(billRefundDetailDto.getShopName());
			billRefundDetail.setMerchantNo(billRefundDetailDto.getMerchantNo());
			billRefundDetail.setMerchantName(billRefundDetailDto.getMerchantName());
			billRefundDetail.setProjectCode(billRefundDetailDto.getProjectCode());
			billRefundDetail.setProjectName(billRefundDetailDto.getProjectName());
			billRefundDetail.setItemNum(billRefundDetailDto.getItemNum());
			billRefundDetail.setReturnAmount(billRefundDetailDto.getReturnAmount());
			billRefundDetail.setUpdateId(billRefundDetailDto.getUpdateId());
			billRefundDetail.setUpdateDate(new Date());
			billRefundDetail.setCreateId(billRefundDetailDto.getCreateId());
			billRefundDetail.setCreateDate(new Date());
			billRefundDetail.setRemark(billRefundDetailDto.getRemark());
			billRefundDetail.setRemark1(billRefundDetailDto.getRemark1());
			billRefundDetail.setRemark2(billRefundDetailDto.getRemark2());
			billRefundDetail.setRemark3(billRefundDetailDto.getRemark3());
			billRefundDetail.setRemark4(billRefundDetailDto.getRemark4());
			billRefundDetailDao.insertSelective(billRefundDetail);
			logger.debug("addBillRefundDetail(BillRefundDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单退款详情信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_DETAIL_ADD_ERROR,"新增账单退款详情信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询账单退款详情信息
	 *
	 * @param findBillRefundDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<BillRefundDetailDto>  findBillRefundDetails(FindBillRefundDetailPage findBillRefundDetailPage)throws TsfaServiceException{
		AssertUtils.notNull(findBillRefundDetailPage);
		List<BillRefundDetailDto> returnList=null;
		try {
			returnList = billRefundDetailDao.findBillRefundDetails(findBillRefundDetailPage);
		} catch (Exception e) {
			logger.error("查找账单退款详情信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_DETAIL_NOT_EXIST_ERROR,"账单退款详情信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateBillRefundDetail(
			BillRefundDetailDto billRefundDetailDto)
			throws TsfaServiceException {
		logger.debug("updateBillRefundDetail(BillRefundDetailDto billRefundDetailDto={}) - start", billRefundDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(billRefundDetailDto);
		AssertUtils.notNullAndEmpty(billRefundDetailDto.getCode(),"Code不能为空");
		try {
			BillRefundDetail billRefundDetail = new BillRefundDetail();
			//update数据录入
			billRefundDetail.setCode(billRefundDetailDto.getCode());
			billRefundDetail.setBillCode(billRefundDetailDto.getBillCode());
			billRefundDetail.setRefundCode(billRefundDetailDto.getRefundCode());
			billRefundDetail.setOperateCode(billRefundDetailDto.getOperateCode());
			billRefundDetail.setShopNo(billRefundDetailDto.getShopNo());
			billRefundDetail.setShopName(billRefundDetailDto.getShopName());
			billRefundDetail.setMerchantNo(billRefundDetailDto.getMerchantNo());
			billRefundDetail.setMerchantName(billRefundDetailDto.getMerchantName());
			billRefundDetail.setProjectCode(billRefundDetailDto.getProjectCode());
			billRefundDetail.setProjectName(billRefundDetailDto.getProjectName());
			billRefundDetail.setItemNum(billRefundDetailDto.getItemNum());
			billRefundDetail.setReturnAmount(billRefundDetailDto.getReturnAmount());
			billRefundDetail.setUpdateId(billRefundDetailDto.getUpdateId());
			billRefundDetail.setUpdateDate(new Date());
//			billRefundDetail.setCreateId(billRefundDetailDto.getCreateId());
//			billRefundDetail.setCreateDate(billRefundDetailDto.getCreateDate());
			billRefundDetail.setRemark(billRefundDetailDto.getRemark());
			billRefundDetail.setRemark1(billRefundDetailDto.getRemark1());
			billRefundDetail.setRemark2(billRefundDetailDto.getRemark2());
			billRefundDetail.setRemark3(billRefundDetailDto.getRemark3());
			billRefundDetail.setRemark4(billRefundDetailDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(billRefundDetailDao.updateByPrimaryKeySelective(billRefundDetail));
			logger.debug("updateBillRefundDetail(BillRefundDetailDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("账单退款详情信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_DETAIL_UPDATE_ERROR,"账单退款详情信息更新信息错误！",e);
		}
	}

	

	@Override
	public BillRefundDetailDto findBillRefundDetail(
			BillRefundDetailDto billRefundDetailDto) throws TsfaServiceException {
		logger.debug("findBillRefundDetail(FindBillRefundDetail findBillRefundDetail={}) - start", billRefundDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(billRefundDetailDto);
		AssertUtils.notAllNull(billRefundDetailDto.getCode(),"Code不能为空");
		try {
			BillRefundDetail billRefundDetail = billRefundDetailDao.selectByPrimaryKey(billRefundDetailDto.getCode());
			if(billRefundDetail == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.BILL_REFUND_DETAIL_NOT_EXIST_ERROR,"账单退款详情信息不存在");
			}
			BillRefundDetailDto findBillRefundDetailReturn = new BillRefundDetailDto();
			//find数据录入
			findBillRefundDetailReturn.setCode(billRefundDetail.getCode());
			findBillRefundDetailReturn.setBillCode(billRefundDetail.getBillCode());
			findBillRefundDetailReturn.setRefundCode(billRefundDetail.getRefundCode());
			findBillRefundDetailReturn.setShopNo(billRefundDetail.getShopNo());
			findBillRefundDetailReturn.setShopName(billRefundDetail.getShopName());
			findBillRefundDetailReturn.setMerchantNo(billRefundDetail.getMerchantNo());
			findBillRefundDetailReturn.setMerchantName(billRefundDetail.getMerchantName());
			findBillRefundDetailReturn.setProjectCode(billRefundDetail.getProjectCode());
			findBillRefundDetailReturn.setProjectName(billRefundDetail.getProjectName());
			findBillRefundDetailReturn.setItemNum(billRefundDetail.getItemNum());
			findBillRefundDetailReturn.setReturnAmount(billRefundDetail.getReturnAmount());
			findBillRefundDetailReturn.setUpdateId(billRefundDetail.getUpdateId());
			findBillRefundDetailReturn.setUpdateDate(billRefundDetail.getUpdateDate());
			findBillRefundDetailReturn.setCreateId(billRefundDetail.getCreateId());
			findBillRefundDetailReturn.setCreateDate(billRefundDetail.getCreateDate());
			findBillRefundDetailReturn.setRemark(billRefundDetail.getRemark());
			findBillRefundDetailReturn.setRemark1(billRefundDetail.getRemark1());
			findBillRefundDetailReturn.setRemark2(billRefundDetail.getRemark2());
			findBillRefundDetailReturn.setRemark3(billRefundDetail.getRemark3());
			findBillRefundDetailReturn.setRemark4(billRefundDetail.getRemark4());
			findBillRefundDetailReturn.setOperateCode(billRefundDetail.getOperateCode());
			logger.debug("findBillRefundDetail(BillRefundDetailDto) - end - return value={}", findBillRefundDetailReturn); //$NON-NLS-1$
			return findBillRefundDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找账单退款详情信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_DETAIL_FIND_ERROR,"查找账单退款详情信息信息错误！",e);
		}


	}

	
	@Override
	public Page<BillRefundDetailDto> findBillRefundDetailPage(
			FindBillRefundDetailPage findBillRefundDetailPage)
			throws TsfaServiceException {
		logger.debug("findBillRefundDetailPage(FindBillRefundDetailPage findBillRefundDetailPage={}) - start", findBillRefundDetailPage); //$NON-NLS-1$

		AssertUtils.notNull(findBillRefundDetailPage);
		List<BillRefundDetailDto> returnList=null;
		int count = 0;
		try {
			returnList = billRefundDetailDao.findBillRefundDetailPage(findBillRefundDetailPage);
			count = billRefundDetailDao.findBillRefundDetailPageCount(findBillRefundDetailPage);
		}  catch (Exception e) {
			logger.error("账单退款详情信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_DETAIL_FIND_PAGE_ERROR,"账单退款详情信息不存在错误.！",e);
		}
		Page<BillRefundDetailDto> returnPage = new Page<BillRefundDetailDto>(returnList, count, findBillRefundDetailPage);

		logger.debug("findBillRefundDetailPage(FindBillRefundDetailPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


}
