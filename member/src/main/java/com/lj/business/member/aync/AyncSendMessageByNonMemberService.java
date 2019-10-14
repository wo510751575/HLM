package com.lj.business.member.aync;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.StringUtils;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.domain.AddFriends;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.pushconfig.FindWxPushConfigPageReturn;
import com.lj.business.member.emus.PushConfigType;
import com.lj.business.member.service.IComanpyPushConfigService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IWxPushConfigService;
import com.lj.business.weixin.dto.imchat.SendImChatByNonMember;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxSmallProgramService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;

/**
 * 
 * 
 * 类说明：新增微信好友时,异步发送话术、问候语，图片，店长名片
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年7月25日
 */
@Service
public class AyncSendMessageByNonMemberService {
	
	private final static Logger logger = LoggerFactory.getLogger(AyncSendMessageByNonMemberService.class);
	
	// 配置消息
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Resource
	private IImChatInfoService imChatInfoService;
	
//	@Resource
//    private IShopService shopService;
	
	@Resource
    private IComanpyPushConfigService comanpyPushConfigService;
	
    @Resource
    private IGuidMemberService guidMemberService;
    
    @Resource
    private IWxPushConfigService wxPushConfigService;
    
    @Resource
    private IWxPublicAccountService wxPublicAccountService;
    
    @Resource
    private IWxSmallProgramService wxSmallProgramService;
	
