package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.BillDetail;
import com.ye.business.hx.dto.BillDetailDto;
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.FindBillDetailPage;

public interface IBillDetailDao {
    int deleteByPrimaryKey(String code);

    int insert(BillDetail record);

    int insertSelective(BillDetail record);

    BillDetail selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(BillDetail record);

    int updateByPrimaryKey(BillDetail record);
    
    List<BillDetailDto> findBillDetails(FindBillDetailPage findBillDetailPage);
    
    List<BillDetailDto> findBillDetailPage(FindBillDetailPage findBillDetailPage);
    
    int findBillDetailPageCount(FindBillDetailPage findBillDetailPage);
    
    int deleteBillDetail(BillDetail record);
    
    List<BillDetailDto> findBillDetailPageByMerchant(FindBillDetailPage findBillDetailPage);
    
    int findBillDetailPageCountByMerchant(FindBillDetailPage findBillDetailPage);
    
    BillDetailDto billProjectSum(FindBillDetailPage findBillDetailPage);
}