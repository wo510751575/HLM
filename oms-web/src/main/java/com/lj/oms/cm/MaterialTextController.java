package com.lj.oms.cm;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.lj.business.cm.dto.FindMaterialTextPage;
import com.lj.business.cm.dto.FindMaterialTextPageReturn;
import com.lj.business.cm.service.IMaterialTextService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;

/**
 * 类说明:文本库
 *@author 贾光朝
 *@createDate 2019年4月22日上午11:27:55
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/materialText")
public class MaterialTextController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialTextController.class);
	@Resource
	private IMaterialTextService materialTextService;
	@Resource
	private OfficeService officeService;

	@RequestMapping(value = {"list", ""})
	public String list(FindMaterialTextPage findMaterialTextPage,Integer pageNo,Integer pageSize, Model model) {
		try {
			
			if(pageNo!=null){
				findMaterialTextPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMaterialTextPage.setLimit(pageSize);
			}
			findMaterialTextPage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindMaterialTextPageReturn> pageDto = materialTextService.findWordsInfoPage(findMaterialTextPage);
			List<FindMaterialTextPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			
			if(!list.isEmpty()){
				for (FindMaterialTextPageReturn findMaterialTextPageReturn : list) {
					Office office = officeService.findOffice(findMaterialTextPageReturn.getMerchantNo());
					if(null != office){
						findMaterialTextPageReturn.setOfficeName(office.getName());
					}
				}
			}     
			com.ape.common.persistence.Page<FindMaterialTextPageReturn> page=new com.ape.common.persistence.Page<FindMaterialTextPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findMaterialTextPage",findMaterialTextPage);
			model.addAttribute("merchantNo",findMaterialTextPage.getMerchantNo());
			
		} catch (Exception e) {
			logger.error("获取文本信息错误！",e);
		}
		return "modules/cm/materialTextList";
	}
	
	@RequestMapping(value = "form")
	public String form(FindMaterialTextPage findMaterialTextPage,Model model){
		model.addAttribute("findMaterialTextPage", findMaterialTextPage);
		return "modules/cm/materialTextForm";
	}
	
	@RequestMapping(value = "save")
	public String save(FindMaterialTextPage findMaterialTextPage,Model model){
		try {
			String code = findMaterialTextPage.getCode();
			if(StringUtils.isEmpty(code)){
				findMaterialTextPage.setCode(GUID.generateByUUID());
				findMaterialTextPage.setUpdateTime(new Date());
				findMaterialTextPage.setMerchantNo(UserUtils.getMerchantNo());
				materialTextService.addText(findMaterialTextPage);
			}else{
				findMaterialTextPage.setMerchantNo(UserUtils.getMerchantNo());
				findMaterialTextPage.setUpdateTime(new Date());
				materialTextService.updateText(findMaterialTextPage);
			}
			model.addAttribute("repMsg", "成功");
		} catch (Exception e) {
			logger.error("保存文本信息错误！",e);
			model.addAttribute("repMsg", "失败");
		}
		return "redirect:" + adminPath + "/cm/materialText/";
	}
	
	
	@RequestMapping(value = "delete")
	public String delete(FindMaterialTextPage findMaterialTextPage,Model model){
		try {
			if(StringUtils.isNotEmpty(findMaterialTextPage.getCode())){
				findMaterialTextPage.setMerchantNo(UserUtils.getMerchantNo());
				materialTextService.deleteText(findMaterialTextPage);
				model.addAttribute("repMsg", "删除成功");
			}
		} catch (Exception e) {
			logger.error("删除文本信息错误！",e);
			model.addAttribute("repMsg", "删除失败");
		}
		return "redirect:" + adminPath + "/cm/materialText/";
	}
	
	@RequestMapping(value = {"listJson"})
	@ResponseBody
	public List<FindMaterialTextPageReturn> listJson(FindMaterialTextPage findMaterialTextPage,Model model) {
		List<FindMaterialTextPageReturn> list = Lists.newArrayList();
		try {
			if(StringUtils.isEmpty(findMaterialTextPage.getMerchantNo())) {
				findMaterialTextPage.setMerchantNo(UserUtils.getMerchantNo());
			}
			findMaterialTextPage.setLimit(Integer.MAX_VALUE);
			Page<FindMaterialTextPageReturn> pageDto = materialTextService.findWordsInfoPage(findMaterialTextPage);
			list.addAll(pageDto.getRows());
			
		} catch (Exception e) {
			logger.error("获取文本信息错误！",e);
		}
		return list;
	}
}
