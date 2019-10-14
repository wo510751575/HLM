package com.lj.oms.sys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.ape.common.persistence.Page;
import com.ape.common.utils.CacheUtils;
import com.ape.common.utils.Collections3;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.base.core.encryption.MD5;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.AddFriends;
import com.lj.business.member.dto.AddGuidMember;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindGuidMemberRwPage;
import com.lj.business.member.dto.GuidMemberRwDto;
import com.lj.business.member.dto.UpdateGuidMember;
import com.lj.business.member.dto.chatroom.FindChatRoomPage;
import com.lj.business.member.dto.chatroom.FindChatRoomPageReturn;
import com.lj.business.member.dto.gmAssistantShop.AddGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.emus.MemberStatus;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberRwService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.st.dto.FindUserCountPage;
import com.lj.business.st.dto.FindUserCountPageReturn;
import com.lj.business.st.service.IUserCountService;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.Role;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.sys.DictService;
import com.lj.oms.service.sys.SystemService;
import com.lj.oms.service.sys.UserService;
import com.lj.oms.utils.UserUtils;
import com.lj.oms.utils.excel.ExportExcel;
import com.lj.oms.utils.excel.dto.UserCountExportDto;
import com.ye.business.ad.dto.FindRwUserBeansPage;
import com.ye.business.ad.dto.RwUserBeansDto;
import com.ye.business.ad.service.IRwUserBeansService;

