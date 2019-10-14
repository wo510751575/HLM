package com.lj.oms.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.AddPmType;
import com.lj.business.member.dto.FindPmType;
import com.lj.business.member.dto.FindPmTypePage;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.FindPmTypeReturn;
import com.lj.business.member.dto.UpdatePmType;
import com.lj.business.member.emus.PmTypeDim;
import com.lj.business.member.service.IPmTypeService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;
/**
 * 
 * 
 * 类说明：客户分类Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年6月24日
 */
@Controller
@RequestMapping(value = "${adminPath}/member/pmType")
public class PmTyPeController  extends BaseController{
	

	@Resource
	private IPmTypeService pmTypeService;			//客户分类服务

	/**
	 * 
	 *
	 * 方法说明：客户分类查询列表
	 *
	 * @param model
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月27日
	 *
	 */
	@RequiresPermissions("member:pmType:view")
	@RequestMapping(value = {"list", ""})
	public String pmTypeList( Model model,Integer pageNo,Integer pageSize,FindPmTypePage findPmTypePage) {
		try {
			findPmTypePage.setMerchantNo(UserUtils.getMerchantNo());
			if (pageNo != null) {
				findPmTypePage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findPmTypePage.setLimit(pageSize);
			}
			Page<FindPmTypePageReturn> pageDto = pmTypeService.findPmTypePage(findPmTypePage);
			List<FindPmTypePageReturn> basePageReturns = Lists.newArrayList();
			basePageReturns.addAll(pageDto.getRows());
			com.ape.common.persistence.Page<FindPmTypePageReturn> page = new com.ape.common.persistence.Page<FindPmTypePageReturn>(pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), basePageReturns);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("findPmTypePage", findPmTypePage);

			// 客户分类类型
//			model.addAttribute("pmTypes", PmTypeType.values());
		} catch (Exception e) {
			logger.error("获取客户分类信息错误！", e);
		}
		return "modules/member/pmTypeList";
	}
	/**
	 * 
	 *
	 * 方法说明：客户分类表单提交
	 *
	 * @param findPmType
	 * @param model
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月27日
	 *
	 */
	@RequiresPermissions("member:pmType:view")
	@RequestMapping(value = "form")
	public String form(FindPmType findPmType, Model model) {
		try {
			if(findPmType!=null && findPmType.getCode()!=null){
				 FindPmTypeReturn findPmTypeReturn = pmTypeService.findPmType(findPmType);
				model.addAttribute("data",findPmTypeReturn);
			}
			//客户分类类型
//			model.addAttribute("pmTypes",PmTypeType.values());
		} catch (Exception e) {
			logger.error("获取客户分类信息错误！", e);
		}
		return "modules/member/pmTypeForm";
	}
	/**
	 * 
	 *
	 * 方法说明：保存客户分类
	 *
	 * @param addPmType
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月24日
	 *
	 */
	@RequiresPermissions("member:pmType:edit")
	@RequestMapping(value="save")
	public String  save(AddPmType addPmType,Model model,RedirectAttributes redirectAttributes){
		try {
			//商户编号
			addPmType.setMerchantNo(UserUtils.getMerchantNo());
			addPmType.setPmTypeDim(PmTypeDim.MERCHANT.toString());
			//创建人
			addPmType.setCreateId(UserUtils.getUser().getName());
			addPmType.setPmTypeType(addPmType.getTypeName());
			pmTypeService.addPmType(addPmType);
			addMessage(redirectAttributes, "保存客户分类'" + addPmType.getTypeName() + "'成功");
		} catch (Exception e) {
			logger.error("保存客户分类信息错误！", e);
		}
		return "redirect:" + adminPath + "/member/pmType/";
		
	}
	/**
	 * 
	 *
	 * 方法说明：编辑客户分类
	 *
	 * @param updatePmType
	 * @param model
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月24日
	 *
	 */
	@RequiresPermissions("member:pmType:edit")
	@RequestMapping(value="edit")
	public String edit(UpdatePmType updatePmType,Model model,RedirectAttributes redirectAttributes){
		try {
			pmTypeService.updatePmType(updatePmType);
			addMessage(redirectAttributes, "修改客户分类'" + updatePmType.getTypeName() + "'成功");
		} catch (Exception e) {
			logger.error("修改客户分类信息错误！", e);
		}
		return "redirect:" + adminPath + "/member/pmType/";
	}
}
