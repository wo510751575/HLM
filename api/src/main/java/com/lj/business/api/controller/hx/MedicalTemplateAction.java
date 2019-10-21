package com.lj.business.api.controller.hx;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.FindPatientMedicalTemplatePage;
import com.ye.business.hx.dto.PatientMedicalTemplateDto;
import com.ye.business.hx.dto.PatientMedicalTemplateListDto;
import com.ye.business.hx.service.IPatientMedicalTemplateListService;
import com.ye.business.hx.service.IPatientMedicalTemplateService;

/**
 * 病历模版
 * 
 * @author duanzhipeng
 *
 */
@Controller
@RequestMapping(value = "/hx/medical/template")
public class MedicalTemplateAction extends Action {

	@Autowired
	private IPatientMedicalTemplateService patientMedicalTemplateService;
	@Autowired
	private IPatientMedicalTemplateListService patientMedicalTemplateListService;

	@ResponseBody
	@RequestMapping(value = "add.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse add(PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException {
		PatientMedicalTemplateListDto findDto = new PatientMedicalTemplateListDto();
		findDto.setCode(patientMedicalTemplateDto.getListCode());
		//新建病历时插入一个病历模板目录
		PatientMedicalTemplateListDto findPatientMedicalTemplateReturn = patientMedicalTemplateListService.findPatientMedicalTemplateList(findDto);
		PatientMedicalTemplateListDto patientMedicalTemplateListDto = new PatientMedicalTemplateListDto();
		patientMedicalTemplateListDto.setCode(GUID.generateCode());
		patientMedicalTemplateListDto.setName(patientMedicalTemplateDto.getName());
		patientMedicalTemplateListDto.setCreateTime(new Date());
		//类型:1-模板目录;2-模板
		patientMedicalTemplateListDto.setType("2");
		patientMedicalTemplateListDto.setLevelCode(findPatientMedicalTemplateReturn.getLevelCode()+1);
		patientMedicalTemplateListDto.setParentName(findPatientMedicalTemplateReturn.getName());
		if(findPatientMedicalTemplateReturn.getParentCodes()==null) {
			patientMedicalTemplateListDto.setParentCode("1");
			patientMedicalTemplateListDto.setParentCodes("1");
		}else {
			patientMedicalTemplateListDto.setParentCode(findPatientMedicalTemplateReturn.getCode());
			patientMedicalTemplateListDto.setParentCodes(findPatientMedicalTemplateReturn.getParentCodes()+","+findPatientMedicalTemplateReturn.getCode());
		}
		patientMedicalTemplateListService.addPatientMedicalTemplateList(patientMedicalTemplateListDto );
		//新增病历模板,code要与模板目录一致,方便后续删除
		patientMedicalTemplateDto.setCode(patientMedicalTemplateListDto.getCode());
		patientMedicalTemplateService.addPatientMedicalTemplate(patientMedicalTemplateDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value = "get.do", produces = "application/json;charset=UTF-8")
	public PatientMedicalTemplateDto get(PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto);
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getCode(), "编号不能为空");
		return patientMedicalTemplateService.findPatientMedicalTemplate(patientMedicalTemplateDto);
	}
	
	@ResponseBody
	@RequestMapping(value = "edit.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse edit(PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto);
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getCode(), "编号不能为空");
		//AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getUpdateId(), "更新人编号不能为空");
		//AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getUpdateName(), "更新人不能为空");
		
		patientMedicalTemplateService.updatePatientMedicalTemplate(patientMedicalTemplateDto);
		
		//如果更改了病历模板名称,则同步修改模板目录名称
		if(patientMedicalTemplateDto.getRemark4()!=null) {
			PatientMedicalTemplateListDto patientMedicalTemplateListDto = new PatientMedicalTemplateListDto();
			patientMedicalTemplateListDto.setCode(patientMedicalTemplateDto.getCode());
			patientMedicalTemplateListDto.setName(patientMedicalTemplateDto.getName());
			patientMedicalTemplateListService.updatePatientMedicalTemplateList(patientMedicalTemplateListDto );
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	public List<PatientMedicalTemplateDto> list(PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException {

		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto);
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getListCode(), "目录编号不能为空");
		
		FindPatientMedicalTemplatePage page = new FindPatientMedicalTemplatePage();
		page.setParam(patientMedicalTemplateDto);
		page.setSortBy("createDesc");
		
		return patientMedicalTemplateService.findPatientMedicalTemplates(page);
	}
	
	@ResponseBody
	@RequestMapping(value = "del.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse del(PatientMedicalTemplateDto patientMedicalTemplateDto) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto);
		AssertUtils.notNullAndEmpty(patientMedicalTemplateDto.getCode(), "编号不能为空");
		patientMedicalTemplateService.del(patientMedicalTemplateDto);
		return GeneralResponse.generateSuccessResponse();
	}
}
