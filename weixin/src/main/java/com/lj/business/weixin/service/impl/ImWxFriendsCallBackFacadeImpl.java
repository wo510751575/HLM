package com.lj.business.weixin.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.common.CommonConstant;
import com.lj.business.supcon.dto.friends.AddFriendsNotifyMessage;
import com.lj.business.supcon.service.IChatFriendsFacade;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.dto.FindSendFriendsJobPage;
import com.lj.business.weixin.dto.FindSendFriendsJobPageReturn;
import com.lj.business.weixin.dto.ImCommentCallBackDto;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImFriendsInfoCallBackDto;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ImLikeCallBackDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.dto.MerchantSendFriendsJobDto;
import com.lj.business.weixin.dto.ToFriendsCommentDto;
import com.lj.business.weixin.dto.friendsjob.UpdateSendFriendsJob;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.emus.SendFriendsJobStatus;
import com.lj.business.weixin.service.IImCommentInfoService;
import com.lj.business.weixin.service.IImFriendsCallBackFacade;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IImLikeInfoService;
import com.lj.business.weixin.service.IMerchantSendFriendsJobService;
import com.lj.business.weixin.service.ISendFriendsJobService;
import com.lj.business.weixin.service.ImFriendsPicDownCallBackDto;

/**
 * 微信朋友圈操作处理回调结果门面 
 * @author ldq
 *
 */
@Service
public class ImWxFriendsCallBackFacadeImpl  implements IImFriendsCallBackFacade {
	
	private static Logger LOG = LoggerFactory.getLogger(ImWxFriendsCallBackFacadeImpl.class);

	@Resource
	IImFriendsInfoService imFriendsInfoService;
	@Resource
	IImCommentInfoService imCommentInfoService;
	@Resource
	IImLikeInfoService   imLikeInfoService;
	@Resource
	IImFriendsFacade friendsFacade;
	@Autowired 
	ICommonService commonService;
    @Autowired 
	ISendFriendsJobService sendFriendsJobService;
    @Autowired 
	IMerchantSendFriendsJobService merchantSendFriendsJobService;
    @Autowired
	ThreadPoolTaskExecutor taskExecutor;
	
	@Override
	public void toLikeCallBack(ImLikeCallBackDto imLikeCallBackDto) {
		LOG.debug("begin toLikeCallBack --- >{}" , imLikeCallBackDto);
		AssertUtils.notNull(imLikeCallBackDto);
		AssertUtils.notNullAndEmpty(imLikeCallBackDto.getStatus(),"发送结果不能为空");
		AssertUtils.notNullAndEmpty(imLikeCallBackDto.getLikesCode(),"点赞CODE不能为空");
		ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
		imLikeInfoDto.setCode(imLikeCallBackDto.getLikesCode());
		imLikeInfoDto = imLikeInfoService.findImLikeInfo(imLikeInfoDto);
		AssertUtils.notNull(imLikeInfoDto,"点赞记录不存在");
		imLikeInfoDto.setStatus(imLikeCallBackDto.getStatus());
		imLikeInfoDto.setRemark(imLikeCallBackDto.getRemark());
		imLikeInfoService.updateImLikeInfo(imLikeInfoDto);
		LOG.debug("end toLikeCallBack ---- > {} 发送结果:--- >{}",imLikeCallBackDto,imLikeCallBackDto.getStatus());
	}

	@Override
	public void toCommentCallBack(ImCommentCallBackDto imCommentCallBackDto) {
		LOG.debug("begin toCommentCallBack ---- >{}",imCommentCallBackDto);
		AssertUtils.notNull(imCommentCallBackDto);
		AssertUtils.notNullAndEmpty(imCommentCallBackDto.getCommentsCode());
		ImCommentInfoDto   imCommentInfoDto = new ImCommentInfoDto();
		imCommentInfoDto.setCode(imCommentCallBackDto.getCommentsCode());
		imCommentInfoDto.setStatus(Integer.valueOf(imCommentCallBackDto.getStatus()));
		imCommentInfoDto.setRemark(imCommentCallBackDto.getRemark());
		imCommentInfoService.updateImCommentInfo(imCommentInfoDto);
		LOG.debug("end process toCommentCallBack Info :{}",imCommentInfoDto);
	}

