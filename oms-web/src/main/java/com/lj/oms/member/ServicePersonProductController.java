package com.lj.oms.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.service.person.FindServicePerson;
import com.lj.business.member.dto.service.person.FindServicePersonReturn;
import com.lj.business.member.dto.service.personproduct.AddServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPage;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPageReturn;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductReturn;
import com.lj.business.member.dto.service.personproduct.UpdateServicePersonProduct;
import com.lj.business.member.service.IServicePersonProductService;
import com.lj.business.member.service.IServicePersonService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;


/**
 * 
 * 
 * 类说明：服务人员作品管理
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 * 
 * CreateDate: 2017年9月21日
 */

@Controller
@RequestMapping(value = "${adminPath}/member/servicePersonProduct")
public class ServicePersonProductController  extends BaseController{
	/**
	 * 服务人员服务
	 */
	@Resource
	private IServicePersonService servicePersonService;
	/**
	 * 服务人员作品服务
	 */
	@Resource
	private IServicePersonProductService servicePersonProductService;
	/**
	 * 终端服务
	 */
//	@Autowired
//	private IShopService shopService;
	/**
	 * 
	 *
	 * 方法说明：服务人员作品列表
	 *
	 * @return 返回分页后的数据展现
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月21日
	 *
	 */
	@RequestMapping(value={"list", ""})
	public String  list(Model model,Integer pageNo,Integer pageSize,FindServicePersonProductPage findServicePersonProductPage ){ 
		try {
			if(pageNo!=null){
				findServicePersonProductPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findServicePersonProductPage.setLimit(pageSize);
			}
			findServicePersonProductPage.setMerchantNo(UserUtils.getMerchantNo());
//			findServicePersonProductPage.setShopNos(CcUtils.getShopNoList());
			Page<FindServicePersonProductPageReturn> pages=servicePersonProductService.findServicePersonProductPage(findServicePersonProductPage);
			List<FindServicePersonProductPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindServicePersonProductPageReturn> page=new com.ape.common.persistence.Page<FindServicePersonProductPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list); 
			page.initialize();
			model.addAttribute("page",page);
			
			model.addAttribute("findServicePersonProductPage",findServicePersonProductPage);
		} catch (Exception e) {
			logger.error("查询服务人员作品列表错误", e);
		}
		return "modules/member/servicePersonProductList";
	}
	/**
	 * 
	 *
	 * 方法说明：服务人员作品修改
	 *
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月21日
	 *
	 */
	@RequiresPermissions("member:servicePersonProduct:view")
	@RequestMapping(value="form")
	public String form(Model model,FindServicePersonProduct findServicePersonProduct){
		try {
			if(findServicePersonProduct !=null && findServicePersonProduct.getCode()!=null){
				FindServicePersonProductReturn findServicePersonProductReturn= servicePersonProductService.findServicePersonProduct(findServicePersonProduct);
				model.addAttribute("data", findServicePersonProductReturn);
			}
			/*获取所有店铺*/
//			FindShop findShop = CcUtils.shopFilter();
//			List<FindShopPageReturn> shops= shopService.findShops(findShop);
//			model.addAttribute("shops",shops);
			/*获取所有服务人员*/
			FindServicePerson findServicePerson = new FindServicePerson();
			findServicePerson.setMerchantNo(UserUtils.getMerchantNo());
			List<FindServicePersonReturn> servicePersons= servicePersonService.findServicePersons(findServicePerson);
			model.addAttribute("servicePersons",servicePersons);
			/*获取所有服务选择 TODO*/
			
		} catch (Exception e) {
			logger.error("查询服务人员作品错误", e);
		}
		return "modules/member/servicePersonProductForm";
	}
	
	/**
	 * 
	 * 
	 * 方法说明：新增保存方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param addServicePersonProduct
	 * @return 保存新增数据后跳转页面
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月15日
	 *
	 */
	@RequiresPermissions("member:servicePersonProduct:edit")
	@RequestMapping(value="save")
     public String save(Model model,RedirectAttributes redirectAttributes,AddServicePersonProduct addServicePersonProduct){
		try {
			addServicePersonProduct.setMerchantNo(UserUtils.getMerchantNo());
			addServicePersonProduct.setMerchantName(UserUtils.getUser().getCompany().getName());
			addServicePersonProduct.setCreateId(UserUtils.getUser().getName());
			
			servicePersonProductService.addServicePersonProduct(addServicePersonProduct);
			addMessage(redirectAttributes, "保存服务人员作品'" +addServicePersonProduct.getDescription()+ "'成功"); 
		} catch (Exception e) {
			logger.error("新增服务人员作品错误", e);
		}
		return  "redirect:" +Global.getAdminPath() + "/member/servicePersonProduct";				
     }
	
	/**
	 * 
	 *
	 * 方法说明：编辑保存方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param updateServicePersonProduct
	 * @return 保存修改编辑后的数据后跳转页面
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月15日
	 *
	 */
	@RequiresPermissions("member:servicePersonProduct:edit")
	@RequestMapping("edit")
	public String edit(Model model,RedirectAttributes redirectAttributes,UpdateServicePersonProduct updateServicePersonProduct){
		try {
			servicePersonProductService.updateServicePersonProduct(updateServicePersonProduct);
			addMessage(redirectAttributes, "保存服务人员作品'" +updateServicePersonProduct.getDescription()+ "'成功"); 
		} catch (Exception e) {
			logger.error("编辑服务人员作品错误", e);
		}
		return  "redirect:" +Global.getAdminPath() + "/member/servicePersonProduct";
	}
	
}
