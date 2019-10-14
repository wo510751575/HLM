package com.lj.business.api.controller.hx;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ape.common.utils.StringUtils;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.FindPatientMedicalPage;
import com.ye.business.hx.dto.PatientMedicalCheckDto;
import com.ye.business.hx.dto.PatientMedicalDmDto;
import com.ye.business.hx.dto.PatientMedicalDto;
import com.ye.business.hx.dto.PatientMedicalPlanDto;
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
//		AssertUtils.notNullAndEmpty(patientMedicalDto.getDoctorNo(), "医生编号不能为空");
//		AssertUtils.notNullAndEmpty(patientMedicalDto.getDoctorName(), "医生名称不能为空");
//		AssertUtils.notNullAndEmpty(patientMedicalDto.getAssistantNo(), "护士编号不能为空");
//		AssertUtils.notNullAndEmpty(patientMedicalDto.getAssistantName(), "护士名称不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getVisitingType(), "就诊类型不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getVisitingDate(), "接诊时间不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getCreateId(), "创建人编号不能为空");
		AssertUtils.notNullAndEmpty(patientMedicalDto.getCreateName(), "创建人不能为空");
		
		//选了牙位，必填
		if(patientMedicalDto.getChecks().size()>0) {
			for (PatientMedicalCheckDto check : patientMedicalDto.getChecks()) {
				if(check!=null && StringUtils.isNotEmpty(check.getDentalPosition())) {
					AssertUtils.notAllNullAndEmpty(check.getCheckOralRemark(),check.getCheckAuxiliaryRemark(), "口腔检查与辅助检查不能同时为空");
				}
			}
		}
		if(patientMedicalDto.getPlans().size()>0) {
			for (PatientMedicalPlanDto plan : patientMedicalDto.getPlans()) {
				if(plan!=null && StringUtils.isNotEmpty(plan.getDentalPosition())) {
					AssertUtils.notAllNullAndEmpty(plan.getPlanDiagnosisRemark(),plan.getPlanTreatmentRemark(), "诊断与治疗计划不能同时为空");
				}
			}
		}
		
		if(patientMedicalDto.getDms().size()>0) {
			for (PatientMedicalDmDto dm : patientMedicalDto.getDms()) {
				if(dm!=null && StringUtils.isNotEmpty(dm.getDentalPosition())) {
					AssertUtils.notNullAndEmpty(dm.getDmDisposalRemark(), "处置不能为空");
				}
			}
		}

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
			return patientMedicalService.findPatientMedical(patientMedicalDto.getCode());
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
		
		//选了牙位，必填
		if(patientMedicalDto.getChecks().size()>0) {
			for (PatientMedicalCheckDto check : patientMedicalDto.getChecks()) {
				if(check!=null && StringUtils.isNotEmpty(check.getDentalPosition())) {
					AssertUtils.notAllNullAndEmpty(check.getCheckOralRemark(),check.getCheckAuxiliaryRemark(), "口腔检查与辅助检查不能同时为空");
				}
			}
		}
		if(patientMedicalDto.getPlans().size()>0) {
			for (PatientMedicalPlanDto plan : patientMedicalDto.getPlans()) {
				if(plan!=null && StringUtils.isNotEmpty(plan.getDentalPosition())) {
					AssertUtils.notAllNullAndEmpty(plan.getPlanDiagnosisRemark(),plan.getPlanTreatmentRemark(), "诊断与治疗计划不能同时为空");
				}
			}
		}
		
		if(patientMedicalDto.getDms().size()>0) {
			for (PatientMedicalDmDto dm : patientMedicalDto.getDms()) {
				if(dm!=null && StringUtils.isNotEmpty(dm.getDentalPosition())) {
					AssertUtils.notNullAndEmpty(dm.getDmDisposalRemark(), "处置不能为空");
				}
			}
		}
		patientMedicalService.updatePatientMedical(patientMedicalDto);
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
	
	@ResponseBody
	@RequestMapping(value = "del.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse del(PatientMedicalDto patientMedicalDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientMedicalDto);
		AssertUtils.notNullAndEmpty(patientMedicalDto.getCode(), "编号不能为空");
		
		PatientMedicalDto dto= patientMedicalService.findPatientMedical(patientMedicalDto);
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -7);
		if(dto.getCreateDate().compareTo(calendar.getTime())<=0) {
			return GeneralResponse.generateFailureResponse("", "当前病历已超出时限范围，不能删除");
		}
		patientMedicalService.delPatientMedical(patientMedicalDto);
		return GeneralResponse.generateSuccessResponse();
	}
}
