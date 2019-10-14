package com.lj.oms.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.oms.entity.sys.Area;
import com.lj.oms.service.sys.AreaService;

public class AreaHessianServiceTest extends BaseJunitTest{

	@Autowired
	AreaHessianServiceImpl areaHessianService;
	@Autowired
	AreaService areaService;
	
	@Test
	public void testArea() throws Exception {
		Map map = areaService.selectCurrentLevelByAreaId("6");
		System.out.println(map);
	}
	
	@Test
	public void findShopCity() throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("merchantNo", "e79d975846ee41ba8c3c55738fda66a9");
		areaHessianService.findShopCity(map);
	}
	
	@Test
	public void selectProvince() throws Exception {
		String area = "广东省";
		List<Area> provinces = areaHessianService.selectProvince();
		for (Area province : provinces) {
//			if(StringUtils.contains(province.getName(), area)){
//				System.out.println();
//			}
			System.out.println(province.getName());
		}
	}
	
	@Test
	public void testPhone() throws Exception {
		System.out.println(!isTelephone("0335-3196011"));
		
	}
	
	public static  boolean isTelephone(String str) {  
    	return Regular(str,TELEPHONE);  
    }
	
	public static final String TELEPHONE = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{2}\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)|(0\\d{3}\\d{7,8}(-\\d{1,4})?)$" ; 
	
	private static  boolean Regular(String str,String pattern){  
        System.out.println("pattern="+pattern);  
        if(null == str || str.trim().length()<=0)  
            return false;           
        Pattern p = Pattern.compile(pattern);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    } 
	
	@Test
	public void testCode() throws Exception {
		Area province=areaService.findAreaByCode("108");
		System.out.println(province.getName());
	}
}
