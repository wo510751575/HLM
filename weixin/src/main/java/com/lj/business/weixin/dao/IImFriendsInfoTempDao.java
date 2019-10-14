package com.lj.business.weixin.dao;

import com.lj.business.weixin.dto.ImFriendsInfoTempDto;

public interface IImFriendsInfoTempDao {
    int deleteByPrimaryKey(String code);

    int insert(ImFriendsInfoTempDto record);

    int insertSelective(ImFriendsInfoTempDto record);
    
    
    int updateStatus(ImFriendsInfoTempDto imFriendsInfoTempDto);

    
 

	 
}