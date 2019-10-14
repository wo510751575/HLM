package com.ye.business.ad.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * *类说明：广告任务上下架任务
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年7月18日
 */
public class AdvertiseTaskDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8258645392414225677L;

	/////////////////////////////////////////////////////////

	public static final String TYPE_ADVERTISE = "advertise";
	public static final String TYPE_CAROUSEL = "carousel";

	public static final String STATUS_UP = "up";
	public static final String STATUS_DOWN = "down";

	/////////////////////////////////////////////////////////

	/**
	 * 广告code
	 */
	private String code;

	/**
	 * 广告类型：advertise - 用户广告；carousel - 轮播广告
	 */
	private String type;

	/**
	 * 执行时间
	 */
	private Date executeTime;

	/**
	 * 状态：up-上架；down-下架
	 */
	private String status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
