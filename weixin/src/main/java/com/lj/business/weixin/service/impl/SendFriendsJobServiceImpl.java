package com.lj.business.weixin.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaException;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cm.service.IFriendsLinkMaterialService;
import com.lj.business.cm.service.IFriendsVideoMaterialService;
import com.lj.business.common.CommonConstant;
import com.lj.business.member.domain.AddFriends;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.weixin.constant.ErrorCode;
import com.lj.business.weixin.dao.ISendFriendsJobDao;
import com.lj.business.weixin.domain.SendFriendsJob;
import com.lj.business.weixin.dto.FindSendFriendsJobPage;
import com.lj.business.weixin.dto.FindSendFriendsJobPageReturn;
import com.lj.business.weixin.dto.ToFriendsInfosDto;
import com.lj.business.weixin.dto.friendsjob.AddSendFriendsJob;
import com.lj.business.weixin.dto.friendsjob.SendFriendsTaskDto;
import com.lj.business.weixin.dto.friendsjob.UpdateSendFriendsJob;
import com.lj.business.weixin.emus.FriendsInfoType;
import com.lj.business.weixin.emus.SendFriendsJobStatus;
import com.lj.business.weixin.service.IImFriendsFacade;
import com.lj.business.weixin.service.ISendFriendsJobService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.domain.JobCenter;
import com.lj.cc.service.IJobMgrService;

/**
 * 
 * 
 * 类说明：发送朋友圈任务实现
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 * 
 *         CreateDate: 2017年12月21日
 */
