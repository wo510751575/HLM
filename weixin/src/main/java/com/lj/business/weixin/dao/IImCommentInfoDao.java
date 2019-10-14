package com.lj.business.weixin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.base.core.pagination.Page;
import com.lj.business.weixin.domain.ImCommentInfo;
import com.lj.business.weixin.dto.FriendsMessageDto;
import com.lj.business.weixin.dto.ImCommentInfoDto;
import com.lj.business.weixin.dto.UpdateFriendsMemberInfo;

public interface IImCommentInfoDao {
	int deleteByPrimaryKey(String code);

	int insertSelective(ImCommentInfo record);

	ImCommentInfo selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(ImCommentInfo record);

	List<ImCommentInfoDto> findImCommentInfoByFC(@Param("friendCode") List<String> friendCode);

	List<ImCommentInfoDto> findImCommentInfoByFriendsCode(@Param("friendsCode") String friendCode,
			@Param("noWxShop") String noWxShop);

	int updateImCommentAppReadFlag(ImCommentInfoDto record);

	int updateImCommentWebReadFlag(ImCommentInfoDto record);

	int findImCommentInfoAppNotRead(ImCommentInfoDto record);

	int findImCommentInfoWebNotRead(ImCommentInfoDto record);

	List<FriendsMessageDto> findImMessageList(@Param("friends") FriendsMessageDto friendsMessageDto,
			@Param("page") Page<FriendsMessageDto> page);

	Integer findImMessageListCount(@Param("friends") FriendsMessageDto friendsMessageDto);

	/**
	 * 根据
	 * 
	 * @param friendsCode
	 * @param timestamp
	 * @return
	 */
	ImCommentInfoDto findImCommentInfoByFriendsCodeAndTimestamp(@Param("noWxShop") String noWxShop,
			@Param("friendsCode") String friendsCode, @Param("timestamp") String timestamp);

	ImCommentInfoDto findImCommentInfoByFriendsIdAndTimestamp(@Param("noWxShop") String noWxShop,
			@Param("friendsId") String friendsId, @Param("timestamp") String timestamp);

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈评论中commentSvrId最大的值
	 * 
	 * @param friendsCode
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月21日
	 *
	 */
	public int getMaxCommentSvrId(@Param("friendsCode") String friendsCode);

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
	public int updateCommentMemberInfo(UpdateFriendsMemberInfo updateFriendsMemberInfo);

	/**
	 * 
	 *
	 * 方法说明：删除朋友圈所有评论
	 *
	 * @param friendsCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月1日
	 *
	 */
	public int deleteCommentByFriendsCode(String friendsCode);

	void updateCancleFriendsCommentData(Map<String, String> map);

	/**
	 * @Desc
	 * @param imCommentInfoDto
	 * @return void
	 * @author 贾光朝
	 * @createDate 2019年5月7日下午5:55:45
	 */
	void delete(ImCommentInfoDto imCommentInfoDto);
}
