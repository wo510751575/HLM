package com.lj.oms.cm;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lj.oms.common.BaseController;
import com.ape.common.utils.SpringContextHolder;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.cm.dto.activity.AddActivity;
import com.lj.business.cm.dto.activity.FindActivity;
import com.lj.business.cm.dto.activity.FindActivityPage;
import com.lj.business.cm.dto.activity.FindActivityPageReturn;
import com.lj.business.cm.dto.activity.FindActivityReturn;
import com.lj.business.cm.dto.activity.UpdateActivity;
import com.lj.business.cm.service.IActivityService;
import com.lj.business.common.SystemParamConstant;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：活动@Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年8月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/activity")
public class ActivityController  extends BaseController{

	
	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	private IActivityService activityService;			//活动服务
	@Autowired
	private OfficeService officeService;				//机构
	
	private static LocalCacheSystemParamsFromCC localCacheSystemParams = SpringContextHolder.getBean(LocalCacheSystemParamsFromCC.class);
	
	private static final String PATH = "oms-web/a/cm/activity/viewH5?code=";
	
	/**
	 * 
	 *
	 * 方法说明：活动列表
	 *
	 * @param findActivityPage
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月1日
	 *
	 */
	@RequiresPermissions("cm:activity:view")
	@RequestMapping(value = {"list", ""})
	public String list(FindActivityPage findActivityPage,Integer pageNo,Integer pageSize, Model model) {
		try {
			findActivityPage.setMerchantNo(UserUtils.getMerchantNo());
			findActivityPage.setSortDir(PageSortType.desc);
			if(pageNo!=null){
				findActivityPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findActivityPage.setLimit(pageSize);
			}
			Page<FindActivityPageReturn> pageDto = activityService.findActivityPage(findActivityPage);
			List<FindActivityPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			 
			com.ape.common.persistence.Page<FindActivityPageReturn> page=new com.ape.common.persistence.Page<FindActivityPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findActivityPage",findActivityPage);
		} catch (Exception e) {
			logger.error("获取活动信息失败！");
		}
		return "modules/cm/activityList";
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：活动表单
	 *
	 * @param findActivity
	 * @param model
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年8月1日
	 *
	 */
	@RequiresPermissions("cm:activity:view")
	@RequestMapping(value = "form")
	public String form(FindActivity findActivity, Model model) {
		try {
			if(findActivity!=null && findActivity.getCode()!=null){
				FindActivityReturn findActivityReturn= activityService.findActivity(findActivity);
				model.addAttribute("data",findActivityReturn);
			}
		} catch (Exception e) {
			logger.error("获取活动信息失败！");
		}
		
		
		return "modules/cm/activityForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：保存活动
	 *
	 * @param addActivity
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("cm:activity:edit")
	@RequestMapping(value = "save")
	public String save(AddActivity addActivity, Model model, RedirectAttributes redirectAttributes) {
		try {
			addActivity.setCode(GUID.generateByUUID());
			//如果链接地址为空,自动生成一个链接
			if(StringUtils.isEmpty(addActivity.getLinkUrl())){
				String uploadPath =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);
				String linkUrl = uploadPath + PATH + addActivity.getCode();
				addActivity.setLinkUrl(linkUrl);
			}
			addActivity.setMerchantNo(UserUtils.getMerchantNo());
			addActivity.setMerchantName(UserUtils.getUser().getCompany().getName());
			addActivity.setCreateId(UserUtils.getUser().getCompany().getName());
			activityService.addActivity(addActivity);
			addMessage(redirectAttributes, "保存活动'" + addActivity.getTitle() + "'成功");
		} catch (Exception e) {
			logger.error("保存活动信息失败！");
		}
		return "redirect:" + adminPath + "/cm/activity/";
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑活动
	 *
	 * @param updateActivity
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("cm:activity:edit")
	@RequestMapping(value = "edit")
	public String edit(UpdateActivity updateActivity, Model model, RedirectAttributes redirectAttributes) {
		try {
			updateActivity.setMerchantName(UserUtils.getUser().getCompany().getName());	
			activityService.updateActivity(updateActivity);
			addMessage(redirectAttributes, "保存活动'" + updateActivity.getTitle() + "'成功");
		} catch (Exception e) {
			logger.error("修改活动信息失败！");
		}
		return "redirect:" + adminPath + "/cm/activity/";
	}
	
//	@RequiresPermissions("cm:activity:view")	快推需要开放
	@RequestMapping(value = "view")
	public String view(FindActivity findActivity, Model model) {
		try {
			if(findActivity!=null && findActivity.getCode()!=null){
				FindActivityReturn findActivityReturn= activityService.findActivity(findActivity);
				model.addAttribute("data",findActivityReturn);
				if(!findActivityReturn.getMerchantNo().isEmpty()){
					Office company= officeService.get(officeService.get(findActivityReturn.getMerchantNo()));
					//公司名
					model.addAttribute("companyName",company.getName());
					//公司LOGO
					model.addAttribute("companyLogo",company.getLogo());
					//公司简介
					model.addAttribute("companyRemarks",company.getRemarks());
				}
			}
		} catch (Exception e) {
			logger.error("获取活动信息失败！");
		}
		return "modules/cm/activityView";
	}
	
	
	@RequestMapping(value = "viewH5")
	public String viewH5(FindActivity findActivity, Model model) {
		try {
			if(findActivity!=null && findActivity.getCode()!=null){
				FindActivityReturn findActivityReturn= activityService.findActivity(findActivity);
				model.addAttribute("data",findActivityReturn);
				
				if(!findActivityReturn.getMerchantNo().isEmpty()){
					Office company= officeService.get(officeService.get(findActivityReturn.getMerchantNo()));
					//公司名
					model.addAttribute("companyName",company.getName());
					//公司LOGO
					model.addAttribute("companyLogo",company.getLogo());
					//公司简介
					model.addAttribute("companyRemarks",company.getRemarks());
				}
				
				/*增加阅读量*/
				Long readCount = findActivityReturn.getReadCount();
				UpdateActivity updateActivity = new UpdateActivity();
				updateActivity.setCode(findActivityReturn.getCode());
				updateActivity.setReadCount(readCount==null?0L:readCount+1); 
				activityService.updateActivity(updateActivity);
			}
		} catch (Exception e) {
			logger.error("获取活动信息失败！");
		}		
		return "modules/cm/activityH5";
	}
}
