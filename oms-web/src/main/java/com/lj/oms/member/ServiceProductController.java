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
import com.lj.business.member.dto.service.product.AddServiceProduct;
import com.lj.business.member.dto.service.product.FindServiceProduct;
import com.lj.business.member.dto.service.product.FindServiceProductPage;
import com.lj.business.member.dto.service.product.FindServiceProductPageReturn;
import com.lj.business.member.dto.service.product.FindServiceProductReturn;
import com.lj.business.member.dto.service.product.UpdateServiceProduct;
import com.lj.business.member.service.IServiceProductService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：服务产品Controller
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
@RequestMapping(value = "${adminPath}/member/serviceProduct")
public class ServiceProductController  extends BaseController{
	@Resource
	private IServiceProductService serviceProductService;
//	@Resource
//	private IShopService shopService;

	/**
	 * 
	 *
	 * 方法说明：查询服务产品
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findServiceProductPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceProduct:view")
	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, FindServiceProductPage findServiceProductPage) {
		try {
			findServiceProductPage.setMerchantNo(UserUtils.getMerchantNo());
//			findServiceProductPage.setShopNos(CcUtils.getShopNoList());
			Page<FindServiceProductPageReturn> pages = serviceProductService.findServiceProductPage(findServiceProductPage);
			List<FindServiceProductPageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindServiceProductPageReturn> page = new com.ape.common.persistence.Page<FindServiceProductPageReturn>(pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findServiceProductPage", findServiceProductPage);
		} catch (Exception e) {
			logger.error("获取服务产品信息错误！", e);
		}
		return "modules/member/serviceProductList";
	}

	/**
	 * 
	 *
	 * 方法说明：去到服务产品编辑页面
	 *
	 * @param model
	 * @param findServiceProduct
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceProduct:view")
	@RequestMapping(value = "form")
	public String form(Model model, FindServiceProduct findServiceProduct) {
		try {
			if (findServiceProduct != null && findServiceProduct.getCode() != null) {
				FindServiceProductReturn findServiceProductReturn = serviceProductService.findServiceProduct(findServiceProduct);
				model.addAttribute("data", findServiceProductReturn);
			}
//			FindShop findShop = CcUtils.shopFilter();
//			List<FindShopPageReturn> shops = shopService.findShops(findShop);
//			model.addAttribute("shopList", shops);
		} catch (Exception e) {
			logger.error("获取服务产品信息错误！", e);
		}
		return "modules/member/serviceProductForm";
	}

	/**
	 * 
	 *
	 * 方法说明：新增服务产品
	 *
	 * @param model
	 * @param addServiceProduct
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceProduct:edit")
	@RequestMapping(value = "save")
	public String save(Model model, AddServiceProduct addServiceProduct, RedirectAttributes redirectAttributes) {
		try {
			addServiceProduct.setMerchantNo(UserUtils.getMerchantNo());
			addServiceProduct.setMerchantName(UserUtils.getUser().getCompany().getName());
			addServiceProduct.setCreateId(UserUtils.getUser().getCompany().getName());
			serviceProductService.addServiceProduct(addServiceProduct);
			addMessage(redirectAttributes, "保存产品'" + addServiceProduct.getProductName() + "'成功");
		} catch (Exception e) {
			logger.error("新增服务产品信息错误！", e);
		}
		return "redirect:" + adminPath + "/member/serviceProduct/";
	}

	/**
	 * 
	 *
	 * 方法说明：编辑服务产品
	 *
	 * @param model
	 * @param updateServiceProduct
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:serviceProduct:edit")
	@RequestMapping(value = "edit")
	public String edit(Model model, UpdateServiceProduct updateServiceProduct, RedirectAttributes redirectAttributes) {
		try {
			serviceProductService.updateServiceProduct(updateServiceProduct);
			addMessage(redirectAttributes, "保存产品'" + updateServiceProduct.getProductName() + "'成功");
		} catch (Exception e) {
			logger.error("编辑服务产品信息错误！", e);
		}
		return "redirect:" + adminPath + "/member/serviceProduct/";
	}

}
