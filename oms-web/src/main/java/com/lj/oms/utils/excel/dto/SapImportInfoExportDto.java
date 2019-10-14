package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明:SAP物料价格导入
 *  
 * 
 * <p>
 * 详细描述:
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2018年07月27日
 */
public class SapImportInfoExportDto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -8475436664919196432L;

	/**
     * 商品编码
     */
	@ExcelField(title="物料号", align=2, sort=10)
    private String matnr;
	
	/**
	 * 基本单位
	 */
	@ExcelField(title="基本单位", align=2, sort=20)
	private String kmein;
	
	/**
	 * 新建议零售价
	 */
	@ExcelField(title="零售价", align=2, sort=40)
	private String kbetr;

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getKmein() {
		return kmein;
	}

	public void setKmein(String kmein) {
		this.kmein = kmein;
	}

	public String getKbetr() {
		return kbetr;
	}

	public void setKbetr(String kbetr) {
		this.kbetr = kbetr;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SapImportInfoExportDto [matnr=");
		builder.append(matnr);
		builder.append(", kmein=");
		builder.append(kmein);
		builder.append(", kbetr=");
		builder.append(kbetr);
		builder.append("]");
		return builder.toString();
	}

}
