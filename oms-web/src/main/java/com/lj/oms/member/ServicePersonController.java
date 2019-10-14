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
import com.lj.business.member.dto.service.person.AddServicePerson;
import com.lj.business.member.dto.service.person.FindServicePerson;
import com.lj.business.member.dto.service.person.FindServicePersonPage;
import com.lj.business.member.dto.service.person.FindServicePersonPageReturn;
import com.lj.business.member.dto.service.person.FindServicePersonReturn;
import com.lj.business.member.dto.service.person.UpdateServicePerson;
import com.lj.business.member.service.IServicePersonService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;


/**
 * 
 * 
 * 类说明：服务人员管理
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
@RequestMapping(value = "${adminPath}/member/servicePerson")
public class ServicePersonController  extends BaseController{
	/**
	 * 服务人员服务
	 */
	@Resource
	private IServicePersonService servicePersonService;
	/**
	 * 终端服务
	 */
//	@Autowired
//	private IShopService shopService;
	
	/**
	 * 
	 *
	 * 方法说明：服务人员列表
	 *
	 * @return 返回分页后的数据展现
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月21日
	 *
	 */
	@RequestMapping(value={"list", ""})
	public String  list(Model model,Integer pageNo,Integer pageSize,FindServicePersonPage findServicePersonPage ){ 
		try {
			if(pageNo!=null){
				findServicePersonPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findServicePersonPage.setLimit(pageSize);
			}
			findServicePersonPage.setMerchantNo(UserUtils.getMerchantNo());
//			findServicePersonPage.setShopNos(CcUtils.getShopNoList());
			Page<FindServicePersonPageReturn> pages=servicePersonService.findServicePersonPage(findServicePersonPage);
			List<FindServicePersonPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindServicePersonPageReturn> page=new com.ape.common.persistence.Page<FindServicePersonPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list); 
			page.initialize();
			model.addAttribute("page",page);
			
			model.addAttribute("findServicePersonPage",findServicePersonPage);
		} catch (Exception e) {
			logger.error("获取服务人员信息错误！", e);
		}
		return "modules/member/servicePersonList";
	}
	/**
	 * 
	 *
	 * 方法说明：服务人员修改
	 *
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月15日
	 *
	 */
	@RequiresPermissions("member:servicePerson:view")
	@RequestMapping(value="form")
	public String form(Model model,FindServicePerson findServicePerson){
		try {
			if(findServicePerson !=null && findServicePerson.getCode()!=null){
				FindServicePersonReturn findServicePersonReturn= servicePersonService.findServicePerson(findServicePerson);
				model.addAttribute("data", findServicePersonReturn);
			}
			/*获取所有店铺*/
//			FindShop findShop = CcUtils.shopFilter();
//			List<FindShopPageReturn> shops= shopService.findShops(findShop);
//			model.addAttribute("shops",shops);
		} catch (Exception e) {
			logger.error("获取服务人员信息错误！", e);
		}
		return "modules/member/servicePersonForm";
	}
	
	/**
	 * 
	 * 
	 * 方法说明：新增保存方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param addServicePerson
	 * @return 保存新增数据后跳转页面
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月15日
	 *
	 */
	@RequiresPermissions("member:servicePerson:edit")
	@RequestMapping(value="save")
     public String save(Model model,RedirectAttributes redirectAttributes,AddServicePerson addServicePerson){
		try {
			addServicePerson.setMerchantNo(UserUtils.getMerchantNo());
			addServicePerson.setMerchantName(UserUtils.getUser().getCompany().getName());
			addServicePerson.setCreateId(UserUtils.getUser().getCompany().getName());
			
			servicePersonService.addServicePerson(addServicePerson);
			addMessage(redirectAttributes, "保存服务人员'" +addServicePerson.getPersonName()+ "'成功"); 
		} catch (Exception e) {
			logger.error("新增服务人员信息错误！", e);
		}
		return  "redirect:" +Global.getAdminPath() + "/member/servicePerson";				
     }
	
	/**
	 * 
	 *
	 * 方法说明：编辑保存方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param updateServicePerson
	 * @return 保存修改编辑后的数据后跳转页面
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月15日
	 *
	 */
	@RequiresPermissions("member:servicePerson:edit")
	@RequestMapping("edit")
	public String edit(Model model,RedirectAttributes redirectAttributes,UpdateServicePerson updateServicePerson){
		try {
			servicePersonService.updateServicePerson(updateServicePerson);
			addMessage(redirectAttributes, "保存服务人员'" +updateServicePerson.getPersonName()+ "'成功"); 
		} catch (Exception e) {
			logger.error("保存服务人员信息错误！", e);
		}
		return  "redirect:" +Global.getAdminPath() + "/member/servicePerson";
	}
	
}
