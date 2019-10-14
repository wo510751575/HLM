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
import com.ye.business.hx.dao.IBillSnapshotDao;
import com.ye.business.hx.domain.BillSnapshot;
import com.ye.business.hx.dto.BillSnapshotDto;
import com.ye.business.hx.dto.FindBillSnapshotPage;
import com.ye.business.hx.service.IBillSnapshotService;
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
public class BillSnapshotServiceImpl implements IBillSnapshotService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BillSnapshotServiceImpl.class);
	

	@Resource
	private IBillSnapshotDao billSnapshotDao;
	
	
	@Override
	public void addBillSnapshot(
			BillSnapshotDto billSnapshotDto) throws TsfaServiceException {
		logger.debug("addBillSnapshot(AddBillSnapshot addBillSnapshot={}) - start", billSnapshotDto); 

		AssertUtils.notNull(billSnapshotDto);
		try {
			BillSnapshot billSnapshot = new BillSnapshot();
			//add数据录入
			billSnapshot.setCode(GUID.getPreUUID());
			billSnapshot.setBillCode(billSnapshotDto.getBillCode());
			billSnapshot.setOperateCode(billSnapshotDto.getOperateCode());
			billSnapshot.setPatientNo(billSnapshotDto.getPatientNo());
			billSnapshot.setPatientName(billSnapshotDto.getPatientName());
			billSnapshot.setMedicalNo(billSnapshotDto.getMedicalNo());
			billSnapshot.setShopNo(billSnapshotDto.getShopNo());
			billSnapshot.setShopName(billSnapshotDto.getShopName());
			billSnapshot.setMerchantNo(billSnapshotDto.getMerchantNo());
			billSnapshot.setMerchantName(billSnapshotDto.getMerchantName());
			billSnapshot.setOriginalAmount(billSnapshotDto.getOriginalAmount());
			billSnapshot.setReallyAmount(billSnapshotDto.getReallyAmount());
			billSnapshot.setDiscountNum(billSnapshotDto.getDiscountNum());
			billSnapshot.setPayAmount(billSnapshotDto.getPayAmount());
			billSnapshot.setDebtAmount(billSnapshotDto.getDebtAmount());
			billSnapshot.setRtAmount(billSnapshotDto.getRtAmount());
			billSnapshot.setPayStatus(billSnapshotDto.getPayStatus());
			billSnapshot.setRtStatus(billSnapshotDto.getRtStatus());
			billSnapshot.setStatus(billSnapshotDto.getStatus());
			billSnapshot.setClinicTime(billSnapshotDto.getClinicTime());
			billSnapshot.setUpdateId(billSnapshotDto.getUpdateId());
			billSnapshot.setUpdateDate(new Date());
			billSnapshot.setCreateId(billSnapshotDto.getCreateId());
			billSnapshot.setCreateName(billSnapshotDto.getCreateName());
			billSnapshot.setCreateDate(new Date());
			billSnapshot.setRemark(billSnapshotDto.getRemark());
			billSnapshot.setRemark1(billSnapshotDto.getRemark1());
			billSnapshot.setRemark2(billSnapshotDto.getRemark2());
			billSnapshot.setRemark3(billSnapshotDto.getRemark3());
			billSnapshot.setRemark4(billSnapshotDto.getRemark4());
			billSnapshot.setSnapshotTime(billSnapshotDto.getSnapshotTime());
			billSnapshotDao.insertSelective(billSnapshot);
			logger.debug("addBillSnapshot(BillSnapshotDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增账单快照信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_SNAPSHOT_ADD_ERROR,"新增账单快照信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询账单快照信息
	 *
	 * @param findBillSnapshotPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<BillSnapshotDto>  findBillSnapshots(FindBillSnapshotPage findBillSnapshotPage)throws TsfaServiceException{
		AssertUtils.notNull(findBillSnapshotPage);
		List<BillSnapshotDto> returnList=null;
		try {
			returnList = billSnapshotDao.findBillSnapshots(findBillSnapshotPage);
		} catch (Exception e) {
			logger.error("查找账单快照信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.BILL_SNAPSHOT_NOT_EXIST_ERROR,"账单快照信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateBillSnapshot(
			BillSnapshotDto billSnapshotDto)
			throws TsfaServiceException {
		logger.debug("updateBillSnapshot(BillSnapshotDto billSnapshotDto={}) - start", billSnapshotDto); //$NON-NLS-1$

		AssertUtils.notNull(billSnapshotDto);
		AssertUtils.notNullAndEmpty(billSnapshotDto.getCode(),"Code不能为空");
		try {
			BillSnapshot billSnapshot = new BillSnapshot();
			//update数据录入
			billSnapshot.setCode(billSnapshotDto.getCode());
			billSnapshot.setBillCode(billSnapshotDto.getBillCode());
			billSnapshot.setOperateCode(billSnapshotDto.getOperateCode());
			billSnapshot.setPatientNo(billSnapshotDto.getPatientNo());
			billSnapshot.setPatientName(billSnapshotDto.getPatientName());
			billSnapshot.setMedicalNo(billSnapshotDto.getMedicalNo());
			billSnapshot.setShopNo(billSnapshotDto.getShopNo());
			billSnapshot.setShopName(billSnapshotDto.getShopName());
			billSnapshot.setMerchantNo(billSnapshotDto.getMerchantNo());
			billSnapshot.setMerchantName(billSnapshotDto.getMerchantName());
			billSnapshot.setOriginalAmount(billSnapshotDto.getOriginalAmount());
			billSnapshot.setReallyAmount(billSnapshotDto.getReallyAmount());
			billSnapshot.setDiscountNum(billSnapshotDto.getDiscountNum());
			billSnapshot.setPayAmount(billSnapshotDto.getPayAmount());
			billSnapshot.setDebtAmount(billSnapshotDto.getDebtAmount());
			billSnapshot.setRtAmount(billSnapshotDto.getRtAmount());
			billSnapshot.setPayStatus(billSnapshotDto.getPayStatus());
			billSnapshot.setRtStatus(billSnapshotDto.getRtStatus());
			billSnapshot.setStatus(billSnapshotDto.getStatus());
			billSnapshot.setClinicTime(billSnapshotDto.getClinicTime());
			billSnapshot.setUpdateId(billSnapshotDto.getUpdateId());
			billSnapshot.setUpdateDate(new Date());
//			billSnapshot.setCreateId(billSnapshotDto.getCreateId());
//			billSnapshot.setCreateName(billSnapshotDto.getCreateName());
//			billSnapshot.setCreateDate(billSnapshotDto.getCreateDate());
			billSnapshot.setRemark(billSnapshotDto.getRemark());
			billSnapshot.setRemark1(billSnapshotDto.getRemark1());
			billSnapshot.setRemark2(billSnapshotDto.getRemark2());
			billSnapshot.setRemark3(billSnapshotDto.getRemark3());
			billSnapshot.setRemark4(billSnapshotDto.getRemark4());
//			billSnapshot.setSnapshotTime(billSnapshotDto.getSnapshotTime());
			AssertUtils.notUpdateMoreThanOne(billSnapshotDao.updateByPrimaryKeySelective(billSnapshot));
			logger.debug("updateBillSnapshot(BillSnapshotDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("账单快照信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_SNAPSHOT_UPDATE_ERROR,"账单快照信息更新信息错误！",e);
		}
	}

	

	@Override
	public BillSnapshotDto findBillSnapshot(
			BillSnapshotDto billSnapshotDto) throws TsfaServiceException {
		logger.debug("findBillSnapshot(FindBillSnapshot findBillSnapshot={}) - start", billSnapshotDto); //$NON-NLS-1$

		AssertUtils.notNull(billSnapshotDto);
		AssertUtils.notAllNull(billSnapshotDto.getCode(),"Code不能为空");
		try {
			BillSnapshot billSnapshot = billSnapshotDao.selectByPrimaryKey(billSnapshotDto.getCode());
			if(billSnapshot == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.BILL_SNAPSHOT_NOT_EXIST_ERROR,"账单快照信息不存在");
			}
			BillSnapshotDto findBillSnapshotReturn = new BillSnapshotDto();
			//find数据录入
			findBillSnapshotReturn.setCode(billSnapshot.getCode());
			findBillSnapshotReturn.setBillCode(billSnapshot.getBillCode());
			findBillSnapshotReturn.setOperateCode(billSnapshot.getOperateCode());
			findBillSnapshotReturn.setPatientNo(billSnapshot.getPatientNo());
			findBillSnapshotReturn.setPatientName(billSnapshot.getPatientName());
			findBillSnapshotReturn.setMedicalNo(billSnapshot.getMedicalNo());
			findBillSnapshotReturn.setShopNo(billSnapshot.getShopNo());
			findBillSnapshotReturn.setShopName(billSnapshot.getShopName());
			findBillSnapshotReturn.setMerchantNo(billSnapshot.getMerchantNo());
			findBillSnapshotReturn.setMerchantName(billSnapshot.getMerchantName());
			findBillSnapshotReturn.setOriginalAmount(billSnapshot.getOriginalAmount());
			findBillSnapshotReturn.setReallyAmount(billSnapshot.getReallyAmount());
			findBillSnapshotReturn.setDiscountNum(billSnapshot.getDiscountNum());
			findBillSnapshotReturn.setPayAmount(billSnapshot.getPayAmount());
			findBillSnapshotReturn.setDebtAmount(billSnapshot.getDebtAmount());
			findBillSnapshotReturn.setRtAmount(billSnapshot.getRtAmount());
			findBillSnapshotReturn.setPayStatus(billSnapshot.getPayStatus());
			findBillSnapshotReturn.setRtStatus(billSnapshot.getRtStatus());
			findBillSnapshotReturn.setStatus(billSnapshot.getStatus());
			findBillSnapshotReturn.setClinicTime(billSnapshot.getClinicTime());
			findBillSnapshotReturn.setUpdateId(billSnapshot.getUpdateId());
			findBillSnapshotReturn.setUpdateDate(billSnapshot.getUpdateDate());
			findBillSnapshotReturn.setCreateId(billSnapshot.getCreateId());
			findBillSnapshotReturn.setCreateName(billSnapshot.getCreateName());
			findBillSnapshotReturn.setCreateDate(billSnapshot.getCreateDate());
			findBillSnapshotReturn.setRemark(billSnapshot.getRemark());
			findBillSnapshotReturn.setRemark1(billSnapshot.getRemark1());
			findBillSnapshotReturn.setRemark2(billSnapshot.getRemark2());
			findBillSnapshotReturn.setRemark3(billSnapshot.getRemark3());
			findBillSnapshotReturn.setRemark4(billSnapshot.getRemark4());
			findBillSnapshotReturn.setSnapshotTime(billSnapshot.getSnapshotTime());
			
			logger.debug("findBillSnapshot(BillSnapshotDto) - end - return value={}", findBillSnapshotReturn); //$NON-NLS-1$
			return findBillSnapshotReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找账单快照信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BILL_SNAPSHOT_FIND_ERROR,"查找账单快照信息信息错误！",e);
		}


	}

	
	@Override
	public Page<BillSnapshotDto> findBillSnapshotPage(
			FindBillSnapshotPage findBillSnapshotPage)
			throws TsfaServiceException {
		logger.debug("findBillSnapshotPage(FindBillSnapshotPage findBillSnapshotPage={}) - start", findBillSnapshotPage); //$NON-NLS-1$

		AssertUtils.notNull(findBillSnapshotPage);
		List<BillSnapshotDto> returnList=null;
		int count = 0;
		try {
			returnList = billSnapshotDao.findBillSnapshotPage(findBillSnapshotPage);
			count = billSnapshotDao.findBillSnapshotPageCount(findBillSnapshotPage);
		}  catch (Exception e) {
			logger.error("账单快照信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.BILL_SNAPSHOT_FIND_PAGE_ERROR,"账单快照信息不存在错误.！",e);
		}
		Page<BillSnapshotDto> returnPage = new Page<BillSnapshotDto>(returnList, count, findBillSnapshotPage);

		logger.debug("findBillSnapshotPage(FindBillSnapshotPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 


}
