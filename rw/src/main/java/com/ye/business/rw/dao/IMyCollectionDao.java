package com.ye.business.rw.dao;

import java.util.List;

import com.ye.business.rw.domain.MyCollection;
import com.ye.business.rw.dto.FindMyCollectionPage;
import com.ye.business.rw.dto.MyCollectionDto;

public interface IMyCollectionDao {
	int deleteByPrimaryKey(String code);

	int insert(MyCollection record);

	int insertSelective(MyCollection record);

	MyCollection selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(MyCollection record);

	int updateByPrimaryKeyWithBLOBs(MyCollection record);

	int updateByPrimaryKey(MyCollection record);

	/**
	 * 未返回 articleHtml 字段
	 * 
	 * @param findMyCollectionPage
	 * @return
	 */
	List<MyCollectionDto> findMyCollections(FindMyCollectionPage findMyCollectionPage);

	/**
	 * 未返回 articleHtml 字段
	 * 
	 * @param findMyCollectionPage
	 * @return
	 */
	List<MyCollectionDto> findMyCollectionPage(FindMyCollectionPage findMyCollectionPage);

	int findMyCollectionPageCount(FindMyCollectionPage findMyCollectionPage);

	int updateMyCollectionForReadNum(String code);

	int deleteByExample(FindMyCollectionPage record);
}