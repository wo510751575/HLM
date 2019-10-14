package com.lj.business.member.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.business.member.dto.im.FindImFriendsPage;
import com.lj.business.member.dto.im.FindImFriendsPageReturn;
import com.lj.business.member.dto.im.FindMaxVersion;
import com.lj.business.member.dto.im.FindPersonMemberByChat;
import com.lj.business.member.dto.im.FindPersonMemberByChatReturn;
import com.lj.business.member.dto.im.FindPersonMemberByShopAndNoWx;
import com.lj.business.member.dto.im.FindPersonMemberByShopAndNoWxReturn;
import com.lj.business.member.dto.im.GmMemberRelateInfoDto;

public interface IPersonMemberImDao {
	
	/**
	 * 
	 *
	 * 方法说明：查询客户最大版本号
	 *
	 * @param findMaxVersion
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月27日
	 *
	 */
	public long findMaxVersion(FindMaxVersion findMaxVersion);
	
	/**
	 * 
	 *
	 * 方法说明：分页查询IM微信好友-记录数
	 *
	 * @param findImFriendsPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月28日
	 *
	 */
	public int findImFriendsCount(FindImFriendsPage findImFriendsPage);
	
	/**
	 * 
	 *
	 * 方法说明：分页查询IM微信好友-列表
	 *
	 * @param findImFriendsPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月28日
	 *
	 */
	public List<FindImFriendsPageReturn> findImFriendsList(FindImFriendsPage findImFriendsPage);
	
	/**
	 * 
	 *
	 * 方法说明：findPersonMemberByChat
	 *
	 * @param findPersonMemberByChat
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月10日
	 *
	 */
	public FindPersonMemberByChatReturn findPersonMemberByChat(FindPersonMemberByChat findPersonMemberByChat);
	/**
	 *
	 *
	 * 方法说明：findGmMemberRelateInfo
	 *
	 * @param
	 * @return
	 *
	 * @author dengxiudong CreateDate: 2018年1月31日
	 *
	 */
	public List<GmMemberRelateInfoDto> findGmMemberRelateInfo();

	/**
	 * 方法说明：根据客户编号查询客户微信号
	 *
	 * @param memberNoGm
	 * @param memberNo
	 * @return
	 * @author dengxiudong CreateDate: 2018年2月1日
	 */
	public Map<String, Object> getGmMemberWxInfo(@Param("memberNoGm") String memberNoGm, @Param("memberNo") String memberNo);
	
	/**
	 * 
	 *
	 * 方法说明：查询绑定导购微信号所有客户的微信列表
	 *
	 * @param shopNo
	 * @param noWxGm
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月16日
	 *
	 */
	public List<String> findMemberWxByNoWxGm(@Param("shopNo") String shopNo, @Param("noWxGm") String noWxGm);
	
	/**
	 * 
	 *
	 * 方法说明：查询拥有指定分组客户的导购绑定微信列表
	 *
	 * @param pmTypeCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月4日
	 *
	 */
	public List<String> findNoWxByPmTypeCode(String pmTypeCode);

	/**
	 * 
	 *
	 * 方法说明：返回终端下添加了指定微信的客户信息
	 *
	 * @param find
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月12日
	 *
	 */
	public FindPersonMemberByShopAndNoWxReturn findPersonMemberByShopAndNoWx(FindPersonMemberByShopAndNoWx find);
	
	/**
	 * 
	 *
	 * 方法说明：统计导购助手下未回复客户数
	 *
	 * @param assistantNo
	 * @param beginTime
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月18日
	 *
	 */
	public int findUnrespMemberCount(@Param("assistantNo") String assistantNo, @Param("beginTime") Date beginTime);
}