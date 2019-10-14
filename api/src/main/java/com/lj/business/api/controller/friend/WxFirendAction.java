package com.lj.business.api.controller.friend;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.dto.FindFriendsMessageRequestDto;
import com.lj.business.api.dto.friend.AddCommentRequestDto;
import com.lj.business.api.dto.friend.AddFriendsInfoRequestDto;
import com.lj.business.api.dto.friend.AddLikeRequestDto;
import com.lj.business.api.dto.friend.AddLikesCommentRequestDto;
import com.lj.business.api.dto.friend.UploadFirendsInfoRequestDto;
import com.lj.business.api.utils.FileUtil;
import com.lj.business.api.utils.JsonDateValueProcessor;
import com.lj.business.api.utils.ZipUtil;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.domain.AddFriends;
import com.lj.business.member.dto.AddFriendsTaskDetailDto;
import com.lj.business.member.dto.AddFriendsTaskDto;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.emus.AddfriendsTaskStatus;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IAddFriendsTaskDetailService;
import com.lj.business.member.service.IAddFriendsTaskService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dto.AddWxFriendsInfoReturn;
import com.lj.business.weixin.dto.FindImFriendsInfoPage;
import com.lj.business.weixin.dto.FriendsMessageDto;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImCommentRequestDto;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.dto.ImLikeRequestDto;
import com.lj.business.weixin.dto.ToFriendsCommentDto;
import com.lj.business.weixin.dto.ToFriendsInfosDto;
import com.lj.business.weixin.dto.ToFriendsLikeDto;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.emus.WhoType;
import com.lj.business.weixin.service.IImCommentInfoService;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IImLikeInfoService;
import com.lj.business.weixin.service.ISendFriendsJobService;
import com.lj.business.weixin.service.IWxFriendsDataService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

@Controller
@RequestMapping("/firend/")
public class WxFirendAction extends Action {

	@Resource
	IWxFriendsDataService wxFriendsDataService;

	@Resource
	IGuidMemberService guidMemberService;

	@Resource
	IShopTerminalService shopTerminalService;

	@Resource
	IPersonMemberService personMemberService;

	@Resource
	IImFriendsInfoService imFriendsInfoService;

	@Resource
	IImFriendsFacade friendsFacade;

	@Resource
	IImCommentInfoService imCommentInfoService;
	@Resource
	IImLikeInfoService imLikeInfoService;
	@Resource
	IAddFriendsTaskDetailService addFriendsTaskDetailService;
	@Resource
	IPersonMemberBaseService personMemberBaseService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private IAddFriendsService addFriendsService;
	@Resource
	private ISendFriendsJobService sendFriendsJobService;
	@Resource
	private IAddFriendsTaskService addFriendsTaskService;
	@Resource
	IGmAssistantShopService gmAssistantShopService;

