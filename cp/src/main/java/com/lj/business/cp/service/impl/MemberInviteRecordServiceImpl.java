package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cp.constant.ErrorCode;
import com.lj.business.cp.dao.IMemberInviteRecordDao;
import com.lj.business.cp.domain.MemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.AddMemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.DelMemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecordPage;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecordPageReturn;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecordReturn;
import com.lj.business.cp.dto.memberInviteRecord.UpdateMemberInviteRecord;
import com.lj.business.cp.service.IMemberInviteRecordService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 杨杰
 * 
 * 
 *         CreateDate: 2017-06-14
 */
@Service
public class MemberInviteRecordServiceImpl implements IMemberInviteRecordService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MemberInviteRecordServiceImpl.class);

	@Resource
	private IMemberInviteRecordDao memberInviteRecordDao;

	@Override
	public void addMemberInviteRecord(AddMemberInviteRecord addMemberInviteRecord) throws TsfaServiceException {
		logger.debug("addMemberInviteRecord(AddMemberInviteRecord addMemberInviteRecord={}) - start", addMemberInviteRecord);

		AssertUtils.notNull(addMemberInviteRecord);
		try {
			MemberInviteRecord memberInviteRecord = new MemberInviteRecord();
			// add数据录入
			memberInviteRecord.setCode(GUID.getPreUUID());
			memberInviteRecord.setMemberMobile(addMemberInviteRecord.getMemberMobile());
			memberInviteRecord.setMemberNo(addMemberInviteRecord.getMemberNo());
			memberInviteRecord.setMemberName(addMemberInviteRecord.getMemberName());
			memberInviteRecord.setAddFriendsCode(addMemberInviteRecord.getAddFriendsCode());
			memberInviteRecord.setNickName(addMemberInviteRecord.getNickName());
			memberInviteRecord.setInvitedMobile(addMemberInviteRecord.getInvitedMobile());
			memberInviteRecord.setInvitedDate(addMemberInviteRecord.getInvitedDate());
			memberInviteRecord.setMemberNameInvited(addMemberInviteRecord.getMemberNameInvited());
			memberInviteRecord.setMemberWxInvited(addMemberInviteRecord.getMemberWxInvited());
			memberInviteRecord.setMemberHeadWxInvited(addMemberInviteRecord.getMemberHeadWxInvited());
			memberInviteRecord.setRuleCode(addMemberInviteRecord.getRuleCode());
			memberInviteRecord.setUpdateId(addMemberInviteRecord.getUpdateId());
			memberInviteRecord.setUpdateDate(addMemberInviteRecord.getUpdateDate());
			memberInviteRecord.setCreateId(addMemberInviteRecord.getCreateId());
			memberInviteRecord.setCreateDate(addMemberInviteRecord.getCreateDate());
			memberInviteRecordDao.insert(memberInviteRecord);
			logger.debug("addMemberInviteRecord(AddMemberInviteRecord) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_INVITE_RECORD_ADD_ERROR, "新增导购行为信息记录表信息错误！", e);
		}
	}

	@Override
	public void delMemberInviteRecord(DelMemberInviteRecord delMemberInviteRecord) throws TsfaServiceException {
		logger.debug("delMemberInviteRecord(DelMemberInviteRecord delMemberInviteRecord={}) - start", delMemberInviteRecord);

		AssertUtils.notNull(delMemberInviteRecord);
		AssertUtils.notNull(delMemberInviteRecord.getCode(), "Code不能为空！");
		try {
			memberInviteRecordDao.deleteByPrimaryKey(delMemberInviteRecord.getCode());
			logger.debug("delMemberInviteRecord(DelMemberInviteRecord) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_INVITE_RECORD_DEL_ERROR, "删除导购行为信息记录表信息错误！", e);

		}
	}

	@Override
	public void updateMemberInviteRecord(UpdateMemberInviteRecord updateMemberInviteRecord) throws TsfaServiceException {
		logger.debug("updateMemberInviteRecord(UpdateMemberInviteRecord updateMemberInviteRecord={}) - start", updateMemberInviteRecord); 

		AssertUtils.notNull(updateMemberInviteRecord);
		AssertUtils.notNullAndEmpty(updateMemberInviteRecord.getCode(), "Code不能为空");
		try {
			MemberInviteRecord memberInviteRecord = new MemberInviteRecord();
			// update数据录入
			memberInviteRecord.setCode(updateMemberInviteRecord.getCode());
			memberInviteRecord.setMemberMobile(updateMemberInviteRecord.getMemberMobile());
			memberInviteRecord.setMemberNo(updateMemberInviteRecord.getMemberNo());
			memberInviteRecord.setMemberName(updateMemberInviteRecord.getMemberName());
			memberInviteRecord.setAddFriendsCode(updateMemberInviteRecord.getAddFriendsCode());
			memberInviteRecord.setNickName(updateMemberInviteRecord.getNickName());
			memberInviteRecord.setInvitedMobile(updateMemberInviteRecord.getInvitedMobile());
			memberInviteRecord.setInvitedDate(updateMemberInviteRecord.getInvitedDate());
			memberInviteRecord.setMemberNameInvited(updateMemberInviteRecord.getMemberNameInvited());
			memberInviteRecord.setMemberWxInvited(updateMemberInviteRecord.getMemberWxInvited());
			memberInviteRecord.setMemberHeadWxInvited(updateMemberInviteRecord.getMemberHeadWxInvited());
			memberInviteRecord.setUpdateId(updateMemberInviteRecord.getUpdateId());
			memberInviteRecord.setUpdateDate(updateMemberInviteRecord.getUpdateDate());
			memberInviteRecord.setCreateId(updateMemberInviteRecord.getCreateId());
			memberInviteRecord.setCreateDate(updateMemberInviteRecord.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(memberInviteRecordDao.updateByPrimaryKeySelective(memberInviteRecord));
			logger.debug("updateMemberInviteRecord(UpdateMemberInviteRecord) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_INVITE_RECORD_UPDATE_ERROR, "导购行为信息记录表信息更新信息错误！", e);
		}
	}

	@Override
	public FindMemberInviteRecordReturn findMemberInviteRecord(FindMemberInviteRecord findMemberInviteRecord) throws TsfaServiceException {
		logger.debug("findMemberInviteRecord(FindMemberInviteRecord findMemberInviteRecord={}) - start", findMemberInviteRecord); 

		AssertUtils.notNull(findMemberInviteRecord);
		AssertUtils.notAllNull(findMemberInviteRecord.getCode(), "Code不能为空");
		try {
			MemberInviteRecord memberInviteRecord = memberInviteRecordDao.selectByPrimaryKey(findMemberInviteRecord.getCode());
			if (memberInviteRecord == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.MEMBER_INVITE_RECORD_NOT_EXIST_ERROR,"导购行为信息记录表信息不存在");
			}
			FindMemberInviteRecordReturn findMemberInviteRecordReturn = new FindMemberInviteRecordReturn();
			// find数据录入
			findMemberInviteRecordReturn.setCode(memberInviteRecord.getCode());
			findMemberInviteRecordReturn.setMemberMobile(memberInviteRecord.getMemberMobile());
			findMemberInviteRecordReturn.setMemberNo(memberInviteRecord.getMemberNo());
			findMemberInviteRecordReturn.setMemberName(memberInviteRecord.getMemberName());
			findMemberInviteRecordReturn.setAddFriendsCode(memberInviteRecord.getAddFriendsCode());
			findMemberInviteRecordReturn.setNickName(memberInviteRecord.getNickName());
			findMemberInviteRecordReturn.setInvitedMobile(memberInviteRecord.getInvitedMobile());
			findMemberInviteRecordReturn.setInvitedDate(memberInviteRecord.getInvitedDate());
			findMemberInviteRecordReturn.setMemberNameInvited(memberInviteRecord.getMemberNameInvited());
			findMemberInviteRecordReturn.setMemberWxInvited(memberInviteRecord.getMemberWxInvited());
			findMemberInviteRecordReturn.setMemberHeadWxInvited(memberInviteRecord.getMemberHeadWxInvited());
			findMemberInviteRecordReturn.setUpdateId(memberInviteRecord.getUpdateId());
			findMemberInviteRecordReturn.setUpdateDate(memberInviteRecord.getUpdateDate());
			findMemberInviteRecordReturn.setCreateId(memberInviteRecord.getCreateId());
			findMemberInviteRecordReturn.setCreateDate(memberInviteRecord.getCreateDate());

			logger.debug("findMemberInviteRecord(FindMemberInviteRecord) - end - return value={}", findMemberInviteRecordReturn); 
			return findMemberInviteRecordReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购行为信息记录表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_INVITE_RECORD_FIND_ERROR, "查找导购行为信息记录表信息信息错误！", e);
		}

	}

	@Override
	public Page<FindMemberInviteRecordPageReturn> findMemberInviteRecordPage(FindMemberInviteRecordPage findMemberInviteRecordPage) throws TsfaServiceException {
		//		logger.debug("findMemberInviteRecordPage(FindMemberInviteRecordPage findMemberInviteRecordPage={}) - start", findMemberInviteRecordPage); 
		//
		// AssertUtils.notNull(findMemberInviteRecordPage);
		// List<FindMemberInviteRecordPageReturn> returnList;
		// int count = 0;
		// try {
		// returnList = memberInviteRecordDao.findMemberInviteRecordPage(findMemberInviteRecordPage);
		// count = memberInviteRecordDao.findMemberInviteRecordPageCount(findMemberInviteRecordPage);
		// } catch (Exception e) {
		// logger.error("导购行为信息记录表信息不存在错误", e);
		// throw new TsfaServiceException(ErrorCode.MEMBER_INVITE_RECORD_FIND_PAGE_ERROR, "导购行为信息记录表信息不存在错误.！", e);
		// }
		// Page<FindMemberInviteRecordPageReturn> returnPage = new Page<FindMemberInviteRecordPageReturn>(returnList, count, findMemberInviteRecordPage);
		//
		//		logger.debug("findMemberInviteRecordPage(FindMemberInviteRecordPage) - end - return value={}", returnPage); 
		// return returnPage;

		return null;
	}
}
