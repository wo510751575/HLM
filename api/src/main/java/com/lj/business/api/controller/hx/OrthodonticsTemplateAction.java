/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  OrthodonticsTemplateAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-11-01 16:34   
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

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.FindOrthodonticsTemplatePage;
import com.ye.business.hx.dto.OrthodonticsTemplateDto;
import com.ye.business.hx.dto.OrthodonticsTemplateVo;
import com.ye.business.hx.service.IOrthodonticsTemplateService;

/**   
 * @ClassName:  OrthodonticsTemplateAction   
 * @Description:TODO(正畸过程模板/目录)   
 * @author: 贾光朝 
 * @date:   2019-11-01 16:34   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/orthodonticsTemplate")
public class OrthodonticsTemplateAction extends Action {

	@Resource
	private IOrthodonticsTemplateService orthodonticsTemplateService;
	
	@ResponseBody
	@RequestMapping(value="/add.do")
	public GeneralResponse add(OrthodonticsTemplateDto orthodonticsTemplateDto) {
		AssertUtils.notNullAndEmpty(orthodonticsTemplateDto);
		orthodonticsTemplateDto.setCode(GUID.generateByUUID());
		orthodonticsTemplateDto.setCreateDate(new Date());
		orthodonticsTemplateService.addOrthodonticsTemplate(orthodonticsTemplateDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do")
	public GeneralResponse edit(OrthodonticsTemplateDto orthodonticsTemplateDto) {
		AssertUtils.notNullAndEmpty(orthodonticsTemplateDto.getCode(),"code不能为空!");
		orthodonticsTemplateService.updateOrthodonticsTemplate(orthodonticsTemplateDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/get.do")
	public GeneralResponse get(OrthodonticsTemplateDto orthodonticsTemplateDto) {
		AssertUtils.notNullAndEmpty(orthodonticsTemplateDto.getCode(),"code不能为空!");
		OrthodonticsTemplateDto findOrthodonticsTemplate = orthodonticsTemplateService.findOrthodonticsTemplate(orthodonticsTemplateDto);
		return GeneralResponse.generateSuccessResponse(findOrthodonticsTemplate);
	}
	
	@ResponseBody
	@RequestMapping(value="/del.do")
	public GeneralResponse delete(OrthodonticsTemplateDto orthodonticsTemplateDto) {
		AssertUtils.notNullAndEmpty(orthodonticsTemplateDto.getCode(),"code不能为空!");
		orthodonticsTemplateService.delete(orthodonticsTemplateDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/list.do")
	public GeneralResponse list(FindOrthodonticsTemplatePage findOrthodonticsTemplatePage,OrthodonticsTemplateDto param) {
		List<OrthodonticsTemplateVo> list = orthodonticsTemplateService.findOrthodonticsTemplates();
		return GeneralResponse.generateSuccessResponse(list);
	}
}
