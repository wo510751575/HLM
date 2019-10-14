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
import com.lj.business.weixin.dto.imemoji.AddImEmoji;
import com.lj.business.weixin.dto.imemoji.FindImEmoji;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPageReturn;
import com.lj.business.weixin.dto.imemoji.FindImEmojiReturn;
import com.lj.business.weixin.dto.imemoji.UpdateImEmoji;
import com.lj.business.weixin.dto.imemoji.UpdateImEmojiPackage;
import com.lj.business.weixin.service.IImEmojiPackageService;
import com.lj.business.weixin.service.IImEmojiService;
import com.lj.oms.utils.DictUtils;
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
 * @author 彭俊霖
 *   
 * CreateDate: 2017年11月01日
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/imEmoji")
public class ImEmojiController extends BaseController{
	
	@Resource
	private  IImEmojiPackageService imEmojiPackageService;
	
	@Resource
	private  IImEmojiService imEmojiService;
	
	/**
	 * 消息返回参数名
	 */
	private static final String REP_MSG = "repMsg";
	/**
	 * 反页参数名
	 */
	private static final String PAGE = "page";
	
	
	/**
	 *  表单列表查询(分页) 
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findImEmojiPage
	 * @return
	 */
	@RequiresPermissions("weixin:imEmoji:view")
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize ,FindImEmojiPage findImEmojiPage){
		try {
			if(pageNo!=null){ 
			  //分页数计算
			  findImEmojiPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				
			  findImEmojiPage.setLimit(pageSize);
			}
			Page<FindImEmojiPageReturn> pages=imEmojiService.findImEmojiPage(findImEmojiPage);
			
			List<FindImEmojiPageReturn> list=Lists.newArrayList();
			
			list.addAll(pages.getRows());
			//封装分页参数
			com.ape.common.persistence.Page<FindImEmojiPageReturn> page 
			= new com.ape.common.persistence.Page<FindImEmojiPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			
			page.initialize();
			model.addAttribute(PAGE, page);
			model.addAttribute("findImEmojiPage", findImEmojiPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/weixin/imEmojiList";
	}
	
	/**
	 * 编辑页面数据查询 ,返回单条数据
	 * @param model
	 * @param findImEmoji
	 * @return
	 */
	@RequiresPermissions("weixin:imEmoji:view")
	@RequestMapping(value="form")
	public String form(Model model,FindImEmoji findImEmoji){
		try {
			//页面回显参数返回
			if(findImEmoji !=null && findImEmoji.getCode()!=null){
				if(StringUtils.isNotBlank(findImEmoji.getCode())){
					
					FindImEmojiReturn findImEmojiReturn = imEmojiService.findImEmoji(findImEmoji);
					
					model.addAttribute("data", findImEmojiReturn);
				}else{
					//回显添加失败时的信息
					model.addAttribute("data", findImEmoji);
				}
			}
			model.addAttribute("packageCode", findImEmoji.getPackageCode());
		} catch (Exception e) {
		e.printStackTrace();
		
		}
		return "modules/weixin/imEmojiForm";
	}
	
	/**
	 * 新增表情接口 
	 * @param model
	 * @param addImEmoji
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:imEmoji:edit")
	@RequestMapping(value="save")
	public String save(Model model,AddImEmoji addImEmoji,RedirectAttributes redirectAttributes){
		try {
			FindImEmoji findImEmoji=new FindImEmoji();
			findImEmoji.setPackageCode(addImEmoji.getPackageCode());
			List<Integer> showIndexList=imEmojiService.findAllShowIndex(addImEmoji.getPackageCode());
			
			if ("true".equals(DictUtils.hasShowIndex(showIndexList,addImEmoji.getShowIndex()))){
				
//				addMessage(model, "保存表情'" + addImEmoji.getEmojiName() + "'失败，显示序号已存在");
				model.addAttribute(REP_MSG, "保存表情'" + addImEmoji.getEmojiName() + "'失败，显示序号已存在");
				BeanUtils.copyProperties(addImEmoji, findImEmoji);
				return form(model, findImEmoji);
			}
			
			//统一版本号
			Long version=System.currentTimeMillis();
			addImEmoji.setVersion(version);
			addImEmoji.setCreateId(UserUtils.getUser().getCompany().getName());
			imEmojiService.addImEmoji(addImEmoji);
			addMessage(redirectAttributes, "保存表情'" + addImEmoji.getEmojiName()+ "'成功！");
			
			
			//表情edit后需更新表情包version
			UpdateImEmojiPackage updateImEmojiPackage=new UpdateImEmojiPackage();
			updateImEmojiPackage.setCode(addImEmoji.getPackageCode());
			updateImEmojiPackage.setVersion(version);
			imEmojiPackageService.updateImEmojiPackage(updateImEmojiPackage);
			
			//update by Peng Junlin 加入packageCode条件
		    FindImEmojiPage findImEmojiPage=new FindImEmojiPage();
		    findImEmojiPage.setPackageCode(addImEmoji.getPackageCode());
		    return list(model, null, null, findImEmojiPage);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "保存属性'" + addImEmoji.getEmojiName()+ "失败！");
			return "error/500";
		}
	}
	/**
	 *  修改表情参数信息接口 
	 * @param model
	 * @param updateImEmoji
	 * @param redirectAttributes
	 * @return
	 */
   @RequiresPermissions("weixin:imEmoji:edit")
   @RequestMapping(value="edit")
   public String edit(Model model,UpdateImEmoji updateImEmoji,RedirectAttributes redirectAttributes){
	   try {
		   //如果本次修改的显示序号有变才校验
			FindImEmoji findImEmoji=new FindImEmoji();
			findImEmoji.setCode(updateImEmoji.getCode());
			findImEmoji.setPackageCode(updateImEmoji.getPackageCode());
			FindImEmojiReturn findImEmojiReturn = imEmojiService.findImEmoji(findImEmoji);
			
			if(!updateImEmoji.getShowIndex().equals(findImEmojiReturn.getShowIndex())){
				List<Integer> showIndexList=imEmojiService.findAllShowIndex(updateImEmoji.getPackageCode());
				if ("true".equals(DictUtils.hasShowIndex(showIndexList,updateImEmoji.getShowIndex()))){
					
//					addMessage(model, "修改表情'" + updateImEmoji.getEmojiName() + "'失败，显示序号已存在");
					model.addAttribute(REP_MSG, "修改表情'" + updateImEmoji.getEmojiName() + "'失败，显示序号已存在");
					return form(model, findImEmoji);
				}
			}
		   
			//统一版本号
		    Long version=System.currentTimeMillis();
			updateImEmoji.setVersion(version);
		    imEmojiService.updateImEmoji(updateImEmoji);
		    addMessage(redirectAttributes, "修改属性'" + updateImEmoji.getEmojiName()+ "'成功！");
		    
		    
		    //表情edit后需更新表情包version
			UpdateImEmojiPackage updateImEmojiPackage=new UpdateImEmojiPackage();
			updateImEmojiPackage.setCode(updateImEmoji.getPackageCode());
			updateImEmojiPackage.setVersion(version);
			imEmojiPackageService.updateImEmojiPackage(updateImEmojiPackage);
		   
		   //update by Peng Junlin 加入packageCode条件
		   FindImEmojiPage findImEmojiPage=new FindImEmojiPage();
		   findImEmojiPage.setPackageCode(updateImEmoji.getPackageCode());
		   return list(model, null, null, findImEmojiPage);
	} catch (Exception e) {
		e.printStackTrace();
		//消息提示语
		addMessage(redirectAttributes, "修改属性'" + updateImEmoji.getEmojiName()+ "失败！");
		return "error/500";
	}
   }

   /**
	 * 
	 *
	 * 方法说明：表情配置
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
   	@RequiresPermissions("weixin:imEmoji:view")
	@RequestMapping(value={"view"})
	public String view(Model model,Integer pageNo,Integer pageSize ,FindImEmojiPage findImEmojiPage){
		try {
			if(pageNo!=null){
				
				findImEmojiPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				
				findImEmojiPage.setLimit(pageSize);
			}
			Page<FindImEmojiPageReturn> pages=imEmojiService.findImEmojiPage(findImEmojiPage);
			List<FindImEmojiPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			//分页参数封装
			com.ape.common.persistence.Page<FindImEmojiPageReturn> page 
			= new com.ape.common.persistence.Page<FindImEmojiPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
		
			page.initialize();
			//返回model对象
			model.addAttribute(PAGE, page);
			model.addAttribute("findImEmojiPage", findImEmojiPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/weixin/imEmojiList";
	}
}