	/**
	 * 
	 *
	 * 方法说明：向新加微信好友发送（话术、问候语，图片，店长名片）
	 *
	 * @param shopNo
	 *
	 * @author 许新龙 CreateDate: 2018年7月25日
	 *
	 */
	public void sendMessage(final AddFriends addFriends) {
		/*taskExecutor.execute(new Runnable() {	// 通过线程池发送
			@Override
			public void run() {
				logger.info("向门店：[{}-{}]新增的微信好友[{}]发送话术、问候语，图片，名片等微信记录", addFriends.getShopNo(), addFriends.getNoWxGm(), addFriends.getNoWx());
				
//				FindShop findShop = new FindShop();
//				findShop.setShopNo(addFriends.getShopNo());
//                FindShopReturn shopReturn = shopService.findShopByShopNo(findShop);
                
                if (shopReturn != null) {
                	// 优先推送门店微信配置的记录
                	FindWxPushConfigPage findWxPushConfigPage = new FindWxPushConfigPage();
                	findWxPushConfigPage.setMerchantNo(shopReturn.getMemberNoMerchant());
                	findWxPushConfigPage.setShopNo(shopReturn.getShopNo());
                	findWxPushConfigPage.setPushTime(PushTime.PUSHNOW.name());
                	findWxPushConfigPage.setNoWx(addFriends.getNoWxGm());
                	List<FindWxPushConfigPageReturn> configList = wxPushConfigService.findWxPushConfigToPush(findWxPushConfigPage);
                	if(null != configList && !configList.isEmpty()) {
                		pushByWx(addFriends, configList);	// 按门店推送
                	} else {	// 门店微信没有配置推送记录，则推送经销商配置的
                		if(StringUtils.isNotEmpty(shopReturn.getCompanyNo())) {
		                    FindComanpyPushConfigSelective findComanpyPushConfigSelective = new FindComanpyPushConfigSelective();
		                    findComanpyPushConfigSelective.setMerchantNo(shopReturn.getMemberNoMerchant());
		                    findComanpyPushConfigSelective.setCompanyNo(shopReturn.getCompanyNo());
		                    findComanpyPushConfigSelective.setStatus(PushConfigStatus.USE.name());
		                    List<FindComanpyPushConfigSelectiveReturn> pushConfigSelective = comanpyPushConfigService.findComanpyPushConfigSelective(findComanpyPushConfigSelective);
		                    if (CollectionUtils.isNotEmpty(pushConfigSelective)) {
		                        logger.debug("经销商推送配置count：[{}]", pushConfigSelective.size());
		                        for (FindComanpyPushConfigSelectiveReturn findComanpyPushConfigSelectiveReturn : pushConfigSelective) {
		                            if (CompanyPushConfigType.GREET.name().equals(findComanpyPushConfigSelectiveReturn.getType())) {
		                                sendGreetings(addFriends, findComanpyPushConfigSelectiveReturn.getContent());
		                            } else if (CompanyPushConfigType.IMAGE.name().equals(findComanpyPushConfigSelectiveReturn.getType())) {
		                                sendImg(addFriends, findComanpyPushConfigSelectiveReturn.getContent());
		                            } else if (CompanyPushConfigType.MGR_CARD.name().equals(findComanpyPushConfigSelectiveReturn.getType())) {
		                                sendManagerCard(addFriends);
		                            }
		                        }
		                    } else {
		                        logger.info("没有推送配置，门店编号：[{}]", addFriends.getShopNo());
		                    }
                		}
                    }
                }
			}
		});*/
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据终端微信推送自定义消息
	 *
	 * @param addFriends
	 * @param configList
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void pushByWx(AddFriends addFriends,List<FindWxPushConfigPageReturn> configList) {
		String orgType = configList.get(0).getOrgType();
		for(FindWxPushConfigPageReturn config : configList) {
			// 如果按终端配置了推送消息，那按商户统一配置的推送消息就不发送
			if(orgType.equals(config.getOrgType())) {
				switch (PushConfigType.valueOf(config.getType())) {
				case GREET:	// 推送话术、问候语
					sendGreetings(addFriends, config.getContent());
					break;
				case LINK:	// 推送话术、问候语(带动态链接)
					sendGreetingsAddLink(addFriends, config.getContent());
					break;
				case IMAGE:	// 推送图片
					sendImg(addFriends, config.getShareIcon());
					break;
//				case MGR_CARD:	// 推送店长名片
//					sendManagerCard(addFriends);
//					break;
				case SHARE:	// 推送分享
					sendShare(addFriends, config);
					break;
				case PA:		// 推送公众号
					sendPublicAccount(addFriends, config);
					break;
				case SP:		// 推送小程序
					sendSmallProgram(addFriends, config);
					break;
				case GM_CARD:
					logger.warn("客户未认领前，不支持推送导购名片: {}", addFriends.getCode());
				default:
					logger.error("不支持的推送数据： {}", config);
					break;
				}
			}
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送图片
	 *
	 * @param addFriends
	 * @param imgUrl 图片URL，不包含域名或者IP
	 *
	 * @author 许新龙 CreateDate: 2018年7月25日
	 *
	 */
	private void sendImg(AddFriends addFriends, String imgUrl) {
	    try {
            if(StringUtils.isEmpty(imgUrl)) {
                return;
            }
            
            if(!StringUtils.isHttp(imgUrl)) {
	            Map<String, String> groupMap = localCacheSystemParams.getSystemParamGroup("api", "common");
	            String commonUrl = groupMap.get("commonUrl");
	            commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() -1) : commonUrl;
	            imgUrl = commonUrl + imgUrl;
            }
            
            //发送消息
            SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
            chatByNonMember.setNoWxShop(addFriends.getNoWxGm());
            chatByNonMember.setNoWx(addFriends.getNoWx());
            chatByNonMember.setAlias(addFriends.getAlias());
            chatByNonMember.setType(ChatInfoType.IMG.getCode());   
            chatByNonMember.setResources(imgUrl);
            chatByNonMember.setMsgSource(MessageSource.SA.getCode());
            chatByNonMember.setAllowZkOffline(Boolean.TRUE);    // 允许离线发送到中控客户端
            logger.info("新增微信好友，自定义推送推送图片消息：{}", chatByNonMember);
            imChatInfoService.sendChatByNonMember(chatByNonMember);
        } catch(Exception e) {
            logger.error("新增微信好友，自定义推送推送图片消息失败", e);
        }
    }
	
	private int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
	 * 
	 *
	 * 方法说明：发送话术、问候语
	 *
	 * @param addFriends
	 * @param content
	 *
	 * @author 许新龙 CreateDate: 2018年7月25日
	 *
	 */
	public void sendGreetings(final AddFriends addFriends, String content) {
		try {
			if(StringUtils.isEmpty(content)) {
				return;
			}
			
			//替换表情
			Map<String, String> greetingEmojiMap = localCacheSystemParams.getSystemParamGroup("ms", "greetingEmoji");
			String findText = "\\{emoji\\}";
	        int num = appearNumber(content, findText);
			for (int i = 0; i < num; i++) {
			    String emojiKey="emoji"+((int)(greetingEmojiMap.size()*Math.random())+1);
			    content = content.replaceFirst(findText, greetingEmojiMap.get(emojiKey));
			}
//			logger.info("sendGreetings content:[{}]", content);
			//替换商城地址
//			String mecUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.name(), "qrcode", "url_pre");
//			content = content.replaceAll("\\{shopUrl\\}", mecUrl + "?shopNo=" + addFriends.getShopNo());
			//替换终端编号
//			content = content.replaceAll("\\{shopNo\\}", addFriends.getShopNo());
			//替换终端名称
//			content = content.replaceAll("\\{shopName\\}", addFriends.getShopName());
			//替换店长姓名
//			List<ManagerMemberReturnDto> managerMemberByShop = managerMemberService.findManagerMemberByShop(addFriends.getShopNo());
//			if (CollectionUtils.isNotEmpty(managerMemberByShop)) {
//			    logger.info("sendGreetings终端编号[{}]，店长手机号[{}]", addFriends.getShopNo(), managerMemberByShop.get(0).getMobile());
//			    content = content.replaceAll("\\{mgrName\\}", managerMemberByShop.get(0).getMemberName());
//            }
			
			//发送消息
			SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
            chatByNonMember.setNoWxShop(addFriends.getNoWxGm());
            chatByNonMember.setNoWx(addFriends.getNoWx());
            chatByNonMember.setAlias(addFriends.getAlias());
            chatByNonMember.setType(ChatInfoType.TEXT.getCode());   
            chatByNonMember.setContent(content);
            chatByNonMember.setMsgSource(MessageSource.SA.getCode());
            chatByNonMember.setAllowZkOffline(Boolean.TRUE);    // 允许离线发送到中控客户端
            logger.info("新增微信好友，自定义推送推送话术、问候语：{}", chatByNonMember);
            imChatInfoService.sendChatByNonMember(chatByNonMember);
		} catch(Exception e) {
			logger.error("新增微信好友，自定义推送推送话术、问候语失败", e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送店长名片
	 *
	 * @param addFriends
	 *
	 * @author 许新龙 CreateDate: 2018年7月25日
	 *
	 */
	@Deprecated
	private void sendManagerCard(final AddFriends addFriends) {
		/*try {
		    List<ManagerMemberReturnDto> managerMemberByShop = managerMemberService.findManagerMemberByShop(addFriends.getShopNo());
            if (CollectionUtils.isNotEmpty(managerMemberByShop)) {
                ManagerMemberReturnDto managerMember = managerMemberByShop.get(0);
               FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
               findGuidMemberPage.setMobile(managerMember.getMobile());
               findGuidMemberPage.setShopNo(addFriends.getShopNo());
               List<FindGuidMemberPageReturn> guidMembers = guidMemberService.findGuidMembers(findGuidMemberPage);
               if (CollectionUtils.isNotEmpty(guidMembers)) {
                   FindGuidMemberPageReturn guidMember = guidMembers.get(0);
                   
                   //发送消息
                   SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
                   chatByNonMember.setNoWxShop(addFriends.getNoWxGm());
                   chatByNonMember.setNoWx(addFriends.getNoWx());
                   chatByNonMember.setAlias(addFriends.getAlias());
                   chatByNonMember.setType(ChatInfoType.CARTE_SHARE.getCode());
                   
                   Map<String, String> groupMap = localCacheSystemParams.getSystemParamGroup("api", "common");
                   if(StringUtils.isNotEmpty(guidMember.getHeadAddress())) {
                       String commonUrl = groupMap.get("commonUrl");
                       commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() -1) : commonUrl;
                       chatByNonMember.setResources(commonUrl + guidMember.getHeadAddress());
                   } else {    // 没有设置头像，取默认头像
                       chatByNonMember.setResources(groupMap.get("guidDHUrl"));
                   }
                   
                   chatByNonMember.setShareTitle(guidMember.getMemberName());
                   chatByNonMember.setShareDes("电话：" + guidMember.getMobile() + " \n" + guidMember.getShopName());
                   chatByNonMember.setShareUrl(groupMap.get("gmCardUrl") + guidMember.getMemberNo());
                   
                   chatByNonMember.setMsgSource(MessageSource.SA.getCode());
                   chatByNonMember.setAllowZkOffline(Boolean.TRUE);    // 允许离线发送到中控客户端
                   logger.info("新增微信好友，自定义推送推送店长名片：{}", chatByNonMember);
                   imChatInfoService.sendChatByNonMember(chatByNonMember);
               }
            }
		} catch(Exception e) {
			logger.error("新增微信好友，自定义推送推送店长名片失败", e);
		}*/
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送分享类消息
	 *
	 * @param addFriends
	 * @param config
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void sendShare(AddFriends addFriends, FindWxPushConfigPageReturn config) {
		try {
	        SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
	        chatByNonMember.setNoWxShop(addFriends.getNoWxGm());
	        chatByNonMember.setNoWx(addFriends.getNoWx());
	        chatByNonMember.setAlias(addFriends.getAlias());
	        chatByNonMember.setType(ChatInfoType.SHARE.getCode());
	        chatByNonMember.setResources(config.getShareIcon());
	        chatByNonMember.setShareTitle(config.getShareTitle());
	        chatByNonMember.setShareDes(config.getShareDes());
	        chatByNonMember.setShareUrl(config.getShareUrl());
	        chatByNonMember.setMsgSource(MessageSource.SA.getCode());
	        chatByNonMember.setAllowZkOffline(Boolean.TRUE);    // 允许离线发送到中控客户端
	        logger.info("新增微信好友，自定义推送推送分享消息：{}", chatByNonMember);
	        imChatInfoService.sendChatByNonMember(chatByNonMember);
		} catch(Exception e) {
			logger.error("新增微信好友，自定义推送推送分享消息", e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送公众号消息
	 *
	 * @param addFriends
	 * @param config
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void sendPublicAccount(AddFriends addFriends, FindWxPushConfigPageReturn config) {
		try {
			FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
			findWxPublicAccount.setCode(config.getShareCode());
			FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService.findWxPublicAccount(findWxPublicAccount);
			if(findWxPublicAccountReturn != null && CommonConstant.I_YES == findWxPublicAccountReturn.getStatus()) {
				SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
				chatByNonMember.setNoWxShop(addFriends.getNoWxGm());
				chatByNonMember.setNoWx(addFriends.getNoWx());
				chatByNonMember.setAlias(addFriends.getAlias());
				chatByNonMember.setType(ChatInfoType.CARD.getCode());
				chatByNonMember.setResources(findWxPublicAccountReturn.getPaLogo());
				chatByNonMember.setShareTitle(findWxPublicAccountReturn.getPaName());
				chatByNonMember.setShareDes(findWxPublicAccountReturn.getPaDesc());
				Map<String, String> contentMap = new HashMap<>();
				contentMap.put("certflag", findWxPublicAccountReturn.getPaCertflag());
				contentMap.put("username", findWxPublicAccountReturn.getPaUsername());
				contentMap.put("alias", findWxPublicAccountReturn.getPaAlias());
				contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY, findWxPublicAccountReturn.getWxParam());
				chatByNonMember.setContent(JsonUtils.jsonFromObject(contentMap));
				chatByNonMember.setMsgSource(MessageSource.SA.getCode());
				chatByNonMember.setAllowZkOffline(Boolean.TRUE);    // 允许离线发送到中控客户端
				logger.info("新增微信好友，自定义推送推送公众号名片：{}", chatByNonMember);
				imChatInfoService.sendChatByNonMember(chatByNonMember);
			}
		} catch(Exception e) {
			logger.error("新增微信好友，自定义推送推送公众号失败", e);
		}
			
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送小程序消息
	 *
	 * @param addFriends
	 * @param config
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月9日
	 *
	 */
	private void sendSmallProgram(AddFriends addFriends, FindWxPushConfigPageReturn config) {
		try {
			FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
			findWxSmallProgram.setCode(config.getShareCode());
			FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService.findWxSmallProgram(findWxSmallProgram);
			if(findWxSmallProgramReturn != null && CommonConstant.I_YES == findWxSmallProgramReturn.getStatus()) {
				SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
				chatByNonMember.setNoWxShop(addFriends.getNoWxGm());
				chatByNonMember.setNoWx(addFriends.getNoWx());
				chatByNonMember.setAlias(addFriends.getAlias());
				chatByNonMember.setType(ChatInfoType.SMALL_PROGRAM.getCode());
				chatByNonMember.setResources(findWxSmallProgramReturn.getSpLogo());
				chatByNonMember.setShareTitle(findWxSmallProgramReturn.getSpName());
				chatByNonMember.setShareDes(findWxSmallProgramReturn.getSpDesc());
				chatByNonMember.setShareUrl(findWxSmallProgramReturn.getSpUrl());
				Map<String, String> contentMap = new HashMap<>();
				contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY, findWxSmallProgramReturn.getWxParam());
				chatByNonMember.setContent(JsonUtils.jsonFromObject(contentMap));
				chatByNonMember.setMsgSource(MessageSource.SA.getCode());
				chatByNonMember.setAllowZkOffline(Boolean.TRUE);    // 允许离线发送到中控客户端
				logger.info("新增微信好友，自定义推送推送小程序：{}", chatByNonMember);
				imChatInfoService.sendChatByNonMember(chatByNonMember);
			}
		} catch(Exception e) {
			logger.error("新增微信好友，自定义推送推送小程序失败", e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送话术、问候语(带动态链接)
	 *
	 * @param addFriends
	 * @param content
	 *
	 * @author 许新龙 CreateDate: 2018年7月25日
	 *
	 */
	public void sendGreetingsAddLink(final AddFriends addFriends, String content) {
		try {
			if(StringUtils.isEmpty(content)) {
				return;
			}
			
			//替换表情
			Map<String, String> greetingEmojiMap = localCacheSystemParams.getSystemParamGroup("ms", "greetingEmoji");
			String findText = "\\{emoji\\}";
	        int num = appearNumber(content, findText);
			for (int i = 0; i < num; i++) {
			    String emojiKey="emoji"+((int)(greetingEmojiMap.size()*Math.random())+1);
			    content = content.replaceFirst(findText, greetingEmojiMap.get(emojiKey));
			}
			
			String mecUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.name(), "Message", "link");
			content = content.replaceAll("\\{shopUrl\\}", mecUrl + "?noWx=" + addFriends.getNoWx() + "&noWxShop="+ addFriends.getNoWxGm());

			
			//发送消息
			SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
            chatByNonMember.setNoWxShop(addFriends.getNoWxGm());
            chatByNonMember.setNoWx(addFriends.getNoWx());
            chatByNonMember.setAlias(addFriends.getAlias());
            chatByNonMember.setType(ChatInfoType.TEXT.getCode());   
            chatByNonMember.setContent(content);
            chatByNonMember.setMsgSource(MessageSource.SA.getCode());
            chatByNonMember.setAllowZkOffline(Boolean.TRUE);    // 允许离线发送到中控客户端
            logger.info("新增微信好友，自定义推送推送话术、问候语：{}", chatByNonMember);
            imChatInfoService.sendChatByNonMember(chatByNonMember);
		} catch(Exception e) {
			logger.error("新增微信好友，自定义推送推送话术、问候语失败", e);
		}
	}
}
