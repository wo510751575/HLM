package com.lj.business.st.domain;

import java.util.Date;

public class StList {
    /**
     * CODE .
     */
    private String code;

    /**
     * 项目名称 .
     */
    private String nameList;

    /**
     * 项目描述 .
     */
    private String desList;

    /**
     * 状态              启用：Y             停用：N              .
     */
    private String status;

    /**
     * 项目类型                           .
     */
    private String typeList;

    /**
     * 项目单位             个：GE             元：YUAN .
     */
    private String unitList;

    /**
     * 表类型             日工作简报项目表：WORK_BR_DAY_LIST              .
     */
    private String tableList;

    /**
     * 图片地址 .
     */
    private String imgAddr;

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
     * 项目名称 .
     *
     */
    public String getNameList() {
        return nameList;
    }

    /**
     * 项目名称 .
     *
     */
    public void setNameList(String nameList) {
        this.nameList = nameList == null ? null : nameList.trim();
    }

    /**
     * 项目描述 .
     *
     */
    public String getDesList() {
        return desList;
    }

    /**
     * 项目描述 .
     *
     */
    public void setDesList(String desList) {
        this.desList = desList == null ? null : desList.trim();
    }

    /**
     * 状态              启用：Y             停用：N              .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态              启用：Y             停用：N              .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 项目类型                           .
     *
     */
    public String getTypeList() {
        return typeList;
    }

    /**
     * 项目类型                           .
     *
     */
    public void setTypeList(String typeList) {
        this.typeList = typeList == null ? null : typeList.trim();
    }

    /**
     * 项目单位             个：GE             元：YUAN .
     *
     */
    public String getUnitList() {
        return unitList;
    }

    /**
     * 项目单位             个：GE             元：YUAN .
     *
     */
    public void setUnitList(String unitList) {
        this.unitList = unitList == null ? null : unitList.trim();
    }

    /**
     * 表类型             日工作简报项目表：WORK_BR_DAY_LIST              .
     *
     */
    public String getTableList() {
        return tableList;
    }

    /**
     * 表类型             日工作简报项目表：WORK_BR_DAY_LIST              .
     *
     */
    public void setTableList(String tableList) {
        this.tableList = tableList == null ? null : tableList.trim();
    }

    /**
     * 图片地址 .
     *
     */
    public String getImgAddr() {
        return imgAddr;
    }

    /**
     * 图片地址 .
     *
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
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
        builder.append("StList [code=").append(code);
        builder.append(",nameList=").append(nameList); 
        builder.append(",desList=").append(desList); 
        builder.append(",status=").append(status); 
        builder.append(",typeList=").append(typeList); 
        builder.append(",unitList=").append(unitList); 
        builder.append(",tableList=").append(tableList); 
        builder.append(",imgAddr=").append(imgAddr); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}