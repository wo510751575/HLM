package com.lj.oms.im;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.WxOpenIdUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.ai.common.ResultCode;
import com.lj.business.ai.dto.MatchProblemReturn;
import com.lj.business.ai.dto.MerchantAutoReplyDto;
import com.lj.business.ai.dto.MerchantPreProblemDto;
import com.lj.business.ai.service.IProblemService;
import com.lj.business.cm.dto.FindMaterialCommenPage;
import com.lj.business.cm.dto.MaterialCommenGroup;
import com.lj.business.cm.dto.activity.FindActivityPage;
import com.lj.business.cm.dto.activity.FindActivityPageReturn;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPage;
import com.lj.business.cm.dto.vrMaterialCommen.FindVrMaterialCommenPageReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeApiReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoApp;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWeb;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWebReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypeSelectReturn;
import com.lj.business.cm.friendsLinkMaterial.AddFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPage;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPageReturn;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialReturn;
import com.lj.business.cm.friendsLinkMaterial.UpdateFriendsLinkMaterial;
import com.lj.business.cm.service.IActivityService;
import com.lj.business.cm.service.IFriendsLinkMaterialService;
import com.lj.business.cm.service.IMaterialCommenService;
import com.lj.business.cm.service.IMaterialService;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.cm.service.IVrMaterialCommenService;
import com.lj.business.cm.service.IVrMaterialTypeService;
import com.lj.business.cm.service.IWordsInfoService;
import com.lj.business.cm.service.IWordsTypeService;
import com.lj.business.common.CommonConstant;
import com.lj.business.common.KeyConstant;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.cp.dto.coupon.AddCoupon;
import com.lj.business.cp.dto.couponMemberRelation.AddCouponMemberRelation;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.emus.RuleStatus;
import com.lj.business.cp.emus.ToCoupon;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dto.EditPersonMember;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindImIndexPage;
import com.lj.business.member.dto.FindImIndexPageReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberPage;
import com.lj.business.member.dto.FindPersonMemberPageReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.FindPersonMemberReturnList;
import com.lj.business.member.dto.FindPmInfoAll;
import com.lj.business.member.dto.FindPmInfoAllReturn;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.FindShopGmDto;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.addfriends.AddAddFriends;
import com.lj.business.member.dto.addfriends.AddAddFriendsReturn;
import com.lj.business.member.dto.addfriends.FindAddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPageReturn;
import com.lj.business.member.dto.addfriends.FindAddFriendsReturn;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.im.FindGuidByShopAndMember;
import com.lj.business.member.dto.im.FindGuidByShopAndMemberReturn;
import com.lj.business.member.dto.im.FindPersonMemberByShopAndNoWx;
import com.lj.business.member.dto.im.FindPersonMemberByShopAndNoWxReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.emus.Gender;
import com.lj.business.member.emus.MemerSourceType;
import com.lj.business.member.emus.PmTypeTimeFlag;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidCardService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmLabelService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.chat.FindChatImageMessage;
import com.lj.business.supcon.dto.chat.UploadChatVideoMessage;
import com.lj.business.supcon.dto.contacts.FindWxInfoRequestDto;
import com.lj.business.supcon.dto.contacts.FindWxInfoReturnDto;
import com.lj.business.supcon.dto.contacts.ScanAddFriendRequestDto;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.IChatService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.business.supcon.service.ITokenService;
import com.lj.business.weixin.common.Constants;
import com.lj.business.weixin.domain.FriendPointCycle;
import com.lj.business.weixin.dto.FindImFriendsInfoPage;
import com.lj.business.weixin.dto.FriendsMessageDto;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.dto.ToDownloadPic;
import com.lj.business.weixin.dto.ToFriendsCommentDto;
import com.lj.business.weixin.dto.ToFriendsInfosDto;
import com.lj.business.weixin.dto.ToFriendsLikeDto;
import com.lj.business.weixin.dto.imchat.FindChatInfoPageFromWeb;
import com.lj.business.weixin.dto.imchat.FindChatInfoPageFromWebReturn;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMember;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMemberReturn;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByTerminal;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateThirdHaveReadFromWeb;
import com.lj.business.weixin.dto.imemoji.FindImEmoji;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiReturn;
import com.lj.business.weixin.dto.imemoji.NewEmojiPackageReturn;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.emus.ChatRoomType;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IFriendPointCycleService;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImCommentInfoService;
import com.lj.business.weixin.service.IImEmojiPackageService;
import com.lj.business.weixin.service.IImEmojiService;
import com.lj.business.weixin.service.IImFriendsFacade;
//import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IImLikeInfoService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxSmallProgramService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.RedisCache;
import com.lj.oms.common.BaseController;
import com.lj.oms.dto.FindFriendsMessageRequestDto;
import com.lj.oms.dto.FriendsDownRequestDto;
import com.lj.oms.dto.ImChatInfoTaskCallable;
import com.lj.oms.dto.ResponseDto;
import com.lj.oms.emus.ChatUtilTypeEnum;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.service.sys.AreaService;
import com.lj.oms.utils.AddQrCodeUtils;
import com.lj.oms.utils.FileUtil;
import com.lj.oms.utils.JsonPage;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.audioUtil.AmrToMp3Util;

/**
 * 
 * 
 * 类说明：导购助手Controller
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author zhangting
 * 
 *         CreateDate: 2017年11月28日
 */
@Controller
@RequestMapping(value = "${adminPath}/im")
public class ImController extends BaseController {

