package com.lj.oms.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.oms.entity.sys.Area;
import com.lj.oms.service.impl.AreaHessianServiceImpl;
import com.lj.oms.service.sys.AreaService;

@Controller
@RequestMapping({ "/api/area" })
public class AreaApiController {
	
	private static final String SZ = "深圳";

	private static final Logger logger = LoggerFactory.getLogger(AreaApiController.class);
	
	@Autowired
	private AreaService areaService;
	@Autowired
	AreaHessianServiceImpl areaHessianService;

	@ResponseBody
	@RequestMapping(value = { "/findHotList" })
	public Map<String, Object> findHotList() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			List<Area> list = areaHessianService.findHotList();
			Map<String,String> map = areaHessianService.findPinyinMap();
			returnMap.put("list", list);
			returnMap.put("map", map);
		} catch (Exception e) {
			logger.error("查找热门城市错误！",e);
		}
		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = { "/findAreaByCityName" })
	public Map<String, Object> findAreaByCityName() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			Area area = areaHessianService.findAreaByCityName(SZ);
			returnMap.put("area", area);
		} catch (Exception e) {
			logger.error("根据城市名称查找地区错误！",e);
		}
		return returnMap;
	}
}
