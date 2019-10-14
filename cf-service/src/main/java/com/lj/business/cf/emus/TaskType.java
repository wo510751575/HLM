package com.lj.business.cf.emus;

/**
 * 
 * 
 * 类说明：任务类型
 *  
 * 
 * <p>
 * 详细描述：各类型顺序请不要随意打乱，在初始化WORK_TASK_CHOOSE工作事项选择表（基础）表时，将以该排序作为字段SEQ的值，并显示到APP
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月19日
 */
public enum TaskType {
	
	/**
	 * 新增客户
	 */
	XIN_ZENG("新增客户"),

	/**
	 * 业务任务
	 */
	GOU_TONG("业务任务"),
	
	/**
	 * 社交任务
	 */
	SHE_JIAO("社交任务"),
	
	/**
	 * 销售目标
	 */
	XIAO_SHOU("销售目标");
	
	private String name;
	
	TaskType(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
