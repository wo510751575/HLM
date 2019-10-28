/**  
 * All rights Reserved, Designed By www.kehuzhitongche.com
 * @Title:  MedicalImgAction.java   
 * @Package com.lj.business.api.controller.hx   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 贾光朝     
 * @date:   2019-10-22 14:58   
 * @version V1.0 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
package com.lj.business.api.controller.hx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.ye.business.hx.domain.PatientImg;
import com.ye.business.hx.dto.FindPatientImgPage;
import com.ye.business.hx.dto.FindPatientImgTypePage;
import com.ye.business.hx.dto.PatienImgListDto;
import com.ye.business.hx.dto.PatientImgDto;
import com.ye.business.hx.dto.PatientImgTypeDto;
import com.ye.business.hx.service.IPatientImgService;
import com.ye.business.hx.service.IPatientImgTypeService;

/**   
 * @ClassName:  MedicalImgAction   
 * @Description:TODO(患者影像)   
 * @author: 贾光朝 
 * @date:   2019-10-22 14:58   
 *     
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳扬恩科技有限公司内部传阅，禁止外泄
 */
@Controller
@RequestMapping("hx/medicalImg")
public class PatientImgAction extends Action {

	@Resource
	private IPatientImgService patientImgService;
	@Resource
	private IPatientImgTypeService patientImgTypeService;
	
	/**
	 * 
	 * @Title: save   
	 * @Description: TODO(保存影像图片)   
	 * @param: @param dto
	 * @param: @return      
	 * @return: GeneralResponse      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/save.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse save(PatientImgDto dto) {
		AssertUtils.notNullAndEmpty(dto);
		AssertUtils.notNullAndEmpty(dto.getImgUrl(),"图片地址不能为空!");
		String[] imgs = dto.getImgUrl().split(",");
		for (String imgUrl : imgs) {
			PatientImgDto patientImgDto = new PatientImgDto();
			patientImgDto.setCode(GUID.generateByUUID());
			patientImgDto.setCreateDate(new Date());
			patientImgDto.setImgTypeCode(dto.getImgTypeCode());
			patientImgDto.setCreateId(dto.getCreateId());
			patientImgDto.setImgUrl(imgUrl);
			patientImgDto.setPatientCode(dto.getPatientCode());
			patientImgDto.setReservationDate(dto.getReservationDate());
			patientImgService.addPatientImg(patientImgDto );
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 * @Title: save   
	 * @Description: TODO(编辑影像图片)   
	 * @param: @param dto
	 * @param: @return      
	 * @return: GeneralResponse      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/edit.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse edit(PatientImgDto dto) {
		AssertUtils.notNullAndEmpty(dto);
		AssertUtils.notNullAndEmpty(dto.getCode(),"code不能为空");
		dto.setUpdateDate(new Date());
		patientImgService.updatePatientImg(dto);
		return GeneralResponse.generateSuccessResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/delete.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse delete(PatientImgDto dto) {
		AssertUtils.notNullAndEmpty(dto.getCode());
		String[] str = dto.getCode().split(",");
		for (String code : str) {
			PatientImgDto patientImgDto = new PatientImgDto();
			patientImgDto.setCode(code);
			patientImgService.deleteImg(patientImgDto);
		}
		return GeneralResponse.generateSuccessResponse();
	}
	
	/**
	 * 
	 * @Title: imgList   
	 * @Description: TODO(影像图片列表)   
	 * @param: @return      
	 * @return: GeneralResponse      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/list.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse imgList(FindPatientImgPage findPatientImgPage,PatientImgDto param) {
		AssertUtils.notNullAndEmpty(findPatientImgPage);
		findPatientImgPage.setParam(param);
		List<PatientImg> list = patientImgService.findPatientImgs(findPatientImgPage);
		
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		//第一次循环创建一个map
		Map<String,List<PatientImg>> map = new HashMap<String, List<PatientImg>>();
		for (PatientImg patientImgDto : list) {
			List<PatientImg> imgList = new ArrayList<>();
			map.put(sft.format(patientImgDto.getReservationDate()), imgList);
		}
		//第二次循环把相同时间的数据放到一个list
		for (PatientImg patientImgDto : list) {
			for(String key:map.keySet()) {
				if(sft.format(patientImgDto.getReservationDate()).equals(key)) {
					map.get(key).add(patientImgDto);
				}
			}
		}
		List<PatienImgListDto> imgList = new ArrayList<>();
		for(String key:map.keySet()) {
			PatienImgListDto patienImgListDto = new PatienImgListDto();
			patienImgListDto.setReservationDate(key);
			patienImgListDto.setList(map.get(key));
			imgList.add(patienImgListDto);
		}
		return GeneralResponse.generateSuccessResponse(imgList);
	}
	
	/**
	 * 
	 * @Title: imgList   
	 * @Description: TODO(影像类型列表)   
	 * @param: @return      
	 * @return: GeneralResponse      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/imgTypeList.do", produces = "application/json;charset=UTF-8")
	public GeneralResponse imgTypeList(FindPatientImgTypePage findPatientImgTypePage) {
		AssertUtils.notNullAndEmpty(findPatientImgTypePage);
		List<PatientImgTypeDto> list = patientImgTypeService.findPatientImgTypes(findPatientImgTypePage);
		return GeneralResponse.generateSuccessResponse(list);
	}
}
