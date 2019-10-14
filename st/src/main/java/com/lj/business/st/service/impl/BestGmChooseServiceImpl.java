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

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IBestGmChooseDao;
import com.lj.business.st.domain.BestGmChoose;
import com.lj.business.st.dto.FindBgcIndex;
import com.lj.business.st.dto.FindBgcIndexReturn;
import com.lj.business.st.dto.bestGmChoose.AddBestGmChoose;
import com.lj.business.st.dto.bestGmChoose.FindBestGmChoose;
import com.lj.business.st.dto.bestGmChoose.FindBestGmChooseReturn;
import com.lj.business.st.service.IBestGmChooseService;

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
public class BestGmChooseServiceImpl implements IBestGmChooseService { 

	

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BestGmChooseServiceImpl.class);
	

	/** The best gm choose dao. */
	@Resource
	private IBestGmChooseDao bestGmChooseDao;
	
	
	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IBestGmChooseService#addBestGmChoose(com.lj.business.st.dto.bestGmChoose.AddBestGmChoose)
	 */
	@Override
	public void addBestGmChoose(
			AddBestGmChoose addBestGmChoose) throws TsfaServiceException {
		logger.debug("addBestGmChoose(AddBestGmChoose addBestGmChoose={}) - start", addBestGmChoose); 

		AssertUtils.notNull(addBestGmChoose);
		try {
			BestGmChoose bestGmChoose = new BestGmChoose();
			//add数据录入
			bestGmChoose.setCode(GUID.generateCode());
			bestGmChoose.setMerchantNo(addBestGmChoose.getMerchantNo());
			bestGmChoose.setShopNo(addBestGmChoose.getShopNo());
			bestGmChoose.setShopName(addBestGmChoose.getShopName());
			bestGmChoose.setMemberNoGm(addBestGmChoose.getMemberNoGm());
			bestGmChoose.setMemberNameGm(addBestGmChoose.getMemberNameGm());
			bestGmChoose.setCodeList(addBestGmChoose.getCodeList());
			bestGmChoose.setNameList(addBestGmChoose.getNameList());
			bestGmChoose.setTypeList(addBestGmChoose.getTypeList());
			bestGmChoose.setSeq(addBestGmChoose.getSeq());
			bestGmChoose.setImgAddr(addBestGmChoose.getImgAddr());
			bestGmChooseDao.insert(bestGmChoose);
			logger.debug("addBestGmChoose(AddBestGmChoose) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增优秀导购选择表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BEST_GM_CHOOSE_ADD_ERROR,"新增优秀导购选择表信息错误！",e);
		}
	}
	

	

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IBestGmChooseService#findBestGmChoose(com.lj.business.st.dto.bestGmChoose.FindBestGmChoose)
	 */
	@Override
	public FindBestGmChooseReturn findBestGmChoose(
			FindBestGmChoose findBestGmChoose) throws TsfaServiceException {
		logger.debug("findBestGmChoose(FindBestGmChoose findBestGmChoose={}) - start", findBestGmChoose); //$NON-NLS-1$

		AssertUtils.notNull(findBestGmChoose);
		AssertUtils.notAllNull(findBestGmChoose.getCode(),"Code不能为空");
		try {
			BestGmChoose bestGmChoose = bestGmChooseDao.selectByPrimaryKey(findBestGmChoose.getCode());
			if(bestGmChoose == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.BEST_GM_CHOOSE_NOT_EXIST_ERROR,"优秀导购选择表信息不存在");
			}
			FindBestGmChooseReturn findBestGmChooseReturn = new FindBestGmChooseReturn();
			//find数据录入
			findBestGmChooseReturn.setCode(bestGmChoose.getCode());
			findBestGmChooseReturn.setMerchantNo(bestGmChoose.getMerchantNo());
			findBestGmChooseReturn.setShopNo(bestGmChoose.getShopNo());
			findBestGmChooseReturn.setShopName(bestGmChoose.getShopName());
			findBestGmChooseReturn.setMemberNoGm(bestGmChoose.getMemberNoGm());
			findBestGmChooseReturn.setMemberNameGm(bestGmChoose.getMemberNameGm());
			findBestGmChooseReturn.setCodeList(bestGmChoose.getCodeList());
			findBestGmChooseReturn.setNameList(bestGmChoose.getNameList());
			findBestGmChooseReturn.setTypeList(bestGmChoose.getTypeList());
			findBestGmChooseReturn.setSeq(bestGmChoose.getSeq());
			findBestGmChooseReturn.setImgAddr(bestGmChoose.getImgAddr());
			findBestGmChooseReturn.setCreateDate(bestGmChoose.getCreateDate());
			
			logger.debug("findBestGmChoose(FindBestGmChoose) - end - return value={}", findBestGmChooseReturn); //$NON-NLS-1$
			return findBestGmChooseReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找优秀导购选择表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.BEST_GM_CHOOSE_FIND_ERROR,"查找优秀导购选择表信息信息错误！",e);
		}


	}




	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IBestGmChooseService#findBgcIndex(com.lj.business.st.dto.FindBgcIndex)
	 */
	@Override
	public List<FindBgcIndexReturn> findBgcIndex(FindBgcIndex findBgcIndex)
			throws TsfaServiceException {
		logger.debug("findBgcIndex(FindBgcIndex findBgcIndex={}) - start", findBgcIndex); //$NON-NLS-1$

		AssertUtils.notNull(findBgcIndex);
		AssertUtils.notNull(findBgcIndex.getMerchantNo(),"商户编号不能为空！");
		try {
			
			List<FindBgcIndexReturn> returnList = bestGmChooseDao.findBgcIndex(findBgcIndex);
			logger.debug("findBgcIndex(FindBgcIndex) - end - return value={}", returnList); //$NON-NLS-1$
			return returnList;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查找导购工作统计表信息错误",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_FIND_ERROR,"查找导购工作统计表信息错误",e);
		}
	}




	@Override
	 public int deleteByPrimaryKey(String str) {
		logger.debug("findBgcIndex(FindBgcIndex findBgcIndex={}) - start", str); //$NON-NLS-1$
		AssertUtils.notNull(str);
        int num=0;
       try {
			num=bestGmChooseDao.deleteByPrimaryKey(str);
		} catch (Exception e) {
			logger.error("删除导购工作统计表信息错误",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_DEL_ERROR,"删除导购工作统计表信息错误",e);
		}
		return num;
	}



}
