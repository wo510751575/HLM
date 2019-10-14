package com.lj.oms.service;

import java.util.List;

import com.lj.oms.entity.sys.Dict;
import com.lj.oms.utils.DictUtils;

public interface DictHessianService {
	
	public List<Dict> findList(Dict dict);
	public List<Dict> findListByType(String type);
	public String getDictLabel(String type,String value);
}
