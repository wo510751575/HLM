package com.lj.business.weixin.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.dto.friends.DeleteFriendsInfoMessage;
import com.lj.business.supcon.service.IChatFriendsFacade;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.constant.ErrorCodeImFriendsInfo;
import com.lj.business.weixin.dao.IImCommentInfoDao;
import com.lj.business.weixin.dao.IImFriendsInfoDao;
import com.lj.business.weixin.dao.IImLikeInfoDao;
import com.lj.business.weixin.domain.ImFriendsInfo;
import com.lj.business.weixin.dto.FindImFriendsInfoPage;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.dto.UpdateFriendsMemberInfo;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImCommentInfoService;
import com.lj.business.weixin.service.IImFriendsInfoService;
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
public class ImFriendsInfoServiceImpl implements IImFriendsInfoService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ImFriendsInfoServiceImpl.class);

	@Resource
	private IImFriendsInfoDao imFriendsInfoDao;

	@Resource
	private IImCommentInfoDao imCommentInfoDao;

	@Resource
	private IImCommentInfoService imCommentInfoService;

	@Resource
	private IImLikeInfoService imLikeInfoService;

	@Resource
	private IImLikeInfoDao imLikeInfoDao;

	@Resource
	private IPersonMemberBaseService personMemberBaseService;

	@Resource
	private IPersonMemberService personMemberService;

	@Resource
	private IShopTerminalService shopTerminalService;

//    @Autowired 
//	private ISystemInfoService systemInfo;

