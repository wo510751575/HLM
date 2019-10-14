/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.im;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lj.base.core.pagination.IPage;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.utils.ApiConstans;
import com.lj.business.api.utils.FileUtil;
import com.lj.business.cm.dto.wordsInfo.FindWordsAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoApp;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoAppReturn;
import com.lj.business.cm.dto.wordsInfo.FindWordsInfoPage;
import com.lj.business.cm.dto.wordsType.FindWordsTypePage;
import com.lj.business.cm.service.ICommonWordsInfoService;
import com.lj.business.cm.service.ICommonWordsTypeService;
import com.lj.business.cm.service.IWordsInfoService;
import com.lj.business.cm.service.IWordsTypeService;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.terminallogfiles.AddTerminalLogFiles;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.service.ITerminalLogFilesService;
import com.lj.business.weixin.dto.imchat.FindHistoryChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoPage;
import com.lj.business.weixin.dto.imemoji.FindImEmojiPackage;
import com.lj.business.weixin.dto.imemoji.FindNewEmojiPackageReturn;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImEmojiPackageService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributelock.IDistributeLock;
import com.lj.distributelock.RedisLock;

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
@RequestMapping(value="/im/chat/")
public class ChatAction extends Action {

	@Resource
	public IImChatInfoService imChatInfoService;
	
	@Resource
	public IImEmojiPackageService imEmojiPackageService;
	
	@Resource
	public ITerminalLogFilesService terminalLogFilesService;
	
	@Resource
	public IShopTerminalService shopTerminalService;
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource 
	private IDistributeLock redisLock;
	
	@Resource
	private IWordsTypeService wordsTypeService;						//个人话术类型服务
	@Resource
	private IWordsInfoService wordsInfoService;						//个人话术服务
	@Resource
	private ICommonWordsTypeService commonWordsTypeService;			//公司话术类型服务
	@Resource
	private ICommonWordsInfoService commonWordsInfoService;			//公司话术服务
	
	/**
	 * 
	 * 方法说明：商户下每个终端客户最新的一条聊天记录
	 * @param findHistoryChatInfo
	 * @return
	 * @throws Exception
	 * @author lhy CreateDate: 2019.05.14
	 *
	 */
	@RequestMapping(value = {"lastGmHistoryChat.do"})
	@ResponseBody
	public IPage<FindHistoryChatInfo> gmLastHistoryChat(FindImChatInfoPage findImChatInfoPage) throws Exception{
		return imChatInfoService.findLastHistoryChatInfoPage(findImChatInfoPage);
	}
	
	/**
	 * 
	 * 方法说明：导购聊天记录/历史消息（用于移动端查询历史记录）
	 * @param findHistoryChatInfo
	 * @return
	 * @throws Exception
	 * @author zlh CreateDate: 2017年12月11日
	 *
	 */
	@RequestMapping(value = {"gmHistoryChat.do"})
	@ResponseBody
	public IPage<FindHistoryChatInfo> gmHistoryChat(FindImChatInfoPage findImChatInfoPage) throws Exception{
		return imChatInfoService.findHistoryChatInfoPage(findImChatInfoPage);
	}
	/**
	 * 
	 *
	 * 方法说明：上传聊天文件，如图片、语音等（APP上传）
	 * @param merchantNo
	 * @param file
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月31日
	 * @throws IOException 
	 *
	 */
	@RequestMapping(value = "uploadFile.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse uploadFileFromApp(String merchantNo, MultipartFile file) throws IOException {
		logger.debug("uploadFileFromApp(merchantNo={}) - start", merchantNo); 
		
		AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
		AssertUtils.notNull(file, "没有上传文件");
		
		return uploadFile(merchantNo, file);
	}
	
	/**
	 * 
	 *
	 * 方法说明：上传聊天文件，如图片、语音等（APP上传），不使用开放平台框架
	 * @param merchantNo
	 * @param file
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月31日
	 * @throws IOException 
	 *
	 */
	@RequestMapping(value = "uploadFileNew.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse uploadFileFromAppNew(String merchantNo, MultipartFile file) throws IOException {
		logger.debug("uploadFileFromAppNew(merchantNo={}) - start", merchantNo); 
		
		AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
		AssertUtils.notNull(file, "没有上传文件");
		
		return uploadFile(merchantNo, file);
	}
	
