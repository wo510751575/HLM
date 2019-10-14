package com.lj.business.ai.emuns;

public enum WordType {
    ANSWER_WORD("回答关键词", "1"), PROBLEM_WORD("问题关键词", "2");

    private String type;
    private String value;

    private WordType(String type, String value) {
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
