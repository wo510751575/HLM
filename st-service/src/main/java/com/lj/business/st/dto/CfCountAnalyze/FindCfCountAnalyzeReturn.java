package com.lj.business.st.dto.CfCountAnalyze;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FindCfCountAnalyzeReturn implements Serializable {

    /**
     * Generate cron.
     *
     * @param
     * @param
     * @throws
     */
    private static final long serialVersionUID = 7665799489051536232L;

    /**
     * 总跟踪次数 .
     */
    private Long numCf;

    /**
     * 1-5次跟踪占比 .
     */
    private String ratioCfFive;

    /**
     * 1-5次跟踪数量 .
     */
    private Integer numCfFive;

    /**
     * 6-10次跟踪占比 .
     */
    private String ratioCfTen;

    /**
     * 6-10次跟踪数量 .
     */
    private Integer numCfTen;

    /**
     * 10-15次跟踪占比 .
     */
    private String ratioCfFifteen;

    /**
     * 10-15次跟踪数量 .
     */
    private Integer numCfFifteen;

    /**
     * 16次以上跟踪占比 .
     */
    private String ratioCfSixteen;

    /**
     * 16次跟踪数量 .
     */
    private Integer numCfSixteen;

    public Long getNumCf() {
        return numCf;
    }

    public void setNumCf(Long numCf) {
        this.numCf = numCf;
    }

    public String getRatioCfFive() {
        return ratioCfFive;
    }

    public void setRatioCfFive(String ratioCfFive) {
        this.ratioCfFive = ratioCfFive;
    }

    public Integer getNumCfFive() {
        return numCfFive;
    }

    public void setNumCfFive(Integer numCfFive) {
        this.numCfFive = numCfFive;
    }

    public String getRatioCfTen() {
        return ratioCfTen;
    }

    public void setRatioCfTen(String ratioCfTen) {
        this.ratioCfTen = ratioCfTen;
    }

    public Integer getNumCfTen() {
        return numCfTen;
    }

    public void setNumCfTen(Integer numCfTen) {
        this.numCfTen = numCfTen;
    }

    public String getRatioCfFifteen() {
        return ratioCfFifteen;
    }

    public void setRatioCfFifteen(String ratioCfFifteen) {
        this.ratioCfFifteen = ratioCfFifteen;
    }

    public Integer getNumCfFifteen() {
        return numCfFifteen;
    }

    public void setNumCfFifteen(Integer numCfFifteen) {
        this.numCfFifteen = numCfFifteen;
    }

    public String getRatioCfSixteen() {
        return ratioCfSixteen;
    }

    public void setRatioCfSixteen(String ratioCfSixteen) {
        this.ratioCfSixteen = ratioCfSixteen;
    }

    public Integer getNumCfSixteen() {
        return numCfSixteen;
    }

    public void setNumCfSixteen(Integer numCfSixteen) {
        this.numCfSixteen = numCfSixteen;
    }
}
