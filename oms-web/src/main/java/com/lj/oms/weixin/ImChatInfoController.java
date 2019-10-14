package com.lj.oms.weixin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.DateUtils;
import com.ape.common.utils.StringUtils;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.business.member.dto.FindGuidMemberPage;
import com.lj.business.member.dto.FindGuidMemberPageReturn;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.weixin.dto.imchat.FindImChatInfoPage;
import com.lj.business.weixin.dto.imchat.FindImChatInfoPageReturn;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.oms.utils.JsonPage;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：IM聊天记录Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年11月06日
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/imChatInfo")
public class ImChatInfoController  extends BaseController{
	
	@Resource
	private IImChatInfoService imChatInfoService;
	@Resource
	private IGuidMemberService guidMemberService;
	@Resource
	private IPersonMemberBaseService personMemberBaseService;
	@Resource
	private IPersonMemberService personMemberService;
	
	/**
	 * 
	 *
	 * 方法说明：IM聊天记录列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月06日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Model model,Integer pageNo,Integer pageSize,String talker,String memberNoGm,String startTime,String endTime) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date start=StringUtils.isBlank(startTime)?null:sdf.parse(startTime);
			Date end=StringUtils.isBlank(endTime)?null:sdf.parse(endTime);
			
			Map<String,Object> parmMap = new HashMap<String,Object>();
			parmMap.put("memberNoGm", memberNoGm);
			parmMap.put("talker", talker);
			parmMap.put("startTime", com.lj.base.core.util.DateUtils.getDateByFirstSeconds(start));
			parmMap.put("endTime", com.lj.base.core.util.DateUtils.getDateByLastSeconds(end));
			parmMap.put("limit",pageSize!=null?pageSize:10);
			parmMap.put("start",pageNo!=null?(pageNo-1)*pageSize:0);
			
			Page<Map<String,Object>> pageDto= imChatInfoService.findImChatInfoPageOMS(parmMap);
			List<Map<String,Object>> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
		    if(StringUtils.isEmpty(talker)){
		    	list = Lists.newArrayList();
		    }
			
		  	com.ape.common.persistence.Page<Map<String,Object>> page=new com.ape.common.persistence.Page<Map<String,Object>>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
		  	page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("parmMap",parmMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/weixin/imChatInfoList";
	}
	
	/**
	 *
	 * 方法说明：导购与客户沟通次数统计(单个导购与几个客户沟通)
	 *
	 * @param model
	 * @param memberNamegm 导购姓名
	 * @param shopName 终端名称
	 * @param startTime 聊天开始日期
	 * @param endTime 聊天结束日期
	 * @return
	 *
	 * @author 李端强 CreateDate: 2017年12月12日
	 *
	 */
	@RequestMapping(value = {"listStatistics"})
	@ResponseBody
	public List<Map<String, Object>> listStatistics(Model model,String memberNamegm,String shopName,Date startTime,Date endTime) {
		try {
			String merchantNo = UserUtils.getUser().getCompany().getId();//商户编码
			Map<String,Object> parmMap = new HashMap<String,Object>();
			parmMap.put("merchantNo", merchantNo);//导购姓名
			parmMap.put("memberNamegm", memberNamegm);//导购姓名
			parmMap.put("shopName", shopName);//终端名称
			if(startTime!=null) {
				parmMap.put("startTime", com.lj.base.core.util.DateUtils.formatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
			}
			if(endTime!=null) {
				parmMap.put("endTime", com.lj.base.core.util.DateUtils.formatDate(new Date(endTime.getTime()+(23*3600+59*60+59)*1000), "yyyy-MM-dd HH:mm:ss"));
			}
			parmMap.put("limit",7);
			parmMap.put("start",0);
			List<String> memberNos = personMemberService.findIntentionMemberNo(merchantNo);//查询该商户下的所有意向用户
			parmMap.put("memberNos", memberNos);
			Page<Map<String,Object>> pageDto= imChatInfoService.findImChatStatisticsOMS(parmMap);
			List<Map<String,Object>> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			return list;
			
		} catch (Exception e) {
			logger.error("listStatistics error:",e);
		}
		return new ArrayList<Map<String, Object>>();
	}
	
	/**
	 * 
	 *
	 * 方法说明：IM聊天记录详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月06日
	 *
	 */
	@RequestMapping(value = {"view"})
	@ResponseBody
	public JsonPage<FindImChatInfoPageReturn> list(Model model,Integer pageNo,Integer pageSize,FindImChatInfoPage findImChatInfoPage) {
		try {
			if(pageNo!=null){
				findImChatInfoPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findImChatInfoPage.setLimit(pageSize);
			}
			findImChatInfoPage.setSortDir(PageSortType.desc);
//			findImChatInfoPage.setChatTimeEnd(DateUtils.addDays(findImChatInfoPage.getChatTimeBegin(), 1));
			Page<FindImChatInfoPageReturn> pageDto= imChatInfoService.findImChatInfoPage(findImChatInfoPage);
			List<FindImChatInfoPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			for (FindImChatInfoPageReturn findImChatInfoPageReturn : list) {
				//获取导购头像
				FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
				findGuidMemberPage.setMemberNo(findImChatInfoPageReturn.getMemberNoGm());
				List<FindGuidMemberPageReturn> guidMember= guidMemberService.findGuidMembers(findGuidMemberPage);
				if(guidMember!=null && guidMember.size()>0){
					findImChatInfoPageReturn.setGmPhoto(guidMember.get(0).getHeadAddress());
				}
				//获取客户头像和昵称
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setNoWx(findImChatInfoPageReturn.getNoWx());
				FindPersonMemberBaseReturn member=  personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
				if(member!=null){
					findImChatInfoPageReturn.setPmNickName(member.getNickNameWx());
					findImChatInfoPageReturn.setPmPhoto(member.getHeadAddress());
				}
			}
		  	com.lj.oms.utils.JsonPage<FindImChatInfoPageReturn> page=new com.lj.oms.utils.JsonPage<FindImChatInfoPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
		  	page.initialize();
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonPage<FindImChatInfoPageReturn>();
	}
}
