package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.PmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotal;
import com.lj.business.st.dto.PmLabelTotal.FindPmLabelTotalReturnDto;

public interface IPmLabelTotalDao {
    int deleteByPrimaryKey(String code);

    int insert(PmLabelTotal record);

    int insertSelective(PmLabelTotal record);

    PmLabelTotal selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(PmLabelTotal record);

    int updateByPrimaryKey(PmLabelTotal record);
    /**
     * 
     *
     * 方法说明：客户分类报表
     *
     * @param findPmLabelTotal
     * @return
     *
     * @author 邹磊 CreateDate: 2017年7月28日
     *
     */
    List<FindPmLabelTotalReturnDto> findPmLabelTotalList(FindPmLabelTotal findPmLabelTotal);
    /**
     * 
     *
     * 方法说明：客户分类报表
     *
     * @param findPmLabelTotal
     * @return
     *
     * @author 邹磊 CreateDate: 2017年7月28日
     *
     */
    List<FindPmLabelTotalReturnDto> findPmLabelTotalListApp(FindPmLabelTotal findPmLabelTotal);
    
    /**
     * 
     *
     * 方法说明：查询最大的一条记录
     *
     * @param findPmLabelTotal
     * @return
     *
     * @author 罗书明 CreateDate: 2017年8月3日
     *
     */
    FindPmLabelTotalReturnDto findPmLabelTotalMax(FindPmLabelTotal findPmLabelTotal);
}