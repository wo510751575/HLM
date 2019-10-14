package com.lj.business.weixin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.CommonConstant;
import com.lj.business.common.KeyConstant;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.weixin.dao.IImCommentInfoDao;
import com.lj.business.weixin.dao.IImFriendsInfoDao;
import com.lj.business.weixin.dao.IImLikeInfoDao;
import com.lj.business.weixin.dao.IWxFriendsInfoDao;
import com.lj.business.weixin.dto.AddWxCommentInfoReturn;
import com.lj.business.weixin.dto.AddWxFriendsInfoReturn;
import com.lj.business.weixin.dto.AddWxLikeInfoReturn;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.dto.ToDownloadPic;
import com.lj.business.weixin.emus.FriendsInfoType;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.emus.ReadFlag;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImCommentInfoService;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.IImLikeInfoService;
import com.lj.business.weixin.service.IWxFriendsDataService;
import com.lj.business.weixin.service.impl.handler.FriendsQueryHandler;
import com.lj.distributecache.RedisCache;

/**
 * 处理微信数据服务
 * 
 * @author ldq
 *
 */
@Service
public class WxFriendsInfoDataServiceImpl implements IWxFriendsDataService {

	private static Logger LOG = LoggerFactory.getLogger(WxFriendsInfoDataServiceImpl.class);

	@Resource
	IImFriendsInfoService imFriendsInfoService;

	@Resource
	IImCommentInfoService imCommentInfoService;

	@Resource
	IImLikeInfoService imLikeInfoService;

	@Resource
	IShopTerminalService shopTerminalService;

	@Resource
	FriendsQueryHandler friendsQueryHandler;

	@Resource
	IPersonMemberService personMemberService;

	@Resource
	IWxFriendsInfoDao iWxFriendsInfoDao;

	@Resource
	IImFriendsInfoDao imFriendsInfoDao;

	@Resource
	private IImCommentInfoDao imCommentInfoDao;

	@Resource
	private IImLikeInfoDao imLikeInfoDao;
	@Resource
	IImFriendsFacade friendsFacade;
	@Resource
	RedisCache redisCache;

