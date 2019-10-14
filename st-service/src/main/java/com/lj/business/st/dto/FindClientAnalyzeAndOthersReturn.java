package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FindClientAnalyzeAndOthersReturn implements Serializable {

    private static final long serialVersionUID = -7478474474791509783L;

    /**
     * 描述
     */
    private Map<String, Object> descMap;

    /**
     * 性别
     */
    private Map<String, Object> sexMap;

    /**
     * 年龄
     */
    private List<Map<String, Object>> ageList;

    /**
     * 客户职业
     */
    private List<Map<String, Object>> occupationList;

    /**
     * 客户兴趣
     */
    private List<Map<String, Object>> interestList;

    public Map<String, Object> getDescMap() {
        return descMap;
    }

    public void setDescMap(Map<String, Object> descMap) {
        this.descMap = descMap;
    }

    public Map<String, Object> getSexMap() {
        return sexMap;
    }

    public void setSexMap(Map<String, Object> sexMap) {
        this.sexMap = sexMap;
    }

    public List<Map<String, Object>> getAgeList() {
        return ageList;
    }

    public void setAgeList(List<Map<String, Object>> ageList) {
        this.ageList = ageList;
    }

    public List<Map<String, Object>> getOccupationList() {
        return occupationList;
    }

    public void setOccupationList(List<Map<String, Object>> occupationList) {
        this.occupationList = occupationList;
    }

    public List<Map<String, Object>> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Map<String, Object>> interestList) {
        this.interestList = interestList;
    }
}
