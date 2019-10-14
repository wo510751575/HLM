package com.lj.oms.utils.excel.dto;

import java.io.Serializable;

import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明:敏感词
 *  
 * 
 * <p>
 * 详细描述:
 *   
 * @Company: 扬恩科技有限公司
 * @author 林进权
 *   
 * CreateDate: 2017年11月15日
 */
public class SensitiveWordExportDto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -8475436664919196432L;

	/**
     * 敏感词
     */
	@ExcelField(title="敏感词", align=2, sort=10)
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