	/**
	 * 通过手机通讯录加粉失败结果通知
	 */
	@RequestMapping(value = "/addFriendsTaskFailNotify.do")
	@ResponseBody
	public GeneralResponse addFriendsTaskFail(String mobileStatus, String noWxGm) {

		try {
			logger.debug("通讯录加粉失败号码 :{}", mobileStatus);
			if (mobileStatus != null) {

				// 将jsonArray字符串转化为JSONArray
				JSONArray jsonArray = JSONArray.fromObject(mobileStatus);
				// 遍历json数组并取出想要的数据

				int completeNum = 0;
				String taskCode = "";
				if (jsonArray.size() > 0) {
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject2 = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
						String mobile = jsonObject2.optString("moblie");
						String status = jsonObject2.optString("status");
						AddFriendsTaskDetailDto addFriendsTaskDetail = new AddFriendsTaskDetailDto();
						addFriendsTaskDetail.setPhone(mobile);
						addFriendsTaskDetail.setNoWxGm(noWxGm);
						AddFriendsTaskDetailDto addFriendsTaskDetailDto = addFriendsTaskDetailService
								.findByCond(addFriendsTaskDetail);
						if (null == addFriendsTaskDetailDto) {
							logger.error("加粉失败结果通知，加粉任务详情已删除！addFriendsTaskDetail={}", addFriendsTaskDetail);
							continue;
						}
						taskCode = addFriendsTaskDetailDto.getTaskCode();
						AddFriendsTaskDetailDto updateDto = new AddFriendsTaskDetailDto();
						updateDto.setCode(addFriendsTaskDetailDto.getCode());
						boolean upFlag = false;
						String oldStatus = addFriendsTaskDetailDto.getStatus();
						// 原状态为执行中，表示中控第一次搜索
						if (AddfriendsTaskStatus.EXECUTING.getCode().equals(oldStatus)) {
							if (status.equals("1")) {
								updateDto.setDetail("可加好友");
								upFlag = true;
							}
							if (status.equals("2")) {
								updateDto.setDetail("已是好友");
								updateDto.setStatus(AddfriendsTaskStatus.SUCCESS.getCode());
//									updateDto.setCallbackDate(new Date());
								upFlag = true;
								completeNum += 1;
							}
							if (status.equals("65536")) {
								updateDto.setDetail("用户不存在");
								updateDto.setStatus(AddfriendsTaskStatus.SUCCESS.getCode());
//									updateDto.setCallbackDate(new Date());
								upFlag = true;
								completeNum += 1;
							}
							if (upFlag) {
								addFriendsTaskDetailService.updateAddFriendsTaskDetail(updateDto);
							}
						}

						// 原状态为已完成，表示中控第二次搜索
						if (AddfriendsTaskStatus.SUCCESS.getCode().equals(oldStatus)) {
							if (status.equals("1")) {
								upFlag = true;
								updateDto.setDetail("可加好友");
								completeNum -= 1; // 可能第一次中控搜不到，第二次搜到了，这里需要把完成数量-1，因为报文那边会统一加1
							}
							if (status.equals("2")) {
								updateDto.setDetail("已是好友");
								updateDto.setStatus(AddfriendsTaskStatus.SUCCESS.getCode());
//									updateDto.setCallbackDate(new Date());
								upFlag = true;
							}
							if (status.equals("65536")) {
								updateDto.setDetail("用户不存在");
								updateDto.setStatus(AddfriendsTaskStatus.SUCCESS.getCode());
//									updateDto.setCallbackDate(new Date());
								upFlag = true;
							}
							if (upFlag) {
								addFriendsTaskDetailService.updateAddFriendsTaskDetail(updateDto);
							}
						}

					}
					// 主任务成功数+1
					if (StringUtils.isNotEmpty(taskCode)) {
						AddFriendsTaskDto addFriendsTaskDto = new AddFriendsTaskDto();
						addFriendsTaskDto.setCode(taskCode);
						AddFriendsTaskDto taskDto = addFriendsTaskService.findAddFriendsTask(addFriendsTaskDto);
						if (null == taskDto) {
							logger.error("主任务已删除！code={}", taskCode);
							return GeneralResponse.generateFailureResponse("0", "主任务已删除！");
						}
						if (String.valueOf(CommonConstant.I_NO).equals(taskDto.getStatus()) && completeNum != 0) {
							AddFriendsTaskDto updateDto = new AddFriendsTaskDto();
							updateDto.setCode(taskDto.getCode());
							updateDto.setCompleteNum(taskDto.getCompleteNum() + completeNum);
							if (updateDto.getCompleteNum() == taskDto.getTotalPhonenum()) {
								// 全部已完成
								updateDto.setStatus(String.valueOf(CommonConstant.I_YES));
							}
							addFriendsTaskService.updateAddFriendsTask(updateDto);
						}
					}
				}
			}
		} catch (TsfaServiceException e) {
			logger.error("处理通讯录加粉失败号码 ", e);
			return GeneralResponse.generateFailureResponse(e);
		}
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 上传朋友圈数据
	 * 
	 * @param paramJson
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadFriendsInfo.do")
	@ResponseBody
	public GeneralResponse uploadFriendsInfos(String noWxGm, String paramJson) {
		GeneralResponse generalResponse = new GeneralResponse();
		try {
			logger.debug(" upload data :{}", paramJson);
			UploadFirendsInfoRequestDto uploadFirendsInfoRequestDto = converDto(paramJson);
			logger.debug("upload  friendsInfo  info :{} ", uploadFirendsInfoRequestDto);
			AssertUtils.notNull(uploadFirendsInfoRequestDto, "'上传信息不能为空");
//				AssertUtils.notNullAndEmpty(uploadFirendsInfoRequestDto.getShopNo(),"门店号不能为空");
//				AssertUtils.notNullAndEmpty(uploadFirendsInfoRequestDto.getMemberNo(),"用户号不能为空");
			AssertUtils.notNullAndEmpty(noWxGm, "终端微信号不能为空");
			AssertUtils.notNull(uploadFirendsInfoRequestDto.getFriendsInfos(), "朋友圈数据不能为空");

			logger.debug("upload friends data, noWx: " + noWxGm);
			FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalByWx(noWxGm);
			AssertUtils.notNull(shopTerminal, "未知设备数据不接受该设备数据");
			List<ImFriendsInfoDto> friendsList = new ArrayList<ImFriendsInfoDto>();
			for (AddFriendsInfoRequestDto addFriendsInfoRequestDto : uploadFirendsInfoRequestDto.getFriendsInfos()) {
				ImFriendsInfoDto imFriendsInfoDto = new ImFriendsInfoDto();
				try {
					if (!"0".equals(addFriendsInfoRequestDto.getSnsId())) {
						imFriendsInfoDto.setCode(GUID.generateCode());
						imFriendsInfoDto.setFriendsId(addFriendsInfoRequestDto.getSnsId());
						imFriendsInfoDto.setContent(addFriendsInfoRequestDto.getContent());
						imFriendsInfoDto.setImei(shopTerminal.getImei());
						imFriendsInfoDto.setMerchantNo(shopTerminal.getMerchantNo());
						imFriendsInfoDto.setMerchantName(shopTerminal.getMerchantName());
						// imFriendsInfoDto.setShopNo(shopTerminal.getShopNo());
						imFriendsInfoDto.setShopName(shopTerminal.getShopName());
						imFriendsInfoDto.setOptFlag(Integer.valueOf(addFriendsInfoRequestDto.getDirection()));
						imFriendsInfoDto.setSourcetype(addFriendsInfoRequestDto.getSourcetype());
						imFriendsInfoDto.setType(addFriendsInfoRequestDto.getType());
						imFriendsInfoDto.setSharetitle(addFriendsInfoRequestDto.getShareTitle());
						imFriendsInfoDto.setShareurl(addFriendsInfoRequestDto.getShareUrl());
						imFriendsInfoDto.setNoWx(addFriendsInfoRequestDto.getUserName());
						imFriendsInfoDto.setNickName(addFriendsInfoRequestDto.getNickName());
						imFriendsInfoDto.setTimestamp(addFriendsInfoRequestDto.getCreateTime());
						imFriendsInfoDto.setImgAddr(addFriendsInfoRequestDto.getMediasUrl());
						imFriendsInfoDto.setNoWxShop(shopTerminal.getNoWx());
						imFriendsInfoDto.setCommentsJson(addFriendsInfoRequestDto.getCommentsJson());
						imFriendsInfoDto.setLikesJson(addFriendsInfoRequestDto.getLikesJson());
						imFriendsInfoDto.setEnckey(addFriendsInfoRequestDto.getEnckey());
						imFriendsInfoDto.setImgStatus(addFriendsInfoRequestDto.getImgStatus());
						if (StringUtils.isNotEmpty(addFriendsInfoRequestDto.getImgUrls())) {
							imFriendsInfoDto.setImgAddr(addFriendsInfoRequestDto.getImgUrls());

						}
						if (addFriendsInfoRequestDto.getCommentsJson() != null) {
							List<ImCommentInfoDto> commentList = new ArrayList<ImCommentInfoDto>();
							List<ImCommentRequestDto> list = JsonUtils.listFromJson(
									addFriendsInfoRequestDto.getCommentsJson(), ImCommentRequestDto.class);
							for (ImCommentRequestDto requestDto : list) {
								ImCommentInfoDto imCommentInfoDto = new ImCommentInfoDto();
								imCommentInfoDto.setContent(requestDto.getContent());
								imCommentInfoDto.setNickname(requestDto.getNickName());
								imCommentInfoDto.setUsername(requestDto.getUserName());
								imCommentInfoDto.setTonickname(requestDto.getToNickName());
								imCommentInfoDto.setTousername(requestDto.getToUserName());
								imCommentInfoDto.setCommentTime(requestDto.getCommentTime());
								imCommentInfoDto.setFriendsCode(imFriendsInfoDto.getCode());
								imCommentInfoDto.setFriendsId(addFriendsInfoRequestDto.getSnsId());
								imCommentInfoDto.setCommentSerId(requestDto.getCommentSvrID());
								imCommentInfoDto.setMerchantNo(shopTerminal.getMerchantNo());
								imCommentInfoDto.setMerchantName(shopTerminal.getMerchantName());
								imCommentInfoDto.setNoWxShop(shopTerminal.getNoWx());
								commentList.add(imCommentInfoDto);
							}
							imFriendsInfoDto.setComments(commentList);

						}
						if (addFriendsInfoRequestDto.getLikesJson() != null) {
							List<ImLikeInfoDto> likeList = new ArrayList<ImLikeInfoDto>();
							List<ImLikeRequestDto> list = JsonUtils
									.listFromJson(addFriendsInfoRequestDto.getLikesJson(), ImLikeRequestDto.class);
							for (ImLikeRequestDto imLikeRequestDto : list) {
								ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
								imLikeInfoDto.setFriendsId(addFriendsInfoRequestDto.getSnsId());
								imLikeInfoDto.setNickname(imLikeRequestDto.getNickName());
								imLikeInfoDto.setUsername(imLikeRequestDto.getUserName());
								imLikeInfoDto.setFriendsCode(imFriendsInfoDto.getCode());
								imLikeInfoDto.setFriendsId(addFriendsInfoRequestDto.getSnsId());
								imLikeInfoDto.setMerchantNo(shopTerminal.getMerchantNo());
								imLikeInfoDto.setMerchantName(shopTerminal.getMerchantName());
								imLikeInfoDto.setNoWxShop(shopTerminal.getNoWx());
								likeList.add(imLikeInfoDto);
							}
							imFriendsInfoDto.setLikes(likeList);
						}
						friendsList.add(imFriendsInfoDto);
					}
				} catch (Exception e) {
					logger.error("数据复制失败", e);
				}

			}
			if (friendsList.size() != 0) {
				AddWxFriendsInfoReturn addFriendsReturn = wxFriendsDataService.addWxFriendsInfoData(friendsList);
				logger.debug(" data conver success :{}", addFriendsReturn);
				generalResponse.setResult(true);
				generalResponse.setReturnObject(addFriendsReturn.getIdWx());
			}

		} catch (TsfaServiceException e) {
			logger.error("处理微信朋友圈数据异常", e);
			generalResponse.setResult(false);
			generalResponse.setErrorMessage("上传FriendsInfo数据处理失败");
		}
		logger.debug("end friendsInfo data success uploadFirendsInfo.do");
		return generalResponse;
	}

