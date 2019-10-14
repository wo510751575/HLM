package com.lj.oms.im;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.exception.TsfaException;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.member.dto.FindImIndexPage;
import com.lj.business.member.dto.FindImIndexPageReturn;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWebReturn;
import com.lj.business.member.emus.PmTypeTimeFlag;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IPmTypeService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dto.couponmultipush.AddCouponMultiPush;
import com.lj.business.weixin.service.ICouponMultiPushService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.oms.common.BaseController;
import com.lj.oms.utils.JsonPage;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：优惠券群发
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年1月24日
 */
@Controller
@RequestMapping(value = "${adminPath}/couponmultipush")
public class CouponMultiPushController  extends BaseController{
    
    @Autowired
    private ICouponRuleService couponRuleService;
    @Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
    @Autowired
    private IShopTerminalService shopTerminalService;
    @Resource
    private IPmTypeService pmTypeService; // 客户分类服务
    @Resource
    private IPersonMemberService personMemberService; // 客户信息服务
    @Resource
    private IGuidMemberService guidMemberService; // 导购服务
    @Autowired
    private ICouponMultiPushService couponMultiPushService;
    
    private static final String DEFAULT_DELAY_TIMES = "60";//推送优惠券默认延迟时间
    
    /**
     * 
     *
     * 方法说明：跳转页面
     *
     * @param model
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月24日
     *
     */
    @RequiresPermissions("im:couponMultiPush:view")
    @RequestMapping(value = {""})
    public String list(Model model) {
        try {
            String companyId = UserUtils.getMerchantNo();
            model.addAttribute("assistantNo", UserUtils.getUser().getId());
            model.addAttribute("merchantNo", companyId);
            String delayTimes = localCacheSystemParams.getSystemParam(SystemAliasName.cp.name(), "min_delay", "cp_min_delay");
            if (org.apache.commons.lang.StringUtils.isBlank(delayTimes)) {
                delayTimes = DEFAULT_DELAY_TIMES;
            }
            model.addAttribute("delayTimes", delayTimes);
        } catch (Exception e) {
            logger.error("查询首页信息错误：" + e);
        }
        return "modules/im/sendCouponList";
    }
    
    @RequestMapping(value = "findCouponRuleList")
    @ResponseBody
    public List<FindCouponRuleReturn> findCouponRuleList(FindCouponRule findCouponRule) {
        try {
            List<FindCouponRuleReturn> findCouponRuleReturnList = couponRuleService.findCouponRuleListWeb(findCouponRule);
            // 客户端展示的地址
            String cp_client_url = localCacheSystemParams.getSystemParam("cp", "client_url", "cp_client_url");
            for (FindCouponRuleReturn findCouponRuleReturn : findCouponRuleReturnList) {
                findCouponRuleReturn.setShareUrl(cp_client_url + findCouponRuleReturn.getCode());
            }
            
            return findCouponRuleReturnList;
        } catch (Exception e) {
            logger.error("查询优惠券列表错误：" , e);
        }
        return new ArrayList<>(0);
    }
    
    
    @RequestMapping(value = "findShopTerminalList")
    @ResponseBody
    public List<FindShopTidFromWebReturn> findShopTerminalList(
            FindShopTidFromWeb findShopTidFromWeb) {
        try {
            List<FindShopTidFromWebReturn> findShopTerminalFromWeb =
                    shopTerminalService.findShopTerminalFromWeb(findShopTidFromWeb);
            return findShopTerminalFromWeb;
        } catch (Exception e) {
            logger.error("查询导购助手终端终端列表错误：" + e);
        }
        return new ArrayList<>(0);
    }
    
    @RequestMapping(value = "findPmTypeByMerchantNo")
    @ResponseBody
    public List<FindPmTypePageReturn> findPmTypeByMerchantNo(FindPmTypePageReturn findPmTypePageReturn) {
        try {
            List<FindPmTypePageReturn> pmTypeList = pmTypeService.findPmTypePages(findPmTypePageReturn);
            addTimePmType(pmTypeList);
            return pmTypeList;
        } catch (Exception e) {
            logger.error("查询商户下分组列表错误：" + e);
        }
        return new ArrayList<>(0);
    }
    
    /**
	 * 新增 今日新增/7天内新增/30天内新增 客户类型
	 * @author 彭俊霖
	 * @param pmType
	 */
	public void addTimePmType(List<FindPmTypePageReturn> pmType) {
		FindPmTypePageReturn todayFlag=new FindPmTypePageReturn();
		todayFlag.setCode(PmTypeTimeFlag.TODAY.toString());
		todayFlag.setTypeName(PmTypeTimeFlag.TODAY.getName());
		FindPmTypePageReturn weekFlag=new FindPmTypePageReturn();
		weekFlag.setCode(PmTypeTimeFlag.WEEK.toString());
		weekFlag.setTypeName(PmTypeTimeFlag.WEEK.getName());
		FindPmTypePageReturn monthFlag=new FindPmTypePageReturn();
		monthFlag.setCode(PmTypeTimeFlag.MONTH.toString());
		monthFlag.setTypeName(PmTypeTimeFlag.MONTH.getName());
		pmType.add(0,todayFlag);
		pmType.add(1,weekFlag);
		pmType.add(2,monthFlag);
	}
    
    @RequestMapping(value={"personMemberList"})
    @ResponseBody
    public Map<String,Object> personMemberList(Model model, FindImIndexPage findImIndex,
            Integer pageNo, Integer pageSize){
        
        //默认查询第一页
        pageNo = pageNo == null ? 1 : pageNo;
        
        if (pageNo != null && pageNo > 0 && pageSize != null && pageSize > 0) {
            findImIndex.setStart((pageNo - 1) * pageSize);
            findImIndex.setLimit(pageSize);
        }
        
        List<FindImIndexPageReturn> list = personMemberService.findPmListByShopTerminals(findImIndex);
        Long count = personMemberService.findPmListByShopTerminalsCount(findImIndex);
        
        JsonPage<FindImIndexPageReturn> page = new JsonPage<FindImIndexPageReturn>(pageNo , pageSize,count, list);
        page.initialize();
        
        Map<String,Object> resultMap=new HashMap<String, Object>();
        resultMap.put("page", page);
        
        return resultMap;
    }
    
    @RequestMapping(value = "addCouponMultiPush")
    @ResponseBody
    public Map<String, Object> addCouponMultiPush(AddCouponMultiPush addCouponMultiPush) {
        Map<String, Object> resultMap = new HashMap<String, Object>(2);

        try {
            couponMultiPushService.addCouponMultiPush(addCouponMultiPush);
            resultMap.put("result", 1);
            resultMap.put("msg", "优惠券群发任务添加成功");
        } catch (TsfaException e) {
            logger.error("优惠券群发任务添加失败-{}", e);
            logger.error(e.getMessage());
            resultMap.put("result", 0);
            resultMap.put("msg", e.getExceptionInfo());
        }

        return resultMap;
    }
    
}
