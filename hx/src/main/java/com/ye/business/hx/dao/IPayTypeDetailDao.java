package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.PayTypeDetail;
import com.ye.business.hx.dto.FindPayTypeDetailPage;
import com.ye.business.hx.dto.PayTypeDetailDto;

public interface IPayTypeDetailDao {
    int deleteByPrimaryKey(String code);

    int insert(PayTypeDetail record);

    int insertSelective(PayTypeDetail record);

    PayTypeDetail selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PayTypeDetail record);

    int updateByPrimaryKey(PayTypeDetail record);
    
    List<PayTypeDetailDto> findPayTypeDetails(FindPayTypeDetailPage findPayTypeDetailPage);
    
    List<PayTypeDetailDto> findPayTypeDetailPage(FindPayTypeDetailPage findPayTypeDetailPage);
    
    int findPayTypeDetailPageCount(FindPayTypeDetailPage findPayTypeDetailPage);
}