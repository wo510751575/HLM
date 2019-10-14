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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cm.emus.MaterialType;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.domain.AddFriends;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.weixin.dao.IMerchantSendFriendsJobDao;
import com.lj.business.weixin.dao.ISendFriendsJobDao;
import com.lj.business.weixin.domain.MerchantSendFriendsJob;
import com.lj.business.weixin.domain.SendFriendsJob;
import com.lj.business.weixin.dto.ToFriendsInfosDto;
import com.lj.business.weixin.dto.friendsjob.SendFriendsTaskDto;
import com.lj.business.weixin.emus.FriendsInfoType;
import com.lj.business.weixin.emus.SendFriendsJobStatus;
import com.lj.business.weixin.emus.WhoType;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.cc.clientintf.IParamJob;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月22日
 */
@Service
public class SendFriendsJobTask implements IParamJob {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(SendFriendsJobTask.class);
    
    @Autowired
    private ISendFriendsJobDao sendFriendsJobDao;
    @Autowired
    private IImFriendsFacade iImFriendsFacade;
	@Autowired
	private IAddFriendsService addFriendsService;
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
    @Autowired
    private IMerchantSendFriendsJobDao merchantSendFriendsJobDao;
    
    public void runParamJob(String param) {
		this.doJob(param);
		
	}
    /**
     * 发送朋友圈
     */
    public synchronized void doJob(String param) {
        
    	logger.debug("发送朋友圈任务-doJob(String param={}) - start"); //$NON-NLS-1$

        SendFriendsTaskDto sendFriendsTaskDto = (SendFriendsTaskDto) JsonUtils.objectFromJson(param, SendFriendsTaskDto.class);

        SendFriendsJob taskDto = sendFriendsJobDao.selectByPrimaryKey(sendFriendsTaskDto.getCode());

        //发送朋友圈
        try {
        	        //图片地址转换为全路径url
                    Map<String, String> map = localCacheSystemParams.getSystemParamGroup("weixin", "imfile");
	                ToFriendsInfosDto toFriendsInfosDto = new ToFriendsInfosDto();
	                
	                if(StringUtils.isEmpty(taskDto.getMerchantJobCode())) {
	                	toFriendsInfosDto.setMemberNoGm(taskDto.getCreateId());
	                }
	                
	                toFriendsInfosDto.setNoWxShop(taskDto.getNoWxs());
	                
	                logger.debug("发送朋友圈任务查询数据",toFriendsInfosDto);
	                
	                toFriendsInfosDto.setJobCode(taskDto.getCode());
	                String content = taskDto.getContent();
	                toFriendsInfosDto.setContent(content);//内容
	                
	                StringBuilder sb = new StringBuilder("");
	                
	                //非纯文字朋友圈才需要处理资源
	                if(!MaterialType.TEXT.getType().equals(taskDto.getType())) {
                		String imgAddr = taskDto.getImgAddr();
                		if(StringUtils.isNotEmpty(imgAddr)) {
		                	//图片地址转换为全路径url
		                    String[] imgUriArr = imgAddr.split(",");
		                    for (String uri : imgUriArr) {
		                    	if(!uri.startsWith("http")) {
		                    		uri = map.get("uploadUrl") + uri;
		                    	}
		                        sb.append(uri + ",");
		                    }
		                    if (sb.length() > 1) {
		                        sb.deleteCharAt(sb.length() - 1);
		                    }
		                    toFriendsInfosDto.setImgAddr(sb.toString());
	                	}
	                }else {
	                	toFriendsInfosDto.setType(FriendsInfoType.MESSAGE.getValue());
	                }
		            
		            
		            if(MaterialType.IMAGE.getType().equals(taskDto.getType())) {
	                    toFriendsInfosDto.setType(FriendsInfoType.PIC.getValue());
	                    //没有传图片的按纯文字发朋友圈
	                    if(StringUtils.isEmpty(sb.toString())) {
	                    	toFriendsInfosDto.setType(FriendsInfoType.MESSAGE.getValue());
	                    }
		            } 
		            if(MaterialType.VIDEO.getType().equals(taskDto.getType())) {
		            	toFriendsInfosDto.setType(FriendsInfoType.VIDEO.getValue());
		            	String linkUrl = taskDto.getLinkUrl();
		            	if(!linkUrl.startsWith("http")) {
		            		linkUrl = map.get("uploadUrl") +linkUrl;
                    	}
		            	toFriendsInfosDto.setShareurl(linkUrl);
		            	if (taskDto.getLinkUrl() != null) {
		            		if (StringUtils.isEmpty(taskDto.getRemark())) {//老的链接素材remark为空，取content的前30个字符
		            			String shareTitle = content.length() > 30 ? content.substring(0, 30) : content;
		            			toFriendsInfosDto.setSharetitle(shareTitle);
		            		} else {
		            			toFriendsInfosDto.setSharetitle(taskDto.getRemark());
		            		}
		            	}
		            }
		            
		           if(MaterialType.LINK.getType().equals(taskDto.getType())) {
		            	toFriendsInfosDto.setType(FriendsInfoType.MESSAGE.getValue());
		            	toFriendsInfosDto.setShareurl(taskDto.getLinkUrl());
		            	if (taskDto.getLinkUrl() != null) {
		            		toFriendsInfosDto.setType(FriendsInfoType.SHARE.getValue());
		            		toFriendsInfosDto.setShareurl(taskDto.getLinkUrl());
		            		if (StringUtils.isEmpty(taskDto.getRemark2())) {//老的链接素材remark为空，取content的前30个字符
		            			String shareTitle = content.length() > 30 ? content.substring(0, 30) : content;
		            			toFriendsInfosDto.setSharetitle(shareTitle);
		            		} else {
		            			toFriendsInfosDto.setSharetitle(taskDto.getRemark2());
		            		}
		            	}
		            } 
		            
		          //如果自动评论，设置自动评论的内容
	                if (Integer.valueOf(CommonConstant.I_YES).equals(taskDto.getAutoComment())) {
	                    String autoContent = taskDto.getAutoContent();
	                    toFriendsInfosDto.setAutoContent(autoContent);
	                }
	        
	                //此处需要加老板级别代码判断
	                //如果为公司老板或者某级别可公开所有客户可见
	                if(taskDto.getCreateUserLevel() == null || String.valueOf(CommonConstant.I_YES).equals(taskDto.getCreateUserLevel())) {
	                	toFriendsInfosDto.setWhoType(WhoType.OPEN.getValue());// 谁可以看类型1.公开2.私密3.部分可见4.不给谁看
	                }else {
	                	//如果为员工级别-查询自己认领的客户
	                	String gmNo = taskDto.getCreateId();
	                	List<AddFriends> addFriendsList = addFriendsService.findClaimMemberList(gmNo);
	                	
	                	int i =0 ;
	                	String whoNoWxs = "";
	                    for(AddFriends pm : addFriendsList) {
	                    	 if(i ==0) {
	                    		 whoNoWxs = pm.getNoWx();
	                    	 }else {
	                    		 whoNoWxs = whoNoWxs +"," +pm.getNoWx();
	                    	 }
	                    	 i=i+1;
	                    }
		              
		        	    // 谁可以看类型1.公开2.私密3.部分可见4.不给谁看
		        		toFriendsInfosDto.setWhoType(WhoType.PART.getValue());
		    			
		        	     // 当whoType 为3和4的时候改字段不为空,传客户微信集合以英文','分隔
		        		toFriendsInfosDto.setWhoNoWxs(whoNoWxs);
		    			//提醒谁看集合以英文','分隔
		        		//toFriendsInfosDto.setRemindNoWxs(toFriendsInfosDto.getRemindNoWxs());
	                }

	                //发送中
	                SendFriendsJob updateSendFriendsJob = new SendFriendsJob();
	                updateSendFriendsJob.setCode(taskDto.getCode());
	                updateSendFriendsJob.setRealExecuteTime(new Date());
	                updateSendFriendsJob.setJobState(SendFriendsJobStatus.SENDING.getCode());
	                sendFriendsJobDao.updateByPrimaryKeySelective(updateSendFriendsJob);
	                
	                if(StringUtils.isNotEmpty(taskDto.getMerchantJobCode())) {
	                	//变更商户任务状态
	                	MerchantSendFriendsJob record = new MerchantSendFriendsJob();
	                	record.setCode(taskDto.getMerchantJobCode());
	                	record.setRealExecuteTime(new Date());
	                	record.setJobState(SendFriendsJobStatus.SENDING.getCode());
	                	merchantSendFriendsJobDao.updateByPrimaryKeySelective(record);
	                }
	                //发送朋友圈
	                iImFriendsFacade.toFriendsInfo(toFriendsInfosDto);
	                
	                //发送完成
	                updateSendFriendsJob.setRealExecuteTime(new Date());
	                updateSendFriendsJob.setJobState(SendFriendsJobStatus.SUCCESS.getCode());
	                sendFriendsJobDao.updateByPrimaryKeySelective(updateSendFriendsJob);
	                
	                if(StringUtils.isNotEmpty(taskDto.getMerchantJobCode())) {
	                	//变更商户任务状态
	                	MerchantSendFriendsJob record = new MerchantSendFriendsJob();
	                	record.setCode(taskDto.getMerchantJobCode());
	                	record.setRealExecuteTime(new Date());
	                	record.setJobState(SendFriendsJobStatus.SUCCESS.getCode());
	                	merchantSendFriendsJobDao.updateByPrimaryKeySelective(record);
	                }
            }catch (Exception e) {//发送失败
                logger.error("发送朋友圈失败", e);
                /**
                 * 记录失败原因
                 */
                SendFriendsJob updateSendFriendsJob = new SendFriendsJob();
                updateSendFriendsJob.setCode(taskDto.getCode());
                updateSendFriendsJob.setJobState(SendFriendsJobStatus.FAILURE.getCode());
                String msg = e.getMessage();
                if(msg.length()>200) {
                	msg.substring(0,200);
                }
                updateSendFriendsJob.setRemark(msg);
                sendFriendsJobDao.updateByPrimaryKeySelective(updateSendFriendsJob);
                
                if(StringUtils.isNotEmpty(taskDto.getMerchantJobCode())) {
                	MerchantSendFriendsJob merchantSendFriendsJob= merchantSendFriendsJobDao.selectByPrimaryKey(taskDto.getMerchantJobCode());
                	//变更商户任务状态
                	MerchantSendFriendsJob record = new MerchantSendFriendsJob();
                	record.setCode(taskDto.getMerchantJobCode());
                	record.setJobState(SendFriendsJobStatus.FAILURE.getCode());
                	record.setRemark(merchantSendFriendsJob.getRemark()+"\n"+msg);
                	merchantSendFriendsJobDao.updateByPrimaryKeySelective(record);
                }
                return;
            }
    }
}