	@Override
	public void toFriendsinfoCallBack(ImFriendsInfoCallBackDto imFriendsInfoCallBackDto) {
		LOG.debug("begin toFriendsinfoCallBack ---- >{}",imFriendsInfoCallBackDto);
		AssertUtils.notNull(imFriendsInfoCallBackDto);
		AssertUtils.notNullAndEmpty(imFriendsInfoCallBackDto.getFriendsCode());
		ImFriendsInfoDto  imFriendsInfoDto = new ImFriendsInfoDto();
		imFriendsInfoDto.setCode(imFriendsInfoCallBackDto.getFriendsCode());
		imFriendsInfoDto = imFriendsInfoService.findImFriendsInfo(imFriendsInfoDto);
		if(imFriendsInfoDto!=null){
			if(FriendsSendStatus.SEND_SUCCESS.getStatus()!=imFriendsInfoDto.getStatus()){
				imFriendsInfoDto.setFriendsId(imFriendsInfoCallBackDto.getFriendsId());
				imFriendsInfoDto.setCode(imFriendsInfoCallBackDto.getFriendsCode());
				imFriendsInfoDto.setStatus(Integer.valueOf(imFriendsInfoCallBackDto.getStatus()));
				imFriendsInfoDto.setRemark(imFriendsInfoCallBackDto.getRemark());
				imFriendsInfoDto.setTimestamp((System.currentTimeMillis()/1000)+"");
				imFriendsInfoService.updateImFriendsInfo(imFriendsInfoDto);
				if(StringUtils.isNotEmpty(imFriendsInfoDto.getFriendsId())){
					LOG.info(" BEGIN AUTO COMMENT   :{}",imFriendsInfoDto);
					if(StringUtils.isNotEmpty(imFriendsInfoDto.getAutoContent())){ //  需发送自动评论
						ToFriendsCommentDto toFriendsCommentDto = new ToFriendsCommentDto();
						toFriendsCommentDto.setToConent(imFriendsInfoDto.getAutoContent());
						toFriendsCommentDto.setToFriendsId(imFriendsInfoDto.getFriendsId());
						toFriendsCommentDto.setShopWxNo(imFriendsInfoDto.getNoWxShop());
//						toFriendsCommentDto.setShopNo(imFriendsInfoDto.getShopNo());
						toFriendsCommentDto.setToWxName(imFriendsInfoDto.getNoWx());
						toFriendsCommentDto.setToWxNo(imFriendsInfoDto.getNickName());
						friendsFacade.toComment(toFriendsCommentDto);
						LOG.info(" BEGIN AUTO COMMENT  send success  :{}",imFriendsInfoDto);
					}
				}
			}
			//反写发送任务为失败
			if(StringUtils.isNotEmpty(imFriendsInfoDto.getJobCode()) && FriendsSendStatus.SEND_FAIL.getStatus().toString().equals(imFriendsInfoCallBackDto.getStatus())) {
				final String jobCode = imFriendsInfoDto.getJobCode();
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						UpdateSendFriendsJob record = new UpdateSendFriendsJob();
						record.setCode(jobCode);
						record.setJobState(SendFriendsJobStatus.FAILURE.getCode());
						record.setRemark(imFriendsInfoCallBackDto.getRemark());
						try {
							sendFriendsJobService.updateSendFriendsJob(record);
							
							FindSendFriendsJobPage findSendFriendsJobPage = new FindSendFriendsJobPage();
							findSendFriendsJobPage.setCode(record.getCode());
							FindSendFriendsJobPageReturn findSendFriendsJobPageReturn= sendFriendsJobService.findSendFriendsJob(findSendFriendsJobPage);
							//变更商户任务状态
							if(StringUtils.isNotEmpty(findSendFriendsJobPageReturn.getMerchantJobCode())) {
								MerchantSendFriendsJobDto merchantSendFriendsJobDto = new MerchantSendFriendsJobDto();
								merchantSendFriendsJobDto.setCode(findSendFriendsJobPageReturn.getMerchantJobCode());
								MerchantSendFriendsJobDto merchantSendFriendsJob= merchantSendFriendsJobService.findMerchantSendFriendsJob(merchantSendFriendsJobDto);
								merchantSendFriendsJobDto.setRemark(merchantSendFriendsJob.getRemark()+"\n"+imFriendsInfoCallBackDto.getRemark());
			                	merchantSendFriendsJobService.updateMerchantSendFriendsJob(merchantSendFriendsJobDto);
							}
						} catch (Exception e) {
							LOG.error("反写朋友圈任务失败 e={}",e);
						}
					}
				});
			}
			LOG.debug("end process friends Info :{}",imFriendsInfoDto);
		}
		
	}

	@Override
	public void toFriendsPicDownCallBack(ImFriendsPicDownCallBackDto imFriendsPicDownCallBackDto) {
		LOG.info("toFriendsPicDownCallBack ------------->{}",imFriendsPicDownCallBackDto);
		if(StringUtils.isEmpty(imFriendsPicDownCallBackDto.getImgUrls()) || StringUtils.isEmpty(imFriendsPicDownCallBackDto.getImgStatus())) {
			LOG.warn("图片地址或图片状态为空");
			return;
		}
		 ImFriendsInfoDto imFriendsInfoDto  = imFriendsInfoService.getImFriendsInfoByFriendsId(imFriendsPicDownCallBackDto.getFriendsId(), imFriendsPicDownCallBackDto.getNoWx());
		 AssertUtils.notNull(imFriendsInfoDto,"朋友圈不存在");
		 if(StringUtils.isEmpty(imFriendsPicDownCallBackDto.getImgUrls()) || StringUtils.isEmpty(imFriendsPicDownCallBackDto.getImgStatus())) {
			 LOG.warn("图片地址为空，或者原图地址为空!");
			 return;
		 }
		 
		 String[] imgUrls = imFriendsPicDownCallBackDto.getImgUrls().split(CommonConstant.SPLIT_DOU_HAO);
		 String[] imgStatus = imFriendsPicDownCallBackDto.getImgStatus().split(CommonConstant.SPLIT_DOU_HAO);
		 String newImgAddrStr = imFriendsInfoDto.getImgAddr();
		 String[] oldImgStatus = imFriendsInfoDto.getImgStatus().split(CommonConstant.SPLIT_DOU_HAO);
		 for (int i = 0; i < imgUrls.length; i++) {
			 String imgUrl = imgUrls[i];	//解密地址
			 String imgStat = imgStatus[i];	//未解密地址
			if(StringUtils.isNotEmpty(imgUrl)){
				newImgAddrStr =newImgAddrStr.replace(imgStat, imgUrl);
				oldImgStatus[i] = imgStat;
			}
		 }
		 ImFriendsInfoDto upDto = new ImFriendsInfoDto();
		 upDto.setCode(imFriendsInfoDto.getCode());
		 upDto.setImgStatus(StringUtils.join(oldImgStatus,CommonConstant.SPLIT_DOU_HAO));
		 upDto.setImgAddr(newImgAddrStr);
		 imFriendsInfoService.updateImFriendsInfo(upDto);
		 
		 /*
		  * 旧方案--------DZP 2019-06-19
		  * if(StringUtils.isEmpty(imgStatus)){
			 newStatusStr.append(imFriendsPicDownCallBackDto.getImgStatus());
			 newImgAddrStr.append(imFriendsPicDownCallBackDto.getImgUrls());
			 
		 }else if(StringUtils.isNotEmpty(imgAddr)&&StringUtils.isNotEmpty(imgStatus)){
			 
			 String[] oldStatus = imgStatus.split(",");
			 String[] newStatus = imFriendsPicDownCallBackDto.getImgStatus().split(",");
			 String[] oldImgAddr = imgAddr.split(",");
			 String[] newImgAddr =imFriendsPicDownCallBackDto.getImgUrls().split(",");
			 for (int i = 0 ; i<oldStatus.length; i++) {
				   if("1".equals(oldStatus[i])){
					   continue;  //以前已经下载成功 则忽略
				   }else {
					   if("1".equals(newStatus[i])){
						   oldStatus[i] = "1";
						   oldImgAddr[i] = newImgAddr[i];
					   }
				   }
					   
			}
			
			for (int i = 0 ; i<oldStatus.length;i++) {
				newStatusStr.append(oldStatus[i]);
				newImgAddrStr.append(oldImgAddr[i]);
				if(i != oldStatus.length-1){
					newStatusStr.append(",");
					newImgAddrStr.append(",");
				}
			}
		
		 }
		 imFriendsInfoDto.setImgStatus(newStatusStr.toString());
		 imFriendsInfoDto.setImgAddr(newImgAddrStr.toString());
		 imFriendsInfoService.updateImFriendsInfo(imFriendsInfoDto);*/
		 /**
		  * 通知导购
		  */
		 if(StringUtils.isNotEmpty(imFriendsInfoDto.getMemberNoGm())) {
			 try {
				 AddFriendsNotifyMessage addFriendsNotifyMessage = new AddFriendsNotifyMessage();
				 addFriendsNotifyMessage.setFriendsId(imFriendsInfoDto.getFriendsId());
				 addFriendsNotifyMessage.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
				 addFriendsNotifyMessage.setNoWx(imFriendsInfoDto.getNoWxShop());
				 addFriendsNotifyMessage.setFriendsCode(imFriendsInfoDto.getCode());
				 addFriendsNotifyMessage.setImgUrls(imFriendsInfoDto.getImgAddr());
				 addFriendsNotifyMessage.setImgStatus(imFriendsInfoDto.getImgStatus());
				 
				 IChatFriendsFacade basic =  commonService.getHessianIChatFriendsFacade(imFriendsInfoDto.getNoWxShop());
				 basic.sendPicDownloadResult(addFriendsNotifyMessage);
			} catch (Exception e) {
				LOG.error("请求下载朋友圈图片通知导购错误 e={}",e);
			}
		 }
		 LOG.info("end toFriendsPicDownCallBack ------------->{}",imFriendsInfoDto);

	}

	

	/**
	 * 
	 *
	 * 方法说明：中控回调删除朋友圈成功
	 *
	 * @param friendsCode
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月1日
	 *
	 */
	@Override
	public void toDeleteFriendsCallBack(String friendsCode) {
		imFriendsInfoService.doDeleteFriendsInfo(friendsCode);
	}

}
