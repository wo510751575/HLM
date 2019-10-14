package com.lj.business.weixin.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.weixin.domain.ImFriendsInfo;
import com.lj.business.weixin.dto.FindImFriendsInfoPage;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.dto.UpdateFriendsMemberInfo;

import org.apache.ibatis.annotations.Param;

public interface IImFriendsInfoDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(ImFriendsInfo record);

    ImFriendsInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ImFriendsInfo record);

    
    ImFriendsInfoDto getFriendsInfoByFriendsId(@Param("friendsId") String friendsId,@Param("noWxShop") String noWxShop);

	List<ImFriendsInfoDto> findImFriendsInfoPage(FindImFriendsInfoPage findImFriendsInfoPage);

	int findImFriendsInfoPageCount(FindImFriendsInfoPage findImFriendsInfoPage);

	List<ImFriendsInfoDto> findImFriendsInfoWebPage(FindImFriendsInfoPage findImFriendsInfoPage);

	int findImFriendsInfoWebPageCount(FindImFriendsInfoPage findImFriendsInfoPage);


	int updateAppReadFlag(FindImFriendsInfoPage findImFriendsInfoPage);


	int updateWebReadFlag(FindImFriendsInfoPage findImFriendsInfoPage);


	int findImFriendsNotRead(FindImFriendsInfoPage findImFriendsInfoPage);
	/**
	 * 周期外客户发送朋友圈的条数
	 * @param memberNoGm
	 * @param memberNo
	 * @author 李端强 2018年1月26日21:59:00
	 * @return
	 */
	int findFriendPointByMemberNo(Map<String, String> map);
	/**
	 * 周期外客户发送朋友圈的客户数量(客户去重)
	 * @param noWxShop
	 * @param shopNo
	 * @author 李端强 2018年2月28日11:41:10
	 * @return
	 */
	int findImFriendsInfoPendingCommentCount(@Param("noWxShop")String noWxShop);
	
	/**
	 * 周期外客户发送朋友圈的内容数量
	 * @param noWxShop
	 * @param shopNo
	 * @author 李端强 2018年2月28日11:41:10
	 * @return
	 */
	int findImFriendsInfoPendingCommentContentCount(@Param("noWxShop")String noWxShop);
	
	/**
	 * 周期外客户发送朋友圈的内容
	 * @param noWxShop
	 * @param shopNo
	 * @author 李端强 2018年2月28日11:41:10
	 * @return
	 */
	List<ImFriendsInfoDto> findImFriendsInfoPendingComment(@Param("noWxShop")String noWxShop,@Param("start")Integer start, @Param("limit")Integer limit);

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈待回复数量
	 *
	 * @param findImFriendsInfoPage
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年3月12日
	 *
	 */
    Integer findImFriendsInfoToReplyCount(FindImFriendsInfoPage findImFriendsInfoPage);

    /**
     * 
     *
     * 方法说明：更新朋友圈待回复数量
     *
     * @param friendsCode
     * @param delta
     *
     * @author 许新龙 CreateDate: 2018年3月12日
     *
     */
    void updateImFriendsInfoToReplyCount(@Param("friendsCode")String friendsCode, @Param("delta")Integer delta);
    
	/**
	 * 
	 *
	 * 方法说明：根据微信信息更新其客户信息
	 *
	 * @param updateFriendsMemberInfo
	 *
	 * @author 曾垂瑜 CreateDate: 2018年5月4日
	 *
	 */
	public int updateFriendsMemberInfo(UpdateFriendsMemberInfo updateFriendsMemberInfo);

	/**
	 * 不分页查询朋友圈信息信息
	 * @param findImFriendsInfoPage
	 * @return
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月22日上午9:20:13
	 */
	List<ImFriendsInfoDto> findImFriendsInfos(FindImFriendsInfoPage findImFriendsInfoPage);

	void updateCancleFriendsCommentData(Map<String,String> map);
	
	/**
	 * 关联评论和点赞查询
	 * @param findImFriendsInfoPage
	 * @return
	 */
	List<ImFriendsInfo> selectFriendsDatas(FindImFriendsInfoPage findImFriendsInfoPage);
	/**
	 * 关联评论和点赞查询
	 * @param findImFriendsInfoPage
	 * @return
	 */
	int selectFriendsDatasCount(FindImFriendsInfoPage findImFriendsInfoPage);

	/**
	 *@Desc 
	 *@param merchantNo
	 *@param noWx
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月8日上午11:51:21
	 */
	void delete(@Param("merchantNo") String merchantNo,@Param("noWx") String noWx);
}