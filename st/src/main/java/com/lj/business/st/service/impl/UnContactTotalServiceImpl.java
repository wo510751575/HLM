package com.lj.business.st.service.impl;

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
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IUnContactTotalDao;
import com.lj.business.st.domain.UnContactTotal;
import com.lj.business.st.dto.AddUnContactTotal;
import com.lj.business.st.dto.AddUnContactTotalReturn;
import com.lj.business.st.dto.DelUnContactTotal;
import com.lj.business.st.dto.DelUnContactTotalReturn;
import com.lj.business.st.dto.FindUnContactTotal;
import com.lj.business.st.dto.FindUnContactTotalInfo;
import com.lj.business.st.dto.FindUnContactTotalInfoReturn;
import com.lj.business.st.dto.FindUnContactTotalPage;
import com.lj.business.st.dto.FindUnContactTotalPageReturn;
import com.lj.business.st.dto.FindUnContactTotalReturn;
import com.lj.business.st.dto.UpdateUnContactTotal;
import com.lj.business.st.dto.UpdateUnContactTotalReturn;
import com.lj.business.st.service.IUnContactTotalService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class UnContactTotalServiceImpl implements IUnContactTotalService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(UnContactTotalServiceImpl.class);
	

	/** The un contact total dao. */
	@Resource
	private IUnContactTotalDao unContactTotalDao;
	
	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IUnContactTotalService#addUnContactTotal(com.lj.business.st.dto.AddUnContactTotal)
	 */
	@Override
	public AddUnContactTotalReturn addUnContactTotal(
			AddUnContactTotal addUnContactTotal) throws TsfaServiceException {
		logger.debug("addUnContactTotal(AddUnContactTotal addUnContactTotal={}) - start", addUnContactTotal); 

		AssertUtils.notNull(addUnContactTotal);
		try {
			UnContactTotal unContactTotal = new UnContactTotal();
			//add数据录入
			unContactTotal.setCode(GUID.getPreUUID());
			unContactTotal.setMerchantNo(addUnContactTotal.getMerchantNo());
			unContactTotal.setShopNo(addUnContactTotal.getShopNo());
			unContactTotal.setShopName(addUnContactTotal.getShopName());
			unContactTotal.setMemberNoGm(addUnContactTotal.getMemberNoGm());
			unContactTotal.setMemberNameGm(addUnContactTotal.getMemberNameGm());
			unContactTotal.setYizhouYiyue(addUnContactTotal.getYizhouYiyue());
			unContactTotal.setYiyueSanyue(addUnContactTotal.getYiyueSanyue());
			unContactTotal.setSanyueLiuyue(addUnContactTotal.getSanyueLiuyue());
			unContactTotal.setLiuyueEnd(addUnContactTotal.getLiuyueEnd());
			unContactTotal.setDimensionSt(addUnContactTotal.getDimensionSt());
			unContactTotalDao.insert(unContactTotal);
			AddUnContactTotalReturn addUnContactTotalReturn = new AddUnContactTotalReturn();
			
			logger.debug("addUnContactTotal(AddUnContactTotal) - end - return value={}", addUnContactTotalReturn); 
			return addUnContactTotalReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增未联系客户统计信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.UN_CONTACT_TOTAL_ADD_ERROR,"新增未联系客户统计信息信息错误！",e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IUnContactTotalService#delUnContactTotal(com.lj.business.st.dto.DelUnContactTotal)
	 */
	@Override
	public DelUnContactTotalReturn delUnContactTotal(
			DelUnContactTotal delUnContactTotal) throws TsfaServiceException {
		logger.debug("delUnContactTotal(DelUnContactTotal delUnContactTotal={}) - start", delUnContactTotal); 

		AssertUtils.notNull(delUnContactTotal);
		AssertUtils.notNull(delUnContactTotal.getCode(),"ID不能为空！");
		try {
			unContactTotalDao.deleteByPrimaryKey(delUnContactTotal.getCode());
			DelUnContactTotalReturn delUnContactTotalReturn  = new DelUnContactTotalReturn();

			logger.debug("delUnContactTotal(DelUnContactTotal) - end - return value={}", delUnContactTotalReturn); //$NON-NLS-1$
			return delUnContactTotalReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除未联系客户统计信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.UN_CONTACT_TOTAL_DEL_ERROR,"删除未联系客户统计信息信息错误！",e);

		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IUnContactTotalService#updateUnContactTotal(com.lj.business.st.dto.UpdateUnContactTotal)
	 */
	@Override
	public UpdateUnContactTotalReturn updateUnContactTotal(
			UpdateUnContactTotal updateUnContactTotal)
			throws TsfaServiceException {
		logger.debug("updateUnContactTotal(UpdateUnContactTotal updateUnContactTotal={}) - start", updateUnContactTotal); //$NON-NLS-1$

		AssertUtils.notNull(updateUnContactTotal);
		AssertUtils.notNullAndEmpty(updateUnContactTotal.getCode(),"ID不能为空");
		try {
			UnContactTotal unContactTotal = new UnContactTotal();
			//update数据录入
			unContactTotal.setCode(updateUnContactTotal.getCode());
			unContactTotal.setMerchantNo(updateUnContactTotal.getMerchantNo());
			unContactTotal.setMemberNoGm(updateUnContactTotal.getMemberNoGm());
			unContactTotal.setMemberNameGm(updateUnContactTotal.getMemberNameGm());
			unContactTotal.setYizhouYiyue(updateUnContactTotal.getYizhouYiyue());
			unContactTotal.setYiyueSanyue(updateUnContactTotal.getYiyueSanyue());
			unContactTotal.setSanyueLiuyue(updateUnContactTotal.getSanyueLiuyue());
			unContactTotal.setLiuyueEnd(updateUnContactTotal.getLiuyueEnd());
			AssertUtils.notUpdateMoreThanOne(unContactTotalDao.updateByPrimaryKeySelective(unContactTotal));
			UpdateUnContactTotalReturn updateUnContactTotalReturn = new UpdateUnContactTotalReturn();

			logger.debug("updateUnContactTotal(UpdateUnContactTotal) - end - return value={}", updateUnContactTotalReturn); //$NON-NLS-1$
			return updateUnContactTotalReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("未联系客户统计信息信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.UN_CONTACT_TOTAL_UPDATE_ERROR,"未联系客户统计信息信息更新信息错误！",e);
		}
	}

	

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IUnContactTotalService#findUnContactTotal(com.lj.business.st.dto.FindUnContactTotal)
	 */
	@Override
	public FindUnContactTotalReturn findUnContactTotal(
			FindUnContactTotal findUnContactTotal) throws TsfaServiceException {
		logger.debug("findUnContactTotal(FindUnContactTotal findUnContactTotal={}) - start", findUnContactTotal); //$NON-NLS-1$

		AssertUtils.notNull(findUnContactTotal);
		AssertUtils.notAllNull(findUnContactTotal.getCode(),"ID不能为空");
		try {
			UnContactTotal unContactTotal = unContactTotalDao.selectByPrimaryKey(findUnContactTotal.getCode());
			if(unContactTotal == null){
				throw new TsfaServiceException(ErrorCode.UN_CONTACT_TOTAL_NOT_EXIST_ERROR,"未联系客户统计信息信息不存在");
			}
			FindUnContactTotalReturn findUnContactTotalReturn = new FindUnContactTotalReturn();
			//find数据录入
			findUnContactTotalReturn.setCode(unContactTotal.getCode());
			findUnContactTotalReturn.setMerchantNo(unContactTotal.getMerchantNo());
			findUnContactTotalReturn.setShopNo(unContactTotal.getShopNo());
			findUnContactTotalReturn.setShopName(unContactTotal.getShopName());
			findUnContactTotalReturn.setMemberNoGm(unContactTotal.getMemberNoGm());
			findUnContactTotalReturn.setMemberNameGm(unContactTotal.getMemberNameGm());
			findUnContactTotalReturn.setYizhouYiyue(unContactTotal.getYizhouYiyue());
			findUnContactTotalReturn.setYiyueSanyue(unContactTotal.getYiyueSanyue());
			findUnContactTotalReturn.setSanyueLiuyue(unContactTotal.getSanyueLiuyue());
			findUnContactTotalReturn.setLiuyueEnd(unContactTotal.getLiuyueEnd());
			findUnContactTotalReturn.setCreateDate(unContactTotal.getCreateDate());
			
			logger.debug("findUnContactTotal(FindUnContactTotal) - end - return value={}", findUnContactTotalReturn); //$NON-NLS-1$
			return findUnContactTotalReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找未联系客户统计信息信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.UN_CONTACT_TOTAL_FIND_ERROR,"查找未联系客户统计信息信息信息错误！",e);
		}


	}

	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IUnContactTotalService#findUnContactTotalPage(com.lj.business.st.dto.FindUnContactTotalPage)
	 */
	@Override
	public Page<FindUnContactTotalPageReturn> findUnContactTotalPage(
			FindUnContactTotalPage findUnContactTotalPage)
			throws TsfaServiceException {
		logger.debug("findUnContactTotalPage(FindUnContactTotalPage findUnContactTotalPage={}) - start", findUnContactTotalPage); //$NON-NLS-1$

		AssertUtils.notNull(findUnContactTotalPage);
		List<FindUnContactTotalPageReturn> returnList;
		int count = 0;
		try {
			returnList = unContactTotalDao.findUnContactTotalPage(findUnContactTotalPage);
			count = unContactTotalDao.findUnContactTotalPageCount(findUnContactTotalPage);
		}  catch (Exception e) {
			logger.error("未联系客户统计信息信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.UN_CONTACT_TOTAL_FIND_PAGE_ERROR,"未联系客户统计信息信息不存在错误.！",e);
		}
		Page<FindUnContactTotalPageReturn> returnPage = new Page<FindUnContactTotalPageReturn>(returnList, count, findUnContactTotalPage);

		logger.debug("findUnContactTotalPage(FindUnContactTotalPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IUnContactTotalService#findUnContactTotalInfo(com.lj.business.st.dto.FindUnContactTotalInfo)
	 */
	@Override
	public FindUnContactTotalInfoReturn findUnContactTotalInfo(
			FindUnContactTotalInfo findUnContactTotalInfo)
			throws TsfaServiceException {
		logger.debug("findUnContactTotalInfo(FindUnContactTotalInfo findUnContactTotalInfo={}) - start", findUnContactTotalInfo); //$NON-NLS-1$

		AssertUtils.notNull(findUnContactTotalInfo);
		try {
			FindUnContactTotalInfoReturn returnList = unContactTotalDao.findUnContactTotalInfo(findUnContactTotalInfo);

			logger.debug("findUnContactTotalInfo(FindUnContactTotalInfo) - end - return value={}", returnList); //$NON-NLS-1$
			return  returnList;
		}  catch (Exception e) {
			logger.error("未联系客户统计信息信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.UN_CONTACT_TOTAL_FIND_PAGE_ERROR,"未联系客户统计信息信息不存在错误.！",e);
		}
		
	}


	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IUnContactTotalService#findList()
	 */
	@Override
	public List<FindUnContactTotalReturn> findList()throws TsfaServiceException {
		logger.debug("findList() - start"); //$NON-NLS-1$
		try {
			List<FindUnContactTotalReturn> returnList = unContactTotalDao.findList();
			logger.debug("findList() - end - return value={}", returnList); //$NON-NLS-1$
			return  returnList;
		}  catch (Exception e) {
			logger.error("未联系客户统计信息信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.UN_CONTACT_TOTAL_FIND_PAGE_ERROR,"未联系客户统计信息信息不存在错误.！",e);
		}
	} 
	
}
