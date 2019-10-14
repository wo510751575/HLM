package com.lj.business.api.controller.cp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cp.dto.AddCouponVoDto;
import com.lj.business.cp.dto.coupon.AddCouponVo;
import com.lj.business.cp.dto.coupon.EmployCoupon;
import com.lj.business.cp.dto.coupon.FindInviteCode;
import com.lj.business.cp.dto.coupon.FindInviteCodeReturn;
import com.lj.business.cp.dto.coupon.UpdateCoupon;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationVoReturn;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.dto.couponRuleEx.UpdateCouponRuleEx;
import com.lj.business.cp.dto.couponType.FindCouponType;
import com.lj.business.cp.dto.couponType.FindCouponTypeReturn;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.emus.RealName;
import com.lj.business.cp.emus.RuleStatus;
import com.lj.business.cp.emus.ToCoupon;
import com.lj.business.cp.emus.UseEnable;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleExService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.cp.service.ICouponTypeService;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;

/**
 * 
 * 
 * 类说明：优惠券APP接口和H5接口列表
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 杨杰
 * 
 *         CreateDate: 2017年9月18日
 */
@Controller
@RequestMapping(value = "/coupon/")
public class CouponInterfaceController {
	private static Logger LOGGER = LoggerFactory.getLogger(CouponInterfaceController.class);
	@Resource
	private ICouponService couponService;
	@Resource
	private ICouponTypeService couponTypeService;
	@Resource
	private ICouponRuleService couponRuleService;
	@Resource
	private ICouponMemberRelationService couponMemberRelationService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private IMerchantService merchantService;
	@Resource
	private  ICouponRuleExService couponRuleExService;
	@Resource
    private  IShopTerminalService shopTerminalService;
	
	private static final String COUPON_IS_NULL_ERROR = "coupon_is_null_error";
	
	private static final String COUPON_USED_ERROR = "coupon_used_error";
	
	private static final String COUPON_FIND_PAGE_ERROR_TIME = "coupon_find_page_error_time";

