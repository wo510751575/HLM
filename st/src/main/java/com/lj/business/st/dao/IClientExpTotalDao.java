package com.lj.business.st.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.st.domain.ClientExpTotal;
import com.lj.business.st.dto.FindClientExpReturn;
import com.lj.business.st.dto.FindClientExpTotal;

/**
 * 
 * 
 * 类说明： 到店客户体验统计
 *  
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月28日
 */
public interface IClientExpTotalDao {

    int insertSelective(ClientExpTotal record);

    ClientExpTotal selectByPrimaryKey(String code);
   
    List<FindClientExpReturn> findClientExpTotal(Map<String,Object> map);

    List<ClientExpTotal> findClientExpTotalList(FindClientExpTotal findClientExpTotal);
}