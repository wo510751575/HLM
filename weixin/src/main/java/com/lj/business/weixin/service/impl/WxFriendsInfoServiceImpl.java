//package com.lj.business.weixin.service.impl;
///**
// * Copyright &copy; 2017-2020  All rights reserved.
// *
// * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
// * 
// */
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.lj.base.core.pagination.Page;
//import com.lj.base.core.pagination.PageSortType;
//import com.lj.base.core.util.AssertUtils;
//import com.lj.base.core.util.GUID;
//import com.lj.base.core.util.StringUtils;
//import com.lj.base.exception.TsfaServiceException;
//import com.lj.business.cf.dto.socialTask.AddSocialTask;
//import com.lj.business.cf.service.ISocialTaskService;
//import com.lj.business.member.dto.FindGuidMember;
//import com.lj.business.member.dto.FindGuidMemberPage;
//import com.lj.business.member.dto.FindGuidMemberPageReturn;
//import com.lj.business.member.dto.FindGuidMemberReturn;
//import com.lj.business.member.emus.MemberType;
//import com.lj.business.member.service.IGuidMemberService;
//import com.lj.business.weixin.constant.ErrorCode;
//import com.lj.business.weixin.dao.IWxFriendsInfoDao;
//import com.lj.business.weixin.domain.WxFriendsInfo;
//import com.lj.business.weixin.dto.AddWxCommentInfo;
//import com.lj.business.weixin.dto.AddWxFriendsInfo;
//import com.lj.business.weixin.dto.AddWxFriendsInfoReturn;
//import com.lj.business.weixin.dto.AddWxLikeInfo;
//import com.lj.business.weixin.dto.FindWxFriendsInfo;
//import com.lj.business.weixin.dto.FindWxFriendsInfoPage;
//import com.lj.business.weixin.dto.FindWxFriendsInfoPageReturn;
//import com.lj.business.weixin.dto.FindWxFriendsInfoReturn;
//import com.lj.business.weixin.dto.UpdateWxFriendsInfo;
//import com.lj.business.weixin.dto.UpdateWxFriendsInfoReturn;
//import com.lj.business.weixin.msg.NotifyWxFriends;
//import com.lj.business.weixin.service.IWxCommentInfoService;
//import com.lj.business.weixin.service.IWxFriendsInfoService;
//import com.lj.business.weixin.service.IWxLikeInfoService;
//import com.lj.messagecenter.emus.SendType;
//import com.lj.messagecenter.msg.dto.AddNotifyInfo;
//import com.lj.messagecenter.msg.enums.MsgSystemType;
//import com.lj.messagecenter.msg.service.INotifyService;
//
///**
// * The Class WxFriendsInfoServiceImpl.
// */
//@Service
//@Deprecated // 聚客1.0版本功能（勾子），已不再使用
//public class WxFriendsInfoServiceImpl implements IWxFriendsInfoService { 
//
//
//	/** Logger for this class. */
//	private static final Logger logger = LoggerFactory.getLogger(WxFriendsInfoServiceImpl.class);
//
//
//	/** The wx friends info dao. */
//	@Resource
//	private IWxFriendsInfoDao wxFriendsInfoDao;
//	@Resource
//	private IGuidMemberService guidMemberService;
//	@Resource
//	private IWxCommentInfoService wxCommentInfoService;
//	@Resource
//	private IWxLikeInfoService wxLikeInfoService; 
//	@Resource
//	private ISocialTaskService socialTaskService; 
//	@Autowired
//	private INotifyService notifyService;
//	@Autowired
//	private NotifyWxFriends notifyWxFriends;
//
//
//	/* (non-Javadoc)
//	 * @see com.lj.business.weixin.service.IWxFriendsInfoService#addWxFriendsInfo(com.lj.business.weixin.dto.AddWxFriendsInfo)
//	 */
//	@Override
//	public AddWxFriendsInfoReturn addWxFriendsInfo(
//			AddWxFriendsInfo addWxFriendsInfo) throws TsfaServiceException {
//		logger.debug("addWxFriendsInfo(AddWxFriendsInfo addWxFriendsInfo={}) - start", addWxFriendsInfo); 
//
//		AssertUtils.notNull(addWxFriendsInfo);
//		try {
//			WxFriendsInfo wxFriendsInfo = new WxFriendsInfo();
//			//add数据录入
//			wxFriendsInfo.setCode(GUID.generateByUUID());
//			wxFriendsInfo.setMemberNo(addWxFriendsInfo.getMemberNo());
//			wxFriendsInfo.setMemberName(addWxFriendsInfo.getMemberName());
//			wxFriendsInfo.setAuthorid(addWxFriendsInfo.getAuthorid());
//			wxFriendsInfo.setAuthorname(addWxFriendsInfo.getAuthorname());
//			wxFriendsInfo.setComments(addWxFriendsInfo.getComments());
//			wxFriendsInfo.setContent(addWxFriendsInfo.getContent());
//			wxFriendsInfo.setIdWx(addWxFriendsInfo.getIdWx());
//			wxFriendsInfo.setMedialist(addWxFriendsInfo.getMedialist());
//			wxFriendsInfo.setTimestamp(addWxFriendsInfo.getTimestamp());
//			wxFriendsInfo.setSourcetype(addWxFriendsInfo.getSourcetype());
//			wxFriendsInfo.setType(addWxFriendsInfo.getType());
//			wxFriendsInfo.setImgpathLocal(addWxFriendsInfo.getImgpathLocal());
//			wxFriendsInfo.setCreateDate(new Date());
//			wxFriendsInfoDao.insertSelective(wxFriendsInfo);
//			AddWxFriendsInfoReturn addWxFriendsInfoReturn = new AddWxFriendsInfoReturn();
//			addWxFriendsInfoReturn.setCode(wxFriendsInfo.getCode());
//			addWxFriendsInfoReturn.setIdWx(wxFriendsInfo.getIdWx());
//
//			logger.debug("addWxFriendsInfo(AddWxFriendsInfo) - end - return value={}", addWxFriendsInfoReturn); 
//			return addWxFriendsInfoReturn;
//		}catch (TsfaServiceException e) {
//			logger.error(e.getMessage(),e);
//			throw e;
//		}  catch (Exception e) {
//			logger.error("新增微信朋友圈信息错误！",e);
//			throw new TsfaServiceException(ErrorCode.WX_FRIENDS_INFO_ADD_ERROR,"新增微信朋友圈信息错误！",e);
//		}
//	}
//
//
//
//	/* (non-Javadoc)
//	 * @see com.lj.business.weixin.service.IWxFriendsInfoService#updateWxFriendsInfo(com.lj.business.weixin.dto.UpdateWxFriendsInfo)
//	 */
//	@Override
//	public UpdateWxFriendsInfoReturn updateWxFriendsInfo(
//			UpdateWxFriendsInfo updateWxFriendsInfo)
//					throws TsfaServiceException {
//		logger.debug("updateWxFriendsInfo(UpdateWxFriendsInfo updateWxFriendsInfo={}) - start", updateWxFriendsInfo); //$NON-NLS-1$
//
//		AssertUtils.notNull(updateWxFriendsInfo);
//		AssertUtils.notNullAndEmpty(updateWxFriendsInfo.getCode(),"Code不能为空");
//		try {
//			WxFriendsInfo wxFriendsInfo = new WxFriendsInfo();
//			//update数据录入
//			wxFriendsInfo.setCode(updateWxFriendsInfo.getCode());
//			wxFriendsInfo.setMemberNo(updateWxFriendsInfo.getMemberNo());
//			wxFriendsInfo.setMemberName(updateWxFriendsInfo.getMemberName());
//			wxFriendsInfo.setAuthorid(updateWxFriendsInfo.getAuthorid());
//			wxFriendsInfo.setAuthorname(updateWxFriendsInfo.getAuthorname());
//			wxFriendsInfo.setComments(updateWxFriendsInfo.getComments());
//			wxFriendsInfo.setContent(updateWxFriendsInfo.getContent());
//			wxFriendsInfo.setIdWx(updateWxFriendsInfo.getIdWx());
//			wxFriendsInfo.setMedialist(updateWxFriendsInfo.getMedialist());
//			wxFriendsInfo.setTimestamp(updateWxFriendsInfo.getTimestamp());
//			wxFriendsInfo.setSourcetype(updateWxFriendsInfo.getSourcetype());
//			wxFriendsInfo.setType(updateWxFriendsInfo.getType());
//			wxFriendsInfo.setImgpathLocal(updateWxFriendsInfo.getImgpathLocal());
//			wxFriendsInfo.setSharetitle(updateWxFriendsInfo.getSharetitle());
//			wxFriendsInfo.setShareurl(updateWxFriendsInfo.getShareurl());
//			AssertUtils.notUpdateMoreThanOne(wxFriendsInfoDao.updateByPrimaryKeySelective(wxFriendsInfo));
//			UpdateWxFriendsInfoReturn updateWxFriendsInfoReturn = new UpdateWxFriendsInfoReturn();
//
//			logger.debug("updateWxFriendsInfo(UpdateWxFriendsInfo) - end - return value={}", updateWxFriendsInfoReturn); //$NON-NLS-1$
//			return updateWxFriendsInfoReturn;
//		}catch (TsfaServiceException e) {
//			logger.error(e.getMessage(),e);
//			throw e;
//		} catch (Exception e) {
//			logger.error("微信朋友圈评论表信息更新信息错误！",e);
//			throw new TsfaServiceException(ErrorCode.WX_FRIENDS_INFO_UPDATE_ERROR,"微信朋友圈评论表信息更新信息错误！",e);
//		}
//	}
//
//
//
//	/* (non-Javadoc)
//	 * @see com.lj.business.weixin.service.IWxFriendsInfoService#findWxFriendsInfo(com.lj.business.weixin.dto.FindWxFriendsInfo)
//	 */
//	@Override
//	public FindWxFriendsInfoReturn findWxFriendsInfo(
//			FindWxFriendsInfo findWxFriendsInfo) throws TsfaServiceException {
//		logger.debug("findWxFriendsInfo(FindWxFriendsInfo findWxFriendsInfo={}) - start", findWxFriendsInfo); //$NON-NLS-1$
//
//		AssertUtils.notNull(findWxFriendsInfo);
//		AssertUtils.notAllNull(findWxFriendsInfo.getCode(),"Code不能为空");
//		try {
//			WxFriendsInfo wxFriendsInfo = wxFriendsInfoDao.selectByPrimaryKey(findWxFriendsInfo.getCode());
//			if(wxFriendsInfo == null){
//				throw new TsfaServiceException(ErrorCode.WX_FRIENDS_INFO_NOT_EXIST_ERROR,"微信朋友圈评论表信息不存在");
//			}
//			FindWxFriendsInfoReturn findWxFriendsInfoReturn = new FindWxFriendsInfoReturn();
//			//find数据录入
//			findWxFriendsInfoReturn.setCode(wxFriendsInfo.getCode());
//			findWxFriendsInfoReturn.setMemberNo(wxFriendsInfo.getMemberNo());
//			findWxFriendsInfoReturn.setMemberName(wxFriendsInfo.getMemberName());
//			findWxFriendsInfoReturn.setAuthorid(wxFriendsInfo.getAuthorid());
//			findWxFriendsInfoReturn.setAuthorname(wxFriendsInfo.getAuthorname());
//			findWxFriendsInfoReturn.setComments(wxFriendsInfo.getComments());
//			findWxFriendsInfoReturn.setContent(wxFriendsInfo.getContent());
//			findWxFriendsInfoReturn.setIdWx(wxFriendsInfo.getIdWx());
//			findWxFriendsInfoReturn.setMedialist(wxFriendsInfo.getMedialist());
//			findWxFriendsInfoReturn.setTimestamp(wxFriendsInfo.getTimestamp());
//			findWxFriendsInfoReturn.setImgpathLocal(wxFriendsInfo.getImgpathLocal());
//			findWxFriendsInfoReturn.setCreateDate(wxFriendsInfo.getCreateDate());
//
//			logger.debug("findWxFriendsInfo(FindWxFriendsInfo) - end - return value={}", findWxFriendsInfoReturn); //$NON-NLS-1$
//			return findWxFriendsInfoReturn;
//		}catch (TsfaServiceException e) {
//			throw e;
//		} catch (Exception e) {
//			logger.error("查找微信朋友圈评论表信息错误！",e);
//			throw new TsfaServiceException(ErrorCode.WX_FRIENDS_INFO_FIND_ERROR,"查找微信朋友圈评论表信息错误！",e);
//		}
//
//
//	}
//
//
//	/* (non-Javadoc)
//	 * @see com.lj.business.weixin.service.IWxFriendsInfoService#findWxFriendsInfoPage(com.lj.business.weixin.dto.FindWxFriendsInfoPage)
//	 */
//	@Override
//	public Page<FindWxFriendsInfoPageReturn> findWxFriendsInfoPage(
//			FindWxFriendsInfoPage findWxFriendsInfoPage)
//					throws TsfaServiceException {
//		logger.debug("findWxFriendsInfoPage(FindWxFriendsInfoPage findWxFriendsInfoPage={}) - start", findWxFriendsInfoPage); //$NON-NLS-1$
//
//		AssertUtils.notNull(findWxFriendsInfoPage);
//		List<FindWxFriendsInfoPageReturn> returnList;
//		int count = 0;
//		try {
//			returnList = wxFriendsInfoDao.findWxFriendsInfoPage(findWxFriendsInfoPage);
//			count = wxFriendsInfoDao.findWxFriendsInfoPageCount(findWxFriendsInfoPage);
//		}  catch (Exception e) {
//			logger.error("微信朋友圈评论表信息不存在错误",e);
//			throw new TsfaServiceException(ErrorCode.WX_FRIENDS_INFO_FIND_PAGE_ERROR,"微信朋友圈评论表信息不存在错误.！",e);
//		}
//		Page<FindWxFriendsInfoPageReturn> returnPage = new Page<FindWxFriendsInfoPageReturn>(returnList, count, findWxFriendsInfoPage);
//
//		logger.debug("findWxFriendsInfoPage(FindWxFriendsInfoPage) - end - return value={}", returnPage); //$NON-NLS-1$
//		return  returnPage;
//	}
//
//
//
//	@Override
//	public long getNewDateByWxNo(String wxNo) {
//		AssertUtils.notNull(wxNo);
//
//		//根据微信号获取导购信息
//		FindGuidMemberPage findGuidMemberPage = new FindGuidMemberPage();
//		findGuidMemberPage.setNoWx(wxNo);
//		List<FindGuidMemberPageReturn> guidMemberPageReturns= guidMemberService.findGuidMembers(findGuidMemberPage);
//		if(guidMemberPageReturns==null|| guidMemberPageReturns.size()<=0){
//			logger.error("导购为空！");
//			throw new TsfaServiceException(ErrorCode.WX_CHAT_INFO_ADD_ERROR,"导购为空！");
//		}
//		FindGuidMemberPageReturn findGuidMemberPageReturn= guidMemberPageReturns.get(0);
//		try {
//			FindWxFriendsInfoPage parm =new FindWxFriendsInfoPage();
//			parm.setMemberNo(findGuidMemberPageReturn.getMemberNo());
//			parm.setSortDir(PageSortType.desc);
//			List<FindWxFriendsInfoPageReturn> returnList = wxFriendsInfoDao.findWxFriendsInfoPage(parm);
//			if(returnList!=null && returnList.size()>0){
//				return Long.parseLong(returnList.get(0).getTimestamp());
//			}
//		}  catch (Exception e) {
//			logger.error("朋友圈信息信息不存在错误",e);
//			throw new TsfaServiceException(ErrorCode.WX_CHAT_INFO_FIND_PAGE_ERROR,"朋友圈信息信息不存在错误.！",e);
//		}
//		return  0L;
//	}
//
//
//
//	@Override
//	public int uploadWxFriendsInfo(String paramJson) {
//		/**
//		 * 主方法取消了事务，子方法也没有启用事务
//		 */
//		AssertUtils.notNull(paramJson);
//		int flag =0;
//		try {
//			JSONObject jsonObject = JSONObject.fromObject(paramJson);
//
//			String noWxGM = jsonObject.getString("noWxGM");
//			String memberNoGuid = null;
//			if(jsonObject.containsKey("memberNoGuid"))
//				 memberNoGuid = jsonObject.getString("memberNoGuid");//导购编号
//			JSONArray data = jsonObject.getJSONArray("data");
//			logger.info("uploadWxFriendsInfo start --- noWxGM:"+noWxGM);
//			//根据微信号获取导购信息
//			FindGuidMember findGuidMember = new FindGuidMember();
//			if(!StringUtils.isEmpty(noWxGM))//预防NO_WX为空，造成全表扫描
//				findGuidMember.setNoWx(noWxGM);
//			else if(!StringUtils.isEmpty(memberNoGuid))
//				findGuidMember.setMemberNo(memberNoGuid);
//			else{
//				logger.error("【朋友圈信息上传】微信号，导购会员编号全部为空！");
//				return flag;
//			}
//			FindGuidMemberReturn findGuidMemberReturn = guidMemberService.findGuidMember(findGuidMember);
//			AddSocialTask lastMessage = null;
//			String merchantNo = findGuidMemberReturn.getMerchantNo();
//			String memberNoGm = findGuidMemberReturn.getMemberNo();
//			String memberNameGm = findGuidMemberReturn.getMemberName();
//			String mobileGm = findGuidMemberReturn.getMobile();
//			for (int i = 0; i < data.size(); i++) {
//				//不使用事务
//				lastMessage = wxFriendsProcess(data, lastMessage, merchantNo,memberNoGm, memberNameGm, mobileGm, i);
//			}
//
//			notifyWxFriends.notifyGm(lastMessage);
//			flag=1;
//		} catch (Exception e) {
//			logger.error("朋友圈信息上传错误",e);
//			throw new TsfaServiceException(ErrorCode.WX_COMMENT_INFO_ADD_ERROR,"朋友圈信息上传错误！",e);
//		}
//		return flag;
//	}
//
//
//
//	/**
//	 * 
//	 *
//	 * 方法说明：朋友圈数据处理
//	 *
//	 * @param data
//	 * @param lastMessage
//	 * @param merchantNo
//	 * @param memberNoGm
//	 * @param memberNameGm
//	 * @param mobileGm
//	 * @param i
//	 * @return
//	 *
//	 * @author 彭阳 CreateDate: 2017年10月26日
//	 *
//	 */
//	private AddSocialTask wxFriendsProcess(JSONArray data,
//			AddSocialTask lastMessage, String merchantNo, String memberNoGm,
//			String memberNameGm, String mobileGm, int i) {
//		JSONObject friend = null;
//		try {
//			//朋友圈信息
//		    friend = data.getJSONObject(i);	
//			String idWx = friend.containsKey("id")?friend.getString("id"):null;
//			FindWxFriendsInfoPage findWxFriendsInfoPage = new FindWxFriendsInfoPage();
//			findWxFriendsInfoPage.setIdWx(idWx);
//			findWxFriendsInfoPage.setMemberNo(memberNoGm);
//			List<FindWxFriendsInfoPageReturn> list= wxFriendsInfoDao.findWxFriendsInfoPage(findWxFriendsInfoPage);
//			String codeWfi = "";
//			if(list==null || list.size()==0){
//				AddWxFriendsInfo addWxFriendsInfo= new AddWxFriendsInfo();
//				addWxFriendsInfo.setMemberNo(memberNoGm);
//				addWxFriendsInfo.setMemberName(memberNameGm);
//				String authorid = friend.containsKey("userName")?friend.getString("userName"):"";
//				addWxFriendsInfo.setAuthorid(authorid);
//				logger.info("uploadWxFriendsInfo authorid --- authorid:"+authorid);
//				String nickName = friend.containsKey("nickName")?friend.getString("nickName").toString():"";
//				//钩子微信端发现脏数据
//				if(nickName.length()>50){
//					return lastMessage;
//				}
//				addWxFriendsInfo.setAuthorname(nickName);	
//
//				String comments = friend.containsKey("comments")?friend.getJSONArray("comments").toString():"";
//				addWxFriendsInfo.setComments(comments);
//
//				String content = friend.containsKey("content")?friend.getString("content"):"";
//				addWxFriendsInfo.setContent(content);
//
//				addWxFriendsInfo.setIdWx(idWx);
//				addWxFriendsInfo.setMedialist("");
//				String timestamp = friend.containsKey("createTime")?friend.getString("createTime"):"0";
//				addWxFriendsInfo.setTimestamp(timestamp);
//				addWxFriendsInfo.setSourcetype(friend.containsKey("sourceType")?friend.getString("sourceType"):"");
//				addWxFriendsInfo.setType(friend.containsKey("type")?friend.getString("type"):"");
//				addWxFriendsInfo.setImgpathLocal("");
//
//				String shareTitle = friend.containsKey("shareTitle")?friend.getString("shareTitle"):"";
//				addWxFriendsInfo.setSharetitle(shareTitle);
//
//				addWxFriendsInfo.setShareurl(friend.containsKey("shareUtl")?friend.getString("shareUtl"):"");
//				
//				AddWxFriendsInfoReturn addWxFriendsInfoReturn= addWxFriendsInfo(addWxFriendsInfo);
//
//				//社交任务 必须微信号不为空才产生社交任务， update by leopeng
//				if(StringUtils.isNotEmpty(authorid)){
//					AddSocialTask addSocialTask = new AddSocialTask();
//					addSocialTask.setIdWx(idWx);
//					addSocialTask.setMemberNoGm(memberNoGm);
//					addSocialTask.setMemberNameGm(memberNameGm);
//					addSocialTask.setWorkDate(new Date());
//					addSocialTask.setNoWx(authorid);
//					addSocialTask.setFriendUpdateDate(new Date(Long.valueOf(timestamp+"000")));
//					socialTaskService.addSocialTask(addSocialTask);
//
//					if (lastMessage == null || addSocialTask.getFriendUpdateDate().compareTo(lastMessage.getFriendUpdateDate()) > 0) {
//						lastMessage = addSocialTask;
//					}
//				}
//
//				/*客户更新朋友圈的通知(客户更新朋友圈后触发，接收对象为导购。”你的客户XXX更新了朋友圈哦，快去点个赞吧！”)*/
//				String senContent = "你的客户${nickName}更新了朋友圈哦，快去点个赞吧！";		//TODO 字典表维护
//				AddNotifyInfo addNotifyInfo = new AddNotifyInfo();
//				addNotifyInfo.setMerchantNo(merchantNo);
//				addNotifyInfo.setMemberNo(memberNoGm);
//				addNotifyInfo.setMemberName(memberNameGm);
//				addNotifyInfo.setMemberType(MemberType.GUID.toString());
//				//						addNotifyInfo.setMemberNoSender(memberNoSender);
//				//						addNotifyInfo.setMemberNameSender(memberNameSender);
//				//						addNotifyInfo.setMemberTypeSender(MemberType.);
//				addNotifyInfo.setMobile(mobileGm);
//				addNotifyInfo.setSendType(SendType.SINGLE.toString());
//				addNotifyInfo.setContent(senContent.replace("${nickName}", nickName));
//				addNotifyInfo.setSysType(MsgSystemType.ALL.toString());
//				//notifyService.sendMsgInfo(addNotifyInfo);
//
//				codeWfi =addWxFriendsInfoReturn.getCode(); 
//			}else{
//				codeWfi =list.get(0).getCode(); 
//			}
//
//			//评论信息
//			if(friend.containsKey("comments")){
//				JSONArray comments =friend.getJSONArray("comments");
//				logger.info("uploadWxFriendsInfo comments start --- likes:"+comments.toString());
//				for (int j = 0; j < comments.size(); j++) {
//					JSONObject comment = comments.getJSONObject(j);	
//					AddWxCommentInfo addWxCommentInfo = new AddWxCommentInfo();
//					//add数据录入
//					addWxCommentInfo.setCodeWfi(codeWfi);
//					addWxCommentInfo.setMemberNo(memberNoGm);
//					addWxCommentInfo.setMemberName(memberNameGm);
//					addWxCommentInfo.setIdWx(idWx);
//					addWxCommentInfo.setUsername(comment.containsKey("userName")?comment.getString("userName"):"");
//
//					String nickName = comment.containsKey("nickName")?comment.getString("nickName"):"";
//					addWxCommentInfo.setNickname(nickName);
//
//					String toNickName = comment.containsKey("toNickName")?comment.getString("toNickName"):"";
//					addWxCommentInfo.setTonickname(toNickName);
//
//					String content = comment.containsKey("content")?comment.getString("content"):"";
//					addWxCommentInfo.setContent(content);
//
//					wxCommentInfoService.addWxCommentInfo(addWxCommentInfo);
//				}
//			}
//
//			//点赞信息
//			if(friend.containsKey("likes")){
//				JSONArray likes =friend.getJSONArray("likes");
//				logger.info("uploadWxFriendsInfo likes start --- likes:"+likes.toString());
//				for (int j = 0; j < likes.size(); j++) {
//					JSONObject like = likes.getJSONObject(j);	
//					AddWxLikeInfo addWxLikeInfo = new AddWxLikeInfo();
//					//add数据录入
//					addWxLikeInfo.setCodeWfi(codeWfi);
//					addWxLikeInfo.setMemberNo(memberNoGm);
//					addWxLikeInfo.setMemberName(memberNameGm);
//					addWxLikeInfo.setIdWx(idWx);
//					addWxLikeInfo.setUsername(like.containsKey("userName")?like.getString("userName"):"");
//					String nickName = like.containsKey("nickName")?like.getString("nickName"):"";
//					addWxLikeInfo.setNickname(nickName);
//					wxLikeInfoService.addWxLikeInfo(addWxLikeInfo);
//				}
//			}
//		} catch (Exception e) {
//			if(friend != null)
//				logger.error("【朋友圈信息上传】info:"+friend.toString());
//			logger.error("【朋友圈信息上传】朋友圈信息上传",e);
//		}
//		return lastMessage;
//	} 
//	
//	
//	
//	
//
//}