	/**
	 * 上传评论以及点赞数据 只会上传客户的
	 * 
	 * @param request
	 * @param paramJson
	 * @return
	 */
	@RequestMapping(value = "/uploadCommentAndLikesInfo.do")
	@ResponseBody
	public GeneralResponse uploadCommentAndLikesInfos(String noWxGm, String paramJson) {
		logger.info("begin upload commentsAndLikesInfo --- >data :{}", paramJson);

		UploadFirendsInfoRequestDto uploadFirendsInfoRequestDto = converDto(paramJson);
		AssertUtils.notNull(uploadFirendsInfoRequestDto, "'上传信息不能为空");
		AssertUtils.notNullAndEmpty(noWxGm, "终端微信不能为空");

		FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalByWx(noWxGm);

		AssertUtils.notNull(shopTerminal, "未知设备数据不接受该设备数据");
		if (uploadFirendsInfoRequestDto.getLikeComments() != null) {
			logger.debug(" begin convert likes data :{}", uploadFirendsInfoRequestDto.getLikeComments());
			List<ImLikeInfoDto> likeList = new ArrayList<ImLikeInfoDto>();
			List<ImCommentInfoDto> commentList = new ArrayList<ImCommentInfoDto>();

			for (AddLikesCommentRequestDto addLikesRequestDto : uploadFirendsInfoRequestDto.getLikeComments()) {

				ImFriendsInfoDto imFriendsInfoDto = imFriendsInfoService
						.getImFriendsInfoByFriendsId(addLikesRequestDto.getSnsId(), shopTerminal.getNoWx());
				if (imFriendsInfoDto == null) {
					logger.error("朋友圈为空addLikesRequestDto.getSnsId()={}, shopTerminal.getNoWx()={}",
							addLikesRequestDto.getSnsId(), shopTerminal.getNoWx());
				}

				// 没有发现该消息
				if (imFriendsInfoDto == null) {
					logger.error("can not find friendsId:" + addLikesRequestDto.getSnsId());
					return GeneralResponse.generateSuccessResponse();
				}
				if ("1".equals(addLikesRequestDto.getCommentType())) {
					ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
					imLikeInfoDto.setFriendsId(addLikesRequestDto.getSnsId());
					imLikeInfoDto.setUsername(addLikesRequestDto.getCurrUserName());
					imLikeInfoDto.setNickname(addLikesRequestDto.getCurrNickName());
					imLikeInfoDto.setNoWxShop(shopTerminal.getNoWx());
					imLikeInfoDto.setMerchantNo(shopTerminal.getMerchantNo());
					imLikeInfoDto.setMerchantName(shopTerminal.getMerchantName());
					likeList.add(imLikeInfoDto);
				} else {
					ImCommentInfoDto imCommentInfoDto = new ImCommentInfoDto();
					imCommentInfoDto.setFriendsId(addLikesRequestDto.getSnsId());
					imCommentInfoDto.setUsername(addLikesRequestDto.getCurrUserName());
					imCommentInfoDto.setMemberName(
							addLikesRequestDto.getFriendNickName() != null ? addLikesRequestDto.getFriendNickName()
									: addLikesRequestDto.getFriendUserName());
					imCommentInfoDto.setNickname(addLikesRequestDto.getCurrNickName());
					imCommentInfoDto.setMerchantNo(shopTerminal.getMerchantNo());
					imCommentInfoDto.setMerchantName(shopTerminal.getMerchantName());
					imCommentInfoDto.setNoWxShop(shopTerminal.getNoWx());
					imCommentInfoDto.setTonickname(addLikesRequestDto.getFriendNickName());
					imCommentInfoDto.setTousername(
							addLikesRequestDto.getFriendUserName() != null ? addLikesRequestDto.getFriendUserName()
									: addLikesRequestDto.getFriendNickName());
					imCommentInfoDto.setContent(addLikesRequestDto.getComment());
					imCommentInfoDto.setCommentTime(addLikesRequestDto.getCommentTime());
					imCommentInfoDto.setCommentSerId(addLikesRequestDto.getCommentSvrId());
					commentList.add(imCommentInfoDto);
				}
			}

			if (likeList.size() > 0) {
				logger.info("begin ---->   add like list :{}", likeList);
				wxFriendsDataService.addWxLikeInfoData(likeList, shopTerminal.getNoWx());
			}

			if (commentList.size() > 0) {
				logger.info("begin ---->   add comment list :{}", commentList);
				wxFriendsDataService.addWxCommentInfoData(commentList, shopTerminal.getNoWx());
			}

		}
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 查询朋友圈信息
	 * 
	 * @param findFriendsRequestDto
	 * @return
	 */
	@RequestMapping(value = "/findFrinedsInfoList.do")
	@ResponseBody
	public GeneralResponse findFrinedsInfoList(FindImFriendsInfoPage findFriendsRequestDto) {
		logger.info("find FriendsInfo list:{}", findFriendsRequestDto);
		AssertUtils.notNull(findFriendsRequestDto);
		AssertUtils.notNullAndEmpty(findFriendsRequestDto.getNoWxShop(), "终端微信号不能为空");
		AssertUtils.notNullAndEmpty(findFriendsRequestDto.getMemberNoGm(), "导购编号不能为空");
		/**
		 * 上级过滤导购限制
		 */
		FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
		findGmAssistantShop.setMerchantNo(findFriendsRequestDto.getMerchantNo());
		findGmAssistantShop.setNoWx(findFriendsRequestDto.getNoWxShop());
		findGmAssistantShop.setAssistantNo(findFriendsRequestDto.getMemberNoGm());
		findGmAssistantShop.setSource(false);
		List<FindGmAssistantShopReturn> list = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
		if (list != null && list.size() > 0) {
			findFriendsRequestDto.setMemberNoGm(null);
		}

		// 只取发送成功的朋友圈
		findFriendsRequestDto.setStatus(String.valueOf(FriendsSendStatus.SEND_SUCCESS.getStatus()));
		Page<ImFriendsInfoDto> pageData = imFriendsInfoService.findImFriendsInfo(findFriendsRequestDto);
		logger.debug("end return friendsInfo -->data:{}", pageData);
		return GeneralResponse.generateSuccessResponse(pageData);
	}

	@RequestMapping("/findTerminalInfo.do")
	@ResponseBody
	public GeneralResponse findShopTerminalInfo(String noWxShop) {
		GeneralResponse generalResponse = new GeneralResponse();
		logger.info("find findShopTerminalInfo noWx:{}", noWxShop);
		AssertUtils.notNull(noWxShop);
		FindShopTerminalReturn shopTerminalReturn = shopTerminalService.findShopTerminalNormal(noWxShop);
		logger.debug("end return friendsInfo -->data:{}", shopTerminalReturn);
		generalResponse.setReturnObject(shopTerminalReturn);
		generalResponse.setResult(true);
		return generalResponse;
	}

	/**
	 * 发朋友圈
	 * 
	 * @param addFriendsInfoRequestDto
	 * @return
	 */
	@RequestMapping(value = "addFriends.do")
	@ResponseBody
	public GeneralResponse addFriendsInfo(String memberNoGm, AddFriendsInfoRequestDto addFriendsInfoRequestDto,
			String paramJson) {
		try {
			if (!StringUtils.isEmpty(paramJson)) {
				// 将json数据转为对象
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setRootClass(AddFriendsInfoRequestDto.class);
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
				JSONObject ja = JSONObject.fromObject(paramJson, jsonConfig);
				JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));
				addFriendsInfoRequestDto = (AddFriendsInfoRequestDto) JSONObject.toBean(ja,
						AddFriendsInfoRequestDto.class);
			}

			logger.info(" addFriendsInfo ------------>  :{}", addFriendsInfoRequestDto);
			ToFriendsInfosDto toFriendsInfosDto = new ToFriendsInfosDto();
			toFriendsInfosDto.setContent(addFriendsInfoRequestDto.getContent());
//			toFriendsInfosDto.setSourcetype(addFriendsInfoRequestDto.getSourcetype());
			toFriendsInfosDto.setType(addFriendsInfoRequestDto.getType());
			toFriendsInfosDto.setSharetitle(addFriendsInfoRequestDto.getShareTitle());
			toFriendsInfosDto.setShareurl(addFriendsInfoRequestDto.getShareUrl());
			toFriendsInfosDto.setNoWxShop(addFriendsInfoRequestDto.getUserName());
//			toFriendsInfosDto.setNickName(addFriendsInfoRequestDto.getNickName());
			toFriendsInfosDto.setImgAddr(addFriendsInfoRequestDto.getMediasUrl());
			toFriendsInfosDto.setWhoType(addFriendsInfoRequestDto.getWhoType());
			if (StringUtils.isEmpty(addFriendsInfoRequestDto.getWhoType())) {
				toFriendsInfosDto.setWhoType(WhoType.OPEN.getValue());
			}

			toFriendsInfosDto.setWhoNoWxs(addFriendsInfoRequestDto.getWhoNoWxs());
			toFriendsInfosDto.setRemindNoWxs(addFriendsInfoRequestDto.getRemindNoWxs());
			toFriendsInfosDto.setMemberNoGm(memberNoGm);

			// **************新需求，导购发朋友圈只对自己认领的用户可见***************（start）
			// 查詢是否為老闆
			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setAssistantNo(memberNoGm);
			findGmAssistantShop.setNoWx(toFriendsInfosDto.getNoWxShop());
			findGmAssistantShop.setSource(false);
			// 如果有下级，则是老板帐号
			List<FindGmAssistantShopReturn> list = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
			if (list != null && list.size() > 0) {
				toFriendsInfosDto.setWhoType(WhoType.OPEN.getValue());// 谁可以看类型1.公开2.私密3.部分可见4.不给谁看
			} else {
				List<AddFriends> addFriendsList = addFriendsService.findClaimMemberList(memberNoGm);
				int i = 0;
				String whoNoWxs = "";
				for (AddFriends pm : addFriendsList) {
					if (i == 0) {
						whoNoWxs = pm.getNoWx();
					} else {
						whoNoWxs = whoNoWxs + "," + pm.getNoWx();
					}
					i = i + 1;
				}

				// 谁可以看类型1.公开2.私密3.部分可见4.不给谁看
				toFriendsInfosDto.setWhoType(WhoType.PART.getValue());

				// 当whoType 为3和4的时候改字段不为空,传客户微信集合以英文','分隔
				toFriendsInfosDto.setWhoNoWxs(whoNoWxs);
				if (StringUtils.isEmpty(whoNoWxs)) {
					toFriendsInfosDto.setWhoType(WhoType.PRIVATE.getValue());
				}
			}
			// **************新需求，导购发朋友圈只对自己认领的用户可见***************（end）
			AddWxFriendsInfoReturn addWxFriendsInfoReturn = friendsFacade.toFriendsInfo(toFriendsInfosDto);
			if (null == addWxFriendsInfoReturn) {
				return GeneralResponse.generateFailureResponse();
			}
			logger.info("add friends success , wait send reuslt callback:{}", toFriendsInfosDto);
			return GeneralResponse.generateSuccessResponse(addWxFriendsInfoReturn);
		} catch (TsfaServiceException e) {
			logger.error("发朋友圈错误={}", e);
			return GeneralResponse.generateFailureResponse(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("发朋友圈错误={}", e);
			return GeneralResponse.generateFailureResponse("0", "发送朋友圈错误！");
		}

	}

