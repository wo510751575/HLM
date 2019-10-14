package com.lj.oms.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.member.dto.AddAgreementMerchantDto;
import com.lj.business.member.dto.AgreementMerchantReturnDto;
import com.lj.business.member.dto.EditAgreementMerchantDto;
import com.lj.business.member.dto.FindAgreementMerchantPageDto;
import com.lj.business.member.emus.AgreementType;
import com.lj.business.member.service.IAgreementMerchantService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：商户服务协议
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月3日
 */
@Controller
@RequestMapping(value = "${adminPath}/baseConfig/agreementMerchant")
public class AgreementMerchantController  extends BaseController{
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AgreementMerchantController.class);
	
	/**   商户服务协议列表页面 **/
	private final static String PAGE_VIEW_AGREEMENT_MERCHANT_LIST = "modules/baseConfig/agreementMerchantList";
	/**   商户服务协议编辑页面 **/
	private final static String PAGE_VIEW_AGREEMENT_MERCHANT_FORM = "modules/baseConfig/agreementMerchantForm";
	/**   重定向到商户服务协议列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_AGREEMENT_MERCHANT = "redirect:"+Global.getAdminPath()+ "/baseConfig/agreementMerchant";
	
	/**
	 * 商户服务协议服务
	 */
	@Resource           
	private IAgreementMerchantService agreementMerchantService;
	
	
	/**
	 * 
	 *
	 * 方法说明：商户服务协议列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param agreementMerchantPageDto
	 * @return	服务提供分页，转换OMS分页列表
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list( Model model,Integer pageNo,Integer pageSize,FindAgreementMerchantPageDto agreementMerchantPageDto ) {
		try {
			agreementMerchantPageDto.setMerchantNo(UserUtils.getMerchantNo());
			if(pageNo !=null){
				agreementMerchantPageDto.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				agreementMerchantPageDto.setLimit(pageSize);
			}
			Page<FindAgreementMerchantPageDto> pages =agreementMerchantService.findAgreementMerchantPage(agreementMerchantPageDto);
			List<FindAgreementMerchantPageDto> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindAgreementMerchantPageDto> page=new com.ape.common.persistence.Page<FindAgreementMerchantPageDto>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list); 
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("AgreementTypes",AgreementType.values());
		} catch (Exception e) {
			logger.error("获取商户数据错误",e);
		}
		return PAGE_VIEW_AGREEMENT_MERCHANT_LIST;
	}
	
	/**
	 * 
	 *
	 * 方法说明：form页面展现
	 *
	 * @param model
	 * @param findAgreementMerchantPageDto
	 * @return 编辑页面数据
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("baseConfig:agreementMerchant:view")
	@RequestMapping(value = "form")
	public String Form(Model model,FindAgreementMerchantPageDto findAgreementMerchantPageDto){
		try {
			if(findAgreementMerchantPageDto !=null && findAgreementMerchantPageDto.getCode()!= null){
				AgreementMerchantReturnDto editAgreementMerchantDto =	agreementMerchantService.selectByCode(findAgreementMerchantPageDto.getCode());
				model.addAttribute("data", editAgreementMerchantDto);		
			}
			model.addAttribute("AgreementType",AgreementType.values());
		} catch (Exception e) {
			logger.error("查询商户数据错误",e);
		}
	
		return PAGE_VIEW_AGREEMENT_MERCHANT_FORM;
	}
	
	/**
	 * 
	 *
	 * 方法说明：商户服务协议保存
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param agreementMerchant
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("baseConfig:agreementMerchant:edit")
	@RequestMapping(value="save")
	public String save(Model model,RedirectAttributes redirectAttributes,AddAgreementMerchantDto agreementMerchant ){
		try {
			agreementMerchant.setMerchantNo(UserUtils.getMerchantNo());
			agreementMerchant.setCreateId(UserUtils.getUser().getName());
			agreementMerchant.setUpdateId(UserUtils.getUser().getName());
			agreementMerchantService.addAgreementMerchant(agreementMerchant);
			
			addMessage(redirectAttributes, "保存协议'" + agreementMerchant.getAgreement() + "'成功");
		} catch (Exception e) {
			logger.error("保存商户数据错误",e);
		}
		return PAGE_VIEW_REDIRECT_AGREEMENT_MERCHANT;
		
	}
	/**
	 * 
	 *
	 * 方法说明：商户服务协议编辑
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param agreementMerchant
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("baseConfig:agreementMerchant:edit")
	@RequestMapping(value="edit")
	public String edit(Model model,RedirectAttributes redirectAttributes,EditAgreementMerchantDto agreementMerchant){
		try {
			agreementMerchantService.editAgreementMerchant(agreementMerchant);
			addMessage(redirectAttributes, "修改协议'" + agreementMerchant.getAgreement() + "'成功");
		} catch (Exception e) {
			logger.debug("修改商户数据错误",e);
		}	
		return PAGE_VIEW_REDIRECT_AGREEMENT_MERCHANT;
		
	}


}
