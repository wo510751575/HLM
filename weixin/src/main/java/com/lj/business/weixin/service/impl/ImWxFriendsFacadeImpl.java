package com.lj.business.weixin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.dto.friends.AddFriendsNotifyMessage;
import com.lj.business.supcon.service.IChatFriendsFacade;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.constant.ErrorCodeImFriendsInfo;
import com.lj.business.weixin.dao.ISendFriendsJobDao;
import com.lj.business.weixin.domain.FriendPointCycle;
import com.lj.business.weixin.domain.SendFriendsJob;
import com.lj.business.weixin.dto.AddWxCommentInfoReturn;
import com.lj.business.weixin.dto.AddWxFriendsInfoReturn;
import com.lj.business.weixin.dto.AddWxLikeInfoReturn;
import com.lj.business.weixin.dto.FindImLikeInfoPage;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.dto.ToDownloadPic;
import com.lj.business.weixin.dto.ToFriendsCommentDto;
import com.lj.business.weixin.dto.ToFriendsInfosDto;
import com.lj.business.weixin.dto.ToFriendsLikeDto;
import com.lj.business.weixin.emus.FriendsInfoType;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.emus.SendFriendsJobStatus;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.emus.WhoType;
import com.lj.business.weixin.service.IFriendPointCycleService;
import com.lj.business.weixin.service.IImCommentInfoService;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IImLikeInfoService;
import com.lj.business.weixin.service.impl.handler.ChatFriendsHandler;
import com.lj.business.weixin.service.impl.handler.FriendsQueryHandler;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.GroupName;
import com.lj.cc.enums.SysParamName;
import com.lj.cc.enums.SystemAliasName;

/**
 * 操作朋友圈统一入口
 * 
 * @author ldq
 *
 */
@Service
public class ImWxFriendsFacadeImpl  implements IImFriendsFacade {

	private static Logger LOG = LoggerFactory.getLogger(ImWxFriendsFacadeImpl.class);

	@Resource
	IImFriendsInfoService imFriendsInfoService;

	@Resource
	IImLikeInfoService imLikeInfoService;

	@Resource
	IImCommentInfoService ImCommentInfoService;

	@Autowired 
	ICommonService commonService;
	
	@Resource
	ChatFriendsHandler chatFriendsHandler;
	
	@Resource
	FriendsQueryHandler friendsQueryHandler;
	
	@Resource
	IFriendPointCycleService friendPointCycleServiceImpl; //朋友圈提示周期
	
	@Resource
	private IMerchantParamsService merchantParamsService;//商户配置
	@Autowired
	IGmAssistantShopService gmAssistantShopService; 
	@Autowired
	ISendFriendsJobDao sendFriendsJobDao; 
	@Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	@Override
	public AddWxLikeInfoReturn toImLike(ToFriendsLikeDto toLikeDto) {
		LOG.debug("开始发送点赞 ----- > {}", toLikeDto);
		AssertUtils.notNull(toLikeDto);
		AssertUtils.notNullAndEmpty(toLikeDto.getToFriendsId(), "点赞朋友圈ID不能为空");
		AssertUtils.notNullAndEmpty(toLikeDto.getShopWxNo(), "终端微信号不能为空");
		
		ICommonService basic = commonService.getHessianCommonService(toLikeDto.getShopWxNo());
		if(!basic.getZkTerminalStatus(toLikeDto.getShopWxNo())) {	// 判断中控是否在线
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "终端已离线");
		}
		FindImLikeInfoPage findImLikeInfoPage = new FindImLikeInfoPage();
		findImLikeInfoPage.setFriendsId(toLikeDto.getToFriendsId());
		findImLikeInfoPage.setUsername(toLikeDto.getShopWxNo());
		List<ImLikeInfoDto> list = imLikeInfoService.findImLikeInfos(findImLikeInfoPage);
		if (list != null && list.size() != 0) {
			return null;
		}
		
