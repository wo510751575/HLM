package com.lj.oms.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.terminalimstatus.AddTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.FindTerminalBatteryLevelPage;
import com.lj.business.member.dto.terminalimstatus.FindTerminalBatteryLevelPageReturn;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatus;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPage;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusPageReturn;
import com.lj.business.member.dto.terminalimstatus.FindTerminalImStatusReturn;
import com.lj.business.member.dto.terminalimstatus.UpdateTerminalImStatus;
import com.lj.business.member.emus.TerminalType;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.service.ITerminalImStatusService;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;
/**
 * 
 * 
 * 类说明：终端IM状态Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年11月02日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/terminalImStatus")
public class TerminalImStatusController  extends BaseController{
   
	/** 终端状态列表页面 **/
	private static final String PAGE_VIEW_TERMINAL_IM_STATUS_LIST = "modules/member/terminalImStatusList";
	/** 终端状态编辑页面 **/
	private static final String PAGE_VIEW_TERMINAL_IM_STATUS_FORM = "modules/member/terminalImStatusForm";
	/** 重定向到终端状态列表页面 **/
	private static final String PAGE_VIEW_REDIRECT_TERMINAL_IM_STATUS_LIST = "redirect:" + Global.getAdminPath() + "/member/terminalImStatus/";
	/** 终端监控列表页面 **/
	private static final String PAGE_VIEW_TERMINAL_BATTERY_LEVEL = "modules/member/terminalBatteryLevel";
	/** 导购终端监控列表页面 **/
	private static final String PAGE_VIEW_TERMINAL_BATTERY_LEVEL_GM = "modules/member/terminalBatteryLevelGm";
	/** 监控中心-终端监控列表页面 **/
	private static final String PAGE_VIEW_TERMINAL_BATTERY_LEVEL_ALL = "modules/member/terminalBatteryLevelAll";
	/** 监控中心-导购终端监控列表页面 **/
	private static final String PAGE_VIEW_TERMINAL_BATTERY_LEVEL_ALL_GM = "modules/member/terminalBatteryLevelAllGm";
	
	@Resource
	private  IShopTerminalService shopTerminalService;
	@Resource
	private  ITerminalImStatusService terminalImStatusService;
	
	@Resource
	private  IAddFriendsService addFriendsService;
	
	@Resource
	public IImChatInfoService imChatInfoService;
//    @Autowired 
//	private ISystemInfoService systemInfo;
    
