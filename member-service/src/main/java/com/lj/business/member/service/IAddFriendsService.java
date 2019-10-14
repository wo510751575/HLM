package com.lj.business.member.service;

import java.util.List;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Map;

import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.AddFriends;
import com.lj.business.member.dto.addfriends.AddAddFriends;
import com.lj.business.member.dto.addfriends.AddAddFriendsReturn;
import com.lj.business.member.dto.addfriends.DistributionMember;
import com.lj.business.member.dto.addfriends.DistributionMemberReturn;
import com.lj.business.member.dto.addfriends.FindAddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsAllotPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPage;
import com.lj.business.member.dto.addfriends.FindAddFriendsPageReturn;
import com.lj.business.member.dto.addfriends.FindAddFriendsReturn;
import com.lj.business.member.dto.addfriends.FindClaimMemberPage;
import com.lj.business.member.dto.addfriends.FindClaimMemberPageReturn;
import com.lj.business.member.dto.addfriends.SyncFriendsFromZk;
import com.lj.business.member.dto.addfriends.UpdateAddFriendStatus;
import com.lj.business.member.dto.addfriends.UpdateAddFriendStatusReturn;
import com.lj.business.member.dto.addfriends.UpdateAddFriends;
import com.lj.business.member.dto.addfriends.UpdateAddFriendsReturn;


/**
 * 
 * 
 * 类说明：添加微信好友接口
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月25日
 */
public interface IAddFriendsService {
	
	/**
	 * 查询导购认领的客户
	 * @param memberNoGm
	 * @return
	 * @throws TsfaServiceException
	 */
	public List<AddFriends> findClaimMemberList(String memberNoGm)throws TsfaServiceException;
	
	/**
	 * 方法说明：取消绑定客户
	*/
	public void updateCancleAddFriendsData(String gmNo, String wxNo,String noWxGm)throws TsfaServiceException; 
	
