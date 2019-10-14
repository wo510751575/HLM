package com.lj.business.st.dao;

import java.util.List;

import com.lj.business.st.domain.StList;
import com.lj.business.st.dto.FindStListPage;
import com.lj.business.st.dto.FindStListPageReturn;

public interface IStListDao {
    int deleteByPrimaryKey(String code);

    int insert(StList record);

    int insertSelective(StList record);

    StList selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(StList record);

    int updateByPrimaryKey(StList record);

	List<FindStListPageReturn> findStListPage(FindStListPage findStListPage);

	int findStListPageCount(FindStListPage findStListPage);

	
	/**
	 * 
	 *
	 * 方法说明：查询所有有效报表项目信息
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年9月19日
	 *
	 */
	public List<FindStListPageReturn> findAllVaildStList();
}