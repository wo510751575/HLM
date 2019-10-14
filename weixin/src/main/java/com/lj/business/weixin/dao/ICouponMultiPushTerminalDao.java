package com.lj.business.weixin.dao;

import java.util.List;
import com.lj.business.weixin.domain.CouponMultiPushTerminal;

public interface ICouponMultiPushTerminalDao {

    int deleteByPrimaryKey(String code);

    int insert(CouponMultiPushTerminal record);

    int insertSelective(CouponMultiPushTerminal record);

    List<CouponMultiPushTerminal> selectBySelective(CouponMultiPushTerminal record);

    CouponMultiPushTerminal selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CouponMultiPushTerminal record);

    int updateByPrimaryKey(CouponMultiPushTerminal record);
}