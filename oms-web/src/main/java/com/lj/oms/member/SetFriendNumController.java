package com.lj.oms.member;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.FindSetFriendNumPage;
import com.lj.business.member.dto.SetFriendNumDto;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.service.ISetFriendNumService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.contacts.PushNotifyMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;

/**
 * 设置好友数
 * @author wo510
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/setFriendNum")
public class SetFriendNumController  extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(SetFriendNumController.class);
	
	/** 设置好友数列表页面 **/
	private final static String PAGE_VIEW_LINE_LIST = "modules/member/setFriendNum/list";
	/** 设置好友数编辑页面 **/
	private final static String PAGE_VIEW_LINE_FORM = "modules/member/setFriendNum/form";
	/** 重定向到设置好友数列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_LINE = "redirect:" + Global.getAdminPath() + "/setFriendNum";

	@Resource
	private ISetFriendNumService setFriendNumService;
	@Autowired 
	private IShopTerminalService shopTerminalService;
	@Autowired 
	private ICommonService commonService;

	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, SetFriendNumDto setFriendNumDto) {
		try {
			FindSetFriendNumPage findSetFriendNumPage = new FindSetFriendNumPage();
			if (pageNo != null) {
				findSetFriendNumPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findSetFriendNumPage.setLimit(pageSize);
			}
			
			setFriendNumDto.setMerchantNo(UserUtils.getMerchantNo());
			findSetFriendNumPage.setParam(setFriendNumDto);
			Page<SetFriendNumDto> pages = setFriendNumService.findSetFriendNumPage(findSetFriendNumPage);
			List<SetFriendNumDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<SetFriendNumDto> page = new com.ape.common.persistence.Page<SetFriendNumDto>(pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findSetFriendNumPage", findSetFriendNumPage);
		} catch (Exception e) {
			logger.error("查询设置好友数信息错误！", e);
		}
		return PAGE_VIEW_LINE_LIST;
	}

	@RequestMapping(value = "form")
	public String form(Model model, SetFriendNumDto setFriendNumDto) {
		try {
			if (setFriendNumDto != null && setFriendNumDto.getCode() != null) {
				
					SetFriendNumDto lineDto = setFriendNumService.findSetFriendNum(setFriendNumDto);
					model.addAttribute("data", lineDto);
				
			}
			FindShopTidFromWeb findShopTidFromWeb = new FindShopTidFromWeb();
			findShopTidFromWeb.setMerchantNo(UserUtils.getMerchantNo());
	    	findShopTidFromWeb.setAssistantNo(UserUtils.getUser().getId());
	    	findShopTidFromWeb.setQueryOnlineBool(Boolean.TRUE); // 查询终端是否在线
	        List<FindShopTidFromWebReturn> findShopTerminalFromWeb = shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
	        model.addAttribute("shopTids", findShopTerminalFromWeb);
		} catch (Exception e) {
			logger.error("查询设置好友数信息错误！", e);
		}
		return PAGE_VIEW_LINE_FORM;

	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, SetFriendNumDto setFriendNumDto,String startDateStr,String endDateStr) {
		try {
			setFriendNumDto.setMerchantNo(UserUtils.getMerchantNo());
			setFriendNumDto.setMerchantName(UserUtils.getMerchantName());
			setFriendNumDto.setCreateId(UserUtils.getUser().getName());
			Calendar calendar = Calendar.getInstance(); 
			Date date =new Date();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(startDateStr.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(startDateStr.split(":")[1]));
			
			setFriendNumDto.setStartDate(calendar.getTime());
			
			calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(endDateStr.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(endDateStr.split(":")[1]));
			setFriendNumDto.setEndDate(calendar.getTime());
			setFriendNumService.addSetFriendNum(setFriendNumDto);
			//推送
			sendToZk(setFriendNumDto.getNoWx());
			addMessage(redirectAttributes, "保存成功");
		} catch (Exception e) {
			logger.error("保存设置好友数信息错误！", e);
		}
		return PAGE_VIEW_REDIRECT_LINE;

	}

	@RequestMapping(value = "edit")
	public String edit(Model model, RedirectAttributes redirectAttributes, SetFriendNumDto setFriendNumDto,String startDateStr,String endDateStr) {
		try {
			setFriendNumDto.setUpdateId(UserUtils.getUser().getName());
			Calendar calendar = Calendar.getInstance(); 
			Date date =new Date();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(startDateStr.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(startDateStr.split(":")[1]));
			
			setFriendNumDto.setStartDate(calendar.getTime());
			
			calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(endDateStr.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(endDateStr.split(":")[1]));
			setFriendNumDto.setEndDate(calendar.getTime());
			setFriendNumService.updateSetFriendNum(setFriendNumDto);
			//推送
			sendToZk(setFriendNumDto.getNoWx());
			addMessage(redirectAttributes, "修改成功");
		} catch (Exception e) {
			logger.error("修改设置好友数信息错误！", e);
		}
		return PAGE_VIEW_REDIRECT_LINE;
	}
	
	@RequestMapping(value = "del")
	public String del(String code,String noWx,RedirectAttributes redirectAttributes) {
		try {
			if (StringUtils.isNotEmpty(code)) {
				int count = setFriendNumService.deleteByPrimaryKey(code);
				if(count >0) {
					//推送
					sendToZk(noWx);
					addMessage(redirectAttributes, "删除成功");
				}
			}
		} catch (Exception e) {
			logger.error("查询设置好友数信息错误！", e);
		}
		return PAGE_VIEW_REDIRECT_LINE;

	}
	
	/**
	 * 推送给终端
	 * @param noWx
	 */
	private void sendToZk(String noWx) {
		FindSetFriendNumPage findSetFriendNumPage = new FindSetFriendNumPage();
		SetFriendNumDto param = new SetFriendNumDto();
		param.setNoWx(noWx);
		findSetFriendNumPage.setParam(param);
		List<SetFriendNumDto> list= setFriendNumService.findSetFriendNums(findSetFriendNumPage);
		
			
		List<Map<String,Object>> pushList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map =null;
		Calendar calendar = Calendar.getInstance(); 
		for (SetFriendNumDto setFriendNumDto : list) {
			map =new HashMap<String, Object>();
			
			calendar.setTime(setFriendNumDto.getStartDate());
			map.put("startDateHours", calendar.get(Calendar.HOUR_OF_DAY));
			map.put("startDateMinutes", calendar.get(Calendar.MINUTE));
			
			calendar.setTime(setFriendNumDto.getEndDate());
			map.put("endDateHours", calendar.get(Calendar.HOUR_OF_DAY));
			map.put("endDateMinutes", calendar.get(Calendar.MINUTE));
			map.put("num", setFriendNumDto.getNum());
			pushList.add(map);
		}
			PushNotifyMessage pushNotifyMessage = new PushNotifyMessage();
			pushNotifyMessage.setCacheAccountNo(noWx);
			pushNotifyMessage.setOfflinePush(true);
			pushNotifyMessage.setTitle("好友设置");
			pushNotifyMessage.setType("30");
			String jsonStr =JSONArray.toJSONString(pushList);
			pushNotifyMessage.setContent(jsonStr);
			System.err.println(jsonStr);
			logger.info("好友设置推送：{}",pushNotifyMessage);
			IContactsService contactsService =commonService.getHessianContactsService(noWx);
			contactsService.sendPushNotifyMessage(pushNotifyMessage);
	}
	
}
