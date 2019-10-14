package com.lj.business.ai.emuns;

public enum AnswerType {
    PRE("预设类型", "1"), AUTO("收录类型", "2");
    private String type;
    private String value;


    private AnswerType(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;

    }
}