	/**
	 * 批量导入朋友圈数据（待优化 XXX）<br>
	 * 当前中控端上传朋友圈逻辑：中控微信获取到新的朋友圈、点赞或者评论，会立即将整条朋友圈上传到服务器，包括朋友圈、点赞和评论<br>
	 * 中控端上传朋友圈带来的问题： 1、如果同一条朋友圈在极短时间内有多条点赞或评论数据更新时，服务器并发会有许多浪费资源的重复计算，并有可能出现脏数据
	 * 2、监听模式下的实时上传对中控端和服务器都会系统计算、网络带宽的影响，实际业务上并没有要求朋友圈数据必须实时上传 中控端优先方案：
	 * 1、采用定时和队列机制，比如每5分钟或超过10条朋友圈更新就上传一次朋友圈数据 服务器优化方案：
	 * 1、属性采用MQ消息队列，将请求与写分开，避免中控端因等待服务器http返回结果而占用线程资源
	 * 2、定时批量处理，比如每分钟处理一次所有中控端上传的朋友圈数据。如果采用redis队列定时批量读出，第1步可以不用
	 * 3、批量读出时，将同一中控微信同一条朋友圈信息的多条数据去除最早的，只留下最后一条处理（因为中控会将整条朋友圈上传到服务器，包括朋友圈、点赞和评论）
	 * 4、为避免1分钟内可能处理不完这一间隔内的朋友圈数据而造成堵塞，实际批量处理时可采用线程池
	 * 5、有大量重复查询但又不容易改变的数据做缓存处理，如客户信息、终端信息
	 */
	@Override
	public AddWxFriendsInfoReturn addWxFriendsInfoData(List<ImFriendsInfoDto> wxFriendsInfosList) {
		AddWxFriendsInfoReturn addWxFriendsInfoReturn = new AddWxFriendsInfoReturn();
		LOG.debug("开始处理微信朋友圈 数据   ---->  data :{}", wxFriendsInfosList);
		StringBuffer friendsIds = new StringBuffer();
		for (ImFriendsInfoDto imFriendsInfoDto : wxFriendsInfosList) {
			LOG.debug("开始处理 朋友圈数据:{}", imFriendsInfoDto);
			if (StringUtils.isEmpty(imFriendsInfoDto.getNoWx())) {
				LOG.error(" 微信号 为空  ， 此数据忽略 :{}", imFriendsInfoDto);
				continue;
			}

			try {
				LOG.debug(" friends  data:{}", imFriendsInfoDto);
				if (StringUtils.isEmpty(imFriendsInfoDto.getFriendsId())) {
					continue; // 如果该调朋友圈信息ID为空则忽略
				}
//				LOG.info("upload friendsInfo  optFlag  :{}", imFriendsInfoDto.getOptFlag());
//				FindShopTerminalReturn shopTerminalReturn  = friendsQueryHandler.getShopTerminal(imFriendsInfoDto.getNoWxShop());
				if (SenderFlag.ZK.getCode().equals(imFriendsInfoDto.getOptFlag())) { // 客戶發送朋友圈
					FindPersonMemberReturn personMember = personMemberService.findPersonMemberByNoWxAndShopWx(
							imFriendsInfoDto.getNoWx(), imFriendsInfoDto.getNoWxShop());
					if (personMember != null) {
						String memberName = StringUtils.isNotEmpty(personMember.getNickNameRemarkWx())
								? personMember.getNickNameRemarkWx()
								: personMember.getMemberName();
						imFriendsInfoDto.setMemberNo(personMember.getMemberNo());
						imFriendsInfoDto.setMemberName(memberName);
						imFriendsInfoDto.setMemberNoGm(personMember.getMemberNoGm());
						imFriendsInfoDto.setMemberNoGmName(personMember.getMemberNameGm());
					} else {
						LOG.warn("微信号查询不到此客户,暂存此数据:-{}-{}", imFriendsInfoDto.getNoWx(),
								imFriendsInfoDto.getFriendsId());
					}
				}

				imFriendsInfoDto.setStatus(FriendsSendStatus.SEND_SUCCESS.getStatus());
				ImFriendsInfoDto friendsInfoDB = imFriendsInfoService
						.getImFriendsInfoByFriendsId(imFriendsInfoDto.getFriendsId(), imFriendsInfoDto.getNoWxShop());
				if (friendsInfoDB == null) {
					// 如果是客戶發送的图文朋友圈，且图片状态为空，则初始化为0,0
					if (SenderFlag.ZK.getCode().equals(imFriendsInfoDto.getOptFlag())
							&& FriendsInfoType.PIC.getValue().equals(imFriendsInfoDto.getType())
							&& StringUtils.isEmpty(imFriendsInfoDto.getImgStatus())
							&& StringUtils.isNotEmpty(imFriendsInfoDto.getImgAddr())) {
						StringBuilder imgStatusBuilder = new StringBuilder();
						for (int i = 0; i < imFriendsInfoDto.getImgAddr().split(",").length; i++) {
							imgStatusBuilder.append(",0");
						}
						imFriendsInfoDto.setImgStatus(imgStatusBuilder.toString().substring(1)); // 去掉字符串开头的逗号
					}
					imFriendsInfoService.addImFriendsInfo(imFriendsInfoDto);
					friendsInfoDB = imFriendsInfoDto;
				} else {
					boolean updateFlag = Boolean.FALSE; // 更新朋友圈标识，默认为不更新
					ImFriendsInfoDto update = new ImFriendsInfoDto();

					// 数据库中朋友圈没有客户信息，认领后有客户数据
					if (StringUtils.isEmpty(friendsInfoDB.getMemberNo())
							&& StringUtils.isNotEmpty(imFriendsInfoDto.getMemberNo())) {
						update.setMemberNo(imFriendsInfoDto.getMemberNo());
						update.setMemberName(imFriendsInfoDto.getMemberName());
						update.setNoWxShop(imFriendsInfoDto.getNoWxShop());
						updateFlag = Boolean.TRUE;

					}

					if (StringUtils.isEmpty(friendsInfoDB.getMemberNoGm())
							&& StringUtils.isNotEmpty(imFriendsInfoDto.getMemberNoGm())) {
						update.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
						update.setMemberNoGmName(imFriendsInfoDto.getMemberNoGmName());
						updateFlag = Boolean.TRUE;
					}

					// 有更新
					if (updateFlag) {
						update.setCode(friendsInfoDB.getCode());
						imFriendsInfoService.updateImFriendsInfo(update);
						LOG.debug("更新朋友圈成功: {}", update);
					}
				}

				/**
				 * 如果是客戶發送的图文朋友圈，则需处理未解密的图片 如果已开启自动下载图片，则直接向中控请求解密图片
				 */
				if (SenderFlag.ZK.getCode().equals(imFriendsInfoDto.getOptFlag())
						&& FriendsInfoType.PIC.getValue().equals(imFriendsInfoDto.getType())
						&& StringUtils.isNotEmpty(imFriendsInfoDto.getImgAddr())
						&& StringUtils.isNotEmpty(imFriendsInfoDto.getFriendsId())) {

					try {
						String val = redisCache.get(KeyConstant.ZK_FRIENDS_AUTO + imFriendsInfoDto.getNoWxShop());
						if (String.valueOf(CommonConstant.I_YES).equals(val)) {
							ToDownloadPic toDownload = new ToDownloadPic();
							toDownload.setNoWx(imFriendsInfoDto.getNoWxShop());
							toDownload.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
							toDownload.setFriendsId(imFriendsInfoDto.getFriendsId());
							friendsFacade.toDownloadPic(toDownload);
						}
					} catch (Exception e) {
						LOG.error("自动下载朋友圈图片失败 e={}", e);
					}
				}

				LOG.debug(" add friends info success :{}", imFriendsInfoDto);
				if (imFriendsInfoDto.getComments() != null && imFriendsInfoDto.getComments().size() > 0) {
					addWxCommentInfoData(imFriendsInfoDto.getComments(), imFriendsInfoDto.getNoWxShop()); // 导入评论数据
				}
				if (imFriendsInfoDto.getLikes() != null && imFriendsInfoDto.getLikes().size() > 0) {
					addWxLikeInfoData(imFriendsInfoDto.getLikes(), imFriendsInfoDto.getNoWxShop()); // 导入点赞数据
				}
				friendsIds.append(imFriendsInfoDto.getFriendsId()).append(" ");
			} catch (Exception e) {
				LOG.error(" friends add info error  data:{}", imFriendsInfoDto);
				LOG.error(" friends add info error  :", e);
				throw new TsfaServiceException(
						com.lj.business.weixin.constant.ErrorCodeImFriendsInfo.IM_FRIENDS_INFO_ADD_ERROR, "新增朋友圈失败");
			}
		}
		addWxFriendsInfoReturn.setIdWx(friendsIds.toString());
		LOG.debug("朋友圈数据处理完成-----{} ", friendsIds);
		return addWxFriendsInfoReturn;
	}

