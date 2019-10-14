package com.lj.business.api.controller.member;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.exception.TsfaException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.forecastName.AddForecastName;
import com.lj.business.member.service.IForecastNameService;
import com.lj.business.member.service.IPersonMemberBaseService;



/**
 * 
 * 
 * 类说明：预报名管理
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 * 
 *         CreateDate: 2018年03月20日
 */

@Controller
@RequestMapping(value = "/member/forecastName/")
public class ForecastNameController extends Action {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(ForecastNameController.class);

    /**
     * 预报名服务
     */
    @Resource
    private IForecastNameService forecastNameService;
    @Resource
    private IPersonMemberBaseService personMemberBaseService;

    /**
     * 
     * 
     * 方法说明：新增保存方法
     *
     * @param model
     * @param redirectAttributes
     * @param addForecastName
     * @return 保存新增数据后跳转页面
     *
     * @author 彭俊霖 CreateDate: 2018年03月20日
     *
     */
    @RequestMapping(value = "addForecastName.do")
    @ResponseBody
    public GeneralResponse addForecastName(AddForecastName addForecastName) {
    	try{
    		forecastNameService.addForecastName(addForecastName);
    		return GeneralResponse.generateSuccessResponse();
    	}catch(TsfaException ex) {
    		logger.error("新增预报名信息失败!", ex.getExceptionInfo());
    		return GeneralResponse.generateResponse(false, ErrorCode.FORECAST_NAME_REPEAT_ERROR, "请勿重复预报名", ex.getExceptionInfo());
    	}catch(Exception e) {
    		logger.error("新增预报名信息失败!", e);
    		return GeneralResponse.generateFailureResponse(e);
    	}
    }

    /**
     * 
     *
     * 方法说明：根据memberNo查询客户基本信息
     *
     * @param memberNo
     * @return 返回结果字段取memberName,sex,mobile
     *
     * @author 许新龙 CreateDate: 2018年3月21日
     *
     */
    @RequestMapping(value = "findPersonMember.do")
    @ResponseBody
    public FindPersonMemberBaseReturn findPersonMember(String memberNo) {
		logger.debug("findPersonMember(String memberNo={}) - start", memberNo); 

        FindPersonMemberBaseReturn findPersonMemberBaseReturn = null;
        try {
            FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
            findPersonMemberBase.setMemberNo(memberNo);
            findPersonMemberBaseReturn = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
        } catch (Exception e) {
            logger.error("获取客户信息失败:{}", e);
        }

		logger.debug("findPersonMember(String) - end - return value={}", findPersonMemberBaseReturn); 
        return findPersonMemberBaseReturn;
    }
    
}
