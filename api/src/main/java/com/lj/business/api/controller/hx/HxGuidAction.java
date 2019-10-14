/**
 * 
 */
package com.lj.business.api.controller.hx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.api.controller.Action;
import com.lj.oms.entity.dto.hx.HxGuidDto;
import com.lj.oms.service.UserHessianService;

/**
 * 
 * 
 * 类说明：焕新店员action
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月11日
 */
@Controller
@RequestMapping(value = "/hx/guid/")
public class HxGuidAction extends Action {
	
	@Autowired
	private UserHessianService userHessianService;
	
	/**
	 * 
	 * @param user.roleEnname 医生 SYS_SHOP_DOCTOR 咨询师 SYS_SHOP_ADVISORY 护士 SYS_SHOP_NURSE
	 * @return
	 */
	@RequestMapping(value = { "list.do" })
	@ResponseBody
	public List<HxGuidDto> findHxGuid(HxGuidDto user) {
		AssertUtils.notNullAndEmpty(user.getMerchantNo(),"商户编号不能为空！");
		
		List<HxGuidDto> users = userHessianService.findUsersByRoleEnname(user);
		
		return users;
	}
	
}