	/**
	 * 
	 *
	 * 方法说明：添加添加微信好友信息
	 *
	 * @param addAddFriends
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月25日
	 *
	 */
	public AddAddFriendsReturn addAddFriends(AddAddFriends addAddFriends) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找添加微信好友信息
	 *
	 * @param findAddFriends
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月25日
	 *
	 */
	public FindAddFriendsReturn findAddFriends(FindAddFriends findAddFriends) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：oms-添加微信好友，列表查询
	 *
	 * @param findAddWxFriends
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年1月12日
	 *
	 */
	Page<FindAddFriendsPageReturn> findAddWxFriends(FindAddFriendsPage findAddFriendsPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：按条件查询数量
	 *
	 * @param findAddFriendsPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年1月13日
	 *
	 */
	int findAddWxFriendsCount(FindAddFriendsPage findAddFriendsPage) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：修改添加微信好友信息
	 *
	 * @param updateAddFriends
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月25日
	 *
	 */
	public UpdateAddFriendsReturn updateAddFriends(UpdateAddFriends updateAddFriends)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找添加微信好友信息
	 *
	 * @param findAddFriendsPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月25日
	 *
	 */
	public Page<FindAddFriendsPageReturn> findAddFriendsPage(FindAddFriendsPage findAddFriendsPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：添加微信好友结果通知
	 *
	 * @param updateAddFriendStatus
	 * @return 
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月25日
	 *
	 */
	public UpdateAddFriendStatusReturn updateAddFriendsStatus(UpdateAddFriendStatus updateAddFriendStatus) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：为新增客户分配所属导购（即客户认领）
	 *
	 * @param distributionGuidMember
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月13日
	 * @return 
	 *
	 */
	public DistributionMemberReturn distributionMember(DistributionMember distributionMember);

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
	public Page<FindAddFriendsPageReturn> findAddFriendsAllotPage(FindAddFriendsAllotPage findAddFriendsAllotPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：向门店终端发送同步通讯录请求
	 *
	 * @param code
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月12日
	 *
	 */
	public void syncFriendsRequest(String code);
	
	/**
	 * 
	 *
	 * 方法说明：客户同步（中控端）
	 * 1、全量同步，即已是门店微信的客户，则更新微信信息，不是则添加到待认领客户列表。
	 * 2、如果已存在待认领客户列表中，则更新微信信息
	 * 3、如果不存在则新增到待认领客户列表中
	 *
	 * @param syncFriendsFromZk
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月22日
	 *
	 */
	public void syncFriendsList(String noWxGm,SyncFriendsFromZk syncFriendsFromZk);
	
	/**
	 * 
	 *
	 * 方法说明：查询导购绑定微信号及所属门店的客户列表
	 *
	 * @param findClaimMemberPage
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月23日
	 *
	 */
	public IPage<FindClaimMemberPageReturn> findClaimMemberPage(FindClaimMemberPage findClaimMemberPage,  boolean memberNoGmFlag,String noWxGm);
	
	/**
	 * 
	 *
	 * 方法说明：查询未分配的客户
	 *
	 * @param noWxGm
	 * @param noWx
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月27日
	 *
	 */
	public FindAddFriendsReturn findNotClaimedFriends( String noWx,String  noWxGm);

	/**
	 * 
	 *
	 * 方法说明：添加微信好友，导入
	 *
	 * @param addAddFriends
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年1月13日
	 *
	 */
    AddAddFriendsReturn addAddWxFriends(AddAddFriends addAddFriends) throws TsfaServiceException;
    
    /**
     * 
     *
     * 方法说明：敏华电商--导购5分钟内未回复客户自动推送二维码 
     *
     * @param paramMap
     * @param merchantNo 商户号
     * @param diffNum 分钟差值
     * @param qcordUrl 二维码地址
     * @return
     *
     * @author 段志鹏 CreateDate: 2018年4月2日
     *
     */
    public void sendQcordByMec(Map<String,Object> paramMap) throws TsfaServiceException;
    
    public void sendGreetings(AddFriends addFriends, String greetingsInfo);

    public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> countYesterdayAddFriends() ;
    
    /**
     * 方法说明：查询商户当天和总数没超过限制的导购微信号
     * @param merchantNo
     * @param dayLimit
     * @param totalLimit
     */
    String findWxGmByMerchantNo(String merchantNo, String code, int dayLimit, int totalLimit);
    
    /**
     * 统计某一天每个商户用户增量
     * @param date
     * @return
     */
    public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> countAddFriendsByDate(String date, String type);
    
    
	/**
	 * 门店新增用户排行榜
	 * @param merchantNo
	 * @param startTime  开始时间
	 * @param endTime    结束时间
	 * @param limit 返回0-N行数据，limit 代表N
	 */
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectShopOrderByaddFriends(String merchantNo, String startTime, String endTime,String limit);
	
	
	/**
	 * 门店维护用户排行榜
	 * @param merchantNo
	 * @param startTime  开始时间
	 * @param endTime    结束时间
	 * @param limit 返回0-N行数据，limit 代表N
	 */
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectShopOrderByServiceFriends(String merchantNo, String startTime, String endTime,String limit);
	
	
	/**
	 * 导购新增排行榜
	 * @param merchantNo
	 * @param startTime  开始时间
	 * @param endTime    结束时间
	 * @param limit 返回0-N行数据，limit 代表N
	 * @return
	 */
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectGuidOrderByaddFriends(String merchantNo, String startTime, String endTime,String limit);
	
	
	
	/**
	 * 导购维护排行榜
	 * @param merchantNo
	 * @param startTime  开始时间
	 * @param endTime    结束时间
	 * @param limit 返回0-N行数据，limit 代表N
	 * @return
	 */
	public List<com.lj.business.member.dto.addfriends.CountAddFriendsEntity> selectGuidOrderByServiceFriends(String merchantNo, String startTime, String endTime,String limit);

	/**
	 *@Desc 删除添加好友信息
	 *@param updateAddFriends
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月6日下午7:01:07
	 */
	public void delete(UpdateAddFriends updateAddFriends);

}
