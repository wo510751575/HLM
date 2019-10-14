package com.lj.business.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.AddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsAllotPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPageReturn;
import com.lj.business.member.dto.addfriends.FindClaimMemberPage;
import com.lj.business.member.dto.addfriends.FindClaimMemberPageReturn;
import com.lj.business.member.dto.addfriends.UnclaimedFriendsByShop;
import com.lj.business.member.dto.addfriends.UpdateAddFriends;

public interface IAddFriendsDao {
    int deleteByPrimaryKey(String code);

    int insert(AddFriends record);

    int insertSelective(AddFriends record);

    AddFriends selectByPrimaryKey(String code);
    
    AddFriends selectBySelective(AddFriends record);
    
	/**
	 * 查询导购认领的客户
	 * @param memberNoGm
	 * @return
	 * @throws TsfaServiceException
	 */
	public List<AddFriends> findClaimMemberList(@Param("memberNoGm")String memberNoGm)throws TsfaServiceException;
	
    /**
               * 根据微信号解除
     * @param wxNo
     * @param membeNo
     * @throws TsfaServiceException
     */
    void updateCancleAddFriendsData(Map param)throws TsfaServiceException;
    
    /**
	 * 转移认领客户
	 * @throws zlh TsfaServiceException
	 */
	public void  updateFriendsWithTransfer(Map param)throws TsfaServiceException;
    /**
     * 
     *
     * 方法说明：查询未分配或导购扫码未通过客户
     *
     * @param record
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年11月22日
     *
     */
    AddFriends selectBySelectiveAndSync(AddFriends record);
    
    
    /**
     * 
     *
     * 方法说明：查询
     *
     * @param record
     * @return
     *
     * @author zlh CreateDate: 2017年11月22日
     *
     */
    AddFriends findNotClaimedFriends(AddFriends record);


    /**
     * 
     *
     * 方法说明：同步通讯录时更新客户信息
     *
     * @param record
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年12月1日
     *
     */
    int updateBySync(AddFriends record);
    
    int updateByPrimaryKeySelective(AddFriends record);

    int updateByPrimaryKey(AddFriends record);
    
    /**
     * 
     *
     * 方法说明：查找添加微信好友信息列表
     *
     * @param findAddFriendsPage
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年10月25日
     *
     */
	public List<FindAddFriendsPageReturn> findAddFriendsPage(FindAddFriendsPage findAddFriendsPage);
	
	/**
	 * 
	 *
	 * 方法说明：查找添加微信好友信息记录数
	 *
	 * @param findAddFriendsPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月25日
	 *
	 */
	public int findAddFriendsPageCount(FindAddFriendsPage findAddFriendsPage);

	/**
	 * 
	 *
	 * 方法说明：未分配微信好友信息
	 *
	 * @param findAddFriendsAllotPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月13日
	 *
	 */
	List<FindAddFriendsPageReturn> findAddFriendsAllotPage(FindAddFriendsAllotPage findAddFriendsAllotPage);

	/**
	 * 
	 *
	 * 方法说明：未分配微信好友信息记录数
	 *
	 * @param findAddFriendsAllotPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月13日
	 *
	 */
	int findAddFriendsAllotPageCount(FindAddFriendsAllotPage findAddFriendsAllotPage);
	
	/**
	 * 
	 *
	 * 方法说明：查询导购绑定微信号及所属门店的未认领（未分配）和已认领客户记录数
	 *
	 * @param findClaimMemberPage
	 * @param shopNo
	 * @param noWxGm
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月23日
	 *
	 */
	public int findClaimMemberPageCount(@Param("param") FindClaimMemberPage findClaimMemberPage,@Param("noWxGm") String noWxGm , @Param("memberNameGm") String memberNameGm);
	
	/**
	 * 
	 *
	 * 方法说明：查询导购绑定微信号及所属门店的未认领（未分配）和已认领客户列表
	 *
	 * @param findClaimMemberPage
	 * @param shopNo
	 * @param noWxGm
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月23日
	 *
	 */
	public List<FindClaimMemberPageReturn> findClaimMemberPageList(@Param("param") FindClaimMemberPage findClaimMemberPage,@Param("noWxGm") String noWxGm, @Param("memberNameGm") String memberNameGm);
	
