package com.lj.business.weixin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.domain.ImChatInfo;
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
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfoGroup;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMember;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByMemberReturn;
import com.lj.business.weixin.dto.imchat.FindUnreadCountByTerminal;
import com.lj.business.weixin.dto.imchat.ReceivedOfflineChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateThirdHaveReadFromWeb;

public interface IImChatInfoDao {
    int deleteByPrimaryKey(String code);

    int insert(ImChatInfo record);

    int insertSelective(ImChatInfo record);

    ImChatInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ImChatInfo record);

    int updateByPrimaryKeyWithBLOBs(ImChatInfo record);

    int updateByPrimaryKey(ImChatInfo record);
    
    /**
     * 
     *
     * 方法说明：查询IM聊天记录列表
     *
     * @param parmMap
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年10月27日
     *
     */
    List<FindImChatInfoPageReturn> findImChatInfoPage(FindImChatInfoPage findImChatInfoPage);
    /**
     * 
     *
     * 方法说明：查询IM聊天记录数量
     *
     * @param parmMap
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年10月27日
     *
     */
	int findImChatInfoPageCount(FindImChatInfoPage findImChatInfoPage);
	
	/**
	 * 
	 *
	 * 方法说明：查找离线聊天记录信息-列表
	 *
	 * @param findOfflineChatInfo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月30日
	 *
	 */
	public List<FindOfflineChatInfoGroup> findOfflineChatInfo(FindOfflineChatInfo findOfflineChatInfo);
	
	/**
	 * 
	 *
	 * 方法说明：客户端已接收到服务器推送的离线聊天记录，则更新这些离线记录为已发送
	 *
	 * @param receivedOfflineChatInfo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月6日
	 *
	 */
	public int updateOfflineChatInfoToSend(ReceivedOfflineChatInfo receivedOfflineChatInfo);
	
	/**
	 * 
	 *
	 * 方法说明：导购客户端已接收到由第三方（导购助手）代替导购发送的离线消息，需更新其同步状态
	 *
	 * @param receivedOfflineChatInfo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月18日
	 *
	 */
	public int updateOfflineChatInfoToSync(ReceivedOfflineChatInfo receivedOfflineChatInfo);
	
	/**
	 * 
	 *
	 * 方法说明： 修改聊天记录为已发送
	 *
	 * @param codeList
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月30日
	 *
	 */
	public void updateChatInfoToSend(List<String> codeList);

	/**
	 * 
	 *
	 * 方法说明： 查找OMS IM聊天记录信息
	 *
	 * @param parmMap
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月06日
	 *
	 */
	List<Map<String, Object>> findImChatInfoPageOMS(Map<String, Object> parmMap);
	
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
	 * 方法说明：导购未读客户数
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
	 * 方法说明：查询客户聊天记录（导购助手）-记录数
	 *
	 * @param findChatInfoPageFromWeb
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public int findChatInfoPageFromWebCount(FindChatInfoPageFromWeb findChatInfoPageFromWeb);
	
	/**
	 * 
	 *
	 * 方法说明：查询客户聊天记录（导购助手）-列表
	 *
	 * @param findChatInfoPageFromWeb
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public List<FindChatInfoPageFromWebReturn> findChatInfoPageFromWebList(FindChatInfoPageFromWeb findChatInfoPageFromWeb);
	
	/**
	 * 
	 *
	 * 方法说明：更新第三方已读（导购助手）
	 *
	 * @param updateThirdHaveReadFromWeb
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public int updateThirdHaveReadFromWeb(UpdateThirdHaveReadFromWeb updateThirdHaveReadFromWeb);

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
	List<String> findImByDate(Map<String, Object> param);

	/**
	 * 
	 *
	 * 方法说明：查询导购某天微信聊天统计
	 *
	 * @param map
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年12月7日
	 *
	 */
	List<Map<String, Object>> findCountImChatByGM(Map<String, Object> map);

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
	ImChatInfo findImFristInfo(FindImChatInfo findImChatInfo);
	
	/**
	 *
	 * 方法说明：导购与客户沟通次数统计(单个导购与几个客户沟通)
	 *
	 * @param parmMap
	 * @return
	 *
	 * @author 李端强 CreateDate: 2017年12月12日
	 *
	 */
	List<Map<String, Object>> findImChatStatisticsOMS(Map<String, Object> parmMap);
	
	/**
	 * 
	 *
	 * 方法说明：查询终端微信和客户微信下所有无客户信息聊天记录(按聊天时间顺序排序)
	 *
	 * @param shopNo
	 * @param noWxShop
	 * @param noWx
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月29日
	 *
	 */
   public List<ImChatInfo> findImChatInfoByNonMember(@Param("noWxShop") String noWxShop, @Param("noWx") String noWx);
   
   /**
	 *
	 * 方法说明：查找导购与客户的最后一条聊天记录
	 *
	 * @param findAutoAnswerChatInfo
	 * @return FindAutoAnswerChatInfoReturn
	 *
	 * @author 彭俊霖 CreateDate: 2018年01月30日
	 *
	 */
	FindAutoAnswerChatInfoReturn getLastChatInfo(FindAutoAnswerChatInfo findAutoAnswerChatInfo);
	
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
	public int findImChatInfoByMecCount(Map<String,Object> paramMap);
	
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

    int findChatTimesByGmAndMemberNo(FindChatTimesByGmAndMemberNo findChatTimesByGmAndMemberNo);
    
    
	/**
	 * 方法说明：查找最后一条聊天记录
	 * @param findImChatInfo
	 * @return
	 * @throws TsfaServiceException
	 */
	public FindImChatInfoReturn findLastImChatInfo(FindImChatInfo findImChatInfo) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param updateImChatInfo
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午5:34:16
	 */
	void delete(UpdateImChatInfo updateImChatInfo);

    
	/**
	 * 查找商户下每个终端客户最新的一条聊天记录
	 * @param findHistoryChatInfo
	 * @return
	 * @author lhy 2019.05.14
	 */
    List<FindImChatInfoPageReturn> findLastImChatInfoPage(FindImChatInfoPage findImChatInfoPage);
   
	/**
	 * 查找商户下每个终端客户最新的一条聊天记录
	 * @param findHistoryChatInfo
	 * @return
	 * @author lhy 2019.05.14
	 */
	int findLastImChatInfoPageCount(FindImChatInfoPage findImChatInfoPage);

	/**
	 *@Desc 
	 *@param map
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月1日上午11:15:52
	 */
	int selectChatCount(@Param("map") Map map);

	/**
	 *@Desc 
	 *@param mapChat
	 *@return
	 *@return int
	 *@author 贾光朝
	 *@createDate 2019年6月5日下午12:30:33
	 */
	int findChatCount(@Param("mapChat") Map mapChat);
    
	/**
	 * 
	 *
	 * 方法说明：查询客户聊天记录（导购助手）-记录总数<p>
	 * 统计导购，所有导购助手微信的未读总数。
	 *
	 * @param findChatInfoPageFromWeb
	 * @return
	 *
	 * @author lhy 2019.03.27 add 
	 *
	 */
	public int findUnreadPersonCount(FindUnreadCountByTerminal findUnreadCountByTerminal);
	
}