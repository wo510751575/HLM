package com.lj.oms.hessian;
import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianConnectionException;
import com.caucho.hessian.client.HessianProxyFactory;
import com.lj.oms.entity.dto.Maike51Dto;
import com.lj.oms.service.Maike51HessianService;

public class SimpleTest {
	public static void main(String[] args) {
//		 String url = "http://mec.lingjukeji.com:8080/sap/hessian/retailService";
//		String url = "http://localhost:8080/oms-web/api/hessian/areaHessianService";
		/* String url = "http://localhost:8080/oms-web/api/hessian/maike51HessianService";
		 
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
//			AreaHessianService area = (AreaHessianService) factory.create(
//					AreaHessianService.class, url);
//			System.out.println(area.findAllList());
			
			Maike51Dto maike51Dto = new Maike51Dto();
			maike51Dto.setNickname("测试1");
			maike51Dto.setPhone("18670275128");
			maike51Dto.setShopId("001");
			maike51Dto.setUserNo("MK001");
			Maike51HessianService serviceImpl = (Maike51HessianService) factory.create(Maike51HessianService.class, url);
			System.out.println(serviceImpl.createUserToKen(maike51Dto));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (HessianConnectionException e) {
			e.printStackTrace();
		}*/
		String a ="123,";
		System.out.println(a.substring(0,a.length()-1));
	}
}
