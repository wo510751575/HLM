/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  DentitionCheckAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-11-05 11:40   
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

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.dto.DentitionCheckDto;
import com.ye.business.hx.service.IDentitionCheckService;

/**   
 * @ClassName:  DentitionCheckAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 贾光朝 
 * @date:   2019-11-05 11:40   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/dentitionCheck")
public class DentitionCheckAction extends Action {

	@Resource
	private IDentitionCheckService dentitionCheckService;
	
	@ResponseBody
	@RequestMapping(value="/add.do",produces="application/json;charset=UTF-8")
	public GeneralResponse add(DentitionCheckDto dentitionCheckDto) {
		dentitionCheckDto.setCode(GUID.generateByUUID());
		dentitionCheckDto.setCreateDate(new Date());
		dentitionCheckService.addDentitionCheck(dentitionCheckDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do",produces="application/json;charset=UTF-8")
	public GeneralResponse edit(DentitionCheckDto dentitionCheckDto) {
		AssertUtils.notNullAndEmpty(dentitionCheckDto.getCode(),"code不能为空!");
		dentitionCheckService.updateDentitionCheck(dentitionCheckDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/get.do",produces="application/json;charset=UTF-8")
	public GeneralResponse get(DentitionCheckDto dentitionCheckDto) {
		AssertUtils.notNullAndEmpty(dentitionCheckDto.getCode(),"code不能为空!");
		DentitionCheckDto findDentitionCheck = dentitionCheckService.findDentitionCheck(dentitionCheckDto);
		return GeneralResponse.generateSuccessResponse(findDentitionCheck);
	}
}
