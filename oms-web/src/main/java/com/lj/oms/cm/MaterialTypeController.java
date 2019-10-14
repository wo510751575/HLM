package com.lj.oms.cm;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.cm.dto.AddMaterialType;
import com.lj.business.cm.dto.FindMaterialType;
import com.lj.business.cm.dto.FindMaterialTypePage;
import com.lj.business.cm.dto.FindMaterialTypePageReturn;
import com.lj.business.cm.dto.FindMaterialTypeReturn;
import com.lj.business.cm.dto.UpdateMaterialType;
import com.lj.business.cm.service.IMaterialTypeService;
import com.lj.oms.common.BaseController;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.utils.UserUtils;
/**
 * 
 * 
 * 类说明：素材类型服务类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月14日
 */
@Controller
@RequestMapping(value = "${adminPath}/business/materialtype")
public class MaterialTypeController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(MaterialTypeController.class);
	

	/**
	 * 素材类型服务
	 */
	@Resource
	private IMaterialTypeService materialtypeService;
	@Resource
	private OfficeService officeService;
	
	/**
	 * 
	 *
	 * 方法说明：进行分页数据转换
	 * 素材类型表
	 * @param model
	 * @return 返回分页数据，OMS进行数据展现
	 *
	 * @author 罗书明 CreateDate: 2017年6月26日
	 *
	 */

	@RequestMapping(value = {"list", ""})
	public String list( Model model,Integer pageNo,Integer pageSize,FindMaterialTypePage findMaterialTypePage) {
		try {
			if(pageNo!=null){
				findMaterialTypePage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMaterialTypePage.setLimit(pageSize);
			}
			findMaterialTypePage.setMerchantNo(UserUtils.getMerchantNo());
			if(null != findMaterialTypePage.getMerchantNo()){
				findMaterialTypePage.setMemberNoGm(UserUtils.getUser().getLoginName());
			}
			Page<FindMaterialTypePageReturn> pages = materialtypeService.findMaterialTypePage(findMaterialTypePage);
		    List<FindMaterialTypePageReturn> list = Lists.newArrayList();
		    list.addAll(pages.getRows());
		    //如果当前用户角色为总管账号,商户号为空,要显示创建机构,创建人信息
			if(!list.isEmpty()){
				for (FindMaterialTypePageReturn findMaterialTypePageReturn : list) {
					Office office = officeService.findOffice(findMaterialTypePageReturn.getMerchantNo());
					if(null != office){
						findMaterialTypePageReturn.setOfficeName(office.getName());
					}
				}
			} 
		    com.ape.common.persistence.Page<FindMaterialTypePageReturn> page=new com.ape.common.persistence.Page<FindMaterialTypePageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list);		
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("merchantNo",findMaterialTypePage.getMerchantNo());
		} catch (Exception e) {
			logger.error("获取素材类型错误！");
		}
		return "modules/business/materialtypeList";
	}
	
	/**
	 * 
	 *
	 * 方法说明：素材类型编辑页面数据
	 *
	 * @param findMaterialType
	 * @param model 
	 * @return 返回编辑页面数据
	 *
	 * @author 罗书明 CreateDate: 2017年6月26日
	 *
	 */
	@RequestMapping(value = "form")
	public String form(FindMaterialType findMaterialType, Model model) {
		try {
			if(findMaterialType!=null && findMaterialType.getCode()!=null){
				FindMaterialTypeReturn FindMaterialReturn= materialtypeService.findMaterialType(findMaterialType);
				model.addAttribute("data",FindMaterialReturn);
			}
		} catch (Exception e) {
			logger.error("获取素材类型错误！");
		}	
		return "modules/business/materialtypeForm";
	}
	 
	/**
	 * 
	 *
	 * 方法说明：新增 素材类型数据保存方法
	 *
	 * @param addMaterialType
	 * @param model
	 * @param redirectAttributes
	 * @return 保存成功后跳转页面
	 * 
	 * @author 罗书明 CreateDate: 2017年6月26日
	 *
	 */
	@RequestMapping(value = "save")
	public String save(AddMaterialType addMaterialType, Model model, RedirectAttributes redirectAttributes) {
		try {
			addMaterialType.setMerchantNo(UserUtils.getMerchantNo());
			addMaterialType.setMemberNoGm(UserUtils.getUser().getLoginName());
			addMaterialType.setMemberNameGm(UserUtils.getUser().getName());
			addMaterialType.setCreateId(UserUtils.getUser().getName());
			materialtypeService.addMaterialType(addMaterialType);
			addMessage(redirectAttributes, "保存素材'" + addMaterialType.getTypeName() + "'成功");
		} catch (Exception e) {
			logger.error("保存素材类型错误！");
		}
		return "redirect:" + adminPath + "/business/materialtype/";
	}
	
	/**
	 * 
	 *
	 * 方法说明：素材类型编辑修改
	 * 
	 * @param updateMaterialType
	 * @param model
	 * @param redirectAttributes
	 * @return 保存数据成功后跳转页面
	 *
	 * @author 罗书明 CreateDate: 2017年6月26日
	 *
	 */
	@RequestMapping(value = "edit")
	public String edit(UpdateMaterialType updateMaterialType, Model model, RedirectAttributes redirectAttributes) {
		try {
			FindMaterialType findMaterialType = new FindMaterialType();
			findMaterialType.setCode(updateMaterialType.getCode());
			findMaterialType.setMerchantNo(UserUtils.getMerchantNo());
			FindMaterialTypeReturn findMaterialTypeReturn = materialtypeService.findMaterialType(findMaterialType);
			materialtypeService.updateMaterialType(updateMaterialType,findMaterialTypeReturn);
			addMessage(redirectAttributes, "保存素材'" + updateMaterialType.getTypeName() + "'成功");
		} catch (Exception e) {
			logger.error("修改素材类型错误！");
		}
		return "redirect:" + adminPath + "/business/materialtype/";
	}

}
