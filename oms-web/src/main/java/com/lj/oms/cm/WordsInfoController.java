package com.lj.oms.cm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
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
import com.lj.business.cm.dto.wordsInfo.AddWordsInfo;
import com.lj.business.cm.dto.wordsInfo.DelWordsInfo;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfo;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPage;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPageReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoWeb;
import com.lj.business.cm.dto.wordsInfo.UpdateWordsInfo;
import com.lj.business.cm.dto.wordsType.FindWordsTypeSelectReturn;
import com.lj.business.cm.service.IWordsInfoService;
import com.lj.business.cm.service.IWordsTypeService;
import com.lj.business.weixin.service.IImSensitiveWordsService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
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
 * @author 彭俊霖
 *   
 * CreateDate: 2017年7月6日
 */
@Controller
@RequestMapping(value = "${adminPath}/cm/wordsInfo")
public class WordsInfoController  extends BaseController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(WordsInfoController.class);

	@Resource
	private IWordsInfoService wordsInfoService;			//话术服务
	@Resource
	private IWordsTypeService wordsTypeService;			//话术类型服务
	@Resource
	private IImSensitiveWordsService imSensitiveWordsService;			//敏感词服务
	@Resource
	private OfficeService officeService;
	
	
	/**
	 * 
	 *
	 * 方法说明：话术列表
	 *
	 * @param findWordsInfoPage
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	@RequiresPermissions("cm:wordsInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(FindWordsInfoPage findWordsInfoPage,Integer pageNo,Integer pageSize, Model model) {
		try {
			
			if(pageNo!=null){
				findWordsInfoPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findWordsInfoPage.setLimit(pageSize);
			}
			findWordsInfoPage.setMerchantNo(UserUtils.getMerchantNo());
			if(null != findWordsInfoPage.getMerchantNo()){
				findWordsInfoPage.setMemberNoGm(UserUtils.getUser().getId());
			}
			Page<FindWordsInfoPageReturn> pageDto = wordsInfoService.findWordsInfoPage(findWordsInfoPage);
			List<FindWordsInfoPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			
			if(!list.isEmpty()){
				for (FindWordsInfoPageReturn findWordsInfoPageReturn : list) {
					Office office = officeService.findOffice(findWordsInfoPageReturn.getMerchantNo());
					if(null != office){
						findWordsInfoPageReturn.setOfficeName(office.getName());
					}
				}
			}   
			com.ape.common.persistence.Page<FindWordsInfoPageReturn> page=new com.ape.common.persistence.Page<FindWordsInfoPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findWordsInfoPage",findWordsInfoPage);
			model.addAttribute("merchantNo",findWordsInfoPage.getMerchantNo());
			//话术类型下拉数据
			FindWordsInfoWeb findWordsInfoWeb=new FindWordsInfoWeb();
			findWordsInfoWeb.setMerchantNo(UserUtils.getMerchantNo());
			if(null != findWordsInfoWeb.getMerchantNo()){
				findWordsInfoWeb.setMemberNoGm(UserUtils.getUser().getId());
			}
			List<FindWordsTypeSelectReturn> typeList=wordsTypeService.findWordsTypes(findWordsInfoWeb);
			model.addAttribute("typeList",typeList);
			
		} catch (Exception e) {
			logger.error("获取话术信息错误！",e);
		}
		return "modules/cm/wordsInfoList";
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查看话术
	 *
	 * @param findWordsInfo
	 * @param model
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	@RequiresPermissions("cm:wordsInfo:view")
	@RequestMapping(value = "form")
	public String form(FindWordsInfo findWordsInfo, Model model) {
		try {
			if(findWordsInfo!=null && findWordsInfo.getCode()!=null){
				if(StringUtils.isNotBlank(findWordsInfo.getCode())){
					FindWordsInfoReturn findWordsInfoReturn= wordsInfoService.findWordsInfo(findWordsInfo);
					model.addAttribute("data",findWordsInfoReturn);
				}else{
					//回显添加失败时的信息
					model.addAttribute("data", findWordsInfo);
				}
			}
			//话术类型
			FindWordsInfoWeb findWordsInfoWeb=new FindWordsInfoWeb();
			findWordsInfoWeb.setMerchantNo(UserUtils.getMerchantNo());
			if(null != findWordsInfoWeb.getMerchantNo()){
				findWordsInfoWeb.setMemberNoGm(UserUtils.getUser().getId());
			}
			List<FindWordsTypeSelectReturn> typeList=wordsTypeService.findWordsTypes(findWordsInfoWeb);
			model.addAttribute("typeList",typeList);
		} catch (Exception e) {
			logger.error("获取话术信息错误！",e);
		}
		
		return "modules/cm/wordsInfoForm";
	}
	
	/**
	 * 
	 *
	 * 方法说明：保存话术
	 *
	 * @param addWordsInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	@RequiresPermissions("cm:wordsInfo:edit")
	@RequestMapping(value = "save")
	public String save(AddWordsInfo addWordsInfo, Model model, RedirectAttributes redirectAttributes) {
		FindWordsInfo findWordsInfo=new FindWordsInfo();
		try {
			int i=addWordsInfo.getContent().getBytes("utf-8").length;
			if(i>300){
				model.addAttribute("repMsg", "保存话术失败,当前话术内容超出100个字符数,请缩小内容！");
				BeanUtils.copyProperties(addWordsInfo, findWordsInfo);
				return form(findWordsInfo,model);
			}
			
			if(imSensitiveWordsService.contains(addWordsInfo.getContent())){
				model.addAttribute("repMsg", "保存话术失败,当前话术内容含有敏感词无法保存");
				model.addAttribute("type", "error");
				BeanUtils.copyProperties(addWordsInfo, findWordsInfo);
				return form(findWordsInfo,model);
			}
			
			addWordsInfo.setMerchantNo(UserUtils.getMerchantNo());
			addWordsInfo.setMemberNoGm(UserUtils.getUser().getId());
			addWordsInfo.setCreateId(UserUtils.getUser().getName());
			
			//如果默认话术数量大于等于四条,且本次新增话术为默认状态
			Integer count=wordsInfoService.findDefaultCount(addWordsInfo.getMemberNoGm());
			if("1".equals(addWordsInfo.getDefaultFlag().toString())&&count!=null&&count>=4){
				model.addAttribute("repMsg", "保存话术失败,默认话术最多设置4条");
				BeanUtils.copyProperties(addWordsInfo, findWordsInfo);
				return form(findWordsInfo,model);
			}
			//HTML特殊字符转义
			String content= StringEscapeUtils.unescapeHtml4(addWordsInfo.getContent()).toString();
			addWordsInfo.setContent(content);
			wordsInfoService.addWordsInfo(addWordsInfo);
			addMessage(redirectAttributes, "保存话术成功");
		} catch (Exception e) {
			logger.error("保存话术信息错误！",e);
		}
		return "redirect:" + adminPath + "/cm/wordsInfo/";
	}
	
	/**
	 * 
	 *
	 * 方法说明：编辑话术
	 *
	 * @param updateWordsInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	@RequiresPermissions("cm:wordsInfo:edit")
	@RequestMapping(value = "edit")
	public String edit(UpdateWordsInfo updateWordsInfo, Model model, RedirectAttributes redirectAttributes) {
		FindWordsInfoPage findWordsInfoPage=new FindWordsInfoPage();
		FindWordsInfo findWordsInfo=new FindWordsInfo();
		try {
			if(imSensitiveWordsService.contains(updateWordsInfo.getContent())){
				model.addAttribute("repMsg", "修改话术失败,当前话术内容含有敏感词无法保存");
				BeanUtils.copyProperties(updateWordsInfo, findWordsInfo);
				return form(findWordsInfo,model);
			}
			
			//当非默认变为默认才校验数量
			findWordsInfo.setCode(updateWordsInfo.getCode());
			FindWordsInfoReturn findWordsInfoReturn = wordsInfoService.findWordsInfo(findWordsInfo);
			if("0".equals(findWordsInfoReturn.getDefaultFlag().toString())&&"1".equals(updateWordsInfo.getDefaultFlag().toString())){
				Integer count=wordsInfoService.findDefaultCount(UserUtils.getUser().getCompany().getId());
				if(count!=null&&count>=4){
					model.addAttribute("repMsg", "修改话术失败,默认话术最多设置4条");
					BeanUtils.copyProperties(updateWordsInfo, findWordsInfo);
					return form(findWordsInfo,model);
				}
			}
			
			//HTML特殊字符转义
			String content= StringEscapeUtils.unescapeHtml4(updateWordsInfo.getContent()).toString();
			updateWordsInfo.setContent(content);
			wordsInfoService.updateWordsInfo(updateWordsInfo);
			model.addAttribute("repMsg", "修改话术成功");
		} catch (Exception e) {
			logger.error("修改话术信息错误！",e);
		}
		return list(findWordsInfoPage, null, null, model);
	}
	
	/**
	 * 
	 *
	 * 方法说明：设为/取消 默认
	 *
	 * @param updateWordsInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月09日
	 *
	 */
	@RequiresPermissions("cm:wordsInfo:edit")
	@RequestMapping(value = "defaultFlag")
	public String defaultFlag(UpdateWordsInfo updateWordsInfo, Model model, RedirectAttributes redirectAttributes) {
		FindWordsInfoPage findWordsInfoPage=new FindWordsInfoPage();
		FindWordsInfo findWordsInfo=new FindWordsInfo();
		try {
			
			//当设为默认才校验数量
			findWordsInfo.setCode(updateWordsInfo.getCode());
			FindWordsInfoReturn findWordsInfoReturn = wordsInfoService.findWordsInfo(findWordsInfo);
			if("0".equals(findWordsInfoReturn.getDefaultFlag().toString())&&"1".equals(updateWordsInfo.getDefaultFlag().toString())){
				Integer count=wordsInfoService.findDefaultCount(UserUtils.getUser().getCompany().getId());
				if(count!=null&&count>=4){
					model.addAttribute("repMsg", "编辑话术默认信息失败,默认话术最多设置4条");
					return list(findWordsInfoPage, null, null, model);
				}
			}
			
			wordsInfoService.updateWordsInfo(updateWordsInfo);
			model.addAttribute("repMsg", "编辑话术默认信息成功");
		} catch (Exception e) {
			logger.error("修改话术信息错误！",e);
		}
		return list(findWordsInfoPage, null, null, model);
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除话术
	 *
	 * @param delWordsInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月11日
	 *
	 */
	@RequiresPermissions("cm:wordsInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(DelWordsInfo delWordsInfo, Model model, RedirectAttributes redirectAttributes) {
		FindWordsInfoPage findWordsInfoPage=new FindWordsInfoPage();
		try {
			wordsInfoService.delWordsInfo(delWordsInfo);
			model.addAttribute("repMsg", "删除话术成功");
		} catch (Exception e) {
			logger.error("删除话术信息错误！",e);
		}
		return list(findWordsInfoPage, null, null, model);
	}
	
}
