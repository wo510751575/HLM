package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.BillPayment;
import com.ye.business.hx.dto.BillPaymentDto;
import com.ye.business.hx.dto.FindBillPaymentPage;

public interface IBillPaymentDao {
    int deleteByPrimaryKey(String code);

    int insert(BillPayment record);

    int insertSelective(BillPayment record);

    BillPayment selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(BillPayment record);

    int updateByPrimaryKey(BillPayment record);
    
    List<BillPaymentDto> findBillPayments(FindBillPaymentPage findBillPaymentPage);
    
    List<BillPaymentDto> findBillPaymentPage(FindBillPaymentPage findBillPaymentPage);
    
    int findBillPaymentPageCount(FindBillPaymentPage findBillPaymentPage);
    
    int updateBillPayment(BillPayment record);
    
    /**
     * 查询账单最后一笔支付记录
     * @param paymentDto
     * @return
     */
    BillPaymentDto getLastNormalPayment(BillPaymentDto paymentDto);
    
    
    List<BillPaymentDto> findBillPaymentPageByMerchant(FindBillPaymentPage findBillPaymentPage);
    
    int findBillPaymentPageCountByMerchant(FindBillPaymentPage findBillPaymentPage);

	/** 按条件：统计退款或收款额度 */
    Long paymentSum(FindBillPaymentPage findBillPaymentPage);
}