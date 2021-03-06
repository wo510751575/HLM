package com.lj.oms.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ape.common.mapper.JsonMapper;
import com.ape.common.utils.CacheUtils;
import com.ape.common.utils.SpringContextHolder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.oms.dao.sys.DictDao;
import com.lj.oms.entity.sys.Dict;

/**
 * 字典工具类
 */
public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	/**
	 * 外部系统对接角色字典类型
	 */
	public static final String OS_ROLE = "os_role";
	
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
	/**
	 * 删除指定的字典
	 * @param bigList
	 * @param args
	 * @return
	 */
	public static List<Dict> delList(List<Dict>bigList,String ...args){
		
		List<String> list = Lists.newArrayList();
		for (String temp : args) {
			list.add(temp);
		}
		String[] bArray = list.toArray(new String[list.size()]);
		int len = bigList.size() - 1;
		for (int i = len; i >= 0; i--) {
			Dict dic = bigList.get(i);
			for (int j = 0; j < bArray.length; j++) {
				String str = bArray[j];
				if (dic.getValue().equals(str)) {
					bigList.remove(i);
					break;
				}
			}
		}
		return bigList;
	}
	
	public static List<Dict> delList(List<Dict>bigList){
		return bigList;
	}
	
	/**
	 * 
	 *
	 * 方法说明：Excel数字类型转码
	 *
	 * @param oldString
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年8月16日
	 *
	 */
	public static String excelChage(String oldString){
		if(StringUtils.isBlank(oldString)) {
			return oldString;
		}
		String newStr = oldString.replace(".","")
				.replace("E8", "")
				.replace("E9", "")
				.replace("E10", "")
				.replace("E11", "")
				.replace("E12", "")
				.replace("E13", "")
				.replace("E14", "")
				.replace("E15", "")
				.replace("E16", "")
				.replace("E17", "")
				.replace("E18", "")
				.replace("E19", "")
				.replace("E20", "");
		return newStr;
	}
	
	/**
	 * 验证显示序号是否已存在
	 * @param showIndexList 
	 * @param showIndex
	 * @author 彭俊霖 CreateDate: 2017年11月20日
	 * 
	 * @return
	 */
	public static String hasShowIndex(List<Integer> showIndexList, Integer showIndex) {
		if (showIndexList.size()>0) {
			for (Integer oldShowIndex : showIndexList) {
				if(oldShowIndex.equals(showIndex)){
					return "true";
				}
			}
		}
		return "false";
	}
	
}
