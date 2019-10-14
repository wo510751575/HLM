package com.lj.oms.service.impl.matnr;

import com.lj.oms.utils.excel.annotation.ExcelField;

public class MatnrImportDto {

	@ExcelField(title="物料号", align=2, sort=10)
    private String matnr;
	
	@ExcelField(title="零售价(元)", align=2, sort=20)
	private Integer price;

	/**
	 * @return the matnr
	 */
	public String getMatnr() {
		return matnr;
	}

	/**
	 * @param matnr the matnr to set
	 */
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatnrImportDto [matnr=");
		builder.append(matnr);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
	
}