	@Override
	public AddWxLikeInfoReturn addWxLikeInfoData(List<ImLikeInfoDto> likesList, String noWxShop) {
		AddWxLikeInfoReturn addWxLikeInfoReturn = new AddWxLikeInfoReturn();
		LOG.debug(" 开始导入 朋友圈信息的点赞数据 ：{} ", likesList);
		for (ImLikeInfoDto imLikeInfoDto : likesList) {

			ImLikeInfoDto imDto = imLikeInfoService.findImLikeInfoByNowxAndId(noWxShop, imLikeInfoDto.getFriendsId(),
					imLikeInfoDto.getUsername());
			if (imDto != null) {
				LOG.warn("点赞数据已导入，此条数据忽略---- >{}   , {}", imLikeInfoDto, imDto);
				continue;
			}
			if (StringUtils.isEmpty(imLikeInfoDto.getFriendsId())) {
				LOG.warn("朋友圈ID为空 ，忽略此数据, {}", imLikeInfoDto);
				continue;
			}
			ImFriendsInfoDto imFriendsInfoDto = friendsQueryHandler.getFriendsInfo(imLikeInfoDto.getFriendsId(),
					noWxShop);
			if (imFriendsInfoDto == null) {
				LOG.warn("朋友圈信息不存在，忽略此数据, {}", imFriendsInfoDto);
				continue;
			}
			FindPersonMemberReturn personMember = personMemberService
					.findPersonMemberByNoWxAndShopWx(imLikeInfoDto.getUsername(), noWxShop);
			try {
				imLikeInfoDto.setFriendsCode(imFriendsInfoDto.getCode());
				if (personMember == null) {
					LOG.warn("客户为空 ，忽略此数据, {}", imLikeInfoDto);
					continue;
				}
				String memberName = StringUtils.isNotEmpty(personMember.getNickNameRemarkWx())
						? personMember.getNickNameRemarkWx()
						: personMember.getMemberName();
				imLikeInfoDto.setMemberNo(personMember.getMemberNo());
				imLikeInfoDto.setMemberName(memberName);
				// 使用朋友圈导购
				imLikeInfoDto.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
				imLikeInfoDto.setMemberNoGmName(imFriendsInfoDto.getMemberNoGmName());

				imLikeInfoDto.setStatus(FriendsSendStatus.SEND_SUCCESS.getStatus());
				imLikeInfoDto.setOptFlag(SenderFlag.ZK.getCode());
				imLikeInfoDto.setNoWxShop(noWxShop);
				imLikeInfoService.addImLikeInfo(imLikeInfoDto);
				LOG.debug(" add imlike info data :{}", imLikeInfoDto);
			} catch (Exception e) {
				LOG.info("点赞信息复制错误 :{} ,{}", e, imLikeInfoDto);
				throw new TsfaServiceException(
						com.lj.business.weixin.constant.ErrorCodeImLikeInfo.IM_LIKE_INFO_ADD_ERROR, "新增点赞失败");

			}
		}
		LOG.debug(" 结束 导入 朋友圈信息：{} 的点赞数据 ：{} ", likesList);
		return addWxLikeInfoReturn;
	}

