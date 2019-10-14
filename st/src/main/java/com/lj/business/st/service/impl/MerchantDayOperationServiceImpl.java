package com.lj.business.st.service.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.DateUtils;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.st.dto.MerchantDayOperateDto;
import com.lj.business.st.dto.ShopDayOperateDto;
import com.lj.business.st.service.IMerchantDayOperationService;
import com.lj.business.st.utils.DateUtil;
import com.lj.business.weixin.service.IWxChatInfoService;

/**
 * 类说明：商户运营日报报表
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 梅宏博
 * 
 * 
 *         CreateDate: 2017-09-27
 */
@Service
public class MerchantDayOperationServiceImpl implements
		IMerchantDayOperationService {
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MerchantDayOperationServiceImpl.class);
	
	@Resource
	private IMerchantService merchantService;//商户表
	
	@Resource
	private IGuidMemberService guidMemberService;//导购表
	
//	@Resource
//	private IClientFollowSummaryService clientFollowSummaryService;// 客户跟踪表
	
//	@Resource
//	private IClientFollowService clientFollowService;//客户跟踪明细表
	
	@Resource
	private IPersonMemberService personMemberService;//客户导购关联表
	
//	@Resource
//	private IComTaskService comTaskService;//业务任务表
//	
//	@Resource
//	private ISocialTaskService socialTaskService;//社交任务表
//	
//	@Resource
//	private IClientInviteService clientInviteService;//邀约任务表
//	
//	@Resource
//	private IClientExpService clientExpService;//到店体验表
	
	@Resource
	private IWxChatInfoService wxChatInfoService;//微信聊天记录表
	
	@Override
	public MerchantDayOperateDto generatorMerChantDayOperate(String merchantCode) {
		logger.info("统计商户{}运营日报表开始", merchantCode);
		try {
			Date queryDate = DateUtils.getPreday(new Date());//查询昨天数据
			
			MerchantDayOperateDto returnData = new MerchantDayOperateDto();
			returnData.setMerchantNo(merchantCode);
			
			FindMerchantDto findMerchantDto = new FindMerchantDto();
			findMerchantDto.setMerchantNo(merchantCode);
			returnData.setMerchantName(merchantService.selectMerchant(findMerchantDto).getMerchantName());//商户名称
			
//			FindShopDto findShopDto = new FindShopDto();
//			findShopDto.setMemberNoMerchant(merchantCode);
//			returnData.setShopNum(shopService.findShopCounts(findShopDto));//门店数量
			
			FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
			findGuidMemberPage.setMerchantNo(merchantCode);
			returnData.setGuidNum(guidMemberService.findGuidMemberCount(findGuidMemberPage));//商户下导购数量
			
			Map<String, Object> map = DateUtil.getDayBeginAndEnd(queryDate);
			map.put("merchantNo", merchantCode);
//			returnData.setDaySale(clientFollowSummaryService.findNumSaleByGm(map));//销售额
			
			returnData.setShopData(getShopData(merchantCode));//商户下各门店数据
			
			returnData.setQueryDate(DateUtils.formatDate(queryDate, DateUtils.PATTERN_yyyy_MM_dd));
			logger.info("统计商户{}运营日报表结束，数据：{}", merchantCode, returnData);
			return returnData;
		} catch (Exception e) {
			logger.error("统计商户{}的运营日报错误", merchantCode, e);
		}
		return null;
	}
	
	//获取该商户下所有门店数据
	private Map<String, ShopDayOperateDto> getShopData(String merchantCode) throws Exception {
		Map<String, ShopDayOperateDto> shopData = new HashMap<>();
		
		Map<String, Object> baseParam = DateUtil.getDayBeginAndEnd(DateUtils.getPreday(new Date()));//基础参数
		baseParam.put("merchantCode", merchantCode);
		
		List<Map<String, Object>> pmNum = personMemberService.findShopPmNum(baseParam);//新增客户数
		convertData(shopData, pmNum);
		
		List<Map<String, Object>> pmIntentionNum = personMemberService.findCountAddIntention(baseParam);//新增意向客户数
		convertData(shopData, pmIntentionNum);
		
		List<Map<String, Object>> pmOtherNum = personMemberService.findCountAddOther(baseParam);//新增非意向客户数
		convertData(shopData, pmOtherNum);
		
//		List<Map<String, Object>> pmOrderNum = clientFollowSummaryService.findCountPmOrder(baseParam);//成单客户数
//		convertData(shopData, pmOrderNum);
		
		List<Map<String, Object>> pmAbandonNum = personMemberService.finCountAddPmAbandon(baseParam);//暂停客户数
		convertData(shopData, pmAbandonNum);
		
		List<Map<String, Object>> pmUngroupNum = personMemberService.findcountAddPmUngroup(baseParam);//未分组客户数
		convertData(shopData, pmUngroupNum);
		
//		List<Map<String, Object>> sale = clientFollowSummaryService.findNumSaleGroupByShop(baseParam);//销售额
//		convertData(shopData, sale);
		
//		List<Map<String, Object>> followSum = clientFollowService.findSumFollowGroupByShop(baseParam);//跟踪次数
//		convertData(shopData, followSum);
		
//		List<Map<String, Object>> maxFollowSum = clientFollowSummaryService.findMaxSumFollow(baseParam);//最高跟踪次数
//		convertData(shopData, maxFollowSum);
		
//		List<Map<String, Object>> comTaskProduceNum = comTaskService.findComTaskProduce(baseParam);//业务任务生产量
//		convertData(shopData, comTaskProduceNum);
		
//		List<Map<String, Object>> comTaskFinishNum = comTaskService.findComTaskFinish(baseParam);//业务任务完成量
//		convertData(shopData, comTaskFinishNum);
		
//		List<Map<String, Object>> socialTaskProduceNum = socialTaskService.findSocialTaskProduce(baseParam);//社交任务生产量
//		convertData(shopData, socialTaskProduceNum);
		
//		List<Map<String, Object>> socialTaskFinishNum = socialTaskService.findSocialTaskFinish(baseParam);//社交任务生产量
//		convertData(shopData, socialTaskFinishNum);
		
//		List<Map<String, Object>> inviteFinishNum = clientInviteService.findInviteFinish(baseParam);//邀约完成量
//		convertData(shopData, inviteFinishNum);
		
//		List<Map<String, Object>> promiseInviteNum = clientInviteService.findPromiseInvite(baseParam);//应邀数量
//		convertData(shopData, promiseInviteNum);
		
//		List<Map<String, Object>> expNum = clientExpService.findExpNum(baseParam);//到店体验量
//		convertData(shopData, expNum);
		
		List<Map<String, Object>> wxChatNum = wxChatInfoService.findCountWxChat(baseParam);//微信聊天次数
		convertData(shopData, wxChatNum);
		
		//计算   成单率  = 成单客户数  / (意向客户数 + 成单客户数)
		for (Entry<String, ShopDayOperateDto> entry : shopData.entrySet()) {
			ShopDayOperateDto shopDayOperateDto = entry.getValue();
			shopDayOperateDto.setOrderRatio(
					(shopDayOperateDto.getNumPmIntention() + shopDayOperateDto.getNumOrderPm()) == 0 ? 0 :
					(int)((double)shopDayOperateDto.getNumOrderPm()) 
					/ (shopDayOperateDto.getNumPmIntention() + shopDayOperateDto.getNumOrderPm()) * 100);
		}
		return shopData;
	}
	
	//为每个字段封装数据
	private void convertData(Map<String, ShopDayOperateDto> shopData, List<Map<String, Object>> paramDatas) throws Exception{
		
		String mapKey = "shopNo";
		
		for (Map<String, Object> paramData : paramDatas) {
			//当门店号为空时跳过
			if (StringUtils.isEmpty(paramData.get(mapKey).toString())) {
				continue;
			}
			
			ShopDayOperateDto shopDayOperateDto = shopData.get(paramData.get(mapKey).toString());
			if (shopDayOperateDto == null) {
				shopDayOperateDto = new ShopDayOperateDto();
				shopData.put(paramData.get(mapKey).toString(), shopDayOperateDto);
			}
			
			Class<? extends ShopDayOperateDto> clazz = shopDayOperateDto.getClass();
			
			//为每个字段赋值
			for (Entry<String, Object> entry : paramData.entrySet()) {
				Field field = clazz.getDeclaredField(entry.getKey());
				field.setAccessible(true);
				Class<?> type = field.getType();
				Object param = paramData.get(entry.getKey());
				if (type.equals(int.class)) {//当前类型不为字符串类型则为number类型中的一种，
					param = Integer.valueOf(paramData.get(entry.getKey()) == null ? "0" : paramData.get(entry.getKey()).toString());
				} else if (type.equals(long.class)){
					param = Long.valueOf(paramData.get(entry.getKey()) == null ? "0" : paramData.get(entry.getKey()).toString());
				} else if(type.equals(double.class)){
					param = Double.valueOf(paramData.get(entry.getKey()) == null ? "0" : paramData.get(entry.getKey()).toString());
				}
				field.set(shopDayOperateDto, param);
			}
		}
		
		
	}

}
