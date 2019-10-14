package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * 类说明：分组列表dto，包含表头和详细
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
public class FindGroupedUnContactMemberPmType implements Serializable {
    private static final long serialVersionUID = -613942225143315037L;

    private FindGroupedUnContactMemberHeader header;
    
    private List<FindUnContactMemberReturn> details;

    public FindGroupedUnContactMemberHeader getHeader() {
        return header;
    }

    public void setHeader(FindGroupedUnContactMemberHeader header) {
        this.header = header;
    }

    public List<FindUnContactMemberReturn> getDetails() {
        return details;
    }

    public void setDetails(List<FindUnContactMemberReturn> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindGroupedUnContactMemberPmType [header=");
        builder.append(header);
        builder.append(", details=");
        builder.append(details);
        builder.append("]");
        return builder.toString();
    }

}
