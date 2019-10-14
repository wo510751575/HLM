package com.lj.oms.utils.excel.dto;

import java.io.Serializable;
import com.lj.oms.utils.excel.annotation.ExcelField;

/**
 * 
 * 
 * 类说明：添加微信好友导入
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
public class AddWxFriendsDto implements Serializable {
    private static final long serialVersionUID = 22202663466375325L;

     /**
     * 客户名称
     */
    @ExcelField(title="客户名称", align=2, sort=20)
    private String memberName;
    
    /**
     * 微信openID
     */
    @ExcelField(title="微信号", align=2, sort=30)
    private String noWx;
    
     /**
     * 手机
     */
    @ExcelField(title="手机(使用文本格式)", align=2, sort=40)
    private String mobile;
    
    /**
     * QQ号
     */
    @ExcelField(title="QQ号(使用文本格式)", align=2, sort=50)
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
    @ExcelField(title="标签(多个标签使用英文逗号分隔)",  align=3, sort=70)
    private String labelName;
    
    /**
     * 客户来源 :
     */
    @ExcelField(title="客户来源", align=2, sort=80)
    private String memberSrc;
    
    /**
     * 终端微信号
     */
    @ExcelField(title="终端微信号", align=2, sort=90)
    private String noWxGm;

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

    public String getNoWx() {
        return noWx;
    }

    public void setNoWx(String noWx) {
        this.noWx = noWx;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddWxFriendsDto [memberName=");
        builder.append(memberName);
        builder.append(", noWx=");
        builder.append(noWx);
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
        builder.append("]");
        return builder.toString();
    }

}
