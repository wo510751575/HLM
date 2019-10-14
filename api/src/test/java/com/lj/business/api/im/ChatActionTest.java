/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.api.im;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.lj.business.api.ApiHelp;


/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月30日
 */
public class ChatActionTest {

	/**
	 * 
	 *
	 * 方法说明：查询离线聊天记录
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月30日
	 *
	 */
	@Test
	public void testfindOfflineChatInfo() {
		Map<String, Object> businessParamMap = new HashMap<String, Object>();
		businessParamMap.put("memberNoGm", "975150e058564ab0afaa6f9c1a2af676");
		businessParamMap.put("noWxGm", "");
		businessParamMap.put("sendFlag", "1");
		ApiHelp.doPost("im/chat/findOfflineChatInfo.do", businessParamMap);
	}

	/**
	 * 
	 *
	 * 方法说明：查询APP表情包信息
	 *
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月02日
	 *
	 */
	@Test
	public void testFindNewEmojiPackage() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("version", "1509568255889");
		ApiHelp.doPost("im/chat/findEmojiPackage.do", paramMap);
	}
	
	
	@Test
	public void testCollections() {
		Map<String, String> systemParamGroup = new HashMap<String, String>();
		systemParamGroup.put("low10", "10");
		systemParamGroup.put("low20", "20");
		systemParamGroup.put("low30", "30");
		System.out.println(systemParamGroup.values());
		System.out.println(sortByValue(systemParamGroup).values());
	}
	
	/**
	 * 
	 * 方法说明：Map按Value排序 sortByValue (从大到小)
	 * 
	 * @param	Map<K, V> map
	 * @author 彭俊霖 CreateDate: 2017年11月13日
	 *
	 */
//	Java8版本
//    @SuppressWarnings("unchecked")
//	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
//        Map<K, V> result = new LinkedHashMap<>();
//        Stream<Entry<K, V>> st = map.entrySet().stream();
//        st.sorted(Comparator.comparing(e -> ((Entry<K, V>) e).getValue()).reversed()).forEach(e -> result.put(e.getKey(), e.getValue()));
//        return result;
//    }
    
//	Java7版本
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o2, Map.Entry<K, V> o1) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
	
}
