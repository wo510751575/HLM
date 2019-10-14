package com.lj.business.api.controller.hx;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.ye.business.hx.dto.FindPatientMedicalPage;
import com.ye.business.hx.dto.PatientMedicalDto;
import com.ye.business.hx.service.IPatientMedicalService;

/**
 * 病历
 * 
 * @author sjiying
 *
 */
@Controller
@RequestMapping(value = "/hx/medical/")
public class MedicalAction extends Action {

	// 患者病历
	@Autowired
	private IPatientMedicalService patientMedicalService;

	/**
	 * 新增
	 * 
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "addMedical.do", produces = "application/json;charset=UTF-8")
	public String addMedical(PatientMedicalDto patientMedicalDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientMedicalDto);
		AssertUtils.notNullAndEmpty(patientMedicalDto.getPatientNo(), "患者编号不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getPatientName(), "患者名称不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getVisitingType(), "就诊类型不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getVisitingDate(), "接诊时间不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getCreateId(), "创建人编号不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getCreateName(), "创建人不能为空");

		Date now = new Date();

		patientMedicalDto.setCreateDate(now);
		if (StringUtils.isBlank(patientMedicalDto.getUpdateId())) {
			patientMedicalDto.setUpdateId(patientMedicalDto.getCreateId());
			patientMedicalDto.setUpdateName(patientMedicalDto.getCreateName());
		}
		patientMedicalDto.setUpdateDate(now);
		
		return patientMedicalService.addPatientMedicalByReservation(patientMedicalDto);
	}
	
	/**
	 * 获取病历
	 * 
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "findMedicalByCode.do", produces = "application/json;charset=UTF-8")
	public PatientMedicalDto findMedicalByCode(PatientMedicalDto patientMedicalDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientMedicalDto);
//		AssertUtils.notNullAndEmpty(patientMedicalDto.getPatientNo(), "患者编号不能为空");
		AssertUtils.notAllNullAndEmpty(patientMedicalDto.getCode(), patientMedicalDto.getPatientReservationCode(), "病历编号或预约编号不能为空");
		
		if (StringUtils.isNotBlank(patientMedicalDto.getCode())) {
			return patientMedicalService.findPatientMedical(patientMedicalDto);
		} else {
			return patientMedicalService.findPatientMedicalByPatientReservationCode(patientMedicalDto);
		}
	}
	
	/**
	 * 病历编辑
	 * 
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "editMedical.do", produces = "application/json;charset=UTF-8")
	public String editMedical(PatientMedicalDto patientMedicalDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientMedicalDto);
		AssertUtils.notNullAndEmpty(patientMedicalDto.getCode(), "编号不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getUpdateId(), "更新人编号不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getUpdateName(), "更新人不能为空");
		
		patientMedicalDto.setUpdateDate(new Date());
		
		patientMedicalService.updatePatientMedicalByCode(patientMedicalDto);
		
		return patientMedicalDto.getCode();
	}
	
	/**
	 * 病历列表
	 * 
	 * @return
	 * @throws TsfaServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	public List<PatientMedicalDto> list(PatientMedicalDto patientMedicalDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientMedicalDto);
		AssertUtils.notNullAndEmpty(patientMedicalDto.getPatientNo(), "患者编号不能为空");
		
		FindPatientMedicalPage findPatientMedicalPage = new FindPatientMedicalPage();
		findPatientMedicalPage.setParam(patientMedicalDto);
		
		findPatientMedicalPage.setSortBy("createDesc");
		
		return patientMedicalService.findPatientMedicals(findPatientMedicalPage);
	}
}
