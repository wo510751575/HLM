package com.lj.business.weixin.service.impl;

import java.util.Date;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.weixin.constant.ErrorCodeImLikeInfo;
import com.lj.business.weixin.dao.IImLikeInfoDao;
import com.lj.business.weixin.domain.ImLikeInfo;
import com.lj.business.weixin.dto.FindImLikeInfoPage;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.emus.ReadFlag;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImLikeInfoService;

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
 *         CreateDate: 2017-08-22
 */
@Service
public class ImLikeInfoServiceImpl implements IImLikeInfoService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ImLikeInfoServiceImpl.class);

	@Resource
	private IImLikeInfoDao imLikeInfoDao;

	@Resource
	private IPersonMemberService personMemberService;

	@Override
	public void addImLikeInfo(ImLikeInfoDto imLikeInfoDto) throws TsfaServiceException {
		logger.debug("addImLikeInfo(AddImLikeInfo addImLikeInfo={}) - start", imLikeInfoDto);

		AssertUtils.notNull(imLikeInfoDto);
		try {
			ImLikeInfo imLikeInfo = new ImLikeInfo();
			// add数据录入
			if (StringUtils.isEmpty(imLikeInfoDto.getCode())) {
				imLikeInfo.setCode(GUID.generateCode());
			} else {
				imLikeInfo.setCode(imLikeInfoDto.getCode());
			}
			imLikeInfo.setFriendsCode(imLikeInfoDto.getFriendsCode());
			imLikeInfo.setFriendsId(imLikeInfoDto.getFriendsId());
			imLikeInfo.setMerchantNo(imLikeInfoDto.getMerchantNo());
			imLikeInfo.setMerchantName(imLikeInfoDto.getMerchantName());
			imLikeInfo.setNoWxShop(imLikeInfoDto.getNoWxShop());
			imLikeInfo.setOptFlag(imLikeInfoDto.getOptFlag());
			imLikeInfo.setMemberNo(imLikeInfoDto.getMemberNo());
			imLikeInfo.setMemberName(imLikeInfoDto.getMemberName());
			imLikeInfo.setUsername(imLikeInfoDto.getUsername());
			imLikeInfo.setNickname(imLikeInfoDto.getNickname());
			imLikeInfo.setStatus(imLikeInfoDto.getStatus());
			imLikeInfo.setCreateDate(new Date());
			imLikeInfo.setRemark(imLikeInfoDto.getRemark());
			imLikeInfo.setRemark2(imLikeInfoDto.getRemark2());
			imLikeInfo.setRemark3(imLikeInfoDto.getRemark3());
			imLikeInfo.setRemark4(imLikeInfoDto.getRemark4());
			imLikeInfo.setMemberNoGm(imLikeInfoDto.getMemberNoGm());
			imLikeInfo.setMemberNoGmName(imLikeInfoDto.getMemberNoGmName());
			if (SenderFlag.ZK.getCode().intValue() == imLikeInfo.getOptFlag()) { // 客户发送，默认未读
				imLikeInfo.setAppReadFlag(String.valueOf(ReadFlag.NO.getCode()));
				imLikeInfo.setWebReadFlag(String.valueOf(ReadFlag.NO.getCode()));
			} else { // 中控微信发送，默认已读
				imLikeInfo.setAppReadFlag(String.valueOf(ReadFlag.YES.getCode()));
				imLikeInfo.setWebReadFlag(String.valueOf(ReadFlag.YES.getCode()));
			}
			imLikeInfoDao.insertSelective(imLikeInfo);
			logger.debug("addImLikeInfo(ImLikeInfoDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增评论信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImLikeInfo.IM_LIKE_INFO_ADD_ERROR, "新增评论信息信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询评论信息信息
	 *
	 * @param findImLikeInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017年07月10日
	 *
	 */
	public List<ImLikeInfoDto> findImLikeInfos(FindImLikeInfoPage findImLikeInfoPage) throws TsfaServiceException {
		AssertUtils.notNull(findImLikeInfoPage);
		List<ImLikeInfoDto> returnList = null;
		try {
			returnList = imLikeInfoDao.findImLikeInfos(findImLikeInfoPage);
		} catch (Exception e) {
			logger.error("查找评论信息信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImLikeInfo.IM_LIKE_INFO_NOT_EXIST_ERROR, "评论信息信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateImLikeInfo(ImLikeInfoDto imLikeInfoDto) throws TsfaServiceException {
		logger.debug("updateImLikeInfo(ImLikeInfoDto imLikeInfoDto={}) - start", imLikeInfoDto);

		AssertUtils.notNull(imLikeInfoDto);
		AssertUtils.notNullAndEmpty(imLikeInfoDto.getCode(), "Code不能为空");
		try {
			ImLikeInfo imLikeInfo = new ImLikeInfo();
			// update数据录入
			imLikeInfo.setCode(imLikeInfoDto.getCode());
			imLikeInfo.setFriendsCode(imLikeInfoDto.getFriendsCode());
			imLikeInfo.setFriendsId(imLikeInfoDto.getFriendsId());
			imLikeInfo.setMerchantNo(imLikeInfoDto.getMerchantNo());
			imLikeInfo.setMerchantName(imLikeInfoDto.getMerchantName());
			imLikeInfo.setNoWxShop(imLikeInfoDto.getNoWxShop());
			imLikeInfo.setOptFlag(imLikeInfoDto.getOptFlag());
			imLikeInfo.setMemberNo(imLikeInfoDto.getMemberNo());
			imLikeInfo.setMemberName(imLikeInfoDto.getMemberName());
			imLikeInfo.setUsername(imLikeInfoDto.getUsername());
			imLikeInfo.setNickname(imLikeInfoDto.getNickname());
			imLikeInfo.setStatus(imLikeInfoDto.getStatus());
			imLikeInfo.setRemark(imLikeInfoDto.getRemark());
			imLikeInfo.setRemark2(imLikeInfoDto.getRemark2());
			imLikeInfo.setRemark3(imLikeInfoDto.getRemark3());
			imLikeInfo.setRemark4(imLikeInfoDto.getRemark4());
			imLikeInfo.setMemberNoGm(imLikeInfoDto.getMemberNoGm());
			imLikeInfo.setMemberNoGmName(imLikeInfoDto.getMemberNoGmName());
			imLikeInfo.setAppReadFlag(imLikeInfoDto.getAppReadFlag());
			imLikeInfo.setWebReadFlag(imLikeInfoDto.getWebReadFlag());
			AssertUtils.notUpdateMoreThanOne(imLikeInfoDao.updateByPrimaryKeySelective(imLikeInfo));
			logger.debug("updateImLikeInfo(ImLikeInfoDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("评论信息信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImLikeInfo.IM_LIKE_INFO_UPDATE_ERROR, "评论信息信息更新信息错误！", e);
		}
	}

	@Override
	public ImLikeInfoDto findImLikeInfo(ImLikeInfoDto imLikeInfoDto) throws TsfaServiceException {
		logger.debug("findImLikeInfo(FindImLikeInfo findImLikeInfo={}) - start", imLikeInfoDto);

		AssertUtils.notNull(imLikeInfoDto);
		AssertUtils.notAllNull(imLikeInfoDto.getCode(), "Code不能为空");
		try {
			ImLikeInfo imLikeInfo = imLikeInfoDao.selectByPrimaryKey(imLikeInfoDto.getCode());
			if (imLikeInfo == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCodeImLikeInfo.IM_LIKE_INFO_NOT_EXIST_ERROR,"评论信息信息不存在");
			}
			ImLikeInfoDto findImLikeInfoReturn = new ImLikeInfoDto();
			// find数据录入
			findImLikeInfoReturn.setCode(imLikeInfo.getCode());
			findImLikeInfoReturn.setFriendsCode(imLikeInfo.getFriendsCode());
			findImLikeInfoReturn.setFriendsId(imLikeInfo.getFriendsId());
			findImLikeInfoReturn.setMerchantNo(imLikeInfo.getMerchantNo());
			findImLikeInfoReturn.setMerchantName(imLikeInfo.getMerchantName());
			findImLikeInfoReturn.setNoWxShop(imLikeInfo.getNoWxShop());
			findImLikeInfoReturn.setOptFlag(imLikeInfo.getOptFlag());
			findImLikeInfoReturn.setMemberNo(imLikeInfo.getMemberNo());
			findImLikeInfoReturn.setMemberName(imLikeInfo.getMemberName());
			findImLikeInfoReturn.setUsername(imLikeInfo.getUsername());
			findImLikeInfoReturn.setNickname(imLikeInfo.getNickname());
			findImLikeInfoReturn.setStatus(imLikeInfo.getStatus());
			findImLikeInfoReturn.setCreateDate(imLikeInfo.getCreateDate());
			findImLikeInfoReturn.setRemark(imLikeInfo.getRemark());
			findImLikeInfoReturn.setRemark2(imLikeInfo.getRemark2());
			findImLikeInfoReturn.setRemark3(imLikeInfo.getRemark3());
			findImLikeInfoReturn.setRemark4(imLikeInfo.getRemark4());
			findImLikeInfoReturn.setMemberNoGm(imLikeInfo.getMemberNoGm());
			findImLikeInfoReturn.setMemberNoGmName(imLikeInfo.getMemberNoGmName());
			findImLikeInfoReturn.setAppReadFlag(imLikeInfo.getAppReadFlag());
			findImLikeInfoReturn.setWebReadFlag(imLikeInfo.getWebReadFlag());
			logger.debug("findImLikeInfo(ImLikeInfoDto) - end - return value={}", findImLikeInfoReturn);
			return findImLikeInfoReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找评论信息信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImLikeInfo.IM_LIKE_INFO_FIND_ERROR, "查找评论信息信息信息错误！", e);
		}

	}

	@Override
	public List<ImLikeInfoDto> findImLikeInfoByFC(List<String> friendCode) {
		logger.debug("findImLikeInfoByFC(List<String> friendCode={}) - start", friendCode);
		List<ImLikeInfoDto> list = imLikeInfoDao.findImLikeInfoByFC(friendCode);
		logger.debug("findImLikeInfoByFC(List<ImLikeInfoDto>) - end - return value={}", list);
		return list;
	}

	@Override
	public List<ImLikeInfoDto> findImLikeByFC(String friendsCode, String noWxShop) {
		logger.debug("findImLikeInfoByFC(List<String> friendCode={}) - start", friendsCode);
		List<ImLikeInfoDto> list = imLikeInfoDao.findImLikeInfoByFriendsCode(friendsCode, noWxShop);
		logger.debug("findImLikeInfoByFC(List<ImLikeInfoDto>) - end - return value={}", list);
		return list;
	}

	@Override
	public int findImLikeAppNotRead(ImLikeInfoDto imLikeInfoDto) {
		logger.debug("findImLikeAppNotRead(ImLikeInfoDto imLikeInfoDto)={}) - start", imLikeInfoDto);
		int count = imLikeInfoDao.findImLikeAppNotRead(imLikeInfoDto);
		imLikeInfoDao.updateImLikeAppReadFlag(imLikeInfoDto);
		logger.debug("findImLikeAppNotRead(ImLikeInfoDto imLikeInfoDto) - end - return value={}", count);

		return count;
	}

	@Override
	public int findImLikeWebNotRead(ImLikeInfoDto imLikeInfoDto) {
		logger.debug("findImLikeWebNotRead(ImLikeInfoDto imLikeInfoDto)={}) - start", imLikeInfoDto);
		int count = imLikeInfoDao.findImLikeWebNotRead(imLikeInfoDto);
		imLikeInfoDao.updateImLikeWebReadFlag(imLikeInfoDto);
		logger.debug("findImLikeWebNotRead(ImLikeInfoDto imLikeInfoDto) - end - return value={}", count);
		return count;

	}

	@Override
	public int findImLikeAppNotReadCount(ImLikeInfoDto imLikeInfoDto) {
		logger.debug("findImLikeAppNotReadCount(ImLikeInfoDto imLikeInfoDto)={}) - start", imLikeInfoDto);
		int count = imLikeInfoDao.findImLikeAppNotRead(imLikeInfoDto);
		logger.debug("findImLikeAppNotReadCount(ImLikeInfoDto imLikeInfoDto) - end - return value={}", count);
		return count;
	}

	@Override
	public int findImLikeWebNotReadCount(ImLikeInfoDto imLikeInfoDto) {
		logger.debug("findImLikeWebNotReadCount(ImLikeInfoDto imLikeInfoDto)={}) - start", imLikeInfoDto);
		int count = imLikeInfoDao.findImLikeWebNotRead(imLikeInfoDto);
		logger.debug("findImLikeWebNotReadCount(ImLikeInfoDto imLikeInfoDto) - end - return value={}", count);
		return count;
	}

	@Override
	public ImLikeInfoDto getImlikeInfoByNoWx(String noWxShop, String friendsCode, String noWx) {
		return imLikeInfoDao.findImLikeInfoByNowx(noWxShop, friendsCode, noWx);
	}

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈下所有点赞客户username
	 *
	 * @param friendsCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月21日
	 *
	 */
	@Override
	public List<String> findUsernameByFriendsCode(String friendsCode) {
		return imLikeInfoDao.findUsernameByFriendsCode(friendsCode);
	}

	@Override
	public ImLikeInfoDto findImLikeInfoByNowxAndId(String noWxShop, String friendsId, String noWx) {
		return imLikeInfoDao.findImLikeInfoByNowxAndId(noWxShop, friendsId, noWx);
	}

	@Override
	public void delete(ImLikeInfoDto imLikeInfoDto) {
		imLikeInfoDao.delete(imLikeInfoDto);

	}

}
