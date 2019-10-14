/**
 * 
 */
package com.lj.business.api.controller.hx;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.oms.entity.dto.hx.HxGuidDto;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.UserHessianService;
import com.ye.business.hx.dto.FindGuidSchedulePage;
import com.ye.business.hx.dto.GuidScheduleCycleDto;
import com.ye.business.hx.dto.GuidScheduleDto;
import com.ye.business.hx.dto.GuidScheduleLogDto;
import com.ye.business.hx.emus.AptType;
import com.ye.business.hx.emus.ScheduleType;
import com.ye.business.hx.service.IGuidScheduleLogService;
import com.ye.business.hx.service.IGuidScheduleService;

/**
 * 
 * 
 * 类说明：员工排班。
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月11日
 */
@Controller
@RequestMapping(value = "/hx/guid/schedule/")
public class GuidScheduleAction extends Action{
	private static Logger  LOG  = LoggerFactory.getLogger(GuidScheduleAction.class);
	/**排班周期（天数） */
	private static final int CYCLE_NUM=7;
	
	@Autowired
	private IGuidScheduleService guidScheduleService;
	
	@Resource
	private IGuidMemberService guidMemberService; // 导购服务
	
	@Autowired
	private UserHessianService userHessianService;

	
	@Autowired
	private IGuidScheduleLogService guidScheduleLogService;

	
	/**
	 * 新增排班
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "add.do" })
	@ResponseBody
	public GuidScheduleDto addGuidSchedule(GuidScheduleDto guidScheduleDto){
		//1.新增
		GuidScheduleDto rtDto = checkAndAddGuidSchedule(guidScheduleDto);
		guidScheduleDto.setCode(rtDto.getCode());
		return guidScheduleDto;
	}
	
	/**
	 * 新增单个排班信息。
	 * @param guidScheduleDto
	 * @return
	 */
	private GuidScheduleDto checkAndAddGuidSchedule(GuidScheduleDto guidScheduleDto) {
		AssertUtils.notNullAndEmpty(guidScheduleDto.getType(),"班次类型不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getScheduleCode(),"班次code不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getDayNum(),"班次日期不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getMemberNoGuid(),"员工编号不能为空！");
		
		User user = userHessianService.findByUserId(guidScheduleDto.getMemberNoGuid());
		guidScheduleDto.setMemberNameGuid(user.getName());
//		guidScheduleDto.setShopNo(user.getCompany().getId());//商户即门诊
//		guidScheduleDto.setShopName(user.getCompany().getName());//商户即门诊
		guidScheduleDto.setMerchantNo(user.getCompany().getId());
		guidScheduleDto.setMerchantName(user.getCompany().getName());
		
		GuidScheduleDto rtDto=guidScheduleService.addGuidSchedule(guidScheduleDto);
		return rtDto;
	}
	
	/**
	 *  删除排班
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "remove.do" })
	@ResponseBody
	public String removeShopSchedule(GuidScheduleDto guidScheduleDto) {
		AssertUtils.notNullAndEmpty(guidScheduleDto.getCode(),"排班code不能为空！");
	 
		GuidScheduleDto updateDto=new GuidScheduleDto();
		updateDto.setCode(guidScheduleDto.getCode());
		
		guidScheduleService.deleteGuidSchedule(updateDto);
		return updateDto.getCode();
	}
	
	/**
	 *  修改排班.
	 *  <br/>当dayNum=null&&scheduleCodes=null,则代表删除员工的某类排班。
	 *  <br/>当dayNum!=null&&scheduleCodes=null,则代表删除员工某类某天的排班。
	 *  <br/>当dayNum!=null&&scheduleCodes!=null,则代表修改员工某类某天的排班。
	 * @param shopScheduleDto 
	 *<br/>memberNoGuid 员工编号 非空
	 *<br/>type 类型 非空
	 *<br/>dayNum 周几 
	 *<br/>scheduleCodes 班次，多个则英文逗号分割 
	 * @return
	 */
	@RequestMapping(value = { "edit.do" })
	@ResponseBody
	public Map<String, String> updateSchedule(GuidScheduleDto guidScheduleDto){
		AssertUtils.notNullAndEmpty(guidScheduleDto.getType(),"班次类型不能为空！");
//		AssertUtils.notNullAndEmpty(guidScheduleDto.getDayNum(),"班次日期不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getMemberNoGuid(),"员工编号不能为空！");
		
		
		User user = userHessianService.findByUserId(guidScheduleDto.getMemberNoGuid());
		guidScheduleDto.setMemberNameGuid(user.getName());
//		guidScheduleDto.setShopNo(user.getCompany().getId());//商户即门诊
//		guidScheduleDto.setShopName(user.getCompany().getName());//商户即门诊
		guidScheduleDto.setMerchantNo(user.getCompany().getId());
		guidScheduleDto.setMerchantName(user.getCompany().getName());
		return guidScheduleService.upadteGuidScheduleByTypeAndDayNum(guidScheduleDto);
	}
	
	
	
	/**
	 * 新增排班。
	 * <br/>批量新增员工的整周排班。
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "addBatch.do" })
	@ResponseBody
	public String addGuidScheduleBatch(GuidScheduleDto guidScheduleDto){
		AssertUtils.notNullAndEmpty(guidScheduleDto.getType(),"班次类型不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getScheduleJson(),"班次信息不能为空！");
		AssertUtils.notNullAndEmpty(guidScheduleDto.getMemberNoGuid(),"员工编号不能为空！");
		
		User user = userHessianService.findByUserId(guidScheduleDto.getMemberNoGuid());
		guidScheduleDto.setMemberNameGuid(user.getName());
//		guidScheduleDto.setShopNo(user.getCompany().getId());//商户即门诊
//		guidScheduleDto.setShopName(user.getCompany().getName());//商户即门诊
		guidScheduleDto.setMerchantNo(user.getCompany().getId());
		guidScheduleDto.setMerchantName(user.getCompany().getName());
		
		//1.批量新增
		guidScheduleService.addGuidScheduleBatch(guidScheduleDto);
		return guidScheduleDto.getMemberNoGuid();
	}
	
	
	/**
	 *按员工分组分页查找员工的排班信息。
	 * @param param
	 * @param findShopSchedulePage
	 * @return
	 */
	@RequestMapping(value = { "list.do" })
	@ResponseBody
	public Page<GuidScheduleCycleDto> findGuidSchedulePage(GuidScheduleDto param,
			FindGuidSchedulePage findGuidSchedulePage) {
		AssertUtils.notNullAndEmpty(param,"门诊号不能为空！");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户编号不能为空！");
//		AssertUtils.notNullAndEmpty(param.getShopNo(),"门诊号不能为空！");
		AssertUtils.notNullAndEmpty(param.getRoleEnname(),"员工角色不能为空！");
		AssertUtils.notNullAndEmpty(param.getType(),"排班类型不能为空！");
		findGuidSchedulePage.setParam(param);
		
		//1.按角色，先从OMS按角色查出所有用户
		HxGuidDto guidDto = new HxGuidDto();
		guidDto.setMerchantNo(param.getMerchantNo());
		guidDto.setRoleEnname(param.getRoleEnname());
		List<HxGuidDto> users = userHessianService.findUsersByRoleEnname(guidDto);
		//2.查出指定页数的前limit个人
		List<String> memberNoGms = new ArrayList<>();
		if(users!=null && users.size()>0) {
			for (Iterator<HxGuidDto> it = users.iterator(); it.hasNext();) {
				HxGuidDto guid = it.next();
				memberNoGms.add(guid.getMemberNo());
			}
		}else {//无该角色的用户则无排班
			return new Page<GuidScheduleCycleDto>(null, 0, findGuidSchedulePage);
		}
		param.setMemberNoGuids(memberNoGms);//查指定范围内的用户
		Page<GuidScheduleDto> pages= guidScheduleService.findGuidSchedulePageGroupByGuid(findGuidSchedulePage);
		
		//3.用户范围再缩减成分页取出的这些人
		if(pages.getTotal()>0) {
			memberNoGms = new ArrayList<>();
			for (Iterator<GuidScheduleDto> it = pages.getRows().iterator(); it.hasNext();) {
				GuidScheduleDto guid = it.next();
				memberNoGms.add(guid.getMemberNoGuid());
			}
		}else {//无排班用户则无排班
			return new Page<GuidScheduleCycleDto>(null, 0, findGuidSchedulePage);
		}
		param.setMemberNoGuids(memberNoGms);//查指定用户的排班
		//4.把个人的所有班次查出来
		List<GuidScheduleCycleDto> datas = guidScheduleService.findGuidScheduleByGm(param);
		Page<GuidScheduleCycleDto> returnPage = new Page<GuidScheduleCycleDto>(datas, pages.getTotal(), findGuidSchedulePage);
		return returnPage;
	}
	
	
	/**
	 *按员工分组分页查找员工的历史排班信息。 
	 * @param param 角色，开始日期，结束日期，门诊都不可为空
	 * @param findShopSchedulePage
	 * @return
	 */
	@RequestMapping(value = { "loglist.do" })
	@ResponseBody
	public Page<GuidScheduleCycleDto> findGuidScheduleLogPage(GuidScheduleDto param,
			FindGuidSchedulePage findGuidSchedulePage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户编号不能为空！");
//		AssertUtils.notNullAndEmpty(param.getShopNo(),"门诊号不能为空！");
		AssertUtils.notNullAndEmpty(param.getRoleEnname(),"员工角色不能为空！");
		AssertUtils.notNullAndEmpty(param.getStartDateStr(),"开始日期不能为空！");
		AssertUtils.notNullAndEmpty(param.getEndDateStr(),"结束日期不能为空！");
		findGuidSchedulePage.setParam(param);

		Date startDate=DateUtils.parseDate(param.getStartDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
		if (startDate == null) {
			throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "开始日期格式异常！");
		}
		Date endDate=DateUtils.parseDate(param.getEndDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
		if (endDate == null) {
			throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "结束日期格式异常！");
		}
		param.setStartDate(startDate);
		param.setEndDate(endDate);
		
		
		//1.按角色，先从OMS按角色查出所有用户
		HxGuidDto guidDto = new HxGuidDto();
		guidDto.setMerchantNo(param.getMerchantNo());
		guidDto.setRoleEnname(param.getRoleEnname());
		List<HxGuidDto> users = userHessianService.findUsersByRoleEnname(guidDto);
		//2.查出指定页数的前limit个人
		List<String> memberNoGms = new ArrayList<>();
		if(users!=null && users.size()>0) {
			for (Iterator<HxGuidDto> it = users.iterator(); it.hasNext();) {
				HxGuidDto guid = it.next();
				memberNoGms.add(guid.getMemberNo());
			}
		}else {//无该角色的用户则无排班
			return new Page<GuidScheduleCycleDto>(null, 0, findGuidSchedulePage);
		}
		param.setMemberNoGuids(memberNoGms);//查指定范围内的用户
		Page<GuidScheduleLogDto> pages= guidScheduleLogService.findGuidScheduleLogPageGroupByGuid(findGuidSchedulePage);
		
		//3.用户范围再缩减成分页取出的这些人
		if(pages.getTotal()>0) {
			memberNoGms = new ArrayList<>();
			for (Iterator<GuidScheduleLogDto> it = pages.getRows().iterator(); it.hasNext();) {
				GuidScheduleLogDto guid = it.next();
				memberNoGms.add(guid.getMemberNoGuid());
			}
		}else {//无排班用户则无排班
			return new Page<GuidScheduleCycleDto>(null, 0, findGuidSchedulePage);
		}
		param.setMemberNoGuids(memberNoGms);//查指定用户的排班
		//4.把个人的所有班次查出来
		List<GuidScheduleCycleDto> datas = guidScheduleLogService.findGuidScheduleLogByGm(param);
		Page<GuidScheduleCycleDto> returnPage = new Page<GuidScheduleCycleDto>(datas, pages.getTotal(), findGuidSchedulePage);
		return returnPage;
	}
	
	
	/**
	 * 根据预约时间及角色查找员工。
	 * @param param
	 * @return
	 */
	@RequestMapping(value = { "enable.do" })
	@ResponseBody
	public List<GuidScheduleCycleDto> findEnableGuidSchedule(GuidScheduleDto param) {
//		AssertUtils.notNullAndEmpty(param.getShopNo(),"门诊编号不能为空！");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户编号不能为空！");
		AssertUtils.notNullAndEmpty(param.getRoleEnname(),"员工角色不能为空！");
//		AssertUtils.notNullAndEmpty(param.getWorkTimeStr(),"预约开始时间不能为空！");
//		AssertUtils.notNullAndEmpty(param.getOffTimeStr(),"预约结束时间不能为空！");
		AssertUtils.notNullAndEmpty(param.getWorkDateStr(),"预约日期不能为空！");
		
		//1.查出指定角色的用户
		HxGuidDto guidDto = new HxGuidDto();
		guidDto.setMemberNo(param.getMerchantNo());
		guidDto.setRoleEnname(param.getRoleEnname());
		List<HxGuidDto> users = userHessianService.findUsersByRoleEnname(guidDto);
		List<String> memberNoGms = new ArrayList<>();
		if(users!=null && users.size()>0) {
			for (Iterator<HxGuidDto> it = users.iterator(); it.hasNext();) {
				HxGuidDto guid = it.next();
				memberNoGms.add(guid.getMemberNo());
			}
		}else {//无该角色的用户则无员工返回null
			return new ArrayList<>();
		}
		
		//2.查出该组用户指定时间可预约用户
		//2.1计算预约时间是当周内，则从当周排班，非当周内则是固定排班内匹配
		Date workDate=DateUtils.parseDate(param.getWorkDateStr(),DateUtils.PATTERN_yyyy_MM_dd,null);
		if(workDate==null) {
			throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "预约日期格式异常！");
		}
		
		Date workTime = null;
		Date offTime = null;
		if(StringUtils.isNotEmpty(param.getWorkTimeStr())) {
			workTime=DateUtils.parseDate(param.getWorkTimeStr(),DateUtils.PATTERN_HH_mm_ss,null);
			if(workTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "预约开始时间格式异常！");
			}
		}
		if(StringUtils.isNotEmpty(param.getOffTimeStr())) {
			offTime=DateUtils.parseDate(param.getOffTimeStr(),DateUtils.PATTERN_HH_mm_ss,null);
			if(offTime==null) {
				throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "预约结束时间格式异常！");
			}
		}
		
		String type = null;//预约时间是当周还是固定排班内
		Date now=new Date();
		int dayNum = DateUtils.getWeekDay(workDate);//预约的周几
		int diffNum=DateUtils.differentDays(now, workDate);//day2-day1
		if (diffNum < 0) {
			throw new TsfaServiceException(ErrorCode.PARAM_ERROR, "预约时间小于当前时间！");
		}
		int nowDayNum=DateUtils.getWeekDay(now);//现在周几
		if ((nowDayNum + diffNum) <= CYCLE_NUM) {
			type = ScheduleType.WEEK.toString();
		}else {
			type = ScheduleType.FIXED.toString();
		}
		LOG.info("nowDayNum:"+nowDayNum+",diffNum:"+diffNum);
		param.setDayNum(dayNum);
		param.setType(type);
		param.setMemberNoGuids(memberNoGms);
		param.setAptFlag(AptType.Y.toString());
		param.setWorkTime(workTime);
		param.setOffTime(offTime);
		
		List<GuidScheduleCycleDto> datas = guidScheduleService.findEnableGuidSchedule(param);
		return datas;
	}
	
	 
}
