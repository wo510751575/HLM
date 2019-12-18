/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  InformedConsentAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-10-31 16:05   
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
import com.ye.business.hx.dto.FindInformedConsentPage;
import com.ye.business.hx.dto.InformedConsentDto;
import com.ye.business.hx.service.IInformedConsentService;

/**   
 * @ClassName:  InformedConsentAction   
 * @Description:TODO(知情同意书)   
 * @author: 贾光朝 
 * @date:   2019-10-31 16:05   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping(value="/hx/informedconsent")
public class InformedConsentAction extends Action {

	@Resource
	private IInformedConsentService informedConsentService;
	
	@ResponseBody
	@RequestMapping(value="/add.do",produces="application/json;charset=UTF-8")
	public GeneralResponse add(InformedConsentDto informedConsentDto) {
		AssertUtils.notNullAndEmpty(informedConsentDto);
		informedConsentDto.setCode(GUID.generateByUUID());
		informedConsentDto.setCreateDate(new Date());
		informedConsentService.addInformedConsent(informedConsentDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/edit.do",produces="application/json;charset=UTF-8")
	public GeneralResponse edit(InformedConsentDto informedConsentDto) {
		AssertUtils.notNullAndEmpty(informedConsentDto.getCode(),"code不能为空!");
		informedConsentService.updateInformedConsent(informedConsentDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/del.do",produces="application/json;charset=UTF-8")
	public GeneralResponse delete(InformedConsentDto informedConsentDto) {
		AssertUtils.notNullAndEmpty(informedConsentDto.getCode(),"code不能为空!");
		informedConsentService.delete(informedConsentDto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/get.do",produces="application/json;charset=UTF-8")
	public GeneralResponse get(InformedConsentDto informedConsentDto) {
		AssertUtils.notNullAndEmpty(informedConsentDto.getCode(),"code不能为空!");
		InformedConsentDto findInformedConsent = informedConsentService.findInformedConsent(informedConsentDto);
		return GeneralResponse.generateSuccessResponse(findInformedConsent);
	}
	
	@ResponseBody
	@RequestMapping(value="/list.do",produces="application/json;charset=UTF-8")
	public GeneralResponse list(FindInformedConsentPage findInformedConsentPage,InformedConsentDto param) {
		findInformedConsentPage.setParam(param);
		Page<InformedConsentDto> page = informedConsentService.findInformedConsentPage(findInformedConsentPage);
		return GeneralResponse.generateSuccessResponse(page);
	}
}
