package com.lj.oms.member;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.addfriends.DistributionMember;
import com.lj.business.member.dto.addfriends.FindAddFriendsAllotPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPageReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.oms.common.BaseController;
import com.lj.oms.dto.CommonRepsonseDto;
import com.lj.oms.utils.UserUtils;
/**
 * 
 * 
 * 类说明：添加微信好友Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年11月13日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/addFriends")
public class AddFriendsController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(AddFriendsController.class);
	
	/**  未分配好友列表页面 **/
	private final static String PAGE_VIEW_ADD_FRIENDS_LIST = "modules/member/addFriendsList";		
	/**  今日待认领客户列表页面 **/
	private final static String PAGE_VIEW_ADD_FRIENDS_TODAYN_LIST = "modules/member/addFriendsTodayNList";
	/**  今日已认领客户列表页面 **/
	private final static String PAGE_VIEW_ADD_FRIENDS_TODAYY_LIST = "modules/member/addFriendsTodayYList";
	/**  分配客户页面 **/
	private final static String PAGE_VIEW_ALLOT_VIEW = "modules/member/allotView";
   
	@Resource
	private  IAddFriendsService addFriendsService;
	@Resource
	private  IPersonMemberService personMemberService;
	@Resource
	private  IGuidMemberService guidMemberService;
	@Resource
	private  IGmAssistantShopService gmAssistantShopService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	/**
	 * 
	 *
	 * 方法说明：未分配好友列表
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月13日
	 *
	 */
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindAddFriendsAllotPage findAddFriendsAllotPage){
		try {
			if(pageNo!=null){
				findAddFriendsAllotPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findAddFriendsAllotPage.setLimit(pageSize);
			}
			findAddFriendsAllotPage.setStartTime(DateUtils.getDateByFirstSeconds(findAddFriendsAllotPage.getStartTime()));
			findAddFriendsAllotPage.setEndTime(DateUtils.getDateByLastSeconds(findAddFriendsAllotPage.getEndTime()));
			
			findAddFriendsAllotPage.setMerchantNo(UserUtils.getMerchantNo());
//			findAddFriendsAllotPage.setShopNos(CcUtils.getShopNoList());
			
			findAddFriendsAllotPage.setFlag(Boolean.FALSE);//待认领
			
			model.addAttribute("page", this.findAddFriends(findAddFriendsAllotPage, pageNo));
			model.addAttribute("findAddFriendsAllotPage", findAddFriendsAllotPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取未分配客户信息失败！",e);
		}
		return PAGE_VIEW_ADD_FRIENDS_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：今日待认领客户列表
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月23日
	 *
	 */
	@RequestMapping(value={"todayNList"})
	public String todayNList(Model model,Integer pageNo,Integer pageSize,FindAddFriendsAllotPage findAddFriendsAllotPage){
		try {
			if(pageNo!=null){
				findAddFriendsAllotPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findAddFriendsAllotPage.setLimit(pageSize);
			}
			
			Date now = new Date();
			findAddFriendsAllotPage.setStartTime(DateUtils.getDateByFirstSeconds(now));
			findAddFriendsAllotPage.setEndTime(DateUtils.getDateByLastSeconds(now));
			findAddFriendsAllotPage.setMerchantNo(UserUtils.getMerchantNo());
			findAddFriendsAllotPage.setFlag(Boolean.FALSE);//待认领
			
			model.addAttribute("page", this.findAddFriends(findAddFriendsAllotPage, pageNo));
			model.addAttribute("findAddFriendsAllotPage", findAddFriendsAllotPage);
		} catch (Exception e) {
			logger.error("获取今日待认领客户信息失败！",e);
		}
		return PAGE_VIEW_ADD_FRIENDS_TODAYN_LIST;
	}

	/**
	 * 
	 *
	 * 方法说明：今日已认领客户列表
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月23日
	 *
	 */
	@RequestMapping(value={"todayYList"})
	public String todayYList(Model model,Integer pageNo,Integer pageSize,FindAddFriendsAllotPage findAddFriendsAllotPage){
		try {
			if(pageNo!=null){
				findAddFriendsAllotPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findAddFriendsAllotPage.setLimit(pageSize);
			}
			Date now = new Date();
			findAddFriendsAllotPage.setStartTime(DateUtils.getDateByFirstSeconds(now));
			findAddFriendsAllotPage.setEndTime(DateUtils.getDateByLastSeconds(now));
			
			findAddFriendsAllotPage.setMerchantNo(UserUtils.getMerchantNo());
//			findAddFriendsAllotPage.setShopNos(CcUtils.getShopNoList());
			
			findAddFriendsAllotPage.setFlag(Boolean.TRUE);//已认领
			
			model.addAttribute("page", this.findAddFriends(findAddFriendsAllotPage, pageNo));
			model.addAttribute("findAddFriendsAllotPage", findAddFriendsAllotPage);
		} catch (Exception e) {
			logger.error("获取今日待认领客户信息失败！",e);
		}
		return PAGE_VIEW_ADD_FRIENDS_TODAYY_LIST;
	}
	
	/**
	 * 
	 * 方法说明：未分配客户所关联导购
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月14日
	 *
	 */
	@RequestMapping(value={"view"})
	public String view(Model model,String noWxGm,String code,String vals,String sourceFlag,String searchVal){
		try {
			//分配的导购列表，因该是绑定了该终端的导购助手
			FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
			findGmAssistantShop.setMerchantNo(UserUtils.getMerchantNo());
			findGmAssistantShop.setNoWx(noWxGm);
			findGmAssistantShop.setSearchVal(searchVal);
			List<FindGmAssistantShopReturn> list =gmAssistantShopService.findGmAssistantShopList(findGmAssistantShop);
			model.addAttribute("list", list);
			model.addAttribute("addFriendsCode", code);
			model.addAttribute("sourceFlag", sourceFlag);
			model.addAttribute("data", findGmAssistantShop);
		} catch (Exception e) {
			logger.error("获取未分配客户导购信息失败！",e);
		}
		return PAGE_VIEW_ALLOT_VIEW;
	}
	
	/**
	 * 
	 * 方法说明：分配客户
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月14日
	 *
	 */
	@ResponseBody
	@RequestMapping(value={"allot"})
	public CommonRepsonseDto allot(Model model,DistributionMember distributionMember, RedirectAttributes redirectAttributes){
		CommonRepsonseDto commonRepsonseDto = null;
		try {
			StringBuilder failureMsg = new StringBuilder();
			
			String[] vals = distributionMember.getCode().split(",");
//			int successNum = 0;
//			int failureNum = 0;
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						for (String val : vals) {
							try {
								distributionMember.setCode(val);
								addFriendsService.distributionMember(distributionMember);
//								successNum++;
							} catch (TsfaServiceException ex) {
								failureMsg.append("<br/>").append(ex.getExceptionInfo());
//								failureNum++;
	//							continue;
							} catch (Exception ex) {
								failureMsg.append("<br/>").append(ex.getMessage());
//								failureNum++;
	//							continue;
							}
						}
					}
				});
			
//			if (failureNum > 0) {
//				failureMsg.insert(0, "，失败 " + failureNum + " 条客户，失败信息如下：");
//			}
//			commonRepsonseDto = CommonRepsonseDto.generateSuccessResponse("成功分配"+successNum+"个客户"+failureMsg);
			return CommonRepsonseDto.generateSuccessResponse("后台分配中，如分配的客户量过多，请多等一会再查看！");
		} catch (Exception e) {
			logger.error("分配客户失败！", e);
			commonRepsonseDto = CommonRepsonseDto.generateFailureResponse("分配客户失败！");
		}
		return commonRepsonseDto;
	}
	

	
	
	/**
	 * 
	 *
	 * 方法说明：查询添加好友记录
	 *
	 * @param findAddFriendsAllotPage
	 * @param pageNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月4日
	 *
	 */
	private com.ape.common.persistence.Page<FindAddFriendsPageReturn> findAddFriends(FindAddFriendsAllotPage findAddFriendsAllotPage, Integer pageNo) {
		Page<FindAddFriendsPageReturn> pages=addFriendsService.findAddFriendsAllotPage(findAddFriendsAllotPage);
		List<FindAddFriendsPageReturn> list=Lists.newArrayList();
		list.addAll(pages.getRows());
//		Map<String, String> shopNameMap = new HashMap<String, String>();
//		String shopName = null;
		for(FindAddFriendsPageReturn addFriendsPageReturn : list){
//			String i = Math.random()+"";
//			addFriendsPageReturn.setRemark(StringUtils.substring(i,2,6));
//			addFriendsPageReturn.setRemark1(StringUtils.substring(i,5,11));
//			if(!StringUtils.isEmpty(addFriendsPageReturn.getNoWx()) && StringUtils.isEmpty(addFriendsPageReturn.getWxOpenId())) {	//未初始化wxOpenId,则强制初始化
//				addFriendsPageReturn.setWxOpenId(WxOpenIdUtils.generateWxOpenId(addFriendsPageReturn.getNoWx()));
//			}
			// 同步终端名称
//			shopName = shopNameMap.get(addFriendsPageReturn.getShopNo());	// 优先从内存中取
//			if(StringUtils.isNotEmpty(shopName)) {
//				addFriendsPageReturn.setShopName(shopName);
//			} else { // 内存中没有,则查询数据库
//				FindShopReturn shop = shopService.findshop(addFriendsPageReturn.getShopNo());
//				if(shop != null) {
//					addFriendsPageReturn.setShopName(shop.getShopName());
//					shopNameMap.put(shop.getShopNo(), shop.getShopName());	// 将查询结果加载内存中
//				}
//			}
		}
		com.ape.common.persistence.Page<FindAddFriendsPageReturn> page = new com.ape.common.persistence.Page<FindAddFriendsPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
		page.initialize();
		return page;
	}
}
