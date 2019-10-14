package com.lj.business.ai.dto;

import java.io.Serializable;
import java.util.Date;

public class MerchantWordsDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3013659739268017404L;

	private String code;

    private String merchantNo;


    private String problemCode;

    private String answerCode;

    private String word;

    private String wordType;

    private Integer count;

    private String status;

    private String remark;

    private Date createDate;

    private Date updateDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getProblemCode() {
        return problemCode;
    }

    public void setProblemCode(String problemCode) {
        this.problemCode = problemCode;
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(String answerCode) {
        this.answerCode = answerCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MerchantWordsDto{");
        sb.append("code='").append(code).append('\'');
        sb.append(", merchantNo='").append(merchantNo).append('\'');
        sb.append(", problemCode='").append(problemCode).append('\'');
        sb.append(", answerCode='").append(answerCode).append('\'');
        sb.append(", word='").append(word).append('\'');
        sb.append(", wordType='").append(wordType).append('\'');
        sb.append(", count=").append(count);
        sb.append(", status='").append(status).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append('}');
        return sb.toString();
    }
}
