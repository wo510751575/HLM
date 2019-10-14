package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FindKeepAbondonReturn implements Serializable {

    private static final long serialVersionUID = -7478474474791509783L;

    /**
     * 客户总量
     */
    private Integer totalCustomerNum;

    /**
     * 最大量
     */
    private Map<String, Object> maxDatas;

    /**
     * 日期量
     */
    private List<Map<String, Object>> datas;

    public Integer getTotalCustomerNum() {
        return totalCustomerNum;
    }

    public void setTotalCustomerNum(Integer totalCustomerNum) {
        this.totalCustomerNum = totalCustomerNum;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }

    public Map<String, Object> getMaxDatas() {
        return maxDatas;
    }

    public void setMaxDatas(Map<String, Object> maxDatas) {
        this.maxDatas = maxDatas;
    }
}
