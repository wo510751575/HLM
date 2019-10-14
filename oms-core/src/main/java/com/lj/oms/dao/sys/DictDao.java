package com.lj.oms.dao.sys;

import java.util.List;
import java.util.Map;

import com.ape.common.persistence.CrudDao;
import com.ape.common.persistence.annotation.MyBatisDao;
import com.lj.oms.entity.sys.Dict;

/**
 * 字典DAO接口
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	public int batchInsert(List<Map<String,Object>> list);
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public int batchUpdate(List<Map<String,Object>> list);
	/**
	 * 方法说明：
	 * @param type
	 * @return 
	 */
	public List<Dict> getList(String type);
}
