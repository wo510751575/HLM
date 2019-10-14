package com.lj.business.weixin.service.impl.job.handler;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.lj.base.core.util.AssertUtils;
import com.lj.business.supcon.dto.friends.AddRedPackMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IRedPackFacade;
import com.lj.business.weixin.dto.FindWxRedpackDetailInfoPage;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;
import com.lj.business.weixin.emus.RedPackStatus;
import com.lj.business.weixin.emus.RedPackTypeEnum;
import com.lj.business.weixin.service.IWXJobHandler;
import com.lj.business.weixin.service.IWxJobInfoService;
import com.lj.business.weixin.service.IWxJobRedPackInfoService;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;
import com.lj.cc.service.ISystemInfoService;
import com.lj.distributecache.RedisCache;
import com.lj.distributecache.RedisCacheConfigFromCC;
import com.lj.distributelock.RedisLock;

@Service("redPackJobHandler")
public class RedPackJobHandler implements IWXJobHandler {
	
	
	private static Logger LOG = LoggerFactory.getLogger(RedPackJobHandler.class);

	@Resource
	IWxRedpackDetailInfoService wxRedpackDetailInfoService;
	
	@Autowired 
	ICommonService commonService;
	
	@Resource
	IWxJobInfoService wxJobInfoService;
	
	@Resource
	IWxJobRedPackInfoService wxJobRedPackInfoService;
	

	
	@Resource
	RedisCacheConfigFromCC redisCacheConfigFromCC;
	
	@Resource
	RedisLock redisLock;
	
    @Autowired 
	private ISystemInfoService systemInfo;
    
    @Autowired 
	private RedisCache redisCache;


	
	@Override
	public void toHandler(String batchCode) {
		LOG.info("RedPackJobHandler  ---- >toHandler  :{} ",batchCode);
		FindWxRedpackDetailInfoPage findWxRedpackDetailInfoPage = new FindWxRedpackDetailInfoPage();
		findWxRedpackDetailInfoPage.setBatchCode(batchCode);
		findWxRedpackDetailInfoPage.setStatus(RedPackStatus.NOSEND.getStatus());
		List<WxRedpackDetailInfoDto> details  = wxRedpackDetailInfoService.findWxRedpackDetailInfos(findWxRedpackDetailInfoPage);
		WxJobRedPackInfoDto wxJobRedPackInfoDto  = new WxJobRedPackInfoDto();
		wxJobRedPackInfoDto.setCode(batchCode);
		wxJobRedPackInfoDto = wxJobRedPackInfoService.findWxJobRedPackInfo(wxJobRedPackInfoDto);
		AssertUtils.notNull(findWxRedpackDetailInfoPage);
//		String lastRedCode=null;
		for (WxRedpackDetailInfoDto wxRedpackDetailInfoDto : details) {
			try {
				//redisLock.getLockUntilTimeout(wxRedpackDetailInfoDto.getNoWxShop()+"_RED_PACK_QUEUE",31000,30);
				Thread.sleep(30000);
			} catch (Exception e) {
				LOG.warn("锁超时  ---   继续发送红包:{} ",wxRedpackDetailInfoDto,e);
			} 
				AddRedPackMessage addRedPackMessage = new AddRedPackMessage();
				addRedPackMessage.setRpCode(wxRedpackDetailInfoDto.getCode());
				addRedPackMessage.setNoWx(wxRedpackDetailInfoDto.getNoWx());
				addRedPackMessage.setNoWxShop(wxRedpackDetailInfoDto.getNoWxShop());
				addRedPackMessage.setType(wxJobRedPackInfoDto.getRedpackType());
				addRedPackMessage.setDes(wxRedpackDetailInfoDto.getContent());
				addRedPackMessage.setAmt(wxRedpackDetailInfoDto.getAmount());
				wxRedpackDetailInfoDto.setSendDate(new Date());
				wxRedpackDetailInfoDto.setStatus(RedPackStatus.SENDING.getStatus());
				wxRedpackDetailInfoService.updateWxRedpackDetailInfo(wxRedpackDetailInfoDto);
//				lastRedCode = wxRedpackDetailInfoDto.getCode();
				LOG.info("sendRedPackRequest success ------ > wxRedpackDetailInfoDto:{}",wxRedpackDetailInfoDto);
				LOG.debug(" begin sendRedPackRequest   ----->:{} ",addRedPackMessage);
				
				IRedPackFacade basic = commonService.getHessianIRedPackFacade(addRedPackMessage.getNoWxShop());
				basic.sendRedPackRequest(addRedPackMessage);
		}
		LOG.info("end  RedPackJobHandler  ------ > toHandler:{}",wxJobRedPackInfoDto);
	}



	@Override
	public void reHandler(String detailCode) {
		LOG.info(" rehandler ------------- >{}",detailCode);
		WxRedpackDetailInfoDto wxRedpackDetailInfoDto = new WxRedpackDetailInfoDto();
		wxRedpackDetailInfoDto.setCode(detailCode);
		wxRedpackDetailInfoDto = wxRedpackDetailInfoService.findWxRedpackDetailInfo(wxRedpackDetailInfoDto);
		AssertUtils.notNull(wxRedpackDetailInfoDto,"红包不存在");
		AddRedPackMessage addRedPackMessage = new AddRedPackMessage();
		addRedPackMessage.setRpCode(wxRedpackDetailInfoDto.getCode());
		addRedPackMessage.setNoWx(wxRedpackDetailInfoDto.getNoWx());
		addRedPackMessage.setNoWxShop(wxRedpackDetailInfoDto.getNoWxShop());
		addRedPackMessage.setType(RedPackTypeEnum.NORMAL.getType());
		addRedPackMessage.setDes(wxRedpackDetailInfoDto.getContent());
		addRedPackMessage.setAmt(wxRedpackDetailInfoDto.getAmount());
		LOG.debug(" begin sendRedPackRequest   ----->:{} ",addRedPackMessage);
		
		IRedPackFacade basic = commonService.getHessianIRedPackFacade(addRedPackMessage.getNoWxShop());
		basic.sendRedPackRequest(addRedPackMessage);
		wxRedpackDetailInfoDto.setSendDate(new Date());
		wxRedpackDetailInfoDto.setStatus(RedPackStatus.SENDING.getStatus());
		wxRedpackDetailInfoService.updateWxRedpackDetailInfo(wxRedpackDetailInfoDto);
		LOG.info("sendRedPackRequest success ------ > wxRedpackDetailInfoDto:{}",wxRedpackDetailInfoDto);
	}

	
	
	
}
