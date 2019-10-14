package com.lj.business.cf.dao;

import java.util.List;

import com.lj.business.cf.domain.CallChatInfo;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoPage;
import com.lj.business.cf.dto.callChatInfo.FindCallChatInfoPageReturn;

public interface ICallChatInfoDao {
    int deleteByPrimaryKey(String code);

    int insert(CallChatInfo record);

    int insertSelective(CallChatInfo record);

    CallChatInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CallChatInfo record);

    int updateByPrimaryKey(CallChatInfo record);

	List<FindCallChatInfoPageReturn> findCallChatInfoPage(
			FindCallChatInfoPage findCallChatInfoPage);

	int findCallChatInfoPageCount(FindCallChatInfoPage findCallChatInfoPage);

	/**
	 * 
	 *
	 * 方法说明：获取通话最大时间
	 *
	 * @param memberNoGuid
	 * @return
	 *
	 * @author 梅宏博  CreateDate: 2017年10月10日
	 *
	 */
	Long getMaxCallDateByGuidNo(String memberNoGuid);
}