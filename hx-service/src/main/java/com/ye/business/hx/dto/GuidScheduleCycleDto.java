/**
 * 
 */
package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * 类说明：員工一周的排班
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年3月12日
 */
public class GuidScheduleCycleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8785676389927506771L;

	/** 员工编号 */
	private String memberNoGuid;

	/** 员工姓名 */
	private String memberNameGuid;
	/** 排班期内的周期 */
	private List<GuidScheduleDto> schedules;

	public String getMemberNoGuid() {
		return memberNoGuid;
	}

	public void setMemberNoGuid(String memberNoGuid) {
		this.memberNoGuid = memberNoGuid;
	}

	public String getMemberNameGuid() {
		return memberNameGuid;
	}

	public void setMemberNameGuid(String memberNameGuid) {
		this.memberNameGuid = memberNameGuid;
	}

	public List<GuidScheduleDto> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<GuidScheduleDto> schedules) {
		this.schedules = schedules;
	}

	@Override
	public String toString() {
		return "GuidScheduleCycleDto [memberNoGuid=" + memberNoGuid + ", memberNameGuid=" + memberNameGuid
				+ ", schedules=" + schedules + "]";
	}

}
