package com.lj.business.member.service.impl;

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
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.ITmallBonusRecordDao;
import com.lj.business.member.domain.TmallBonusRecord;
import com.lj.business.member.dto.FindTmallBonusRecordPage;
import com.lj.business.member.dto.TmallBonusRecordDto;
import com.lj.business.member.service.ITmallBonusRecordService;
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
public class TmallBonusRecordServiceImpl implements ITmallBonusRecordService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(TmallBonusRecordServiceImpl.class);
	

	@Resource
	private ITmallBonusRecordDao tmallBonusRecordDao;
	
	
	@Override
	public void addTmallBonusRecord(
			TmallBonusRecordDto tmallBonusRecordDto) throws TsfaServiceException {
		logger.debug("addTmallBonusRecord(AddTmallBonusRecord addTmallBonusRecord={}) - start", tmallBonusRecordDto); 

		AssertUtils.notNull(tmallBonusRecordDto);
		try {
			TmallBonusRecord tmallBonusRecord = new TmallBonusRecord();
			//add数据录入
			tmallBonusRecord.setCode(GUID.generateByUUID());
			tmallBonusRecord.setMerchantNo(tmallBonusRecordDto.getMerchantNo());
			tmallBonusRecord.setMemberNo(tmallBonusRecordDto.getMemberNo());
			tmallBonusRecord.setMemberName(tmallBonusRecordDto.getMemberName());
			tmallBonusRecord.setNoWx(tmallBonusRecordDto.getNoWx());
			tmallBonusRecord.setOrderNo(tmallBonusRecordDto.getOrderNo());
			tmallBonusRecord.setOrderAmt(tmallBonusRecordDto.getOrderAmt());
			tmallBonusRecord.setBonuxAmt(tmallBonusRecordDto.getBonuxAmt());
			tmallBonusRecord.setPushTime(new Date());
			tmallBonusRecord.setStatus(tmallBonusRecordDto.getStatus());
			tmallBonusRecord.setRemark(tmallBonusRecordDto.getRemark());
			tmallBonusRecord.setRemark2(tmallBonusRecordDto.getRemark2());
			tmallBonusRecord.setRemark3(tmallBonusRecordDto.getRemark3());
			tmallBonusRecord.setRemark4(tmallBonusRecordDto.getRemark4());
			tmallBonusRecord.setOrderDate(new Date());
			tmallBonusRecord.setMchBillno(tmallBonusRecordDto.getMchBillno());
			tmallBonusRecord.setNoWw(tmallBonusRecordDto.getNoWw());
			tmallBonusRecord.setSendListid(tmallBonusRecordDto.getSendListid());
			tmallBonusRecordDao.insert(tmallBonusRecord);
			logger.debug("addTmallBonusRecord(TmallBonusRecordDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增天猫订单红包记录信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_RECORD_ADD_ERROR,"新增天猫订单红包记录信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询天猫订单红包记录信息
	 *
	 * @param findTmallBonusRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<TmallBonusRecordDto>  findTmallBonusRecords(FindTmallBonusRecordPage findTmallBonusRecordPage)throws TsfaServiceException{
		AssertUtils.notNull(findTmallBonusRecordPage);
		List<TmallBonusRecordDto> returnList=null;
		try {
			returnList = tmallBonusRecordDao.findTmallBonusRecords(findTmallBonusRecordPage);
		} catch (Exception e) {
			logger.error("查找天猫订单红包记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_RECORD_NOT_EXIST_ERROR,"天猫订单红包记录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateTmallBonusRecord(
			TmallBonusRecordDto tmallBonusRecordDto)
			throws TsfaServiceException {
		logger.debug("updateTmallBonusRecord(TmallBonusRecordDto tmallBonusRecordDto={}) - start", tmallBonusRecordDto); //$NON-NLS-1$

		AssertUtils.notNull(tmallBonusRecordDto);
		AssertUtils.notNullAndEmpty(tmallBonusRecordDto.getCode(),"Code不能为空");
		try {
			TmallBonusRecord tmallBonusRecord = new TmallBonusRecord();
			//update数据录入
			tmallBonusRecord.setCode(tmallBonusRecordDto.getCode());
			tmallBonusRecord.setMerchantNo(tmallBonusRecordDto.getMerchantNo());
			tmallBonusRecord.setMemberNo(tmallBonusRecordDto.getMemberNo());
			tmallBonusRecord.setMemberName(tmallBonusRecordDto.getMemberName());
			tmallBonusRecord.setNoWx(tmallBonusRecordDto.getNoWx());
			tmallBonusRecord.setOrderNo(tmallBonusRecordDto.getOrderNo());
			tmallBonusRecord.setOrderAmt(tmallBonusRecordDto.getOrderAmt());
			tmallBonusRecord.setBonuxAmt(tmallBonusRecordDto.getBonuxAmt());
			tmallBonusRecord.setPushTime(tmallBonusRecordDto.getPushTime());
			tmallBonusRecord.setStatus(tmallBonusRecordDto.getStatus());
			tmallBonusRecord.setRemark(tmallBonusRecordDto.getRemark());
			tmallBonusRecord.setRemark2(tmallBonusRecordDto.getRemark2());
			tmallBonusRecord.setRemark3(tmallBonusRecordDto.getRemark3());
			tmallBonusRecord.setRemark4(tmallBonusRecordDto.getRemark4());
			tmallBonusRecord.setMchBillno(tmallBonusRecordDto.getMchBillno());
			tmallBonusRecord.setNoWw(tmallBonusRecordDto.getNoWw());
			tmallBonusRecord.setSendListid(tmallBonusRecordDto.getSendListid());
			AssertUtils.notUpdateMoreThanOne(tmallBonusRecordDao.updateByPrimaryKeySelective(tmallBonusRecord));
			logger.debug("updateTmallBonusRecord(TmallBonusRecordDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("天猫订单红包记录信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_RECORD_UPDATE_ERROR,"天猫订单红包记录信息更新信息错误！",e);
		}
	}

	

	@Override
	public TmallBonusRecordDto findTmallBonusRecord(
			TmallBonusRecordDto tmallBonusRecordDto) throws TsfaServiceException {
		logger.debug("findTmallBonusRecord(FindTmallBonusRecord findTmallBonusRecord={}) - start", tmallBonusRecordDto); //$NON-NLS-1$

		AssertUtils.notNull(tmallBonusRecordDto);
		AssertUtils.notAllNull(tmallBonusRecordDto.getCode(),"Code不能为空");
		try {
			TmallBonusRecord tmallBonusRecord = tmallBonusRecordDao.selectByPrimaryKey(tmallBonusRecordDto.getCode());
			if(tmallBonusRecord == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.TMALL_BONUS_RECORD_NOT_EXIST_ERROR,"天猫订单红包记录信息不存在");
			}
			TmallBonusRecordDto findTmallBonusRecordReturn = new TmallBonusRecordDto();
			//find数据录入
			findTmallBonusRecordReturn.setCode(tmallBonusRecord.getCode());
			findTmallBonusRecordReturn.setMerchantNo(tmallBonusRecord.getMerchantNo());
			findTmallBonusRecordReturn.setMemberNo(tmallBonusRecord.getMemberNo());
			findTmallBonusRecordReturn.setMemberName(tmallBonusRecord.getMemberName());
			findTmallBonusRecordReturn.setNoWx(tmallBonusRecord.getNoWx());
			findTmallBonusRecordReturn.setOrderNo(tmallBonusRecord.getOrderNo());
			findTmallBonusRecordReturn.setOrderAmt(tmallBonusRecord.getOrderAmt());
			findTmallBonusRecordReturn.setBonuxAmt(tmallBonusRecord.getBonuxAmt());
			findTmallBonusRecordReturn.setPushTime(tmallBonusRecord.getPushTime());
			findTmallBonusRecordReturn.setStatus(tmallBonusRecord.getStatus());
			findTmallBonusRecordReturn.setRemark(tmallBonusRecord.getRemark());
			findTmallBonusRecordReturn.setRemark2(tmallBonusRecord.getRemark2());
			findTmallBonusRecordReturn.setRemark3(tmallBonusRecord.getRemark3());
			findTmallBonusRecordReturn.setRemark4(tmallBonusRecord.getRemark4());
			findTmallBonusRecordReturn.setOrderDate(tmallBonusRecord.getOrderDate());
			findTmallBonusRecordReturn.setMchBillno(tmallBonusRecord.getMchBillno());
			findTmallBonusRecordReturn.setNoWw(tmallBonusRecord.getNoWw());
			findTmallBonusRecordReturn.setSendListid(tmallBonusRecord.getSendListid());
			logger.debug("findTmallBonusRecord(TmallBonusRecordDto) - end - return value={}", findTmallBonusRecordReturn); //$NON-NLS-1$
			return findTmallBonusRecordReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找天猫订单红包记录信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_RECORD_FIND_ERROR,"查找天猫订单红包记录信息信息错误！",e);
		}


	}

	
	@Override
	public Page<TmallBonusRecordDto> findTmallBonusRecordPage(
			FindTmallBonusRecordPage findTmallBonusRecordPage)
			throws TsfaServiceException {
		logger.debug("findTmallBonusRecordPage(FindTmallBonusRecordPage findTmallBonusRecordPage={}) - start", findTmallBonusRecordPage); //$NON-NLS-1$

		AssertUtils.notNull(findTmallBonusRecordPage);
		List<TmallBonusRecordDto> returnList=null;
		int count = 0;
		try {
			returnList = tmallBonusRecordDao.findTmallBonusRecordPage(findTmallBonusRecordPage);
			count = tmallBonusRecordDao.findTmallBonusRecordPageCount(findTmallBonusRecordPage);
		}  catch (Exception e) {
			logger.error("天猫订单红包记录信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_RECORD_FIND_PAGE_ERROR,"天猫订单红包记录信息不存在错误.！",e);
		}
		Page<TmallBonusRecordDto> returnPage = new Page<TmallBonusRecordDto>(returnList, count, findTmallBonusRecordPage);

		logger.debug("findTmallBonusRecordPage(FindTmallBonusRecordPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public int findTmallBonusRecordPageCount(FindTmallBonusRecordPage findTmallBonusRecordPage)
			throws TsfaServiceException {
		logger.debug("findTmallBonusRecordPageCount(FindTmallBonusRecordPage findTmallBonusRecordPage={}) - start", findTmallBonusRecordPage); //$NON-NLS-1$

		AssertUtils.notNull(findTmallBonusRecordPage);
		int count = 0;
		try {
			count = tmallBonusRecordDao.findTmallBonusRecordPageCount(findTmallBonusRecordPage);
		}  catch (Exception e) {
			logger.error("天猫订单红包记录信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_RECORD_FIND_PAGE_ERROR,"天猫订单红包记录信息不存在错误.！",e);
		}
		logger.debug("findTmallBonusRecordPageCount(FindTmallBonusRecordPage) - end - return value={}", count); //$NON-NLS-1$
		return count;
	}


	@Override
	public TmallBonusRecordDto findByOrderNo(String orderNo,String merchantNo) throws TsfaServiceException {
		logger.debug("findByOrderNo(String orderNo={},String merchantNo)={}) - start", orderNo,merchantNo); //$NON-NLS-1$

		AssertUtils.notNullAndEmpty(orderNo,"订单号不能为空");
		AssertUtils.notNullAndEmpty(merchantNo,"商户号不能为空");
		TmallBonusRecordDto bonusRecordDto = null;
		try {
			FindTmallBonusRecordPage findTmallBonusRecordPage = new FindTmallBonusRecordPage();
			TmallBonusRecordDto param = new TmallBonusRecordDto();
			param.setMerchantNo(merchantNo);
			findTmallBonusRecordPage.setParam(param);
			findTmallBonusRecordPage.setOrderNoEq(orderNo);
			List<TmallBonusRecordDto> list = findTmallBonusRecords(findTmallBonusRecordPage);
			if(null != list && list.size()>0) {
				return list.get(0);
			}
					
		}  catch (Exception e) {
			logger.error("天猫订单红包记录信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.TMALL_BONUS_RECORD_FIND_PAGE_ERROR,"天猫订单红包记录信息不存在错误.！",e);
		}
		logger.debug("findByOrderNo(tmallBonusRecordDto) - end - return value={}",bonusRecordDto); //$NON-NLS-1$
		return bonusRecordDto;
	} 


}
