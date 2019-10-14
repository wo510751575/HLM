package com.lj.business.ai.dto;

import java.io.Serializable;

public class MatchMerchantProblemDto extends  MerchantPreProblemDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2283907130543447451L;

    /**
     * 回答次数
     */
    private String answerCount;


    private String sessionId;

    private String memberNoGm;//导购编号
    

    public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(String answerCount) {
        this.answerCount = answerCount;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatchMerchantProblemDto [answerCount=");
		builder.append(answerCount);
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append("]");
		return builder.toString();
	}

    
}
