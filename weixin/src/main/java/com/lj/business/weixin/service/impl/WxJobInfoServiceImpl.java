package com.lj.business.weixin.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.constant.ErrorCodeWxJobInfo;
import com.lj.business.weixin.dao.IWxJobInfoDao;
import com.lj.business.weixin.domain.WxJobInfo;
import com.lj.business.weixin.dto.FindWxJobInfoPage;
import com.lj.business.weixin.dto.WxJobInfoDto;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;
import com.lj.business.weixin.emus.RedPackTypeEnum;
import com.lj.business.weixin.service.IWxJobInfoService;
import com.lj.business.weixin.service.IWxJobRedPackInfoService;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.domain.JobCenter;
import com.lj.cc.service.IJobMgrService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 罗丹青
 * 
 * 
 * CreateDate: 2017-08-22
 */
@Service
public class WxJobInfoServiceImpl implements IWxJobInfoService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WxJobInfoServiceImpl.class);
	

	@Resource
	private IWxJobInfoDao wxJobInfoDao;
	
	
	@Resource
	private IJobMgrService jobMgrService;
	
	@Resource
	private IWxJobRedPackInfoService wxJobRedPackInfoService;
	
	
	@Resource
	private IWxRedpackDetailInfoService wxRedPackDetailinfoService;
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	
	@Resource
	private IShopTerminalService shopterminalService;
	
	
 
	
	
	@Override
	public WxJobInfoDto addWxJobInfo(WxJobInfoDto wxJobInfoDto) throws TsfaServiceException {
		logger.debug("addWxJobInfo(AddWxJobInfo addWxJobInfo={}) - start", wxJobInfoDto); 

		AssertUtils.notNull(wxJobInfoDto);
		try {
			WxJobInfo wxJobInfo = new WxJobInfo();
			//add数据录入
			wxJobInfoDto.setCode(GUID.generateCode());
			wxJobInfo.setCode(wxJobInfoDto.getCode());
			wxJobInfo.setJobName(wxJobInfoDto.getJobName());
			wxJobInfo.setJobLevel(wxJobInfoDto.getJobLevel());
			wxJobInfo.setJobStatus("1");
			wxJobInfo.setJobDelayTime(wxJobInfoDto.getJobDelayTime());
			wxJobInfo.setJobType(wxJobInfoDto.getJobType());
			wxJobInfo.setJobService(wxJobInfoDto.getJobService());
			wxJobInfo.setCreateDate(wxJobInfoDto.getCreateDate());
			wxJobInfo.setUpdateDate(wxJobInfoDto.getUpdateDate());
			wxJobInfo.setCreateUser(wxJobInfoDto.getCreateUser());
			wxJobInfo.setExecuteTime(wxJobInfoDto.getExecuteTime());
			wxJobInfo.setExecuteType(wxJobInfoDto.getExecuteType());
			wxJobInfo.setJobDelayTime(wxJobInfoDto.getDelayTimes());
			wxJobInfoDao.insert(wxJobInfo);
            String callbackUrl = localCacheSystemParams.getSystemParam("weixin", "redPackJob", "sendRedPackJobCallbackUrl");
            Date nextSendTime;
            JobCenter jc =null;
            Set<Integer> randomDelaySet = new HashSet<>();//随机延迟时间的集合，不能重复
            List<WxJobRedPackInfoDto> noWxPackInfos = wxJobInfoDto.getWxJobRedPackInfoDtoList();
            	for(WxJobRedPackInfoDto noWxpackInfo:noWxPackInfos){
            		logger.info("randomDelaySet  ---------- > {}",randomDelaySet);
            		noWxpackInfo.setCode(GUID.generateCode());
            		noWxpackInfo.setJobCode(wxJobInfo.getCode());
            		noWxpackInfo.setRedpackType(RedPackTypeEnum.NORMAL.getType());
//            		FindShopTerminalReturn shopTerminal = shopterminalService.findShopTerminalNormal(noWxpackInfo.getWxNoShop());
//            		if(shopTerminal!=null){
//            			noWxpackInfo.setShopNo(shopTerminal.getShopNo());
//            		}
	                nextSendTime = wxJobInfo.getExecuteTime();
	                if ("2".equals(wxJobInfoDto.getExecuteType())) {   //立即执行
	                		int delay = new Random().nextInt(wxJobInfoDto.getDelayTimes())+1;
	                        //生成随机延迟，如果重复再次生成
	                        while (randomDelaySet.contains(delay)) {
	                            delay = new Random().nextInt(wxJobInfoDto.getDelayTimes()) + 1;
	                          //  logger.debug("while  randomDelaySet ---------- > {}",delay);
	                        }
	                        randomDelaySet.add(delay);
	                        Date now = new Date();
	                        nextSendTime = DateUtils.addMinutes(now, delay);
	                        jc= getJobCenterWithDate(nextSendTime);
	                }else {
	                	 int delay = new Random().nextInt(wxJobInfoDto.getDelayTimes()) + 1;
	                     //Date now = new Date();
	                     nextSendTime = DateUtils.addMinutes(wxJobInfoDto.getExecuteTime(), delay);
	                	 jc = getJobCenterWithDate(nextSendTime);
	                }
	                jc.setJobEnglishName("addWxJobInfo:" + noWxpackInfo.getCode() + ":start");
	                jc.setJobIntf(callbackUrl);
	                jc.setJobName("添加发送红包任务调度");
	                jc.setSystemAliasName("weixin");
	                jc.setJobParam(noWxpackInfo.getCode());
	                logger.debug("addWxJobInfo: " + jc);
	                wxJobRedPackInfoService.addWxJobRedPackInfo(noWxpackInfo);
	                wxRedPackDetailinfoService.addWxRedpackDetailInfoBatch(noWxpackInfo.getRedPackList(),noWxpackInfo);
	                jobMgrService.addTempJob(jc);
            	}
			logger.debug("addWxJobInfo(WxJobInfoDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增微信任务信息错误！",e);
			throw new TsfaServiceException(ErrorCodeWxJobInfo.WX_JOB_INFO_ADD_ERROR,"新增微信任务信息错误！",e);
		}
		return wxJobInfoDto;
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询微信任务信息
	 *
	 * @param findWxJobInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017年07月10日
	 *
	 */
	public List<WxJobInfoDto>  findWxJobInfos(FindWxJobInfoPage findWxJobInfoPage)throws TsfaServiceException{
		AssertUtils.notNull(findWxJobInfoPage);
		List<WxJobInfoDto> returnList=null;
		try {
			//returnList = wxJobInfoDao.findWxJobInfos(findWxJobInfoPage);
		} catch (Exception e) {
			logger.error("查找微信任务信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeWxJobInfo.WX_JOB_INFO_NOT_EXIST_ERROR,"微信任务信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateWxJobInfo(WxJobInfoDto wxJobInfoDto)
			throws TsfaServiceException {
		logger.debug("updateWxJobInfo(WxJobInfoDto wxJobInfoDto={}) - start", wxJobInfoDto); 

		AssertUtils.notNull(wxJobInfoDto);
		AssertUtils.notNullAndEmpty(wxJobInfoDto.getCode(),"Code不能为空");
		try {
			WxJobInfo wxJobInfo = new WxJobInfo();
			//update数据录入
			wxJobInfo.setCode(wxJobInfoDto.getCode());
			wxJobInfo.setJobName(wxJobInfoDto.getJobName());
			wxJobInfo.setJobLevel(wxJobInfoDto.getJobLevel());
			wxJobInfo.setJobStatus(wxJobInfoDto.getJobStatus());
			wxJobInfo.setJobDelayTime(wxJobInfoDto.getJobDelayTime());
			wxJobInfo.setJobType(wxJobInfoDto.getJobType());
			wxJobInfo.setJobService(wxJobInfoDto.getJobService());
			wxJobInfo.setCreateDate(wxJobInfoDto.getCreateDate());
			wxJobInfo.setUpdateDate(wxJobInfoDto.getUpdateDate());
			wxJobInfo.setCreateUser(wxJobInfoDto.getCreateUser());
			AssertUtils.notUpdateMoreThanOne(wxJobInfoDao.updateByPrimaryKeySelective(wxJobInfo));
			logger.debug("updateWxJobInfo(WxJobInfoDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("微信任务信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCodeWxJobInfo.WX_JOB_INFO_UPDATE_ERROR,"微信任务信息更新信息错误！",e);
		}
	}

	

	@Override
	public WxJobInfoDto findWxJobInfo(
			WxJobInfoDto wxJobInfoDto) throws TsfaServiceException {
		logger.debug("findWxJobInfo(FindWxJobInfo findWxJobInfo={}) - start", wxJobInfoDto); 

		AssertUtils.notNull(wxJobInfoDto);
		AssertUtils.notAllNull(wxJobInfoDto.getCode(),"Code不能为空");
		try {
			WxJobInfo wxJobInfo = wxJobInfoDao.selectByPrimaryKey(wxJobInfoDto.getCode());
			if(wxJobInfo == null){
				return null;
				//throw new TsfaServiceException(ErrorCodeWxJobInfo.WX_JOB_INFO_NOT_EXIST_ERROR,"微信任务信息不存在");
			}
			WxJobInfoDto findWxJobInfoReturn = new WxJobInfoDto();
			//find数据录入
			findWxJobInfoReturn.setCode(wxJobInfo.getCode());
			findWxJobInfoReturn.setJobName(wxJobInfo.getJobName());
			findWxJobInfoReturn.setJobLevel(wxJobInfo.getJobLevel());
			findWxJobInfoReturn.setJobStatus(wxJobInfo.getJobStatus());
			findWxJobInfoReturn.setJobDelayTime(wxJobInfo.getJobDelayTime());
			findWxJobInfoReturn.setJobType(wxJobInfo.getJobType());
			findWxJobInfoReturn.setJobService(wxJobInfo.getJobService());
			findWxJobInfoReturn.setCreateDate(wxJobInfo.getCreateDate());
			findWxJobInfoReturn.setUpdateDate(wxJobInfo.getUpdateDate());
			findWxJobInfoReturn.setCreateUser(wxJobInfo.getCreateUser());
			
			logger.debug("findWxJobInfo(WxJobInfoDto) - end - return value={}", findWxJobInfoReturn); 
			return findWxJobInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找微信任务信息信息错误！",e);
			throw new TsfaServiceException(ErrorCodeWxJobInfo.WX_JOB_INFO_FIND_ERROR,"查找微信任务信息信息错误！",e);
		}


	}
	 private JobCenter getJobCenterWithDate(Date date) {
	        JobCenter jc = new JobCenter();
	        jc.setIsEnable("1");
	        //构造corn表达式
	        StringBuilder jobCalender = new StringBuilder("");
	        Calendar calendar = Calendar.getInstance();	
	        calendar.setTime(date);//设置日历时间
	        jobCalender.append(calendar.get(Calendar.SECOND))
	        .append(" ").append(calendar.get(Calendar.MINUTE))
	        .append(" ").append(calendar.get(Calendar.HOUR_OF_DAY))
	        .append(" ").append(calendar.get(Calendar.DATE))
	        .append(" ").append(calendar.get(Calendar.MONTH) + 1)
	        .append(" ?")
	        .append(" ").append(calendar.get(Calendar.YEAR));
	        jc.setJobCalender(jobCalender.toString());//0 47 11 25 12 ? 2017
	        
	        return jc;
	    }

	
	@Override
	public Page<WxJobInfoDto> findWxJobInfoPage(
			FindWxJobInfoPage findWxJobInfoPage)
			throws TsfaServiceException {
		logger.debug("findWxJobInfoPage(FindWxJobInfoPage findWxJobInfoPage={}) - start", findWxJobInfoPage); 

		AssertUtils.notNull(findWxJobInfoPage);
		List<WxJobInfoDto> returnList=null;
		int count = 0;
		try {
			//returnList = wxJobInfoDao.findWxJobInfoPage(findWxJobInfoPage);
			//count = wxJobInfoDao.findWxJobInfoPageCount(findWxJobInfoPage);
		}  catch (Exception e) {
			logger.error("微信任务信息不存在错误",e);
			throw new TsfaServiceException(ErrorCodeWxJobInfo.WX_JOB_INFO_FIND_PAGE_ERROR,"微信任务信息不存在错误.！",e);
		}
		Page<WxJobInfoDto> returnPage = new Page<WxJobInfoDto>(returnList, count, findWxJobInfoPage);

		logger.debug("findWxJobInfoPage(FindWxJobInfoPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
