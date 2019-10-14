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
public class ImportAddWxFriendsDto implements Serializable {
    private static final long serialVersionUID = 22202663466375325L;

     /**
     * 客户名称
     */
    @ExcelField(title="客户名称", align=2, sort=20)
    private String memberName;
    

     /**
     * 手机
     */
    @ExcelField(title="手机（使用文本格式）", align=3, sort=30)
    private String mobile;
    
    /**
     * 验证话术
     */
    @ExcelField(title="验证话术（可不填）", align=3, sort=40)
    private String validation;

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
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






    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddWxFriendsDto [memberName=");
        builder.append(memberName);
        builder.append(", mobile=");
        builder.append(mobile);

        builder.append("]");
        return builder.toString();
    }

}
