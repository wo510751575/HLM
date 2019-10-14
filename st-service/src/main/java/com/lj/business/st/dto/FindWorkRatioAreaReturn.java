package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FindWorkRatioAreaReturn implements Serializable {

    private static final long serialVersionUID = -7478474474791509783L;

    /**
     * 最大数据
     */
    private Map<String, Object> maxDatas;

    /**
     * 省份数据
     */
    private List<Map<String, Object>> provinceList;

    /**
     * 区域数据
     */
    private List<Map<String, Object>> areaList;

    public Map<String, Object> getMaxDatas() {
        return maxDatas;
    }

    public void setMaxDatas(Map<String, Object> maxDatas) {
        this.maxDatas = maxDatas;
    }

    public List<Map<String, Object>> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<Map<String, Object>> provinceList) {
        this.provinceList = provinceList;
    }

    public List<Map<String, Object>> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Map<String, Object>> areaList) {
        this.areaList = areaList;
    }
}
