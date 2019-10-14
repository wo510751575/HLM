package com.lj.business.api.domain;



import com.lj.oms.entity.TreeEntity;

/**
 * 区域Entity
 */
public class Area extends TreeEntity<Area> {

	private static final long serialVersionUID = 1L;
	public static final String CACHE_AREA_VERSION = "areaVersion";
	private String code; // 区域编码
	private String type; // 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
	private String zone; // 区域地带(0：华东；1：华北；2：华中；3： 华南；4：西南；5：西北；6：东北；7：港澳台)
	private String initial;//拼音首字母
	public Area() {
		super();
		this.sort = 30;
	}

	public Area(String id) {
		super(id);
	}

	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String toString() {
		return name;
	}
	
	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}
}