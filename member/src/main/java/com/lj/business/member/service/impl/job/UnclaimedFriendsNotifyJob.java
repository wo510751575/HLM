/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service.impl.job;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dao.IAddFriendsDao;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.ManagerMemberReturnDto;
import com.lj.business.member.dto.addfriends.UnclaimedFriendsByShop;
import com.lj.business.member.dto.im.FindPersonMemberByShopAndNoWx;
import com.lj.business.member.dto.im.FindPersonMemberByShopAndNoWxReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IManagerMemberService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.supcon.dto.contacts.PushNotifyMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.cc.clientintf.IJob;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.service.ISystemInfoService;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 类说明：未认领客户每日提醒任务
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2018年1月24日
 */
@Service
public class UnclaimedFriendsNotifyJob implements IJob {

	private static final Logger logger = LoggerFactory.getLogger(UnclaimedFriendsNotifyJob.class);

	public static final String UNCLAIMED_FRIENDS_NOTIFY_JOB = "unclaimedFriendsNotifyJob";

	@Resource
	private JobLogFeedBackService jobLogFeedBackService;

	@Resource
	private IAddFriendsDao addFriendDao;

	@Resource
	private IManagerMemberService managerMemberService;

	@Resource
	private IGuidMemberService guidMemberService;

	@Resource
	private IPersonMemberImService personMemberImService;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Autowired 
	ICommonService commonService;
	
	@Resource
	private IImChatInfoService imChatInfoService;

    @Autowired 
	private ISystemInfoService systemInfo;
    
    @Autowired 
	private RedisCache redisCache;
    
