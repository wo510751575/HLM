package com.lj.oms.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ape.common.config.Global;
import com.ape.common.utils.CacheUtils;
import com.lj.oms.entity.dto.AreaPinyinDto;
import com.lj.oms.entity.sys.Area;
import com.lj.oms.entity.sys.Dict;
import com.lj.oms.service.AreaHessianService;
import com.lj.oms.service.sys.AreaService;
import com.lj.oms.service.sys.DictService;
import com.lj.oms.utils.DictUtils;

/**
 * 
 * 
 * 类说明：开放省市区接口实现类
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 * 
 *         CreateDate: 2017年7月11日
 */
@Service
public class AreaHessianServiceImpl implements AreaHessianService {

    private static final String CONFIG_HOT_CITY="hot.city";
	@Autowired
    private AreaService areaService;
//    @Autowired
//    private IShopService shopService;
    @Autowired
    private DictService dictService;

    /**
     * 获取省份信息
     */
    @Override
    public List<Area> selectProvince() {
        return areaService.selectProvince();
    }

    /**
     * 查询所有的省市区信息
     */
    @Override
    public List<Area> FindProvinceAndCityarea() {
        return areaService.FindProvinceAndCityarea();
    }

    /**
     * 根据父级获取子级列表
     */
    @Override
    public List<Area> selectAreaByParentId(String parentId) {
        return areaService.selectAreaByParentId(parentId);
    }
    
    /**
	 * 查询当前区域同一级别的其它区域信息
	 * @param areaId
	 * @return
	 */
    @Override
    public Map selectCurrentLevelByAreaId(String areaId) {
    	return areaService.selectCurrentLevelByAreaId(areaId);
    }
    
    /**
     * 查询当前区域同一级别的其它区域信息
     * @param areaId
     * @return
     */
    @Override
    public Map selectCurrentLevelByCityId(String cityId) {
    	return areaService.selectCurrentLevelByCityId(cityId);
    }

    @Override
    public List<Dict> selectAreaCode() {
        return DictUtils.getDictList(Dict.ERP_DICT_1);
    }

    @Override
    public String getAreaNameByCode(String code) {
        return DictUtils.getDictLabel(code, Dict.ERP_DICT_1, "");
    }

    @Override
    public List<Area> findAllList() {
        return areaService.findAll();
    }

    @Override
    public int getAreaVersion() {
        Integer version = (Integer) CacheUtils.get(Area.CACHE_AREA_VERSION);
        if (version == null) {
            version = 1;
            CacheUtils.put(Area.CACHE_AREA_VERSION, version);
        }
        return version;
    }

    @Override
    public List<Map<String, Object>> findShopCity(Map<String, Object> parmMap) {
        /*List<Map<String, Object>> list = shopService.findGroupByCity(parmMap);
        for (Map<String, Object> map : list) {
            if (map != null) {
                map.put("CITY_NAME", UserUtils.getAreaName(
                        map.get("CITY_CODE") == null ? "" : map.get("CITY_CODE").toString()));
            }
        }
        return list;*/
    	return null;
    }

    @Override
    public List<Dict> findMaterialTemp(String tempType) {
        Dict dict = new Dict();
        dict.setType(tempType);
        List<Dict> list = dictService.findList(dict);
        return list;
    }

    @Override
    public List<Area> selectCity() {
        return areaService.selectCity();
    }

    /**
     * 获取热门城市
     */
    @Override
    public List<Area> findHotList() {
        String hotCity = Global.getConfig(CONFIG_HOT_CITY);
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(hotCity.split(","));
        List<Area> relist = areaService.findHotList(list);
        return relist;
    }

    /**
     * 获取拼音首字母列表 A,B
     */
    @Override
    public Map<String, String> findPinyinMap() {
        Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        List<AreaPinyinDto> relist = areaService.findPinyinMap();
        for (AreaPinyinDto dto : relist) {
            map.put(dto.getPinyin(), dto.getCitys());
        }
        return map;
    }

    @Override
    public Area findAreaByCityName(String cityName) {
        Area area = areaService.findAreaByCityName(cityName);
        return area;
    }

    @Override
    public List<Area> selectRegion() {
        return areaService.selectRegion();
    }

    @Override
    public List<Area> findCityByProvinceCode(String provinceCode) {
        Area area = areaService.findProvinceByCode(provinceCode);
        return areaService.findCityByProvinceId(area.getParent().getId());
    }

    @Override
    public List<Area> findRegionByCityCode(String cityCode) {
        Area area = areaService.findCityByCode(cityCode);
        return areaService.findRegionByCityId(area.getParent().getId());
    }
    
    @Override
    public Area findArea(String id) {
        return areaService.get(id);
    }
    
    @Override
    public Area findAreaByCode(String code) {
    	return areaService.findAreaByCode(code);
    }

    // @Override
    public List<String> getAreaNameByProvinceCode(String provinceCode) {
        List<String> list = null;
        
        String defaultName = "无";
        Area area = areaService.findProvinceByCode(provinceCode);
        if (area != null) {
            list = new ArrayList<>(2);
            String areaName = DictUtils.getDictLabel(area.getZone(), "erp_dict_1", defaultName);
            list.add(area.getZone());
            list.add(areaName);
        }
        
        return list;
    }

    @Override
    public Area get(String parentId) {
        return areaService.get(parentId);
    }

	@Override
	public List<Area> findAreaListByIds(List<String> ids) {
		List<Area> relist = areaService.findHotList(ids);
		return relist;
	}
    
    
}
