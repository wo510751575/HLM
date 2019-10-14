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
import com.ape.common.utils.StringUtils;
import com.lj.oms.common.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.GUID;
import com.lj.business.member.dto.AddInterestPmDto;
import com.lj.business.member.dto.EditInterestPmDto;
import com.lj.business.member.dto.FindInterestPmPageDto;
import com.lj.business.member.dto.InterestPmReturnDto;
import com.lj.business.member.service.IInterestPmService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：客户兴趣指数
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
@RequestMapping(value="${adminPath}/baseConfig/interestPm")
public class IInterestPmController   extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(IInterestPmController.class);
	
	/**  客户兴趣指数列表页面 **/
	private final static String PAGE_VIEW_INTEREST_PM_LIST = "modules/baseConfig/interestPmList";
	/**  客户兴趣指数编辑页面 **/
	private final static String PAGE_VIEW_INTEREST_PM_FORM = "modules/baseConfig/interestPmForm";
	/**  重定向到客户兴趣指数列表页面 **/
	private final static String PAGE_VIEW_REDIRECT_INTEREST_PM = "redirect:"+Global.getAdminPath()+ "/baseConfig/interestPm";
	
   /**
    * 客户兴趣指数服务
    */
	@Resource
	private IInterestPmService interestPmService; 
	
	/**
	 * 
	 *
	 * 方法说明：客户兴趣指数列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findInterestPmPageDto
	 * @return 服务提供分页技术,返回分页数据展现
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list( Model model ,Integer pageNo,Integer pageSize,FindInterestPmPageDto findInterestPmPageDto) {
		try {
			findInterestPmPageDto.setMerchantNo(UserUtils.getMerchantNo());
			if(pageNo !=null){
				findInterestPmPageDto.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findInterestPmPageDto.setLimit(pageSize);
			}
			//分页转换
			Page<FindInterestPmPageDto>  pages=interestPmService.findInterestPmPage(findInterestPmPageDto);
			List<FindInterestPmPageDto> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindInterestPmPageDto> page	=new com.ape.common.persistence.Page<FindInterestPmPageDto>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list); 
			
			page.initialize();
			model.addAttribute("page",page);
			model.addAttribute("findInterestPmPageDto", findInterestPmPageDto);
		} catch (Exception e) {
			logger.error("查询客户兴趣信息错误！", e);
		}

		return PAGE_VIEW_INTEREST_PM_LIST;
	}
	
    /**
     * 
     *
     * 方法说明：客户兴趣指数编辑页数据
     *
     * @param model
     * @param findInterestPmPageDto
     * @return 返回编辑页面数据
     *
     * @author 罗书明 CreateDate: 2017年7月14日
     *
     */
	@RequiresPermissions("baseConfig:interestPm:view")
	@RequestMapping(value = "form")
	public String form(Model model,FindInterestPmPageDto findInterestPmPageDto){
		if(findInterestPmPageDto !=null && StringUtils.isNotBlank(findInterestPmPageDto.getCode())){
			try {
				InterestPmReturnDto editInterestPmDto =interestPmService.selectByCode(findInterestPmPageDto.getCode());
				model.addAttribute("data", editInterestPmDto);
			} catch (Exception e) {
				logger.error("查询客户兴趣信息错误！", e);
			}	
		}
		return PAGE_VIEW_INTEREST_PM_FORM;
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户兴趣指数数据保存方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param interestPm
	 * @return 保存成功条跳转页面
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("baseConfig:interestPm:edit")
	@RequestMapping(value="save")
	public String save(Model model,RedirectAttributes redirectAttributes,AddInterestPmDto  interestPm){
		try {
			interestPm.setCode(GUID.getPreUUID());
			interestPm.setMerchantNo(UserUtils.getMerchantNo());
			interestPmService.addInterestPm(interestPm);
			addMessage(redirectAttributes, "保存兴趣'" + interestPm.getName() + "'成功！");
		} catch (Exception e) {
			logger.error("保存客户兴趣信息错误！", e);
		}	
		return PAGE_VIEW_REDIRECT_INTEREST_PM;				
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户兴趣指数编辑修改方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param interestPm
	 * @return 保存成功跳转页面
	 *
	 * @author 罗书明 CreateDate: 2017年7月14日
	 *
	 */
	@RequiresPermissions("baseConfig:interestPm:edit")
	@RequestMapping(value="edit")
	public String edit(Model model,RedirectAttributes redirectAttributes,EditInterestPmDto  interestPm){
		try {
			interestPmService.editInterestPm(interestPm);
			addMessage(redirectAttributes, "保存兴趣'" + interestPm.getName() + "'成功！");	
		} catch (Exception e) {
			logger.error("修改客户兴趣信息错误！", e);
		}			
		return PAGE_VIEW_REDIRECT_INTEREST_PM;	
	}
}