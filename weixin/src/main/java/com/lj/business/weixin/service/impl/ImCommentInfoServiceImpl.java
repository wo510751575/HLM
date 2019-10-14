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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.constant.ErrorCodeImCommentInfo;
import com.lj.business.weixin.dao.IImCommentInfoDao;
import com.lj.business.weixin.dao.IImFriendsInfoDao;
import com.lj.business.weixin.dao.IImLikeInfoDao;
import com.lj.business.weixin.domain.ImCommentInfo;
import com.lj.business.weixin.dto.FindImCommentInfoPage;
import com.lj.business.weixin.dto.FriendsMessageDto;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.emus.ReadFlag;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImCommentInfoService;

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
public class ImCommentInfoServiceImpl implements IImCommentInfoService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ImCommentInfoServiceImpl.class);

	@Resource
	private IImCommentInfoDao imCommentInfoDao;

	@Resource
	private IImLikeInfoDao imLikeInfoDao;

	@Resource
	private IPersonMemberBaseService personMemberBaseService;

	@Resource
	private IPersonMemberService personMemberService;

	@Resource
	private IShopTerminalService shopTerminalService;
	@Autowired
	private IImFriendsInfoDao friendsInfoDao;
//	@Autowired
//    private ThreadPoolTaskExecutor taskExecutor;

	@Override
	public void addImCommentInfo(ImCommentInfoDto imCommentInfoDto) throws TsfaServiceException {
		logger.debug("addImCommentInfo(AddImCommentInfo addImCommentInfo={}) - start", imCommentInfoDto);

		AssertUtils.notNull(imCommentInfoDto);
		try {
			ImCommentInfo imCommentInfo = new ImCommentInfo();
			// add数据录入
			if (StringUtils.isEmpty(imCommentInfoDto.getCode())) {
				imCommentInfo.setCode(GUID.generateCode());
			} else {
				imCommentInfo.setCode(imCommentInfoDto.getCode());
			}

			imCommentInfo.setFriendsCode(imCommentInfoDto.getFriendsCode());
			imCommentInfo.setFriendsId(imCommentInfoDto.getFriendsId());
			imCommentInfo.setMerchantNo(imCommentInfoDto.getMerchantNo());
			imCommentInfo.setMerchantName(imCommentInfoDto.getMerchantName());
			imCommentInfo.setNoWxShop(imCommentInfoDto.getNoWxShop());
			imCommentInfo.setOptFlag(imCommentInfoDto.getOptFlag());
			imCommentInfo.setMemberNo(imCommentInfoDto.getMemberNo());
			imCommentInfo.setMemberName(imCommentInfoDto.getMemberName());
			imCommentInfo.setUsername(imCommentInfoDto.getUsername());
			imCommentInfo.setNickname(imCommentInfoDto.getNickname());
			imCommentInfo.setTousername(imCommentInfoDto.getTousername());
			imCommentInfo.setTonickname(imCommentInfoDto.getTonickname());
			imCommentInfo.setStatus(imCommentInfoDto.getStatus());
			imCommentInfo.setCreateDate(new Date());
			imCommentInfo.setRemark(imCommentInfoDto.getRemark());
			imCommentInfo.setRemark2(imCommentInfoDto.getRemark2());
			imCommentInfo.setRemark3(imCommentInfoDto.getRemark3());
			imCommentInfo.setRemark4(imCommentInfoDto.getRemark4());
			imCommentInfo.setContent(imCommentInfoDto.getContent());
			imCommentInfo.setMemberNoGm(imCommentInfoDto.getMemberNoGm());
			imCommentInfo.setMemberNoGmName(imCommentInfoDto.getMemberNoGmName());
			if (SenderFlag.ZK.getCode().intValue() == imCommentInfo.getOptFlag()) { // 客户发送，默认未读
				if (StringUtils.isEmpty(imCommentInfoDto.getAppReadFlag())) {
					imCommentInfo.setAppReadFlag(String.valueOf(ReadFlag.NO.getCode()));
				}
				if (StringUtils.isEmpty(imCommentInfoDto.getWebReadFlag())) {
					imCommentInfo.setWebReadFlag(String.valueOf(ReadFlag.NO.getCode()));
				}
			} else { // 中控微信发送，默认已读
				imCommentInfo.setAppReadFlag(String.valueOf(ReadFlag.YES.getCode()));
				imCommentInfo.setWebReadFlag(String.valueOf(ReadFlag.YES.getCode()));
			}
			imCommentInfo.setCommentTime(imCommentInfoDto.getCommentTime());
			imCommentInfo.setCommentSerId(imCommentInfoDto.getCommentSerId());
			imCommentInfoDao.insertSelective(imCommentInfo);
			logger.debug("addImCommentInfo(ImCommentInfoDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增评论信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImCommentInfo.IM_COMMENT_INFO_ADD_ERROR, "新增评论信息信息错误！", e);
		}
	}

	@Override
	public void updateImCommentInfo(ImCommentInfoDto imCommentInfoDto) throws TsfaServiceException {
		logger.debug("updateImCommentInfo(ImCommentInfoDto imCommentInfoDto={}) - start", imCommentInfoDto);

		AssertUtils.notNull(imCommentInfoDto);
		AssertUtils.notNullAndEmpty(imCommentInfoDto.getCode(), "Code不能为空");
		try {
			ImCommentInfo imCommentInfo = new ImCommentInfo();
			// update数据录入
			imCommentInfo.setCode(imCommentInfoDto.getCode());
			imCommentInfo.setFriendsCode(imCommentInfoDto.getFriendsCode());
			imCommentInfo.setFriendsId(imCommentInfoDto.getFriendsId());
			imCommentInfo.setMerchantNo(imCommentInfoDto.getMerchantNo());
			imCommentInfo.setMerchantName(imCommentInfoDto.getMerchantName());
			imCommentInfo.setNoWxShop(imCommentInfoDto.getNoWxShop());
			imCommentInfo.setOptFlag(imCommentInfoDto.getOptFlag());
			imCommentInfo.setMemberNo(imCommentInfoDto.getMemberNo());
			imCommentInfo.setMemberName(imCommentInfoDto.getMemberName());
			imCommentInfo.setUsername(imCommentInfoDto.getUsername());
			imCommentInfo.setNickname(imCommentInfoDto.getNickname());
			imCommentInfo.setTousername(imCommentInfoDto.getTousername());
			imCommentInfo.setTonickname(imCommentInfoDto.getTonickname());
			imCommentInfo.setStatus(imCommentInfoDto.getStatus());
			imCommentInfo.setCreateDate(imCommentInfoDto.getCreateDate());
			imCommentInfo.setRemark(imCommentInfoDto.getRemark());
			imCommentInfo.setRemark2(imCommentInfoDto.getRemark2());
			imCommentInfo.setRemark3(imCommentInfoDto.getRemark3());
			imCommentInfo.setRemark4(imCommentInfoDto.getRemark4());
			imCommentInfo.setContent(imCommentInfoDto.getContent());
			imCommentInfo.setMemberNoGm(imCommentInfoDto.getMemberNoGm());
			imCommentInfo.setMemberNoGmName(imCommentInfoDto.getMemberNoGmName());
			AssertUtils.notUpdateMoreThanOne(imCommentInfoDao.updateByPrimaryKeySelective(imCommentInfo));
			logger.debug("updateImCommentInfo(ImCommentInfoDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("评论信息信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImCommentInfo.IM_COMMENT_INFO_UPDATE_ERROR, "评论信息信息更新信息错误！", e);
		}
	}

	@Override
	public ImCommentInfoDto findImCommentInfo(ImCommentInfoDto imCommentInfoDto) throws TsfaServiceException {
		logger.debug("findImCommentInfo(FindImCommentInfo findImCommentInfo={}) - start", imCommentInfoDto);

		AssertUtils.notNull(imCommentInfoDto);
		AssertUtils.notAllNull(imCommentInfoDto.getCode(), "Code不能为空");
		try {
			ImCommentInfo imCommentInfo = imCommentInfoDao.selectByPrimaryKey(imCommentInfoDto.getCode());
			if (imCommentInfo == null) {
				return null;
			}
			ImCommentInfoDto findImCommentInfoReturn = new ImCommentInfoDto();
			// find数据录入
			findImCommentInfoReturn.setCode(imCommentInfo.getCode());
			findImCommentInfoReturn.setFriendsCode(imCommentInfo.getFriendsCode());
			findImCommentInfoReturn.setFriendsId(imCommentInfo.getFriendsId());
			findImCommentInfoReturn.setMerchantNo(imCommentInfo.getMerchantNo());
			findImCommentInfoReturn.setMerchantName(imCommentInfo.getMerchantName());
			findImCommentInfoReturn.setNoWxShop(imCommentInfo.getNoWxShop());
			findImCommentInfoReturn.setOptFlag(imCommentInfo.getOptFlag());
			findImCommentInfoReturn.setMemberNo(imCommentInfo.getMemberNo());
			findImCommentInfoReturn.setMemberName(imCommentInfo.getMemberName());
			findImCommentInfoReturn.setUsername(imCommentInfo.getUsername());
			findImCommentInfoReturn.setNickname(imCommentInfo.getNickname());
			findImCommentInfoReturn.setTousername(imCommentInfo.getTousername());
			findImCommentInfoReturn.setTonickname(imCommentInfo.getTonickname());
			findImCommentInfoReturn.setStatus(imCommentInfo.getStatus());
			findImCommentInfoReturn.setCreateDate(imCommentInfo.getCreateDate());
			findImCommentInfoReturn.setRemark(imCommentInfo.getRemark());
			findImCommentInfoReturn.setRemark2(imCommentInfo.getRemark2());
			findImCommentInfoReturn.setRemark3(imCommentInfo.getRemark3());
			findImCommentInfoReturn.setRemark4(imCommentInfo.getRemark4());
			findImCommentInfoReturn.setContent(imCommentInfo.getContent());
			findImCommentInfoReturn.setMemberNoGm(imCommentInfo.getMemberNoGm());
			findImCommentInfoReturn.setMemberNoGmName(imCommentInfo.getMemberNoGmName());
			findImCommentInfoReturn.setCommentSerId(imCommentInfo.getCommentSerId());
			logger.debug("findImCommentInfo(ImCommentInfoDto) - end - return value={}", findImCommentInfoReturn);
			return findImCommentInfoReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找评论信息信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImCommentInfo.IM_COMMENT_INFO_FIND_ERROR, "查找评论信息信息信息错误！", e);
		}

	}

	@Override
	public Page<ImCommentInfoDto> findImCommentInfoPage(FindImCommentInfoPage findImCommentInfoPage)
			throws TsfaServiceException {
		logger.debug("findImCommentInfoPage(FindImCommentInfoPage findImCommentInfoPage={}) - start",
				findImCommentInfoPage);

		AssertUtils.notNull(findImCommentInfoPage);
		List<ImCommentInfoDto> returnList = null;
		int count = 0;
		try {
			/*
			 * returnList = imCommentInfoDao.findImCommentInfoPage(findImCommentInfoPage);
			 * count = imCommentInfoDao.findImCommentInfoPageCount(findImCommentInfoPage );
			 */
		} catch (Exception e) {
			logger.error("评论信息信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeImCommentInfo.IM_COMMENT_INFO_FIND_PAGE_ERROR, "评论信息信息不存在错误.！", e);
		}
		Page<ImCommentInfoDto> returnPage = new Page<ImCommentInfoDto>(returnList, count, findImCommentInfoPage);

		logger.debug("findImCommentInfoPage(FindImCommentInfoPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public List<ImCommentInfoDto> findImCommentInfoByFC(List<String> friendCode) {
		logger.debug("findImCommentInfoByFC(List<String> friendCode={}) - start", friendCode);
		List<ImCommentInfoDto> list = imCommentInfoDao.findImCommentInfoByFC(friendCode);
		logger.debug("findImCommentInfoByFC(List<String>) - end - return value={}", list);
		return list;
	}

	@Override
	public List<ImCommentInfoDto> findImCommentInfoByFC(String friendsCode, String noWxShop) {
		logger.debug("findImCommentInfoByFC(List<String> friendCode={}) - start", friendsCode);
		List<ImCommentInfoDto> list = imCommentInfoDao.findImCommentInfoByFriendsCode(friendsCode, noWxShop);
		logger.debug("findImCommentInfoByFC(List<String>) - end - return value={}", list);
		return list;
	}

	@Override
	public Integer findImCommentInfoAppNotReadCount(ImCommentInfoDto imCommentInfoDto) {
		logger.debug("findImCommentInfoAppNotReadCount(ImCommentInfoDto imCommentInfoDto) - start", imCommentInfoDto);
		int count = imCommentInfoDao.findImCommentInfoAppNotRead(imCommentInfoDto);
		logger.debug("findImCommentInfoAppNotReadCount(ImCommentInfoDto imCommentInfoDto) - end - return value={}",
				count);
		return count;
	}

	@Override
	public Integer findImCommentInfoWebNotReadCount(ImCommentInfoDto imCommentInfoDto) {
		logger.debug("findImCommentInfoWebNotReadCount(ImCommentInfoDto imCommentInfoDto) - start", imCommentInfoDto);
		int count = imCommentInfoDao.findImCommentInfoWebNotRead(imCommentInfoDto);
		logger.debug("findImCommentInfoWebNotReadCount(ImCommentInfoDto imCommentInfoDto) - end - return value={}",
				count);
		return count;
	}

	@Override
	public Page<FriendsMessageDto> findFriendsMessageNotRelease(FriendsMessageDto friendsMessage,
			Page<FriendsMessageDto> page) {
		Integer count = imCommentInfoDao.findImMessageListCount(friendsMessage);
		if (count != 0) {
			page.setTotal(count);
			List<FriendsMessageDto> messageList = imCommentInfoDao.findImMessageList(friendsMessage, page);
			FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
			for (FriendsMessageDto friendsMessageDto : messageList) {
				logger.debug("  friendsMessage ---- > {}", friendsMessageDto);
				if (friendsMessageDto.getMemberNo() != null) {
					findPersonMemberBase.setMemberNo(friendsMessageDto.getMemberNo());
					FindPersonMemberBaseReturn member = personMemberBaseService
							.findPersonMemberBase(findPersonMemberBase);
					logger.debug("  member ---- > {}", member);
					if (member != null) {
						friendsMessageDto.setHeadImg(member.getHeadAddress());
					}
				}
			}

			page.setRows(messageList);
		}
		return page;

	}

	@Override
	public Page<FriendsMessageDto> findFriendsMessage(FriendsMessageDto friendsMessage, Page<FriendsMessageDto> page) {
		Integer count = imCommentInfoDao.findImMessageListCount(friendsMessage);
		if (count != 0) {
			page.setTotal(count);
			List<FriendsMessageDto> messageList = imCommentInfoDao.findImMessageList(friendsMessage, page);
			for (FriendsMessageDto friendsMessageDto : messageList) {
				// logger.debug(" friendsMessage ---- > {}",friendsMessageDto);
				if (StringUtils.isNotEmpty(friendsMessageDto.getMemberNo())) {

					try {
						FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
						findPersonMemberBase.setMemberNo(friendsMessageDto.getMemberNo());
						FindPersonMemberBaseReturn member = personMemberBaseService
								.findPersonMemberBase(findPersonMemberBase);
						if (member != null) {
							friendsMessageDto.setHeadImg(member.getHeadAddress());
						}
					} catch (Exception e) {
						logger.error("获取客户基础信息出错，朋友圈存在脏数据！MemberNo={}", friendsMessageDto.getMemberNo());
						/**
						 * 物理删除相关朋友圈脏数据-异步
						 */
						final String friendsCode = friendsMessageDto.getFriendsCode();
						friendsInfoDao.deleteByPrimaryKey(friendsCode);
						imCommentInfoDao.deleteCommentByFriendsCode(friendsCode);
						imLikeInfoDao.deleteLikeByFriendsCode(friendsCode);
						continue;
					}

				} else {
					FindShopTerminalReturn shopTerminal = shopTerminalService
							.findShopTerminalByWx(friendsMessageDto.getNoWxShop());
					// logger.debug("shopTerminal --------->{}",shopTerminal);
					if (shopTerminal != null) {
						friendsMessageDto.setHeadImg(shopTerminal.getHeadAddress());
					}
				}
			}

			page.setRows(messageList);
		}
		// 暂时两端都改为已读
		if (!StringUtils.isEmpty(friendsMessage.getNoWxShop())) {
			ImCommentInfoDto imCommentInfoDto = new ImCommentInfoDto();
			imCommentInfoDto.setNoWxShop(friendsMessage.getNoWxShop());
			imCommentInfoDto.setMemberNoGm(friendsMessage.getMemberNoGm());

			// WEB/APP 评论，点赞
			imCommentInfoDao.updateImCommentWebReadFlag(imCommentInfoDto);
			imCommentInfoDao.updateImCommentAppReadFlag(imCommentInfoDto);

			ImLikeInfoDto imLikeInfoDto = new ImLikeInfoDto();
			imLikeInfoDto.setNoWxShop(friendsMessage.getNoWxShop());
			imLikeInfoDto.setMemberNoGm(friendsMessage.getMemberNoGm());
			imLikeInfoDao.updateImLikeWebReadFlag(imLikeInfoDto);
			imLikeInfoDao.updateImLikeAppReadFlag(imLikeInfoDto);
		}
		return page;
	}

	@Override
	public ImCommentInfoDto getImCommentInfoTimestamp(String noWxShop, String friendsCode, String timestamp) {
		return imCommentInfoDao.findImCommentInfoByFriendsCodeAndTimestamp(noWxShop, friendsCode, timestamp);
	}

	@Override
	public ImCommentInfoDto getImCommentInfoByFriendsId(String noWxShop, String friendsId, String timestamp) {
		return imCommentInfoDao.findImCommentInfoByFriendsIdAndTimestamp(noWxShop, friendsId, timestamp);
	}

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈评论中commentSvrId最大的值
	 * 
	 * @param friendsCode
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月21日
	 *
	 */
	@Override
	public int getMaxCommentSvrId(String friendsCode) {
		return imCommentInfoDao.getMaxCommentSvrId(friendsCode);
	}

	@Override
	public void delete(ImCommentInfoDto imCommentInfoDto) {
		imCommentInfoDao.delete(imCommentInfoDto);

	}

}
