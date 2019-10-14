/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.controller.im;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.base.core.pagination.IPage;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.api.common.ErrorCode;
import com.lj.business.api.controller.Action;
import com.lj.business.api.domain.GeneralResponse;
import com.lj.business.api.domain.ResponseCode;
import com.lj.business.member.domain.ChatRoom;
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.dto.FindImIndexPage;
import com.lj.business.member.dto.FindImIndexPageReturn;
import com.lj.business.member.dto.addfriends.DistributionMember;
import com.lj.business.member.dto.addfriends.FindClaimMemberPage;
import com.lj.business.member.dto.addfriends.FindClaimMemberPageReturn;
import com.lj.business.member.dto.addfriends.SyncFriendsDetail;
import com.lj.business.member.dto.addfriends.SyncFriendsFromZk;
import com.lj.business.member.dto.im.FindImFriendsPage;
import com.lj.business.member.dto.im.FindImFriendsPageReturn;
import com.lj.business.member.dto.im.FindMemberWxByNoWxGm;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.weixin.service.IWxFriendsDataService;
import com.lj.distributecache.IDistributeCache;
import com.lj.distributelock.IDistributeLock;
import com.lj.distributelock.RedisLock;

import net.sf.json.JSONObject;

/**
 * 
 * 类说明：IM-通讯录相关服务
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年10月28日
 */
@Controller
@RequestMapping(value = "/im/contacts/")
public class ContactsAction extends Action {

	private static Logger logger = LoggerFactory.getLogger(ChatAction.class);

	
	@Resource
	public IPersonMemberBaseService personMemberBaseService;
	
	@Resource
	public IPersonMemberImService personMemberImService;

	@Resource
	IWxFriendsDataService wxFriendsDataService;
	
	@Resource
	IPersonMemberService personMemberService;
	
	@Resource
	public IAddFriendsService addFriendsService;
	
	@Resource
	public IDistributeLock redisLock;
	
	@Resource
	public IDistributeCache redis;
	
