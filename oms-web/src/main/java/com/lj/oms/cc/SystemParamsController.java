package com.lj.oms.cc;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ape.common.config.Global;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.IPage;
import com.lj.cc.domain.SystemParams;
import com.lj.cc.domain.SystemParamsKey;
import com.lj.cc.dto.FindSystemParam;
import com.lj.cc.service.ISystemParamsService;

/**
 * 
 * 
 * 类说明：cc配置Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年10月18日
 */
@Controller
@RequestMapping(value = "${adminPath}/cc/systemParams")
public class SystemParamsController{
	
	private static final Logger logger = LoggerFactory.getLogger(SystemParamsController.class);
    
	@Resource
	private ISystemParamsService systemParamsService;
	
	
	/**
	 * 
	 *
	 * 方法说明：获取cc配置信息
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param param
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年4月4日
	 *
	 */
//	@RequiresPermissions("cc:systemParams:view")
	@RequestMapping(value={"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindSystemParam param){
		try {
			if(pageNo!=null){
				param.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				param.setLimit(pageSize);
			}
			IPage<SystemParams> pages = systemParamsService.findSystemParamsPage(param);
			List<SystemParams> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<SystemParams> page=new com.ape.common.persistence.Page<SystemParams>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("param",param);
		} catch (Exception e) {
		  logger.error("获取cc配置信息错误！");
		}
		return "modules/cc/systemParamsList";	
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：获取cc配置信息
	 *
	 * @param model
	 * @param key
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年4月4日
	 *
	 */
//	@RequiresPermissions("cc:systemParams:edit")
	@RequestMapping(value="from")
	public String from(Model model,SystemParamsKey key){
     try {
		if(key.getGroupName()!=null && key.getSysParamName()!=null){
			SystemParams params = systemParamsService.selectByPrimaryKey(key);
			model.addAttribute("data", params);
		}
	} catch (Exception e) {
		logger.error("获取cc配置信息错误！");
	}
     return "modules/cc/systemParamsFrom";
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：修改cc配置信息
	 *
	 * @param model
	 * @param systemParams
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年4月4日
	 *
	 */
//	@RequiresPermissions("cc:systemParams:edit")	
	@RequestMapping(value="edit")
	public String edit(Model model,SystemParams systemParams){
		try {
			if(systemParams != null){
				systemParamsService.updateSystemParam(systemParams);
			}
		} catch (Exception e) {
			logger.error("修改cc配置信息错误！");
		}
		return "redirect:" +Global.getAdminPath() + "/cc/systemParams";
	}
}
