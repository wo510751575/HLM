package com.ye.business.rw.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.SystemParamConstant;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.RedisCache;
import com.lj.kms.service.IEncryptor;
import com.ye.business.rw.constant.ErrorCodeRwUser;
import com.ye.business.rw.constant.SmsCodeConstant;
import com.ye.business.rw.dao.IRwUserDao;
import com.ye.business.rw.domain.RwUser;
import com.ye.business.rw.dto.FindRwUserPage;
import com.ye.business.rw.dto.RwUserDto;
import com.ye.business.rw.enums.RwUserEnums;
import com.ye.business.rw.enums.Status;
import com.ye.business.rw.service.IRwUserService;

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
public class RwUserServiceImpl implements IRwUserService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(RwUserServiceImpl.class);

	@Resource
	private IRwUserDao rwUserDao;

	/** The local cache system params. */
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	/** The encryptor service. */
	@Resource
	private IEncryptor encryptorService;

	@Autowired
	private RedisCache redisCache; // 记录并区分添加方式

	@Override
	public String addRwUser(RwUserDto rwUserDto) throws TsfaServiceException {
		logger.debug("addRwUser(AddRwUser addRwUser={}) - start", rwUserDto);

		AssertUtils.notNull(rwUserDto);
		try {
			RwUser rwUser = new RwUser();
			// add数据录入
			rwUser.setCode(GUID.getPreUUID());
			rwUser.setMemberNoGuid(rwUserDto.getMemberNoGuid());
			rwUser.setMemberNameGuid(rwUserDto.getMemberNameGuid());
			rwUser.setShopNo(rwUserDto.getShopNo());
			rwUser.setShopName(rwUserDto.getShopName());
			rwUser.setMerchantNo(rwUserDto.getMerchantNo());
			rwUser.setMerchantName(rwUserDto.getMerchantName());
			rwUser.setStatus(rwUserDto.getStatus());
			rwUser.setUserLevel(rwUserDto.getUserLevel());
			rwUser.setMobile(rwUserDto.getMobile());
			rwUser.setImei(rwUserDto.getImei());
			rwUser.setEmail(rwUserDto.getEmail());
			rwUser.setNickName(rwUserDto.getNickName());
			rwUser.setAreaCode(rwUserDto.getAreaCode());
			rwUser.setAreaName(rwUserDto.getAreaName());
			rwUser.setProvinceCode(rwUserDto.getProvinceCode());
			rwUser.setCityCode(rwUserDto.getCityCode());
			rwUser.setCityAreaCode(rwUserDto.getCityAreaCode());
			rwUser.setAddress(rwUserDto.getAddress());
			rwUser.setAge(rwUserDto.getAge());
			rwUser.setGender(rwUserDto.getGender());
			rwUser.setLogoAddr(rwUserDto.getLogoAddr());
			rwUser.setWebsiteUrl(rwUserDto.getWebsiteUrl());
			rwUser.setCompanyId(rwUserDto.getCompanyId());
			rwUser.setCompanyName(rwUserDto.getCompanyName());
			rwUser.setLoginName(rwUserDto.getLoginName());
			rwUser.setPwd(rwUserDto.getPwd());
			rwUser.setEncryptionCode(rwUserDto.getEncryptionCode());
			rwUser.setBirthday(rwUserDto.getBirthday());
			rwUser.setHeadAddress(rwUserDto.getHeadAddress());
			rwUser.setQcord(rwUserDto.getQcord());
			rwUser.setNoWx(rwUserDto.getNoWx());
			rwUser.setNoWxPersonal(rwUserDto.getNoWxPersonal());
			rwUser.setLoginWxOpenid(rwUserDto.getLoginWxOpenid());
			rwUser.setCreateId(rwUserDto.getCreateId());
			rwUser.setCreateDate(rwUserDto.getCreateDate());
			rwUser.setUpdateId(rwUserDto.getUpdateId());
			rwUser.setUpdateDate(rwUserDto.getUpdateDate());
			rwUser.setRemark(rwUserDto.getRemark());
			rwUser.setRemark2(rwUserDto.getRemark2());
			rwUser.setRemark3(rwUserDto.getRemark3());
			rwUser.setRemark4(rwUserDto.getRemark4());
			rwUserDao.insertSelective(rwUser);
			logger.debug("addRwUser(RwUserDto) - end - return");
			return rwUser.getCode();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增热文用户记录信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_ADD_ERROR, "新增热文用户记录信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询热文用户记录信息
	 *
	 * @param findRwUserPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019年04月18日
	 *
	 */
	public List<RwUserDto> findRwUsers(FindRwUserPage findRwUserPage) throws TsfaServiceException {
		AssertUtils.notNull(findRwUserPage);
		List<RwUserDto> returnList = null;
		try {
			returnList = rwUserDao.findRwUsers(findRwUserPage);
		} catch (Exception e) {
			logger.error("查找热文用户记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_NOT_EXIST_ERROR, "热文用户记录信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateRwUser(RwUserDto rwUserDto) throws TsfaServiceException {
		logger.debug("updateRwUser(RwUserDto rwUserDto={}) - start", rwUserDto);

		AssertUtils.notNull(rwUserDto);
		AssertUtils.notNullAndEmpty(rwUserDto.getCode(), "Code不能为空");
		try {
			RwUser rwUser = new RwUser();
			// update数据录入
			rwUser.setCode(rwUserDto.getCode());
			rwUser.setMemberNoGuid(rwUserDto.getMemberNoGuid());
			rwUser.setMemberNameGuid(rwUserDto.getMemberNameGuid());
			rwUser.setShopNo(rwUserDto.getShopNo());
			rwUser.setShopName(rwUserDto.getShopName());
			rwUser.setMerchantNo(rwUserDto.getMerchantNo());
			rwUser.setMerchantName(rwUserDto.getMerchantName());
			rwUser.setStatus(rwUserDto.getStatus());
			rwUser.setUserLevel(rwUserDto.getUserLevel());
			rwUser.setMobile(rwUserDto.getMobile());
			rwUser.setImei(rwUserDto.getImei());
			rwUser.setEmail(rwUserDto.getEmail());
			rwUser.setNickName(rwUserDto.getNickName());
			rwUser.setAreaCode(rwUserDto.getAreaCode());
			rwUser.setAreaName(rwUserDto.getAreaName());
			rwUser.setProvinceCode(rwUserDto.getProvinceCode());
			rwUser.setCityCode(rwUserDto.getCityCode());
			rwUser.setCityAreaCode(rwUserDto.getCityAreaCode());
			rwUser.setAddress(rwUserDto.getAddress());
			rwUser.setAge(rwUserDto.getAge());
			rwUser.setGender(rwUserDto.getGender());
			rwUser.setLogoAddr(rwUserDto.getLogoAddr());
			rwUser.setWebsiteUrl(rwUserDto.getWebsiteUrl());
			rwUser.setCompanyId(rwUserDto.getCompanyId());
			rwUser.setCompanyName(rwUserDto.getCompanyName());
			rwUser.setLoginName(rwUserDto.getLoginName());
			rwUser.setPwd(rwUserDto.getPwd());
			rwUser.setEncryptionCode(rwUserDto.getEncryptionCode());
			rwUser.setBirthday(rwUserDto.getBirthday());
			rwUser.setHeadAddress(rwUserDto.getHeadAddress());
			rwUser.setQcord(rwUserDto.getQcord());
			rwUser.setNoWx(rwUserDto.getNoWx());
			rwUser.setNoWxPersonal(rwUserDto.getNoWxPersonal());
			rwUser.setLoginWxOpenid(rwUserDto.getLoginWxOpenid());
			rwUser.setCreateId(rwUserDto.getCreateId());
			rwUser.setCreateDate(rwUserDto.getCreateDate());
			rwUser.setUpdateId(rwUserDto.getUpdateId());
			rwUser.setUpdateDate(rwUserDto.getUpdateDate());
			rwUser.setRemark(rwUserDto.getRemark());
			rwUser.setRemark2(rwUserDto.getRemark2());
			rwUser.setRemark3(rwUserDto.getRemark3());
			rwUser.setRemark4(rwUserDto.getRemark4());
			AssertUtils.notUpdateMoreThanOne(rwUserDao.updateByPrimaryKeySelective(rwUser));
			logger.debug("updateRwUser(RwUserDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("热文用户记录信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_UPDATE_ERROR, "热文用户记录信息更新信息错误！", e);
		}
	}

	@Override
	public RwUserDto findRwUser(RwUserDto rwUserDto) throws TsfaServiceException {
		logger.debug("findRwUser(FindRwUser findRwUser={}) - start", rwUserDto);

		AssertUtils.notNull(rwUserDto);
		AssertUtils.notAllNull(rwUserDto.getCode(), "Code不能为空");
		try {
			RwUser rwUser = rwUserDao.selectByPrimaryKey(rwUserDto.getCode());
			if (rwUser == null) {
				return null;
				// throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_NOT_EXIST_ERROR,"热文用户记录信息不存在");
			}
			RwUserDto findRwUserReturn = new RwUserDto();
			// find数据录入
			findRwUserReturn.setCode(rwUser.getCode());
			findRwUserReturn.setMemberNoGuid(rwUser.getMemberNoGuid());
			findRwUserReturn.setMemberNameGuid(rwUser.getMemberNameGuid());
			findRwUserReturn.setShopNo(rwUser.getShopNo());
			findRwUserReturn.setShopName(rwUser.getShopName());
			findRwUserReturn.setMerchantNo(rwUser.getMerchantNo());
			findRwUserReturn.setMerchantName(rwUser.getMerchantName());
			findRwUserReturn.setStatus(rwUser.getStatus());
			findRwUserReturn.setUserLevel(rwUser.getUserLevel());
			findRwUserReturn.setMobile(rwUser.getMobile());
			findRwUserReturn.setImei(rwUser.getImei());
			findRwUserReturn.setEmail(rwUser.getEmail());
			findRwUserReturn.setNickName(rwUser.getNickName());
			findRwUserReturn.setAreaCode(rwUser.getAreaCode());
			findRwUserReturn.setAreaName(rwUser.getAreaName());
			findRwUserReturn.setProvinceCode(rwUser.getProvinceCode());
			findRwUserReturn.setCityCode(rwUser.getCityCode());
			findRwUserReturn.setCityAreaCode(rwUser.getCityAreaCode());
			findRwUserReturn.setAddress(rwUser.getAddress());
			findRwUserReturn.setAge(rwUser.getAge());
			findRwUserReturn.setGender(rwUser.getGender());
			findRwUserReturn.setLogoAddr(rwUser.getLogoAddr());
			findRwUserReturn.setWebsiteUrl(rwUser.getWebsiteUrl());
			findRwUserReturn.setCompanyId(rwUser.getCompanyId());
			findRwUserReturn.setCompanyName(rwUser.getCompanyName());
			findRwUserReturn.setLoginName(rwUser.getLoginName());
			findRwUserReturn.setPwd(rwUser.getPwd());
			findRwUserReturn.setEncryptionCode(rwUser.getEncryptionCode());
			findRwUserReturn.setBirthday(rwUser.getBirthday());
			findRwUserReturn.setHeadAddress(rwUser.getHeadAddress());
			findRwUserReturn.setQcord(rwUser.getQcord());
			findRwUserReturn.setNoWx(rwUser.getNoWx());
			findRwUserReturn.setNoWxPersonal(rwUser.getNoWxPersonal());
			findRwUserReturn.setLoginWxOpenid(rwUser.getLoginWxOpenid());
			findRwUserReturn.setCreateId(rwUser.getCreateId());
			findRwUserReturn.setCreateDate(rwUser.getCreateDate());
			findRwUserReturn.setUpdateId(rwUser.getUpdateId());
			findRwUserReturn.setUpdateDate(rwUser.getUpdateDate());
			findRwUserReturn.setRemark(rwUser.getRemark());
			findRwUserReturn.setRemark2(rwUser.getRemark2());
			findRwUserReturn.setRemark3(rwUser.getRemark3());
			findRwUserReturn.setRemark4(rwUser.getRemark4());

			logger.debug("findRwUser(RwUserDto) - end - return value={}", findRwUserReturn);
			return findRwUserReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找热文用户记录信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "查找热文用户记录信息信息错误！", e);
		}

	}

	@Override
	public Page<RwUserDto> findRwUserPage(FindRwUserPage findRwUserPage) throws TsfaServiceException {
		logger.debug("findRwUserPage(FindRwUserPage findRwUserPage={}) - start", findRwUserPage);

		AssertUtils.notNull(findRwUserPage);
		List<RwUserDto> returnList = null;
		int count = 0;
		try {
			returnList = rwUserDao.findRwUserPage(findRwUserPage);
			count = rwUserDao.findRwUserPageCount(findRwUserPage);
		} catch (Exception e) {
			logger.error("热文用户记录信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_PAGE_ERROR, "热文用户记录信息不存在错误.！", e);
		}
		Page<RwUserDto> returnPage = new Page<RwUserDto>(returnList, count, findRwUserPage);

		logger.debug("findRwUserPage(FindRwUserPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public RwUserDto personRwUserLogin(RwUserDto rwUser) throws TsfaServiceException {
		logger.debug("personRwUserLogin(RwUserDto rwUser={}) - start", rwUser);

		AssertUtils.notNull(rwUser);

		String pwd = rwUser.getPwd();
		String loginName = rwUser.getLoginName();

		AssertUtils.notNullAndEmpty(loginName, "登录帐号不能为空！");
		AssertUtils.notNullAndEmpty(pwd, "密码不能为空！");

		RwUserDto findRwUserReturn = null;
		try {

			RwUser record = rwUserDao.selectByLoginName(loginName);

			if (record == null) {
				logger.info("个人会员登录错误：会员不存在！");
				throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员登录错误：会员不存在！");
			}

			if (Status.CANCEL.toString().equals(record.getStatus()) || Status.FREEZE.toString().equals(record.getStatus())) {
				logger.error("个人会员登录错误：会员被注销！");
				throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员登录错误：会员被注销！");
			}

			// 密码检测 前台会做一次MD5
//			DecryptRequest decrpytDTO = new DecryptRequest();
//			decrpytDTO.setEncryptorId(record.getEncryptionCode());
//			decrpytDTO.setInputText(pwd);
//			DecryptResponse decryptResponse = encryptorService.decrypt(decrpytDTO);

			String epwd = MD5.encryptByMD5(pwd);
			logger.info("登陆密码：{}", epwd);

//			if (!decryptResponse.getOutputText().equals(record.getPwd())) {
			if (!epwd.equals(record.getPwd())) {
				logger.error("个人会员登录错误：登录密码错误！");
				throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员登录错误：登录密码错误！");
			}

			String uploadUrl = localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(), SystemParamConstant.UPLOAD_GROUP, SystemParamConstant.UPLOAD_URL);

			// 登录成功；更新最后登陆时间
			RwUser updateRecord = new RwUser();
			updateRecord.setCode(record.getCode());
			updateRecord.setUpdateDate(new Date());
			rwUserDao.updateByPrimaryKeySelective(updateRecord);

			findRwUserReturn = new RwUserDto();
			// find数据录入
			findRwUserReturn.setCode(record.getCode());
			findRwUserReturn.setMemberNoGuid(record.getMemberNoGuid());
			findRwUserReturn.setMemberNameGuid(record.getMemberNameGuid());
			findRwUserReturn.setShopNo(record.getShopNo());
			findRwUserReturn.setShopName(record.getShopName());
			findRwUserReturn.setMerchantNo(record.getMerchantNo());
			findRwUserReturn.setMerchantName(record.getMerchantName());
			findRwUserReturn.setStatus(record.getStatus());
			findRwUserReturn.setUserLevel(record.getUserLevel());
			findRwUserReturn.setMobile(record.getMobile());
			findRwUserReturn.setImei(record.getImei());
			findRwUserReturn.setEmail(record.getEmail());
			findRwUserReturn.setNickName(record.getNickName());
			findRwUserReturn.setAreaCode(record.getAreaCode());
			findRwUserReturn.setAreaName(record.getAreaName());
			findRwUserReturn.setProvinceCode(record.getProvinceCode());
			findRwUserReturn.setCityCode(record.getCityCode());
			findRwUserReturn.setCityAreaCode(record.getCityAreaCode());
			findRwUserReturn.setAddress(record.getAddress());
			findRwUserReturn.setAge(record.getAge());
			findRwUserReturn.setGender(record.getGender());
			findRwUserReturn.setLogoAddr(record.getLogoAddr());
			findRwUserReturn.setWebsiteUrl(record.getWebsiteUrl());
			findRwUserReturn.setCompanyId(record.getCompanyId());
			findRwUserReturn.setCompanyName(record.getCompanyName());
			findRwUserReturn.setLoginName(record.getLoginName());
			findRwUserReturn.setBirthday(rwUser.getBirthday());
			findRwUserReturn.setHeadAddress(record.getHeadAddress());
			findRwUserReturn.setQcord(record.getQcord());
			findRwUserReturn.setNoWx(record.getNoWx());
			findRwUserReturn.setNoWxPersonal(record.getNoWxPersonal());
			findRwUserReturn.setCreateId(record.getCreateId());
			findRwUserReturn.setCreateDate(record.getCreateDate());
			findRwUserReturn.setUpdateId(record.getUpdateId());
			findRwUserReturn.setUpdateDate(record.getUpdateDate());
			findRwUserReturn.setRemark(record.getRemark());
			findRwUserReturn.setRemark2(record.getRemark2());
			findRwUserReturn.setRemark3(record.getRemark3());
			findRwUserReturn.setRemark4(record.getRemark4());

			findRwUserReturn.setUploadUrl(uploadUrl);

		} catch (Exception e) {
			logger.error("个人会员登录错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员登录错误！", e);
		}

		logger.debug("personRwUserLogin(RwUserDto) - end - return value={}", findRwUserReturn);
		return findRwUserReturn;
	}

	@Override
	public String personRwUserRegistered(RwUserDto rwUser) throws TsfaServiceException {
		logger.debug("personRwUserRegistered(RwUserDto rwUser={}) - start", rwUser);

		AssertUtils.notNull(rwUser);

		String pwd = rwUser.getPwd();
		String loginName = rwUser.getLoginName();
		String verification = rwUser.getVerification();

		AssertUtils.notNullAndEmpty(loginName, "登录帐号不能为空！");
		AssertUtils.notNullAndEmpty(pwd, "密码不能为空！");
		AssertUtils.notNullAndEmpty(verification, "短信验证码不能为空！");

		String rs = "OK";

		RwUser record = rwUserDao.selectByLoginName(loginName);

		if (record != null) {
			logger.info("个人会员注册错误：会员已存在！");
			throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员注册错误：会员已存在！");
		}

		// 验证短信验证码
		String vcode = redisCache.get(SmsCodeConstant.RW_REGISTERED_SMS_CODE + "1" + loginName);
		if (StringUtils.isEmpty(vcode) || !verification.equals(vcode)) {
			logger.info("个人会员注册错误：验证码错误！");
			throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员注册错误：验证码错误！");
		}

		redisCache.del(SmsCodeConstant.RW_REGISTERED_SMS_CODE + "1" + loginName);

		rwUser.setEncryptionCode(GUID.generateByUUID());

		rwUser.setLoginName(loginName);
		rwUser.setMobile(loginName);
		rwUser.setMemberNoGuid(GUID.getPreUUID());
		rwUser.setMemberNameGuid(loginName);

		String epwd = MD5.encryptByMD5(pwd);
		rwUser.setPwd(epwd);

		Date now = new Date();
		rwUser.setCreateDate(now);
		rwUser.setUpdateDate(now);

		rwUser.setCreateId(loginName);
		rwUser.setUpdateId(loginName);

		rwUser.setUserLevel(RwUserEnums.Ordinary.getName());

		addRwUser(rwUser);

		logger.debug("personRwUserLogin(RwUserDto) - end - return value={}", rs);
		return rs;
	}

	public static void main(String[] args) {
		String pwd="dc483e80a7a0bd9ef71d8cf973673924";
		System.out.println(MD5.encryptByMD5(pwd));
	}
	@Override
	public void personRwUserPwd(RwUserDto rwUser) throws TsfaServiceException {
		logger.debug("personRwUserPwd(RwUserDto rwUser={}) - start", rwUser);

		AssertUtils.notNull(rwUser);

		String pwd = rwUser.getPwd();
		String loginName = rwUser.getLoginName();
		String verification = rwUser.getVerification();
		String nowPwd = rwUser.getNowPwd();

		AssertUtils.notNullAndEmpty(loginName, "登录帐号不能为空！");
		AssertUtils.notNullAndEmpty(nowPwd, "新密码不能为空！");
		boolean flag = true; // 标记

		if (StringUtils.isNotEmpty(pwd)) {
			AssertUtils.notNullAndEmpty(pwd, "密码不能为空！");
		} else {
			AssertUtils.notNullAndEmpty(verification, "短信验证码不能为空！");
			flag = false;
		}

		try {

			RwUser record = rwUserDao.selectByLoginName(loginName);

			if (record == null) {
				logger.info("个人会员修改密码错误：会员不存在！");
				throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员修改密码错误：会员不存在！");
			}

			String encryPwd = MD5.encryptByMD5(nowPwd);

			if (flag) {
				// 使用密码更改
				if (!MD5.encryptByMD5(pwd).equals(encryPwd)) {
					logger.info("个人会员修改密码错误：密码错误！");
					throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员修改密码错误：密码错误！");
				}
			} else {
				// 验证短信验证码
				String vcode = redisCache.get(SmsCodeConstant.RW_FORGET_SMS_CODE + "2" + loginName);
				if (StringUtils.isEmpty(vcode) || !verification.equals(vcode)) {
					logger.info("个人会员修改密码错误：验证码错误！");
					throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员修改密码错误：验证码错误！");
				}
				redisCache.del(SmsCodeConstant.RW_FORGET_SMS_CODE + "2" + loginName);
			}

			RwUser updateRecord = new RwUser();
			updateRecord.setCode(record.getCode());
			updateRecord.setPwd(encryPwd);
			Date now = new Date();
			updateRecord.setUpdateDate(now);
			rwUser.setUpdateId(loginName);

			rwUserDao.updateByPrimaryKeySelective(updateRecord);

		} catch (Exception e) {
			logger.error("个人会员修改密码错误！", e);
			throw new TsfaServiceException(ErrorCodeRwUser.RW_USER_FIND_ERROR, "个人会员修改密码错误！", e);
		}

		logger.debug("personRwUserPwd(RwUserDto) - end");
	}
}