	@Autowired
	private IChatRoomService chatRoomService;
	@Autowired 
	ICommonService commonService;

	
	/**
	 * 方法说明：设置消息置顶
	 * String roomType     1 个人  2 群
     * String roomCode     当roomType 为1 可空，  当roomType 为2 非空
      *String memberNo     当roomType 为1  非空
     * String setType      1：设置   0取消
	 */
	@RequestMapping(value = "setUpUser.do")
	@ResponseBody
	public GeneralResponse setUpUser(String roomType,String roomCode, String memberNo,String setType, String noWxGm,String memberNoGm){
		AssertUtils.notNullAndEmpty(roomType);
		AssertUtils.notNullAndEmpty(setType);
		PersonMemberBase personMemberBase = new PersonMemberBase();
		try {
			//个人设置置顶
			if(roomType.equals("1")) {
				personMemberBase.setSetUpUser(setType);
				personMemberBase.setMemberNo(memberNo);
				personMemberBaseService.updateCancleSetUpUser(personMemberBase);
				
				ICommonService basic= commonService.getHessianCommonService(noWxGm);
				basic.sendSetUpUser(memberNoGm,noWxGm,  memberNo, setType);
			}
			
			//设置群置顶
			if(roomType.equals("2")) {
				ChatRoom updateChatRoom = new ChatRoom();
				updateChatRoom.setCode(roomCode);
				updateChatRoom.setSetUpUser(setType);
				chatRoomService.updateSetUpUser(updateChatRoom);	
				ICommonService basic= commonService.getHessianCommonService(noWxGm);
				basic.sendSetUpUser(memberNoGm,noWxGm,roomCode, setType);
			}
			
			return GeneralResponse.generateSuccessResponse();
		}catch(Exception e) {
			logger.error("app設置置頂錯誤", e);
			return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "roomType或setType为空", null);
		}
	}
	
	
	/**
	 * 方法说明：查询所有消息置顶的用户
	 * String roomType     1 个人  2 群
	 */
	@RequestMapping(value = "selectSetUpList.do")
	@ResponseBody
	public Map<String,String> selectSetUpUser(String noWxGm){
		AssertUtils.notNullAndEmpty(noWxGm);

		Map<String,String> map =new HashMap<String,String>();
		try {
			FindImIndexPage findImIndex = new FindImIndexPage();
			findImIndex.setLimit(500);
			findImIndex.setStart(0);
			findImIndex.setSetUpUser("1"); //1为置顶用户， 0 为取消置顶
			findImIndex.setNoWx(noWxGm);//导购微信号
			String topid= "";
			List<FindImIndexPageReturn> list = personMemberService.findImIndexList(findImIndex);
			for (FindImIndexPageReturn person : list) {
				topid = topid +","+ person.getMemberNo();
			}
			map.put("topid", topid);
		}catch(Exception e) {
			logger.error("app設置置頂錯誤", e);
		}
		return map;
	}
	
	@RequestMapping(value = "selectSetUp.do")
	@ResponseBody
	public String selectSetUp(String memberNo,String roomCode){
		String setup = null;
		try {
			if(StringUtils.isNotEmpty(roomCode)){	//roomCode不为空,查询群聊是否置顶
				setup = chatRoomService.selectSetUp(roomCode);
			}else{	//查询个人聊天是否置顶
				setup = personMemberBaseService.selectSetUp(memberNo);
			}
		} catch (Exception e) {
			logger.error("查询置顶错误!",e);
		}
		
		return setup;
	}

	/**
	 * 
	 *
	 * 方法说明：查询通讯录（导购客户端）
	 *
	 * @param findImFriendsPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月28日
	 *
	 */
	@RequestMapping(value = "findFriends.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public IPage<FindImFriendsPageReturn> findFriends(FindImFriendsPage findImFriendsPage) {
		//如果是老板账号可以查询所有用户
		if(findImFriendsPage.getAdminUserId()!=null && findImFriendsPage.getAdminUserId()){
			findImFriendsPage.setMemberNoGm(null);
		}
		IPage<FindImFriendsPageReturn> page = personMemberImService.findImFriends(findImFriendsPage);
		return page;
	}

	/**
	 * 
	 *
	 * 方法说明：查询通讯录（中控客户端）
	 *
	 * @param shopNo
	 * @param noWxGms
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月16日
	 *
	 */
	@RequestMapping(value = "findFriendsFromZk.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, List<String>> findFriendsFromZk(String shopNo, String noWxGms) {
		AssertUtils.notNullAndEmpty(shopNo);
		AssertUtils.notNullAndEmpty(noWxGms);
		FindMemberWxByNoWxGm findMemberWxByNoWxGm = new FindMemberWxByNoWxGm();
//		findMemberWxByNoWxGm.setShopNo(shopNo);
		findMemberWxByNoWxGm.setNoWxGms(noWxGms.split(","));
		return personMemberImService.findMemberWxByNoWxGm(findMemberWxByNoWxGm);
	}

	/**
	 * 
	 *
	 * 方法说明：同步通讯录(中控客户端)
	 *
	 * @param shopNo
	 * @param noWxGms
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月16日
	 *
	 */
	@RequestMapping(value = "syncFriendsFromZk.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse syncFriendsFromZk(String paramJson,String noWxGm) {
		logger.info("syncFriendsFromZk(String paramJson={}) - start", paramJson); 
		if (StringUtils.isEmpty(paramJson)) {
			logger.error("待同步客户数据为空");
			return GeneralResponse.generateResponse(Boolean.FALSE, ErrorCode.PARAM_ERROR, "客户数据为空", null);
		}
		AssertUtils.notAllNullAndEmpty(noWxGm, "终端微信不能为空");
		@SuppressWarnings("rawtypes")
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("friendsList", SyncFriendsDetail[].class);
		SyncFriendsFromZk syncContactsRequest = (SyncFriendsFromZk) JsonUtils.objectFromJson(paramJson, SyncFriendsFromZk.class, classMap);
		try {
			addFriendsService.syncFriendsList(noWxGm,syncContactsRequest);
		} catch (Exception e) {
			logger.error("同步终端客户数据失败", e);
		}

		logger.info("syncFriendsFromZk(String paramJson) - end"); 
		return GeneralResponse.generateSuccessResponse();
	}

	/**
	 * 
	 *
	 * 方法说明：客户认领分页查询
	 *
	 * @param currentMemberNoGm
	 * @param findClaimMemberPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月23日
	 *
	 */
	@RequestMapping(value = "findMemberClaimPage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public IPage<FindClaimMemberPageReturn> findMemberClaimPage(String noWxGm,String currentMemberNoGm, Boolean memberNoGmFlag, FindClaimMemberPage findClaimMemberPage) {
		findClaimMemberPage.setMemberNoGm(currentMemberNoGm);
		if(memberNoGmFlag == null) {
			memberNoGmFlag = false;
		}
		return addFriendsService.findClaimMemberPage(findClaimMemberPage, memberNoGmFlag,noWxGm);
	}
	
	
	/**
	 * 取消客户绑定 (解认领)
	 */
	@RequestMapping(value="/cancleBingFriends.do")
	@ResponseBody
	public  GeneralResponse cancleBingFriends(String[] wxStr,String paramJson,String memberNoGm,String noWxGm){
        logger.info("cancleBindFriends :" + paramJson);
		try {
			for(String wxNo : wxStr) {
				if(StringUtils.isNotEmpty(wxNo)) {
					//置空记录
		            personMemberService.updateCanclePersonMember(memberNoGm, wxNo,noWxGm);
		            //清空friend导购信息
		            addFriendsService.updateCancleAddFriendsData(memberNoGm, wxNo,noWxGm);
		            //清空朋友圈信息
		            wxFriendsDataService.updateCancleFriendsCommentData(memberNoGm, wxNo, null, noWxGm);
		          //通知APP取消认领成功
		            personMemberService.sendTransSuccess(memberNoGm, null, "2", noWxGm,wxNo);
				}
	            
			}
            
            //各种任务统计
            //innerProcess(toShopFlag, guidMember, personMemberBase, addPersonMember, ptp, ptp2);
            
            logger.debug(" data cancleBingFriends success :{}", "void");
			return GeneralResponse.generateSuccessResponse();
		} catch (TsfaServiceException e){
			logger.error("取消客户绑定异常",e);
			return GeneralResponse.generateFailureResponse(e.getExceptionCode(),"取消客户绑定异常");
		}
	}
	
	/**
	 * 店长管理员取消客户绑定 (解认领)
	 */
	@RequestMapping(value="/cancleBingFriendsAdmin.do")
	@ResponseBody
	public  GeneralResponse cancleBingFriendsAdmin(String[] wxStr,String[] gmNo,String paramJson,String noWxGm){
        logger.info("cancleBingFriendsAdmin :" + paramJson);
		try {
			for(int i=0;i<wxStr.length;i++) {
				
				String noWx = wxStr[i];
				String memberNoGm = gmNo[i];
				if(StringUtils.isNotEmpty(noWx) && StringUtils.isNotEmpty(memberNoGm)) {
					//置空记录
		            personMemberService.updateCanclePersonMember(memberNoGm, noWx,noWxGm);
		            //清空friend导购信息
		            addFriendsService.updateCancleAddFriendsData(memberNoGm, noWx,noWxGm);
		            //清空朋友圈信息
		            wxFriendsDataService.updateCancleFriendsCommentData(memberNoGm, noWx, null, noWxGm);
		            //通知APP取消认领成功
		            personMemberService.sendTransSuccess(memberNoGm,null , "2", noWxGm,noWx);
				}
			}
            
            //各种任务统计
            //innerProcess(toShopFlag, guidMember, personMemberBase, addPersonMember, ptp, ptp2);
            
            logger.debug(" data cancleBingFriendsAdmin success :{}", "void");
			return GeneralResponse.generateSuccessResponse();
		} catch (TsfaServiceException e){
			logger.error("取消客户绑定异常",e);
			return GeneralResponse.generateFailureResponse(e.getExceptionCode(),"取消客户绑定异常");
		}
	}
	
	
	/**
	 * 方法说明：同一终端微信是否存在员工自动认领
	 * @param noWx 客户微信号
	 * @param memberNoGm 导购编号
	 * @param noWxGm 导购微信
	 */
	@RequestMapping(value = "getClaimExist.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse getAutoClaimExist(HttpServletRequest request ,String paramJson) {
		logger.info("getAutoClaimExist(String noWx, String memberNoGm) - end returnValues = {}", paramJson +"");
		GeneralResponse gr = null;
		try {
			JSONObject object = JSONObject.fromObject(paramJson);
			
	        String noWx = object.get("noWx").toString();
			String lockName = RedisLock.LOCK_NAME_PREFIX + noWx;
			ResponseCode rc = new ResponseCode();
			//查询是否存在
			String value = redis.get(lockName);
			if( value != null && !value.equals("")) {
				 gr = GeneralResponse.generateSuccessResponse();
				 Long howlong = redis.ttl(lockName);
				 
				 //已过期或者不设置过期时间
				 if(howlong == -1L || howlong == -2L) {
					 rc.setCode("Y");//没有相关导购开启自动认领，可使用
					 rc.setMessage("没有相关导购开启自动认领");
					 gr.setReturnObject(rc);
				 }
				 
				 //查询到过期时间
				 if(howlong != -1L || howlong != -2L) {
					 rc.setCode("N");//存在导购开启自动认领，不可使用，返回秒数
					 rc.setMessage(howlong.toString());
					 gr.setReturnObject(rc);
				 }
				 
			}else{
				 gr = GeneralResponse.generateSuccessResponse();
				 rc.setCode("Y");//不存在,可使用
				 rc.setMessage("没有相关导购开启自动认领");
				 gr.setReturnObject(rc);
			};
		}catch(Exception e) {
			logger.error("getAutoClaimExist", e);
			gr = GeneralResponse.generateFailureResponse();
			gr.setErrorMessage("参数值错误");
		}
		logger.info("getAutoClaimExist(String noWx, String memberNoGm, String noWxGm) - end returnValues = {}", gr); 
		return gr;
	}
	
	
	
	/**
	    * 方法说明：同一终端微信员工设置自动认领
	 * @param noWx 客户微信号
	 * @param memberNoGm 导购编号
	 * @param noWxGm 导购微信
	 */
	@RequestMapping(value = "doAutoClaim.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse doAutoClaim(HttpServletRequest request ,String paramJson) {
		logger.info("getAutoClaimExist(String noWx, String memberNoGm, String noWxGm) - end returnValues = {}", paramJson +"");
		GeneralResponse gr = null;
		try {
			JSONObject object = JSONObject.fromObject(paramJson);
			
	        String noWx = object.get("noWx").toString();
	        String memberNoGm = object.get("memberNoGm").toString();
	        
			String lockName = RedisLock.LOCK_NAME_PREFIX + noWx;
			Integer timeOut = 32;
			Integer lockExpireSeconds = 30;
			String lockValue = System.currentTimeMillis() + "," + memberNoGm +","+noWx;

			ResponseCode rc = new ResponseCode();
			gr = GeneralResponse.generateSuccessResponse();
			String value = redis.get(lockName);
			//不存在锁定
			if( (value==null || value.equals("")) && redisLock.getLockNoWait(lockName, lockValue, lockExpireSeconds, timeOut) != null ) {
				 
		         
				 rc.setCode("Y");//锁定成功
				 rc.setMessage(String.valueOf(lockExpireSeconds));
				 gr.setReturnObject(rc);
				 return gr;
		    //已存在锁定
			}else{
				 
                 Long howlong = redis.ttl(lockName);
               //  String value = redis.get(lockName);
                 
				 //已过期或者不设置过期时间
				 if(howlong == -1L || howlong == -2L) {
					 rc.setCode("O");//存在导购开启自动认领
					 rc.setMessage("系统繁忙,请从试");
					 gr.setReturnObject(rc);
					 return gr;
				 }
				 
				 //查询到过期时间 
				 if(howlong != -1L || howlong != -2L) {
					 
                     //查看是否属于自己之前锁定的
					 if(value != null && !value.equals("")) {
						 String [] array = value.split(",");
						  if(array[1].equals(memberNoGm)) {
							     rc.setCode("Y");//存在导购开启自动认领,，返回秒数
								 rc.setMessage(howlong.toString());
								 gr.setReturnObject(rc);
								 return gr;
						  };
					 }
					 //不属于自己之前锁定的
					 rc.setCode("N");//存在导购开启自动认领,，返回秒数
					 rc.setMessage(howlong.toString());
					 gr.setReturnObject(rc);
					 return gr;
				 }
				 
			};
		}catch(Exception e) {
			logger.error("doAutoClaim", e);
			gr = GeneralResponse.generateFailureResponse();
			gr.setErrorMessage("参数值错误");
		}
		logger.info("doAutoClaim(String noWx, String memberNoGm, String noWxGm) - end returnValues = {}", gr); 
		return gr;
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户认领
	 *
	 * @param mbrCodes				认领code列表，用英文逗号隔开
	 * @param currentMemberNoGm		当前登录导购编号
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月24日
	 *
	 */
	@RequestMapping(value = "claimMembers.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public GeneralResponse claimMembers(String mbrCodes, String currentMemberNoGm) {
		logger.info("claimMembers(String mbrCodes={}) - start", mbrCodes);
		
	
		
		if(StringUtils.isEmpty(mbrCodes)) {
			AssertUtils.notNullAndEmpty(mbrCodes, "请选择未认领客户");
		}
		
		DistributionMember distributionMember = new DistributionMember();
		distributionMember.setMemberNoGm(currentMemberNoGm);
		for (String mbrCode : mbrCodes.split(",")) {
			distributionMember.setCode(mbrCode);
			addFriendsService.distributionMember(distributionMember);
		}
		
		GeneralResponse gr = GeneralResponse.generateSuccessResponse();
		logger.info("claimMembers(String mbrCodes) - end returnValues = {}", gr); 
		return gr;
	}
}
