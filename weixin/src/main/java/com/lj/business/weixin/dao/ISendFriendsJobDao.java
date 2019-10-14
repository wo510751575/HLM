package com.lj.business.weixin.dao;

import java.util.List;

import com.lj.business.weixin.domain.SendFriendsJob;
import com.lj.business.weixin.dto.FindSendFriendsJobPage;
import com.lj.business.weixin.dto.FindSendFriendsJobPageReturn;
import com.lj.business.weixin.dto.friendsjob.AddSendFriendsJob;

public interface ISendFriendsJobDao {

    int deleteByPrimaryKey(String code);

    int insertSelective(SendFriendsJob record);

    List<SendFriendsJob> selectBySelective(SendFriendsJob record);

    SendFriendsJob selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SendFriendsJob record);

    /**
     * 查看朋友圈记录
     * @param findSendFriendsJobPage
     * @return
     *
     * @author 彭俊霖
     * @CreateDate 2018年6月21日下午3:27:19
     */
	int findSendFriendsJobPageCount(FindSendFriendsJobPage findSendFriendsJobPage);

	List<FindSendFriendsJobPageReturn> findSendFriendsJobPage(FindSendFriendsJobPage findSendFriendsJobPage);

	/**
	 *@Desc 
	 *@param addSendFriendsJob
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午6:22:42
	 */
	void delete(AddSendFriendsJob addSendFriendsJob);
}