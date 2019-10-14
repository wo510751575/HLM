package com.lj.business.cf.dto.socialTask;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：微信提醒返回的标签dto类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月11日
 */
public class FindStIndexPageReturnHcLabelDto implements Serializable {
    private static final long serialVersionUID = 3243315524948076727L;

    /**
     * 客户标签CODE .
     */
    private String code;

    /**
     * 客户标签名称 .
     */
    private String labelName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindStIndexPageReturnHcLabelDto [code=");
        builder.append(code);
        builder.append(", labelName=");
        builder.append(labelName);
        builder.append("]");
        return builder.toString();
    }
    
}
