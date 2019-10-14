package com.lj.business.cp.dao;

import java.util.List;

import com.lj.business.cp.couponRecords.FindCouponRecordsPage;
import com.lj.business.cp.couponRecords.FindCouponRecordsPageReturn;
import com.lj.business.cp.domain.CouponRecords;

public interface ICouponRecordsDao {
    int deleteByPrimaryKey(String code);

    int insert(CouponRecords record);

    int insertSelective(CouponRecords record);

    CouponRecords selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CouponRecords record);

    int updateByPrimaryKey(CouponRecords record);

	List<FindCouponRecordsPageReturn> findCouponRecordsPage(FindCouponRecordsPage findCouponRecordsPage);

	int findCouponRecordsPageCount(FindCouponRecordsPage findCouponRecordsPage);
}