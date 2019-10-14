package com.lj.business.member.dao;

import java.util.List;

import com.lj.business.member.domain.ServiceProject;
import com.lj.business.member.dto.service.project.FindServiceProjectApp;
import com.lj.business.member.dto.service.project.FindServiceProjectAppReturn;
import com.lj.business.member.dto.service.project.FindServiceProjectPage;
import com.lj.business.member.dto.service.project.FindServiceProjectPageReturn;

public interface IServiceProjectDao {

    int insert(ServiceProject record);

    int insertSelective(ServiceProject record);

    ServiceProject selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ServiceProject record);

    int updateByPrimaryKey(ServiceProject record);

	List<FindServiceProjectPageReturn> findServiceProjectPage(FindServiceProjectPage findServiceProjectPage);

	int findServiceProjectPageCount(FindServiceProjectPage findServiceProjectPage);
	
	List<FindServiceProjectAppReturn> findServiceProjectList(FindServiceProjectApp findServiceProjectApp);
	
	/**
	 * 
	 *
	 * 方法说明：查询服务项目详情-APP
	 *
	 * @param findServiceProjectApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月21日
	 *
	 */
	public List<FindServiceProjectAppReturn> findServiceProjectApp(FindServiceProjectApp findServiceProjectApp);

}