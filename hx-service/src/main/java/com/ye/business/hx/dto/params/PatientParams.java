package com.ye.business.hx.dto.params;

/**
 * 患者参数
 * 
 * @author bobo
 *
 */
public class PatientParams extends LoginInfo {
	/** 姓名 */
	private String name;

	/** 来源1code */
	private String source1Code;

	/** 手机号 */
	private String phone;

	/** 初诊医生code */
	private String firstMemberNo;

	/** 初诊开始时间 */
	private String startTime;

	/** 初诊结束时间 */
	private String endTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "".equals(name) ? null : name;
	}

	public String getSource1Code() {
		return source1Code;
	}

	public void setSource1Code(String source1Code) {
		this.source1Code = "".equals(source1Code) ? null : source1Code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = "".equals(phone) ? null : phone;
	}

	public String getFirstMemberNo() {
		return firstMemberNo;
	}

	public void setFirstMemberNo(String firstMemberNo) {
		this.firstMemberNo = "".equals(firstMemberNo) ? null : firstMemberNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = "".equals(startTime) ? null : startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = "".equals(endTime) ? null : endTime;
	}
}
