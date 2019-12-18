/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  OrthodonticsPlanAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-10-31 16:55   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.lj.business.api.controller.hx;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.FindOrthodonticsPlanPage;
import com.ye.business.hx.dto.OrthodonticsPlanDto;
import com.ye.business.hx.service.IOrthodonticsPlanService;

/**   
 * @ClassName:  OrthodonticsPlanAction   
 * @Description:TODO(正畸计划配置:主诉/问题/矫正目标/治疗步骤)   
 * @author: 贾光朝 
 * @date:   2019-10-31 16:55   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/orthodonticsPlan")
public class OrthodonticsPlanAction extends Action {

	@Resource
	private IOrthodonticsPlanService orthodonticsPlanService;
	
	@ResponseBody
	@RequestMapping(value="/add.do",produces="application/json;charset=UTF-8")
	public GeneralResponse add(OrthodonticsPlanDto orthodonticsPlanDto) {
		AssertUtils.notNullAndEmpty(orthodonticsPlanDto);
		orthodonticsPlanDto.setCode(GUID.generateByUUID());
		orthodonticsPlanDto.setCreateDate(new Date());
		orthodonticsPlanService.addOrthodonticsPlan(orthodonticsPlanDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do",produces="application/json;charset=UTF-8")
	public GeneralResponse edit(OrthodonticsPlanDto orthodonticsPlanDto) {
		AssertUtils.notNullAndEmpty(orthodonticsPlanDto.getCode(),"code不能为空");
		orthodonticsPlanService.updateOrthodonticsPlan(orthodonticsPlanDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/del.do",produces="application/json;charset=UTF-8")
	public GeneralResponse delete(OrthodonticsPlanDto orthodonticsPlanDto) {
		AssertUtils.notNullAndEmpty(orthodonticsPlanDto.getCode(),"code不能为空");
		orthodonticsPlanService.delete(orthodonticsPlanDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/get.do",produces="application/json;charset=UTF-8")
	public GeneralResponse get(OrthodonticsPlanDto orthodonticsPlanDto) {
		AssertUtils.notNullAndEmpty(orthodonticsPlanDto.getCode(),"code不能为空");
		OrthodonticsPlanDto findOrthodonticsPlan = orthodonticsPlanService.findOrthodonticsPlan(orthodonticsPlanDto);
		return GeneralResponse.generateSuccessResponse(findOrthodonticsPlan);
	}
	
	@ResponseBody
	@RequestMapping(value="/list.do",produces="application/json;charset=UTF-8")
	public GeneralResponse list(FindOrthodonticsPlanPage findOrthodonticsPlanPage,OrthodonticsPlanDto param) {
		AssertUtils.notNullAndEmpty(param);
		findOrthodonticsPlanPage.setParam(param);
		Page<OrthodonticsPlanDto> page = orthodonticsPlanService.findOrthodonticsPlanPage(findOrthodonticsPlanPage);
		return GeneralResponse.generateSuccessResponse(page);
	}
	
	@ResponseBody
	@RequestMapping(value="/getMaxSort.do",produces="application/json;charset=UTF-8")
	public GeneralResponse getMaxSort(OrthodonticsPlanDto param) {
		Integer sort = orthodonticsPlanService.getMaxSort();
		return GeneralResponse.generateSuccessResponse(sort);
	}
}
