/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.imh5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
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
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidCardService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.dto.FindImFriendsInfoPage;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.emus.SenderFlag;
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

/**
 * 
 * 类说明：朋友圈
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2018年7月31日
 */
@Controller
@RequestMapping(value = "/imh5")
public class ImFriendsController extends Action {

	private static final Logger logger = LoggerFactory.getLogger(ImFriendsController.class);

	@Resource
	private IImFriendsInfoService imFriendsInfoService;
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
	private IImEmojiPackageService imEmojiPackageService;
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

	/**
	 * 
	 *
	 * 方法说明：删除朋友圈 <br>
	 * 1、删除朋友圈需要请求中控客户，是一个异步操作 <br>
	 * 2、此方法只能同步得到请求是否成功 <br>
	 * 3、要得到删除结果，客户端需异步请求查询结果的方法findFriendsDeleteStatus，建议每隔5秒查询一次，最多查询6次
	 *
	 * @param friendsCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月31日
	 *
	 */
	@RequestMapping(value = "delectFriends.do")
	@ResponseBody
	public GeneralResponse delectFriends(String friendsCode, String memberNoGm) {
		try {
			if (StringUtils.isBlank(friendsCode)) {
				return GeneralResponse.generateFailureResponse("0", "朋友圈编号为空");
			}
			imFriendsInfoService.sendDeleteFriendsInfo(friendsCode, memberNoGm);
			return GeneralResponse.generateSuccessResponse();
		} catch (TsfaServiceException e) {
			logger.error("删除朋友圈失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("删除朋友圈失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈是否已成功删除
	 *
	 * @param friendsCode
	 * @return 0-正在删除、1-删除成功、2-查询结果失败
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月31日
	 *
	 */
	@RequestMapping(value = "findFriendsDeleteStatus.do")
	@ResponseBody
	public GeneralResponse findFriendsDeleteStatus(String friendsCode) {
		try {
			ImFriendsInfoDto imFriendsInfoDto = new ImFriendsInfoDto();
			imFriendsInfoDto.setCode(friendsCode);
			imFriendsInfoDto = imFriendsInfoService.findImFriendsInfo(imFriendsInfoDto);
			if (imFriendsInfoDto == null) { // 没有找到则表示删除成功
				return GeneralResponse.generateSuccessResponse();
			} else { // 找到，则表示正在删除
				return GeneralResponse.generateFailureResponse();
			}
		} catch (TsfaServiceException e) {
			logger.error("查询删除朋友圈结果失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		} catch (Exception e) {
			logger.error("查询删除朋友圈结果失败！", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	/**
	 *
	 * 方法说明：查询待评论的数量
	 * 
	 * @param findImIndex
	 * @return
	 * @author 李端强 CreateDate: 2018年2月28日
	 */
	@RequestMapping(value = "loadPendingCommentCount.do")
	@ResponseBody
	public GeneralResponse loadPendingCommentCount(String noWxShop) {
		int count = imFriendsInfoService.findImFriendsInfoPendingCommentCount(noWxShop);
		return GeneralResponse.generateSuccessResponse(count);
	}

	/**
	 * 方法说明：获取周期控制外的待评论朋友圈信息
	 * 
	 * @param model
	 * @param findImFriendsInfoPage
	 * @param pageNo
	 * @return
	 * @author 李端强 CreateDate: 2017年12月22日
	 */
	@RequestMapping(value = { "loadPendingCommentFriends.do" })
	@ResponseBody
	public GeneralResponse loadPendingCommentFriends(String noWxShop, String shopNo, Integer pageNo, Integer pageSize) {
		if (pageSize == null)
			pageSize = 10;// 尺寸
		int start = 0;
		if (pageNo == null)
			pageNo = 1;
		start = (pageNo - 1) * pageSize;// 起始
		// 获取朋友圈信息
		Page<ImFriendsInfoDto> infos = imFriendsInfoService.findImFriendsInfoPendingComment(noWxShop, start, pageSize);
		return GeneralResponse.generateSuccessResponse(infos);
	}

	/**
	 * 
	 *
	 * 方法说明：朋友圈待回复数量统计
	 *
	 * @param findImFriendsInfoPage
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年3月12日
	 *
	 */
	@RequestMapping(value = { "loadToReplyFriendsCount.do" })
	@ResponseBody
	public Integer loadToReplyFriendsCount(FindImFriendsInfoPage findImFriendsInfoPage) {
		return imFriendsInfoService.findImFriendsInfoToReplyCount(findImFriendsInfoPage);
	}

	/**
	 * 
	 *
	 * 方法说明：朋友圈待回复列表
	 *
	 * @param model
	 * @param findImFriendsInfoPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年3月12日
	 *
	 */
	@RequestMapping(value = { "loadToReplyFriends.do" })
	@ResponseBody
	public Map<String, Object> loadToReplyFriends(FindImFriendsInfoPage findImFriendsInfoPage, Integer pageNo,
			Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (findImFriendsInfoPage == null) {
				findImFriendsInfoPage = new FindImFriendsInfoPage();
			}

			// 获取朋友圈信息
			findImFriendsInfoPage.setStart(pageNo == null ? 1 : (pageNo - 1) * 10);
			Page<ImFriendsInfoDto> page = imFriendsInfoService.findImFriendsInfoPage(findImFriendsInfoPage);
			List<ImFriendsInfoDto> list = Lists.newArrayList();
			list.addAll(page.getRows());
			List<String> friendCode = new ArrayList<>();

			for (ImFriendsInfoDto info : list) {
				friendCode.add(info.getCode());
			}
			map.put("friendsInfo", list);

			// 获取朋友圈评论信息
			List<ImCommentInfoDto> commentList = null;
			if (friendCode.size() > 0) {
				commentList = imCommentInfoService.findImCommentInfoByFC(friendCode);
			}
			Map<String, List<ImCommentInfoDto>> commentMap = new HashMap<>();
			if (commentList != null) {
				for (ImCommentInfoDto imCommentInfoDto : commentList) {
					List<ImCommentInfoDto> commentInfoDtos = null;
					if (commentMap.containsKey(imCommentInfoDto.getFriendsCode())) {
						commentInfoDtos = commentMap.get(imCommentInfoDto.getFriendsCode());
					} else {
						commentInfoDtos = new ArrayList<>();
						commentMap.put(imCommentInfoDto.getFriendsCode(), commentInfoDtos);
					}
					commentInfoDtos.add(imCommentInfoDto);
				}
			}
			map.put("commentInfo", commentMap);

			// 获取朋友圈点赞信息
			List<ImLikeInfoDto> likeInfoList = null;
			if (friendCode.size() > 0) {
				likeInfoList = imLikeInfoService.findImLikeInfoByFC(friendCode);
			}
			Map<String, List<ImLikeInfoDto>> likeInfoMap = new HashMap<>();
			if (likeInfoList != null) {
				for (ImLikeInfoDto imLikeInfoDto : likeInfoList) {
					List<ImLikeInfoDto> imLikeInfoDtos = new ArrayList<>();
					if (likeInfoMap.containsKey(imLikeInfoDto.getFriendsCode())) {
						imLikeInfoDtos = likeInfoMap.get(imLikeInfoDto.getFriendsCode());
					} else {
						likeInfoMap.put(imLikeInfoDto.getFriendsCode(), imLikeInfoDtos);
					}
					imLikeInfoDtos.add(imLikeInfoDto);
				}

			}
			if (pageSize != null) {
				findImFriendsInfoPage.setLimit(pageSize);// 尺寸
			}
			if (pageNo != null) {
				findImFriendsInfoPage.setStart((pageNo - 1) * findImFriendsInfoPage.getLimit());// 起始
			}
			findImFriendsInfoPage.setStatus(FriendsSendStatus.SEND_SUCCESS.getStatus().toString());// 只查询发送成功的
			Page<ImFriendsInfoDto> infos = imFriendsInfoService.findImFriendsInfoWeb(findImFriendsInfoPage);
			logger.info(" find data :{}", infos);

			map.put("friendForumPage", infos);// 成功返回
			map.put("likeInfo", likeInfoMap);
			map.put("pageNo", pageNo);
		} catch (Exception e) {
			map.put("friendForumPage", "获取导购绑定的朋友圈信息异常.");
			logger.error("获取朋友圈信息错误", e);
		}
		logger.info("map ---- >", map);
		return map;
	}

	/**
	 * 查询互动消息条数
	 * 
	 * @param findImFriendsInfoPage
	 * @return
	 */
	@RequestMapping(value = "loadInteractMessageCount.do")
	@ResponseBody
	public GeneralResponse loadInteractMessageCount(FindImFriendsInfoPage findImFriendsInfoPage) {
		try {
			logger.info("loadInteractMessage ------ >{}", findImFriendsInfoPage);
			AssertUtils.notNull(findImFriendsInfoPage);
			AssertUtils.notNull(findImFriendsInfoPage.getNoWxShop(), "门店微信号不能为空");
			AssertUtils.notNull(findImFriendsInfoPage.getMemberNoGm(), "导购编号不能为空 ");
//			Map<String,Object> result = new HashMap<String,Object>();
			Integer messageCount = 0;
			/**
			 * 上级过滤导购限制
			 */
			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setMerchantNo(findImFriendsInfoPage.getMerchantNo());
			findGmAssistantShop.setNoWx(findImFriendsInfoPage.getNoWxShop());
			findGmAssistantShop.setAssistantNo(findImFriendsInfoPage.getMemberNoGm());
			findGmAssistantShop.setSource(false);
			List<FindGmAssistantShopReturn> list = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
			if (list != null && list.size() > 0) {
				findImFriendsInfoPage.setMemberNoGm(null);
			}
			ImCommentInfoDto imCommentInfoDto = new ImCommentInfoDto();
			imCommentInfoDto.setNoWxShop(findImFriendsInfoPage.getNoWxShop());
			imCommentInfoDto.setMemberNoGm(findImFriendsInfoPage.getMemberNoGm());
			Integer commentCount = imCommentInfoService.findImCommentInfoWebNotReadCount(imCommentInfoDto);

			ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
			imLikeInfoDto.setNoWxShop(findImFriendsInfoPage.getNoWxShop());
			imLikeInfoDto.setMemberNoGm(findImFriendsInfoPage.getMemberNoGm());
			Integer likeCount = imLikeInfoService.findImLikeWebNotReadCount(imLikeInfoDto);

			messageCount = commentCount + likeCount;
			return GeneralResponse.generateSuccessResponse(messageCount);
		} catch (Exception e) {
			logger.error("朋友圈查询互动消息条数 失败", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	/**
	 * 查询互动消息数据
	 * 
	 * @param findImFriendsInfoPage
	 * @return
	 */
	/*
	 * @RequestMapping(value="messageList.do")
	 * 
	 * @ResponseBody
	 * 
	 * @Deprecated public Page<FriendsMessageDto>
	 * messageList(FindFriendsMessageRequestDto friendsMessage,Integer
	 * pageNo,Integer pageSize){
	 * logger.info("messageList ------ >{}",friendsMessage);
	 * AssertUtils.notNull(friendsMessage);
	 * AssertUtils.notNull(friendsMessage.getNoWxShop(),"门店微信号不能为空");
	 * Page<FriendsMessageDto> page = new Page<>(); page.setStart(pageNo == null ? 1
	 * : (pageNo - 1) * 10); page.setLimit(pageSize==null?10:pageSize);
	 * 
	 * FriendsMessageDto findFriendsMessageDto = new FriendsMessageDto();
	 * findFriendsMessageDto.setNoWxShop(friendsMessage.getNoWxShop());
	 * findFriendsMessageDto.setBeginDate(friendsMessage.getBeginDate());
	 * findFriendsMessageDto.setEndDate(friendsMessage.getEndDate());
	 * findFriendsMessageDto.setMemberName(friendsMessage.getMemberName());
	 * findFriendsMessageDto.setOptFlag(2); // 只查询客户发送的 page =
	 * imCommentInfoService.findFriendsMessage(findFriendsMessageDto, page); return
	 * page; }
	 */

	@RequestMapping(value = "getFriendsInfo.do")
	@ResponseBody
	public ImFriendsInfoDto getFriendsInfo(ImFriendsInfoDto friendsInfo) {
		logger.info("getFriendsInfo ------ >{}", friendsInfo);
		AssertUtils.notNull(friendsInfo);
		AssertUtils.notNull(friendsInfo.getCode(), "CODE不能为空");
		friendsInfo = imFriendsInfoService.findImFriendsInfo(friendsInfo);
		if (friendsInfo != null) {
			if (SenderFlag.ZK.getCode().equals(friendsInfo.getOptFlag())) {
				FindPersonMemberReturn findPersonMemberReturn = personMemberService
						.findPersonMemberByNoWxAndShopWx(friendsInfo.getNoWx(), friendsInfo.getNoWxShop());
				if (findPersonMemberReturn != null) {
					friendsInfo.setHeadImg(findPersonMemberReturn.getHeadAddress());
					friendsInfo.setMemberName(findPersonMemberReturn.getMemberName());
				}
			} else {
				FindShopTerminalReturn shopTerminalReturn = shopTerminalService
						.findShopTerminalNormal(friendsInfo.getNoWxShop());
				logger.debug("shopTerminalReturn :{}", shopTerminalReturn);
				if (shopTerminalReturn != null) {
					friendsInfo.setHeadImg(shopTerminalReturn.getHeadAddress());
				}
			}
			List<ImCommentInfoDto> commentInfos = imCommentInfoService.findImCommentInfoByFC(friendsInfo.getCode(),
					friendsInfo.getNoWxShop());
			friendsInfo.setComments(commentInfos);
			List<ImLikeInfoDto> likeInfos = imLikeInfoService.findImLikeByFC(friendsInfo.getCode(),
					friendsInfo.getNoWxShop());
			friendsInfo.setLikes(likeInfos);
		}

		logger.info("getFriendsInfo ------ >{}", friendsInfo);
		return friendsInfo;
	}

	@RequestMapping(value = "getFriends.do")
	@ResponseBody
	public String getFriends(String code) {
		logger.info("code ------ >{}", code);
		AssertUtils.notNullAndEmpty(code, "编号不能为空");
		ImFriendsInfoDto friendsInfo = new ImFriendsInfoDto();
		friendsInfo.setCode(code);
		ImFriendsInfoDto dto = imFriendsInfoService.findImFriendsInfo(friendsInfo);
		AssertUtils.notNull(dto, "朋友圈不存在");
		logger.info("getFriendsInfo end------ >{}", dto);
		return dto.getImgAddr();
	}
}