	@Override
	public AddWxCommentInfoReturn addWxCommentInfoData(List<ImCommentInfoDto> commentInfList, String noWxShop) {
		AddWxCommentInfoReturn addWxCommentInfoReturn = new AddWxCommentInfoReturn();
		LOG.debug(" 开始导入 朋友圈信 的评论数据 ：{} ", commentInfList);
		for (ImCommentInfoDto imCommentInfoDto : commentInfList) {
			try {
				if (noWxShop.equals(imCommentInfoDto.getUsername())) {
					LOG.warn("自己发送的评论 , 此数据忽略---- >{}   , {}", imCommentInfoDto);
					continue;
				}
				ImCommentInfoDto imDto = imCommentInfoService.getImCommentInfoByFriendsId(noWxShop,
						imCommentInfoDto.getFriendsId(), imCommentInfoDto.getCommentTime());
				if (imDto != null) {
					LOG.warn("评论数据已导入，此条数据忽略---- >{}   , {}", imCommentInfoDto, imDto);
					continue;
				}
				ImFriendsInfoDto imFriendsInfoDto = friendsQueryHandler.getFriendsInfo(imCommentInfoDto.getFriendsId(),
						noWxShop);
				if (imFriendsInfoDto == null) {
					LOG.warn("朋友圈信息不存在，此条数据忽略---- >{},{}", imCommentInfoDto.getFriendsId(), noWxShop);
					continue;
				}

				FindShopTerminalReturn shopTerminalReturn = friendsQueryHandler.getShopTerminalServiceByNoWx(noWxShop);
				LOG.info(" 所属终端信息:{}", shopTerminalReturn);
				imCommentInfoDto.setFriendsCode(imFriendsInfoDto.getCode());
				imCommentInfoDto.setOptFlag(SenderFlag.ZK.getCode());
				imCommentInfoDto.setStatus(FriendsSendStatus.SEND_SUCCESS.getStatus());

				/**
				 * 处理客户的备注名
				 */
				String noWx = null;
				if (StringUtils.isNotEmpty(imCommentInfoDto.getUsername())
						&& !imCommentInfoDto.getUsername().equals(shopTerminalReturn.getNoWx())) {
					noWx = imCommentInfoDto.getUsername();
					FindPersonMemberReturn personMember = personMemberService.findPersonMemberByNoWxAndShopWx(noWx,
							noWxShop);
					if (personMember != null) {
						imCommentInfoDto.setMemberNo(personMember.getMemberNo());
						// 先用备注
						String memberName = StringUtils.isNotEmpty(personMember.getNickNameRemarkWx())
								? personMember.getNickNameRemarkWx()
								: personMember.getMemberName();
						imCommentInfoDto.setMemberName(memberName);
						imCommentInfoDto.setNickname(memberName);
						// 使用朋友圈导购
						imCommentInfoDto.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
						imCommentInfoDto.setMemberNoGmName(imFriendsInfoDto.getMemberNoGmName());

					}
				}
				if (StringUtils.isNotEmpty(imCommentInfoDto.getTousername())
						&& !imCommentInfoDto.getTousername().equals(shopTerminalReturn.getNoWx())) {
					noWx = imCommentInfoDto.getTousername();
					FindPersonMemberReturn personMember = personMemberService.findPersonMemberByNoWxAndShopWx(noWx,
							noWxShop);
					if (personMember != null) {
						String memberName = StringUtils.isNotEmpty(personMember.getNickNameRemarkWx())
								? personMember.getNickNameRemarkWx()
								: personMember.getMemberName();
						imCommentInfoDto.setMemberNo(personMember.getMemberNo());
						imCommentInfoDto.setMemberName(memberName);
						imCommentInfoDto.setTonickname(memberName);
						// 使用朋友圈导购
						imCommentInfoDto.setMemberNoGm(imFriendsInfoDto.getMemberNoGm());
						imCommentInfoDto.setMemberNoGmName(imFriendsInfoDto.getMemberNoGmName());
					}
				}

				// 处理朋友圈待回复数量
				if (SenderFlag.GM.getCode().equals(imFriendsInfoDto.getOptFlag())) {
					// 中控发的朋友圈客户评论或者回复中控+1，回复共同好友不算
					if (StringUtils.isEmpty(imCommentInfoDto.getTousername())
							|| imCommentInfoDto.getTousername().equals(shopTerminalReturn.getUsernameWx())
							|| shopTerminalReturn.getNoWx().equals(imCommentInfoDto.getTousername())) {
						imFriendsInfoService.updateImFriendsInfoToReplyCount(imFriendsInfoDto.getCode(), 1);
					}
				} else { // 客户发的朋友圈
					if (StringUtils.isNotEmpty(imCommentInfoDto.getTousername())) {
						// 客户回复中控+1
						if (imCommentInfoDto.getTousername().equals(shopTerminalReturn.getUsernameWx())
								|| shopTerminalReturn.getNoWx().equals(imCommentInfoDto.getTousername())) {
							imFriendsInfoService.updateImFriendsInfoToReplyCount(imFriendsInfoDto.getCode(), 1);
						} else { // 客户回复客户，已读标识为已读
							imCommentInfoDto.setWebReadFlag(String.valueOf(ReadFlag.YES.getCode()));
							imCommentInfoDto.setAppReadFlag(String.valueOf(ReadFlag.YES.getCode()));
						}
					}
				}

				// 自己
				if (SenderFlag.GM.getCode().equals(imFriendsInfoDto.getOptFlag())) {
					imCommentInfoService.addImCommentInfo(imCommentInfoDto);
					return addWxCommentInfoReturn;
				}
				// 客户
				if (SenderFlag.ZK.getCode().equals(imFriendsInfoDto.getOptFlag())) {
					if (StringUtils.isNotEmpty(imCommentInfoDto.getMemberNo())) {
						imCommentInfoService.addImCommentInfo(imCommentInfoDto);
						return addWxCommentInfoReturn;
					} else {
						LOG.error("该用户还没同步到通讯录" + imCommentInfoDto.getTousername());
					}
				}
			} catch (Exception e) {
				LOG.info("评论信息复制错误 ,{}", addWxCommentInfoReturn, e);
				throw new TsfaServiceException(
						com.lj.business.weixin.constant.ErrorCodeImCommentInfo.IM_COMMENT_INFO_ADD_ERROR, "新增评论失败");
			}
		}
		LOG.debug(" 结束 导入 朋友圈信息：{} 的评论数据 ：{} ", commentInfList);
		return addWxCommentInfoReturn;
	}

