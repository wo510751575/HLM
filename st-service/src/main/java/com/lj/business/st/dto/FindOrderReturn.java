package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FindOrderReturn implements Serializable {

    private static final long serialVersionUID = -7478474474791509783L;

    /**
     * 订单总量
     */
    private Integer totalOrderNum;

    /**
     * 最大订单量
     */
    private Map<String, Object> maxDatas;

    /**
     * 日期成单量
     */
    private List<Map<String, Object>> datas;

    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
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
