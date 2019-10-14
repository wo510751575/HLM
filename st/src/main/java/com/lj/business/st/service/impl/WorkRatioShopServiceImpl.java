package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IWorkRatioShopDao;
import com.lj.business.st.domain.WorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.AddWorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.FindExcellentShop;
import com.lj.business.st.dto.WorkRatioShop.FindExcellentShopReturn;
import com.lj.business.st.dto.WorkRatioShop.FindTopTenShop;
import com.lj.business.st.dto.WorkRatioShop.FindTopTenShopReturn;
import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShop;
import com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShopReturn;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.service.IWorkRatioShopService;

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
public class WorkRatioShopServiceImpl implements IWorkRatioShopService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WorkRatioShopServiceImpl.class);
	
	/** The work ratio shop dao. */
	@Resource
	private IWorkRatioShopDao workRatioShopDao;
//	@Autowired
//	private IClientFollowSummaryService clientFollowSummaryService;
	@Resource
	private IGuidMemberService guidMemberService;

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioShopService#addWorkRatioShop(com.lj.business.st.dto.WorkRatioShop.AddWorkRatioShop)
	 */
	@Override
	public void addWorkRatioShop(
			AddWorkRatioShop addWorkRatioShop) throws TsfaServiceException {
		logger.debug("addWorkRatioShop(AddWorkRatioShop addWorkRatioShop={}) - start", addWorkRatioShop); 

		AssertUtils.notNull(addWorkRatioShop);
		try {
			WorkRatioShop workRatioShop = new WorkRatioShop();
			//add数据录入
			workRatioShop.setCode(GUID.generateByUUID());
			workRatioShop.setMerchantNo(addWorkRatioShop.getMerchantNo());
			workRatioShop.setShopNo(addWorkRatioShop.getShopNo());
			workRatioShop.setShopName(addWorkRatioShop.getShopName());
			workRatioShop.setLogoAddr(addWorkRatioShop.getLogoAddr());
			workRatioShop.setMemberNoSp(addWorkRatioShop.getMemberNoSp());
			workRatioShop.setMemberNameSp(addWorkRatioShop.getMemberNameSp());
			workRatioShop.setHeadAddress(addWorkRatioShop.getHeadAddress());
			workRatioShop.setAreaCode(addWorkRatioShop.getAreaCode());
			workRatioShop.setProvinceCode(addWorkRatioShop.getProvinceCode());
			workRatioShop.setCityCode(addWorkRatioShop.getCityCode());
			workRatioShop.setCityAreaCode(addWorkRatioShop.getCityAreaCode());
			workRatioShop.setNumOrder(addWorkRatioShop.getNumOrder());
			workRatioShop.setNumPmOrder(addWorkRatioShop.getNumPmOrder());
			workRatioShop.setNumPmAdd(addWorkRatioShop.getNumPmAdd());
			workRatioShop.setNumPm(addWorkRatioShop.getNumPm());
			workRatioShop.setNumPmCf(addWorkRatioShop.getNumPmCf());
			workRatioShop.setNumPmKeep(addWorkRatioShop.getNumPmKeep());
			workRatioShop.setNumPmAbandon(addWorkRatioShop.getNumPmAbandon());
			workRatioShop.setNumPmIntention(addWorkRatioShop.getNumPmIntention());
			workRatioShop.setNumSale(addWorkRatioShop.getNumSale());
			workRatioShop.setOrderRate(addWorkRatioShop.getOrderRate());
			workRatioShop.setNumOrderRank(addWorkRatioShop.getNumOrderRank());
			workRatioShop.setNumPmAddRank(addWorkRatioShop.getNumPmAddRank());
			workRatioShop.setNumPmRank(addWorkRatioShop.getNumPmRank());
			workRatioShop.setNumPmCfRank(addWorkRatioShop.getNumPmCfRank());
			workRatioShop.setNumPmKeepRank(addWorkRatioShop.getNumPmKeepRank());
			workRatioShop.setNumPmAbandonRank(addWorkRatioShop.getNumPmAbandonRank());
			workRatioShop.setNumSaleRank(addWorkRatioShop.getNumSaleRank());
			workRatioShop.setNumOrderRankYtd(addWorkRatioShop.getNumOrderRankYtd());
			workRatioShop.setNumPmAddRankYtd(addWorkRatioShop.getNumPmAddRankYtd());
			workRatioShop.setNumPmRankYtd(addWorkRatioShop.getNumPmRankYtd());
			workRatioShop.setNumPmCfRankYtd(addWorkRatioShop.getNumPmCfRankYtd());
			workRatioShop.setNumPmKeepRankYtd(addWorkRatioShop.getNumPmKeepRankYtd());
			workRatioShop.setNumPmAbandonRankYtd(addWorkRatioShop.getNumPmAbandonRankYtd());
			workRatioShop.setNumSaleRankYtd(addWorkRatioShop.getNumSaleRankYtd());
			workRatioShop.setDaySt(addWorkRatioShop.getDaySt());
			workRatioShop.setOpenDate(addWorkRatioShop.getOpenDate());
			workRatioShop.setDimensionSt(addWorkRatioShop.getDimensionSt());
			workRatioShop.setCreateDate(new Date());
			workRatioShopDao.insertSelective(workRatioShop);
			logger.debug("addWorkRatioShop(AddWorkRatioShop) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购工作统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_ADD_ERROR,"新增导购工作统计表信息错误！",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioShopService#findWorkRatioShopList(com.lj.business.st.dto.WorkRatioShop.FindWorkRatioShop)
	 */
	@Override
	public List<WorkRatioShop> findWorkRatioShopList(FindWorkRatioShop findWorkRatioShop) {
		logger.debug("findWorkRatioShopList(findWorkRatioShop={}) - start", findWorkRatioShop);

		AssertUtils.notNull(findWorkRatioShop);
		List<WorkRatioShop> list=null;
		try {
			logger.debug("findWorkRatioShopList(findWorkRatioShopList) - end - return");
			list= workRatioShopDao.findWorkRatioShopList(findWorkRatioShop);
			logger.debug(list+"");
			return list;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查询导购工作统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_FIND_ERROR,"查询导购工作统计表信息错误！",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioShopService#findWorkRatioShopPage(java.util.Map)
	 */
	@Override
	public Page<WorkRatioShop> findWorkRatioShopPage(Map<String, Object> parmMap) {
		logger.debug("findWorkRatioShopPage(Map<String, Object> parmMap={}) - start", parmMap); 

		AssertUtils.notNull(parmMap);
		AssertUtils.notNull(parmMap.get("start"));
		AssertUtils.notNull(parmMap.get("limit"));
		
		if(parmMap.get("endTime") == null) {
			parmMap.put("stDate", DateUtils.getPreday(new Date()));	// 默认取前一天
		} else {
			Date stDate = (Date) parmMap.get("endTime");
			Date now = new Date();
			// 如果结束日期endTime为当天，或者大于当天，则统计日期stDate取前一天日期
			if(DateUtils.isSameDay(stDate, now) || now.before(stDate)) {
				parmMap.put("stDate", DateUtils.getPreday(now));
			} else { // 否则取endTime
				parmMap.put("stDate", stDate);
			}
		}
		
		int count=0;
		List<WorkRatioShop> list= null;
		try {
			list = workRatioShopDao.findWorkRatioShopPage(parmMap);
			count = workRatioShopDao.findWorkRatioShopRankCount(parmMap);
		} catch (Exception e) {
			logger.error("分页查询门店工作统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_FIND_ERROR,"分页查询门店工作统计表信息错误！",e);
		}
		Page<WorkRatioShop> returnPage = new Page<WorkRatioShop>(list, count,Integer.valueOf(parmMap.get("start").toString()) , Integer.valueOf(parmMap.get("limit").toString()));
		logger.debug("findWorkRatioShopPage(parmMap) - end - return value={}", parmMap);
		return returnPage;
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioShopService#findWorkRatioShopCount(java.util.Map)
	 */
	@Override
	public int findWorkRatioShopCount(Map<String, Object> parmMap) {
		logger.debug("findWorkRatioShopCount(Map<String, Object> parmMap={}) - start", parmMap); 

		AssertUtils.notNull(parmMap);
		
		int count=0;
		try {
			count = workRatioShopDao.findWorkRatioShopCount(parmMap);
		} catch (Exception e) {
			logger.error("分页查询门店工作统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_FIND_ERROR,"分页查询门店工作统计表信息错误！",e);
		}
		logger.debug("findWorkRatioShopCount(parmMap) - end - return value={}", parmMap);
		return count;
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioShopService#findTopTenShop(com.lj.business.st.dto.WorkRatioShop.FindTopTenShop)
	 */
	@Override
	public List<FindTopTenShopReturn> findTopTenShop(
			FindTopTenShop findTopTenShop) {
		logger.debug("findTopTenShop(FindTopTenShop findTopTenShop={}) - start", findTopTenShop); //$NON-NLS-1$

		AssertUtils.notNull(findTopTenShop);
		AssertUtils.notNull(findTopTenShop.getMerchantNo(),"商户编号不能为空！");
		try {
			if(findTopTenShop.getDaySt() == null){
				Date daySt = DateUtils.getPreday(org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH) );
				findTopTenShop.setDaySt(daySt);
			}
			List<FindTopTenShopReturn> returnList = workRatioShopDao.findTopTenShop(findTopTenShop);
			logger.debug("findTopTenShop(FindTopTenShop) - end - return value={}", returnList); //$NON-NLS-1$
			return returnList;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("查找导购工作统计表信息错误",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_FIND_ERROR,"查找导购工作统计表信息错误",e);
		}
	}


	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioShopService#findWorkRatioShops(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> findWorkRatioShops(
			Map<String, Object> parmMap) {
		logger.debug("findWorkRatioShops(Map<String, Object> parmMap={}) - start", parmMap); 

		AssertUtils.notNull(parmMap);
		List<Map<String, Object>> list= null;
		try {
			list = workRatioShopDao.findWorkRatioShops(parmMap);
		} catch (Exception e) {
			logger.error("查询门店工作统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_FIND_ERROR,"查询门店工作统计表信息错误！",e);
		}
		logger.debug("findWorkRatioShops(parmMap) - end - return value={}", parmMap);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.IWorkRatioShopService#findExcellentShop(com.lj.business.st.dto.WorkRatioShop.FindExcellentShop)
	 */
	@Override
	public List<FindExcellentShopReturn> findExcellentShop(
			FindExcellentShop findExcellentShop) {
		logger.debug("findExcellentShop(FindExcellentShop findExcellentShop={}) - start", findExcellentShop); //$NON-NLS-1$

		AssertUtils.notNull(findExcellentShop);
		AssertUtils.notNull(findExcellentShop.getMerchantNo(),"商户编号不能为空！");
		try {
			if(findExcellentShop.getDaySt() == null){
				Date daySt = DateUtils.getPreday(org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH) );
				findExcellentShop.setDaySt(daySt);
			}
			
			logger.info("@@@@@@@@@@@@@@DimensionSt:" + findExcellentShop.getDimensionSt());
			logger.info("@@@@@@@@@@@@@@AreaCode:" + findExcellentShop.getAreaCode());
			
			if (StringUtils.isBlank(findExcellentShop.getAreaCode()) && !"MERCHANT".equals(findExcellentShop.getDimensionSt())) {//当查询维度为门店时，需按区域维度查询
				
				logger.info("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
				
//				FindShop findShop = new FindShop();
//				findShop.setShopNo(findExcellentShop.getShopNo());
//				FindShopReturn shopReturn = shopService.findShopByShopNo(findShop);
//				findExcellentShop.setAreaCode(shopReturn.getAreaCode());
			}
			
			List<FindExcellentShopReturn> returnList = workRatioShopDao.findExcellentShop(findExcellentShop);

			logger.debug("findExcellentShop(FindExcellentShop) - end - return value={}", returnList); //$NON-NLS-1$
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
	public Map<String, Object> indexSale(Map<String, Object> parmMap) {
		logger.debug("indexSale(Map<String, Object> parmMap={}) - start", parmMap); 

		AssertUtils.notNull(parmMap);
		AssertUtils.notNull(parmMap.get("merchantNo"));
		Map<String, Object> returnMap = new HashMap<>();
		try {
			Date nowDate = new Date();
			//当前销售额 = 当天0时0分0秒 - 当前时间
			Calendar calendar = Calendar.getInstance();  
			//将小时至0  
			calendar.set(Calendar.HOUR_OF_DAY, 0);  
			//将分钟至0  
			calendar.set(Calendar.MINUTE, 0);  
			//将秒至0  
			calendar.set(Calendar.SECOND,0);  
			Date zeroDate= calendar.getTime();
			parmMap.put("startTime",zeroDate);
			parmMap.put("endTime",nowDate);
			long nowSale = 0;
			
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMerchantNo(parmMap.get("merchantNo").toString());
			
			//根据维度查询导购信息
			if(!DimensionSt.MERCHANT.toString().equals( parmMap.get("dimensionSt").toString())){
				if (StringUtils.isNotBlank(parmMap.get("areaCode") == null ? "" : parmMap.get("areaCode").toString())) {
					findGuidMemberPage.setAreaCode(parmMap.get("areaCode").toString());
				} else {
//					findGuidMemberPage.setShopNo(parmMap.get("shopNo").toString());
				}
			}
			
			//查询各个导购的成单金额
			List<FindGuidMemberPageReturn> members = guidMemberService.findGuidMembers(findGuidMemberPage);
			for (FindGuidMemberPageReturn findGuidMemberPageReturn : members) {
				parmMap.put("memberNoGm", findGuidMemberPageReturn.getMemberNo());
//				nowSale += clientFollowSummaryService.sumAmountByMerchantNo(parmMap);
			}
			
			//获取昨天销售额
			//parmMap.put("dimensionSt", DimensionSt.MERCHANT.toString());
			parmMap.put("startTime",DateUtils.formatDate(DateUtils.getPreday(nowDate), DateUtils.PATTERN_yyyy_MM_dd) );
			parmMap.put("endTime", DateUtils.formatDate(DateUtils.getPreday(nowDate), DateUtils.PATTERN_yyyy_MM_dd) );
			List<Map<String, Object>> list = this.findWorkRatioShops(parmMap);
			long numSale = 0L;		//昨日销售额
			if(list != null && list.size()>0){
				for(Map<String, Object> map : list){
					numSale = numSale + Long.valueOf(map.get("NUM_SALE") == null ? "0" : map.get("NUM_SALE").toString());
				}
			}
			
			//同比 = (当前销售额 - 昨日销售额)/昨日销售额 正数上涨，负数下降
			double ratioSale =  0.0;
			if(0d== nowSale && 0d == numSale){
				ratioSale =  0.0;
			}else if(0d !=nowSale  && 0d == numSale){
				ratioSale =  1.0;
			}else{
				ratioSale = (nowSale-numSale)/Double.valueOf(numSale);
			}
					
			//四舍五入保留两位小数
			BigDecimal bigDecimal = new BigDecimal(ratioSale);
			ratioSale =bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			returnMap.put("nowSale", nowSale);
			returnMap.put("ratioSale", ratioSale);
		} catch (Exception e) {
			logger.error("查询门店工作统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_FIND_ERROR,"查询门店工作统计表信息错误！",e);
		}
		logger.debug("findWorkRatioShops(parmMap) - end - return value={}", parmMap);
		return returnMap;
	}

	
	
	@Override
	public List<FindExcellentShopReturn> findWorkRatioShopReturnList(
			FindWorkRatioShop findWorkRatioShop) {
		AssertUtils.notNull(findWorkRatioShop);
		List<FindExcellentShopReturn> list=null;
		try {
			list=workRatioShopDao.findWorkRatioShopReturnList(findWorkRatioShop);
		} catch (Exception e) {
			logger.error("查询门店工作统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_FIND_ERROR,"查询门店工作统计表信息错误！",e);
		}
		logger.debug("findWorkRatioShopReturnList(findWorkRatioShop) - end - return value={}", findWorkRatioShop);
		
		return list;
	}

	@Override
	public List<FindWorkRatioShopReturn> findWorkRatioShopNum(
			Map<String, Object> parmMap) {
		AssertUtils.notNull(parmMap);
		List<FindWorkRatioShopReturn> list=null;
		try {
			list=workRatioShopDao.findWorkRatioShopNum(parmMap);
		} catch (Exception e) {
			logger.error("查询门店工作统计表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_FIND_ERROR,"查询门店工作统计表信息错误！",e);
		}
		
		return list;
	}
}
