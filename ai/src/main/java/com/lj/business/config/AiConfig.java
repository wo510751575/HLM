package com.lj.business.config;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AiConfig {
	/**
	 * 会话超时时间
	 */

	private Long sessionTimeiout;

	/**
	 * 匹配数据量参数控制
	 */
	private Map<Integer, Integer> matchParam;

	/**
	 * 一次会话同一关键词匹配次数
	 */
	private Integer matchCount;
	
	
	private Integer defualtCount=10;

	public Long getSessionTimeiout() {
		return sessionTimeiout;
	}

	public void setSessionTimeiout(Long sessionTimeiout) {
		this.sessionTimeiout = sessionTimeiout;
	}

	public Map<Integer, Integer> getMatchParam() {
		return matchParam;
	}

	public void setMatchParam(Map<Integer, Integer> matchParam) {
		this.matchParam = matchParam;
	}

	public Integer getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(Integer matchCount) {
		this.matchCount = matchCount;
	}

	public Integer getDefualtCount() {
		return defualtCount;
	}

	public void setDefualtCount(Integer defualtCount) {
		this.defualtCount = defualtCount;
	}
	

}
