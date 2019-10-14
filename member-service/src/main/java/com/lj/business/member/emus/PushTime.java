package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：推送时间
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月8日
 */
public enum PushTime {
	
	/**
	 * 新增好友后立即推送
	 */
    PUSHNOW("立即推送"),
    
    /**
     * 导购认领后
     */
    CLAIMED("认领后");

    PushTime(String name){
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
