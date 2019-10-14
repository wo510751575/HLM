package com.lj.business.weixin.dao;

import com.lj.business.weixin.domain.WxJobInfo;

public interface IWxJobInfoDao {
    int deleteByPrimaryKey(String code);

    int insert(WxJobInfo record);

    int insertSelective(WxJobInfo record);

    WxJobInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(WxJobInfo record);

    int updateByPrimaryKey(WxJobInfo record);
}