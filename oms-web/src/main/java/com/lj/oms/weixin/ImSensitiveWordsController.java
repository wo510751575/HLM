package com.lj.oms.weixin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.weixin.dto.imSensitiveWords.AddImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.DelImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWords;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPage;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsPageReturn;
import com.lj.business.weixin.dto.imSensitiveWords.FindImSensitiveWordsReturn;
import com.lj.business.weixin.dto.imSensitiveWords.UpdateImSensitiveWords;
import com.lj.business.weixin.service.IImSensitiveWordsService;
import com.lj.oms.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/weixin/imSensitiveWords")
public class ImSensitiveWordsController  extends BaseController{

	@Resource
	private  IImSensitiveWordsService imSensitiveWordsService;
	
	@RequiresPermissions("weixin:imSensitiveWords:view")
	@RequestMapping(value={"list",""})
	public String list(Model model,FindImSensitiveWordsPage findImSensitiveWordsPage,Integer pageNo,Integer pageSize){
		try {
			if(pageNo != null){
			 findImSensitiveWordsPage.setStart((pageNo-1)*pageSize);	
			}
			if(pageSize !=null){
			 findImSensitiveWordsPage.setLimit(pageSize);
			}
			findImSensitiveWordsPage.setMerchantNo(UserUtils.getUser().getCompany().getId());
			Page<FindImSensitiveWordsPageReturn> pages= imSensitiveWordsService.findImSensitiveWordsPage(findImSensitiveWordsPage);
			List<FindImSensitiveWordsPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindImSensitiveWordsPageReturn> page	
			=new com.ape.common.persistence.Page<FindImSensitiveWordsPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findImSensitiveWordsPage", findImSensitiveWordsPage);
		} catch (Exception e) {
           e.printStackTrace();
		}
		return "modules/weixin/imSensitiveWordsList";
	}
	
	@RequiresPermissions("weixin:imSensitiveWords:edit")
	@RequestMapping(value="form")
	public String form(Model model,FindImSensitiveWords findImSensitiveWords){
		try {
              if(findImSensitiveWords !=null && findImSensitiveWords.getCode()!=null){
            	  FindImSensitiveWordsReturn findImSensitiveWordsReturn=imSensitiveWordsService.findImSensitiveWords(findImSensitiveWords);
            	  model.addAttribute("data", findImSensitiveWordsReturn);
              }
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return "modules/weixin/imSensitiveWordsForm";
	}
	
	
	@RequiresPermissions("weixin:imSensitiveWords:edit")
	@RequestMapping(value="edit")
	public String edit(RedirectAttributes redirectAttributes,UpdateImSensitiveWords updateImSensitiveWords){
		try {
			if(updateImSensitiveWords.getCode() !=null){
				imSensitiveWordsService.updateImSensitiveWords(updateImSensitiveWords);
				addMessage(redirectAttributes, "修改敏感词成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "修改敏感词失败！");
		}
		return "redirect:" +Global.getAdminPath() + "/weixin/imSensitiveWords";
	}
	
	
	@RequiresPermissions("weixin:imSensitiveWords:edit")
	@RequestMapping(value="save")
	public String save(RedirectAttributes redirectAttributes,AddImSensitiveWords addImSensitiveWords){
		try {
			addImSensitiveWords.setCreateId(UserUtils.getUser().getName());
			addImSensitiveWords.setMerchantName(UserUtils.getUser().getCompany().getName());
			addImSensitiveWords.setMerchantNo(UserUtils.getUser().getCompany().getId());
			imSensitiveWordsService.addImSensitiveWords(addImSensitiveWords);
			addMessage(redirectAttributes, "新增敏感词成功！");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "新增敏感词失败！");
		}
		return "redirect:" +Global.getAdminPath() + "/weixin/imSensitiveWords";
	}
	
	
	@RequestMapping(value="delect")
	public String delect(RedirectAttributes redirectAttributes,DelImSensitiveWords delImSensitiveWords){
		try {
			if(delImSensitiveWords.getCode()!=null){
			 imSensitiveWordsService.delImSensitiveWords(delImSensitiveWords);
			}
			addMessage(redirectAttributes, "删除敏感词成功！");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "删除敏感词失败！");
		}
		return "redirect:" +Global.getAdminPath() + "/weixin/imSensitiveWords";
		
	}
}
