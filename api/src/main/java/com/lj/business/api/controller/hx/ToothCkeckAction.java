/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  ToothCkeckAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-10-28 16:35   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.lj.business.api.controller.hx;

import java.util.ArrayList;
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
import com.ye.business.hx.dto.FindToothCheckPage;
import com.ye.business.hx.dto.ToothCheckDto;
import com.ye.business.hx.service.IToothCheckService;

/**   
 * @ClassName:  ToothCkeckAction   
 * @Description:TODO(牙齿检查)   
 * @author: 贾光朝 
 * @date:   2019-10-28 16:35   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/toochCkeck")
public class ToothCkeckAction extends Action {

	@Resource
	private IToothCheckService toothCheckService;
	
	@ResponseBody
	@RequestMapping(value="/add.do",produces="application/json;charset=UTF-8")
	public GeneralResponse add(ToothCheckDto toothCheckDto) {
		AssertUtils.notNullAndEmpty(toothCheckDto);
		toothCheckDto.setCode(GUID.generateByUUID());
		toothCheckDto.setCreateDate(new Date());
		toothCheckService.addToothCheck(toothCheckDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do",produces="application/json;charset=UTF-8")
	public GeneralResponse edit(ToothCheckDto toothCheckDto) {
		AssertUtils.notNullAndEmpty(toothCheckDto.getCode(),"code不能为空!");
		toothCheckService.updateToothCheck(toothCheckDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/del.do",produces="application/json;charset=UTF-8")
	public GeneralResponse del(ToothCheckDto toothCheckDto) {
		AssertUtils.notNullAndEmpty(toothCheckDto.getCode(),"code不能为空!");
		toothCheckService.delete(toothCheckDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/page.do",produces="application/json;charset=UTF-8")
	public GeneralResponse page(FindToothCheckPage findToothCheckPage,ToothCheckDto param) {
		AssertUtils.notNullAndEmpty(param);
		findToothCheckPage.setParam(param);
		Page<ToothCheckDto> page = toothCheckService.findToothCheckPage(findToothCheckPage);
		return GeneralResponse.generateSuccessResponse(page);
	}
	
	@ResponseBody
	@RequestMapping(value="/timeList.do",produces="application/json;charset=UTF-8")
	public GeneralResponse timeList(FindToothCheckPage findToothCheckPage,ToothCheckDto param) {
		AssertUtils.notNullAndEmpty(param);
		findToothCheckPage.setParam(param);
		List<Date> list = toothCheckService.findTimeList(findToothCheckPage);
		List<ToothCheckDto> listDate = new ArrayList<>(); 
		if(list!=null) {
			for (Date date : list) {
				ToothCheckDto toothCheckDto = new ToothCheckDto();
				toothCheckDto.setCreateDate(date);
				listDate.add(toothCheckDto);
			}
		}
		return GeneralResponse.generateSuccessResponse(listDate);
	}
	
}
