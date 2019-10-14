package com.lj.business.member.domain;

import java.util.Date;

public class PersonMemberExt {
    /**
     * code .
     */
    private String code;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     *  .
     */
    private String openId;

    /**
     * 创建时间 .
     */
    private Date createTime;
    
    private String remark4;
    private String remark3;
    private String remark2;
    private String remark;
    private String remark1;
    /**
     * 真实姓名
     */
    private String realName;
    

    public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
     * code .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * code .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 客户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 客户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     *  .
     *
     */
    public String getOpenId() {
        return openId;
    }

    /**
     *  .
     *
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PersonMemberExt [code=").append(code);
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",openId=").append(openId); 
        builder.append(",createTime=").append(createTime); 
        builder.append("]");
        return builder.toString();
    }
}