/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.st.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ape.common.utils.Encodes;
import com.ape.common.utils.Reflections;
import com.google.common.collect.Lists;
import com.lj.oms.emus.GradeType;
import com.lj.oms.utils.DictUtils;
import com.lj.oms.utils.excel.BaseExcel;
import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明：统计报表 自定义导出Excel文件
 *   （导出“XLSX”格式，支持大数据量导出   @see org.apache.poi.ss.SpreadsheetVersion）
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2018年06月19日
 */
public class OperationExportExcel extends BaseExcel{
	
	private static Logger log = LoggerFactory.getLogger(OperationExportExcel.class);
			
	/**
	 * 工作薄对象
	 */
	private SXSSFWorkbook wb;
	
	/**
	 * 工作表对象
	 */
	private Sheet sheet;
	
	/**
	 * 样式列表
	 */
	private Map<String, CellStyle> styles;
	
	/**
	 * 当前行号
	 */
	private int rownum;
	
	/**
	 * 注解列表（Object[]{ ExcelField, Field/Method }）
	 */
	List<Object[]> annotationList = Lists.newArrayList();
	
	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param cls 实体对象，通过annotation.ExportField获取标题
	 * @param type 导出类型（1:导出数据；2：导出模板）
	 * @param gradeType 机构类型
	 * @param groups 导入分组
	 */
	public OperationExportExcel(String title, Class<?> cls, int type, String gradeType, int... groups){
		sort(annotationList, cls, type, groups);
		// Initialize
		List<String> headerList = Lists.newArrayList();
		for (Object[] os : annotationList){
			String t = ((ExcelField)os[0]).title();
			// 如果是导出，则去掉注释
			if (type==1){
				String[] ss = StringUtils.split(t, "**", 2);
				if (ss.length==2){
					t = ss[0];
				}
			}
			headerList.add(t);
		}
		if(GradeType.MERCHANT.getValue().equals(gradeType)){
			merchantCustomInitialize(title, headerList);
		}else if(GradeType.DEALER.getValue().equals(gradeType)){
			companyCustomInitialize(title, headerList);
		}else if(GradeType.SHOP.getValue().equals(gradeType)){
			shopCustomInitialize(title, headerList);
		}
	}
	
	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headers 表头数组
	 */
	public OperationExportExcel(String title, String[] headers) {
		initialize(title, Lists.newArrayList(headers));
	}
	
	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	public OperationExportExcel(String title, List<String> headerList) {
		initialize(title, headerList);
	}
	
