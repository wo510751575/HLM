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
import com.lj.business.member.dto.service.project.FindServiceProjectApp;
import com.lj.business.member.dto.service.project.FindServiceProjectAppReturn;
import com.lj.business.member.dto.service.typechoose.AddServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePage;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePageReturn;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChooseReturn;
import com.lj.business.member.dto.service.typechoose.UpdateServiceTypeChoose;
import com.lj.business.member.emus.ServiceType;
import com.lj.business.member.service.IServiceProjectService;
import com.lj.business.member.service.IServiceTypeChooseService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明： 服务类型配置
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 * 
 *         CreateDate: 2017年9月23日
 */

@Controller
@RequestMapping(value = "${adminPath}/member/serviceTypeChoose")
public class ServiceTypeChooseController  extends BaseController{

	@Resource
	private IServiceTypeChooseService serviceTypeChooseService;
	@Resource
	private IServiceProjectService serviceProjectService;
//	@Resource
//	private IShopService shopService;

	/**
	 * 
	 *
	 * 方法说明：服务类型查询
	 *
	 * @param model
	 * @param pageSize
	 * @param pageNo
	 * @param findServiceTypeChoosePage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceTypeChoose:view")
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageSize, Integer pageNo, FindServiceTypeChoosePage findServiceTypeChoosePage) {
		try {
			if (pageNo != null) {
				findServiceTypeChoosePage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findServiceTypeChoosePage.setLimit(pageSize);
			}

			findServiceTypeChoosePage.setMerchantNo(UserUtils.getMerchantNo());
//			findServiceTypeChoosePage.setShopNos(CcUtils.getShopNoList());
			Page<FindServiceTypeChoosePageReturn> pages = serviceTypeChooseService.findServiceTypeChoosePage(findServiceTypeChoosePage);
			List<FindServiceTypeChoosePageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindServiceTypeChoosePageReturn> page = new com.ape.common.persistence.Page<FindServiceTypeChoosePageReturn>(pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			model.addAttribute("page", page);
			model.addAttribute("findServiceTypeChoosePage", findServiceTypeChoosePage);
			model.addAttribute("serviceTypes", ServiceType.values());
		} catch (Exception e) {
			logger.error("获取服务类型信息错误！", e);
		}
		return "modules/member/serviceTypeChooseList";

	}

	/**
	 * 
	 *
	 * 方法说明：去到服务类型编辑页面
	 *
	 * @param model
	 * @param findServiceTypeChoose
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceTypeChoose:edit")
	@RequestMapping(value = "form")
	public String form(Model model, FindServiceTypeChoose findServiceTypeChoose) {
		FindServiceProjectApp findServiceProjectApp = new FindServiceProjectApp();
		try {
			if (findServiceTypeChoose != null && findServiceTypeChoose.getCode() != null) {
				FindServiceTypeChooseReturn findServiceTypeChooseReturn = serviceTypeChooseService.findServiceTypeChoose(findServiceTypeChoose);
				model.addAttribute("data", findServiceTypeChooseReturn);
			}
			findServiceProjectApp.setMerchantNo(UserUtils.getMerchantNo());
			List<FindServiceProjectAppReturn> list = serviceProjectService.findServiceProjectList(findServiceProjectApp);
//			FindShop findShop = CcUtils.shopFilter();
//			List<FindShopPageReturn> shops = shopService.findShops(findShop);

//			model.addAttribute("shopList", shops);
			model.addAttribute("list", list);
			model.addAttribute("serviceTypes", ServiceType.values());
		} catch (Exception e) {
			logger.error("获取服务类型信息错误！", e);
		}
		return "modules/member/serviceTypeChooseForm";
	}

	/**
	 * 
	 *
	 * 方法说明：新增服务类型
	 *
	 * @param redirectAttributes
	 * @param addServiceTypeChoose
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceTypeChoose:edit")
	@RequestMapping(value = "save")
	public String save(RedirectAttributes redirectAttributes, AddServiceTypeChoose addServiceTypeChoose) {
		try {
			addServiceTypeChoose.setMerchantNo(UserUtils.getMerchantNo());
			addServiceTypeChoose.setMerchantName(UserUtils.getUser().getCompany().getName());
			addServiceTypeChoose.setCreateId(UserUtils.getUser().getCompany().getName());
			addServiceTypeChoose.setServiceCode(addServiceTypeChoose.getProjectCode());
			serviceTypeChooseService.addServiceTypeChoose(addServiceTypeChoose);
			addMessage(redirectAttributes, "保存项目'" + addServiceTypeChoose.getServiceName() + "'成功");
		} catch (Exception e) {
			logger.error("保存服务类型信息错误！", e);
		}
		return "redirect:" + adminPath + "/member/serviceTypeChoose/";
	}

	/**
	 * 
	 *
	 * 方法说明：编辑服务类型
	 *
	 * @param redirectAttributes
	 * @param updateServiceTypeChoose
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceTypeChoose:edit")
	@RequestMapping(value = "edit")
	public String edit(RedirectAttributes redirectAttributes, UpdateServiceTypeChoose updateServiceTypeChoose) {
		try {
			updateServiceTypeChoose.setServiceCode(updateServiceTypeChoose.getProjectCode());
			serviceTypeChooseService.updateServiceTypeChoose(updateServiceTypeChoose);
		} catch (Exception e) {
			logger.error("修改服务类型信息错误！", e);
		}
		return "redirect:" + adminPath + "/member/serviceTypeChoose/";
	}
}
