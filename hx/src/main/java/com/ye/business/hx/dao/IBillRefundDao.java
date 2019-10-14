package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.BillRefund;
import com.ye.business.hx.dto.BillRefundDto;
import com.ye.business.hx.dto.FindBillRefundPage;

public interface IBillRefundDao {
    int deleteByPrimaryKey(String code);

    int insert(BillRefund record);

    int insertSelective(BillRefund record);

    BillRefund selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(BillRefund record);

    int updateByPrimaryKey(BillRefund record);
    
    List<BillRefundDto> findBillRefunds(FindBillRefundPage findBillRefundPage);
    
    List<BillRefundDto> findBillRefundPage(FindBillRefundPage findBillRefundPage);
    
    int findBillRefundPageCount(FindBillRefundPage findBillRefundPage);
    
    int updateByOperateCodeSelective(BillRefund record);

}