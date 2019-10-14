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
import com.ye.business.hx.dao.IPayDetailDao;
import com.ye.business.hx.domain.PayDetail;
import com.ye.business.hx.dto.FindPayDetailPage;
import com.ye.business.hx.dto.PayDetailDto;
import com.ye.business.hx.service.IPayDetailService;
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
public class PayDetailServiceImpl implements IPayDetailService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PayDetailServiceImpl.class);
	

	@Resource
	private IPayDetailDao payDetailDao;
	
	
	@Override
	public void addPayDetail(
			PayDetailDto payDetailDto) throws TsfaServiceException {
		logger.debug("addPayDetail(AddPayDetail addPayDetail={}) - start", payDetailDto); 

		AssertUtils.notNull(payDetailDto);
		try {
			PayDetail payDetail = new PayDetail();
			//add数据录入
			payDetail.setCode(GUID.getPreUUID());
			payDetail.setBillCode(payDetailDto.getBillCode());
			payDetail.setOperateCode(payDetailDto.getOperateCode());
			payDetail.setProjectCode(payDetailDto.getProjectCode());
			payDetail.setProjectName(payDetailDto.getProjectName());
			payDetail.setPayAmount(payDetailDto.getPayAmount());
			payDetail.setReallyAmount(payDetailDto.getReallyAmount());
			payDetail.setDebtAmount(payDetailDto.getDebtAmount());
			payDetail.setOriginalPayAmount(payDetailDto.getOriginalPayAmount());
			payDetail.setOriginalReallyAmount(payDetailDto.getOriginalReallyAmount());
			payDetail.setOriginalDebtAmount(payDetailDto.getOriginalDebtAmount());
			payDetail.setIndexNo(payDetailDto.getIndexNo());
			payDetailDao.insertSelective(payDetail);
			logger.debug("addPayDetail(PayDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增收费项目详细信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PAY_DETAIL_ADD_ERROR,"新增收费项目详细信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询收费项目详细信息
	 *
	 * @param findPayDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<PayDetailDto>  findPayDetails(FindPayDetailPage findPayDetailPage)throws TsfaServiceException{
		AssertUtils.notNull(findPayDetailPage);
		List<PayDetailDto> returnList=null;
		try {
			returnList = payDetailDao.findPayDetails(findPayDetailPage);
		} catch (Exception e) {
			logger.error("查找收费项目详细信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PAY_DETAIL_NOT_EXIST_ERROR,"收费项目详细信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updatePayDetail(
			PayDetailDto payDetailDto)
			throws TsfaServiceException {
		logger.debug("updatePayDetail(PayDetailDto payDetailDto={}) - start", payDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(payDetailDto);
		AssertUtils.notNullAndEmpty(payDetailDto.getCode(),"Code不能为空");
		try {
			PayDetail payDetail = new PayDetail();
			//update数据录入
			payDetail.setCode(payDetailDto.getCode());
			payDetail.setBillCode(payDetailDto.getBillCode());
			payDetail.setOperateCode(payDetailDto.getOperateCode());
			payDetail.setProjectCode(payDetailDto.getProjectCode());
			payDetail.setProjectName(payDetailDto.getProjectName());
			payDetail.setPayAmount(payDetailDto.getPayAmount());
			payDetail.setReallyAmount(payDetailDto.getReallyAmount());
			payDetail.setDebtAmount(payDetailDto.getDebtAmount());
			payDetail.setOriginalPayAmount(payDetailDto.getOriginalPayAmount());
			payDetail.setOriginalReallyAmount(payDetailDto.getOriginalReallyAmount());
			payDetail.setOriginalDebtAmount(payDetailDto.getOriginalDebtAmount());
			payDetail.setIndexNo(payDetailDto.getIndexNo());
			AssertUtils.notUpdateMoreThanOne(payDetailDao.updateByPrimaryKeySelective(payDetail));
			logger.debug("updatePayDetail(PayDetailDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("收费项目详细信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PAY_DETAIL_UPDATE_ERROR,"收费项目详细信息更新信息错误！",e);
		}
	}

	

	@Override
	public PayDetailDto findPayDetail(
			PayDetailDto payDetailDto) throws TsfaServiceException {
		logger.debug("findPayDetail(FindPayDetail findPayDetail={}) - start", payDetailDto); //$NON-NLS-1$

		AssertUtils.notNull(payDetailDto);
		AssertUtils.notAllNull(payDetailDto.getCode(),"Code不能为空");
		try {
			PayDetail payDetail = payDetailDao.selectByPrimaryKey(payDetailDto.getCode());
			if(payDetail == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PAY_DETAIL_NOT_EXIST_ERROR,"收费项目详细信息不存在");
			}
			PayDetailDto findPayDetailReturn = new PayDetailDto();
			//find数据录入
			findPayDetailReturn.setCode(payDetail.getCode());
			findPayDetailReturn.setBillCode(payDetail.getBillCode());
			findPayDetailReturn.setOperateCode(payDetail.getOperateCode());
			findPayDetailReturn.setProjectCode(payDetail.getProjectCode());
			findPayDetailReturn.setProjectName(payDetail.getProjectName());
			findPayDetailReturn.setPayAmount(payDetail.getPayAmount());
			findPayDetailReturn.setReallyAmount(payDetail.getReallyAmount());
			findPayDetailReturn.setDebtAmount(payDetail.getDebtAmount());
			findPayDetailReturn.setOriginalPayAmount(payDetail.getOriginalPayAmount());
			findPayDetailReturn.setOriginalReallyAmount(payDetail.getOriginalReallyAmount());
			findPayDetailReturn.setOriginalDebtAmount(payDetail.getOriginalDebtAmount());
			findPayDetailReturn.setIndexNo(payDetail.getIndexNo());
			logger.debug("findPayDetail(PayDetailDto) - end - return value={}", findPayDetailReturn); //$NON-NLS-1$
			return findPayDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找收费项目详细信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PAY_DETAIL_FIND_ERROR,"查找收费项目详细信息信息错误！",e);
		}


	}

	
	@Override
	public Page<PayDetailDto> findPayDetailPage(
			FindPayDetailPage findPayDetailPage)
			throws TsfaServiceException {
		logger.debug("findPayDetailPage(FindPayDetailPage findPayDetailPage={}) - start", findPayDetailPage); //$NON-NLS-1$

		AssertUtils.notNull(findPayDetailPage);
		List<PayDetailDto> returnList=null;
		int count = 0;
		try {
			returnList = payDetailDao.findPayDetailPage(findPayDetailPage);
			count = payDetailDao.findPayDetailPageCount(findPayDetailPage);
		}  catch (Exception e) {
			logger.error("收费项目详细信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PAY_DETAIL_FIND_PAGE_ERROR,"收费项目详细信息不存在错误.！",e);
		}
		Page<PayDetailDto> returnPage = new Page<PayDetailDto>(returnList, count, findPayDetailPage);

		logger.debug("findPayDetailPage(FindPayDetailPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


}
