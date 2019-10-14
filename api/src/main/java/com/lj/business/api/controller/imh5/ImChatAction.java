/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.imh5;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.utils.FileUtil;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;

/**
 * 
 * 类说明：IM-聊天相关服务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月28日
 */
@Controller
@RequestMapping(value="/imh5/chat/")
public class ImChatAction extends Action {
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	/**
	 * 
	 *
	 * 方法说明：上传聊天文件，如图片、语音等(导购助手上传)
	 *
	 * @param merchantNo
	 * @param file
	 * @return
	 * @throws IOException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	@RequestMapping(value = "uploadFileFromWeb.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse uploadFileFromWeb(String merchantNo, MultipartFile file) throws IOException {
		logger.debug("uploadFileFromWeb(merchantNo={}) - start", merchantNo); //$NON-NLS-1$
		
		AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
		AssertUtils.notNull(file, "没有上传文件");
		
		return uploadFile(merchantNo, file);
	}
	
	private GeneralResponse uploadFile(String merchantNo, MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		logger.debug("im uploadFile(filename = {})", fileName);
		
		// 判断文件格式
		String fileType = FileUtil.getFileType(fileName);
		if(StringUtils.isEmpty(fileType)) {
			logger.error("不支持的文件格式: {}", fileName);
			fileType = "file";	// 默认为file格式
			/*ResponseCode code = GeneralResponse.getErrorResponseByBusinessCode(ErrorCode.UNSUPPORTED_FILE_FORMAT);
			return GeneralResponse.generateResponse(Boolean.FALSE, code.getCode(), code.getMessage(), null);*/
		}

		Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "imfile");
		String uploadPath = map.get("uploadPath"); 
		String today = DateUtils.formatDate(new Date(), DateUtils.PATTERN_yyyyMMdd);
		String filePath = merchantNo + "/" + today + "/"  + fileType.toLowerCase() + "/";
		String imageFolder = uploadPath+filePath;
		// 保存文件
		String fileInputName= FileUtil.saveFile(imageFolder, file);
		String url = map.get("uploadUrl") + filePath + fileInputName;
		return GeneralResponse.generateSuccessResponse(url);
	}
	
}