	/**
	 * 导购解绑客户后清除朋友圈认领数据
	 * 
	 * @param gmNo 导购编号
	 * @param noWx 客户微信
	 */
	public void updateCancleFriendsCommentData(String memberNoGm, String noWx, String memberNo, String noWxGm) {
		LOG.debug(" 开始清除朋友圈数据 ：String memberNoGm={}, String noWx={}, String memberNo={},String noWxGm={} ", memberNoGm,
				noWx, memberNo, noWxGm);
		try {
			AssertUtils.notAllNullAndEmpty(memberNo, noWx, "客户编号和客户微信不能全部为空");
			AssertUtils.notNullAndEmpty(memberNoGm, "导购编号不能为空");
			AssertUtils.notNullAndEmpty(noWxGm, "终端微信不能为空");
			Map<String, String> map = new HashMap<String, String>();
			map.put("memberNoGm", memberNoGm);
			map.put("noWx", noWx);
			map.put("memberNo", memberNo);
			map.put("noWxGm", noWxGm);
			/**
			 * 物理删除相关朋友圈脏数据-异步
			 */
			imFriendsInfoDao.updateCancleFriendsCommentData(map);
			imCommentInfoDao.updateCancleFriendsCommentData(map);
			imLikeInfoDao.updateCancleFriendsCommentData(map);

		} catch (Exception e) {
			LOG.error("开始清除朋友圈数据错误 ,{}", e);
			throw e;
		}
	}

}
