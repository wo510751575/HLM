package com.lj.business.api.controller.imh5;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.cm.dto.FindMaterialLinkPage;
import com.lj.business.cm.dto.FindMaterialLinkPageReturn;
import com.lj.business.cm.dto.FindMaterialTextPage;
import com.lj.business.cm.dto.FindMaterialTextPageReturn;
import com.lj.business.cm.dto.MaterialTypes;
import com.lj.business.cm.dto.wordsType.FindMaterialPhotoPage;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPage;
import com.lj.business.cm.dto.wordsType.FindMaterialVideoPageReturn;
import com.lj.business.cm.service.IMaterialLinkService;
import com.lj.business.cm.service.IMaterialPhotoService;
import com.lj.business.cm.service.IMaterialTextService;
import com.lj.business.cm.service.IMaterialTypesService;
import com.lj.business.cm.service.IMaterialVideoService;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年4月23日下午7:06:53
 */
@Controller
@RequestMapping(value="/imh5/imMaterial/")
public class ImMaterialAction extends Action{

	private static final Logger logger = LoggerFactory.getLogger(ImMaterialAction.class);
	
	@Resource
	private IMaterialTypesService materialTypesService;
	@Resource
	private IMaterialVideoService materialVideoService;
	@Resource
	private IMaterialPhotoService materialPhotoService;
	@Resource
	private IMaterialTextService materialTextService;
	@Resource
	private IMaterialLinkService  materialLinkService;
	
	@RequestMapping(value="/getTypes.do")
	@ResponseBody
	public GeneralResponse getTypes(){
		List<MaterialTypes> list = new ArrayList<>();
		try {
			list = materialTypesService.getTypes();
		} catch (Exception e) {
			logger.error("获取素材类型错误!",e);
		}
		return GeneralResponse.generateSuccessResponse(list);
	}
	
	@RequestMapping(value="/materialVideoList.do")
	@ResponseBody
	public GeneralResponse materialVideoList(FindMaterialVideoPage findMaterialVideoPage,Integer pageNo,Integer pageSize){
		try {
			if(pageNo!=null){
				findMaterialVideoPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMaterialVideoPage.setLimit(pageSize);
			}
			if(StringUtils.isEmpty(findMaterialVideoPage.getParentId())){
				findMaterialVideoPage.setParentId("0");
			}
			findMaterialVideoPage.setMerchantNo(findMaterialVideoPage.getMerchantNo());
			Page<FindMaterialVideoPageReturn> pageDto = materialVideoService.findMaterialVideoPage(findMaterialVideoPage);
			List<FindMaterialVideoPageReturn> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			 
			//com.ape.common.persistence.Page<FindMaterialVideoPageReturn> page=new com.ape.common.persistence.Page<FindMaterialVideoPageReturn>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			//page.initialize();
			return GeneralResponse.generateSuccessResponse(pageDto);
		} catch (Exception e) {
			logger.error("获取视频库列表错误!",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	@RequestMapping(value="/materialPhotoList.do")
	@ResponseBody
	public GeneralResponse materialPhotoList(FindMaterialPhotoPage findMaterialPhotoPage,Integer pageNo,Integer pageSize){
		try {
			if(pageNo!=null){
				findMaterialPhotoPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMaterialPhotoPage.setLimit(pageSize);
			}
			if(StringUtils.isEmpty(findMaterialPhotoPage.getParentId())){
				findMaterialPhotoPage.setParentId("0");
			}
			findMaterialPhotoPage.setMerchantNo(findMaterialPhotoPage.getMerchantNo());
			Page<FindMaterialPhotoPage> pageDto = materialPhotoService.findMaterialPhotoPage(findMaterialPhotoPage);
			List<FindMaterialPhotoPage> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			 
			//com.ape.common.persistence.Page<FindMaterialPhotoPage> page=new com.ape.common.persistence.Page<FindMaterialPhotoPage>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			//page.initialize();
			return GeneralResponse.generateSuccessResponse(pageDto);
		} catch (Exception e) {
			logger.error("获取图片库列表错误!",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	@RequestMapping(value="/materialTextList.do")
	@ResponseBody
	public GeneralResponse materialTextList(FindMaterialTextPage findMaterialTextPage,Integer pageNo,Integer pageSize){
		try {
			if(pageNo!=null){
				findMaterialTextPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMaterialTextPage.setLimit(pageSize);
			}
			findMaterialTextPage.setMerchantNo(findMaterialTextPage.getMerchantNo());
			Page<FindMaterialTextPageReturn> pageDto = materialTextService.findWordsInfoPage(findMaterialTextPage);
			List<FindMaterialTextPageReturn> list = Lists.newArrayList();
			for (FindMaterialTextPageReturn findMaterialTextPageReturn : pageDto.getRows()) {
				String content = StringEscapeUtils.unescapeHtml4(findMaterialTextPageReturn.getContent());
				findMaterialTextPageReturn.setContent(content);
				list.add(findMaterialTextPageReturn);
			} 
			pageDto.setRows(list); 
			//com.ape.common.persistence.Page<FindMaterialPhotoPage> page=new com.ape.common.persistence.Page<FindMaterialPhotoPage>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			//page.initialize();
			return GeneralResponse.generateSuccessResponse(pageDto);
		} catch (Exception e) {
			logger.error("获取文本库列表错误!",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
	
	@RequestMapping(value="/materialLinkList.do")
	@ResponseBody
	public GeneralResponse materialLinkList(FindMaterialLinkPage findMaterialLinkPage,Integer pageNo,Integer pageSize){
		try {
			if(pageNo!=null){
				findMaterialLinkPage.setStart((pageNo-1)*pageSize);
			}
			if(pageSize!=null){
				findMaterialLinkPage.setLimit(pageSize);
			}
			findMaterialLinkPage.setMerchantNo(findMaterialLinkPage.getMerchantNo());
			Page<FindMaterialLinkPageReturn> pageDto = materialLinkService.findLinkInfoPage(findMaterialLinkPage);
			List<FindMaterialLinkPageReturn> list = Lists.newArrayList();
			for (FindMaterialLinkPageReturn findMaterialLinkPageReturn : pageDto.getRows()) {
				String title = StringEscapeUtils.unescapeHtml4(findMaterialLinkPageReturn.getTitle());
				findMaterialLinkPageReturn.setTitle(title);
			}
			list.addAll(pageDto.getRows());
			 
			//com.ape.common.persistence.Page<FindMaterialPhotoPage> page=new com.ape.common.persistence.Page<FindMaterialPhotoPage>(pageNo==null?1:pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			//page.initialize();
			return GeneralResponse.generateSuccessResponse(pageDto);
		} catch (Exception e) {
			logger.error("获取链接库列表错误!",e);
			return GeneralResponse.generateFailureResponse();
		}
	}
	
}
