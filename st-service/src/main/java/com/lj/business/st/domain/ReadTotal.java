package com.lj.business.st.domain;

import java.util.Date;

public class ReadTotal {
    /**
     * CODE .
     */
    private String code;

    /**
     * 文章名称 .
     */
    private String name;

    /**
     * URL地址 .
     */
    private String urlAddress;

    /**
     * 访问量 .
     */
    private Long visitNum;

    /**
     * 访问人数（一人只能统计一次） .
     */
    private Long visitNumPerson;

    /**
     * 创建时间 .
     */
    private Date createDate;

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
     * 文章名称 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 文章名称 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * URL地址 .
     *
     */
    public String getUrlAddress() {
        return urlAddress;
    }

    /**
     * URL地址 .
     *
     */
    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress == null ? null : urlAddress.trim();
    }

    /**
     * 访问量 .
     *
     */
    public Long getVisitNum() {
        return visitNum;
    }

    /**
     * 访问量 .
     *
     */
    public void setVisitNum(Long visitNum) {
        this.visitNum = visitNum;
    }

    /**
     * 访问人数（一人只能统计一次） .
     *
     */
    public Long getVisitNumPerson() {
        return visitNumPerson;
    }

    /**
     * 访问人数（一人只能统计一次） .
     *
     */
    public void setVisitNumPerson(Long visitNumPerson) {
        this.visitNumPerson = visitNumPerson;
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReadTotal [code=").append(code);
        builder.append(",name=").append(name); 
        builder.append(",urlAddress=").append(urlAddress); 
        builder.append(",visitNum=").append(visitNum); 
        builder.append(",visitNumPerson=").append(visitNumPerson); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}