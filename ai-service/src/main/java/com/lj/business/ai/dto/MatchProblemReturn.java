package com.lj.business.ai.dto;

import java.io.Serializable;
import java.util.List;

public class MatchProblemReturn<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -847145298865662946L;

	private Integer matchCode;

	private String matchMessage;
	
	private String sessionId;

	private List<T> result;

	public Integer getMatchCode() {
		return matchCode;
	}

	public void setMatchCode(Integer matchCode) {
		this.matchCode = matchCode;
	}

	public String getMatchMessage() {
		return matchMessage;
	}

	public void setMatchMessage(String matchMessage) {
		this.matchMessage = matchMessage;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
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
		builder.append("MatchProblemReturn [matchCode=");
		builder.append(matchCode);
		builder.append(", matchMessage=");
		builder.append(matchMessage);
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append(", result=");
		builder.append(result);
		builder.append("]");
		return builder.toString();
	}

}
