package com.lj.oms.cp;

import java.util.Date;
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
import com.ape.common.utils.DateUtils;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.cp.dto.couponRule.AddCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRulePage;
import com.lj.business.cp.dto.couponRule.FindCouponRulePageReturn;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.dto.couponRule.UpdateCouponRule;
import com.lj.business.cp.dto.couponRuleEx.AddCouponRuleEx;
import com.lj.business.cp.dto.couponType.FindCouponType;
import com.lj.business.cp.dto.couponType.FindCouponTypeReturn;
import com.lj.business.cp.emus.IsProduce;
import com.lj.business.cp.emus.RealName;
import com.lj.business.cp.emus.UseEnable;
import com.lj.business.cp.service.ICouponRuleExService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.cp.service.ICouponTypeService;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.UserUtils;


/**
 * 
 * 
 * 类说明：优惠券规则管理
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 * 
 * CreateDate: 2017年9月15日
 */

@Controller
@RequestMapping(value = "${adminPath}/cp/coupon/rule")
public class CouponRuleController  extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(CouponRuleController.class);
	
	/**
	 * 优惠券规则服务
	 */
	@Resource
	private ICouponRuleService couponRuleService;
	/**
	 * 优惠券类型服务
	 */
	@Resource
	private ICouponTypeService couponTypeService;
	/**
	 * 终端服务
	 */