	private static final String DEFAULT_FRIENDS_POINT_CYCLE = "30";

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
	@Autowired
	private IActivityService activityService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private ICouponRuleService couponRuleService;
	@Resource
	private IPersonMemberImService personMemberImService;
	@Resource
	private IImEmojiService imEmojiService;
	@Autowired
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
	private IChatRoomService chatRoomService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private RedisCache redisCache; // 次数限制
	@Autowired
	private IAddFriendsService addFriendsService;
	@Autowired
	private IMerchantParamsService merchantParamsService;
	@Autowired
	private IFriendPointCycleService friendPointCycleService; // 朋友圈提示周期
	@Autowired
	private ITokenService tokenService;
	@Autowired
	private IProblemService problemService;
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
	 * 方法说明：分页查询
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author zhangting CreateDate: 2017年11月28日
	 *
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, FindShopTidFromWeb findShopTidFromWeb,
			HttpServletRequest request) {
		try {
			indexData(model, findShopTidFromWeb);
		} catch (Exception e) {
			logger.error("查询首页信息错误：" + e);
		}
		return "modules/im/indexIm";
	}

	/**
	 * 方法说明：直接跳转IM的HTML5页面
	 * 
	 * @author 李端强 2018年1月22日19:22:24
	 */
	@RequestMapping(value = { "listHtml5" })
	public String listHtml5(HttpServletRequest request) {
		logger.debug("listHtml5(HttpServletRequest request={}) - start", request);
		try {
			String path = "/im-web";// HTML5项目首页
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path;
			Token token = tokenService.generateToken(UserUtils.getUser().getId(), "IM_H5", Token.TOKEN_TIMEOUT_SECONDS);
			// 测试地址
			String returnString = "redirect:" + basePath + "?token=" + token.getAccessToken();
			logger.debug("listHtml5(HttpServletRequest) - end - return value={}", returnString);
			return returnString;
		} catch (Exception e) {
			logger.error("listHtml5(HttpServletRequest)", e);
			e.printStackTrace();
		}
		logger.debug("listHtml5(HttpServletRequest) - end - return value={}", "");
		return "";
	}

	/**
	 * 
	 *
	 * 方法说明：分页查询
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author zhangting CreateDate: 2017年11月28日
	 *
	 */
	@RequestMapping(value = { "friendList" })
	public String friendList(Model model, Integer pageNo, Integer pageSize, FindShopTidFromWeb findShopTidFromWeb) {
		try {
			indexData(model, findShopTidFromWeb);
		} catch (Exception e) {
			logger.error("查询首页信息错误：" + e);
		}
		return "modules/im/friendIm";
	}

	private void indexData(Model model, FindShopTidFromWeb findShopTidFromWeb) {
		String assistantNo = UserUtils.getUser().getId();// 导购助手编号
		String merchantNo = UserUtils.getMerchantNo();// 商户编号

		// 客户分组查询
		FindPmTypePageReturn findPmTypePageReturn = new FindPmTypePageReturn();
		findPmTypePageReturn.setStatus(CommonConstant.Y);
		findPmTypePageReturn.setMerchantNo(merchantNo);
		List<FindPmTypePageReturn> pmType = pmTypeService.findPmTypePages(findPmTypePageReturn);
		// 新增 今日新增/7天内新增/30天内新增 客户类型
//		addTimePmType(pmType);	FIXBUG 312
		model.addAttribute("pmType", pmType);

		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
		findGuidMemberPage.setLimit(500);
		findGuidMemberPage.setMerchantNo(merchantNo);

		Page<FindGuidMemberPageReturn> pageDto = guidMemberService.findGuidMemberPage(findGuidMemberPage);
		List<FindGuidMemberPageReturn> guidMembers = Lists.newArrayList();
		guidMembers.addAll(pageDto.getRows());
		model.addAttribute("guidMembers", guidMembers);
		// 导购助手管理的终端列表查询
		findShopTidFromWeb.setMerchantNo(merchantNo);
		findShopTidFromWeb.setAssistantNo(assistantNo);
		findShopTidFromWeb.setQueryOnlineBool(Boolean.TRUE); // 查询终端是否在线
		List<FindShopTidFromWebReturn> shopTids = shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
		model.addAttribute("shopTids", shopTids);
		// 查询的参数
		model.addAttribute("ShopTidFromWeb", findShopTidFromWeb);
		model.addAttribute("user_photo", UserUtils.getUser().getPhoto());
	}

	/**
	 * 新增 今日新增/7天内新增/30天内新增 客户类型
	 * 
	 * @author 彭俊霖
	 * @param pmType
	 */
	public void addTimePmType(List<FindPmTypePageReturn> pmType) {
		FindPmTypePageReturn todayFlag = new FindPmTypePageReturn();
		todayFlag.setCode(PmTypeTimeFlag.TODAY.toString());
		todayFlag.setTypeName(PmTypeTimeFlag.TODAY.getName());
		FindPmTypePageReturn weekFlag = new FindPmTypePageReturn();
		weekFlag.setCode(PmTypeTimeFlag.WEEK.toString());
		weekFlag.setTypeName(PmTypeTimeFlag.WEEK.getName());
		FindPmTypePageReturn monthFlag = new FindPmTypePageReturn();
		monthFlag.setCode(PmTypeTimeFlag.MONTH.toString());
		monthFlag.setTypeName(PmTypeTimeFlag.MONTH.getName());
		pmType.add(0, todayFlag);
		pmType.add(1, weekFlag);
		pmType.add(2, monthFlag);
	}

	/**
	 * 
	 *
	 * 方法说明：客户聊天
	 *
	 * @param model
	 * @return
	 *
	 * @author zhangting CreateDate: 2017年11月29日
	 *
	 */
	@RequestMapping(value = { "chat" })
	public String chat(Model model, String memberNo, String noWxGm) {
		try {

			// 终端信息
			FindShopTerminalReturn findShopTerminalReturn = shopTerminalService.findShopTerminalByWx(noWxGm);
			model.addAttribute("findShopTerminalReturn", findShopTerminalReturn);

			// 个人资料
			Map<String, String> param = new HashMap<>();
			param.put("merchantNo", UserUtils.getMerchantNo());
			param.put("memberNo", memberNo);
			param.put("shopWx", noWxGm);
			FindPersonMemberPageReturn memberPageReturn = personMemberService.getByCond(param);

			// 查询朋友圈提醒周期
			FriendPointCycle friendPointCycleReturn = friendPointCycleService.selectByMemberNo(memberNo);
			if (friendPointCycleReturn != null) {
				memberPageReturn.setCycle(friendPointCycleReturn.getCycle());
			} else {
				memberPageReturn.setCycle(Integer.valueOf(getPointCycleValue(null)));
			}

			// 个人资料
			model.addAttribute("personMember", memberPageReturn);

			// VR素材类型
			FindVrMaterialType findVrMaterialType = new FindVrMaterialType();
			findVrMaterialType.setMerchantNo(UserUtils.getMerchantNo());
			List<FindVrMaterialTypeApiReturn> typeApiReturns = vrMaterialTypeService
					.findVrMaterialTypeReturnList(findVrMaterialType);
			model.addAttribute("vrMaterialType", typeApiReturns);

			// 客户来源枚举
			model.addAttribute("memerSources", MemerSourceType.values());
			// 性别
			model.addAttribute("genders", Gender.values());

			// 朋友圈提醒周期
			model.addAttribute("cycle", getPointCycleValue(memberNo));

		} catch (Exception e) {
			logger.error("查询客户聊天错误：" + e);
		}
		return "modules/im/chat";
	}

	/**
	 * 
	 *
	 * 方法说明：朋友圈查询个人信息
	 *
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月29日
	 *
	 */
	@RequestMapping(value = { "personInfo" })
	public String personInfo(Model model, String memberNo, String shopWx) {
		try {
			chat(model, memberNo, shopWx);
		} catch (TsfaServiceException ex) {
			logger.error("查询朋友圈个人信息错误！", ex.getExceptionInfo());
		} catch (Exception e) {
			logger.error("查询朋友圈个人信息错误：", e);
		}
		return "modules/im/personInfo";
	}

	/**
	 * 
	 *
	 * 方法说明：客户聊天记录
	 *
	 * @param model
	 * @return
	 *
	 * @author zhangting CreateDate: 2017年11月30日
	 *
	 */
	@RequestMapping(value = { "historyChat" })
	public String historyChat(Model model, FindPersonMemberPage findPersonMemberPage) {
		try {
			Page<FindPersonMemberPageReturn> pageDto = personMemberService.queryPersonMemberPage(findPersonMemberPage);
			List<FindPersonMemberPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			FindPersonMemberPageReturn memberPageReturn = list.get(0);

			// 个人资料
			model.addAttribute("personMember", memberPageReturn);
			// 导购个人信息findGuidMember
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(findPersonMemberPage.getMemberNoGm());
			FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
			model.addAttribute("findGuidMemberReturn", findGuidMemberReturn);
		} catch (Exception e) {
			logger.error("查询客户聊天记录错误：" + e);
		}
		return "modules/im/historyChat";
	}

	/**
	 * 
	 *
	 * 方法说明：导购聊天记录
	 *
	 * @param model
	 * @param findChatInfoPageFromWeb
	 * @return
	 * @throws Exception
	 *
	 * @author 梅宏博 CreateDate: 2017年12月11日
	 *
	 */
	@RequestMapping(value = { "gmChat" })
	@ResponseBody
	public IPage<FindChatInfoPageFromWebReturn> gmChat(Model model, FindChatInfoPageFromWeb findChatInfoPageFromWeb)
			throws Exception {
		logger.debug("gmChat(Model model, FindChatInfoPageFromWeb findChatInfoPageFromWeb={})",
				findChatInfoPageFromWeb);
		IPage<FindChatInfoPageFromWebReturn> page = imChatInfoService.findChatInfoPageFromWeb(findChatInfoPageFromWeb);
		return page;
	}

	/**
	 * 
	 * 方法说明：IM聊天表情/优惠券/活动/个人名片/素材/VR素材
	 * 
	 * @param code 表情包code
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年12月4日
	 *
	 */
	@RequestMapping(value = "expressionPackage")
	@ResponseBody
	public Object expressionPackage(String packageCode, String type, String noWxGm, String memberNo,
			FindVrMaterialCommenPage findVrMaterialCommenPage) {

		String merchantNo = UserUtils.getMerchantNo();
		if (ChatUtilTypeEnum.IM_EMO_JI.toString().equals(type)) {
			// 表情包及表情
			FindImEmojiPackage findImEmojiPackage = new FindImEmojiPackage();
			List<NewEmojiPackageReturn> imEmojiList = imEmojiPackageService.findImWebEmojiPackage(findImEmojiPackage);
			return imEmojiList;
		}

		if (ChatUtilTypeEnum.COUPON.toString().equals(type)) {
			// 必填 商户编号 终端编号
			List<FindCouponRuleReturn> findCouponRuleList = null;
			try {
				FindCouponRule findCouponRule = new FindCouponRule();
				findCouponRule.setMerchantNo(merchantNo);
				findCouponRule.setToCoupon(ToCoupon.NONE.toString());
				findCouponRule.setRuleStatus(RuleStatus.YES.toString());
				// 客户端分享地址
				String cp_share_url = localCacheSystemParams.getSystemParam(SystemAliasName.cp.name(), "share_url",
						"cp_share_url");
				// 客户端展示的地址
				String cp_client_url = localCacheSystemParams.getSystemParam(SystemAliasName.cp.name(), "client_url",
						"cp_client_url");

				findCouponRuleList = couponRuleService.findCouponRuleList(findCouponRule);
				for (FindCouponRuleReturn findCouponRuleReturn : findCouponRuleList) {
					findCouponRuleReturn.setShareUrl(cp_share_url + findCouponRuleReturn.getCode());
					findCouponRuleReturn.setClientUrl(cp_client_url + findCouponRuleReturn.getCode());
					findCouponRuleReturn.setMerchantLogoUrl(
							localCacheSystemParams.getSystemParam(SystemAliasName.api.name(), "logo", "couponLogoUrl"));
				}
				Collections.sort(findCouponRuleList, new Comparator<FindCouponRuleReturn>() {
					@Override
					public int compare(FindCouponRuleReturn o1, FindCouponRuleReturn o2) { // 数组倒序
						return (int) (o2.getCouponNotes() - o1.getCouponNotes());
					}
				});
			} catch (Exception e) {
				logger.error("查询IM聊天优惠券信息错误：" + e);
			}

			return findCouponRuleList;
		}
		if (ChatUtilTypeEnum.ACTIVITY.toString().equals(type)) {
			// 活动
			FindActivityPage findActivityPage = new FindActivityPage();
			findActivityPage.setMerchantNo(merchantNo);
			findActivityPage.setLimit(100);
			Page<FindActivityPageReturn> pageDto = activityService.findActivityPage(findActivityPage);
			List<FindActivityPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			return list;
		}

		if (ChatUtilTypeEnum.GUID_CARD.toString().equals(type)) {
			// 终端信息
			FindShopTerminalReturn findShopTerminalReturn = shopTerminalService.findShopTerminalByWx(noWxGm);
			// 个人名片访问地址
			String gmCardUrl = localCacheSystemParams.getSystemParam(SystemAliasName.api.name(), "common", "gmCardUrl");
			// 导购头像地址
//			String guidDHUrl=localCacheSystemParams.getSystemParam(SystemAliasName.api.name(), "common", "guidDHUrl");
			Map<String, String> map = new HashMap<>();
			map.put("gmCardUrl", gmCardUrl + noWxGm);
			map.put("guidDHUrl", findShopTerminalReturn.getHeadurl());
			map.put("nickName", findShopTerminalReturn.getNickname());
			map.put("alias", findShopTerminalReturn.getAlias());
			return map;
		}

		if (ChatUtilTypeEnum.MATERIAL.toString().equals(type)) {
			Map<String, List<?>> map = new HashMap<>();
			// 素材中心
			FindMaterialCommenPage findMaterialCommenPage = new FindMaterialCommenPage();
			findMaterialCommenPage.setMerchantNo(merchantNo);

			List<MaterialCommenGroup> materialCommenGroup = materialcommenService
					.findMaterialCommenByMerchantNo(findMaterialCommenPage);
			map.put("materialCommenGroup", materialCommenGroup);

			return map;
		}

		if (ChatUtilTypeEnum.VR_MATERIAL.toString().equals(type)) {
			findVrMaterialCommenPage.setMerchantNo(merchantNo);
			List<String> typeList = Lists.newArrayList();
			if (StringUtils.isNotBlank(findVrMaterialCommenPage.getCodes())) {
				String codes = findVrMaterialCommenPage.getCodes();
				if (codes.startsWith(",")) {
					codes = codes.substring(1);
				}
				if (codes.endsWith(",")) {
					codes = codes.substring(0, codes.length() - 1);
				}

				if (StringUtils.isNotEmpty(codes)) {
					String[] str = codes.split(",");
					for (String string : str) {
						typeList.add(string);
					}
				}
			}
			findVrMaterialCommenPage.setTypeCodes(typeList);
			Page<FindVrMaterialCommenPageReturn> page = vrMaterialCommenService
					.findVrMaterialCommenPage(findVrMaterialCommenPage);
			List<FindVrMaterialCommenPageReturn> list = Lists.newArrayList();
			list.addAll(page.getRows());
			return list;
		}
		return null;
	}

	/**
	 * 
	 * 方法说明：导购聊天
	 *
	 * @param model
	 * @return
	 *
	 * @author zhangting CreateDate: 2017年12月01日
	 *
	 */
	@RequestMapping(value = { "guideChat" })
	public String guideChat(Model model, FindPersonMember findPersonMember) {
		try {
			FindPersonMemberPageReturn personMember = findPersonMember(null, findPersonMember);

			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(personMember.getMemberNoGm());
			FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);

			FindShopTidFromWeb findShopTidFromWeb = new FindShopTidFromWeb();
			findShopTidFromWeb.setMerchantNo(UserUtils.getMerchantNo());
			findShopTidFromWeb.setAssistantNo(UserUtils.getUser().getId());
			List<FindShopTidFromWebReturn> shopTids = shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);

			FindShopTidFromWebReturn shopTid = null;
			for (FindShopTidFromWebReturn findShopTidFromWebReturn : shopTids) {
				if (guidMember.getNoWx().equals(findShopTidFromWebReturn.getNoWx())) {
					shopTid = findShopTidFromWebReturn;
					break;
				}
			}
			// 朋友圈提醒周期
			FriendPointCycle friendPointCycle = friendPointCycleService.selectByMemberNo(personMember.getMemberNo());
			if (friendPointCycle != null) {
				personMember.setRemark4(String.valueOf(friendPointCycle.getCycle()));
			} else {
				personMember.setRemark4(getPointCycleValue(null));
			}

			model.addAttribute("personMember", personMember);
			model.addAttribute("guidMember", guidMember);
			model.addAttribute("shopTid", shopTid);

			// 客户来源枚举
			model.addAttribute("memerSources", MemerSourceType.values());

			// 获取添加了导购私人微信对应的客户信息
			FindPersonMemberByShopAndNoWx find = new FindPersonMemberByShopAndNoWx();
//			 find.setShopNo(guidMember.getShopNo());
			find.setNoWx(guidMember.getNoWxPersonal());
			FindPersonMemberByShopAndNoWxReturn personMemberByGm = personMemberImService
					.findPersonMemberByShopWxAndNoWx(find);
			model.addAttribute("personMemberByGm", personMemberByGm);

			// VR素材类型
			FindVrMaterialType findVrMaterialType = new FindVrMaterialType();
			findVrMaterialType.setMerchantNo(findPersonMember.getMerchantNo());
			List<FindVrMaterialTypeApiReturn> typeApiReturns = vrMaterialTypeService
					.findVrMaterialTypeReturnList(findVrMaterialType);
			model.addAttribute("vrMaterialType", typeApiReturns);

			// 电商网站
