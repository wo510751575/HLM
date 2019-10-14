package com.lj.business.weixin.service.impl;

import java.util.Date;
/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.CommonConstant;
import com.lj.business.weixin.constant.ErrorCode;
import com.lj.business.weixin.dao.IMerchantSendFriendsJobDao;
import com.lj.business.weixin.domain.MerchantSendFriendsJob;
import com.lj.business.weixin.dto.FindMerchantSendFriendsJobPage;
import com.lj.business.weixin.dto.MerchantSendFriendsJobDto;
import com.lj.business.weixin.dto.friendsjob.AddSendFriendsJob;
import com.lj.business.weixin.service.IMerchantSendFriendsJobService;
import com.lj.business.weixin.service.ISendFriendsJobService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.GroupName;
import com.lj.cc.enums.SysParamName;
import com.lj.cc.enums.SystemAliasName;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
@Service
public class MerchantSendFriendsJobServiceImpl implements IMerchantSendFriendsJobService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MerchantSendFriendsJobServiceImpl.class);
	

	@Resource
	private IMerchantSendFriendsJobDao merchantSendFriendsJobDao;
	@Autowired
	private ISendFriendsJobService sendFriendsJobService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	
	@Override
	public void addMerchantSendFriendsJob(
			MerchantSendFriendsJobDto merchantSendFriendsJobDto) throws TsfaServiceException {
		logger.debug("addMerchantSendFriendsJob(AddMerchantSendFriendsJob addMerchantSendFriendsJob={}) - start", merchantSendFriendsJobDto); 

		AssertUtils.notNull(merchantSendFriendsJobDto);
		try {
			MerchantSendFriendsJob merchantSendFriendsJob = new MerchantSendFriendsJob();
			//add数据录入
			merchantSendFriendsJob.setCode(GUID.generateCode());
			merchantSendFriendsJob.setMerchantNo(merchantSendFriendsJobDto.getMerchantNo());
			merchantSendFriendsJob.setMerchantName(merchantSendFriendsJobDto.getMerchantName());
			merchantSendFriendsJob.setContent(merchantSendFriendsJobDto.getContent());
			merchantSendFriendsJob.setImgAddr(merchantSendFriendsJobDto.getImgAddr());
			merchantSendFriendsJob.setLinkUrl(merchantSendFriendsJobDto.getLinkUrl());
			merchantSendFriendsJob.setAutoComment(StringUtils.isEmpty(merchantSendFriendsJobDto.getAutoContent())?CommonConstant.I_NO:CommonConstant.I_YES);
			merchantSendFriendsJob.setAutoContent(merchantSendFriendsJobDto.getAutoContent());
			merchantSendFriendsJob.setNoWxs(merchantSendFriendsJobDto.getNoWxs());
			merchantSendFriendsJob.setExecuteTime(merchantSendFriendsJobDto.getExecuteTime());
			merchantSendFriendsJob.setRealExecuteTime(merchantSendFriendsJobDto.getRealExecuteTime());
			merchantSendFriendsJob.setJobState(merchantSendFriendsJobDto.getJobState());
			merchantSendFriendsJob.setSentNoWxs(merchantSendFriendsJobDto.getSentNoWxs());
			merchantSendFriendsJob.setCreateId(merchantSendFriendsJobDto.getCreateId());
			merchantSendFriendsJob.setCreateUserLevel(merchantSendFriendsJobDto.getCreateUserLevel());
			merchantSendFriendsJob.setCreateDate(new Date());
			merchantSendFriendsJob.setType(merchantSendFriendsJobDto.getType());
			merchantSendFriendsJob.setRemark(merchantSendFriendsJobDto.getRemark());
			merchantSendFriendsJob.setRemark2(merchantSendFriendsJobDto.getRemark2());
			merchantSendFriendsJob.setRemark3(merchantSendFriendsJobDto.getRemark3());
			merchantSendFriendsJob.setRemark4(merchantSendFriendsJobDto.getRemark4());
			merchantSendFriendsJob.setCreateName(merchantSendFriendsJobDto.getCreateName());
			merchantSendFriendsJobDao.insertSelective(merchantSendFriendsJob);
			
			merchantSendFriendsJobDto.setCode(merchantSendFriendsJob.getCode());
			addSendFriendsJob(merchantSendFriendsJobDto);
			logger.debug("addMerchantSendFriendsJob(MerchantSendFriendsJobDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增商户发朋友圈任务信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_SEND_FRIENDS_JOB_ADD_ERROR,"新增商户发朋友圈任务信息错误！",e);
		}
	}
	
	
	private void addSendFriendsJob(MerchantSendFriendsJobDto merchantSendFriendsJobDto) {
		AssertUtils.notNullAndEmpty(merchantSendFriendsJobDto.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(merchantSendFriendsJobDto.getExecuteTime(), "执行不能为空");
		AssertUtils.notNullAndEmpty(merchantSendFriendsJobDto.getNoWxs(), "微信不能为空");
		AssertUtils.notNullAndEmpty(merchantSendFriendsJobDto.getType(), "类型不能为空");
		Date now = new Date();
		if(now.compareTo(merchantSendFriendsJobDto.getExecuteTime()) > 0) {
            throw new TsfaServiceException(ErrorCode.MERCHANT_SEND_FRIENDS_JOB_ADD_ERROR,"时间必须大于当前时间！");
        }
		
		String uploadUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(),GroupName.upload.toString(),SysParamName.UPLOADURL.getText());
		String imgAddrs = merchantSendFriendsJobDto.getImgAddr();
		StringBuilder sb = new StringBuilder("");
		if(StringUtils.isNotEmpty(imgAddrs)) {
        	//图片地址转换为全路径url
            String[] imgUriArr = imgAddrs.split(",");
            for (String uri : imgUriArr) {
            	if(!uri.startsWith("http")) {
            		uri = uploadUrl + uri;
            	}
                sb.append(uri + ",");
            }
            if (sb.length() > 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            imgAddrs = sb.toString();
    	}
		
		String resourcePath=merchantSendFriendsJobDto.getLinkUrl();
		if(StringUtils.isNotEmpty(resourcePath)) {
			if(!resourcePath.startsWith("http")) {
				resourcePath = uploadUrl + resourcePath;
        	}
		}
		
		String []arrayNoWxs = merchantSendFriendsJobDto.getNoWxs().split(",");
		
		AddSendFriendsJob addSendFriendsJob = new AddSendFriendsJob();
		addSendFriendsJob.setMerchantNo(merchantSendFriendsJobDto.getMerchantNo());
		addSendFriendsJob.setMerchantName(merchantSendFriendsJobDto.getMerchantName());
		addSendFriendsJob.setMerchantJobCode(merchantSendFriendsJobDto.getCode());
		addSendFriendsJob.setContent(merchantSendFriendsJobDto.getContent());
		addSendFriendsJob.setType(merchantSendFriendsJobDto.getType());
		String shareTitle =merchantSendFriendsJobDto.getContent();
		if(shareTitle.length()>100) {
			shareTitle =shareTitle.substring(0,100);
		}
		addSendFriendsJob.setShareTitle(shareTitle);
		addSendFriendsJob.setCreateUserLevel(String.valueOf(CommonConstant.I_YES));
		addSendFriendsJob.setCreateId(merchantSendFriendsJobDto.getCreateId());
		addSendFriendsJob.setCreateName(merchantSendFriendsJobDto.getCreateName());
		addSendFriendsJob.setImgAddr(imgAddrs);
		addSendFriendsJob.setResourcePath(resourcePath);
		addSendFriendsJob.setAutoContent(merchantSendFriendsJobDto.getAutoContent());
		addSendFriendsJob.setAutoComment(merchantSendFriendsJobDto.getAutoComment());
		for(String noWx :arrayNoWxs) {
			addSendFriendsJob.setNoWxs(noWx);
			if(arrayNoWxs.length == 1) {
				logger.info("定时任务时间："+merchantSendFriendsJobDto.getExecuteTime());
				addSendFriendsJob.setExecuteTime(merchantSendFriendsJobDto.getExecuteTime());
			}
			
			//为了防止同时发同一微信内容被封号，需要根据微信数量定义发送时间
			//随机延迟1-30分钟
			if(arrayNoWxs.length > 1 && (arrayNoWxs.length < 100 || arrayNoWxs.length == 100)) {
				int x=1+(int)(Math.random()*30);
           	    addSendFriendsJob.setExecuteTime(DateUtils.addMinutes(merchantSendFriendsJobDto.getExecuteTime(), x));
			}
			//60分钟
            if(arrayNoWxs.length > 100  && (arrayNoWxs.length < 300 || arrayNoWxs.length == 300)) {
            	int x=1+(int)(Math.random()*60);
           	    addSendFriendsJob.setExecuteTime(DateUtils.addMinutes(merchantSendFriendsJobDto.getExecuteTime(), x));
			}
            //120分钟
            if(arrayNoWxs.length > 300) {
            	 int x=1+(int)(Math.random()*120);
            	 addSendFriendsJob.setExecuteTime(DateUtils.addMinutes(merchantSendFriendsJobDto.getExecuteTime(), x));
			}
			sendFriendsJobService.addSendFriendsJob(addSendFriendsJob);
		}
	}
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询商户发朋友圈任务信息
	 *
	 * @param findMerchantSendFriendsJobPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<MerchantSendFriendsJobDto>  findMerchantSendFriendsJobs(FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage)throws TsfaServiceException{
		AssertUtils.notNull(findMerchantSendFriendsJobPage);
		List<MerchantSendFriendsJobDto> returnList=null;
		try {
			returnList = merchantSendFriendsJobDao.findMerchantSendFriendsJobs(findMerchantSendFriendsJobPage);
		} catch (Exception e) {
			logger.error("查找商户发朋友圈任务信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_SEND_FRIENDS_JOB_NOT_EXIST_ERROR,"商户发朋友圈任务信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateMerchantSendFriendsJob(
			MerchantSendFriendsJobDto merchantSendFriendsJobDto)
			throws TsfaServiceException {
		logger.debug("updateMerchantSendFriendsJob(MerchantSendFriendsJobDto merchantSendFriendsJobDto={}) - start", merchantSendFriendsJobDto); 

		AssertUtils.notNull(merchantSendFriendsJobDto);
		AssertUtils.notNullAndEmpty(merchantSendFriendsJobDto.getCode(),"Code不能为空");
		try {
			MerchantSendFriendsJob merchantSendFriendsJob = new MerchantSendFriendsJob();
			//update数据录入
			merchantSendFriendsJob.setCode(merchantSendFriendsJobDto.getCode());
			merchantSendFriendsJob.setMerchantNo(merchantSendFriendsJobDto.getMerchantNo());
			merchantSendFriendsJob.setMerchantName(merchantSendFriendsJobDto.getMerchantName());
			merchantSendFriendsJob.setContent(merchantSendFriendsJobDto.getContent());
			merchantSendFriendsJob.setImgAddr(merchantSendFriendsJobDto.getImgAddr());
			merchantSendFriendsJob.setLinkUrl(merchantSendFriendsJobDto.getLinkUrl());
			merchantSendFriendsJob.setAutoComment(merchantSendFriendsJobDto.getAutoComment());
			merchantSendFriendsJob.setAutoContent(merchantSendFriendsJobDto.getAutoContent());
			merchantSendFriendsJob.setNoWxs(merchantSendFriendsJobDto.getNoWxs());
			merchantSendFriendsJob.setExecuteTime(merchantSendFriendsJobDto.getExecuteTime());
			merchantSendFriendsJob.setRealExecuteTime(merchantSendFriendsJobDto.getRealExecuteTime());
			merchantSendFriendsJob.setJobState(merchantSendFriendsJobDto.getJobState());
			merchantSendFriendsJob.setSentNoWxs(merchantSendFriendsJobDto.getSentNoWxs());
			merchantSendFriendsJob.setCreateId(merchantSendFriendsJobDto.getCreateId());
			merchantSendFriendsJob.setCreateUserLevel(merchantSendFriendsJobDto.getCreateUserLevel());
			merchantSendFriendsJob.setCreateDate(merchantSendFriendsJobDto.getCreateDate());
			merchantSendFriendsJob.setType(merchantSendFriendsJobDto.getType());
			merchantSendFriendsJob.setRemark(merchantSendFriendsJobDto.getRemark());
			merchantSendFriendsJob.setRemark2(merchantSendFriendsJobDto.getRemark2());
			merchantSendFriendsJob.setRemark3(merchantSendFriendsJobDto.getRemark3());
			merchantSendFriendsJob.setRemark4(merchantSendFriendsJobDto.getRemark4());
			merchantSendFriendsJob.setCreateName(merchantSendFriendsJobDto.getCreateName());
			AssertUtils.notUpdateMoreThanOne(merchantSendFriendsJobDao.updateByPrimaryKeySelective(merchantSendFriendsJob));
			logger.debug("updateMerchantSendFriendsJob(MerchantSendFriendsJobDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("商户发朋友圈任务信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_SEND_FRIENDS_JOB_UPDATE_ERROR,"商户发朋友圈任务信息更新信息错误！",e);
		}
	}

	

	@Override
	public MerchantSendFriendsJobDto findMerchantSendFriendsJob(
			MerchantSendFriendsJobDto merchantSendFriendsJobDto) throws TsfaServiceException {
		logger.debug("findMerchantSendFriendsJob(FindMerchantSendFriendsJob findMerchantSendFriendsJob={}) - start", merchantSendFriendsJobDto); 

		AssertUtils.notNull(merchantSendFriendsJobDto);
		AssertUtils.notAllNull(merchantSendFriendsJobDto.getCode(),"Code不能为空");
		try {
			MerchantSendFriendsJob merchantSendFriendsJob = merchantSendFriendsJobDao.selectByPrimaryKey(merchantSendFriendsJobDto.getCode());
			if(merchantSendFriendsJob == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.MERCHANT_SEND_FRIENDS_JOB_NOT_EXIST_ERROR,"商户发朋友圈任务信息不存在");
			}
			MerchantSendFriendsJobDto findMerchantSendFriendsJobReturn = new MerchantSendFriendsJobDto();
			//find数据录入
			findMerchantSendFriendsJobReturn.setCode(merchantSendFriendsJob.getCode());
			findMerchantSendFriendsJobReturn.setMerchantNo(merchantSendFriendsJob.getMerchantNo());
			findMerchantSendFriendsJobReturn.setMerchantName(merchantSendFriendsJob.getMerchantName());
			findMerchantSendFriendsJobReturn.setContent(merchantSendFriendsJob.getContent());
			findMerchantSendFriendsJobReturn.setImgAddr(merchantSendFriendsJob.getImgAddr());
			findMerchantSendFriendsJobReturn.setLinkUrl(merchantSendFriendsJob.getLinkUrl());
			findMerchantSendFriendsJobReturn.setAutoComment(merchantSendFriendsJob.getAutoComment());
			findMerchantSendFriendsJobReturn.setAutoContent(merchantSendFriendsJob.getAutoContent());
			findMerchantSendFriendsJobReturn.setNoWxs(merchantSendFriendsJob.getNoWxs());
			findMerchantSendFriendsJobReturn.setExecuteTime(merchantSendFriendsJob.getExecuteTime());
			findMerchantSendFriendsJobReturn.setRealExecuteTime(merchantSendFriendsJob.getRealExecuteTime());
			findMerchantSendFriendsJobReturn.setJobState(merchantSendFriendsJob.getJobState());
			findMerchantSendFriendsJobReturn.setSentNoWxs(merchantSendFriendsJob.getSentNoWxs());
			findMerchantSendFriendsJobReturn.setCreateId(merchantSendFriendsJob.getCreateId());
			findMerchantSendFriendsJobReturn.setCreateUserLevel(merchantSendFriendsJob.getCreateUserLevel());
			findMerchantSendFriendsJobReturn.setCreateDate(merchantSendFriendsJob.getCreateDate());
			findMerchantSendFriendsJobReturn.setType(merchantSendFriendsJob.getType());
			findMerchantSendFriendsJobReturn.setRemark(merchantSendFriendsJob.getRemark());
			findMerchantSendFriendsJobReturn.setRemark2(merchantSendFriendsJob.getRemark2());
			findMerchantSendFriendsJobReturn.setRemark3(merchantSendFriendsJob.getRemark3());
			findMerchantSendFriendsJobReturn.setRemark4(merchantSendFriendsJob.getRemark4());
			findMerchantSendFriendsJobReturn.setCreateName(merchantSendFriendsJob.getCreateName());
			
			logger.debug("findMerchantSendFriendsJob(MerchantSendFriendsJobDto) - end - return value={}", findMerchantSendFriendsJobReturn); 
			return findMerchantSendFriendsJobReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找商户发朋友圈任务信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_SEND_FRIENDS_JOB_FIND_ERROR,"查找商户发朋友圈任务信息信息错误！",e);
		}


	}

	
	@Override
	public Page<MerchantSendFriendsJobDto> findMerchantSendFriendsJobPage(
			FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage)
			throws TsfaServiceException {
		logger.debug("findMerchantSendFriendsJobPage(FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage={}) - start", findMerchantSendFriendsJobPage); 

		AssertUtils.notNull(findMerchantSendFriendsJobPage);
		List<MerchantSendFriendsJobDto> returnList=null;
		int count = 0;
		try {
			returnList = merchantSendFriendsJobDao.findMerchantSendFriendsJobPage(findMerchantSendFriendsJobPage);
			count = merchantSendFriendsJobDao.findMerchantSendFriendsJobPageCount(findMerchantSendFriendsJobPage);
		}  catch (Exception e) {
			logger.error("商户发朋友圈任务信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.MERCHANT_SEND_FRIENDS_JOB_FIND_PAGE_ERROR,"商户发朋友圈任务信息不存在错误.！",e);
		}
		Page<MerchantSendFriendsJobDto> returnPage = new Page<MerchantSendFriendsJobDto>(returnList, count, findMerchantSendFriendsJobPage);

		logger.debug("findMerchantSendFriendsJobPage(FindMerchantSendFriendsJobPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
