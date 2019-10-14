package com.lj.business.cf.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cf.constant.ErrorCode;
import com.lj.business.cf.dao.ICallChatInfoDao;
import com.lj.business.cf.domain.CallChatInfo;
import com.lj.business.cf.dto.callChatInfo.AddCallChatInfo;
import com.lj.business.cf.dto.callChatInfo.DelCallChatInfo;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfo;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoPage;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoPageReturn;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoReturn;
import com.lj.business.cf.dto.callChatInfo.UpdateCallChatInfo;
import com.lj.business.cf.dto.clientFollow.AddClientFollow;
import com.lj.business.cf.dto.clientFollowSummary.FindClientFollowSummary;
import com.lj.business.cf.dto.clientFollowSummary.FindClientFollowSummaryReturn;
import com.lj.business.cf.dto.clientKeep.AddClientKeep;
import com.lj.business.cf.dto.clientKeepSummary.FindClientKeepSummary;
import com.lj.business.cf.dto.clientKeepSummary.FindClientKeepSummaryReturn;
import com.lj.business.cf.emus.CallStatusEmus;
import com.lj.business.cf.emus.KeepType;
import com.lj.business.cf.emus.Status;
import com.lj.business.cf.service.ICallChatInfoService;
import com.lj.business.cf.service.IClientFollowService;
import com.lj.business.cf.service.IClientFollowSummaryService;
import com.lj.business.cf.service.IClientKeepService;
import com.lj.business.cf.service.IClientKeepSummaryService;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class CallChatInfoServiceImpl implements ICallChatInfoService { 


	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CallChatInfoServiceImpl.class);


	@Resource
	private ICallChatInfoDao callChatInfoDao;

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


	@Override
	public void addCallChatInfo(
			AddCallChatInfo addCallChatInfo) throws TsfaServiceException {
		logger.debug("addCallChatInfo(AddCallChatInfo addCallChatInfo={}) - start", addCallChatInfo); 

		AssertUtils.notNull(addCallChatInfo);
		try {
			CallChatInfo callChatInfo = new CallChatInfo();
			//add数据录入
			callChatInfo.setCode(addCallChatInfo.getCode());
			callChatInfo.setMemberNo(addCallChatInfo.getMemberNo());
			callChatInfo.setMemberName(addCallChatInfo.getMemberName());
			callChatInfo.setMobile(addCallChatInfo.getMobile());
			callChatInfo.setLinkmanRemark(addCallChatInfo.getLinkmanRemark());
			callChatInfo.setCallTime(addCallChatInfo.getCallTime());
			callChatInfo.setCallDate(addCallChatInfo.getCallDate());
			callChatInfo.setStatus(addCallChatInfo.getStatus());
			callChatInfo.setCreateDate(addCallChatInfo.getCreateDate());
			callChatInfo.setRemark(addCallChatInfo.getRemark());
			callChatInfo.setRemark2(addCallChatInfo.getRemark2());
			callChatInfo.setRemark3(addCallChatInfo.getRemark3());
			callChatInfo.setRemark4(addCallChatInfo.getRemark4());
			callChatInfoDao.insert(callChatInfo);
			logger.debug("addCallChatInfo(AddCallChatInfo) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增电话聊天信息记录表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CALL_CHAT_INFO_ADD_ERROR,"新增电话聊天信息记录表信息错误！",e);
		}
	}


	@Override
	public void delCallChatInfo(
			DelCallChatInfo delCallChatInfo) throws TsfaServiceException {
		logger.debug("delCallChatInfo(DelCallChatInfo delCallChatInfo={}) - start", delCallChatInfo); 

		AssertUtils.notNull(delCallChatInfo);
		AssertUtils.notNull(delCallChatInfo.getCode(),"Code不能为空！");
		try {
			callChatInfoDao.deleteByPrimaryKey(delCallChatInfo.getCode());
			logger.debug("delCallChatInfo(DelCallChatInfo) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除电话聊天信息记录表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CALL_CHAT_INFO_DEL_ERROR,"删除电话聊天信息记录表信息错误！",e);

		}
	}

	@Override
	public void updateCallChatInfo(
			UpdateCallChatInfo updateCallChatInfo)
					throws TsfaServiceException {
		logger.debug("updateCallChatInfo(UpdateCallChatInfo updateCallChatInfo={}) - start", updateCallChatInfo); //$NON-NLS-1$

		AssertUtils.notNull(updateCallChatInfo);
		AssertUtils.notNullAndEmpty(updateCallChatInfo.getCode(),"Code不能为空");
		try {
			CallChatInfo callChatInfo = new CallChatInfo();
			//update数据录入
			callChatInfo.setCode(updateCallChatInfo.getCode());
			callChatInfo.setMemberNo(updateCallChatInfo.getMemberNo());
			callChatInfo.setMemberName(updateCallChatInfo.getMemberName());
			callChatInfo.setMobile(updateCallChatInfo.getMobile());
			callChatInfo.setLinkmanRemark(updateCallChatInfo.getLinkmanRemark());
			callChatInfo.setCallTime(updateCallChatInfo.getCallTime());
			callChatInfo.setCallDate(updateCallChatInfo.getCallDate());
			callChatInfo.setStatus(updateCallChatInfo.getStatus());
			callChatInfo.setCreateDate(updateCallChatInfo.getCreateDate());
			callChatInfo.setRemark(updateCallChatInfo.getRemark());
			callChatInfo.setRemark2(updateCallChatInfo.getRemark2());
			callChatInfo.setRemark3(updateCallChatInfo.getRemark3());
			callChatInfo.setRemark4(updateCallChatInfo.getRemark4());
			AssertUtils.notUpdateMoreThanOne(callChatInfoDao.updateByPrimaryKeySelective(callChatInfo));
			logger.debug("updateCallChatInfo(UpdateCallChatInfo) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("电话聊天信息记录表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CALL_CHAT_INFO_UPDATE_ERROR,"电话聊天信息记录表信息更新信息错误！",e);
		}
	}



	@Override
	public FindCallChatInfoReturn findCallChatInfo(
			FindCallChatInfo findCallChatInfo) throws TsfaServiceException {
		logger.debug("findCallChatInfo(FindCallChatInfo findCallChatInfo={}) - start", findCallChatInfo); //$NON-NLS-1$

		AssertUtils.notNull(findCallChatInfo);
		AssertUtils.notAllNull(findCallChatInfo.getCode(),"Code不能为空");
		try {
			CallChatInfo callChatInfo = callChatInfoDao.selectByPrimaryKey(findCallChatInfo.getCode());
			if(callChatInfo == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.CALL_CHAT_INFO_NOT_EXIST_ERROR,"电话聊天信息记录表信息不存在");
			}
			FindCallChatInfoReturn findCallChatInfoReturn = new FindCallChatInfoReturn();
			//find数据录入
			findCallChatInfoReturn.setCode(callChatInfo.getCode());
			findCallChatInfoReturn.setMemberNo(callChatInfo.getMemberNo());
			findCallChatInfoReturn.setMemberName(callChatInfo.getMemberName());
			findCallChatInfoReturn.setMobile(callChatInfo.getMobile());
			findCallChatInfoReturn.setLinkmanRemark(callChatInfo.getLinkmanRemark());
			findCallChatInfoReturn.setCallTime(callChatInfo.getCallTime());
			findCallChatInfoReturn.setCallDate(callChatInfo.getCallDate());
			findCallChatInfoReturn.setStatus(callChatInfo.getStatus());
			findCallChatInfoReturn.setCreateDate(callChatInfo.getCreateDate());
			findCallChatInfoReturn.setRemark(callChatInfo.getRemark());
			findCallChatInfoReturn.setRemark2(callChatInfo.getRemark2());
			findCallChatInfoReturn.setRemark3(callChatInfo.getRemark3());
			findCallChatInfoReturn.setRemark4(callChatInfo.getRemark4());

			logger.debug("findCallChatInfo(FindCallChatInfo) - end - return value={}", findCallChatInfoReturn); //$NON-NLS-1$
			return findCallChatInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找电话聊天信息记录表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CALL_CHAT_INFO_FIND_ERROR,"查找电话聊天信息记录表信息信息错误！",e);
		}


	}


	@Override
	public Page<FindCallChatInfoPageReturn> findCallChatInfoPage(
			FindCallChatInfoPage findCallChatInfoPage)
					throws TsfaServiceException {
		logger.debug("findCallChatInfoPage(FindCallChatInfoPage findCallChatInfoPage={}) - start", findCallChatInfoPage); //$NON-NLS-1$

		AssertUtils.notNull(findCallChatInfoPage);
		List<FindCallChatInfoPageReturn> returnList;
		int count = 0;
		try {
			returnList = callChatInfoDao.findCallChatInfoPage(findCallChatInfoPage);
			count = callChatInfoDao.findCallChatInfoPageCount(findCallChatInfoPage);
		}  catch (Exception e) {
			logger.error("电话聊天信息记录表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.CALL_CHAT_INFO_FIND_PAGE_ERROR,"电话聊天信息记录表信息不存在错误.！",e);
		}
		Page<FindCallChatInfoPageReturn> returnPage = new Page<FindCallChatInfoPageReturn>(returnList, count, findCallChatInfoPage);

		logger.debug("findCallChatInfoPage(FindCallChatInfoPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public int uploadCallChatInfo(String paramJson) throws TsfaServiceException {
		//主方法取消了事务
		AssertUtils.notNull(paramJson);
		int flag =0;
		try {
			JSONObject jsonObject = JSONObject.fromObject(paramJson);

			String memberNoGuid = jsonObject.getString("memberNoGuid");
			JSONArray data = jsonObject.getJSONArray("data");
			logger.info("uploadCallChatInfo start --- memberNoGuid:" + memberNoGuid);
			//根据导购编号获取导购信息
			FindGuidMember findGuidMember = new FindGuidMember();
			findGuidMember.setMemberNo(memberNoGuid);
			FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);

			long maxChatDate = getMaxCallDateByGuidNo(memberNoGuid);
			if (data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					callChatInfoProcess(data, guidMember, maxChatDate, i);
				}
			}
			flag = 1;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增电话聊天信息记录表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CALL_CHAT_INFO_ADD_ERROR,"新增电话聊天信息记录表信息错误！",e);
		}
		return flag;

	}


	/**
	 * 
	 *
	 * 方法说明：电话聊天记录处理
	 *
	 * @param data
	 * @param guidMember
	 * @param maxChatDate
	 * @param i
	 *
	 * @author 彭阳 CreateDate: 2017年10月26日
	 *
	 */
	private void callChatInfoProcess(JSONArray data,
			FindGuidMemberReturn guidMember, long maxChatDate, int i) {
		try {
			JSONObject info = data.getJSONObject(i);
			if (info.getLong("callDate") <= maxChatDate) {
				return;
			}
			FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
			findPersonMemberBase.setMobile(info.getString("mobile"));
			FindPersonMemberBaseReturn personMember = personMemberBaseService.findByMobile(findPersonMemberBase);
			if (personMember != null && (CallStatusEmus.CALL_IN.getStatus().equals(info.getString("status"))
					|| CallStatusEmus.CALL_TO.getStatus().equals(info.getString("status")))) {
				FindClientFollowSummary findClientFollowSummary = new FindClientFollowSummary();
				findClientFollowSummary.setMerchantNo(guidMember.getMerchantNo());
				findClientFollowSummary.setMemberNoGm(guidMember.getMemberNo());
				findClientFollowSummary.setMemberNo(personMember.getMemberNo());
				FindClientFollowSummaryReturn summaryLast = clientFollowSummaryService.findClientFollowSummaryLast(findClientFollowSummary);//查询跟进记录

				if (summaryLast == null) {//当跟进记录不存在时则在维护记录中添加
					FindClientKeepSummary findClientKeepSummary =new FindClientKeepSummary();
					findClientKeepSummary.setMerchantNo(guidMember.getMerchantNo());
					findClientKeepSummary.setMemberNoGm(guidMember.getMemberNo());
					findClientKeepSummary.setMemberNo(personMember.getMemberNo());
					FindClientKeepSummaryReturn findClientKeepSummaryReturn = clientKeepSummaryService.findClientKeepSummaryLast(findClientKeepSummary);

					if (findClientKeepSummaryReturn != null) {
						AddClientKeep addClientKeep = new AddClientKeep();
						addClientKeep.setCkNo(findClientKeepSummaryReturn.getCkNo());
						addClientKeep.setMerchantNo(guidMember.getMerchantNo());
						addClientKeep.setMemberNo(personMember.getMemberNo());
						addClientKeep.setMemberName(findClientKeepSummaryReturn.getMemberName());
						addClientKeep.setMemberNoGm(guidMember.getMemberNo());
						addClientKeep.setMemberNameGm(guidMember.getMemberName());
						addClientKeep.setKeepTime(new Date(info.getLong("callDate")));
						addClientKeep.setKeepType(KeepType.PHONE.toString());
						long callTime = info.getLong("callTime");
						int min = (int) (callTime / 60);
						int sec = (int)(callTime % 60);
						addClientKeep.setKeepContent("与客户通话时长为" + min + "`" + sec);
						addClientKeep.setCreateId(personMember.getMemberNo());
						clientKeepService.addClientKeepInfo(addClientKeep);
					}

				} else {
					AddClientFollow addClientFollow = new AddClientFollow();
					addClientFollow.setCfNo(summaryLast.getCfNo());
					addClientFollow.setMerchantNo(guidMember.getMerchantNo());
					addClientFollow.setMemberNo(personMember.getMemberNo());
					addClientFollow.setMemberName(personMember.getMemberName());
					addClientFollow.setMemberNoGm(guidMember.getMemberNo());
					addClientFollow.setMemberNameGm(guidMember.getMemberName());
					addClientFollow.setFollowTime(new Date(info.getLong("callDate")));
					addClientFollow.setFollowType(KeepType.PHONE.toString());
					long callTime = info.getLong("callTime");
					int min = (int) (callTime / 60);
					int sec = (int)(callTime % 60);
					addClientFollow.setFollowInfo("与客户通话时长为" + min + "`" + sec);
					addClientFollow.setDeal("N");
					addClientFollow.setStatus(Status.NORMAL);
					addClientFollow.setTaskName(KeepType.PHONE.getName());
					addClientFollow.setComTaskType(KeepType.PHONE.toString());
					addClientFollow.setComTaskTypeCf(KeepType.PHONE.toString());
					addClientFollow.setCreateId(guidMember.getMemberNo());
					clientFollowService.addCFOrder(addClientFollow, "0", false, false);
				}
			}

			CallChatInfo callChatInfo = new CallChatInfo();
			//add数据录入
			callChatInfo.setCode(GUID.getPreUUID());
			callChatInfo.setMemberNo(guidMember.getMemberNo());
			callChatInfo.setMemberName(guidMember.getMemberName());
			callChatInfo.setMobile(info.getString("mobile"));
			callChatInfo.setCallTime(info.getLong("callTime"));
			callChatInfo.setCallDate(info.getLong("callDate"));
			callChatInfo.setStatus(info.getString("status"));
			callChatInfo.setCreateDate(new Date());
			callChatInfoDao.insert(callChatInfo);
		} catch (Exception e) {
			logger.error("新增电话聊天信息记录表信息错误！",e);
		}
	}


	@Override
	public long getMaxCallDateByGuidNo(String memberNoGuid)
			throws TsfaServiceException {
		logger.info("getMaxCallDateByGuidNo(String memberNoGuid={})---start",memberNoGuid);
		try {
			if (StringUtils.isEmpty(memberNoGuid)) {
				return 0L;
			}
			Long maxChatDate = callChatInfoDao.getMaxCallDateByGuidNo(memberNoGuid);
			long maxTime = maxChatDate == null ? 0L : maxChatDate;
			logger.info("getMaxCallDateByGuidNo(maxTime={})---end",maxTime);
			return maxTime;
		} catch (Exception e) {
			logger.error("查找电话聊天信息记录表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CALL_CHAT_INFO_FIND_ERROR,"查找电话聊天信息记录表信息信息错误！",e);
		}
	} 


}