	/**
	 * 
	 *
	 * 方法说明：获取优惠券类型
	 *
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@RequestMapping(value = "findCouponTypeList.do")
	@ResponseBody
	public Object findCouponTypeList(FindCouponType findCouponType) {
		List<FindCouponTypeReturn> findCouponTypeList = null;
		try {
			findCouponType.setUseEnable(UseEnable.YES.toString());
			findCouponTypeList = couponTypeService.findCouponTypeList(findCouponType);
		} catch (Exception e) {
			LOGGER.error("获取优惠券类型error:{}", e);
		}
		return findCouponTypeList;
	}

	/**
	 * 
	 *
	 * 方法说明：获取优惠券规则
	 *
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@RequestMapping(value = "findCouponRuleList.do")
	@ResponseBody
	public Object findCouponRuleList(FindCouponRule findCouponRule) {
		List<FindCouponRuleReturn> findCouponRuleList = null;
		try {
			findCouponRule.setToCoupon(ToCoupon.NONE.toString());
			findCouponRule.setRealName(RealName.YES.toString());
			findCouponRule.setRuleStatus(RuleStatus.YES.toString());
			findCouponRule.setProduce("YES");
			// 客户端分享地址
			String cp_share_url = localCacheSystemParams.getSystemParam("cp", "new_share_url", "cp_new_share_url");
			// 客户端展示的地址
			String cp_client_url = localCacheSystemParams.getSystemParam("cp", "new_client_url", "cp_new_client_url");
			// 商户图片静态地址
			String uploadUrl = localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl");
			findCouponRuleList = couponRuleService.findCouponRuleList(findCouponRule);
			for (FindCouponRuleReturn findCouponRuleReturn : findCouponRuleList) {
				findCouponRuleReturn.setShareUrl(cp_share_url);
				findCouponRuleReturn.setClientUrl(cp_client_url);
				FindMerchantDto findMerchantDto = new FindMerchantDto();
				findMerchantDto.setMerchantNo(findCouponRuleReturn.getMerchantNo());
				FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
				if (findMerchantReturnDto != null && StringUtils.isNotEmpty(findMerchantReturnDto.getLogoAddr())) {
					findCouponRuleReturn.setMerchantLogoUrl(uploadUrl + findMerchantReturnDto.getLogoAddr());
				} else {
					findCouponRuleReturn.setMerchantLogoUrl(localCacheSystemParams.getSystemParam("api", "logo", "couponLogoUrl"));
				}
			}
			Collections.sort(findCouponRuleList, new Comparator<FindCouponRuleReturn>() {
				@Override
				public int compare(FindCouponRuleReturn o1, FindCouponRuleReturn o2) { // 数组倒序
					return (int) (o2.getCouponNotes() - o1.getCouponNotes());
				}

			});
		} catch (Exception e) {
			LOGGER.error("获取优惠券规则error:{}", e);
		}
		return findCouponRuleList;
	}

	/**
	 * 
	 *
	 * 方法说明：根据code获取优惠券规则
	 *
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@RequestMapping(value = "findCouponRule.do")
	@ResponseBody
	public Object findCouponRule(FindCouponRule findCouponRule) {
		FindCouponRuleReturn findCouponRuleReturn = null;
		try {
			findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
		} catch (Exception e) {
			LOGGER.error("根据code获取优惠券规则error:{}", e);
		}
		return findCouponRuleReturn;
	}

	/**
	 * 
	 *
	 * 方法说明：生成优惠券
	 *
	 * @param addCoupon
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "insertCoupon.do")
	@ResponseBody
	public Object insertCoupon(String paramJson) {
		LOGGER.info("传入参数json为：{}",paramJson);
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("couponList", AddCouponVo[].class);
		AddCouponVoDto addCouponVoDto = (AddCouponVoDto) JsonUtils.objectFromJson(paramJson, AddCouponVoDto.class, classMap);
		List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
		AddCouponVo[] addCouponVoList = addCouponVoDto.getCouponList();
		try {
			for (AddCouponVo addCouponVo : addCouponVoList) {
				Map<String, Object> resultMap = couponService.insertCoupon(addCouponVo);
				retList.add(resultMap);
			}
		} catch (Exception e) {
			LOGGER.error("生成优惠券error:{}", e);
		}
		return retList;
	}
   
	
	
	/**
	 * 
	 *
	 * 方法说明：核销优惠券
	 *
	 * @param updateCoupon
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@Deprecated
	@RequestMapping(value = "updateCouponByCouponNo.do")
	@ResponseBody
	public Object updateCouponByCouponNo(UpdateCoupon updateCoupon) {
		Map<String, String> retMap = new HashMap<String, String>();
		try {
			String returnStr = couponService.updateCouponByCouponNo(updateCoupon);
			if (StringUtils.isNotEmpty(returnStr)) {
				retMap.put("status", returnStr);
			}
		} catch (Exception e) {
			LOGGER.error("核销优惠券error:{}", e);
		}
		return retMap;
	}

	/**
	 * 
	 *
	 * 方法说明：获取导购核销优惠券列表
	 *
	 * @param updateCoupon
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@Deprecated
	@RequestMapping(value = "findCouponMemberRelationList.do")
	@ResponseBody
	public Object findCouponMemberRelationList(FindCouponMemberRelation findCouponMemberRelation) {
		try {
			findCouponMemberRelation.setCouponStatus(CouponStatus.USED.toString());
			couponMemberRelationService.findCouponMemberRelationList(findCouponMemberRelation);
		} catch (Exception e) {
			LOGGER.error("获取导购核销优惠券列表error:{}", e);
		}
		return null;
	}

	/**
	 * 
	 *
	 * 方法说明：根据条件获取客户获取的优惠券
	 *
	 * @param updateCoupon
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@Deprecated
	@RequestMapping(value = "findCouponMemberRelation.do")
	@ResponseBody
	public Object findCouponMemberRelation(FindCouponMemberRelation findCouponMemberRelation) {
		FindCouponMemberRelationVoReturn couponMemberRelationVoReturn = null;
		try {
			couponMemberRelationVoReturn = couponMemberRelationService.findCouponMemberRelation(findCouponMemberRelation);
		} catch (Exception e) {
			LOGGER.error("根据条件获取客户获取的优惠券error:{}", e);
		}
		return couponMemberRelationVoReturn;
	}
	

	
	/**
	 * 
	 *
	 * 方法说明：优惠券使用
	 *
	 * @return  CouponStatus
	 * UNUSED("未使用"),
	 * USED("已使用"),
	 * EXPIRED("已过期"),
	 * NONE("优惠券不存在"),
	 * IS_GET("已领取")
	 *
	 * @author 罗书明 CreateDate: 2018年1月29日
	 *
	 */
	@RequestMapping(value ="couponConsumption.do")
	@ResponseBody
	public String couponConsumption(EmployCoupon employCoupon){
		LOGGER.debug("couponConsumption(EmployCoupon employCoupon)",employCoupon);
		String couponStatus = null;
		try {
		 couponStatus = couponService.couponConsumption(employCoupon);
		} catch (TsfaServiceException e) {
		  if(e.getExceptionCode().equals(COUPON_IS_NULL_ERROR)){
			  couponStatus = CouponStatus.NONE.toString();
		  }else if(e.getExceptionCode().equals(COUPON_USED_ERROR)){
			  couponStatus = CouponStatus.USED.toString();
		  }else if(e.getExceptionCode().equals(COUPON_FIND_PAGE_ERROR_TIME)){
			  couponStatus = CouponStatus.EXPIRED.toString(); 
		  }
		}catch (Exception e) {
		 LOGGER.error("使用优惠券错误！",e);
		}
		return couponStatus;
				 
	}
	
	
	
