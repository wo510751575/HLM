package com.lj.oms.cm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.cm.dto.textInfo.AddTextInfo;
import com.lj.business.cm.dto.textInfo.FindTextInfo;
import com.lj.business.cm.dto.textInfo.FindTextInfoPage;
import com.lj.business.cm.dto.textInfo.FindTextInfoPageReturn;
import com.lj.business.cm.dto.textInfo.FindTextInfoReturn;
import com.lj.business.cm.dto.textInfo.UpdateTextInfo;
import com.lj.business.cm.service.ITextInfoService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：话术Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月6日
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/textInfo")
public class TextInfoController  extends BaseController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(TextInfoController.class);

	@Resource
	private ITextInfoService textInfoService;			//话术服务
	
	/**
	 * 
	 *
	 * 方法说明：话术列表
	 *
	 * @param findTextInfoPage
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("cm:textInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(FindTextInfoPage findTextInfoPage,Integer pageNo,Integer pageSize, Model model) {
		try {
			findTextInfoPage.setMerchantNo(UserUtils.getUser().getCompany().getId());
			if(pageNo!=null){
				findTextInfoPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findTextInfoPage.setLimit(pageSize);
			}
			Page<FindTextInfoPageReturn> pageDto = textInfoService.findTextInfoPage(findTextInfoPage);
			List<FindTextInfoPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			 
			com.ape.common.persistence.Page<FindTextInfoPageReturn> page=new com.ape.common.persistence.Page<FindTextInfoPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findTextInfoPage",findTextInfoPage);
		} catch (Exception e) {
			logger.error("获取话术信息错误！");
		}
		return "modules/cm/textInfoList";
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查看话术
	 *
	 * @param findTextInfo
	 * @param model
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("cm:textInfo:view")
	@RequestMapping(value = "form")
	public String form(FindTextInfo findTextInfo, Model model) {
		try {
			if(findTextInfo!=null && findTextInfo.getCode()!=null){
				FindTextInfoReturn findTextInfoReturn= textInfoService.findTextInfo(findTextInfo);
				model.addAttribute("data",findTextInfoReturn);
			}
		} catch (Exception e) {
			logger.error("获取话术信息错误！");
		}
		
		
		return "modules/cm/textInfoForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：保存话术
	 *
	 * @param addTextInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("cm:textInfo:edit")
	@RequestMapping(value = "save")
	public String save(AddTextInfo addTextInfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			addTextInfo.setMerchantNo(UserUtils.getUser().getCompany().getId());
			addTextInfo.setMerchantName(UserUtils.getUser().getCompany().getName());
			textInfoService.addTextInfo(addTextInfo);
			addMessage(redirectAttributes, "保存话术'" + addTextInfo.getContent() + "'成功");
		} catch (Exception e) {
			logger.error("保存话术信息错误！");
		}
		return "redirect:" + adminPath + "/cm/textInfo/";
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑话术
	 *
	 * @param updateTextInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("cm:textInfo:edit")
	@RequestMapping(value = "edit")
	public String edit(UpdateTextInfo updateTextInfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			textInfoService.updateTextInfo(updateTextInfo);
			addMessage(redirectAttributes, "保存话术'" + updateTextInfo.getContent() + "'成功");
		} catch (Exception e) {
			logger.error("修改话术信息错误！");
		}
		return "redirect:" + adminPath + "/cm/textInfo/";
	}
	
}
