package com.lj.business.cp.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.cp.dto.memberInviteRecord.AddMemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.DelMemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecord;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecordPage;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecordPageReturn;
import com.lj.business.cp.dto.memberInviteRecord.FindMemberInviteRecordReturn;
import com.lj.business.cp.dto.memberInviteRecord.UpdateMemberInviteRecord;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;


/**
 * 类说明：接口类
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
public interface IMemberInviteRecordService {
	
	/**
	 * 
	 *
	 * 方法说明：添加导购行为信息记录表信息
	 *
	 * @param addMemberInviteRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addMemberInviteRecord(AddMemberInviteRecord addMemberInviteRecord) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @param findMemberInviteRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindMemberInviteRecordReturn findMemberInviteRecord(FindMemberInviteRecord findMemberInviteRecord) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @param delMemberInviteRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delMemberInviteRecord(DelMemberInviteRecord delMemberInviteRecord) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @param updateMemberInviteRecord
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateMemberInviteRecord(UpdateMemberInviteRecord updateMemberInviteRecord)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购行为信息记录表信息
	 *
	 * @param findMemberInviteRecordPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindMemberInviteRecordPageReturn> findMemberInviteRecordPage(FindMemberInviteRecordPage findMemberInviteRecordPage) throws TsfaServiceException;
	

}