//			 model.addAttribute("eshop", CcUtils.getEshopUrl());

		} catch (Exception e) {
			logger.error("查询导购聊天错误：" + e);
		}
		return "modules/im/guideChat";
	}

	/**
	 * 
	 *
	 * 方法说明：终端微信（终端）详情查询/终端微信明细查询
	 *
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月04日
	 *
	 */
	@RequestMapping(value = { "shopTerminalInfo" })
	@ResponseBody
	public FindShopTerminalReturn shopTerminalInfo(Model model, FindShopTerminal findShopTerminal) {
		return shopTerminalService.findShopTerminal(findShopTerminal);
	}

	/**
	 * 
	 *
	 * 方法说明：未读客户数
	 *
	 * @param model
	 * @param findUnreadCountByTerminal
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月5日
	 *
	 */
	@RequestMapping(value = { "unreadPersonCount" })
	@ResponseBody
	public List<Map<String, Object>> unreadPersonCount(Model model, String noWxList) {
		try {
			if (StringUtils.isNoneBlank(noWxList)) {
				String[] noWxs = noWxList.split(",");
				List<String> list = new ArrayList<>();
				for (String string : noWxs) {
					list.add(string);
				}
				FindUnreadCountByTerminal findUnreadCountByTerminal = new FindUnreadCountByTerminal();
				findUnreadCountByTerminal.setNoWxList(list);
				findUnreadCountByTerminal.setMerchantNo(UserUtils.getMerchantNo());
				return imChatInfoService.findUnreadPersonCountByWx(findUnreadCountByTerminal);
			}
		} catch (Exception e) {
			logger.error("查询未读客户数错误：" + e);
		}
		return null;
	}

	/**
	 * 
	 *
	 * 方法说明：客户未读聊天记录数统计（导购助手）
	 *
	 * @param model
	 * @param noWxShop     终端微信
	 * @param memberNoList 客户列表
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月5日
	 *
	 */
	@RequestMapping(value = { "unreadCountByMember" })
	@ResponseBody
	public List<FindUnreadCountByMemberReturn> unreadCountByMember(Model model, String noWxShop, String memberNoList) {
		List<FindUnreadCountByMemberReturn> returnList = new ArrayList<FindUnreadCountByMemberReturn>();
		try {
			if (StringUtils.isNotBlank(memberNoList)) {
				String[] memberNos = memberNoList.split(",");
				List<String> list = new ArrayList<>();
				for (String string : memberNos) {
					list.add(string);
				}
				FindUnreadCountByMember findUnreadCountByMember = new FindUnreadCountByMember();
				findUnreadCountByMember.setNoWxShop(noWxShop);
				findUnreadCountByMember.setMemberNoList(list);
				returnList = imChatInfoService.findUnreadCountByMemberFromWeb(findUnreadCountByMember);
			}
		} catch (Exception e) {
			logger.error("查询未读聊天记录错误：" + e);
		}
		return returnList;
	}

	/**
	 * 
	 *
	 * 方法说明：发送聊天记录
	 *
	 * @param model
	 * @param sendImChatInfo
	 * @param code           客户关联表（pm）code、微信小程序记录code、微信公众号记录code
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月5日
	 *
	 */
	@RequestMapping(value = { "sendChatMessage" })
	@ResponseBody
	public Map<String, String> sendChatMessage(Model model, MultipartFile uploadFile, SendImChatInfo sendImChatInfo,
			String couponRuleCode, String code, String flag) {

		Map<String, String> resultMap = new HashMap<>();
		if (sendImChatInfo.getChatroomType() == null || StringUtils.isEmpty(sendImChatInfo.getMemberNo())
				|| StringUtils.isEmpty(sendImChatInfo.getType())) {
			resultMap.put("false", "发送失败：参数错误！");
			return resultMap;
		}
		/**
		 * 公共配置
		 */
		Map<String, String> groupMap = localCacheSystemParams.getSystemParamGroup(SystemAliasName.api.name(), "common");
		String commonUrl = groupMap.get("commonUrl");
		commonUrl = commonUrl.endsWith("/") ? commonUrl.substring(0, commonUrl.length() - 1) : commonUrl;
		String noWxGm = null; // 中控微信，用来获取中控微信是否离线

		if (ChatRoomType.PERSONAL.getCode().equals(sendImChatInfo.getChatroomType())) {
			noWxGm = sendImChatInfo.getNoWxGm();
		} else { // 群聊查询群信息
			FindChatRoom findChatRoom = new FindChatRoom();
			findChatRoom.setCode(sendImChatInfo.getMemberNo());
			FindChatRoomReturn findChatRoomReturn = chatRoomService.findChatRoom(findChatRoom);
			if (null == findChatRoomReturn) {
				resultMap.put("false", "发送失败：群聊不存在！");
				return resultMap;
			}
			noWxGm = findChatRoomReturn.getNoWxZk();
		}

		try {
			if (StringUtils.isNotBlank(sendImChatInfo.getShareUrl())) {
				sendImChatInfo.setShareUrl(StringEscapeUtils.unescapeHtml4(sendImChatInfo.getShareUrl()).toString());
			}

			sendImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
			sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
			sendImChatInfo.setChatAssistantCode(UserUtils.getUser().getId()); // 导购助手编号
			sendImChatInfo.setChatAssistantName(UserUtils.getUser().getName()); // 导购助手名称
			sendImChatInfo.setNoWxGm(noWxGm);

			switch (sendImChatInfo.getType()) {
			case "1": // 文本 文本消息内容不转义
				sendImChatInfo.setContent(StringEscapeUtils.unescapeHtml4(sendImChatInfo.getContent()).toString());
				break;
			case "42": // 发送名片，包括个人名片和公众号
				/**
				 * content字段以json格式保存以下参数： 1、发送个人名片时(前端组装参数)： 发送人微信号（noWx）：username
				 * 发送人微信别名：alias 发送人客户编号：memberNo 发送人所属导购：memberNoGm 名片标识：certflag=0
				 * 2、发送公众号时(后台组装参数)： 公众号xml参数: wxParam 公众号微信名：username 公众号微信别名：alias
				 * 名片标识：certflag=8 resources：个人名片时为微信头像、公众号时为logo
				 * shareTitle：个人名片时为客户名称（优先）或微信昵称、公众号时为公众号名字 shareDes：个人名片时先不设置、公众号时为公众号
				 */
				if ("0".equals(flag)) { // 发送个人名片
					FindPersonMember findPersonMember = new FindPersonMember();
					findPersonMember.setCode(code);
					FindPersonMemberReturn findPersonMemberReturn = personMemberService
							.findPersonMember(findPersonMember);
					FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
					findPersonMemberBase.setMemberNo(findPersonMemberReturn.getMemberNo());
					FindPersonMemberBaseReturn personMemberBaseCard = personMemberBaseService
							.findPersonMemberBase(findPersonMemberBase);
					sendImChatInfo.setResources(personMemberBaseCard.getHeadAddress());
					sendImChatInfo.setShareTitle(personMemberBaseCard.getNickNameWx());
					Map<String, String> contentMap = new HashMap<>();
					contentMap.put("certflag", "0");
					contentMap.put("username", personMemberBaseCard.getNoWx());
					contentMap.put("alias", personMemberBaseCard.getNoWxAlias());
					contentMap.put("memberNo", findPersonMemberReturn.getMemberNo());
					contentMap.put("memberNoGm", findPersonMemberReturn.getMemberNoGm());
					sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
					logger.info("发送微信个人名片：{}", sendImChatInfo);
				} else { // 发公众号名片
					FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
					findWxPublicAccount.setCode(code);
					FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService
							.findWxPublicAccount(findWxPublicAccount);
					sendImChatInfo.setResources(findWxPublicAccountReturn.getPaLogo());
					sendImChatInfo.setShareTitle(findWxPublicAccountReturn.getPaName());
					sendImChatInfo.setShareDes(findWxPublicAccountReturn.getPaDesc());
					Map<String, String> contentMap = new HashMap<>();
					contentMap.put("certflag", findWxPublicAccountReturn.getPaCertflag());
					contentMap.put("username", findWxPublicAccountReturn.getPaUsername());
					contentMap.put("alias", findWxPublicAccountReturn.getPaAlias());
					contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY,
							findWxPublicAccountReturn.getWxParam());
					sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
					logger.info("发送公众号名片：{}", sendImChatInfo);
				}
				break;
			case "47": // 图片表情

				FindImEmoji findImEmoji = new FindImEmoji();
				findImEmoji.setCode(sendImChatInfo.getContent());
				FindImEmojiReturn findImEmojiReturn = imEmojiService.findImEmoji(findImEmoji);
				Map<String, String> map = new HashMap<String, String>();
				map.put("code", findImEmojiReturn.getCode());
				map.put("name", findImEmojiReturn.getEmojiName());

				map.put("url", commonUrl + findImEmojiReturn.getEmojiUrl());
				sendImChatInfo.setContent(JsonUtils.jsonFromObject(map));
				break;
			case "490": // 素材
				if (StringUtils.isEmpty(sendImChatInfo.getResources())) {

					// 获取终端
					FindShopTerminalReturn findShopTerminalReturn = shopTerminalService.findShopTerminalByWx(noWxGm);
					if (findShopTerminalReturn == null) {
						resultMap.put("false", "发送失败：终端不存在！");
						return resultMap;
					}

					// XXX 分享icon地址没有写入到resources字段
					if (StringUtils.isNotEmpty(findShopTerminalReturn.getHeadurl())) {
						sendImChatInfo.setResources(findShopTerminalReturn.getHeadurl());
					} else { // 没有设置头像，取默认头像
						sendImChatInfo.setResources(groupMap.get("guidDHUrl"));
					}
				}
				break;
			case "493": // 导购名片
				// 获取终端
				FindShopTerminalReturn findShopTerminalReturn = shopTerminalService.findShopTerminalByWx(noWxGm);
				if (findShopTerminalReturn == null) {
					resultMap.put("false", "发送失败：终端不存在！");
					return resultMap;
				}

				if (StringUtils.isNotEmpty(findShopTerminalReturn.getHeadurl())) {
					sendImChatInfo.setResources(findShopTerminalReturn.getHeadurl());
				} else { // 没有设置头像，取默认头像
					sendImChatInfo.setResources(groupMap.get("guidDHUrl"));
				}

				sendImChatInfo.setShareTitle(findShopTerminalReturn.getNickname());
				sendImChatInfo.setShareDes("电话：" + findShopTerminalReturn.getAlias());
				sendImChatInfo.setShareUrl(groupMap.get("gmCardUrl") + noWxGm);
				logger.info("发送导购个人名片：{}", sendImChatInfo);
				break;
			case "491": // 优惠券 (群聊暂不支持)

//				//获取终端
				findShopTerminalReturn = shopTerminalService.findShopTerminalByWx(noWxGm);
				if (findShopTerminalReturn == null) {
					resultMap.put("false", "发送失败：终端不存在！");
					return resultMap;
				}

				FindCouponRule findCouponRule = new FindCouponRule();
				findCouponRule.setCode(couponRuleCode);

				FindCouponRuleReturn findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);

				AddCoupon addCoupon = new AddCoupon();
				addCoupon.setMerchantNo(findShopTerminalReturn.getMerchantNo());
				addCoupon.setMerchantName(findShopTerminalReturn.getMerchantName());
				addCoupon.setRuleNo(findCouponRuleReturn.getCode());
				addCoupon.setCouponNo(GUID.generateCode());
				addCoupon.setCouponStatus(CouponStatus.USED.toString());
				addCoupon.setUseDate(new Date());
				addCoupon.setCreateDate(new Date());
				couponService.addCoupon(addCoupon); // 新增优惠券

				// 查询客户基础信息
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setMemberNo(sendImChatInfo.getMemberNo());
				FindPersonMemberBaseReturn personMemberBase = personMemberBaseService
						.findPersonMemberBase(findPersonMemberBase);

				AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
//				addCouponMemberRelation.setMemberNoGm(guidMember.getMemberNo());
//				addCouponMemberRelation.setMemberNameGm(guidMember.getMemberName());
				addCouponMemberRelation.setMemberNo(personMemberBase.getMemberNo());
				addCouponMemberRelation.setMemberName(personMemberBase.getMemberName());
				addCouponMemberRelation.setCouponNo(addCoupon.getCouponNo());
				addCouponMemberRelation.setCouponStatus(addCoupon.getCouponStatus());
				addCouponMemberRelation.setUseDate(new Date());
				addCouponMemberRelation.setCreateDate(new Date());
				couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation); // 新增优惠券用户绑定关系

				String cp_result_url = localCacheSystemParams.getSystemParam(SystemAliasName.cp.name(), "result_url",
						"cp_result_url");
				// 优惠券领取url
				String resultUrl = String.format(cp_result_url, noWxGm, personMemberBase.getMemberNo(),
						addCoupon.getCouponNo());

				sendImChatInfo.setType("491"); // 分享优惠券
				String couponUrl = localCacheSystemParams.getSystemParam(SystemAliasName.api.name(), "logo",
						"couponLogoUrl");
				sendImChatInfo.setResources(couponUrl);
				sendImChatInfo.setShareTitle(findCouponRuleReturn.getCouponName());
				sendImChatInfo.setShareDes("说明:" + findCouponRuleReturn.getCouponRemark());
				sendImChatInfo.setShareUrl(resultUrl);
				logger.info("发送优惠券：{}", sendImChatInfo);
				break;
			case "494": // 小程序
				// 查询小程序信息
				FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
				findWxSmallProgram.setCode(code);
				FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService
						.findWxSmallProgram(findWxSmallProgram);
				sendImChatInfo.setResources(findWxSmallProgramReturn.getSpLogo());
				sendImChatInfo.setShareTitle(findWxSmallProgramReturn.getSpName());
				sendImChatInfo.setShareDes(findWxSmallProgramReturn.getSpDesc());
				sendImChatInfo.setShareUrl(findWxSmallProgramReturn.getSpUrl());
				Map<String, String> contentMap = new HashMap<>();
				contentMap.put(com.lj.business.supcon.common.Constants.WX_PARAM_KEY,
						findWxSmallProgramReturn.getWxParam());
				sendImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
				logger.info("发送小程序：{}", sendImChatInfo);
				break;
			case "48":
				sendImChatInfo.setContent(com.lj.base.core.util.StringUtils.converJson(sendImChatInfo.getContent()));
				break;
			default: // 其他：语音等
				if (StringUtils.isBlank(sendImChatInfo.getResources())) {
					sendImChatInfo.setResources(uploadFile(uploadFile, UserUtils.getMerchantNo()));
				}
				break;
			}
			imChatInfoService.sendChat(sendImChatInfo);
			resultMap.put("true", "发送成功");
			return resultMap;
		} catch (TsfaServiceException e) {
			if (com.lj.business.weixin.constant.ErrorCode.INCLUDE_SENSITIVE_WORDS.equals(e.getExceptionCode())) {
				resultMap.put(com.lj.business.weixin.constant.ErrorCode.INCLUDE_SENSITIVE_WORDS, "发送内容包含敏感字符，发送失败");
			} else if (com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE.equals(e.getExceptionCode())) {
				resultMap.put(com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE, "中控客户端已离线，发送失败！");
			} else {
				resultMap.put("false", e.getExceptionInfo());
			}
			return resultMap;
		} catch (Exception e) {
			logger.error("查询未读聊天记录错误：" + e);
			resultMap.put("false", "发送失败");
			return resultMap;
		}
	}

	private String uploadFile(MultipartFile uploadFile, String merchantNo) throws IOException {
		if (uploadFile == null || uploadFile.isEmpty()) {
			throw new TsfaServiceException(ErrorCode.HEAD_IMAGE_IS_EMPTY, "上传文件为空");
		}

		// 判断文件格式
		String fileName = uploadFile.getOriginalFilename();
		String fileType = FileUtil.getFileType(fileName);
		if (StringUtils.isEmpty(fileType)) {
			logger.error("不支持的文件格式: {}", fileName);
			fileType = "file"; // 默认为file格式
			/*
			 * ResponseCode code = GeneralResponse.getErrorResponseByBusinessCode(ErrorCode.
			 * UNSUPPORTED_FILE_FORMAT); return
			 * GeneralResponse.generateResponse(Boolean.FALSE, code.getCode(),
			 * code.getMessage(), null);
			 */
		}

		Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "imfile");
		String uploadPath = map.get("uploadPath");
		String filePath = merchantNo + "/" + fileType.toLowerCase() + "/";
		String imageFolder = uploadPath + filePath;
		// 保存文件
		String fileInputName = FileUtil.saveFile(imageFolder, uploadFile);
		String url = map.get("uploadUrl") + filePath + fileInputName;
		return url;

