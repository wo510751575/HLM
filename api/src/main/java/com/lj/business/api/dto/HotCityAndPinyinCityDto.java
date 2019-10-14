package com.lj.business.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lj.oms.entity.sys.Area;

public class HotCityAndPinyinCityDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -676275038309327349L;
	
	/**
	 * 热门城市
	 */
	private List<Area> hotAreaList = new ArrayList<Area>();
	
	/**
	 * 拼音城市
	 */
	private Map<String,String> pinyinCityMap = new HashMap<String,String>();

	public List<Area> getHotAreaList() {
		return hotAreaList;
	}

	public void setHotAreaList(List<Area> hotAreaList) {
		this.hotAreaList = hotAreaList;
	}

	public Map<String, String> getPinyinCityMap() {
		return pinyinCityMap;
	}

	public void setPinyinCityMap(Map<String, String> pinyinCityMap) {
		this.pinyinCityMap = pinyinCityMap;
	}

	@Override
	public String toString() {
		return "HotCityAndPinyinCityDto [hotAreaList=" + hotAreaList
				+ ", pinyinCityMap=" + pinyinCityMap + "]";
	}
	
}
