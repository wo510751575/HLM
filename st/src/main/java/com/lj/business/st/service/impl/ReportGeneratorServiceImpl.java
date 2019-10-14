package com.lj.business.st.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.ArithUtil;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.GeneratorMaterialTotalReturn;
import com.lj.business.cm.dto.textInfo.FindTextInfo;
import com.lj.business.cm.dto.textInfo.FindTextInfoPageReturn;
import com.lj.business.cm.service.IMaterialCommenService;
import com.lj.business.cm.service.IMaterialService;
import com.lj.business.cm.service.ITextInfoService;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindManagerMemberPage;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberAgeStatisticsReturn;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseList;
import com.lj.business.member.dto.FindPersonMemberInterestStatisticsReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.FindPersonMemberReturnList;
import com.lj.business.member.dto.FindPersonMemberSexStatisticsReturn;
import com.lj.business.member.dto.FindPmLabelReturnList;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.FindUrgentMbrPage;
import com.lj.business.member.dto.FindUrgentMbrPageReturn;
import com.lj.business.member.dto.GuidInfoShop;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.ManagerMemberReturnDto;
import com.lj.business.member.dto.guidMemberIntegral.GuidMemberIntegralDto;
import com.lj.business.member.dto.guidMemberIntegralDay.FindGuidMemberIntegralDay;
import com.lj.business.member.dto.guidMemberIntegralDay.FindGuidMemberIntegralDayReturn;
import com.lj.business.member.emus.Gender;
import com.lj.business.member.emus.IntegralType;
import com.lj.business.member.emus.MemberType;
import com.lj.business.member.emus.ProductType;
import com.lj.business.member.emus.Status;
import com.lj.business.member.service.IGuidMemberIntegralDayService;
import com.lj.business.member.service.IGuidMemberIntegralService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.domain.ClientAnalyze;
import com.lj.business.st.domain.ClientInterestRpt;
import com.lj.business.st.domain.ClientLineRpt;
import com.lj.business.st.domain.WorkRatioGm;
import com.lj.business.st.dto.AddClientAnalyze;
import com.lj.business.st.dto.AddClientExpTotal;
import com.lj.business.st.dto.AddEmployeeAnalyze;
import com.lj.business.st.dto.AddUnContactTotal;
import com.lj.business.st.dto.AddWorkRatioArea;
import com.lj.business.st.dto.AddWorkRatioGm;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;
import com.lj.business.st.dto.FindUnContactTotalInfo;
import com.lj.business.st.dto.FindUnContactTotalInfoReturn;
import com.lj.business.st.dto.FindUnContactTotalReturn;
import com.lj.business.st.dto.UpdateUnContactTotal;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyze;
import com.lj.business.st.dto.AreaOrderAnalyze.FindAreaOrderAnalyzeReturn;
import com.lj.business.st.dto.CfAnalyze.AddCfAnalyze;
import com.lj.business.st.dto.ClientInterestRpt.AddClientInterestRpt;
import com.lj.business.st.dto.ClientLineRpt.AddClientLineRpt;
import com.lj.business.st.dto.MaterialTotal.AddMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotal;
import com.lj.business.st.dto.MaterialTotal.FindMaterialTotalReturn;
import com.lj.business.st.dto.OperationAnalysisDayBrief.AddOperationAnalysisDayBrief;
import com.lj.business.st.dto.PmLabelTotal.AddPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotalReturnDto;
import com.lj.business.st.dto.PmTalkTotal.AddPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotal;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalAllReturnList;
import com.lj.business.st.dto.PmTalkTotal.FindPmTalkTotalReturn;
import com.lj.business.st.dto.PmTypeTotal.AddPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotal;
import com.lj.business.st.dto.PmTypeTotal.FindPmTypeTotalReturn;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyze;
import com.lj.business.st.dto.SocialAnalyze.FindSocialAnalyzeReturn;
import com.lj.business.st.dto.WorkRatioShop.AddWorkRatioShop;
import com.lj.business.st.emus.DimensionSt;
import com.lj.business.st.emus.TextType;
import com.lj.business.st.service.IAreaOrderAnalyzeService;
import com.lj.business.st.service.ICfAnalyzeService;
import com.lj.business.st.service.ICfCountAnalyzeService;
import com.lj.business.st.service.IClientAnalyzeService;
import com.lj.business.st.service.IClientExpTotalService;
import com.lj.business.st.service.IClientInterestRptService;
import com.lj.business.st.service.IClientLineRptService;
import com.lj.business.st.service.IEmployeeAnalyzeService;
import com.lj.business.st.service.IMaterialTotalService;
import com.lj.business.st.service.IOperationAnalysisDayBriefService;
import com.lj.business.st.service.IPmLabelTotalService;
import com.lj.business.st.service.IPmTalkTotalService;
import com.lj.business.st.service.IPmTypeTotalService;
import com.lj.business.st.service.IReportGeneratorService;
import com.lj.business.st.service.ISocialAnalyzeService;
import com.lj.business.st.service.IUnContactTotalService;
import com.lj.business.st.service.IWorkRatioAreaService;
import com.lj.business.st.service.IWorkRatioGmService;
import com.lj.business.st.service.IWorkRatioShopService;
import com.lj.business.weixin.dto.FindWxChatInfo;
import com.lj.business.weixin.service.IWxChatInfoService;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.service.AreaHessianService;

/**
 * The Class ReportGeneratorServiceImpl.
 */
@Service
public class ReportGeneratorServiceImpl implements IReportGeneratorService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory
			.getLogger(ReportGeneratorServiceImpl.class);

	/** The work ratio gm dao. */
	@Resource
	private IWorkRatioGmService workRatioGmService; // 导购工作统计
	@Resource
	private IWorkRatioShopService workRatioShopService; // 门店工作统计
	@Autowired
	private IClientExpTotalService clientExpTotalService; // 客户体验统计服务
//	@Autowired
//	private IClientExpService clientExpService; // 客户体验服务
	@Autowired
	private IMerchantService merchantService; // 商户服务
	@Autowired
	private IGuidMemberService guidMemberService; // 导购服务
	@Autowired
	private IManagerMemberService managerMemberService; // 管理人员服务
	@Autowired
	private IEmployeeAnalyzeService employeeAnalyzeService; // 员工画像服务
//	@Autowired
//	private IClientFollowService clientFollowService; // 跟踪明细服务
//	@Autowired
//	private IClientKeepService clientKeepService;
	@Autowired
	private ICfCountAnalyzeService countAnalyzeService; // 跟进次数分析

	@Autowired
	private IPmLabelTotalService pmLabelTotalService; // 客户标签统计
	@Autowired
	private IPmTalkTotalService pmTalkTotalService; // 客户咨询
	@Autowired
	private IPmTypeTotalService pmTypeTotalService;// 客户分类
	@Autowired
	private ISocialAnalyzeService socialAnalyzeService; // 社交分析
	@Autowired
	private IMaterialTotalService materialTotalService;// 素材统计中心
	@Autowired
	private ITextInfoService textInfoService;// 话术信息
	@Autowired
	private ICfAnalyzeService cfAnalyzeService;// 跟进分析摘要
	@Autowired
	private IPmLabelService pmLabelService;// 客户标签
//	@Autowired
//	private IShopService shopService; // 门店服务
//	@Autowired
//	private IClientFollowSummaryService clientFollowSummaryService;// 客户跟踪表
//	@Autowired
//	private IClientKeepSummaryService clientKeepSummaryService;// 客户维护表
	@Autowired
	private IAreaOrderAnalyzeService areaOrderAnalyzeService;// 区域订单分析
	@Autowired
	private IPersonMemberBaseService personMemberBaseService;// 客户基础服务
	@Autowired
	private IPersonMemberService personMemberService;// 客户服务
	@Autowired
	private IWorkRatioAreaService workRatioAreaService;// 区域统计
	@Autowired
	private IWxChatInfoService wxChatInfoService;// 微信聊天记录
//	@Autowired
//	private ISocialTaskService socialTaskService; // 社交统计

	@Resource
	private IGuidMemberIntegralService guidMemberIntegralService;

	@Resource
	private IUnContactTotalService unContactTotalService;

	@Resource
	private IMaterialService materialService;

	@Resource
	private IMaterialCommenService materialCommenService;

	@Resource
	private IOperationAnalysisDayBriefService operationAnalysisDayBriefService;// 运营分析摘要

	@Resource
	private AreaHessianService areaHessianService; // 省接口

	@Resource
	private IGuidMemberIntegralDayService guidMemberIntegralDayService;
	
	@Resource
	private IClientAnalyzeService clientAnalyzeService;

	@Resource
	private IClientInterestRptService clientInterestRptService;
	
	@Resource
	private IClientLineRptService clientLineRptService;
	
	