//    @Autowired 
//	private RedisCache redisCache;
	@Autowired
	ICommonService commonService;

	@Override
	public void addImFriendsInfo(ImFriendsInfoDto imFriendsInfoDto) throws TsfaServiceException {
		logger.debug("addImFriendsInfo(AddImFriendsInfo addImFriendsInfo={}) - start", imFriendsInfoDto);

		AssertUtils.notNull(imFriendsInfoDto);
		try {
			ImFriendsInfo imFriendsInfo = new ImFriendsInfo();
			// add数据录入
			if (StringUtils.isEmpty(imFriendsInfoDto.getCode())) {
				imFriendsInfo.setCode(GUID.generateCode());
			} else {
				imFriendsInfo.setCode(imFriendsInfoDto.getCode());
			}
			imFriendsInfo.setFriendsId(imFriendsInfoDto.getFriendsId());
			imFriendsInfo.setMerchantNo(imFriendsInfoDto.getMerchantNo());
			imFriendsInfo.setMerchantName(imFriendsInfoDto.getMerchantName());
			imFriendsInfo.setNoWxShop(imFriendsInfoDto.getNoWxShop());
			imFriendsInfo.setMemberNo(imFriendsInfoDto.getMemberNo());
			imFriendsInfo.setMemberName(imFriendsInfoDto.getMemberName());
			imFriendsInfo.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
			imFriendsInfo.setMemberNoGmName(imFriendsInfoDto.getMemberNoGmName());
			imFriendsInfo.setNoWx(imFriendsInfoDto.getNoWx());
			imFriendsInfo.setNickName(imFriendsInfoDto.getNickName());
			imFriendsInfo.setTimestamp(imFriendsInfoDto.getTimestamp());
			imFriendsInfo.setSourcetype(imFriendsInfoDto.getSourcetype());
			imFriendsInfo.setType(imFriendsInfoDto.getType());
			imFriendsInfo.setShareurl(imFriendsInfoDto.getShareurl());
			imFriendsInfo.setSharetitle(imFriendsInfoDto.getSharetitle());
			imFriendsInfo.setImgAddr(imFriendsInfoDto.getImgAddr());
			imFriendsInfo.setOptFlag(imFriendsInfoDto.getOptFlag());
			imFriendsInfo.setSendTime(imFriendsInfoDto.getSendTime());
			imFriendsInfo.setStatus(imFriendsInfoDto.getStatus());
			imFriendsInfo.setImei(imFriendsInfoDto.getImei());
			imFriendsInfo.setJobCode(imFriendsInfoDto.getJobCode());
			imFriendsInfo.setAutoContent(imFriendsInfoDto.getAutoContent());
			imFriendsInfo.setCreateDate(new Date());
			imFriendsInfo.setRemark(imFriendsInfoDto.getRemark());
			imFriendsInfo.setRemark2(imFriendsInfoDto.getRemark2());
			imFriendsInfo.setRemark3(imFriendsInfoDto.getRemark3());
			imFriendsInfo.setRemark4(imFriendsInfoDto.getRemark4());
			imFriendsInfo.setContent(imFriendsInfoDto.getContent());
			imFriendsInfo.setAppReadFlag(imFriendsInfoDto.getAppReadFlag());
			imFriendsInfo.setWebReadFlag(imFriendsInfoDto.getWebReadFlag());
			imFriendsInfo.setEncKey(imFriendsInfoDto.getEnckey());
			imFriendsInfo.setImgStatus(imFriendsInfoDto.getImgStatus());
			imFriendsInfo.setWhoType(imFriendsInfoDto.getWhoType());
			imFriendsInfo.setWhoNoWxs(imFriendsInfoDto.getWhoNoWxs());
			imFriendsInfo.setRemindNoWxs(imFriendsInfoDto.getRemindNoWxs());
			imFriendsInfoDao.insertSelective(imFriendsInfo);
			logger.debug("addImFriendsInfo(ImFriendsInfoDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增朋友圈信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_ADD_ERROR, "新增朋友圈信息信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询朋友圈信息信息
	 *
	 * @param findImFriendsInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 林进权 CreateDate: 2017年07月10日
	 *
	 */
	public List<ImFriendsInfoDto> findImFriendsInfos(FindImFriendsInfoPage findImFriendsInfoPage)
			throws TsfaServiceException {
		AssertUtils.notNull(findImFriendsInfoPage);
		AssertUtils.notNullAndEmpty(findImFriendsInfoPage.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findImFriendsInfoPage.getJobCode(), "朋友圈任务CODE不能为空");
		List<ImFriendsInfoDto> returnList = null;
		try {
			returnList = imFriendsInfoDao.findImFriendsInfos(findImFriendsInfoPage);
		} catch (Exception e) {
			logger.error("查找朋友圈信息信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_NOT_EXIST_ERROR, "朋友圈信息信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateImFriendsInfo(ImFriendsInfoDto imFriendsInfoDto) throws TsfaServiceException {
		logger.debug("updateImFriendsInfo(ImFriendsInfoDto imFriendsInfoDto={}) - start", imFriendsInfoDto);

		AssertUtils.notNull(imFriendsInfoDto);
		AssertUtils.notNullAndEmpty(imFriendsInfoDto.getCode(), "Code不能为空");
		try {
			ImFriendsInfo imFriendsInfo = new ImFriendsInfo();
			// update数据录入
			imFriendsInfo.setCode(imFriendsInfoDto.getCode());
			imFriendsInfo.setFriendsId(imFriendsInfoDto.getFriendsId());
			imFriendsInfo.setMerchantNo(imFriendsInfoDto.getMerchantNo());
			imFriendsInfo.setMerchantName(imFriendsInfoDto.getMerchantName());
			imFriendsInfo.setNoWxShop(imFriendsInfoDto.getNoWxShop());
			imFriendsInfo.setMemberNo(imFriendsInfoDto.getMemberNo());
			imFriendsInfo.setMemberName(imFriendsInfoDto.getMemberName());
			imFriendsInfo.setNoWx(imFriendsInfoDto.getNoWx());
			imFriendsInfo.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
			imFriendsInfo.setMemberNoGmName(imFriendsInfoDto.getMemberNoGmName());
			imFriendsInfo.setNickName(imFriendsInfoDto.getNickName());
			imFriendsInfo.setTimestamp(imFriendsInfoDto.getTimestamp());
			imFriendsInfo.setSourcetype(imFriendsInfoDto.getSourcetype());
			imFriendsInfo.setType(imFriendsInfoDto.getType());
			imFriendsInfo.setShareurl(imFriendsInfoDto.getShareurl());
			imFriendsInfo.setSharetitle(imFriendsInfoDto.getSharetitle());
			imFriendsInfo.setImgAddr(imFriendsInfoDto.getImgAddr());
			imFriendsInfo.setOptFlag(imFriendsInfoDto.getOptFlag());
			imFriendsInfo.setSendTime(imFriendsInfoDto.getSendTime());
			imFriendsInfo.setStatus(imFriendsInfoDto.getStatus());
			imFriendsInfo.setImei(imFriendsInfoDto.getImei());
			imFriendsInfo.setJobCode(imFriendsInfoDto.getJobCode());
			imFriendsInfo.setAutoContent(imFriendsInfoDto.getAutoContent());
			imFriendsInfo.setCreateDate(imFriendsInfoDto.getCreateDate());
			imFriendsInfo.setRemark(imFriendsInfoDto.getRemark());
			imFriendsInfo.setRemark2(imFriendsInfoDto.getRemark2());
			imFriendsInfo.setRemark3(imFriendsInfoDto.getRemark3());
			imFriendsInfo.setRemark4(imFriendsInfoDto.getRemark4());
			imFriendsInfo.setContent(imFriendsInfoDto.getContent());
			imFriendsInfo.setImgStatus(imFriendsInfoDto.getImgStatus());
			imFriendsInfo.setToReplyCount(imFriendsInfoDto.getToReplyCount());
			imFriendsInfo.setWhoType(imFriendsInfoDto.getWhoType());
			imFriendsInfo.setWhoNoWxs(imFriendsInfoDto.getWhoNoWxs());
			imFriendsInfo.setRemindNoWxs(imFriendsInfoDto.getRemindNoWxs());
			AssertUtils.notUpdateMoreThanOne(imFriendsInfoDao.updateByPrimaryKeySelective(imFriendsInfo));
			logger.debug("updateImFriendsInfo(ImFriendsInfoDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("朋友圈信息信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_UPDATE_ERROR, "朋友圈信息信息更新信息错误！", e);
		}
	}

	@Override
	public ImFriendsInfoDto findImFriendsInfo(ImFriendsInfoDto imFriendsInfoDto) throws TsfaServiceException {
		logger.debug("findImFriendsInfo(FindImFriendsInfo findImFriendsInfo={}) - start", imFriendsInfoDto);

		AssertUtils.notNull(imFriendsInfoDto);
		AssertUtils.notAllNull(imFriendsInfoDto.getCode(), "Code不能为空");
		try {
			ImFriendsInfo imFriendsInfo = imFriendsInfoDao.selectByPrimaryKey(imFriendsInfoDto.getCode());
			if (imFriendsInfo == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_NOT_EXIST_ERROR,"朋友圈信息信息不存在");
			}
			ImFriendsInfoDto findImFriendsInfoReturn = new ImFriendsInfoDto();
			// find数据录入
			findImFriendsInfoReturn.setCode(imFriendsInfo.getCode());
			findImFriendsInfoReturn.setFriendsId(imFriendsInfo.getFriendsId());
			findImFriendsInfoReturn.setMerchantNo(imFriendsInfo.getMerchantNo());
			findImFriendsInfoReturn.setMerchantName(imFriendsInfo.getMerchantName());
			findImFriendsInfoReturn.setMemberNoGm(imFriendsInfo.getMemberNoGm());
			findImFriendsInfoReturn.setMemberNoGmName(imFriendsInfo.getMemberNoGmName());
			findImFriendsInfoReturn.setNoWxShop(imFriendsInfo.getNoWxShop());
			findImFriendsInfoReturn.setMemberNo(imFriendsInfo.getMemberNo());
			findImFriendsInfoReturn.setMemberName(imFriendsInfo.getMemberName());
			findImFriendsInfoReturn.setNoWx(imFriendsInfo.getNoWx());
			findImFriendsInfoReturn.setNickName(imFriendsInfo.getNickName());
			findImFriendsInfoReturn.setTimestamp(imFriendsInfo.getTimestamp());
			findImFriendsInfoReturn.setSourcetype(imFriendsInfo.getSourcetype());
			findImFriendsInfoReturn.setType(imFriendsInfo.getType());
			findImFriendsInfoReturn.setShareurl(imFriendsInfo.getShareurl());
			findImFriendsInfoReturn.setSharetitle(imFriendsInfo.getSharetitle());
			findImFriendsInfoReturn.setImgAddr(imFriendsInfo.getImgAddr());
			findImFriendsInfoReturn.setOptFlag(imFriendsInfo.getOptFlag());
			findImFriendsInfoReturn.setSendTime(imFriendsInfo.getSendTime());
			findImFriendsInfoReturn.setStatus(imFriendsInfo.getStatus());
			findImFriendsInfoReturn.setImei(imFriendsInfo.getImei());
			findImFriendsInfoReturn.setJobCode(imFriendsInfo.getJobCode());
			findImFriendsInfoReturn.setAutoContent(imFriendsInfo.getAutoContent());
			findImFriendsInfoReturn.setCreateDate(imFriendsInfo.getCreateDate());
			findImFriendsInfoReturn.setRemark(imFriendsInfo.getRemark());
			findImFriendsInfoReturn.setRemark2(imFriendsInfo.getRemark2());
			findImFriendsInfoReturn.setRemark3(imFriendsInfo.getRemark3());
			findImFriendsInfoReturn.setRemark4(imFriendsInfo.getRemark4());
			findImFriendsInfoReturn.setContent(imFriendsInfo.getContent());
			findImFriendsInfoReturn.setWebReadFlag(imFriendsInfo.getWebReadFlag());
			findImFriendsInfoReturn.setAppReadFlag(imFriendsInfo.getAppReadFlag());
			findImFriendsInfoReturn.setWhoType(imFriendsInfoDto.getWhoType());
			findImFriendsInfoReturn.setWhoNoWxs(imFriendsInfoDto.getWhoNoWxs());
			findImFriendsInfoReturn.setRemindNoWxs(imFriendsInfoDto.getRemindNoWxs());
			logger.debug("findImFriendsInfo(ImFriendsInfoDto) - end - return value={}", findImFriendsInfoReturn);
			return findImFriendsInfoReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找朋友圈信息信息信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_FIND_ERROR, "查找朋友圈信息信息信息错误！", e);
		}

	}

	@Override
	public Page<ImFriendsInfoDto> findImFriendsInfoPage(FindImFriendsInfoPage findImFriendsInfoPage)
			throws TsfaServiceException {
		logger.debug("findImFriendsInfoPage(FindImFriendsInfoPage findImFriendsInfoPage={}) - start",
				findImFriendsInfoPage);

		AssertUtils.notNull(findImFriendsInfoPage);
		List<ImFriendsInfoDto> returnList = null;
		int count = 0;
		try {
			returnList = imFriendsInfoDao.findImFriendsInfoPage(findImFriendsInfoPage);
			count = imFriendsInfoDao.findImFriendsInfoPageCount(findImFriendsInfoPage);
		} catch (Exception e) {
			logger.error("朋友圈信息信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_FIND_PAGE_ERROR, "朋友圈信息信息不存在错误.！", e);
		}
		Page<ImFriendsInfoDto> returnPage = new Page<ImFriendsInfoDto>(returnList, count, findImFriendsInfoPage);

		logger.debug("findImFriendsInfoPage(FindImFriendsInfoPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public com.lj.business.weixin.dto.ImFriendsInfoDto getImFriendsInfoByFriendsId(String friendsId, String noWxShop) {
		return imFriendsInfoDao.getFriendsInfoByFriendsId(friendsId, noWxShop);
	}

	@Override
	public Page<ImFriendsInfoDto> findImFriendsInfo(FindImFriendsInfoPage findImFriendsInfoPage)
			throws TsfaServiceException {
		logger.debug("findImFriendsInfo(FindImFriendsInfoPage findImFriendsInfoPage={}) - start",
				findImFriendsInfoPage);

		AssertUtils.notNull(findImFriendsInfoPage);
		List<ImFriendsInfoDto> returnList = null;
		int count = 0;
		try {

			count = imFriendsInfoDao.findImFriendsInfoPageCount(findImFriendsInfoPage);
			if (count > 0) {
				returnList = imFriendsInfoDao.findImFriendsInfoPage(findImFriendsInfoPage);
				convertCommentAndLike(returnList, "APP", findImFriendsInfoPage);
				imFriendsInfoDao.updateAppReadFlag(findImFriendsInfoPage);
			}
		} catch (Exception e) {
			logger.error("朋友圈信息信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_FIND_PAGE_ERROR, "朋友圈信息信息不存在错误.！", e);
		}
		Page<ImFriendsInfoDto> returnPage = new Page<ImFriendsInfoDto>(returnList, count, findImFriendsInfoPage);

		logger.debug("findImFriendsInfoPage(FindImFriendsInfoPage) - end - return value={}", returnPage);
		return returnPage;
	}

	/**
	 * 
	 *
	 * 方法说明：获取评论和点赞
	 *
	 * @param returnList
	 *
	 * @author 梅宏博 CreateDate: 2018年1月2日
	 *
	 */
	private void convertCommentAndLike(List<ImFriendsInfoDto> returnList, String type,
			FindImFriendsInfoPage findImFriendsInfoPage) {
		List<String> friendsIdList = new ArrayList<>();
		if (returnList != null && returnList.size() != 0) {
			for (ImFriendsInfoDto imFriendsInfoDto : returnList) {
				friendsIdList.add(imFriendsInfoDto.getFriendsId());

				if (imFriendsInfoDto.getOptFlag() == SenderFlag.ZK.getCode()) {
					try {
						FindPersonMemberBaseReturn member = personMemberBaseService
								.findMemberBaseByNoWxOrAlias(imFriendsInfoDto.getNoWx(), null);
						if (member != null) {
							if (StringUtils.isEmpty(imFriendsInfoDto.getMemberName())) {
								imFriendsInfoDto.setMemberName(member.getMemberName());
							}
							imFriendsInfoDto.setHeadImg(member.getHeadAddress());
						}
					} catch (Exception e) {
						/**
						 * 物理删除相关朋友圈脏数据-异步
						 */
						final String friendsCode = imFriendsInfoDto.getCode();
						logger.error("获取客户基础信息出错，朋友圈存在脏数据！friendsCode={},MemberNo={}, MemberNoGm={}", friendsCode,
								imFriendsInfoDto.getMemberNo(), imFriendsInfoDto.getMemberNoGm());
						imFriendsInfoDao.deleteByPrimaryKey(friendsCode);
						imCommentInfoDao.deleteCommentByFriendsCode(friendsCode);
						imLikeInfoDao.deleteLikeByFriendsCode(friendsCode);
						continue;
					}
				} else if (imFriendsInfoDto.getOptFlag() == SenderFlag.GM.getCode()) {
					logger.info(" gm friends info :{}", imFriendsInfoDto);
					FindShopTerminalReturn shopTerminalReturn = shopTerminalService
							.findShopTerminalNormal(imFriendsInfoDto.getNoWxShop());
					logger.debug("shopTerminalReturn :{}", shopTerminalReturn);
					if (shopTerminalReturn != null) {
						imFriendsInfoDto.setHeadImg(shopTerminalReturn.getHeadAddress());
					}
				}
				List<ImCommentInfoDto> commentAllList = imCommentInfoService
						.findImCommentInfoByFC(imFriendsInfoDto.getCode(), imFriendsInfoDto.getNoWxShop());
				// 过滤掉非所属导购的客户评论记录
				/*
				 * if (StringUtils.isNotEmpty(findImFriendsInfoPage.getMemberNoGm()) &&
				 * CollectionUtils.isNotEmpty(commentAllList)) { List<ImCommentInfoDto>
				 * commentList = new ArrayList<ImCommentInfoDto>(); // 过滤后的评论
				 * for(ImCommentInfoDto commentInfoDto : commentAllList) {
				 * if(SenderFlag.GM.getCode().equals(commentInfoDto.getOptFlag()) ||
				 * findImFriendsInfoPage.getMemberNoGm().equals(commentInfoDto.getMemberNoGm()))
				 * { commentList.add(commentInfoDto); } }
				 * imFriendsInfoDto.setComments(commentList); } else {
				 */
				imFriendsInfoDto.setComments(commentAllList);
//		        }

				List<ImLikeInfoDto> likeAllList = imLikeInfoService.findImLikeByFC(imFriendsInfoDto.getCode(),
						imFriendsInfoDto.getNoWxShop());
				// 过滤掉非所属导购的客户点赞记录
				/*
				 * if (StringUtils.isNotEmpty(findImFriendsInfoPage.getMemberNoGm()) &&
				 * CollectionUtils.isNotEmpty(likeAllList)) { List<ImLikeInfoDto> likeList = new
				 * ArrayList<ImLikeInfoDto>(); // 过滤后的点赞 for(ImLikeInfoDto likeInfoDto :
				 * likeAllList) { if(SenderFlag.GM.getCode().equals(likeInfoDto.getOptFlag()) ||
				 * findImFriendsInfoPage.getMemberNoGm().equals(likeInfoDto.getMemberNoGm())) {
				 * likeList.add(likeInfoDto); } } imFriendsInfoDto.setLikes(likeList); } else {
				 */
				imFriendsInfoDto.setLikes(likeAllList);
//		        }
			}
		}
	}

	@Override
	public Page<ImFriendsInfoDto> findImFriendsInfoWeb(FindImFriendsInfoPage findImFriendsInfoPage) {
		logger.debug("findImFriendsInfoWeb(FindImFriendsInfoPage findImFriendsInfoPage={}) - start",
				findImFriendsInfoPage);

		AssertUtils.notNull(findImFriendsInfoPage);
		List<ImFriendsInfoDto> returnList = null;
		int count = 0;
		try {
			count = imFriendsInfoDao.findImFriendsInfoWebPageCount(findImFriendsInfoPage);
			if (count > 0) {
				returnList = imFriendsInfoDao.findImFriendsInfoWebPage(findImFriendsInfoPage);
			}

			convertCommentAndLike(returnList, "WEB", findImFriendsInfoPage);
			imFriendsInfoDao.updateWebReadFlag(findImFriendsInfoPage);

		} catch (Exception e) {
			logger.error("朋友圈信息信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_FIND_PAGE_ERROR, "朋友圈信息信息不存在错误.！", e);
		}
		Page<ImFriendsInfoDto> returnPage = new Page<ImFriendsInfoDto>(returnList, count, findImFriendsInfoPage);
		logger.debug("findImFriendsInfoWeb(FindImFriendsInfoPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public Integer findImFriendsNotRead(FindImFriendsInfoPage findImFriendsInfoPage) {
		return imFriendsInfoDao.findImFriendsNotRead(findImFriendsInfoPage);
	}

	@Override
	public Integer findImFriendsInfoPendingCommentCount(String noWxShop) throws TsfaServiceException {
		logger.debug("findImFriendsInfoPendingCommentCount(String noWxShop={}) - start", noWxShop);
		AssertUtils.notNullAndEmpty(noWxShop, "店铺微信不能为空");
		int returnNum = 0;
		try {
			returnNum = imFriendsInfoDao.findImFriendsInfoPendingCommentCount(noWxShop);
		} catch (Exception e) {
			logger.error("查找朋友圈信息数量错误！", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_FIND_ERROR, "查找朋友圈信息信息信息错误！", e);
		}
		logger.debug("findImFriendsInfoPendingCommentCount(String, String) - end - return value={}", returnNum);
		return returnNum;
	}

	@Override
	public Page<ImFriendsInfoDto> findImFriendsInfoPendingComment(String noWxShop, Integer start, Integer limit)
			throws TsfaServiceException {
		logger.debug("findImFriendsInfoPendingComment(String noWxShop={}) - start", noWxShop);
		AssertUtils.notNullAndEmpty(noWxShop, "店铺微信不能为空");
		List<ImFriendsInfoDto> returnList = null;
		int count = 0;
		try {
			count = imFriendsInfoDao.findImFriendsInfoPendingCommentContentCount(noWxShop);
			if (count > 0) {
				returnList = imFriendsInfoDao.findImFriendsInfoPendingComment(noWxShop, start, limit);
			}

			FindImFriendsInfoPage findImFriendsInfoPage = new FindImFriendsInfoPage();
			findImFriendsInfoPage.setNoWxShop(noWxShop);
			findImFriendsInfoPage.setStart(start);
			findImFriendsInfoPage.setLimit(limit);
			convertCommentAndLike(returnList, "WEB", findImFriendsInfoPage);// 补充头像等信息
		} catch (Exception e) {
			logger.error("查找朋友圈信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_FIND_ERROR, "查找朋友圈信息错误！", e);
		}
		Page<ImFriendsInfoDto> returnPage = new Page<ImFriendsInfoDto>(returnList, count, start, limit);
		logger.debug("findImFriendsInfoPendingComment(String, String) - end - return value={}", returnList);
		return returnPage;
	}

	@Override
	public boolean findFriendPointByMemberNo(String shopWx, String memberNo) {
		logger.debug("findFriendPointByMemberNo(String shopWx={}, String memberNo={}) - start", shopWx, memberNo);
		AssertUtils.notNullAndEmpty(shopWx, "终端微信不能为空");
		AssertUtils.notNullAndEmpty(memberNo, "客户编号不能为空");
		Map<String, String> paramMap = Maps.newHashMap();
		paramMap.put("shopWx", shopWx);
		paramMap.put("memberNo", memberNo);
		int count = imFriendsInfoDao.findFriendPointByMemberNo(paramMap);
		if (count > 0)
			return true;
		logger.debug("findFriendPointByMemberNo(String, String) - end - return value={}", false);
		return false;
	}

	@Override
	public Integer findImFriendsInfoToReplyCount(FindImFriendsInfoPage findImFriendsInfoPage)
			throws TsfaServiceException {
		logger.debug("findImFriendsInfoToReplyCount(FindImFriendsInfoPage findImFriendsInfoPage={}) - start",
				findImFriendsInfoPage);

		AssertUtils.notNull(findImFriendsInfoPage);
		Integer count = 0;
		try {
			Integer toReplyCount = imFriendsInfoDao.findImFriendsInfoToReplyCount(findImFriendsInfoPage);
			if (null != toReplyCount) {
				count = toReplyCount;
			}
		} catch (Exception e) {
			logger.error("朋友圈信息信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_FIND_PAGE_ERROR, "朋友圈信息信息不存在错误.！", e);
		}

		logger.debug("findImFriendsInfoToReplyCount(FindImFriendsInfoPage) - end - return value={}", count);
		return count;
	}

	@Override
	public void updateImFriendsInfoToReplyCount(String friendsCode, Integer delta) {
		logger.debug("updateImFriendsInfoToReplyCount(String friendsCode={}, Integer delta={}) - start", friendsCode,
				delta);

		AssertUtils.notNull(friendsCode);
		AssertUtils.notNull(delta);
		try {
			imFriendsInfoDao.updateImFriendsInfoToReplyCount(friendsCode, delta);
		} catch (Exception e) {
			logger.error("朋友圈信息信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_UPDATE_ERROR, "朋友圈信息信息更新信息错误！", e);
		}

		logger.debug("updateImFriendsInfoToReplyCount(String, Integer) - end");
	}

	/**
	 * 
	 *
	 * 方法说明：客户未认领前发的朋友圈、评论和点赞没有客户信息，认领后需更新其客户信息
	 *
	 * @param updateFriendsMemberInfo
	 *
	 * @author 曾垂瑜 CreateDate: 2018年5月4日
	 *
	 */
	@Override
	public void updateFriendsMemberInfo(UpdateFriendsMemberInfo updateFriendsMemberInfo) {
		logger.debug("updateFriendsMemberInfo(UpdateFriendsMemberInfo updateFriendsMemberInfo={}}) - start",
				updateFriendsMemberInfo);

		AssertUtils.notNull(updateFriendsMemberInfo);

		try {
			// 更新朋友圈中的客户信息
			imFriendsInfoDao.updateFriendsMemberInfo(updateFriendsMemberInfo);

			// 更新评论中的客户信息 TODO没有索引
			imCommentInfoDao.updateCommentMemberInfo(updateFriendsMemberInfo);

			// 更新点赞中的客户信息 TODO没有索引
			imLikeInfoDao.updateLikeMemberInfo(updateFriendsMemberInfo);
		} catch (Exception e) {
			logger.error("更新朋友圈、评论和点赞中的客户信息错误！", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_UPDATE_ERROR, "更新朋友圈、评论和点赞中的客户信息错误！",
					e);
		}

		logger.debug("updateFriendsMemberInfo(UpdateFriendsMemberInfo) - end");
	}

	/**
	 * 
	 *
	 * 方法说明：向中控请求删除朋友圈
	 *
	 * @param friendsCode
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月1日
	 *
	 */
	@Override
	public void sendDeleteFriendsInfo(String friendsCode, String memberNoGm) {
		logger.info("deleteFriendsInfo(String friendsCode={}}) - start", friendsCode);

		try {
			ImFriendsInfo friendsInfo = imFriendsInfoDao.selectByPrimaryKey(friendsCode);
			if (friendsInfo == null) {
				throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_NOT_EXIST_ERROR, "朋友圈不存在");
			}
			if (!SenderFlag.GM.getCode().equals(friendsInfo.getOptFlag())) {
				throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_UPDATE_ERROR, "只能删除中控微信发的朋友圈");
			}
			if (!memberNoGm.equals(friendsInfo.getMemberNoGm())) {
				throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_UPDATE_ERROR, "只能删除自己发的朋友圈");
			}
			// 向中控请求删除朋友圈
			DeleteFriendsInfoMessage deleteFriendsInfoMessage = new DeleteFriendsInfoMessage();
			deleteFriendsInfoMessage.setFriendsCode(friendsCode);
			deleteFriendsInfoMessage.setFriendsId(friendsInfo.getFriendsId());
			deleteFriendsInfoMessage.setNoWx(friendsInfo.getNoWxShop());

			IChatFriendsFacade basic = commonService.getHessianIChatFriendsFacade(deleteFriendsInfoMessage.getNoWx());

			basic.sendDeleteFriends(deleteFriendsInfoMessage);
		} catch (TsfaServiceException e) {
			logger.error("向中控请求删除朋友圈失败", e);
			throw e;
		} catch (Exception e) {
			logger.error("向中控请求删除朋友圈失败", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_UPDATE_ERROR, "删除朋友圈失败");
		}

		logger.info("deleteFriendsInfo(String) - end");
	}

	/**
	 * 
	 *
	 * 方法说明：删除朋友圈
	 *
	 * @param friendsCode
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月1日
	 *
	 */
	@Override
	public void doDeleteFriendsInfo(String friendsCode) {
		logger.info("doDeleteFriendsInfo(String friendsCode={}}) - start", friendsCode);

		try {
			// 删除朋友圈记录
			imFriendsInfoDao.deleteByPrimaryKey(friendsCode);

			// 删除所有评论
			imCommentInfoDao.deleteCommentByFriendsCode(friendsCode);

			// 删除所有点赞
			imLikeInfoDao.deleteLikeByFriendsCode(friendsCode);
		} catch (Exception e) {
			logger.error("删除朋友圈失败", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_UPDATE_ERROR, "删除朋友圈失败");
		}

		logger.info("doDeleteFriendsInfo(String) - end");

	}

	@Override
	public ImFriendsInfoDto findImFriendsInfoByNoWx(String noWx) {
		FindImFriendsInfoPage page = new FindImFriendsInfoPage();
		page.setNoWx(noWx);
		List<ImFriendsInfoDto> imFriendsInfoDtos = imFriendsInfoDao.findImFriendsInfoWebPage(page);
		logger.info("findImFriendsInfoByMemberNoAndMemberNoGm.imFriendsInfoDtos: {}", imFriendsInfoDtos);
		return imFriendsInfoDtos == null || imFriendsInfoDtos.isEmpty() ? null : imFriendsInfoDtos.get(0);
	}

	@Override
	public Page<ImFriendsInfo> selectFriendsDatasPage(FindImFriendsInfoPage findImFriendsInfoPage)
			throws TsfaServiceException {
		logger.debug("findImFriendsInfo(FindImFriendsInfoPage findImFriendsInfoPage={}) - start", //$NON-NLS-1$
				findImFriendsInfoPage);

		AssertUtils.notNull(findImFriendsInfoPage);
		List<ImFriendsInfo> returnList = null;
		int count = 0;
		try {

			count = imFriendsInfoDao.selectFriendsDatasCount(findImFriendsInfoPage);
			if (count > 0) {
				returnList = imFriendsInfoDao.selectFriendsDatas(findImFriendsInfoPage);
			}
		} catch (Exception e) {
			logger.error("朋友圈信息信息不存在错误", e);
			throw new TsfaServiceException(ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_FIND_PAGE_ERROR, "朋友圈信息信息不存在错误.！", e);
		}
		Page<ImFriendsInfo> returnPage = new Page<ImFriendsInfo>(returnList, count, findImFriendsInfoPage);

		logger.debug("findImFriendsInfoPage(FindImFriendsInfoPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return returnPage;
	}

	@Override
	public void delete(String merchantNo, String noWx) {
		imFriendsInfoDao.delete(merchantNo, noWx);

	}
}
