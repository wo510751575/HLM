package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：商户试用状态
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 梅宏博
 *   
 * CreateDate: 2017年10月25日
 */
public enum MerchantProbationStatusEnum {

	/** 试用期. */
	PROBATION("试用期")
	,

	/** 试用结束. */
	END("试用结束"),

	/** 续费. */
	RENEW("续费")
	;

	/**
	 * Instantiates a new member status.
	 *
	 * @param chName the ch name
	 */
	MerchantProbationStatusEnum(String name){
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static MerchantProbationStatusEnum getStatus(String days){
		switch (days) {
		case "0":
			return END;
			
		case "-1":
			return RENEW;

		default:
			return PROBATION;
		}
	}
}
