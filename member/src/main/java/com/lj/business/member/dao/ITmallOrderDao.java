package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.TmallOrder;
import com.lj.business.member.dto.FindTmallOrderPage;
import com.lj.business.member.dto.TmallOrderDto;

public interface ITmallOrderDao {
    int deleteByPrimaryKey(String code);

    int insert(TmallOrder record);

    int insertSelective(TmallOrder record);

    TmallOrder selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(TmallOrder record);

    int updateByPrimaryKey(TmallOrder record);
    
    List<TmallOrderDto> findTmallOrders(FindTmallOrderPage findTmallOrderPage);
    
    List<TmallOrderDto> findTmallOrderPage(FindTmallOrderPage findTmallOrderPage);
    
    int findTmallOrderPageCount(FindTmallOrderPage findTmallOrderPage);
}