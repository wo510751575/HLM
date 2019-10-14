package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.base.core.util.ArithUtil;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IPmTalkTotalDao;
import com.lj.business.st.domain.PmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.AddPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalAllReturnList;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalReturn;
import com.lj.business.st.service.IPmTalkTotalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class PmTalkTotalServiceImpl implements IPmTalkTotalService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PmTalkTotalServiceImpl.class);
	

	@Resource
	private IPmTalkTotalDao pmTalkTotalDao;
	
	
	@Override
	public void addPmTalkTotal(
			AddPmTalkTotal addPmTalkTotal) throws TsfaServiceException {
		logger.debug("addPmTalkTotal(AddPmTalkTotal addPmTalkTotal={}) - start", addPmTalkTotal); 

		AssertUtils.notNull(addPmTalkTotal);
		try {
			PmTalkTotal pmTalkTotal = new PmTalkTotal();
			//add数据录入
			pmTalkTotal.setCode(GUID.getPreUUID());
			pmTalkTotal.setMerchantNo(addPmTalkTotal.getMerchantNo());
			pmTalkTotal.setAreaCode(addPmTalkTotal.getAreaCode());
			pmTalkTotal.setAreaName(addPmTalkTotal.getAreaName());
			pmTalkTotal.setShopNo(addPmTalkTotal.getShopNo());
			pmTalkTotal.setShopName(addPmTalkTotal.getShopName());
			pmTalkTotal.setMemberNoGm(addPmTalkTotal.getMemberNoGm());
			pmTalkTotal.setMemberNameGm(addPmTalkTotal.getMemberNameGm());
			pmTalkTotal.setStartDate(addPmTalkTotal.getStartDate());
			pmTalkTotal.setEndDate(addPmTalkTotal.getEndDate());
			pmTalkTotal.setNumTalk(addPmTalkTotal.getNumTalk());
			pmTalkTotal.setDaySt(addPmTalkTotal.getDaySt());
			pmTalkTotal.setDimensionSt(addPmTalkTotal.getDimensionSt());
			pmTalkTotal.setCreateDate(addPmTalkTotal.getCreateDate());
			pmTalkTotalDao.insert(pmTalkTotal);
			logger.debug("addPmTalkTotal(AddPmTalkTotal) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增客户咨询统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TALK_TOTAL_ADD_ERROR,"新增客户咨询统计表信息错误！",e);
		}
	}

	@Override
	public List<FindPmTalkTotalReturn> findPmTalkTotalList(FindPmTalkTotal findPmTalkTotal) {
		AssertUtils.notNull(findPmTalkTotal);
		//AssertUtils.notNull(findPmTalkTotal.getBeginDate(), "开始日期不能为空");
		//AssertUtils.notNull(findPmTalkTotal.getEndDate(), "结束日期不能为空");

		List<FindPmTalkTotalReturn> result = new ArrayList<>();
		try {
			List<PmTalkTotal> pmTalkTotalList = pmTalkTotalDao.findPmTalkTotalList(findPmTalkTotal);
			if (!CollectionUtils.isEmpty(pmTalkTotalList)) {
				// 计算总数
				Integer totalNum = 0;
				for (PmTalkTotal each : pmTalkTotalList) {
					totalNum += each.getNumTalk();
				}

				for (PmTalkTotal each : pmTalkTotalList) {
					FindPmTalkTotalReturn item = new FindPmTalkTotalReturn();
					item.setEndDate(DateUtils.formatDate(each.getEndDate(), "HH:mm"));
					item.setNumTalk(each.getNumTalk());
					item.setStartDate(DateUtils.formatDate(each.getStartDate(), "HH:mm"));
					item.setRatio(mulHundredToString(totalNum, each.getNumTalk()));
					result.add(item);
				}

				// 按照数量排序
				Collections.sort(result, new Comparator<FindPmTalkTotalReturn>() {
					@Override
					public int compare(FindPmTalkTotalReturn o1, FindPmTalkTotalReturn o2) {
						int result = o1.getStartDate().compareTo(o2.getStartDate());
						if (result > 0) {
							return 1;
						}
						else if (result < 0) {
							return -1;
						}
						return 0;
					}
				});
			}
			logger.debug("findPmTalkTotalList(findPmTalkTotalList) - end - return");
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询客户咨询统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TALK_TOTAL_FIND_ERROR,"查询客户咨询统计表信息错误！",e);
		}
		return result;
	}
	
	@Override
	public List<FindPmTalkTotalReturn> findPmTalkTotalListApp(FindPmTalkTotal findPmTalkTotal) {
		AssertUtils.notNull(findPmTalkTotal);
		
		List<FindPmTalkTotalReturn> result = new ArrayList<>();
		try {
			List<PmTalkTotal> pmTalkTotalList = pmTalkTotalDao.findPmTalkTotalListApp(findPmTalkTotal);
			if (!CollectionUtils.isEmpty(pmTalkTotalList)) {
				// 计算总数
				Integer totalNum = 0;
				for (PmTalkTotal each : pmTalkTotalList) {
					totalNum += each.getNumTalk();
				}

				for (PmTalkTotal each : pmTalkTotalList) {
					FindPmTalkTotalReturn item = new FindPmTalkTotalReturn();
					item.setEndDate(DateUtils.formatDate(each.getEndDate(), "HH:mm"));
					item.setNumTalk(each.getNumTalk());
					item.setStartDate(DateUtils.formatDate(each.getStartDate(), "HH:mm"));
					item.setRatio(toString(totalNum, each.getNumTalk()));
					result.add(item);
				}
			}
			logger.debug("findPmTalkTotalList(findPmTalkTotalList) - end - return");
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询客户咨询统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TALK_TOTAL_FIND_ERROR,"查询客户咨询统计表信息错误！",e);
		}
		return result;
	}
	
	@Override
	public List<FindPmTalkTotalReturn> findPmTalkTotalOrgListApp(FindPmTalkTotal findPmTalkTotal) {
		AssertUtils.notNull(findPmTalkTotal);
		
		List<FindPmTalkTotalReturn> result = new ArrayList<>();
		try {
			List<PmTalkTotal> pmTalkTotalList = pmTalkTotalDao.findPmTalkTotalOrgListApp(findPmTalkTotal);
			if (!CollectionUtils.isEmpty(pmTalkTotalList)) {
				// 计算总数
				Integer totalNum = 0;
				for (PmTalkTotal each : pmTalkTotalList) {
					totalNum += each.getNumTalk();
				}

				for (PmTalkTotal each : pmTalkTotalList) {
					FindPmTalkTotalReturn item = new FindPmTalkTotalReturn();
					item.setEndDate(DateUtils.formatDate(each.getEndDate(), "HH:mm"));
					item.setNumTalk(each.getNumTalk());
					item.setStartDate(DateUtils.formatDate(each.getStartDate(), "HH:mm"));
					item.setRatio(toString(totalNum, each.getNumTalk()));
					result.add(item);
				}
			}
			logger.debug("findPmTalkTotalList(findPmTalkTotalList) - end - return");
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询客户咨询统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TALK_TOTAL_FIND_ERROR,"查询客户咨询统计表信息错误！",e);
		}
		return result;
	}

	private String toString(Integer totalNum, Integer num) {
		Double div = 0.0;
		if(totalNum > 0){
			div = ArithUtil.div(num, totalNum);
		}
		BigDecimal bd = new BigDecimal(div);
		bd = bd.setScale(4, RoundingMode.HALF_UP);
		return bd.toString();
	}

	private String mulHundredToString(Integer totalNum, Integer num) {
		Double div = 0.0;
		if(totalNum > 0){
			div = ArithUtil.div(num, totalNum);
		}
		BigDecimal bd = new BigDecimal(div * 100);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.toString();
	}

	@Override
	public  FindPmTalkTotalReturn findPmTalkTotalMax(FindPmTalkTotal findPmTalkTotal) {
		AssertUtils.notNull(findPmTalkTotal);
		AssertUtils.notNullAndEmpty(findPmTalkTotal.getMerchantNo(),"商户号不能为空");
		AssertUtils.notNullAndEmpty(findPmTalkTotal.getMemberNoGm(),"会员编号不能为空");
		 try {
			 FindPmTalkTotalReturn findPmTalkTotalReturn = pmTalkTotalDao.findPmTalkTotalMax(findPmTalkTotal);
			 return findPmTalkTotalReturn;
		} catch (Exception e) {
			logger.error("查询客户咨询统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TALK_TOTAL_FIND_ERROR,"查询客户咨询统计表信息错误！",e);
		}
	}

	@Override
	public List<FindPmTalkTotalAllReturnList> findPmTalkTotaReturnList (
			FindPmTalkTotal findPmTalkTotal) throws TsfaServiceException{
		AssertUtils.notNull(findPmTalkTotal);
		List<FindPmTalkTotalAllReturnList> list=null;
		try {
			list=pmTalkTotalDao.findPmTalkTotaReturnList(findPmTalkTotal);
		} catch (Exception e) {
			logger.error("统计查询客户咨询统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TALK_TOTAL_FIND_ERROR,"统计查询客户咨询统计表信息错误！",e);
		}
		
		return list;
	}

	@Override
	public FindPmTalkTotalAllReturnList findPmTalkTotaReturnMax()
			throws TsfaServiceException {
		logger.debug("findPmTalkTotaReturnMax() - start"); 
		 FindPmTalkTotalAllReturnList findPmTalkTotalAllReturnList=null;
		try {
			findPmTalkTotalAllReturnList=pmTalkTotalDao.findPmTalkTotaReturnMax();
		} catch (Exception e) {
			logger.error("统计查询客户咨询统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TALK_TOTAL_FIND_ERROR,"统计查询客户咨询统计表信息错误！",e);
		}
		return findPmTalkTotalAllReturnList;
	}
	@Override
	public List<FindPmTalkTotalAllReturnList> findPmTalkTotaReturnData(
			FindPmTalkTotal findPmTalkTotal) throws TsfaServiceException {
		AssertUtils.notNull(findPmTalkTotal);
		List<FindPmTalkTotalAllReturnList> findPmTalkTotalAllReturnList=null;
		try {
			findPmTalkTotalAllReturnList=pmTalkTotalDao.findPmTalkTotaReturnData(findPmTalkTotal);
		} catch (Exception e) {
			logger.error("统计查询客户咨询统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PM_TALK_TOTAL_FIND_ERROR,"统计查询客户咨询统计表信息错误！",e);
		}
		 return findPmTalkTotalAllReturnList;
	}
	
	
}
