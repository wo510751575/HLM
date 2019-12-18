/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  SoftCheckAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-11-05 11:25   
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
import com.ye.business.hx.dto.SoftCheckDto;
import com.ye.business.hx.service.ISoftCheckService;

/**   
 * @ClassName:  SoftCheckAction   
 * @Description:TODO(正畸检查/颌骨及软组织检查)   
 * @author: 贾光朝 
 * @date:   2019-11-05 11:25   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/softCheck")
public class SoftCheckAction extends Action {

	@Resource
	private ISoftCheckService softCheckService;
	
	@ResponseBody
	@RequestMapping(value="/add.do",produces="application/json;charset=UTF-8")
	public GeneralResponse add(SoftCheckDto softCheckDto) {
		softCheckDto.setCode(GUID.generateByUUID());
		softCheckDto.setCreateDate(new Date());
		softCheckService.addSoftCheck(softCheckDto);
		return GeneralResponse.generateSuccessResponse();
		
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do",produces="application/json;charset=UTF-8")
	public GeneralResponse edit(SoftCheckDto softCheckDto) {
		AssertUtils.notNullAndEmpty(softCheckDto.getCode(),"code不能为空!");
		softCheckService.updateSoftCheck(softCheckDto);
		return GeneralResponse.generateSuccessResponse();
		
	}
	
	@ResponseBody
	@RequestMapping(value="/get.do",produces="application/json;charset=UTF-8")
	public GeneralResponse get(SoftCheckDto softCheckDto) {
		AssertUtils.notNullAndEmpty(softCheckDto.getPatientNo(),"患者编号不能为空!");
		SoftCheckDto findSoftCheck = softCheckService.findSoftCheck(softCheckDto);
		return GeneralResponse.generateSuccessResponse(findSoftCheck);
		
	}
}
