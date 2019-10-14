package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明：添加微信好友导出
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2018年1月13日
 */
public class AddWxFriendsExportDto implements Serializable {
    private static final long serialVersionUID = 22202663466375325L;

    /**
     * 头像地址
     */
    @ExcelField(title="头像地址", align=2, sort=10)
    private String headAddress;
    
     /**
     * 客户名称
     */
    @ExcelField(title="客户名称", align=2, sort=20)
    private String memberName;
    
    /**
     * 微信openID
     */
    @ExcelField(title="微信OpenId", align=2, sort=30)
    private String wxOpenId;
    
     /**
     * 手机
     */
    @ExcelField(title="手机(使用文本格式)", align=3, sort=40)
    private String mobile;
    
    /**
     * QQ号
     */
    @ExcelField(title="QQ号(使用文本格式)", align=3, sort=50)
    private String noQQ;
    
    /**
     * 性别
     * 男.
        MALE("男"),
     ** 女. *
        FEMALE("女");
     */
    @ExcelField(title="性别(男：MALE,女：FEMALE)", align=2, sort=60)
    private String sex;
    
    /**
     * 标签
     */
    @ExcelField(title="标签(多个标签使用英文逗号分隔)", align=3, sort=70)
    private String labelName;
    
    /**
     * 客户来源 :
     */
    @ExcelField(title="客户来源", align=0, sort=80)
    private String memberSrc;
    
    /**
     * 终端微信号
     */
    @ExcelField(title="终端微信号", align=2, sort=90)
    private String noWxGm;
    
    /**
     * 添加状态
     */
    @ExcelField(title="添加状态", align=2, sort=100)
    private String addStatus;
    
    /**
     * 录入时间
     */
    @ExcelField(title="录入时间", align=2, sort=150)
    private String remark;

    public String getHeadAddress() {
        return headAddress;
    }

    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress;
    }

    public String getAddStatus() {
        return addStatus;
    }

    public void setAddStatus(String addStatus) {
        this.addStatus = addStatus;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNoQQ() {
        return noQQ;
    }

    public void setNoQQ(String noQQ) {
        this.noQQ = noQQ;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getMemberSrc() {
        return memberSrc;
    }

    public void setMemberSrc(String memberSrc) {
        this.memberSrc = memberSrc;
    }

    public String getNoWxGm() {
        return noWxGm;
    }

    public void setNoWxGm(String noWxGm) {
        this.noWxGm = noWxGm;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddWxFriendsExportDto [headAddress=");
        builder.append(headAddress);
        builder.append(", memberName=");
        builder.append(memberName);
        builder.append(", wxOpenId=");
        builder.append(wxOpenId);
        builder.append(", mobile=");
        builder.append(mobile);
        builder.append(", noQQ=");
        builder.append(noQQ);
        builder.append(", sex=");
        builder.append(sex);
        builder.append(", labelName=");
        builder.append(labelName);
        builder.append(", memberSrc=");
        builder.append(memberSrc);
        builder.append(", noWxGm=");
        builder.append(noWxGm);
        builder.append(", addStatus=");
        builder.append(addStatus);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }
    
}
