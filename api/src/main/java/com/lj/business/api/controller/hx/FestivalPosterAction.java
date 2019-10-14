/**
 * 
 */
package com.lj.business.api.controller.hx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.api.controller.Action;
import com.ye.business.hx.dto.FestivalPosterDto;
import com.ye.business.hx.dto.FindFestivalPosterPage;
import com.ye.business.hx.dto.FindShopFestivalPosterPage;
import com.ye.business.hx.dto.ShopFestivalPosterDto;
import com.ye.business.hx.service.IFestivalPosterService;
import com.ye.business.hx.service.IShopFestivalPosterService;

/**
 * 
 * 
 * 类说明：节日海报
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年7月19日
 */
@Controller
@RequestMapping(value = "/hx/fp/")
public class FestivalPosterAction extends Action {

	@Autowired
	private IFestivalPosterService festivalPosterService;

	@Autowired
	private IShopFestivalPosterService shopFestivalPosterService;

	/**
	 * 模板列表
	 * 
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "list.do" })
	@ResponseBody
	public List<FestivalPosterDto> findFpPage() {
		//1.查出模板
		FindFestivalPosterPage findPage =new FindFestivalPosterPage();
		List<FestivalPosterDto> pages = festivalPosterService.findFestivalPosters(findPage);
		return pages;
	}

	/**
	 * 新增门诊海报
	 * 
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "shopfp/add.do" })
	@ResponseBody
	public ShopFestivalPosterDto addShopFestivalPoster(ShopFestivalPosterDto param) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户编号不能为空！");
		AssertUtils.notNullAndEmpty(param.getMerchantName(),"商户名称不能为空！");
		AssertUtils.notNullAndEmpty(param.getTemplateImg(),"模板海报不能为空！");
		AssertUtils.notNullAndEmpty(param.getQcordImg(),"门诊海报不能为空！");
		AssertUtils.notNullAndEmpty(param.getFpCode(),"模板code不能为空！");
		AssertUtils.notNullAndEmpty(param.getTypeName(),"节日名称不能为空！");
		AssertUtils.notNullAndEmpty(param.getShopWx(),"门诊微信不能为空！");
		
		ShopFestivalPosterDto rt = shopFestivalPosterService.addShopFestivalPoster(param);
		return rt;
	}

	/**
	 * 门诊节日海报
	 * 
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "shopfp/list.do" })
	@ResponseBody
	public List<ShopFestivalPosterDto> shopFestivalPoster(ShopFestivalPosterDto param) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户编号不能为空！"); 
		AssertUtils.notNullAndEmpty(param.getFpCode(),"模板code不能为空！");
		AssertUtils.notNullAndEmpty(param.getShopWx(),"门诊微信不能为空！");
		FindShopFestivalPosterPage findPage=new FindShopFestivalPosterPage();
		findPage.setParam(param);
		List<ShopFestivalPosterDto> rt = shopFestivalPosterService.findShopFestivalPostersByTemplateImg(findPage);
		return rt;
	}
}
