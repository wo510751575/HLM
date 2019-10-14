/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.service.impl.matnr;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.lj.oms.service.impl.BaseJunitTest;
import com.lj.oms.utils.excel.ImportExcel;

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
 * CreateDate: 2018年7月28日
 */
public class MatnrImportTest  extends BaseJunitTest {

	/**
	 * 
	 *
	 * 方法说明：批量发送特殊短信
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2018年7月6日
	 *
	 */
	@Test
	public void testSmsSmsByExcel() throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException {
		File file = new File("D:\\需变更价格的物料20180728.xlsx");
		ImportExcel ei = new ImportExcel(file, 0, 0);
		List<MatnrImportDto> list = ei.getDataList(MatnrImportDto.class);
		StringBuilder sBuilder = new StringBuilder();
		for(MatnrImportDto matnr : list) {
//			System.out.println(matnr.getMatnr() + " - " + matnr.getPrice());
//			System.out.println("select * from product_shop_price psp, sap_product sp where psp.matnr_code = sp.code and sp.matnr = '" + matnr.getMatnr() + "';");
			System.out.println("update product_shop_price psp, sap_product sp set psp.sale_price = " + matnr.getPrice() + " where psp.matnr_code = sp.code and sp.matnr = '" + matnr.getMatnr() + "';");
			sBuilder.append("'").append(matnr.getMatnr()).append("',");
		}
//		System.out.println(sBuilder.toString());
		
	}
}
