package com.lj.business.cf.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cf.constant.ErrorCode;
import com.lj.business.cf.dao.IClientNoteInfoDao;
import com.lj.business.cf.domain.ClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoList;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoPage;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoPageReturn;
import com.lj.business.cf.dto.clientNoteInfo.FindClientNoteInfoReturn;
import com.lj.business.cf.dto.clientNoteInfo.UpdateClientNoteInfo;
import com.lj.business.cf.dto.clientNoteInfo.UpdateClientNoteInfoReturn;
import com.lj.business.cf.service.IClientFollowService;
import com.lj.business.cf.service.IClientFollowSummaryService;
import com.lj.business.cf.service.IClientKeepService;
import com.lj.business.cf.service.IClientKeepSummaryService;
import com.lj.business.cf.service.IClientNoteInfoService;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;

/**
 * 
 * 
 * 类说明：短信记录信息实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年10月20日
 */
@Service
public class ClientNoteInfoServiceImpl implements IClientNoteInfoService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ClientNoteInfoServiceImpl.class);
	

	@Resource
	private IClientNoteInfoDao clientNoteInfoDao;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private IClientFollowService clientFollowService;
	
	@Resource
	private IClientFollowSummaryService clientFollowSummaryService;
	
	@Resource
	private IPersonMemberBaseService personMemberBaseService;
	
	@Resource
	private IClientKeepSummaryService clientKeepSummaryService;
	
	@Resource
	private IClientKeepService clientKeepService;
	
	@Resource
	private ClientNoteInfoServiceImpl clientNoteInfoServiceImpl;
	
	
	
	@Override
	public void addClientNoteInfo(String info) throws TsfaServiceException {
		logger.debug(info); 
		AssertUtils.notNull(info);
		try {
			JSONObject jsonObject = JSONObject.fromObject(info);
			String memberNoGm=jsonObject.getString("memberNoGuid");
			boolean flag = false;
			if(StringUtils.isEmpty(memberNoGm)){
				flag=true;
			}
			if(flag){
			 throw new TsfaServiceException(ErrorCode.CLIENT_NOTE_INFO_ADD_ERROR, "导购编号不能为空！");
			}
			JSONArray josnDate=jsonObject.getJSONArray("data");
			if(josnDate.size()>0){
				for(int i=0 ;i<josnDate.size();i++){
					try {
						clientNoteInfoServiceImpl.clientNoteProcess(memberNoGm, josnDate, i);
					} catch (Exception e) {
						logger.error("【短信记录上传失败】info:"+info.toString(),e);
					}
					
				}
			}
			
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增短信信息记录表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_NOTE_INFO_ADD_ERROR,"新增短信信息记录表信息错误！",e);
		}
	}


	/**
	 * 
	 *
	 * 方法说明：短信记录上传处理
	 *
	 * @param memberNoGm
	 * @param josnDate
	 * @param i
	 *
	 * @author 彭阳 CreateDate: 2017年10月24日
	 *
	 */
	public void clientNoteProcess(String memberNoGm, JSONArray josnDate, int i) {
		JSONObject job;
		job=josnDate.getJSONObject(i);
		ClientNoteInfo clientNoteInfo=new ClientNoteInfo();
		//add数据录入
		clientNoteInfo.setCode(GUID.generateCode());
		clientNoteInfo.setMemberNoGm(memberNoGm);
		clientNoteInfo.setMobile(job.containsKey("mobile")?job.getString("mobile"):"");
		clientNoteInfo.setSendType(job.containsKey("sendType")?job.getString("sendType"):"");
		clientNoteInfo.setSendTime(job.containsKey("sendTime")?job.getString("sendTime"):"");
		clientNoteInfo.setCreateDate(new Date());
		clientNoteInfoDao.insertSelective(clientNoteInfo);
	}
	

	@Override
	public UpdateClientNoteInfoReturn updateClientNoteInfo(
			UpdateClientNoteInfo updateClientNoteInfo)
			throws TsfaServiceException {
		logger.debug("updateClientNoteInfo(UpdateClientNoteInfo updateClientNoteInfo={}) - start", updateClientNoteInfo); //$NON-NLS-1$

		AssertUtils.notNull(updateClientNoteInfo);
		AssertUtils.notNullAndEmpty(updateClientNoteInfo.getCode(),"CODE不能为空");
		try {
			ClientNoteInfo clientNoteInfo = new ClientNoteInfo();
			//update数据录入
			clientNoteInfo.setCode(updateClientNoteInfo.getCode());
			clientNoteInfo.setMemberNo(updateClientNoteInfo.getMemberNo());
			clientNoteInfo.setMobile(updateClientNoteInfo.getMobile());
			clientNoteInfo.setSendType(updateClientNoteInfo.getSendType());
			clientNoteInfo.setSendTime(updateClientNoteInfo.getSendTime());
			clientNoteInfo.setCreateDate(updateClientNoteInfo.getCreateDate());
			clientNoteInfo.setRemark(updateClientNoteInfo.getRemark());
			clientNoteInfo.setRemark4(updateClientNoteInfo.getRemark4());
			clientNoteInfo.setRemark3(updateClientNoteInfo.getRemark3());
			clientNoteInfo.setRemark2(updateClientNoteInfo.getRemark2());
			AssertUtils.notUpdateMoreThanOne(clientNoteInfoDao.updateByPrimaryKeySelective(clientNoteInfo));
			UpdateClientNoteInfoReturn updateClientNoteInfoReturn = new UpdateClientNoteInfoReturn();

			logger.debug("updateClientNoteInfo(UpdateClientNoteInfo) - end - return value={}", updateClientNoteInfoReturn); //$NON-NLS-1$
			return updateClientNoteInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("短信信息记录表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_NOTE_INFO_UPDATE_ERROR,"短信信息记录表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindClientNoteInfoReturn findClientNoteInfo(
			FindClientNoteInfo findClientNoteInfo) throws TsfaServiceException {
		logger.debug("findClientNoteInfo(FindClientNoteInfo findClientNoteInfo={}) - start", findClientNoteInfo); //$NON-NLS-1$

		AssertUtils.notNull(findClientNoteInfo);
		AssertUtils.notAllNull(findClientNoteInfo.getCode(),"CODE不能为空");
		try {
			ClientNoteInfo clientNoteInfo = clientNoteInfoDao.selectByPrimaryKey(findClientNoteInfo.getCode());
			if(clientNoteInfo == null){
				throw new TsfaServiceException(ErrorCode.CLIENT_NOTE_INFO_NOT_EXIST_ERROR,"短信信息记录表信息不存在");
			}
			FindClientNoteInfoReturn findClientNoteInfoReturn = new FindClientNoteInfoReturn();
			//find数据录入
			findClientNoteInfoReturn.setCode(clientNoteInfo.getCode());
			findClientNoteInfoReturn.setMemberNo(clientNoteInfo.getMemberNo());
			findClientNoteInfoReturn.setMobile(clientNoteInfo.getMobile());
			findClientNoteInfoReturn.setSendType(clientNoteInfo.getSendType());
			findClientNoteInfoReturn.setSendTime(clientNoteInfo.getSendTime());
			findClientNoteInfoReturn.setCreateDate(clientNoteInfo.getCreateDate());
			findClientNoteInfoReturn.setRemark(clientNoteInfo.getRemark());
			findClientNoteInfoReturn.setRemark4(clientNoteInfo.getRemark4());
			findClientNoteInfoReturn.setRemark3(clientNoteInfo.getRemark3());
			findClientNoteInfoReturn.setRemark2(clientNoteInfo.getRemark2());
			  
			logger.debug("findClientNoteInfo(FindClientNoteInfo) - end - return value={}", findClientNoteInfoReturn); //$NON-NLS-1$
			return findClientNoteInfoReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找短信信息记录表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_NOTE_INFO_FIND_ERROR,"查找短信信息记录表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindClientNoteInfoPageReturn> findClientNoteInfoPage(
			FindClientNoteInfoPage findClientNoteInfoPage)
			throws TsfaServiceException {
		logger.debug("findClientNoteInfoPage(FindClientNoteInfoPage findClientNoteInfoPage={}) - start", findClientNoteInfoPage); //$NON-NLS-1$

		AssertUtils.notNull(findClientNoteInfoPage);
		List<FindClientNoteInfoPageReturn> returnList;
		int count = 0;
		try {
			returnList = clientNoteInfoDao.findClientNoteInfoPage(findClientNoteInfoPage);
			count = clientNoteInfoDao.findClientNoteInfoPageCount(findClientNoteInfoPage);
		}  catch (Exception e) {
			logger.error("短信信息记录表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_NOTE_INFO_FIND_PAGE_ERROR,"短信信息记录表信息不存在错误.！",e);
		}
		Page<FindClientNoteInfoPageReturn> returnPage = new Page<FindClientNoteInfoPageReturn>(returnList, count, findClientNoteInfoPage);

		logger.debug("findClientNoteInfoPage(FindClientNoteInfoPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public long clientNoteInfoSendTime(FindClientNoteInfo findClientNoteInfo)
			throws TsfaServiceException {
		AssertUtils.notAllNull(findClientNoteInfo.getMemberNoGm(),"导购编号不能为空");
		FindClientNoteInfoList infoReturn=null;
		try {
			infoReturn=clientNoteInfoDao.clientNoteInfoSendTime(findClientNoteInfo);
			if(infoReturn !=null){
				return Long.parseLong(infoReturn.getSendTime());
			}else{
				return 0L;
			}
		} catch (Exception e) {
			logger.error("短信信息记录表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_NOTE_INFO_FIND_PAGE_ERROR,"短信信息记录表信息不存在错误.！",e);
		}
	}


	@Override
	public List<Map<String, Object>> findCountNoteByGm(
			Map<String, Object> map) {
		logger.debug("findCountNoteByGm(Map<String, Object> map={}) - start", map); //$NON-NLS-1$
		return clientNoteInfoDao.findCountNoteByGm(map);
	}


	@Override
	public ClientNoteInfo findFristNoteInfo(
			FindClientNoteInfo findClientNoteInfo) {
		logger.debug("findFristNoteInfo(FindClientNoteInfo findClientNoteInfo={}) - start", findClientNoteInfo); //$NON-NLS-1$
		return clientNoteInfoDao.findFristNoteInfo(findClientNoteInfo);
	}


	@Override
	public List<FindClientNoteInfoList> findClientInfoMemberNoGm() throws TsfaServiceException {
		List<FindClientNoteInfoList> list =null;
		try {
			list=clientNoteInfoDao.findClientInfoMemberNoGm();
		} catch (Exception e) {
			logger.error("短信信息记录表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.CLIENT_NOTE_INFO_FIND_PAGE_ERROR,"短信信息记录表信息不存在错误.！",e);
		}
		return list;
	} 

}
