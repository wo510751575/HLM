package com.ye.business.ad.service.impl;

/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindMemberLoginInfoReturn;
import com.lj.business.member.service.IMemberLoginInfoService;
import com.lj.messagecenter.msg.dto.AddNotifyInfo;
import com.lj.messagecenter.msg.enums.MsgSendType;
import com.lj.messagecenter.msg.enums.MsgSystemType;
import com.lj.messagecenter.msg.service.INotifyService;
import com.ye.business.ad.constant.ErrorCodeRwUserBeans;
import com.ye.business.ad.dao.IRwUserBeansDao;
import com.ye.business.ad.domain.RwUserBeans;
import com.ye.business.ad.dto.FindRwUserBeansPage;
import com.ye.business.ad.dto.RwUserBeansDto;
import com.ye.business.ad.service.IRwUserBeansService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author sjiying
 * 
 * 
 * CreateDate: 2019.04.18
 */
@Service
public class RwUserBeansServiceImpl implements IRwUserBeansService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(RwUserBeansServiceImpl.class);

	@Resource
	private IRwUserBeansDao rwUserBeansDao;
	
	@Autowired
	private INotifyService notifyService;
	@Autowired
	private IMemberLoginInfoService memberLoginInfoService;

	@Override
	public String addRwUserBeans(RwUserBeansDto rwUserBeansDto) throws TsfaServiceException {
		logger.debug("addRwUserBeans(AddRwUserBeans addRwUserBeans={}) - start", rwUserBeansDto);

		AssertUtils.notNull(rwUserBeansDto);
		try {
			RwUserBeans rwUserBeans = new RwUserBeans();
			// add数据录入
//			rwUserBeans.setCode(GUID.getPreUUID());
			rwUserBeans.setCode(rwUserBeansDto.getCode());
			rwUserBeans.setMemberNo(rwUserBeansDto.getMemberNo());
			rwUserBeans.setMemberName(rwUserBeansDto.getMemberName());
			rwUserBeans.setShopNo(rwUserBeansDto.getShopNo());
			rwUserBeans.setShopName(rwUserBeansDto.getShopName());
			rwUserBeans.setMerchantNo(rwUserBeansDto.getMerchantNo());
			rwUserBeans.setMerchantName(rwUserBeansDto.getMerchantName());
			rwUserBeans.setBeansSum(rwUserBeansDto.getBeansSum());
			rwUserBeans.setBeansUse(rwUserBeansDto.getBeansUse());
			rwUserBeans.setBeansFreeze(rwUserBeansDto.getBeansFreeze());
			rwUserBeans.setBeansNormal(rwUserBeansDto.getBeansNormal());
			rwUserBeans.setCreateId(rwUserBeansDto.getCreateId());
			rwUserBeans.setCreateName(rwUserBeansDto.getCreateName());
			rwUserBeans.setCreateDate(rwUserBeansDto.getCreateDate());
			rwUserBeans.setUpdateId(rwUserBeansDto.getUpdateId());
			rwUserBeans.setUpdateName(rwUserBeansDto.getUpdateName());
			rwUserBeans.setUpdateDate(rwUserBeansDto.getUpdateDate());
			rwUserBeans.setRemark(rwUserBeansDto.getRemark());
			rwUserBeans.setRemark2(rwUserBeansDto.getRemark2());
			rwUserBeans.setRemark3(rwUserBeansDto.getRemark3());
			rwUserBeans.setRemark4(rwUserBeansDto.getRemark4());
			rwUserBeansDao.insertSelective(rwUserBeans);
			logger.debug("addRwUserBeans(RwUserBeansDto) - end - return");
			return rwUserBeans.getCode();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增用户豆子记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_ADD_ERROR, "新增用户豆子记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询用户豆子记录信息
	 *
	 * @param findRwUserBeansPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<RwUserBeansDto> findRwUserBeanss(FindRwUserBeansPage findRwUserBeansPage) throws TsfaServiceException {
		AssertUtils.notNull(findRwUserBeansPage);
		List<RwUserBeansDto> returnList = null;
		try {
			returnList = rwUserBeansDao.findRwUserBeanss(findRwUserBeansPage);
		} catch (Exception e) {
			logger.error("查找用户豆子记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_NOT_EXIST_ERROR, "用户豆子记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateRwUserBeans(RwUserBeansDto rwUserBeansDto) throws TsfaServiceException {
		logger.debug("updateRwUserBeans(RwUserBeansDto rwUserBeansDto={}) - start", rwUserBeansDto);

		AssertUtils.notNull(rwUserBeansDto);
		AssertUtils.notNullAndEmpty(rwUserBeansDto.getCode(), "Code不能为空");
		try {
			RwUserBeans rwUserBeans = new RwUserBeans();
			// update数据录入
			rwUserBeans.setCode(rwUserBeansDto.getCode());
			rwUserBeans.setMemberNo(rwUserBeansDto.getMemberNo());
			rwUserBeans.setMemberName(rwUserBeansDto.getMemberName());
			rwUserBeans.setShopNo(rwUserBeansDto.getShopNo());
			rwUserBeans.setShopName(rwUserBeansDto.getShopName());
			rwUserBeans.setMerchantNo(rwUserBeansDto.getMerchantNo());
			rwUserBeans.setMerchantName(rwUserBeansDto.getMerchantName());
			rwUserBeans.setBeansSum(rwUserBeansDto.getBeansSum());
			rwUserBeans.setBeansUse(rwUserBeansDto.getBeansUse());
			rwUserBeans.setBeansFreeze(rwUserBeansDto.getBeansFreeze());
			rwUserBeans.setBeansNormal(rwUserBeansDto.getBeansNormal());
			rwUserBeans.setCreateId(rwUserBeansDto.getCreateId());
			rwUserBeans.setCreateName(rwUserBeansDto.getCreateName());
			rwUserBeans.setCreateDate(rwUserBeansDto.getCreateDate());
			rwUserBeans.setUpdateId(rwUserBeansDto.getUpdateId());
			rwUserBeans.setUpdateName(rwUserBeansDto.getUpdateName());
			rwUserBeans.setUpdateDate(rwUserBeansDto.getUpdateDate());
			rwUserBeans.setRemark(rwUserBeansDto.getRemark());
			rwUserBeans.setRemark2(rwUserBeansDto.getRemark2());
			rwUserBeans.setRemark3(rwUserBeansDto.getRemark3());
			rwUserBeans.setRemark4(rwUserBeansDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(rwUserBeansDao.updateByPrimaryKeySelective(rwUserBeans));
			logger.debug("updateRwUserBeans(RwUserBeansDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("用户豆子记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_UPDATE_ERROR, "用户豆子记录信息更新信息错误！", e);
		}
	}

	@Override
	public RwUserBeansDto findRwUserBeans(RwUserBeansDto rwUserBeansDto) throws TsfaServiceException {
		logger.debug("findRwUserBeans(FindRwUserBeans findRwUserBeans={}) - start", rwUserBeansDto);

		AssertUtils.notNull(rwUserBeansDto);
		AssertUtils.notAllNull(rwUserBeansDto.getCode(), "Code不能为空");
		try {
			RwUserBeans rwUserBeans = rwUserBeansDao.selectByPrimaryKey(rwUserBeansDto.getCode());
			if (rwUserBeans == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_NOT_EXIST_ERROR,"用户豆子记录信息不存在");
			}
			RwUserBeansDto findRwUserBeansReturn = new RwUserBeansDto();
			// find数据录入
			findRwUserBeansReturn.setCode(rwUserBeans.getCode());
			findRwUserBeansReturn.setMemberNo(rwUserBeans.getMemberNo());
			findRwUserBeansReturn.setMemberName(rwUserBeans.getMemberName());
			findRwUserBeansReturn.setShopNo(rwUserBeans.getShopNo());
			findRwUserBeansReturn.setShopName(rwUserBeans.getShopName());
			findRwUserBeansReturn.setMerchantNo(rwUserBeans.getMerchantNo());
			findRwUserBeansReturn.setMerchantName(rwUserBeans.getMerchantName());
			findRwUserBeansReturn.setBeansSum(rwUserBeans.getBeansSum());
			findRwUserBeansReturn.setBeansUse(rwUserBeans.getBeansUse());
			findRwUserBeansReturn.setBeansFreeze(rwUserBeans.getBeansFreeze());
			findRwUserBeansReturn.setBeansNormal(rwUserBeans.getBeansNormal());
			findRwUserBeansReturn.setCreateId(rwUserBeans.getCreateId());
			findRwUserBeansReturn.setCreateName(rwUserBeans.getCreateName());
			findRwUserBeansReturn.setCreateDate(rwUserBeans.getCreateDate());
			findRwUserBeansReturn.setUpdateId(rwUserBeans.getUpdateId());
			findRwUserBeansReturn.setUpdateName(rwUserBeans.getUpdateName());
			findRwUserBeansReturn.setUpdateDate(rwUserBeans.getUpdateDate());
			findRwUserBeansReturn.setRemark(rwUserBeans.getRemark());
			findRwUserBeansReturn.setRemark2(rwUserBeans.getRemark2());
			findRwUserBeansReturn.setRemark3(rwUserBeans.getRemark3());
			findRwUserBeansReturn.setRemark4(rwUserBeans.getRemark4());

			logger.debug("findRwUserBeans(RwUserBeansDto) - end - return value={}", findRwUserBeansReturn);
			return findRwUserBeansReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找用户豆子记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_FIND_ERROR, "查找用户豆子记录信息信息错误！", e);
		}

	}

	@Override
	public Page<RwUserBeansDto> findRwUserBeansPage(FindRwUserBeansPage findRwUserBeansPage) throws TsfaServiceException {
		logger.debug("findRwUserBeansPage(FindRwUserBeansPage findRwUserBeansPage={}) - start", findRwUserBeansPage);

		AssertUtils.notNull(findRwUserBeansPage);
		List<RwUserBeansDto> returnList = null;
		int count = 0;
		try {
			returnList = rwUserBeansDao.findRwUserBeansPage(findRwUserBeansPage);
			count = rwUserBeansDao.findRwUserBeansPageCount(findRwUserBeansPage);
		} catch (Exception e) {
			logger.error("用户豆子记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_FIND_PAGE_ERROR, "用户豆子记录信息不存在错误.！", e);
		}
		Page<RwUserBeansDto> returnPage = new Page<RwUserBeansDto>(returnList, count, findRwUserBeansPage);

		logger.debug("findRwUserBeansPage(FindRwUserBeansPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void removeByPrimaryKey(String code) throws TsfaServiceException {
		logger.debug("removeByPrimaryKey(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "Code不能为空");
		try {

			AssertUtils.notUpdateMoreThanOne(rwUserBeansDao.deleteByPrimaryKey(code));
			logger.debug("removeByPrimaryKey(code) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("用户豆子记录信息刪除信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_UPDATE_ERROR, "用户豆子记录信息刪除信息错误！", e);
		}
	}

	@Override
	public RwUserBeansDto findRwUserBeans(String memberNo) throws TsfaServiceException {
		logger.debug("findRwUserBeans(String memberNo={}) - start", memberNo);

		AssertUtils.notAllNull(memberNo, "memberNo不能为空");
		try {
			RwUserBeans rwUserBeans = rwUserBeansDao.selectByMemberNo(memberNo);
			if (rwUserBeans == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_NOT_EXIST_ERROR,"用户豆子记录信息不存在");
			}
			RwUserBeansDto findRwUserBeansReturn = new RwUserBeansDto();
			// find数据录入
			findRwUserBeansReturn.setCode(rwUserBeans.getCode());
			findRwUserBeansReturn.setMemberNo(rwUserBeans.getMemberNo());
			findRwUserBeansReturn.setMemberName(rwUserBeans.getMemberName());
			findRwUserBeansReturn.setShopNo(rwUserBeans.getShopNo());
			findRwUserBeansReturn.setShopName(rwUserBeans.getShopName());
			findRwUserBeansReturn.setMerchantNo(rwUserBeans.getMerchantNo());
			findRwUserBeansReturn.setMerchantName(rwUserBeans.getMerchantName());
			findRwUserBeansReturn.setBeansSum(rwUserBeans.getBeansSum());
			findRwUserBeansReturn.setBeansUse(rwUserBeans.getBeansUse());
			findRwUserBeansReturn.setBeansFreeze(rwUserBeans.getBeansFreeze());
			findRwUserBeansReturn.setBeansNormal(rwUserBeans.getBeansNormal());
			findRwUserBeansReturn.setCreateId(rwUserBeans.getCreateId());
			findRwUserBeansReturn.setCreateName(rwUserBeans.getCreateName());
			findRwUserBeansReturn.setCreateDate(rwUserBeans.getCreateDate());
			findRwUserBeansReturn.setUpdateId(rwUserBeans.getUpdateId());
			findRwUserBeansReturn.setUpdateName(rwUserBeans.getUpdateName());
			findRwUserBeansReturn.setUpdateDate(rwUserBeans.getUpdateDate());
			findRwUserBeansReturn.setRemark(rwUserBeans.getRemark());
			findRwUserBeansReturn.setRemark2(rwUserBeans.getRemark2());
			findRwUserBeansReturn.setRemark3(rwUserBeans.getRemark3());
			findRwUserBeansReturn.setRemark4(rwUserBeans.getRemark4());

			logger.debug("findRwUserBeans(RwUserBeansDto) - end - return value={}", findRwUserBeansReturn);
			return findRwUserBeansReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找用户豆子记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_FIND_ERROR, "查找用户豆子记录信息信息错误！", e);
		}
	}
	
	@Override
	public void updateIncreaseAmountByPrimaryKey(RwUserBeansDto record) throws TsfaServiceException {
		logger.debug("updateIncreaseAmountByPrimaryKey(RwUserBeansDto record={}) - start", record);

		AssertUtils.notNull(record);
		AssertUtils.notNullAndEmpty(record.getCode(), "Code不能为空");
		try {
			AssertUtils.notUpdateMoreThanOne(rwUserBeansDao.updateIncreaseAmountByPrimaryKey(record));
			logger.debug("updateRwUserBeans(RwUserBeansDto) - end - return");
			
			try {

				RwUserBeans data = rwUserBeansDao.selectByPrimaryKey(record.getCode());

				// 查询导购最后一次登录信息
				FindMemberLoginInfoReturn findMemberLoginInfoReturn = memberLoginInfoService.findLastMemberLoginInfo(data.getCode());

				// 通知客户端变更记录
				AddNotifyInfo addNotifyInfo = new AddNotifyInfo();

				addNotifyInfo.setMerchantNo(data.getMerchantNo());
				addNotifyInfo.setMemberNo(data.getMemberNo());
				addNotifyInfo.setMemberName(data.getMemberName());
				addNotifyInfo.setMemberType("GUID");
//				addNotifyInfo.setTag("");
				addNotifyInfo.setSysType(MsgSystemType.IOS.toString());
				addNotifyInfo.setSendType(MsgSendType.SINGLE.toString());
//				addNotifyInfo.setMemberNoSender(data.getMemberNo());
//				addNotifyInfo.setMemberNameSender(data.getMemberName());
//				addNotifyInfo.setMemberTypeSender(MsgType.NOTIFY.toString());
//				addNotifyInfo.setTitle(addNotifyInfo.getTitle());

				Map<String, Integer> dataBeans = Maps.newHashMap();
				dataBeans.put("beansNormal", data.getBeansNormal());
				dataBeans.put("beansFreeze", data.getBeansFreeze());
//				addNotifyInfo.setContent(JSON.toJSONString(dataBeans));
				
				String content = String.format("用户正常积分：%d;冻结积分：%d", data.getBeansNormal(), data.getBeansFreeze());
				addNotifyInfo.setContent(content);
				
//				addNotifyInfo.setStatus(addNotifyInfo.getStatus());
				addNotifyInfo.setRemark(JSON.toJSONString(dataBeans));
				addNotifyInfo.setMobile(findMemberLoginInfoReturn.getMac());
				addNotifyInfo.setType("memberBeans");

				notifyService.sendMsgInfo(addNotifyInfo);
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			}
			
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("用户豆子记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUserBeans.RW_USER_BEANS_UPDATE_ERROR, "用户豆子记录信息更新信息错误！", e);
		}
	}
}