	/**
	 * 
	 *
	 * 方法说明：聊天文件批量上传
	 *
	 * @param merchantNo
	 * @param files
	 * @return
	 * @throws IOException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月23日
	 *
	 */
	@RequestMapping(value="uploadFilesFromApp.do",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse uploadFilesFromApp(String merchantNo,MultipartFile[] files) throws IOException {
		AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
		AssertUtils.notNull(files, "没有上传文件");
		logger.debug("聊天文件批量上传开始： {}", files.length);
		
		List<String> urlList = new ArrayList<String>();
		for(MultipartFile file : files) {
			urlList.add(uploadFile(merchantNo, file).getReturnObject().toString());
			logger.debug("保存文件：{}", file.getOriginalFilename());
		}
		return GeneralResponse.generateSuccessResponse(urlList);
	}
	
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
		logger.debug("uploadFileFromWeb(merchantNo={}) - start", merchantNo); 
		
		AssertUtils.notNullAndEmpty(merchantNo, "商户编号不能为空");
		AssertUtils.notNull(file, "没有上传文件");
		
		return uploadFile(merchantNo, file);
	}
	
	public GeneralResponse uploadFile(String merchantNo, MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		logger.debug("im uploadFile(filename = {})", fileName);
		
		// 获取锁
		String claimLockName = RedisLock.LOCK_NAME_PREFIX+fileName;
		String claimLockValue = null;
		try {
			claimLockValue = redisLock.getLockNoWait(claimLockName,10);
		} catch(Exception e) {
			logger.error("该文件正在上传:"+fileName);
			return GeneralResponse.generateFailureResponse();
		}
				
		try {
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
		} catch(Exception e) {
			logger.error("文件上传错误 e={}"+e);
			return GeneralResponse.generateFailureResponse(e);
		} finally {
			// 释放锁
			redisLock.releaseLock(claimLockName, claimLockValue);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询表情包
	 *
	 * @param findImEmojiPackage
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	@RequestMapping(value="findEmojiPackage.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public FindNewEmojiPackageReturn findNewEmojiPackage(FindImEmojiPackage findImEmojiPackage) {
		return imEmojiPackageService.findNewEmojiPackage(findImEmojiPackage);
	}
	

	/**
	 * 
	 *
	 * 方法说明：终端日志上传
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-12-21
	 * 
	 * @param logFiles		日志文件集合
	 * @param imei			设备终端imei号
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(value="uploadLogFiles.do",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse uploadLogFiles(MultipartFile[] logs,String noWx) throws IllegalStateException, IOException, ParseException {
		AssertUtils.notEmpty(logs, "没有上传文件");
//		AssertUtils.notNullAndEmpty(imei, "IMEI号不能为空");
		AssertUtils.notNullAndEmpty(noWx, "noWx号不能为空");
		
		return uploadLogFile(noWx, logs);
	}
	
	private GeneralResponse uploadLogFile(String noWx, MultipartFile[] logs) throws IOException, ParseException {
		//获取终端终端对象
		FindShopTerminalReturn st = shopTerminalService.findShopTerminalByWx(noWx);
		Map<String, String> map = localCacheSystemParams.getSystemParamGroup(SystemAliasName.weixin.name(), "imfile");
		String uploadPath = map.get("uploadPath"); 
		String filePath = ApiConstans.LOG_PRE + noWx + "/";	 // 日志文件保存在im/tidlogs/下
		String imageFolder = uploadPath+filePath;
		for (MultipartFile logFiles : logs) {
			String fileName = logFiles.getOriginalFilename();
			logger.debug("im uploadLogFile(filename = {})", fileName);
			// 保存文件
			String fileInputName= FileUtil.saveLogFile(imageFolder, logFiles,noWx);
			// 录入终端日志文件表
			saveTerminalLogFiles(noWx, st, filePath, fileInputName);
		}
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 录入终端日志文件
	 * @param imei
	 * @param createTime
	 * @param st
	 * @param filePath
	 * @param fileInputName
	 * @throws ParseException
	 */
	public void saveTerminalLogFiles(String noWx,FindShopTerminalReturn st, String filePath, String fileInputName)
			throws ParseException {
		
			// 录入终端日志文件表
			AddTerminalLogFiles addTerminalLogFiles=new AddTerminalLogFiles();
			addTerminalLogFiles.setImei(noWx);
			addTerminalLogFiles.setTerminalCode(st.getTerminalCode());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String dstr=fileInputName.substring(0, 14);  
			Date date=sdf.parse(dstr); 
			addTerminalLogFiles.setLogBeginTime(date);
			addTerminalLogFiles.setLogFileName(fileInputName);
			addTerminalLogFiles.setLogAddr(filePath+fileInputName);
			addTerminalLogFiles.setMerchantNo(st.getMerchantNo());
			addTerminalLogFiles.setMerchantName(st.getMerchantName());
//			addTerminalLogFiles.setShopNo(st.getShopNo());
//			addTerminalLogFiles.setShopName(st.getShopName());
			terminalLogFilesService.addTerminalLogFiles(addTerminalLogFiles);
			logger.info("已添加终端日志文件信息：" + addTerminalLogFiles);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取上次当前终端日志的最后一个日志文件的时间
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-12-28
	 * 
	 * @param imei			设备终端imei号
	 * @return
	 */
	@RequestMapping(value="getLastFileDate.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getLastFileDate(String imei)  {
		return terminalLogFilesService.getLastFileDate(imei);
	}
	
	
	
	
	
	/**
	 * 
	 *
	 * 方法说明：检验话术是否存在
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-17
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value="checkWords.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse checkWords(String code)  {
		String isExist=wordsInfoService.checkWords(code)>0?Boolean.TRUE.toString():Boolean.FALSE.toString();
		return GeneralResponse.generateSuccessResponse(isExist);
	}
	
	/**
	 * 
	 *
	 * 方法说明：话术列表
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	@RequestMapping(value="findWords.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<FindWordsAppReturn> findWords(FindWordsTypePage findWordsTypePage)  {
		List<FindWordsAppReturn> list = new ArrayList<>();
		String code = findWordsTypePage.getCode();
		//code equals 1 查公共 ,code equals 2查个人
		if("1".equals(code)){
			FindWordsInfoApp findWordsInfoApp = new FindWordsInfoApp();
			findWordsInfoApp.setMerchantNo(findWordsTypePage.getMerchantNo());
			list = commonWordsTypeService.findWords(findWordsInfoApp);
		}else{
			list = wordsTypeService.findPersonWords(findWordsTypePage);
		}
		return list;
	}
	
	
	
	/**
	 * 
	 *
	 * 方法说明：话术搜索
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	@RequestMapping(value="wordsSearch.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<FindWordsInfoAppReturn> wordsSearch(FindWordsInfoPage findWordsInfoPage)  {
		List<FindWordsInfoAppReturn> personWordsList = wordsInfoService.wordsPersonSearch(findWordsInfoPage);
		FindWordsInfoApp findWordsInfoApp = new FindWordsInfoApp();
		findWordsInfoApp.setContent(findWordsInfoPage.getContent());
		findWordsInfoApp.setMerchantNo(findWordsInfoPage.getMerchantNo());
		List<FindWordsInfoAppReturn> commonWordsList = commonWordsInfoService.wordsSearch(findWordsInfoApp);
		commonWordsList.addAll(personWordsList);
		return commonWordsList;
	}
	
	/**
	 * 
	 *
	 * 方法说明：默认话术
	 *
	 * @author 彭俊霖
	 *   
	 * CreateDate: 2017-01-09
	 * 
	 * @param findWordsInfoApp
	 * @return
	 */
	@RequestMapping(value="wordsSelect.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<FindWordsInfoAppReturn> wordsSelect(FindWordsInfoPage findWordsInfoPage)  {
		List<FindWordsInfoAppReturn> list = wordsInfoService.findDefaultWords(findWordsInfoPage);
		return list;
	}
	
	
}
