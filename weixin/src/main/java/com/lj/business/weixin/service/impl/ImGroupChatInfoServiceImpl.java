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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.constant.ErrorCodeImGroupChatInfo;
import com.lj.business.weixin.dao.IImGroupChatInfoDao;
import com.lj.business.weixin.domain.ImGroupChatInfo;
import com.lj.business.weixin.dto.FindImGroupChatInfoPage;
import com.lj.business.weixin.dto.ImGroupChatInfoDto;
import com.lj.business.weixin.service.IImGroupChatInfoService;
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
public class ImGroupChatInfoServiceImpl implements IImGroupChatInfoService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ImGroupChatInfoServiceImpl.class);
	

	@Resource
	private IImGroupChatInfoDao imGroupChatInfoDao;
	
	
	@Override
	public String addImGroupChatInfo(ImGroupChatInfoDto imGroupChatInfoDto) throws TsfaServiceException {
		logger.debug("addImGroupChatInfo(AddImGroupChatInfo addImGroupChatInfo={}) - start", imGroupChatInfoDto); 

		AssertUtils.notNull(imGroupChatInfoDto);
		try {
			ImGroupChatInfo imGroupChatInfo = new ImGroupChatInfo();
			//add数据录入
			imGroupChatInfo.setCode(GUID.generateCode());
			imGroupChatInfo.setMerchantNo(imGroupChatInfoDto.getMerchantNo());
			imGroupChatInfo.setMerchantName(imGroupChatInfoDto.getMerchantName());
			imGroupChatInfo.setMemberNos(imGroupChatInfoDto.getMemberNos());
			imGroupChatInfo.setMemberNames(imGroupChatInfoDto.getMemberNames());
			imGroupChatInfo.setMemberNoWxs(imGroupChatInfoDto.getMemberNoWxs());
//			imGroupChatInfo.setShopNo(imGroupChatInfoDto.getShopNo());
//			imGroupChatInfo.setShopName(imGroupChatInfoDto.getShopName());
			imGroupChatInfo.setMemberNoGm(imGroupChatInfoDto.getMemberNoGm());
			imGroupChatInfo.setMemberNameGm(imGroupChatInfoDto.getMemberNameGm());
			imGroupChatInfo.setNoWxGm(imGroupChatInfoDto.getNoWxGm());
			imGroupChatInfo.setType(imGroupChatInfoDto.getType());
			imGroupChatInfo.setStatus(imGroupChatInfoDto.getStatus());
			imGroupChatInfo.setResourcesPath(imGroupChatInfoDto.getResourcesPath());
			imGroupChatInfo.setChatroomType(imGroupChatInfoDto.getChatroomType());
			imGroupChatInfo.setChatroomNoWx(imGroupChatInfoDto.getChatroomNoWx());
			imGroupChatInfo.setChatAssistantCode(imGroupChatInfoDto.getChatAssistantCode());
			imGroupChatInfo.setCreateDate(new Date());
			imGroupChatInfo.setRemark(imGroupChatInfoDto.getRemark());
			imGroupChatInfo.setRemark2(imGroupChatInfoDto.getRemark2());
			imGroupChatInfo.setRemark3(imGroupChatInfoDto.getRemark3());
			imGroupChatInfo.setRemark4(imGroupChatInfoDto.getRemark4());
			imGroupChatInfo.setContent(imGroupChatInfoDto.getContent());
			imGroupChatInfoDao.insertSelective(imGroupChatInfo);
			logger.debug("addImGroupChatInfo(ImGroupChatInfoDto) - end - return={}",imGroupChatInfo.getCode()); 
			return imGroupChatInfo.getCode();
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增群发聊天设置信息错误！",e);
			throw new TsfaServiceException(ErrorCodeImGroupChatInfo.IM_GROUP_CHAT_INFO_ADD_ERROR,"新增群发聊天设置信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询群发聊天设置信息
	 *
	 * @param findImGroupChatInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<ImGroupChatInfoDto>  findImGroupChatInfos(FindImGroupChatInfoPage findImGroupChatInfoPage)throws TsfaServiceException{
		AssertUtils.notNull(findImGroupChatInfoPage);
		List<ImGroupChatInfoDto> returnList=null;
		try {
			returnList = imGroupChatInfoDao.findImGroupChatInfos(findImGroupChatInfoPage);
		} catch (Exception e) {
			logger.error("查找群发聊天设置信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImGroupChatInfo.IM_GROUP_CHAT_INFO_NOT_EXIST_ERROR,"群发聊天设置信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateImGroupChatInfo(
			ImGroupChatInfoDto imGroupChatInfoDto)
			throws TsfaServiceException {
		logger.debug("updateImGroupChatInfo(ImGroupChatInfoDto imGroupChatInfoDto={}) - start", imGroupChatInfoDto); 

		AssertUtils.notNull(imGroupChatInfoDto);
		AssertUtils.notNullAndEmpty(imGroupChatInfoDto.getCode(),"Code不能为空");
		try {
			ImGroupChatInfo imGroupChatInfo = new ImGroupChatInfo();
			//update数据录入
			imGroupChatInfo.setCode(imGroupChatInfoDto.getCode());
			imGroupChatInfo.setMerchantNo(imGroupChatInfoDto.getMerchantNo());
			imGroupChatInfo.setMerchantName(imGroupChatInfoDto.getMerchantName());
			imGroupChatInfo.setMemberNos(imGroupChatInfoDto.getMemberNos());
			imGroupChatInfo.setMemberNames(imGroupChatInfoDto.getMemberNames());
			imGroupChatInfo.setMemberNoWxs(imGroupChatInfoDto.getMemberNoWxs());
//			imGroupChatInfo.setShopNo(imGroupChatInfoDto.getShopNo());
//			imGroupChatInfo.setShopName(imGroupChatInfoDto.getShopName());
			imGroupChatInfo.setMemberNoGm(imGroupChatInfoDto.getMemberNoGm());
			imGroupChatInfo.setMemberNameGm(imGroupChatInfoDto.getMemberNameGm());
			imGroupChatInfo.setNoWxGm(imGroupChatInfoDto.getNoWxGm());
			imGroupChatInfo.setType(imGroupChatInfoDto.getType());
			imGroupChatInfo.setStatus(imGroupChatInfoDto.getStatus());
			imGroupChatInfo.setResourcesPath(imGroupChatInfoDto.getResourcesPath());
			imGroupChatInfo.setChatroomType(imGroupChatInfoDto.getChatroomType());
			imGroupChatInfo.setChatroomNoWx(imGroupChatInfoDto.getChatroomNoWx());
			imGroupChatInfo.setChatAssistantCode(imGroupChatInfoDto.getChatAssistantCode());
			imGroupChatInfo.setRemark(imGroupChatInfoDto.getRemark());
			imGroupChatInfo.setRemark2(imGroupChatInfoDto.getRemark2());
			imGroupChatInfo.setRemark3(imGroupChatInfoDto.getRemark3());
			imGroupChatInfo.setRemark4(imGroupChatInfoDto.getRemark4());
			imGroupChatInfo.setContent(imGroupChatInfoDto.getContent());
			AssertUtils.notUpdateMoreThanOne(imGroupChatInfoDao.updateByPrimaryKeySelective(imGroupChatInfo));
			logger.debug("updateImGroupChatInfo(ImGroupChatInfoDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("群发聊天设置信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCodeImGroupChatInfo.IM_GROUP_CHAT_INFO_UPDATE_ERROR,"群发聊天设置信息更新信息错误！",e);
		}
	}

	

	@Override
	public ImGroupChatInfoDto findImGroupChatInfo(
			ImGroupChatInfoDto imGroupChatInfoDto) throws TsfaServiceException {
		logger.debug("findImGroupChatInfo(FindImGroupChatInfo findImGroupChatInfo={}) - start", imGroupChatInfoDto); 

		AssertUtils.notNull(imGroupChatInfoDto);
		AssertUtils.notAllNull(imGroupChatInfoDto.getCode(),"Code不能为空");
		try {
			ImGroupChatInfo imGroupChatInfo = imGroupChatInfoDao.selectByPrimaryKey(imGroupChatInfoDto.getCode());
			if(imGroupChatInfo == null){
				return null;
				//throw new TsfaServiceException(ErrorCodeImGroupChatInfo.IM_GROUP_CHAT_INFO_NOT_EXIST_ERROR,"群发聊天设置信息不存在");
			}
			ImGroupChatInfoDto findImGroupChatInfoReturn = new ImGroupChatInfoDto();
			//find数据录入
			findImGroupChatInfoReturn.setCode(imGroupChatInfo.getCode());
			findImGroupChatInfoReturn.setMerchantNo(imGroupChatInfo.getMerchantNo());
			findImGroupChatInfoReturn.setMerchantName(imGroupChatInfo.getMerchantName());
			findImGroupChatInfoReturn.setMemberNos(imGroupChatInfo.getMemberNos());
			findImGroupChatInfoReturn.setMemberNames(imGroupChatInfo.getMemberNames());
			findImGroupChatInfoReturn.setMemberNoWxs(imGroupChatInfo.getMemberNoWxs());
//			findImGroupChatInfoReturn.setShopNo(imGroupChatInfo.getShopNo());
//			findImGroupChatInfoReturn.setShopName(imGroupChatInfo.getShopName());
			findImGroupChatInfoReturn.setMemberNoGm(imGroupChatInfo.getMemberNoGm());
			findImGroupChatInfoReturn.setMemberNameGm(imGroupChatInfo.getMemberNameGm());
			findImGroupChatInfoReturn.setNoWxGm(imGroupChatInfo.getNoWxGm());
			findImGroupChatInfoReturn.setType(imGroupChatInfo.getType());
			findImGroupChatInfoReturn.setStatus(imGroupChatInfo.getStatus());
			findImGroupChatInfoReturn.setResourcesPath(imGroupChatInfo.getResourcesPath());
			findImGroupChatInfoReturn.setChatroomType(imGroupChatInfo.getChatroomType());
			findImGroupChatInfoReturn.setChatroomNoWx(imGroupChatInfo.getChatroomNoWx());
			findImGroupChatInfoReturn.setChatAssistantCode(imGroupChatInfo.getChatAssistantCode());
			findImGroupChatInfoReturn.setCreateDate(imGroupChatInfo.getCreateDate());
			findImGroupChatInfoReturn.setRemark(imGroupChatInfo.getRemark());
			findImGroupChatInfoReturn.setRemark2(imGroupChatInfo.getRemark2());
			findImGroupChatInfoReturn.setRemark3(imGroupChatInfo.getRemark3());
			findImGroupChatInfoReturn.setRemark4(imGroupChatInfo.getRemark4());
			findImGroupChatInfoReturn.setContent(imGroupChatInfo.getContent());
			
			logger.debug("findImGroupChatInfo(ImGroupChatInfoDto) - end - return value={}", findImGroupChatInfoReturn); 
			return findImGroupChatInfoReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找群发聊天设置信息信息错误！",e);
			throw new TsfaServiceException(ErrorCodeImGroupChatInfo.IM_GROUP_CHAT_INFO_FIND_ERROR,"查找群发聊天设置信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ImGroupChatInfoDto> findImGroupChatInfoPage(
			FindImGroupChatInfoPage findImGroupChatInfoPage)
			throws TsfaServiceException {
		logger.debug("findImGroupChatInfoPage(FindImGroupChatInfoPage findImGroupChatInfoPage={}) - start", findImGroupChatInfoPage); 

		AssertUtils.notNull(findImGroupChatInfoPage);
		List<ImGroupChatInfoDto> returnList=null;
		int count = 0;
		try {
			count = imGroupChatInfoDao.findImGroupChatInfoPageCount(findImGroupChatInfoPage);
			if(count>0) {
				returnList = imGroupChatInfoDao.findImGroupChatInfoPage(findImGroupChatInfoPage);
			}
			
		}  catch (Exception e) {
			logger.error("群发聊天设置信息不存在错误",e);
			throw new TsfaServiceException(ErrorCodeImGroupChatInfo.IM_GROUP_CHAT_INFO_FIND_PAGE_ERROR,"群发聊天设置信息不存在错误.！",e);
		}
		Page<ImGroupChatInfoDto> returnPage = new Page<ImGroupChatInfoDto>(returnList, count, findImGroupChatInfoPage);

		logger.debug("findImGroupChatInfoPage(FindImGroupChatInfoPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	
	@Override
	public void delete(ImGroupChatInfoDto imGroupChatInfoDto) {
		imGroupChatInfoDao.delete(imGroupChatInfoDto);
		
	} 


}
