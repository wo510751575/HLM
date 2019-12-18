/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  PatientTreatmentPlanAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-12-05 17:18   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.lj.business.api.controller.hx;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.FindPatientTreatmentPlanPage;
import com.ye.business.hx.dto.OrthodonticsPlanDto;
import com.ye.business.hx.dto.PatientTreatmentPlanDto;
import com.ye.business.hx.service.IOrthodonticsPlanService;
import com.ye.business.hx.service.IPatientTreatmentPlanService;

/**
 * @ClassName: PatientTreatmentPlanAction
 * @Description:TODO(正畸计划-治疗计划)
 * @author: 贾光朝
 * @date: 2019-12-05 17:18
 * 
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved.
 *             注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping("/hx/patientTreatmentPlan")
public class PatientTreatmentPlanAction extends Action {

	@Resource
	private IPatientTreatmentPlanService patientTreatmentPlanService;
	@Resource
	private IOrthodonticsPlanService orthodonticsPlanService;

	@ResponseBody
	@RequestMapping(value = "/add.do", produces = "application/json; charset=UTF-8")
	public GeneralResponse add(PatientTreatmentPlanDto patientTreatmentPlanDto) {
		patientTreatmentPlanDto.setCode(GUID.generateByUUID());
		patientTreatmentPlanDto.setCreateDate(new Date());
		// 如果治疗步骤不为空
		List<OrthodonticsPlanDto> parseArray = JSON.parseArray(patientTreatmentPlanDto.getList(),
				OrthodonticsPlanDto.class);
		if (null != parseArray) {
			for (OrthodonticsPlanDto orthodonticsPlanDto : parseArray) {
				orthodonticsPlanDto.setCode(GUID.generateByUUID());
				orthodonticsPlanDto.setCreateDate(new Date());
				orthodonticsPlanDto.setPatientNo(patientTreatmentPlanDto.getPatientNo());
				// remark4关联patientTreatmentPlan主键code
				orthodonticsPlanDto.setRemark4(patientTreatmentPlanDto.getCode());
				orthodonticsPlanService.addOrthodonticsPlan(orthodonticsPlanDto);
			}
		}
		patientTreatmentPlanService.addPatientTreatmentPlan(patientTreatmentPlanDto);
		return GeneralResponse.generateSuccessResponse();
	}

	@ResponseBody
	@RequestMapping(value = "/edit.do", produces = "application/json; charset=UTF-8")
	public GeneralResponse edit(PatientTreatmentPlanDto patientTreatmentPlanDto) {
		AssertUtils.notNullAndEmpty(patientTreatmentPlanDto.getCode(), "code不能为空!");
		// 如果治疗步骤不为空
		List<OrthodonticsPlanDto> parseArray = JSON.parseArray(patientTreatmentPlanDto.getList(),
				OrthodonticsPlanDto.class);
		if (null != parseArray) {
			for (OrthodonticsPlanDto orthodonticsPlanDto : parseArray) {
				if (StringUtils.isEmpty(orthodonticsPlanDto.getCode())) {
					orthodonticsPlanDto.setCode(GUID.generateByUUID());
					orthodonticsPlanDto.setCreateDate(new Date());
					orthodonticsPlanDto.setPatientNo(patientTreatmentPlanDto.getPatientNo());
					// remark4关联patientTreatmentPlan主键code
					orthodonticsPlanDto.setRemark4(patientTreatmentPlanDto.getCode());
					orthodonticsPlanService.addOrthodonticsPlan(orthodonticsPlanDto);
				} else {
					orthodonticsPlanService.updateOrthodonticsPlan(orthodonticsPlanDto);
				}
			}
		}
		patientTreatmentPlanService.updatePatientTreatmentPlan(patientTreatmentPlanDto);
		return GeneralResponse.generateSuccessResponse();
	}

	@ResponseBody
	@RequestMapping(value = "/get.do", produces = "application/json; charset=UTF-8")
	public GeneralResponse get(PatientTreatmentPlanDto patientTreatmentPlanDto) {
		AssertUtils.notNullAndEmpty(patientTreatmentPlanDto.getPatientNo(), "患者编号不能为空!");
		PatientTreatmentPlanDto findPatientTreatmentPlan = patientTreatmentPlanService
				.findPatientTreatmentPlan(patientTreatmentPlanDto);
		return GeneralResponse.generateSuccessResponse(findPatientTreatmentPlan);
	}
	
	@ResponseBody
	@RequestMapping(value = "/del.do", produces = "application/json; charset=UTF-8")
	public GeneralResponse del(PatientTreatmentPlanDto patientTreatmentPlanDto) {
		AssertUtils.notNullAndEmpty(patientTreatmentPlanDto.getCode(), "code不能为空!");
		patientTreatmentPlanService.del(patientTreatmentPlanDto);
		return GeneralResponse.generateSuccessResponse();
	}

	@ResponseBody
	@RequestMapping(value = "/list.do", produces = "application/json; charset=UTF-8")
	public GeneralResponse list(FindPatientTreatmentPlanPage findPatientTreatmentPlanPage,
			PatientTreatmentPlanDto param) {
		AssertUtils.notNullAndEmpty(param.getPatientNo(), "患者编号不能为空!");
		findPatientTreatmentPlanPage.setParam(param);
		List<PatientTreatmentPlanDto> list = patientTreatmentPlanService
				.findPatientTreatmentPlans(findPatientTreatmentPlanPage);
		return GeneralResponse.generateSuccessResponse(list);
	}
	
	
}
