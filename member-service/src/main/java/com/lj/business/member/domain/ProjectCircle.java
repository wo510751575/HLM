package com.lj.business.member.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * 类说明：项目周期实体类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月5日
 */
public class ProjectCircle implements Serializable {
    private static final long serialVersionUID = 3792812910595321861L;

    /**
     * CODE
     */
    private String code;

    /**
     * 周期名称
     */
    private String circleName;

    /**
     * 周期时长（天）
     */
    private Integer circleDays;

    /**
     * 项目CODE
     */
    private String projectCode;

    /**
     * 显示序号
     */
    private Integer showIndex;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 备注4
     */
    private String remark4;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName == null ? null : circleName.trim();
    }

    public Integer getCircleDays() {
        return circleDays;
    }

    public void setCircleDays(Integer circleDays) {
        this.circleDays = circleDays;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public Integer getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProjectCircle [code=");
        builder.append(code);
        builder.append(", circleName=");
        builder.append(circleName);
        builder.append(", circleDays=");
        builder.append(circleDays);
        builder.append(", projectCode=");
        builder.append(projectCode);
        builder.append(", showIndex=");
        builder.append(showIndex);
        builder.append(", createId=");
        builder.append(createId);
        builder.append(", createDate=");
        builder.append(createDate);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", remark2=");
        builder.append(remark2);
        builder.append(", remark3=");
        builder.append(remark3);
        builder.append(", remark4=");
        builder.append(remark4);
        builder.append("]");
        return builder.toString();
    }
    
}