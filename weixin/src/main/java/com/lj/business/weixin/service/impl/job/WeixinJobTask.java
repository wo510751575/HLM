package com.lj.business.weixin.service.impl.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.business.weixin.dao.IWxJobInfoDao;
import com.lj.business.weixin.dao.IWxJobRedPackInfoDao;
import com.lj.business.weixin.domain.WxJobInfo;
import com.lj.business.weixin.domain.WxJobRedPackInfo;
import com.lj.business.weixin.service.IWXJobHandler;
import com.lj.cc.clientintf.IParamJob;

@Service
public class WeixinJobTask implements IParamJob {
	
	private static Logger LOG = LoggerFactory.getLogger(WeixinJobTask.class);
	
	@Resource
	IWxJobInfoDao wxJobInfoDao;
	
	@Resource
	IWxJobRedPackInfoDao wxJobRedPackInfoDao;
	
	@Resource
	IWXJobHandler wxJobHandler;
	
	
	
	@Override
	public void runParamJob(String param) {
		LOG.info(" begin WeixinJobTask ----------------- > code:{}",param);
		WxJobRedPackInfo wxJobRedPackInfo = wxJobRedPackInfoDao.selectByPrimaryKey(param);
		LOG.info(" get wxJobRedPackInfo --------------->:{}",wxJobRedPackInfo);
		WxJobInfo wxJobInfo = wxJobInfoDao.selectByPrimaryKey(wxJobRedPackInfo.getJobCode());
		wxJobHandler.toHandler(wxJobRedPackInfo.getCode());
		LOG.info("toHandler    ----------------->:{}",wxJobInfo);
	}

}
