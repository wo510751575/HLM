package com.lj.business.weixin.dao;

import java.util.List;

import com.lj.business.weixin.domain.ImChatInfoSensitive;
import com.lj.business.weixin.dto.FindImChatInfoSensitivePage;
import com.lj.business.weixin.dto.FindImChatInfoSensitivePageReturn;


public interface IImChatInfoSensitiveDao {
    int deleteByPrimaryKey(String code);

    int insert(ImChatInfoSensitive record);

    int insertSelective(ImChatInfoSensitive record);

    ImChatInfoSensitive selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ImChatInfoSensitive record);

    int updateByPrimaryKeyWithBLOBs(ImChatInfoSensitive record);

    int updateByPrimaryKey(ImChatInfoSensitive record);
    
    List<FindImChatInfoSensitivePageReturn> findImChatInfoSensitivePage(FindImChatInfoSensitivePage findImChatInfoSensitivePage);
    
     int findImChatInfoSensitiveCount(FindImChatInfoSensitivePage findImChatInfoSensitivePage);
}