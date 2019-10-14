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
import com.ye.business.hx.dao.IBillOperateDao;
import com.ye.business.hx.domain.BillOperate;
import com.ye.business.hx.dto.BillOperateDto;
import com.ye.business.hx.dto.FindBillOperatePage;
import com.ye.business.hx.service.IBillOperateService;
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
public class BillOperateServiceImpl implements IBillOperateService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BillOperateServiceImpl.class);
	

	@Resource
	private IBillOperateDao billOperateDao;
	
	
	@Override
	public void addBillOperate(
			BillOperateDto billOperateDto) throws TsfaServiceException {
		logger.debug("addBillOperate(AddBillOperate addBillOperate={}) - start", billOperateDto); 

		AssertUtils.notNull(billOperateDto);
		try {
			BillOperate billOperate = new BillOperate();
			//add数据录入
			billOperate.setCode(GUID.getPreUUID());
			billOperate.setBillCode(billOperateDto.getBillCode());
			billOperate.setOperateType(billOperateDto.getOperateType());
			billOperate.setStatus(billOperateDto.getStatus());
			billOperate.setProcess(billOperateDto.getProcess());
			billOperate.setCheckStatus(billOperateDto.getCheckStatus());
			billOperate.setMemberNoGuid(billOperateDto.getMemberNoGuid());
			billOperate.setMemberNameGuid(billOperateDto.getMemberNameGuid());
			billOperate.setCheckerNoGuid(billOperateDto.getCheckerNoGuid());
			billOperate.setCheckerNameGuid(billOperateDto.getCheckerNameGuid());
			billOperate.setApplyTime(billOperateDto.getApplyTime());
			billOperate.setCheckTime(billOperateDto.getCheckTime());
			billOperate.setPayType(billOperateDto.getPayType());
			billOperate.setPayTypeName(billOperateDto.getPayTypeName());
			billOperate.setPayRemark(billOperateDto.getPayRemark());
			billOperate.setPayAmount(billOperateDto.getPayAmount());
			billOperate.setPayTime(billOperateDto.getPayTime());
			billOperate.setRecieverNo(billOperateDto.getRecieverNo());
			billOperate.setRecieverName(billOperateDto.getRecieverName());
			billOperate.setOriginalAmount(billOperateDto.getOriginalAmount());
			billOperate.setReallyAmount(billOperateDto.getReallyAmount());
			billOperate.setDiscountNum(billOperateDto.getDiscountNum());
			billOperate.setDebtAmount(billOperateDto.getDebtAmount());
			billOperate.setUpdateId(billOperateDto.getUpdateId());
			billOperate.setUpdateDate(new Date());
			billOperate.setCreateId(billOperateDto.getCreateId());
			billOperate.setCreateDate(new Date());
			billOperate.setRemark(billOperateDto.getRemark());
			billOperate.setRemark1(billOperateDto.getRemark1());
			billOperate.setRemark2(billOperateDto.getRemark2());
			billOperate.setRemark3(billOperateDto.getRemark3());
			billOperate.setRemark4(billOperateDto.getRemark4());
			
			billOperate.setRefundType(billOperateDto.getRefundType());
			billOperate.setShopNo(billOperateDto.getShopNo());
			billOperate.setShopName(billOperateDto.getShopName());
			billOperate.setMerchantNo(billOperateDto.getMerchantNo());
			billOperate.setMerchantName(billOperateDto.getMerchantName());
			billOperate.setBillPayAmount(billOperateDto.getBillPayAmount());
			billOperateDao.insertSelective(billOperate);
			logger.debug("addBillOperate(BillOperateDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单交易操作信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_ADD_ERROR,"新增账单交易操作信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询账单交易操作信息
	 *
	 * @param findBillOperatePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<BillOperateDto>  findBillOperates(FindBillOperatePage findBillOperatePage)throws TsfaServiceException{
		AssertUtils.notNull(findBillOperatePage);
		List<BillOperateDto> returnList=null;
		try {
			returnList = billOperateDao.findBillOperates(findBillOperatePage);
		} catch (Exception e) {
			logger.error("查找账单交易操作信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_NOT_EXIST_ERROR,"账单交易操作信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateBillOperate(
			BillOperateDto billOperateDto)
			throws TsfaServiceException {
		logger.debug("updateBillOperate(BillOperateDto billOperateDto={}) - start", billOperateDto); //$NON-NLS-1$

		AssertUtils.notNull(billOperateDto);
		AssertUtils.notNullAndEmpty(billOperateDto.getCode(),"Code不能为空");
		try {
			BillOperate billOperate = new BillOperate();
			//update数据录入
			billOperate.setCode(billOperateDto.getCode());
			billOperate.setBillCode(billOperateDto.getBillCode());
			billOperate.setOperateType(billOperateDto.getOperateType());
			billOperate.setStatus(billOperateDto.getStatus());
			billOperate.setProcess(billOperateDto.getProcess());
			billOperate.setCheckStatus(billOperateDto.getCheckStatus());
			billOperate.setMemberNoGuid(billOperateDto.getMemberNoGuid());
			billOperate.setMemberNameGuid(billOperateDto.getMemberNameGuid());
			billOperate.setCheckerNoGuid(billOperateDto.getCheckerNoGuid());
			billOperate.setCheckerNameGuid(billOperateDto.getCheckerNameGuid());
			billOperate.setApplyTime(billOperateDto.getApplyTime());
			billOperate.setCheckTime(billOperateDto.getCheckTime());
			billOperate.setPayType(billOperateDto.getPayType());
			billOperate.setPayTypeName(billOperateDto.getPayTypeName());
			billOperate.setPayRemark(billOperateDto.getPayRemark());
			billOperate.setPayAmount(billOperateDto.getPayAmount());
			billOperate.setPayTime(billOperateDto.getPayTime());
			billOperate.setRecieverNo(billOperateDto.getRecieverNo());
			billOperate.setRecieverName(billOperateDto.getRecieverName());
			billOperate.setOriginalAmount(billOperateDto.getOriginalAmount());
			billOperate.setReallyAmount(billOperateDto.getReallyAmount());
			billOperate.setDiscountNum(billOperateDto.getDiscountNum());
			billOperate.setDebtAmount(billOperateDto.getDebtAmount());
			billOperate.setUpdateId(billOperateDto.getUpdateId());
			billOperate.setUpdateDate(new Date());
//			billOperate.setCreateId(billOperateDto.getCreateId());
//			billOperate.setCreateDate(billOperateDto.getCreateDate());
			billOperate.setRemark(billOperateDto.getRemark());
			billOperate.setRemark1(billOperateDto.getRemark1());
			billOperate.setRemark2(billOperateDto.getRemark2());
			billOperate.setRemark3(billOperateDto.getRemark3());
			billOperate.setRemark4(billOperateDto.getRemark4());
			
			billOperate.setRefundType(billOperateDto.getRefundType());
			billOperate.setShopNo(billOperateDto.getShopNo());
			billOperate.setShopName(billOperateDto.getShopName());
			billOperate.setMerchantNo(billOperateDto.getMerchantNo());
			billOperate.setMerchantName(billOperateDto.getMerchantName());
			billOperate.setBillPayAmount(billOperateDto.getBillPayAmount());
			AssertUtils.notUpdateMoreThanOne(billOperateDao.updateByPrimaryKeySelective(billOperate));
			logger.debug("updateBillOperate(BillOperateDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("账单交易操作信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_UPDATE_ERROR,"账单交易操作信息更新信息错误！",e);
		}
	}

	

	@Override
	public BillOperateDto findBillOperate(
			BillOperateDto billOperateDto) throws TsfaServiceException {
		logger.debug("findBillOperate(FindBillOperate findBillOperate={}) - start", billOperateDto); //$NON-NLS-1$

		AssertUtils.notNull(billOperateDto);
		AssertUtils.notAllNull(billOperateDto.getCode(),"Code不能为空");
		try {
			BillOperate billOperate = billOperateDao.selectByPrimaryKey(billOperateDto.getCode());
			if(billOperate == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.BILL_OPERATE_NOT_EXIST_ERROR,"账单交易操作信息不存在");
			}
			BillOperateDto findBillOperateReturn = new BillOperateDto();
			//find数据录入
			findBillOperateReturn.setCode(billOperate.getCode());
			findBillOperateReturn.setBillCode(billOperate.getBillCode());
			findBillOperateReturn.setOperateType(billOperate.getOperateType());
			findBillOperateReturn.setStatus(billOperate.getStatus());
			findBillOperateReturn.setProcess(billOperate.getProcess());
			findBillOperateReturn.setCheckStatus(billOperate.getCheckStatus());
			findBillOperateReturn.setMemberNoGuid(billOperate.getMemberNoGuid());
			findBillOperateReturn.setMemberNameGuid(billOperate.getMemberNameGuid());
			findBillOperateReturn.setCheckerNoGuid(billOperate.getCheckerNoGuid());
			findBillOperateReturn.setCheckerNameGuid(billOperate.getCheckerNameGuid());
			findBillOperateReturn.setApplyTime(billOperate.getApplyTime());
			findBillOperateReturn.setCheckTime(billOperate.getCheckTime());
			findBillOperateReturn.setPayType(billOperate.getPayType());
			findBillOperateReturn.setPayTypeName(billOperate.getPayTypeName());
			findBillOperateReturn.setPayRemark(billOperate.getPayRemark());
			findBillOperateReturn.setPayAmount(billOperate.getPayAmount());
			findBillOperateReturn.setPayTime(billOperate.getPayTime());
			findBillOperateReturn.setRecieverNo(billOperate.getRecieverNo());
			findBillOperateReturn.setRecieverName(billOperate.getRecieverName());
			findBillOperateReturn.setOriginalAmount(billOperate.getOriginalAmount());
			findBillOperateReturn.setReallyAmount(billOperate.getReallyAmount());
			findBillOperateReturn.setDiscountNum(billOperate.getDiscountNum());
			findBillOperateReturn.setDebtAmount(billOperate.getDebtAmount());
			findBillOperateReturn.setUpdateId(billOperate.getUpdateId());
			findBillOperateReturn.setUpdateDate(billOperate.getUpdateDate());
			findBillOperateReturn.setCreateId(billOperate.getCreateId());
			findBillOperateReturn.setCreateDate(billOperate.getCreateDate());
			findBillOperateReturn.setRemark(billOperate.getRemark());
			findBillOperateReturn.setRemark1(billOperate.getRemark1());
			findBillOperateReturn.setRemark2(billOperate.getRemark2());
			findBillOperateReturn.setRemark3(billOperate.getRemark3());
			findBillOperateReturn.setRemark4(billOperate.getRemark4());
			findBillOperateReturn.setRefundType(billOperate.getRefundType());
			findBillOperateReturn.setShopNo(billOperate.getShopNo());
			findBillOperateReturn.setShopName(billOperate.getShopName());
			findBillOperateReturn.setMerchantNo(billOperate.getMerchantNo());
			findBillOperateReturn.setMerchantName(billOperate.getMerchantName());
			findBillOperateReturn.setBillPayAmount(billOperate.getBillPayAmount());
			logger.debug("findBillOperate(BillOperateDto) - end - return value={}", findBillOperateReturn); //$NON-NLS-1$
			return findBillOperateReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找账单交易操作信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_FIND_ERROR,"查找账单交易操作信息信息错误！",e);
		}


	}

	
	@Override
	public Page<BillOperateDto> findBillOperatePage(
			FindBillOperatePage findBillOperatePage)
			throws TsfaServiceException {
		logger.debug("findBillOperatePage(FindBillOperatePage findBillOperatePage={}) - start", findBillOperatePage); //$NON-NLS-1$

		AssertUtils.notNull(findBillOperatePage);
		List<BillOperateDto> returnList=null;
		int count = 0;
		try {
			returnList = billOperateDao.findBillOperatePage(findBillOperatePage);
			count = billOperateDao.findBillOperatePageCount(findBillOperatePage);
		}  catch (Exception e) {
			logger.error("账单交易操作信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_FIND_PAGE_ERROR,"账单交易操作信息不存在错误.！",e);
		}
		Page<BillOperateDto> returnPage = new Page<BillOperateDto>(returnList, count, findBillOperatePage);

		logger.debug("findBillOperatePage(FindBillOperatePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 

	@Override
	public Page<BillOperateDto> findUntreatedBillOperatePage(
			FindBillOperatePage findBillOperatePage)
			throws TsfaServiceException {
		logger.debug("findUntreatedBillOperatePage(FindBillOperatePage findBillOperatePage={}) - start", findBillOperatePage); //$NON-NLS-1$

		AssertUtils.notNull(findBillOperatePage);
		List<BillOperateDto> returnList=null;
		int count = 0;
		try {
			returnList = billOperateDao.findUntreatedBillOperatePage(findBillOperatePage);
			count = billOperateDao.findUntreatedBillOperatePageCount(findBillOperatePage);
		}  catch (Exception e) {
			logger.error("账单交易操作信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_OPERATE_FIND_PAGE_ERROR,"账单交易操作信息不存在错误.！",e);
		}
		Page<BillOperateDto> returnPage = new Page<BillOperateDto>(returnList, count, findBillOperatePage);

		logger.debug("findUntreatedBillOperatePage(FindBillOperatePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 

	
}
