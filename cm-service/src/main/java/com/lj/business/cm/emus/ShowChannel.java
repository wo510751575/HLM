package com.lj.business.cm.emus;
/**
 * 
 * 
 * 类说明：VR素材显示渠道类型
 * 
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2018年07月18日
 */
public enum ShowChannel {
	
	ALL("全部终端"),
	CHANNEL("按系列划分"),
	PART("按终端划分"),
	;
	
	ShowChannel(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