@Service
public class SendFriendsJobServiceImpl implements ISendFriendsJobService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(SendFriendsJobServiceImpl.class);

	@Autowired
	private ISendFriendsJobDao sendFriendsJobDao;

	@Resource
	private IJobMgrService jobMgrService;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Resource
	private IFriendsLinkMaterialService friendsLinkMaterialService;

	@Resource
	private IFriendsVideoMaterialService friendsVideoMaterialService;
	@Autowired
	private IImFriendsFacade iImFriendsFacade;
	@Autowired
	private IAddFriendsService addFriendsService;
	@Autowired
	private IGuidMemberService guidMemberService;
	
	@Override
	public void addSendFriendsJob(AddSendFriendsJob addSendFriendsJob) throws TsfaException {
		logger.debug("	(AddSendFriendsJob addSendFriendsJob={}) - start", addSendFriendsJob);

		AssertUtils.notNullAndEmpty(addSendFriendsJob.getMerchantNo(), "商户编号不能为空！");
		AssertUtils.notNullAndEmpty(addSendFriendsJob.getNoWxs(), "发送微信号不能为空！");

		logger.info("定时任务时间：" + addSendFriendsJob.getExecuteTime());
		Date now = new Date();

		try {

			if (now.compareTo(addSendFriendsJob.getExecuteTime()) > 0) {
				logger.error("新增发送朋友圈任务时间错误错误！");
				throw new TsfaServiceException(ErrorCode.SEND_FRIENDS_JOB_ADD_ERROR, "新增发送朋友圈任务时间错误错误！！");
			}
			SendFriendsJob sendFriendsJob = new SendFriendsJob();

			// add数据录入
			sendFriendsJob.setCode(GUID.getPreUUID());
			sendFriendsJob.setMerchantJobCode(addSendFriendsJob.getMerchantJobCode());
			sendFriendsJob.setMerchantNo(addSendFriendsJob.getMerchantNo());
			sendFriendsJob.setMerchantName(addSendFriendsJob.getMerchantName());
			sendFriendsJob.setContent(addSendFriendsJob.getContent());
			sendFriendsJob.setNoWxs(addSendFriendsJob.getNoWxs());
			sendFriendsJob.setExecuteTime(addSendFriendsJob.getExecuteTime());
			sendFriendsJob.setJobState(SendFriendsJobStatus.INIT.getCode());
			sendFriendsJob.setCreateDate(new Date());
			sendFriendsJob.setResourcePath(addSendFriendsJob.getResourcePath());
			sendFriendsJob.setLinkUrl(addSendFriendsJob.getResourcePath());
			sendFriendsJob.setImgAddr(addSendFriendsJob.getImgAddr());
			sendFriendsJob.setType(addSendFriendsJob.getType());
			sendFriendsJob.setCreateUserLevel(addSendFriendsJob.getCreateUserLevel());
			sendFriendsJob.setCreateId(addSendFriendsJob.getCreateId());
			if(StringUtils.isNotBlank(addSendFriendsJob.getCreateId())
					&& StringUtils.isBlank(addSendFriendsJob.getCreateName())) {
				//创建人ID存储创建人名称
				FindGuidMember findGuidMember = new FindGuidMember();
				findGuidMember.setMemberNo(addSendFriendsJob.getCreateId());
				FindGuidMemberReturn findGuidMemberReturn= guidMemberService.findGuidMember(findGuidMember);
				if(null != findGuidMemberReturn) {
					sendFriendsJob.setCreateName(findGuidMemberReturn.getMemberName());
				}
			}else {
				sendFriendsJob.setCreateName(addSendFriendsJob.getCreateName());
			}
			sendFriendsJob.setRemark2(addSendFriendsJob.getShareTitle());
			sendFriendsJob.setAutoContent(addSendFriendsJob.getAutoContent());
			if(null == sendFriendsJob.getAutoComment()) {
				sendFriendsJob.setAutoComment(StringUtils.isNotEmpty(addSendFriendsJob.getAutoContent())?CommonConstant.I_YES:CommonConstant.I_NO);
			}else {
				sendFriendsJob.setAutoComment(addSendFriendsJob.getAutoComment());
			}
			
			sendFriendsJobDao.insertSelective(sendFriendsJob);

			JobCenter jc = getJobCenterWithDate(addSendFriendsJob.getExecuteTime());

			String callbackUrl = localCacheSystemParams.getSystemParam("weixin", "sendFriendsJob",
					"sendFriendsJobCallbackUrl");
			jc.setJobIntf(callbackUrl);
			jc.setJobName("添加发送朋友圈任务调度");
			jc.setSystemAliasName("weixin");
			jc.setJobEnglishName("AddSendFriendsJob:" + sendFriendsJob.getCode() + ":" + addSendFriendsJob.getNoWxs()
					+ ":" + now.getTime());

			SendFriendsTaskDto sendFriendsTaskDto = new SendFriendsTaskDto();
			sendFriendsTaskDto.setCode(sendFriendsJob.getCode());
			sendFriendsTaskDto.setActionType(1);
			sendFriendsTaskDto.setWxNo(addSendFriendsJob.getNoWxs());
			jc.setJobParam(JsonUtils.jsonFromObject(sendFriendsTaskDto));
			logger.debug("AddSendFriendsJob: " + jc);
			jobMgrService.addTempJob(jc);

		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增发送朋友圈任务错误！", e);
			throw new TsfaServiceException(ErrorCode.SEND_FRIENDS_JOB_ADD_ERROR, "新增发送朋友圈任务错误！", e);
		}
	}

	private JobCenter getJobCenterWithDate(Date date) {
		JobCenter jc = new JobCenter();
		jc.setIsEnable("1");
		// 构造corn表达式
		StringBuilder jobCalender = new StringBuilder("");
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);// 设置日历时间

		jobCalender.append(calendar.get(Calendar.SECOND)).append(" ").append(calendar.get(Calendar.MINUTE)).append(" ")
				.append(calendar.get(Calendar.HOUR_OF_DAY)).append(" ").append(calendar.get(Calendar.DATE)).append(" ")
				.append(calendar.get(Calendar.MONTH) + 1).append(" ?").append(" ").append(calendar.get(Calendar.YEAR));
		jc.setJobCalender(jobCalender.toString());// 0 47 11 25 12 ? 2017

		return jc;
	}

	@Override
	public Page<FindSendFriendsJobPageReturn> findSendFriendsJobPage(FindSendFriendsJobPage findSendFriendsJobPage)
			throws TsfaServiceException {
		logger.debug("findSendFriendsJobPage(FindSendFriendsJobPage findSendFriendsJobPage={}) - start",
				findSendFriendsJobPage);

		AssertUtils.notNull(findSendFriendsJobPage);
		AssertUtils.notNullAndEmpty(findSendFriendsJobPage.getMerchantNo(), "商户编号不能为空!");
		List<FindSendFriendsJobPageReturn> returnList = null;
		int count = 0;
		try {
			count = sendFriendsJobDao.findSendFriendsJobPageCount(findSendFriendsJobPage);
			if (count > 0) {
				returnList = sendFriendsJobDao.findSendFriendsJobPage(findSendFriendsJobPage);
			}
		} catch (Exception e) {
			logger.error("查询朋友圈任务信息错误", e);
			throw new TsfaServiceException(ErrorCode.SEND_FRIENDS_JOB_FIND_ERROR, "查询朋友圈任务信息错误！", e);
		}
		Page<FindSendFriendsJobPageReturn> returnPage = new Page<FindSendFriendsJobPageReturn>(returnList, count,
				findSendFriendsJobPage);

		logger.debug("findSendFriendsJobPage(FindSendFriendsJobPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public void delSendFriendsJob(AddSendFriendsJob addSendFriendsJob) throws TsfaException {
		sendFriendsJobDao.deleteByPrimaryKey(addSendFriendsJob.getCode());

	}

	@Override
	public void repeat(String code) {
		logger.debug("repeat(String code={}) - start", code);

		AssertUtils.notNullAndEmpty(code, "朋友圈任务编号不能为空!");

		try {
			// 发送朋友圈
			SendFriendsJob taskDto = sendFriendsJobDao.selectByPrimaryKey(code);
			// 图片地址转换为全路径url
			Map<String, String> map = localCacheSystemParams.getSystemParamGroup("weixin", "imfile");
			ToFriendsInfosDto toFriendsInfosDto = new ToFriendsInfosDto();

			toFriendsInfosDto.setMemberNoGm(taskDto.getCreateId());
			toFriendsInfosDto.setNoWxShop(taskDto.getNoWxs());

			logger.debug("发送朋友圈任务查询数据", toFriendsInfosDto);

			toFriendsInfosDto.setJobCode(taskDto.getCode());
			String content = taskDto.getContent();

			// IMAGE("1", "图片素材"), VIDEO("2", "视频素材"),LINK("3","链接素材");

			String imgAddr = taskDto.getImgAddr();

			if ("1".equals(taskDto.getType())) {
				// 图片地址转换为全路径url
				// Map<String, String> map =
				// localCacheSystemParams.getSystemParamGroup("weixin", "imfile");
				String[] imgUriArr = imgAddr.split(",");
				StringBuilder sb = new StringBuilder("");
				String url = null;
				for (String uri : imgUriArr) {
					url = map.get("uploadUrl") + uri;
					sb.append(url + ",");
				}
				if (sb.length() > 1) {
					sb.deleteCharAt(sb.length() - 1);
				}

				toFriendsInfosDto.setType(FriendsInfoType.PIC.getValue());
				toFriendsInfosDto.setContent(content);
				toFriendsInfosDto.setImgAddr(sb.toString());

			} else if ("3".equals(taskDto.getType())) {
				toFriendsInfosDto.setType(FriendsInfoType.MESSAGE.getValue());
				if (StringUtils.isNotEmpty(imgAddr)) {

					String[] imgUriArr = imgAddr.split(",");
					StringBuilder sb = new StringBuilder("");
					String url = null;
					for (String uri : imgUriArr) {
						url = map.get("uploadUrl") + uri;
						sb.append(url + ",");
					}
					if (sb.length() > 1) {
						sb.deleteCharAt(sb.length() - 1);
					}
					toFriendsInfosDto.setImgAddr(sb.toString());
				}
				toFriendsInfosDto.setShareurl(taskDto.getLinkUrl());
				if (taskDto.getLinkUrl() != null) {
					toFriendsInfosDto.setType(FriendsInfoType.SHARE.getValue());
					toFriendsInfosDto.setShareurl(taskDto.getLinkUrl());
					if (StringUtils.isEmpty(taskDto.getRemark())) {// 老的链接素材remark为空，取content的前30个字符
						String shareTitle = content.length() > 30 ? content.substring(0, 30) : content;
						toFriendsInfosDto.setSharetitle(shareTitle);
					} else {
						toFriendsInfosDto.setSharetitle(taskDto.getRemark());
					}
				}
				toFriendsInfosDto.setContent(content);
			} else if ("2".equals(taskDto.getType())) {
				toFriendsInfosDto.setType(FriendsInfoType.VIDEO.getValue());
				toFriendsInfosDto.setShareurl(map.get("uploadUrl") + taskDto.getLinkUrl());
				if (taskDto.getLinkUrl() != null) {
					if (StringUtils.isEmpty(taskDto.getRemark())) {// 老的链接素材remark为空，取content的前30个字符
						String shareTitle = content.length() > 30 ? content.substring(0, 30) : content;
						toFriendsInfosDto.setSharetitle(shareTitle);
					} else {
						toFriendsInfosDto.setSharetitle(taskDto.getRemark());
					}
				}
				toFriendsInfosDto.setContent(content);
			}

			// 如果自动评论，设置自动评论的内容
			if (Integer.valueOf(1).equals(taskDto.getAutoComment())) {
				String autoContent = taskDto.getAutoContent();
				toFriendsInfosDto.setAutoContent(autoContent);
			}

			// 此处需要加老板级别代码判断
			// 如果为公司老板或者某级别可公开所有客户可见
			if (taskDto.getCreateUserLevel() == null || taskDto.getCreateUserLevel().equals("1")) {
				toFriendsInfosDto.setWhoType("1");// 谁可以看类型1.公开2.私密3.部分可见4.不给谁看

				SendFriendsJob updateSendFriendsJob = new SendFriendsJob();
				updateSendFriendsJob.setCode(taskDto.getCode());
				updateSendFriendsJob.setRealExecuteTime(new Date());
				updateSendFriendsJob.setJobState(SendFriendsJobStatus.SENDING.getCode());

				sendFriendsJobDao.updateByPrimaryKeySelective(updateSendFriendsJob);

				iImFriendsFacade.toFriendsInfo(toFriendsInfosDto);
			}
			// ***************** 新版发朋友圈方式，可选择客户可看 (start)******************

			// 如果为员工级别
			if (taskDto.getCreateUserLevel() != null && taskDto.getCreateUserLevel().equals("2")) {
				// 查询自己认领的客户
				String gmNo = taskDto.getCreateId();
				List<AddFriends> addFriendsList = addFriendsService.findClaimMemberList(gmNo);

				int i = 0;
				String whoNoWxs = "";
				for (AddFriends pm : addFriendsList) {
					if (i == 0) {
						whoNoWxs = pm.getNoWx();
					} else {
						whoNoWxs = whoNoWxs + "," + pm.getNoWx();
					}
					i = i + 1;
				}

				// 谁可以看类型1.公开2.私密3.部分可见4.不给谁看
				toFriendsInfosDto.setWhoType("3");

				// 当whoType 为3和4的时候改字段不为空,传客户微信集合以英文','分隔
				toFriendsInfosDto.setWhoNoWxs(whoNoWxs);
				// 提醒谁看集合以英文','分隔
				// toFriendsInfosDto.setRemindNoWxs(toFriendsInfosDto.getRemindNoWxs());

				SendFriendsJob updateSendFriendsJob = new SendFriendsJob();
				updateSendFriendsJob.setCode(taskDto.getCode());
				updateSendFriendsJob.setRealExecuteTime(new Date());
				updateSendFriendsJob.setJobState(SendFriendsJobStatus.SENDING.getCode());

				sendFriendsJobDao.updateByPrimaryKeySelective(updateSendFriendsJob);

				iImFriendsFacade.toFriendsInfo(toFriendsInfosDto);
			}
			// ***************** 新版发朋友圈方式，可选择客户可看 (end)******************

			SendFriendsJob updateSendFriendsJob = new SendFriendsJob();
			updateSendFriendsJob.setCode(taskDto.getCode());
			updateSendFriendsJob.setRealExecuteTime(new Date());
			updateSendFriendsJob.setJobState(SendFriendsJobStatus.SUCCESS.getCode());

			sendFriendsJobDao.updateByPrimaryKeySelective(updateSendFriendsJob);
		} catch (Exception e) {
			logger.error("查询朋友圈任务信息错误", e);
			throw new TsfaServiceException(ErrorCode.SEND_FRIENDS_JOB_FIND_ERROR, "查询朋友圈任务信息错误！", e);
		}
	}

	@Override
	public void updateSendFriendsJob(UpdateSendFriendsJob updateSendFriendsJob) throws TsfaException {
		logger.debug("updateSendFriendsJob(UpdateSendFriendsJob updateSendFriendsJob={}) - start", updateSendFriendsJob); 

		AssertUtils.notNull(updateSendFriendsJob);
		AssertUtils.notNullAndEmpty(updateSendFriendsJob.getCode(),"编号不能为空");
		try {
			SendFriendsJob record  = new SendFriendsJob();
			//update数据录入

			record.setCode(updateSendFriendsJob.getCode());;
			record.setJobState(updateSendFriendsJob.getJobState());
			record.setRemark(updateSendFriendsJob.getRemark());
			AssertUtils.notUpdateMoreThanOne(sendFriendsJobDao.updateByPrimaryKeySelective(record));

		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("聊天记录信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WX_CHAT_INFO_UPDATE_ERROR,"聊天记录信息更新信息错误！",e);
		}
		
	}

	
	@Override
	public void delete(AddSendFriendsJob addSendFriendsJob) {
		sendFriendsJobDao.delete(addSendFriendsJob);
		
	}

	@Override
	public FindSendFriendsJobPageReturn findSendFriendsJob(FindSendFriendsJobPage findSendFriendsJobPage)
			throws TsfaException {
		logger.debug("findSendFriendsJob(FindSendFriendsJob findSendFriendsJob={}) - start", findSendFriendsJobPage); 

		AssertUtils.notNull(findSendFriendsJobPage);
		AssertUtils.notAllNull(findSendFriendsJobPage.getCode(),"Code不能为空");
		try {
			SendFriendsJob sendFriendsJob = sendFriendsJobDao.selectByPrimaryKey(findSendFriendsJobPage.getCode());
			if(sendFriendsJob == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SEND_FRIENDS_JOB_NOT_EXIST_ERROR,"发朋友圈任务信息不存在");
			}
			FindSendFriendsJobPageReturn findSendFriendsJobReturn = new FindSendFriendsJobPageReturn();
			//find数据录入
			findSendFriendsJobReturn.setCode(sendFriendsJob.getCode());
			findSendFriendsJobReturn.setMerchantNo(sendFriendsJob.getMerchantNo());
			findSendFriendsJobReturn.setMerchantName(sendFriendsJob.getMerchantName());
			findSendFriendsJobReturn.setContent(sendFriendsJob.getContent());
			findSendFriendsJobReturn.setImgAddr(sendFriendsJob.getImgAddr());
			findSendFriendsJobReturn.setLinkUrl(sendFriendsJob.getLinkUrl());
			findSendFriendsJobReturn.setAutoComment(sendFriendsJob.getAutoComment());
			findSendFriendsJobReturn.setAutoContent(sendFriendsJob.getAutoContent());
			findSendFriendsJobReturn.setNoWxs(sendFriendsJob.getNoWxs());
			findSendFriendsJobReturn.setExecuteTime(sendFriendsJob.getExecuteTime());
			findSendFriendsJobReturn.setRealExecuteTime(sendFriendsJob.getRealExecuteTime());
			findSendFriendsJobReturn.setJobState(sendFriendsJob.getJobState());
			findSendFriendsJobReturn.setSentNoWxs(sendFriendsJob.getSentNoWxs());
			findSendFriendsJobReturn.setCreateId(sendFriendsJob.getCreateId());
			findSendFriendsJobReturn.setCreateUserLevel(sendFriendsJob.getCreateUserLevel());
			findSendFriendsJobReturn.setCreateDate(sendFriendsJob.getCreateDate());
			findSendFriendsJobReturn.setType(sendFriendsJob.getType());
			findSendFriendsJobReturn.setRemark(sendFriendsJob.getRemark());
			findSendFriendsJobReturn.setRemark2(sendFriendsJob.getRemark2());
			findSendFriendsJobReturn.setRemark3(sendFriendsJob.getRemark3());
			findSendFriendsJobReturn.setRemark4(sendFriendsJob.getRemark4());
			findSendFriendsJobReturn.setCreateName(sendFriendsJob.getCreateName());
			findSendFriendsJobReturn.setMerchantJobCode(sendFriendsJob.getMerchantJobCode());
			
			logger.debug("findSendFriendsJob(SendFriendsJobDto) - end - return value={}", findSendFriendsJobReturn); 
			return findSendFriendsJobReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找发朋友圈任务信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SEND_FRIENDS_JOB_FIND_ERROR,"查找发朋友圈任务信息信息错误！",e);
		}
	}

}