//	@Autowired
//	private IShopService shopService;
	
	@Resource
	private ICouponRuleExService couponRuleExService;
	
	@Resource
	private ICouponService couponService; 
	
	/**
	 * 返回参数
	 */
	private static final String PAGE = "page";
	
	/**
	 * 列表地址
	 */
	private static final String LIST = "modules/cp/couponRuleList";
	/**
	 * 终端编号集合
	 */
	private static final String SHOP_NO = "shops";
	/**
	 * 返回前端参数定义
	 */
	private static final String DATA = "data";
	/**
	 * 重定向路径
	 */
	private static final String EDIT = "redirect:" +Global.getAdminPath() + "/cp/coupon/rule";
	/**
	 * 编辑页面路径
	 */
	private static final String FROM = "modules/cp/couponRuleForm";
	/**
	 * 终端绑定页面
	 */
	private static final String SHOP_BIND = "modules/cp/couponShopBind";
	/**
	 * 终端状态
	 */
	private static final String SHOP_STATUS = "shopStatus";
	/**
	 * 前端回显参数
	 */
	private static final String REQ_PARAM ="reqParam";
	
	private static final String COUPON_TYPES = "couponTypes";
	

	/**
	 * 
	 *
	 * 方法说明：优惠券规则列表
	 *
	 * @return 返回分页后的数据展现
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月15日
	 *
	 */
	@RequestMapping(value={"list", ""})
	public String  list(Model model,Integer pageNo,Integer pageSize,FindCouponRulePage findCouponRulePage ){ 
		try {
			if(pageNo!=null){
				findCouponRulePage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize !=null){
				findCouponRulePage.setLimit(pageSize);
			}
			findCouponRulePage.setMerchantNo(UserUtils.getMerchantNo());
			Page<FindCouponRulePageReturn> pages=couponRuleService.findCouponRulePage(findCouponRulePage);
			List<FindCouponRulePageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindCouponRulePageReturn> page=new com.ape.common.persistence.Page<FindCouponRulePageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list); 
			page.initialize();
			model.addAttribute(PAGE,page);
			
			model.addAttribute("findCouponRulePage",findCouponRulePage);
		} catch (Exception e) {
			logger.error("获取优惠券规则信息错误！",e);
		}
		return LIST;
	}
	/**
	 * 
	 *
	 * 方法说明：优惠券规则修改
	 *
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月15日
	 *
	 */
	@RequiresPermissions("cp:couponRule:view")
	@RequestMapping(value="form")
	public String form(Model model,FindCouponRule findCouponRule){
		try {
			if(findCouponRule !=null && findCouponRule.getCode()!=null){
				FindCouponRuleReturn findCouponRuleReturn= couponRuleService.findCouponRule(findCouponRule);
				model.addAttribute(DATA, findCouponRuleReturn);
			}
			FindCouponType findCouponType=new FindCouponType();
			findCouponType.setUseEnable(UseEnable.YES.toString());
			findCouponType.setMerchantNo(UserUtils.getUser().getCompany().getId());
			List<FindCouponTypeReturn> couponTypes = couponTypeService.findCouponTypeList(findCouponType);
			
			model.addAttribute(COUPON_TYPES,couponTypes);
		} catch (Exception e) {
			logger.error("获取优惠券规则信息错误！");
		}
		return FROM;
	}
	
	/**
	 * 
	 * 
	 * 方法说明：新增保存方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param addCouponRule
	 * @return 保存新增数据后跳转页面
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月15日
	 *
	 */
	@RequiresPermissions("cp:couponRule:edit")
	@RequestMapping(value="save")
     public String save(Model model,RedirectAttributes redirectAttributes,AddCouponRule addCouponRule){
		try {
			String merchantNo = UserUtils.getUser().getCompany().getId();//商户编号
			String merchantName = UserUtils.getUser().getCompany().getName();//商户名称
			logger.debug("新增优惠券 开始执行"+addCouponRule);
			
			addCouponRule.setMerchantNo(merchantNo);
			addCouponRule.setMerchantName(merchantName);
			addCouponRule.setCreateId(merchantName);
			addCouponRule.setIsProduce(IsProduce.NO.toString());
			//BigDecimal转Double传值
			addCouponRule.setDoubleCouponMax(addCouponRule.getCouponMax().doubleValue());
			addCouponRule.setDoubleCouponNotes(addCouponRule.getCouponNotes().doubleValue());
			addCouponRule.setCouponNum(addCouponRule.getCouponAvgNum());
			
			if(addCouponRule.getCouponNum()<= 0){
				addMessage(redirectAttributes, "保存优惠券规则失败！优惠券数不能少于零！");
				return form(model,new FindCouponRule());
			}
			AddCouponRule couponRule =couponRuleService.addCouponRule(addCouponRule);
			couponRule.setCouponAvgNum(addCouponRule.getCouponAvgNum());
			
		
			//同步写入业务扩展信息
			AddCouponRuleEx addCouponRuleEx = new AddCouponRuleEx();
			addCouponRuleEx.setRuleCode(couponRule.getCode());
			addCouponRuleEx.setUseNum(1);//系统默认每种规则下只可以领一次
			if(addCouponRule.getRealName().equals(RealName.YES.toString())){
				addCouponRuleEx.setSurplusNum(addCouponRule.getCouponNum());
			}else{
				addCouponRuleEx.setSurplusNum(1);//默认一次
			}
			addCouponRuleEx.setCv(0);
			addCouponRuleEx.setPv(0); 
			addCouponRuleEx.setUv(0);
			couponRuleExService.addCouponRuleEx(addCouponRuleEx);
	
			//生成优惠券  如果执行时间不是当天则由调度任务生成券
			if(DateUtils.isSameDay(addCouponRule.getEnableDate(), new Date())&& addCouponRule.getRealName().equals(RealName.YES.toString())){
				int num = couponService.addCoupon(couponRule);
				if(num>0){
					//制券成功则更新状态
					UpdateCouponRule updateCouponRule = new UpdateCouponRule();
					updateCouponRule.setCode(couponRule.getCode());
					updateCouponRule.setIsProduce(IsProduce.YES.toString());
					updateCouponRule.setCouponNum(num);
					couponRuleService.updateCouponRule(updateCouponRule);
				}
			}
			addMessage(redirectAttributes, "保存优惠券规则'" +addCouponRule.getCouponName()+ "'成功"); 
		} catch (Exception e) {
			logger.error("保存优惠券规则信息错误！",e);
		}
		return  EDIT;				
     }
	
	/**
	 * 
	 *
	 * 方法说明：编辑保存方法
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param updateCouponRule
	 * @return 保存修改编辑后的数据后跳转页面
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月15日
	 *
	 */
	@RequiresPermissions("cp:couponRule:edit")
	@RequestMapping("edit")
	public String edit(Model model,RedirectAttributes redirectAttributes,UpdateCouponRule updateCouponRule){
		try {
		
			updateCouponRule.setUpdateId(UserUtils.getUser().getName());
			//BigDecimal转Double传值
			if(updateCouponRule.getCouponMax()!=null){
				updateCouponRule.setDoubleCouponMax(updateCouponRule.getCouponMax().doubleValue());
				updateCouponRule.setDoubleCouponNotes(updateCouponRule.getCouponNotes().doubleValue());
			}
			couponRuleService.updateCouponRule(updateCouponRule);
			addMessage(redirectAttributes, "保存优惠券规则'" +updateCouponRule.getCouponName()+ "'成功"); 
		} catch (Exception e) {
			logger.error("修改优惠券规则信息错误！",e);
		}
		return  EDIT;
	}
	/**
	 * 
	 *
	 * 方法说明：修改状态
	 *
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年2月2日
	 *
	 */
	@RequestMapping(value="updataStatus")
	public String updataStatus(Model model,UpdateCouponRule updateCouponRule,RedirectAttributes redirectAttributes){
		try {
			if(updateCouponRule.getCode() !=null){
				couponRuleService.updateCouponRule(updateCouponRule);
			}
			addMessage(redirectAttributes, "修改优惠券规则成功"); 
		} catch (Exception e) {
			
			logger.error("修改状态信息错误！",e);
		}
		return  EDIT;
	}
	
	
	/**
	 * 跳转绑定终端页面
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param findProductPage
	 * @return
	 */
	/*@RequestMapping(value = {"toBindShop"})
	@Deprecated
	public String toBindShop(Model model,Integer pageNo,Integer pageSize,FindShopPage findShopPage, String promotionCode){
		try {
			if(pageNo!=null){
				findShopPage.setStart((pageNo-1)*pageSize);
			}
			findShopPage.setLimit(Integer.MAX_VALUE);
		 
			findShopPage.setMemberNoMerchant(UserUtils.getUser().getCompany().getId());
//			Page<FindShopPageReturn> pageDto = shopService.findShopPage(findShopPage);
//			List<FindShopPageReturn> shopDtos = Lists.newArrayList();
//			shopDtos.addAll(pageDto.getRows());
			 
//		  	com.ape.common.persistence.Page<FindShopPageReturn> page=new com.ape.common.persistence.Page<FindShopPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), shopDtos);
//		  	page.initialize();
		  	model.addAttribute(SHOP_STATUS, ShopStatus.values());
//			model.addAttribute(PAGE, page);
			model.addAttribute(REQ_PARAM, findShopPage);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return SHOP_BIND;
	}*/
	
}
