/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  PatientSymptom.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-10-30 11:28   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.lj.business.api.controller.hx;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.FindPatientSymptomPage;
import com.ye.business.hx.dto.PatientSymptomDto;
import com.ye.business.hx.dto.PatientSymptomVo;
import com.ye.business.hx.service.IPatientSymptomService;

/**   
 * @ClassName:  PatientSymptom   
 * @Description:TODO(牙齿检查-症状)   
 * @author: 贾光朝 
 * @date:   2019-10-30 11:28   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/patientSymptom")
public class PatientSymptomAction extends Action {

	@Resource
	private IPatientSymptomService patientSymptomService;
	
	@ResponseBody
	@RequestMapping(value="/list.do",produces="application/json;charset=UTF-8")
	public GeneralResponse list(FindPatientSymptomPage findPatientSymptomPage,PatientSymptomDto param) {
		findPatientSymptomPage.setParam(param);
		List<PatientSymptomVo> list = patientSymptomService.findPatientSymptoms(findPatientSymptomPage);
		return GeneralResponse.generateSuccessResponse(list);
		
	}
	
}
