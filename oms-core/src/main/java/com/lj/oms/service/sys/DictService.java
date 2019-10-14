package com.lj.oms.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ape.common.utils.CacheUtils;
import com.lj.oms.dao.sys.DictDao;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.service.CrudService;
import com.lj.oms.utils.DictUtils;

/**
 * 字典Service
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	@Autowired
	private DictDao dictDao;
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查询有效的数据字典
	 *
	 * @param type
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月15日
	 *
	 */
   public List<Dict> getList(String type){
	   return dictDao.getList(type);
   }
	

}
