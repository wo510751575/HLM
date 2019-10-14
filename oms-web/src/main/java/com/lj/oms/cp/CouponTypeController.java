package com.lj.oms.cp;

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
import com.lj.business.cp.dto.couponType.AddCouponType;
import com.lj.business.cp.dto.couponType.FindCouponType;
import com.lj.business.cp.dto.couponType.FindCouponTypePage;
import com.lj.business.cp.dto.couponType.FindCouponTypePageReturn;
import com.lj.business.cp.dto.couponType.FindCouponTypeReturn;
import com.lj.business.cp.dto.couponType.UpdateCouponType;
import com.lj.business.cp.emus.CouponType;
import com.lj.business.cp.service.ICouponTypeService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：优惠券类型
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年9月16日
 */
@Controller
@RequestMapping(value = "${adminPath}/cp/couponType")
public class CouponTypeController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(CouponTypeController.class);
	
	@Resource
	private ICouponTypeService couponTypeService;
	
	/**
	 * 编辑页面路径
	 */
	private static final String FROM = "modules/cp/couponTypeForm";
	/**
	 * 列表页面路径
	 */
	private static final String LIST = "modules/cp/couponTypeList";
	
	private static final String REDIRECT ="redirect:" + Global.getAdminPath() + "/cp/couponType/";
	
	private static final String PAGE = "page";
	private static final String DATA = "data";
	/**
	 * 枚举定义
	 */
	private static final String COUPON_TYPES = "couponTypes";
	
	
	
	/**
	 * 
	 *
	 * 方法说明：分页查询
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findCouponTypePage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月16日
	 *
	 */
	@RequestMapping(value = {"list",""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindCouponTypePage findCouponTypePage){
		try {
			if(pageNo!=null){
				findCouponTypePage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findCouponTypePage.setLimit(pageSize);
			}
			findCouponTypePage.setMerchantNo(UserUtils.getUser().getCompany().getId());
			Page<FindCouponTypePageReturn> pages=couponTypeService.findCouponTypePage(findCouponTypePage);
			List<FindCouponTypePageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindCouponTypePageReturn> page=
					new com.ape.common.persistence.Page<FindCouponTypePageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list); 
		    page.initialize();
			model.addAttribute(PAGE,page);
			model.addAttribute(COUPON_TYPES,CouponType.values());
			model.addAttribute("findCouponTypePage", findCouponTypePage);
		} catch (Exception e) {
			logger.error("获取优惠券分类信息！",e);
		}
		return LIST;
	}
	@RequiresPermissions("cp:couponType:view")
	@RequestMapping(value="form")
	public String from(Model model,FindCouponType findCouponType){
		try {
			if(findCouponType!=null&& findCouponType.getCode()!=null){
				FindCouponTypeReturn findCouponTypeReturn= couponTypeService.findCouponType(findCouponType);
				model.addAttribute(DATA,findCouponTypeReturn);
			}
		} catch (Exception e) {
			logger.error("获取优惠券分类信息！",e);
		}
		 return FROM;
	}
	
	@RequiresPermissions("cp:couponType:edit")
	@RequestMapping (value="save")
	public String save(Model model,RedirectAttributes redirectAttributes,AddCouponType addCouponType){
		try {
			String type=addCouponType.getCouponType();
			addCouponType.setMerchantNo(UserUtils.getUser().getCompany().getId());
			addCouponType.setMerchantName(UserUtils.getUser().getCompany().getName());
			addCouponType.setCreateId(UserUtils.getUser().getCompany().getName());
			
			couponTypeService.addCouponType(addCouponType);
			 addMessage(redirectAttributes, "保存类型'" + type+ "'成功"); 
		} catch (Exception e) {
			logger.error("修改优惠券分类信息！",e);
		}
		return REDIRECT;
	}
	 
	@RequiresPermissions("cp:couponType:edit")
	@RequestMapping(value="edit")
	public String edit(Model model,UpdateCouponType updateCouponType,RedirectAttributes redirectAttributes){
		try {
			
			couponTypeService.updateCouponType(updateCouponType);
			 addMessage(redirectAttributes, "修改类型'" + updateCouponType.getCouponType() + "'成功"); 
		} catch (Exception e) {
			logger.error("新增优惠券分类信息！");
		}
		return REDIRECT;
	}
	
	
}
