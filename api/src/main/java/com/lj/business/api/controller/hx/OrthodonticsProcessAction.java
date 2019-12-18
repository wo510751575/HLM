/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  OrthodonticsProcess.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(正畸病历-正畸过程)   
 * @author: 贾光朝     
 * @date:   2019-12-05 17:15   
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
import com.ye.business.hx.dto.FindOrthodonticsProcessRecordPage;
import com.ye.business.hx.dto.OrthodonticsProcessDto;
import com.ye.business.hx.dto.OrthodonticsProcessExtDto;
import com.ye.business.hx.dto.OrthodonticsProcessRecordDto;
import com.ye.business.hx.service.IOrthodonticsProcessRecordService;
import com.ye.business.hx.service.IOrthodonticsProcessService;

/**
 * @ClassName: PatientTreatmentPlanAction
 * @Description:TODO(正畸病历-正畸过程)
 * @author: 贾光朝
 * @date: 2019-12-05 17:18
 * 
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved.
 *             注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping("/hx/orthodonticsProcess")
public class OrthodonticsProcessAction extends Action {

	@Resource
	private IOrthodonticsProcessService orthodonticsProcessService;
	@Resource
	private IOrthodonticsProcessRecordService orthodonticsProcessRecordService;

	@ResponseBody
	@RequestMapping(value = "/add.do", produces = "application/json; charset=UTF-8")
	public GeneralResponse add(OrthodonticsProcessExtDto orthodonticsProcessExtDto) {
		if (StringUtils.isNotEmpty(orthodonticsProcessExtDto.getList())) {
			// 新增正畸过程记录
			OrthodonticsProcessRecordDto orthodonticsProcessRecordDto = new OrthodonticsProcessRecordDto();
			orthodonticsProcessRecordDto.setCode(GUID.generateByUUID());
			orthodonticsProcessRecordDto.setPatientNo(orthodonticsProcessExtDto.getPatientNo());
			orthodonticsProcessRecordDto.setCreateDate(orthodonticsProcessExtDto.getCreateDate());
			orthodonticsProcessRecordDto.setAttendingDoctor(orthodonticsProcessExtDto.getAttendingDoctor());
			orthodonticsProcessRecordDto.setCreateBy(null);// 创建人
			orthodonticsProcessRecordDto.setClinic(null);// 诊所
			orthodonticsProcessRecordDto.setNurse(orthodonticsProcessExtDto.getNurse());
			orthodonticsProcessRecordService.addOrthodonticsProcessRecord(orthodonticsProcessRecordDto);

			List<OrthodonticsProcessDto> list = JSON.parseArray(orthodonticsProcessExtDto.getList(),
					OrthodonticsProcessDto.class);
			for (OrthodonticsProcessDto orthodonticsProcess : list) {
				orthodonticsProcess.setCode(GUID.generateByUUID());
				orthodonticsProcess.setPatientNo(orthodonticsProcessExtDto.getPatientNo());
				orthodonticsProcess.setAttendingDoctor(orthodonticsProcessExtDto.getAttendingDoctor());
				orthodonticsProcess.setNurse(orthodonticsProcessExtDto.getNurse());
				orthodonticsProcess.setCreateDate(orthodonticsProcessExtDto.getCreateDate());
				orthodonticsProcess.setRecordCode(orthodonticsProcessRecordDto.getCode());

				orthodonticsProcessService.addOrthodonticsProcess(orthodonticsProcess);
			}
		}
		return GeneralResponse.generateSuccessResponse();
	}

	@ResponseBody
	@RequestMapping(value = "/edit.do", produces = "application/json; charset=UTF-8")
	public GeneralResponse edit(OrthodonticsProcessExtDto orthodonticsProcessExtDto) {
		AssertUtils.notNullAndEmpty(orthodonticsProcessExtDto.getRecordCode(), "recordCode不能为空");
		if (StringUtils.isNotEmpty(orthodonticsProcessExtDto.getList())) {
			List<OrthodonticsProcessDto> list = JSON.parseArray(orthodonticsProcessExtDto.getList(),
					OrthodonticsProcessDto.class);
			for (OrthodonticsProcessDto orthodonticsProcess : list) {
				if (StringUtils.isNotEmpty(orthodonticsProcess.getCode())) {
					orthodonticsProcess.setUpdateDate(new Date());
					orthodonticsProcessService.updateOrthodonticsProcess(orthodonticsProcess);
				} else {
					orthodonticsProcess.setCode(GUID.generateByUUID());
					orthodonticsProcess.setPatientNo(orthodonticsProcessExtDto.getPatientNo());
					orthodonticsProcess.setAttendingDoctor(orthodonticsProcessExtDto.getAttendingDoctor());
					orthodonticsProcess.setNurse(orthodonticsProcessExtDto.getNurse());
					orthodonticsProcess.setCreateDate(orthodonticsProcessExtDto.getCreateDate());
					orthodonticsProcess.setRecordCode(orthodonticsProcessExtDto.getRecordCode());

					orthodonticsProcessService.addOrthodonticsProcess(orthodonticsProcess);
				}
			}
		}
		return GeneralResponse.generateSuccessResponse();
	}

	@ResponseBody
	@RequestMapping(value = "/get.do", produces = "application/json; charset=UTF-8")
	public GeneralResponse get(OrthodonticsProcessDto orthodonticsProcessDto) {
		AssertUtils.notNullAndEmpty(orthodonticsProcessDto.getPatientNo(), "客户编号不能为空!");
		FindOrthodonticsProcessRecordPage findOrthodonticsProcessRecordPage = new FindOrthodonticsProcessRecordPage();
		OrthodonticsProcessRecordDto param = new OrthodonticsProcessRecordDto();
		param.setPatientNo(orthodonticsProcessDto.getPatientNo());
		findOrthodonticsProcessRecordPage.setParam(param);
		List<OrthodonticsProcessRecordDto> recordList = orthodonticsProcessRecordService
				.findOrthodonticsProcessRecords(findOrthodonticsProcessRecordPage);
		for (OrthodonticsProcessRecordDto orthodonticsProcessRecordDto : recordList) {
			orthodonticsProcessDto.setRecordCode(orthodonticsProcessRecordDto.getCode());
			List<OrthodonticsProcessDto> list = orthodonticsProcessService
					.findOrthodonticsProcess(orthodonticsProcessDto);
			for (OrthodonticsProcessDto OrthodonticsProcessDto : list) {
				switch (OrthodonticsProcessDto.getType()) {
				case 1:
					orthodonticsProcessRecordDto.getMangement().add(OrthodonticsProcessDto);
					break;
				case 2:
					orthodonticsProcessRecordDto.getCheck().add(OrthodonticsProcessDto);
					break;
				case 3:
					orthodonticsProcessRecordDto.getDoctorAdvice().add(OrthodonticsProcessDto);
					break;

				default:
					break;
				}
			}
		}
		return GeneralResponse.generateSuccessResponse(recordList);
	}
	
	@ResponseBody
	@RequestMapping(value = "/del.do", produces = "application/json; charset=UTF-8")
	public GeneralResponse del(OrthodonticsProcessDto orthodonticsProcessDto) {
		orthodonticsProcessRecordService.del(orthodonticsProcessDto);
		return GeneralResponse.generateSuccessResponse();
	}
}
