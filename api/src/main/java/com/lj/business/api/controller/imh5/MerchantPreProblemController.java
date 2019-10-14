package com.lj.business.api.controller.imh5;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.ai.dto.MerchantAutoReplyDto;
import com.lj.business.ai.service.IProblemService;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;

/**
 * 
 * 
 * 类说明：商家预设问题Controller
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2018年01月17日
 */
@Controller
@RequestMapping(value = "/imh5/ai/merchantPreProblem")
public class MerchantPreProblemController  extends Action{

	@Resource
	private IProblemService problemService;			
	

	
	/**
	 * 查询导购是否开启自动回复
	 */
	@RequestMapping(value = "insertGmAutoStatus.do")
	@ResponseBody
	public GeneralResponse insertGmAutoStatus(String memberNoGm, String memberNameGm,String merchantNo){
		AssertUtils.notNullAndEmpty(merchantNo,"商户编号不能为空");
		AssertUtils.notNullAndEmpty(memberNoGm,"导购编号不能为空");
		AssertUtils.notNullAndEmpty(memberNameGm,"导购名称不能为空");
		try {
			List<MerchantAutoReplyDto>  list = problemService.selectAutoReplyList(merchantNo, memberNoGm);
			if(list != null && list.size() > 0) {
				return GeneralResponse.generateFailureResponse("", "导购已开启");
			}
		    problemService.insertGmAutoStatus(memberNoGm, merchantNo, memberNameGm);
		}catch(Exception e) {
			logger.error("开启错误e={}",e);
			return GeneralResponse.generateFailureResponse(e);
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
	
	@RequestMapping(value = "isEnable.do")
	@ResponseBody
	public GeneralResponse isEnable(String memberNoGm, String merchantNo){
		AssertUtils.notNullAndEmpty(merchantNo,"商户编号不能为空");
		AssertUtils.notNullAndEmpty(memberNoGm,"导购编号不能为空");
		try {
			List<MerchantAutoReplyDto>  list = problemService.selectAutoReplyList(merchantNo, memberNoGm);
			if(list != null && list.size() > 0) {
				return GeneralResponse.generateSuccessResponse(true);
			}else {
				return GeneralResponse.generateSuccessResponse(false);
			}
		}catch(Exception e) {
			logger.error("错误e={}",e);
			return GeneralResponse.generateFailureResponse(e);
		}
	}
	
	/**
	 * 关闭自动回复
	 */
	@RequestMapping(value = "deleteAutoReply.do")
	@ResponseBody
	public GeneralResponse  deleteAutoReplyList(String memberNoGm){
		try {
		    problemService.deleteAutoReplyList(memberNoGm);
		}catch(Exception e) {
			logger.error("关闭自动回复错误：",e);
			return GeneralResponse.generateFailureResponse(e);
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
}
