package com.lj.business.api.controller.hx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.FindPtTreatmentRecordPage;
import com.ye.business.hx.dto.PtTreatmentRecordDto;
import com.ye.business.hx.service.IPtTreatmentRecordService;

/**
 * 
 * 类说明：就诊记录
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年7月4日
 */
@Controller
@RequestMapping(value = "/hx/treatment/")
public class PtTreatmentRecordAction extends Action {
	
	@Autowired
	private IPtTreatmentRecordService ptTreatmentRecordService;
	
	/**
	 * 新增就诊记录
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "add.do" })
	@ResponseBody
	public PtTreatmentRecordDto addTreatment(PtTreatmentRecordDto recordDto) {
		AssertUtils.notNullAndEmpty(recordDto.getMemberNo(),"客户编号不能为空！");
		AssertUtils.notNullAndEmpty(recordDto.getMerchantNo(),"商户编号不能为空！");
		AssertUtils.notNullAndEmpty(recordDto.getMerchantName(),"商户名称不能为空！");
		
		PtTreatmentRecordDto rt= ptTreatmentRecordService.addPtTreatmentRecord(recordDto);
		return rt;
	}
	
	/**
	 *  修改就诊记录
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "edit.do" })
	@ResponseBody
	public GeneralResponse editTreatment(PtTreatmentRecordDto recordDto) {
		AssertUtils.notNullAndEmpty(recordDto.getCode(),"就诊记录编号不能为空！");
		
		ptTreatmentRecordService.updatePtTreatmentRecord(recordDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 *  就诊记录详情
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "detail.do" })
	@ResponseBody
	public GeneralResponse findTreatment(PtTreatmentRecordDto recordDto) {
		AssertUtils.notNullAndEmpty(recordDto.getCode(),"就诊记录编号不能为空！");
		
		PtTreatmentRecordDto data = ptTreatmentRecordService.findPtTreatmentRecord(recordDto);
		return GeneralResponse.generateSuccessResponse(data);
	}
	
	/**
	 *  就诊记录列表
	 * @param shopScheduleDto
	 * @return
	 */
	@RequestMapping(value = { "list.do" })
	@ResponseBody
	public Page<PtTreatmentRecordDto> findTreatmentPage(PtTreatmentRecordDto param,FindPtTreatmentRecordPage findPage) {
		AssertUtils.notNullAndEmpty(param.getMerchantNo(),"商户号不能为空！");
		
		findPage.setParam(param);
		Page<PtTreatmentRecordDto> pages = ptTreatmentRecordService.findPtTreatmentRecordPage(findPage);
		return pages;
	}
	
}
