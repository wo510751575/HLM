package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：温情提醒-顾客列表-表头信息
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月6日
 */
public class FindGroupedUnContactMemberHeader implements Serializable {
    private static final long serialVersionUID = 8470329448802434069L;
    
    /**
     * 客户分类CODE .
     */
    private String code;

    /**
     * 客户分类名称 .
     */
    private String typeName;
    
    /**
     * 客户分类类型
                     成单客户：SUCCESS
                     已放弃客户：GIVE_UP
                     紧急客户：URGENCY
                     交叉客户：REPEAT
                     意向客户：INTENTION
                     其他：OTHER .
     */
    private String pmTypeType;

    /**
     * 客户数量 .
     */
    private Long pmCount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPmTypeType() {
        return pmTypeType;
    }

    public void setPmTypeType(String pmTypeType) {
        this.pmTypeType = pmTypeType;
    }

    public Long getPmCount() {
        return pmCount;
    }

    public void setPmCount(Long pmCount) {
        this.pmCount = pmCount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindGroupedUnContactMemberHeader [code=");
        builder.append(code);
        builder.append(", typeName=");
        builder.append(typeName);
        builder.append(", pmTypeType=");
        builder.append(pmTypeType);
        builder.append(", pmCount=");
        builder.append(pmCount);
        builder.append("]");
        return builder.toString();
    }
    
}
