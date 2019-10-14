package com.lj.business.weixin.dao;

import java.util.List;
import com.lj.business.weixin.domain.CouponMultiPushPm;

public interface ICouponMultiPushPmDao {

    int deleteByPrimaryKey(String code);

    int insert(CouponMultiPushPm record);

    int insertSelective(CouponMultiPushPm record);

    List<CouponMultiPushPm> selectBySelective(CouponMultiPushPm record);

    CouponMultiPushPm selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CouponMultiPushPm record);

    int updateByPrimaryKey(CouponMultiPushPm record);
}