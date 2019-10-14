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
import com.lj.business.member.dto.service.projectproperty.AddProjectProperty;
import com.lj.business.member.dto.service.projectproperty.FindProjectProperty;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPage;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPageReturn;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyReturn;
import com.lj.business.member.dto.service.projectproperty.UpdateProjectProperty;
import com.lj.business.member.service.IProjectPropertyService;
import com.lj.business.member.service.IServiceProjectService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年9月21日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/projectProperty")
public class ProjectPropertyController extends BaseController{
	
	@Resource
	private IProjectPropertyService projectPropertyService;
	
	@Resource
	private  IServiceProjectService serviceProjectService;
	
//	@Resource
//	private IShopService shopService;
	
	/**
	 * 
	 *
	 * 方法说明：查询服务项目属性
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findProjectPropertyPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:projectProperty:view")
	@RequestMapping(value={"list","view", ""})
	public String list(Model model,Integer pageNo,Integer pageSize ,FindProjectPropertyPage findProjectPropertyPage){
		try {
			if(pageNo!=null){
				findProjectPropertyPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findProjectPropertyPage.setLimit(pageSize);
			}
			findProjectPropertyPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindProjectPropertyPageReturn> pages=projectPropertyService.findProjectPropertyPage(findProjectPropertyPage);
			List<FindProjectPropertyPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindProjectPropertyPageReturn> page 
			= new com.ape.common.persistence.Page<FindProjectPropertyPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findProjectPropertyPage", findProjectPropertyPage);
		} catch (Exception e) {
			logger.error("获取服务项目属性信息错误！", e);
		}
		return "modules/member/projectPropertyList";
	}
	
	/**
	 * 
	 *
	 * 方法说明：去到服务项目属性编辑页面
	 *
	 * @param model
	 * @param findProjectProperty
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:projectProperty:view")
	@RequestMapping(value="form")
	public String form(Model model,FindProjectProperty findProjectProperty){
		try {
			//查询项目
//			FindServiceProjectApp findServiceProjectApp=new FindServiceProjectApp();
//			findServiceProjectApp.setMerchantNo(UserUtils.getMerchantNo());
//			List<FindServiceProjectAppReturn> list=serviceProjectService.findServiceProjectList(findServiceProjectApp);
//			model.addAttribute("list", list);
			if(findProjectProperty !=null && findProjectProperty.getCode()!=null){
				FindProjectPropertyReturn findProjectPropertyReturn = projectPropertyService.findProjectProperty(findProjectProperty);
				model.addAttribute("data", findProjectPropertyReturn);
			}
			model.addAttribute("projectCode", findProjectProperty.getProjectCode());
			//查询终端
//			FindShop findShop = new FindShop();
//			findShop.setMemberNoMerchant(UserUtils.getMerchantNo());
//			List<FindShopPageReturn> shops= shopService.findShops(findShop);
//		    model.addAttribute("shopList", shops);
		} catch (Exception e) {
			logger.error("获取服务项目属性信息错误！", e);
		
		}
		return "modules/member/projectPropertyForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：新增服务项目属性
	 *
	 * @param model
	 * @param addProjectProperty
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
	@RequiresPermissions("member:projectProperty:edit")
	@RequestMapping(value="save")
	public String save(Model model,AddProjectProperty addProjectProperty,RedirectAttributes redirectAttributes){
		try {
			addProjectProperty.setCreateId(UserUtils.getUser().getCompany().getName());
			projectPropertyService.addProjectProperty(addProjectProperty);
			addMessage(redirectAttributes, "保存属性'" + addProjectProperty.getPropertyName() + "'成功！");
			// update by Peng Junlin 加入projectCode条件
			FindProjectPropertyPage findProjectPropertyPage = new FindProjectPropertyPage();
			findProjectPropertyPage.setProjectCode(addProjectProperty.getProjectCode());
			return list(model, null, null, findProjectPropertyPage);
		} catch (Exception e) {
			logger.error("新增服务项目属性信息错误！", e);
			addMessage(redirectAttributes, "保存属性'" + addProjectProperty.getPropertyName() + "失败！");
			return "error/500";
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改服务项目属性
	 *
	 * @param model
	 * @param updateProjectProperty
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月9日
	 *
	 */
   @RequiresPermissions("member:projectProperty:edit")
   @RequestMapping(value="edit")
   public String edit(Model model,UpdateProjectProperty updateProjectProperty,RedirectAttributes redirectAttributes){
		try {
			projectPropertyService.updateProjectProperty(updateProjectProperty);
			addMessage(redirectAttributes, "修改属性'" + updateProjectProperty.getPropertyName() + "'成功！");
			// update by Peng Junlin 加入projectCode条件
			FindProjectPropertyPage findProjectPropertyPage = new FindProjectPropertyPage();
			findProjectPropertyPage.setProjectCode(updateProjectProperty.getProjectCode());
			return list(model, null, null, findProjectPropertyPage);
		} catch (Exception e) {
			logger.error("修改服务项目属性信息错误！", e);
			addMessage(redirectAttributes, "修改属性'" + updateProjectProperty.getPropertyName() + "失败！");
			return "error/500";
		}
   }

   /**
	 * 
	 *
	 * 方法说明：属性配置
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年10月20日
	 *
	 */
   /**
    * 已合并到方法{@link #list(Model, Integer, Integer, FindProjectPropertyPage)}
    * update by 	曾垂瑜
    * update date 	2018年4月9日
    */
   	/*@RequiresPermissions("member:projectProperty:view")
	@RequestMapping(value={"view"})
	public String view(Model model,Integer pageNo,Integer pageSize ,FindProjectPropertyPage findProjectPropertyPage){
		try {
			if(pageNo!=null){
				findProjectPropertyPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findProjectPropertyPage.setLimit(pageSize);
			}
			findProjectPropertyPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindProjectPropertyPageReturn> pages=projectPropertyService.findProjectPropertyPage(findProjectPropertyPage);
			List<FindProjectPropertyPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindProjectPropertyPageReturn> page 
			= new com.ape.common.persistence.Page<FindProjectPropertyPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findProjectPropertyPage", findProjectPropertyPage);
		} catch (Exception e) {
			logger.error("获取服务项目属性信息错误！", e);
		}
		return "modules/member/projectPropertyList";
	}*/
}