//		String UPLOAD_PATH =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_PATH);
//		String pth = FileUtil.saveFile(UPLOAD_PATH + ApiConstans.IMG_PRE , uploadFile);
//		UPLOAD_PATH =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
//		return UPLOAD_PATH + ApiConstans.IMG_PRE + pth;
	}

	/**
	 * 
	 *
	 * 方法说明：更新第三方已读
	 *
	 * @param model
	 * @param updateThirdHaveReadFromWeb
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月5日
	 *
	 */
	@RequestMapping(value = { "updateThirdHaveRead" })
	@ResponseBody
	public Boolean updateThirdHaveRead(Model model, UpdateThirdHaveReadFromWeb updateThirdHaveReadFromWeb) {
		try {
			/**
			 * 增加需求下属微信不标记为已读
			 */
			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setAssistantNo(UserUtils.getUser().getId());
			findGmAssistantShop.setNoWx(updateThirdHaveReadFromWeb.getNoWxGm());
			List<FindGmAssistantShopReturn> list = gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
			if (list != null && list.size() > 0) {
				FindGmAssistantShopReturn findGmAssistantShopReturn = list.get(0);
				if (StringUtils.isEmpty(findGmAssistantShopReturn.getSource())) {
					imChatInfoService.updateThirdHaveReadFromWeb(updateThirdHaveReadFromWeb);
					/**
					 * 推送未读数报文给导购端
					 */
					commonService.sendChatInfoReadNum(UserUtils.getUser().getId(),
							updateThirdHaveReadFromWeb.getMemberNo(), 0);
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("更新第三方已读错误：" + e);
		}
		return false;
	}

	/**
	 * 
	 *
	 * 方法说明：查询客户列表
	 *
	 * @param model
	 * @param findPersonMemberPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月4日
	 *
	 */
	@RequestMapping(value = { "personMemberList" })
	@ResponseBody
	public Map<String, Object> personMemberList(Model model, FindImIndexPage findImIndex, Integer pageNo,
			Integer pageSize) {

		findImIndex.setStartTime(DateUtils.getDateByFirstSeconds(findImIndex.getStartTime()));
		findImIndex.setEndTime(DateUtils.getDateByLastSeconds(findImIndex.getEndTime()));
		String typeCode = findImIndex.getTypeCode();

		// 默认查询第一页
		pageNo = pageNo == null ? 1 : pageNo;

		if (pageNo != null && pageNo > 0 && pageSize != null && pageSize > 0) {
			findImIndex.setStart((pageNo - 1) * pageSize);
			findImIndex.setLimit(pageSize);
		}

		// XXX LEOPENG 性能差，少量数据执行需3S
		List<FindImIndexPageReturn> list = personMemberService.findImIndexList(findImIndex);
		// XXX LEOPENG性能差，少量数据执行需3S
		Long count = personMemberService.findImIndexListCount(findImIndex);

		// XXX LEOPENG 今日新增客户数 性能差，少量数据执行需3S
		findImIndex.setTypeCode(PmTypeTimeFlag.TODAY.toString());// 查询今日数据
		findImIndex.setQueryChatRoom("false"); // 今日客户数，只查询客户，不包含群
		Long todayCount = personMemberService.findImIndexListCount(findImIndex);

		FindShopGmDto findShopGmDto = new FindShopGmDto();
		findShopGmDto.setNoWx(findImIndex.getNoWx());
		findShopGmDto.setMerchantNo(UserUtils.getMerchantNo());
		List<FindGuidMemberPageReturn> guidMembers = guidMemberService.findGuidMembersByShopWx(findShopGmDto);

		JsonPage<FindImIndexPageReturn> page = new JsonPage<FindImIndexPageReturn>(pageNo == null ? 1 : pageNo,
				pageSize, count, list);
		page.initialize();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", page);
		resultMap.put("guidMembers", guidMembers);
		resultMap.put("todayCount", todayCount);

		// TODO 是否展示朋友圈提醒
		/*
		 * if(list!=null && list.size()>0) { Map<String, Object> tipMap =
		 * Maps.newHashMap(); //TODO UPDATE BY LEOPENG
		 * 加了查询索引，目前速度没问题，建议修改成单次查询再统一获取数据的模式。 for (FindImIndexPageReturn dto : list) {
		 * if(!dto.isChatRoomFlag()) { boolean tip =
		 * imFriendsInfoService.findFriendPointByMemberNo(dto.getNoWxGm(),dto.
		 * getMemberNo()); tipMap.put(dto.getMemberNo(), tip); } }
		 * resultMap.put("friendPoint", tipMap); }
		 */

		// 只取客户数
		findImIndex.setStart(0);
		findImIndex.setLimit(Integer.MAX_VALUE);
		findImIndex.setTypeCode(typeCode);
		Long kehuNum = personMemberService.findImIndexListCount(findImIndex);
		resultMap.put("chatRoomNum", count - kehuNum);
		resultMap.put("kehuNum", kehuNum);
		return resultMap;
	}

	/**
	 * 判断字符串是否为枚举(PmTypeTimeFlag)中的值
	 * 
	 * @param typeCode
	 */
	/*
	 * private boolean inEnum(String typeCode) { PmTypeTimeFlag[] values =
	 * PmTypeTimeFlag.values(); for (PmTypeTimeFlag pmTypeTimeFlag : values) {
	 * if(typeCode.equals(pmTypeTimeFlag.toString())){ return true; } } return
	 * false; }
	 */

	/**
	 * 
	 *
	 * 方法说明：查询客户信息
	 *
	 * @param model
	 * @param findPersonMember
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月6日
	 *
	 */
	@RequestMapping(value = { "findPersonMember" })
	@ResponseBody
	public FindPersonMemberPageReturn findPersonMember(Model model, FindPersonMember findPersonMember) {
		try {
			Map<String, String> map = new HashMap<>();
			map.put("code", findPersonMember.getCode());
			map.put("pmTypeCode", findPersonMember.getPmTypeCode());
			FindPersonMemberPageReturn personMemberPageReturn = personMemberService.getByCond(map);

			FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
			findGuidMemberDto.setMemberNo(UserUtils.getUser().getId());
			GuidMemberReturnDto findGuid = guidMemberService.findGuid(findGuidMemberDto);
			personMemberPageReturn.setMobileGm(findGuid.getMobile());
			personMemberPageReturn.setMemberNameGm(findGuid.getMemberName());

			// 设置地区
			String province = personMemberPageReturn.getProvinceCode() == null ? ""
					: UserUtils.getAreaName(personMemberPageReturn.getProvinceCode());
			String city = personMemberPageReturn.getCityCode() == null ? ""
					: UserUtils.getAreaName(personMemberPageReturn.getCityCode());
			personMemberPageReturn.setAddress(province + city);
			// 朋友圈提醒周期
			FriendPointCycle friendPointCycle = friendPointCycleService
					.selectByMemberNo(personMemberPageReturn.getMemberNo());
			if (friendPointCycle != null) {
				personMemberPageReturn.setRemark4(String.valueOf(friendPointCycle.getCycle()));
			} else {
				personMemberPageReturn.setRemark4(getPointCycleValue(null));
			}

			return personMemberPageReturn;
		} catch (Exception e) {
			logger.error("查询客户信息错误：", e);
		}
		return null;
	}

	/**
	 * 
	 *
	 * 方法说明：更新客户为意向未到店
	 *
	 * @param code       客户表code
	 * @param memberNoGm 导购编号
	 * @param memberNo   客户编号
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年03月21日
	 *
	 */
	@RequestMapping(value = { "updateIntentionN" })
	@ResponseBody
	public Map<String, Object> updateIntentionN(String memberNoGm, String memberNo, String merchantNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 客户分组change

			/*
			 * FindPmType findPmType = new FindPmType();
			 * findPmType.setMerchantNo(merchantNo);
			 * findPmType.setPmTypeType(PmTypeType.INTENTION.name()); // 改为意向到店
			 * FindPmTypeReturn pt = pmTypeService.findPmTypeByMP(findPmType);
			 * 
			 * ChangePmTypeUngroup changePmTypeUngroup=new ChangePmTypeUngroup();
			 * changePmTypeUngroup.setPmTypeCode(pt.getCode());
			 * changePmTypeUngroup.setMemberNo(memberNo);
			 * changePmTypeUngroup.setMemberNoGm(memberNoGm);
			 * changePmTypeUngroup.setMerchantNo(merchantNo);
			 * 
			 * pmTypeService.changePmType_app_ungroup(changePmTypeUngroup);
			 * map.put("result", "true"); map.put("msg", pt.getCode());
			 */
			return map;

		} catch (TsfaServiceException e) {
			logger.error("更新客户信息失败", e);
			map.put("result", "false");
			map.put("msg", e.getExceptionInfo());
			return map;
		} catch (Exception e) {
			logger.error("更新客户信息失败", e);
			map.put("result", "false");
			map.put("msg", "更新客户信息失败");
			return map;
		}
	}

	/**
	 * 
	 *
	 * 方法说明：朋友圈查询客户详情
	 *
	 * @param model
	 * @param findPersonMember
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月26日
	 *
	 */
	@RequestMapping(value = { "findFriendsPersonMember" })
	@ResponseBody
	public FindPersonMemberPageReturn findFriendsPersonMember(Model model, FindPersonMember findPersonMember) {
		FindPersonMemberPageReturn pm = new FindPersonMemberPageReturn();
		try {
			FindPmInfoAll findPmInfoAll = new FindPmInfoAll();
			findPmInfoAll.setMemberNo(findPersonMember.getMemberNo());
			findPmInfoAll.setMemberNoGm(findPersonMember.getMemberNoGm());
			FindPmInfoAllReturn pmInfo = personMemberService.findPmInfoAll(findPmInfoAll);

			// 查询客户分类
			FindPersonMemberReturnList returnList = personMemberService.queryPersonMemberPmType(findPersonMember);
			if (returnList != null) {
				findPersonMember.setPmTypeCode(returnList.getCode());
			}

			findPersonMember.setCode(pmInfo.getCodePm());
			pm = findPersonMember(model, findPersonMember);
			return pm;
		} catch (TsfaServiceException ex) {
			logger.error("查询客户信息错误：", ex.getExceptionInfo());
		} catch (Exception e) {
			logger.error("查询客户信息错误：", e);
		}
		return pm;
	}

	/**
	 * 
	 *
	 * 方法说明：获取导购电话
	 *
	 * @param model
	 * @param findGuidMemberDto
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月11日
	 *
	 */
	@RequestMapping(value = { "getGmMobile" })
	@ResponseBody
	public String getGmMobile(Model model, FindGuidMemberDto findGuidMemberDto) {
		GuidMemberReturnDto guid = guidMemberService.findGuid(findGuidMemberDto);
		return guid.getMobile();
	}

	/**
	 * 
	 *
	 * 方法说明：获取导购个人微信号
	 *
	 * @param findGuidMemberDto
	 * @return noWxPersonal
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月16日
	 *
	 */
	@RequestMapping(value = { "getGmNoWxPersonal" })
	@ResponseBody
	public Map<String, String> getGmNoWxPersonal(FindGuidMemberDto findGuidMemberDto) {
		GuidMemberReturnDto guid = guidMemberService.findGuid(findGuidMemberDto);
		Map<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isBlank(guid.getNoWxPersonal())) {
			resultMap.put("state", "false");
			resultMap.put("msg", "导购个人微信号为空");
			return resultMap;
		}
		// 获取添加了导购私人微信对应的客户信息
		FindPersonMemberByShopAndNoWx find = new FindPersonMemberByShopAndNoWx();
//		 find.setShopNo(guid.getShopNo());
		find.setNoWx(guid.getNoWxPersonal());
		FindPersonMemberByShopAndNoWxReturn personMemberByGm = personMemberImService
				.findPersonMemberByShopWxAndNoWx(find);
		if (personMemberByGm == null) {
			resultMap.put("state", "false");
			resultMap.put("msg", "导购所属终端没有添加导购个人微信为好友");
		} else {
			resultMap.put("state", "true");
			resultMap.put("msg", guid.getNoWxPersonal());
		}
		return resultMap;
	}

	/**
	 * 
	 *
	 * 方法说明：转换音频格式amr===>>mp3
	 *
	 * @param model
	 * @param resource
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月11日
	 * @throws IOException
	 *
	 */
	@RequestMapping(value = { "audioConvert" })
	@ResponseBody
	public String audioConvert(Model model, String resource) throws IOException {

		try {
			// IM文件存储路径前缀
			String UPLOAD_IM_PATH = localCacheSystemParams.getSystemParam(SystemAliasName.weixin.toString(),
					SystemParamConstant.IM_UPLOAD_GROUP, SystemParamConstant.UPLOAD_PATH);
			String UPLOAD_IM_URL = localCacheSystemParams.getSystemParam(SystemAliasName.weixin.toString(),
					SystemParamConstant.IM_UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);

			// 源文件
			resource = resource.replace(UPLOAD_IM_URL, "/");
			File sourcefile = new File(UPLOAD_IM_PATH + resource);

			// 如果源文件不存在
			if (!sourcefile.exists()) {
				logger.error("源文件不存在：" + UPLOAD_IM_PATH + resource);
				return null;
			}

			String mp3 = AmrToMp3Util.encoder(sourcefile);
			String parentPath = resource.substring(0, resource.lastIndexOf("/"));
			String returnPath = UPLOAD_IM_URL + parentPath + "/" + mp3;
			return returnPath;
		} catch (Exception e) {
			logger.error("文件地址错误：" + resource, e);
		} finally {

		}
		return null;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath String 原文件路径 如：c:/fqf.txt
	 * @param newPath String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
//						int length; 
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}

	public static Date getDayBeginTime(Date date) throws Exception {
		String formatDate = DateUtils.formatDate(date, "yyyy-MM-dd");
		return DateUtils.parseDate(formatDate, "yyyy-MM-dd");
	}

	/**
	 * 
	 *
	 * 方法说明：更新客户信息
	 *
	 * @param memberNoGm 导购编号
	 * @param memberNo   客户编号
	 * @param memberName 客户名称(微信本地备注 刘萱瑶12-14 11：00确定)
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月12日
	 *
	 */
	@RequestMapping(value = { "updatePersonMember" })
	@ResponseBody
	public String updatePersonMember(String noWxGm, String memberNo, String memberName) {
		EditPersonMember editPersonMember = new EditPersonMember();
		editPersonMember.setMemberNo(memberNo);
//		editPersonMember.setMemberNoGm(memberNoGm);
		editPersonMember.setNoWxGm(noWxGm);
		editPersonMember.setMemberName(memberName);
		try {
			// HTML特殊字符转义
			String content = StringEscapeUtils.unescapeHtml4(editPersonMember.getMemberName()).toString();
			editPersonMember.setMemberName(content);
			personMemberService.updatePersonMemberName(editPersonMember);
		} catch (TsfaServiceException e) {
			logger.error("更新客户信息失败", e);
			return e.getExceptionInfo();
		} catch (Exception e) {
			logger.error("更新客户信息失败", e);
			return "更新客户信息失败";
		}
		return "success";
	}

	/**
	 * 
	 *
	 * 方法说明：朋友圈点赞
	 *
	 * @param model
	 * @param toLikeDto
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月22日
	 *
	 */
	@RequestMapping(value = { "toImLike" })
	@ResponseBody
	public Map<String, String> toImLike(Model model, ToFriendsLikeDto toLikeDto, Integer cycle) {
		Map<String, String> map = new HashMap<>();
		try {
			friendsFacade.toImLike(toLikeDto);
			/*
			 * if(toLikeDto.getMemberNo()!=null) { if(cycle==null) { cycle =
			 * Integer.valueOf(getPointCycleValue(toLikeDto.getMemberNo()));//查询用户周期,
			 * 没有则使用默认提醒周期 } FriendPointCycle friendPointCycle = new FriendPointCycle();
			 * friendPointCycle.setMemberNo(toLikeDto.getMemberNo());
			 * friendPointCycle.setCycle(cycle); String createId =
			 * UserUtils.getUser().getId(); friendPointCycle.setCreateId(createId);//指定创建者
			 * friendPointCycleService.updateByMemberNo(friendPointCycle); }
			 */
			map.put("state", "true");
		} catch (TsfaServiceException e) {
			logger.error("点赞朋友圈错误", e);
			map.put("msg", e.getExceptionInfo());
		} catch (Exception e) {
			logger.error("点赞朋友圈错误", e);
			map.put("state", "false");
		}
		return map;
	}

	/**
	 * 
	 *
	 * 方法说明：朋友圈评论
	 *
	 * @param model
	 * @param toFriendsCommentDto
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月22日
	 *
	 */
	@RequestMapping(value = { "toComment" })
	@ResponseBody
	public Map<String, String> toComment(Model model, ToFriendsCommentDto toFriendsCommentDto, Integer cycle) {
		Map<String, String> map = new HashMap<>();
		try {
			// 转义特殊HTML字符
			String toConent = StringEscapeUtils.unescapeHtml4(toFriendsCommentDto.getToConent()).toString();
			toFriendsCommentDto.setToConent(toConent);
			friendsFacade.toComment(toFriendsCommentDto);
			/*
			 * if(toFriendsCommentDto.getMemberNo()!=null) { if(cycle==null) { cycle =
			 * Integer.valueOf(getPointCycleValue(toFriendsCommentDto.getMemberNo()));//
			 * 查询用户周期,没有则使用默认提醒周期 } FriendPointCycle friendPointCycle = new
			 * FriendPointCycle();
			 * friendPointCycle.setMemberNo(toFriendsCommentDto.getMemberNo());
			 * friendPointCycle.setCycle(cycle); String createId =
			 * UserUtils.getUser().getId(); friendPointCycle.setCreateId(createId);//指定创建者
			 * friendPointCycleService.updateByMemberNo(friendPointCycle); }
			 */
			map.put("state", "true");
		} catch (Exception e) {
			logger.error("评论朋友圈错误", e);
			map.put("state", "false");
		}
		return map;
	}

	/**
	 * 
	 *
	 * 方法说明：发送朋友圈
	 *
	 * @param model
	 * @param toFriendsInfosDto
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月22日
	 *
	 */
	@RequestMapping(value = { "toFriendsInfo" })
	@ResponseBody
	public Map<String, String> toFriendsInfo(Model model, ToFriendsInfosDto toFriendsInfosDto) {
		Map<String, String> map = new HashMap<>();
		try {
			friendsFacade.toFriendsInfo(toFriendsInfosDto);
			map.put("state", "true");
		} catch (Exception e) {
			logger.error("评论朋友圈错误", e);
			map.put("state", "false");
		}
		return map;
	}

	/**
	 * 
	 *
	 * 方法说明：获取朋友圈信息
	 *
	 * @param model
	 * @param findImFriendsInfoPage
	 * @param pageNo
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月22日
	 *
	 */
	@RequestMapping(value = { "getfriendData" })
	@ResponseBody
	public Map<String, Object> getfriendData(Model model, FindImFriendsInfoPage findImFriendsInfoPage, Integer pageNo,
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
			ArrayList<ImFriendsInfoDto> basePageReturns = Lists.newArrayList();
			if (infos.getRows() == null || infos.getRows().size() == 0) {
				logger.info(" rows is  null  --- >{}", infos);
				map.put("success", true);
				map.put("friendForumPage",
						new Page<ImFriendsInfoDto>(new ArrayList<ImFriendsInfoDto>(), 0, findImFriendsInfoPage));
			} else {
				basePageReturns.addAll(infos.getRows());
				com.ape.common.persistence.Page<ImFriendsInfoDto> pages = new com.ape.common.persistence.Page<ImFriendsInfoDto>(
						pageNo == null ? 1 : pageNo, infos.getLimit(), infos.getTotal(), basePageReturns);
				pages.initialize();
				map.put("success", true);
				map.put("friendForumPage", pages);// 成功返回
			}
			// map.put("likeInfo", likeInfoMap);
			map.put("pageNo", pageNo);
		} catch (Exception e) {
			map.put("success", false);
			map.put("friendForumPage", "获取导购绑定的朋友圈信息异常.");
			logger.error("获取朋友圈信息错误", e);
		}
		logger.info("map ---- >", map);
		return map;
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
			ArrayList<ImFriendsInfoDto> basePageReturns = Lists.newArrayList();
			if (infos.getRows() == null || infos.getRows().size() == 0) {
				logger.info(" rows is  null  --- >{}", infos);
				map.put("success", true);
				map.put("friendForumPage",
						new Page<ImFriendsInfoDto>(new ArrayList<ImFriendsInfoDto>(), 0, findImFriendsInfoPage));
			} else {
				basePageReturns.addAll(infos.getRows());
				com.ape.common.persistence.Page<ImFriendsInfoDto> pages = new com.ape.common.persistence.Page<ImFriendsInfoDto>(
						pageNo == null ? 1 : pageNo, infos.getLimit(), infos.getTotal(), basePageReturns);
				pages.initialize();
				map.put("success", true);
				map.put("friendForumPage", pages);// 成功返回
			}
			// map.put("likeInfo", likeInfoMap);
			map.put("pageNo", pageNo);
		} catch (Exception e) {
			map.put("success", false);
			map.put("friendForumPage", "获取导购绑定的朋友圈信息异常.");
			logger.error("获取朋友圈信息错误", e);
		}
		logger.info("map ---- >", map);
		return map;
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
	public Map<String, Object> loadToReplyFriendsCount(FindImFriendsInfoPage findImFriendsInfoPage) {
		Map<String, Object> map = new HashMap<String, Object>(3);
		try {
			Integer count = imFriendsInfoService.findImFriendsInfoToReplyCount(findImFriendsInfoPage);
			map.put("success", true);
			map.put("count", count);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "获取朋友圈待回复数量错误");
			logger.error("获取朋友圈待回复数量错误", e);
		}
		logger.info("map ---- >", map);
		return map;
	}

	/**
	 *
	 * 方法说明：进入单个朋友圈获取个人信息
	 * 
	 * @param model
	 * @param findImFriendsInfoPage
	 * @param pageNo
	 * @return
	 * @author 李端强 CreateDate: 2018年1月26日14:39:56
	 */
	@RequestMapping(value = { "toSignalFriendData" })
	public String toSignalFriendData(Model model, String memberNo, String type, String noWxShop) {
		try {
			String merchantNo = UserUtils.getMerchantNo();
			Map<String, String> param = new HashMap<String, String>();
			param.put("memberNo", memberNo);
			param.put("merchantNo", merchantNo);
			param.put("shopWx", noWxShop);
			FindPersonMemberPageReturn memberPageReturn = personMemberService.getByCond(param);

			// 个人资料
			model.addAttribute("personMember", memberPageReturn);

			Map<String, Object> map = new HashMap<>();
			map.put("pageNo", 1);
			model.addAttribute("messagePush", map);

			boolean isCf = true;
			// VR素材类型
			FindVrMaterialType findVrMaterialType = new FindVrMaterialType();
			findVrMaterialType.setMerchantNo(merchantNo);
			List<FindVrMaterialTypeApiReturn> typeApiReturns = vrMaterialTypeService
					.findVrMaterialTypeReturnList(findVrMaterialType);
			model.addAttribute("vrMaterialType", typeApiReturns);

			// 是否跟进记录
			model.addAttribute("isCf", isCf);
			// 客户来源枚举
			model.addAttribute("memerSources", MemerSourceType.values());
			// 性别
			model.addAttribute("genders", Gender.values());
			// 朋友圈提醒周期
			model.addAttribute("cycle", getPointCycleValue(memberNo));
			// 查询终端
			FindShopTerminalReturn findShopTerminalReturn = shopTerminalService.findShopTerminalByWx(noWxShop);
			model.addAttribute("shopTerminal", findShopTerminalReturn);

		} catch (Exception e) {
			logger.error("查询客户聊天错误：" + e);
		}
		return "modules/im/signalFriend";
	}

	/**
	 * 方法说明：获取朋友圈提醒周期,商户配置默认值
	 * 
	 * @return
	 * @author 李端强 CreateDate: 2018年1月31日
	 */
	@RequestMapping(value = { "getPointCycleValue" })
	@ResponseBody
	public String getPointCycleValue(String memberNo) {
		// 朋友圈提醒周期
		if (!StringUtils.isEmpty(memberNo)) {
			FriendPointCycle friendPointCycle = friendPointCycleService.selectByMemberNo(memberNo);
			if (friendPointCycle != null) {
				return String.valueOf(friendPointCycle.getCycle());// 返回用户的详细配置周期
			}
		}
		String value = AddQrCodeUtils.getMerchantParamsValue(UserUtils.getMerchantNo(), merchantParamsService,
				"friends", "friends_point_cycle");
		if (StringUtils.isEmpty(value)) {
			value = DEFAULT_FRIENDS_POINT_CYCLE;// 默认朋友圈提醒为30天
		}
		return value;
	}

	/**
	 * 修改客户的朋友圈提示周期
	 * 
	 * @param memberNo
	 * @param cycle
	 * @return
	 */
	@RequestMapping(value = { "updateFriendPointCycle" })
	@ResponseBody
	public boolean updateFriendPointCycle(FriendPointCycle friendPointCycle) {
		logger.debug("updateFriendPointCycle(FriendPointCycle) - start");
		boolean ret = false;
		try {
			String createId = UserUtils.getUser().getId();
			friendPointCycle.setCreateId(createId);// 指定创建者
			friendPointCycleService.updateByMemberNo(friendPointCycle);
			ret = true;
		} catch (Exception e) {
			logger.error("updateFriendPointCycle(FriendPointCycle)", e);
			return ret;
		}
		logger.debug("updateFriendPointCycle(FriendPointCycle) - end");
		return ret;
	}

	/**
	 * 
	 *
	 * 方法说明：获取导购助手微信信息
	 *
	 * @param model
	 * @param findShopTidFromWeb
	 * @return
	 *
	 * @author 梅宏博 CreateDate: 2017年12月23日
	 *
	 */
	@RequestMapping(value = { "getShopWx" })
	@ResponseBody
	public List<FindShopTidFromWebReturn> getShopWx(Model model, FindShopTidFromWeb findShopTidFromWeb) {
		String assistantNo = UserUtils.getUser().getId();// 导购助手编号
		// 导购助手管理的终端列表查询
		findShopTidFromWeb.setAssistantNo(assistantNo);
		return shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
	}

	/**
	 * 
	 *
	 * 方法说明：新增链接素材
	 *
	 * @param addFriendsLinkMaterial
	 * @return boolean
	 *
	 * @author 罗书明 CreateDate: 2018年1月9日
	 *
	 */
	@RequestMapping(value = "addFriendsLinkMaterial")
	public String addFriendsLinkMaterial(Model model, AddFriendsLinkMaterial addFriendsLinkMaterial) {
		try {
			addFriendsLinkMaterial
					.setTitle(StringEscapeUtils.unescapeHtml4(addFriendsLinkMaterial.getTitle()).toString());
			addFriendsLinkMaterial
					.setShareTitle(StringEscapeUtils.unescapeHtml4(addFriendsLinkMaterial.getShareTitle()).toString());
			addFriendsLinkMaterial
					.setContent(StringEscapeUtils.unescapeHtml4(addFriendsLinkMaterial.getContent()).toString());
			if (addFriendsLinkMaterial.getAutoComment() == 1) {
				addFriendsLinkMaterial.setCommentContent(
						StringEscapeUtils.unescapeHtml4(addFriendsLinkMaterial.getCommentContent()).toString());
			}
			logger.debug("新增链接素材，转换后的内容：{}" + addFriendsLinkMaterial);

			addFriendsLinkMaterial.setMerchantNo(UserUtils.getMerchantNo());
			addFriendsLinkMaterial.setDeleteFlag(0);
			friendsLinkMaterialService.addFriendsLinkMaterial(addFriendsLinkMaterial);
		} catch (Exception e) {
			logger.error("新增链接素材错误！", e);
		}
		return "redirect:" + Global.getAdminPath() + "/im/findFriendsLinkMaterialList";
	}

	@RequestMapping(value = "friendsLinkMaterialForm")
	public String friendsLinkMaterialForm(Model model, String code) {
		if (StringUtils.isNotEmpty(code)) {
			FindFriendsLinkMaterial findFriendsLinkMaterial = new FindFriendsLinkMaterial();
			findFriendsLinkMaterial.setCode(code);
			FindFriendsLinkMaterialReturn findFriendsLinkMaterialReturn = friendsLinkMaterialService
					.findFriendsLinkMaterial(findFriendsLinkMaterial);
			model.addAttribute("data", findFriendsLinkMaterialReturn);
		}
		String merchantNo = UserUtils.getUser().getCompany().getId();// 商户编号
		model.addAttribute("merchantNo", merchantNo);
		return "modules/im/friendslinkmateriaViewH5";
	}

	/**
	 * 
	 *
	 * 方法说明：逻辑删除链接素材
	 *
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月10日
	 *
	 */
	@RequestMapping(value = "delectMaterial")
	@ResponseBody
	public Map<String, Object> delectMaterial(UpdateFriendsLinkMaterial updateFriendsLinkMaterial) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			if (StringUtils.isBlank(updateFriendsLinkMaterial.getCode())) {
				map.put("success", false);
				map.put("msg", "CODE不能为空！");
			}
			friendsLinkMaterialService.updateFriendsLinkMaterial(updateFriendsLinkMaterial);
			map.put("success", true);
			map.put("msg", "删除链接素材成功！");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除链接素材失败,系统出现异常！");
			logger.error("删除链接素材失败！", e);
		}
		return map;
	}

	/**
	 * 
	 *
	 * 方法说明：修改链接素材
	 *
	 * @param model
	 * @param updateFriendsLinkMaterial
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月10日
	 *
	 */
	@RequestMapping(value = "updataMaterial")
	public String updataMaterial(Model model, UpdateFriendsLinkMaterial updateFriendsLinkMaterial) {

		try {
			if (updateFriendsLinkMaterial.getCode() != null) {
				updateFriendsLinkMaterial
						.setTitle(StringEscapeUtils.unescapeHtml4(updateFriendsLinkMaterial.getTitle()).toString());
				updateFriendsLinkMaterial.setShareTitle(
						StringEscapeUtils.unescapeHtml4(updateFriendsLinkMaterial.getShareTitle()).toString());
				updateFriendsLinkMaterial
						.setContent(StringEscapeUtils.unescapeHtml4(updateFriendsLinkMaterial.getContent()).toString());
				if (updateFriendsLinkMaterial.getAutoComment() == 1) {
					updateFriendsLinkMaterial.setCommentContent(
							StringEscapeUtils.unescapeHtml4(updateFriendsLinkMaterial.getCommentContent()).toString());
				}
				logger.debug("修改链接素材，转换后的内容：{}" + updateFriendsLinkMaterial);

				friendsLinkMaterialService.updateFriendsLinkMaterial(updateFriendsLinkMaterial);
				model.addAttribute("data", updateFriendsLinkMaterial);
			}
		} catch (Exception e) {
			logger.error("修改链接素材");
		}
		return "redirect:" + Global.getAdminPath() + "/im/findFriendsLinkMaterialList";

	}

	/**
	 * 
	 *
	 * 方法说明：列表返回地址
	 *
	 * @return Url
	 *
	 * @author 罗书明 CreateDate: 2018年1月10日
	 *
	 */
	@RequestMapping(value = "findFriendsLinkMaterialList")
	public String findFriendsLinkMaterialList() {
		return "modules/im/friendslinkmateriaListH5";
	}

	/**
	 * 
	 *
	 * 方法说明：链接素材分页查询
	 *
	 * @return page
	 *
	 * @author 罗书明 CreateDate: 2018年1月9日
	 *
	 */
	@RequestMapping(value = "findFriendsLinkMaterialPage")
	@ResponseBody
	public Page<FindFriendsLinkMaterialPageReturn> findFriendsLinkMaterialPage(Model model, Integer pageNo,
			Integer pageSize, FindFriendsLinkMaterialPage findFriendsLinkMaterialPage) {
		Page<FindFriendsLinkMaterialPageReturn> page = null;
		try {
			if (pageNo != null) {
				findFriendsLinkMaterialPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findFriendsLinkMaterialPage.setLimit(pageSize);
			}
			findFriendsLinkMaterialPage.setMerchantNo(UserUtils.getMerchantNo());
			page = friendsLinkMaterialService.findFriendsLinkMaterialPage(findFriendsLinkMaterialPage);
		} catch (Exception e) {
			logger.error("分页查询链接素材错误！", e);
		}
		return page;
	}

	/**
	 * 
	 *
	 * 方法说明：话术选择
	 *
	 * @author 彭俊霖
	 * 
	 *         CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	@RequestMapping(value = "wordsSelect")
	@ResponseBody
	public List<FindWordsInfoAppReturn> wordsSelect(FindWordsInfoApp findWordsInfoApp) {
		List<FindWordsInfoAppReturn> wordsSelect = Lists.newArrayList();
		try {
			if (findWordsInfoApp != null) {
				findWordsInfoApp.setMerchantNo(UserUtils.getMerchantNo());
				wordsSelect = wordsInfoService.wordsSelect(findWordsInfoApp);
			}
		} catch (Exception e) {
			logger.error("获取话术信息错误！", e);
		}
		return wordsSelect;
	}

	/**
	 * 
	 *
	 * 方法说明：话术信息-更多
	 *
	 * @author 彭俊霖
	 * 
	 *         CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoWeb
	 * @return
	 */
	@RequestMapping(value = "moreWords")
	@ResponseBody
	public FindWordsInfoWebReturn moreWords(FindWordsInfoWeb findWordsInfoWeb) {
		FindWordsInfoWebReturn moreWords = new FindWordsInfoWebReturn();
		try {
			if (findWordsInfoWeb != null) {
				findWordsInfoWeb.setMerchantNo(UserUtils.getMerchantNo());
				List<FindWordsInfoReturn> infoList = wordsInfoService.moreWords(findWordsInfoWeb);
				List<FindWordsTypeSelectReturn> typeList = wordsTypeService.findWordsTypes(findWordsInfoWeb);
				moreWords.setInfoList(infoList);
				moreWords.setTypeList(typeList);
			}
		} catch (Exception e) {
			logger.error("获取话术信息-更多错误！", e);
		}
		return moreWords;
	}

	/**
	 * 
	 *
	 * 方法说明：查询下级省市区
	 *
	 * @param parentId
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月9日
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/selectAreaByParentId" })
	public List<Area> selectAreaByParentId(String parentId) {
		// 为空，则查询中国下所有省
		if (StringUtils.isEmpty(parentId)) {
			parentId = "1";
		}
		return areaService.selectAreaByParentId(parentId);
	}

	/**
	 *
	 * 方法说明：跳转添加好友页面 search
	 */
	@RequestMapping(value = "searchFriend")
	public String addFriendsType(Model model, String merchantNo) {
		if (com.lj.base.core.util.StringUtils.isEmpty(merchantNo)) {
			merchantNo = UserUtils.getMerchantNo();// 商户编号
		}
		Set<String> filterSet = findShopWxInfo(merchantNo);
		model.addAttribute("shopWxs", filterSet);
		User user = UserUtils.getUser();
		String userId = user.getId();
		String companyId = UserUtils.getMerchantNo();
		model.addAttribute("assistantNo", userId);
		model.addAttribute("merchantNo", companyId);
		model.addAttribute("merchantName", user.getCompany().getName());
		return "modules/im/searchFriend";
	}

	/**
	 *
	 * 方法说明：搜索微信基本信息
	 * 
	 * @param merchentNo
	 * @param merchentWxNo
	 * @param qrCode
	 * @return
	 * @author 李端强 CreateDate: 2018年1月11日
	 *
	 */
	@RequestMapping(value = "doSearchFriend")
	@ResponseBody
	public Map<String, Object> doSeachFriend(String noWxGm, String qrCode, int count) {
		String merchantNo = UserUtils.getMerchantNo();// 商户编号
		logger.debug("doSeachFriend(String merchentNo={}, String noWxGm={}, String qrCode={}) - start", merchantNo,
				noWxGm, qrCode);

		String addFriendKey = noWxGm + qrCode;// 终端微信号+QrCode
		Map<String, Object> ret = Maps.newHashMap();
		String searchRet = redisCache.hget(addFriendKey, KeyConstant.OMS_SEARCH_ADD_RET_PREFIX);// 搜索结果
		FindWxInfoReturnDto dto = null;
		Map<String, Object> msgMap = Maps.newHashMap();
		try {
			if (searchRet != null) {
				dto = (FindWxInfoReturnDto) JsonUtils.objectFromJson(searchRet, FindWxInfoReturnDto.class);// 搜索到结果
				ret.put("success", true);
			} else {
				String hasSearch = redisCache.hget(addFriendKey, KeyConstant.OMS_SEARCH_ADD_PREFIX);
				if (hasSearch == null) { // 首次搜索
					msgMap = privateSearch(merchantNo, noWxGm, qrCode);
					FindPersonMemberBaseReturn findDto = (FindPersonMemberBaseReturn) msgMap.get("data");// 好友已添加,无需再次发送搜索,直接返回DB中数据
					if (findDto != null) {
						dto = new FindWxInfoReturnDto();
						dto.setNickNameWx(findDto.getNickNameWx());
						dto.setHeadAddress(findDto.getHeadAddress());
					}
				} else {// 搜索进行中
					if (count > 3) {// 长时间未返回允许客户再次调用搜索中控
						logger.info(
								"doSeachFriend(String merchentNo={}, String merchentWxNo={}, String qrCode={}) - 允许商户再次调用中控进行搜索!",
								merchantNo, noWxGm, qrCode);
						redisCache.hdel(addFriendKey, KeyConstant.OMS_SEARCH_ADD_PREFIX);
						msgMap = privateSearch(merchantNo, noWxGm, qrCode);
					}
				}
				ret.put("success", true);
			}
		} catch (TsfaServiceException e) {
			logger.error("doSeachFriend(String, String, String)", e);
			ret.put("success", false);
		}
		FindWxInfoReturnDto finalDto = new FindWxInfoReturnDto();
		if (dto != null && dto.getScanId() != null) {// 成功搜索完成
			if (StringUtils.isEmpty(dto.getNoWx()) && StringUtils.isEmpty(dto.getNickNameWx())) {
				ret.put("data", "");// 搜索微信信息不存在
			} else {
				BeanUtils.copyProperties(dto, finalDto);
				redisCache.hdel(addFriendKey, KeyConstant.OMS_SEARCH_ADD_PREFIX);
				redisCache.hdel(addFriendKey, KeyConstant.OMS_SEARCH_ADD_RET_PREFIX);
				ret.put("data", finalDto);
			}
		} else {
			ret.put("data", msgMap.get("data"));// 放置已成为好友的信息
		}
		ret.put("msg", msgMap.get("msg"));// 不能搜索好友的原因
		logger.debug("doSeachFriend(String, String, String) - end - return value={}", ret);
		return ret;
	}

	/**
	 *
	 * 方法说明：向中控发送搜索请求
	 * 
	 * @param merchentNo
	 * @param merchentWxNo
	 * @param qrCode
	 * @author 李端强 CreateDate: 2018年1月11日
	 */
	private Map<String, Object> privateSearch(String merchantNo, String noWxGm, String qrCode) {
		FindWxInfoRequestDto findWxInfoRequestDto = new FindWxInfoRequestDto();
		findWxInfoRequestDto.setMemberNoGm("");// 导购编号,可以不传
		findWxInfoRequestDto.setWxQrCode(qrCode);// 搜索条件
		findWxInfoRequestDto.setNoWxGM(noWxGm);// 中控端微信号

		IContactsService basic = commonService.getHessianContactsService(noWxGm);
		// 通过校验字符串判断搜索号码的类型
		Map<String, Object> msgMap = basic.sendAddNewFriendMessage(findWxInfoRequestDto,
				KeyConstant.OMS_SEARCH_ADD_PREFIX);
		logger.info("doSeachFriend(String merchentNo={}, String merchentWxNo={}, String qrCode={}) - 进行首次搜索!",
				merchantNo, noWxGm, qrCode);
		return msgMap;
	}

	/**
	 * 方法说明：查询商户下的中控微信号
	 * 
	 * @param currentMerchentNo
	 * @return
	 * @author 李端强 CreateDate: 2018年1月11日
	 */
	private Set<String> findShopWxInfo(String currentMerchentNo) {
		logger.debug("findShopWxInfo(String currentMerchentNo={}) - start", currentMerchentNo);
		FindShopTerminal findShopTerminal = new FindShopTerminal();
		findShopTerminal.setMerchantNo(currentMerchentNo);// 指定商户编号
		List<FindShopTerminalReturn> retList = shopTerminalService.findShopTerminalSelect(findShopTerminal);
		Set<String> filterSet = Sets.newHashSet();// 去重后的商户微信号
		if (retList != null && retList.size() > 0) {
			for (FindShopTerminalReturn retDto : retList) {
				filterSet.add(retDto.getNoWx());// 商户微信号
			}
		}
		logger.debug("findShopWxInfo(String) - end - return value={}", filterSet);
		return filterSet;
	}

	/**
	 * 方法说明：跳转到申请好友历史列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "applayFriend")
	public String applayFriend(Model model) {
		return "modules/im/applayFriend";
	}

	/**
	 * 方法说明：异步加载添加好友列表
	 * 
	 * @return
	 * @author 李端强 CreateDate: 2018年1月11日
	 *
	 */
	@RequestMapping(value = "applayFriendList")
	@ResponseBody
	public Map<String, Object> applayFriendList(Model model, Integer pageNo, Integer pageSize) {
		logger.debug("applayFriendList(Model model={}, Integer pageNo={}, Integer pageSize={}) - start", model, pageNo,
				pageSize);
		Map<String, Object> retMap = Maps.newHashMap();
		try {
			friendList(pageNo, pageSize, retMap);
		} catch (Exception e) {
			logger.warn("applayFriendList(Model, Integer, Integer) - exception ignored", e);
		}
		logger.debug("applayFriendList(Model, Integer, Integer) - end - return value={}", retMap);
		return retMap;
	}

	/**
	 *
	 * 方法说明：组装添加好友列表内容
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param retMap
	 * @author 李端强 CreateDate: 2018年1月12日
	 */
	private void friendList(Integer pageNo, Integer pageSize, Map<String, Object> retMap) {
		FindAddFriendsPage findAddFriendsPage = new FindAddFriendsPage();
		String merchantNo = UserUtils.getMerchantNo();// 商户编号
		findAddFriendsPage.setMerchantNo(merchantNo);
		List<Integer> wxAddTypes = Lists.newArrayList();
		wxAddTypes.add(5);
		wxAddTypes.add(6);
		wxAddTypes.add(7);
		findAddFriendsPage.setWxAddTypes(wxAddTypes);// 加好友的方式
		if (pageSize != null) {
			findAddFriendsPage.setLimit(pageSize);// 尺寸
		}
		if (pageNo != null) {
			findAddFriendsPage.setStart((pageNo - 1) * findAddFriendsPage.getLimit());// 起始
		}
		ArrayList<FindAddFriendsPageReturn> basePageReturns = Lists.newArrayList();
		Page<FindAddFriendsPageReturn> hisAddFriend = addFriendsService.findAddFriendsPage(findAddFriendsPage);// 分页查询
		if (hisAddFriend != null && hisAddFriend.getRows() != null) {
			basePageReturns.addAll(hisAddFriend.getRows());// 重新定义分页
			JsonPage<FindAddFriendsPageReturn> page = new JsonPage<FindAddFriendsPageReturn>(
					pageNo == null ? 1 : pageNo, hisAddFriend.getLimit(), hisAddFriend.getTotal(), basePageReturns);
			page.initialize();
			retMap.put("success", true);
			retMap.put("data", page);// 成功返回
		} else {
			retMap.put("success", true);
			retMap.put("data", "");// 成功返回无数据
		}
	}

	/**
	 * 
	 *
	 * 方法说明：已搜索到的微信基本信息执行加好友动作(调用次数校验)
	 *
	 * @param model
	 * @param dto
	 * @param merchantWxNo
	 * @param nickRemark
	 * @param validation
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月15日
	 *
	 */
	@RequestMapping(value = "doApplayFriend")
	@ResponseBody
	public Map<String, Object> doApplayFriend(Model model, FindWxInfoReturnDto dto, String noWxGm, String nickRemark,
			String validation, String labelName, String mobile) {
		Map<String, Object> retMap = Maps.newHashMap();
//		String merchantNo = UserUtils.getMerchantNo();//商户编号
//		if(AddQrCodeUtils.limited(merchantNo,noWxGm,redisCache,merchantParamsService,KeyConstant.ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_KEY)) {
//			retMap.put("success", false);
//			retMap.put("msg", "今天添加的好友数量已达上限");
//			return retMap;
//		};

		FindShopTerminalReturn findShopTerminalReturn = shopTerminalService.findShopTerminalByWx(noWxGm);
		if (findShopTerminalReturn.getNoWx().equals(dto.getWxQrCode())
				|| findShopTerminalReturn.getAlias().equals(dto.getWxQrCode())) {
			retMap.put("success", false);
			retMap.put("msg", "不能添加自己");
			return retMap;
		}

		// add_friends表添加记录,并向中控发送添加好友请求.
		AddAddFriends addAddFriends = new AddAddFriends();
		addAddFriends.setMemberNoGm(UserUtils.getUser().getId()); // 导购编号
		addAddFriends.setNoWxGm(noWxGm);
		addAddFriends.setWxQrCode(dto.getWxQrCode());
		if (StringUtils.isEmpty(dto.getMemberNoGm())) {
			if (AddQrCodeUtils.isMobile(dto.getWxQrCode())) {// 手机号
				addAddFriends.setMobile(dto.getWxQrCode());
				addAddFriends.setWxAddType(5);
			} else if (com.lj.base.core.util.StringUtils.isNumeric(dto.getWxQrCode())) {// QQ号
				addAddFriends.setNoQq(dto.getWxQrCode());
				addAddFriends.setWxAddType(7);
			} else {
				addAddFriends.setNoWx(dto.getWxQrCode());// 微信号
				addAddFriends.setWxAddType(6);
			}
		} else {
			dto.setNoWx(dto.getWxQrCode());
			addAddFriends.setWxAddType(1);// 主动搜索添加时,增加添加渠道
		}
		addAddFriends.setAlias(dto.getAlias());
		addAddFriends.setNickNameWx(dto.getNickNameWx());
		addAddFriends.setHeadAddress(dto.getHeadAddress());// 头像
		addAddFriends.setSex(dto.getSex());
		addAddFriends.setWxOpenId(WxOpenIdUtils.generateWxOpenId(dto.getNoWx()));// OPENID
		addAddFriends.setNoWx(dto.getNoWx());// 临时微信号
		addAddFriends.setLabelName(labelName);
		addAddFriends.setValidation(validation);// 验证信息
		addAddFriends.setMobile(mobile);
		addAddFriends.setNickRemark(nickRemark);// 昵称备注
		AddAddFriendsReturn addAddFriendsReturn = null;
		try {
			addAddFriendsReturn = addFriendsService.addAddFriends(addAddFriends);
			logger.info("doApplayFriend,已保存添加微信好友记录：{}", addAddFriendsReturn);
		} catch (TsfaServiceException e) {
			logger.error("doApplayFriend,保存添加微信好友记录异常", e);
			retMap.put("msg", e.getExceptionInfo());
			return retMap;
		} catch (Exception e) {
			logger.error("doApplayFriend,保存添加微信好友记录异常", e);
			retMap.put("msg", "无法添加该微信为好友,客户信息重复");
			return retMap;
		}
		// 向中控发送请求
		ScanAddFriendRequestDto scanAddFriendRequestDto = new ScanAddFriendRequestDto();
		scanAddFriendRequestDto.setMemberNoGm(dto.getMemberNoGm());
		scanAddFriendRequestDto.setNoWxGm(noWxGm);// 导购微信号
		scanAddFriendRequestDto.setScanId(dto.getScanId());
		scanAddFriendRequestDto.setAddCode(addAddFriendsReturn.getCode());// 主键
		scanAddFriendRequestDto.setWxQrCode(dto.getWxQrCode());
		scanAddFriendRequestDto.setNoWx(dto.getNoWx());// 微信号
		scanAddFriendRequestDto.setAlias(dto.getAlias());
		scanAddFriendRequestDto.setNickRemark(dto.getNickNameWx());
		scanAddFriendRequestDto.setValidation(validation);// 验证信息
		scanAddFriendRequestDto.setUsernamev2(dto.getUsernamev2());

		IContactsService basic = commonService.getHessianContactsService(scanAddFriendRequestDto.getNoWxGm());
		if (!basic.sendScanAddNewFriendMessage(scanAddFriendRequestDto)) {
			retMap.put("success", false);
			retMap.put("msg", "中控微信已离线！");
			return retMap;
		}

		retMap.put("success", true);
		return retMap;
	}

	/**
	 *
	 * 方法说明：已指定导购并执行加好友动作(调用次数校验)
	 * 
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param code     主键
	 * @return
	 * @author 李端强 CreateDate: 2018年1月12日
	 */
	@RequestMapping(value = "doListApplayFriend")
	@ResponseBody
	public Map<String, Object> doListApplayFriend(Model model, String code) {
		Map<String, Object> retMap = Maps.newHashMap();
		String merchantNo = UserUtils.getMerchantNo();// 商户编号
		FindAddFriends findAddFriends = new FindAddFriends();
		findAddFriends.setCode(code);
		FindAddFriendsReturn retData = addFriendsService.findAddFriends(findAddFriends);
		FindWxInfoRequestDto findWxInfoRequestDto = new FindWxInfoRequestDto();
		findWxInfoRequestDto.setMemberNoGm(retData.getMemberNoGm());// 导购编号,可以不传
		findWxInfoRequestDto.setWxQrCode(retData.getWxQrCode());// 搜索条件
		findWxInfoRequestDto.setNoWxGM(retData.getNoWxGm());// 中控端微信号
		findWxInfoRequestDto.setAddCode(code);// add_friends表主键
		if (AddQrCodeUtils.limited(merchantNo, retData.getNoWxGm(), redisCache, merchantParamsService,
				KeyConstant.ADD_FRIENDS_DAY_LIMIT_BYSHOPWX_KEY)) {
			retMap.put("success", false);
			retMap.put("msg", "今天添加的好友数量已达上限");
			return retMap;
		}
		;

		// 获取导购对应的中控微信号
		FindGuidMember findGuidMember = new FindGuidMember();
		String loginAccountNo = "";
		if (!StringUtils.isEmpty(findWxInfoRequestDto.getMemberNoGm())) {// 存在导购编号
			findGuidMember.setMemberNo(findWxInfoRequestDto.getMemberNoGm());// 导购编号,查询WX号
			FindGuidMemberReturn guid = guidMemberService.findGuidMember(findGuidMember);
			loginAccountNo = guid.getNoWx(); // 客户微信对应的中控客户端登录账号
		} else {
			loginAccountNo = findWxInfoRequestDto.getNoWxGM();// 直接使用中控微信号
		}

		IContactsService basic = commonService.getHessianContactsService(loginAccountNo);
		retMap = basic.sendAddNewFriendMessage(findWxInfoRequestDto, KeyConstant.OMS_FRIEND_LIST_SEARCH_ADD_PREFIX);
		logger.info(
				"doListApplayFriend(String merchentNo={}, String merchentWxNo={}, String qrCode={}) - 进行首次搜索并自动发送添加申请!",
				merchantNo, retData.getNoWxGm(), retData.getWxQrCode());
		retMap.put("success", true);
		return retMap;
	}

	/**
	 * 方法说明：自动回复
	 * 
	 * @param matchMerchantProblemDto
	 * @return
	 * @author 彭俊霖 CreateDate: 2018年01月24日
	 */
	@RequestMapping(value = "autoAnswer")
	@ResponseBody
	public MatchProblemReturn<MerchantPreProblemDto> autoAnswer() {
		MatchProblemReturn<MerchantPreProblemDto> answer = new MatchProblemReturn<>();
		try {
			// 查询该导购是否开启自动回复
			String merchantNo = UserUtils.getMerchantNo();
			String MemberNoGm = UserUtils.getUser().getId();
			List<MerchantAutoReplyDto> list = problemService.selectAutoReplyList(merchantNo, MemberNoGm);
			if (list == null || list.size() == 0) {
				answer.setMatchCode(ResultCode.NOT_PROBLEM);
				answer.setMatchMessage("没有开启自动回复");
				return answer;
			}

			answer.setMatchCode(ResultCode.NOT_PROBLEM);
			answer.setMatchMessage("没有匹配内容");
		} catch (TsfaServiceException ex) {
			answer.setMatchCode(ResultCode.MATCH_FAIL);
			answer.setMatchMessage("匹配失败");
			logger.error("自动回复错误！", ex.getExceptionInfo());
		} catch (Exception e) {
			answer.setMatchCode(ResultCode.MATCH_FAIL);
			answer.setMatchMessage("匹配失败");
			logger.error("自动回复错误！", e);
		}
		return answer;
	}

	/**
	 * 查询互动消息条数
	 * 
	 * @param findImFriendsInfoPage
	 * @return
	 */
	@RequestMapping(value = "loadInteractMessageCount.do")
	@ResponseBody
	public Map<String, Object> loadInteractMessageCount(FindImFriendsInfoPage findImFriendsInfoPage) {
		Map<String, Object> retMap = Maps.newHashMap();
		logger.info("loadInteractMessage ------ >{}", findImFriendsInfoPage);
		AssertUtils.notNull(findImFriendsInfoPage);
		AssertUtils.notNull(findImFriendsInfoPage.getNoWxShop(), "终端微信号不能为空");
		Integer messageCount = 0;
		ImCommentInfoDto imCommentInfoDto = new ImCommentInfoDto();
		imCommentInfoDto.setNoWxShop(findImFriendsInfoPage.getNoWxShop());
		Integer commentCount = imCommentInfoService.findImCommentInfoWebNotReadCount(imCommentInfoDto);
		ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
		imLikeInfoDto.setNoWxShop(findImFriendsInfoPage.getNoWxShop());
		Integer likeCount = imLikeInfoService.findImLikeWebNotReadCount(imLikeInfoDto);
		messageCount = commentCount + likeCount;
		retMap.put("messageCount", messageCount);
		if (messageCount > 0) {
			Page<FriendsMessageDto> page = new Page<>();
			page.setStart(0);
			page.setLimit(1);
			FriendsMessageDto findFriendsMessageDto = new FriendsMessageDto();
			findFriendsMessageDto.setNoWxShop(findImFriendsInfoPage.getNoWxShop());
			findFriendsMessageDto.setOptFlag(2); // 只查询客户发的
			page = imCommentInfoService.findFriendsMessageNotRelease(findFriendsMessageDto, page);
			Iterator<FriendsMessageDto> it = page.getRows().iterator();
			while (it.hasNext()) {
				FriendsMessageDto fm = it.next();
				String headImg = fm.getHeadImg();

				retMap.put("headImg", headImg);
				break;
			}
		}
		logger.info("===== >return loadInteractMessageCount ->{} ", findImFriendsInfoPage);
		return retMap;
	}

	/**
	 * 查询互动消息数据
	 * 
	 * @param findImFriendsInfoPage
	 * @return
	 */
	@RequestMapping(value = "messageList.do")
	@ResponseBody
	public Page<FriendsMessageDto> messageList(FindFriendsMessageRequestDto friendsMessage) {
		logger.info("messageList ------ >{}", friendsMessage);
		AssertUtils.notNull(friendsMessage);
		AssertUtils.notNull(friendsMessage.getNoWxShop(), "终端微信号不能为空");
		Page<FriendsMessageDto> page = new Page<>();
		page.setStart(friendsMessage.getStart());
		page.setLimit(friendsMessage.getLimit());
		FriendsMessageDto findFriendsMessageDto = new FriendsMessageDto();
		findFriendsMessageDto.setNoWxShop(friendsMessage.getNoWxShop());
		findFriendsMessageDto.setBeginDate(friendsMessage.getBeginDate());
		findFriendsMessageDto.setEndDate(friendsMessage.getEndDate());
		findFriendsMessageDto.setMemberName(friendsMessage.getMemberName());
		findFriendsMessageDto.setOptFlag(2); // 只查询客户发送的
		page = imCommentInfoService.findFriendsMessage(findFriendsMessageDto, page);
		return page;
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
	public Map<String, Object> loadPendingCommentCount(String noWxShop) {
		logger.debug("loadPendingCommentCount(String noWxShop={}) - start", noWxShop);
		Map<String, Object> retMap = Maps.newHashMap();
		try {
			int count = imFriendsInfoService.findImFriendsInfoPendingCommentCount(noWxShop);
			retMap.put("pendingCommentCount", count);
			logger.debug("loadPendingCommentCount(FindImIndexPage) - end - return value={}", retMap);
		} catch (TsfaServiceException e) {
			retMap.put("success", false);
			retMap.put("friendForum", "获取导购绑定的朋友圈信息异常.");
			logger.error("获取朋友圈待评论信息错误", e);
		}
		return retMap;
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
	public Map<String, Object> loadPendingCommentFriends(String noWxShop, Integer pageNo, Integer pageSize) {
		logger.debug("loadPendingCommentFriends(String noWxShop={}, String shopNo={}) - start", noWxShop);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (pageSize == null)
				pageSize = 10;// 尺寸
			int start = 0;
			if (pageNo == null)
				pageNo = 1;
			start = (pageNo - 1) * pageSize;// 起始
			// 获取朋友圈信息
			Page<ImFriendsInfoDto> infos = imFriendsInfoService.findImFriendsInfoPendingComment(noWxShop, start,
					pageSize);
			ArrayList<ImFriendsInfoDto> basePageReturns = Lists.newArrayList();
			if (infos.getRows() == null || infos.getRows().size() == 0) {// 可能为空null,则无需后续判断
				logger.info("loadPendingCommentFriends rows is  null  --- >{}", infos);
				map.put("success", true);
				map.put("friendForumPage",
						new Page<ImFriendsInfoDto>(new ArrayList<ImFriendsInfoDto>(), 0, start, pageSize));
				logger.debug("loadPendingCommentFriends(String, String) - end - return value={}", map);
				return map;
			} else {
				basePageReturns.addAll(infos.getRows());// 真实数据
				com.ape.common.persistence.Page<ImFriendsInfoDto> pages = new com.ape.common.persistence.Page<ImFriendsInfoDto>(
						pageNo == null ? 1 : pageNo, infos.getLimit(), infos.getTotal(), basePageReturns);
				pages.initialize();
				map.put("success", true);
				map.put("friendForumPage", pages);// 成功返回
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("friendForumPage", "获取导购绑定的朋友圈信息异常.");
			logger.error("获取朋友圈待评论信息错误", e);
		}
		logger.info("map ---- >", map);
		return map;
	}

	/**
	 * 获取朋友圈信息
	 * 
	 * @param friendsInfo
	 * @return
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
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setMemberNo(friendsInfo.getMemberNo());
				FindPersonMemberBaseReturn member = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
				if (member != null) {
					friendsInfo.setHeadImg(member.getHeadAddress());
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

	/**
	 * 
	 *
	 * 方法说明：批量获取终端微信的在线状态
	 *
	 * @param noWxShops 终端微信号列表，英文逗号隔开
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年2月1日
	 *
	 */
	@RequestMapping(value = "getZkTerminalStatusList.do")
	@ResponseBody
	public Map<String, Boolean> getZkTerminalStatusList(String noWxShops) {
		if (StringUtils.isEmpty(noWxShops)) {
			return null;
		}
		List<String> noWxList = Arrays.asList(noWxShops.split(","));
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (String noWx : noWxList) {
			// 集群环境下手动调用
			ICommonService basic = commonService.getHessianCommonService(noWx);
			map.put(noWx, basic.getZkTerminalStatus(noWx));
		}
		return map;
	}

	/**
	 * 
	 *
	 * 方法说明：查询聊天记录详情
	 *
	 * @param code 聊天记录CODE
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年2月2日
	 *
	 */
	@RequestMapping(value = "findChatInfo.do")
	@ResponseBody
	public FindImChatInfoReturn findChatInfo(String code) {
		try {
			Future<FindImChatInfoReturn> returnInfo = taskExecutor
					.submit(new ImChatInfoTaskCallable(imChatInfoService, code));
			logger.debug("异步获取IM聊天记录 info={}", returnInfo.get());
			return returnInfo.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
			logger.error("异步获取IM聊天记录异常! e={}", e);
		} catch (InterruptedException e) {
			logger.error("线程阻塞，立即中断! e={}", e);
			Thread.currentThread().interrupt();
		}
		return null;
	}

	/**
	 * 
	 *
	 * 方法说明：获取聊天记录图片
	 *
	 * @param code    聊天记录CODE
	 * @param imgType 图片类型：1中图（默认，当前只支持中图）、2大图
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年2月2日
	 *
	 */
	@RequestMapping(value = "findChatImage.do")
	@ResponseBody
	public FindImChatInfoReturn findChatImage(String code) {
		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(code);
		final FindImChatInfoReturn findImChatInfoReturn = imChatInfoService.findImChatInfo(findImChatInfo);

		String content = findImChatInfoReturn.getContent(); // 图片类型消息扩展内容，格式为JSON串

		if (StringUtils.isEmpty(content)) { // 请求中控端上传图片
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					sendFindChatImageMessage(findImChatInfoReturn);
				}
			});
		} else {
			// 将扩展内容转换为MAP对象
			Map<String, String> contentMap = JsonUtils.mapFromJson(content);
			// String imgKey = imgType == 2 ? Constants.CHAT_IMAGE_BIG_KEY:
			// Constants.CHAT_IMAGE_MIDDLE_KEY; // 图片类型关键字(默认中图)
			String imgAddr = contentMap.get(Constants.CHAT_IMAGE_MIDDLE_KEY);
			// 图片已上传到服务器，则直接返回
			if (StringUtils.isNotEmpty(imgAddr)) {
				return findImChatInfoReturn;
			} else { // 没有上传到服务器，则请求中控端上传图片
				this.sendFindChatImageMessage(findImChatInfoReturn);
			}
		}
		return null;
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
		findChatImageMessage.setFindFlag(2); // 导购助手查询
		try {
			IChatService basic = commonService.getHessianChatService(findChatImageMessage.getNoWxShop());
			basic.sendFindChatImageMessage(findChatImageMessage);
		} catch (Exception e) {
			logger.error("请求中控客户端获取图片失败", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：获取聊天记录视频文件
	 *
	 * @param code 聊天记录CODE
	 * @returnc
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月3日
	 *
	 */
	@RequestMapping(value = "findChatVideo.do")
	@ResponseBody
	public FindImChatInfoReturn findChatVideo(String code) {
		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(code);
		FindImChatInfoReturn findImChatInfoReturn = imChatInfoService.findImChatInfo(findImChatInfo);

		if (StringUtils.isEmpty(findImChatInfoReturn.getResourcesPath())) { // 请求中控端上传视频
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
	 * 图片下载请求
	 * 
	 * @param friendsDownRequestDto
	 * @return
	 */
	@RequestMapping("toDownload.do")
	@ResponseBody
	public Map<String, String> toDownload(FriendsDownRequestDto friendsDownRequestDto) {
		logger.info("toDownload------------ >{}", friendsDownRequestDto);
		ToDownloadPic toDownload = new ToDownloadPic();
		toDownload.setNoWx(friendsDownRequestDto.getNoWx());
		toDownload.setFriendsId(friendsDownRequestDto.getFriendsId());
		toDownload.setImgUrl(friendsDownRequestDto.getPicUrl());
		friendsFacade.toDownloadPic(toDownload);
		Map<String, String> result = new HashMap<String, String>();
		result.put("state", "true");
		return result;
	}

	/**
	 * 
	 *
	 * 方法说明：导购助手积分商城入口
	 *
	 * @param memberNo
	 * @param memberNoGm
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年4月20日
	 *
	 */
	@RequestMapping(value = "queryIemEntry.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> queryIemEntry(String memberNo, String memberNoGm) {
		logger.debug("queryIemEntry(String memberNoGm={}, String memberNo={}) - start", memberNoGm, memberNo);

		Map<String, Object> map = Maps.newHashMapWithExpectedSize(5);
		String merchantNo = UserUtils.getMerchantNo();// 商户编号
		// 聚客app返回是否在IM显示积分商城入口
		try {
			FindMerchantParams findMerchantParams = new FindMerchantParams();
			String groupName = "IEM";// 组名
			findMerchantParams.setGroupName(groupName);
			findMerchantParams.setMerchantNo(merchantNo);
			Map<String, String> paramsMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);

			String showIemEntry = paramsMap.get("IEM_SWITCH");
			String shareTitle = StringUtils.isNotEmpty(paramsMap.get("IEM_SHARE_TITLE"))
					? paramsMap.get("IEM_SHARE_TITLE")
					: "积分商城 | 积分当钱花";
			String shareDesc = StringUtils.isNotEmpty(paramsMap.get("IEM_SHARE_DESC")) ? paramsMap.get("IEM_SHARE_DESC")
					: "玩转积分享大礼";
			if (com.lj.base.core.util.StringUtils.isNotEmpty(showIemEntry) && "ON".equals(showIemEntry)) {
				map.put("showIemEntry", true);
				map.put("shareTitle", shareTitle);
				map.put("shareDesc", shareDesc);

				Map<String, String> params = localCacheSystemParams.getSystemParamGroup(SystemAliasName.iem.name(),
						"IEM");
				StringBuilder sb = new StringBuilder("?");
				sb.append("merchantNo=");
				sb.append(merchantNo);
				sb.append("&memberNo=");
				sb.append(memberNo);
				sb.append("&memberNoGm=");
				sb.append(memberNoGm);

				String urlDomain = localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl");
				map.put("shareUrl", urlDomain + "api" + params.get("SHARE_URL") + sb.toString());// 分享地址 "/member/iem"
				map.put("iconUrl", urlDomain + params.get("SHARE_ICON"));// logo地址 "/app/logo/iemlogo.png"
			} else {
				map.put("showIemEntry", false);
			}
		} catch (Exception e) {
			logger.error("加载积分商城配置参数错误！", e);
		}

		logger.debug("queryIemEntry(String, String) - end - return value={}", map);
		return map;
	}

	/**
	 * 
	 *
	 * 方法说明：统计当前导购助手下未回复客户数
	 *
	 * @return 废弃，太TMD耗性能了 DZP
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月18日
	 *
	 */
	@Deprecated
	@RequestMapping(value = "findUnrespMemberCount.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int findUnrespMemberCount() {
//		return personMemberImService.findUnrespMemberCount(UserUtils.getUser().getId());
		return 0;
	}

	/**
	 * 
	 *
	 * 方法说明：根据微信号搜索客户信息
	 *
	 * @param shopNo     终端编号
	 * @param noWxShop   终端微信
	 * @param memberNoGm 当前添加好友的导购
	 * @param username   待搜索客户username
	 * @param alias      待搜索客户alias
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月15日
	 *
	 */
	@RequestMapping(value = "findFriendsByWxNo")
	@ResponseBody
	public Map<String, Object> findFriendsByWxNo(String shopNo, String noWxShop, String memberNoGm, String username,
			String alias) {
		logger.debug(
				"findFriendsByWxNo(String shopNo={},  String noWxShop={}, String memberNoGm={}, String username={}, String alias={}) - start",
				shopNo, noWxShop, memberNoGm, username, alias);
		Map<String, Object> ret = Maps.newHashMap();

		// 根据客户微信号和别名查询客户基础表信息
		FindPersonMemberBaseReturn findPersonMemberBaseReturn = personMemberBaseService
				.findMemberBaseByNoWxOrAlias(username, alias);
		// 客户基础表不存在，直接返回
		if (findPersonMemberBaseReturn == null) {
			ret.put("success", true);
			logger.debug("findFriendsByWxNo() - end - return value={}", ret);
			return ret;
		}

		// 客户基础表存在，则查询同一终端是否已添加该客户
		FindGuidByShopAndMember findGuidByShopAndMember = new FindGuidByShopAndMember();
//		findGuidByShopAndMember.setShopNo(shopNo);
		findGuidByShopAndMember.setMemberNo(findPersonMemberBaseReturn.getMemberNo());
		List<FindGuidByShopAndMemberReturn> guidList = guidMemberService
				.findGuidByShopAndMember(findGuidByShopAndMember);
		for (FindGuidByShopAndMemberReturn guid : guidList) {
			ret.put("memberNameGm", guid.getMemberName());
			if (memberNoGm.equals(guid.getMemberNo())) { // 该导购已添加该客户
				ret.put("success", false);
				ret.put("msg", "");
				logger.debug("findFriendsByWxNo() - end - return value={}", ret);
				return ret;
			}
			// 同一终端同一微信号
			if (noWxShop.equals(guid.getNoWx())) { // 同一终端其他导购已添加该客户微信
				ret.put("success", false);
				ret.put("msg", "终端其他导购已添加该客户");
				logger.debug("findFriendsByWxNo() - end - return value={}", ret);
				return ret;
			}
		}
		return ret;
	}

	/**
	 * 撤销消息
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "toCancelChatInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseDto toCancelChatInfo(String code) {
		logger.debug("toCancelChatInfo(String code)", code);
		try {
			if (StringUtils.isBlank(code)) {
				return ResponseDto.failureResp("0", "参数错误!");
			}
			imChatInfoService.toCancelChatInfo(code);
			return ResponseDto.successResp();
		} catch (Exception e) {
			logger.error("撤销错误 e={}", e);
			return ResponseDto.failureResp("0", "撤销消息失败!");
		}
	}

}
