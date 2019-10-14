package com.lj.oms.cm;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lj.oms.common.BaseController;
import com.lj.oms.service.sys.OfficeService;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.business.cm.dto.merchantParams.AddMerchantParams;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsPage;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsPageReturn;
import com.lj.business.cm.dto.merchantParams.FindMerchantParamsReturn;
import com.lj.business.cm.dto.merchantParams.UpdateMerchantParams;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.member.dto.FindMerchantPageReturn;
import com.lj.business.member.service.IMerchantService;

/**
 * 
 * 
 * 类说明：商户配置@Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年9月18日
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/merchantParams")
public class MerchantParamsController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantParamsController.class);

	@Resource
	private IMerchantParamsService merchantParamsService;			//商户配置服务
	@Resource
	private IMerchantService merchantService;			//商户服务
	@Resource
	private OfficeService officeService;
	
	/** 
	 * 
	 *
	 * 方法说明：商户配置列表
	 *
	 * @param findMerchantParamsPage
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月18日
	 *
	 */
	@RequiresPermissions("cm:merchantParams:view")
	@RequestMapping(value = {"list", ""})
	public String list(FindMerchantParamsPage findMerchantParamsPage,Integer pageNo,Integer pageSize, Model model) {
		try {
			findMerchantParamsPage.setSortDir(PageSortType.desc);
			if(pageNo!=null){
				findMerchantParamsPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMerchantParamsPage.setLimit(pageSize);
			}
			Page<FindMerchantParamsPageReturn> pageDto = merchantParamsService.findMerchantParamsPage(findMerchantParamsPage);
			List<FindMerchantParamsPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			 
			com.ape.common.persistence.Page<FindMerchantParamsPageReturn> page=new com.ape.common.persistence.Page<FindMerchantParamsPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findMerchantParamsPage",findMerchantParamsPage);
		} catch (Exception e) {
			logger.error("获取商户配置信息错误！");
		}
		return "modules/cm/merchantParamsList";
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：商户配置表单
	 *
	 * @param findMerchantParams
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月18日
	 *
	 */
	@RequiresPermissions("cm:merchantParams:view")
	@RequestMapping(value = "form")
	public String form(FindMerchantParams findMerchantParams, Model model) {
		try {
			if(findMerchantParams!=null && findMerchantParams.getCode()!=null){
				FindMerchantParamsReturn findMerchantParamsReturn= merchantParamsService.findMerchantParams(findMerchantParams);
				model.addAttribute("data",findMerchantParamsReturn);
			}
			//获取所有商户
//			List<FindMerchantPageReturn> list = merchantService.findAllMerchant();
//			officeService.findList(office);
//			model.addAttribute("list", list);
		} catch (Exception e) {
			logger.error("获取商户配置信息错误！");
		}
		return "modules/cm/merchantParamsForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：保存商户配置
	 *
	 * @param addMerchantParams
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月18日
	 *
	 */
	@RequiresPermissions("cm:merchantParams:edit")
	@RequestMapping(value = "save")
	public String save(AddMerchantParams addMerchantParams, Model model, RedirectAttributes redirectAttributes) {
		try {
		    addMerchantParams.setSysParamValue(StringEscapeUtils.unescapeHtml4(addMerchantParams.getSysParamValue()).toString());
//			addMerchantParams.setGroupName(GroupNameEmus.SYSPARAM.toString());
			addMerchantParams.setCreateDate(new Date());
			merchantParamsService.addMerchantParams(addMerchantParams);
			addMessage(redirectAttributes, "保存商户配置'" + addMerchantParams.getSysParamName() + "'成功");
		} catch (Exception e) {
			logger.error("获取商户配置信息错误！");
		}
		return "redirect:" + adminPath + "/cm/merchantParams/";
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑商户配置
	 *
	 * @param updateMerchantParams
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月18日
	 *
	 */
	@RequiresPermissions("cm:merchantParams:edit")
	@RequestMapping(value = "edit")
	public String edit(UpdateMerchantParams updateMerchantParams, Model model, RedirectAttributes redirectAttributes) {
		try {
		    updateMerchantParams.setSysParamValue(StringEscapeUtils.unescapeHtml4(updateMerchantParams.getSysParamValue()).toString());
			merchantParamsService.updateMerchantParams(updateMerchantParams);
			addMessage(redirectAttributes, "保存商户配置'" + updateMerchantParams.getSysParamName() + "'成功");
		} catch (Exception e) {
			logger.error("编辑商户配置信息错误！");
		}
		return "redirect:" + adminPath + "/cm/merchantParams/";
	}
}
