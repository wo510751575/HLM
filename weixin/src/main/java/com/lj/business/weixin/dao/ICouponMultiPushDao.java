package com.lj.business.weixin.dao;

import java.util.List;
import com.lj.business.weixin.domain.CouponMultiPush;

public interface ICouponMultiPushDao {

    int deleteByPrimaryKey(String code);

    int insert(CouponMultiPush record);

    int insertSelective(CouponMultiPush record);

    List<CouponMultiPush> selectBySelective(CouponMultiPush record);

    CouponMultiPush selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CouponMultiPush record);

    int updateByPrimaryKey(CouponMultiPush record);
}