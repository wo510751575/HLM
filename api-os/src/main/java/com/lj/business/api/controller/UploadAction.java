package com.lj.business.api.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.utils.ApiConstans;
import com.lj.business.api.utils.FileUtil;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;


@Controller
@RequestMapping(value = "/upload/")
public class UploadAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(UploadAction.class);
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	
	/**
	 * 上传图片
	 * @param uploadFile
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="uploadImage.do",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String uploadHeadImage(@RequestParam(required=false) MultipartFile uploadFile) throws IllegalStateException, IOException {
		logger.debug("uploadHeadImage(MultipartFile uploadFile={}) - start", uploadFile); 

		if (uploadFile == null || uploadFile.isEmpty()) {
			throw new TsfaServiceException(ErrorCode.HEAD_IMAGE_IS_EMPTY, "上传图片为空");
		}
		String UPLOAD_PATH =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_PATH);

		String pth = FileUtil.saveFile(UPLOAD_PATH + ApiConstans.IMG_PRE , uploadFile);
		String returnString = ApiConstans.IMG_PRE + pth;
		logger.debug("uploadHeadImage() - end - return value={}", returnString); 
		return returnString;
	}
}
