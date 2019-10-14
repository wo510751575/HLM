package com.lj.oms.im;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaException;
import com.lj.business.member.dto.EditPersonMember;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.weixin.domain.FriendPointCycle;
import com.lj.business.weixin.service.IFriendPointCycleService;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.utils.UserUtils;

/**
 * 
 * 
 * 类说明：导购助手客户资料编辑，到店、消费记录新增
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年1月23日
 */
@Controller
@RequestMapping(value = "${adminPath}/im/member")
public class ImMemberController {

    private static final Logger logger = LoggerFactory.getLogger(ImMemberController.class);
    
    @Resource
    public IPersonMemberService personMemberService;
    @Resource
    public IPersonMemberImService personMemberImService;
//    @Resource
//    public IPersonMemberCffService personMemberCffService;
    @Resource
    private AreaHessianService areaHessianService; // 地区服务
    @Resource
    private IFriendPointCycleService friendPointCycleService;
    
    
    /**
     * 
     *
     * 方法说明：非车发发客户资料编辑
     *
     * @param editPersonMember
     * @return
     *
     * @author 许新龙 CreateDate: 2018年1月23日
     *
     */
    @RequestMapping(value = "modifyMemberInfo")
    @ResponseBody
    public Map<String,Object> modifyMemberInfo(EditPersonMember editPersonMember) {
        logger.debug("modifyMemberInfo(EditPersonMember editPersonMember={}) - start", editPersonMember); 

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(StringUtils.isEmpty(editPersonMember.getCode()) 
        		|| StringUtils.isEmpty(editPersonMember.getMemberNo())
        		|| StringUtils.isEmpty(editPersonMember.getCodePm())) {
        	resultMap.put("result", 0);
            resultMap.put("msg","参数错误！");
        }
        editPersonMember.setMerchantNo(UserUtils.getMerchantNo());
        try {
            personMemberService.editPersonMemberGMA(editPersonMember);
            
            //更新朋友圈提醒周期
            if (editPersonMember.getCycle() != null) {
                FriendPointCycle record = new FriendPointCycle();
                record.setMemberNo(editPersonMember.getMemberNo());
                record.setCycle(editPersonMember.getCycle());
                friendPointCycleService.updateByMemberNo(record);
            }

            resultMap.put("result", 1);
            resultMap.put("msg", "客户资料修改成功");
        } catch (TsfaException e) {
            logger.error("客户资料修改失败", e);
            resultMap.put("result", 0);
            resultMap.put("msg", e.getExceptionInfo());
        }

        logger.debug("modifyMemberInfo(EditPersonMember) - end - return value={}", resultMap); 
        return resultMap;
    }
    
    @RequestMapping(value = { "/findAllProvince" })
    @ResponseBody
    public List<Area> findAllProvince() {
        return areaHessianService.selectProvince();
    }
    
    @RequestMapping(value = { "/findCityByProvinceCode" })
    @ResponseBody
    public List<Area> findCityByProvinceCode(String provinceCode) {
        return areaHessianService.findCityByProvinceCode(provinceCode);
    }
    
    @RequestMapping(value = { "/findRegionByCityCode" })
    @ResponseBody
    public List<Area> findRegionByCityCode(String cityCode) {
        return areaHessianService.findRegionByCityCode(cityCode);
    }
    
    /**
     * 
     *
     * 方法说明：开启或关闭查看客户朋友圈
     *
     * @param pmCode
     * @param seeFriends
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年10月13日
     *
     */
   /* @RequestMapping(value = { "/openSeeFriends" })
    @ResponseBody
    public String openSeeFriends(String pmCode, boolean seeFriends) {
    	try {
    		personMemberImService.openSeeFriends(pmCode, seeFriends);
    	} catch(TsfaServiceException e) {
    		logger.error("开启或关闭查看客户朋友圈失败", e);
    		return e.getExceptionInfo();
    	} catch (Exception e) {
    		logger.error("开启或关闭查看客户朋友圈失败", e);
    		return seeFriends ? "打开查看朋友圈失败" : "关闭查看朋友圈失败";
		}
    	return "success";
    }*/
    
}
