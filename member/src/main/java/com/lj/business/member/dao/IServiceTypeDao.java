package com.lj.business.member.dao;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.ServiceType;
import com.lj.business.member.dto.service.type.FindServiceType;
import com.lj.business.member.dto.service.type.FindServiceTypeReturn;

public interface IServiceTypeDao {
    int insert(ServiceType record);

    int insertSelective(ServiceType record);
    
    ServiceType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ServiceType record);

    int updateByPrimaryKey(ServiceType record);
    
    /**
	 * 
	 *
	 * 方法说明：查询所有服务人员列表
	 *
	 * @param findServiceType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月21日
	 *
	 */
	List<FindServiceTypeReturn> findServiceTypes(
			FindServiceType findServiceType);
}