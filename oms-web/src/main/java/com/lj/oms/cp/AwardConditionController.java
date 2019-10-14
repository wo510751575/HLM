package com.lj.oms.cp;

import java.util.List;

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
import com.lj.business.cp.dto.awardCondition.AddAwardCondition;
import com.lj.business.cp.dto.awardCondition.FindAwardCondition;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionPage;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionPageReturn;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionReturn;
import com.lj.business.cp.dto.awardCondition.UpdateAwardCondition;
import com.lj.business.cp.service.IAwardConditionService;
import com.lj.oms.utils.CcUtils;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：优惠券奖励条件
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
@RequestMapping(value = "${adminPath}/cp/awardCondition")
public class AwardConditionController {
	
	private static final Logger logger = LoggerFactory.getLogger(AwardConditionController.class);
	
	private IAwardConditionService awardConditionService;
	
	/**列表返回地址*/
	private static final String LIST = "modules/cp/awardConditionList";
	/**编辑页面地址*/
	private static final String FROM = "modules/cp/awardConditionForm";
	/**重定向*/
	private static final String EDIT = "redirect:" + Global.getAdminPath() + "/cp/awardCondition/";
	/**商户编号*/
	private static final String MERCHANT_NO = "merchantNo";
	/**终端集合*/
	private static final String SHOP_NO_S   = "shopNos";
	/**返回分页参数*/
	private static final String PAGE = "page";  
	
	
	
	/**
	 * 
	 *
	 * 方法说明：分页查询列表
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月16日
	 *
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Model model,Integer pageNo,Integer pageSize,FindAwardConditionPage findAwardConditionPage){
		try {
			if(pageNo!=null){
				findAwardConditionPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findAwardConditionPage.setLimit(pageSize);
			}
			/**获取缓存用户信息*/
			findAwardConditionPage.setMerchantNo(UserUtils.getMerchantNo());
//			List<String> shopList = CcUtils.getShopNoList();
//			if(shopList.size() > 0){
//				findAwardConditionPage.setShopNos(shopList);
//			}
			//分页
			Page<FindAwardConditionPageReturn>  pages=awardConditionService.findAwardConditionPage(findAwardConditionPage);
			List<FindAwardConditionPageReturn> list=Lists.newArrayList();
			list.addAll(pages.getRows());
			//组装参数
			com.ape.common.persistence.Page<FindAwardConditionPageReturn> page=new com.ape.common.persistence.Page<FindAwardConditionPageReturn>(pageNo==null?1:pageNo, pages.getLimit(), pages.getTotal(), list); 
			page.initialize();
		   model.addAttribute(PAGE,page);
		   model.addAttribute("findAwardConditionPage", findAwardConditionPage);
		} catch (Exception e) {
			logger.error("获取优惠券奖励信息错误！");
		}
		return LIST;
	}
	 /**
	  * 编辑页面数据
	  * @param model
	  * @param findAwardCondition
	  * @return
	  */
	 @RequiresPermissions("cp:couponType:view")
	 @RequestMapping(value="form")
     public String form(Model model,FindAwardCondition findAwardCondition){
		try {
		 if(findAwardCondition!=null && findAwardCondition.getCode()!=null){
			 FindAwardConditionReturn findAwardConditionReturn= awardConditionService.findAwardCondition(findAwardCondition);
			 model.addAttribute("data", findAwardConditionReturn);
		 }
		} catch (Exception e) {
			logger.error("获取优惠券奖励信息错误！",e);
		}
		return FROM;
    }
	/**
	 * 新增方法
	 * @param redirectAttributes
	 * @param addAwardCondition
	 * @return
	 */
	@RequiresPermissions("cp:couponType:edit")
	@RequestMapping(value="save")
	public String save(RedirectAttributes redirectAttributes,AddAwardCondition addAwardCondition){
		try {
			addAwardCondition.setMerchantNo(UserUtils.getUser().getCompany().getId());
			addAwardCondition.setMerchantNo(UserUtils.getUser().getCompany().getName());
			awardConditionService.addAwardCondition(addAwardCondition);
		} catch (Exception e) {
			logger.error("新增优惠券奖励信息错误！",e);
		}
		return EDIT;
	}
	
	/**
	 * 修改方法
	 * @param redirectAttributes
	 * @param updateAwardCondition
	 * @return
	 */
	@RequiresPermissions("cp:couponType:edit")
	@RequestMapping(value="edit")
	public String edit(RedirectAttributes redirectAttributes,UpdateAwardCondition updateAwardCondition){
		try {
			awardConditionService.updateAwardCondition(updateAwardCondition);
		} catch (Exception e) {
			logger.error("修改优惠券奖励信息错误！",e);
		}
		return EDIT;
	}
}
