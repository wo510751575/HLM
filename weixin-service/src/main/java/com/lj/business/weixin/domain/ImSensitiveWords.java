package com.lj.business.weixin.domain;

import java.util.Date;

public class ImSensitiveWords {
    /**
     * CODE .
     */
    private String code;

    /**
     * 敏感词 .
     */
    private String word;

    /**
     * 版本号 .
     */
    private Long version;

    /**
     * 状态：0无效、1有效 .
     */
    private Integer status;

    /**
     * 显示序号 .
     */
    private Integer showIndex;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;

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
     * 敏感词 .
     *
     */
    public String getWord() {
        return word;
    }

    /**
     * 敏感词 .
     *
     */
    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    /**
     * 版本号 .
     *
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号 .
     *
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 状态：0无效、1有效 .
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：0无效、1有效 .
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 显示序号 .
     *
     */
    public Integer getShowIndex() {
        return showIndex;
    }

    /**
     * 显示序号 .
     *
     */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ImSensitiveWords [code=").append(code);
        builder.append(",word=").append(word); 
        builder.append(",version=").append(version); 
        builder.append(",status=").append(status); 
        builder.append(",showIndex=").append(showIndex); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}