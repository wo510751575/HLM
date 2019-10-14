package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.ServiceProduct;
import com.lj.business.member.dto.service.product.FindServiceProductPage;
import com.lj.business.member.dto.service.product.FindServiceProductPageReturn;

public interface IServiceProductDao {
    int deleteByPrimaryKey(String code);

    int insert(ServiceProduct record);

    int insertSelective(ServiceProduct record);

    ServiceProduct selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ServiceProduct record);

    int updateByPrimaryKey(ServiceProduct record);

	List<FindServiceProductPageReturn> findServiceProductPage(FindServiceProductPage findServiceProductPage);

	int findServiceProductPageCount(FindServiceProductPage findServiceProductPage);
}