		ImFriendsInfoDto imFriendsInfoDto = friendsQueryHandler.getFriendsInfo(toLikeDto.getToFriendsId(),toLikeDto.getShopWxNo());
		FindShopTerminalReturn shopTerminalReturn = friendsQueryHandler.getShopTerminalServiceByNoWx(toLikeDto.getShopWxNo());
		ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
		imLikeInfoDto.setCode(GUID.generateCode());
		imLikeInfoDto.setFriendsCode(imFriendsInfoDto.getCode());
		imLikeInfoDto.setFriendsId(imFriendsInfoDto.getFriendsId());
		imLikeInfoDto.setMerchantName(imFriendsInfoDto.getMerchantName());
		imLikeInfoDto.setMerchantNo(imFriendsInfoDto.getMerchantNo());
		imLikeInfoDto.setNickname(shopTerminalReturn.getWxNickname());
		imLikeInfoDto.setUsername(shopTerminalReturn.getNoWx());
		imLikeInfoDto.setNoWxShop(shopTerminalReturn.getNoWx());
		imLikeInfoDto.setMemberName(shopTerminalReturn.getWxNickname());
		imLikeInfoDto.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
		imLikeInfoDto.setMemberNoGmName(imFriendsInfoDto.getMemberNoGm());
		if(StringUtils.isNotEmpty(imFriendsInfoDto.getMemberNoGm()) && StringUtils.isEmpty(imFriendsInfoDto.getMemberNoGmName())) {
			FindGmAssistantShopReturn findGmAssistantShopReturn =gmAssistantShopService.findGmByWxAndNo(shopTerminalReturn.getNoWx(), imFriendsInfoDto.getMemberNoGm());
			imLikeInfoDto.setMemberNoGmName(findGmAssistantShopReturn.getAssistantName());
		}
		imLikeInfoDto.setOptFlag(SenderFlag.GM.getCode());
		imLikeInfoDto.setStatus(FriendsSendStatus.SENDING.getStatus());
		imLikeInfoService.addImLikeInfo(imLikeInfoDto);
		boolean sendFlag = chatFriendsHandler.sendLikesMessage(imLikeInfoDto,shopTerminalReturn.getNoWx());
		if (sendFlag) {
		    LOG.info("结束  点赞 ---------- >{}", imLikeInfoDto);
		    
		    //点赞后加入朋友圈提醒周期控制
		    Integer cycle=null;
		    if(imFriendsInfoDto.getMemberNo()!=null) {
		    	if(cycle==null) {
		    		cycle = Integer.valueOf(getPointCycleValue(imFriendsInfoDto.getMemberNo(),imFriendsInfoDto.getMerchantNo()));//查询用户周期,没有则使用默认提醒周期
		    	}
		    	FriendPointCycle friendPointCycle = new FriendPointCycle();
		    	friendPointCycle.setMemberNo(imFriendsInfoDto.getMemberNo());
		    	friendPointCycle.setCycle(cycle);
		    	//String createId = UserUtils.getUser().getId();
		    	//friendPointCycle.setCreateId(createId);//指定创建者
		    	friendPointCycleServiceImpl.updateByMemberNo(friendPointCycle);
		    }
		} else {
		    ImLikeInfoDto updateImLikeInfoDto = new ImLikeInfoDto();
		    updateImLikeInfoDto.setCode(imLikeInfoDto.getCode());
		    updateImLikeInfoDto.setStatus(FriendsSendStatus.SEND_FAIL.getStatus());
		    imLikeInfoService.updateImLikeInfo(updateImLikeInfoDto);
		}
		AddWxLikeInfoReturn addWxLikeInfoReturn = new AddWxLikeInfoReturn();
		return addWxLikeInfoReturn;
	}


	
	@Override
	public AddWxCommentInfoReturn toComment(ToFriendsCommentDto toFriendsCommentDto) {
		LOG.debug("开始 发送 评论 ----- > {}", toFriendsCommentDto);
		AssertUtils.notNull(toFriendsCommentDto);
		AssertUtils.notNullAndEmpty(toFriendsCommentDto.getToFriendsId(),"评论朋友圈ID不能为空");
		AssertUtils.notNullAndEmpty(toFriendsCommentDto.getToConent(),"评论内容不能为空");
		AssertUtils.notNullAndEmpty(toFriendsCommentDto.getShopWxNo(),"终端微信号不能空 ");
		
		ImFriendsInfoDto imFriendsInfoDto = friendsQueryHandler.getFriendsInfo(toFriendsCommentDto.getToFriendsId(),toFriendsCommentDto.getShopWxNo());
		
		ICommonService basic = commonService.getHessianCommonService(toFriendsCommentDto.getShopWxNo());
		if(!basic.getZkTerminalStatus(toFriendsCommentDto.getShopWxNo())) {	// 判断中控是否在线
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "终端已离线");
		}
		FindShopTerminalReturn shopTerminal = friendsQueryHandler.getShopTerminalServiceByNoWx(toFriendsCommentDto.getShopWxNo());
		ImCommentInfoDto addImCommentInfoDto = new ImCommentInfoDto();
		addImCommentInfoDto.setCode(GUID.generateCode());
		addImCommentInfoDto.setFriendsCode(imFriendsInfoDto.getCode());
		addImCommentInfoDto.setFriendsId(imFriendsInfoDto.getFriendsId());
		addImCommentInfoDto.setContent(toFriendsCommentDto.getToConent());
		addImCommentInfoDto.setFriendsCode(imFriendsInfoDto.getCode());
		addImCommentInfoDto.setFriendsId(imFriendsInfoDto.getFriendsId());
		addImCommentInfoDto.setCommentTime((System.currentTimeMillis()/1000)+"");
		addImCommentInfoDto.setNoWxShop(shopTerminal.getNoWx());
		addImCommentInfoDto.setNickname(shopTerminal.getWxNickname());
		addImCommentInfoDto.setUsername(shopTerminal.getNoWx());
		addImCommentInfoDto.setMemberName(shopTerminal.getWxNickname());
		addImCommentInfoDto.setMerchantNo(imFriendsInfoDto.getMerchantNo());
		addImCommentInfoDto.setMerchantName(imFriendsInfoDto.getMerchantName());
		addImCommentInfoDto.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
		addImCommentInfoDto.setMemberNoGmName(imFriendsInfoDto.getMemberNoGmName());
		if(StringUtils.isNotEmpty(imFriendsInfoDto.getMemberNoGm()) && StringUtils.isEmpty(imFriendsInfoDto.getMemberNoGmName())) {
			FindGmAssistantShopReturn findGmAssistantShopReturn =gmAssistantShopService.findGmByWxAndNo(shopTerminal.getNoWx(), imFriendsInfoDto.getMemberNoGm());
			addImCommentInfoDto.setMemberNoGmName(findGmAssistantShopReturn.getAssistantName());
		}
		
		addImCommentInfoDto.setOptFlag(1);
		addImCommentInfoDto.setStatus(FriendsSendStatus.SENDING.getStatus());
		addImCommentInfoDto.setTousername(imFriendsInfoDto.getNoWx());
		String commonSvrId = null;
		if (StringUtils.isNotEmpty(toFriendsCommentDto.getToCommentCode())) { // 则为回复评论
			ImCommentInfoDto imCommentInfoDto = new ImCommentInfoDto();
			imCommentInfoDto.setCode(toFriendsCommentDto.getToCommentCode());
			imCommentInfoDto = ImCommentInfoService.findImCommentInfo(imCommentInfoDto);
			AssertUtils.notNull(imCommentInfoDto, "回复的评论不存在");
			addImCommentInfoDto.setTonickname(toFriendsCommentDto.getToWxName());
			addImCommentInfoDto.setTousername(toFriendsCommentDto.getToWxNo());
			commonSvrId = imCommentInfoDto.getCommentSerId();
		}else {
			addImCommentInfoDto.setCommentSerId("0");
		}
		ImCommentInfoService.addImCommentInfo(addImCommentInfoDto);
		boolean sendFlag = chatFriendsHandler.sendCommentMessage(addImCommentInfoDto, shopTerminal.getNoWx(),commonSvrId);
		if (sendFlag) {
		    LOG.debug("add comment success ,info:{}", addImCommentInfoDto);
		    
		    //处理朋友圈待回复数量
		    ImFriendsInfoDto friendsInfoDto = new ImFriendsInfoDto();
		    friendsInfoDto.setCode(addImCommentInfoDto.getFriendsCode());
		    ImFriendsInfoDto findImFriendsInfo = imFriendsInfoService.findImFriendsInfo(friendsInfoDto);
		    
		    if (findImFriendsInfo.getOptFlag() == 1) {//导购助手发出的朋友圈
		    	imFriendsInfoService.updateImFriendsInfoToReplyCount(findImFriendsInfo.getCode(), -1);
		    } else {
		    	ImFriendsInfoDto imFriendsInfoDto2 = new ImFriendsInfoDto();
		    	imFriendsInfoDto2.setCode(findImFriendsInfo.getCode());
		    	imFriendsInfoDto2.setToReplyCount(0);
		    	imFriendsInfoService.updateImFriendsInfo(imFriendsInfoDto2);
		    }
		    
		    //评论后加入朋友圈提醒周期控制
		    Integer cycle=null;
		    if(imFriendsInfoDto.getMemberNo()!=null) {
		    	if(cycle==null) {
		    		cycle = Integer.valueOf(getPointCycleValue(imFriendsInfoDto.getMemberNo(),imFriendsInfoDto.getMerchantNo()));//查询用户周期,没有则使用默认提醒周期
		    	}
		    	FriendPointCycle friendPointCycle = new FriendPointCycle();
		    	friendPointCycle.setMemberNo(imFriendsInfoDto.getMemberNo());
		    	friendPointCycle.setCycle(cycle);
		    	//String createId = UserUtils.getUser().getId();
		    	//friendPointCycle.setCreateId(createId);//指定创建者
		    	friendPointCycleServiceImpl.updateByMemberNo(friendPointCycle);
		    }
		} else {
		    ImCommentInfoDto updateImCommentInfoDto = new ImCommentInfoDto();
		    updateImCommentInfoDto.setCode(addImCommentInfoDto.getCode());
		    updateImCommentInfoDto.setStatus(FriendsSendStatus.SEND_FAIL.getStatus());
		    ImCommentInfoService.updateImCommentInfo(updateImCommentInfoDto);
		}
		AddWxCommentInfoReturn commentInfoReturn = new AddWxCommentInfoReturn();
		return commentInfoReturn;
	}
	
	/**
	 * 方法说明：获取朋友圈提醒周期,商户配置默认值
	 * @return
	 * @author 李端强 CreateDate: 2018年1月31日
	 */
	private String getPointCycleValue(String memberNo,String merchantNo) {
		//朋友圈提醒周期
		if(!StringUtils.isEmpty(memberNo)) {
			FriendPointCycle friendPointCycle = friendPointCycleServiceImpl.selectByMemberNo(memberNo);
			if(friendPointCycle!=null) {
				return String.valueOf(friendPointCycle.getCycle());//返回用户的详细配置周期
			}
		}
		String value = getMerchantParamsValue(merchantNo, merchantParamsService, "friends", "friends_point_cycle");
		if(StringUtils.isEmpty(value)) {
			value = "30";//默认朋友圈提醒为30天
		}
		return value;
	}
	
	/**
	 *
	 * 方法说明：获取商户的配置参数值
	 * @param merchantNo
	 * @param merchantParamsService
	 * @param groupName
	 * @param key
	 * @return
	 * @author 李端强 CreateDate: 2018年1月31日
	 */
	private static String getMerchantParamsValue(String merchantNo,IMerchantParamsService merchantParamsService,String groupName,String key) {
		FindMerchantParams findMerchantParams = new FindMerchantParams();
		findMerchantParams.setGroupName(groupName);//组名
		findMerchantParams.setMerchantNo(merchantNo);//商户编号
		Map<String, String> openApiMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);//获取cm商户系统参数
		String paramValue = openApiMap.get(key);
		String value = "";
		if(!StringUtils.isEmpty(paramValue)) {
			value = paramValue;
		}
		return value;//返回商户参数
	}

	@Override
	public AddWxFriendsInfoReturn toFriendsInfo(ToFriendsInfosDto toFriendsInfosDto) {
		LOG.debug(" begin add friends info :{}", toFriendsInfosDto);
		AssertUtils.notNull(toFriendsInfosDto);
		AssertUtils.notNullAndEmpty(toFriendsInfosDto.getNoWxShop(),"终端微信不能为空");
//		AssertUtils.notNullAndEmpty(toFriendsInfosDto.getContent(),"内容不能为空");
		AssertUtils.notNullAndEmpty(toFriendsInfosDto.getType(),"类型不能为空");
		if(!FriendsInfoType.getType(toFriendsInfosDto.getType())){
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_ADD_ERROR, "朋友圈类型不匹配");
		}
		if (FriendsInfoType.SHARE.getValue().equals(toFriendsInfosDto.getType())) { // 分享
				AssertUtils.notNullAndEmpty(toFriendsInfosDto.getSharetitle(),"分享标题不能为空"); //
				AssertUtils.notNullAndEmpty(toFriendsInfosDto.getShareurl(),"分享链接不能为空");
		}
		
		/**
		 * 去重
		 */
		if(StringUtils.isNotEmpty(toFriendsInfosDto.getWhoNoWxs())) {
			toFriendsInfosDto.setWhoNoWxs(StringUtils.replay(toFriendsInfosDto.getWhoNoWxs()));
		}
		if(StringUtils.isNotEmpty(toFriendsInfosDto.getRemindNoWxs())) {
			toFriendsInfosDto.setRemindNoWxs(StringUtils.replay(toFriendsInfosDto.getRemindNoWxs()));
		}
		
		/**
		 * 谁可以看类型
		 * 默认1公开
		 * 2私密		-就不能提醒和谁可见了
		 * 3部分可见	-可见客户列表不能为空，并且@只能从可见中@
		 * 4不给谁看	-就不能@那个客户了
		 */
		if (StringUtils.isNotEmpty(toFriendsInfosDto.getWhoType())){
			if(toFriendsInfosDto.getWhoType().equals(WhoType.PRIVATE.getValue())) {
				toFriendsInfosDto.setWhoNoWxs(null);
				toFriendsInfosDto.setRemindNoWxs(null);
			}else if(toFriendsInfosDto.getWhoType().equals(WhoType.PART.getValue())) {
				AssertUtils.notNullAndEmpty(toFriendsInfosDto.getWhoNoWxs(),"可见客户不能为空");
			}else if(toFriendsInfosDto.getWhoType().equals(WhoType.DO_NOT.getValue())) {
				AssertUtils.notNullAndEmpty(toFriendsInfosDto.getWhoNoWxs(),"不给谁看客户不能为空");
				if(StringUtils.isNotEmpty(toFriendsInfosDto.getRemindNoWxs())) {
					String[] remindNoWxs = toFriendsInfosDto.getRemindNoWxs().split(",");
					for (String string : remindNoWxs) {
						if(toFriendsInfosDto.getWhoNoWxs().contains(string)) {
							throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_ADD_ERROR, "不给他看，就别@他！");
						}
					}
				}
			}
		}
		
		FindShopTerminalReturn shopTerminal = friendsQueryHandler.getShopTerminalServiceByNoWx(toFriendsInfosDto.getNoWxShop());
		ImFriendsInfoDto imFriendsInfoDto = new ImFriendsInfoDto();
		imFriendsInfoDto.setCode(GUID.generateCode());
		imFriendsInfoDto.setJobCode(toFriendsInfosDto.getJobCode());
		imFriendsInfoDto.setContent(toFriendsInfosDto.getContent());
		imFriendsInfoDto.setAutoContent(toFriendsInfosDto.getAutoContent());
		if(StringUtils.isNotEmpty(toFriendsInfosDto.getImgAddr())){
			String[] imgStr =toFriendsInfosDto.getImgAddr().split(",");
				if(imgStr.length>9){
					throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_ADD_ERROR, "朋友圈图片不能多于9张");
				}
				StringBuffer imgStatus = new StringBuffer("");
				for (int i = 0; i < imgStr.length; i++) {
					imgStatus.append("1").append(",");
				}
				if(imgStatus.length()>0) {
					imgStatus.deleteCharAt(imgStatus.length()-1);
				}
				imFriendsInfoDto.setImgAddr(toFriendsInfosDto.getImgAddr());
				imFriendsInfoDto.setImgStatus(imgStatus.toString());
		}
		imFriendsInfoDto.setImei(shopTerminal.getImei());
		imFriendsInfoDto.setTimestamp((System.currentTimeMillis()/1000)+"");
		imFriendsInfoDto.setMerchantNo(shopTerminal.getMerchantNo());
		imFriendsInfoDto.setMerchantName(shopTerminal.getMerchantName());
		imFriendsInfoDto.setNickName(shopTerminal.getWxNickname());
		imFriendsInfoDto.setNoWx(shopTerminal.getNoWx());
		imFriendsInfoDto.setNoWxShop(shopTerminal.getNoWx());
		imFriendsInfoDto.setSharetitle(toFriendsInfosDto.getSharetitle());
		imFriendsInfoDto.setShareurl(toFriendsInfosDto.getShareurl());
		imFriendsInfoDto.setType(toFriendsInfosDto.getType());
		imFriendsInfoDto.setSendTime(new Date());
		imFriendsInfoDto.setOptFlag(1);
		imFriendsInfoDto.setStatus(FriendsSendStatus.SENDING.getStatus());
		imFriendsInfoDto.setWhoType(toFriendsInfosDto.getWhoType());
		imFriendsInfoDto.setWhoNoWxs(toFriendsInfosDto.getWhoNoWxs());
		imFriendsInfoDto.setRemindNoWxs(toFriendsInfosDto.getRemindNoWxs());
		imFriendsInfoDto.setMemberNoGm(toFriendsInfosDto.getMemberNoGm());
		imFriendsInfoDto.setMemberName(imFriendsInfoDto.getNickName());
		if(StringUtils.isNotEmpty(toFriendsInfosDto.getMemberNoGm())) {
			FindGmAssistantShopReturn findGmAssistantShopReturn= gmAssistantShopService.findGmByWxAndNo(shopTerminal.getNoWx(), toFriendsInfosDto.getMemberNoGm());
			if(null != findGmAssistantShopReturn) {
				imFriendsInfoDto.setMemberNoGmName(findGmAssistantShopReturn.getAssistantName());
			}
		}
		imFriendsInfoService.addImFriendsInfo(imFriendsInfoDto);
		boolean sendFlag = chatFriendsHandler.sendFriendsMessage(imFriendsInfoDto);
		if (!sendFlag) {
			imFriendsInfoDto.setStatus(FriendsSendStatus.SEND_FAIL.getStatus());
			imFriendsInfoService.updateImFriendsInfo(imFriendsInfoDto);
			LOG.debug("add wx friends info fail  :{}", imFriendsInfoDto);
			
			//反写发送任务为失败
			try {
				if(StringUtils.isNotEmpty(imFriendsInfoDto.getJobCode())) {
					SendFriendsJob record = new SendFriendsJob();
					record.setCode(imFriendsInfoDto.getJobCode());
					record.setJobState(SendFriendsJobStatus.FAILURE.getCode());
					sendFriendsJobDao.updateByPrimaryKeySelective(record);
				}
			} catch (Exception e) {
				LOG.error("反写朋友圈任务失败 e={}",e);
			}
			
		} else {
			LOG.debug("add wx friends info success , and send ing wait callback with result :{}",imFriendsInfoDto);
		}
		AddWxFriendsInfoReturn addWxFriendsInfoReturn = new AddWxFriendsInfoReturn();
		return addWxFriendsInfoReturn;
	}

	@Override
	public void toDownloadPic(ToDownloadPic toDownload) {
		LOG.debug("ImWxFriendsFacadeImpl.toDownloadPic --------------> :{}", toDownload);
		AssertUtils.notNull(toDownload);
		AssertUtils.notNullAndEmpty(toDownload.getFriendsId(),"朋友圈ID不能为空");
		AssertUtils.notNullAndEmpty(toDownload.getNoWx(),"终端微信不能为空");
	
		ImFriendsInfoDto imFriendsInfoDto = imFriendsInfoService.getImFriendsInfoByFriendsId(toDownload.getFriendsId(), toDownload.getNoWx());
		AssertUtils.notNull(imFriendsInfoDto,"朋友圈不存在 ");
		/**
		 * 导购请求的，回写
		 */
		if(StringUtils.isNotEmpty(toDownload.getMemberNoGm()) &&
				(StringUtils.isEmpty(imFriendsInfoDto.getMemberNoGm()) 
				|| !imFriendsInfoDto.getMemberNoGm().equals(toDownload.getMemberNoGm()))) {
			ImFriendsInfoDto updateInfo = new ImFriendsInfoDto();
			updateInfo.setCode(imFriendsInfoDto.getCode());
			updateInfo.setMemberNoGm(toDownload.getMemberNoGm());
			imFriendsInfoService.updateImFriendsInfo(updateInfo);
		}
		
		String uploadUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),GroupName.upload.toString(),SysParamName.UPLOADURL.getText());
		String[] imgUrls = imFriendsInfoDto.getImgAddr().split(CommonConstant.SPLIT_DOU_HAO);
		StringBuffer imgUrl = new StringBuffer();
		for (String string : imgUrls) {
			if(!string.startsWith(uploadUrl)) {
				imgUrl =imgUrl.append(string).append(CommonConstant.SPLIT_DOU_HAO);
			}
		}
		if(imgUrl.length()>0) {
			imgUrl.deleteCharAt(imgUrl.length()-1);
			toDownload.setImgUrl(imgUrl.toString());
		}
		
		toDownload.setEncKey(imFriendsInfoDto.getEnckey());
		/**
		 * 如果都已经下载过了，就不需要找中控请求了，直接返回给导购
		 */
		if(StringUtils.isEmpty(toDownload.getImgUrl())) {
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
					 IChatFriendsFacade basic =  commonService.getHessianIChatFriendsFacade(imFriendsInfoDto.getNoWxShop());
					 basic.sendPicDownloadResult(addFriendsNotifyMessage);
					 return;
				} catch (Exception e) {
					LOG.error("请求下载朋友圈图片通知导购错误 e={}",e);
				}
			 }
		}
		boolean sendFlag  = chatFriendsHandler.sendFriendsPicDownMessage(toDownload);
		if(!sendFlag){
			LOG.warn("toDownloadPic fail  :{}", toDownload);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：重发朋友圈
	 *
	 * @param friendsCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年6月22日
	 *
	 */
	@Override
	public AddWxFriendsInfoReturn resendFriendsInfo(String friendsCode) {
		LOG.debug("resendFriendsInfo(String friendsCode={})", friendsCode);
		
		ImFriendsInfoDto find = new ImFriendsInfoDto();
		find.setCode(friendsCode);
		ImFriendsInfoDto imFriendsInfoDto = imFriendsInfoService.findImFriendsInfo(find);
		if(imFriendsInfoDto == null) {
			LOG.error("没有找到朋友圈记录: {}", friendsCode);
			throw new TsfaServiceException(com.lj.business.weixin.constant.ErrorCode.WX_FRIENDS_INFO_FIND_ERROR, "没有找到朋友圈记录");
		}
		if(FriendsSendStatus.SEND_SUCCESS.getStatus().equals(imFriendsInfoDto.getStatus())) {
			LOG.error("朋友圈已发送成功，不能重复发送");
			throw new TsfaServiceException(com.lj.business.weixin.constant.ErrorCode.SEND_FRIENDS_WX_ERROR, "朋友圈已发送，不能重复发送");
		}
		
		boolean sendFlag = chatFriendsHandler.sendFriendsMessage(imFriendsInfoDto);
		if (!sendFlag) {
			imFriendsInfoDto.setStatus(FriendsSendStatus.SEND_FAIL.getStatus());
			imFriendsInfoService.updateImFriendsInfo(imFriendsInfoDto);
			LOG.debug("resend friends info fail  :{}", imFriendsInfoDto);
		} else {
			LOG.debug("resend friends info success , and send ing wait callback with result :{}",imFriendsInfoDto);
		}
		
		AddWxFriendsInfoReturn addWxFriendsInfoReturn = new AddWxFriendsInfoReturn();
		return addWxFriendsInfoReturn;
	}

}