/**
 * 用户Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	private static final String FORM = "modules/sys/userForm";
	private static final String REDIRECT_LIST = "redirect:" + Global.getAdminPath() + "/sys/user/list?repage";
	private static final String REDIRECT_USER_COUNT_LIST = "redirect:" + Global.getAdminPath() + "/sys/user/count?repage";
	private static final String LIST = "modules/sys/userList";
	private static final String USER_INDEX = "modules/sys/userIndex";
	public static final String USER_CACHE = "userCache";
	public static final String CACHE_MENU_LIST = "menuList";
	private DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化一下时间
	@Autowired
	private SystemService systemService;
	@Autowired
	private IGmAssistantShopService gmAssistantShopService;
	@Autowired
	private IGuidMemberService guidMemberService;
	@Autowired
	private DictService dictService;
	@Autowired
	private UserService userService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	// 导购扩展热文用户记录
	@Autowired
	private IGuidMemberRwService guidMemberRwService;
	// 用户广告豆
	@Autowired
	private IRwUserBeansService rwUserBeansService;
	@Resource
	public IAddFriendsService addFriendsService;
	@Resource
	public IChatRoomService chatRoomService;
	@Resource
	public IUserCountService userCountService;
	@Resource
	private IImChatInfoService imChatInfoService;
	@Resource
	private IPersonMemberService personMemberService;
	
	
	@ModelAttribute
	public User get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return systemService.getUser(id);
		} else {
			return new User();
		}
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "index" })
	public String index(User user, Model model) {
		return USER_INDEX;
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "list", "" })
	public String list(User user, String searchCompanyId, String searchOfficeId, String searchName, String searchLoginName, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (StringUtils.isNotEmpty(searchCompanyId)) {
			Office company = new Office();
			company.setId(searchCompanyId);
			user.setCompany(company);
		}
		if (StringUtils.isNotEmpty(searchOfficeId)) {
			Office office = new Office();
			office.setId(searchOfficeId);
			user.setOffice(office);
		}
		if (StringUtils.isNotEmpty(searchName)) {
			user.setName(searchName);
		}
		if (StringUtils.isNotEmpty(searchLoginName)) {
			user.setLoginName(searchLoginName);
		}
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
		List<User> userList = page.getList();
		for (User userTmp : userList) {
			List<Role> roleList = systemService.findRolesByUserId(userTmp.getId());
			if(!roleList.isEmpty()){
				String roleName = "";
				for (Role role : roleList) {
					if(StringUtils.isEmpty(roleName)){
						roleName = role.getName();
					}else{
						roleName = roleName + "," +role.getName();
					}
				}
				userTmp.setRoleName(roleName);
			}
		}
		// 扩展热文用户信息记录
		List<String> codeList = page.getList().stream().map(User::getId).collect(Collectors.toList());
		if (!Collections3.isEmpty(codeList)) {

			GuidMemberRwDto findGuidMemberRw = new GuidMemberRwDto();
			findGuidMemberRw.setCodeList(codeList);

			FindGuidMemberRwPage findGuidMemberRwPage = new FindGuidMemberRwPage();
			findGuidMemberRwPage.setParam(findGuidMemberRw);

			List<GuidMemberRwDto> returnGuidMemberRw = guidMemberRwService.findGuidMemberRws(findGuidMemberRwPage);
			Map<String, GuidMemberRwDto> returnGuidMemberRwMap = returnGuidMemberRw.stream()
					.collect(Collectors.toMap(GuidMemberRwDto::getCode, GuidMemberRwDto -> GuidMemberRwDto));

			model.addAttribute("returnGuidMemberRwMap", returnGuidMemberRwMap);

			// 查询用户广告豆
			RwUserBeansDto findRwUserBeans = new RwUserBeansDto();
			findRwUserBeans.setMemberNoList(codeList);

			FindRwUserBeansPage findRwUserBeansPage = new FindRwUserBeansPage();
			findRwUserBeansPage.setParam(findRwUserBeans);

			List<RwUserBeansDto> returnRwUserBeans = rwUserBeansService.findRwUserBeanss(findRwUserBeansPage);
			Map<String, RwUserBeansDto> returnRwUserBeansMap = returnRwUserBeans.stream().collect(Collectors.toMap(RwUserBeansDto::getMemberNo, RwUserBeansDto -> RwUserBeansDto));
			model.addAttribute("returnRwUserBeansMap", returnRwUserBeansMap);
		}

		model.addAttribute("page", page);
		return LIST;
	}
	
	/**
	 * 
	 *@Desc 客户统计查询页面
	 *@param user
	 *@param searchCompanyId
	 *@param searchOfficeId
	 *@param searchName
	 *@param searchLoginName
	 *@param request
	 *@param response
	 *@param model
	 *@return
	 *@return String
	 *@author 贾光朝
	 *@createDate 2019年5月29日下午5:43:01
	 */
	@RequestMapping(value = "count")
	public String count(FindUserCountPage findUserCountPage,Integer pageNo, Integer pageSize, Model model){
		try {
			if(null == findUserCountPage.getStartTime()){
				Date dNow = new Date(); //当前时间
				Date dBefore = new Date();
				Calendar calendar = Calendar.getInstance(); //得到日历
				calendar.setTime(dNow);//把当前时间赋给日历
				calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
				dBefore = calendar.getTime(); //得到前一天的时间
				String startDate = dateFmt.format(dBefore); //格式化前一天
				startDate = startDate.substring(0,10)+" 00:00:00";
				String endDate = startDate.substring(0,10)+" 23:59:59";
				findUserCountPage.setStartTime(DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
				findUserCountPage.setEndTime(DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
			}else{
				if(null != findUserCountPage.getStartTime() && null != findUserCountPage.getEndTime()){
					String start = dateFmt.format(findUserCountPage.getStartTime());
					String end = dateFmt.format(findUserCountPage.getEndTime());
					String endDate = end.substring(0,10)+" 23:59:59";
					findUserCountPage.setEndTime(DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
				}
			}
			findUserCountPage.setMerchantNo(UserUtils.getMerchantNo());
			findUserCountPage.setStart(pageNo == null? 0:(pageNo - 1) * pageSize);
			findUserCountPage.setLimit(pageSize == null? 10:pageSize);
			
			com.lj.base.core.pagination.Page<FindUserCountPageReturn> tempPage = userCountService.findUserCountPage(findUserCountPage);
			List<FindUserCountPageReturn> list = Lists.newArrayList();
			list.addAll(tempPage.getRows());
			for (FindUserCountPageReturn findUserCountPageReturn : list) {
				Map map = new HashMap<>();
				map.put("memberNoGm", findUserCountPageReturn.getUserId());
				map.put("merchantNo", findUserCountPage.getMerchantNo());
				map.put("endTime", findUserCountPage.getEndTime());
				int totalMember = personMemberService.findTotalMember(map);
				findUserCountPageReturn.setTotalMember(totalMember);//用户认领客户总量
				map.put("startTime", findUserCountPage.getStartTime());
				int newMember = personMemberService.findTotalMember(map);
				findUserCountPageReturn.setNewMember(newMember);
				
				Map mapPhone = new HashMap<>();
				mapPhone.put("memberNoGm", findUserCountPageReturn.getUserId());
				mapPhone.put("endTime", findUserCountPage.getEndTime());
				int totalMemberPhone = personMemberService.findTotalMemberPhone(mapPhone);
				findUserCountPageReturn.setTotalMemberPhone(totalMemberPhone);//手机号码客户总量
				mapPhone.put("startTime", findUserCountPage.getStartTime());
				int newMemberPhone = personMemberService.findTotalMemberPhone(mapPhone);
				findUserCountPageReturn.setNewMemberPhone(newMemberPhone);;//新增手机号码客户量
				
				Map mapChat = new HashMap<>();
				mapChat.put("memberNoGm", findUserCountPageReturn.getUserId());
				mapChat.put("startTime", findUserCountPage.getStartTime());
				mapChat.put("endTime", findUserCountPage.getEndTime());
				int chatTotal = imChatInfoService.selectChatCount(mapChat);
				findUserCountPageReturn.setChatTotal(chatTotal);//统计时间内用户聊天总条数
				
				int chatCount = imChatInfoService.findChatCount(mapChat);
				findUserCountPageReturn.setChatCount(chatCount);//聊天客户量
				findUserCountPageReturn.setUnChatCount(totalMember-chatCount);//未聊天客户量
			}
			com.ape.common.persistence.Page<FindUserCountPageReturn> page = new com.ape.common.persistence.Page<FindUserCountPageReturn>(pageNo == null ? 1 : pageNo, tempPage.getLimit(),
					tempPage.getTotal(), list);
			page.initialize();
			
			model.addAttribute("page", page);
			model.addAttribute("findUserCountPage", findUserCountPage);
		} catch (Exception e) {
			logger.error("查询客户统计信息错误!", e);
		}
		return "modules/sys/countList";
	}
	
	/**
	 * 
	 *@Desc 导出用户统计数据
	 *@param response
	 *@param findPersonMemberPage
	 *@param pageNo
	 *@param pageSize
	 *@param redirectAttributes
	 *@return
	 *@return String
	 *@author 贾光朝
	 *@createDate 2019年6月3日下午3:16:14
	 */
	@RequestMapping(value = "export")
	public String export(HttpServletResponse response, FindUserCountPage findUserCountPage, Integer pageNo, Integer pageSize, RedirectAttributes redirectAttributes) {
		try {
			if(null == findUserCountPage.getStartTime()){
				Date dNow = new Date(); //当前时间
				Date dBefore = new Date();
				Calendar calendar = Calendar.getInstance(); //得到日历
				calendar.setTime(dNow);//把当前时间赋给日历
				calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
				dBefore = calendar.getTime(); //得到前一天的时间
				String startDate = dateFmt.format(dBefore); //格式化前一天
				startDate = startDate.substring(0,10)+" 00:00:00";
				String endDate = startDate.substring(0,10)+" 23:59:59";
				findUserCountPage.setStartTime(DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
				findUserCountPage.setEndTime(DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
			}else{
				if(null != findUserCountPage.getStartTime() && null != findUserCountPage.getEndTime()){
					String start = dateFmt.format(findUserCountPage.getStartTime());
					String end = dateFmt.format(findUserCountPage.getEndTime());
					String endDate = end.substring(0,10)+" 23:59:59";
					findUserCountPage.setEndTime(DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
				}
			}
			findUserCountPage.setMerchantNo(UserUtils.getMerchantNo());
			findUserCountPage.setStart(pageNo == null? 0:(pageNo - 1) * pageSize);
			findUserCountPage.setLimit(pageSize == null? 10:pageSize);
			com.lj.base.core.pagination.Page<FindUserCountPageReturn> tempPage = userCountService.findUserCountPage(findUserCountPage);
			List<FindUserCountPageReturn> list = Lists.newArrayList();
			list.addAll(tempPage.getRows());
			for (FindUserCountPageReturn findUserCountPageReturn : list) {
				Map map = new HashMap<>();
				map.put("memberNoGm", findUserCountPageReturn.getUserId());
				map.put("merchantNo", findUserCountPage.getMerchantNo());
				map.put("endTime", findUserCountPage.getEndTime());
				int totalMember = personMemberService.findTotalMember(map);
				findUserCountPageReturn.setTotalMember(totalMember);//用户认领客户总量
				map.put("startTime", findUserCountPage.getStartTime());
				int newMember = personMemberService.findTotalMember(map);
				findUserCountPageReturn.setNewMember(newMember);
				
				Map mapPhone = new HashMap<>();
				mapPhone.put("memberNoGm", findUserCountPageReturn.getUserId());
				mapPhone.put("endTime", findUserCountPage.getEndTime());
				int totalMemberPhone = personMemberService.findTotalMemberPhone(mapPhone);
				findUserCountPageReturn.setTotalMemberPhone(totalMemberPhone);//手机号码客户总量
				mapPhone.put("startTime", findUserCountPage.getStartTime());
				int newMemberPhone = personMemberService.findTotalMemberPhone(mapPhone);
				findUserCountPageReturn.setNewMemberPhone(newMemberPhone);;//新增手机号码客户量
				
				Map mapChat = new HashMap<>();
				mapChat.put("memberNoGm", findUserCountPageReturn.getUserId());
				mapChat.put("startTime", findUserCountPage.getStartTime());
				mapChat.put("endTime", findUserCountPage.getEndTime());
				int chatTotal = imChatInfoService.selectChatCount(mapChat);
				findUserCountPageReturn.setChatTotal(chatTotal);//统计时间内用户聊天总条数
				
				int chatCount = imChatInfoService.findChatCount(mapChat);
				findUserCountPageReturn.setChatCount(chatCount);//聊天客户量
				findUserCountPageReturn.setUnChatCount(totalMember-chatCount);//未聊天客户量
			}
			String fileName = "用户统计数据导出.xlsx";
			new ExportExcel("用户统计数据导出", UserCountExportDto.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			logger.error("导出用户统计数据错误!", e);
		}
		return REDIRECT_USER_COUNT_LIST;
	}

	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "listData" })
	public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
		return page;
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, String searchCompanyId, String searchOfficeId, String searchName, String searchLoginName, Model model) {
		if (user.getCompany() == null || user.getCompany().getId() == null) {
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice() == null || user.getOffice().getId() == null) {
			user.setOffice(UserUtils.getUser().getOffice());
		}

		// 创建当前登录账户可管理的用户类型
		String userTypeStr = UserUtils.getUser().getUserType();
		if (StringUtils.isNotEmpty(userTypeStr)) {
			int userType = Integer.valueOf(userTypeStr);
			List<Dict> dictList = dictService.getList("sys_user_type");
			if (dictList != null && !dictList.isEmpty()) {
				Map<String, String> userTypeMap = new HashMap<String, String>();
				for (Dict dict : dictList) {
					if (StringUtils.isNotEmpty(dict.getValue()) && userType <= Integer.valueOf(dict.getValue())) {
						userTypeMap.put(dict.getValue(), dict.getLabel());
					}
					model.addAttribute("userTypeMap", userTypeMap);
				}
			}
		}

		// 获取用户扩展信息
		if (StringUtils.isNotBlank(user.getId())) {

			GuidMemberRwDto findGuidMemberRw = new GuidMemberRwDto();
			findGuidMemberRw.setCode(user.getId());
			GuidMemberRwDto guidMemberRw = guidMemberRwService.findGuidMemberRw(findGuidMemberRw);

			model.addAttribute("guidMemberRw", guidMemberRw);

			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(user.getId());
			FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);

			model.addAttribute("guidMember", findGuidMemberReturn);
		}

		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		model.addAttribute("searchCompanyId", searchCompanyId);
		model.addAttribute("searchOfficeId", searchOfficeId);
		model.addAttribute("searchName", searchName);
		model.addAttribute("searchLoginName", searchLoginName);

		return FORM;
	}

	/**
	 * 
	 * *方法说明：
	 *
	 * @param user
	 * @param oldShopNos
	 * @param searchCompanyId
	 * @param searchOfficeId
	 * @param searchName
	 * @param searchLoginName
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @param flag
	 * @param guidMemberRw 导购扩展热文用户信息
	 * @param addGuidMember 导购用户信息
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年6月24日
	 */
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(User user, String oldShopNos,String searchCompanyId,String searchOfficeId,String searchName,String searchLoginName, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes, String flag, GuidMemberRwDto guidMemberRw, AddGuidMember addGuidMember) {
		/** 新增用户则必须填密码*/
		if (StringUtils.isBlank(user.getId())){
			if (StringUtils.isBlank(user.getNewPassword())) {
				addMessage(model, "保存用户'" + user.getLoginName() + "'失败，未设置密码！");
				return form(user, searchCompanyId, searchOfficeId, searchName, searchLoginName, model);
			}
		}
		String pwd = user.getPassword();	//同步导购密码用
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
			pwd = user.getNewPassword();
		}
		if (!TRUE.equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))) {
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, searchCompanyId, searchOfficeId, searchName, searchLoginName, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		List<Role> allRole = systemService.findAllRole();
		for (Role r : allRole) {
			if (roleIdList.contains(r.getId())) {
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		user.setLayout(User._DARKBLUE);

		// 保存用户信息
		systemService.saveUser(user);

		String status = user.getLoginFlag().equals("1") ? MemberStatus.NORMAL.toString() : MemberStatus.CANCEL.toString();
		// 同步导购信息-商户管理员才同步
		if (user.getUserType().equals(User.USERTYPE_2)||user.getUserType().equals(User.USERTYPE_4)) {
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(user.getId());
			FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
			if (findGuidMemberReturn == null) {
				addGuid(user, pwd, status, addGuidMember);
			} else {
				updateGuid(user, pwd, status, addGuidMember);
			}

			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					synGmAssistantShop(user);
					logger.info("synGmAssistantShop end ");
				}
			});
			
			// 同步热文用户信息
			updateGuidMemberRw(user, guidMemberRw);
		}

		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())) {
			UserUtils.clearCache();
		}
		//清除角色菜单缓存
		CacheUtils.remove(USER_CACHE, CACHE_MENU_LIST + user.getId());

		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");

		return "redirect:" + Global.getAdminPath() + "/sys/user/list?searchCompanyId=" + searchCompanyId + "&searchOfficeId=" + searchOfficeId + "&searchName=" + searchName
				+ "&searchLoginName=" + searchLoginName;
	}

	/**
	 * 
	 * *方法说明：同步热文信息
	 *
	 * @param user
	 * @param guidMemberRw
	 * @author sjiying
	 * @CreateDate 2019年6月24日
	 */
	private void updateGuidMemberRw(User user, GuidMemberRwDto guidMemberRw) {
		// 同步热文信息
		GuidMemberRwDto findGuidMemberRw = new GuidMemberRwDto();
		findGuidMemberRw.setCode(user.getId());
		GuidMemberRwDto findGuidMemberRwReturn = guidMemberRwService.findGuidMemberRw(findGuidMemberRw);

		guidMemberRw.setCode(user.getId());
		guidMemberRw.setMerchantNo(user.getCompany().getId());
		guidMemberRw.setLoginName(user.getLoginName());
		guidMemberRw.setUpdateTime(new Date());
		guidMemberRw.setMemberName(user.getName());

		if (findGuidMemberRwReturn == null) {
			guidMemberRw.setCreateTime(new Date());
			guidMemberRwService.addGuidMemberRw(guidMemberRw);
		} else {
			guidMemberRw.setHasUpdateAll(true); // 修改全部值
			guidMemberRw.setCreateTime(findGuidMemberRwReturn.getCreateTime());
			guidMemberRwService.updateGuidMemberRw(guidMemberRw);
		}
		
		RwUserBeansDto beans = rwUserBeansService.findRwUserBeans(user.getId());
		if (beans == null) {

			Date now = new Date();

			RwUserBeansDto upBeans = new RwUserBeansDto();
			upBeans.setCode(user.getId());
			upBeans.setMemberNo(user.getId());
			upBeans.setMemberName(user.getName());
			upBeans.setMerchantNo(user.getOffice().getId());
			upBeans.setMerchantName(user.getOffice().getName());
			upBeans.setBeansSum(0);
			upBeans.setBeansNormal(0);
			upBeans.setBeansFreeze(0);
			upBeans.setBeansUse(0);
			upBeans.setCreateId(user.getId());
			upBeans.setCreateName(user.getName());
			upBeans.setUpdateId(user.getId());
			upBeans.setUpdateName(user.getName());
			upBeans.setCreateDate(now);
			upBeans.setUpdateDate(now);
			rwUserBeansService.addRwUserBeans(upBeans);
		}
	}

	/**
	 * 同步导购助手
	 * 
	 * @param user
	 */
	private void synGmAssistantShop(User user) {
		// 如果是上级机构，自动同步下级所有的导购助手
		if (user.getOffice() != null && StringUtils.isNotBlank(user.getOffice().getId())) {
			String loginNames = userService.findLoginNameByParentOfficeId("%" + user.getOffice().getId() + "%");
			if (StringUtils.isNotBlank(loginNames)) {
				String[] lNames = loginNames.split(",");
				FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
				findGmAssistantShop.setLoginNames(lNames);
				List<FindGmAssistantShopReturn> assistantShopReturns= gmAssistantShopService.findListGroupByNoWx(findGmAssistantShop);
				//查询现有导购助手列表
				String noWxs = gmAssistantShopService.findGroupConcatByAssNo(user.getId());
				for (FindGmAssistantShopReturn findGmAssistantShopReturn : assistantShopReturns) {
					if (noWxs == null || !noWxs.contains(findGmAssistantShopReturn.getNoWx())) {
						AddGmAssistantShop addGmAssistantShop = new AddGmAssistantShop();
						// 添加下属导购助手
						try {
							addGmAssistantShop.setCode(null);
							addGmAssistantShop.setAssistantNo(user.getId());
							addGmAssistantShop.setAssistantName(user.getName());
							addGmAssistantShop.setLoginName(user.getLoginName());
							addGmAssistantShop.setCreateId(UserUtils.getUser().getName());
							addGmAssistantShop.setSource("下属微信");
							addGmAssistantShop.setMerchantNo(findGmAssistantShopReturn.getMerchantNo());
							addGmAssistantShop.setMerchantName(findGmAssistantShopReturn.getMerchantName());
							addGmAssistantShop.setRemark(findGmAssistantShopReturn.getRemark());
							addGmAssistantShop.setRemark2(findGmAssistantShopReturn.getRemark2());
							addGmAssistantShop.setRemark3(findGmAssistantShopReturn.getRemark3());
							addGmAssistantShop.setRemark4(findGmAssistantShopReturn.getRemark4());
							addGmAssistantShop.setTidCode(findGmAssistantShopReturn.getTidCode());
							addGmAssistantShop.setTerminalCode(findGmAssistantShopReturn.getTerminalCode());
							addGmAssistantShop.setNoWx(findGmAssistantShopReturn.getNoWx());
							addGmAssistantShop.setWxNickname(findGmAssistantShopReturn.getWxNickname());
							gmAssistantShopService.addGmAssistantShop(addGmAssistantShop);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("复制导购助手错误 e={}", e);
						}

					}
				}
			}
		}
	}

	/**
	 * @param user
	 * @param pwd
	 * @param status
	 * @throws TsfaServiceException
	 */
	private void updateGuid(User user, String pwd, String status, AddGuidMember guidMemberParam) throws TsfaServiceException {
		UpdateGuidMember updateGuidMember = new UpdateGuidMember();
		updateGuidMember.setMemberNo(user.getId());
		updateGuidMember.setMemberName(user.getName());
		updateGuidMember.setStatus(status);
		updateGuidMember.setJobNum(user.getNo());
		updateGuidMember.setMobile(user.getMobile());
		updateGuidMember.setLoginName(user.getLoginName());
		updateGuidMember.setHeadAddress(user.getPhoto());
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			updateGuidMember.setPwd(MD5.encryptByMD5(pwd));
		}
		updateGuidMember.setUpdateId(UserUtils.getUser().getName());
		
		if (guidMemberParam != null) {
			updateGuidMember.setAddress(guidMemberParam.getAddress());
			updateGuidMember.setNoWx(guidMemberParam.getNoWx());
			updateGuidMember.setGender(guidMemberParam.getGender());
		}
		
		guidMemberService.updateGuidMember(updateGuidMember);

		/**
		 * 登录名变更，同步导购助手
		 */
		if (StringUtils.isNotBlank(user.getOldLoginName()) && StringUtils.isNotBlank(user.getLoginName()) && !user.getOldLoginName().equals(user.getLoginName())) {
			gmAssistantShopService.synByLoginName(user.getOldLoginName(), user.getLoginName());
		}
	}

	private void addGuid(User user, String pwd, String status, AddGuidMember guidMemberParam) {
		AddGuidMember addGuidMember = new AddGuidMember();
		addGuidMember.setMemberNo(user.getId());
		addGuidMember.setMemberName(user.getName());
		addGuidMember.setMerchantNo(user.getCompany().getId());
		addGuidMember.setMerchantName(user.getCompany().getName());
		addGuidMember.setLoginName(user.getLoginName());
		addGuidMember.setStatus(status);
		addGuidMember.setJobNum(user.getNo());
		addGuidMember.setMobile(user.getMobile());
		addGuidMember.setPwd(MD5.encryptByMD5(pwd));
		addGuidMember.setHeadAddress(user.getPhoto());
		addGuidMember.setCreateId(UserUtils.getUser().getName());
		
		addGuidMember.setAddress(guidMemberParam.getAddress());
		addGuidMember.setNoWx(guidMemberParam.getNoWx());
		addGuidMember.setGender(guidMemberParam.getGender());
		
		guidMemberService.addGuidMember(addGuidMember);
	}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		if (UserUtils.getUser().getId().equals(user.getId())) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		} else if (User.isAdmin(user.getId())) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		} else {
			//如果被删除的用户有认领的群或客户,不允许删除
			List<AddFriends> memberList = addFriendsService.findClaimMemberList(user.getId());
			FindChatRoomPage findChatRoomPage = new FindChatRoomPage();
			findChatRoomPage.setMemberNoGm(user.getId());
			findChatRoomPage.setMerchantNo(user.getCompany().getId());
			List<FindChatRoomPageReturn> chatRoomList = chatRoomService.findChatRooms(findChatRoomPage );
			if(!memberList.isEmpty() || !chatRoomList.isEmpty()){
				addMessage(redirectAttributes, "删除用户失败, 该用户还有已认领的客户和群，请先将已认领客户和群转移给其他员工。");
			}else{
				systemService.deleteUser(user);
				addMessage(redirectAttributes, "删除用户成功");
			}
		}
		return REDIRECT_LIST;
	}

	/**
	 * 验证登录名是否有效
	 * 
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName != null && loginName.equals(oldLoginName)) {
			return TRUE;
		} else if (loginName != null && systemService.getUserByLoginName(loginName) == null) {
			return TRUE;
		}
		return FALSE;
	}

	/**
	 * 用户信息显示及保存
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())) {
//			currentUser.setEmail(user.getEmail());
//			currentUser.setPhone(user.getPhone());
//			currentUser.setMobile(user.getMobile());
//			currentUser.setRemarks(user.getRemarks());
			currentUser.setPhoto(user.getPhoto());
			systemService.updateUserInfo(currentUser);

			updateGuid(currentUser, null, MemberStatus.NORMAL.toString(), null);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/userInfo";
	}

	/**
	 * 返回用户信息
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}

	/**
	 * 修改个人用户密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {
			if (SystemService.validatePassword(oldPassword, user.getPassword())) {
				systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			} else {
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> list = systemService.findUserByOfficeId(officeId);
		for (int i = 0; i < list.size(); i++) {
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_" + e.getId());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}

		return mapList;
	}

	/*@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "shopNos")
	public List<FindShopReturn> getShopNos(String merchantNo, String userType) {
		User user = new User();
		Office company = new Office();
		company.setId(merchantNo);
		user.setCompany(company);
		user.setUserType(userType);

	/**
	 * 方法说明：找店。
	 * 
	 * @param shops
	 * @param shopNo
	 *
	 */
}
