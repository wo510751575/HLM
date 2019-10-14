package com.lj.oms.cm;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.business.cm.dto.FindMaterialLinkPage;
import com.lj.business.cm.dto.FindMaterialLinkPageReturn;
import com.lj.business.cm.service.IMaterialLinkService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;

/**
 * 类说明:链接库
 *@author 贾光朝
 *@createDate 2019年4月22日上午11:27:55
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/materialLink")
public class MaterialLinkController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(MaterialLinkController.class);
	
	@Resource
	private IMaterialLinkService  materialLinkService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private OfficeService officeService;
	
	@RequestMapping(value = {"list", ""})
	public String list(FindMaterialLinkPage findMaterialLinkPage,Integer pageNo,Integer pageSize, Model model) {
		try {
			
			if(pageNo!=null){
				findMaterialLinkPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMaterialLinkPage.setLimit(pageSize);
			}
			findMaterialLinkPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindMaterialLinkPageReturn> pageDto = materialLinkService.findLinkInfoPage(findMaterialLinkPage);
			List<FindMaterialLinkPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			
			if(!list.isEmpty()){
				for (FindMaterialLinkPageReturn findMaterialLinkPageReturn : list) {
					Office office = officeService.findOffice(findMaterialLinkPageReturn.getMerchantNo());
					if(null != office){
						findMaterialLinkPageReturn.setOfficeName(office.getName());
					}
				}
			}    
			com.ape.common.persistence.Page<FindMaterialLinkPageReturn> page=new com.ape.common.persistence.Page<FindMaterialLinkPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findMaterialLinkPage",findMaterialLinkPage);
			model.addAttribute("merchantNo",findMaterialLinkPage.getMerchantNo());
		} catch (Exception e) {
			logger.error("获取链接信息错误！",e);
		}
		return "modules/cm/materialLinkList";
	}
	
	@RequestMapping(value = "form")
	public String form(FindMaterialLinkPage findMaterialLinkPage,Model model){
		model.addAttribute("findMaterialLinkPage", findMaterialLinkPage);
		return "modules/cm/materialLinkForm";
	}
	
	@RequestMapping(value = "save")
	public String save(HttpSession session, HttpServletRequest request,Model model,FindMaterialLinkPage findMaterialLinkPage){
		try {
			String code = findMaterialLinkPage.getCode();
			if(StringUtils.isEmpty(code)){
				findMaterialLinkPage.setCode(GUID.generateByUUID());
				findMaterialLinkPage.setUpdateTime(new Date());
				findMaterialLinkPage.setMerchantNo(UserUtils.getMerchantNo());
				materialLinkService.add(findMaterialLinkPage);
			}else{
				findMaterialLinkPage.setMerchantNo(UserUtils.getMerchantNo());
				findMaterialLinkPage.setUpdateTime(new Date());
				materialLinkService.update(findMaterialLinkPage);
			}
			model.addAttribute("repMsg", "成功");
		} catch (Exception e) {
			logger.error("保存链接信息错误！",e);
			model.addAttribute("repMsg", "失败");
		}
		return "redirect:" + adminPath + "/cm/materialLink/";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(FindMaterialLinkPage findMaterialLinkPage,Model model){
		try {
			if(StringUtils.isNotEmpty(findMaterialLinkPage.getCode())){
				findMaterialLinkPage.setMerchantNo(UserUtils.getMerchantNo());
				materialLinkService.delete(findMaterialLinkPage);
				model.addAttribute("repMsg", "删除成功");
			}
		} catch (Exception e) {
			logger.error("删除链接错误！",e);
			model.addAttribute("repMsg", "删除失败");
		}
		return "redirect:" + adminPath + "/cm/materialLink/";
	}
	
	@RequestMapping(value = {"listJson"})
	@ResponseBody
	public List<FindMaterialLinkPageReturn> listJson(FindMaterialLinkPage findMaterialLinkPage) {
		List<FindMaterialLinkPageReturn> list = Lists.newArrayList();
		try {
			
			if(StringUtils.isEmpty(findMaterialLinkPage.getMerchantNo())){
				findMaterialLinkPage.setMerchantNo(UserUtils.getMerchantNo());
			}
			
			Page<FindMaterialLinkPageReturn> pageDto = materialLinkService.findLinkInfoPage(findMaterialLinkPage);
			
			list.addAll(pageDto.getRows());
		} catch (Exception e) {
			logger.error("获取链接信息错误！",e);
		}
		return list;
	}
}