	@RequestMapping(value = "findInviteCode.do")
    @ResponseBody
    public Map<String, Object> findInviteCode(FindInviteCode findInviteCode) {
        Map<String, Object> map = new HashMap<>(2);
        try {
            FindInviteCodeReturn findInviteCodeReturn = couponRuleService.findInviteCode(findInviteCode);//邀请码
            
            //终端终端二维码
            String qcord = "";
            FindShopTerminal findShopTerminal = new FindShopTerminal();
            findShopTerminal.setNoWx(findInviteCode.getShopNoWx());
            List<FindShopTerminalReturn> sts = shopTerminalService.findShopTerminalSelect(findShopTerminal);
            if (CollectionUtils.isEmpty(sts)) {
                map.put("result", 0);
                map.put("msg", "获取邀请码失败");
                return map;
            }
            qcord = StringUtils.isNotBlank(sts.get(0).getQcord()) ? sts.get(0).getQcord() : "";
            
            map.put("result", 1);
            Map<String, Object> dataMap = new HashMap<>(2);
            dataMap.put("inviteCode", findInviteCodeReturn.getInviteCode());
            dataMap.put("qcord", qcord);
            
            map.put("data", dataMap);
        } catch (Exception e) {
            LOGGER.error("优惠券分享，获取邀请码失败:{}", e);
            map.put("result", 0);
            map.put("msg", "获取邀请码失败");
        }
        return map;
    }
	
	/**
	 * 
	 *
	 * 方法说明：更新浏览量
	 *
	 * @param couponRuleEx
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月29日
	 *
	 */
	@RequestMapping(value ="updateCouponRuleExData.do")
	@ResponseBody
	public int updateCouponRuleExData(UpdateCouponRuleEx couponRuleEx){
		int i = 0;
		try {
			couponRuleExService.updateCouponRuleExData(couponRuleEx);
		} catch (Exception e) {
		    LOGGER.error("更新浏览量错误！",e);
		}
	   return i;
	}
	
	
	
}
