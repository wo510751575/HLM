package com.lj.business.st.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.st.domain.ClientLineRpt;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;

public interface IClientLineRptDao {
    int deleteByPrimaryKey(String code);

    int insert(ClientLineRpt record);

    int insertSelective(ClientLineRpt record);

    ClientLineRpt selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ClientLineRpt record);

    int updateByPrimaryKey(ClientLineRpt record);

    List<ClientLineRpt> selectClientLineRptList(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers);

    /**
     * 获取分店维度的所有数据
     * @return
     */
    List<ClientLineRpt> selectAllShopData(Map<String,String> map);
    
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
    List<ClientLineRpt> selectMerchantTotalByArea();
    
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
    List<ClientLineRpt> selectAreaTotalByShop();
    
}