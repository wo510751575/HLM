/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  DiagnosisAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-10-31 14:58   
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

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.DiagnosisDto;
import com.ye.business.hx.dto.FindDiagnosisPage;
import com.ye.business.hx.service.IDiagnosisService;

/**   
 * @ClassName:  DiagnosisAction   
 * @Description:TODO(牙型/牙骨)   
 * @author: 贾光朝 
 * @date:   2019-10-31 14:58   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/diagnosis")
public class DiagnosisAction extends Action {

	@Resource
	private IDiagnosisService diagnosisService;
	
	@ResponseBody
	@RequestMapping(value="/add.do",produces="application/json;charset=UTF-8")
	public GeneralResponse add(DiagnosisDto diagnosisDto) {
		AssertUtils.notNullAndEmpty(diagnosisDto);
		diagnosisDto.setCode(GUID.generateByUUID());
		diagnosisDto.setCreateDate(new Date());
		diagnosisService.addDiagnosis(diagnosisDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do",produces="application/json;charset=UTF-8")
	public GeneralResponse edit(DiagnosisDto diagnosisDto) {
		AssertUtils.notNullAndEmpty(diagnosisDto.getCode(),"code不能为空!");
		diagnosisService.updateDiagnosis(diagnosisDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/del.do",produces="application/json;charset=UTF-8")
	public GeneralResponse delete(DiagnosisDto diagnosisDto) {
		AssertUtils.notNullAndEmpty(diagnosisDto.getCode(),"code不能为空!");
		diagnosisService.delete(diagnosisDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/list.do",produces="application/json;charset=UTF-8")
	public GeneralResponse list(FindDiagnosisPage findDiagnosisPage,DiagnosisDto param) {
		AssertUtils.notNullAndEmpty(param.getType(),"type不能为空!");
		findDiagnosisPage.setParam(param);
		Page<DiagnosisDto> page = diagnosisService.findDiagnosisPage(findDiagnosisPage);
		return GeneralResponse.generateSuccessResponse(page);
	}
	
}
