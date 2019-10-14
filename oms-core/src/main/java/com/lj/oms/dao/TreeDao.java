package com.lj.oms.dao;

import java.util.List;

import com.ape.common.persistence.CrudDao;
import com.lj.oms.entity.TreeEntity;

/**
 * DAO鏀寔绫诲疄鐜�
 */
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

	
	
	
	/**
	 * 鎵惧埌鎵�鏈夊瓙鑺傜偣
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 鏇存柊鎵�鏈夌埗鑺傜偣瀛楁
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
	
}