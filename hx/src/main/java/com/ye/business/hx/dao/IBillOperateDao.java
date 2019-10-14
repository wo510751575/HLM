package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.BillOperate;
import com.ye.business.hx.dto.BillOperateDto;
import com.ye.business.hx.dto.FindBillOperatePage;

public interface IBillOperateDao {
    int deleteByPrimaryKey(String code);

    int insert(BillOperate record);

    int insertSelective(BillOperate record);

    BillOperate selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(BillOperate record);

    int updateByPrimaryKey(BillOperate record);
    
    List<BillOperateDto> findBillOperates(FindBillOperatePage findBillOperatePage);
    
    List<BillOperateDto> findBillOperatePage(FindBillOperatePage findBillOperatePage);
    
    int findBillOperatePageCount(FindBillOperatePage findBillOperatePage);
    
    List<BillOperateDto> findUntreatedBillOperatePage(FindBillOperatePage findBillOperatePage);
    
    int findUntreatedBillOperatePageCount(FindBillOperatePage findBillOperatePage);
    
}