package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
//import com.lj.business.mec.dto.pv.PvDataStsByShop;
//import com.lj.business.mec.emus.PvType;
//import com.lj.business.mec.service.IPvDataService;
import com.lj.business.member.dto.PersonMemberStsGroupByShop;
import com.lj.business.member.service.IPersonMemberService;
//import com.lj.business.ord.dto.OrderStsGroupByShop;
//import com.lj.business.ord.service.IOrderInfoService;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IShopOperationDayReportDao;
import com.lj.business.st.domain.ShopOperationDayReport;
import com.lj.business.st.dto.mec.FindShopOperationDayReportPage;
import com.lj.business.st.dto.mec.FindShopOperationDayReportPageReturn;
import com.lj.business.st.service.IShopOperationDayReportService;
import com.lj.messagecenter.dto.sms.SmsStsByShop;
import com.lj.messagecenter.emus.SmsAppId;
import com.lj.messagecenter.service.ISmsInfoService;

/**
 * 
 * 
 * 类说明：门店运营日报实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
@Service
public class ShopOperationDayReportServiceImpl implements IShopOperationDayReportService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ShopOperationDayReportServiceImpl.class);
	
	@Resource
	private IShopOperationDayReportDao shopOperationDayReportDao;
	
	@Resource
	private IPersonMemberService personMemberService;
	
//	@Resource
//	private IOrderInfoService orderInfoService;
//	
//	@Resource
//	private IPvDataService pvDataService;
	
	@Resource
	private ISmsInfoService smsInfoService;
	
	/**
	 * 
	 *
	 * 方法说明：生成商户下所有门店指定日期的运营日报
	 *
	 * @param merchantNo
	 * @param reportDate
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月13日
	 *
	 */
	@Override
	public void generatorDayReport(String merchantNo, Date reportDate) {
		String reportDateStr = DateUtils.formatDate(reportDate, DateUtils.PATTERN_yyyy_MM_dd);
		logger.info("生成商户{}下门店运营日报{} -------- start", merchantNo, reportDateStr);
		
		Date beginTime = DateUtils.getDateByFirstSeconds(reportDate);	// 开始时间
		Date endTime = DateUtils.getDateByLastSeconds(beginTime);		// 结束时间
		
		// 统计门店客户情况
		List<PersonMemberStsGroupByShop> memberStsList = personMemberService.memberStsGroupByShop(merchantNo, beginTime, endTime);
		if(memberStsList != null && memberStsList.size() > 0) {
			// 统计门店下单情况
//			List<OrderStsGroupByShop> orderCreateStsList = orderInfoService.orderCreateStsGroupByShop(merchantNo, beginTime, endTime);
			// 将门店下单统计列表转为map
//			Map<String, OrderStsGroupByShop> orderCreateStsMap = this.covertToMap(orderCreateStsList);
			
			// 统计门店支付完成订单情况
//			List<OrderStsGroupByShop> paySuccessStsList = orderInfoService.paySuccessStsGroupByShop(merchantNo, beginTime, endTime);
			// 将门店支付完成订单统计列表转为map
//			Map<String, OrderStsGroupByShop> paySuccessStsMap = this.covertToMap(paySuccessStsList);
			
			// 统计客户访问商城主页情况
//			List<PvDataStsByShop> indexPvStsList = pvDataService.stsByShop(merchantNo, PvType.INDEX, beginTime, endTime);
			// 将门店访问商城主页列表转为map
//			Map<String, PvDataStsByShop> indexPvStsMap = this.covertToMapByPv(indexPvStsList);
			
			// 统计客户访问商品详情页情况
//			List<PvDataStsByShop> detailPvStsList = pvDataService.stsByShop(merchantNo, PvType.GOODSDETAIL, beginTime, endTime);
			// 将门店访问商品详情页列表转为map
//			Map<String, PvDataStsByShop> detailPvStsMap = this.covertToMapByPv(detailPvStsList);
			
			// 统计门店发送短信情况
			List<SmsStsByShop> smsStsList = smsInfoService.stsByShop(merchantNo, beginTime, endTime, SmsAppId.MEC.toString());
			// 将门店发送短信列表转为map
			Map<String, SmsStsByShop> smsStsMap = this.covertToMapBySms(smsStsList);
			
			// 创建门店运营日报记录
			ShopOperationDayReport shopOperationDayReport = null;
			Date now = new Date();
			for(PersonMemberStsGroupByShop memberSts : memberStsList) {
				if(StringUtils.isEmpty(memberSts.getCompanyNo())) {	// 不统计无经销商的门店
					continue;
				}
					
				// 门店下单情况
//				OrderStsGroupByShop ordOrderSts = orderCreateStsMap.get(memberSts.getShopNo());
				// 门店支付完成订单情况
//				OrderStsGroupByShop succOrderSts = paySuccessStsMap.get(memberSts.getShopNo());
				// 门店访问商城主页情况
//				PvDataStsByShop indexPvSts = indexPvStsMap.get(memberSts.getShopNo());
				// 门店访问商品详情页情况
//				PvDataStsByShop detailPvSts = detailPvStsMap.get(memberSts.getShopNo());
				// 门店发送短信统计情况
//				SmsStsByShop smsStsByShop = smsStsMap.get(memberSts.getShopNo());
				
				// 有总客户数、下单数、成交数、客户访问商城主页、发送了短信才生成门店日报
				if(memberSts.getTotalCount() > 0)/* 										// 总客户数
						|| (ordOrderSts != null && ordOrderSts.getOrderCount() > 0) 	// 下单数
						|| (succOrderSts != null && succOrderSts.getOrderCount() > 0) 	// 成交数
						|| (indexPvSts != null && indexPvSts.getTotalCount() > 0)*/		// 客户访问商城主页
//						|| (smsStsByShop != null && smsStsByShop.getSmsCount() > 0)) 
					{	// 短信数量
					try {
						shopOperationDayReport = new ShopOperationDayReport();
						shopOperationDayReport.setCode(GUID.generateByUUID());
						shopOperationDayReport.setReportDate(reportDate);
						shopOperationDayReport.setShopCode(memberSts.getShopNoMerchant());
//						shopOperationDayReport.setShopNo(memberSts.getShopNo());
//						shopOperationDayReport.setShopName(memberSts.getShopName());
						shopOperationDayReport.setDealerCode(memberSts.getDealerCode());
						shopOperationDayReport.setCompanyNo(memberSts.getCompanyNo());
						shopOperationDayReport.setCompanyName(memberSts.getCompanyName());
						shopOperationDayReport.setMerchantNo(merchantNo);
						shopOperationDayReport.setMbrTotalCount(memberSts.getTotalCount());
						shopOperationDayReport.setMbrTodayCount(memberSts.getTodayCount());
						
						// 门店下单情况
//						if(ordOrderSts != null) {
//							shopOperationDayReport.setOrdGoodCount(ordOrderSts.getGoodCount());
//							shopOperationDayReport.setOrdOrderCount(ordOrderSts.getOrderCount());
//							shopOperationDayReport.setOrdOrderAmount(ordOrderSts.getOrderAmount());
//							shopOperationDayReport.setOrdCustomerPrice(ordOrderSts.getCustomerPrice());
//							shopOperationDayReport.setOrdActivitieAmount(ordOrderSts.getActivitieAmount());
//							shopOperationDayReport.setOrdPayAmount(ordOrderSts.getPayAmount());
//						} else {
							shopOperationDayReport.setOrdGoodCount(0);
							shopOperationDayReport.setOrdOrderCount(0);
							shopOperationDayReport.setOrdOrderAmount(0L);
							shopOperationDayReport.setOrdCustomerPrice(0L);
							shopOperationDayReport.setOrdActivitieAmount(0L);
							shopOperationDayReport.setOrdPayAmount(0L);
//						}
						
						// 门店支付完成订单情况
//						if(succOrderSts != null) {
//							shopOperationDayReport.setSuccGoodCount(succOrderSts.getGoodCount());
//							shopOperationDayReport.setSuccOrderCount(succOrderSts.getOrderCount());
//							shopOperationDayReport.setSuccOrderAmount(succOrderSts.getOrderAmount());
//							shopOperationDayReport.setSuccCustomerPrice(succOrderSts.getCustomerPrice());
//							shopOperationDayReport.setSuccActivitieAmount(succOrderSts.getActivitieAmount());
//							shopOperationDayReport.setSuccPayAmount(succOrderSts.getPayAmount());
//						} else {
							shopOperationDayReport.setSuccGoodCount(0);
							shopOperationDayReport.setSuccOrderCount(0);
							shopOperationDayReport.setSuccOrderAmount(0L);
							shopOperationDayReport.setSuccCustomerPrice(0L);
							shopOperationDayReport.setSuccActivitieAmount(0L);
							shopOperationDayReport.setSuccPayAmount(0L);
//						}
						
						// 门店访问商城主页情况
//						if(indexPvSts != null) {
//							shopOperationDayReport.setPvIndexTotalCount(indexPvSts.getTotalCount());
//							shopOperationDayReport.setPvIndexMemberCount(indexPvSts.getMemberCount());
//						} else {
							shopOperationDayReport.setPvIndexTotalCount(0);
							shopOperationDayReport.setPvIndexMemberCount(0);
//						}
						
						// 门店访问商品详情页情况
//						if(detailPvSts != null) {
//							shopOperationDayReport.setPvDetailTotalCount(detailPvSts.getTotalCount());
//							shopOperationDayReport.setPvDetailMemberCount(detailPvSts.getMemberCount());
//						} else {
							shopOperationDayReport.setPvDetailTotalCount(0);
							shopOperationDayReport.setPvDetailMemberCount(0);
//						}
						
						// 门店发送短信统计情况
//						if(smsStsByShop != null) {
//							shopOperationDayReport.setSmsCount(smsStsByShop.getSmsCount());
//						} else {
							shopOperationDayReport.setSmsCount(0);
//						}
						
		//				shopOperationDayReport.setRemark(memberSts.getRemark());
						shopOperationDayReport.setCreateTime(now);
						shopOperationDayReportDao.insert(shopOperationDayReport);
					} catch (Exception e) {
//						logger.error("创建门店运营日报记录失败：{} - {}", memberSts.getShopNo(), memberSts.getShopName());
						logger.error("错误日志：", e);
						throw new TsfaServiceException(ErrorCode.SHOP_OPERATION_DAY_REPORT_ADD_ERROR, "创建门店运营日报记录失败", e);
					}
				} else {
//					logger.info("{}门店没有运营数据不生成运营日报： {} - {}", reportDateStr, memberSts.getShopNo(), memberSts.getShopName());
				}
			}
		}
		
		logger.info("生成商户{}下门店运营日报{} -------- end", merchantNo, reportDateStr);
	}

	/**
	 * 
	 *
	 * 方法说明：将门店订单统计列表转换为map： key=门店编号、value=门店订单统计情况
	 *
	 * @param orderStsList
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月13日
	 *
	 */
