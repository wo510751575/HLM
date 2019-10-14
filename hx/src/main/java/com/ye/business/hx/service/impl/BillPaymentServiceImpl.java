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
import com.ye.business.hx.dao.IBillPaymentDao;
import com.ye.business.hx.domain.BillPayment;
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.BillPaymentDto;
import com.ye.business.hx.dto.FindBillPaymentPage;
import com.ye.business.hx.emus.PayMode;
import com.ye.business.hx.service.IBillPaymentService;
import com.ye.business.hx.util.GenerateNo;
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
public class BillPaymentServiceImpl implements IBillPaymentService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BillPaymentServiceImpl.class);
	

	@Resource
	private IBillPaymentDao billPaymentDao;
	
	
	@Override
	public void addBillPayment(
			BillPaymentDto billPaymentDto) throws TsfaServiceException {
		logger.debug("addBillPayment(AddBillPayment addBillPayment={}) - start", billPaymentDto); 

		AssertUtils.notNull(billPaymentDto);
		try {
			BillPayment billPayment = new BillPayment();
			//add数据录入
			billPayment.setCode(GUID.getPreUUID());
			billPayment.setPayNo(GenerateNo.getPayNo());
			billPayment.setPatientNo(billPaymentDto.getPatientNo());
			billPayment.setPatientName(billPaymentDto.getPatientName());
			billPayment.setMedicalNo(billPaymentDto.getMedicalNo());
			billPayment.setBillCode(billPaymentDto.getBillCode());
			billPayment.setOperateCode(billPaymentDto.getOperateCode());
			billPayment.setShopNo(billPaymentDto.getShopNo());
			billPayment.setShopName(billPaymentDto.getShopName());
			billPayment.setMerchantNo(billPaymentDto.getMerchantNo());
			billPayment.setMerchantName(billPaymentDto.getMerchantName());
			billPayment.setPayType(billPaymentDto.getPayType());
			billPayment.setPayTypeName(billPaymentDto.getPayTypeName());
			billPayment.setPayRemark(billPaymentDto.getPayRemark());
			billPayment.setPayAmount(billPaymentDto.getPayAmount());
			billPayment.setPayTime(billPaymentDto.getPayTime());
			billPayment.setRecieverNo(billPaymentDto.getRecieverNo());
			billPayment.setRecieverName(billPaymentDto.getRecieverName());
			billPayment.setBizType(billPaymentDto.getBizType());
			billPayment.setStatus(billPaymentDto.getStatus());
			billPayment.setReceivableAmt(billPaymentDto.getReceivableAmt());
			billPayment.setDebtAmt(billPaymentDto.getDebtAmt());
			billPayment.setPayMode(billPaymentDto.getPayMode());
			billPayment.setUpdateId(billPaymentDto.getUpdateId());
			billPayment.setUpdateDate(new Date());
			billPayment.setCreateId(billPaymentDto.getCreateId());
			billPayment.setCreateDate(new Date());
			billPayment.setRemark(billPaymentDto.getRemark());
			billPayment.setRemark1(billPaymentDto.getRemark1());
			billPayment.setRemark2(billPaymentDto.getRemark2());
			billPayment.setRemark3(billPaymentDto.getRemark3());
			billPayment.setRemark4(billPaymentDto.getRemark4());
			billPayment.setBillPayAmount(billPaymentDto.getBillPayAmount());
			billPaymentDao.insertSelective(billPayment);
			logger.debug("addBillPayment(BillPaymentDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单支付信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_ADD_ERROR,"新增账单支付信息信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询账单支付信息信息
	 *
	 * @param findBillPaymentPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<BillPaymentDto>  findBillPayments(FindBillPaymentPage findBillPaymentPage)throws TsfaServiceException{
		AssertUtils.notNull(findBillPaymentPage);
		List<BillPaymentDto> returnList=null;
		try {
			returnList = billPaymentDao.findBillPayments(findBillPaymentPage);
		} catch (Exception e) {
			logger.error("查找账单支付信息信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_NOT_EXIST_ERROR,"账单支付信息信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateBillPayment(
			BillPaymentDto billPaymentDto)
			throws TsfaServiceException {
		logger.debug("updateBillPayment(BillPaymentDto billPaymentDto={}) - start", billPaymentDto); //$NON-NLS-1$

		AssertUtils.notNull(billPaymentDto);
		AssertUtils.notNullAndEmpty(billPaymentDto.getCode(),"Code不能为空");
		try {
			BillPayment billPayment = new BillPayment();
			//update数据录入
			billPayment.setCode(billPaymentDto.getCode());
			billPayment.setPatientNo(billPaymentDto.getPatientNo());
			billPayment.setPatientName(billPaymentDto.getPatientName());
			billPayment.setMedicalNo(billPaymentDto.getMedicalNo());
			billPayment.setBillCode(billPaymentDto.getBillCode());
			billPayment.setOperateCode(billPaymentDto.getOperateCode());
			billPayment.setShopNo(billPaymentDto.getShopNo());
			billPayment.setShopName(billPaymentDto.getShopName());
			billPayment.setMerchantNo(billPaymentDto.getMerchantNo());
			billPayment.setMerchantName(billPaymentDto.getMerchantName());
			billPayment.setPayType(billPaymentDto.getPayType());
			billPayment.setPayTypeName(billPaymentDto.getPayTypeName());
			billPayment.setPayRemark(billPaymentDto.getPayRemark());
			billPayment.setPayAmount(billPaymentDto.getPayAmount());
			billPayment.setPayTime(billPaymentDto.getPayTime());
			billPayment.setRecieverNo(billPaymentDto.getRecieverNo());
			billPayment.setRecieverName(billPaymentDto.getRecieverName());
			billPayment.setBizType(billPaymentDto.getBizType());
			billPayment.setStatus(billPaymentDto.getStatus());
			billPayment.setReceivableAmt(billPaymentDto.getReceivableAmt());
			billPayment.setDebtAmt(billPaymentDto.getDebtAmt());
			billPayment.setPayMode(billPaymentDto.getPayMode());
			billPayment.setUpdateId(billPaymentDto.getUpdateId());
			billPayment.setUpdateDate(new Date());
//			billPayment.setCreateId(billPaymentDto.getCreateId());
//			billPayment.setCreateDate(billPaymentDto.getCreateDate());
			billPayment.setRemark(billPaymentDto.getRemark());
			billPayment.setRemark1(billPaymentDto.getRemark1());
			billPayment.setRemark2(billPaymentDto.getRemark2());
			billPayment.setRemark3(billPaymentDto.getRemark3());
			billPayment.setRemark4(billPaymentDto.getRemark4());
			billPayment.setBillPayAmount(billPaymentDto.getBillPayAmount());
			AssertUtils.notUpdateMoreThanOne(billPaymentDao.updateByPrimaryKeySelective(billPayment));
			logger.debug("updateBillPayment(BillPaymentDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("账单支付信息信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_UPDATE_ERROR,"账单支付信息信息更新信息错误！",e);
		}
	}

	

	@Override
	public BillPaymentDto findBillPayment(
			BillPaymentDto billPaymentDto) throws TsfaServiceException {
		logger.debug("findBillPayment(FindBillPayment findBillPayment={}) - start", billPaymentDto); //$NON-NLS-1$

		AssertUtils.notNull(billPaymentDto);
		AssertUtils.notAllNull(billPaymentDto.getCode(),"Code不能为空");
		try {
			BillPayment billPayment = billPaymentDao.selectByPrimaryKey(billPaymentDto.getCode());
			if(billPayment == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_NOT_EXIST_ERROR,"账单支付信息信息不存在");
			}
			BillPaymentDto findBillPaymentReturn = new BillPaymentDto();
			//find数据录入
			findBillPaymentReturn.setCode(billPayment.getCode());
			findBillPaymentReturn.setPayNo(billPayment.getPayNo());
			findBillPaymentReturn.setPatientNo(billPayment.getPatientNo());
			findBillPaymentReturn.setPatientName(billPayment.getPatientName());
			findBillPaymentReturn.setMedicalNo(billPayment.getMedicalNo());
			findBillPaymentReturn.setBillCode(billPayment.getBillCode());
			findBillPaymentReturn.setOperateCode(billPayment.getOperateCode());
			findBillPaymentReturn.setShopNo(billPayment.getShopNo());
			findBillPaymentReturn.setShopName(billPayment.getShopName());
			findBillPaymentReturn.setMerchantNo(billPayment.getMerchantNo());
			findBillPaymentReturn.setMerchantName(billPayment.getMerchantName());
			findBillPaymentReturn.setPayType(billPayment.getPayType());
			findBillPaymentReturn.setPayTypeName(billPayment.getPayTypeName());
			findBillPaymentReturn.setPayRemark(billPayment.getPayRemark());
			findBillPaymentReturn.setPayAmount(billPayment.getPayAmount());
			findBillPaymentReturn.setPayTime(billPayment.getPayTime());
			findBillPaymentReturn.setRecieverNo(billPayment.getRecieverNo());
			findBillPaymentReturn.setRecieverName(billPayment.getRecieverName());
			findBillPaymentReturn.setBizType(billPayment.getBizType());
			findBillPaymentReturn.setStatus(billPayment.getStatus());
			findBillPaymentReturn.setReceivableAmt(billPayment.getReceivableAmt());
			findBillPaymentReturn.setDebtAmt(billPayment.getDebtAmt());
			findBillPaymentReturn.setPayMode(billPayment.getPayMode());
			findBillPaymentReturn.setUpdateId(billPayment.getUpdateId());
			findBillPaymentReturn.setUpdateDate(billPayment.getUpdateDate());
			findBillPaymentReturn.setCreateId(billPayment.getCreateId());
			findBillPaymentReturn.setCreateDate(billPayment.getCreateDate());
			findBillPaymentReturn.setRemark(billPayment.getRemark());
			findBillPaymentReturn.setRemark1(billPayment.getRemark1());
			findBillPaymentReturn.setRemark2(billPayment.getRemark2());
			findBillPaymentReturn.setRemark3(billPayment.getRemark3());
			findBillPaymentReturn.setRemark4(billPayment.getRemark4());
			findBillPaymentReturn.setBillPayAmount(billPayment.getBillPayAmount());
			
			logger.debug("findBillPayment(BillPaymentDto) - end - return value={}", findBillPaymentReturn); //$NON-NLS-1$
			return findBillPaymentReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找账单支付信息信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_FIND_ERROR,"查找账单支付信息信息信息错误！",e);
		}


	}

	
	@Override
	public Page<BillPaymentDto> findBillPaymentPage(
			FindBillPaymentPage findBillPaymentPage)
			throws TsfaServiceException {
		logger.debug("findBillPaymentPage(FindBillPaymentPage findBillPaymentPage={}) - start", findBillPaymentPage); //$NON-NLS-1$

		AssertUtils.notNull(findBillPaymentPage);
		List<BillPaymentDto> returnList=null;
		int count = 0;
		try {
			returnList = billPaymentDao.findBillPaymentPage(findBillPaymentPage);
			count = billPaymentDao.findBillPaymentPageCount(findBillPaymentPage);
		}  catch (Exception e) {
			logger.error("账单支付信息信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_FIND_PAGE_ERROR,"账单支付信息信息不存在错误.！",e);
		}
		Page<BillPaymentDto> returnPage = new Page<BillPaymentDto>(returnList, count, findBillPaymentPage);

		logger.debug("findBillPaymentPage(FindBillPaymentPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public Page<BillPaymentDto> findBillPaymentPageByMerchant(FindBillPaymentPage findBillPaymentPage)
			throws TsfaServiceException {
		logger.debug("findBillPaymentPageByMerchant(FindBillPaymentPage findBillPaymentPage={}) - start", findBillPaymentPage); //$NON-NLS-1$

		AssertUtils.notNull(findBillPaymentPage);
		List<BillPaymentDto> returnList=null;
		int count = 0;
		try {
			returnList = billPaymentDao.findBillPaymentPageByMerchant(findBillPaymentPage);
			count = billPaymentDao.findBillPaymentPageCountByMerchant(findBillPaymentPage);
		}  catch (Exception e) {
			logger.error("账单支付信息信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_PAYMENT_FIND_PAGE_ERROR,"账单支付信息信息不存在错误.！",e);
		}
		Page<BillPaymentDto> returnPage = new Page<BillPaymentDto>(returnList, count, findBillPaymentPage);

		logger.debug("findBillPaymentPageByMerchant(FindBillPaymentPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public BillDto paymentSum(FindBillPaymentPage findBillPaymentPage) throws TsfaServiceException {
		AssertUtils.notNull(findBillPaymentPage);
		BillDto rt = new BillDto();
		findBillPaymentPage.getParam().setPayMode(PayMode.ADD.name());
		Long payAmount = billPaymentDao.paymentSum(findBillPaymentPage);//收款总额
		findBillPaymentPage.getParam().setPayMode(PayMode.SUB.name());
		Long rtAmount = billPaymentDao.paymentSum(findBillPaymentPage);//退款总额
		rt.setPayAmount(payAmount==null?0L:payAmount);
		rt.setRtAmount(rtAmount==null?0L:rtAmount);
		return rt;
	} 


}
