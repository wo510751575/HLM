package com.lj.oms.weixin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.weixin.dto.imemoji.AddImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackagePageReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackageReturn;
import com.lj.business.weixin.dto.imemoji.UpdateImEmojiPackage;
import com.lj.business.weixin.service.IImEmojiPackageService;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.UserUtils;
/**
 * 
 * 
 * 类说明：IM表情包Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年11月01日
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/imEmojiPackage")
public class ImEmojiPackageController  extends BaseController{
   
	@Resource
	private  IImEmojiPackageService imEmojiPackageService;
	
	@RequiresPermissions("weixin:imEmojiPackage:view")
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindImEmojiPackagePage findImEmojiPackagePage){
		try {
			if(pageNo!=null){
				findImEmojiPackagePage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findImEmojiPackagePage.setLimit(pageSize);
			}
			Page<FindImEmojiPackagePageReturn> pages=imEmojiPackageService.findImEmojiPackagePage(findImEmojiPackagePage);
			List<FindImEmojiPackagePageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindImEmojiPackagePageReturn> page 
			= new com.ape.common.persistence.Page<FindImEmojiPackagePageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findImEmojiPackagePage", findImEmojiPackagePage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/weixin/imEmojiPackageList";
	}
	
	@RequiresPermissions("weixin:imEmojiPackage:view")
	@RequestMapping(value="form")
	public String form(Model model,FindImEmojiPackage findImEmojiPackage){
		try {
			if(findImEmojiPackage!=null && findImEmojiPackage.getCode()!=null){
				if(StringUtils.isNotBlank(findImEmojiPackage.getCode())){
					FindImEmojiPackageReturn findImEmojiPackageReturn= imEmojiPackageService.findImEmojiPackage(findImEmojiPackage);
					model.addAttribute("data", findImEmojiPackageReturn);
				}else{
					//回显添加失败时的信息
					model.addAttribute("data", findImEmojiPackage);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/weixin/imEmojiPackageForm";
		
	}
	@RequiresPermissions("weixin:imEmojiPackage:edit")
	@RequestMapping(value="save")
	public String save(AddImEmojiPackage addImEmojiPackage, Model model,RedirectAttributes  redirectAttributes){
		try {
			FindImEmojiPackage findImEmojiPackage=new FindImEmojiPackage();
			List<Integer> showIndexList=imEmojiPackageService.findAllShowIndex();
			if ("true".equals(DictUtils.hasShowIndex(showIndexList,addImEmojiPackage.getShowIndex()))){
//				addMessage(model, "保存表情包'" + addImEmojiPackage.getPackageName() + "'失败，显示序号已存在");
				model.addAttribute("repMsg", "保存表情包'" + addImEmojiPackage.getPackageName() + "'失败，显示序号已存在");
				BeanUtils.copyProperties(addImEmojiPackage, findImEmojiPackage);
				return form(model, findImEmojiPackage);
			}
			
			addImEmojiPackage.setCreateId(UserUtils.getUser().getCompany().getName());
			imEmojiPackageService.addImEmojiPackage(addImEmojiPackage);
			addMessage(redirectAttributes, "保存表情包'" + addImEmojiPackage.getPackageName()+ "'成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + adminPath + "/weixin/imEmojiPackage/";
	}

	@RequiresPermissions("weixin:imEmojiPackage:edit")
	@RequestMapping(value="edit")
	public String edit(RedirectAttributes redirectAttributes,Model model,UpdateImEmojiPackage updateImEmojiPackage){
		try {
			//如果本次修改的显示序号有变才校验
			FindImEmojiPackage findImEmojiPackage=new FindImEmojiPackage();
			findImEmojiPackage.setCode(updateImEmojiPackage.getCode());
			FindImEmojiPackageReturn findImEmojiPackageReturn = imEmojiPackageService.findImEmojiPackage(findImEmojiPackage);
			if(!updateImEmojiPackage.getShowIndex().equals(findImEmojiPackageReturn.getShowIndex())){
				List<Integer> showIndexList=imEmojiPackageService.findAllShowIndex();
				if ("true".equals(DictUtils.hasShowIndex(showIndexList,updateImEmojiPackage.getShowIndex()))){
//					addMessage(model, "修改表情包'" + updateImEmojiPackage.getPackageName() + "'失败，显示序号已存在");
					model.addAttribute("repMsg", "修改表情包'" + updateImEmojiPackage.getPackageName() + "'失败，显示序号已存在");
					return form(model, findImEmojiPackage);
				}
			}
			
			imEmojiPackageService.updateImEmojiPackage(updateImEmojiPackage);
			addMessage(redirectAttributes, "修改表情包'" + updateImEmojiPackage.getPackageName()+ "'成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return "redirect:" + adminPath + "/weixin/imEmojiPackage/";
	}
	
	
}
