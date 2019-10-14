/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service;

import java.util.List;
import java.util.Map;

import com.lj.business.supcon.dto.common.VersionInfoMessage;

/**
 * 
 * 类说明：公共服务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月27日
 */
public interface ICommonService {
	
	/**
	 * 方法说明：发送置顶用户信息
	 * @param memberNoGm
	 * @param noWxGm
	 * @param loginName
	 *@param  memberNo
	 * @param setType
	 */
	public void sendSetUpUser( String memberNoGm,String noWxGm, String memberNo, String setType);

	/**
	 * 
	 *
	 * 方法说明：向终端下发上传日志文件指令
	 *
	 * @param imei			手机串号
	 * @param beginTime		上传开始时间，格式yyyyMMddHHmmss
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月27日
	 *
	 */
	public void sendUploadTerminalLogFilesMessage(String imei, String beginTime);
	
	/**
	 * 
	 *
	 * 方法说明：下发服务器最新版本信息
	 *
	 * @param versionInfoMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月9日
	 *
	 */
	public void sendVersionInfoMessage(VersionInfoMessage versionInfoMessage);
	
	/**
	 * 
	 *
	 * 方法说明：获取中控终端在线状态
	 *
	 * @param noWx
	 * @return	true在线、false离线
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月23日
	 *
	 */
	public boolean getZkTerminalStatus(String noWx);
	
	/**
	 * 
	 *
	 * 方法说明：批量获取中控终端在线状态
	 *
	 * @param noWxList
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年2月1日
	 *
	 */
	public Map<String, Boolean> getZkTerminalStatusList(List<String> noWxList);
	
	/**
	 * 
	 *
	 * 方法说明：获取客户端在线状态
	 *
	 * @param account	客户端登录账号：1、导购客户端-导购编号；2、中控客户端-终端串号
	 * @return	true在线、false离线
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月23日
	 *
	 */
	public boolean getClientStatus(String account);
	/**
	 * 
	 *
	 * 方法说明：获取中控在线状态
	 *
	 * @param noWx	中控微信账号 
	 * @return	true在线、false离线
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月23日
	 *
	 */
//	public boolean getZKClientStatus(String noWx);
	/**
	 * 
	 *
	 * 方法说明：下发签到命令
	 *
	 * @param imei
	 * @param token
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月27日
	 *
	 */
	public void sendSignCommandMessage(String noWxGm, String token);
	
	/**
	 * 下发签到结果
	 * 1.TCP登录完直接下发
	 * 2.修改支付密码直接下发
	 * @param noWxGm
	 * @param token
	 */
	public void sendSignResponse(String noWxGm,  String token,String encrypt,Long timestamp);
	
	/**
	 * 
	 *
	 * 方法说明：打开或关闭朋友圈上传功能
	 *
	 * @param imei
	 * @param uploadFriends
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月15日
	 *
	 */
	public void sendUploadFriendsFlagCommand(String imei, boolean uploadFriends);
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public ICommonService getHessianCommonService(String noWx);
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IContactsService getHessianContactsService(String noWx);
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IChatService getHessianChatService(String noWx);
	
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IChatFriendsFacade getHessianIChatFriendsFacade(String noWx);
	
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IRedPackFacade getHessianIRedPackFacade(String noWx);
	
	

	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IWxChatRoomService getHessianWxChatRoomService(String noWx);
	
	/**
 * 集群环境动态hessianUri
 * @param noWx
 * @return
 */
    public IWxAccountService getHessianIWxAccountService(String noWx);
    
    
    public IRedPacketService getHessianIRedPacketService(String noWx);
    
    /**
     * 发送消息未读数报文
     * @param memberNo
     * @param num
     */
    public void sendChatInfoReadNum(String memberNoGm,String memberNo, int num);
}
