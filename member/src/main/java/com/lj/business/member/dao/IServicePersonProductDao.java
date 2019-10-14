package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.ServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPage;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPageReturn;

public interface IServicePersonProductDao {
    int deleteByPrimaryKey(String code);

    int insert(ServicePersonProduct record);

    int insertSelective(ServicePersonProduct record);

    ServicePersonProduct selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ServicePersonProduct record);

    int updateByPrimaryKey(ServicePersonProduct record);
    
    /**
     * 
     *
     * 方法说明：分页查询
     *
     * @param findServicePersonProductPage
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年9月21日
     *
     */
	List<FindServicePersonProductPageReturn> findServicePersonProductPage(FindServicePersonProductPage findServicePersonProductPage);

	int findServicePersonProductPageCount(FindServicePersonProductPage findServicePersonProductPage);
}