	/**
	 * 初始化函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	private void initialize(String title, List<String> headerList) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
		}
		
		// Add cell
		Row cellRow = sheet.createRow(rownum++);
		cellRow.setHeightInPoints(16);
		
		//自定义样式
		CellStyle tempStyle = styles.get("header");
		tempStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		Font tempFont = wb.createFont();
//		tempFont.setFontName("SimSum");
		tempFont.setFontName("Arial");
		tempFont.setFontHeightInPoints((short) 11);
		tempFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		tempFont.setColor(IndexedColors.BLACK.getIndex());
		tempStyle.setFont(tempFont);
		
		Cell autoCell0 = cellRow.createCell(0);
		autoCell0.setCellStyle(tempStyle);
		autoCell0.setCellValue("日期");
		CellRangeAddress region0 = new CellRangeAddress(1,2,0,0);
		sheet.addMergedRegion(region0);
		setBorderStyle(CellStyle.BORDER_THIN, region0, sheet, wb);
		
		Cell autoCell1 = cellRow.createCell(1);
		autoCell1.setCellStyle(tempStyle);
		autoCell1.setCellValue("客户情况");
		CellRangeAddress region1 = new CellRangeAddress(1,1,1,2);
		sheet.addMergedRegion(region1);
		setBorderStyle(CellStyle.BORDER_THIN, region1, sheet, wb);
		
		Cell autoCell2 = cellRow.createCell(3);
		autoCell2.setCellStyle(tempStyle);
		autoCell2.setCellValue("下单情况");
		CellRangeAddress region2 = new CellRangeAddress(1,1,3,8);
		sheet.addMergedRegion(region2);
		setBorderStyle(CellStyle.BORDER_THIN, region2, sheet, wb);
		
		Cell autoCell3 = cellRow.createCell(9);
		autoCell3.setCellStyle(tempStyle);
		autoCell3.setCellValue("成交情况");
		CellRangeAddress region3 = new CellRangeAddress(1,1,9,14);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region3);
		setBorderStyle(CellStyle.BORDER_THIN, region3, sheet, wb);//统一 合并单元格 样式
		
		Cell autoCell4 = cellRow.createCell(15);
		autoCell4.setCellStyle(tempStyle);
		autoCell4.setCellValue("客户访问情况");
		CellRangeAddress region4 = new CellRangeAddress(1,1,15,18);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region4);
		setBorderStyle(CellStyle.BORDER_THIN, region4, sheet, wb);//统一 合并单元格 样式
		
		Cell autoCell5 = cellRow.createCell(19);
		autoCell5.setCellStyle(tempStyle);
		autoCell5.setCellValue("其他");
		CellRangeAddress region5 = new CellRangeAddress(1,1,19,19);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region5);
		setBorderStyle(CellStyle.BORDER_THIN, region5, sheet, wb);//统一 合并单元格 样式
		
		// Create header
		if (headerList == null){
			throw new RuntimeException("headerList not null!");
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length==2){
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
						new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			}else{
				cell.setCellValue(headerList.get(i));
			}
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < headerList.size(); i++) {  
			int colWidth = sheet.getColumnWidth(i)*2;
	        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
		}
		log.debug("Initialize success.");
	}
	
	/**
	 * 商城运营日报-初始化函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	private void merchantCustomInitialize(String title, List<String> headerList) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
		}
		
		// Add cell
		Row cellRow = sheet.createRow(rownum++);
		cellRow.setHeightInPoints(16);
		
		//自定义样式
		CellStyle tempStyle = styles.get("header");
		tempStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		Font tempFont = wb.createFont();
//		tempFont.setFontName("SimSum");
		tempFont.setFontName("Arial");
		tempFont.setFontHeightInPoints((short) 11);
		tempFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		tempFont.setColor(IndexedColors.BLACK.getIndex());
		tempStyle.setFont(tempFont);
		
		Cell autoCell0 = cellRow.createCell(0);
		autoCell0.setCellStyle(tempStyle);
		autoCell0.setCellValue("日期");
		CellRangeAddress region0 = new CellRangeAddress(1,2,0,0);
		sheet.addMergedRegion(region0);
		setBorderStyle(CellStyle.BORDER_THIN, region0, sheet, wb);
		
		Cell autoCell1 = cellRow.createCell(1);
		autoCell1.setCellStyle(tempStyle);
		autoCell1.setCellValue("客户情况");
		CellRangeAddress region1 = new CellRangeAddress(1,1,1,2);
		sheet.addMergedRegion(region1);
		setBorderStyle(CellStyle.BORDER_THIN, region1, sheet, wb);
		
		Cell autoCell2 = cellRow.createCell(3);
		autoCell2.setCellStyle(tempStyle);
		autoCell2.setCellValue("下单情况");
		CellRangeAddress region2 = new CellRangeAddress(1,1,3,8);
		sheet.addMergedRegion(region2);
		setBorderStyle(CellStyle.BORDER_THIN, region2, sheet, wb);
		
		Cell autoCell3 = cellRow.createCell(9);
		autoCell3.setCellStyle(tempStyle);
		autoCell3.setCellValue("成交情况");
		CellRangeAddress region3 = new CellRangeAddress(1,1,9,14);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region3);
		setBorderStyle(CellStyle.BORDER_THIN, region3, sheet, wb);//统一 合并单元格 样式
		
		Cell autoCell4 = cellRow.createCell(15);
		autoCell4.setCellStyle(tempStyle);
		autoCell4.setCellValue("客户访问情况");
		CellRangeAddress region4 = new CellRangeAddress(1,1,15,18);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region4);
		setBorderStyle(CellStyle.BORDER_THIN, region4, sheet, wb);//统一 合并单元格 样式
		
		Cell autoCell5 = cellRow.createCell(19);
		autoCell5.setCellStyle(tempStyle);
		autoCell5.setCellValue("其他");
		CellRangeAddress region5 = new CellRangeAddress(1,1,19,19);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region5);
		setBorderStyle(CellStyle.BORDER_THIN, region5, sheet, wb);//统一 合并单元格 样式
		
		// Create header
		if (headerList == null){
			throw new RuntimeException("headerList not null!");
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length==2){
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
						new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			}else{
				cell.setCellValue(headerList.get(i));
			}
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < headerList.size(); i++) {  
			int colWidth = sheet.getColumnWidth(i)*2;
			sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
		}
		log.debug("Initialize success.");
	}
	
	/**
	 * 经销商运营日报-初始化函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	private void companyCustomInitialize(String title, List<String> headerList) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
		}
		
		// Add cell
		Row cellRow = sheet.createRow(rownum++);
		cellRow.setHeightInPoints(16);
		
		//自定义样式
		CellStyle tempStyle = styles.get("header");
		tempStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		Font tempFont = wb.createFont();
//		tempFont.setFontName("SimSum");
		tempFont.setFontName("Arial");
		tempFont.setFontHeightInPoints((short) 11);
		tempFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		tempFont.setColor(IndexedColors.BLACK.getIndex());
		tempStyle.setFont(tempFont);
		
		Cell autoCell0 = cellRow.createCell(0);
		autoCell0.setCellStyle(tempStyle);
		autoCell0.setCellValue("日期");
		CellRangeAddress region0 = new CellRangeAddress(1,2,0,0);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region0);
		setBorderStyle(CellStyle.BORDER_THIN, region0, sheet, wb);
		
		Cell autoCell1 = cellRow.createCell(1);
		autoCell1.setCellStyle(tempStyle);
		autoCell1.setCellValue("经销商代码");
		CellRangeAddress region1 = new CellRangeAddress(1,2,1,1);
		sheet.addMergedRegion(region1);
		setBorderStyle(CellStyle.BORDER_THIN, region1, sheet, wb);
		
		Cell autoCell2 = cellRow.createCell(2);
		autoCell2.setCellStyle(tempStyle);
		autoCell2.setCellValue("经销商名称");
		CellRangeAddress region2 = new CellRangeAddress(1,2,2,2);
		sheet.addMergedRegion(region2);
		setBorderStyle(CellStyle.BORDER_THIN, region2, sheet, wb);
		
		Cell autoCell3 = cellRow.createCell(3);
		autoCell3.setCellStyle(tempStyle);
		autoCell3.setCellValue("客户情况");
		CellRangeAddress region3 = new CellRangeAddress(1,1,3,4);
		sheet.addMergedRegion(region3);
		setBorderStyle(CellStyle.BORDER_THIN, region3, sheet, wb);
		
		Cell autoCell4 = cellRow.createCell(5);
		autoCell4.setCellStyle(tempStyle);
		autoCell4.setCellValue("下单情况");
		CellRangeAddress region4 = new CellRangeAddress(1,1,5,10);
		sheet.addMergedRegion(region4);
		setBorderStyle(CellStyle.BORDER_THIN, region4, sheet, wb);
		
		Cell autoCell5 = cellRow.createCell(11);
		autoCell5.setCellStyle(tempStyle);
		autoCell5.setCellValue("成交情况");
		CellRangeAddress region5 = new CellRangeAddress(1,1,11,16);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region5);
		setBorderStyle(CellStyle.BORDER_THIN, region5, sheet, wb);//统一 合并单元格 样式
		
		Cell autoCell6 = cellRow.createCell(17);
		autoCell6.setCellStyle(tempStyle);
		autoCell6.setCellValue("客户访问情况");
		CellRangeAddress region6 = new CellRangeAddress(1,1,17,20);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region6);
		setBorderStyle(CellStyle.BORDER_THIN, region6, sheet, wb);//统一 合并单元格 样式
		
		Cell autoCell7 = cellRow.createCell(21);
		autoCell7.setCellStyle(tempStyle);
		autoCell7.setCellValue("其他");
		CellRangeAddress region7 = new CellRangeAddress(1,1,21,21);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region7);
		setBorderStyle(CellStyle.BORDER_THIN, region7, sheet, wb);//统一 合并单元格 样式
		
		// Create header
		if (headerList == null){
			throw new RuntimeException("headerList not null!");
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length==2){
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
						new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			}else{
				cell.setCellValue(headerList.get(i));
			}
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < headerList.size(); i++) {  
			int colWidth = sheet.getColumnWidth(i)*2;
			sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
		}
		log.debug("Initialize success.");
	}
	
	/**
	 * 门诊运营日报-初始化函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	private void shopCustomInitialize(String title, List<String> headerList) {
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
		}
		
		// Add cell
		Row cellRow = sheet.createRow(rownum++);
		cellRow.setHeightInPoints(16);
		
		//自定义样式
		CellStyle tempStyle = styles.get("header");
		tempStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		tempStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		Font tempFont = wb.createFont();
//		tempFont.setFontName("SimSum");
		tempFont.setFontName("Arial");
		tempFont.setFontHeightInPoints((short) 11);
		tempFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		tempFont.setColor(IndexedColors.BLACK.getIndex());
		tempStyle.setFont(tempFont);
		
		Cell autoCell0 = cellRow.createCell(0);
		autoCell0.setCellStyle(tempStyle);
		autoCell0.setCellValue("日期");
		CellRangeAddress region0 = new CellRangeAddress(1,2,0,0);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region0);
		setBorderStyle(CellStyle.BORDER_THIN, region0, sheet, wb);
		
		Cell autoCell1 = cellRow.createCell(1);
		autoCell1.setCellStyle(tempStyle);
		autoCell1.setCellValue("经销商代码");
		CellRangeAddress region1 = new CellRangeAddress(1,2,1,1);
		sheet.addMergedRegion(region1);
		setBorderStyle(CellStyle.BORDER_THIN, region1, sheet, wb);
		
		Cell autoCell2 = cellRow.createCell(2);
		autoCell2.setCellStyle(tempStyle);
		autoCell2.setCellValue("经销商名称");
		CellRangeAddress region2 = new CellRangeAddress(1,2,2,2);
		sheet.addMergedRegion(region2);
		setBorderStyle(CellStyle.BORDER_THIN, region2, sheet, wb);
		
		Cell autoCell3 = cellRow.createCell(3);
		autoCell3.setCellStyle(tempStyle);
		autoCell3.setCellValue("门诊代码");
		CellRangeAddress region3 = new CellRangeAddress(1,2,3,3);
		sheet.addMergedRegion(region3);
		setBorderStyle(CellStyle.BORDER_THIN, region3, sheet, wb);
		
		Cell autoCell4 = cellRow.createCell(4);
		autoCell4.setCellStyle(tempStyle);
		autoCell4.setCellValue("门诊名称");
		CellRangeAddress region4 = new CellRangeAddress(1,2,4,4);
		sheet.addMergedRegion(region4);
		setBorderStyle(CellStyle.BORDER_THIN, region4, sheet, wb);
		
		Cell autoCell5 = cellRow.createCell(5);
		autoCell5.setCellStyle(tempStyle);
		autoCell5.setCellValue("客户情况");
		CellRangeAddress region5 = new CellRangeAddress(1,1,5,6);
		sheet.addMergedRegion(region5);
		setBorderStyle(CellStyle.BORDER_THIN, region5, sheet, wb);
		
		Cell autoCell6 = cellRow.createCell(7);
		autoCell6.setCellStyle(tempStyle);
		autoCell6.setCellValue("下单情况");
		CellRangeAddress region6 = new CellRangeAddress(1,1,7,12);
		sheet.addMergedRegion(region6);
		setBorderStyle(CellStyle.BORDER_THIN, region6, sheet, wb);
		
		Cell autoCell7 = cellRow.createCell(13);
		autoCell7.setCellStyle(tempStyle);
		autoCell7.setCellValue("成交情况");
		CellRangeAddress region7 = new CellRangeAddress(1,1,13,18);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region7);
		setBorderStyle(CellStyle.BORDER_THIN, region7, sheet, wb);//统一 合并单元格 样式
		
		Cell autoCell8 = cellRow.createCell(19);
		autoCell8.setCellStyle(tempStyle);
		autoCell8.setCellValue("客户访问情况");
		CellRangeAddress region8 = new CellRangeAddress(1,1,19,22);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region8);
		setBorderStyle(CellStyle.BORDER_THIN, region8, sheet, wb);//统一 合并单元格 样式
		
		Cell autoCell9 = cellRow.createCell(23);
		autoCell9.setCellStyle(tempStyle);
		autoCell9.setCellValue("其他");
		CellRangeAddress region9 = new CellRangeAddress(1,1,23,23);//起始行,结束行,起始列,结束列
		sheet.addMergedRegion(region9);
		setBorderStyle(CellStyle.BORDER_THIN, region9, sheet, wb);//统一 合并单元格 样式
		
		// Create header
		if (headerList == null){
			throw new RuntimeException("headerList not null!");
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length==2){
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
						new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			}else{
				cell.setCellValue(headerList.get(i));
			}
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < headerList.size(); i++) {  
			int colWidth = sheet.getColumnWidth(i)*2;
			sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
		}
		log.debug("Initialize success.");
	}
	
	/**
	 * 统一 合并单元格 样式
	 * @param border
	 * @param region
	 * @param sheet
	 * @param wb
	 *
	 * @author 彭俊霖
	 * @CreateDate 2018年6月19日下午3:23:40
	 */
	private void setBorderStyle(int border, CellRangeAddress region, Sheet sheet, SXSSFWorkbook wb){
        RegionUtil.setBorderBottom(border, region, sheet, wb);   //下边框
        RegionUtil.setBorderLeft(border, region, sheet, wb);     //左边框
        RegionUtil.setBorderRight(border, region, sheet, wb);    //右边框
        RegionUtil.setBorderTop(border, region, sheet, wb);      //上边框
    }
	
