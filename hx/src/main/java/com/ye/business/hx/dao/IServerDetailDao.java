package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.ServerDetail;
import com.ye.business.hx.dto.FindServerDetailPage;
import com.ye.business.hx.dto.ServerDetailDto;

public interface IServerDetailDao {
    int deleteByPrimaryKey(String code);

    int insert(ServerDetail record);

    int insertSelective(ServerDetail record);

    ServerDetail selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ServerDetail record);

    int updateByPrimaryKey(ServerDetail record);
    
    List<ServerDetailDto> findServerDetails(FindServerDetailPage findServerDetailPage);
    
    List<ServerDetailDto> findServerDetailPage(FindServerDetailPage findServerDetailPage);
    
    int findServerDetailPageCount(FindServerDetailPage findServerDetailPage);
    
    int deleteByServerCode(String serverCode);
}