package com.lj.business.api.controller.hx;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.ye.business.hx.dto.PatientServiceAdvisoryDto;
import com.ye.business.hx.service.IPatientServiceAdvisoryService;

/**
 * 咨询
 * 
 * @author sjiying
 *
 */
@Controller
@RequestMapping(value = "/hx/advisory/")
public class AdvisoryAction extends Action {

	// 预约咨询
	@Autowired
	private IPatientServiceAdvisoryService patientServiceAdvisoryService;

	/**
	 * 咨询新增
	 * 
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "addAdvisory.do", produces = "application/json;charset=UTF-8")
	public String addAdvisory(PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto);
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getPatientNo(), "患者编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getPatientName(), "患者名称不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getVisitingDate(), "接诊时间不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getProjectCodes(), "咨询项目编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getProjectNames(), "咨询项目不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getWant(), "成交意愿不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getCreateId(), "创建人编号不能为空");
		
		patientServiceAdvisoryDto.setCreateDate(new Date());
		
		return patientServiceAdvisoryService.addPatientServiceAdvisoryByReservation(patientServiceAdvisoryDto);
	}
	
	/**
	 * 获取咨询
	 * 
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "findAdvisoryByCode.do", produces = "application/json;charset=UTF-8")
	public PatientServiceAdvisoryDto findAdvisoryByCode(PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto);
//		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getPatientNo(), "患者编号不能为空");
		AssertUtils.notAllNullAndEmpty(patientServiceAdvisoryDto.getCode(), patientServiceAdvisoryDto.getPatientReservationCode(), "咨询编号或预约编号不能为空");
		
		if (StringUtils.isNotBlank(patientServiceAdvisoryDto.getCode())) {
			return patientServiceAdvisoryService.findPatientServiceAdvisory(patientServiceAdvisoryDto);
		} else {
			return patientServiceAdvisoryService.findPatientServiceAdvisoryByPatientReservationCode(patientServiceAdvisoryDto);
		}
		
	}
	
	/**
	 * 咨询编辑
	 * 
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "editAdvisory.do", produces = "application/json;charset=UTF-8")
	public String editAdvisory(PatientServiceAdvisoryDto patientServiceAdvisoryDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto);
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getCode(), "编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getVisitingDate(), "接诊时间不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getProjectCodes(), "咨询项目编号不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getProjectNames(), "咨询项目不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getWant(), "成交意愿不能为空");
		AssertUtils.notNullAndEmpty(patientServiceAdvisoryDto.getCreateId(), "创建人编号不能为空");
		
		patientServiceAdvisoryDto.setCreateDate(new Date());
		
		patientServiceAdvisoryService.updatePatientServiceAdvisory(patientServiceAdvisoryDto);
		
		return patientServiceAdvisoryDto.getCode();
	}

}