//    @Autowired 
//	private RedisCache redisCache;
	
    @Autowired 
	ICommonService commonService;
	/**
	 * 
	 *
	 * 方法说明：终端状态查询
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findTerminalImStatusPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	@RequiresPermissions("member:terminalImStatus:view")
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindTerminalImStatusPage findTerminalImStatusPage){
		try {
			if (pageNo != null) {
				findTerminalImStatusPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findTerminalImStatusPage.setLimit(pageSize);
			}
			findTerminalImStatusPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindTerminalImStatusPageReturn> pages = terminalImStatusService.findTerminalImStatusPage(findTerminalImStatusPage);
			List<FindTerminalImStatusPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindTerminalImStatusPageReturn> page = new com.ape.common.persistence.Page<FindTerminalImStatusPageReturn>(pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findTerminalImStatusPage", findTerminalImStatusPage);
		} catch (Exception e) {
			logger.error("终端IM状态信息获取异常！", e);
		}
		return PAGE_VIEW_TERMINAL_IM_STATUS_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：去到终端状态编辑页面
	 *
	 * @param model
	 * @param findTerminalImStatus
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	@RequiresPermissions("member:terminalImStatus:view")
	@RequestMapping(value="form")
	public String form(Model model,FindTerminalImStatus findTerminalImStatus){
		try {
			if(findTerminalImStatus!=null && findTerminalImStatus.getCode()!=null){
				FindTerminalImStatusReturn findTerminalImStatusReturn= terminalImStatusService.findTerminalImStatus(findTerminalImStatus);
				model.addAttribute("data", findTerminalImStatusReturn);
			}
		} catch (Exception e) {
			logger.error("终端IM状态信息获取异常！", e);
		}
		return PAGE_VIEW_TERMINAL_IM_STATUS_FORM;
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：新增终端状态
	 *
	 * @param addTerminalImStatus
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	@RequiresPermissions("member:terminalImStatus:edit")
	@RequestMapping(value="save")
	public String save(AddTerminalImStatus addTerminalImStatus,RedirectAttributes  redirectAttributes){
		try {
			addTerminalImStatus.setMerchantNo(UserUtils.getMerchantNo());
			addTerminalImStatus.setMerchantName(UserUtils.getUser().getCompany().getName());
			addTerminalImStatus.setCreateId(UserUtils.getUser().getCompany().getName());
			terminalImStatusService.addTerminalImStatus(addTerminalImStatus);
			addMessage(redirectAttributes, "保存终端IM状态'" + addTerminalImStatus.getImei()+ "'成功");
			
			terminalImStatusService.addTerminalImStatus(addTerminalImStatus);
		} catch (Exception e) {
			logger.error("新增终端IM状态信息异常！", e);
		}
		return PAGE_VIEW_REDIRECT_TERMINAL_IM_STATUS_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑终端状态
	 *
	 * @param redirectAttributes
	 * @param updateTerminalImStatus
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月10日
	 *
	 */
	@RequiresPermissions("member:terminalImStatus:edit")
	@RequestMapping(value="edit")
	public String edit(RedirectAttributes redirectAttributes,UpdateTerminalImStatus updateTerminalImStatus){
		try {
			terminalImStatusService.updateTerminalImStatus(updateTerminalImStatus);
			addMessage(redirectAttributes, "修改终端IM状态'" + updateTerminalImStatus.getImei()+ "'成功");
		} catch (Exception e) {
			logger.error("修改终端IM状态信息异常！", e);
		}
	   return PAGE_VIEW_REDIRECT_TERMINAL_IM_STATUS_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：终端监控-中控手机电量
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月03日
	 *
	 */
	@RequiresPermissions("member:terminalImStatus:view")
	@RequestMapping(value={"batteryLevel"})
	public String batteryLevel(Model model,Integer pageNo,Integer pageSize,FindTerminalBatteryLevelPage findTerminalBatteryLevelPage){
		try {
			if(pageNo!=null){
				findTerminalBatteryLevelPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findTerminalBatteryLevelPage.setLimit(pageSize);
			}
			findTerminalBatteryLevelPage.setMerchantNo(UserUtils.getMerchantNo());
			findTerminalBatteryLevelPage.setTerminalType(TerminalType.ZK.name());// 只显示中控客户端
			Page<FindTerminalBatteryLevelPageReturn> pages=terminalImStatusService.findTerminalBatteryLevelPage(findTerminalBatteryLevelPage);
			List<FindTerminalBatteryLevelPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindTerminalBatteryLevelPageReturn> page 
			= new com.ape.common.persistence.Page<FindTerminalBatteryLevelPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findTerminalBatteryLevelPage", findTerminalBatteryLevelPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询终端手机电量信息异常！", e);
		}
		return PAGE_VIEW_TERMINAL_BATTERY_LEVEL;
	}
	
	/**
	 * 
	 *
	 * 方法说明：终端监控-导购手机电量
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月25日
	 *
	 */
	@RequiresPermissions("member:terminalImStatus:view")
	@RequestMapping(value={"gmBatteryLevel"})
	public String gmBatteryLevel(Model model,Integer pageNo,Integer pageSize,FindTerminalBatteryLevelPage findTerminalBatteryLevelPage){
		try {
			if(pageNo!=null){
				findTerminalBatteryLevelPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findTerminalBatteryLevelPage.setLimit(pageSize);
			}
			findTerminalBatteryLevelPage.setMerchantNo(UserUtils.getMerchantNo());
			findTerminalBatteryLevelPage.setTerminalType(TerminalType.GM.name());// 只显示导购客户端
			Page<FindTerminalBatteryLevelPageReturn> pages=terminalImStatusService.findTerminalBatteryLevelPage(findTerminalBatteryLevelPage);
			List<FindTerminalBatteryLevelPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindTerminalBatteryLevelPageReturn> page 
			= new com.ape.common.persistence.Page<FindTerminalBatteryLevelPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findTerminalBatteryLevelPage", findTerminalBatteryLevelPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询导购手机电量信息异常！", e);
		}
		return PAGE_VIEW_TERMINAL_BATTERY_LEVEL_GM;
	}

	/**
	 * 
	 *
	 * 方法说明：监控中心-中控手机电量
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月25日
	 *
	 */
	@RequiresPermissions("member:terminalImStatusAll:view")
	@RequestMapping(value={"batteryLevelAll"})
	public String batteryLevelAll(Model model,Integer pageNo,Integer pageSize,FindTerminalBatteryLevelPage findTerminalBatteryLevelPage){
		try {
			if(pageNo!=null){
				findTerminalBatteryLevelPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findTerminalBatteryLevelPage.setLimit(pageSize);
			}else{
				findTerminalBatteryLevelPage.setLimit(100);
			}
			findTerminalBatteryLevelPage.setTerminalType(TerminalType.ZK.name());// 只显示中控客户端
			Page<FindTerminalBatteryLevelPageReturn> pages=terminalImStatusService.findTerminalBatteryLevelPage(findTerminalBatteryLevelPage);
			List<FindTerminalBatteryLevelPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindTerminalBatteryLevelPageReturn> page 
			= new com.ape.common.persistence.Page<FindTerminalBatteryLevelPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findTerminalBatteryLevelPage", findTerminalBatteryLevelPage);
		} catch (Exception e) {
			logger.error("查询监控中心终端手机电量信息异常！", e);
		}
		return PAGE_VIEW_TERMINAL_BATTERY_LEVEL_ALL;
	}
	
	/**
	 * 
	 *
	 * 方法说明：监控中心-导购手机电量
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年12月25日
	 *
	 */
	@RequiresPermissions("member:terminalImStatusAll:view")
	@RequestMapping(value={"gmBatteryLevelAll"})
	public String gmBatteryLevelAll(Model model,Integer pageNo,Integer pageSize,FindTerminalBatteryLevelPage findTerminalBatteryLevelPage){
		try {
			if(pageNo!=null){
				findTerminalBatteryLevelPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findTerminalBatteryLevelPage.setLimit(pageSize);
			}else{
				findTerminalBatteryLevelPage.setLimit(100);
			}
			findTerminalBatteryLevelPage.setTerminalType(TerminalType.GM.name());// 只显示导购客户端
			Page<FindTerminalBatteryLevelPageReturn> pages=terminalImStatusService.findTerminalBatteryLevelPage(findTerminalBatteryLevelPage);
			List<FindTerminalBatteryLevelPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindTerminalBatteryLevelPageReturn> page 
			= new com.ape.common.persistence.Page<FindTerminalBatteryLevelPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findTerminalBatteryLevelPage", findTerminalBatteryLevelPage);
		} catch (Exception e) {
			logger.error("查询监控中心导购手机电量信息异常！", e);
		}
		return PAGE_VIEW_TERMINAL_BATTERY_LEVEL_ALL_GM;
	}
	
	/**
	 * 
	 *
	 * 方法说明：终端终端版本号更新
	 *
	 * @param code
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月13日
	 *
	 */
	@RequestMapping(value="checkAndUpdateVersionAll")
	@ResponseBody
	public Map<String,String> checkAndUpdateVersionAll(RedirectAttributes redirectAttributes,String stCode){
		Map<String,String> map = Maps.newHashMap();
		String returnMessage = null;
		try {
			boolean b= shopTerminalService.checkAndUpdateVersion(stCode);
			if(b){
				returnMessage = "已向终端发送版本升级请求";
			}else{
				returnMessage = "当前已是最新版本";
			}
			map.put("returnMessage",returnMessage);
		} catch (TsfaServiceException e) {
			logger.error("更新中控终端版本错误",e);
			returnMessage = e.getExceptionInfo();
			map.put("returnMessage",returnMessage);
			map.put("type","error");
		} catch (Exception e) {
			logger.error("更新中控终端版本错误",e);
			addMessage(redirectAttributes, "检查升级失败");
			returnMessage ="检查升级失败";
			map.put("type","error");
			map.put("returnMessage",returnMessage);
		}
		return map;
	}
	
	/**
	 * 
	 *
	 * 方法说明：同步通讯录
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param stCode
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月15日
	 *
	 */
	@RequestMapping(value ="syncFriendsRequestAll")
	@ResponseBody
	public Map<String,String> syncFriendsRequestAll(Model model,RedirectAttributes redirectAttributes,String stCode){
		Map<String,String> map =Maps.newHashMap();
		String returnMessage = null;
		try {
			addFriendsService.syncFriendsRequest(stCode);
			returnMessage = "已向终端发送同步通讯录请求";
			map.put("returnMessage", returnMessage);
		} catch (TsfaServiceException e) {
			logger.error("同步通讯录失败",e);
			returnMessage = e.getExceptionInfo();
			map.put("returnMessage", returnMessage);
			map.put("type", "error");
		} catch (Exception e) {
			logger.error("同步通讯录失败",e);
			addMessage(redirectAttributes, "同步通讯录失败");
			returnMessage = "同步通讯录失败";
			map.put("returnMessage", returnMessage);
			map.put("type", "error");
		}
		return map;
	}
	
	/**
	 * 
	 *
	 * 方法说明：检查升级
	 *
	 * @param redirectAttributes
	 * @param stCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月13日
	 *
	 */
	@RequestMapping(value="checkAndUpdateVersion")
	@ResponseBody
	public Map<String,String> checkAndUpdateVersion(RedirectAttributes redirectAttributes,String stCode){
		Map<String,String> map =Maps.newHashMap();
		String returnMessage = null;
		try {
			boolean b= shopTerminalService.checkAndUpdateVersion(stCode);
			if(b){
				returnMessage = "已向终端发送版本升级请求";
			}else{
				returnMessage = "当前已是最新版本";
			}
			map.put("returnMessage", returnMessage);
		} catch (TsfaServiceException e) {
			logger.error("更新中控终端版本错误",e);
			returnMessage = e.getExceptionInfo();
			map.put("returnMessage", returnMessage);
			map.put("type","error");
		} catch (Exception e) {
			logger.error("更新中控终端版本错误",e);
			returnMessage = "检查升级失败";
			map.put("returnMessage", returnMessage);
			map.put("type", "error");
		}
		return map;
	}
	
	/**
	 * 
	 *
	 * 方法说明：同步通讯录
	 *
	 * @param redirectAttributes
	 * @param stCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月13日
	 *
	 */
	@RequestMapping(value ="syncFriendsRequest")
	@ResponseBody
	public Map<String,String> syncFriendsRequest(RedirectAttributes redirectAttributes,String stCode){
		Map<String,String> map = Maps.newHashMap();
		String returnMessage = null;
		try {
			addFriendsService.syncFriendsRequest(stCode);
			returnMessage = "已向终端发送同步通讯录请求";
            map.put("returnMessage", returnMessage);
		} catch (TsfaServiceException e) {
			logger.error("同步通讯录失败",e);
			addMessage(redirectAttributes, e.getExceptionInfo());
			returnMessage = e.getExceptionInfo();
			map.put("returnMessage", returnMessage);
			map.put("type","error");
		} catch (Exception e) {
			logger.error("同步通讯录失败",e);
			returnMessage = "同步通讯录失败";
			map.put("returnMessage", returnMessage);
		    map.put("type","error");
		    
		}
		return map;
	}
	
	
	/**
	 * 同步历史聊天记录
	 */
	@RequestMapping(value ="sysLastChatData" )
	@ResponseBody
	public Map<String,String> sysLastChatData(String noWxGm) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			//发送同步历史记录
			if(!commonService.getZkTerminalStatus(noWxGm)) {
				map.put("result", "false");
				map.put("message", "终端未在线");
				return map;
			}
			FindImChatInfo findImChatInfo = new FindImChatInfo();
			findImChatInfo.setNoWxGm(noWxGm);
			FindImChatInfoReturn  chat = imChatInfoService.findLastImChatInfo(findImChatInfo);
			if( chat != null && chat.getChatTime() != null) {
				
				imChatInfoService.syncNoWxGmRequest(noWxGm, String.valueOf(chat.getChatTime().getTime()));
			}
			//没有记录的情况下
			if( !(chat != null && chat.getChatTime() != null)) {
			    String chatTime = String.valueOf(System.currentTimeMillis());
				imChatInfoService.syncNoWxGmRequest(noWxGm, chatTime);
			}
			
			map.put("result", "true");
			map.put("message", "处理成功");
			return map;
		}catch(Exception e) {
			logger.error("同步历史聊天记录", e);
			map.put("result", "false");
			map.put("message", "终端未在线");
		}
		return map;
	}
	
	/**
	 * 同步微信群信息
	 * @param redirectAttributes
	 * @param stCode
	 * @return
	 */
	@RequestMapping(value ="sendSyncChatRoom")
	@ResponseBody
	public Map<String,String> sendSyncChatRoom(RedirectAttributes redirectAttributes,String noWxZk){
		Map<String,String> map = Maps.newHashMap();
		String returnMessage = null;
		try {
			// 同步微信群信息
			SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
			syncChatRoomMessage.setNoWxZk(noWxZk);
			
			IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(syncChatRoomMessage.getNoWxZk());
			basic.sendSyncChatRoom(syncChatRoomMessage);
			
			returnMessage = "已向终端发送同步微信群请求";
            map.put("returnMessage", returnMessage);
		} catch (TsfaServiceException e) {
			logger.error("同步微信群失败",e);
			addMessage(redirectAttributes, e.getExceptionInfo());
			returnMessage = e.getExceptionInfo();
			map.put("returnMessage", returnMessage);
			map.put("type","error");
		} catch (Exception e) {
			logger.error("同步微信群失败",e);
			returnMessage = "同步微信群失败";
			map.put("returnMessage", returnMessage);
		    map.put("type","error");
		    
		}
		return map;
	}
	

}