//	@Resource
//	private IClientArriveService clientArriveService;
//	
//	@Resource
//	private IClientInviteHcService clientInviteHcService;
//	
//	@Resource
//	private IClientConsumeService clientConsumeService;
	
	@Resource
	private IPmTypeService pmTypeService;
	

	public static final int LIMIT = 1000;
    
	public static final Date EndDate=DateUtils.getPreday(org.apache.commons.lang.time.DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH) );
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.st.service.IReportGeneratorService#generatorWorkRatioGm()
	 */
	@Override
	public void generatorWorkRatioGm(Date date) throws TsfaServiceException {
		logger.debug("generatorWorkRatioGm---------start");
		try {
			List<FindMerchantPageReturn> merchantList = merchantService.findAllMerchant();
			if (CollectionUtils.isEmpty(merchantList)) {
				return;
			}
			// 将商户列表按商户编号存入MAP
			Map<String, FindMerchantPageReturn> merchantMap = new HashMap<String, FindMerchantPageReturn>();
			for (FindMerchantPageReturn merchant : merchantList) {
				merchantMap.put(merchant.getMerchantNo(), merchant);
			}

			int currentPage = 1;// 当前页
			int limit = 1000;// 每页数量

			Page<FindGuidMemberPageReturn> guidMemberPage = guidMemberService.findGuidMemberPage(currentPage, limit);
			int realSize = guidMemberPage.getRows().size();// 当前数据总数

			Map<String, Map<String, AddWorkRatioGm>> map = new HashMap<>();// 商户和
			map.put(DimensionSt.MERCHANT.toString(), new HashMap<String, AddWorkRatioGm>());
			map.put(DimensionSt.SHOP.toString(), new HashMap<String, AddWorkRatioGm>());
			map.put(DimensionSt.AREA.toString(), new HashMap<String, AddWorkRatioGm>());

			
			//TODO
			for (; realSize > 0; realSize = guidMemberPage.getRows().size()) {
				convertWorkRatioGmToAdd(date, map, guidMemberPage.getRows(), merchantMap);
				guidMemberPage = guidMemberService.findGuidMemberPage(++currentPage, limit);
			}

			for (Entry<String, Map<String, AddWorkRatioGm>> entry : map.entrySet()) {
				if (entry.getValue().size() > 0) {
					for (Entry<String, AddWorkRatioGm> addWorkRatioGmEntry : entry.getValue().entrySet()) {
						workRatioGmService.addWorkRatioGm(addWorkRatioGmEntry.getValue());
					}
				}
			}

		} catch (Exception e) {
			logger.error("生成导购工作统计错误！", e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_ADD_ERROR, "新增导购工作完成度统计信息错误！", e);
		}
		logger.debug("generatorWorkRatioGm---------end");
	}

	/**
	 * 组装工作统计数据并插入
	 * update by 曾垂瑜 2017-09-26 分邀约型和非邀约型所属商户进行统计
	 * 
	 * @param guidMemberPage
	 */
	private void convertWorkRatioGmToAdd(Date date, Map<String, Map<String, AddWorkRatioGm>> map, Collection<FindGuidMemberPageReturn> GuidMembers, Map<String, FindMerchantPageReturn> merchantMap) {
		for (FindGuidMemberPageReturn findGuidMemberPageReturn : GuidMembers) {
			logger.debug("统计导购{}开始", findGuidMemberPageReturn.getMemberNo());
			AddWorkRatioGm addWorkRatioGm = new AddWorkRatioGm();
			addWorkRatioGm.setMerchantNo(findGuidMemberPageReturn.getMerchantNo());
			addWorkRatioGm.setAreaCode(findGuidMemberPageReturn.getAreaCode());
			addWorkRatioGm.setAreaName(findGuidMemberPageReturn.getAreaName());
//			addWorkRatioGm.setShopNo(findGuidMemberPageReturn.getShopNo());
//			addWorkRatioGm.setShopName(findGuidMemberPageReturn.getShopName());
			addWorkRatioGm.setMemberNoGm(findGuidMemberPageReturn.getMemberNo());
			addWorkRatioGm.setMemberNameGm(findGuidMemberPageReturn.getMemberName());
			if(findGuidMemberPageReturn.getHeadAddress()!=null){
				addWorkRatioGm.setHeadAddress(findGuidMemberPageReturn.getHeadAddress());
			}
			
			addWorkRatioGm.setNumBeatGm(0);// TODO 获取导购公司排名
			addWorkRatioGm.setNumPm(getNumPmByGm(findGuidMemberPageReturn.getMerchantNo(), findGuidMemberPageReturn.getMemberNo()));// 导购客户数量
			addWorkRatioGm.setNumPmAdd(getNumPmAddByGmDay(findGuidMemberPageReturn.getMemberNo(), date));// 导购新增客户数量
			
			// 查询导购所属商户
			FindMerchantPageReturn merchant = merchantMap.get(findGuidMemberPageReturn.getMerchantNo());
			// 非邀约型商户统计(CLIENT_CONSUME)
			if(merchant != null && ProductType.NOINVITE.name().equals(merchant.getProductType())) {
				addWorkRatioGm.setRatioWork(0d);// 工作完成度积分
				addWorkRatioGm.setNumPmIntention(0L);// 导购意向客户数量
				addWorkRatioGm.setNumPmAbandon(0L);// 暂停客户数量
//				FindConsumeAmtTotalReturn total = this.getTradeTotalByGmHc(findGuidMemberPageReturn.getMemberNo(), date);
//				addWorkRatioGm.setNumSale(total.getTradeAmt());// 销售额
//				addWorkRatioGm.setNumOrder(Long.valueOf(total.getTradeCount()));// 成单数量
//				addWorkRatioGm.setNumPmOrder(Long.valueOf(total.getPmCount()));// 成单客户数量
				addWorkRatioGm.setNumRead(0L);// TODO 阅读数量
				addWorkRatioGm.setNumSocial(0L);// 社交数量
//				addWorkRatioGm.setNumVisit(Long.valueOf(getNumVisitBerDayByGmHc(findGuidMemberPageReturn.getMemberNo(), date)));// 到店体验数
				addWorkRatioGm.setNumPmCf(0L);// 跟进客户数量
				addWorkRatioGm.setNumPmKeep(0L);// 维护客户数量
			} else {	// 邀约型商户统计(CLIENT_FOLLOW_SUMMARY)
				addWorkRatioGm.setRatioWork(getRatioWorkByGMDay(findGuidMemberPageReturn.getMemberNo(), date));// 工作完成度积分
//				addWorkRatioGm.setNumPmIntention(Long.valueOf(getPmCountByType(findGuidMemberPageReturn.getMemberNo(), PmTypeType.INTENTION) + getPmCountByType(findGuidMemberPageReturn.getMemberNo(), PmTypeType.INTENTION_N)));// 导购意向客户数量
//				addWorkRatioGm.setNumPmAbandon(Long.valueOf(getPmCountByType(findGuidMemberPageReturn.getMemberNo(), PmTypeType.GIVE_UP)));// 暂停客户数量
//				addWorkRatioGm.setNumSale(getNumSaleBerDayByGm(findGuidMemberPageReturn.getMemberNo(), date));// 销售额
//				addWorkRatioGm.setNumOrder(Long.valueOf(getNumOrderByGm(findGuidMemberPageReturn.getMemberNo(), date)));// 成单数量
//				addWorkRatioGm.setNumPmOrder(Long.valueOf(getNumPmOrderByGm(findGuidMemberPageReturn.getMemberNo(), date)));// 成单客户数量
				addWorkRatioGm.setNumRead(0L);// TODO 阅读数量
//				addWorkRatioGm.setNumSocial(Long.valueOf(getNumSocialBerDayByGm(findGuidMemberPageReturn.getMemberNo(), date)));// 社交数量
//				addWorkRatioGm.setNumVisit(Long.valueOf(getNumVisitBerDayByGm(findGuidMemberPageReturn.getMemberNo(), date)));// 到店体验数
//				addWorkRatioGm.setNumPmCf(getNumPmCf(findGuidMemberPageReturn.getMemberNo()));// 跟进客户数量
//				addWorkRatioGm.setNumPmCfDay(getNumPmCfDayByGm(findGuidMemberPageReturn.getMemberNo(), date));// 当天跟进客户数
//				addWorkRatioGm.setNumCfDay(getNumCfDay(findGuidMemberPageReturn.getMemberNo(), date));// 当天跟进总次数
//				addWorkRatioGm.setNumPmKeep(getNumPmKeep(findGuidMemberPageReturn.getMemberNo()));// 维护客户数量
			}

			addWorkRatioGm.setRatioWorkRank(0);// TODO 排名后加
			addWorkRatioGm.setNumPmRank(0);// TODO 排名后加
			addWorkRatioGm.setNumPmAddRank(0);// TODO 排名后加
			addWorkRatioGm.setNumSaleRank(0);// TODO 排名后加
			addWorkRatioGm.setNumOrderRank(0);// TODO 排名后加
			addWorkRatioGm.setNumReadRank(0);// TODO 排名后加
			addWorkRatioGm.setNumVisitRank(0L);// TODO 排名后加
			addWorkRatioGm.setRatioWorkRankYtd(0);// TODO 排名后加
			addWorkRatioGm.setNumPmRankYtd(0);// TODO 排名后加
			addWorkRatioGm.setNumPmAddRankYtd(0);// TODO 排名后加
			addWorkRatioGm.setNumSaleRankYtd(0);// TODO 排名后加
			addWorkRatioGm.setNumOrderRankYtd(0);// TODO 排名后加
			addWorkRatioGm.setNumReadRankYtd(0);// TODO 排名后加
			addWorkRatioGm.setNumVisitRankYtd(0L);// TODO 排名后加

			addWorkRatioGm.setDaySt(DateUtils.getPreday(new Date()));
			addWorkRatioGm.setDimensionSt(DimensionSt.GUID.toString());
			addWorkRatioGm.setCreateDate(new Date());

			workRatioGmService.addWorkRatioGm(addWorkRatioGm);

			convertDimensionWorkRatioGm(DimensionSt.MERCHANT, addWorkRatioGm.getMerchantNo(), map, addWorkRatioGm);// 组装商户维度

			convertDimensionWorkRatioGm(DimensionSt.SHOP, addWorkRatioGm.getShopNo(), map, addWorkRatioGm);// 组装门店维度

			convertDimensionWorkRatioGm(DimensionSt.AREA, addWorkRatioGm.getAreaCode() + addWorkRatioGm.getMerchantNo(), map, addWorkRatioGm);// 组装区域维度
		}
	}

	/**
	 * 组装不同维度的统计
	 * 
	 * @param dimensionSt
	 * @param dimCode
	 * @param map
	 * @param addWorkRatioGm
	 */
	private void convertDimensionWorkRatioGm(DimensionSt dimensionSt, String dimCode, Map<String, Map<String, AddWorkRatioGm>> map, AddWorkRatioGm addWorkRatioGm) {
		if (map.get(dimensionSt.toString()).containsKey(dimCode)) {
			AddWorkRatioGm workRatioGm = map.get(dimensionSt.toString()).get(dimCode);
			workRatioGm.setRatioWork(workRatioGm.getRatioWork() + addWorkRatioGm.getRatioWork());
			workRatioGm.setNumBeatGm(workRatioGm.getNumBeatGm() + addWorkRatioGm.getNumBeatGm());
			workRatioGm.setNumPm(workRatioGm.getNumPm() + addWorkRatioGm.getNumPm());
			workRatioGm.setNumPmAdd(workRatioGm.getNumPmAdd() + addWorkRatioGm.getNumPmAdd());
			workRatioGm.setNumPmIntention(workRatioGm.getNumPmIntention() + addWorkRatioGm.getNumPmIntention());// 导购意向客户数量
			workRatioGm.setNumPmAbandon(workRatioGm.getNumPmAbandon() + addWorkRatioGm.getNumPmAbandon());// 暂停客户数量
			workRatioGm.setNumSale(workRatioGm.getNumSale() + addWorkRatioGm.getNumSale());// 销售额
			workRatioGm.setNumOrder(workRatioGm.getNumOrder() + addWorkRatioGm.getNumOrder());// 成单数量
			workRatioGm.setNumPmOrder(workRatioGm.getNumPmOrder() + addWorkRatioGm.getNumPmOrder());// 成单客户数量
			workRatioGm.setNumRead(workRatioGm.getNumRead() + addWorkRatioGm.getNumRead());// 阅读数量
			workRatioGm.setNumSocial(workRatioGm.getNumSocial() + addWorkRatioGm.getNumSocial());// 社交数量
			workRatioGm.setNumVisit(workRatioGm.getNumVisit() + addWorkRatioGm.getNumVisit());// 到店体验数
			workRatioGm.setNumPmCf(workRatioGm.getNumPmCf() + addWorkRatioGm.getNumPmCf());// 跟进客户数量
			workRatioGm.setNumPmKeep(workRatioGm.getNumPmKeep() + addWorkRatioGm.getNumPmKeep());// 维护客户数量
		} else {
			AddWorkRatioGm workRatioGm = new AddWorkRatioGm();
			switch (dimensionSt) {
			case MERCHANT:
				workRatioGm.setMerchantNo(addWorkRatioGm.getMerchantNo());
				break;
			case SHOP:
				workRatioGm.setMerchantNo(addWorkRatioGm.getMerchantNo());
				workRatioGm.setShopNo(addWorkRatioGm.getShopNo());
				workRatioGm.setShopName(addWorkRatioGm.getShopName());
				workRatioGm.setAreaCode(addWorkRatioGm.getAreaCode());
				workRatioGm.setAreaName(addWorkRatioGm.getAreaName());
				break;
			case AREA:
				workRatioGm.setMerchantNo(addWorkRatioGm.getMerchantNo());
				workRatioGm.setAreaCode(addWorkRatioGm.getAreaCode());
				workRatioGm.setAreaName(addWorkRatioGm.getAreaName());
				break;
			default:
				logger.error("统计维度错误");
				throw new TsfaServiceException(ErrorCode.WORK_RATIO_GM_ADD_ERROR, "新增导购工作完成度统计维度错误！");
			}

			workRatioGm.setRatioWork(addWorkRatioGm.getRatioWork());
			workRatioGm.setNumBeatGm(addWorkRatioGm.getNumBeatGm());
			workRatioGm.setNumPm(addWorkRatioGm.getNumPm());
			workRatioGm.setNumPmAdd(addWorkRatioGm.getNumPmAdd());
			workRatioGm.setNumPmIntention(addWorkRatioGm.getNumPmIntention());// 导购意向客户数量
			workRatioGm.setNumPmAbandon(addWorkRatioGm.getNumPmAbandon());// 暂停客户数量
			workRatioGm.setNumSale(addWorkRatioGm.getNumSale());// 销售额
			workRatioGm.setNumOrder(addWorkRatioGm.getNumOrder());// 成单数量
			workRatioGm.setNumPmOrder(addWorkRatioGm.getNumPmOrder());// 成单客户数
			workRatioGm.setNumRead(addWorkRatioGm.getNumRead());// 阅读数量
			workRatioGm.setNumSocial(addWorkRatioGm.getNumSocial());// 社交数量
			workRatioGm.setNumVisit(addWorkRatioGm.getNumVisit());// 到店体验数
			workRatioGm.setNumPmCf(addWorkRatioGm.getNumPmCf());// 跟进客户数量
			workRatioGm.setNumPmKeep(addWorkRatioGm.getNumPmKeep());// 维护客户数量

			workRatioGm.setRatioWorkRank(0);// TODO 排名后加
			workRatioGm.setNumPmRank(0);// TODO 排名后加
			workRatioGm.setNumPmAddRank(0);// TODO 排名后加
			workRatioGm.setNumSaleRank(0);// TODO 排名后加
			workRatioGm.setNumOrderRank(0);// TODO 排名后加
			workRatioGm.setNumReadRank(0);// TODO 排名后加
			workRatioGm.setNumVisitRank(0L);// TODO 排名后加
			workRatioGm.setRatioWorkRankYtd(0);// TODO 排名后加
			workRatioGm.setNumPmRankYtd(0);// TODO 排名后加
			workRatioGm.setNumPmAddRankYtd(0);// TODO 排名后加
			workRatioGm.setNumSaleRankYtd(0);// TODO 排名后加
			workRatioGm.setNumOrderRankYtd(0);// TODO 排名后加
			workRatioGm.setNumReadRankYtd(0);// TODO 排名后加
			workRatioGm.setNumVisitRankYtd(0L);// TODO 排名后加

			workRatioGm.setDaySt(DateUtils.getPreday(new Date()));
			workRatioGm.setDimensionSt(dimensionSt.toString());
			workRatioGm.setCreateDate(new Date());
			map.get(dimensionSt.toString()).put(dimCode, workRatioGm);
		}
	}
	
	/**
	 * 根据导购号查询当天客户跟进量
	 * 
	 * @param memberNo
	 * @param date
	 * @return
	 */
	/*private Long getNumPmCfDayByGm(String memberNo, Date date) {
		logger.debug("统计导购{}前一天客户跟进量", memberNo);
		Map<String, Object> map = DateUtil.getDayBeginAndEnd(date);
		map.put("memberNoGm", memberNo);
		return clientFollowService.findCountNumPmCfDayByGm(map);
	}*/
	
	/**
	 * 根据导购号查询当天客户跟进量
	 * 
	 * @param memberNo
	 * @param date
	 * @return
	 */
	/*private Long getNumCfDay(String memberNo, Date date) {
		logger.debug("统计导购{}前一天客户跟进次数", memberNo);
		Map<String, Object> map = DateUtil.getDayBeginAndEnd(date);
		map.put("memberNoGm", memberNo);
		return clientFollowService.findCountNumCfDayByGm(map);
	}*/

	/**
	 * 根据导购号查询跟维护客户数量
	 * 
	 * @param memberNo
	 * @return
	 */
	/*private long getNumPmKeep(String memberNo) {
		logger.debug("统计导购{}维护客户数", memberNo);
		return clientKeepSummaryService.findCountPmKeepByGm(memberNo);
	}*/

	/**
	 * 根据导购号查询跟进客户数量
	 * 
	 * @param memberNo
	 * @return
	 */
	/*private long getNumPmCf(String memberNo) {
		logger.debug("统计导购{}跟进客户数", memberNo);
		return clientFollowSummaryService.findCountPmCfByGm(memberNo);
	}*/

	/**
	 * 根据导购号获取前一天的客户到店体验数
	 * 
	 * @param memberNoGm
	 * @return
	 */
	/*private int getNumVisitBerDayByGm(String memberNoGm, Date date) {
		logger.debug("统计导购{}前一天客户到店体验数", memberNoGm);
		Map<String, Object> map = DateUtil.getDayBeginAndEnd(date);
		map.put("memberNoGm", memberNoGm);
		map.put("expResult", "Y");
		int numVisit = clientExpService.findCountVisitByGm(map);
		return numVisit;
	}*/
	
	/**
	 * 根据导购号获取前一天的客户到店体验数(理发)
	 * 
	 * @param memberNoGm
	 * @return
	 */
	/*private int getNumVisitBerDayByGmHc(String memberNoGm, Date date) {
		logger.debug("统计导购{}前一天客户到店体验数(理发)", memberNoGm);
		FindClientArrivePage param = new FindClientArrivePage();
		param.setMemberNoGm(memberNoGm);
		Map<String, Object> map = DateUtil.getDayBeginAndEnd(date);
		param.setArriveTimeBegin((Date) map.get("beginTime"));
		param.setArriveTimeEnd((Date) map.get("endTime")); 
		return clientArriveService.findClienArrivePageCount(param);
	}*/

	/**
	 * 根据导购编号获取导购前一天的社交任务完成量
	 * 
	 * @param memberNoGm
	 * @return
	 */
	/*private int getNumSocialBerDayByGm(String memberNoGm, Date date) {
		logger.debug("统计导购{}前一天社交任务完成量", memberNoGm);
		Map<String, Object> map = DateUtil.getDayBeginAndEnd(date);
		map.put("memberNoGm", memberNoGm);
		map.put("finish", "Y");
		int numSocial = socialTaskService.findCountSocialByGm(map);
		return numSocial;
	}*/

	/**
	 * 根据导购编号获取导购前一天的成单数量
	 * 
	 * @param memberNoGm
	 * @return
	 */
	/*private int getNumOrderByGm(String memberNoGm, Date date) {
		logger.debug("统计导购{}前一天成单数量", memberNoGm);
		Map<String, Object> map = DateUtil.getDayBeginAndEnd(date);
		map.put("memberNoGm", memberNoGm);
		int numOrder = clientFollowSummaryService.findCountOrderByGm(map);
		return numOrder;
	}*/
	
	/**
	 * 根据导购编号获取导购前一天的成单客户数量
	 * 
	 * @param memberNoGm
	 * @return
	 */
	/*private int getNumPmOrderByGm(String memberNoGm, Date date) {
		logger.debug("统计导购{}前一天成单客户数量", memberNoGm);
		Map<String, Object> map = DateUtil.getDayBeginAndEnd(date);
		map.put("memberNoGm", memberNoGm);
		int numOrder = clientFollowSummaryService.findCountPmOrderByGm(map);
		return numOrder;
	}*/

	/**
	 * 根据导购编号获取导购前一天的销售额
	 * 
	 * @param memberNo
	 * @return
	 */
	/*private long getNumSaleBerDayByGm(String memberNo, Date date) {
		logger.debug("统计导购{}前一天销售额", memberNo);
		Map<String, Object> map = DateUtil.getDayBeginAndEnd(date);
		map.put("memberNoGm", memberNo);
		long numSale = clientFollowSummaryService.findNumSaleByGm(map);
		return numSale;
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：统计导购前一天的销售情况（理发）：交易笔数、交易金额、成单客户数
	 *
	 * @param memberNo
	 * @param date
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月26日
	 *
	 */
	/*private FindConsumeAmtTotalReturn getTradeTotalByGmHc(String memberNo, Date date) {
		logger.debug("统计导购{}前一天的销售情况（理发）", memberNo);
		FindConsumeAmtTotal findConsumeAmtTotal = new FindConsumeAmtTotal();
		findConsumeAmtTotal.setMemberNoGm(memberNo);
		Map<String, Object> map = DateUtil.getDayBeginAndEnd(date);
		findConsumeAmtTotal.setServiceTimeBegin((Date) map.get("beginTime"));
		findConsumeAmtTotal.setServiceTimeEnd((Date) map.get("endTime")); 
		return clientConsumeService.findConsumeAmtTotal(findConsumeAmtTotal);
	}*/

	/**
	 * 统计客户类型的客户数量
	 * 
	 * @param findGuidMemberPageReturn
	 * @return
	 */
	/*private int getPmCountByType(String memberNoGm, PmTypeType pmTypeType) {
		logger.debug("统计导购{}客户的{}客户类型数量", memberNoGm, pmTypeType);
		FindPmType findPmType = new FindPmType();
		findPmType.setMemberNo(memberNoGm);
		findPmType.setPmTypeType(pmTypeType.toString());
		int numPmIntention = personMemberService.findCountPmByType(findPmType);
		return numPmIntention;
	}*/

	/**
	 * 根据导购号查询导购新增客户数量
	 * 
	 * @param memberNo
	 * @return
	 */
	private long getNumPmAddByGmDay(String memberNo, Date date) {
		logger.debug("统计导购{}前一天新增客户数量", memberNo);
		return personMemberService.findCountPmAddByGmDay(memberNo, date);
	}

	/**
	 * 根据导购号查询导购客户数量
	 * 
	 * @param findGuidMemberPageReturn
	 * @return
	 */
	private long getNumPmByGm(String merchantNo, String memberNo) {
		logger.debug("统计导购{}前一天客户数量", memberNo);
		return (long) personMemberService.findCountByMemberNoGm(merchantNo,memberNo);
	}

	/**
	 * 根据导购号和时间获取工作完成度积分
	 * 
	 * @param findGuidMemberPageReturn
	 * @return
	 */
	private double getRatioWorkByGMDay(String memberNo, Date date) {
		logger.debug("统计导购{}前一天工作完成度积分", memberNo);
		FindGuidMemberIntegralDay findGuidMemberIntegralDay = new FindGuidMemberIntegralDay();
		findGuidMemberIntegralDay.setMemberNo(memberNo);
		findGuidMemberIntegralDay.setDaySt(date);
		List<FindGuidMemberIntegralDayReturn> list = guidMemberIntegralDayService
				.findByGMDayList(findGuidMemberIntegralDay);
		double ratioWork = 0;
		for (FindGuidMemberIntegralDayReturn findGuidMemberIntegralDayReturn : list) {
			ratioWork += findGuidMemberIntegralDayReturn.getIntegralScore();
		}
		return ratioWork;
	}

	@Override
	public void generatorWorkRatioShop(Date date) throws TsfaServiceException {
		logger.debug("generatorWorkRatioShop---------start");
		try {
			List<FindMerchantPageReturn> merchantList = merchantService.findAllMerchant();
			if (CollectionUtils.isEmpty(merchantList)) {
				return;
			}
			// 将商户列表按商户编号存入MAP
			Map<String, FindMerchantPageReturn> merchantMap = new HashMap<String, FindMerchantPageReturn>();
			for (FindMerchantPageReturn merchant : merchantList) {
				merchantMap.put(merchant.getMerchantNo(), merchant);
			}
			
			querySaveWorkRatioShop(DimensionSt.MERCHANT.toString(), date, merchantMap);
			querySaveWorkRatioShop(DimensionSt.SHOP.toString(), date, merchantMap);
			querySaveWorkRatioShop(DimensionSt.AREA.toString(), date, merchantMap);
		} catch (Exception e) {
			logger.error("生成门店工作统计错误！", e);
			throw new TsfaServiceException(ErrorCode.WORK_RATIO_SHOP_ADD_ERROR,
					"新增门店工作完成度统计信息错误！", e);
		}
		logger.debug("generatorWorkRatioShop---------end");
	}

	private void querySaveWorkRatioShop(String dimensionSt, Date date, Map<String, FindMerchantPageReturn> merchantMap) {
		Map<String, Object> map = new HashMap<>();
		map.put("dimensionSt", dimensionSt);
		map.put("date", date);
		List<WorkRatioGm> workRatioGmList = workRatioGmService
				.findWorkRatioByDimDay(map);
		for (WorkRatioGm workRatioGm : workRatioGmList) {
			AddWorkRatioShop addWorkRatioShop = new AddWorkRatioShop();
			addWorkRatioShop.setMerchantNo(workRatioGm.getMerchantNo());
			addWorkRatioShop.setShopNo(workRatioGm.getShopNo());
			addWorkRatioShop.setShopName(workRatioGm.getShopName());
//			FindShop findShop = new FindShop();
//			findShop.setShopNo(workRatioGm.getShopNo());
 
			if (DimensionSt.SHOP.toString().equals(dimensionSt)) {//当维度为门店时才需要设置
				List<ManagerMemberReturnDto> managerList = managerMemberService.findManagerMemberByShop(workRatioGm.getShopNo());
				if (managerList.size() > 0) {
					
					addWorkRatioShop.setMemberNoSp(managerList.get(0).getMemberNo());
					addWorkRatioShop.setMemberNameSp(managerList.get(0).getMemberName());
					addWorkRatioShop.setHeadAddress(managerList.get(0).getHeadAddress());
				}
//				FindShopReturn findShopReturn = null;
//				try {
//					findShopReturn = shopService.findShopByShopNo(findShop);
//				} catch (Exception e) {
//					logger.info("未查询到门店号{}", workRatioGm.getShopNo());
//					continue;
//				}
//				if (findShopReturn != null) {
//				    addWorkRatioShop.setLogoAddr(findShopReturn.getLogoAddr());
//	                
//	                addWorkRatioShop.setProvinceCode(findShopReturn.getProvinceCode());
//	                addWorkRatioShop.setCityCode(findShopReturn.getCityCode());
//	                addWorkRatioShop.setCityAreaCode(findShopReturn.getCityAreaCode());
//	                
//	                addWorkRatioShop.setOpenDate(findShopReturn.getOpenDate());// 开店日期
//                }
			}
			
			addWorkRatioShop.setAreaCode(workRatioGm.getAreaCode());

			addWorkRatioShop.setNumOrder(workRatioGm.getNumOrder() == null ? 0 : workRatioGm.getNumOrder());
			addWorkRatioShop.setNumPmOrder(workRatioGm.getNumPmOrder() == null ? 0 : workRatioGm.getNumPmOrder());
			addWorkRatioShop.setNumPmAdd(workRatioGm.getNumPmAdd());
			addWorkRatioShop.setNumPm(workRatioGm.getNumPm() == null ? 0 : workRatioGm.getNumPm());

			addWorkRatioShop.setNumPmCf(workRatioGm.getNumPmCf());// 跟进客户数量
			addWorkRatioShop.setNumPmKeep(workRatioGm.getNumPmKeep());// 维护客户数量
			addWorkRatioShop.setNumPmAbandon(workRatioGm.getNumPmAbandon());
			addWorkRatioShop.setNumPmIntention(workRatioGm.getNumPmIntention() == null ? 0 : workRatioGm.getNumPmIntention());
			addWorkRatioShop.setNumSale(workRatioGm.getNumSale());
			
			/**
			 * update by 曾垂瑜 2017-09-27 
			 * 修改成单率算法：
			 * 1、非邀约型商户成单率 = 成单数 / 总客户数
			 * 2、邀约型商户成单率 = 成单数 / （意向到店客户数 + 当日成单客户数）
			 */
			// 查询导购所属商户
			FindMerchantPageReturn merchant = merchantMap.get(workRatioGm.getMerchantNo());
			// 非邀约型商户成单率 = 成单数 / 总客户数
			if(merchant != null && ProductType.NOINVITE.name().equals(merchant.getProductType())) {
				if(workRatioGm.getNumPm() == 0) {
					addWorkRatioShop.setOrderRate(0D);
				} else {
					addWorkRatioShop.setOrderRate(((double) (addWorkRatioShop.getNumOrder()) / addWorkRatioShop.getNumPm()));// 订单成交率
				}
			} else {	// 邀约型商户成单率 = 成单数 / （意向到店客户数 + 当日成单客户数）
				if(addWorkRatioShop.getNumPmIntention() == 0 && addWorkRatioShop.getNumPmOrder() == 0) {
					addWorkRatioShop.setOrderRate(0D);
				} else {
					addWorkRatioShop.setOrderRate(((double) (addWorkRatioShop.getNumOrder()) / (addWorkRatioShop.getNumPmIntention() + addWorkRatioShop.getNumPmOrder())));// 订单成交率
				}
			}

			addWorkRatioShop.setNumOrderRank(0);// TODO 订单排名
			addWorkRatioShop.setNumPmAddRank(0);// TODO 新增客户排名
			addWorkRatioShop.setNumPmRank(0);// TODO 客户数量排名
			addWorkRatioShop.setNumPmCfRank(0);// TODO 跟进客户数量排名
			addWorkRatioShop.setNumPmKeepRank(0);// TODO 维护客户数量排名
			addWorkRatioShop.setNumPmAbandonRank(0);// TODO 放弃客户数量排名
			addWorkRatioShop.setNumSaleRank(0);// TODO 销售额排名
			addWorkRatioShop.setNumOrderRankYtd(0);// TODO 成单数排名昨日
			addWorkRatioShop.setNumPmAddRankYtd(0);// TODO 新增客户排名昨日
			addWorkRatioShop.setNumPmRankYtd(0);// TODO 客户数量排名昨日
			addWorkRatioShop.setNumPmCfRankYtd(0);// TODO 跟进客户数量排名昨日
			addWorkRatioShop.setNumPmKeepRankYtd(0);// TODO 维护客户数量排名昨日
			addWorkRatioShop.setNumPmAbandonRankYtd(0);// TODO 放弃客户数量排名昨日
			addWorkRatioShop.setNumSaleRankYtd(0);// TODO 销售额排名 昨日

			addWorkRatioShop.setDaySt(workRatioGm.getDaySt());
			addWorkRatioShop.setDimensionSt(dimensionSt);
			addWorkRatioShop.setCreateDate(new Date());// 创建日期
			workRatioShopService.addWorkRatioShop(addWorkRatioShop);
		}
	}

	/**
	 * 生产客户到店体验报表
	 * update by 曾垂瑜 2017-09-26 根据商户所属产品类型，区分邀约型或非邀约型，按不同的表进行统计
	 */
	@Override
	public void generatorClientExpTotal(Date date) throws TsfaServiceException {
		logger.debug("generatorClientExpTotal---------start");
		try {
			List<FindMerchantPageReturn> merchantList = merchantService.findAllMerchant();
			if(CollectionUtils.isEmpty(merchantList)) {
				return;
			}
			// 将商户列表按商户编号存入MAP
			Map<String, FindMerchantPageReturn> merchantMap = new HashMap<String, FindMerchantPageReturn>();
			for(FindMerchantPageReturn merchant : merchantList) {
				merchantMap.put(merchant.getMerchantNo(), merchant);
			}

			int currentPage = 1;// 当前页
			int limit = 1000;// 每页数量
			
			Page<FindGuidMemberPageReturn> guidMemberPage = guidMemberService.findGuidMemberPage(currentPage, limit);
			int realSize = guidMemberPage.getRows().size();// 当前数据总数

			Date stDate = DateUtils.getPreday(new Date());
			int numAdd = 0;	// 到店数量
			for (; realSize > 0; realSize = guidMemberPage.getRows().size()) {
				for (FindGuidMemberPageReturn findGuidMemberPageReturn : guidMemberPage.getRows()) {
					// 查询导购所属商户
					FindMerchantPageReturn merchant = merchantMap.get(findGuidMemberPageReturn.getMerchantNo());
					// 非邀约型商户按到店表CLIENT_ARRIVE进行统计
					/*if(merchant != null && ProductType.NOINVITE.name().equals(merchant.getProductType())) {
						FindClientArrivePage findClientArrivePage = new FindClientArrivePage();
						findClientArrivePage.setMemberNoGm(findGuidMemberPageReturn.getMemberNo());
						Map<String, Object> dateMap = DateUtil.getDayBeginAndEnd(date);
						findClientArrivePage.setArriveTimeBegin((Date) dateMap.get("beginTime"));
						findClientArrivePage.setArriveTimeEnd((Date) dateMap.get("endTime"));
						numAdd = clientArriveService.findClienArrivePageCount(findClientArrivePage);
					} else {*/	// 邀约型商户按客户体验表CLIENT_EXP进行统计
						// 获取昨天所有客户体验数据
						/*FindClientExpPage findClientExpPage = new FindClientExpPage();
						findClientExpPage.setMemberNoGm(findGuidMemberPageReturn.getMemberNo());
						Map<String, Object> dateMap = DateUtil.getDayBeginAndEnd(date);
						findClientExpPage.setStartTime(DateUtils.formatDate((Date) dateMap.get("beginTime"), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
						findClientExpPage.setEndTime(DateUtils.formatDate((Date) dateMap.get("endTime"), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
						numAdd = clientExpService.findClientExpPageCount(findClientExpPage);*/
//					}

					// 添加统计记录
					AddClientExpTotal addClientExpTotal = new AddClientExpTotal();
					addClientExpTotal.setMerchantNo(findGuidMemberPageReturn.getMerchantNo());
//					addClientExpTotal.setShopNo(findGuidMemberPageReturn.getShopNo());
//					addClientExpTotal.setShopName(findGuidMemberPageReturn.getShopName());
					addClientExpTotal.setMemberNoGm(findGuidMemberPageReturn.getMemberNo());
					addClientExpTotal.setMemberNameGm(findGuidMemberPageReturn.getMemberName());
					addClientExpTotal.setNumAdd(numAdd);
					addClientExpTotal.setStDate(stDate);
					clientExpTotalService.insertSelective(addClientExpTotal);
				}
				guidMemberPage = guidMemberService.findGuidMemberPage(++currentPage, limit);
			}
		} catch (Exception e) {
			logger.error("生成到店体验统计错误！", e);
			throw new TsfaServiceException(
					ErrorCode.CLIENT_EXP_TOTAL_ADD_ERROR, "生成到店体验统计错误！", e);
		}
		logger.debug("generatorClientExpTotal---------end");
	}

	@Override
	public void generatorEmployeeAnalyze() throws TsfaServiceException {
		logger.debug("generatorEmployeeAnalyze---------start");
		// 商户维度
		try {
			// 获取所有商户
			List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
			for (FindMerchantPageReturn findMerchantPageReturn : merchants) {

				// 获取导购总数
				FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
				findGuidMemberPage.setMerchantNo(findMerchantPageReturn.getMerchantNo());
				int guidTotle = guidMemberService.findGuidMemberCount(findGuidMemberPage);

				// 获取管理人员总数   导购表中包含店长信息
				FindManagerMemberPage findManagerMemberPage = new FindManagerMemberPage();
				findManagerMemberPage.setMemberNoMerchant(findMerchantPageReturn.getMerchantNo());
				findManagerMemberPage.setMemberType(MemberType.AREA_MAN.toString());
				int manageTotle = managerMemberService
						.findManagerMemberCount(findManagerMemberPage);

				// 员工总数=导购+管理人员 
				long numEmployee = guidTotle + manageTotle;
				
				// 获取20-29员工数和占比
				Map<String, Object> mapTwenty = calcAge(20, 29, numEmployee,
						findGuidMemberPage, findManagerMemberPage);
				int numAgeTwenty = Integer.valueOf(mapTwenty.get("numAge")
						.toString());
				double ratioAgeTwenty = Double.valueOf(mapTwenty
						.get("ratioAge").toString());

				// 获取30-39员工数和占比
				Map<String, Object> mapThirty = calcAge(30, 39, numEmployee,
						findGuidMemberPage, findManagerMemberPage);
				int numAgeThirty = Integer.valueOf(mapThirty.get("numAge")
						.toString());
				double ratioAgeThirty = Double.valueOf(mapThirty
						.get("ratioAge").toString());

				// 获取40-49员工数和占比
				Map<String, Object> mapForty = calcAge(40, 49, numEmployee,
						findGuidMemberPage, findManagerMemberPage);
				int numAgeForty = Integer.valueOf(mapForty.get("numAge")
						.toString());
				double ratioAgeForty = Double.valueOf(mapForty.get("ratioAge")
						.toString());

				// 获取50-59员工数和占比
				Map<String, Object> mapFifty = calcAge(50, 59, numEmployee,
						findGuidMemberPage, findManagerMemberPage);
				int numAgeFifty = Integer.valueOf(mapFifty.get("numAge")
						.toString());
				double ratioAgeFifty = Double.valueOf(mapFifty.get("ratioAge")
						.toString());

				AddEmployeeAnalyze addEmployeeAnalyze = new AddEmployeeAnalyze();
				addEmployeeAnalyze.setDimensionSt(DimensionSt.MERCHANT
						.toString());
				addEmployeeAnalyze.setMerchantNo(findMerchantPageReturn
						.getMerchantNo());
				addEmployeeAnalyze.setNumEmployee(numEmployee);
				addEmployeeAnalyze.setNumAgeTwenty(numAgeTwenty);
				addEmployeeAnalyze.setRatioAgeTwenty(ratioAgeTwenty);
				addEmployeeAnalyze.setNumAgeThirty(numAgeThirty);
				addEmployeeAnalyze.setRatioAgeThirty(ratioAgeThirty);
				addEmployeeAnalyze.setNumAgeForty(numAgeForty);
				addEmployeeAnalyze.setRatioAgeForty(ratioAgeForty);
				addEmployeeAnalyze.setNumAgeFifty(numAgeFifty);
				addEmployeeAnalyze.setRatioAgeFifty(ratioAgeFifty);
				addEmployeeAnalyze.setStDate(EndDate);
				employeeAnalyzeService.addEmployeeAnalyze(addEmployeeAnalyze);
			}
		} catch (Exception e) {
			logger.error("生成员工画像信息错误！", e);
			throw new TsfaServiceException(
					ErrorCode.EMPLOYEE_ANALYZE_ADD_ERROR, "生成员工画像信息错误！", e);
		}
		logger.debug("generatorEmployeeAnalyze---------end");
	}
	
	// 跟进门店维度
	public void generatorCfCountAnalyze() throws TsfaServiceException {
		logger.debug("cfCountAnalyze---------end");
		try {
			// 获取所有商户
			List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
			for (FindMerchantPageReturn findMerchantPageReturn : merchants) {
//				FindShopDto findShopDto = new FindShopDto();
//				findShopDto.setMemberNoMerchant(findMerchantPageReturn.getMerchantNo());
//				List<FindShopReturnAreaCode> findShopReturnAreaCodes = shopService.selectByAreaCode(findShopDto);
//				for (FindShopReturnAreaCode findShopReturnAreaCode : findShopReturnAreaCodes) {
//					FindClientFollowMap findClientFollowMap = new FindClientFollowMap();
//					findClientFollowMap.setMerchantNo(findShopReturnAreaCode.getMemberNoMerchant());
//					 findClientFollowMap.setShopNo(findShopReturnAreaCode.getShopNo());
					 //总数
//				    long sum=calcFollowSum(findClientFollowMap);
					// 获取1-5次的跟踪次数与占比
//					Map<String, Object> mapFive = calcFollowNum(1, 5,findClientFollowMap);
//					int numCfFive = Integer.valueOf(mapFive.get("count").toString());
//					double ratioCfFive = sum == 0L ? 0.0 : Double.valueOf(numCfFive) / sum;
					
					// 获取6-10次的跟踪次数与占比
//					Map<String, Object> mapTen = calcFollowNum(6, 10,findClientFollowMap);
//					int numCfTen = Integer.valueOf(mapTen.get("count").toString());
//					double ratioCfTen = sum == 0L ? 0.0 : Double.valueOf(numCfTen) / sum;
					
					// 获取10-15次的跟踪次数与占比
//					Map<String, Object> mapFifteen = calcFollowNum(10, 15,findClientFollowMap);
//					int numCfFifteen = Integer.valueOf(mapFifteen.get("count").toString());
//					double ratioCfFifteen = sum == 0L ? 0.0 : Double.valueOf(numCfFifteen) / sum;
					
					// 获取16以上次的跟踪次数与占比
//					Map<String, Object> mapSixteen = calcFollowNum(16, 0,findClientFollowMap);
//					int numCfSixteen = Integer.valueOf(mapSixteen.get("count").toString());
//					double ratioCfSixteen = sum == 0L ? 0.0 : Double.valueOf(numCfSixteen) / sum;
					
//					AddCfCountAnalyze addCfCountAnalyze = new AddCfCountAnalyze();
//					addCfCountAnalyze.setMerchantNo(findShopReturnAreaCode.getMemberNoMerchant());
//					addCfCountAnalyze.setShopNo(findShopReturnAreaCode.getShopNo());
//					addCfCountAnalyze.setShopName(findShopReturnAreaCode.getShopName());
//					addCfCountAnalyze.setAreaCode(findShopReturnAreaCode.getAreaCode());
//					addCfCountAnalyze.setAreaName(findShopReturnAreaCode.getAreaName());
//					addCfCountAnalyze.setNumCf(sum);
//					addCfCountAnalyze.setRatioCfFive(ratioCfFive);
//					addCfCountAnalyze.setNumCfFive(numCfFive);
//					addCfCountAnalyze.setRatioCfTen(ratioCfTen);
//					addCfCountAnalyze.setNumCfTen(numCfTen);
//					addCfCountAnalyze.setRatioCfFifteen(ratioCfFifteen);
//					addCfCountAnalyze.setNumCfFifteen(numCfFifteen);
//					addCfCountAnalyze.setRatioCfSixteen(ratioCfSixteen);
//					addCfCountAnalyze.setNumCfSixteen(numCfSixteen);
//					addCfCountAnalyze.setStDate(EndDate);
//					addCfCountAnalyze.setCreateDate(new Date());
//					countAnalyzeService.addCfCountAnalyze(addCfCountAnalyze);
//				}
			}
			
		} catch (Exception e) {
			logger.error("新增跟进次数分析表信息错误！", e);
			throw new TsfaServiceException(
					ErrorCode.CF_COUNT_ANALYZE_ADD_ERROR, "新增跟进次数分析表信息错误！", e);
		}
		logger.debug("cfCountAnalyze---------end");
	}

	/**
	 * 
	 *
	 * 方法说明：返回跟进次数和占比与总数
	 *
	 * @param followNumFrom
	 * @param followNumTo
	 * @param findClientFollowMap
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月3日
	 *
	 */
