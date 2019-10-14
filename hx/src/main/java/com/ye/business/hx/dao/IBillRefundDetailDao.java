package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.BillRefundDetail;
import com.ye.business.hx.dto.BillRefundDetailDto;
import com.ye.business.hx.dto.FindBillRefundDetailPage;

public interface IBillRefundDetailDao {
    int deleteByPrimaryKey(String code);

    int insert(BillRefundDetail record);

    int insertSelective(BillRefundDetail record);

    BillRefundDetail selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(BillRefundDetail record);

    int updateByPrimaryKey(BillRefundDetail record);
    
    List<BillRefundDetailDto> findBillRefundDetails(FindBillRefundDetailPage findBillRefundDetailPage);
    
    List<BillRefundDetailDto> findBillRefundDetailPage(FindBillRefundDetailPage findBillRefundDetailPage);
    
    int findBillRefundDetailPageCount(FindBillRefundDetailPage findBillRefundDetailPage);
    
    int deleteBillRefundDetail(BillRefundDetail record);
}