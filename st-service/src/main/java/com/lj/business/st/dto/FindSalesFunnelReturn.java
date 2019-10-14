package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FindSalesFunnelReturn implements Serializable {

    private static final long serialVersionUID = -7478474474791509783L;

    /**
     * 客户总量
     */
    private Integer totalCustomerNum;

    /**
     * 意向客户
     */
    private Integer intentionCustomerNum;

    /**
     * 成单量
     */
    private Integer orderNum;

    /**
     * 最大量
     */
    private Map<String, Object> maxDatas;

    /**
     * 日期量
     */
    private List<Map<String, Object>> datas;

    public Integer getTotalCustomerNum() {
        return totalCustomerNum == null ? 0 : totalCustomerNum;
    }

    public void setTotalCustomerNum(Integer totalCustomerNum) {
        this.totalCustomerNum = totalCustomerNum;
    }

    public Integer getIntentionCustomerNum() {
        return intentionCustomerNum == null ? 0 : intentionCustomerNum;
    }

    public void setIntentionCustomerNum(Integer intentionCustomerNum) {
        this.intentionCustomerNum = intentionCustomerNum;
    }

    public Integer getOrderNum() {
        return orderNum == null ? 0 : orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Map<String, Object> getMaxDatas() {
        return maxDatas;
    }

    public void setMaxDatas(Map<String, Object> maxDatas) {
        this.maxDatas = maxDatas;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }
}
