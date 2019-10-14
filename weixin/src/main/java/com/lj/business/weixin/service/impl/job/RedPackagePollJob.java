/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.service.impl.job;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.business.supcon.dto.redpacket.FindRedPacketMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IRedPacketService;
import com.lj.business.weixin.dao.IWxRedpackDetailInfoDao;
import com.lj.business.weixin.domain.WxRedpackDetailInfo;
import com.lj.cc.clientintf.IJob;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.cc.service.ISystemInfoService;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 类说明：发红包结果轮询任务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月3日
 */
@Service
public class RedPackagePollJob implements IJob {
	
	private static final Logger logger = LoggerFactory.getLogger(RedPackagePollJob.class);
	@Autowired 
	ICommonService commonService;
	@Resource
	private IWxRedpackDetailInfoDao wxRedpackDetailInfoDao;
	
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
    
    
    @Autowired 
 	private ISystemInfoService systemInfo;
     
     @Autowired 
 	private RedisCache redisCache;

	
	@Override
	public void runJob() {
		List<WxRedpackDetailInfo> detailList = null;
		try {
			Map<String, String> paramMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "redPackJob");
			int maxPollCount = Integer.valueOf(StringUtils.toString(paramMap.get("maxPollCount"), "3"));		// 最大轮询次数
			int pollBeginHours = Integer.valueOf(StringUtils.toString(paramMap.get("pollBeginHours"), "24"));	// 轮询pollBeginHours小时内的红包
			Date now = new Date();
			Date sendDateBegin = DateUtils.addHours(now, -pollBeginHours);
			Date sendDateEnd = DateUtils.addMinutes(now, -2);
			// 查询轮询次数小于maxPollCount，发送时间从sendDateBegin到sendDateEnd的发送中红包明细记录
			detailList = wxRedpackDetailInfoDao.findRedpackDetailByPoll(maxPollCount, sendDateBegin, sendDateEnd);
		} catch(Exception e) {
			logger.error("查询待轮询红包明细失败", e);
			return;
		}
		if(null == detailList || detailList.isEmpty()) {
			return;
		}
		
		FindRedPacketMessage message = new FindRedPacketMessage();
		for(WxRedpackDetailInfo detail : detailList) {
			logger.debug("请求中控服务器查询红包：{}", detail.getCode());
			message.setRpCode(detail.getCode());
			message.setNoWxShop(detail.getNoWxShop());
			try {
				IRedPacketService basic = commonService.getHessianIRedPacketService(message.getNoWxShop());
				basic.sendFindRedpacketMessage(message);
			} catch(Exception e) {
				logger.error("请求中控服务器查询红包失败", e);
			}
		}
	}
	
	


}
