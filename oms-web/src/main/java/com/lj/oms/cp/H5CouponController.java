package com.lj.oms.cp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lj.oms.common.BaseController;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cp.dto.coupon.EmployCoupon;
import com.lj.business.cp.dto.coupon.FindCoupon;
import com.lj.business.cp.dto.coupon.FindCouponPageReturn;
import com.lj.business.cp.dto.coupon.FindCouponReturn;
import com.lj.business.cp.dto.coupon.FindInviteCode;
import com.lj.business.cp.dto.coupon.FindInviteCodeReturn;
import com.lj.business.cp.dto.coupon.UpdateCoupon;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationVoReturn;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExReturn;
import com.lj.business.cp.dto.couponRuleEx.UpdateCouponRuleEx;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleExService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.oms.weixin.utils.WeixinApiService;

/**
 * 
 * 
 * 类说明：H5页面接口
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 杨杰
 * 
 * @date: 2017年9月21日
 */
@Controller
@RequestMapping(value = "cp/h5coupon")
public class H5CouponController  extends BaseController{
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
	private ICouponRuleExService  couponRuleExService;
	@Resource
	private ICouponService couponService;
	@Autowired
    private IShopTerminalService shopTerminalService;
	@Resource
	private WeixinApiService weixinApiService;
	@Resource
    private IGuidMemberService guidMemberService;
	
	
	private static final String COUPON_INVITE_RESULT ="modules/cp/couponInviteResult";
	
	private static final String FIND_COUPONRULE = "modules/cp/findCouponRule";
	
	private static final String FIND_COUPONEXPLAIN = "modules/cp/findCouponExplain";
	
	private static final String FIND_COUPON_MEMBER_RELATION ="modules/cp/findCouponMemberRelation";
	
	private static final String FIND_COUPON_DETAIL = "modules/cp/findCouponDetail";
	
	private static final String FIND_COUPON_USERDETAIL ="modules/cp/findCouponUserDetail";
	
	private static final String FIND_COUPON_INVITEDETAIL ="modules/cp/findCouponInviteDetail";
	
	private static final String FIND_COUPON_OTHER_INVITE_DETAIL = "modules/cp/findCouponOtherInviteDetail";
	
	private static final String FIND_COUPON_INVITE = "modules/cp/findCouponInvite";
	
	private static final String MERCHANT_NO_COUPON_IS_NULL_ERROR = "merchant_no_coupon_is_null_error";
	
	private static final String COUPON_FIND_PAGE_ERROR_TIME = "coupon_find_page_error_time";
	
	private static final String COUPON_IS_NULL_ERROR = "coupon_is_null_error";
	
	private static final String COUPON_NOT_EXIST_ERROR = "coupon_not_exist_error";
	

	/**
	 * 终端编号
	 */
	private static final String SHOP_NO = "shopNo";
	
	/**
	 * 返回给页面的数据
	 */
	private static final String DATA = "data";
	/**
	 * 终端微信号
	 */
	private static final String NO_WX_SHOP = "noWxShop";
	/**
	 * 路径
	 */
	private static final String CTX = "/coupon/";
	/**
	 * 优惠券编号
	 */
	private static final String RULE_NO = "ruleNo";
    
	private static final String COUPON_RULE = "couponRule";
	
	private static final String MERCHANT_INFO ="MERCHANT_INFO";
	
	private static final String MERCHANT_NO = "merchantNo";
	
	private static final String MESSAGE = "message";
	private static final String STATUS  = "status";
	
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
		return FIND_COUPONRULE;
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

