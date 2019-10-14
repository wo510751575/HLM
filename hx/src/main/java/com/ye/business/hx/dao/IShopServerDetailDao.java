package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.ShopServerDetail;
import com.ye.business.hx.dto.FindShopServerDetailPage;
import com.ye.business.hx.dto.ShopServerDetailDto;
import com.ye.business.hx.dto.params.ClueParams;
import com.ye.business.hx.dto.vo.shopServerListVo;

public interface IShopServerDetailDao {
    int deleteByPrimaryKey(String code);

    int insert(ShopServerDetail record);

    int insertSelective(ShopServerDetail record);

    ShopServerDetail selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ShopServerDetail record);

    int updateByPrimaryKey(ShopServerDetail record);
    
    List<ShopServerDetailDto> findShopServerDetails(FindShopServerDetailPage findShopServerDetailPage);
    
    List<ShopServerDetailDto> findShopServerDetailPage(FindShopServerDetailPage findShopServerDetailPage);
    
    int findShopServerDetailPageCount(FindShopServerDetailPage findShopServerDetailPage);
    
    /**
     * 根据用户类型商户no查询商户服务
     * @param params
     * @return
     */
    List<shopServerListVo> shopServerList(ClueParams params);
    
    /**
     * 工单查询服务列表数量
     * @param params
     * @return
     */
    int clinicServicesCount(ClueParams params);
    
    /**
     * 工单查询服务列表
     * @param params
     * @return
     */
    List<shopServerListVo> clinicServicesList(ClueParams params);
}