//	private Map<String, Object> calcFollowNum(int followNumFrom,
//			int followNumTo/*, FindClientFollowMap findClientFollowMap*/) {
//		int count = 0;
//		if (StringUtils.isNotEmpty(findClientFollowMap.getShopNo())) {
//			FindShopGmDto findShopGmDto = new FindShopGmDto();
//			findShopGmDto.setMerchantNo(findClientFollowMap.getMerchantNo());
//			findShopGmDto.setShopNo(findClientFollowMap.getShopNo());
//			List<FindShopGmReturn> findShopGm = guidMemberService.findShopGmPy(findShopGmDto);
//			for (FindShopGmReturn findShopGmReturn : findShopGm) {
//				findClientFollowMap.setMemberNoGm(findShopGmReturn.getMemberNo());
//				findClientFollowMap.setFollowNumFrom(followNumFrom);
//				findClientFollowMap.setFollowNumTo(followNumTo);
////				count += clientFollowService.findClientFollowCount(findClientFollowMap);
//			}
//		}
//		Map<String, Object> returnMap = new HashMap<>();
//		returnMap.put("count", count);
//		return returnMap;
//	}
	/**
	 * 
	 *
	 * 方法说明：总次数
	 *
	 * @param followNumFrom
	 * @param followNumTo
	 * @param findClientFollowMap
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月9日
	 *
	 */
	/*private long  calcFollowSum(FindClientFollowMap findClientFollowMap) {
		long sum = 0;
		if (StringUtils.isNotEmpty(findClientFollowMap.getShopNo())) {
			FindShopGmDto findShopGmDto = new FindShopGmDto();
			findShopGmDto.setMerchantNo(findClientFollowMap.getMerchantNo());
			findShopGmDto.setShopNo(findClientFollowMap.getShopNo());
			List<FindShopGmReturn> findShopGm = guidMemberService.findShopGmPy(findShopGmDto);
			for (FindShopGmReturn findShopGmReturn : findShopGm) {
				findClientFollowMap.setMemberNoGm(findShopGmReturn.getMemberNo());
				 sum += clientFollowService.findClientFollowSum(findClientFollowMap);
			}
		}
		return sum;
	}*/

	/**
	 *
	 *
	 * 方法说明：返回年龄段员工数和占比
	 *
	 * @param ageFrom
	 * @param ageTo
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月2日
	 *
	 */
	private Map<String, Object> calcAge(int ageFrom, int ageTo,
			long numEmployee, FindGuidMemberPage findGuidMemberPage,
			FindManagerMemberPage findManagerMemberPage) {

		// 设置导购年龄段
		findGuidMemberPage.setAgeFrom(ageFrom);
		findGuidMemberPage.setAgeTo(ageTo);
		int guid = guidMemberService.findGuidMemberCount(findGuidMemberPage);

		// 设置管理人员年龄段  导购表中包含店长信息
		findManagerMemberPage.setAgeFrom(ageFrom);
		findManagerMemberPage.setAgeTo(ageTo);
		findManagerMemberPage.setMemberType(MemberType.AREA_MAN.toString());
		int manage = managerMemberService
				.findManagerMemberCount(findManagerMemberPage);

		// 年龄段员工数
		int numAge = guid + manage;
		// 年龄段员工占比
		Double ratioAge = numEmployee == 0L ? 0.0 : Double.valueOf(numAge)
				/ numEmployee;

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("numAge", numAge);
		returnMap.put("ratioAge", ratioAge);
		return returnMap;
	}

	public void generatorCfAnalyze(Date date) throws TsfaServiceException {
		FindTextInfo findTextInfo = new FindTextInfo();
		FindPmTalkTotal findPmTalkTotal = new FindPmTalkTotal();
		FindPmTypeTotal findPmTypeTotal = new FindPmTypeTotal();
		FindSocialAnalyze findSocialAnalyze = new FindSocialAnalyze();
		FindMaterialTotal findMaterialTotal = new FindMaterialTotal();
		FindPmLabelTotal findPmLabelTotal = new FindPmLabelTotal();
		Map<String, Object> map = new HashMap<>();
		try {
			List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
			for (FindMerchantPageReturn findMerchantPageReturn : merchants) {
				FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
				findGuidMemberDto.setMerchantNo(findMerchantPageReturn.getMerchantNo());
				
				List<GuidInfoShop> guidInfoShops = guidMemberService.findGuidInfoShop(findGuidMemberDto);
				for (GuidInfoShop list : guidInfoShops) {
					findPmLabelTotal.setMerchantNo(list.getMerchantNo());
					findPmLabelTotal.setMemberNoGm(list.getMemberNo());
					FindPmLabelTotalReturnDto pmLabelTotalReturnDtos = pmLabelTotalService.findPmLabelTotalMax(findPmLabelTotal);
					if(pmLabelTotalReturnDtos == null){
						pmLabelTotalReturnDtos = new FindPmLabelTotalReturnDto();
						pmLabelTotalReturnDtos.setPmNum(0);
						pmLabelTotalReturnDtos.setMerchantNo(list.getMerchantNo());
						pmLabelTotalReturnDtos.setMemberNoGm(list.getMemberNo());
						pmLabelTotalReturnDtos.setLabelName("未知");
					}
					
					findTextInfo.setTextType(TextType.CLIENT_ANALYZE.toString());
					findTextInfo.setCount(pmLabelTotalReturnDtos.getPmNum());
					findTextInfo.setMerchantNo(pmLabelTotalReturnDtos.getMerchantNo());
					FindTextInfoPageReturn pmLabelTotalConten = textInfoService.findTextInfoReturnContent(findTextInfo);
					String pltcontent = "";
					if(pmLabelTotalConten != null){
						pltcontent = pmLabelTotalConten.getContent();
					}
					
					if(pmLabelTotalReturnDtos.getPmNum() > 0){
						map.put("BriefClientAnalyze", "你有" + pmLabelTotalReturnDtos.getPmNum()
								+ "个客户" + "是" + pmLabelTotalReturnDtos.getLabelName() + "," + pltcontent);
					}else{
						map.put("BriefClientAnalyze", pltcontent);
					}
					logger.debug("获取客户标签信息统计");
					
					
					findPmTalkTotal.setMerchantNo(list.getMerchantNo());
					findPmTalkTotal.setMemberNoGm(list.getMemberNo());
					List<FindPmTalkTotalReturn> listTt = pmTalkTotalService.findPmTalkTotalListApp(findPmTalkTotal);
			        int maxTt = 0;
			        String startDate = "";
			        String endDate = "";
			        if (listTt !=null && listTt.size()>0) {
			        	maxTt = listTt.get(0).getNumTalk();
						startDate = listTt.get(0).getStartDate();
						endDate = listTt.get(0).getEndDate();
			        }
					
					findTextInfo.setTextType(TextType.CLIENT_BEHAVIOR.toString());
					findTextInfo.setCount(maxTt);
					findTextInfo.setMerchantNo(list.getMerchantNo());
					FindTextInfoPageReturn pmTalkTotalContent = textInfoService.findTextInfoReturnContent(findTextInfo);
					
					String ptlcontent = "";
					if(pmTalkTotalContent != null){
						ptlcontent = pmTalkTotalContent.getContent();
					}
				
					if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate) ){
						map.put("BriefClientAction", startDate + "-" + endDate+ "," + ptlcontent);
					}else{
						map.put("BriefClientAction", ptlcontent);
					}
					
					logger.debug("客户信息统计");

					
					String preDate =  DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd);
					findPmTypeTotal.setMerchantNo(list.getMerchantNo());
					findPmTypeTotal.setMemberNoGm(list.getMemberNo());
					findPmTypeTotal.setBeginDate(preDate);
					findPmTypeTotal.setEndDate(preDate);
					
					List<FindPmTypeTotalReturn> listPtt = pmTypeTotalService.findPmTypeTotalListApp(findPmTypeTotal);
					int maxPtt = 0;
					String rpm = "";
					String fl = "";
					if (listPtt != null && listPtt.size() > 0) {
						for (FindPmTypeTotalReturn findPmTypeTotalReturn : listPtt){
							if(maxPtt < findPmTypeTotalReturn.getNumPm()){
								maxPtt = findPmTypeTotalReturn.getNumPm();
								rpm = findPmTypeTotalReturn.getRatioPm();
//								fl = EnumUtils.getValue(PmTypeType.class, findPmTypeTotalReturn.getPmTypeType());
							}
						}
					}
					
					findTextInfo.setTextType(TextType.CLIENT_TYPE.toString());
					findTextInfo.setCount(maxPtt);
					findTextInfo.setMerchantNo(list.getMerchantNo());
					FindTextInfoPageReturn pmTypeTotalContent = textInfoService.findTextInfoReturnContent(findTextInfo);
					String pttcontent = "";
					if(pmTypeTotalContent != null){
						pttcontent = "," + pmTypeTotalContent.getContent();
					}
					
				
					if(!StringUtils.isEmpty(fl) && !StringUtils.isEmpty(rpm)){
						int ration = new BigDecimal(Double.valueOf(rpm)).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
						map.put("BriefClientType",fl + "客户占比"+ ration + "%" + pttcontent);
					}else{
						map.put("BriefClientType", pttcontent);
					}
					logger.debug("客户分类统计");

					
					findSocialAnalyze.setMerchantNo(list.getMerchantNo());
					findSocialAnalyze.setMemberNoGm(list.getMemberNo());
					findSocialAnalyze.setStDate(date);
					FindSocialAnalyzeReturn findSocialAnalyzeReturns = socialAnalyzeService.findSocialAnalyzeMax(findSocialAnalyze);
					
					if(findSocialAnalyzeReturns == null){
						findSocialAnalyzeReturns = new FindSocialAnalyzeReturn();
						findSocialAnalyzeReturns.setMerchantNo(list.getMerchantNo());
						findSocialAnalyzeReturns.setMemberNoGm(list.getMemberNo());
						findSocialAnalyzeReturns.setNumSocial(0);
					}
					findTextInfo.setTextType(TextType.SOCIAL_ANALYZE.toString());
					findTextInfo.setCount(findSocialAnalyzeReturns.getNumSocial());
					findTextInfo.setMerchantNo(list.getMerchantNo());
					FindTextInfoPageReturn socialAnalyzeContent = textInfoService.findTextInfoReturnContent(findTextInfo);
					String scontent = "";
					if(socialAnalyzeContent != null){
						scontent = socialAnalyzeContent.getContent();
					}
			
					map.put("BriefSocial", scontent);
					
					logger.debug("社交分析统计");

					findMaterialTotal.setMerchantNo(list.getMerchantNo());
					findMaterialTotal.setMemberNoGm(list.getMemberNo());
					
					List<FindMaterialTotalReturn> listMt = materialTotalService.findMaterialTotalApp(findMaterialTotal);
					int max = 0;
					String materialTypeName = "";
					if (listMt != null && listMt.size() > 0) {
						max = listMt.get(0).getRespondNum();
						materialTypeName = listMt.get(0).getMaterialTypeName();
					}
					
					findTextInfo.setTextType(TextType.MATERIAL_ANALYZE.toString());
					findTextInfo.setCount(max);
					findTextInfo.setMerchantNo(list.getMerchantNo());
					FindTextInfoPageReturn materialTotalContent = textInfoService.findTextInfoReturnContent(findTextInfo);
					if(!StringUtils.isEmpty(materialTypeName)){
						if(materialTotalContent != null){
							map.put("BriefMaterial",materialTypeName + "的素材" +  materialTotalContent.getContent());
						}else{
							map.put("BriefMaterial","");
						}
					}else{
						if(materialTotalContent != null){
							map.put("BriefMaterial",materialTotalContent.getContent());
						}else{
							map.put("BriefMaterial","");
						}
					}
			
					logger.debug("素材分析统计");

					AddCfAnalyze addCfAnalyze = new AddCfAnalyze();
					addCfAnalyze.setMerchantNo(findMerchantPageReturn.getMerchantNo());
					addCfAnalyze.setMemberNoGm(list.getMemberNo());
//					addCfAnalyze.setShopNo(list.getShopNo());
//					addCfAnalyze.setShopName(list.getShopName());
					addCfAnalyze.setMemberNameGm(list.getMemberName());
					if (map.get("BriefClientAction") != null) {
						addCfAnalyze.setBriefClientAction(map.get("BriefClientAction").toString());
					}
					if (map.get("BriefClientAnalyze") != null) {
						addCfAnalyze.setBriefClientAnalyze(map.get("BriefClientAnalyze").toString());
					}
					if (map.get("BriefClientType") != null) {
						addCfAnalyze.setBriefClientType(map.get("BriefClientType").toString());
					}
					if (map.get("BriefMaterial") != null) {
						addCfAnalyze.setBriefMaterial(map.get("BriefMaterial").toString());
					}
					if (map.get("BriefSocial") != null) {
						addCfAnalyze.setBriefSocial(map.get("BriefSocial").toString());
					}
					addCfAnalyze.setDaySt(DateUtils.getPreday(new Date()));
					addCfAnalyze.setDimensionSt("GUID");
					cfAnalyzeService.addCfAnalyze(addCfAnalyze);
				}
			}

		} catch (Exception e) {
			logger.error("新增跟进分析摘要表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CF_ANALYZE_ADD_ERROR,
					"新增跟进分析摘要表信息错误！", e);
		}

	}

	public void generatorPmLabelTotal() throws TsfaServiceException{
		   AddPmLabelTotal addPmLabelTotal=new AddPmLabelTotal();
		   try {
				List<FindPmLabelReturnList> guidList=pmLabelService.findPmlabelGuidMember();
				if(guidList !=null && guidList.size()>0){
					for(FindPmLabelReturnList findPmLabelReturnList:guidList){
						pmLabelTotalList(addPmLabelTotal, findPmLabelReturnList,DimensionSt.GUID.toString());	
					}
				}	
					 
	    	   	List<FindPmLabelReturnList> areaCodeList=pmLabelService.findPmlabelAreaCode();
				if(areaCodeList!=null&& areaCodeList.size()>0){
					for(FindPmLabelReturnList findPmLabelReturnList:areaCodeList){
						pmLabelTotalList(addPmLabelTotal, findPmLabelReturnList,DimensionSt.AREA.toString());	
					}
				}

				List<FindPmLabelReturnList> shopList=pmLabelService.findPmlabelShop();
   		        if(shopList!=null && shopList.size()>0){
   		        	for(FindPmLabelReturnList findPmLabelReturnList:shopList){
   		        		pmLabelTotalList(addPmLabelTotal, findPmLabelReturnList,DimensionSt.SHOP.toString());	
   		        	}
   		        }
				  
   		        List<FindPmLabelReturnList> merchantNoList=pmLabelService.findPmlabelMerchantNo();
   		        if(merchantNoList!=null&&merchantNoList.size()>0){
   		        	for(FindPmLabelReturnList findPmLabelReturnList:merchantNoList){
   		        		pmLabelTotalList(addPmLabelTotal, findPmLabelReturnList,DimensionSt.MERCHANT.toString());		
   		        	}
   		        }
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			logger.error("新增客户标签统计表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PM_LABEL_TOTAL_ADD_ERROR,
					"新增客户标签统计表信息错误！", e);
		}
    	  
	}
     /**
      * 
      *
      * 方法说明：新增客戶标签
      *
      * @param addPmLabelTotal
      * @param findPmLabelReturnList
      *
      * @author 罗书明 CreateDate: 2017年8月26日
      *
      */
	public void pmLabelTotalList(AddPmLabelTotal addPmLabelTotal,FindPmLabelReturnList findPmLabelReturnList,String dimensionSt) {
		  addPmLabelTotal.setCode(GUID.getPreUUID());
		  addPmLabelTotal.setMerchantNo(findPmLabelReturnList.getMerchantNo());
		  addPmLabelTotal.setMemberNoGm(findPmLabelReturnList.getMemberNoGm());
		  addPmLabelTotal.setMemberNameGm(findPmLabelReturnList.getMemberNameGm());
//		  addPmLabelTotal.setShopNo(findPmLabelReturnList.getShopNo());
//		  addPmLabelTotal.setShopName(findPmLabelReturnList.getShopName());
		  addPmLabelTotal.setLabelId(findPmLabelReturnList.getCode());
		  addPmLabelTotal.setLabelName(findPmLabelReturnList.getLabelName());
		  addPmLabelTotal.setPmNum(findPmLabelReturnList.getMemberNo());
		  addPmLabelTotal.setDimensionSt(dimensionSt);
		  pmLabelTotalService.addPmLabelTotal(addPmLabelTotal);
	}

	/**
	 * 
	 *
	 * 方法说明：素材中心统计
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 梅宏博 CreateDate: 2017年8月18日
	 *
	 */
	public void generatorMaterialTotal() throws TsfaServiceException {
		logger.debug("统计素材中心数据开始");
		try {
			
			// 获取所有商户
			List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
			for (FindMerchantPageReturn findMerchantPageReturn : merchants) {
				// 统计回应总量
				BigDecimal responseTotal = BigDecimal.valueOf(materialService.getMaterialResponseTotal(findMerchantPageReturn.getMerchantNo())
						+ materialCommenService.getMaterialCommentResponseTotal(findMerchantPageReturn.getMerchantNo()));
	
				responseTotal = responseTotal.compareTo(new BigDecimal(0)) > 0 ? responseTotal
						: new BigDecimal(1);// 防止除数为零
	
				List<GeneratorMaterialTotalReturn> totalReturn = materialService.generatorMaterialTotal(findMerchantPageReturn.getMerchantNo());// 统计导购素材总量
				List<GeneratorMaterialTotalReturn> commenTotalReturn = materialCommenService.generatorMaterialCommenTotal(findMerchantPageReturn.getMerchantNo());// 统计公共素材总量
	
				Map<String, Map<String, AddMaterialTotal>> map = new HashMap<>();
	
				for (GeneratorMaterialTotalReturn generatorMaterialTotalReturn : totalReturn) {// 获取导购材料的总数
					AddMaterialTotal addMaterialTotal = convertAddMaterialTotal(
							responseTotal, generatorMaterialTotalReturn);
					if (map.get(addMaterialTotal.getMemberNoGm()) == null) {
						map.put(addMaterialTotal.getMemberNoGm(),
								new HashMap<String, AddMaterialTotal>());
					}
					map.get(addMaterialTotal.getMemberNoGm()).put(
							addMaterialTotal.getMaterialTypeCode(),
							addMaterialTotal);
	
				}
	
				//查询该商户下的所有导购
				FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
				findGuidMemberPage.setMerchantNo(findMerchantPageReturn.getMerchantNo());
				List<FindGuidMemberPageReturn> guidMembers = guidMemberService.findGuidMembers(findGuidMemberPage);
				for (FindGuidMemberPageReturn findGuidMemberPageReturn : guidMembers) {
					for (GeneratorMaterialTotalReturn generatorMaterialTotalReturn : commenTotalReturn){
						
						if (map.containsKey(findGuidMemberPageReturn.getMemberNo())) {// 当前导购素材中包含该导购
						
							Map<String, AddMaterialTotal> gmMap = map.get(findGuidMemberPageReturn.getMemberNo());
							if (gmMap.get(generatorMaterialTotalReturn.getMaterialTypeCode()) == null) {//该导购素材中未包含该公共素材类型
								AddMaterialTotal addMaterialTotal = new AddMaterialTotal();
								addMaterialTotal.setCode(GUID.getPreUUID());
								addMaterialTotal.setMerchantNo(findGuidMemberPageReturn.getMerchantNo());
//								addMaterialTotal.setShopNo(findGuidMemberPageReturn.getShopNo());
//								addMaterialTotal.setShopName(findGuidMemberPageReturn.getShopName());
								addMaterialTotal.setMemberNoGm(findGuidMemberPageReturn.getMemberNo());
								addMaterialTotal.setMemberNameGm(findGuidMemberPageReturn.getMemberName());
								addMaterialTotal.setRespondNum(generatorMaterialTotalReturn.getRespondNum());
								BigDecimal responseNum = BigDecimal.valueOf(generatorMaterialTotalReturn.getRespondNum());
								addMaterialTotal.setRatioRespond(responseNum.divide(responseTotal, BigDecimal.ROUND_HALF_UP));
								addMaterialTotal.setDaySt(DateUtils.getPreday(new Date()));
								addMaterialTotal.setMaterialTypeCode(generatorMaterialTotalReturn.getMaterialTypeCode());
								addMaterialTotal.setMaterialTypeName(generatorMaterialTotalReturn.getMaterialTypeName());
								addMaterialTotal.setDimensionSt(DimensionSt.GUID.toString());
								addMaterialTotal.setCreateDate(new Date());
								gmMap.put(generatorMaterialTotalReturn.getMaterialTypeCode(), addMaterialTotal);
							} else {
								AddMaterialTotal addMaterialTotal = gmMap.get(generatorMaterialTotalReturn.getMaterialTypeCode());
								addMaterialTotal.setRespondNum(addMaterialTotal.getRespondNum() + generatorMaterialTotalReturn.getRespondNum());
								BigDecimal responseNum = BigDecimal.valueOf(addMaterialTotal.getRespondNum());
								addMaterialTotal.setRatioRespond(responseNum.divide(responseTotal, BigDecimal.ROUND_HALF_UP));
							}
						} else {// 当前导购素材中未包含该导购

							AddMaterialTotal addMaterialTotal = new AddMaterialTotal();
							addMaterialTotal.setCode(GUID.getPreUUID());
							addMaterialTotal.setMerchantNo(findGuidMemberPageReturn.getMerchantNo());
//							addMaterialTotal.setShopNo(findGuidMemberPageReturn.getShopNo());
//							addMaterialTotal.setShopName(findGuidMemberPageReturn.getShopName());
							addMaterialTotal.setMemberNoGm(findGuidMemberPageReturn.getMemberNo());
							addMaterialTotal.setMemberNameGm(findGuidMemberPageReturn.getMemberName());
							addMaterialTotal.setRespondNum(generatorMaterialTotalReturn.getRespondNum());
							BigDecimal responseNum = BigDecimal.valueOf(generatorMaterialTotalReturn.getRespondNum());
							addMaterialTotal.setRatioRespond(responseNum.divide(responseTotal, BigDecimal.ROUND_HALF_UP));
							addMaterialTotal.setDaySt(DateUtils.getPreday(new Date()));
							addMaterialTotal.setMaterialTypeCode(generatorMaterialTotalReturn.getMaterialTypeCode());
							addMaterialTotal.setMaterialTypeName(generatorMaterialTotalReturn.getMaterialTypeName());
							addMaterialTotal.setDimensionSt(DimensionSt.GUID.toString());
							addMaterialTotal.setCreateDate(new Date());
							if (map.get(findGuidMemberPageReturn.getMemberNo()) == null) {
								map.put(findGuidMemberPageReturn.getMemberNo(),
										new HashMap<String, AddMaterialTotal>());
							}
							map.get(findGuidMemberPageReturn.getMemberNo())
									.put(generatorMaterialTotalReturn
											.getMaterialTypeCode(),
											addMaterialTotal);
						}
					}
				}
				
				for (Entry<String, Map<String, AddMaterialTotal>> entry : map
						.entrySet()) {
					for (Entry<String, AddMaterialTotal> addMaterialTotalEntry : entry
							.getValue().entrySet()) {
						materialTotalService.addMaterialTotal(addMaterialTotalEntry
								.getValue());
					}
				}
			}
		} catch (Exception e) {
			logger.error("添加素材中心统计表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MATERIAL_TOTAL_ADD_ERROR,
					"添加素材中心统计表信息错误！", e);
		}
	}

	/**
	 * 组装添加素材统计数据
	 * 
	 * @param responseTotal
	 *            素材回应总数
	 * @param generatorMaterialTotalReturn
	 * @return
	 */
	private AddMaterialTotal convertAddMaterialTotal(BigDecimal responseTotal,
			GeneratorMaterialTotalReturn generatorMaterialTotalReturn) {
		AddMaterialTotal addMaterialTotal = new AddMaterialTotal();
		addMaterialTotal.setCode(GUID.getPreUUID());
		addMaterialTotal.setMerchantNo(generatorMaterialTotalReturn
				.getMerchantNo());
		FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
		findGuidMemberDto.setMemberNo(generatorMaterialTotalReturn.getMemberNoGm());
		GuidMemberReturnDto findGuid = guidMemberService.findGuid(findGuidMemberDto);
//		addMaterialTotal.setShopNo(findGuid.getShopNo());
//		addMaterialTotal.setShopName(findGuid.getShopName());
		addMaterialTotal.setMemberNoGm(generatorMaterialTotalReturn
				.getMemberNoGm());
		addMaterialTotal.setMemberNameGm(generatorMaterialTotalReturn
				.getMemberNameGm());
		addMaterialTotal.setRespondNum(generatorMaterialTotalReturn
				.getRespondNum());
		BigDecimal responseNum = BigDecimal
				.valueOf(generatorMaterialTotalReturn.getRespondNum());
		addMaterialTotal.setRatioRespond(responseNum.divide(responseTotal, BigDecimal.ROUND_HALF_UP));
		addMaterialTotal.setDaySt(DateUtils.getPreday(new Date()));
		addMaterialTotal.setMaterialTypeCode(generatorMaterialTotalReturn
				.getMaterialTypeCode());
		addMaterialTotal.setMaterialTypeName(generatorMaterialTotalReturn
				.getMaterialTypeName());
		addMaterialTotal.setDimensionSt(DimensionSt.GUID.toString());
		addMaterialTotal.setCreateDate(new Date());
		return addMaterialTotal;
	}

	
	
	public void generatorAreaOrderAnalyze() throws TsfaServiceException {
		FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
//		FindSuccessNum findSuccessNum = new FindSuccessNum();
		try {
			
			List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
			
			for (FindMerchantPageReturn findMerchantPageReturn : merchants) {
//				FindShopDto findShopDto = new FindShopDto();
//				findShopDto.setMemberNoMerchant(findMerchantPageReturn.getMerchantNo());
//				List<FindShopReturnAreaCode> list = shopService.findAreaCode(findShopDto);
				/*for (FindShopReturnAreaCode findShopReturnAreaCode : list) {
					findGuidMemberDto.setMerchantNo(findMerchantPageReturn.getMerchantNo());
					findGuidMemberDto.setAreaCode(findShopReturnAreaCode.getAreaCode());
					List<GuidInfoShop> guidInfoShops = guidMemberService.findGuidInfoShop(findGuidMemberDto);
					if(guidInfoShops.size()>0){
						GuidInfoShop areaName= guidInfoShops.get(0);
//						int count = getAreaNum(findSuccessNum,guidInfoShops);
						AddAreaOrderAnalyze addAreaOrderAnalyze = new AddAreaOrderAnalyze();
						addAreaOrderAnalyze.setCode(GUID.getPreUUID());
						addAreaOrderAnalyze.setMerchantNo(findMerchantPageReturn.getMerchantNo());
						addAreaOrderAnalyze.setAreaCode(findShopReturnAreaCode.getAreaCode());
						if(areaName.getAreaName() != null){
						  addAreaOrderAnalyze.setAreaName(areaName.getAreaName());
						}
						addAreaOrderAnalyze.setNumOrder(0L);
						areaOrderAnalyzeService.addAreaOrderAnalyze(addAreaOrderAnalyze);
					}
				}*/
			}
		} catch (Exception e) {
			logger.error("区域订单统计报错！", e);
			e.printStackTrace();
		}

	}
    /**
     * 方法说明：统计区域成单数
     * @param findSuccessNum
     * @param guidInfoShops
     * @return
     * @author 罗书明 CreateDate: 2017年11月23日
     */
	/*private int getAreaNum(FindSuccessNum findSuccessNum,List<GuidInfoShop> guidInfoShops) {
		int count =0;
		for (GuidInfoShop guidInfoShop : guidInfoShops) {
			findSuccessNum.setMemberNoGm(guidInfoShop.getMemberNo());
		    count += clientFollowSummaryService.findBuySuccessNum(findSuccessNum);
		}
		return count;
	}*/
	
	

	
	
	/**
	 * 
	 *
	 * 方法说明：运营分析
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年8月7日
	 *
	 */
	public void generatorOperationAnalysisDayBrief(Date date)
			throws TsfaServiceException {
		AddOperationAnalysisDayBrief addOperationAnalysisDayBrief = new AddOperationAnalysisDayBrief();
		FindAreaOrderAnalyze findAreaOrderAnalyze = new FindAreaOrderAnalyze();
		FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
//		FindShopDto findShopDto = new FindShopDto();
		FindPmTalkTotal findPmTalkTotal = new FindPmTalkTotal();
		FindPersonMember findPersonMember = new FindPersonMember();
//		FindClientFollowMap findClientFollowMap = new FindClientFollowMap();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		// 商户 维度
		try {
			List<FindMerchantPageReturn> merchants = merchantService
					.findMerchants(new FindMerchantPage());
			for (FindMerchantPageReturn findMerchantPageReturn : merchants) {
				findAreaOrderAnalyze.setMerchantNo(findMerchantPageReturn
						.getMerchantNo());
				findPersonMemberBase.setMerchantNo(findMerchantPageReturn
						.getMerchantNo());
//				findShopDto.setMemberNoMerchant(findMerchantPageReturn
//						.getMerchantNo());
				findPmTalkTotal.setMerchantNo(findMerchantPageReturn
						.getMerchantNo());
				findPersonMember.setMerchantNo(findMerchantPageReturn
						.getMerchantNo());
//				findClientFollowMap.setMerchantNo(findMerchantPageReturn
//						.getMerchantNo());

				List<FindAreaOrderAnalyzeReturn> findAreaOrderAnalyzeReturn = areaOrderAnalyzeService
						.findAreaCodeMaxNum(findAreaOrderAnalyze);

				List<FindPersonMemberBaseList> findPersonMemberBaseList = personMemberBaseService
						.findPersonMemberBaseMemberCount(findPersonMemberBase);

				List<FindPmTalkTotalAllReturnList> allReturnList = pmTalkTotalService
						.findPmTalkTotaReturnData(findPmTalkTotal);

				List<FindUrgentMbrPageReturn> mbrPageReturn = personMemberService
						.findPersonMemberSexCount(findPersonMember);

				String sex = null;
				if (mbrPageReturn.size() > 0) {
					sex = mbrPageReturn.get(0).getSex();
					if (Gender.MALE.toString().equals(sex)) {
						sex = Gender.MALE.getName();
					} else if (Gender.FEMALE.toString().equals(sex)) {
						sex = Gender.FEMALE.getName();
					} else {
						sex = Gender.UNKNOWN.getName();
					}
				}

				// 销售漏斗(每天成单)
//				findClientFollowMap.setCreateDate(date);
				
//				int countMemberNo = clientFollowService.findLClientFollowCountMemberNo(findClientFollowMap);

//				int countDeal = clientFollowService
//						.findLClientFollowCountDeal(findClientFollowMap);
				double order = 0.0;
//				if (countMemberNo != 0 && countDeal != 0) {
//					order = (double) (countDeal / countMemberNo);
//					order = order * 100;
//				} else {
//					order = 0.0;
//				}
				// 跟进分析(商户总跟进)
//				int followNum = clientFollowService.findClientFollowSum(findClientFollowMap);

//				int sumMemberNo = clientFollowService.findClientFollowSumMemberNo(findClientFollowMap);
				double sum = 0.0;
//				if (sumMemberNo != 0 && followNum != 0) {
//					sum = sumMemberNo / followNum;
//				} else {
//					sum = 0.0;
//				}
				if (findMerchantPageReturn.getMerchantNo() != null) {
					String merchantNo = findMerchantPageReturn.getMerchantNo();
					addOperationAnalysisDayBrief.setMerchantNo(merchantNo);
				}

				if (findAreaOrderAnalyzeReturn.size() > 0) {
					addOperationAnalysisDayBrief.setBriefOrder("主要分布在"
							+ findAreaOrderAnalyzeReturn.get(0).getAreaName());
				}

				if (allReturnList.size() > 0) {
					addOperationAnalysisDayBrief.setBriefClientAction("客户咨询高峰"
							+ sdf.format(allReturnList.get(0).getStartDate())
							+ "-"
							+ sdf.format(allReturnList.get(0).getEndDate()));
				}

				if (findPersonMemberBaseList.size() > 0) {
					addOperationAnalysisDayBrief.setBriefCaArea("客户主要分布在"
							+ findPersonMemberBaseList.get(0).getAreaName());
				}

				addOperationAnalysisDayBrief.setBriefSale("成单率" + order + "%");
				addOperationAnalysisDayBrief.setBriefClientAnalyze("客户主要以"
						+ sex + "为主");
				addOperationAnalysisDayBrief.setBriefCf("平均跟进" + sum + "次");
				addOperationAnalysisDayBrief
						.setDimensionSt(DimensionSt.MERCHANT.toString());
				addOperationAnalysisDayBrief.setDaySt(DateUtils.getPreday(new Date()));
				addOperationAnalysisDayBrief.setCreateDate(new Date());
				operationAnalysisDayBriefService
						.addOperationAnalysisDayBrief(addOperationAnalysisDayBrief);
			}
		} catch (Exception e) {
			logger.error("新增报表项目信息错误！", e);
			throw new TsfaServiceException(
					ErrorCode.OPERATION_ANALYSIS_DAY_BRIEF_ADD_ERROR,"新增报表项目信息错误！", e);
		}

	}

	public void generatorWorkRatioArea() throws TsfaServiceException {
		try {

			List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
			Map<String,Object> map = findAreas();//省接口
			for (FindMerchantPageReturn findMerchantPageReturn : merchants) {
				String merchantNo = findMerchantPageReturn.getMerchantNo();
//				FindShopDto findShopDto = new FindShopDto();
//				findShopDto.setMemberNoMerchant(merchantNo);

				/*List<FindShopReturnAreaCode> findShopReturnAreaCodes = shopService.findAreaCode(findShopDto);
				// 区域维度
				for (FindShopReturnAreaCode list : findShopReturnAreaCodes) {
					FindPersonMember findPersonMember=new FindPersonMember();
					FindUrgentMbrPage findUrgentMbrPage = new FindUrgentMbrPage();
					findUrgentMbrPage.setMerchantNo(merchantNo);
					int SumNumPm = personMemberService.findPersonMemberSums(findUrgentMbrPage);
					findPersonMember.setMerchantNo(list.getMemberNoMerchant());
					findPersonMember.setAreaCode(list.getAreaCode());
					long numPm=personMemberService.findPersonMemberCont(findPersonMember);
					double ratioPm = SumNumPm == 0L ? 0.0 : Double.valueOf(numPm)/ SumNumPm;
					FindShopDto findShopDtos = new FindShopDto();
					findShopDtos.setMemberNoMerchant(list.getMemberNoMerchant());
					int ShopSum = shopService.findShopCounts(findShopDtos);
					findShopDtos.setAreaCode(list.getAreaCode());
					int count = shopService.findShopCounts(findShopDtos);
					Double ratioShop = ShopSum == 0L ? 0.0 : Double.valueOf(count) / ShopSum;
					AddWorkRatioArea addWorkRatioArea = new AddWorkRatioArea();
					addWorkRatioArea.setMemberNoMerchant(list.getMemberNoMerchant());
					addWorkRatioArea.setAreaCode(list.getAreaCode());
					addWorkRatioArea.setAreaName(list.getAreaName());
					addWorkRatioArea.setNumShop(count);
					addWorkRatioArea.setRatioShop(ratioShop);
					addWorkRatioArea.setNumPm(numPm);
					addWorkRatioArea.setRatioPm(ratioPm);
					addWorkRatioArea.setStDate(DateUtils.getPreday(new Date()));
					addWorkRatioArea.setDimensionSt(DimensionSt.AREA.toString());
					workRatioAreaService.addWorkRatioArea(addWorkRatioArea);
				}*/

				// 省维度 findAreas()
//				List<FindShopReturnAreaCode> areaCodes = shopService.selectByProvinceCode(findShopDto);
				
				for(Entry<String, Object> entry : map.entrySet()){
					FindPersonMember findPersonMember=new FindPersonMember();
					//省查询门店   门店查询客户数     商户查总数    
					FindUrgentMbrPage findUrgentMbrPage = new FindUrgentMbrPage();
					findUrgentMbrPage.setMerchantNo(findMerchantPageReturn.getMerchantNo());
					int SumNumPm = personMemberService.findPersonMemberSums(findUrgentMbrPage);
					findPersonMember.setMerchantNo(merchantNo);
					findPersonMember.setProvinceCode(entry.getKey());
					//客户数
					long numPm=personMemberService.findPersonMemberCont(findPersonMember);
					Double ratioPm = SumNumPm == 0L ? 0.0 : Double.valueOf(numPm) / SumNumPm;

//					FindShopDto findShopDtos = new FindShopDto();
//					findShopDtos.setMemberNoMerchant(merchantNo);
					//门店总数
//					int ShopSum = shopService.findShopCounts(findShopDtos);
//					findShopDtos.setProvinceCode(entry.getKey());
                    //省维度门店数
//					int count = shopService.findShopCounts(findShopDtos);
//					Double ratioShop = ShopSum == 0L ? 0.0 : Double.valueOf(count) / ShopSum;
					AddWorkRatioArea addWorkRatioArea = new AddWorkRatioArea();
					addWorkRatioArea.setMemberNoMerchant(merchantNo);
				/*	addWorkRatioArea.setAreaCode(provinceCode.getAreaCode());
					addWorkRatioArea.setAreaName(provinceCode.getAreaName());*/
					addWorkRatioArea.setProvinceCode(entry.getKey());
                     //关联省
					/*Map<String, Object> map = findAreas();
					for (Entry<String, Object> entry : map.entrySet()) {
						if (provinceCode.getProvinceCode() != null && provinceCode.getProvinceCode().equals(entry.getKey())) {
							addWorkRatioArea.setProvinceName(entry.getValue().toString());
						}
					}*/
					addWorkRatioArea.setProvinceName(entry.getValue().toString());
//					addWorkRatioArea.setNumShop(count);
//					addWorkRatioArea.setRatioShop(ratioShop);
					addWorkRatioArea.setNumPm(numPm);
					addWorkRatioArea.setRatioPm(ratioPm);
					addWorkRatioArea.setStDate(DateUtils.getPreday(new Date()));
					addWorkRatioArea.setDimensionSt(DimensionSt.PROVINCE
							.toString());
					workRatioAreaService.addWorkRatioArea(addWorkRatioArea);
				}
			}
		} catch (Exception e) {
			logger.error("新增区域工作统计错误！", e);
			throw new TsfaServiceException(ErrorCode.SHOP_AREA_ADD_ERROR,
					"新增区域工作统计错误！", e);
		}

	}

	/**
	 * 
	 *
	 * 方法说明：所有省份信息
	 *
	 * @return code name
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年8月19日
	 *
	 */
	private Map<String, Object> findAreas() throws TsfaServiceException {
		List<Area> areas = areaHessianService.selectProvince();
		Map<String, Object> map = new HashMap<>();
		for (Area area : areas) {
			map.put(area.getCode(), area.getName());
		}
		return map;
	}

	/**
	 * @author liupei 未联系客户统计
	 * 
	 * update date 曾垂瑜 2017-09-25 区分导购所属商户，按产品类型进行不同维护的统计
	 */
	public void generatorUnContactAnalyze() throws TsfaServiceException {
		try {
			List<FindMerchantPageReturn> merchantList = merchantService.findAllMerchant();
			if(CollectionUtils.isEmpty(merchantList)) {
				return;
			}
			// 将商户列表按商户编号存入MAP
			Map<String, FindMerchantPageReturn> merchantMap = new HashMap<String, FindMerchantPageReturn>();
			for(FindMerchantPageReturn merchant : merchantList) {
				merchantMap.put(merchant.getMerchantNo(), merchant);
			}
			
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setStatus(Status.NORMAL.toString());
			List<FindGuidMemberPageReturn> guidMembers = guidMemberService.findGuidMembers(findGuidMemberPage);
			//查询所有的导购
			if(null != guidMembers && guidMembers.size() > 0){
				for(FindGuidMemberPageReturn guidMember : guidMembers){
					FindPersonMember personMember = new FindPersonMember();
					personMember.setMerchantNo(guidMember.getMerchantNo());
					personMember.setMemberNoGm(guidMember.getMemberNo());
					List<FindPersonMemberReturn> pmList = personMemberService.findPersonMemberByGm(personMember);
					
					if(!CollectionUtils.isEmpty(pmList)){
						// 查询导购所属商户
						FindMerchantPageReturn merchant = merchantMap.get(guidMember.getMerchantNo());
						// 非邀约型商户按：邀约、到店、新增成单进行统计
						if(merchant != null && ProductType.NOINVITE.name().equals(merchant.getProductType())) {
							logger.info("非邀约商户所属导购按：邀约、到店、新增成单进行统计：" + guidMember.getMemberNo());
							this.generatorUnContactAnalyzeByNoInvite(guidMember, pmList);
						} else { // 邀约型商户按：跟踪、维护进行统计
							logger.info("非邀约商户所属导购按：跟踪、维护进行统计：" + guidMember.getMemberNo());
							this.generatorUnContactAnalyzeByInvite(guidMember, pmList);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.SOCIAL_ANALYZE_ADD_ERROR,"未联系客户统计信息错误！",e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：非邀约型商户按：邀约、到店、新增成单进行统计未联系客户
	 *
	 * @param guidMember
	 * @param pmList
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月25日
	 *
	 */
	private void generatorUnContactAnalyzeByNoInvite(FindGuidMemberPageReturn guidMember, List<FindPersonMemberReturn> pmList) {
		int yizhouYiyue = 0;	// 一周到一月未联系数量
		int yiyueSanyue = 0;	// 一月到三月未联系数量
		int sanyueLiuyue = 0;	// 三月到六月未联系数量
		int liuyueEnd = 0;		// 半年未联系数量
		
		int unContactDays = 0;	// 未联系天数
		Date today = new Date();// 今天
		for(FindPersonMemberReturn pm : pmList){
			// 查询最后一次消费记录
//			FindClientConsumeReturn consume = clientConsumeService.findLastClientConsume(new FindLastClientConsume(pm.getMemberNoGm(), pm.getMemberNo()));
			int consumeDiffDays = 0;	// 距离最后一次消费的天数
//			if(consume != null) {		// 根据服务时间计算天数
//				consumeDiffDays = DateUtils.differentDays(consume.getServiceTime(), today);
//			}
			
			// 查询最后一次到店记录
//			FindClientArriveReturn arrive = clientArriveService.findLastClientArrive(new FindLastClientArrive(pm.getMemberNoGm(), pm.getMemberNo()));
			int arriveDiffDays = 0;		// 距离最后一次到店的天数
//			if(arrive != null) {		// 根据到店时间计算天数
//				arriveDiffDays = DateUtils.differentDays(arrive.getArriveTime(), today);
//			}
			
			// 查询最后一次邀约记录
//			FindClientInviteHcReturn invite = clientInviteHcService.findLastClientInviteHc(new FindLastClientInviteHc(pm.getMemberNoGm(), pm.getMemberNo()));
			int inviteDiffDays = 0;		// 距离最后一次邀约的天数
//			if(invite != null) {		// 根据预计到店时间计算天数
//				inviteDiffDays = DateUtils.differentDays(invite.getReachTime(), today);
//			}
			
			// 计算距离创建客户的天数
			int createDiffDays = DateUtils.differentDays(pm.getCreateDate(), today);
			
			// 计算未联系天数：取consumeDiffDays、arriveDiffDays、inviteDiffDays和createDiffDays的最小值
			unContactDays = consumeDiffDays > arriveDiffDays ? arriveDiffDays : consumeDiffDays;
			unContactDays = unContactDays > inviteDiffDays ? inviteDiffDays : unContactDays;
			unContactDays = unContactDays > createDiffDays ? createDiffDays : unContactDays;
			
			// 未联系天数统计相应维度的客户数
			if(unContactDays >= 180){
				liuyueEnd = liuyueEnd + 1;
			}else if(unContactDays >= 90){
				sanyueLiuyue = sanyueLiuyue + 1;
			}else if(unContactDays >= 30){
				yiyueSanyue = yiyueSanyue + 1;
			}else if(unContactDays >= 7){
				yizhouYiyue = yizhouYiyue + 1;
			}
		}
		
		// 设置导购未联系统计数据
		this.setUnContactTotal(guidMember, yizhouYiyue, yiyueSanyue, sanyueLiuyue, liuyueEnd);
	}
	
	/**
	 * 
	 *
	 * 方法说明：邀约型商户按：跟踪、维护进行统计未联系客户
	 *
	 * @param guidMember
	 * @param pmList
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月25日
	 *
	 */
	private void generatorUnContactAnalyzeByInvite(FindGuidMemberPageReturn guidMember, List<FindPersonMemberReturn> pmList) {
		int yizhouYiyue = 0;	// 一周到一月未联系数量
		int yiyueSanyue = 0;	// 一月到三月未联系数量
		int sanyueLiuyue = 0;	// 三月到六月未联系数量
		int liuyueEnd = 0;		// 半年未联系数量
		  
		//查询导购下客户的最近跟进和维护时间并统计不同时间段未联系客户个数
		for(FindPersonMemberReturn pm : pmList){
			int fallowDiffDays = -1;	// 距离最后一次跟踪的天数
			int keepDiffDays = -1;		// 距离最后一次维护的天数
			
			/*FindClientFollow findClientFollow = new FindClientFollow();
			findClientFollow.setMemberNo(pm.getMemberNo());
			findClientFollow.setMemberNoGm(pm.getMemberNameGm());
			FindClientFollowReturn findClientFollowReturn = clientFollowService.findClientFollowByGmAndMember(findClientFollow);
			*/
			Date now = new Date();
			Date lastFollowTime = null;
			Date lastContactTime = null;
			
//			if(null != findClientFollowReturn){
//				lastFollowTime = findClientFollowReturn.getFollowTime();
//			}
			
			/*AddClientKeep ck = new AddClientKeep();
			ck.setMemberNo(pm.getMemberNo());
			ck.setMemberNoGm(pm.getMemberNoGm());
			FindClientKeepReturn clientKeep = clientKeepService.findLastClientKeepByGmAndMember(ck);*/
			
			Date lastKeepTime = null;
//			if(clientKeep != null){
//				lastKeepTime = clientKeep.getKeepTime();
//			}
			
			if(lastFollowTime != null){
				fallowDiffDays = DateUtils.differentDays(lastFollowTime, now);
			}
			
			if(lastKeepTime != null){
				keepDiffDays = DateUtils.differentDays(lastKeepTime, now);
			}
			
			int betweenDays = 0;	// 未联系天数
			if(fallowDiffDays > keepDiffDays){
				betweenDays = keepDiffDays;
			}else {
				betweenDays= fallowDiffDays;
			}
			
			if(fallowDiffDays == -1 && keepDiffDays == -1){
				lastContactTime = pm.getCreateDate();
				betweenDays =  DateUtils.differentDays(lastContactTime, now);
			}
			
			if(betweenDays >= 180){
				liuyueEnd = liuyueEnd + 1;
			}else if(betweenDays >= 90){
				sanyueLiuyue = sanyueLiuyue + 1;
			}else if(betweenDays >= 30){
				yiyueSanyue = yiyueSanyue + 1;
			}else if(betweenDays >= 7){
				yizhouYiyue = yizhouYiyue + 1;
			}
			
		}
		
		// 设置导购未联系统计数据
		this.setUnContactTotal(guidMember, yizhouYiyue, yiyueSanyue, sanyueLiuyue, liuyueEnd);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：设置导购未联系统计数据
	 *
	 * @param guidMember
	 * @param yizhouYiyue
	 * @param yiyueSanyue
	 * @param sanyueLiuyue
	 * @param liuyueEnd
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月25日
	 *
	 */
	private void setUnContactTotal(FindGuidMemberPageReturn guidMember, int yizhouYiyue, int yiyueSanyue, int sanyueLiuyue, int liuyueEnd) {
		// 查询统计记录
		FindUnContactTotalInfo findUnContactTotalInfo = new FindUnContactTotalInfo();
		findUnContactTotalInfo.setMemberNoGm(guidMember.getMemberNo());
		FindUnContactTotalInfoReturn findUnContactTotalInfoReturn = unContactTotalService.findUnContactTotalInfo(findUnContactTotalInfo);
		
		// 找到则更新
		if(null != findUnContactTotalInfoReturn){
			UpdateUnContactTotal updateUnContactTotal = new UpdateUnContactTotal();
			updateUnContactTotal.setCode(findUnContactTotalInfoReturn.getCode());
			updateUnContactTotal.setMerchantNo(findUnContactTotalInfoReturn.getMerchantNo());
			updateUnContactTotal.setMemberNoGm(findUnContactTotalInfoReturn.getMemberNoGm());
			updateUnContactTotal.setMemberNameGm(findUnContactTotalInfoReturn.getMemberNameGm());
			updateUnContactTotal.setYizhouYiyue(yizhouYiyue);
			updateUnContactTotal.setYiyueSanyue(yiyueSanyue);
			updateUnContactTotal.setSanyueLiuyue(sanyueLiuyue);
			updateUnContactTotal.setLiuyueEnd(liuyueEnd);
			unContactTotalService.updateUnContactTotal(updateUnContactTotal);
		}else{	// 没找到则新增
			AddUnContactTotal addUnContactTotal = new AddUnContactTotal();
			addUnContactTotal.setCode(GUID.getPreUUID());
			addUnContactTotal.setMemberNoGm(guidMember.getMemberNo());
			addUnContactTotal.setMerchantNo(guidMember.getMerchantNo());
			addUnContactTotal.setDimensionSt(DimensionSt.GUID.toString());
			addUnContactTotal.setMemberNameGm(guidMember.getMemberName());
//			addUnContactTotal.setShopName(guidMember.getShopName());
//			addUnContactTotal.setShopNo(guidMember.getShopNo());
			
			addUnContactTotal.setYizhouYiyue(yizhouYiyue);
			addUnContactTotal.setYiyueSanyue(yiyueSanyue);
			addUnContactTotal.setSanyueLiuyue(sanyueLiuyue);
			addUnContactTotal.setSanyueLiuyue(sanyueLiuyue);
			addUnContactTotal.setLiuyueEnd(liuyueEnd);
			
			unContactTotalService.addUnContactTotal(addUnContactTotal);
		}
	}

	/**
	 * 社交数量统计
	 */
	@Override
	public void generatorSocialAnalyze(Date date) throws TsfaServiceException {// TODO 时间写在sql中
		/*try {
			List<FindSocialTaskSt> list = socialTaskService.findSocialTaskStByDay(date);
			if (null != list && list.size() > 0) {
				for (FindSocialTaskSt fsts : list) {
					AddSocialAnalyze addSocialAnalyze = new AddSocialAnalyze();
					addSocialAnalyze.setCode(GUID.getPreUUID());
					addSocialAnalyze.setMerchantNo(fsts.getMerchantNo());
					addSocialAnalyze.setShopNo(fsts.getShopNo());
					addSocialAnalyze.setShopName(fsts.getShopName());
					addSocialAnalyze.setMemberNoGm(fsts.getMemberNoGm());
					addSocialAnalyze.setMemberNameGm(fsts.getMemberNameGm());
					addSocialAnalyze.setMemberNo(fsts.getMemberNo());
					addSocialAnalyze.setMemberName(fsts.getMemberName());
					addSocialAnalyze.setStDate(fsts.getStDate());
					addSocialAnalyze.setDimensionSt(DimensionSt.GUID.toString());
					addSocialAnalyze.setNumSocial(fsts.getNumSocial());
					addSocialAnalyze.setCreateDate(new Date());
					socialAnalyzeService.addSocialAnalyze(addSocialAnalyze);
				}
			}
		} catch (Exception e) {
			logger.error("新增社交分析表信息错误", e);
			throw new TsfaServiceException(ErrorCode.SOCIAL_ANALYZE_ADD_ERROR,
					"新增社交分析表信息错误！", e);
		}*/
	}

	@Override
	public void generatorPmTalkTotal() throws TsfaServiceException {
		try {
//			FindShopDto findShopDto = new FindShopDto();
			AddPmTalkTotal addPmTalkTotal = new AddPmTalkTotal();

			List<FindMerchantPageReturn> merchants = merchantService
					.findMerchants(new FindMerchantPage());

			for (FindMerchantPageReturn findMerchantPageReturn : merchants) {
				FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
				findGuidMemberDto.setMerchantNo(findMerchantPageReturn.getMerchantNo());

				List<GuidInfoShop> guidInfoShops = guidMemberService
						.findGuidInfoShop(findGuidMemberDto);
				// 导购维度
				for (GuidInfoShop guidInfoShop : guidInfoShops) {
					int count = WxChatInfos("00:00", "01:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"00:00", "01:00", count);

					count = WxChatInfos("01:00", "02:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"01:00", "02:00", count);

					count = WxChatInfos("02:00", "03:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"02:00", "03:00", count);

					count = WxChatInfos("03:00", "04:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"03:00", "04:00", count);

					count = WxChatInfos("04:00", "05:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"04:00", "05:00", count);

					count = WxChatInfos("05:00", "06:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"05:00", "06:00", count);

					count = WxChatInfos("06:00", "07:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"06:00", "07:00", count);

					count = WxChatInfos("07:00", "08:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"07:00", "08:00", count);

					count = WxChatInfos("08:00", "09:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"08:00", "09:00", count);

					count = WxChatInfos("09:00", "10:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"09:00", "10:00", count);

					count = WxChatInfos("10:00", "11:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"10:00", "11:00", count);

					count = WxChatInfos("11:00", "12:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"11:00", "12:00", count);

					count = WxChatInfos("12:00", "13:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"12:00", "13:00", count);

					count = WxChatInfos("13:00", "14:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"13:00", "14:00", count);

					count = WxChatInfos("14:00", "15:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"14:00", "15:00", count);

					count = WxChatInfos("15:00", "16:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"15:00", "16:00", count);

					count = WxChatInfos("16:00", "17:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"16:00", "17:00", count);

					count = WxChatInfos("17:00", "18:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"17:00", "18:00", count);

					count = WxChatInfos("18:00", "19:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"18:00", "19:00", count);

					count = WxChatInfos("19:00", "20:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"19:00", "20:00", count);

					count = WxChatInfos("20:00", "21:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"20:00", "21:00", count);

					count = WxChatInfos("21:00", "22:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"21:00", "22:00", count);

					count = WxChatInfos("22:00", "23:00",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"22:00", "23:00", count);

					count = WxChatInfos("23:00", "23:59",guidInfoShop.getMemberNo());
					addPmTalkTotals(findMerchantPageReturn, guidInfoShop,"23:00", "23:59", count);
				}
				// 商户维度
				FindPmTalkTotal findPmTalkTotal = new FindPmTalkTotal();
				findPmTalkTotal.setMerchantNo(findMerchantPageReturn.getMerchantNo());
				findPmTalkTotal.setDimensionSt(DimensionSt.GUID.toString());
				List<FindPmTalkTotalAllReturnList> list = pmTalkTotalService.findPmTalkTotaReturnList(findPmTalkTotal);

				for (FindPmTalkTotalAllReturnList findPmTalkTotalAllReturnList : list) {
					addPmTalkTotal.setMerchantNo(findMerchantPageReturn.getMerchantNo());
					addPmTalkTotal.setStartDate(findPmTalkTotalAllReturnList.getStartDate());
					addPmTalkTotal.setEndDate(findPmTalkTotalAllReturnList.getEndDate());
					addPmTalkTotal.setNumTalk(findPmTalkTotalAllReturnList.getCount());
					addPmTalkTotal.setDaySt(DateUtils.getPreday(new Date()));
					addPmTalkTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
					addPmTalkTotal.setCreateDate(new Date());
					pmTalkTotalService.addPmTalkTotal(addPmTalkTotal);
				}

				// 区域维度
//				findShopDto.setMemberNoMerchant(findMerchantPageReturn.getMerchantNo());
//				List<FindShopReturnAreaCode> lists = shopService.findAreaCode(findShopDto);

				/*for (FindShopReturnAreaCode areaCode : lists) {
					findPmTalkTotal = new FindPmTalkTotal();
					findPmTalkTotal.setMerchantNo(areaCode.getMemberNoMerchant());
					findPmTalkTotal.setAreaCode(areaCode.getAreaCode());

					List<FindPmTalkTotalAllReturnList> findPmTalkTotalAllReturnLists = pmTalkTotalService.findPmTalkTotaReturnList(findPmTalkTotal);

					if (findPmTalkTotalAllReturnLists != null&& findPmTalkTotalAllReturnLists.size() > 0) {
						for (FindPmTalkTotalAllReturnList findPmTalkTotalAllReturnList : findPmTalkTotalAllReturnLists) {
							addPmTalkTotal.setMerchantNo(findPmTalkTotalAllReturnList.getMerchantNo());
							addPmTalkTotal.setAreaCode(findPmTalkTotalAllReturnList.getAreaCode());
							addPmTalkTotal.setAreaName(findPmTalkTotalAllReturnList.getAreaName());
							addPmTalkTotal.setStartDate(findPmTalkTotalAllReturnList.getStartDate());
							addPmTalkTotal.setEndDate(findPmTalkTotalAllReturnList.getEndDate());
							addPmTalkTotal.setNumTalk(findPmTalkTotalAllReturnList.getCount());
							addPmTalkTotal.setDaySt(DateUtils.getPreday(new Date()));
							addPmTalkTotal.setDimensionSt(DimensionSt.AREA.toString());
							addPmTalkTotal.setCreateDate(new Date());
							pmTalkTotalService.addPmTalkTotal(addPmTalkTotal);
						}
					}

				}*/
				// 门店维度
				/*List<FindShopReturnAreaCode> findShopReturnAreaCodes = shopService.selectByAreaCode(findShopDto);

				for (FindShopReturnAreaCode findShopReturnAreaCode : findShopReturnAreaCodes) {
					findPmTalkTotal = new FindPmTalkTotal();
					findPmTalkTotal.setShopNo(findShopReturnAreaCode.getShopNo());
					findPmTalkTotal.setMerchantNo(findShopReturnAreaCode.getMemberNoMerchant());

					List<FindPmTalkTotalAllReturnList> findPmTalkTotalAllReturnLists = pmTalkTotalService.findPmTalkTotaReturnList(findPmTalkTotal);

					if (findPmTalkTotalAllReturnLists != null&& findPmTalkTotalAllReturnLists.size() > 0) {
						for (FindPmTalkTotalAllReturnList allReturnList : findPmTalkTotalAllReturnLists) {
							addPmTalkTotal.setMerchantNo(allReturnList.getMerchantNo());
							addPmTalkTotal.setAreaCode(allReturnList.getAreaCode());
							addPmTalkTotal.setAreaName(allReturnList.getAreaName());
							addPmTalkTotal.setShopNo(allReturnList.getShopNo());
							addPmTalkTotal.setShopName(allReturnList.getShopName());
							addPmTalkTotal.setStartDate(allReturnList.getStartDate());
							addPmTalkTotal.setEndDate(allReturnList.getEndDate());
							addPmTalkTotal.setNumTalk(allReturnList.getCount());
							addPmTalkTotal.setDaySt(DateUtils.getPreday(new Date()));
							addPmTalkTotal.setDimensionSt(DimensionSt.SHOP.toString());
							addPmTalkTotal.setCreateDate(new Date());
							pmTalkTotalService.addPmTalkTotal(addPmTalkTotal);
						}
					}

				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 方法说明：新增客户咨询
	 * 
	 * @param findMerchantPageReturn
	 * @param guidInfoShop
	 * @param startDate
	 * @param endDate
	 * @param count
	 * @author 罗书明 CreateDate: 2017年8月14日
	 */
	public void addPmTalkTotals(FindMerchantPageReturn findMerchantPageReturn,
			GuidInfoShop guidInfoShop, String startDate, String endDate,int  count) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String strDate = startDate;
		Date startDates;
		Date endDates;
		try {
			startDates = sdf.parse(strDate);
			endDates = sdf.parse(endDate);
			AddPmTalkTotal addPmTalkTotal = new AddPmTalkTotal();
			addPmTalkTotal.setMerchantNo(findMerchantPageReturn.getMerchantNo());
			addPmTalkTotal.setAreaCode(guidInfoShop.getAreaCode());
			addPmTalkTotal.setAreaName(guidInfoShop.getAreaName());
			addPmTalkTotal.setMemberNameGm(guidInfoShop.getMemberName());
			addPmTalkTotal.setMemberNoGm(guidInfoShop.getMemberNo());
//			addPmTalkTotal.setShopNo(guidInfoShop.getShopNo());
//			addPmTalkTotal.setShopName(guidInfoShop.getShopName());
			addPmTalkTotal.setStartDate(startDates);
			addPmTalkTotal.setEndDate(endDates);
			addPmTalkTotal.setNumTalk(count);
			addPmTalkTotal.setDaySt(DateUtils.getPreday(new Date()));
			addPmTalkTotal.setDimensionSt(DimensionSt.GUID.toString());
			addPmTalkTotal.setCreateDate(new Date());
			pmTalkTotalService.addPmTalkTotal(addPmTalkTotal);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 *
	 * 方法说明：微信聊天记录统计
	 *
	 * @param startTime
	 * @param endTime
	 * @param findWxChatInfo
	 *
	 * @author 罗书明 CreateDate: 2017年8月8日
	 *
	 */
	private int WxChatInfos(String startTime, String endTime, String memberNo) {
		FindWxChatInfo findWxChatInfo = new FindWxChatInfo();
		findWxChatInfo.setStartTime(startTime);
		findWxChatInfo.setEndTime(endTime);
		findWxChatInfo.setMemberNo(memberNo);
		int count = wxChatInfoService.findWxChantInfoCountTime(findWxChatInfo);
		return count;
	}

	/**
	 * 统计未联系客户后添加积分
	 * @throws TsfaServiceException
	 */
	private void generatorUnContactTotal() throws TsfaServiceException {

		List<FindUnContactTotalReturn> list = unContactTotalService.findList();
		if (list != null && list.size() > 0)
			for (FindUnContactTotalReturn findUnContactTotalReturn : list) {
				GuidMemberIntegralDto guidMemberIntegralDto = new GuidMemberIntegralDto();
				guidMemberIntegralDto.setMerchantNo(findUnContactTotalReturn.getMerchantNo());
//				guidMemberIntegralDto.setShopNo(findUnContactTotalReturn.getShopNo());
				guidMemberIntegralDto.setMemberNo(findUnContactTotalReturn
						.getMemberNoGm());

				FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
				findGuidMemberDto.setMemberNo(findUnContactTotalReturn
						.getMemberNoGm());
				GuidMemberReturnDto guidMemberReturnDto = guidMemberService
						.findGuid(findGuidMemberDto);
				if (guidMemberReturnDto != null) {
					guidMemberIntegralDto.setAreaCode(guidMemberReturnDto
							.getAreaCode());
				}
				guidMemberIntegralDto.setIntegralType(IntegralType.NOTICE
						.toString());

				if (findUnContactTotalReturn.getYizhouYiyue() != null
						&& findUnContactTotalReturn.getYizhouYiyue() == 0) {
					guidMemberIntegralService.doIntegral(guidMemberIntegralDto);
				}

				if (findUnContactTotalReturn.getYiyueSanyue() != null
						&& findUnContactTotalReturn.getYiyueSanyue() == 0) {
					guidMemberIntegralService.doIntegral(guidMemberIntegralDto);
				}

				if (findUnContactTotalReturn.getSanyueLiuyue() != null
						&& findUnContactTotalReturn.getSanyueLiuyue() == 0) {
					guidMemberIntegralService.doIntegral(guidMemberIntegralDto);
				}
				
				if (findUnContactTotalReturn.getLiuyueEnd() != null
						&& findUnContactTotalReturn.getLiuyueEnd() == 0) {
					guidMemberIntegralService.doIntegral(guidMemberIntegralDto);
				}
			}

	}
  
	public void generatorPmTypeTotal() throws TsfaServiceException {
//		FindShopDto findShopDto = new FindShopDto();
		FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
		FindPmTypePageReturn findPmTypePageReturn = new FindPmTypePageReturn();
		AddPmTypeTotal addPmTypeTotal = new AddPmTypeTotal();
		FindPersonMember findMerchantPersonMember = new FindPersonMember();
		AddPmTypeTotal pmTypeTotal = new AddPmTypeTotal();
		FindPersonMember findAreaPersonMember = new FindPersonMember();
		try {
			// 获取商户信息
			List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
			for (FindMerchantPageReturn findMerchantPageReturn : merchants) {
				 String merchantNo = findMerchantPageReturn.getMerchantNo();  
				 //查询商户分类信息 
				 findPmTypePageReturn.setMerchantNo(merchantNo);
				 List<FindPmTypePageReturn> pmList = pmTypeService.findPmTypePages(findPmTypePageReturn);
				 findMerchantPersonMember = new FindPersonMember();
				 findMerchantPersonMember.setMerchantNo(merchantNo);
				 int countSum = personMemberService.findPersonMemberTypeCount(findMerchantPersonMember);//客户总数
				 
				 for(FindPmTypePageReturn pmTypePageReturn : pmList ){
					 addPmTypeTotal = new AddPmTypeTotal();
					 findMerchantPersonMember = new FindPersonMember();
					 findMerchantPersonMember.setMerchantNo(merchantNo);
					 findMerchantPersonMember.setPmTypeCode(pmTypePageReturn.getCode());
					 FindPersonMemberReturnList memberReturnList =  personMemberService.findPersonMemberType(findMerchantPersonMember);
					 addPmTypeTotal.setMerchantNo(pmTypePageReturn.getMerchantNo());
					 addPmTypeTotal.setPmTypeCode(pmTypePageReturn.getCode());
				     addPmTypeTotal.setTypeName(pmTypePageReturn.getTypeName());
					 addPmTypeTotal.setPmTypeType(pmTypePageReturn.getPmTypeType());
					 if(memberReturnList != null){
						findMerchantPersonMember.setCreateDate(DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
						findMerchantPersonMember.setPmTypeType(pmTypePageReturn.getPmTypeType());
						int numAdd = personMemberService.findPersonMemberTypeCount(findMerchantPersonMember);//分类新增客户数
						addPmTypeTotal.setNumAdd(numAdd);
						Double ratioPm = countSum == 0L ? 0.0 : Double.valueOf(memberReturnList.getCount()) / countSum;//客户占比
						addPmTypeTotal.setRatioPm(ratioPm);
						addPmTypeTotal.setNumPm(memberReturnList.getCount());//分类数量
					 }
					 addPmTypeTotal.setDaySt(DateUtils.getPreday(new Date()));
					 addPmTypeTotal.setDimensionSt(DimensionSt.MERCHANT.toString());
					 pmTypeTotalService.addPmTypeTotal(addPmTypeTotal);
				 }
				// 区域维度
//				findShopDto.setMemberNoMerchant(findMerchantPageReturn.getMerchantNo());
				/*List<FindShopReturnAreaCode> lists = shopService.findAreaCode(findShopDto);
				for (FindShopReturnAreaCode returnAreaCode : lists) {
					 String areaCode = returnAreaCode.getAreaCode();
					 findAreaPersonMember = new FindPersonMember();
					 findAreaPersonMember.setMerchantNo(findMerchantPageReturn.getMerchantNo());
					 findAreaPersonMember.setAreaCode(areaCode);
				     int countSumAreaCode = personMemberService.findPersonMemberTypeCount(findAreaPersonMember);
					 for(FindPmTypePageReturn pmTypePageReturn : pmList ){
						 findAreaPersonMember = new FindPersonMember();
						 findAreaPersonMember.setMerchantNo(merchantNo);
						 findAreaPersonMember.setPmTypeCode(pmTypePageReturn.getCode());
						 findAreaPersonMember.setAreaCode(areaCode);
						 FindPersonMemberReturnList findPersonMemberReturnList = personMemberService.findPersonMemberType(findAreaPersonMember);
						 pmTypeTotal = new AddPmTypeTotal();
						 pmTypeTotal.setMerchantNo(pmTypePageReturn.getMerchantNo());
						 pmTypeTotal.setAreaCode(returnAreaCode.getAreaCode());
						 pmTypeTotal.setAreaName(returnAreaCode.getAreaName());
						 pmTypeTotal.setPmTypeCode(pmTypePageReturn.getCode());
						 pmTypeTotal.setPmTypeType(pmTypePageReturn.getPmTypeType());
						 pmTypeTotal.setTypeName(pmTypePageReturn.getTypeName());
					     if(findPersonMemberReturnList != null){
						     pmTypeTotal.setNumPm(findPersonMemberReturnList.getCount());
							 Double ratioPm = countSumAreaCode == 0L ? 0.0 : Double.valueOf(findPersonMemberReturnList.getCount()) / countSumAreaCode;
							 pmTypeTotal.setRatioPm(ratioPm);
							 findAreaPersonMember = new FindPersonMember();
							 findAreaPersonMember.setMerchantNo(merchantNo); 
							 findAreaPersonMember.setCreateDate(DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
							 findAreaPersonMember.setPmTypeType(pmTypePageReturn.getPmTypeType());
							 findAreaPersonMember.setAreaCode(areaCode); 
						     int numAdd = personMemberService.findPersonMemberTypeCount(findAreaPersonMember);
							 pmTypeTotal.setNumAdd(numAdd);
						 }
						 pmTypeTotal.setDaySt(DateUtils.getPreday(new Date()));
						 pmTypeTotal.setDimensionSt(DimensionSt.AREA.toString());
						 pmTypeTotalService.addPmTypeTotal(pmTypeTotal);
					}
						 
				}*/
					 
				
				// 门店维度
				/*List<FindShopReturnAreaCode> findShopReturnAreaCodes = shopService.selectByAreaCode(findShopDto);
				for(FindShopReturnAreaCode shopReturnAreaCode : findShopReturnAreaCodes) {
					findAreaPersonMember = new FindPersonMember();
					findAreaPersonMember.setMerchantNo(merchantNo);
					findAreaPersonMember.setShopNo(shopReturnAreaCode.getShopNo());
					int countSumAreaCode = personMemberService.findPersonMemberTypeCount(findAreaPersonMember);
					for(FindPmTypePageReturn pmTypePageReturn : pmList ){
						findAreaPersonMember = new FindPersonMember();
						findAreaPersonMember.setMerchantNo(findMerchantPageReturn.getMerchantNo());
						findAreaPersonMember.setShopNo(shopReturnAreaCode.getShopNo());
						findAreaPersonMember.setPmTypeCode(pmTypePageReturn.getCode());
						FindPersonMemberReturnList findPersonMemberReturnList = personMemberService.findPersonMemberType(findAreaPersonMember);
						pmTypeTotal = new AddPmTypeTotal();
						pmTypeTotal.setMerchantNo(pmTypePageReturn.getMerchantNo());
						pmTypeTotal.setAreaCode(shopReturnAreaCode.getAreaCode());
						pmTypeTotal.setAreaName(shopReturnAreaCode.getAreaName());
						pmTypeTotal.setShopNo(shopReturnAreaCode.getShopNo());
						pmTypeTotal.setShopName(shopReturnAreaCode.getShopName());
						pmTypeTotal.setPmTypeCode(pmTypePageReturn.getCode());
						pmTypeTotal.setPmTypeType(pmTypePageReturn.getPmTypeType());
						pmTypeTotal.setTypeName(pmTypePageReturn.getTypeName());
					    if(findPersonMemberReturnList != null){
					    	findAreaPersonMember = new FindPersonMember();
					    	findAreaPersonMember.setCreateDate(DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
					    	findAreaPersonMember.setPmTypeType(findPersonMemberReturnList.getPmTypeType());
					    	findAreaPersonMember.setMerchantNo(merchantNo);
					    	findAreaPersonMember.setShopNo(shopReturnAreaCode.getShopNo());
							int numAdd = personMemberService.findPersonMemberTypeCount(findAreaPersonMember);
							pmTypeTotal.setNumAdd(numAdd);
							pmTypeTotal.setNumPm(findPersonMemberReturnList.getCount());
							Double ratioPm = countSumAreaCode == 0L ? 0.0 : Double.valueOf(findPersonMemberReturnList.getCount())/ countSumAreaCode;
							pmTypeTotal.setRatioPm(ratioPm);
					    }
						pmTypeTotal.setDaySt(DateUtils.getPreday(new Date()));
						pmTypeTotal.setDimensionSt(DimensionSt.SHOP.toString());
						pmTypeTotalService.addPmTypeTotal(pmTypeTotal);
					}
					
				}*/
				
				// 导购维度
				findGuidMemberDto = new FindGuidMemberDto();
				findGuidMemberDto.setMerchantNo(merchantNo);
				List<GuidInfoShop> guidInfoShops = guidMemberService.findGuidInfoShop(findGuidMemberDto);
				for (GuidInfoShop guidInfoShop : guidInfoShops) {
					 findAreaPersonMember = new FindPersonMember();
					 findAreaPersonMember.setMerchantNo(merchantNo);
					 findAreaPersonMember.setMemberNoGm(guidInfoShop.getMemberNo());
					 int countSumAreaCode = personMemberService.findPersonMemberTypeCount(findAreaPersonMember);
					 for(FindPmTypePageReturn pmTypePageReturn : pmList){
						 findAreaPersonMember.setPmTypeCode(pmTypePageReturn.getCode());
						 FindPersonMemberReturnList findPersonMemberReturnList = personMemberService.findPersonMemberType(findAreaPersonMember);
						 pmTypeTotal = new AddPmTypeTotal();
						 pmTypeTotal.setMerchantNo(guidInfoShop.getMerchantNo());
						 pmTypeTotal.setAreaCode(guidInfoShop.getAreaCode());
						 pmTypeTotal.setAreaName(guidInfoShop.getAreaName());
//						 pmTypeTotal.setShopNo(guidInfoShop.getShopNo());
//						 pmTypeTotal.setShopName(guidInfoShop.getShopName());
						 pmTypeTotal.setMemberNoGm(guidInfoShop.getMemberNo());
						 if(findPersonMemberReturnList != null){
							 findAreaPersonMember.setCreateDate(DateUtils.formatDate(DateUtils.getPreday(new Date()), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
							 findAreaPersonMember.setPmTypeType(pmTypePageReturn.getPmTypeType());
							 int numAdd = personMemberService.findPersonMemberTypeCount(findAreaPersonMember);
							 pmTypeTotal.setNumAdd(numAdd);
							 pmTypeTotal.setNumPm(findPersonMemberReturnList.getCount());
							 Double ratioPm = countSumAreaCode == 0L ? 0.0 : Double.valueOf(findPersonMemberReturnList.getCount())/ countSumAreaCode;
							 pmTypeTotal.setRatioPm(ratioPm);
						 }
						 pmTypeTotal.setMemberNameGm(guidInfoShop.getMemberName());
						 pmTypeTotal.setPmTypeCode(pmTypePageReturn.getCode());
						 pmTypeTotal.setPmTypeType(pmTypePageReturn.getPmTypeType());
						 pmTypeTotal.setTypeName(pmTypePageReturn.getTypeName());
						
						 pmTypeTotal.setDaySt(DateUtils.getPreday(new Date()));
						 pmTypeTotal.setDimensionSt(DimensionSt.GUID.toString());
						 pmTypeTotalService.addPmTypeTotal(pmTypeTotal);
					 }
					
				}

			}
		} catch (Exception e) {
			logger.error("统计失败", e);
		}

	}
	
	
	
	@Override
	public int addOrUpdateClientAnalyzeStatistics() {
		logger.info("开始新增或更新客户画像");
		try {
			// 统计分店的性别
			List<FindPersonMemberSexStatisticsReturn> memberList = personMemberService.selectSexStatisticsByShopNo();
			if (!CollectionUtils.isEmpty(memberList)) {

				// 给每个分店分组
				Map<String, List<FindPersonMemberSexStatisticsReturn>> shopMap = new HashMap<>();
				for (FindPersonMemberSexStatisticsReturn each : memberList) {
//					List<FindPersonMemberSexStatisticsReturn> shopList = shopMap.get(each.getShopNo());
//					if (shopList == null) {
//						shopList = new ArrayList<>();
//						shopMap.put(each.getShopNo(), shopList);
//					}
//					shopList.add(each);
				}

				// 查询10-59岁的客户
				Map<String, FindPersonMemberAgeStatisticsReturn> tenAgeMap = getAgeMap(19, 10);
				Map<String, FindPersonMemberAgeStatisticsReturn> twentyAgeMap = getAgeMap(29, 20);
				Map<String, FindPersonMemberAgeStatisticsReturn> thirtyAgeMap = getAgeMap(39, 30);
				Map<String, FindPersonMemberAgeStatisticsReturn> fortyAgeMap = getAgeMap(49, 40);
				Map<String, FindPersonMemberAgeStatisticsReturn> fiftyAgeMap = getAgeMap(59, 50);

				// 统计每个分店
				for (Map.Entry<String, List<FindPersonMemberSexStatisticsReturn>> entry : shopMap.entrySet()) {

					// 计算分店的客户数
					Long totalNum = 0L;
					Long maleNum = 0L;
					Long femaleNum = 0L;
					String merchantNo = null;
					String areaCode = null;
					String areaName = null;
					String shopNo = null;
					String shopName = null;
					List<FindPersonMemberSexStatisticsReturn> shopMemberList = entry.getValue();
					for (FindPersonMemberSexStatisticsReturn each : shopMemberList) {
						totalNum += each.getSexCount();
						if ("FEMALE".equals(each.getSex())) {
							femaleNum += each.getSexCount();
						}
						else if ("MALE".equals(each.getSex())) {
							maleNum += each.getSexCount();
						}

						merchantNo = each.getMerchantNo();
						areaCode = each.getAreaCode();
						areaName = each.getAreaName();
//						shopNo = each.getShopNo();
//						shopName = each.getShopName();
					}

					// 计算每个分店年龄数
					Integer tenAgeCount = getAgeCount(tenAgeMap, shopNo);
					Integer twentyAgeCount = getAgeCount(twentyAgeMap, shopNo);
					Integer thirtyAgeCount = getAgeCount(thirtyAgeMap, shopNo);
					Integer fortyAgeCount = getAgeCount(fortyAgeMap, shopNo);
					Integer fiftyAgeCount = getAgeCount(fiftyAgeMap, shopNo);
					Integer totalAgeCount = tenAgeCount + twentyAgeCount + thirtyAgeCount + fortyAgeCount + fiftyAgeCount;

					// 查询商户的统计是否已存在
					ClientAnalyze shopClientAnalyze = clientAnalyzeService.findByShopNo(entry.getKey());
					if (shopClientAnalyze == null) {
						AddClientAnalyze addClientAnalyze = new AddClientAnalyze();
						addClientAnalyze.setMerchantNo(merchantNo);
						addClientAnalyze.setAreaCode(areaCode);
						addClientAnalyze.setAreaName(areaName);
						addClientAnalyze.setShopNo(shopNo);
						addClientAnalyze.setShopName(shopName);
						addClientAnalyze.setStDate(new Date());
						addClientAnalyze.setRatioMale(getRatio(totalNum, maleNum));
						addClientAnalyze.setRatioFemale(getRatio(totalNum, femaleNum));
						addClientAnalyze.setNumPm(totalNum);

						addClientAnalyze.setNumAgeTen(tenAgeCount);
						addClientAnalyze.setNumAgeTwenty(twentyAgeCount);
						addClientAnalyze.setNumAgeThirty(thirtyAgeCount);
						addClientAnalyze.setNumAgeForty(fortyAgeCount);
						addClientAnalyze.setNumAgeFifty(fiftyAgeCount);

						addClientAnalyze.setRatioAgeTen(getRatio(totalAgeCount, tenAgeCount));
						addClientAnalyze.setRatioAgeTwenty(getRatio(totalAgeCount, twentyAgeCount));
						addClientAnalyze.setRatioAgeThirty(getRatio(totalAgeCount, thirtyAgeCount));
						addClientAnalyze.setRatioAgeForty(getRatio(totalAgeCount, fortyAgeCount));
						addClientAnalyze.setRatioAgeFifty(getRatio(totalAgeCount, fiftyAgeCount));

						addClientAnalyze.setDimensionSt(DimensionSt.SHOP.toString());
                        addClientAnalyze.setNumMale(maleNum.intValue());
                        addClientAnalyze.setNumFemale(femaleNum.intValue());
                        clientAnalyzeService.addClientAnalyze(addClientAnalyze);
					}
					else {
						shopClientAnalyze.setRatioMale(getRatio(totalNum, maleNum));
						shopClientAnalyze.setRatioFemale(getRatio(totalNum, femaleNum));
						shopClientAnalyze.setNumPm(totalNum);

						shopClientAnalyze.setNumAgeTen(tenAgeCount);
						shopClientAnalyze.setNumAgeTwenty(twentyAgeCount);
						shopClientAnalyze.setNumAgeThirty(thirtyAgeCount);
						shopClientAnalyze.setNumAgeForty(fortyAgeCount);
						shopClientAnalyze.setNumAgeFifty(fiftyAgeCount);

						shopClientAnalyze.setRatioAgeTen(getRatio(totalAgeCount, tenAgeCount));
						shopClientAnalyze.setRatioAgeTwenty(getRatio(totalAgeCount, twentyAgeCount));
						shopClientAnalyze.setRatioAgeThirty(getRatio(totalAgeCount, thirtyAgeCount));
						shopClientAnalyze.setRatioAgeForty(getRatio(totalAgeCount, fortyAgeCount));
						shopClientAnalyze.setRatioAgeFifty(getRatio(totalAgeCount, fiftyAgeCount));
                        shopClientAnalyze.setNumMale(maleNum.intValue());
                        shopClientAnalyze.setNumFemale(femaleNum.intValue());
                        clientAnalyzeService.updateClientAnalyze(shopClientAnalyze);
					}
				}
			}

			// 增加兴趣统计
			addShopInterestRpt();

			// 增加职业统计
			addShopLineRpt();

			// 增加客户画像商户和区域统计
			modifyClientAnalyze();

			// 增加兴趣商户和区域统计
			modifyClientInterest();

			// 增加职业商户和区域统计
			modifyClientLine();

			return 1;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("增加或更新客户画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_ANALYZE_ADD_ERROR,"增加或更新客户画像信息错误！",e);
		}
	}
	
	
	private Integer getAgeCount(Map<String, FindPersonMemberAgeStatisticsReturn> ageMap, String shopNo) {
		FindPersonMemberAgeStatisticsReturn result = ageMap.get(shopNo);
		return result == null ? 0 : (result.getAgeCount() == null ? 0 : result.getAgeCount());
	}

	/**
	 * 
	 *
	 * 方法说明：根据商户编号统计年龄
	 *
	 * @param beginAge
	 * @param endAge
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年8月30日
	 *
	 */
	private Map<String, FindPersonMemberAgeStatisticsReturn> getAgeMap(int beginAge, int endAge) {
		Map<String, FindPersonMemberAgeStatisticsReturn> result = new HashMap<>();
		List<FindPersonMemberAgeStatisticsReturn> ageList = personMemberService.selectAgeStatisticsByShopNo(getBeginDate(beginAge), getEndDate(endAge));
//		if (!CollectionUtils.isEmpty(ageList)) {
//			for (FindPersonMemberAgeStatisticsReturn each : ageList) {
//				result.put(each.getShopNo(), each);
//			}
//		}
		return result;
	}
	
	private Double getRatio(Integer totalNum, Integer num) {
		return getRatio(totalNum.longValue(), num.longValue());
	}
	
	private Double getRatio(Long totalNum, Long num) {
		if (totalNum == 0 || num == 0) {
			return 0D;
		}
		double result = ArithUtil.div(num, totalNum);
		BigDecimal bd = new BigDecimal(result);
		bd = bd.setScale(4, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	private static String getBeginDate(Integer minusYear) {
		Calendar cal = getCalendar(minusYear);
		return DateUtils.formatDate(cal.getTime(), "yyyy-01-01");
	}

	private static String getEndDate(Integer minusYear) {
		Calendar cal = getCalendar(minusYear);
		return DateUtils.formatDate(cal.getTime(), "yyyy-12-31");
	}
	
	private static Calendar getCalendar(Integer minusYear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -minusYear);
		return cal;
	}


	/**
	 * 增加商户兴趣统计
	 */
	private void addShopInterestRpt() {
	    // 统计兴趣
        List<FindPersonMemberInterestStatisticsReturn> interestList = personMemberService.selectInterestStatisticsByShopNo();
        if (!CollectionUtils.isEmpty(interestList)) {
            // 给每个分店分组
            Map<String, List<FindPersonMemberInterestStatisticsReturn>> shopMap = new HashMap<>();
//            for (FindPersonMemberInterestStatisticsReturn each : interestList) {
//                List<FindPersonMemberInterestStatisticsReturn> shopList = shopMap.get(each.getShopNo());
//                if (shopList == null) {
//                    shopList = new ArrayList<>();
//                    shopMap.put(each.getShopNo(), shopList);
//                }
//                shopList.add(each);
//            }

            // 统计每个分店
            for (Map.Entry<String, List<FindPersonMemberInterestStatisticsReturn>> entry : shopMap.entrySet()) {

                // 计算分店的兴趣数
                Long totalNum = 0L;
                String merchantNo = null;
                String areaCode = null;
                String shopNo = null;
                String shopName = null;
                List<FindPersonMemberInterestStatisticsReturn> shopInterestList = entry.getValue();
                for (FindPersonMemberInterestStatisticsReturn each : shopInterestList) {
                    totalNum += each.getNumInterest();
                    merchantNo = each.getMerchantNo();
                    areaCode = each.getAreaCode();
//                    shopNo = each.getShopNo();
//                    shopName = each.getShopName();
                }

                // 循环设置分店数据
                for (FindPersonMemberInterestStatisticsReturn each : shopInterestList) {
                    // 查询商户的职业统计是否已存在
                    FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
                    findClientAnalyzeAndOthers.setShopNo(entry.getKey());
                    findClientAnalyzeAndOthers.setDimensionSt(DimensionSt.SHOP.toString());
                    findClientAnalyzeAndOthers.setCodeLine(each.getCodeInterest());
                    List<ClientInterestRpt> clientInterestRpts = clientInterestRptService.selectClientInterestRptList(findClientAnalyzeAndOthers);
                    ClientInterestRpt clientInterestRpt = !CollectionUtils.isEmpty(clientInterestRpts) ? clientInterestRpts.get(0) : null;
                    if (clientInterestRpt == null) {
                        AddClientInterestRpt addClientInterestRpt = new AddClientInterestRpt();
                        addClientInterestRpt.setMerchantNo(merchantNo);
                        addClientInterestRpt.setShopName(shopName);
                        addClientInterestRpt.setShopNo(shopNo);
                        addClientInterestRpt.setAreaCode(areaCode);
                        addClientInterestRpt.setCodeInterest(each.getCodeInterest());
                        addClientInterestRpt.setInterestName(each.getInterestName());
                        addClientInterestRpt.setRatioLine(new BigDecimal(getRatio(totalNum, each.getNumInterest().longValue())));
                        addClientInterestRpt.setNumInterest(each.getNumInterest());
                        addClientInterestRpt.setDimensionSt(DimensionSt.SHOP.toString());
                        clientInterestRptService.addClientInterestRpt(addClientInterestRpt);
                    }
                    else {
						clientInterestRpt.setRatioLine(new BigDecimal(getRatio(totalNum, each.getNumInterest().longValue())));
                        clientInterestRpt.setNumInterest(each.getNumInterest());
                        clientInterestRptService.updateClientInterestRpt(clientInterestRpt);
                    }
                }
            }
        }
    }

	
	/**
	 * 增加商户职业统计
	 */
	private void addShopLineRpt() {
	    // 统计职业
//        List<FindPersonMemberLineStatisticsReturn> lineList = personMemberService.selectLineStatisticsByShopNo();
        /*if (!CollectionUtils.isEmpty(lineList)) {
            // 给每个分店分组
            Map<String, List<FindPersonMemberLineStatisticsReturn>> shopMap = new HashMap<>();
            for (FindPersonMemberLineStatisticsReturn each : lineList) {
                List<FindPersonMemberLineStatisticsReturn> shopList = shopMap.get(each.getShopNo());
                if (shopList == null) {
                    shopList = new ArrayList<>();
                    shopMap.put(each.getShopNo(), shopList);
                }
                shopList.add(each);
            }

            // 统计每个分店
            for (Map.Entry<String, List<FindPersonMemberLineStatisticsReturn>> entry : shopMap.entrySet()) {

                // 计算分店的职业数
                Long totalNum = 0L;
                String merchantNo = null;
                String areaCode = null;
                String shopNo = null;
                String shopName = null;
                List<FindPersonMemberLineStatisticsReturn> shopLineList = entry.getValue();
                for (FindPersonMemberLineStatisticsReturn each : shopLineList) {
                    totalNum += each.getNumLine();
                    merchantNo = each.getMerchantNo();
                    areaCode = each.getAreaCode();
//                    shopNo = each.getShopNo();
//                    shopName = each.getShopName();
                }

                // 循环设置分店数据
                for (FindPersonMemberLineStatisticsReturn each : shopLineList) {
                    // 查询商户的职业统计是否已存在
                    FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
                    findClientAnalyzeAndOthers.setShopNo(entry.getKey());
                    findClientAnalyzeAndOthers.setDimensionSt(DimensionSt.SHOP.toString());
                    findClientAnalyzeAndOthers.setCodeLine(each.getCodeLine());
                    List<ClientLineRpt> clientLineRpts = clientLineRptService.selectClientLineRptList(findClientAnalyzeAndOthers);
                    ClientLineRpt clientLineRpt = !CollectionUtils.isEmpty(clientLineRpts) ? clientLineRpts.get(0) : null;
                    if (clientLineRpt == null) {
                        AddClientLineRpt addClientLineRpt = new AddClientLineRpt();
                        addClientLineRpt.setMerchantNo(merchantNo);
                        addClientLineRpt.setShopName(shopName);
                        addClientLineRpt.setShopNo(shopNo);
                        addClientLineRpt.setAreaCode(areaCode);
                        addClientLineRpt.setCodeLine(each.getCodeLine());
                        addClientLineRpt.setLineName(each.getLineName());
                        addClientLineRpt.setRatioLine(new BigDecimal(getRatio(totalNum, each.getNumLine().longValue())));
                        addClientLineRpt.setNumLine(each.getNumLine());
                        addClientLineRpt.setDimensionSt(DimensionSt.SHOP.toString());
                        clientLineRptService.addClientLineRpt(addClientLineRpt);
                    }
                    else {
						clientLineRpt.setRatioLine(new BigDecimal(getRatio(totalNum, each.getNumLine().longValue())));
                        clientLineRpt.setNumLine(each.getNumLine());
                        clientLineRptService.updateClientLineRpt(clientLineRpt);
                    }
                }
            }
        }*/
    }


	/**
	 * 增加或更新商户和区域的客户兴趣
	 * update by 曾垂瑜 2017-09-18 重写此方法
	 */
	private void modifyClientInterest() {
		// 1、按商户区域维度统计
		// 根据分店维度数据统计商户区域数据
		List<ClientInterestRpt> areaTotalList = clientInterestRptService.selectAreaTotalByShop();
		if (CollectionUtils.isEmpty(areaTotalList)) {
			return;
		}
		
		// 统计各商户区域总数
		Map<String, Integer> areaTotalMap = new HashMap<String, Integer>(); // key = 商户编号 + 区域编码
		for(ClientInterestRpt rpt : areaTotalList) {
			String key = rpt.getMerchantNo() + rpt.getAreaCode();
			Integer total = areaTotalMap.get(key);
			if(total != null) {
				areaTotalMap.put(key, total + rpt.getNumInterest());
			} else {
				areaTotalMap.put(key, rpt.getNumInterest());
			}
		}
		
		// 增加或更新商户区域维度数据
		for(ClientInterestRpt rpt : areaTotalList) {
			// 查询职业统计是否已存在
            FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
            findClientAnalyzeAndOthers.setMerchantNo(rpt.getMerchantNo());
            findClientAnalyzeAndOthers.setAreaCode(rpt.getAreaCode());
            findClientAnalyzeAndOthers.setCodeInterest(rpt.getCodeInterest());
            findClientAnalyzeAndOthers.setDimensionSt("AREA");
            List<ClientInterestRpt> clientRpts = clientInterestRptService.selectClientInterestRptList(findClientAnalyzeAndOthers);
            ClientInterestRpt clientRpt = !CollectionUtils.isEmpty(clientRpts) ? clientRpts.get(0) : null;
            if (clientRpt == null) {
                AddClientInterestRpt addClientInterestRpt = new AddClientInterestRpt();
                addClientInterestRpt.setMerchantNo(rpt.getMerchantNo());
                addClientInterestRpt.setAreaCode(rpt.getAreaCode());
//                addClientInterestRpt.setAreaName(rpt.getAreaName());
                addClientInterestRpt.setCodeInterest(rpt.getCodeInterest());
                addClientInterestRpt.setInterestName(rpt.getInterestName());
                addClientInterestRpt.setRatioLine(new BigDecimal(getRatio(areaTotalMap.get(rpt.getMerchantNo() + rpt.getAreaCode()).longValue(), rpt.getNumInterest().longValue())));
                addClientInterestRpt.setNumInterest(rpt.getNumInterest());
                addClientInterestRpt.setDimensionSt("AREA");
                clientInterestRptService.addClientInterestRpt(addClientInterestRpt);
            }
            else {
            	ClientInterestRpt update = new ClientInterestRpt();
            	update.setCode(clientRpt.getCode());
            	update.setRatioLine(new BigDecimal(getRatio(areaTotalMap.get(rpt.getMerchantNo() + rpt.getAreaCode()).longValue(), rpt.getNumInterest().longValue())));
            	update.setNumInterest(rpt.getNumInterest());
            	clientInterestRptService.updateClientInterestRpt(update);
            }
		}
		logger.info("已生成商户区域维度客户兴趣统计报表");
		
		
		// 2、按商户维度统计
		// 根据商户区域维度数据统计商户数据
		List<ClientInterestRpt> merchantTotalList = clientInterestRptService.selectMerchantTotalByArea();
		if (CollectionUtils.isEmpty(merchantTotalList)) {
			return;
		}
		
		// 统计各商户总数
		Map<String, Integer> merchantTotalMap = new HashMap<String, Integer>(); // key = 商户编号
		for(ClientInterestRpt rpt : merchantTotalList) {
			Integer total = merchantTotalMap.get(rpt.getMerchantNo());
			if(total != null) {
				merchantTotalMap.put(rpt.getMerchantNo(), total + rpt.getNumInterest());
			} else {
				merchantTotalMap.put(rpt.getMerchantNo(), rpt.getNumInterest());
			}
		}
		
		// 增加或更新商户维度数据
		for(ClientInterestRpt rpt : merchantTotalList) {
			// 查询职业统计是否已存在
            FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
            findClientAnalyzeAndOthers.setMerchantNo(rpt.getMerchantNo());
            findClientAnalyzeAndOthers.setCodeInterest(rpt.getCodeInterest());
            findClientAnalyzeAndOthers.setDimensionSt("MERCHANT");
            List<ClientInterestRpt> clientRpts = clientInterestRptService.selectClientInterestRptList(findClientAnalyzeAndOthers);
            ClientInterestRpt clientRpt = !CollectionUtils.isEmpty(clientRpts) ? clientRpts.get(0) : null;
            if (clientRpt == null) {
            	AddClientInterestRpt addClientInterestRpt = new AddClientInterestRpt();
                addClientInterestRpt.setMerchantNo(rpt.getMerchantNo());
                addClientInterestRpt.setCodeInterest(rpt.getCodeInterest());
                addClientInterestRpt.setInterestName(rpt.getInterestName());
                addClientInterestRpt.setRatioLine(new BigDecimal(getRatio(merchantTotalMap.get(rpt.getMerchantNo()).longValue(), rpt.getNumInterest().longValue())));
                addClientInterestRpt.setNumInterest(rpt.getNumInterest());
                addClientInterestRpt.setDimensionSt("MERCHANT");
                clientInterestRptService.addClientInterestRpt(addClientInterestRpt);
            }
            else {
            	ClientInterestRpt update = new ClientInterestRpt();
            	update.setCode(clientRpt.getCode());
            	update.setRatioLine(new BigDecimal(getRatio(merchantTotalMap.get(rpt.getMerchantNo()).longValue(), rpt.getNumInterest().longValue())));
            	update.setNumInterest(rpt.getNumInterest());
            	clientInterestRptService.updateClientInterestRpt(update);
            }
		}
		logger.info("已生成商户维度客户兴趣统计报表");
		
		
		/*// 查询所有分店维度的数据
		List<ClientInterestRpt> clientInterestRpts = clientInterestRptService.selectAllShopData();
		if (!CollectionUtils.isEmpty(clientInterestRpts)) {
			// 获取所有的分店编号
			List<String> shopNoList = new ArrayList<>();
			for (ClientInterestRpt each : clientInterestRpts) {
				if (!shopNoList.contains(each.getShopNo())) {
					shopNoList.add(each.getShopNo());
				}
			}

			// 查找分店数据
			Map<String, FindShopPageReturn> shopMap = new HashMap<>();
			List<FindShopPageReturn> findShopPageReturns = shopService.selectByShopNoList(shopNoList);
			for (FindShopPageReturn each : findShopPageReturns) {
				shopMap.put(each.getShopNo(), each);
			}

			// 分组商户数据和区域数据
			Map<String, List<ClientInterestRpt>> merchantMap = new HashMap<>();
			Map<String, List<ClientInterestRpt>> areaMap = new HashMap<>();
			for (ClientInterestRpt each : clientInterestRpts) {
				FindShopPageReturn shop = shopMap.get(each.getShopNo());

				// 商户数据
				List<ClientInterestRpt> merchantList = merchantMap.get(shop.getMemberNoMerchant());
				if (merchantList == null) {
					merchantList = new ArrayList<>();
					merchantMap.put(shop.getMemberNoMerchant(), merchantList);
				}
				merchantList.add(each);

				// 区域数据
				List<ClientInterestRpt> areaList = areaMap.get(shop.getAreaCode());
				if (areaList == null) {
					areaList = new ArrayList<>();
					areaMap.put(shop.getAreaCode(), areaList);
				}
				areaList.add(each);
			}

			// 计算商户数据
			for (Map.Entry<String, List<ClientInterestRpt>> entry : merchantMap.entrySet()) {
				String merchantNo = entry.getKey();

				// 按行业编号分类
				Integer totalNum = 0;
				Map<String, List<ClientInterestRpt>> codeMap = new HashMap<>();
				List<ClientInterestRpt> lineRptList = entry.getValue();
				for(ClientInterestRpt each : lineRptList) {
					List<ClientInterestRpt> codeList = codeMap.get(each.getAreaCode());
					if (codeList == null) {
						codeList = new ArrayList<>();
						codeMap.put(each.getAreaCode(), codeList);
					}
					codeList.add(each);
					totalNum += each.getNumInterest();
				}

				// 保存每个商户的每个兴趣
				for(Map.Entry<String, List<ClientInterestRpt>> codeEntry : codeMap.entrySet()) {
					String codeInterest = codeEntry.getKey();

					// 计算每个兴趣的数量
					Integer codeNum = 0;
					String interestName = null;
					for (ClientInterestRpt each : codeEntry.getValue()) {
						codeNum += each.getNumInterest();
						if (interestName == null) {
							interestName = each.getInterestName();
						}
					}

					// 查询商户的职业统计是否已存在
					FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
					findClientAnalyzeAndOthers.setMerchantNo(merchantNo);
					findClientAnalyzeAndOthers.setDimensionSt("MERCHANT");
					findClientAnalyzeAndOthers.setCodeInterest(codeInterest);
					List<ClientInterestRpt> merchantInterestList = clientInterestRptService.selectClientInterestRptList(findClientAnalyzeAndOthers);
					ClientInterestRpt clientInterestRpt = !CollectionUtils.isEmpty(merchantInterestList) ? merchantInterestList.get(0) : null;
					if (clientInterestRpt == null) {
						AddClientInterestRpt addClientInterestRpt = new AddClientInterestRpt();
						addClientInterestRpt.setMerchantNo(merchantNo);
						addClientInterestRpt.setCodeInterest(codeInterest);
						addClientInterestRpt.setInterestName(interestName);
						addClientInterestRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
						addClientInterestRpt.setNumInterest(codeNum);
						addClientInterestRpt.setDimensionSt("MERCHANT");
						clientInterestRptService.addClientInterestRpt(addClientInterestRpt);
					}
					else {
						clientInterestRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
						clientInterestRpt.setNumInterest(codeNum);
						clientInterestRptService.updateClientInterestRpt(clientInterestRpt);
					}
				}
			}

			// 计算区域数据
			for (Map.Entry<String, List<ClientInterestRpt>> entry : areaMap.entrySet()) {
				String areaCode = entry.getKey();

				// 按兴趣编号分类
				Integer totalNum = 0;
				Map<String, List<ClientInterestRpt>> codeMap = new HashMap<>();
				List<ClientInterestRpt> lineRptList = entry.getValue();
				for(ClientInterestRpt each : lineRptList) {
					List<ClientInterestRpt> codeList = codeMap.get(each.getAreaCode());
					if (codeList == null) {
						codeList = new ArrayList<>();
						codeMap.put(each.getAreaCode(), codeList);
					}
					codeList.add(each);
					totalNum += each.getNumInterest();
				}

				// 保存每个区域的每个兴趣
				for(Map.Entry<String, List<ClientInterestRpt>> codeEntry : codeMap.entrySet()) {
					String codeInterest = codeEntry.getKey();

					// 计算每个行业的数量
					Integer codeNum = 0;
					String interestName = null;
					for (ClientInterestRpt each : codeEntry.getValue()) {
						codeNum += each.getNumInterest();
						if (interestName == null) {
							interestName = each.getInterestName();
						}
					}

					// 查询区域的兴趣统计是否已存在
					FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
					findClientAnalyzeAndOthers.setAreaCode(areaCode);
					findClientAnalyzeAndOthers.setDimensionSt("AREA");
					findClientAnalyzeAndOthers.setCodeInterest(codeInterest);
					List<ClientInterestRpt> merchantInterestList = clientInterestRptService.selectClientInterestRptList(findClientAnalyzeAndOthers);
					ClientInterestRpt clientInterestRpt = !CollectionUtils.isEmpty(merchantInterestList) ? merchantInterestList.get(0) : null;
					if (clientInterestRpt == null) {
						AddClientInterestRpt addClientInterestRpt = new AddClientInterestRpt();
						addClientInterestRpt.setAreaCode(areaCode);
						addClientInterestRpt.setCodeInterest(codeInterest);
						addClientInterestRpt.setInterestName(interestName);
						addClientInterestRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
						addClientInterestRpt.setNumInterest(codeNum);
						addClientInterestRpt.setDimensionSt("AREA");
						clientInterestRptService.addClientInterestRpt(addClientInterestRpt);
					}
					else {
						clientInterestRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
						clientInterestRpt.setNumInterest(codeNum);
						clientInterestRptService.updateClientInterestRpt(clientInterestRpt);
					}
				}
			}
		}*/
	}

	/**
	 * 增加或更新商户和区域的客户职业
	 * 
	 * update by 曾垂瑜 2017-09-18 重写此方法
	 */
	private void modifyClientLine() {
		// 1、按商户区域维度统计
		// 根据分店维度数据统计商户区域数据
		List<ClientLineRpt> areaTotalList = clientLineRptService.selectAreaTotalByShop();
		if (CollectionUtils.isEmpty(areaTotalList)) {
			return;
		}
		
		// 统计各商户区域职业总数
		Map<String, Integer> areaTotalMap = new HashMap<String, Integer>(); // key = 商户编号 + 区域编码
		for(ClientLineRpt rpt : areaTotalList) {
			String key = rpt.getMerchantNo() + rpt.getAreaCode();
			Integer total = areaTotalMap.get(key);
			if(total != null) {
				areaTotalMap.put(key, total + rpt.getNumLine());
			} else {
				areaTotalMap.put(key, rpt.getNumLine());
			}
		}
		
		// 增加或更新商户区域维度数据
		for(ClientLineRpt rpt : areaTotalList) {
			// 查询职业统计是否已存在
            FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
            findClientAnalyzeAndOthers.setMerchantNo(rpt.getMerchantNo());
            findClientAnalyzeAndOthers.setAreaCode(rpt.getAreaCode());
            findClientAnalyzeAndOthers.setCodeLine(rpt.getCodeLine());
            findClientAnalyzeAndOthers.setDimensionSt("AREA");
            List<ClientLineRpt> clientLineRpts = clientLineRptService.selectClientLineRptList(findClientAnalyzeAndOthers);
            ClientLineRpt clientLineRpt = !CollectionUtils.isEmpty(clientLineRpts) ? clientLineRpts.get(0) : null;
            if (clientLineRpt == null) {
                AddClientLineRpt addClientLineRpt = new AddClientLineRpt();
                addClientLineRpt.setMerchantNo(rpt.getMerchantNo());
                addClientLineRpt.setAreaCode(rpt.getAreaCode());
//                addClientLineRpt.setAreaName(rpt.getAreaName());
                addClientLineRpt.setCodeLine(rpt.getCodeLine());
                addClientLineRpt.setLineName(rpt.getLineName());
                addClientLineRpt.setRatioLine(new BigDecimal(getRatio(areaTotalMap.get(rpt.getMerchantNo() + rpt.getAreaCode()).longValue(), rpt.getNumLine().longValue())));
                addClientLineRpt.setNumLine(rpt.getNumLine());
                addClientLineRpt.setDimensionSt("AREA");
                clientLineRptService.addClientLineRpt(addClientLineRpt);
            }
            else {
            	ClientLineRpt update = new ClientLineRpt();
            	update.setCode(clientLineRpt.getCode());
            	update.setRatioLine(new BigDecimal(getRatio(areaTotalMap.get(rpt.getMerchantNo() + rpt.getAreaCode()).longValue(), rpt.getNumLine().longValue())));
            	update.setNumLine(rpt.getNumLine());
                clientLineRptService.updateClientLineRpt(update);
            }
		}
		logger.info("已生成商户区域维度客户职业统计报表");
		
		
		// 2、按商户维度统计
		// 根据商户区域维度数据统计商户数据
		List<ClientLineRpt> merchantTotalList = clientLineRptService.selectMerchantTotalByArea();
		if (CollectionUtils.isEmpty(merchantTotalList)) {
			return;
		}
		
		// 统计各商户职业总数
		Map<String, Integer> merchantTotalMap = new HashMap<String, Integer>(); // key = 商户编号
		for(ClientLineRpt rpt : merchantTotalList) {
			Integer total = merchantTotalMap.get(rpt.getMerchantNo());
			if(total != null) {
				merchantTotalMap.put(rpt.getMerchantNo(), total + rpt.getNumLine());
			} else {
				merchantTotalMap.put(rpt.getMerchantNo(), rpt.getNumLine());
			}
		}
		
		// 增加或更新商户维度数据
		for(ClientLineRpt rpt : merchantTotalList) {
			// 查询职业统计是否已存在
            FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
            findClientAnalyzeAndOthers.setMerchantNo(rpt.getMerchantNo());
            findClientAnalyzeAndOthers.setCodeLine(rpt.getCodeLine());
            findClientAnalyzeAndOthers.setDimensionSt("MERCHANT");
            List<ClientLineRpt> clientLineRpts = clientLineRptService.selectClientLineRptList(findClientAnalyzeAndOthers);
            ClientLineRpt clientLineRpt = !CollectionUtils.isEmpty(clientLineRpts) ? clientLineRpts.get(0) : null;
            if (clientLineRpt == null) {
                AddClientLineRpt addClientLineRpt = new AddClientLineRpt();
                addClientLineRpt.setMerchantNo(rpt.getMerchantNo());
                addClientLineRpt.setCodeLine(rpt.getCodeLine());
                addClientLineRpt.setLineName(rpt.getLineName());
                addClientLineRpt.setRatioLine(new BigDecimal(getRatio(merchantTotalMap.get(rpt.getMerchantNo()).longValue(), rpt.getNumLine().longValue())));
                addClientLineRpt.setNumLine(rpt.getNumLine());
                addClientLineRpt.setDimensionSt("MERCHANT");
                clientLineRptService.addClientLineRpt(addClientLineRpt);
            }
            else {
            	ClientLineRpt update = new ClientLineRpt();
            	update.setCode(clientLineRpt.getCode());
            	update.setRatioLine(new BigDecimal(getRatio(merchantTotalMap.get(rpt.getMerchantNo()).longValue(), rpt.getNumLine().longValue())));
            	update.setNumLine(rpt.getNumLine());
                clientLineRptService.updateClientLineRpt(update);
            }
		}
		logger.info("已生成商户维度客户职业统计报表");
		
		
		//XXX LEOPENG 待改造
		/*
		// 获取所有商户
		List<FindMerchantPageReturn> merchants = merchantService.findMerchants(new FindMerchantPage());
		for (FindMerchantPageReturn findMerchantPageReturn : merchants) {
			String merchantNo = findMerchantPageReturn.getMerchantNo();

			// 查询商户的职业统计是否已存在
			FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
			findClientAnalyzeAndOthers.setMerchantNo(merchantNo);
			findClientAnalyzeAndOthers.setDimensionSt("MERCHANT");
			findClientAnalyzeAndOthers.setCodeLine(mapResult.get("CODE_LINE"));
			List<ClientLineRpt> merchantCodeLineList = clientLineRptService.selectClientLineRptList(findClientAnalyzeAndOthers);
			ClientLineRpt clientLineRpt = !CollectionUtils.isEmpty(merchantCodeLineList) ? merchantCodeLineList.get(0) : null;

			//统计商户数据
			Map<String, String> map = new HashMap<String, String>();
			map.put("merchantNo", merchantNo);
			List<Map<String,String>> listResult = clientLineRptDao.selectMerchantTotal(map);
			for (Map<String, String> mapResult : listResult) {
				if (clientLineRpt == null) {
					AddClientLineRpt addClientLineRpt = new AddClientLineRpt();
					addClientLineRpt.setMerchantNo(merchantNo);
					addClientLineRpt.setCodeLine(mapResult.get("CODE_LINE"));
					addClientLineRpt.setLineName(mapResult.get("LINE_NAME"));
					addClientLineRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
					addClientLineRpt.setNumLine(codeNum);
					addClientLineRpt.setDimensionSt("MERCHANT");
					clientLineRptService.addClientLineRpt(addClientLineRpt);
				}
				else {
					clientLineRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
					clientLineRpt.setNumLine(codeNum);
					clientLineRptService.updateClientLineRpt(clientLineRpt);
				}
				
			}


			


			//统计分区数据
			Map<String,String> map = new HashMap<String,String>();
			String merchantNo = findMerchantPageReturn.getMerchantNo();
			map.put("merchantNo", merchantNo);
			// 查询商户下所有分店维度的数据
			List<ClientLineRpt> clientLineRpts = clientLineRptService.selectAllShopData(map);
			if (!CollectionUtils.isEmpty(clientLineRpts)) {
				// 获取所有的分店编号
				List<String> shopNoList = new ArrayList<>();
				for (ClientLineRpt each : clientLineRpts) {
					if (!shopNoList.contains(each.getShopNo())) {
						shopNoList.add(each.getShopNo());
					}
				}

				// 查找分店数据
				Map<String, FindShopPageReturn> shopMap = new HashMap<>();
				List<FindShopPageReturn> findShopPageReturns = shopService.selectByShopNoList(shopNoList);
				for (FindShopPageReturn each : findShopPageReturns) {
					shopMap.put(each.getShopNo(), each);
				}

				// 分组商户数据和区域数据
				Map<String, List<ClientLineRpt>> merchantMap = new HashMap<>();
				Map<String, List<ClientLineRpt>> areaMap = new HashMap<>();
				for (ClientLineRpt each : clientLineRpts) {
					FindShopPageReturn shop = shopMap.get(each.getShopNo());

					// 商户数据
					List<ClientLineRpt> merchantList = merchantMap.get(shop.getMemberNoMerchant());
					if (merchantList == null) {
						merchantList = new ArrayList<>();
						merchantMap.put(shop.getMemberNoMerchant(), merchantList);
					}
					merchantList.add(each);

					//XXX LEOPENG 区域数据，没有区分商户
					List<ClientLineRpt> areaList = areaMap.get(shop.getAreaCode());
					if (areaList == null) {
						areaList = new ArrayList<>();
						areaMap.put(shop.getAreaCode(), areaList);
					}
					areaList.add(each);
				}

				// 计算商户数据
				for (Map.Entry<String, List<ClientLineRpt>> entry : merchantMap.entrySet()) {
					String merchantNo = entry.getKey();

					// 按行业编号分类
					Integer totalNum = 0;
					Map<String, List<ClientLineRpt>> codeMap = new HashMap<>();
					List<ClientLineRpt> lineRptList = entry.getValue();
					for(ClientLineRpt each : lineRptList) {
						List<ClientLineRpt> codeList = codeMap.get(each.getAreaCode());
						if (codeList == null) {
							codeList = new ArrayList<>();
							codeMap.put(each.getAreaCode(), codeList);
						}
						codeList.add(each);
						totalNum += each.getNumLine();
					}

					// 保存每个商户的每个行业
					for(Map.Entry<String, List<ClientLineRpt>> codeEntry : codeMap.entrySet()) {
						String areaCode = codeEntry.getKey();

						// 计算每个行业的数量
						Integer codeNum = 0;
						String lineName = null;
						for (ClientLineRpt each : codeEntry.getValue()) {
							codeNum += each.getNumLine();
							if (lineName == null) {
								lineName = each.getLineName();
							}
						}

						// 查询商户的职业统计是否已存在
						FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
						findClientAnalyzeAndOthers.setMerchantNo(merchantNo);
						findClientAnalyzeAndOthers.setDimensionSt("MERCHANT");
						findClientAnalyzeAndOthers.setCodeLine(areaCode);
						List<ClientLineRpt> merchantCodeLineList = clientLineRptService.selectClientLineRptList(findClientAnalyzeAndOthers);
						ClientLineRpt clientLineRpt = !CollectionUtils.isEmpty(merchantCodeLineList) ? merchantCodeLineList.get(0) : null;
						if (clientLineRpt == null) {
							AddClientLineRpt addClientLineRpt = new AddClientLineRpt();
							addClientLineRpt.setMerchantNo(merchantNo);
							addClientLineRpt.setCodeLine(areaCode);
							addClientLineRpt.setLineName(lineName);
							addClientLineRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
							addClientLineRpt.setNumLine(codeNum);
							addClientLineRpt.setDimensionSt("MERCHANT");
							clientLineRptService.addClientLineRpt(addClientLineRpt);
						}
						else {
							clientLineRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
							clientLineRpt.setNumLine(codeNum);
							clientLineRptService.updateClientLineRpt(clientLineRpt);
						}
					}
				}

				// 计算区域数据
				for (Map.Entry<String, List<ClientLineRpt>> entry : areaMap.entrySet()) {
					String areaCode = entry.getKey();

					// 按行业编号分类
					Integer totalNum = 0;
					Map<String, List<ClientLineRpt>> codeMap = new HashMap<>();
					List<ClientLineRpt> lineRptList = entry.getValue();
					for(ClientLineRpt each : lineRptList) {
						List<ClientLineRpt> codeList = codeMap.get(each.getAreaCode());
						if (codeList == null) {
							codeList = new ArrayList<>();
							codeMap.put(each.getAreaCode(), codeList);
						}
						codeList.add(each);
						totalNum += each.getNumLine();
					}

					// 保存每个区域的每个行业
					for(Map.Entry<String, List<ClientLineRpt>> codeEntry : codeMap.entrySet()) {
						String codeLine = codeEntry.getKey();

						// 计算每个行业的数量
						Integer codeNum = 0;
						String lineName = null;
						for (ClientLineRpt each : codeEntry.getValue()) {
							codeNum += each.getNumLine();
							if (lineName == null) {
								lineName = each.getLineName();
							}
						}

						// 查询区域的职业统计是否已存在
						FindClientAnalyzeAndOthers findClientAnalyzeAndOthers = new FindClientAnalyzeAndOthers();
						findClientAnalyzeAndOthers.setAreaCode(areaCode);
						findClientAnalyzeAndOthers.setDimensionSt("AREA");
						findClientAnalyzeAndOthers.setCodeLine(codeLine);
						List<ClientLineRpt> areaCodeLineList = clientLineRptService.selectClientLineRptList(findClientAnalyzeAndOthers);
						ClientLineRpt clientLineRpt = !CollectionUtils.isEmpty(areaCodeLineList) ? areaCodeLineList.get(0) : null;
						if (clientLineRpt == null) {
							AddClientLineRpt addClientLineRpt = new AddClientLineRpt();
							addClientLineRpt.setMerchantNo(merchantNo);
							addClientLineRpt.setAreaCode(areaCode);
							addClientLineRpt.setCodeLine(codeLine);
							addClientLineRpt.setLineName(lineName);
							addClientLineRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
							addClientLineRpt.setNumLine(codeNum);
							addClientLineRpt.setDimensionSt("AREA");
							clientLineRptService.addClientLineRpt(addClientLineRpt);
						}
						else {
							clientLineRpt.setRatioLine(new BigDecimal(getRatio(totalNum, codeNum)));
							clientLineRpt.setNumLine(codeNum);
							clientLineRptService.updateClientLineRpt(clientLineRpt);
						}
					}
				}
			}
		}

	*/
		}

	/**
	 * 增加或更新商户和区域的客户画像
	 * 
	 * update by 曾垂瑜 2017-09-18 重写此方法
	 */
	private void modifyClientAnalyze() {
		// 1、按商户区域维度统计
		// 根据分店维度数据统计商户区域数据
		List<ClientAnalyze> areaTotalList = clientAnalyzeService.selectAreaTotalByShop();
		if (CollectionUtils.isEmpty(areaTotalList)) {
			return;
		}
		
		Date nowDate = new Date();
		
		// 增加或更新商户区域维度数据
		for(ClientAnalyze clientAnalyze : areaTotalList) {
			// 查询职业统计是否已存在
			FindClientAnalyzeAndOthers findClientAnalyze = new FindClientAnalyzeAndOthers();
            findClientAnalyze.setMerchantNo(clientAnalyze.getMerchantNo());
            findClientAnalyze.setAreaCode(clientAnalyze.getAreaCode());
            findClientAnalyze.setDimensionSt("AREA");
            ClientAnalyze clientRpt = clientAnalyzeService.findClientAnalyzeBase(findClientAnalyze);
            if (clientRpt == null) {
                clientAnalyze.setRatioMale(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumMale().longValue()));
                clientAnalyze.setRatioFemale(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumFemale().longValue()));
                clientAnalyze.setRatioAgeTen(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeTen().longValue()));
                clientAnalyze.setRatioAgeTwenty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeTwenty().longValue()));
                clientAnalyze.setRatioAgeThirty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeThirty().longValue()));
                clientAnalyze.setRatioAgeForty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeForty().longValue()));
                clientAnalyze.setRatioAgeFifty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeFifty().longValue()));
            	clientAnalyze.setDimensionSt("AREA");
            	clientAnalyze.setStDate(nowDate);
                clientAnalyzeService.addClientAnalyze(clientAnalyze);
            } else {
            	ClientAnalyze update = new ClientAnalyze();
            	update.setCode(clientRpt.getCode());
                update.setRatioMale(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumMale().longValue()));
                update.setNumMale(clientAnalyze.getNumMale());
                update.setRatioFemale(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumFemale().longValue()));
                update.setNumFemale(clientAnalyze.getNumFemale());
                update.setNumPm(clientAnalyze.getNumPm());
                update.setRatioAgeTen(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeTen().longValue()));
                update.setNumAgeTen(clientAnalyze.getNumAgeTen());
                update.setRatioAgeTwenty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeTwenty().longValue()));
                update.setNumAgeTwenty(clientAnalyze.getNumAgeTwenty());
                update.setRatioAgeThirty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeThirty().longValue()));
                update.setNumAgeThirty(clientAnalyze.getNumAgeThirty());
                update.setRatioAgeForty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeForty().longValue()));
                update.setNumAgeForty(clientAnalyze.getNumAgeForty());
                update.setRatioAgeFifty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeFifty().longValue()));
                update.setNumAgeFifty(clientAnalyze.getNumAgeFifty());
                clientAnalyze.setStDate(nowDate);
                clientAnalyzeService.updateClientAnalyze(update);
            }
		}
		logger.info("已生成商户区域维度客户画像");
		
		// 2、按商户维度统计
		// 根据商户区域维度数据统计商户数据
		List<ClientAnalyze> merchantTotalList = clientAnalyzeService.selectMerchantTotalByArea();
		if (CollectionUtils.isEmpty(merchantTotalList)) {
			return;
		}
		
		// 增加或更新商户维度数据
		for(ClientAnalyze clientAnalyze : merchantTotalList) {
			// 查询职业统计是否已存在
			FindClientAnalyzeAndOthers findClientAnalyze = new FindClientAnalyzeAndOthers();
            findClientAnalyze.setMerchantNo(clientAnalyze.getMerchantNo());
            findClientAnalyze.setDimensionSt("MERCHANT");
            ClientAnalyze clientRpt = clientAnalyzeService.findClientAnalyzeBase(findClientAnalyze);
            if (clientRpt == null) {
                clientAnalyze.setRatioMale(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumMale().longValue()));
                clientAnalyze.setRatioFemale(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumFemale().longValue()));
                clientAnalyze.setRatioAgeTen(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeTen().longValue()));
                clientAnalyze.setRatioAgeTwenty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeTwenty().longValue()));
                clientAnalyze.setRatioAgeThirty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeThirty().longValue()));
                clientAnalyze.setRatioAgeForty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeForty().longValue()));
                clientAnalyze.setRatioAgeFifty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeFifty().longValue()));
            	clientAnalyze.setDimensionSt("MERCHANT");
                clientAnalyzeService.addClientAnalyze(clientAnalyze);
            } else {
            	ClientAnalyze update = new ClientAnalyze();
            	update.setCode(clientRpt.getCode());
                update.setRatioMale(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumMale().longValue()));
                update.setNumMale(clientAnalyze.getNumMale());
                update.setRatioFemale(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumFemale().longValue()));
                update.setNumFemale(clientAnalyze.getNumFemale());
                update.setNumPm(clientAnalyze.getNumPm());
                update.setRatioAgeTen(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeTen().longValue()));
                update.setNumAgeTen(clientAnalyze.getNumAgeTen());
                update.setRatioAgeTwenty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeTwenty().longValue()));
                update.setNumAgeTwenty(clientAnalyze.getNumAgeTwenty());
                update.setRatioAgeThirty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeThirty().longValue()));
                update.setNumAgeThirty(clientAnalyze.getNumAgeThirty());
                update.setRatioAgeForty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeForty().longValue()));
                update.setNumAgeForty(clientAnalyze.getNumAgeForty());
                update.setRatioAgeFifty(getRatio(clientAnalyze.getNumPm(), clientAnalyze.getNumAgeFifty().longValue()));
                update.setNumAgeFifty(clientAnalyze.getNumAgeFifty());
                clientAnalyzeService.updateClientAnalyze(update);
            }
		}
		logger.info("已生成商户维度客户画像");
		
		
		/*// 查询所有分店维度的数据
		List<ClientAnalyze> shopClientAnalyzes = clientAnalyzeService.selectAllShopData();
		if (!CollectionUtils.isEmpty(shopClientAnalyzes)) {
			// 获取所有的分店编号
			List<String> shopNoList = new ArrayList<>();
			for (ClientAnalyze each : shopClientAnalyzes) {
				if (!shopNoList.contains(each.getShopNo())) {
					shopNoList.add(each.getShopNo());
				}
			}

			// 查找分店数据
			Map<String, FindShopPageReturn> shopMap = new HashMap<>();
			List<FindShopPageReturn> findShopPageReturns = shopService.selectByShopNoList(shopNoList);
			for (FindShopPageReturn each : findShopPageReturns) {
				shopMap.put(each.getShopNo(), each);
			}

			// 分组商户数据和区域数据
			Map<String, List<ClientAnalyze>> merchantMap = new HashMap<>();
			Map<String, List<ClientAnalyze>> areaMap = new HashMap<>();
			for (ClientAnalyze each : shopClientAnalyzes) {
				FindShopPageReturn shop = shopMap.get(each.getShopNo());

				// 商户数据
				List<ClientAnalyze> merchantList = merchantMap.get(shop.getMemberNoMerchant());
				if (merchantList == null) {
					merchantList = new ArrayList<>();
					merchantMap.put(shop.getMemberNoMerchant(), merchantList);
				}
				merchantList.add(each);

				// 区域数据
				List<ClientAnalyze> areaList = areaMap.get(shop.getAreaCode());
				if (areaList == null) {
					areaList = new ArrayList<>();
					areaMap.put(shop.getAreaCode(), areaList);
				}
				areaList.add(each);
			}

			// 计算商户数据
			for (Map.Entry<String, List<ClientAnalyze>> entry : merchantMap.entrySet()) {
				String merchantNo = entry.getKey();

				// 计算客户数
				Long totalNum = 0L;
				Long maleNum = 0L;
				Long femaleNum = 0L;
				Integer tenAgeCount = 0;
				Integer twentyAgeCount = 0;
				Integer thirtyAgeCount = 0;
				Integer fortyAgeCount = 0;
				Integer fiftyAgeCount = 0;
				List<ClientAnalyze> merchantList = entry.getValue();
				for (ClientAnalyze each : merchantList) {
					totalNum += each.getNumPm();
					maleNum += each.getNumMale();
					femaleNum += each.getNumFemale();
					tenAgeCount += each.getNumAgeTen();
					twentyAgeCount += each.getNumAgeTwenty();
					thirtyAgeCount += each.getNumAgeThirty();
					fortyAgeCount += each.getNumAgeForty();
					fiftyAgeCount += each.getNumAgeFifty();
				}
				Integer totalAgeCount = tenAgeCount + twentyAgeCount + thirtyAgeCount + fortyAgeCount + fiftyAgeCount;

				// 查询商户的统计是否已存在
				ClientAnalyze shopClientAnalyze = clientAnalyzeService.findByMerchantNo(merchantNo);
				if (shopClientAnalyze == null) {
					AddClientAnalyze addClientAnalyze = new AddClientAnalyze();
					addClientAnalyze.setMerchantNo(merchantNo);
					addClientAnalyze.setStDate(new Date());
					addClientAnalyze.setRatioMale(getRatio(totalNum, maleNum));
					addClientAnalyze.setRatioFemale(getRatio(totalNum, femaleNum));
					addClientAnalyze.setNumPm(totalNum);

					addClientAnalyze.setNumAgeTen(tenAgeCount);
					addClientAnalyze.setNumAgeTwenty(twentyAgeCount);
					addClientAnalyze.setNumAgeThirty(thirtyAgeCount);
					addClientAnalyze.setNumAgeForty(fortyAgeCount);
					addClientAnalyze.setNumAgeFifty(fiftyAgeCount);

					addClientAnalyze.setRatioAgeTen(getRatio(totalAgeCount, tenAgeCount));
					addClientAnalyze.setRatioAgeTwenty(getRatio(totalAgeCount, twentyAgeCount));
					addClientAnalyze.setRatioAgeThirty(getRatio(totalAgeCount, thirtyAgeCount));
					addClientAnalyze.setRatioAgeForty(getRatio(totalAgeCount, fortyAgeCount));
					addClientAnalyze.setRatioAgeFifty(getRatio(totalAgeCount, fiftyAgeCount));

					addClientAnalyze.setDimensionSt("MERCHANT");
					addClientAnalyze.setNumMale(maleNum.intValue());
					addClientAnalyze.setNumFemale(femaleNum.intValue());
					clientAnalyzeService.addClientAnalyze(addClientAnalyze);
				}
				else {
					shopClientAnalyze.setRatioMale(getRatio(totalNum, maleNum));
					shopClientAnalyze.setRatioFemale(getRatio(totalNum, femaleNum));
					shopClientAnalyze.setNumPm(totalNum);

					shopClientAnalyze.setNumAgeTen(tenAgeCount);
					shopClientAnalyze.setNumAgeTwenty(twentyAgeCount);
					shopClientAnalyze.setNumAgeThirty(thirtyAgeCount);
					shopClientAnalyze.setNumAgeForty(fortyAgeCount);
					shopClientAnalyze.setNumAgeFifty(fiftyAgeCount);

					shopClientAnalyze.setRatioAgeTen(getRatio(totalAgeCount, tenAgeCount));
					shopClientAnalyze.setRatioAgeTwenty(getRatio(totalAgeCount, twentyAgeCount));
					shopClientAnalyze.setRatioAgeThirty(getRatio(totalAgeCount, thirtyAgeCount));
					shopClientAnalyze.setRatioAgeForty(getRatio(totalAgeCount, fortyAgeCount));
					shopClientAnalyze.setRatioAgeFifty(getRatio(totalAgeCount, fiftyAgeCount));
					shopClientAnalyze.setNumMale(maleNum.intValue());
					shopClientAnalyze.setNumFemale(femaleNum.intValue());
					clientAnalyzeService.updateClientAnalyze(shopClientAnalyze);
				}
			}

			// 计算区域数据
			for (Map.Entry<String, List<ClientAnalyze>> entry : areaMap.entrySet()) {
				String areaCode = entry.getKey();
                
				// 计算客户数
				Long totalNum = 0L;
				Long maleNum = 0L;
				Long femaleNum = 0L;
				Integer tenAgeCount = 0;
				Integer twentyAgeCount = 0;
				Integer thirtyAgeCount = 0;
				Integer fortyAgeCount = 0;
				Integer fiftyAgeCount = 0;
				String merchantNo=null;
				String areaName=null;
				List<ClientAnalyze> merchantList = entry.getValue();
				for (ClientAnalyze each : merchantList) {
					merchantNo=each.getMerchantNo();
					areaName=each.getAreaName();
					
					totalNum += each.getNumPm();
					maleNum += each.getNumMale();
					femaleNum += each.getNumFemale();
					tenAgeCount += each.getNumAgeTen();
					twentyAgeCount += each.getNumAgeTwenty();
					thirtyAgeCount += each.getNumAgeThirty();
					fortyAgeCount += each.getNumAgeForty();
					fiftyAgeCount += each.getNumAgeFifty();
				}
				Integer totalAgeCount = tenAgeCount + twentyAgeCount + thirtyAgeCount + fortyAgeCount + fiftyAgeCount;

				// 查询商户的统计是否已存在
				ClientAnalyze areaClientAnalyze = clientAnalyzeService.findByAreaCode(areaCode);
				if (areaClientAnalyze == null) {
					AddClientAnalyze addClientAnalyze = new AddClientAnalyze();
					addClientAnalyze.setMerchantNo(merchantNo);
					addClientAnalyze.setAreaName(areaName);
					
					addClientAnalyze.setAreaCode(areaCode);
					addClientAnalyze.setStDate(new Date());
					addClientAnalyze.setRatioMale(getRatio(totalNum, maleNum));
					addClientAnalyze.setRatioFemale(getRatio(totalNum, femaleNum));
					addClientAnalyze.setNumPm(totalNum);
					addClientAnalyze.setNumAgeTen(tenAgeCount);
					addClientAnalyze.setNumAgeTwenty(twentyAgeCount);
					addClientAnalyze.setNumAgeThirty(thirtyAgeCount);
					addClientAnalyze.setNumAgeForty(fortyAgeCount);
					addClientAnalyze.setNumAgeFifty(fiftyAgeCount);

					addClientAnalyze.setRatioAgeTen(getRatio(totalAgeCount, tenAgeCount));
					addClientAnalyze.setRatioAgeTwenty(getRatio(totalAgeCount, twentyAgeCount));
					addClientAnalyze.setRatioAgeThirty(getRatio(totalAgeCount, thirtyAgeCount));
					addClientAnalyze.setRatioAgeForty(getRatio(totalAgeCount, fortyAgeCount));
					addClientAnalyze.setRatioAgeFifty(getRatio(totalAgeCount, fiftyAgeCount));

					addClientAnalyze.setDimensionSt("AREA");
					addClientAnalyze.setNumMale(maleNum.intValue());
					addClientAnalyze.setNumFemale(femaleNum.intValue());
					clientAnalyzeService.addClientAnalyze(addClientAnalyze);
				}
				else {
					areaClientAnalyze.setRatioMale(getRatio(totalNum, maleNum));
					areaClientAnalyze.setRatioFemale(getRatio(totalNum, femaleNum));
					areaClientAnalyze.setNumPm(totalNum);

					areaClientAnalyze.setNumAgeTen(tenAgeCount);
					areaClientAnalyze.setNumAgeTwenty(twentyAgeCount);
					areaClientAnalyze.setNumAgeThirty(thirtyAgeCount);
					areaClientAnalyze.setNumAgeForty(fortyAgeCount);
					areaClientAnalyze.setNumAgeFifty(fiftyAgeCount);

					areaClientAnalyze.setRatioAgeTen(getRatio(totalAgeCount, tenAgeCount));
					areaClientAnalyze.setRatioAgeTwenty(getRatio(totalAgeCount, twentyAgeCount));
					areaClientAnalyze.setRatioAgeThirty(getRatio(totalAgeCount, thirtyAgeCount));
					areaClientAnalyze.setRatioAgeForty(getRatio(totalAgeCount, fortyAgeCount));
					areaClientAnalyze.setRatioAgeFifty(getRatio(totalAgeCount, fiftyAgeCount));
					areaClientAnalyze.setNumMale(maleNum.intValue());
					areaClientAnalyze.setNumFemale(femaleNum.intValue());
					clientAnalyzeService.updateClientAnalyze(areaClientAnalyze);
				}
			}
		}*/
	}

	

}