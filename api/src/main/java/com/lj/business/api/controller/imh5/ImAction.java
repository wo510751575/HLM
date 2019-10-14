/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.imh5;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.StringUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.cm.service.IFriendsLinkMaterialService;
import com.lj.business.cm.service.IMaterialCommenService;
import com.lj.business.cm.service.IMaterialService;
import com.lj.business.cm.service.IVrMaterialCommenService;
import com.lj.business.cm.service.IVrMaterialTypeService;
import com.lj.business.cm.service.IWordsInfoService;
import com.lj.business.cm.service.IWordsTypeService;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidCardService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IPmTypeService;
//import com.lj.business.member.service.IShopService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.chat.FindChatImageMessage;
import com.lj.business.supcon.dto.chat.UploadChatVideoMessage;
import com.lj.business.supcon.service.IChatService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImCommentInfoService;
import com.lj.business.weixin.service.IImEmojiPackageService;
import com.lj.business.weixin.service.IImEmojiService;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IImLikeInfoService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxSmallProgramService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.service.AreaHessianService;

@Controller
@RequestMapping(value="/imh5/")
public class ImAction extends Action {

	@Resource
	private IGmAssistantShopService gmAssistantShopService;
	@Resource
	private IShopTerminalService shopTerminalService;
	@Resource
	private IGuidCardService guidCardService;
	@Resource
	private IPersonMemberService personMemberService; // 客户信息服务
	@Resource
	private ICouponMemberRelationService couponMemberRelationService;// 优惠券用户关联service
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	@Resource
	private IPmTypeService pmTypeService; // 客户分类服务
//	@Resource
//	private IShopService shopService; // 分店服务
	@Resource
	private IPmLabelService pmLabelService; // 客户标签服务
	@Resource
	private IPersonMemberBaseService personMemberBaseService; // 客户基础信息服务
	@Resource
	private  IImEmojiPackageService imEmojiPackageService;
	@Resource
	private AreaHessianService areaHessianService; // 地区服务
	@Resource
	private IMaterialCommenService materialcommenService;
	@Resource
	private IMaterialService materialService;
	@Resource
	private ICouponService couponService;
	@Resource
	private IImChatInfoService imChatInfoService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private ICouponRuleService couponRuleService;
	@Resource
	private IMerchantService merchantService;
	@Resource
	private IPersonMemberImService personMemberImService;
	@Resource
	private IImEmojiService imEmojiService;
	@Resource
	private IImFriendsFacade friendsFacade;
	@Resource
	private IVrMaterialCommenService vrMaterialCommenService;
	@Resource
	private IVrMaterialTypeService vrMaterialTypeService;
	@Resource
	private IImFriendsInfoService imFriendsInfoService;
	@Resource
	private IImCommentInfoService imCommentInfoService;
	@Resource
	private IImLikeInfoService imLikeInfoService;
	@Resource
	private IFriendsLinkMaterialService friendsLinkMaterialService;
	@Resource
	public IWordsInfoService wordsInfoService;
	@Resource
	public IWordsTypeService wordsTypeService;
	@Autowired 
	ICommonService commonService;
	@Resource
	private IWxPublicAccountService wxPublicAccountService;
	@Resource
	private IWxSmallProgramService wxSmallProgramService;
    @Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	/**
	 * 
	 *
	 * 方法说明：获取聊天记录视频文件
	 *
	 * @param code		聊天记录CODE
	 * @returnc
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月3日
	 *
	 */
	@RequestMapping(value="findChatVideo.do")
	@ResponseBody
	public FindImChatInfoReturn findChatVideo(String code) {
		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(code);
		FindImChatInfoReturn findImChatInfoReturn = imChatInfoService.findImChatInfo(findImChatInfo);
		
		if(StringUtils.isEmpty(findImChatInfoReturn.getResourcesPath())) {	// 请求中控端上传视频
			UploadChatVideoMessage uploadChatVideoMessage = new UploadChatVideoMessage();
			uploadChatVideoMessage.setNoWxShop(findImChatInfoReturn.getNoWxGm());
			uploadChatVideoMessage.setMsgId(findImChatInfoReturn.getCode());
			uploadChatVideoMessage.setContent(findImChatInfoReturn.getContent());
			
			IChatService basic = commonService.getHessianChatService(uploadChatVideoMessage.getNoWxShop());
			basic.sendUploadChatVideoMessage(uploadChatVideoMessage);
			logger.debug("已向中控请求上传视频聊天记录视频文件");
		}
		return findImChatInfoReturn;
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：获取聊天记录图片
	 *
	 * @param code		聊天记录CODE
	 * @param imgType	图片类型：1中图（默认，当前只支持中图）、2大图
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年2月2日
	 *
	 */
	@RequestMapping(value="findChatImage.do")
	@ResponseBody
	public FindImChatInfoReturn findChatImage(String code) {
		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(code);
		final FindImChatInfoReturn findImChatInfoReturn = imChatInfoService.findImChatInfo(findImChatInfo);
		
//		String content = findImChatInfoReturn.getContent();	// 图片类型消息扩展内容，格式为JSON串
		//如果已有大图直接返回
		if(StringUtils.isNotEmpty(findImChatInfoReturn.getBigImg()) || StringUtils.isNotEmpty(findImChatInfoReturn.getMidImg())) {
			return findImChatInfoReturn;
		}
//		if(StringUtils.isEmpty(content)) {	// 请求中控端上传图片
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					sendFindChatImageMessage(findImChatInfoReturn);
				}
			});
//		}else {
//			// 将扩展内容转换为MAP对象
//			Map<String, String> contentMap = JsonUtils.mapFromJson(content);
//			//String imgKey = imgType == 2 ? Constants.CHAT_IMAGE_BIG_KEY: Constants.CHAT_IMAGE_MIDDLE_KEY;	// 图片类型关键字(默认中图)
//			String imgAddr = contentMap.get(Constants.CHAT_IMAGE_MIDDLE_KEY);
//			// 图片已上传到服务器，则直接返回
//			if(StringUtils.isNotEmpty(imgAddr)) {
//				return findImChatInfoReturn;
//			} else {	// 没有上传到服务器，则请求中控端上传图片
//				this.sendFindChatImageMessage(findImChatInfoReturn);
//			}
//		}
		return findImChatInfoReturn;
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：请求中控端上传图片
	 *
	 * @param findImChatInfoReturn
	 *
	 * @author 曾垂瑜 CreateDate: 2018年2月2日
	 *
	 */
	private void sendFindChatImageMessage(FindImChatInfoReturn findImChatInfoReturn) {
		FindChatImageMessage findChatImageMessage = new FindChatImageMessage();
		findChatImageMessage.setMsgId(findImChatInfoReturn.getCode());
		findChatImageMessage.setNoWxShop(findImChatInfoReturn.getNoWxGm());
		findChatImageMessage.setType(1);
		findChatImageMessage.setFindFlag(2);	// 导购助手查询
		try {
			IChatService basic = commonService.getHessianChatService(findChatImageMessage.getNoWxShop());
			basic.sendFindChatImageMessage(findChatImageMessage);
		} catch(Exception e) {
			logger.error("请求中控客户端获取图片失败", e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询聊天记录详情
	 *
	 * @param code		聊天记录CODE
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年2月2日
	 *
	 */
	@RequestMapping(value="findChatInfo.do")
	@ResponseBody
	public FindImChatInfoReturn findChatInfo(String code) {
		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(code);
		return imChatInfoService.findImChatInfo(findImChatInfo);
	}
}
