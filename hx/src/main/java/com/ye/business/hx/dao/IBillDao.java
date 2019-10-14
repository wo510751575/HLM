package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.Bill;
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.FindBillPage;

public interface IBillDao {
    int deleteByPrimaryKey(String code);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
    
    List<BillDto> findBills(FindBillPage findBillPage);
    
    List<BillDto> findBillPage(FindBillPage findBillPage);
    
    int findBillPageCount(FindBillPage findBillPage);
    /** 统计患者的 实收，退款，欠款 金额*/
    BillDto billSum(BillDto record);
    /** 统计商户的 实收，退款，欠款 金额 */
    BillDto billSumBySearch(FindBillPage findBillPage);
    
}