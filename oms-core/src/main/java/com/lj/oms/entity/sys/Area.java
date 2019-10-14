package com.lj.oms.entity.sys;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ape.common.utils.IdGen;
import com.lj.oms.entity.TreeEntity;
import com.lj.oms.utils.UserUtils;

/**
 * 区域Entity
 */
public class Area extends TreeEntity<Area> {

	public static final String ROOT_ID = "1";
	private static final long serialVersionUID = 1L;
	public static final String CACHE_AREA_VERSION = "areaVersion";
	public static final String TYPE_PRIVINCE = "2";
	public static final String TYPE_CITY = "3";
	public static final String TYPE_REGION = "4";
	
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

	@Override
	public void preInsert() {
		super.preInsert();
		if (!this.isNewRecord){
			setId(IdGen.uuid());
		}
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
			this.createBy = user;
		}
	}
	
	@Override
	public void preUpdate(){
		super.preUpdate();
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
		}
	}
	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}
	
	public static void sortList(List<Area> list, List<Area> sourcelist, String parentId){
		for (int i=0; i<sourcelist.size(); i++){
			Area e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null&& e.getParent().getId().equals(parentId)){
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<sourcelist.size(); j++){
					Area childe = sourcelist.get(j);
					if (childe.getParent()!=null && childe.getParent().getId()!=null
							&& childe.getParent().getId().equals(e.getId())){
						sortList(list, sourcelist, e.getId());
						break;
					}
				}
			}
		}
	}
}