	/**
	 * 创建表格样式
	 * @param wb 工作薄对象
	 * @return 样式列表
	 */
	private Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		styles.put("title", style);

		style = wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		Font dataFont = wb.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 10);
		style.setFont(dataFont);
		styles.put("data", style);
		
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_LEFT);
		styles.put("data1", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);
		styles.put("data2", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		styles.put("data3", style);
		
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
//		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font headerFont = wb.createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		styles.put("header", style);
		
		return styles;
	}

	/**
	 * 添加一行
	 * @return 行对象
	 */
	public Row addRow(){
		return sheet.createRow(rownum++);
	}
	

	/**
	 * 添加一个单元格
	 * @param row 添加的行
	 * @param column 添加列号
	 * @param val 添加值
	 * @return 单元格对象
	 */
	public Cell addCell(Row row, int column, Object val){
		return this.addCell(row, column, val, 0, Class.class);
	}
	
	/**
	 * 添加一个单元格
	 * @param row 添加的行
	 * @param column 添加列号
	 * @param val 添加值
	 * @param align 对齐方式（1：靠左；2：居中；3：靠右）
	 * @return 单元格对象
	 */
	public Cell addCell(Row row, int column, Object val, int align, Class<?> fieldType){
		Cell cell = row.createCell(column);
		CellStyle style = styles.get("data"+(align>=1&&align<=3?align:""));
		try {
			if (val == null){
				cell.setCellValue("");
			} else if (val instanceof String) {
				cell.setCellValue((String) val);
			} else if (val instanceof Integer) {
				cell.setCellValue((Integer) val);
			} else if (val instanceof Long) {
				cell.setCellValue((Long) val);
			} else if (val instanceof Double) {
				cell.setCellValue((Double) val);
			} else if (val instanceof Float) {
				cell.setCellValue((Float) val);
			} else if (val instanceof Date) {
				DataFormat format = wb.createDataFormat();
	            style.setDataFormat(format.getFormat("yyyy-MM-dd"));
				cell.setCellValue((Date) val);
			} else {
				if (fieldType != Class.class){
					cell.setCellValue((String)fieldType.getMethod("setValue", Object.class).invoke(null, val));
				}else{
					cell.setCellValue((String)Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(), 
						"fieldtype."+val.getClass().getSimpleName()+"Type")).getMethod("setValue", Object.class).invoke(null, val));
				}
			}
		} catch (Exception ex) {
			log.info("Set cell value ["+row.getRowNum()+","+column+"] error: " + ex.toString());
			cell.setCellValue(val.toString());
		}
		cell.setCellStyle(style);
		return cell;
	}

	/**
	 * 添加数据（通过annotation.ExportField添加数据）
	 * @return list 数据列表
	 */
	public <E> OperationExportExcel setDataList(List<E> list){
		for (E e : list){
			int colunm = 0;
			Row row = this.addRow();
			StringBuilder sb = new StringBuilder();
			for (Object[] os : annotationList){
				ExcelField ef = (ExcelField)os[0];
				Object val = null;
				// Get entity value
				try{
					if (StringUtils.isNotBlank(ef.value())){
						val = Reflections.invokeGetter(e, ef.value());
					}else{
						if (os[1] instanceof Field){
							val = Reflections.invokeGetter(e, ((Field)os[1]).getName());
						}else if (os[1] instanceof Method){
							val = Reflections.invokeMethod(e, ((Method)os[1]).getName(), new Class[] {}, new Object[] {});
						}
					}
					// If is dict, get dict label
					if (StringUtils.isNotBlank(ef.dictType())){
						val = DictUtils.getDictLabel(val==null?"":val.toString(), ef.dictType(), "");
					}
				}catch(Exception ex) {
					// Failure to ignore
					log.info(ex.toString());
					val = "";
				}
				this.addCell(row, colunm++, val, ef.align(), ef.fieldType());
				sb.append(val + ", ");
			}
			log.debug("Write success: ["+row.getRowNum()+"] "+sb.toString());
		}
		return this;
	}
	
	/**
	 * 输出数据流
	 * @param os 输出数据流
	 */
	public OperationExportExcel write(OutputStream os) throws IOException{
		wb.write(os);
		os.close();
		return this;
	}
	
	/**
	 * 输出到客户端
	 * @param fileName 输出文件名
	 */
	public OperationExportExcel write(HttpServletResponse response, String fileName) throws IOException{
		response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+Encodes.urlEncode(fileName));
		write(response.getOutputStream());
		return this;
	}
	
	/**
	 * 输出到文件
	 * @param fileName 输出文件名
	 */
	public OperationExportExcel writeFile(String name) throws FileNotFoundException, IOException{
		FileOutputStream os = new FileOutputStream(name);
		this.write(os);
		return this;
	}
	
	/**
	 * 清理临时文件
	 */
	public OperationExportExcel dispose(){
		wb.dispose();
		return this;
	}
	
	
//	/**
//	 * 导出测试
//	 */
//	public static void main(String[] args) throws Throwable {
//		
//		List<String> headerList = Lists.newArrayList();
//		for (int i = 1; i <= 10; i++) {
//			headerList.add("表头"+i);
//		}
//		
//		List<String> dataRowList = Lists.newArrayList();
//		for (int i = 1; i <= headerList.size(); i++) {
//			dataRowList.add("数据"+i);
//		}
//		
//		List<List<String>> dataList = Lists.newArrayList();
//		for (int i = 1; i <=1000000; i++) {
//			dataList.add(dataRowList);
//		}
//
//		ExportExcel ee = new ExportExcel("表格标题", headerList);
//		
//		for (int i = 0; i < dataList.size(); i++) {
//			Row row = ee.addRow();
//			for (int j = 0; j < dataList.get(i).size(); j++) {
//				ee.addCell(row, j, dataList.get(i).get(j));
//			}
//		}
//		
//		ee.writeFile("target/export.xlsx");
//
//		ee.dispose();
//		
//		log.debug("Export success.");
//		
//	}

}