	/**
	 * 评论
	 * 
	 * @param addCommentRequestDto
	 * @return
	 */
	@RequestMapping(value = "addComment.do")
	@ResponseBody
	public GeneralResponse addCommentRequest(AddCommentRequestDto addCommentRequestDto, String noWxGm) {
		try {
			logger.info("addCommentRequest ---- > {} ", addCommentRequestDto);
			AssertUtils.notNullAndEmpty(addCommentRequestDto.getFriendsId(), "朋友圈ID不能为空");
			AssertUtils.notNullAndEmpty(addCommentRequestDto.getContent(), "评论内容不能为空");
			imFriendsInfoService.getImFriendsInfoByFriendsId(addCommentRequestDto.getFriendsId(),
					addCommentRequestDto.getNoWx());
			ToFriendsCommentDto toFriendsCommentDto = new ToFriendsCommentDto();
			toFriendsCommentDto.setToFriendsId(addCommentRequestDto.getFriendsId());
			toFriendsCommentDto.setToConent(addCommentRequestDto.getContent());
			toFriendsCommentDto.setToCommentCode(addCommentRequestDto.getCommentCode());
			toFriendsCommentDto.setToWxName(addCommentRequestDto.getTonickname());
			toFriendsCommentDto.setToWxNo(addCommentRequestDto.getTousername());
			toFriendsCommentDto.setShopWxNo(noWxGm);
			friendsFacade.toComment(toFriendsCommentDto);
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("朋友圈评论失败", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	/**
	 * 点赞
	 * 
	 * @param addLikeRequestDto
	 * @return
	 */
	@RequestMapping(value = "addLikeRequest.do")
	@ResponseBody
	public GeneralResponse addLikeRequest(AddLikeRequestDto addLikeRequestDto) {
		try {
			logger.info("addLikeRequest ---- > {} ", addLikeRequestDto);
			AssertUtils.notNullAndEmpty(addLikeRequestDto.getFriendsId(), "朋友圈ID不能为空");

			ToFriendsLikeDto toFriendsLikeDto = new ToFriendsLikeDto();
			imFriendsInfoService.getImFriendsInfoByFriendsId(addLikeRequestDto.getFriendsId(),
					addLikeRequestDto.getUserName());
			toFriendsLikeDto.setToFriendsId(addLikeRequestDto.getFriendsId());
			toFriendsLikeDto.setToWxNo(addLikeRequestDto.getUserName());
			toFriendsLikeDto.setToNickName(addLikeRequestDto.getNickName());
			toFriendsLikeDto.setShopWxNo(addLikeRequestDto.getUserName());
//			toFriendsLikeDto.setShopNo(imFriendsInfoDto.getShopNo());
			friendsFacade.toImLike(toFriendsLikeDto);
			return GeneralResponse.generateSuccessResponse();
		} catch (Exception e) {
			logger.error("朋友圈点赞失败", e);
			return GeneralResponse.generateFailureResponse(e);
		}

	}

	@RequestMapping(value = "loadNoReadFriendsCount.do")
	@ResponseBody
	public GeneralResponse loadNoReadFriendsCount(FindImFriendsInfoPage findFriendsRequestDto) {
		GeneralResponse generalResponse = new GeneralResponse();
		logger.info("loadNoReadFriendsCount ------ >{}", findFriendsRequestDto);
		AssertUtils.notNull(findFriendsRequestDto);
		AssertUtils.notNull(findFriendsRequestDto.getNoWxShop(), "终端微信号不能为空");
		findFriendsRequestDto.setAppReadFlag("1");
		// findFriendsRequestDto.setOptFlag(2);
		/**
		 * 上级过滤导购限制
		 */
		FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
		findGmAssistantShop.setMerchantNo(findFriendsRequestDto.getMerchantNo());
		findGmAssistantShop.setNoWx(findFriendsRequestDto.getNoWxShop());
		findGmAssistantShop.setAssistantNo(findFriendsRequestDto.getMemberNoGm());
		findGmAssistantShop.setSource(false);
		List<FindGmAssistantShopReturn> list = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
		if (list != null && list.size() > 0) {
			findFriendsRequestDto.setMemberNoGm(null);
		}

		Integer count = imFriendsInfoService.findImFriendsNotRead(findFriendsRequestDto);
		generalResponse.setResult(true);
		generalResponse.setReturnObject(count);
		return generalResponse;
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
			AssertUtils.notNull(findImFriendsInfoPage.getNoWxShop(), "终端微信号不能为空");
			AssertUtils.notNull(findImFriendsInfoPage.getMemberNoGm(), "导购编号不能为空 ");
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
			Integer commentCount = imCommentInfoService.findImCommentInfoAppNotReadCount(imCommentInfoDto);

			ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
			imLikeInfoDto.setNoWxShop(findImFriendsInfoPage.getNoWxShop());
			imLikeInfoDto.setMemberNoGm(findImFriendsInfoPage.getMemberNoGm());

			Integer likeCount = imLikeInfoService.findImLikeAppNotReadCount(imLikeInfoDto);
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
	@RequestMapping(value = "messageList.do")
	@ResponseBody
	public GeneralResponse messageList(FindFriendsMessageRequestDto friendsMessage) {
		logger.info("messageList ------ >{}", friendsMessage);
		AssertUtils.notNull(friendsMessage);
		AssertUtils.notNull(friendsMessage.getNoWxShop(), "终端微信号不能为空");
		AssertUtils.notNull(friendsMessage.getMemberNoGm(), "导购编号不能为空 ");
		Page<FriendsMessageDto> page = new Page<>();
		page.setStart(friendsMessage.getStart());
		page.setLimit(friendsMessage.getLimit());
		FriendsMessageDto findFriendsMessageDto = new FriendsMessageDto();
		findFriendsMessageDto.setNoWxShop(friendsMessage.getNoWxShop());
		findFriendsMessageDto.setMemberNoGm(friendsMessage.getMemberNoGm());

		/**
		 * 上级过滤导购限制
		 */
		FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
		findGmAssistantShop.setNoWx(friendsMessage.getNoWxShop());
		findGmAssistantShop.setAssistantNo(friendsMessage.getMemberNoGm());
		findGmAssistantShop.setSource(false);
		List<FindGmAssistantShopReturn> list = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
		if (list != null && list.size() > 0) {
			findFriendsMessageDto.setMemberNoGm(null);
		}
		page = imCommentInfoService.findFriendsMessage(findFriendsMessageDto, page);
		return GeneralResponse.generateSuccessResponse(page);
	}

	@RequestMapping(value = "getFriendsInfo.do")
	@ResponseBody
	public GeneralResponse getFriendsInfo(ImFriendsInfoDto friendsInfo) {
		GeneralResponse generalResponse = new GeneralResponse();
		logger.info("getFriendsInfo ------ >{}", friendsInfo);
		AssertUtils.notNull(friendsInfo);
		AssertUtils.notNull(friendsInfo.getCode(), "CODE不能为空");
		friendsInfo = imFriendsInfoService.findImFriendsInfo(friendsInfo);
		if (friendsInfo != null) {
			// 1自己、2客户 FIXBUG 466
			if (friendsInfo.getOptFlag() == SenderFlag.GM.getCode()) {
				FindShopTerminalReturn shopTerminalReturn = shopTerminalService
						.findShopTerminalNormal(friendsInfo.getNoWxShop());
				logger.debug("shopTerminalReturn :{}", shopTerminalReturn);
				if (shopTerminalReturn != null) {
					friendsInfo.setHeadImg(shopTerminalReturn.getHeadAddress());
				}
			} else {
				if (StringUtils.isNotEmpty(friendsInfo.getNoWx())) {
					FindPersonMemberBaseReturn member = personMemberBaseService
							.findMemberBaseByNoWxOrAlias(friendsInfo.getNoWx(), friendsInfo.getNoWx());
					if (member != null) {
						friendsInfo.setHeadImg(member.getHeadAddress());
					}
				}
			}

			List<ImCommentInfoDto> commentInfos = imCommentInfoService.findImCommentInfoByFC(friendsInfo.getCode(),
					friendsInfo.getNoWxShop());
			friendsInfo.setComments(commentInfos);
			List<ImLikeInfoDto> likeInfos = imLikeInfoService.findImLikeByFC(friendsInfo.getCode(),
					friendsInfo.getNoWxShop());
			friendsInfo.setLikes(likeInfos);
		}
		generalResponse.setResult(true);
		generalResponse.setReturnObject(friendsInfo);
		logger.info("getFriendsInfo ------ >{}", friendsInfo);
		return generalResponse;
	}

	@SuppressWarnings("rawtypes")
	private UploadFirendsInfoRequestDto converDto(String paramJson) {
		Map<String, Class> clazzMap = new HashMap<String, Class>();
		clazzMap.put("	", ImCommentRequestDto.class);
		clazzMap.put("likesJson", ImLikeRequestDto.class);
		clazzMap.put("friendsInfos", AddFriendsInfoRequestDto.class);
		clazzMap.put("likeComments", AddLikesCommentRequestDto.class);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setClassMap(clazzMap);
		jsonConfig.setRootClass(UploadFirendsInfoRequestDto.class);
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject ja = JSONObject.fromObject(paramJson, jsonConfig);
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));
		UploadFirendsInfoRequestDto uploadData = (UploadFirendsInfoRequestDto) JSONObject.toBean(ja,
				UploadFirendsInfoRequestDto.class, clazzMap);
		return uploadData;
	}

	/**
	 * 方法说明：朋友圈图片压缩文件上传
	 * 
	 * @author 彭俊霖
	 * @CreateDate: 2018-01-31
	 * @param zipfile    压缩文件
	 * @param merchantNo 商户编号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "uploadZipFiles.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse uploadZipFiles(MultipartFile zipfile, String merchantNo) throws Exception {
		AssertUtils.notNull(zipfile, "没有上传文件");
		AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
		try {
			String fileName = zipfile.getOriginalFilename();
			logger.debug("wxFriend uploadZipFiles(filename = {})", fileName);

			// 判断文件格式(只支持zip格式)
			String fileType = FileUtil.getFileType(fileName);
			if (StringUtils.isEmpty(fileType) || !"zip".equals(FilenameUtils.getExtension(fileName.toLowerCase()))) {
				logger.error("不支持的文件格式: {}", fileName);
				return GeneralResponse.generateFailureResponse(ErrorCode.UNSUPPORTED_FILE_FORMAT, "不支持的文件格式");
			}

			Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(),
					"imfile");
			String uploadPath = map.get("uploadPath");
			String uploadUrl = map.get("uploadUrl");
			String today = DateUtils.formatDate(new Date(), DateUtils.PATTERN_yyyyMMdd);
			String filePath = merchantNo + "/" + today + "/" + fileType.toLowerCase() + "/";
			String imageFolder = uploadPath + filePath;// 文件夹路径
//			String imageFolder = "D:/"+filePath;
			// 保存文件
			String fileInputName = FileUtil.saveFile(imageFolder, zipfile);
			File file = new File(imageFolder + fileInputName);
			String descDir = uploadPath + filePath;// 保存路径
			String visDir = uploadUrl + filePath;// 访问路径
			Map<String, Object> resultMap = ZipUtil.unZipFiles(file, descDir, visDir);
//			Map<String, Object> resultMap = ZipUtil.unZipFiles(file,imageFolder,imageFolder);
			return GeneralResponse.generateSuccessResponse(resultMap);
		} catch (Exception e) {
			logger.error("朋友圈图片压缩文件上传失败", e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}

	/**
	 * 重发朋友圈
	 * 
	 * @param addFriendsInfoRequestDto
	 * @return
	 */
	@RequestMapping(value = "repeatFriends.do")
	@ResponseBody
	public GeneralResponse repeatFriends(String merchantNo, String code) {
		AssertUtils.notNullAndEmpty(code, "编号不能为空");
		AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
		try {
			logger.info("repeatFriends ------------>code:{}", code);
			FindImFriendsInfoPage findImFriendsInfoPage = new FindImFriendsInfoPage();
			findImFriendsInfoPage.setMerchantNo(merchantNo);
			findImFriendsInfoPage.setJobCode(code);
			findImFriendsInfoPage.setStatus(FriendsSendStatus.SEND_FAIL.getStatus().toString());
			List<ImFriendsInfoDto> list = imFriendsInfoService.findImFriendsInfos(findImFriendsInfoPage);
			AssertUtils.notNullAndEmpty(list, "朋友圈任务不存在!");
			for (ImFriendsInfoDto imFriendsInfoDto : list) {
				friendsFacade.resendFriendsInfo(imFriendsInfoDto.getCode());
			}
			return GeneralResponse.generateSuccessResponse();
		} catch (TsfaServiceException e) {
			logger.error("发朋友圈错误={}", e);
			return GeneralResponse.generateFailureResponse(e.getExceptionCode(), e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("发朋友圈错误={}", e);
			return GeneralResponse.generateFailureResponse("0", "发送朋友圈错误！");
		}

	}
}