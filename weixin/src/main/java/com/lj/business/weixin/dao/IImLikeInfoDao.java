package com.lj.business.weixin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.business.weixin.domain.ImLikeInfo;
import com.lj.business.weixin.dto.FindImLikeInfoPage;
import com.lj.business.weixin.dto.ImLikeInfoDto;
import com.lj.business.weixin.dto.UpdateFriendsMemberInfo;

public interface IImLikeInfoDao {
	int deleteByPrimaryKey(String code);

	int insertSelective(ImLikeInfo record);

	ImLikeInfo selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(ImLikeInfo record);

	List<ImLikeInfoDto> findImLikeInfoByFC(@Param("friendCode") List<String> friendCode);

	List<ImLikeInfoDto> findImLikeInfoByFriendsCode(@Param("friendsCode") String friendCode,
			@Param("noWxShop") String noWxShop);

	List<ImLikeInfoDto> findImLikeInfos(FindImLikeInfoPage findImLikeInfoPage);

	int updateImLikeAppReadFlag(ImLikeInfoDto imLikeInfoDto);

	int updateImLikeWebReadFlag(ImLikeInfoDto imLikeInfoDto);

	int findImLikeAppNotRead(ImLikeInfoDto imLikeInfoDto);

	int findImLikeWebNotRead(ImLikeInfoDto imLikeInfoDto);

	ImLikeInfoDto findImLikeInfoByNowx(@Param("noWxShop") String noWxShop, @Param("friendsCode") String friendsCode,
			@Param("noWx") String noWx);

	/**
	 * 查询朋友圈点赞信息通过friendsId
	 * 
	 * @param noWxShop
	 * @param friendsId
	 * @param noWx
	 * @return
	 */
	ImLikeInfoDto findImLikeInfoByNowxAndId(@Param("noWxShop") String noWxShop, @Param("friendsId") String friendsId,
			@Param("noWx") String noWx);

	/**
	 * 
	 *
	 * 方法说明：查询朋友圈下所有点赞客户username
	 *
	 * @param friendsCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月21日
	 *
	 */
	public List<String> findUsernameByFriendsCode(@Param("friendsCode") String friendsCode);

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
	public int updateLikeMemberInfo(UpdateFriendsMemberInfo updateFriendsMemberInfo);

	/**
	 * 
	 *
	 * 方法说明：删除朋友圈所有点赞
	 *
	 * @param friendsCode
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月1日
	 *
	 */
	public int deleteLikeByFriendsCode(String friendsCode);

	void updateCancleFriendsCommentData(Map<String, String> map);

	/**
	 * @Desc
	 * @param imLikeInfoDto
	 * @return void
	 * @author 贾光朝
	 * @createDate 2019年5月7日下午6:15:50
	 */
	void delete(ImLikeInfoDto imLikeInfoDto);
}