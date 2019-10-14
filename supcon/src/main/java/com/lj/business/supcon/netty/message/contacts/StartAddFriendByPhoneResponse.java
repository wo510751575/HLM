package com.lj.business.supcon.netty.message.contacts;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 中控开始利用通讯录手机号加粉
 * @author zlh
 *
 */
public class StartAddFriendByPhoneResponse extends BaseResponse {

	private static final long serialVersionUID = 7278074710351424716L;
	
    /**
              * 回应时间
     */
	private Long startTime;
	
	/**
	 * 手机号
	 */
	private String mobile;

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
