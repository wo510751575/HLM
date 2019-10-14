package com.lj.business.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.utils.ApiConstans;
import com.lj.business.api.utils.FileUtil;
import com.lj.business.api.utils.ZipUtil;
import com.lj.business.common.SystemParamConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.UpdateShopTerminal;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributelock.IDistributeLock;
import com.lj.distributelock.RedisLock;


@Controller
@RequestMapping(value = "/upload/")
public class UploadAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(UploadAction.class);
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource 
	private IDistributeLock redisLock;
	@Resource
	private IShopTerminalService shopTerminalService;
	
	/**
	 * 
	 *
	 * 方法说明：上传头像
	 *
	 * @author 
	 *   
	 * CreateDate: 2017-08-03
	 * 
	 * @param uploadFile
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="uploadHeadImage.do",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String uploadHeadImage(@RequestParam(required=false) MultipartFile uploadFile) throws IllegalStateException, IOException {
		logger.debug("uploadHeadImage(MultipartFile uploadFile={}) - start", uploadFile); 

		if (uploadFile == null || uploadFile.isEmpty()) {
			throw new TsfaServiceException(ErrorCode.HEAD_IMAGE_IS_EMPTY, "上传会员头像文件为空");
		}
		String UPLOAD_PATH =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_PATH);

		String pth = FileUtil.saveFile(UPLOAD_PATH + ApiConstans.IMG_PRE , uploadFile);
		String returnString = ApiConstans.IMG_PRE + pth;
		logger.debug("uploadHeadImage() - end - return value={}", returnString); 
		return returnString;
	}
	
	/**
	 * 
	 *
	 * 方法说明：上传二维码
	 *
	 * @author 
	 *   
	 * CreateDate: 2017-08-16
	 * 
	 * @param uploadFile
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="uploadQcodeImage.do",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse uploadQcordImage(MultipartFile uploadFile,String noWxGm) throws IllegalStateException, IOException {
		logger.debug("uploadQcordImage(MultipartFile uploadFile={}) - start", uploadFile); 
		AssertUtils.notAllNullAndEmpty(noWxGm, "终端微信不能为空");
		
		if (uploadFile == null || uploadFile.isEmpty()) {
			throw new TsfaServiceException(ErrorCode.FILE_IS_EMPTY, "上传二维码为空");
		}
		if (!FileUtil.fileSizeVaild(uploadFile)) {
			throw new TsfaServiceException(ErrorCode.FILE_IS_EMPTY, "二维码大小不能超过10M");
		}
		String fileName = uploadFile.getOriginalFilename();
		// 判断文件格式
		if (null == FileUtil.getFileType(fileName)) {
			GeneralResponse.generateFailureResponse(ErrorCode.FILE_IS_EMPTY, "二维码格式不正确！");
		}
					
		// 获取锁
		String claimLockName = RedisLock.LOCK_NAME_PREFIX+fileName;
		String claimLockValue = null;
		try {
			claimLockValue = redisLock.getLockNoWait(claimLockName);
		} catch(Exception e) {
			logger.error("该二维码正在上传:"+fileName);
			return GeneralResponse.generateFailureResponse();
		}
		
		
		try {
			
			String UPLOAD_PATH =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_PATH);
			String pth = FileUtil.saveFile(UPLOAD_PATH + ApiConstans.ZK_QCORD , uploadFile);
			String returnString = ApiConstans.ZK_QCORD + pth;
			logger.debug("uploadQcordImage() - end - return value={}", returnString); 
			FindShopTerminalReturn shopTerminal = shopTerminalService.findShopTerminalByWx(noWxGm);
			AssertUtils.notNull(shopTerminal,"终端不存在");
			UpdateShopTerminal updateShopTerminal = new UpdateShopTerminal();
			updateShopTerminal.setCode(shopTerminal.getCode());
			boolean upFlag=false;
			if(StringUtils.isEmpty(shopTerminal.getQcord()) || !shopTerminal.getQcord().equals(returnString)) {
				updateShopTerminal.setQcord(returnString);
				upFlag=true;
			}
			//更新二维码
			if(upFlag) {
				shopTerminalService.updateShopTerminal(updateShopTerminal);
				logger.info("已更新终端二维码");
			}
			return GeneralResponse.generateSuccessResponse();
		} catch(Exception e) {
			logger.error("文件二维码错误 e={}"+e);
			return GeneralResponse.generateFailureResponse(e);
		} finally {
			// 释放锁
			redisLock.releaseLock(claimLockName, claimLockValue);
		}
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：上传简历
	 *
	 * @param uploadFile
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 *
	 * @author 彭阳 CreateDate: 2018年6月5日
	 *
	 */
	@RequestMapping(value="uploadResume.do",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String uploadResume(@RequestParam(required=false) MultipartFile uploadFile) throws IllegalStateException, IOException {
		logger.debug("uploadResume(MultipartFile uploadFile={}) - start", uploadFile); 

		if (uploadFile == null || uploadFile.isEmpty()) {
			throw new TsfaServiceException(ErrorCode.FILE_IS_EMPTY, "上传文件为空");
		}
		String SHOWINFO_PATH =  localCacheSystemParams.getSystemParam(SystemAliasName.recruit.toString(),SystemParamConstant.RECRUIT_UPLOAD, SystemParamConstant.SHOWINFO_PATH);

		String pth = FileUtil.saveFile(SHOWINFO_PATH , uploadFile);
		String returnString = ApiConstans.IMG_PRE + pth;

		logger.debug("uploadResume(MultipartFile) - end - return value={}", returnString); 
		return returnString;
	}
	
	/**
	 * 方法说明：图片压缩文件上传
	 * @author 彭俊霖
	 * @CreateDate: 2018-01-31
	 * @param zipfile		压缩文件
	 * @param merchantNo	商户编号
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="uploadZipFiles.do",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse uploadZipFiles(MultipartFile zipfile,String merchantNo) throws Exception {
		AssertUtils.notNull(zipfile, "没有上传文件");
		AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
		try{
			String fileName = zipfile.getOriginalFilename();
			logger.debug("wxFriend uploadZipFiles(filename = {})", fileName);
			
			// 判断文件格式(只支持zip格式)
			String fileType = FileUtil.getFileType(fileName);
			if(StringUtils.isEmpty(fileType)||!"zip".equals(FilenameUtils.getExtension(fileName.toLowerCase()))) {
				logger.error("不支持的文件格式: {}", fileName);
				return GeneralResponse.generateFailureResponse(com.lj.business.api.common.ErrorCode.UNSUPPORTED_FILE_FORMAT,"不支持的文件格式");
			}
			String uploadPath =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_PATH);
//			Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "imfile");
//			String uploadPath = map.get("uploadPath"); 
//			String uploadUrl = map.get("uploadUrl"); 
			String today = DateUtils.formatDate(new Date(), DateUtils.PATTERN_yyyyMMdd);
			String filePath = "imgs/"+merchantNo + "/" + today + "/"  + fileType.toLowerCase() + "/";
			String imageFolder = uploadPath+filePath;//文件夹路径
//			String imageFolder = "D:/"+filePath;
			// 保存文件
			String fileInputName = FileUtil.saveFile(imageFolder, zipfile);
			File file=new File(imageFolder+fileInputName);
			String descDir =uploadPath+filePath;//保存路径
			String visDir = "/"+filePath;// 访问相对路径
			Map<String, Object> resultMap = ZipUtil.unZipFiles(file,descDir,visDir);
//			Map<String, Object> resultMap = ZipUtil.unZipFiles(file,imageFolder,imageFolder);
			return GeneralResponse.generateSuccessResponse(resultMap);
		} catch (Exception e) {
	        logger.error("图片压缩文件上传失败",e);
	        return GeneralResponse.generateFailureResponse(e);
	    }
	}
	
}
