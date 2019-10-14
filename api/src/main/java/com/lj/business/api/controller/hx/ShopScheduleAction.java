/**
 * 
 */
package com.lj.business.api.controller.hx;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.business.api.controller.Action;
import com.ye.business.hx.dto.FindShopSchedulePage;
import com.ye.business.hx.dto.ShopScheduleDto;
import com.ye.business.hx.emus.DelFlag;
import com.ye.business.hx.service.IShopScheduleService;

/**
 * 
 * 
 * 类说明：门店班次设置action.
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
@RequestMapping(value = "/hx/shop/schedule/")
public class ShopScheduleAction extends Action {

	@Autowired
	private IShopScheduleService shopScheduleService;

	/**
	 * 新增班次
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "add.do" })
	@ResponseBody
	public ShopScheduleDto addShopSchedule(ShopScheduleDto shopScheduleDto) {
		AssertUtils.notNullAndEmpty(shopScheduleDto.getScheduleName(),"班次名称不能为空！");
		AssertUtils.notNullAndEmpty(shopScheduleDto.getWorkTimeStr(),"上班时间不能为空！");
		AssertUtils.notNullAndEmpty(shopScheduleDto.getOffTimeStr(),"下班时间不能为空！");
		AssertUtils.notNullAndEmpty(shopScheduleDto.getAptFlag(),"是否可约诊不能为空！");
//		AssertUtils.notNullAndEmpty(shopScheduleDto.getShopNo(),"门诊号不能为空！");
//		AssertUtils.notNullAndEmpty(shopScheduleDto.getShopName(),"门诊名称不能为空！");
		AssertUtils.notNullAndEmpty(shopScheduleDto.getMerchantNo(),"商户号不能为空！");
		AssertUtils.notNullAndEmpty(shopScheduleDto.getMerchantName(),"商户名称不能为空！");
		
		
		Date workTime=DateUtils.parseDate(shopScheduleDto.getWorkTimeStr(),DateUtils.PATTERN_HH_mm_ss,null);
		Date offTime=DateUtils.parseDate(shopScheduleDto.getOffTimeStr(),DateUtils.PATTERN_HH_mm_ss,null);
		AssertUtils.notNullAndEmpty(workTime,"上班时间格式异常！");
		AssertUtils.notNullAndEmpty(offTime,"下班时间格式异常！");
		shopScheduleDto.setWorkTime(workTime);
		shopScheduleDto.setOffTime(offTime);
		shopScheduleDto.setDelFlag(DelFlag.N.toString());
		
		ShopScheduleDto rtDto = shopScheduleService.addShopSchedule(shopScheduleDto);
		return rtDto;
	}

	/**
	 *  修改班次
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "edit.do" })
	@ResponseBody
	public String editShopSchedule(ShopScheduleDto shopScheduleDto) {
		AssertUtils.notNullAndEmpty(shopScheduleDto.getCode(),"班次code不能为空！");
		AssertUtils.notNullAndEmpty(shopScheduleDto.getScheduleName(),"班次名称不能为空！");
		AssertUtils.notNullAndEmpty(shopScheduleDto.getWorkTimeStr(),"上班时间不能为空！");
		AssertUtils.notNullAndEmpty(shopScheduleDto.getOffTimeStr(),"下班时间不能为空！");
		AssertUtils.notNullAndEmpty(shopScheduleDto.getAptFlag(),"是否可约诊不能为空！");
		
		Date workTime=DateUtils.parseDate(shopScheduleDto.getWorkTimeStr(),DateUtils.PATTERN_HH_mm_ss,null);
		Date offTime=DateUtils.parseDate(shopScheduleDto.getOffTimeStr(),DateUtils.PATTERN_HH_mm_ss,null);
		AssertUtils.notNullAndEmpty(workTime,"上班时间格式异常！");
		AssertUtils.notNullAndEmpty(offTime,"下班时间格式异常！");
		shopScheduleDto.setWorkTime(workTime);
		shopScheduleDto.setOffTime(offTime);
		
		shopScheduleService.updateShopSchedule(shopScheduleDto);
		return shopScheduleDto.getCode();
	}

	/**
	 *  删除班次
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "remove.do" })
	@ResponseBody
	public String removeShopSchedule(ShopScheduleDto shopScheduleDto) {
		AssertUtils.notNullAndEmpty(shopScheduleDto.getCode(),"班次code不能为空！");
	 
		ShopScheduleDto updateDto=new ShopScheduleDto();
		updateDto.setCode(shopScheduleDto.getCode());
		updateDto.setDelFlag(DelFlag.Y.toString());
		updateDto.setUpdateId(shopScheduleDto.getUpdateId());
		
		shopScheduleService.updateShopSchedule(updateDto);
		return updateDto.getCode();
	}
	
	/**
	 *分页查找班次
	 * @param param
	 * @param findShopSchedulePage
	 * @return
	 */
	@RequestMapping(value = { "list.do" })
	@ResponseBody
	public Page<ShopScheduleDto> findShopSchedulePage(ShopScheduleDto param,
			FindShopSchedulePage findShopSchedulePage) {
//		AssertUtils.notNullAndEmpty(param,"门诊号不能为空！");
//		AssertUtils.notNullAndEmpty(param.getShopNo(),"门诊号不能为空！");
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		
		param.setDelFlag(DelFlag.N.toString());
		findShopSchedulePage.setParam(param);
		Page<ShopScheduleDto> page = shopScheduleService.findShopSchedulePage(findShopSchedulePage);

		return page;
	}
}
