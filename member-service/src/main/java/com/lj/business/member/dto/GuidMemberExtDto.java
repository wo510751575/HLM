package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

public class GuidMemberExtDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 5462779007316633088L;

	/**
     *  .
     */
    private String code;

    /**
     *  .
     */
    private String memberNoMerchant;

    /**
     *  .
     */
    private String memberNameMerchant;

    /**
     * 终端编号 .
     */
    

    /**
     * 终端名称 .
     */
    

    /**
     * 终端代码(自定义) .
     */
    private String shopNoMerchant;

    /**
     * 姓名 .
     */
    private String memberName;

    /**
     * 工号 .
     */
    private String jobNum;

    /**
     * 出生日期 .
     */
    private String birthday;

    /**
     * 手机 .
     */
    private String mobile;

    /**
     * 年龄 .
     */
    private String age;

    /**
     * 性别：男 male
             女female
              .
     */
    private String gender;

    /**
     * 微信账号 .
     */
    private String noWx;

    /**
     * 微信密码 .
     */
    private String noWxPsw;

    /**
     * 入职时间 .
     */
    private Date workDate;

    /**
     * 职位: 店长/员工 .
     */
    private String position;

    /**
     * 所属区域：营运中心/华南大区/深圳北区/深圳南山欢乐颂店 .
     */
    private String areaName;

    /**
     * 员工状态: INIT初始(未审核) 、
              UNPASS(审核未通过)、
             NORMAL正常、
                    CANCEL注销
                          FREEZE冻结
              .
     */
    private String status;

    /**
     *  .
     */
    private Date createTime;

    /**
     *  .
     */
    private Date updateTime;

    /**
     *  .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     *  .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     *  .
     *
     */
    public String getMemberNoMerchant() {
        return memberNoMerchant;
    }

    /**
     *  .
     *
     */
    public void setMemberNoMerchant(String memberNoMerchant) {
        this.memberNoMerchant = memberNoMerchant == null ? null : memberNoMerchant.trim();
    }

    /**
     *  .
     *
     */
    public String getMemberNameMerchant() {
        return memberNameMerchant;
    }

    /**
     *  .
     *
     */
    public void setMemberNameMerchant(String memberNameMerchant) {
        this.memberNameMerchant = memberNameMerchant == null ? null : memberNameMerchant.trim();
    }

    /**
     * 终端代码(自定义) .
     *
     */
    public String getShopNoMerchant() {
        return shopNoMerchant;
    }

    /**
     * 终端代码(自定义) .
     *
     */
    public void setShopNoMerchant(String shopNoMerchant) {
        this.shopNoMerchant = shopNoMerchant == null ? null : shopNoMerchant.trim();
    }

    /**
     * 姓名 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 工号 .
     *
     */
    public String getJobNum() {
        return jobNum;
    }

    /**
     * 工号 .
     *
     */
    public void setJobNum(String jobNum) {
        this.jobNum = jobNum == null ? null : jobNum.trim();
    }

    /**
     * 出生日期 .
     *
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 出生日期 .
     *
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * 手机 .
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机 .
     *
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 年龄 .
     *
     */
    public String getAge() {
        return age;
    }

    /**
     * 年龄 .
     *
     */
    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    /**
     * 性别：男 male
             女female
              .
     *
     */
    public String getGender() {
        return gender;
    }

    /**
     * 性别：男 male
             女female
              .
     *
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * 微信账号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 微信账号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 微信密码 .
     *
     */
    public String getNoWxPsw() {
        return noWxPsw;
    }

    /**
     * 微信密码 .
     *
     */
    public void setNoWxPsw(String noWxPsw) {
        this.noWxPsw = noWxPsw == null ? null : noWxPsw.trim();
    }

    /**
     * 入职时间 .
     *
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     * 入职时间 .
     *
     */
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    /**
     * 职位: 店长/员工 .
     *
     */
    public String getPosition() {
        return position;
    }

    /**
     * 职位: 店长/员工 .
     *
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 所属区域：营运中心/华南大区/深圳北区/深圳南山欢乐颂店 .
     *
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 所属区域：营运中心/华南大区/深圳北区/深圳南山欢乐颂店 .
     *
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 员工状态: INIT初始(未审核) 、
              UNPASS(审核未通过)、
             NORMAL正常、
                    CANCEL注销
                          FREEZE冻结
              .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 员工状态: INIT初始(未审核) 、
              UNPASS(审核未通过)、
             NORMAL正常、
                    CANCEL注销
                          FREEZE冻结
              .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *  .
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *  .
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *  .
     *
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *  .
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GuidMemberExt [code=").append(code);
        builder.append(",memberNoMerchant=").append(memberNoMerchant); 
        builder.append(",memberNameMerchant=").append(memberNameMerchant); 
        builder.append(",shopNoMerchant=").append(shopNoMerchant); 
        builder.append(",memberName=").append(memberName); 
        builder.append(",jobNum=").append(jobNum); 
        builder.append(",birthday=").append(birthday); 
        builder.append(",mobile=").append(mobile); 
        builder.append(",age=").append(age); 
        builder.append(",gender=").append(gender); 
        builder.append(",noWx=").append(noWx); 
        builder.append(",noWxPsw=").append(noWxPsw); 
        builder.append(",workDate=").append(workDate); 
        builder.append(",position=").append(position); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",status=").append(status); 
        builder.append(",createTime=").append(createTime); 
        builder.append(",updateTime=").append(updateTime); 
        builder.append("]");
        return builder.toString();
    }
}
