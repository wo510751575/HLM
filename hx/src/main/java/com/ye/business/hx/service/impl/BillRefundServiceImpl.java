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
import com.ye.business.hx.dao.IBillRefundDao;
import com.ye.business.hx.domain.BillRefund;
import com.ye.business.hx.dto.BillRefundDto;
import com.ye.business.hx.dto.FindBillRefundPage;
import com.ye.business.hx.service.IBillRefundService;
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
public class BillRefundServiceImpl implements IBillRefundService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BillRefundServiceImpl.class);
	

	@Resource
	private IBillRefundDao billRefundDao;
	
	
	@Override
	public void addBillRefund(
			BillRefundDto billRefundDto) throws TsfaServiceException {
		logger.debug("addBillRefund(AddBillRefund addBillRefund={}) - start", billRefundDto); 

		AssertUtils.notNull(billRefundDto);
		try {
			BillRefund billRefund = new BillRefund();
			//add数据录入
			billRefund.setCode(GUID.getPreUUID());
			billRefund.setBillCode(billRefundDto.getBillCode());
			billRefund.setOperateCode(billRefundDto.getOperateCode());
			billRefund.setShopNo(billRefundDto.getShopNo());
			billRefund.setShopName(billRefundDto.getShopName());
			billRefund.setMerchantNo(billRefundDto.getMerchantNo());
			billRefund.setMerchantName(billRefundDto.getMerchantName());
			billRefund.setPatientNo(billRefundDto.getPatientNo());
			billRefund.setPatientName(billRefundDto.getPatientName());
			billRefund.setMedicalNo(billRefundDto.getMedicalNo());
			billRefund.setPayType(billRefundDto.getPayType());
			billRefund.setPayTypeName(billRefundDto.getPayTypeName());
			billRefund.setRtAmount(billRefundDto.getRtAmount());
			billRefund.setRefundGdNo(billRefundDto.getRefundGdNo());
			billRefund.setRefundGdName(billRefundDto.getRefundGdName());
			billRefund.setRefundTime(billRefundDto.getRefundTime());
			billRefund.setRefundStatus(billRefundDto.getRefundStatus());
			billRefund.setStatus(billRefundDto.getStatus());
			billRefund.setRefundType(billRefundDto.getRefundType());
			billRefund.setUpdateId(billRefundDto.getUpdateId());
			billRefund.setUpdateDate(new Date());
			billRefund.setCreateId(billRefundDto.getCreateId());
			billRefund.setCreateDate(new Date());
			billRefund.setRemark(billRefundDto.getRemark());
			billRefund.setRemark1(billRefundDto.getRemark1());
			billRefund.setRemark2(billRefundDto.getRemark2());
			billRefund.setRemark3(billRefundDto.getRemark3());
			billRefund.setRemark4(billRefundDto.getRemark4());
			
			billRefund.setMemberNoGuid(billRefundDto.getMemberNoGuid());
			billRefund.setMemberNameGuid(billRefundDto.getMemberNameGuid());
			billRefundDao.insertSelective(billRefund);
			logger.debug("addBillRefund(BillRefundDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单退款信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_ADD_ERROR,"新增账单退款信息信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询账单退款信息信息
	 *
	 * @param findBillRefundPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<BillRefundDto>  findBillRefunds(FindBillRefundPage findBillRefundPage)throws TsfaServiceException{
		AssertUtils.notNull(findBillRefundPage);
		List<BillRefundDto> returnList=null;
		try {
			returnList = billRefundDao.findBillRefunds(findBillRefundPage);
		} catch (Exception e) {
			logger.error("查找账单退款信息信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_NOT_EXIST_ERROR,"账单退款信息信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateBillRefund(
			BillRefundDto billRefundDto)
			throws TsfaServiceException {
		logger.debug("updateBillRefund(BillRefundDto billRefundDto={}) - start", billRefundDto); //$NON-NLS-1$

		AssertUtils.notNull(billRefundDto);
		AssertUtils.notNullAndEmpty(billRefundDto.getCode(),"Code不能为空");
		try {
			BillRefund billRefund = new BillRefund();
			//update数据录入
			billRefund.setCode(billRefundDto.getCode());
			billRefund.setBillCode(billRefundDto.getBillCode());
			billRefund.setOperateCode(billRefundDto.getOperateCode());
			billRefund.setShopNo(billRefundDto.getShopNo());
			billRefund.setShopName(billRefundDto.getShopName());
			billRefund.setMerchantNo(billRefundDto.getMerchantNo());
			billRefund.setMerchantName(billRefundDto.getMerchantName());
			billRefund.setPatientNo(billRefundDto.getPatientNo());
			billRefund.setPatientName(billRefundDto.getPatientName());
			billRefund.setMedicalNo(billRefundDto.getMedicalNo());
			billRefund.setPayType(billRefundDto.getPayType());
			billRefund.setPayTypeName(billRefundDto.getPayTypeName());
			billRefund.setRtAmount(billRefundDto.getRtAmount());
			billRefund.setRefundGdNo(billRefundDto.getRefundGdNo());
			billRefund.setRefundGdName(billRefundDto.getRefundGdName());
			billRefund.setRefundTime(billRefundDto.getRefundTime());
			billRefund.setRefundStatus(billRefundDto.getRefundStatus());
			billRefund.setStatus(billRefundDto.getStatus());
			billRefund.setRefundType(billRefundDto.getRefundType());
			billRefund.setUpdateId(billRefundDto.getUpdateId());
			billRefund.setUpdateDate(new Date());
//			billRefund.setCreateId(billRefundDto.getCreateId());
//			billRefund.setCreateDate(billRefundDto.getCreateDate());
			billRefund.setRemark(billRefundDto.getRemark());
			billRefund.setRemark1(billRefundDto.getRemark1());
			billRefund.setRemark2(billRefundDto.getRemark2());
			billRefund.setRemark3(billRefundDto.getRemark3());
			billRefund.setRemark4(billRefundDto.getRemark4());
			billRefund.setMemberNoGuid(billRefundDto.getMemberNoGuid());
			billRefund.setMemberNameGuid(billRefundDto.getMemberNameGuid());
			AssertUtils.notUpdateMoreThanOne(billRefundDao.updateByPrimaryKeySelective(billRefund));
			logger.debug("updateBillRefund(BillRefundDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("账单退款信息信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_UPDATE_ERROR,"账单退款信息信息更新信息错误！",e);
		}
	}

	

	@Override
	public BillRefundDto findBillRefund(
			BillRefundDto billRefundDto) throws TsfaServiceException {
		logger.debug("findBillRefund(FindBillRefund findBillRefund={}) - start", billRefundDto); //$NON-NLS-1$

		AssertUtils.notNull(billRefundDto);
		AssertUtils.notAllNull(billRefundDto.getCode(),"Code不能为空");
		try {
			BillRefund billRefund = billRefundDao.selectByPrimaryKey(billRefundDto.getCode());
			if(billRefund == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.BILL_REFUND_NOT_EXIST_ERROR,"账单退款信息信息不存在");
			}
			BillRefundDto findBillRefundReturn = new BillRefundDto();
			//find数据录入
			findBillRefundReturn.setCode(billRefund.getCode());
			findBillRefundReturn.setBillCode(billRefund.getBillCode());
			findBillRefundReturn.setOperateCode(billRefund.getOperateCode());
			findBillRefundReturn.setShopNo(billRefund.getShopNo());
			findBillRefundReturn.setShopName(billRefund.getShopName());
			findBillRefundReturn.setMerchantNo(billRefund.getMerchantNo());
			findBillRefundReturn.setMerchantName(billRefund.getMerchantName());
			findBillRefundReturn.setPatientNo(billRefund.getPatientNo());
			findBillRefundReturn.setPatientName(billRefund.getPatientName());
			findBillRefundReturn.setMedicalNo(billRefund.getMedicalNo());
			findBillRefundReturn.setPayType(billRefund.getPayType());
			findBillRefundReturn.setPayTypeName(billRefund.getPayTypeName());
			findBillRefundReturn.setRtAmount(billRefund.getRtAmount());
			findBillRefundReturn.setRefundGdNo(billRefund.getRefundGdNo());
			findBillRefundReturn.setRefundGdName(billRefund.getRefundGdName());
			findBillRefundReturn.setRefundTime(billRefund.getRefundTime());
			findBillRefundReturn.setRefundStatus(billRefund.getRefundStatus());
			findBillRefundReturn.setStatus(billRefund.getStatus());
			findBillRefundReturn.setRefundType(billRefund.getRefundType());
			findBillRefundReturn.setUpdateId(billRefund.getUpdateId());
			findBillRefundReturn.setUpdateDate(billRefund.getUpdateDate());
			findBillRefundReturn.setCreateId(billRefund.getCreateId());
			findBillRefundReturn.setCreateDate(billRefund.getCreateDate());
			findBillRefundReturn.setRemark(billRefund.getRemark());
			findBillRefundReturn.setRemark1(billRefund.getRemark1());
			findBillRefundReturn.setRemark2(billRefund.getRemark2());
			findBillRefundReturn.setRemark3(billRefund.getRemark3());
			findBillRefundReturn.setRemark4(billRefund.getRemark4());
			
			findBillRefundReturn.setMemberNoGuid(billRefund.getMemberNoGuid());
			findBillRefundReturn.setMemberNameGuid(billRefund.getMemberNameGuid());
			
			logger.debug("findBillRefund(BillRefundDto) - end - return value={}", findBillRefundReturn); //$NON-NLS-1$
			return findBillRefundReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找账单退款信息信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_FIND_ERROR,"查找账单退款信息信息信息错误！",e);
		}


	}

	
	@Override
	public Page<BillRefundDto> findBillRefundPage(
			FindBillRefundPage findBillRefundPage)
			throws TsfaServiceException {
		logger.debug("findBillRefundPage(FindBillRefundPage findBillRefundPage={}) - start", findBillRefundPage); //$NON-NLS-1$

		AssertUtils.notNull(findBillRefundPage);
		List<BillRefundDto> returnList=null;
		int count = 0;
		try {
			returnList = billRefundDao.findBillRefundPage(findBillRefundPage);
			count = billRefundDao.findBillRefundPageCount(findBillRefundPage);
		}  catch (Exception e) {
			logger.error("账单退款信息信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_FIND_PAGE_ERROR,"账单退款信息信息不存在错误.！",e);
		}
		Page<BillRefundDto> returnPage = new Page<BillRefundDto>(returnList, count, findBillRefundPage);

		logger.debug("findBillRefundPage(FindBillRefundPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public BillRefundDto findBillRefundByOperateCode(BillRefundDto billRefundDto) throws TsfaServiceException {
		logger.debug("findBillRefundByOperateCode(FindBillRefund findBillRefund={}) - start", billRefundDto); //$NON-NLS-1$

		AssertUtils.notNull(billRefundDto);
		AssertUtils.notAllNull(billRefundDto.getCode(),"Code不能为空");
		try {
			BillRefundDto findBillRefundReturn=null;
			
			FindBillRefundPage findBillRefundPage =new FindBillRefundPage();
			findBillRefundPage.setParam(billRefundDto);
			List<BillRefundDto>  billRefunds = billRefundDao.findBillRefunds(findBillRefundPage);
			if (billRefunds != null && billRefunds.size() > 0) {
				findBillRefundReturn=billRefunds.get(0);
			}
			return findBillRefundReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找账单退款信息信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_REFUND_FIND_ERROR,"查找账单退款信息信息信息错误！",e);
		}
	
	} 


}