	@Override
	public void runJob() {
		logger.info("未认领客户每日提醒任务 -- start");
		jobLogFeedBackService.pushLog(UNCLAIMED_FRIENDS_NOTIFY_JOB, "未认领客户每日提醒任务 - 开始", "begin");

		// 从cc配置中获取推送信息
		String message = localCacheSystemParams.getSystemParam("ms", "friends", "unclaimedMemberNotify");

		// 统计每个终端未认领客户数
		List<UnclaimedFriendsByShop> unclaimedList = addFriendDao.findUnclaimedFriendsCountByAllShop();
		if (unclaimedList == null || unclaimedList.isEmpty()) {
			return;
		}
		logger.info("一共有{}个终端还有未认领客户", unclaimedList.size());
		jobLogFeedBackService.pushLog(UNCLAIMED_FRIENDS_NOTIFY_JOB, "未认领客户终端数量：" + unclaimedList.size(), "total");

		// 初始化查询入参
		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
		FindGuidMember findGuidMember = new FindGuidMember();
		FindPersonMemberByShopAndNoWx findPersonalMember = new FindPersonMemberByShopAndNoWx();

		// 聊天记录(初始化公共参数)
		SendImChatInfo chat = new SendImChatInfo();
		chat.setSenderFlag(1);
		chat.setType("1");
		chat.setAllowZkOffline(Boolean.FALSE);
		chat.setMsgSource(MessageSource.SA.getCode());

		// 推送消息（初始化公共参数）
		PushNotifyMessage pushNotifyMessage = new PushNotifyMessage();
		pushNotifyMessage.setType("20"); // 20-未认领客户提醒

		// 统计每个终端未认领客户数
		for (UnclaimedFriendsByShop unclaimedFriendsByShop : unclaimedList) {
			/*try {
				// 查询该终端店长信息
//				List<ManagerMemberReturnDto> findManagerMemberByShopList = managerMemberService.findManagerMemberByShop(unclaimedFriendsByShop.getShopNo());
				if (findManagerMemberByShopList != null && findManagerMemberByShopList.size() > 0) { // 有店长时才发送
					ManagerMemberReturnDto manager = findManagerMemberByShopList.get(0); // 获取店长信息
					// 查询店长对应导购
					findGuidMemberPage.setMobile(manager.getMobile());
					findGuidMemberPage.setShopNo(unclaimedFriendsByShop.getShopNo());
					List<FindGuidMemberPageReturn> guidList = guidMemberService.findGuidMembers(findGuidMemberPage);
					if (guidList == null || guidList.isEmpty()) {
						logger.error("店长没有对应的导购信息: {}", manager.getMemberNo());
						continue;
					}
					FindGuidMemberPageReturn guid = guidList.get(0);

					// 如果店长绑定了私人微信，则查询该私人微信，并将提醒发到私人微信上
					if (StringUtils.isNotEmpty(guid.getNoWxPersonal())) {
						findPersonalMember.setShopNo(unclaimedFriendsByShop.getShopNo());
						findPersonalMember.setNoWx(guid.getNoWxPersonal());
						FindPersonMemberByShopAndNoWxReturn personalMember = personMemberImService.findPersonMemberByShopAndNoWx(findPersonalMember);
						// 私人微信是该终端客户，则把提醒消息发到店长的微信上
						if (personalMember != null) {
							String noWxShop = null; // 终端微信
							if (personalMember.getMemberNoGm().equals(guid.getMemberNo())) { // 店长加了自己的私人微信
								noWxShop = guid.getNoWx();
							} else { // 终端其他导购加了店长的私人微信
								findGuidMember.setMemberNo(personalMember.getMemberNoGm());
								FindGuidMemberReturn gm = guidMemberService.findGuidMember(findGuidMember);
								noWxShop = gm.getNoWx();
							}
							
						  	//集群环境下手动调用
				      
					        ICommonService basic = commonService.getHessianCommonService(noWxShop);
					        
							// 如果终端终端在线，则发送到到店长的微信上
							if (basic.getZkTerminalStatus(noWxShop)) {
								chat.setMsgId(GUID.generateByUUID());
								chat.setMemberNoGm(personalMember.getMemberNoGm());
								chat.setMemberNo(personalMember.getMemberNo());
								String content = message.replaceAll("wxCount", String.valueOf(unclaimedFriendsByShop.getWxCount()));
								content = content.replaceAll("pmCount", String.valueOf(unclaimedFriendsByShop.getUnclaimedCount()));
								chat.setContent(content);
								imChatInfoService.sendChat(chat);
								logger.info("已向店长私人微信发送未认领客户提醒: {}", chat.getMsgId());
								continue; // 处理下一个终端
							}
						}
					}
					ICommonService basic = commonService.getHessianCommonService(guid.getMemberNo());
					// 导购在线，则推送一条消息给导购
					if (basic.getClientStatus(guid.getMemberNo())) {
						pushNotifyMessage.setCacheAccountNo(guid.getMemberNo()); // 店长对应的导购编号
						String content = message.replaceAll("wxCount", String.valueOf(unclaimedFriendsByShop.getWxCount()));
						content = content.replaceAll("pmCount", String.valueOf(unclaimedFriendsByShop.getUnclaimedCount()));
						pushNotifyMessage.setTitle(content);
						IContactsService contactsServiceBasic = commonService.getHessianContactsService(pushNotifyMessage.getCacheAccountNo());
						contactsServiceBasic.sendPushNotifyMessage(pushNotifyMessage);
						logger.info("已向店长所属导购客户端发送未认领客户提醒: {}", guid.getMemberNo());
						continue; // 处理下一个终端
					}

					// 否则就发送短信提醒
					// TODO 暂不实现
				} else {
					logger.warn("终端{}没有店长", unclaimedFriendsByShop.getShopNo());
				}
			} catch (TsfaServiceException e) {
				logger.error("给终端" + unclaimedFriendsByShop.getShopNo() + "发送未认领客户提醒失败", e.getExceptionInfo());
				jobLogFeedBackService.pushLog(UNCLAIMED_FRIENDS_NOTIFY_JOB, "发送未认领客户提醒失败：" + unclaimedFriendsByShop.getShopNo(), "error");
			} catch (Exception e) {
				logger.error("给终端" + unclaimedFriendsByShop.getShopNo() + "发送未认领客户提醒失败", e);
				jobLogFeedBackService.pushLog(UNCLAIMED_FRIENDS_NOTIFY_JOB, "发送未认领客户提醒失败：" + unclaimedFriendsByShop.getShopNo(), "error");
			}*/
		}

		jobLogFeedBackService.pushLog(UNCLAIMED_FRIENDS_NOTIFY_JOB, "未认领客户每日提醒任务 - 结束", "end");
		logger.info("未认领客户每日提醒任务 -- end");
	}
	
	




}
