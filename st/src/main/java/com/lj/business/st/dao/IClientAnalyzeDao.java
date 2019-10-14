package com.lj.business.st.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.st.domain.ClientAnalyze;
import com.lj.business.st.dto.FindClientAnalyze;
import com.lj.business.st.dto.FindClientAnalyzeAndOthers;
import com.lj.business.st.dto.FindClientAnalyzeReturn;
/**
 * 
 * 
 * 类说明：客户画像
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月31日
 */
public interface IClientAnalyzeDao {
	/**
	 * 
	 *
	 * 方法说明：新增客户画像统计信息
	 *
	 * @param record
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月31日
	 *
	 */
    int insertSelective(ClientAnalyze record);
    /**
     * 
     *
     * 方法说明：查询客户画像统计信息
     *
     * @param map
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月31日
     *
     */
    List<Map<String,Object>> findClientAnalyzeList(Map<String,Object> map);
   
    /**
     *  
     *
     * 方法说明:查询客户性别数量
     *
     * @param findClientAnalyze
     * @return
     *
     * @author 罗书明 CreateDate: 2017年7月31日
     *
     */
    List<FindClientAnalyzeReturn> findClientAnalyze(FindClientAnalyze findClientAnalyze);

	ClientAnalyze findClientAnalyzeBase(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers);

	int updateClientAnalyze(ClientAnalyze clientAnalyze);

	/**
	 * 获取分店维度的所有数据
	 * @return
	 */
	List<ClientAnalyze> selectAllShopData();
	
	/**
	 * 
	 *
	 * 方法说明：查询商户编号
	 *
	 * @param findClientAnalyzeAndOthers
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月11日
	 *
	 */
	FindClientAnalyzeReturn findMerchantNo(FindClientAnalyzeAndOthers findClientAnalyzeAndOthers);


    
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
    List<ClientAnalyze> selectMerchantTotalByArea();
    
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
    List<ClientAnalyze> selectAreaTotalByShop();
	
}