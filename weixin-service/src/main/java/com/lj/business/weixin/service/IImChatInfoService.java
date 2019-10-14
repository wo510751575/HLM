package com.lj.business.weixin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.domain.ImChatInfo;
import com.lj.business.weixin.domain.ImChatInfoTemp;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.dto.imchat.AddImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.DelImChatInfo;
import com.lj.business.weixin.dto.imchat.DelImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindAutoAnswerChatInfo;
import com.lj.business.weixin.dto.imchat.FindAutoAnswerChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindChatInfoPageFromWeb;
import com.lj.business.weixin.dto.imchat.FindChatInfoPageFromWebReturn;
import com.lj.business.weixin.dto.imchat.FindChatTimesByGmAndMemberNo;
import com.lj.business.weixin.dto.imchat.FindHistoryChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoPage;
import com.lj.business.weixin.dto.imchat.FindImChatInfoPageReturn;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindMemberChatCount;
import com.lj.business.weixin.dto.imchat.FindMemberChatCountReturn;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfo;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMember;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMemberReturn;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByTerminal;
import com.lj.business.weixin.dto.imchat.ReceivedOfflineChatInfo;
import com.lj.business.weixin.dto.imchat.SendChatByNewPerson;
import com.lj.business.weixin.dto.imchat.SendChatToGm;
import com.lj.business.weixin.dto.imchat.SendImChatByNonMember;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfoReceived;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.UpdateThirdHaveReadFromWeb;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IImChatInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加IM聊天记录信息
	 *
	 * @param addImChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddImChatInfoReturn addImChatInfo(AddImChatInfo addImChatInfo) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：同步历史聊天记录
	 *
	 * @param addImChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddImChatInfoReturn addImOldChatInfo(ImChatInfo ImChatInfo) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找IM聊天记录信息
	 *
	 * @param findImChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindImChatInfoReturn findImChatInfo(FindImChatInfo findImChatInfo) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除IM聊天记录信息
	 *
	 * @param delImChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelImChatInfoReturn delImChatInfo(DelImChatInfo delImChatInfo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改IM聊天记录信息
	 *
	 * @param updateImChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateImChatInfoReturn updateImChatInfo(UpdateImChatInfo updateImChatInfo)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：根据msgId更新IM聊天记录信息状态
	 *
	 * @param updateImChatInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年10月27日
	 *
	 */
	public UpdateImChatInfoReturn updateImChatInfoStatus(UpdateImChatInfo updateImChatInfo)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：更新聊天记录已接收
	 *
	 * @param updateImChatInfoReceived
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月5日
	 *
	 */
	public void updateImChatInfoReceived(UpdateImChatInfoReceived updateImChatInfoReceived);

	/**
	 * 
	 *
	 * 方法说明：查找IM聊天记录信息
	 *
	 * @param findImChatInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindImChatInfoPageReturn> findImChatInfoPage(FindImChatInfoPage findImChatInfoPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找IM聊天记录信息数量
	 *
	 * @param findImChatInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年10月27日
	 *
	 */
	public int findImChatInfoPageCount(FindImChatInfoPage findImChatInfoPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：手机客户端查询离线消息
	 *
	 * @param findOfflineChatInfo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 *
	 */
	public FindOfflineChatInfoReturn findOfflineChatInfo(FindOfflineChatInfo findOfflineChatInfo);
	
	/**
	 * 
	 *
	 * 方法说明：客户端已接收到服务器推送的离线聊天记录，则更新这些离线记录为已发送
	 *
	 * @param receivedOfflineChatInfo
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 *
	 */
	public void receivedOfflineChatInfo(ReceivedOfflineChatInfo receivedOfflineChatInfo);

	/**
	 * 
	 *
	 * 方法说明：按年月日分组查询OMS所需
	 *1.memberNo 导购编号
	 *2.startTime 开始时间
	 *3.endTime 结束时间
	 *4.talker 客户微信号
	 *5.start 开始行
	 *6.limit 查询条数
	 * @param parmMap
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月06日
	 *
	 */
	public Page<Map<String, Object>> findImChatInfoPageOMS(Map<String, Object> parmMap) throws TsfaServiceException;
	
	/**
	 * 方法说明：按日期,导购姓名,终端名称查询导购单日与多少客户沟通
	 *
	 * @param 1.memberNamegm 导购姓名
	 * 2.shopName 终端名称
	 * 3.startTime 聊天开始日期
	 * 4.endTime 聊天结束日期
	 * 5.start 分页开始
	 * 6.limit 每页尺寸
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 李端强 CreateDate: 2017年12月12日
	 *
	 */
	public Page<Map<String, Object>> findImChatStatisticsOMS(Map<String, Object> parmMap) throws TsfaServiceException;

	
	/**
	 * 
	 *
	 * 方法说明：未读客户数
	 *
	 * @param findUnreadCountByTerminal
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public List<Map<String, Object>> findUnreadPersonCountByWx(FindUnreadCountByTerminal findUnreadCountByTerminal);
	
	
	/**
	 * 导购未读数
	 * @param findUnreadCountByTerminal
	 * @return
	 */
	public List<Map<String, Object>> findUnreadPersonCountByGm(FindUnreadCountByTerminal findUnreadCountByTerminal);
	/**
	 * 
	 *
	 * 方法说明：客户未读聊天记录数统计（导购助手）
	 *
	 * @param findUnreadCountByTerminal
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public List<FindUnreadCountByMemberReturn> findUnreadCountByMemberFromWeb(FindUnreadCountByMember findUnreadCountByMember);
	
	/**
	 * 
	 *
	 * 方法说明：查询客户聊天记录（导购助手）-按聊天时间倒序排序
	 *
	 * @param findChatInfoPageFromWeb
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public IPage<FindChatInfoPageFromWebReturn> findChatInfoPageFromWeb(FindChatInfoPageFromWeb findChatInfoPageFromWeb);
	
	/**
	 * 
	 *
	 * 方法说明：更新聊天记录为已读
	 *
	 * @param updateThirdHaveReadFromWeb
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public void updateThirdHaveReadFromWeb(UpdateThirdHaveReadFromWeb updateThirdHaveReadFromWeb);

	/**
	 * 
	 *
	 * 方法说明：查找该段时间类微信聊天的导购
	 *
	 * @param param
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年12月7日
	 *
	 */
	public List<String> findImByDate(Map<String, Object> param);

	/**
	 * 
	 *
	 * 方法说明：查询导购某天微信聊天统计
	 *
	 * @param param
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年12月7日
	 *
	 */
	public List<Map<String, Object>> findCountImChatByGM(Map<String, Object> param);

	/**
	 * 
	 *
	 * 方法说明：查询im聊天的第一条聊天记录
	 *
	 * @param findImChatInfo
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年12月7日
	 *
	 */
	public ImChatInfo findImFristInfo(FindImChatInfo findImChatInfo);
	
	/**
	 * 
	 *
	 * 方法说明：新增客户后，将只有客户微信没有客户编号的聊天记录发送给导购
	 *
	 * @param sendChatByNewPerson
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月22日
	 *
	 */
	public void sendChatByNewPerson(SendChatByNewPerson sendChatByNewPerson);
	
	/**
	 * 
	 *
	 * 方法说明：向客户发送IM聊天记录(无事务)<br>
	 * 适用场景：<br>
	 * 1、导购助手与客户聊天<br>
	 * 2、系统级应用向客户自动发送消息
	 *
	 * @param chat
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月25日
	 *
	 */
	public void sendChat(SendImChatInfo chat);
	
	/**
	 * 
	 *
	 * 方法说明：向未认领客户发送IM聊天记录（没有客户信息，memberNo为空）
	 * 应用场景如：客户主动添加终端微信为好友后，系统自动向客户发送一条优惠券聊天记录
	 *
	 * @param chatByNonMember
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月29日
	 *
	 */
	public void sendChatByNonMember(SendImChatByNonMember chatByNonMember);
	
	/**
	 * 
	 *
	 * 方法说明：向导购发送聊天记录
	 * 应用场景：给客户发送红包成功后，生成一条红包类型的聊天记录发送给导购
	 *
	 * @param sendChatToGm
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月30日
	 *
	 */
	public void sendChatToGm(SendChatToGm sendChatToGm);
	
	/**
	 * 
	 *
	 * 方法说明：查找导购与客户的最后一条聊天记录
	 *
	 * @param findAutoAnswerChatInfo
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月30日
	 *
	 */
	public FindAutoAnswerChatInfoReturn getLastChatInfo(FindAutoAnswerChatInfo findAutoAnswerChatInfo);
	
	/**
	 * 
	 *
	 * 方法说明：电商在添加一个终端店长微信成功后，5分钟无回应，则推送一个二维码
	 *
	 * @param paramMap
	 * @param merchantNo 商户号
	 * @param noWxGm 导购微信号
	 * @param noWx 客户微信号
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2018年4月2日
	 *
	 */
	public int findImChatInfoByMecCount(Map<String,Object> paramMap) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：统计客户与导购的聊天记录数
	 *
	 * @param findMemberChatCount
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月18日
	 *
	 */
	public List<FindMemberChatCountReturn> stsMemberChatCount(FindMemberChatCount findMemberChatCount);
	
	/**
	 * 
	 *
	 * 方法说明：根据导购和客户以及时间查询聊天次数
	 *
	 * @param findChatTimesByGmAndMemberNo
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年8月7日
	 *
	 */
	int findChatTimesByGmAndMemberNo(FindChatTimesByGmAndMemberNo findChatTimesByGmAndMemberNo);
	
	/**
	 * 
	 *
	 * 方法说明： 请求撤销聊天记录
	 *
	 * @param code
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月30日
	 *
	 */
	public void toCancelChatInfo(String code); 
	
	/**
	 * 方法说明：查找最后一条聊天记录
	 * @param findImChatInfo
	 * @return
	 * @throws TsfaServiceException
	 */
	public FindImChatInfoReturn findLastImChatInfo(FindImChatInfo findImChatInfo) throws TsfaServiceException;
	
	/**
	 * 方法说明：同步历史聊天记录
	 * @param noWxGm
	 */
	public void syncNoWxGmRequest(String noWxGm,String chatTime) ;
	
	/**
	 * 方法说明移动端查询历史聊天记录
	 * @param FindHistoryChatInfo
	 * @return
	 */
	public IPage<FindHistoryChatInfo> findHistoryChatInfoPage(FindImChatInfoPage findHistoryChatInfo);
	
	/**
	 * 请求下载文件
	 * @param msgId
	 * @param content
	 * @param findFlag
	 */
    void requestZkUploadChatFile(String msgId, String content, int findFlag);

	/**
	 * 下载视频
	 * @param msgId
	 * @param content
	 * @param findFlag
	 */
    void requestZkUploadChatVideo(String msgId, String content, int findFlag);


	/**
	 *@Desc 
	 *@param updateImChatInfo
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午5:33:31
	 */
	public void delete(UpdateImChatInfo updateImChatInfo);




	/**
	 *@Desc 
	 *@param merchantNo
	 *@param noWx
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月8日上午11:15:14
	 */
	public void deleteTemp(String merchantNo, String noWx);
	
	/**
	 * 查找商户下每个终端客户最新的一条聊天记录
	 * @param findHistoryChatInfo
	 * @return
	 * @author lhy 2019.05.14
	 */
	public IPage<FindHistoryChatInfo> findLastHistoryChatInfoPage(FindImChatInfoPage findHistoryChatInfo) throws TsfaServiceException;


	/**
	 *@Desc 
	 *@param map
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月1日上午11:14:53
	 */
	public int selectChatCount(Map map)throws TsfaServiceException;


	/**
	 *@Desc 
	 *@param mapChat
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月5日下午12:28:58
	 */
	public int findChatCount(Map mapChat);
	
	/**
	 * 
	 *
	 * 方法说明：查询客户聊天记录（导购助手）-记录总数<p>
	 * 统计导购，所有导购助手微信的未读总数。
	 *
	 * @param findUnreadCountByTerminal
	 * @return
	 * @author  
	 *
	 */
	public int findUnreadPersonCount(FindUnreadCountByTerminal findUnreadCountByTerminal)  throws TsfaServiceException;
	
}
