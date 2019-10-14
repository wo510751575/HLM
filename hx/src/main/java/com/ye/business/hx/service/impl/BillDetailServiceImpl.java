package com.ye.business.hx.service.impl;

import java.util.Date;
import java.util.Iterator;
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
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IBillDao;
import com.ye.business.hx.dao.IBillDetailDao;
import com.ye.business.hx.domain.Bill;
import com.ye.business.hx.domain.BillDetail;
import com.ye.business.hx.dto.BillDetailDto;
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.FindBillDetailPage;
import com.ye.business.hx.service.IBillDetailService;
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
public class BillDetailServiceImpl implements IBillDetailService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BillDetailServiceImpl.class);
	

	@Resource
	private IBillDetailDao billDetailDao;
	@Resource
	private IBillDao billDao;
	
	@Override
	public void addBillDetail(
			BillDetailDto billDetailDto) throws TsfaServiceException {
		logger.debug("addBillDetail(AddBillDetail addBillDetail={}) - start", billDetailDto); 

		AssertUtils.notNull(billDetailDto);
		try {
			BillDetail billDetail = new BillDetail();
			//add数据录入
			billDetail.setCode(GUID.getPreUUID());
			billDetail.setBillCode(billDetailDto.getBillCode());
			billDetail.setShopNo(billDetailDto.getShopNo());
			billDetail.setShopName(billDetailDto.getShopName());
			billDetail.setMerchantNo(billDetailDto.getMerchantNo());
			billDetail.setMerchantName(billDetailDto.getMerchantName());
			billDetail.setProjectCode(billDetailDto.getProjectCode());
			billDetail.setProjectName(billDetailDto.getProjectName());
			billDetail.setProjectUnit(billDetailDto.getProjectUnit());
			billDetail.setUnitPrice(billDetailDto.getUnitPrice());
			billDetail.setItemDisUnitPrice(billDetailDto.getItemDisUnitPrice());
			billDetail.setItemNum(billDetailDto.getItemNum());
			billDetail.setOriginalAmount(billDetailDto.getOriginalAmount());
			billDetail.setItemDiscountAmount(billDetailDto.getItemDiscountAmount());
			billDetail.setDiscountItem(billDetailDto.getDiscountItem());
			billDetail.setRtNum(billDetailDto.getRtNum());
			billDetail.setRtAmount(billDetailDto.getRtAmount());
			billDetail.setDiscountOrderStatus(billDetailDto.getDiscountOrderStatus());
			billDetail.setAdvisoryNo(billDetailDto.getAdvisoryNo());
			billDetail.setAdvisoryName(billDetailDto.getAdvisoryName());
			billDetail.setDoctorNo(billDetailDto.getDoctorNo());
			billDetail.setDoctorName(billDetailDto.getDoctorName());
			billDetail.setAssistantNo(billDetailDto.getAssistantNo());
			billDetail.setAssistantName(billDetailDto.getAssistantName());
			billDetail.setUpdateId(billDetailDto.getUpdateId());
			billDetail.setUpdateDate(new Date());
			billDetail.setCreateId(billDetailDto.getCreateId());
			billDetail.setCreateDate(new Date());
			billDetail.setRemark(billDetailDto.getRemark());
			billDetail.setRemark1(billDetailDto.getRemark1());
			billDetail.setRemark2(billDetailDto.getRemark2());
			billDetail.setRemark3(billDetailDto.getRemark3());
			billDetail.setRemark4(billDetailDto.getRemark4());
			billDetail.setPayAmount(billDetailDto.getPayAmount());
			billDetail.setReallyAmount(billDetailDto.getReallyAmount());
			billDetail.setDebtAmount(billDetailDto.getDebtAmount());
			billDetailDao.insertSelective(billDetail);
			logger.debug("addBillDetail(BillDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单详情信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_ADD_ERROR,"新增账单详情信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询账单详情信息
	 *
	 * @param findBillDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<BillDetailDto>  findBillDetails(FindBillDetailPage findBillDetailPage)throws TsfaServiceException{
		AssertUtils.notNull(findBillDetailPage);
		List<BillDetailDto> returnList=null;
		try {
			returnList = billDetailDao.findBillDetails(findBillDetailPage);
		} catch (Exception e) {
			logger.error("查找账单详情信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_NOT_EXIST_ERROR,"账单详情信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateBillDetail(
			BillDetailDto billDetailDto)
			throws TsfaServiceException {
		logger.debug("updateBillDetail(BillDetailDto billDetailDto={}) - start", billDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(billDetailDto);
		AssertUtils.notNullAndEmpty(billDetailDto.getCode(),"Code不能为空");
		try {
			BillDetail billDetail = new BillDetail();
			//update数据录入
			billDetail.setCode(billDetailDto.getCode());
			billDetail.setBillCode(billDetailDto.getBillCode());
			billDetail.setShopNo(billDetailDto.getShopNo());
			billDetail.setShopName(billDetailDto.getShopName());
			billDetail.setMerchantNo(billDetailDto.getMerchantNo());
			billDetail.setMerchantName(billDetailDto.getMerchantName());
			billDetail.setProjectCode(billDetailDto.getProjectCode());
			billDetail.setProjectName(billDetailDto.getProjectName());
			billDetail.setProjectUnit(billDetailDto.getProjectUnit());
			billDetail.setUnitPrice(billDetailDto.getUnitPrice());
			billDetail.setItemDisUnitPrice(billDetailDto.getItemDisUnitPrice());
			billDetail.setItemNum(billDetailDto.getItemNum());
			billDetail.setOriginalAmount(billDetailDto.getOriginalAmount());
			billDetail.setItemDiscountAmount(billDetailDto.getItemDiscountAmount());
			billDetail.setDiscountItem(billDetailDto.getDiscountItem());
			billDetail.setRtNum(billDetailDto.getRtNum());
			billDetail.setRtAmount(billDetailDto.getRtAmount());
			billDetail.setDiscountOrderStatus(billDetailDto.getDiscountOrderStatus());
			billDetail.setAdvisoryNo(billDetailDto.getAdvisoryNo());
			billDetail.setAdvisoryName(billDetailDto.getAdvisoryName());
			billDetail.setDoctorNo(billDetailDto.getDoctorNo());
			billDetail.setDoctorName(billDetailDto.getDoctorName());
			billDetail.setAssistantNo(billDetailDto.getAssistantNo());
			billDetail.setAssistantName(billDetailDto.getAssistantName());
			billDetail.setUpdateId(billDetailDto.getUpdateId());
			billDetail.setUpdateDate(new Date());
//			billDetail.setCreateId(billDetailDto.getCreateId());
//			billDetail.setCreateDate(billDetailDto.getCreateDate());
			billDetail.setRemark(billDetailDto.getRemark());
			billDetail.setRemark1(billDetailDto.getRemark1());
			billDetail.setRemark2(billDetailDto.getRemark2());
			billDetail.setRemark3(billDetailDto.getRemark3());
			billDetail.setRemark4(billDetailDto.getRemark4());
			billDetail.setPayAmount(billDetailDto.getPayAmount());
			billDetail.setReallyAmount(billDetailDto.getReallyAmount());
			billDetail.setDebtAmount(billDetailDto.getDebtAmount());
			AssertUtils.notUpdateMoreThanOne(billDetailDao.updateByPrimaryKeySelective(billDetail));
			logger.debug("updateBillDetail(BillDetailDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("账单详情信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_UPDATE_ERROR,"账单详情信息更新信息错误！",e);
		}
	}

	

	@Override
	public BillDetailDto findBillDetail(
			BillDetailDto billDetailDto) throws TsfaServiceException {
		logger.debug("findBillDetail(FindBillDetail findBillDetail={}) - start", billDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(billDetailDto);
		AssertUtils.notAllNull(billDetailDto.getCode(),"Code不能为空");
		try {
			BillDetail billDetail = billDetailDao.selectByPrimaryKey(billDetailDto.getCode());
			if(billDetail == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.BILL_DETAIL_NOT_EXIST_ERROR,"账单详情信息不存在");
			}
			BillDetailDto findBillDetailReturn = new BillDetailDto();
			//find数据录入
			findBillDetailReturn.setCode(billDetail.getCode());
			findBillDetailReturn.setBillCode(billDetail.getBillCode());
			findBillDetailReturn.setShopNo(billDetail.getShopNo());
			findBillDetailReturn.setShopName(billDetail.getShopName());
			findBillDetailReturn.setMerchantNo(billDetail.getMerchantNo());
			findBillDetailReturn.setMerchantName(billDetail.getMerchantName());
			findBillDetailReturn.setProjectCode(billDetail.getProjectCode());
			findBillDetailReturn.setProjectName(billDetail.getProjectName());
			findBillDetailReturn.setProjectUnit(billDetail.getProjectUnit());
			findBillDetailReturn.setUnitPrice(billDetail.getUnitPrice());
			findBillDetailReturn.setItemDisUnitPrice(billDetail.getItemDisUnitPrice());
			findBillDetailReturn.setItemNum(billDetail.getItemNum());
			findBillDetailReturn.setOriginalAmount(billDetail.getOriginalAmount());
			findBillDetailReturn.setItemDiscountAmount(billDetail.getItemDiscountAmount());
			findBillDetailReturn.setDiscountItem(billDetail.getDiscountItem());
			findBillDetailReturn.setRtNum(billDetail.getRtNum());
			findBillDetailReturn.setRtAmount(billDetail.getRtAmount());
			findBillDetailReturn.setDiscountOrderStatus(billDetail.getDiscountOrderStatus());
			findBillDetailReturn.setAdvisoryNo(billDetail.getAdvisoryNo());
			findBillDetailReturn.setAdvisoryName(billDetail.getAdvisoryName());
			findBillDetailReturn.setDoctorNo(billDetail.getDoctorNo());
			findBillDetailReturn.setDoctorName(billDetail.getDoctorName());
			findBillDetailReturn.setAssistantNo(billDetail.getAssistantNo());
			findBillDetailReturn.setAssistantName(billDetail.getAssistantName());
			findBillDetailReturn.setUpdateId(billDetail.getUpdateId());
			findBillDetailReturn.setUpdateDate(billDetail.getUpdateDate());
			findBillDetailReturn.setCreateId(billDetail.getCreateId());
			findBillDetailReturn.setCreateDate(billDetail.getCreateDate());
			findBillDetailReturn.setRemark(billDetail.getRemark());
			findBillDetailReturn.setRemark1(billDetail.getRemark1());
			findBillDetailReturn.setRemark2(billDetail.getRemark2());
			findBillDetailReturn.setRemark3(billDetail.getRemark3());
			findBillDetailReturn.setRemark4(billDetail.getRemark4());
			findBillDetailReturn.setPayAmount(billDetail.getPayAmount());
			findBillDetailReturn.setReallyAmount(billDetail.getReallyAmount());
			findBillDetailReturn.setDebtAmount(billDetail.getDebtAmount());
			logger.debug("findBillDetail(BillDetailDto) - end - return value={}", findBillDetailReturn); //$NON-NLS-1$
			return findBillDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找账单详情信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_FIND_ERROR,"查找账单详情信息信息错误！",e);
		}


	}

	
	@Override
	public Page<BillDetailDto> findBillDetailPage(
			FindBillDetailPage findBillDetailPage)
			throws TsfaServiceException {
		logger.debug("findBillDetailPage(FindBillDetailPage findBillDetailPage={}) - start", findBillDetailPage); //$NON-NLS-1$

		AssertUtils.notNull(findBillDetailPage);
		List<BillDetailDto> returnList=null;
		int count = 0;
		try {
			returnList = billDetailDao.findBillDetailPage(findBillDetailPage);
			count = billDetailDao.findBillDetailPageCount(findBillDetailPage);
		}  catch (Exception e) {
			logger.error("账单详情信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_FIND_PAGE_ERROR,"账单详情信息不存在错误.！",e);
		}
		Page<BillDetailDto> returnPage = new Page<BillDetailDto>(returnList, count, findBillDetailPage);

		logger.debug("findBillDetailPage(FindBillDetailPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public void updateBillDetailMember(BillDto billDto) throws TsfaServiceException {
		List<BillDetailDto> details=billDto.getDetails();
		StringBuffer doctorNames = new StringBuffer();
		for (Iterator<BillDetailDto> iterator = details.iterator(); iterator.hasNext();) {
			BillDetailDto billDetailDto =  iterator.next();
			AssertUtils.notNullAndEmpty(billDetailDto.getCode(),"账单明细编号不能为空！");
			
			billDetailDto.setUpdateId(billDto.getUpdateId());
			BillDetail billDetail = new BillDetail();
			//update数据录入
			billDetail.setCode(billDetailDto.getCode());
			billDetail.setAdvisoryNo(billDetailDto.getAdvisoryNo());
			billDetail.setAdvisoryName(billDetailDto.getAdvisoryName());
			billDetail.setDoctorNo(billDetailDto.getDoctorNo());
			billDetail.setDoctorName(billDetailDto.getDoctorName());
			billDetail.setAssistantNo(billDetailDto.getAssistantNo());
			billDetail.setAssistantName(billDetailDto.getAssistantName());
			billDetail.setUpdateId(billDetailDto.getUpdateId());
			billDetail.setUpdateDate(new Date());
 
			billDetailDao.updateByPrimaryKeySelective(billDetail);
			
			if(StringUtils.isNotEmpty(billDetail.getDoctorName())) {
				String key=billDetail.getDoctorName()+",";
				if(doctorNames.indexOf(key)==-1) {
					doctorNames.append(key);
				}
			}
		}
		
		//2.修改 账单的医生 
		String dString=doctorNames.toString();
		if(StringUtils.isNotEmpty(dString)) {
			Bill updateBill = new Bill();
			updateBill.setCode(billDto.getCode());
			updateBill.setRemark1(doctorNames.toString().substring(0, dString.length()-1));
			billDao.updateByPrimaryKeySelective(updateBill);
		}
		
	}


	@Override
	public Page<BillDetailDto> findBillDetailPageByMerchant(FindBillDetailPage findBillDetailPage)
			throws TsfaServiceException {
		logger.debug("findBillDetailPageByMerchant(FindBillDetailPage findBillDetailPage={}) - start", findBillDetailPage); //$NON-NLS-1$

		AssertUtils.notNull(findBillDetailPage);
		List<BillDetailDto> returnList=null;
		int count = 0;
		try {
			returnList = billDetailDao.findBillDetailPageByMerchant(findBillDetailPage);
			count = billDetailDao.findBillDetailPageCountByMerchant(findBillDetailPage);
		}  catch (Exception e) {
			logger.error("账单详情信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_FIND_PAGE_ERROR,"账单详情信息不存在错误.！",e);
		}
		Page<BillDetailDto> returnPage = new Page<BillDetailDto>(returnList, count, findBillDetailPage);

		logger.debug("findBillDetailPageByMerchant(FindBillDetailPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public BillDetailDto billProjectSum(FindBillDetailPage findBillDetailPage) throws TsfaServiceException {

		logger.debug("billProjectSum(FindBillDetailPage findBillDetailPage={}) - start", findBillDetailPage); //$NON-NLS-1$

		AssertUtils.notNull(findBillDetailPage);
		BillDetailDto rt=null;
		try {
			rt = billDetailDao.billProjectSum(findBillDetailPage);
		}  catch (Exception e) {
			logger.error("账单详情信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_DETAIL_FIND_PAGE_ERROR,"账单详情信息不存在错误.！",e);
		}

		logger.debug("billProjectSum(FindBillDetailPage) - end - return value={}", rt); //$NON-NLS-1$
		return  rt;
	
	} 


}
