package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.List;

public class FindGroupedUnContactMemberReturn implements Serializable {
    private static final long serialVersionUID = 2700569366450546033L;

    /**
     * 顾客总数
     */
    private int pmTotal = 0;
    
    /**
     * 顾客分组及分组明细
     */
    private List<FindGroupedUnContactMemberPmType> PmTypes;

    public int getPmTotal() {
        return pmTotal;
    }

    public void setPmTotal(int pmTotal) {
        this.pmTotal = pmTotal;
    }

    public List<FindGroupedUnContactMemberPmType> getPmTypes() {
        return PmTypes;
    }

    public void setPmTypes(List<FindGroupedUnContactMemberPmType> pmTypes) {
        PmTypes = pmTypes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindGroupedUnContactMemberReturn [pmTotal=");
        builder.append(pmTotal);
        builder.append(", PmTypes=");
        builder.append(PmTypes);
        builder.append("]");
        return builder.toString();
    }
    
}
