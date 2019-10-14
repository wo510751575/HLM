package com.lj.business.member.service.impl;

import java.util.Date;
/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IAddFriendsTaskDetailDao;
import com.lj.business.member.domain.AddFriendsTaskDetail;
import com.lj.business.member.dto.AddFriendsTaskDetailDto;
import com.lj.business.member.dto.FindAddFriendsTaskDetailPage;
import com.lj.business.member.service.IAddFriendsTaskDetailService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
public class AddFriendsTaskDetailServiceImpl implements IAddFriendsTaskDetailService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AddFriendsTaskDetailServiceImpl.class);
	

	@Resource
	private IAddFriendsTaskDetailDao addFriendsTaskDetailDao;
	
	
	@Override
	public void addAddFriendsTaskDetail(
			AddFriendsTaskDetailDto addFriendsTaskDetailDto) throws TsfaServiceException {
		logger.debug("addAddFriendsTaskDetail(AddAddFriendsTaskDetail addAddFriendsTaskDetail={}) - start", addFriendsTaskDetailDto); 

		AssertUtils.notNull(addFriendsTaskDetailDto);
		try {
			AddFriendsTaskDetail addFriendsTaskDetail = new AddFriendsTaskDetail();
			//add数据录入
			if(StringUtils.isNotEmpty(addFriendsTaskDetailDto.getCode())) {
				addFriendsTaskDetail.setCode(addFriendsTaskDetailDto.getCode());
			}else {
				addFriendsTaskDetail.setCode(GUID.generateCode());
			}
			addFriendsTaskDetail.setTaskCode(addFriendsTaskDetailDto.getTaskCode());
			addFriendsTaskDetail.setPhone(addFriendsTaskDetailDto.getPhone());
			addFriendsTaskDetail.setUsername(addFriendsTaskDetailDto.getUsername());
			addFriendsTaskDetail.setSendMessage(addFriendsTaskDetailDto.getSendMessage());
			addFriendsTaskDetail.setCreateDate(new Date());
			addFriendsTaskDetail.setExecuteDate(addFriendsTaskDetailDto.getExecuteDate());
			addFriendsTaskDetail.setAddfriendsDate(addFriendsTaskDetailDto.getAddfriendsDate());
			addFriendsTaskDetail.setCallbackDate(addFriendsTaskDetailDto.getCallbackDate());
			addFriendsTaskDetail.setNoWx(addFriendsTaskDetailDto.getNoWx());
			addFriendsTaskDetail.setNoWxGm(addFriendsTaskDetailDto.getNoWxGm());
			addFriendsTaskDetail.setStatus(addFriendsTaskDetailDto.getStatus());
			addFriendsTaskDetail.setDetail(addFriendsTaskDetailDto.getDetail());
			addFriendsTaskDetail.setMerchantNo(addFriendsTaskDetailDto.getMerchantNo());
			addFriendsTaskDetail.setMerchantName(addFriendsTaskDetailDto.getMerchantName());
			addFriendsTaskDetailDao.insertSelective(addFriendsTaskDetail);
			logger.debug("addAddFriendsTaskDetail(AddFriendsTaskDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增加粉任务信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_ADD_ERROR,"新增加粉任务信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询加粉任务信息
	 *
	 * @param findAddFriendsTaskDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<AddFriendsTaskDetailDto>  findAddFriendsTaskDetails(FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage)throws TsfaServiceException{
		AssertUtils.notNull(findAddFriendsTaskDetailPage);
		List<AddFriendsTaskDetailDto> returnList=null;
		try {
			returnList = addFriendsTaskDetailDao.findAddFriendsTaskDetails(findAddFriendsTaskDetailPage);
		} catch (Exception e) {
			logger.error("查找加粉任务信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_NOT_EXIST_ERROR,"加粉任务信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateAddFriendsTaskDetail(
			AddFriendsTaskDetailDto addFriendsTaskDetailDto)
			throws TsfaServiceException {
		logger.debug("updateAddFriendsTaskDetail(AddFriendsTaskDetailDto addFriendsTaskDetailDto={}) - start", addFriendsTaskDetailDto); 

		AssertUtils.notNull(addFriendsTaskDetailDto);
		AssertUtils.notNullAndEmpty(addFriendsTaskDetailDto.getCode(),"Code不能为空");
		try {
			AddFriendsTaskDetail addFriendsTaskDetail = new AddFriendsTaskDetail();
			//update数据录入
			addFriendsTaskDetail.setCode(addFriendsTaskDetailDto.getCode());
			addFriendsTaskDetail.setTaskCode(addFriendsTaskDetailDto.getTaskCode());
			addFriendsTaskDetail.setPhone(addFriendsTaskDetailDto.getPhone());
			addFriendsTaskDetail.setUsername(addFriendsTaskDetailDto.getUsername());
			addFriendsTaskDetail.setSendMessage(addFriendsTaskDetailDto.getSendMessage());
			addFriendsTaskDetail.setExecuteDate(addFriendsTaskDetailDto.getExecuteDate());
			addFriendsTaskDetail.setAddfriendsDate(addFriendsTaskDetailDto.getAddfriendsDate());
			addFriendsTaskDetail.setCallbackDate(addFriendsTaskDetailDto.getCallbackDate());
			addFriendsTaskDetail.setNoWx(addFriendsTaskDetailDto.getNoWx());
			addFriendsTaskDetail.setNoWxGm(addFriendsTaskDetailDto.getNoWxGm());
			addFriendsTaskDetail.setStatus(addFriendsTaskDetailDto.getStatus());
			addFriendsTaskDetail.setDetail(addFriendsTaskDetailDto.getDetail());
			AssertUtils.notUpdateMoreThanOne(addFriendsTaskDetailDao.updateByPrimaryKeySelective(addFriendsTaskDetail));
			logger.debug("updateAddFriendsTaskDetail(AddFriendsTaskDetailDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("加粉任务信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_UPDATE_ERROR,"加粉任务信息更新信息错误！",e);
		}
	}

	

	@Override
	public AddFriendsTaskDetailDto findAddFriendsTaskDetail(
			AddFriendsTaskDetailDto addFriendsTaskDetailDto) throws TsfaServiceException {
		logger.debug("findAddFriendsTaskDetail(FindAddFriendsTaskDetail findAddFriendsTaskDetail={}) - start", addFriendsTaskDetailDto); 

		AssertUtils.notNull(addFriendsTaskDetailDto);
		AssertUtils.notAllNull(addFriendsTaskDetailDto.getCode(),"Code不能为空");
		try {
			AddFriendsTaskDetail addFriendsTaskDetail = addFriendsTaskDetailDao.selectByPrimaryKey(addFriendsTaskDetailDto.getCode());
			if(addFriendsTaskDetail == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_NOT_EXIST_ERROR,"加粉任务信息不存在");
			}
			AddFriendsTaskDetailDto findAddFriendsTaskDetailReturn = new AddFriendsTaskDetailDto();
			//find数据录入
			findAddFriendsTaskDetailReturn.setCode(addFriendsTaskDetail.getCode());
			findAddFriendsTaskDetailReturn.setTaskCode(addFriendsTaskDetail.getTaskCode());
			findAddFriendsTaskDetailReturn.setPhone(addFriendsTaskDetail.getPhone());
			findAddFriendsTaskDetailReturn.setUsername(addFriendsTaskDetail.getUsername());
			findAddFriendsTaskDetailReturn.setSendMessage(addFriendsTaskDetail.getSendMessage());
			findAddFriendsTaskDetailReturn.setCreateDate(addFriendsTaskDetail.getCreateDate());
			findAddFriendsTaskDetailReturn.setExecuteDate(addFriendsTaskDetail.getExecuteDate());
			findAddFriendsTaskDetailReturn.setAddfriendsDate(addFriendsTaskDetail.getAddfriendsDate());
			findAddFriendsTaskDetailReturn.setCallbackDate(addFriendsTaskDetail.getCallbackDate());
			findAddFriendsTaskDetailReturn.setNoWx(addFriendsTaskDetail.getNoWx());
			findAddFriendsTaskDetailReturn.setNoWxGm(addFriendsTaskDetail.getNoWxGm());
			findAddFriendsTaskDetailReturn.setStatus(addFriendsTaskDetail.getStatus());
			findAddFriendsTaskDetailReturn.setDetail(addFriendsTaskDetail.getDetail());
			findAddFriendsTaskDetailReturn.setMerchantNo(addFriendsTaskDetail.getMerchantNo());
			findAddFriendsTaskDetailReturn.setMerchantName(addFriendsTaskDetail.getMerchantName());
			
			logger.debug("findAddFriendsTaskDetail(AddFriendsTaskDetailDto) - end - return value={}", findAddFriendsTaskDetailReturn); 
			return findAddFriendsTaskDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找加粉任务信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_FIND_ERROR,"查找加粉任务信息信息错误！",e);
		}


	}

	
	@Override
	public Page<AddFriendsTaskDetailDto> findAddFriendsTaskDetailPage(
			FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage)
			throws TsfaServiceException {
		logger.debug("findAddFriendsTaskDetailPage(FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage={}) - start", findAddFriendsTaskDetailPage); 

		AssertUtils.notNull(findAddFriendsTaskDetailPage);
		List<AddFriendsTaskDetailDto> returnList=null;
		int count = 0;
		try {
			count = addFriendsTaskDetailDao.findAddFriendsTaskDetailPageCount(findAddFriendsTaskDetailPage);
			if(count>0) {
				returnList = addFriendsTaskDetailDao.findAddFriendsTaskDetailPage(findAddFriendsTaskDetailPage);
			}
		}  catch (Exception e) {
			logger.error("加粉任务信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_FIND_PAGE_ERROR,"加粉任务信息不存在错误.！",e);
		}
		Page<AddFriendsTaskDetailDto> returnPage = new Page<AddFriendsTaskDetailDto>(returnList, count, findAddFriendsTaskDetailPage);

		logger.debug("findAddFriendsTaskDetailPage(FindAddFriendsTaskDetailPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	@Override
	public void updateByCond(AddFriendsTaskDetailDto addFriendsTaskDetailDto) throws TsfaServiceException {
		logger.debug("updateStatus(AddFriendsTaskDetailDto addFriendsTaskDetailDto={}) - start", addFriendsTaskDetailDto); 
//		AssertUtils.notNullAndEmpty(addFriendsTaskDetailDto.getStatus(),"状态不能为空");
		if(StringUtils.isEmpty(addFriendsTaskDetailDto.getTaskCode())) {
			AssertUtils.notNullAndEmpty(addFriendsTaskDetailDto.getCodes(),"任务编号和编号集合不能全部为空");
		}
		
		try {
			addFriendsTaskDetailDao.updateStatus(addFriendsTaskDetailDto);
			logger.debug("updateStatus() - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("加粉任务信息更新状态错误！",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_UPDATE_ERROR,"加粉任务信息更新状态错误！",e);
		}
	}


	@Override
	public AddFriendsTaskDetailDto findByCond(AddFriendsTaskDetailDto addFriendsTaskDetailDto)
			throws TsfaServiceException {
		logger.debug("findByCond(FindAddFriendsTaskDetail findAddFriendsTaskDetail={}) - start", addFriendsTaskDetailDto); 

		AssertUtils.notNull(addFriendsTaskDetailDto);
		try {
			AddFriendsTaskDetail addFriendsTaskDetail = addFriendsTaskDetailDao.selectByCond(addFriendsTaskDetailDto);
			if(addFriendsTaskDetail == null){
				return null;
			}
			AddFriendsTaskDetailDto findAddFriendsTaskDetailReturn = new AddFriendsTaskDetailDto();
			//find数据录入
			findAddFriendsTaskDetailReturn.setCode(addFriendsTaskDetail.getCode());
			findAddFriendsTaskDetailReturn.setTaskCode(addFriendsTaskDetail.getTaskCode());
			findAddFriendsTaskDetailReturn.setPhone(addFriendsTaskDetail.getPhone());
			findAddFriendsTaskDetailReturn.setUsername(addFriendsTaskDetail.getUsername());
			findAddFriendsTaskDetailReturn.setSendMessage(addFriendsTaskDetail.getSendMessage());
			findAddFriendsTaskDetailReturn.setCreateDate(addFriendsTaskDetail.getCreateDate());
			findAddFriendsTaskDetailReturn.setExecuteDate(addFriendsTaskDetail.getExecuteDate());
			findAddFriendsTaskDetailReturn.setAddfriendsDate(addFriendsTaskDetail.getAddfriendsDate());
			findAddFriendsTaskDetailReturn.setCallbackDate(addFriendsTaskDetail.getCallbackDate());
			findAddFriendsTaskDetailReturn.setNoWx(addFriendsTaskDetail.getNoWx());
			findAddFriendsTaskDetailReturn.setNoWxGm(addFriendsTaskDetail.getNoWxGm());
			findAddFriendsTaskDetailReturn.setStatus(addFriendsTaskDetail.getStatus());
			findAddFriendsTaskDetailReturn.setDetail(addFriendsTaskDetail.getDetail());
			findAddFriendsTaskDetailReturn.setMerchantNo(addFriendsTaskDetail.getMerchantNo());
			findAddFriendsTaskDetailReturn.setMerchantName(addFriendsTaskDetail.getMerchantName());
			
			logger.debug("findByCond(AddFriendsTaskDetailDto) - end - return value={}", findAddFriendsTaskDetailReturn); 
			return findAddFriendsTaskDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找加粉任务信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_FIND_ERROR,"查找加粉任务信息信息错误！",e);
		}
	}


	@Override
	public int insertForeach(List<AddFriendsTaskDetailDto> record) throws TsfaServiceException {
		logger.debug("insertForeach(List<AddFriendsTaskDetailDto> record={}) - start", record); 
		AssertUtils.notNullAndEmpty(record,"批量插入集合不能为空");
		int count =0;
		try {
			count = addFriendsTaskDetailDao.insertForeach(record);
			logger.debug("insertForeach(record) - end - return={}",count); 
		}
		catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {			logger.error("新增加粉任务信息错误！",e);
			if(e.getCause() instanceof MySQLIntegrityConstraintViolationException) {
				String message = "";
				if(e.getMessage().contains("'")) {
					int first =e.getMessage().indexOf("'")+1;
					if(first>0) {
						message = e.getMessage().substring(first, e.getMessage().indexOf("'",first));
					}
				}
				throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_ADD_REPEAT_ERROR,"手机号重复："+message,e);
			}
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_ADD_ERROR,"新增加粉任务信息错误！",e);
		}
		return count;
	}


	@Override
	public int deleteByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("deleteByPrimaryKey(String code={}) - start", code); 
		AssertUtils.notNullAndEmpty(code,"编号不能为空");
		int count =0;
		try {
			count = addFriendsTaskDetailDao.deleteByPrimaryKey(code);
			logger.debug("insertForeach(record) - end - return={}",count); 
		}
		catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {			
			logger.error("删除加粉任务信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_ADD_ERROR,"删除加粉任务信息错误！",e);
		}
		return count;
	}


	@Override
	public List<AddFriendsTaskDetailDto> findJobResult(int num) throws TsfaServiceException {
		logger.debug("findJobResult(int num={}) - start", num); 
		AssertUtils.notNullAndEmpty(num,"数量不能为空");
		List<AddFriendsTaskDetailDto> list =null;
		try {
			list =addFriendsTaskDetailDao.findJobResult(num);
			logger.debug("findJobResult(num) - end - return={}",list); 
		}
		catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {			
			logger.error("查询加粉定时任务信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ADDFRIENDS_TASK_DETAIL_ADD_ERROR,"查询加粉定时任务信息错误！",e);
		}
		return list;
		
	}

}