//	private Map<String, OrderStsGroupByShop> covertToMap(List<OrderStsGroupByShop> orderStsList) {
//		Map<String, OrderStsGroupByShop> map = new HashMap<String, OrderStsGroupByShop>();
//		if(orderStsList != null && !orderStsList.isEmpty()) {
//			for(OrderStsGroupByShop orderStsGroupByShop : orderStsList) {
//				map.put(orderStsGroupByShop.getShopNo(), orderStsGroupByShop);
//			}
//		}
//		return map;
//	}
	
	/**
	 * 
	 *
	 * 方法说明：将门店商城页面访问统计列表转换为map： key=门店编号、value=门店访问统计情况
	 *
	 * @param pvStsList
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月20日
	 *
	 */
//	private Map<String, PvDataStsByShop> covertToMapByPv(List<PvDataStsByShop> pvStsList) {
//		Map<String, PvDataStsByShop> map = new HashMap<String, PvDataStsByShop>();
//		if(pvStsList != null && !pvStsList.isEmpty()) {
//			for(PvDataStsByShop sts : pvStsList) {
//				map.put(sts.getShopNo(), sts);
//			}
//		}
//		return map;
//	}
	
	/**
	 * 
	 *
	 * 方法说明：将门店发送短信统计列表转换为map： key=门店编号、value=短信统计
	 *
	 * @param smsStsList
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月3日
	 *
	 */
	private Map<String, SmsStsByShop> covertToMapBySms(List<SmsStsByShop> smsStsList) {
		Map<String, SmsStsByShop> map = new HashMap<String, SmsStsByShop>();
		if(smsStsList != null && !smsStsList.isEmpty()) {
			for(SmsStsByShop sts : smsStsList) {
//				map.put(sts.getShopNo(), sts);
			}
		}
		return map;
	}
	

	@Override
	public Page<FindShopOperationDayReportPageReturn> findShopOperationDayReportPage(FindShopOperationDayReportPage findShopOperationDayReportPage) throws TsfaServiceException {
		logger.debug("findShopOperationDayReportPage(FindShopOperationDayReportPage findShopOperationDayReportPage={}) - start", findShopOperationDayReportPage); //$NON-NLS-1$

		AssertUtils.notNull(findShopOperationDayReportPage);
		List<FindShopOperationDayReportPageReturn> returnList = null;
		int count = 0;
		try {
			count = shopOperationDayReportDao.findShopOperationDayReportPageCount(findShopOperationDayReportPage);
			if(count > 0) {
				returnList = shopOperationDayReportDao.findShopOperationDayReportPage(findShopOperationDayReportPage);
			}
		}  catch (Exception e) {
			logger.error("门店运营日报信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SHOP_OPERATION_DAY_REPORT_FIND_PAGE_ERROR,"门店运营日报信息不存在错误.！",e);
		}
		Page<FindShopOperationDayReportPageReturn> returnPage = new Page<FindShopOperationDayReportPageReturn>(returnList, count, findShopOperationDayReportPage);

		logger.debug("findShopOperationDayReportPage(FindShopOperationDayReportPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 
}
