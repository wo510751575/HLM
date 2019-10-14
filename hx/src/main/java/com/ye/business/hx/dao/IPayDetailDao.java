package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PayDetail;
import com.ye.business.hx.dto.FindPayDetailPage;
import com.ye.business.hx.dto.PayDetailDto;

public interface IPayDetailDao {
    int deleteByPrimaryKey(String code);

    int insert(PayDetail record);

    int insertSelective(PayDetail record);

    PayDetail selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PayDetail record);

    int updateByPrimaryKey(PayDetail record);
    
    List<PayDetailDto> findPayDetails(FindPayDetailPage findPayDetailPage);
    
    List<PayDetailDto> findPayDetailPage(FindPayDetailPage findPayDetailPage);
    
    int findPayDetailPageCount(FindPayDetailPage findPayDetailPage);
    
    int deleteDetail(PayDetail record);
}