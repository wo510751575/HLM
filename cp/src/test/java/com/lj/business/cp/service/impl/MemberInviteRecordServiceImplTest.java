package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cp.dto.memberInviteRecord.AddMemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.DelMemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecordPage;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecordPageReturn;
import com.lj.business.cp.dto.memberInviteRecord.UpdateMemberInviteRecord;
import com.lj.business.cp.service.IMemberInviteRecordService;

/**
 * 类说明：测试类
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
public class MemberInviteRecordServiceImplTest extends SpringTestCase {

	@Resource
	IMemberInviteRecordService memberInviteRecordService;

	/**
	 * 
	 *
	 * 方法说明：添加导购行为信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addMemberInviteRecord() throws TsfaServiceException {
		AddMemberInviteRecord addMemberInviteRecord = new AddMemberInviteRecord();
		// add数据录入
		addMemberInviteRecord.setCode("Code");
		addMemberInviteRecord.setMemberMobile("MemberMobile");
		addMemberInviteRecord.setMemberNo("MemberNo");
		addMemberInviteRecord.setMemberName("MemberName");
		addMemberInviteRecord.setInvitedMobile("InvitedMobile");
		// addMemberInviteRecord.setInvitedDate("InvitedDate");
		addMemberInviteRecord.setMemberNameInvited("MemberNameInvited");
		addMemberInviteRecord.setMemberWxInvited("MemberWxInvited");
		addMemberInviteRecord.setMemberHeadWxInvited("MemberHeadWxInvited");
		addMemberInviteRecord.setUpdateId("UpdateId");
		// addMemberInviteRecord.setUpdateDate("UpdateDate");
		addMemberInviteRecord.setCreateId("CreateId");
		// addMemberInviteRecord.setCreateDate("CreateDate");

		memberInviteRecordService.addMemberInviteRecord(addMemberInviteRecord);

	}

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void updateMemberInviteRecord() throws TsfaServiceException {
		UpdateMemberInviteRecord updateMemberInviteRecord = new UpdateMemberInviteRecord();
		// update数据录入
		updateMemberInviteRecord.setCode("Code");
		updateMemberInviteRecord.setMemberMobile("MemberMobile");
		updateMemberInviteRecord.setMemberNo("MemberNo");
		updateMemberInviteRecord.setMemberName("MemberName");
		updateMemberInviteRecord.setInvitedMobile("InvitedMobile");
		// updateMemberInviteRecord.setInvitedDate("InvitedDate");
		updateMemberInviteRecord.setMemberNameInvited("MemberNameInvited");
		updateMemberInviteRecord.setMemberWxInvited("MemberWxInvited");
		updateMemberInviteRecord.setMemberHeadWxInvited("MemberHeadWxInvited");
		updateMemberInviteRecord.setUpdateId("UpdateId");
		// updateMemberInviteRecord.setUpdateDate("UpdateDate");
		updateMemberInviteRecord.setCreateId("CreateId");
		// updateMemberInviteRecord.setCreateDate("CreateDate");

		memberInviteRecordService.updateMemberInviteRecord(updateMemberInviteRecord);

	}

	/**
	 * 
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findMemberInviteRecord() throws TsfaServiceException {
		FindMemberInviteRecord findMemberInviteRecord = new FindMemberInviteRecord();
		findMemberInviteRecord.setCode("111");
		memberInviteRecordService.findMemberInviteRecord(findMemberInviteRecord);
	}

	/**
	 * 
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findMemberInviteRecordPage() throws TsfaServiceException {
		FindMemberInviteRecordPage findMemberInviteRecordPage = new FindMemberInviteRecordPage();
		Page<FindMemberInviteRecordPageReturn> page = memberInviteRecordService.findMemberInviteRecordPage(findMemberInviteRecordPage);
		Assert.assertNotNull(page);

	}

	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void delMemberInviteRecord() throws TsfaServiceException {
		DelMemberInviteRecord delMemberInviteRecord = new DelMemberInviteRecord();
		delMemberInviteRecord.setCode("111");
		memberInviteRecordService.delMemberInviteRecord(delMemberInviteRecord);

	}

}
