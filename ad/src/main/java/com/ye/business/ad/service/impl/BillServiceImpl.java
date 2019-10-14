package com.ye.business.ad.service.impl;

/**
 * Copyright &copy; 2018-2021  All rights reserved.
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
import com.ye.business.ad.constant.ErrorCodeBill;
import com.ye.business.ad.dao.IBillDao;
import com.ye.business.ad.domain.Bill;
import com.ye.business.ad.dto.BillDto;
import com.ye.business.ad.dto.FindBillPage;
import com.ye.business.ad.service.IBillService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
@Service
public class BillServiceImpl implements IBillService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);
	

	@Resource
	private IBillDao billDao;
	
	
	@Override
	public void addBill(
			BillDto billDto) throws TsfaServiceException {
		logger.debug("addBill(AddBill addBill={}) - start", billDto); 

		AssertUtils.notNull(billDto);
		try {
			Bill bill = new Bill();
			//add数据录入
			bill.setCode(billDto.getCode());
			bill.setTradeNo(billDto.getTradeNo());
			bill.setTradeType(billDto.getTradeType());
			bill.setMemberNo(billDto.getMemberNo());
			bill.setMemberName(billDto.getMemberName());
			bill.setMerchantNo(billDto.getMerchantNo());
			bill.setMerchantName(billDto.getMerchantName());
			bill.setLoginName(billDto.getLoginName());
			bill.setAmount(billDto.getAmount());
			bill.setCreateTime(billDto.getCreateTime());
			bill.setCreateId(billDto.getCreateId());
			bill.setRemark(billDto.getRemark());
			bill.setRemark2(billDto.getRemark2());
			bill.setRemark3(billDto.getRemark3());
			bill.setRemark4(billDto.getRemark4());
			bill.setAdvertiseCode(billDto.getAdvertiseCode());
			bill.setArticleCode(billDto.getArticleCode());
			
			bill.setCode(GUID.getPreUUID());
			
			billDao.insertSelective(bill);
			logger.debug("addBill(BillDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增交易流水信息错误！",e);
			throw new TsfaServiceException(ErrorCodeBill.BILL_ADD_ERROR,"新增交易流水信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询交易流水信息
	 *
	 * @param findBillPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<BillDto>  findBills(FindBillPage findBillPage)throws TsfaServiceException{
		AssertUtils.notNull(findBillPage);
		List<BillDto> returnList=null;
		try {
			returnList = billDao.findBills(findBillPage);
		} catch (Exception e) {
			logger.error("查找交易流水信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeBill.BILL_NOT_EXIST_ERROR,"交易流水信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateBill(
			BillDto billDto)
			throws TsfaServiceException {
		logger.debug("updateBill(BillDto billDto={}) - start", billDto); 

		AssertUtils.notNull(billDto);
		AssertUtils.notNullAndEmpty(billDto.getCode(),"Code不能为空");
		try {
			Bill bill = new Bill();
			//update数据录入
			bill.setCode(billDto.getCode());
			bill.setTradeNo(billDto.getTradeNo());
			bill.setTradeType(billDto.getTradeType());
			bill.setMemberNo(billDto.getMemberNo());
			bill.setMemberName(billDto.getMemberName());
			bill.setMerchantNo(billDto.getMerchantNo());
			bill.setMerchantName(billDto.getMerchantName());
			bill.setLoginName(billDto.getLoginName());
			bill.setAmount(billDto.getAmount());
			bill.setCreateTime(billDto.getCreateTime());
			bill.setCreateId(billDto.getCreateId());
			bill.setRemark(billDto.getRemark());
			bill.setRemark2(billDto.getRemark2());
			bill.setRemark3(billDto.getRemark3());
			bill.setRemark4(billDto.getRemark4());
			bill.setAdvertiseCode(billDto.getAdvertiseCode());
			bill.setArticleCode(billDto.getArticleCode());
			AssertUtils.notUpdateMoreThanOne(billDao.updateByPrimaryKeySelective(bill));
			logger.debug("updateBill(BillDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("交易流水信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCodeBill.BILL_UPDATE_ERROR,"交易流水信息更新信息错误！",e);
		}
	}

	

	@Override
	public BillDto findBill(
			BillDto billDto) throws TsfaServiceException {
		logger.debug("findBill(FindBill findBill={}) - start", billDto); 

		AssertUtils.notNull(billDto);
		AssertUtils.notAllNull(billDto.getCode(),"Code不能为空");
		try {
			Bill bill = billDao.selectByPrimaryKey(billDto.getCode());
			if(bill == null){
				return null;
				//throw new TsfaServiceException(ErrorCodeBill.BILL_NOT_EXIST_ERROR,"交易流水信息不存在");
			}
			BillDto findBillReturn = new BillDto();
			//find数据录入
			findBillReturn.setCode(bill.getCode());
			findBillReturn.setTradeNo(bill.getTradeNo());
			findBillReturn.setTradeType(bill.getTradeType());
			findBillReturn.setMemberNo(bill.getMemberNo());
			findBillReturn.setMemberName(bill.getMemberName());
			findBillReturn.setMerchantNo(bill.getMerchantNo());
			findBillReturn.setMerchantName(bill.getMerchantName());
			findBillReturn.setLoginName(bill.getLoginName());
			findBillReturn.setAmount(bill.getAmount());
			findBillReturn.setCreateTime(bill.getCreateTime());
			findBillReturn.setCreateId(bill.getCreateId());
			findBillReturn.setRemark(bill.getRemark());
			findBillReturn.setRemark2(bill.getRemark2());
			findBillReturn.setRemark3(bill.getRemark3());
			findBillReturn.setRemark4(bill.getRemark4());
			findBillReturn.setAdvertiseCode(bill.getAdvertiseCode());
			findBillReturn.setArticleCode(bill.getArticleCode());
			
			logger.debug("findBill(BillDto) - end - return value={}", findBillReturn); 
			return findBillReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找交易流水信息信息错误！",e);
			throw new TsfaServiceException(ErrorCodeBill.BILL_FIND_ERROR,"查找交易流水信息信息错误！",e);
		}


	}

	
	@Override
	public Page<BillDto> findBillPage(
			FindBillPage findBillPage)
			throws TsfaServiceException {
		logger.debug("findBillPage(FindBillPage findBillPage={}) - start", findBillPage); 

		AssertUtils.notNull(findBillPage);
		List<BillDto> returnList=null;
		int count = 0;
		try {
			returnList = billDao.findBillPage(findBillPage);
			count = billDao.findBillPageCount(findBillPage);
		}  catch (Exception e) {
			logger.error("交易流水信息不存在错误",e);
			throw new TsfaServiceException(ErrorCodeBill.BILL_FIND_PAGE_ERROR,"交易流水信息不存在错误.！",e);
		}
		Page<BillDto> returnPage = new Page<BillDto>(returnList, count, findBillPage);

		logger.debug("findBillPage(FindBillPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
