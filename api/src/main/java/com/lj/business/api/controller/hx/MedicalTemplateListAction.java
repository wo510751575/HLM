package com.lj.business.api.controller.hx;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.FindPatientMedicalTemplateListPage;
import com.ye.business.hx.dto.FindPatientMedicalTemplatePage;
import com.ye.business.hx.dto.PatientMedicalTemplateDto;
import com.ye.business.hx.dto.PatientMedicalTemplateListDto;
import com.ye.business.hx.service.IPatientMedicalTemplateListService;
import com.ye.business.hx.service.IPatientMedicalTemplateService;

/**
 * 病历模版目录
 * 
 * @author duanzhipeng
 *
 */
@Controller
@RequestMapping(value = "/hx/medical/templateList")
public class MedicalTemplateListAction extends Action {

	@Autowired
	private IPatientMedicalTemplateListService patientMedicalTemplateListService;
	@Autowired
	private IPatientMedicalTemplateService patientMedicalTemplateService;

	@ResponseBody
	@RequestMapping(value = "add.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse add(PatientMedicalTemplateListDto patientMedicalTemplateDto) throws TsfaServiceException {
		patientMedicalTemplateListService.addPatientMedicalTemplateList(patientMedicalTemplateDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value = "get.do", produces = "application/json;charset=UTF-8")
	public PatientMedicalTemplateListDto get(PatientMedicalTemplateListDto patientMedicalTemplateDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto);
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getCode(), "编号不能为空");
		return patientMedicalTemplateListService.findPatientMedicalTemplateList(patientMedicalTemplateDto);
	}
	
	@ResponseBody
	@RequestMapping(value = "edit.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse edit(PatientMedicalTemplateListDto patientMedicalTemplateDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto);
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getCode(), "编号不能为空");
		
		patientMedicalTemplateListService.updatePatientMedicalTemplateList(patientMedicalTemplateDto);
		
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	public List<PatientMedicalTemplateListDto> list(PatientMedicalTemplateListDto patientMedicalTemplateDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto);
		
		FindPatientMedicalTemplateListPage page = new FindPatientMedicalTemplateListPage();
		page.setParam(patientMedicalTemplateDto);
		page.setSortBy("createDesc");
		
		List<PatientMedicalTemplateListDto> sourceList = patientMedicalTemplateListService.findPatientMedicalTemplateLists(page);
		
		List<PatientMedicalTemplateListDto> list = new ArrayList<PatientMedicalTemplateListDto>();
		PatientMedicalTemplateListDto.sortList(list, sourceList, PatientMedicalTemplateListDto.getRootId(), true);
		logger.debug("templateList --> list( return={}) - end",list);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "del.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse del(PatientMedicalTemplateListDto patientMedicalTemplateDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto);
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getCode(), "编号不能为空");
		
		//删除目录有子目录或模板时
		FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage = new FindPatientMedicalTemplateListPage();
		PatientMedicalTemplateListDto paramListDto = new PatientMedicalTemplateListDto();
		paramListDto.setParentCode(patientMedicalTemplateDto.getCode());
		findPatientMedicalTemplateListPage.setParam(paramListDto);
		List<PatientMedicalTemplateListDto> list = patientMedicalTemplateListService.findPatientMedicalTemplateLists(findPatientMedicalTemplateListPage);
		if(list.size()>0) {
			return GeneralResponse.generateFailureResponse("", "当前目录不能删除，请先删除子目录或模板");
		}
		
		FindPatientMedicalTemplatePage findPatientMedicalTemplatePage = new FindPatientMedicalTemplatePage();
		PatientMedicalTemplateDto paramDto = new PatientMedicalTemplateDto();
		paramDto.setListCode(patientMedicalTemplateDto.getCode());
		findPatientMedicalTemplatePage.setParam(paramDto);
		List<PatientMedicalTemplateDto> templateList =patientMedicalTemplateService.findPatientMedicalTemplates(findPatientMedicalTemplatePage);
		if(templateList.size()>0) {
			return GeneralResponse.generateFailureResponse("", "当前目录不能删除，请先删除子目录或模板");
		}
		
		patientMedicalTemplateListService.del(patientMedicalTemplateDto);
		return GeneralResponse.generateSuccessResponse();
	}
}
