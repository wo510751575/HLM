package com.lj.oms.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.service.project.AddServiceProject;
import com.lj.business.member.dto.service.project.FindServiceProject;
import com.lj.business.member.dto.service.project.FindServiceProjectPage;
import com.lj.business.member.dto.service.project.FindServiceProjectPageReturn;
import com.lj.business.member.dto.service.project.FindServiceProjectReturn;
import com.lj.business.member.dto.service.project.UpdateServiceProject;
import com.lj.business.member.service.IServiceProjectService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：服务项目Controller
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 * 
 *         CreateDate: 2017年9月21日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/serviceProject")
public class ServiceProjectController  extends BaseController{

	@Resource
	private IServiceProjectService serviceProjectService;

//	@Resource
//	private IShopService shopService;

	/**
	 * 
	 *
	 * 方法说明：查询服务项目
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findServiceProjectPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, FindServiceProjectPage findServiceProjectPage) {
		try {
			if (pageNo != null) {
				findServiceProjectPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findServiceProjectPage.setLimit(pageSize);
			}

			findServiceProjectPage.setMerchantNo(UserUtils.getMerchantNo());
//			List<String> shopList = CcUtils.getShopNoList();
//			findServiceProjectPage.setShopNos(shopList);
			Page<FindServiceProjectPageReturn> pages = serviceProjectService.findServiceProjectPage(findServiceProjectPage);
			List<FindServiceProjectPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindServiceProjectPageReturn> page = new com.ape.common.persistence.Page<FindServiceProjectPageReturn>(pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findServiceProjectPage", findServiceProjectPage);
		} catch (Exception e) {
			logger.error("获取服务项目信息错误！", e);
		}
		return "modules/member/serviceProjectList";
	}

	/**
	 * 
	 *
	 * 方法说明：去到服务项目编辑页面
	 *
	 * @param model
	 * @param findServiceProject
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceProject:edit")
	@RequestMapping(value = "form")
	public String form(Model model, FindServiceProject findServiceProject) {
		try {
			if (findServiceProject != null && findServiceProject.getCode() != null) {
				FindServiceProjectReturn findServiceProjectReturn = serviceProjectService.findServiceProject(findServiceProject);
				model.addAttribute("data", findServiceProjectReturn);
			}
//			FindShop findShop = CcUtils.shopFilter();
//			List<FindShopPageReturn> shops = shopService.findShops(findShop);
//			model.addAttribute("shopList", shops);
		} catch (Exception e) {
			logger.error("获取服务项目信息错误！", e);
		}
		return "modules/member/serviceProjectForm";

	}

	/**
	 * 
	 *
	 * 方法说明：新增服务项目
	 *
	 * @param addServiceProject
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceProject:edit")
	@RequestMapping(value = "save")
	public String save(AddServiceProject addServiceProject, RedirectAttributes redirectAttributes) {
		try {
			addServiceProject.setMerchantNo(UserUtils.getMerchantNo());
			addServiceProject.setMerchantName(UserUtils.getUser().getCompany().getName());
			addServiceProject.setCreateId(UserUtils.getUser().getCompany().getName());
			serviceProjectService.addServiceProject(addServiceProject);
			addMessage(redirectAttributes, "保存项目'" + addServiceProject.getProjectName() + "'成功");
		} catch (Exception e) {
			logger.error("保存服务项目信息错误！", e);
		}
		return "redirect:" + adminPath + "/member/serviceProject/";
	}

	/**
	 * 
	 *
	 * 方法说明：编辑服务项目
	 *
	 * @param redirectAttributes
	 * @param updateServiceProject
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceProject:edit")
	@RequestMapping(value = "edit")
	public String edit(RedirectAttributes redirectAttributes, UpdateServiceProject updateServiceProject) {
		try {
			serviceProjectService.updateServiceProject(updateServiceProject);
			addMessage(redirectAttributes, "修改项目'" + updateServiceProject.getProjectName() + "'成功");
		} catch (Exception e) {
			logger.error("编辑服务项目信息错误！", e);
		}
		return "redirect:" + adminPath + "/member/serviceProject/";
	}

}
