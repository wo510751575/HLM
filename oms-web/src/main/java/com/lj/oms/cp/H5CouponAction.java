package com.lj.oms.cp;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lj.oms.common.BaseController;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationVoReturn;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;

	/**
	 * 
	 * 
	 * 类说明：H5页面接口(旧版优惠券迁移)
	 * <p>
	 * 详细描述：
	 * 
	 * @Company: 扬恩科技有限公司
	 * @author 杨杰
	 * 
	 * @date: 2017年9月21日
	 */
@Controller
@RequestMapping(value = "${adminPath}/cp/h5coupon")
public class H5CouponAction {
		private static Logger LOGGER = LoggerFactory.getLogger(H5CouponController.class);
		@Resource
		private ICouponRuleService couponRuleService;
		@Resource
		private ICouponMemberRelationService couponMemberRelationService;
		@Resource
		private IMerchantService merchantService;
		@Resource
		private LocalCacheSystemParamsFromCC localCacheSystemParams;
		@Resource
		private ICouponService couponService;
		@Autowired
	    private IShopTerminalService shopTerminalService;
		
		/**
		 * 
		 *
		 * 方法说明：根据code获取优惠券规则(分享出去的)
		 *
		 * @param model
		 * @return
		 *
		 * @author 杨杰
		 * @date 2017年9月21日
		 *
		 */
		@RequestMapping(value = "findCouponRule")
		public String findCouponRule(Model model, FindCouponRule findCouponRule) {
			FindCouponRuleReturn findCouponRuleReturn = null;
			try {
				findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
				model.addAttribute("couponRule", findCouponRuleReturn);
			} catch (Exception e) {
				LOGGER.error("根据code获取优惠券规则error:{}", e);
			}
			return "modules/cp/findCouponRule";
		}

		/**
		 * 
		 *
		 * 方法说明：根据code获取优惠券规则(客户端的)
		 *
		 * @param model
		 * @return
		 *
		 * @author 杨杰
		 * @date 2017年9月21日
		 *
		 */
		@RequestMapping(value = "findCouponExplain")
		public String findCouponExplain(Model model, FindCouponRule findCouponRule) {
			FindCouponRuleReturn findCouponRuleReturn = null;
			try {
				findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);

				// 商户图片静态地址
				String uploadUrl = localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl");
				FindMerchantDto findMerchantDto = new FindMerchantDto();
				findMerchantDto.setMerchantNo(findCouponRuleReturn.getMerchantNo());
				FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
				if (findMerchantReturnDto != null) {
					findCouponRuleReturn.setMerchantLogoUrl(uploadUrl + findMerchantReturnDto.getLogoAddr());
				}

				model.addAttribute("couponRule", findCouponRuleReturn);
			} catch (Exception e) {
				LOGGER.error("根据code获取优惠券规则error:{}", e);
			}
			return "modules/cp/findCouponExplain";
		}

		/**
		 * 
		 *
		 * 方法说明：根据条件获取客户获取的优惠券
		 *
		 * @param memberNoGm = 电商版为中控微信
		 * @return
		 *
		 * @author 杨杰 CreateDate: 2017年9月18日
		 *
		 */
		@RequestMapping(value = "findCouponMemberRelation")
		public String findCouponMemberRelation(Model model, FindCouponMemberRelation findCouponMemberRelation) {
			FindCouponMemberRelationVoReturn couponMemberRelationVoReturn = null;
			try {
				couponMemberRelationVoReturn = couponMemberRelationService.findCouponMemberRelation(findCouponMemberRelation);
				model.addAttribute("couponMemberRelationVo", couponMemberRelationVoReturn);
			} catch (Exception e) {
				LOGGER.error("根据条件获取客户获取的优惠券error:{}", e);
			}
			return "modules/cp/findCouponMemberRelation";
		}
		
		
	}

