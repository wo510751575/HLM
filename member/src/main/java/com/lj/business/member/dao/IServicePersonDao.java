package com.lj.business.member.dao;

import java.util.List;

import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.ServicePerson;
import com.lj.business.member.dto.service.person.FindServicePerson;
import com.lj.business.member.dto.service.person.FindServicePersonApp;
import com.lj.business.member.dto.service.person.FindServicePersonAppReturn;
import com.lj.business.member.dto.service.person.FindServicePersonPage;
import com.lj.business.member.dto.service.person.FindServicePersonPageReturn;
import com.lj.business.member.dto.service.person.FindServicePersonReturn;

public interface IServicePersonDao {
    int deleteByPrimaryKey(String code);

    int insert(ServicePerson record);

    int insertSelective(ServicePerson record);

    ServicePerson selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ServicePerson record);

    int updateByPrimaryKey(ServicePerson record);
    
    /**
     * 
     *
     * 方法说明：分页查询
     *
     * @param findServicePersonPage
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年9月21日
     *
     */
	List<FindServicePersonPageReturn> findServicePersonPage(FindServicePersonPage findServicePersonPage);

	int findServicePersonPageCount(FindServicePersonPage findServicePersonPage);
	
	/**
	 * 
	 *
	 * 方法说明：APP查询所有服务人员列表
	 *
	 * @param findServicePersonApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月21日
	 *
	 */
	public List<FindServicePersonAppReturn> findServicePersonApp(FindServicePersonApp findServicePersonApp);

	/**
	 * 
	 *
	 * 方法说明：查询所有服务人员列表
	 *
	 * @param findServicePerson
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年9月21日
	 *
	 */
	List<FindServicePersonReturn> findServicePersons(
			FindServicePerson findServicePerson);
	
}