package com.lj.business.ai.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class MerchantPreProblemDto extends PageParamEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5800142041271380941L;

	private String code;

    private String merchantNo;
    
    private String memberNo;
    
    private String memberName;

    private String problemType;

    private String problemContent;

    private String problemWord;

    private Integer targetCount;

    private String status;

    private String remark;

    private Date createDate;

    private Date updateDate;

    private String isAll;

    private MerchantPreAnswerDto answers;

    private String answerContent;

    public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	private List<MerchantWordsDto> words;

    public List<MerchantWordsDto> getWords() {
        return words;
    }

    public void setWords(List<MerchantWordsDto> words) {
        this.words = words;
    }

    public MerchantPreAnswerDto getAnswers() {
        return answers;
    }

    public void setAnswers(MerchantPreAnswerDto answers) {
        this.answers = answers;
    }

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

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemContent() {
        return problemContent;
    }

    public void setProblemContent(String problemContent) {
        this.problemContent = problemContent;
    }

    public String getProblemWord() {
        return problemWord;
    }

    public void setProblemWord(String problemWord) {
        this.problemWord = problemWord;
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

    public String getIsAll() {
        return isAll;
    }

    public void setIsAll(String isAll) {
        this.isAll = isAll;
    }

    public Integer getTargetCount() {
        return targetCount;
    }

    public void setTargetCount(Integer targetCount) {
        this.targetCount = targetCount;
    }
    

    public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MerchantPreProblemDto [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", problemType=");
		builder.append(problemType);
		builder.append(", problemContent=");
		builder.append(problemContent);
		builder.append(", problemWord=");
		builder.append(problemWord);
		builder.append(", targetCount=");
		builder.append(targetCount);
		builder.append(", status=");
		builder.append(status);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", isAll=");
		builder.append(isAll);
		builder.append(", answers=");
		builder.append(answers);
		builder.append(", words=");
		builder.append(words);
		builder.append("]");
		return builder.toString();
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
}
