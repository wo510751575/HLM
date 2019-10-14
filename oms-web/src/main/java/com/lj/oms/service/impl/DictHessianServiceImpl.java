package com.lj.oms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lj.oms.entity.sys.Dict;
import com.lj.oms.service.DictHessianService;
import com.lj.oms.service.sys.DictService;
import com.lj.oms.utils.DictUtils;

@Service
public class DictHessianServiceImpl implements DictHessianService {
	
	@Resource
	private DictService dictService;

	@Override
	public List<Dict> findList(Dict dict) {
		return dictService.findList(dict);
	}
	
	@Override
	public List<Dict> findListByType(String type) {
		return DictUtils.getDictList(type);
	}

	@Override
	public String getDictLabel(String type, String value) {
		return DictUtils.getDictLabel(value, type, "");
	}
	

}
