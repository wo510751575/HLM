package com.lj.business.weixin.dto.friendsjob;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：添加发送朋友圈任务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月21日
 */
public class UpdateSendFriendsJob implements Serializable {
    private static final long serialVersionUID = 4607292372655170729L;
    private String code;

	/**
     * 状态
     */
    private Integer jobState;

    private String remark;
    
	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}




	public Integer getJobState() {
		return jobState;
	}


	public void setJobState(Integer jobState) {
		this.jobState = jobState;
	}


	@Override
	public String toString() {
		return "UpdateSendFriendsJob [code=" + code + ", jobState=" + jobState + ", remark=" + remark + "]";
	}

}
