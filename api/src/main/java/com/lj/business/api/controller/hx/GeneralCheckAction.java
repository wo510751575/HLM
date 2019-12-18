/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  GeneralCheckAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-11-05 10:29   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.lj.business.api.controller.hx;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.GeneralCheckDto;
import com.ye.business.hx.service.IGeneralCheckService;

/**   
 * @ClassName:  GeneralCheckAction   
 * @Description:TODO(正畸检查/一般检查)   
 * @author: 贾光朝 
 * @date:   2019-11-05 10:29   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/generalCheck")
public class GeneralCheckAction extends Action {

	@Resource
	private IGeneralCheckService generalCheckService;
	
	@ResponseBody
	@RequestMapping(value="/add.do",produces="application/json;charset=UTF-8")
	public GeneralResponse add(GeneralCheckDto generalCheckDto) {
		generalCheckDto.setCode(GUID.generateByUUID());
		generalCheckDto.setCreateDate(new Date());
		generalCheckService.addGeneralCheck(generalCheckDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do",produces="application/json;charset=UTF-8")
	public GeneralResponse edit(GeneralCheckDto generalCheckDto) {
		AssertUtils.notNullAndEmpty(generalCheckDto.getCode(),"code不能为空!");
		generalCheckService.updateGeneralCheck(generalCheckDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/get.do",produces="application/json;charset=UTF-8")
	public GeneralResponse get(GeneralCheckDto generalCheckDto) {
		AssertUtils.notNullAndEmpty(generalCheckDto.getPatientNo(),"患者编号不能为空!");
		GeneralCheckDto generalCheck = generalCheckService.findGeneralCheck(generalCheckDto);
		return GeneralResponse.generateSuccessResponse(generalCheck);
	}
}