	/**
	 * 
	 *
	 * 方法说明：查询绑定导购微信号所有未认领（未分配）客户的微信列表
	 *
	 * @param shopNo
	 * @param noWxGm
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月16日
	 *
	 */
	public List<String> findClaimMemberWxByNoWxGm(@Param("shopNo") String shopNo, @Param("noWxGm") String noWxGm);
	
	 /**
	  * 
	  *
	  * 方法说明：查询指定微信未分配的客户
	  *
	  * @param record
	  * @return
	  *
	  * @author 曾垂瑜 CreateDate: 2018年1月18日
	  *
	  */
    List<AddFriends> selectByRepeatAndClaim(AddFriends record); 
    
    /**
     * 
     *
     * 方法说明：统计每个终端未认领客户数
     *
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2018年1月24日
     *
     */
    public List<UnclaimedFriendsByShop> findUnclaimedFriendsCountByAllShop();
    /**
     * 
     *
     * 方法说明：敏华电商--导购5分钟内未回复客户自动推送二维码 
     *
     * @param paramMap
     * @param merchantNo 商户号
     * @param diffNum 分钟差值
     * @return
     *
     * @author 段志鹏 CreateDate: 2018年4月2日
     *
     */
    public List<FindAddFriendsPageReturn> findAddFriendsByMec(Map<String,Object> paramMap);

    


	/**
	 *
	 * 方法说明：定时任务，每天凌晨  统计每个商户昨天的新增总量
	 * 
	 * @author gongwenxue CreateDate: 2018年12月19日
   * @return 
	 *
	 */
	//List<Object> countAddByMerchantNo();
	
	/**
	 * 根据商户编号查询昨天新增客户量
	 * @return
	 */
    com.lj.business.member.dto.addfriends.CountAddFriendsEntity getYesterdayCountByMerchantNo(@Param("merchantNo")String merchantNo);
    
    
    /**
               * 根据商户号和日期
     * @param merchantNo
     * @param merchantNo
     * @return
     */
    com.lj.business.member.dto.addfriends.CountAddFriendsEntity getCountByMerchantNoWithDate(@Param("merchantNo")String merchantNo, @Param("date")String date);

    /**
             * 根据微信号和日期
	* @param merchantNo
	* @param merchantNo
	* @return
	*/
	com.lj.business.member.dto.addfriends.CountAddFriendsEntity getCountByWxNoWithDate(@Param("noWx")String noWx, @Param("date")String date);

    /**
     * 查询当天没超过限制的微信号
     * @param merchantNo
     * @param dayLimit
     * @param totalLimit
     * @return
     */
    public Integer findDayWxGmByMerchantNo(@Param("merchantNo")String merchantNo, @Param("wxNoGm")String wxNoGm);
    /**
     * 查询总数没超过限制的微信号
     * @param merchantNo
     * @param dayLimit
     * @param totalLimit
     * @return
     */
    public Integer findTotalWxGmByMerchantNo(@Param("merchantNo")String merchantNo, @Param("wxNoGm")String wxNoGm);
    
    
    /**
     * 查询门店排行榜
     */
    public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectShopOrderByaddFriends(@Param("merchantNo")String merchantNo, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("limit") Integer limit);
    
    /**
     * 查询门店排行榜
     */
    public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectShopOrderByServiceFriends(@Param("merchantNo")String merchantNo, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("limit") Integer limit);
    
    
    public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectGuidOrderByaddFriends(@Param("merchantNo")String merchantNo, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("limit") Integer limit);
    
    public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectGuidOrderByServiceFriends(@Param("merchantNo")String merchantNo, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("limit") Integer limit);

	/**
	 *@Desc 删除添加好友信息
	 *@param updateAddFriends
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月6日下午7:02:29
	 */
	void delete(UpdateAddFriends updateAddFriends);

}