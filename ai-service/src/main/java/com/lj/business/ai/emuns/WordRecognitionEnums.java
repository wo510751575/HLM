package com.lj.business.ai.emuns;

public enum WordRecognitionEnums {
    N("n", "名词"),
    NR("nr", "人名"),
    NS("ns", "地名"),
    T("t", "时间词"),
    V("v", "动词"),
    VN("vn", "动名词");
    private String key;
    private String value;

    private WordRecognitionEnums(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

}