			model.addAttribute(COUPON_RULE, findCouponRuleReturn);
		} catch (Exception e) {
			LOGGER.error("根据code获取优惠券规则error:{}", e);
		}
		return FIND_COUPONEXPLAIN;
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
	@RequestMapping(value = "findCouponMemberRelation")
	public String findCouponMemberRelation(Model model, FindCouponMemberRelation findCouponMemberRelation) {
		FindCouponMemberRelationVoReturn couponMemberRelationVoReturn = null;
		try {
			couponMemberRelationVoReturn = couponMemberRelationService.findCouponMemberRelation(findCouponMemberRelation);
			model.addAttribute("couponMemberRelationVo", couponMemberRelationVoReturn);
		} catch (Exception e) {
			LOGGER.error("根据条件获取客户获取的优惠券error:{}", e);
		}
		return FIND_COUPON_MEMBER_RELATION;
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据规则获取详情
	 * 
	 *
	 * @param model
	 * @param ruleNo
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月30日
	 *
	 */
	@RequestMapping(value="findCouponDetail")
	public String findCouponDetail(Model model,String ruleNo, String memberNoGm){
		try {
			FindCouponRule findCouponRule =  new FindCouponRule();
			findCouponRule.setCode(ruleNo);
			FindCouponRuleReturn couponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
			FindCouponRuleEx findCouponRuleEx = new FindCouponRuleEx();
			findCouponRuleEx.setRuleCode(ruleNo);
			FindCouponRuleExReturn couponRuleExReturn = couponRuleExService.findCouponRuleExByRuleCode(findCouponRuleEx);
			couponRuleReturn.setSurplusNum(couponRuleExReturn.getSurplusNum());
			couponRuleReturn.setMemberNoGm(memberNoGm);
			
            //查询终端编号
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(memberNoGm);
			FindGuidMemberReturn guidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
			
			FindMerchantDto findMerchantDto = new FindMerchantDto();
            findMerchantDto.setMerchantNo(guidMemberReturn.getMerchantNo());
            FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
            model.addAttribute(MERCHANT_INFO, findMerchantReturnDto);
			
//			model.addAttribute(SHOP_NO, guidMemberReturn.getShopNo());
			model.addAttribute(NO_WX_SHOP, guidMemberReturn.getNoWx());
			model.addAttribute(DATA, couponRuleReturn);
			model.addAttribute(CTX, "/coupon/");
		} catch (Exception e) {
			LOGGER.error("获取优惠券规则错误！",e);
		}
		return FIND_COUPON_DETAIL;
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户领取优惠券
	 *
	 * @param findCoupon
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月26日
	 */
	@RequestMapping(value="memberGainCoupon")
    @ResponseBody
	public Map<String,Object> memberGainCoupon(FindCoupon findCoupon){
		LOGGER.debug("memberGainCoupon(FindCoupon findCoupon)",findCoupon);
		Map<String,Object> map= null; 
		try {
			map = couponService.gainCoupon(findCoupon); 
		} catch (TsfaServiceException e) {
			logger.error(e.getExceptionInfo(),e);
			if(e.getExceptionCode().equals(MERCHANT_NO_COUPON_IS_NULL_ERROR)){
				map.put(MESSAGE, "商户没有该优惠券信息！");
				map.put(STATUS, Boolean.FALSE);
			}else if(e.getExceptionCode().equals(COUPON_FIND_PAGE_ERROR_TIME)){
				map.put(MESSAGE, "优惠券已过期！");
				map.put(STATUS, Boolean.FALSE);
			}else if(e.getExceptionCode().equals(COUPON_IS_NULL_ERROR)){
				map.put(MESSAGE, "优惠券已领取完了！");
				map.put(STATUS, Boolean.FALSE);
			}else if(e.getExceptionCode().equals(COUPON_NOT_EXIST_ERROR)){
				map.put(MESSAGE, "系统异常,领取优惠券失败！");
				map.put(STATUS, Boolean.FALSE);
			}
		}catch (Exception e) {
			LOGGER.error("客户获取的优惠券错误error:{}", e);
		}
    	return map;
    }
	
	/**
	 * 
	 *
	 * 方法说明：导购分享给客户的优惠券详情页面
	 *
	 * @param model
	 * @param ruleNo
	 * @param memberNoGm
	 * @param memberNo
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月30日
	 *
	 */
	@RequestMapping(value="findCouponUserDetail")
    public String findCouponUserDetail(Model model, String params){//String ruleNo, String memberNoGm, String memberNo
        try {
            //转码
            params = params.replace("&quot;", "\"");
            JSONObject paramsJson = JSON.parseObject(params);
            String ruleNo = paramsJson.getString("ruleNo");
            String memberNoGm = paramsJson.getString("memberNoGm");
            String memberNo = paramsJson.getString("memberNo");

            FindCouponRule findCouponRule =  new FindCouponRule();
            findCouponRule.setCode(ruleNo);
            FindCouponRuleReturn couponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
            FindCouponRuleEx findCouponRuleEx = new FindCouponRuleEx();
            findCouponRuleEx.setRuleCode(ruleNo);
            FindCouponRuleExReturn couponRuleExReturn = couponRuleExService.findCouponRuleExByRuleCode(findCouponRuleEx);
            couponRuleReturn.setSurplusNum(couponRuleExReturn.getSurplusNum());
            couponRuleReturn.setMemberNoGm(memberNoGm);
            couponRuleReturn.setMemberNo(memberNo);
            
            //查询导购微信
            FindGuidMember findGuidMember = new FindGuidMember();
            findGuidMember.setMemberNo(memberNoGm);
            FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
            model.addAttribute(NO_WX_SHOP, findGuidMemberReturn.getNoWx());
            
            //浏览量加1
            if (ruleNo != null) {
                UpdateCouponRuleEx couponRuleEx = new UpdateCouponRuleEx();
                couponRuleEx.setRuleCode(ruleNo);
                couponRuleExService.updateCouponRuleExData(couponRuleEx);
            }
            /**
             * 用户只要打开，就发一张券码，并标记为已领取
             */
            FindCoupon findCoupon = new FindCoupon();
            findCoupon.setRuleNo(ruleNo);
            findCoupon.setCouponStatus(CouponStatus.UNUSED.toString());
            FindCouponReturn findCouponReturn  = couponService.findCouponLimit1(findCoupon);
            String couponNo = "券已领完！";
            if(null != findCouponReturn) {
            	couponNo = findCouponReturn.getCouponNo();
            	UpdateCoupon updateCoupon = new UpdateCoupon();
            	updateCoupon.setCode(findCouponReturn.getCode());
            	updateCoupon.setCouponStatus(CouponStatus.IS_GET.toString());
            	couponService.updateCoupon(updateCoupon);
            }
            model.addAttribute(DATA, couponRuleReturn);
            model.addAttribute("couponNo", couponNo);
            model.addAttribute(CTX, "/coupon/");
        } catch (Exception e) {
            LOGGER.error("获取优惠券规则错误！",e);
        }
        return FIND_COUPON_USERDETAIL;
    }
	
    
	/**
	 * 
	 *
	 * 方法说明：B打开A分享出去的页面
	 *
	 * @param model
	 * @param ruleNo
	 * @param memberNo
	 * @param noWxShop
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月31日
	 *
	 */
    @RequestMapping(value="findCouponInviteDetail")
    public String findCouponInviteDetail(Model model,String ruleNo, String memberNo, String memberNoGm, String noWxShop){
        try {
            //优惠券信息
            FindCouponRule findCouponRule =  new FindCouponRule();
            findCouponRule.setCode(ruleNo);
            FindCouponRuleReturn couponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
            FindCouponRuleEx findCouponRuleEx = new FindCouponRuleEx();
            findCouponRuleEx.setRuleCode(ruleNo);
            FindCouponRuleExReturn couponRuleExReturn = couponRuleExService.findCouponRuleExByRuleCode(findCouponRuleEx);
            couponRuleReturn.setSurplusNum(couponRuleExReturn.getSurplusNum());
            couponRuleReturn.setMemberNo(memberNo);
            
            //查询商户信息
            FindMerchantDto findMerchantDto = new FindMerchantDto();
            findMerchantDto.setMerchantNo(couponRuleReturn.getMerchantNo());
            FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
            
            //邀请码
            FindInviteCode findInviteCode = new FindInviteCode();
            findInviteCode.setType(1);
            findInviteCode.setCouponRuleCode(ruleNo);
            findInviteCode.setMemberNo(memberNo);
            findInviteCode.setShopNoWx(noWxShop);
            findInviteCode.setMemberNoGm(memberNoGm);
            FindInviteCodeReturn findInviteCodeReturn = couponRuleService.findInviteCode(findInviteCode);
            
            
            //终端终端二维码
            String qcord = "";
            FindShopTerminal findShopTerminal = new FindShopTerminal();
            findShopTerminal.setNoWx(noWxShop);
            List<FindShopTerminalReturn> sts = shopTerminalService.findShopTerminalSelect(findShopTerminal);
            if (CollectionUtils.isNotEmpty(sts)) {
                qcord = StringUtils.isNotBlank(sts.get(0).getQcord()) ? sts.get(0).getQcord() : "";
            }
            
            //浏览量加1
            if (ruleNo != null) {
                UpdateCouponRuleEx couponRuleEx = new UpdateCouponRuleEx();
                couponRuleEx.setRuleCode(ruleNo);
                couponRuleExService.updateCouponRuleExData(couponRuleEx);
            }
            
            model.addAttribute(MERCHANT_INFO, findMerchantReturnDto);
            model.addAttribute(DATA, couponRuleReturn);
            model.addAttribute("qcord", qcord);
            model.addAttribute("inviteCode", findInviteCodeReturn == null ? "" : findInviteCodeReturn.getInviteCode());
            model.addAttribute(CTX, "/coupon/");
        } catch (Exception e) {
            LOGGER.error("获取邀请码错误！",e);
        }
        return FIND_COUPON_INVITEDETAIL;
    }
    
    /**
     * 
     *
     * 方法说明：C打开B分享出去的页面
     *
     * @param model
     * @param ruleNo
     * @param addFriendsCode
     * @param noWxShop
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月31日
     *
     */
    @RequestMapping(value="findCouponOtherInviteDetail")
    public String findCouponOtherInviteDetail(Model model, String ruleNo, String addFriendsCode, String memberNo, String memberNoGm, String noWxShop){
        try {
            //优惠券信息
            FindCouponRule findCouponRule =  new FindCouponRule();
            findCouponRule.setCode(ruleNo);
            FindCouponRuleReturn couponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
            FindCouponRuleEx findCouponRuleEx = new FindCouponRuleEx();
            findCouponRuleEx.setRuleCode(ruleNo);
            FindCouponRuleExReturn couponRuleExReturn = couponRuleExService.findCouponRuleExByRuleCode(findCouponRuleEx);
            couponRuleReturn.setSurplusNum(couponRuleExReturn.getSurplusNum());
            couponRuleReturn.setAddFriendsCode(addFriendsCode);
            
            //查询商户信息
            FindMerchantDto findMerchantDto = new FindMerchantDto();
            findMerchantDto.setMerchantNo(couponRuleReturn.getMerchantNo());
            FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
            model.addAttribute(MERCHANT_INFO, findMerchantReturnDto);
            
            //邀请码
            FindInviteCode findInviteCode = new FindInviteCode();
            if(addFriendsCode == null) {
            	findInviteCode.setType(1);
            	findInviteCode.setCouponRuleCode(ruleNo);
            	findInviteCode.setMemberNo(memberNo);
            	findInviteCode.setMemberNoGm(memberNoGm);
            	findInviteCode.setShopNoWx(noWxShop);
            } else {
            	findInviteCode.setType(2);
            	findInviteCode.setCouponRuleCode(ruleNo);
            	findInviteCode.setAddFriendsCode(addFriendsCode);
            	findInviteCode.setShopNoWx(noWxShop);
            }
            FindInviteCodeReturn findInviteCodeReturn = couponRuleService.findInviteCode(findInviteCode);
            
            
            //终端终端二维码
            String qcord = "";
            FindShopTerminal findShopTerminal = new FindShopTerminal();
            findShopTerminal.setNoWx(noWxShop);
            List<FindShopTerminalReturn> sts = shopTerminalService.findShopTerminalSelect(findShopTerminal);
            if (CollectionUtils.isNotEmpty(sts)) {
                qcord = StringUtils.isNotBlank(sts.get(0).getQcord()) ? sts.get(0).getQcord() : "";
            }
            
            //浏览量加1
            if (ruleNo != null) {
                UpdateCouponRuleEx couponRuleEx = new UpdateCouponRuleEx();
                couponRuleEx.setRuleCode(ruleNo);
                couponRuleExService.updateCouponRuleExData(couponRuleEx);
            }
            
            model.addAttribute(DATA, couponRuleReturn);
            model.addAttribute("qcord", qcord);
            model.addAttribute("inviteCode", findInviteCodeReturn == null ? "" : findInviteCodeReturn.getInviteCode());
            model.addAttribute(CTX, "/coupon/");
        } catch (Exception e) {
            LOGGER.error("获取邀请码错误！",e);
        }
        return FIND_COUPON_OTHER_INVITE_DETAIL;
    }
    
    /**
     * 
     *
     * 方法说明：中控发出的链接，让微信好友领取优惠券
     *
     * @param model
     * @param noWxShop 终端微信
     * @param addFriendsCode  B的微信
     * @param merchantNo  商户编号
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月30日
     *
     */
    @RequestMapping(value="findCouponInvite")
    public String findCouponInvite(Model model, String params){//String noWxShop, String addFriendsCode, String merchantNo
        try {
            //转码
            params = params.replace("&quot;", "\"");
            JSONObject paramsJson = JSON.parseObject(params);
//            String noWxShop = paramsJson.getString("noWxShop");
//            String addFriendsCode = paramsJson.getString("addFriendsCode");
            String merchantNo = paramsJson.getString(MERCHANT_NO);
            
            FindMerchantDto findMerchantDto = new FindMerchantDto();
            findMerchantDto.setMerchantNo(merchantNo);
            FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
            
            model.addAttribute(MERCHANT_INFO, findMerchantReturnDto);
        } catch (Exception e) {
            LOGGER.error("领取优惠券页面错误！",e);
        }
        return FIND_COUPON_INVITE;
    }
    
    /**
     * 
     *
     * 方法说明：B领取结果页面，有立即使用和分享按钮
     *
     * @param model
     * @param ruleNo
     * @param noWxShop
     * @param noWx
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月31日
     *
     */
    @RequestMapping(value="couponInviteResult")
    public String couponInviteResult(Model model, String params){//String ruleNo, String noWxShop, String addFriendsCode
        try {
            //转码
            params = params.replace("&quot;", "\"");
            JSONObject paramsJson = JSON.parseObject(params);
            String ruleNo = paramsJson.getString(RULE_NO);
            String noWxShop = paramsJson.getString(NO_WX_SHOP);
            String addFriendsCode = paramsJson.getString("addFriendsCode");
            
            FindCouponRule findCouponRule =  new FindCouponRule();
            findCouponRule.setCode(ruleNo);
            FindCouponRuleReturn couponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
            FindCouponRuleEx findCouponRuleEx = new FindCouponRuleEx();
            findCouponRuleEx.setRuleCode(ruleNo);
            FindCouponRuleExReturn couponRuleExReturn = couponRuleExService.findCouponRuleExByRuleCode(findCouponRuleEx);
            couponRuleReturn.setSurplusNum(couponRuleExReturn.getSurplusNum());
            couponRuleReturn.setAddFriendsCode(addFriendsCode);
            
            //查询商户信息
            FindMerchantDto findMerchantDto = new FindMerchantDto();
            findMerchantDto.setMerchantNo(couponRuleReturn.getMerchantNo());
            FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
            model.addAttribute(MERCHANT_INFO, findMerchantReturnDto);
            
            //浏览量加1
            if (ruleNo != null) {
                UpdateCouponRuleEx couponRuleEx = new UpdateCouponRuleEx();
                couponRuleEx.setRuleCode(ruleNo);
                couponRuleExService.updateCouponRuleExData(couponRuleEx);
            }
            
            model.addAttribute(DATA, couponRuleReturn);
            model.addAttribute(RULE_NO, ruleNo);
            model.addAttribute(NO_WX_SHOP, noWxShop);
        } catch (Exception e) {
            LOGGER.error("领取优惠券页面错误！",e);
        }
        return COUPON_INVITE_RESULT;
    }
	
	@RequestMapping(value="findCouponStatus")
	@ResponseBody
    public Map<String, Object> findCouponStatus(FindCoupon findCoupon) {
	    Map<String, Object> findCouponStatus = couponMemberRelationService.findCouponStatus(findCoupon);
	    
	    FindCouponRule findCouponRule =  new FindCouponRule();
        findCouponRule.setCode(findCoupon.getRuleNo());
        FindCouponRuleReturn couponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
        if (couponRuleReturn != null) {
            //优惠券规则信息
            Date expireDate = DateUtils.addDays(couponRuleReturn.getEndDate(), 1);
            findCouponStatus.put("expire", expireDate.before(new Date()));
        }
	    
        return findCouponStatus;
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
    @RequestMapping(value ="couponConsumption")
    @ResponseBody
    public Map<String, Object> couponConsumption(EmployCoupon employCoupon){
        LOGGER.debug("couponConsumption(EmployCoupon employCoupon)",employCoupon);
        Map<String, Object> map = new HashMap<>(3);
        String couponStatus = null;
        try {
         couponStatus = couponService.couponConsumption(employCoupon);
         map.put("result", Boolean.TRUE);
         map.put(DATA, couponStatus);
        } catch (Exception e) {
            LOGGER.error("使用优惠券错误！",e);
            map.put("result", Boolean.FALSE);
            map.put("msg", "使用优惠券错误");
        }
        return map;
                 
    }
    
	/**
	 * 方法说明： js sdk 签名。<p>
	 * 用于 微信 JSSDK使用，比如分享。
	 * @param url 当前网页的URL，不包含#及其后面部分
	 * @param merchantCode 商户号
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月29日
	 */
	@RequestMapping(value = "/couponJsconfig", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> jsSdkSign(String url,String merchantCode){
		LOGGER.info("Url:" + url + ",merchantCode:" + merchantCode);
		if(StringUtils.isBlank(url)||StringUtils.isBlank(merchantCode)){
			return null;
		}
		Map<String, Object> jsSdk=weixinApiService.getJsapiSign(url,merchantCode);
		return jsSdk;
	}
	
}
