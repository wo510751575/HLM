/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  PatientOrthodonticsPlanAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-12-05 17:19   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.lj.business.api.controller.hx;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.PatientOrthodonticsPlanDto;
import com.ye.business.hx.service.IPatientOrthodonticsPlanService;

/**   
 * @ClassName:  PatientOrthodonticsPlanAction   
 * @Description:TODO(正畸病历-正畸计划)   
 * @author: 贾光朝 
 * @date:   2019-12-05 17:19   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping("/hx/patientOrthodonticsPlan")
public class PatientOrthodonticsPlanAction extends Action {

	@Resource
	private IPatientOrthodonticsPlanService patientOrthodonticsPlanService;
	
	@ResponseBody
	@RequestMapping(value="/add.do",produces="application/json; charset=UTF-8")
	public GeneralResponse add(PatientOrthodonticsPlanDto patientOrthodonticsPlanDto) {
		patientOrthodonticsPlanService.addPatientOrthodonticsPlan(patientOrthodonticsPlanDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do",produces="application/json; charset=UTF-8")
	public GeneralResponse edit(PatientOrthodonticsPlanDto patientOrthodonticsPlanDto) {
		AssertUtils.notNullAndEmpty(patientOrthodonticsPlanDto.getPatientNo(),"患者编号不能为空!");
		patientOrthodonticsPlanService.updatePatientOrthodonticsPlan(patientOrthodonticsPlanDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/get.do",produces="application/json; charset=UTF-8")
	public GeneralResponse get(PatientOrthodonticsPlanDto patientOrthodonticsPlanDto) {
		AssertUtils.notNullAndEmpty(patientOrthodonticsPlanDto.getPatientNo(),"患者编号不能为空!");
		PatientOrthodonticsPlanDto findPatientOrthodonticsPlan = patientOrthodonticsPlanService.findPatientOrthodonticsPlan(patientOrthodonticsPlanDto);
		return GeneralResponse.generateSuccessResponse(findPatientOrthodonticsPlan);
	}
}
