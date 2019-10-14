package com.lj.oms.cm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.cm.dto.wordsType.AddWordsType;
import com.lj.business.cm.dto.wordsType.DelWordsType;
import com.lj.business.cm.dto.wordsType.FindWordsType;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;
import com.lj.business.cm.dto.wordsType.FindWordsTypePageReturn;
import com.lj.business.cm.dto.wordsType.FindWordsTypeReturn;
import com.lj.business.cm.dto.wordsType.UpdateWordsType;
import com.lj.business.cm.service.IWordsTypeService;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：话术类型Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年7月6日
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/wordsType")
public class WordsTypeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(WordsTypeController.class);

	@Resource
	private IWordsTypeService wordsTypeService;			//话术类型服务
	@Resource
	private OfficeService officeService;
	
	/**
	 * 
	 *
	 * 方法说明：话术类型列表
	 *
	 * @param findWordsTypePage
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	@RequiresPermissions("cm:wordsType:view")
	@RequestMapping(value = {"list", ""})
	public String list(FindWordsTypePage findWordsTypePage,Integer pageNo,Integer pageSize, Model model) {
		try {
			
			if(pageNo!=null){
				findWordsTypePage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findWordsTypePage.setLimit(pageSize);
			}
			findWordsTypePage.setMerchantNo(UserUtils.getMerchantNo());
			if(null != findWordsTypePage.getMerchantNo()){
				findWordsTypePage.setMemberNoGm(UserUtils.getUser().getId());
			}
			Page<FindWordsTypePageReturn> pageDto = wordsTypeService.findWordsTypePage(findWordsTypePage);
			List<FindWordsTypePageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			//如果当前用户角色为总管账号,商户号为空,要显示创建机构,创建人信息
			if(!list.isEmpty()){
				for (FindWordsTypePageReturn findWordsTypePageReturn : list) {
					Office office = officeService.findOffice(findWordsTypePageReturn.getMerchantNo());
					if(null != office){
						findWordsTypePageReturn.setOfficeName(office.getName());
					}
				}
			}    
			com.ape.common.persistence.Page<FindWordsTypePageReturn> page=new com.ape.common.persistence.Page<FindWordsTypePageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findWordsTypePage",findWordsTypePage);
			model.addAttribute("merchantNo",findWordsTypePage.getMerchantNo());
		} catch (Exception e) {
			logger.error("获取话术类型信息错误！",e);
		}
		return "modules/cm/wordsTypeList";
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查看话术类型
	 *
	 * @param findWordsType
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	@RequiresPermissions("cm:wordsType:view")
	@RequestMapping(value = "form")
	public String form(FindWordsType findWordsType, Model model) {
		try {
			if(findWordsType!=null && findWordsType.getCode()!=null){
				if(StringUtils.isNotBlank(findWordsType.getCode())){
					FindWordsTypeReturn findWordsTypeReturn= wordsTypeService.findWordsType(findWordsType);
					model.addAttribute("data",findWordsTypeReturn);
				}else{
					//回显添加失败时的信息
					model.addAttribute("data", findWordsType);
				}
			}
		} catch (Exception e) {
			logger.error("获取话术类型信息错误！",e);
		}
		
		return "modules/cm/wordsTypeForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：保存话术类型
	 *
	 * @param addWordsType
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	@RequiresPermissions("cm:wordsType:edit")
	@RequestMapping(value = "save")
	public String save(AddWordsType addWordsType, Model model, RedirectAttributes redirectAttributes) {
		try {
			FindWordsType findWordsType=new FindWordsType();
			addWordsType.setMerchantNo(UserUtils.getMerchantNo());
			addWordsType.setMemberNoGm(UserUtils.getUser().getId());
			if(wordsTypeService.hasTypeName(addWordsType.getMemberNoGm(),addWordsType.getTypeName())>0){
				model.addAttribute("repMsg", "保存话术类型失败，话术类型'" + addWordsType.getTypeName() + "'已存在");
				BeanUtils.copyProperties(addWordsType, findWordsType);
				return form(findWordsType,model);
			}
			if(wordsTypeService.hasSeq(addWordsType.getMemberNoGm(),addWordsType.getSeq())>0){
				model.addAttribute("repMsg", "保存话术类型失败，排序'" + addWordsType.getSeq() + "'已存在");
				BeanUtils.copyProperties(addWordsType, findWordsType);
				return form(findWordsType,model);
			}
			
			addWordsType.setCreateId(UserUtils.getUser().getName());
			wordsTypeService.addWordsType(addWordsType);
//			addMessage(redirectAttributes, "保存话术类型'" + addWordsType.getTypeName() + "'成功");
			model.addAttribute("repMsg", "保存话术类型'" + addWordsType.getTypeName() + "'成功");
		} catch (Exception e) {
			logger.error("保存话术类型信息错误！",e);
		}
//		return "redirect:" + adminPath + "/cm/wordsType/";
		FindWordsTypePage findWordsTypePage=new FindWordsTypePage();
		return list(findWordsTypePage, null, null, model);
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑话术类型
	 *
	 * @param updateWordsType
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	@RequiresPermissions("cm:wordsType:edit")
	@RequestMapping(value = "edit")
	public String edit(UpdateWordsType updateWordsType, Model model, RedirectAttributes redirectAttributes) {
		try {
			updateWordsType.setMerchantNo(UserUtils.getMerchantNo());
			updateWordsType.setMemberNoGm(UserUtils.getUser().getId());
			FindWordsType findWordsType=new FindWordsType();
			findWordsType.setCode(updateWordsType.getCode());
			FindWordsTypeReturn findWordsTypeReturn = wordsTypeService.findWordsType(findWordsType);
			//如果本次修改的话术类型信息有变才校验
			if(!updateWordsType.getTypeName().equals(findWordsTypeReturn.getTypeName())){
				if(wordsTypeService.hasTypeName(updateWordsType.getMemberNoGm(),updateWordsType.getTypeName())>0){
					model.addAttribute("repMsg", "保存话术类型失败，话术类型'" + updateWordsType.getTypeName() + "'已存在");
					BeanUtils.copyProperties(updateWordsType, findWordsType);
					return form(findWordsType,model);
				}
			}
			if(!updateWordsType.getSeq().equals(findWordsTypeReturn.getSeq())){
				if(wordsTypeService.hasSeq(updateWordsType.getMemberNoGm(),updateWordsType.getSeq())>0){
					model.addAttribute("repMsg", "保存话术类型失败，排序'" + updateWordsType.getSeq() + "'已存在");
					BeanUtils.copyProperties(updateWordsType, findWordsType);
					return form(findWordsType,model);
				}
			}
			
			wordsTypeService.updateWordsType(updateWordsType,findWordsTypeReturn);
//			addMessage(redirectAttributes, "编辑话术类型'" + updateWordsType.getTypeName() + "'成功");
			model.addAttribute("repMsg", "编辑话术类型'" + updateWordsType.getTypeName() + "'成功");
		} catch (Exception e) {
			logger.error("修改话术类型信息错误！",e);
		}
//		return "redirect:" + adminPath + "/cm/wordsType/";
		FindWordsTypePage findWordsTypePage=new FindWordsTypePage();
		return list(findWordsTypePage, null, null, model);
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除话术类型
	 *
	 * @param delWordsType
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	@RequiresPermissions("cm:wordsType:edit")
	@RequestMapping(value = "delete")
	public String delete(DelWordsType delWordsType, Model model, RedirectAttributes redirectAttributes) {
		try {
			wordsTypeService.delWordsType(delWordsType);
			model.addAttribute("repMsg", "删除话术类型'" + delWordsType.getTypeName() + "'成功");
		} catch (Exception e) {
			logger.error("删除话术类型信息错误！",e);
		}
		FindWordsTypePage findWordsTypePage=new FindWordsTypePage();
		return list(findWordsTypePage, null, null, model);
	}
	
}
