package com.lj.business.member.dao;

import com.lj.business.member.domain.DealerApplyShop;

public interface IDealerApplyShopDao {
    int deleteByPrimaryKey(String code);

    int insert(DealerApplyShop record);

    int insertSelective(DealerApplyShop record);

    DealerApplyShop selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(DealerApplyShop record);

    int updateByPrimaryKey(DealerApplyShop record);
}