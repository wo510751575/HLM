package com.lj.business.st.dao;

import com.lj.business.st.domain.ClientInterestRpt;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;

import java.util.List;

public interface IClientInterestRptDao {
    int deleteByPrimaryKey(String code);

    int insert(ClientInterestRpt record);

    int insertSelective(ClientInterestRpt record);

    ClientInterestRpt selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ClientInterestRpt record);

    int updateByPrimaryKey(ClientInterestRpt record);

    List<ClientInterestRpt> selectClientInterestRptList(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers);

    /**
     * 获取分店维度的所有数据
     * @return
     */
    List<ClientInterestRpt> selectAllShopData();


    
    /**
     * 
     *
     * 方法说明：根据商户区域维度数据统计商户数据
     *
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年9月18日
     *
     */
    List<ClientInterestRpt> selectMerchantTotalByArea();
    
    /**
     * 
     *
     * 方法说明：根据分店维度数据统计商户区域数据
     *
     * @return
     *
     * @author 曾垂瑜 CreateDate: 2017年9月18日
     *
     */
    List<ClientInterestRpt> selectAreaTotalByShop();
}