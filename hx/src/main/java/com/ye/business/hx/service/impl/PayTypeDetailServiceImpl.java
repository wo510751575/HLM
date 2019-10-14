package com.ye.business.hx.service.impl;

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
import com.ye.business.hx.dao.IPayTypeDetailDao;
import com.ye.business.hx.domain.PayTypeDetail;
import com.ye.business.hx.dto.FindPayTypeDetailPage;
import com.ye.business.hx.dto.PayTypeDetailDto;
import com.ye.business.hx.service.IPayTypeDetailService;
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
public class PayTypeDetailServiceImpl implements IPayTypeDetailService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PayTypeDetailServiceImpl.class);
	

	@Resource
	private IPayTypeDetailDao payTypeDetailDao;
	
	
	@Override
	public void addPayTypeDetail(
			PayTypeDetailDto payTypeDetailDto) throws TsfaServiceException {
		logger.debug("addPayTypeDetail(AddPayTypeDetail addPayTypeDetail={}) - start", payTypeDetailDto); 

		AssertUtils.notNull(payTypeDetailDto);
		try {
			PayTypeDetail payTypeDetail = new PayTypeDetail();
			//add数据录入
			payTypeDetail.setCode(GUID.getPreUUID());
			payTypeDetail.setBillCode(payTypeDetailDto.getBillCode());
			payTypeDetail.setOperateCode(payTypeDetailDto.getOperateCode());
			payTypeDetail.setPayType(payTypeDetailDto.getPayType());
			payTypeDetail.setPayTypeName(payTypeDetailDto.getPayTypeName());
			payTypeDetail.setPayAmount(payTypeDetailDto.getPayAmount());
			payTypeDetail.setIndexNo(payTypeDetailDto.getIndexNo());
			payTypeDetailDao.insertSelective(payTypeDetail);
			logger.debug("addPayTypeDetail(PayTypeDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增支付方式详细信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PAY_TYPE_DETAIL_ADD_ERROR,"新增支付方式详细信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询支付方式详细信息
	 *
	 * @param findPayTypeDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<PayTypeDetailDto>  findPayTypeDetails(FindPayTypeDetailPage findPayTypeDetailPage)throws TsfaServiceException{
		AssertUtils.notNull(findPayTypeDetailPage);
		List<PayTypeDetailDto> returnList=null;
		try {
			returnList = payTypeDetailDao.findPayTypeDetails(findPayTypeDetailPage);
		} catch (Exception e) {
			logger.error("查找支付方式详细信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PAY_TYPE_DETAIL_NOT_EXIST_ERROR,"支付方式详细信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePayTypeDetail(
			PayTypeDetailDto payTypeDetailDto)
			throws TsfaServiceException {
		logger.debug("updatePayTypeDetail(PayTypeDetailDto payTypeDetailDto={}) - start", payTypeDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(payTypeDetailDto);
		AssertUtils.notNullAndEmpty(payTypeDetailDto.getCode(),"Code不能为空");
		try {
			PayTypeDetail payTypeDetail = new PayTypeDetail();
			//update数据录入
			payTypeDetail.setCode(payTypeDetailDto.getCode());
			payTypeDetail.setBillCode(payTypeDetailDto.getBillCode());
			payTypeDetail.setOperateCode(payTypeDetailDto.getOperateCode());
			payTypeDetail.setPayType(payTypeDetailDto.getPayType());
			payTypeDetail.setPayTypeName(payTypeDetailDto.getPayTypeName());
			payTypeDetail.setPayAmount(payTypeDetailDto.getPayAmount());
			payTypeDetail.setIndexNo(payTypeDetailDto.getIndexNo());
			AssertUtils.notUpdateMoreThanOne(payTypeDetailDao.updateByPrimaryKeySelective(payTypeDetail));
			logger.debug("updatePayTypeDetail(PayTypeDetailDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("支付方式详细信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PAY_TYPE_DETAIL_UPDATE_ERROR,"支付方式详细信息更新信息错误！",e);
		}
	}

	

	@Override
	public PayTypeDetailDto findPayTypeDetail(
			PayTypeDetailDto payTypeDetailDto) throws TsfaServiceException {
		logger.debug("findPayTypeDetail(FindPayTypeDetail findPayTypeDetail={}) - start", payTypeDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(payTypeDetailDto);
		AssertUtils.notAllNull(payTypeDetailDto.getCode(),"Code不能为空");
		try {
			PayTypeDetail payTypeDetail = payTypeDetailDao.selectByPrimaryKey(payTypeDetailDto.getCode());
			if(payTypeDetail == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PAY_TYPE_DETAIL_NOT_EXIST_ERROR,"支付方式详细信息不存在");
			}
			PayTypeDetailDto findPayTypeDetailReturn = new PayTypeDetailDto();
			//find数据录入
			findPayTypeDetailReturn.setCode(payTypeDetail.getCode());
			findPayTypeDetailReturn.setBillCode(payTypeDetail.getBillCode());
			findPayTypeDetailReturn.setOperateCode(payTypeDetail.getOperateCode());
			findPayTypeDetailReturn.setPayType(payTypeDetail.getPayType());
			findPayTypeDetailReturn.setPayTypeName(payTypeDetail.getPayTypeName());
			findPayTypeDetailReturn.setPayAmount(payTypeDetail.getPayAmount());
			findPayTypeDetailReturn.setIndexNo(payTypeDetail.getIndexNo());
			
			logger.debug("findPayTypeDetail(PayTypeDetailDto) - end - return value={}", findPayTypeDetailReturn); //$NON-NLS-1$
			return findPayTypeDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找支付方式详细信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PAY_TYPE_DETAIL_FIND_ERROR,"查找支付方式详细信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PayTypeDetailDto> findPayTypeDetailPage(
			FindPayTypeDetailPage findPayTypeDetailPage)
			throws TsfaServiceException {
		logger.debug("findPayTypeDetailPage(FindPayTypeDetailPage findPayTypeDetailPage={}) - start", findPayTypeDetailPage); //$NON-NLS-1$

		AssertUtils.notNull(findPayTypeDetailPage);
		List<PayTypeDetailDto> returnList=null;
		int count = 0;
		try {
			returnList = payTypeDetailDao.findPayTypeDetailPage(findPayTypeDetailPage);
			count = payTypeDetailDao.findPayTypeDetailPageCount(findPayTypeDetailPage);
		}  catch (Exception e) {
			logger.error("支付方式详细信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PAY_TYPE_DETAIL_FIND_PAGE_ERROR,"支付方式详细信息不存在错误.！",e);
		}
		Page<PayTypeDetailDto> returnPage = new Page<PayTypeDetailDto>(returnList, count, findPayTypeDetailPage);

		logger.debug("findPayTypeDetailPage(FindPayTypeDetailPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


}
