package com.lj.oms.sys;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lj.oms.common.BaseController;
import com.lj.base.core.pagination.Page;
//import com.lj.business.mec.emus.Status;
import com.lj.business.member.dto.FindMerchant;
import com.lj.business.member.dto.FindMerchantPage;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.dto.FindMerchantReturn;
import com.lj.business.member.dto.UpdateMerchant;
import com.lj.business.member.service.IMerchantService;

/**
 * 
 * 
 * 类说明：商户试用controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 梅宏博
 *   
 * CreateDate: 2017年10月25日
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/probation")
public class MerchantProbationController extends BaseController{

	@Autowired
	private IMerchantService merchantService;
	
	/**
	 * 
	 *
	 * 方法说明：商户试用期列表
	 *
	 * @param model
	 * @param findMerchantPage
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月25日
	 *
	 */
	@RequiresPermissions("sys:probation:view")
	@RequestMapping(value = {"list", ""})
	public String list(Model model, FindMerchantPage findMerchantPage, Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (pageNo == null || pageSize == null || pageNo <= 0 || pageSize <= 0) {
				pageNo = 1;
				pageSize = 10;
			}
			
			findMerchantPage.setStart((pageNo - 1) * pageSize);
			findMerchantPage.setLimit(pageSize);
			
			Page<FindMerchantPageReturn> pageDto = merchantService.findMerchantPage(findMerchantPage);
			com.ape.common.persistence.Page<FindMerchantPageReturn> page
				= new com.ape.common.persistence.Page<>(pageNo, pageSize, pageDto.getTotal(), new ArrayList<>(pageDto.getRows()));
			page.initialize();
			model.addAttribute("page", page);
//			model.addAttribute("statuss", Status.values());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/sys/probationList";
	}
	
	/**
	 * 
	 *
	 * 方法说明：商户试用详情
	 *
	 * @param findMerchant
	 * @param model
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月25日
	 *
	 */
	@RequiresPermissions("sys:probation:view")
	@RequestMapping(value = "form")
	public String form(FindMerchant findMerchant, Model model) {
		try {
			FindMerchantReturn merchant = merchantService.findMerchant(findMerchant);
			model.addAttribute("data", merchant);
//			model.addAttribute("statuss", Status.values());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/sys/probationForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑商户试用信息
	 *
	 * @param updateMerchant
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月25日
	 *
	 */
	@RequiresPermissions("member:shop:edit")
	@RequestMapping(value = "edit")
	public String edit(UpdateMerchant updateMerchant, Model model,RedirectAttributes redirectAttributes) {
		try {
			merchantService.updateMerchant(updateMerchant);
			addMessage(redirectAttributes, "保存商户试用信息'" + updateMerchant.getMerchantName()+ "'成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:" + adminPath + "/sys/probation";
	}
}
