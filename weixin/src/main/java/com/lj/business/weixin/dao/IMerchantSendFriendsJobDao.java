package com.lj.business.weixin.dao;

import java.util.List;

import com.lj.business.weixin.domain.MerchantSendFriendsJob;
import com.lj.business.weixin.dto.FindMerchantSendFriendsJobPage;
import com.lj.business.weixin.dto.MerchantSendFriendsJobDto;

public interface IMerchantSendFriendsJobDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(MerchantSendFriendsJob record);

    MerchantSendFriendsJob selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MerchantSendFriendsJob record);

	List<MerchantSendFriendsJobDto> findMerchantSendFriendsJobs(
			FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage);

	List<MerchantSendFriendsJobDto> findMerchantSendFriendsJobPage(
			FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage);

	int findMerchantSendFriendsJobPageCount(FindMerchantSendFriendsJobPage findMerchantSendFriendsJobPage);
}