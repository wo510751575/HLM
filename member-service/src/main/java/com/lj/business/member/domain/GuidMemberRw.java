package com.lj.business.member.domain;

import java.util.Date;

/**
 * 
 * *类说明：导购扩展热文用户
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年6月24日
 */
public class GuidMemberRw {
    /**
     * CODE .
     */
    private String code;

    /**
     * 导购姓名 .
     */
    private String memberName;

    /**
     * 开通时间 .
     */
    private Date openLevelDate;

    /**
     * 结束时间 .
     */
    private Date endLevelDate;

    /**
     * 用户级别：1-普通用户；2-个体用户；3-商户 .
     */
    private String userLevel;

    /**
     * 创建时间 .
     */
    private Date createTime;

    /**
     * 修改时间 .
     */
    private Date updateTime;

    /**
     * 出生年月 .
     */
    private Date birthDate;

    /**
     * 用户喜好 .
     */
    private String userLike;
    
    /**
	 * 商户号
	 */
	private String merchantNo;
	
	/**
	 * 登录名
	 */
	private String loginName;

    
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 开通时间 .
     *
     */
    public Date getOpenLevelDate() {
        return openLevelDate;
    }

    /**
     * 开通时间 .
     *
     */
    public void setOpenLevelDate(Date openLevelDate) {
        this.openLevelDate = openLevelDate;
    }

    /**
     * 结束时间 .
     *
     */
    public Date getEndLevelDate() {
        return endLevelDate;
    }

    /**
     * 结束时间 .
     *
     */
    public void setEndLevelDate(Date endLevelDate) {
        this.endLevelDate = endLevelDate;
    }

    /**
     * 用户级别：1-普通用户；2-个体用户；3-商户 .
     *
     */
    public String getUserLevel() {
        return userLevel;
    }

    /**
     * 用户级别：1-普通用户；2-个体用户；3-商户 .
     *
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? null : userLevel.trim();
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
     * 修改时间 .
     *
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间 .
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 出生年月 .
     *
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * 出生年月 .
     *
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 用户喜好 .
     *
     */
    public String getUserLike() {
        return userLike;
    }

    /**
     * 用户喜好 .
     *
     */
    public void setUserLike(String userLike) {
        this.userLike = userLike == null ? null : userLike.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GuidMemberRw [code=").append(code);
        builder.append(",memberName=").append(memberName); 
        builder.append(",openLevelDate=").append(openLevelDate); 
        builder.append(",endLevelDate=").append(endLevelDate); 
        builder.append(",userLevel=").append(userLevel); 
        builder.append(",createTime=").append(createTime); 
        builder.append(",updateTime=").append(updateTime); 
        builder.append(",birthDate=").append(birthDate); 
        builder.append(",userLike=").append(userLike); 
        builder.append("]");
        return builder.toString();
    }
}